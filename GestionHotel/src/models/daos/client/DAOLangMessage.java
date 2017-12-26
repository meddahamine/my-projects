package models.daos.client;

import models.beans.LangMessage;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>Remote Access for DAO
 */

public abstract class DAOLangMessage {
	public static LangMessage getLangMessage(int id) {
		if (id == 0)
			return new LangMessage();
		String query = "select * from `langMessage` where `id`="+id+" limit 1";
		return (LangMessage)communication.SocketCommunicator.getInstance().sendQuery(query);
	}

	public static String getSQLDeleting(LangMessage langMessage) {
		return models.daos.server.DAOLangMessage.getSQLDeleting(langMessage);
	}

	public static String getSQLDeleting() {
		return models.daos.server.DAOLangMessage.getSQLDeleting();
	}

	public static String getSQLWriting(LangMessage langMessage) {
		return models.daos.server.DAOLangMessage.getSQLWriting(langMessage);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLWritingByPreparedStatement(LangMessage langMessage) {
		return models.daos.server.DAOLangMessage.getSQLWritingByPreparedStatement(langMessage);
	}

	public static int write(LangMessage langMessage) {
		int id = (Integer)communication.SocketCommunicator.getInstance().sendQuery(langMessage);
		langMessage.setId(id);
		return id;
	}

	public static void writeByPreparedStatement(LangMessage langMessage) {
		utils.SGBDConfig.InsertUpdateSQLQueries writingQuery = getSQLWritingByPreparedStatement(langMessage);
		communication.SocketCommunicator.getInstance().sendQuery(writingQuery);
	}

	public static void delete(LangMessage langMessage){
		communication.SocketCommunicator.getInstance().sendQuery(getSQLDeleting(langMessage));
	}

	public static void deleteAll(){
		communication.SocketCommunicator.getInstance().sendQuery(getSQLDeleting());
	}

	public static java.util.List<LangMessage> getListInstances(){
		return getListInstances("");
	}

	public static java.util.List<LangMessage> getListInstances(String whereCondition){
		String query = "select * from langMessage";
		if (!whereCondition.trim().equals("")){
			if (!whereCondition.trim().toLowerCase().equals("") && !whereCondition.trim().toLowerCase().startsWith("where")){
				query += " where ";
			}
			query += " "+whereCondition;
		}
		return getListInstancesBySQLQuery(query);
	}

@SuppressWarnings("unchecked")
	public static java.util.List<LangMessage> getListInstancesBySQLQuery(String sqlQuery){
		java.util.List<LangMessage> list = new java.util.ArrayList<LangMessage>();
		Object response = communication.SocketCommunicator.getInstance().sendQuery(sqlQuery);
		if (response != null){
			if (response instanceof java.util.List<?>){
				list = (java.util.List<LangMessage>)response;
			}
			else{
				list.add((LangMessage)response);
			}
		}
		return list;
	}

	public static String getSQLUpdateForMessage(LangMessage langMessage){
		return models.daos.server.DAOLangMessage.getSQLUpdateForMessage(langMessage);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForMessageByPreparedStatement(LangMessage langMessage){
		return models.daos.server.DAOLangMessage.getSQLUpdateForMessageByPreparedStatement(langMessage);
	}

	public static void updateMessage(LangMessage langMessage){
		String sqlUpdate = getSQLUpdateForMessage(langMessage);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getConcatIDs(){
		return getConcatIDs("");
	}

	public static String getConcatIDs(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return (String)communication.SocketCommunicator.getInstance().sendQuery("select group_concat(id) from `langMessage` "+whereCondition);
	}

	public static int getCountInTable(){
		return getCountInTable("");
	}

	public static int getCountInTable(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return (Integer)communication.SocketCommunicator.getInstance().sendQuery("select count(id) from `langMessage` "+whereCondition);
	}

	public static int getCountInTableFromServer(){
		return getCountInTableFromServer("");
	}

	public static int getCountInTableFromServer(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return ((Integer)communication.SocketCommunicator.getInstance().sendQuery("select count(id) from `langMessage` "+whereCondition)).intValue();
	}

	public static String getSQLTuple(LangMessage langMessage){
		return models.daos.server.DAOLangMessage.getSQLTuple(langMessage);
	}

	public static String getSQLStructure(boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOLangMessage.getSQLStructure(foreignKeyCheck, addDropTable);
	}

	public static String getSQLStructure(boolean addDropTable){
		return models.daos.server.DAOLangMessage.getSQLStructure(addDropTable);
	}

	public static String getSQLStructure(){
		return getSQLStructure(true);
	}

	public static String getSQLContent(boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOLangMessage.getSQLContent(foreignKeyCheck, addDropTable);
	}

	public static String getSQLContent(java.util.List<LangMessage> list, boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOLangMessage.getSQLContent(list, foreignKeyCheck, addDropTable);
	}

	public static String showSQLStructure(){
		return models.daos.server.DAOLangMessage.showSQLStructure();
	}

	public static String getTableName(){
		return models.daos.server.DAOLangMessage.getTableName();
	}
	
	public static java.util.List<models.beans.Translation> getListOfTranslationsMessage(models.beans.LangMessage langMessage){
		java.util.List<models.beans.Translation> listOfTranslationsMessage = models.daos.client.DAOTranslation.getListInstances("where idMessage='"+langMessage.getId().intValue()+"'");
		return listOfTranslationsMessage;
	}
}