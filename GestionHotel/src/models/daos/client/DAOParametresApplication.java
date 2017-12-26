package models.daos.client;

import models.beans.ParametresApplication;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>Remote Access for DAO
 */

public abstract class DAOParametresApplication {
	public static ParametresApplication getParametresApplication(int id) {
		if (id == 0)
			return new ParametresApplication();
		String query = "select * from `parametresApplication` where `id`="+id+" limit 1";
		return (ParametresApplication)communication.SocketCommunicator.getInstance().sendQuery(query);
	}

	public static String getSQLDeleting(ParametresApplication parametresApplication) {
		return models.daos.server.DAOParametresApplication.getSQLDeleting(parametresApplication);
	}

	public static String getSQLDeleting() {
		return models.daos.server.DAOParametresApplication.getSQLDeleting();
	}

	public static String getSQLWriting(ParametresApplication parametresApplication) {
		return models.daos.server.DAOParametresApplication.getSQLWriting(parametresApplication);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLWritingByPreparedStatement(ParametresApplication parametresApplication) {
		return models.daos.server.DAOParametresApplication.getSQLWritingByPreparedStatement(parametresApplication);
	}

	public static int write(ParametresApplication parametresApplication) {
		int id = (Integer)communication.SocketCommunicator.getInstance().sendQuery(parametresApplication);
		parametresApplication.setId(id);
		return id;
	}

	public static void writeByPreparedStatement(ParametresApplication parametresApplication) {
		utils.SGBDConfig.InsertUpdateSQLQueries writingQuery = getSQLWritingByPreparedStatement(parametresApplication);
		communication.SocketCommunicator.getInstance().sendQuery(writingQuery);
	}

	public static void delete(ParametresApplication parametresApplication){
		communication.SocketCommunicator.getInstance().sendQuery(getSQLDeleting(parametresApplication));
	}

	public static void deleteAll(){
		communication.SocketCommunicator.getInstance().sendQuery(getSQLDeleting());
	}

	public static java.util.List<ParametresApplication> getListInstances(){
		return getListInstances("");
	}

	public static java.util.List<ParametresApplication> getListInstances(String whereCondition){
		String query = "select * from parametresApplication";
		if (!whereCondition.trim().equals("")){
			if (!whereCondition.trim().toLowerCase().equals("") && !whereCondition.trim().toLowerCase().startsWith("where")){
				query += " where ";
			}
			query += " "+whereCondition;
		}
		return getListInstancesBySQLQuery(query);
	}

@SuppressWarnings("unchecked")
	public static java.util.List<ParametresApplication> getListInstancesBySQLQuery(String sqlQuery){
		java.util.List<ParametresApplication> list = new java.util.ArrayList<ParametresApplication>();
		Object response = communication.SocketCommunicator.getInstance().sendQuery(sqlQuery);
		if (response != null){
			if (response instanceof java.util.List<?>){
				list = (java.util.List<ParametresApplication>)response;
			}
			else{
				list.add((ParametresApplication)response);
			}
		}
		return list;
	}

	public static String getSQLUpdateForDesignation(ParametresApplication parametresApplication){
		return models.daos.server.DAOParametresApplication.getSQLUpdateForDesignation(parametresApplication);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForDesignationByPreparedStatement(ParametresApplication parametresApplication){
		return models.daos.server.DAOParametresApplication.getSQLUpdateForDesignationByPreparedStatement(parametresApplication);
	}

	public static void updateDesignation(ParametresApplication parametresApplication){
		String sqlUpdate = getSQLUpdateForDesignation(parametresApplication);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForDescription(ParametresApplication parametresApplication){
		return models.daos.server.DAOParametresApplication.getSQLUpdateForDescription(parametresApplication);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForDescriptionByPreparedStatement(ParametresApplication parametresApplication){
		return models.daos.server.DAOParametresApplication.getSQLUpdateForDescriptionByPreparedStatement(parametresApplication);
	}

	public static void updateDescription(ParametresApplication parametresApplication){
		String sqlUpdate = getSQLUpdateForDescription(parametresApplication);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForIdPhoto(ParametresApplication parametresApplication){
		return models.daos.server.DAOParametresApplication.getSQLUpdateForIdPhoto(parametresApplication);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIdPhotoByPreparedStatement(ParametresApplication parametresApplication){
		return models.daos.server.DAOParametresApplication.getSQLUpdateForIdPhotoByPreparedStatement(parametresApplication);
	}

	public static void updateIdPhoto(ParametresApplication parametresApplication){
		String sqlUpdate = getSQLUpdateForIdPhoto(parametresApplication);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getConcatIDs(){
		return getConcatIDs("");
	}

	public static String getConcatIDs(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return (String)communication.SocketCommunicator.getInstance().sendQuery("select group_concat(id) from `parametresApplication` "+whereCondition);
	}

	public static int getCountInTable(){
		return getCountInTable("");
	}

	public static int getCountInTable(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return (Integer)communication.SocketCommunicator.getInstance().sendQuery("select count(id) from `parametresApplication` "+whereCondition);
	}

	public static int getCountInTableFromServer(){
		return getCountInTableFromServer("");
	}

	public static int getCountInTableFromServer(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return ((Integer)communication.SocketCommunicator.getInstance().sendQuery("select count(id) from `parametresApplication` "+whereCondition)).intValue();
	}

	public static String getSQLTuple(ParametresApplication parametresApplication){
		return models.daos.server.DAOParametresApplication.getSQLTuple(parametresApplication);
	}

	public static String getSQLStructure(boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOParametresApplication.getSQLStructure(foreignKeyCheck, addDropTable);
	}

	public static String getSQLStructure(boolean addDropTable){
		return models.daos.server.DAOParametresApplication.getSQLStructure(addDropTable);
	}

	public static String getSQLStructure(){
		return getSQLStructure(true);
	}

	public static String getSQLContent(boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOParametresApplication.getSQLContent(foreignKeyCheck, addDropTable);
	}

	public static String getSQLContent(java.util.List<ParametresApplication> list, boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOParametresApplication.getSQLContent(list, foreignKeyCheck, addDropTable);
	}

	public static String showSQLStructure(){
		return models.daos.server.DAOParametresApplication.showSQLStructure();
	}

	public static String getTableName(){
		return models.daos.server.DAOParametresApplication.getTableName();
	}
}