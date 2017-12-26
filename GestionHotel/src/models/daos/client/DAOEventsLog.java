package models.daos.client;

import models.beans.EventsLog;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>Remote Access for DAO
 */

public abstract class DAOEventsLog {
	public static EventsLog getEventsLog(int id) {
		if (id == 0)
			return new EventsLog();
		String query = "select * from `eventsLog` where `id`="+id+" limit 1";
		return (EventsLog)communication.SocketCommunicator.getInstance().sendQuery(query);
	}

	public static String getSQLDeleting(EventsLog eventsLog) {
		return models.daos.server.DAOEventsLog.getSQLDeleting(eventsLog);
	}

	public static String getSQLDeleting() {
		return models.daos.server.DAOEventsLog.getSQLDeleting();
	}

	public static String getSQLWriting(EventsLog eventsLog) {
		return models.daos.server.DAOEventsLog.getSQLWriting(eventsLog);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLWritingByPreparedStatement(EventsLog eventsLog) {
		return models.daos.server.DAOEventsLog.getSQLWritingByPreparedStatement(eventsLog);
	}

	public static int write(EventsLog eventsLog) {
		int id = (Integer)communication.SocketCommunicator.getInstance().sendQuery(eventsLog);
		eventsLog.setId(id);
		return id;
	}

	public static void writeByPreparedStatement(EventsLog eventsLog) {
		utils.SGBDConfig.InsertUpdateSQLQueries writingQuery = getSQLWritingByPreparedStatement(eventsLog);
		communication.SocketCommunicator.getInstance().sendQuery(writingQuery);
	}

	public static void delete(EventsLog eventsLog){
		communication.SocketCommunicator.getInstance().sendQuery(getSQLDeleting(eventsLog));
	}

	public static void deleteAll(){
		communication.SocketCommunicator.getInstance().sendQuery(getSQLDeleting());
	}

	public static java.util.List<EventsLog> getListInstances(){
		return getListInstances("");
	}

	public static java.util.List<EventsLog> getListInstances(String whereCondition){
		String query = "select * from eventsLog";
		if (!whereCondition.trim().equals("")){
			if (!whereCondition.trim().toLowerCase().equals("") && !whereCondition.trim().toLowerCase().startsWith("where")){
				query += " where ";
			}
			query += " "+whereCondition;
		}
		return getListInstancesBySQLQuery(query);
	}

@SuppressWarnings("unchecked")
	public static java.util.List<EventsLog> getListInstancesBySQLQuery(String sqlQuery){
		java.util.List<EventsLog> list = new java.util.ArrayList<EventsLog>();
		Object response = communication.SocketCommunicator.getInstance().sendQuery(sqlQuery);
		if (response != null){
			if (response instanceof java.util.List<?>){
				list = (java.util.List<EventsLog>)response;
			}
			else{
				list.add((EventsLog)response);
			}
		}
		return list;
	}

	public static String getSQLUpdateForEvenement(EventsLog eventsLog){
		return models.daos.server.DAOEventsLog.getSQLUpdateForEvenement(eventsLog);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForEvenementByPreparedStatement(EventsLog eventsLog){
		return models.daos.server.DAOEventsLog.getSQLUpdateForEvenementByPreparedStatement(eventsLog);
	}

	public static void updateEvenement(EventsLog eventsLog){
		String sqlUpdate = getSQLUpdateForEvenement(eventsLog);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForIdConnexionsLog(EventsLog eventsLog){
		return models.daos.server.DAOEventsLog.getSQLUpdateForIdConnexionsLog(eventsLog);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIdConnexionsLogByPreparedStatement(EventsLog eventsLog){
		return models.daos.server.DAOEventsLog.getSQLUpdateForIdConnexionsLogByPreparedStatement(eventsLog);
	}

	public static void updateIdConnexionsLog(EventsLog eventsLog){
		String sqlUpdate = getSQLUpdateForIdConnexionsLog(eventsLog);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForDate(EventsLog eventsLog){
		return models.daos.server.DAOEventsLog.getSQLUpdateForDate(eventsLog);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForDateByPreparedStatement(EventsLog eventsLog){
		return models.daos.server.DAOEventsLog.getSQLUpdateForDateByPreparedStatement(eventsLog);
	}

	public static void updateDate(EventsLog eventsLog){
		String sqlUpdate = getSQLUpdateForDate(eventsLog);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getConcatIDs(){
		return getConcatIDs("");
	}

	public static String getConcatIDs(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return (String)communication.SocketCommunicator.getInstance().sendQuery("select group_concat(id) from `eventsLog` "+whereCondition);
	}

	public static int getCountInTable(){
		return getCountInTable("");
	}

	public static int getCountInTable(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return (Integer)communication.SocketCommunicator.getInstance().sendQuery("select count(id) from `eventsLog` "+whereCondition);
	}

	public static int getCountInTableFromServer(){
		return getCountInTableFromServer("");
	}

	public static int getCountInTableFromServer(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return ((Integer)communication.SocketCommunicator.getInstance().sendQuery("select count(id) from `eventsLog` "+whereCondition)).intValue();
	}

	public static String getSQLTuple(EventsLog eventsLog){
		return models.daos.server.DAOEventsLog.getSQLTuple(eventsLog);
	}

	public static String getSQLStructure(boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOEventsLog.getSQLStructure(foreignKeyCheck, addDropTable);
	}

	public static String getSQLStructure(boolean addDropTable){
		return models.daos.server.DAOEventsLog.getSQLStructure(addDropTable);
	}

	public static String getSQLStructure(){
		return getSQLStructure(true);
	}

	public static String getSQLContent(boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOEventsLog.getSQLContent(foreignKeyCheck, addDropTable);
	}

	public static String getSQLContent(java.util.List<EventsLog> list, boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOEventsLog.getSQLContent(list, foreignKeyCheck, addDropTable);
	}

	public static String showSQLStructure(){
		return models.daos.server.DAOEventsLog.showSQLStructure();
	}

	public static String getTableName(){
		return models.daos.server.DAOEventsLog.getTableName();
	}
}