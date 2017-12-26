package models.daos.client;

import models.beans.ParametresApplicationUtilisateur;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>Remote Access for DAO
 */

public abstract class DAOParametresApplicationUtilisateur {
	public static ParametresApplicationUtilisateur getParametresApplicationUtilisateur(int id) {
		if (id == 0)
			return new ParametresApplicationUtilisateur();
		String query = "select * from `parametresApplicationUtilisateur` where `id`="+id+" limit 1";
		return (ParametresApplicationUtilisateur)communication.SocketCommunicator.getInstance().sendQuery(query);
	}

	public static String getSQLDeleting(ParametresApplicationUtilisateur parametresApplicationUtilisateur) {
		return models.daos.server.DAOParametresApplicationUtilisateur.getSQLDeleting(parametresApplicationUtilisateur);
	}

	public static String getSQLDeleting() {
		return models.daos.server.DAOParametresApplicationUtilisateur.getSQLDeleting();
	}

	public static String getSQLWriting(ParametresApplicationUtilisateur parametresApplicationUtilisateur) {
		return models.daos.server.DAOParametresApplicationUtilisateur.getSQLWriting(parametresApplicationUtilisateur);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLWritingByPreparedStatement(ParametresApplicationUtilisateur parametresApplicationUtilisateur) {
		return models.daos.server.DAOParametresApplicationUtilisateur.getSQLWritingByPreparedStatement(parametresApplicationUtilisateur);
	}

	public static int write(ParametresApplicationUtilisateur parametresApplicationUtilisateur) {
		int id = (Integer)communication.SocketCommunicator.getInstance().sendQuery(parametresApplicationUtilisateur);
		parametresApplicationUtilisateur.setId(id);
		return id;
	}

	public static void writeByPreparedStatement(ParametresApplicationUtilisateur parametresApplicationUtilisateur) {
		utils.SGBDConfig.InsertUpdateSQLQueries writingQuery = getSQLWritingByPreparedStatement(parametresApplicationUtilisateur);
		communication.SocketCommunicator.getInstance().sendQuery(writingQuery);
	}

	public static void delete(ParametresApplicationUtilisateur parametresApplicationUtilisateur){
		communication.SocketCommunicator.getInstance().sendQuery(getSQLDeleting(parametresApplicationUtilisateur));
	}

	public static void deleteAll(){
		communication.SocketCommunicator.getInstance().sendQuery(getSQLDeleting());
	}

	public static java.util.List<ParametresApplicationUtilisateur> getListInstances(){
		return getListInstances("");
	}

	public static java.util.List<ParametresApplicationUtilisateur> getListInstances(String whereCondition){
		String query = "select * from parametresApplicationUtilisateur";
		if (!whereCondition.trim().equals("")){
			if (!whereCondition.trim().toLowerCase().equals("") && !whereCondition.trim().toLowerCase().startsWith("where")){
				query += " where ";
			}
			query += " "+whereCondition;
		}
		return getListInstancesBySQLQuery(query);
	}

@SuppressWarnings("unchecked")
	public static java.util.List<ParametresApplicationUtilisateur> getListInstancesBySQLQuery(String sqlQuery){
		java.util.List<ParametresApplicationUtilisateur> list = new java.util.ArrayList<ParametresApplicationUtilisateur>();
		Object response = communication.SocketCommunicator.getInstance().sendQuery(sqlQuery);
		if (response != null){
			if (response instanceof java.util.List<?>){
				list = (java.util.List<ParametresApplicationUtilisateur>)response;
			}
			else{
				list.add((ParametresApplicationUtilisateur)response);
			}
		}
		return list;
	}

	public static String getSQLUpdateForPeriodeNotification(ParametresApplicationUtilisateur parametresApplicationUtilisateur){
		return models.daos.server.DAOParametresApplicationUtilisateur.getSQLUpdateForPeriodeNotification(parametresApplicationUtilisateur);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForPeriodeNotificationByPreparedStatement(ParametresApplicationUtilisateur parametresApplicationUtilisateur){
		return models.daos.server.DAOParametresApplicationUtilisateur.getSQLUpdateForPeriodeNotificationByPreparedStatement(parametresApplicationUtilisateur);
	}

	public static void updatePeriodeNotification(ParametresApplicationUtilisateur parametresApplicationUtilisateur){
		String sqlUpdate = getSQLUpdateForPeriodeNotification(parametresApplicationUtilisateur);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForVisibilityOfNotification(ParametresApplicationUtilisateur parametresApplicationUtilisateur){
		return models.daos.server.DAOParametresApplicationUtilisateur.getSQLUpdateForVisibilityOfNotification(parametresApplicationUtilisateur);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForVisibilityOfNotificationByPreparedStatement(ParametresApplicationUtilisateur parametresApplicationUtilisateur){
		return models.daos.server.DAOParametresApplicationUtilisateur.getSQLUpdateForVisibilityOfNotificationByPreparedStatement(parametresApplicationUtilisateur);
	}

	public static void updateVisibilityOfNotification(ParametresApplicationUtilisateur parametresApplicationUtilisateur){
		String sqlUpdate = getSQLUpdateForVisibilityOfNotification(parametresApplicationUtilisateur);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForVisibilityOfMainToolBar(ParametresApplicationUtilisateur parametresApplicationUtilisateur){
		return models.daos.server.DAOParametresApplicationUtilisateur.getSQLUpdateForVisibilityOfMainToolBar(parametresApplicationUtilisateur);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForVisibilityOfMainToolBarByPreparedStatement(ParametresApplicationUtilisateur parametresApplicationUtilisateur){
		return models.daos.server.DAOParametresApplicationUtilisateur.getSQLUpdateForVisibilityOfMainToolBarByPreparedStatement(parametresApplicationUtilisateur);
	}

	public static void updateVisibilityOfMainToolBar(ParametresApplicationUtilisateur parametresApplicationUtilisateur){
		String sqlUpdate = getSQLUpdateForVisibilityOfMainToolBar(parametresApplicationUtilisateur);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForIdLang(ParametresApplicationUtilisateur parametresApplicationUtilisateur){
		return models.daos.server.DAOParametresApplicationUtilisateur.getSQLUpdateForIdLang(parametresApplicationUtilisateur);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIdLangByPreparedStatement(ParametresApplicationUtilisateur parametresApplicationUtilisateur){
		return models.daos.server.DAOParametresApplicationUtilisateur.getSQLUpdateForIdLangByPreparedStatement(parametresApplicationUtilisateur);
	}

	public static void updateIdLang(ParametresApplicationUtilisateur parametresApplicationUtilisateur){
		String sqlUpdate = getSQLUpdateForIdLang(parametresApplicationUtilisateur);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForLookAndFeel(ParametresApplicationUtilisateur parametresApplicationUtilisateur){
		return models.daos.server.DAOParametresApplicationUtilisateur.getSQLUpdateForLookAndFeel(parametresApplicationUtilisateur);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForLookAndFeelByPreparedStatement(ParametresApplicationUtilisateur parametresApplicationUtilisateur){
		return models.daos.server.DAOParametresApplicationUtilisateur.getSQLUpdateForLookAndFeelByPreparedStatement(parametresApplicationUtilisateur);
	}

	public static void updateLookAndFeel(ParametresApplicationUtilisateur parametresApplicationUtilisateur){
		String sqlUpdate = getSQLUpdateForLookAndFeel(parametresApplicationUtilisateur);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getConcatIDs(){
		return getConcatIDs("");
	}

	public static String getConcatIDs(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return (String)communication.SocketCommunicator.getInstance().sendQuery("select group_concat(id) from `parametresApplicationUtilisateur` "+whereCondition);
	}

	public static int getCountInTable(){
		return getCountInTable("");
	}

	public static int getCountInTable(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return (Integer)communication.SocketCommunicator.getInstance().sendQuery("select count(id) from `parametresApplicationUtilisateur` "+whereCondition);
	}

	public static int getCountInTableFromServer(){
		return getCountInTableFromServer("");
	}

	public static int getCountInTableFromServer(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return ((Integer)communication.SocketCommunicator.getInstance().sendQuery("select count(id) from `parametresApplicationUtilisateur` "+whereCondition)).intValue();
	}

	public static String getSQLTuple(ParametresApplicationUtilisateur parametresApplicationUtilisateur){
		return models.daos.server.DAOParametresApplicationUtilisateur.getSQLTuple(parametresApplicationUtilisateur);
	}

	public static String getSQLStructure(boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOParametresApplicationUtilisateur.getSQLStructure(foreignKeyCheck, addDropTable);
	}

	public static String getSQLStructure(boolean addDropTable){
		return models.daos.server.DAOParametresApplicationUtilisateur.getSQLStructure(addDropTable);
	}

	public static String getSQLStructure(){
		return getSQLStructure(true);
	}

	public static String getSQLContent(boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOParametresApplicationUtilisateur.getSQLContent(foreignKeyCheck, addDropTable);
	}

	public static String getSQLContent(java.util.List<ParametresApplicationUtilisateur> list, boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOParametresApplicationUtilisateur.getSQLContent(list, foreignKeyCheck, addDropTable);
	}

	public static String showSQLStructure(){
		return models.daos.server.DAOParametresApplicationUtilisateur.showSQLStructure();
	}

	public static String getTableName(){
		return models.daos.server.DAOParametresApplicationUtilisateur.getTableName();
	}
	
	public static java.util.List<models.beans.Utilisateur> getListOfUtilisateursParametres(models.beans.ParametresApplicationUtilisateur parametresApplicationUtilisateur){
		java.util.List<models.beans.Utilisateur> listOfUtilisateursParametres = models.daos.client.DAOUtilisateur.getListInstances("where idParametres='"+parametresApplicationUtilisateur.getId().intValue()+"'");
		return listOfUtilisateursParametres;
	}
}