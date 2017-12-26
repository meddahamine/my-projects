package models.daos.client;

import models.beans.ClientConsommeService;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>Remote Access for DAO
 */

public abstract class DAOClientConsommeService {
	public static ClientConsommeService getClientConsommeService(int id) {
		if (id == 0)
			return new ClientConsommeService();
		String query = "select * from `clientConsommeService` where `id`="+id+" limit 1";
		return (ClientConsommeService)communication.SocketCommunicator.getInstance().sendQuery(query);
	}

	public static String getSQLDeleting(ClientConsommeService clientConsommeService) {
		return models.daos.server.DAOClientConsommeService.getSQLDeleting(clientConsommeService);
	}

	public static String getSQLDeleting() {
		return models.daos.server.DAOClientConsommeService.getSQLDeleting();
	}

	public static String getSQLWriting(ClientConsommeService clientConsommeService) {
		return models.daos.server.DAOClientConsommeService.getSQLWriting(clientConsommeService);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLWritingByPreparedStatement(ClientConsommeService clientConsommeService) {
		return models.daos.server.DAOClientConsommeService.getSQLWritingByPreparedStatement(clientConsommeService);
	}

	public static int write(ClientConsommeService clientConsommeService) {
		int id = (Integer)communication.SocketCommunicator.getInstance().sendQuery(clientConsommeService);
		clientConsommeService.setId(id);
		return id;
	}

	public static void writeByPreparedStatement(ClientConsommeService clientConsommeService) {
		utils.SGBDConfig.InsertUpdateSQLQueries writingQuery = getSQLWritingByPreparedStatement(clientConsommeService);
		communication.SocketCommunicator.getInstance().sendQuery(writingQuery);
	}

	public static void delete(ClientConsommeService clientConsommeService){
		communication.SocketCommunicator.getInstance().sendQuery(getSQLDeleting(clientConsommeService));
	}

	public static void deleteAll(){
		communication.SocketCommunicator.getInstance().sendQuery(getSQLDeleting());
	}

	public static java.util.List<ClientConsommeService> getListInstances(){
		return getListInstances("");
	}

	public static java.util.List<ClientConsommeService> getListInstances(String whereCondition){
		String query = "select * from clientConsommeService";
		if (!whereCondition.trim().equals("")){
			if (!whereCondition.trim().toLowerCase().equals("") && !whereCondition.trim().toLowerCase().startsWith("where")){
				query += " where ";
			}
			query += " "+whereCondition;
		}
		return getListInstancesBySQLQuery(query);
	}

@SuppressWarnings("unchecked")
	public static java.util.List<ClientConsommeService> getListInstancesBySQLQuery(String sqlQuery){
		java.util.List<ClientConsommeService> list = new java.util.ArrayList<ClientConsommeService>();
		Object response = communication.SocketCommunicator.getInstance().sendQuery(sqlQuery);
		if (response != null){
			if (response instanceof java.util.List<?>){
				list = (java.util.List<ClientConsommeService>)response;
			}
			else{
				list.add((ClientConsommeService)response);
			}
		}
		return list;
	}

	public static String getSQLUpdateForIdClient(ClientConsommeService clientConsommeService){
		return models.daos.server.DAOClientConsommeService.getSQLUpdateForIdClient(clientConsommeService);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIdClientByPreparedStatement(ClientConsommeService clientConsommeService){
		return models.daos.server.DAOClientConsommeService.getSQLUpdateForIdClientByPreparedStatement(clientConsommeService);
	}

	public static void updateIdClient(ClientConsommeService clientConsommeService){
		String sqlUpdate = getSQLUpdateForIdClient(clientConsommeService);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForIdService(ClientConsommeService clientConsommeService){
		return models.daos.server.DAOClientConsommeService.getSQLUpdateForIdService(clientConsommeService);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIdServiceByPreparedStatement(ClientConsommeService clientConsommeService){
		return models.daos.server.DAOClientConsommeService.getSQLUpdateForIdServiceByPreparedStatement(clientConsommeService);
	}

	public static void updateIdService(ClientConsommeService clientConsommeService){
		String sqlUpdate = getSQLUpdateForIdService(clientConsommeService);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForPrixService(ClientConsommeService clientConsommeService){
		return models.daos.server.DAOClientConsommeService.getSQLUpdateForPrixService(clientConsommeService);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForPrixServiceByPreparedStatement(ClientConsommeService clientConsommeService){
		return models.daos.server.DAOClientConsommeService.getSQLUpdateForPrixServiceByPreparedStatement(clientConsommeService);
	}

	public static void updatePrixService(ClientConsommeService clientConsommeService){
		String sqlUpdate = getSQLUpdateForPrixService(clientConsommeService);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForDateConsommationService(ClientConsommeService clientConsommeService){
		return models.daos.server.DAOClientConsommeService.getSQLUpdateForDateConsommationService(clientConsommeService);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForDateConsommationServiceByPreparedStatement(ClientConsommeService clientConsommeService){
		return models.daos.server.DAOClientConsommeService.getSQLUpdateForDateConsommationServiceByPreparedStatement(clientConsommeService);
	}

	public static void updateDateConsommationService(ClientConsommeService clientConsommeService){
		String sqlUpdate = getSQLUpdateForDateConsommationService(clientConsommeService);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getConcatIDs(){
		return getConcatIDs("");
	}

	public static String getConcatIDs(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return (String)communication.SocketCommunicator.getInstance().sendQuery("select group_concat(id) from `clientConsommeService` "+whereCondition);
	}

	public static int getCountInTable(){
		return getCountInTable("");
	}

	public static int getCountInTable(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return (Integer)communication.SocketCommunicator.getInstance().sendQuery("select count(id) from `clientConsommeService` "+whereCondition);
	}

	public static int getCountInTableFromServer(){
		return getCountInTableFromServer("");
	}

	public static int getCountInTableFromServer(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return ((Integer)communication.SocketCommunicator.getInstance().sendQuery("select count(id) from `clientConsommeService` "+whereCondition)).intValue();
	}

	public static String getSQLTuple(ClientConsommeService clientConsommeService){
		return models.daos.server.DAOClientConsommeService.getSQLTuple(clientConsommeService);
	}

	public static String getSQLStructure(boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOClientConsommeService.getSQLStructure(foreignKeyCheck, addDropTable);
	}

	public static String getSQLStructure(boolean addDropTable){
		return models.daos.server.DAOClientConsommeService.getSQLStructure(addDropTable);
	}

	public static String getSQLStructure(){
		return getSQLStructure(true);
	}

	public static String getSQLContent(boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOClientConsommeService.getSQLContent(foreignKeyCheck, addDropTable);
	}

	public static String getSQLContent(java.util.List<ClientConsommeService> list, boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOClientConsommeService.getSQLContent(list, foreignKeyCheck, addDropTable);
	}

	public static String showSQLStructure(){
		return models.daos.server.DAOClientConsommeService.showSQLStructure();
	}

	public static String getTableName(){
		return models.daos.server.DAOClientConsommeService.getTableName();
	}
}