package models.daos.client;

import models.beans.Service;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>Remote Access for DAO
 */

public abstract class DAOService {
	public static Service getService(int id) {
		if (id == 0)
			return new Service();
		String query = "select * from `service` where `id`="+id+" limit 1";
		return (Service)communication.SocketCommunicator.getInstance().sendQuery(query);
	}

	public static String getSQLDeleting(Service service) {
		return models.daos.server.DAOService.getSQLDeleting(service);
	}

	public static String getSQLDeleting() {
		return models.daos.server.DAOService.getSQLDeleting();
	}

	public static String getSQLWriting(Service service) {
		return models.daos.server.DAOService.getSQLWriting(service);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLWritingByPreparedStatement(Service service) {
		return models.daos.server.DAOService.getSQLWritingByPreparedStatement(service);
	}

	public static int write(Service service) {
		int id = (Integer)communication.SocketCommunicator.getInstance().sendQuery(service);
		service.setId(id);
		return id;
	}

	public static void writeByPreparedStatement(Service service) {
		utils.SGBDConfig.InsertUpdateSQLQueries writingQuery = getSQLWritingByPreparedStatement(service);
		communication.SocketCommunicator.getInstance().sendQuery(writingQuery);
	}

	public static void delete(Service service){
		communication.SocketCommunicator.getInstance().sendQuery(getSQLDeleting(service));
	}

	public static void deleteAll(){
		communication.SocketCommunicator.getInstance().sendQuery(getSQLDeleting());
	}

	public static java.util.List<Service> getListInstances(){
		return getListInstances("");
	}

	public static java.util.List<Service> getListInstances(String whereCondition){
		String query = "select * from service";
		if (!whereCondition.trim().equals("")){
			if (!whereCondition.trim().toLowerCase().equals("") && !whereCondition.trim().toLowerCase().startsWith("where")){
				query += " where ";
			}
			query += " "+whereCondition;
		}
		return getListInstancesBySQLQuery(query);
	}

@SuppressWarnings("unchecked")
	public static java.util.List<Service> getListInstancesBySQLQuery(String sqlQuery){
		java.util.List<Service> list = new java.util.ArrayList<Service>();
		Object response = communication.SocketCommunicator.getInstance().sendQuery(sqlQuery);
		if (response != null){
			if (response instanceof java.util.List<?>){
				list = (java.util.List<Service>)response;
			}
			else{
				list.add((Service)response);
			}
		}
		return list;
	}

	public static String getSQLUpdateForDesignation(Service service){
		return models.daos.server.DAOService.getSQLUpdateForDesignation(service);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForDesignationByPreparedStatement(Service service){
		return models.daos.server.DAOService.getSQLUpdateForDesignationByPreparedStatement(service);
	}

	public static void updateDesignation(Service service){
		String sqlUpdate = getSQLUpdateForDesignation(service);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForPrix(Service service){
		return models.daos.server.DAOService.getSQLUpdateForPrix(service);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForPrixByPreparedStatement(Service service){
		return models.daos.server.DAOService.getSQLUpdateForPrixByPreparedStatement(service);
	}

	public static void updatePrix(Service service){
		String sqlUpdate = getSQLUpdateForPrix(service);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getConcatIDs(){
		return getConcatIDs("");
	}

	public static String getConcatIDs(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return (String)communication.SocketCommunicator.getInstance().sendQuery("select group_concat(id) from `service` "+whereCondition);
	}

	public static int getCountInTable(){
		return getCountInTable("");
	}

	public static int getCountInTable(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return (Integer)communication.SocketCommunicator.getInstance().sendQuery("select count(id) from `service` "+whereCondition);
	}

	public static int getCountInTableFromServer(){
		return getCountInTableFromServer("");
	}

	public static int getCountInTableFromServer(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return ((Integer)communication.SocketCommunicator.getInstance().sendQuery("select count(id) from `service` "+whereCondition)).intValue();
	}

	public static String getSQLTuple(Service service){
		return models.daos.server.DAOService.getSQLTuple(service);
	}

	public static String getSQLStructure(boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOService.getSQLStructure(foreignKeyCheck, addDropTable);
	}

	public static String getSQLStructure(boolean addDropTable){
		return models.daos.server.DAOService.getSQLStructure(addDropTable);
	}

	public static String getSQLStructure(){
		return getSQLStructure(true);
	}

	public static String getSQLContent(boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOService.getSQLContent(foreignKeyCheck, addDropTable);
	}

	public static String getSQLContent(java.util.List<Service> list, boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOService.getSQLContent(list, foreignKeyCheck, addDropTable);
	}

	public static String showSQLStructure(){
		return models.daos.server.DAOService.showSQLStructure();
	}

	public static String getTableName(){
		return models.daos.server.DAOService.getTableName();
	}
	
	public static java.util.List<models.beans.ClientConsommeService> getListOfClientConsommeServicesService(models.beans.Service service){
		java.util.List<models.beans.ClientConsommeService> listOfClientConsommeServicesService = models.daos.client.DAOClientConsommeService.getListInstances("where idService='"+service.getId().intValue()+"'");
		return listOfClientConsommeServicesService;
	}
}