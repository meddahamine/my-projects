package models.daos.client;

import models.beans.Chambre;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>Remote Access for DAO
 */

public abstract class DAOChambre {
	public static Chambre getChambre(int id) {
		if (id == 0)
			return new Chambre();
		String query = "select * from `chambre` where `id`="+id+" limit 1";
		return (Chambre)communication.SocketCommunicator.getInstance().sendQuery(query);
	}

	public static String getSQLDeleting(Chambre chambre) {
		return models.daos.server.DAOChambre.getSQLDeleting(chambre);
	}

	public static String getSQLDeleting() {
		return models.daos.server.DAOChambre.getSQLDeleting();
	}

	public static String getSQLWriting(Chambre chambre) {
		return models.daos.server.DAOChambre.getSQLWriting(chambre);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLWritingByPreparedStatement(Chambre chambre) {
		return models.daos.server.DAOChambre.getSQLWritingByPreparedStatement(chambre);
	}

	public static int write(Chambre chambre) {
		int id = (Integer)communication.SocketCommunicator.getInstance().sendQuery(chambre);
		chambre.setId(id);
		return id;
	}

	public static void writeByPreparedStatement(Chambre chambre) {
		utils.SGBDConfig.InsertUpdateSQLQueries writingQuery = getSQLWritingByPreparedStatement(chambre);
		communication.SocketCommunicator.getInstance().sendQuery(writingQuery);
	}

	public static void delete(Chambre chambre){
		communication.SocketCommunicator.getInstance().sendQuery(getSQLDeleting(chambre));
	}

	public static void deleteAll(){
		communication.SocketCommunicator.getInstance().sendQuery(getSQLDeleting());
	}

	public static java.util.List<Chambre> getListInstances(){
		return getListInstances("");
	}

	public static java.util.List<Chambre> getListInstances(String whereCondition){
		String query = "select * from chambre";
		if (!whereCondition.trim().equals("")){
			if (!whereCondition.trim().toLowerCase().equals("") && !whereCondition.trim().toLowerCase().startsWith("where")){
				query += " where ";
			}
			query += " "+whereCondition;
		}
		return getListInstancesBySQLQuery(query);
	}

@SuppressWarnings("unchecked")
	public static java.util.List<Chambre> getListInstancesBySQLQuery(String sqlQuery){
		java.util.List<Chambre> list = new java.util.ArrayList<Chambre>();
		Object response = communication.SocketCommunicator.getInstance().sendQuery(sqlQuery);
		if (response != null){
			if (response instanceof java.util.List<?>){
				list = (java.util.List<Chambre>)response;
			}
			else{
				list.add((Chambre)response);
			}
		}
		return list;
	}

	public static String getSQLUpdateForNumChamre(Chambre chambre){
		return models.daos.server.DAOChambre.getSQLUpdateForNumChamre(chambre);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForNumChamreByPreparedStatement(Chambre chambre){
		return models.daos.server.DAOChambre.getSQLUpdateForNumChamreByPreparedStatement(chambre);
	}

	public static void updateNumChamre(Chambre chambre){
		String sqlUpdate = getSQLUpdateForNumChamre(chambre);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForNumEtage(Chambre chambre){
		return models.daos.server.DAOChambre.getSQLUpdateForNumEtage(chambre);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForNumEtageByPreparedStatement(Chambre chambre){
		return models.daos.server.DAOChambre.getSQLUpdateForNumEtageByPreparedStatement(chambre);
	}

	public static void updateNumEtage(Chambre chambre){
		String sqlUpdate = getSQLUpdateForNumEtage(chambre);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForIdCategorieChambre(Chambre chambre){
		return models.daos.server.DAOChambre.getSQLUpdateForIdCategorieChambre(chambre);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIdCategorieChambreByPreparedStatement(Chambre chambre){
		return models.daos.server.DAOChambre.getSQLUpdateForIdCategorieChambreByPreparedStatement(chambre);
	}

	public static void updateIdCategorieChambre(Chambre chambre){
		String sqlUpdate = getSQLUpdateForIdCategorieChambre(chambre);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForPrix(Chambre chambre){
		return models.daos.server.DAOChambre.getSQLUpdateForPrix(chambre);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForPrixByPreparedStatement(Chambre chambre){
		return models.daos.server.DAOChambre.getSQLUpdateForPrixByPreparedStatement(chambre);
	}

	public static void updatePrix(Chambre chambre){
		String sqlUpdate = getSQLUpdateForPrix(chambre);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getConcatIDs(){
		return getConcatIDs("");
	}

	public static String getConcatIDs(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return (String)communication.SocketCommunicator.getInstance().sendQuery("select group_concat(id) from `chambre` "+whereCondition);
	}

	public static int getCountInTable(){
		return getCountInTable("");
	}

	public static int getCountInTable(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return (Integer)communication.SocketCommunicator.getInstance().sendQuery("select count(id) from `chambre` "+whereCondition);
	}

	public static int getCountInTableFromServer(){
		return getCountInTableFromServer("");
	}

	public static int getCountInTableFromServer(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return ((Integer)communication.SocketCommunicator.getInstance().sendQuery("select count(id) from `chambre` "+whereCondition)).intValue();
	}

	public static String getSQLTuple(Chambre chambre){
		return models.daos.server.DAOChambre.getSQLTuple(chambre);
	}

	public static String getSQLStructure(boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOChambre.getSQLStructure(foreignKeyCheck, addDropTable);
	}

	public static String getSQLStructure(boolean addDropTable){
		return models.daos.server.DAOChambre.getSQLStructure(addDropTable);
	}

	public static String getSQLStructure(){
		return getSQLStructure(true);
	}

	public static String getSQLContent(boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOChambre.getSQLContent(foreignKeyCheck, addDropTable);
	}

	public static String getSQLContent(java.util.List<Chambre> list, boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOChambre.getSQLContent(list, foreignKeyCheck, addDropTable);
	}

	public static String showSQLStructure(){
		return models.daos.server.DAOChambre.showSQLStructure();
	}

	public static String getTableName(){
		return models.daos.server.DAOChambre.getTableName();
	}
	
	public static java.util.List<models.beans.ClientReseveChambre> getListOfClientReseveChambresChambre(models.beans.Chambre chambre){
		java.util.List<models.beans.ClientReseveChambre> listOfClientReseveChambresChambre = models.daos.client.DAOClientReseveChambre.getListInstances("where idChambre='"+chambre.getId().intValue()+"'");
		return listOfClientReseveChambresChambre;
	}
}