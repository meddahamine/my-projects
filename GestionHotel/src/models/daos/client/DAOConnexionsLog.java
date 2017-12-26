package models.daos.client;

import models.beans.ConnexionsLog;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>Remote Access for DAO
 */

public abstract class DAOConnexionsLog {
	public static ConnexionsLog getConnexionsLog(int id) {
		if (id == 0)
			return new ConnexionsLog();
		String query = "select * from `connexionsLog` where `id`="+id+" limit 1";
		return (ConnexionsLog)communication.SocketCommunicator.getInstance().sendQuery(query);
	}

	public static String getSQLDeleting(ConnexionsLog connexionsLog) {
		return models.daos.server.DAOConnexionsLog.getSQLDeleting(connexionsLog);
	}

	public static String getSQLDeleting() {
		return models.daos.server.DAOConnexionsLog.getSQLDeleting();
	}

	public static String getSQLWriting(ConnexionsLog connexionsLog) {
		return models.daos.server.DAOConnexionsLog.getSQLWriting(connexionsLog);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLWritingByPreparedStatement(ConnexionsLog connexionsLog) {
		return models.daos.server.DAOConnexionsLog.getSQLWritingByPreparedStatement(connexionsLog);
	}

	public static int write(ConnexionsLog connexionsLog) {
		int id = (Integer)communication.SocketCommunicator.getInstance().sendQuery(connexionsLog);
		connexionsLog.setId(id);
		return id;
	}

	public static void writeByPreparedStatement(ConnexionsLog connexionsLog) {
		utils.SGBDConfig.InsertUpdateSQLQueries writingQuery = getSQLWritingByPreparedStatement(connexionsLog);
		communication.SocketCommunicator.getInstance().sendQuery(writingQuery);
	}

	public static void delete(ConnexionsLog connexionsLog){
		communication.SocketCommunicator.getInstance().sendQuery(getSQLDeleting(connexionsLog));
	}

	public static void deleteAll(){
		communication.SocketCommunicator.getInstance().sendQuery(getSQLDeleting());
	}

	public static java.util.List<ConnexionsLog> getListInstances(){
		return getListInstances("");
	}

	public static java.util.List<ConnexionsLog> getListInstances(String whereCondition){
		String query = "select * from connexionsLog";
		if (!whereCondition.trim().equals("")){
			if (!whereCondition.trim().toLowerCase().equals("") && !whereCondition.trim().toLowerCase().startsWith("where")){
				query += " where ";
			}
			query += " "+whereCondition;
		}
		return getListInstancesBySQLQuery(query);
	}

@SuppressWarnings("unchecked")
	public static java.util.List<ConnexionsLog> getListInstancesBySQLQuery(String sqlQuery){
		java.util.List<ConnexionsLog> list = new java.util.ArrayList<ConnexionsLog>();
		Object response = communication.SocketCommunicator.getInstance().sendQuery(sqlQuery);
		if (response != null){
			if (response instanceof java.util.List<?>){
				list = (java.util.List<ConnexionsLog>)response;
			}
			else{
				list.add((ConnexionsLog)response);
			}
		}
		return list;
	}

	public static String getSQLUpdateForLogin(ConnexionsLog connexionsLog){
		return models.daos.server.DAOConnexionsLog.getSQLUpdateForLogin(connexionsLog);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForLoginByPreparedStatement(ConnexionsLog connexionsLog){
		return models.daos.server.DAOConnexionsLog.getSQLUpdateForLoginByPreparedStatement(connexionsLog);
	}

	public static void updateLogin(ConnexionsLog connexionsLog){
		String sqlUpdate = getSQLUpdateForLogin(connexionsLog);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForUuid(ConnexionsLog connexionsLog){
		return models.daos.server.DAOConnexionsLog.getSQLUpdateForUuid(connexionsLog);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForUuidByPreparedStatement(ConnexionsLog connexionsLog){
		return models.daos.server.DAOConnexionsLog.getSQLUpdateForUuidByPreparedStatement(connexionsLog);
	}

	public static void updateUuid(ConnexionsLog connexionsLog){
		String sqlUpdate = getSQLUpdateForUuid(connexionsLog);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForDateDeConnexion(ConnexionsLog connexionsLog){
		return models.daos.server.DAOConnexionsLog.getSQLUpdateForDateDeConnexion(connexionsLog);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForDateDeConnexionByPreparedStatement(ConnexionsLog connexionsLog){
		return models.daos.server.DAOConnexionsLog.getSQLUpdateForDateDeConnexionByPreparedStatement(connexionsLog);
	}

	public static void updateDateDeConnexion(ConnexionsLog connexionsLog){
		String sqlUpdate = getSQLUpdateForDateDeConnexion(connexionsLog);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForDateDeDeconnexion(ConnexionsLog connexionsLog){
		return models.daos.server.DAOConnexionsLog.getSQLUpdateForDateDeDeconnexion(connexionsLog);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForDateDeDeconnexionByPreparedStatement(ConnexionsLog connexionsLog){
		return models.daos.server.DAOConnexionsLog.getSQLUpdateForDateDeDeconnexionByPreparedStatement(connexionsLog);
	}

	public static void updateDateDeDeconnexion(ConnexionsLog connexionsLog){
		String sqlUpdate = getSQLUpdateForDateDeDeconnexion(connexionsLog);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForIp(ConnexionsLog connexionsLog){
		return models.daos.server.DAOConnexionsLog.getSQLUpdateForIp(connexionsLog);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIpByPreparedStatement(ConnexionsLog connexionsLog){
		return models.daos.server.DAOConnexionsLog.getSQLUpdateForIpByPreparedStatement(connexionsLog);
	}

	public static void updateIp(ConnexionsLog connexionsLog){
		String sqlUpdate = getSQLUpdateForIp(connexionsLog);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForMac(ConnexionsLog connexionsLog){
		return models.daos.server.DAOConnexionsLog.getSQLUpdateForMac(connexionsLog);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForMacByPreparedStatement(ConnexionsLog connexionsLog){
		return models.daos.server.DAOConnexionsLog.getSQLUpdateForMacByPreparedStatement(connexionsLog);
	}

	public static void updateMac(ConnexionsLog connexionsLog){
		String sqlUpdate = getSQLUpdateForMac(connexionsLog);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForMachine(ConnexionsLog connexionsLog){
		return models.daos.server.DAOConnexionsLog.getSQLUpdateForMachine(connexionsLog);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForMachineByPreparedStatement(ConnexionsLog connexionsLog){
		return models.daos.server.DAOConnexionsLog.getSQLUpdateForMachineByPreparedStatement(connexionsLog);
	}

	public static void updateMachine(ConnexionsLog connexionsLog){
		String sqlUpdate = getSQLUpdateForMachine(connexionsLog);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForConnexionAccepted(ConnexionsLog connexionsLog){
		return models.daos.server.DAOConnexionsLog.getSQLUpdateForConnexionAccepted(connexionsLog);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForConnexionAcceptedByPreparedStatement(ConnexionsLog connexionsLog){
		return models.daos.server.DAOConnexionsLog.getSQLUpdateForConnexionAcceptedByPreparedStatement(connexionsLog);
	}

	public static void updateConnexionAccepted(ConnexionsLog connexionsLog){
		String sqlUpdate = getSQLUpdateForConnexionAccepted(connexionsLog);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForMotifError(ConnexionsLog connexionsLog){
		return models.daos.server.DAOConnexionsLog.getSQLUpdateForMotifError(connexionsLog);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForMotifErrorByPreparedStatement(ConnexionsLog connexionsLog){
		return models.daos.server.DAOConnexionsLog.getSQLUpdateForMotifErrorByPreparedStatement(connexionsLog);
	}

	public static void updateMotifError(ConnexionsLog connexionsLog){
		String sqlUpdate = getSQLUpdateForMotifError(connexionsLog);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForIdUtilisateur(ConnexionsLog connexionsLog){
		return models.daos.server.DAOConnexionsLog.getSQLUpdateForIdUtilisateur(connexionsLog);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIdUtilisateurByPreparedStatement(ConnexionsLog connexionsLog){
		return models.daos.server.DAOConnexionsLog.getSQLUpdateForIdUtilisateurByPreparedStatement(connexionsLog);
	}

	public static void updateIdUtilisateur(ConnexionsLog connexionsLog){
		String sqlUpdate = getSQLUpdateForIdUtilisateur(connexionsLog);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getConcatIDs(){
		return getConcatIDs("");
	}

	public static String getConcatIDs(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return (String)communication.SocketCommunicator.getInstance().sendQuery("select group_concat(id) from `connexionsLog` "+whereCondition);
	}

	public static int getCountInTable(){
		return getCountInTable("");
	}

	public static int getCountInTable(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return (Integer)communication.SocketCommunicator.getInstance().sendQuery("select count(id) from `connexionsLog` "+whereCondition);
	}

	public static int getCountInTableFromServer(){
		return getCountInTableFromServer("");
	}

	public static int getCountInTableFromServer(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return ((Integer)communication.SocketCommunicator.getInstance().sendQuery("select count(id) from `connexionsLog` "+whereCondition)).intValue();
	}

	public static String getSQLTuple(ConnexionsLog connexionsLog){
		return models.daos.server.DAOConnexionsLog.getSQLTuple(connexionsLog);
	}

	public static String getSQLStructure(boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOConnexionsLog.getSQLStructure(foreignKeyCheck, addDropTable);
	}

	public static String getSQLStructure(boolean addDropTable){
		return models.daos.server.DAOConnexionsLog.getSQLStructure(addDropTable);
	}

	public static String getSQLStructure(){
		return getSQLStructure(true);
	}

	public static String getSQLContent(boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOConnexionsLog.getSQLContent(foreignKeyCheck, addDropTable);
	}

	public static String getSQLContent(java.util.List<ConnexionsLog> list, boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOConnexionsLog.getSQLContent(list, foreignKeyCheck, addDropTable);
	}

	public static String showSQLStructure(){
		return models.daos.server.DAOConnexionsLog.showSQLStructure();
	}

	public static String getTableName(){
		return models.daos.server.DAOConnexionsLog.getTableName();
	}
	
	public static java.util.List<models.beans.EventsLog> getListOfEventsLogsConnexionsLog(models.beans.ConnexionsLog connexionsLog){
		java.util.List<models.beans.EventsLog> listOfEventsLogsConnexionsLog = models.daos.client.DAOEventsLog.getListInstances("where idConnexionsLog='"+connexionsLog.getId().intValue()+"'");
		return listOfEventsLogsConnexionsLog;
	}
}