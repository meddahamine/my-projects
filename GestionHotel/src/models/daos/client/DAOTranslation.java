package models.daos.client;

import models.beans.Translation;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>Remote Access for DAO
 */

public abstract class DAOTranslation {
	public static Translation getTranslation(int id) {
		if (id == 0)
			return new Translation();
		String query = "select * from `translation` where `id`="+id+" limit 1";
		return (Translation)communication.SocketCommunicator.getInstance().sendQuery(query);
	}

	public static String getSQLDeleting(Translation translation) {
		return models.daos.server.DAOTranslation.getSQLDeleting(translation);
	}

	public static String getSQLDeleting() {
		return models.daos.server.DAOTranslation.getSQLDeleting();
	}

	public static String getSQLWriting(Translation translation) {
		return models.daos.server.DAOTranslation.getSQLWriting(translation);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLWritingByPreparedStatement(Translation translation) {
		return models.daos.server.DAOTranslation.getSQLWritingByPreparedStatement(translation);
	}

	public static int write(Translation translation) {
		int id = (Integer)communication.SocketCommunicator.getInstance().sendQuery(translation);
		translation.setId(id);
		return id;
	}

	public static void writeByPreparedStatement(Translation translation) {
		utils.SGBDConfig.InsertUpdateSQLQueries writingQuery = getSQLWritingByPreparedStatement(translation);
		communication.SocketCommunicator.getInstance().sendQuery(writingQuery);
	}

	public static void delete(Translation translation){
		communication.SocketCommunicator.getInstance().sendQuery(getSQLDeleting(translation));
	}

	public static void deleteAll(){
		communication.SocketCommunicator.getInstance().sendQuery(getSQLDeleting());
	}

	public static java.util.List<Translation> getListInstances(){
		return getListInstances("");
	}

	public static java.util.List<Translation> getListInstances(String whereCondition){
		String query = "select * from translation";
		if (!whereCondition.trim().equals("")){
			if (!whereCondition.trim().toLowerCase().equals("") && !whereCondition.trim().toLowerCase().startsWith("where")){
				query += " where ";
			}
			query += " "+whereCondition;
		}
		return getListInstancesBySQLQuery(query);
	}

@SuppressWarnings("unchecked")
	public static java.util.List<Translation> getListInstancesBySQLQuery(String sqlQuery){
		java.util.List<Translation> list = new java.util.ArrayList<Translation>();
		Object response = communication.SocketCommunicator.getInstance().sendQuery(sqlQuery);
		if (response != null){
			if (response instanceof java.util.List<?>){
				list = (java.util.List<Translation>)response;
			}
			else{
				list.add((Translation)response);
			}
		}
		return list;
	}

	public static String getSQLUpdateForIdLang(Translation translation){
		return models.daos.server.DAOTranslation.getSQLUpdateForIdLang(translation);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIdLangByPreparedStatement(Translation translation){
		return models.daos.server.DAOTranslation.getSQLUpdateForIdLangByPreparedStatement(translation);
	}

	public static void updateIdLang(Translation translation){
		String sqlUpdate = getSQLUpdateForIdLang(translation);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForIdMessage(Translation translation){
		return models.daos.server.DAOTranslation.getSQLUpdateForIdMessage(translation);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIdMessageByPreparedStatement(Translation translation){
		return models.daos.server.DAOTranslation.getSQLUpdateForIdMessageByPreparedStatement(translation);
	}

	public static void updateIdMessage(Translation translation){
		String sqlUpdate = getSQLUpdateForIdMessage(translation);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForTraduction(Translation translation){
		return models.daos.server.DAOTranslation.getSQLUpdateForTraduction(translation);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForTraductionByPreparedStatement(Translation translation){
		return models.daos.server.DAOTranslation.getSQLUpdateForTraductionByPreparedStatement(translation);
	}

	public static void updateTraduction(Translation translation){
		String sqlUpdate = getSQLUpdateForTraduction(translation);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getConcatIDs(){
		return getConcatIDs("");
	}

	public static String getConcatIDs(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return (String)communication.SocketCommunicator.getInstance().sendQuery("select group_concat(id) from `translation` "+whereCondition);
	}

	public static int getCountInTable(){
		return getCountInTable("");
	}

	public static int getCountInTable(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return (Integer)communication.SocketCommunicator.getInstance().sendQuery("select count(id) from `translation` "+whereCondition);
	}

	public static int getCountInTableFromServer(){
		return getCountInTableFromServer("");
	}

	public static int getCountInTableFromServer(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return ((Integer)communication.SocketCommunicator.getInstance().sendQuery("select count(id) from `translation` "+whereCondition)).intValue();
	}

	public static String getSQLTuple(Translation translation){
		return models.daos.server.DAOTranslation.getSQLTuple(translation);
	}

	public static String getSQLStructure(boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOTranslation.getSQLStructure(foreignKeyCheck, addDropTable);
	}

	public static String getSQLStructure(boolean addDropTable){
		return models.daos.server.DAOTranslation.getSQLStructure(addDropTable);
	}

	public static String getSQLStructure(){
		return getSQLStructure(true);
	}

	public static String getSQLContent(boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOTranslation.getSQLContent(foreignKeyCheck, addDropTable);
	}

	public static String getSQLContent(java.util.List<Translation> list, boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOTranslation.getSQLContent(list, foreignKeyCheck, addDropTable);
	}

	public static String showSQLStructure(){
		return models.daos.server.DAOTranslation.showSQLStructure();
	}

	public static String getTableName(){
		return models.daos.server.DAOTranslation.getTableName();
	}
	
	public static String translate(String text, String lang){
		if (lang == null || lang.equals("") || lang.equals("FR")){
			return text;
		}
		
		java.util.List<models.beans.LangMessage> messages = DAOLangMessage.getListInstances(" where message = '"+text+"' limit 1");
		models.beans.LangMessage message;
		if (messages == null || messages.size() == 0){
			message = new models.beans.LangMessage();
			message.setMessage(text);
			DAOLangMessage.write(message);
		}
		else{
			message = messages.get(0);
		}
		
		models.beans.Lang langItem = DAOLang.getLangByCodeLang(lang);
		java.util.List<models.beans.Translation> translations = DAOTranslation.getListInstances(" where idMessage = '"+message.getId().intValue()+"' and idLang='"+langItem.getId().intValue()+"' limit 1");
		
		if (translations == null || translations.size() != 1){
			models.beans.Translation newTranslation = new models.beans.Translation();
			newTranslation.setIdMessage(message.getId().intValue());
			newTranslation.setIdLang(langItem.getId().intValue());
			
			DAOTranslation.write(newTranslation);
			return text;
		}
		else{
			return translations.get(0).getTraduction().toString();
		}
	}
}