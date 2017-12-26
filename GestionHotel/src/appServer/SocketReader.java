package appServer;

import gui.utils.GUIReportEditor;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import utils.DataWrapper;
import utils.SGBDConfig;
import utils.StringUtils;
import utils.SGBDConfig.InsertUpdateSQLQueries;
import utils.SGBDConfig.UpdateDeleteSQLQueries;

public class SocketReader implements Runnable{
	private Socket socket;
	
	public SocketReader(Socket socket){
		this.socket = socket;
	}
	
	public void start(){
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void run(){
		try {
			long minRunningMemory = (1024*1024);
			Runtime runtime = Runtime.getRuntime();
			if (runtime.freeMemory() <= minRunningMemory){
				runtime.gc();
				runtime.gc();
			}
			
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			
//			String decompressedQuery = ois.readObject().toString();
//			decompressedQuery = utils.StringUtils.deCompressString(decompressedQuery);
//			Object objectRequest = utils.FilesAndLaunchUtils.getObjectFromHex(decompressedQuery);
			Object objectRequest = ois.readObject();
			StringUtils.printDebug ("Server says - Request : "+objectRequest);
			
			Object objectResponse = getObjectResponse(objectRequest);
//			String responseHex = utils.FilesAndLaunchUtils.getHexFromObject((Serializable)objectResponse);
//			responseHex = utils.StringUtils.compressString(responseHex);
			StringUtils.printDebug ("Server says - Response : "+objectResponse);
			
			oos.writeObject(objectResponse);
			oos.flush();
			
			oos.close();
			ois.close();
			socket.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private Object getObjectResponse(Object objectRequest){
		if (objectRequest instanceof Map<?, ?>){
			try{
				Map<?, ?> parameters = (Map<?, ?>)objectRequest;
				
				File file = utils.FilesAndLaunchUtils.getFileFromData((byte[])parameters.get("reportModel"), File.createTempFile("fileReportModel", ".xml").getAbsolutePath());
				String sqlQuery = (String)parameters.get("sqlQuery");
				
				parameters.remove("sqlQuery");
				parameters.remove("reportModel");
				
				GUIReportEditor reportEditor = GUIReportEditor.getInstance(utils.SGBDConfig.getInstance());
				File pdfFile = reportEditor.generateReportAsPDF(file, sqlQuery, (Map<Object, Object>)parameters);
				if (pdfFile == null){
					return "";
				}
				else{
					byte[] data = utils.FilesAndLaunchUtils.getDataFromFile(pdfFile);
					return data;
				}
			}
			catch (Exception e){
				return "";
			}
		}
		else if (objectRequest instanceof UpdateDeleteSQLQueries){
			SGBDConfig.getInstance().sendQueries((UpdateDeleteSQLQueries)objectRequest);
			return "";
		}
		else if (objectRequest instanceof InsertUpdateSQLQueries){
			SGBDConfig.getInstance().sendQueriesByPreparedStatement((InsertUpdateSQLQueries)objectRequest);
			return "";
		}
		else if (objectRequest instanceof DataWrapper){
			DataWrapper dataWrapper = (DataWrapper)objectRequest;
			if (dataWrapper.getRequestForServer().equalsIgnoreCase("importSQLFromFile")){
				byte[] data = dataWrapper.getData();
				try{
					File file = File.createTempFile("importationData", "import");
					utils.FilesAndLaunchUtils.getFileFromData(data, file.getAbsolutePath());
					utils.SGBDConfig.getInstance().importFromFile(file);
					file.delete();
				}
				catch (Exception e){
					utils.StringUtils.printDebug(e);
				}
			}
		}
		else if (objectRequest instanceof String){
			String query = (String)objectRequest;
			if (query.equalsIgnoreCase("forTestingServer")){
				return "OK";
			}
			else if (query.toLowerCase().startsWith("getDateTimeFromServer".toLowerCase())){
				return StringUtils.getDateTimeOfThisMoment();
			}
			else if (query.toLowerCase().startsWith("getDateFromServer".toLowerCase())){
				return StringUtils.getDateOfThisDay();
			}
			else if (query.equalsIgnoreCase("getSQLExportingString")){
				return utils.SGBDConfig.getInstance().exportToString();
			}
			else if (query.startsWith("saveAllData")){
				String[] parts = query.split("/");
				String fileName = parts[1].split("=")[1];
				return utils.SGBDConfig.getInstance().saveAllData(fileName);
			}
			else if (query.equals("getListSavedDataFiles")){
				List<String> list = new ArrayList<String>();
				
				return list;
			}
			else if (query.toLowerCase().startsWith("select * from") || query.toLowerCase().startsWith("select id from")){
				int index1 = query.toLowerCase().indexOf("from")+"from".length();
				int index2 = query.toLowerCase().indexOf("where");
				
				if (index2 == -1)
					index2 = query.toLowerCase().indexOf("limit");
				
				if (index2 == -1)
					index2 = query.length();
				
				String tableName = query.substring(index1, index2).trim();
				if (tableName.startsWith("`"))
					tableName = tableName.substring(1);
				
				if (tableName.endsWith("`"))
					tableName = tableName.substring(0, tableName.length()-1);
				
				if (tableName.contains(",")){
					tableName = tableName.split(",")[0].trim();
				}
				
				StringUtils.printDebug ("Server Says - Table Name : "+tableName);
				
				Object response = null;
				
				/*****************************************************/
				/******** Here will be insert the fetch code *********/
				/*****************************************************/
				
				List<Class<?>> listClasses = utils.FilesAndLaunchUtils.getClasses("models.beans");

				for (Class<?> clazz : listClasses){
					String className = clazz.getSimpleName();
					if (className.equalsIgnoreCase(tableName)){
						Class<?> clazzDAO = null;
						try{
							clazzDAO = Class.forName("models.daos.server.DAO"+className);
						}
						catch (Exception e){
							utils.StringUtils.printDebug(e);
						}
						
						Class<?>[] types = {String.class};
						Object[] values = {query};
						
						List<?> list = null;
						try{
							list = (List<?>)clazzDAO.getMethod("getListInstancesBySQLQuery", types).invoke(null, values);
						}
						catch (Exception e){
							StringUtils.printDebug(e);
						}
						response = list;
						
						break;
					}
				}
				
				StringUtils.printDebug ("Server Says - instance : " + response);
				if (response instanceof List<?>){
					if (((List<?>)response).size() == 1)
						return ((List<?>)response).get(0);
					else if (((List<?>)response).size() > 1)
						return response;
				}
					
				return response;
			}
			else if (query.toLowerCase().startsWith("select count")){
				try {
					ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults(query);
					if (data.next())
						return data.getInt(1);
				}catch (Exception e){
					utils.StringUtils.printDebug("Server Says (Exception) - ");
					utils.StringUtils.printDebug(e);
				}
				return 0;
			}
			else if (query.toLowerCase().startsWith("select sum")){
				try {
					ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults(query);
					if (data.next())
						return data.getObject(1);
				}catch (Exception e){
					utils.StringUtils.printDebug("Server Says (Exception) - ");
					utils.StringUtils.printDebug(e);
				}
				return 0;
			}
			else if (query.toLowerCase().startsWith("select group_concat")){
				String group_concat = "";
				try {
					ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults(query);
					if (data.next()){
						group_concat = data.getString(1);
						if (group_concat == null){
							group_concat = "0";
						}
						else{
							if (group_concat.endsWith(",")){
								group_concat = group_concat.substring(0, group_concat.length()-1);
							}
						}
					}
				}catch (Exception e){
					utils.StringUtils.printDebug("Server Says (Exception) - ");
					utils.StringUtils.printDebug(e);
				}
				
				return group_concat;
			}
			else{ // we suppose that this part handle update, delete and truncate SQL queries
				SGBDConfig.getInstance().sendQuery(query);
				return "";
			}
			
		}
		/*****************************************************/
		/**** Here will be insert the update/deletion code****/
		/*****************************************************/
		else{
			List<Class<?>> listClasses = utils.FilesAndLaunchUtils.getClasses("models.beans");
			for (Class<?> clazz : listClasses){
				if (clazz.isInstance(objectRequest)){
					Class<?> clazzDAO = null;
					try{
						clazzDAO = Class.forName("models.daos.server.DAO"+objectRequest.getClass().getSimpleName());
					}
					catch (Exception e){
						utils.StringUtils.printDebug(e);
					}
					
					int id = 0;
					try{
						Class<?>[] paramsTypes0 = {};
						Object[] paramsValues0 = {};
						
						Class<?>[] paramsTypes1 = {clazz};
						Object[] paramsValues1 = {objectRequest};
						
						id = (Integer)clazz.getMethod("getId", paramsTypes0).invoke(objectRequest, paramsValues0);
						
						if (id >= 0){
							clazzDAO.getMethod("writeByPreparedStatement", paramsTypes1).invoke(objectRequest, paramsValues1);
						}
						else{
							Class<?>[] paramsTypes2 = {Integer.class};
							Object[] paramsValues2 = {Math.abs(id)};
							
							clazz.getMethod("setId", paramsTypes2).invoke(objectRequest, paramsValues2);
							clazzDAO.getMethod("delete", paramsTypes1).invoke(objectRequest, paramsValues1);
							
							Class<?>[] paramsTypes3 = {Integer.class};
							Object[] paramsValues3 = {0};
							
							clazz.getMethod("setId", paramsTypes3).invoke(objectRequest, paramsValues3);
						}
						
						return (Integer)clazz.getMethod("getId", paramsTypes0).invoke(objectRequest, paramsValues0);
					}
					catch (Exception e){
						StringUtils.printDebug(e);
					}
					break;
				}
			}
		}
		
		return "";
	}
}