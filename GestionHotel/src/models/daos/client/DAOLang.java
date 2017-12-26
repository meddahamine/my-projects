package models.daos.client;

import models.beans.Lang;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>Remote Access for DAO
 */

public abstract class DAOLang {
	public static Lang getLang(int id) {
		if (id == 0)
			return new Lang();
		String query = "select * from `lang` where `id`="+id+" limit 1";
		return (Lang)communication.SocketCommunicator.getInstance().sendQuery(query);
	}

	public static String getSQLDeleting(Lang lang) {
		return models.daos.server.DAOLang.getSQLDeleting(lang);
	}

	public static String getSQLDeleting() {
		return models.daos.server.DAOLang.getSQLDeleting();
	}

	public static String getSQLWriting(Lang lang) {
		return models.daos.server.DAOLang.getSQLWriting(lang);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLWritingByPreparedStatement(Lang lang) {
		return models.daos.server.DAOLang.getSQLWritingByPreparedStatement(lang);
	}

	public static int write(Lang lang) {
		int id = (Integer)communication.SocketCommunicator.getInstance().sendQuery(lang);
		lang.setId(id);
		return id;
	}

	public static void writeByPreparedStatement(Lang lang) {
		utils.SGBDConfig.InsertUpdateSQLQueries writingQuery = getSQLWritingByPreparedStatement(lang);
		communication.SocketCommunicator.getInstance().sendQuery(writingQuery);
	}

	public static void delete(Lang lang){
		communication.SocketCommunicator.getInstance().sendQuery(getSQLDeleting(lang));
	}

	public static void deleteAll(){
		communication.SocketCommunicator.getInstance().sendQuery(getSQLDeleting());
	}

	public static java.util.List<Lang> getListInstances(){
		return getListInstances("");
	}

	public static java.util.List<Lang> getListInstances(String whereCondition){
		String query = "select * from lang";
		if (!whereCondition.trim().equals("")){
			if (!whereCondition.trim().toLowerCase().equals("") && !whereCondition.trim().toLowerCase().startsWith("where")){
				query += " where ";
			}
			query += " "+whereCondition;
		}
		return getListInstancesBySQLQuery(query);
	}

@SuppressWarnings("unchecked")
	public static java.util.List<Lang> getListInstancesBySQLQuery(String sqlQuery){
		java.util.List<Lang> list = new java.util.ArrayList<Lang>();
		Object response = communication.SocketCommunicator.getInstance().sendQuery(sqlQuery);
		if (response != null){
			if (response instanceof java.util.List<?>){
				list = (java.util.List<Lang>)response;
			}
			else{
				list.add((Lang)response);
			}
		}
		return list;
	}

	public static String getSQLUpdateForLangue(Lang lang){
		return models.daos.server.DAOLang.getSQLUpdateForLangue(lang);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForLangueByPreparedStatement(Lang lang){
		return models.daos.server.DAOLang.getSQLUpdateForLangueByPreparedStatement(lang);
	}

	public static void updateLangue(Lang lang){
		String sqlUpdate = getSQLUpdateForLangue(lang);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForCodeLang(Lang lang){
		return models.daos.server.DAOLang.getSQLUpdateForCodeLang(lang);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForCodeLangByPreparedStatement(Lang lang){
		return models.daos.server.DAOLang.getSQLUpdateForCodeLangByPreparedStatement(lang);
	}

	public static Lang getLangByCodeLang(java.lang.String codeLang){
		String sqlQuery ="select * from `lang` where codeLang = '"+codeLang+"' limit 1";
		Object response = communication.SocketCommunicator.getInstance().sendQuery(sqlQuery);
		if (response != null && response instanceof Lang){
			return ((Lang)response);
		}
		return null;
	}

	public static void updateCodeLang(Lang lang){
		String sqlUpdate = getSQLUpdateForCodeLang(lang);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForOrientation(Lang lang){
		return models.daos.server.DAOLang.getSQLUpdateForOrientation(lang);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForOrientationByPreparedStatement(Lang lang){
		return models.daos.server.DAOLang.getSQLUpdateForOrientationByPreparedStatement(lang);
	}

	public static void updateOrientation(Lang lang){
		String sqlUpdate = getSQLUpdateForOrientation(lang);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getConcatIDs(){
		return getConcatIDs("");
	}

	public static String getConcatIDs(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return (String)communication.SocketCommunicator.getInstance().sendQuery("select group_concat(id) from `lang` "+whereCondition);
	}

	public static int getCountInTable(){
		return getCountInTable("");
	}

	public static int getCountInTable(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return (Integer)communication.SocketCommunicator.getInstance().sendQuery("select count(id) from `lang` "+whereCondition);
	}

	public static int getCountInTableFromServer(){
		return getCountInTableFromServer("");
	}

	public static int getCountInTableFromServer(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return ((Integer)communication.SocketCommunicator.getInstance().sendQuery("select count(id) from `lang` "+whereCondition)).intValue();
	}

	public static String getSQLTuple(Lang lang){
		return models.daos.server.DAOLang.getSQLTuple(lang);
	}

	public static String getSQLStructure(boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOLang.getSQLStructure(foreignKeyCheck, addDropTable);
	}

	public static String getSQLStructure(boolean addDropTable){
		return models.daos.server.DAOLang.getSQLStructure(addDropTable);
	}

	public static String getSQLStructure(){
		return getSQLStructure(true);
	}

	public static String getSQLContent(boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOLang.getSQLContent(foreignKeyCheck, addDropTable);
	}

	public static String getSQLContent(java.util.List<Lang> list, boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOLang.getSQLContent(list, foreignKeyCheck, addDropTable);
	}

	public static String showSQLStructure(){
		return models.daos.server.DAOLang.showSQLStructure();
	}

	public static String getTableName(){
		return models.daos.server.DAOLang.getTableName();
	}
	
	public static java.util.List<models.beans.ParametresApplicationUtilisateur> getListOfParametresApplicationUtilisateursLang(models.beans.Lang lang){
		java.util.List<models.beans.ParametresApplicationUtilisateur> listOfParametresApplicationUtilisateursLang = models.daos.client.DAOParametresApplicationUtilisateur.getListInstances("where idLang='"+lang.getId().intValue()+"'");
		return listOfParametresApplicationUtilisateursLang;
	}
	
	public static java.util.List<models.beans.Translation> getListOfTranslationsLang(models.beans.Lang lang){
		java.util.List<models.beans.Translation> listOfTranslationsLang = models.daos.client.DAOTranslation.getListInstances("where idLang='"+lang.getId().intValue()+"'");
		return listOfTranslationsLang;
	}
}