package models.daos.client;

import models.beans.ClientReseveChambre;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>Remote Access for DAO
 */

public abstract class DAOClientReseveChambre {
	public static ClientReseveChambre getClientReseveChambre(int id) {
		if (id == 0)
			return new ClientReseveChambre();
		String query = "select * from `clientReseveChambre` where `id`="+id+" limit 1";
		return (ClientReseveChambre)communication.SocketCommunicator.getInstance().sendQuery(query);
	}

	public static String getSQLDeleting(ClientReseveChambre clientReseveChambre) {
		return models.daos.server.DAOClientReseveChambre.getSQLDeleting(clientReseveChambre);
	}

	public static String getSQLDeleting() {
		return models.daos.server.DAOClientReseveChambre.getSQLDeleting();
	}

	public static String getSQLWriting(ClientReseveChambre clientReseveChambre) {
		return models.daos.server.DAOClientReseveChambre.getSQLWriting(clientReseveChambre);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLWritingByPreparedStatement(ClientReseveChambre clientReseveChambre) {
		return models.daos.server.DAOClientReseveChambre.getSQLWritingByPreparedStatement(clientReseveChambre);
	}

	public static int write(ClientReseveChambre clientReseveChambre) {
		int id = (Integer)communication.SocketCommunicator.getInstance().sendQuery(clientReseveChambre);
		clientReseveChambre.setId(id);
		return id;
	}

	public static void writeByPreparedStatement(ClientReseveChambre clientReseveChambre) {
		utils.SGBDConfig.InsertUpdateSQLQueries writingQuery = getSQLWritingByPreparedStatement(clientReseveChambre);
		communication.SocketCommunicator.getInstance().sendQuery(writingQuery);
	}

	public static void delete(ClientReseveChambre clientReseveChambre){
		communication.SocketCommunicator.getInstance().sendQuery(getSQLDeleting(clientReseveChambre));
	}

	public static void deleteAll(){
		communication.SocketCommunicator.getInstance().sendQuery(getSQLDeleting());
	}

	public static java.util.List<ClientReseveChambre> getListInstances(){
		return getListInstances("");
	}

	public static java.util.List<ClientReseveChambre> getListInstances(String whereCondition){
		String query = "select * from clientReseveChambre";
		if (!whereCondition.trim().equals("")){
			if (!whereCondition.trim().toLowerCase().equals("") && !whereCondition.trim().toLowerCase().startsWith("where")){
				query += " where ";
			}
			query += " "+whereCondition;
		}
		return getListInstancesBySQLQuery(query);
	}

@SuppressWarnings("unchecked")
	public static java.util.List<ClientReseveChambre> getListInstancesBySQLQuery(String sqlQuery){
		java.util.List<ClientReseveChambre> list = new java.util.ArrayList<ClientReseveChambre>();
		Object response = communication.SocketCommunicator.getInstance().sendQuery(sqlQuery);
		if (response != null){
			if (response instanceof java.util.List<?>){
				list = (java.util.List<ClientReseveChambre>)response;
			}
			else{
				list.add((ClientReseveChambre)response);
			}
		}
		return list;
	}

	public static String getSQLUpdateForIdClient(ClientReseveChambre clientReseveChambre){
		return models.daos.server.DAOClientReseveChambre.getSQLUpdateForIdClient(clientReseveChambre);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIdClientByPreparedStatement(ClientReseveChambre clientReseveChambre){
		return models.daos.server.DAOClientReseveChambre.getSQLUpdateForIdClientByPreparedStatement(clientReseveChambre);
	}

	public static void updateIdClient(ClientReseveChambre clientReseveChambre){
		String sqlUpdate = getSQLUpdateForIdClient(clientReseveChambre);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForIdChambre(ClientReseveChambre clientReseveChambre){
		return models.daos.server.DAOClientReseveChambre.getSQLUpdateForIdChambre(clientReseveChambre);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIdChambreByPreparedStatement(ClientReseveChambre clientReseveChambre){
		return models.daos.server.DAOClientReseveChambre.getSQLUpdateForIdChambreByPreparedStatement(clientReseveChambre);
	}

	public static void updateIdChambre(ClientReseveChambre clientReseveChambre){
		String sqlUpdate = getSQLUpdateForIdChambre(clientReseveChambre);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForDateDebutReservation(ClientReseveChambre clientReseveChambre){
		return models.daos.server.DAOClientReseveChambre.getSQLUpdateForDateDebutReservation(clientReseveChambre);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForDateDebutReservationByPreparedStatement(ClientReseveChambre clientReseveChambre){
		return models.daos.server.DAOClientReseveChambre.getSQLUpdateForDateDebutReservationByPreparedStatement(clientReseveChambre);
	}

	public static void updateDateDebutReservation(ClientReseveChambre clientReseveChambre){
		String sqlUpdate = getSQLUpdateForDateDebutReservation(clientReseveChambre);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForDateFinReservation(ClientReseveChambre clientReseveChambre){
		return models.daos.server.DAOClientReseveChambre.getSQLUpdateForDateFinReservation(clientReseveChambre);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForDateFinReservationByPreparedStatement(ClientReseveChambre clientReseveChambre){
		return models.daos.server.DAOClientReseveChambre.getSQLUpdateForDateFinReservationByPreparedStatement(clientReseveChambre);
	}

	public static void updateDateFinReservation(ClientReseveChambre clientReseveChambre){
		String sqlUpdate = getSQLUpdateForDateFinReservation(clientReseveChambre);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getConcatIDs(){
		return getConcatIDs("");
	}

	public static String getConcatIDs(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return (String)communication.SocketCommunicator.getInstance().sendQuery("select group_concat(id) from `clientReseveChambre` "+whereCondition);
	}

	public static int getCountInTable(){
		return getCountInTable("");
	}

	public static int getCountInTable(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return (Integer)communication.SocketCommunicator.getInstance().sendQuery("select count(id) from `clientReseveChambre` "+whereCondition);
	}

	public static int getCountInTableFromServer(){
		return getCountInTableFromServer("");
	}

	public static int getCountInTableFromServer(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return ((Integer)communication.SocketCommunicator.getInstance().sendQuery("select count(id) from `clientReseveChambre` "+whereCondition)).intValue();
	}

	public static String getSQLTuple(ClientReseveChambre clientReseveChambre){
		return models.daos.server.DAOClientReseveChambre.getSQLTuple(clientReseveChambre);
	}

	public static String getSQLStructure(boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOClientReseveChambre.getSQLStructure(foreignKeyCheck, addDropTable);
	}

	public static String getSQLStructure(boolean addDropTable){
		return models.daos.server.DAOClientReseveChambre.getSQLStructure(addDropTable);
	}

	public static String getSQLStructure(){
		return getSQLStructure(true);
	}

	public static String getSQLContent(boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOClientReseveChambre.getSQLContent(foreignKeyCheck, addDropTable);
	}

	public static String getSQLContent(java.util.List<ClientReseveChambre> list, boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOClientReseveChambre.getSQLContent(list, foreignKeyCheck, addDropTable);
	}

	public static String showSQLStructure(){
		return models.daos.server.DAOClientReseveChambre.showSQLStructure();
	}

	public static String getTableName(){
		return models.daos.server.DAOClientReseveChambre.getTableName();
	}
}