package models.daos.client;

import models.beans.Facture;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>Remote Access for DAO
 */

public abstract class DAOFacture {
	public static Facture getFacture(int id) {
		if (id == 0)
			return new Facture();
		String query = "select * from `facture` where `id`="+id+" limit 1";
		return (Facture)communication.SocketCommunicator.getInstance().sendQuery(query);
	}

	public static String getSQLDeleting(Facture facture) {
		return models.daos.server.DAOFacture.getSQLDeleting(facture);
	}

	public static String getSQLDeleting() {
		return models.daos.server.DAOFacture.getSQLDeleting();
	}

	public static String getSQLWriting(Facture facture) {
		return models.daos.server.DAOFacture.getSQLWriting(facture);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLWritingByPreparedStatement(Facture facture) {
		return models.daos.server.DAOFacture.getSQLWritingByPreparedStatement(facture);
	}

	public static int write(Facture facture) {
		int id = (Integer)communication.SocketCommunicator.getInstance().sendQuery(facture);
		facture.setId(id);
		return id;
	}

	public static void writeByPreparedStatement(Facture facture) {
		utils.SGBDConfig.InsertUpdateSQLQueries writingQuery = getSQLWritingByPreparedStatement(facture);
		communication.SocketCommunicator.getInstance().sendQuery(writingQuery);
	}

	public static void delete(Facture facture){
		communication.SocketCommunicator.getInstance().sendQuery(getSQLDeleting(facture));
	}

	public static void deleteAll(){
		communication.SocketCommunicator.getInstance().sendQuery(getSQLDeleting());
	}

	public static java.util.List<Facture> getListInstances(){
		return getListInstances("");
	}

	public static java.util.List<Facture> getListInstances(String whereCondition){
		String query = "select * from facture";
		if (!whereCondition.trim().equals("")){
			if (!whereCondition.trim().toLowerCase().equals("") && !whereCondition.trim().toLowerCase().startsWith("where")){
				query += " where ";
			}
			query += " "+whereCondition;
		}
		return getListInstancesBySQLQuery(query);
	}

@SuppressWarnings("unchecked")
	public static java.util.List<Facture> getListInstancesBySQLQuery(String sqlQuery){
		java.util.List<Facture> list = new java.util.ArrayList<Facture>();
		Object response = communication.SocketCommunicator.getInstance().sendQuery(sqlQuery);
		if (response != null){
			if (response instanceof java.util.List<?>){
				list = (java.util.List<Facture>)response;
			}
			else{
				list.add((Facture)response);
			}
		}
		return list;
	}

	public static String getSQLUpdateForDateFacture(Facture facture){
		return models.daos.server.DAOFacture.getSQLUpdateForDateFacture(facture);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForDateFactureByPreparedStatement(Facture facture){
		return models.daos.server.DAOFacture.getSQLUpdateForDateFactureByPreparedStatement(facture);
	}

	public static void updateDateFacture(Facture facture){
		String sqlUpdate = getSQLUpdateForDateFacture(facture);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForIdClient(Facture facture){
		return models.daos.server.DAOFacture.getSQLUpdateForIdClient(facture);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIdClientByPreparedStatement(Facture facture){
		return models.daos.server.DAOFacture.getSQLUpdateForIdClientByPreparedStatement(facture);
	}

	public static void updateIdClient(Facture facture){
		String sqlUpdate = getSQLUpdateForIdClient(facture);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForMontant(Facture facture){
		return models.daos.server.DAOFacture.getSQLUpdateForMontant(facture);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForMontantByPreparedStatement(Facture facture){
		return models.daos.server.DAOFacture.getSQLUpdateForMontantByPreparedStatement(facture);
	}

	public static void updateMontant(Facture facture){
		String sqlUpdate = getSQLUpdateForMontant(facture);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForTypePayementC(Facture facture){
		return models.daos.server.DAOFacture.getSQLUpdateForTypePayementC(facture);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForTypePayementCByPreparedStatement(Facture facture){
		return models.daos.server.DAOFacture.getSQLUpdateForTypePayementCByPreparedStatement(facture);
	}

	public static void updateTypePayementC(Facture facture){
		String sqlUpdate = getSQLUpdateForTypePayementC(facture);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getConcatIDs(){
		return getConcatIDs("");
	}

	public static String getConcatIDs(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return (String)communication.SocketCommunicator.getInstance().sendQuery("select group_concat(id) from `facture` "+whereCondition);
	}

	public static int getCountInTable(){
		return getCountInTable("");
	}

	public static int getCountInTable(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return (Integer)communication.SocketCommunicator.getInstance().sendQuery("select count(id) from `facture` "+whereCondition);
	}

	public static int getCountInTableFromServer(){
		return getCountInTableFromServer("");
	}

	public static int getCountInTableFromServer(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return ((Integer)communication.SocketCommunicator.getInstance().sendQuery("select count(id) from `facture` "+whereCondition)).intValue();
	}

	public static String getSQLTuple(Facture facture){
		return models.daos.server.DAOFacture.getSQLTuple(facture);
	}

	public static String getSQLStructure(boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOFacture.getSQLStructure(foreignKeyCheck, addDropTable);
	}

	public static String getSQLStructure(boolean addDropTable){
		return models.daos.server.DAOFacture.getSQLStructure(addDropTable);
	}

	public static String getSQLStructure(){
		return getSQLStructure(true);
	}

	public static String getSQLContent(boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOFacture.getSQLContent(foreignKeyCheck, addDropTable);
	}

	public static String getSQLContent(java.util.List<Facture> list, boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOFacture.getSQLContent(list, foreignKeyCheck, addDropTable);
	}

	public static String showSQLStructure(){
		return models.daos.server.DAOFacture.showSQLStructure();
	}

	public static String getTableName(){
		return models.daos.server.DAOFacture.getTableName();
	}
}