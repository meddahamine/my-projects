package models.daos.client;

import models.beans.CategorieChambre;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>Remote Access for DAO
 */

public abstract class DAOCategorieChambre {
	public static CategorieChambre getCategorieChambre(int id) {
		if (id == 0)
			return new CategorieChambre();
		String query = "select * from `categorieChambre` where `id`="+id+" limit 1";
		return (CategorieChambre)communication.SocketCommunicator.getInstance().sendQuery(query);
	}

	public static String getSQLDeleting(CategorieChambre categorieChambre) {
		return models.daos.server.DAOCategorieChambre.getSQLDeleting(categorieChambre);
	}

	public static String getSQLDeleting() {
		return models.daos.server.DAOCategorieChambre.getSQLDeleting();
	}

	public static String getSQLWriting(CategorieChambre categorieChambre) {
		return models.daos.server.DAOCategorieChambre.getSQLWriting(categorieChambre);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLWritingByPreparedStatement(CategorieChambre categorieChambre) {
		return models.daos.server.DAOCategorieChambre.getSQLWritingByPreparedStatement(categorieChambre);
	}

	public static int write(CategorieChambre categorieChambre) {
		int id = (Integer)communication.SocketCommunicator.getInstance().sendQuery(categorieChambre);
		categorieChambre.setId(id);
		return id;
	}

	public static void writeByPreparedStatement(CategorieChambre categorieChambre) {
		utils.SGBDConfig.InsertUpdateSQLQueries writingQuery = getSQLWritingByPreparedStatement(categorieChambre);
		communication.SocketCommunicator.getInstance().sendQuery(writingQuery);
	}

	public static void delete(CategorieChambre categorieChambre){
		communication.SocketCommunicator.getInstance().sendQuery(getSQLDeleting(categorieChambre));
	}

	public static void deleteAll(){
		communication.SocketCommunicator.getInstance().sendQuery(getSQLDeleting());
	}

	public static java.util.List<CategorieChambre> getListInstances(){
		return getListInstances("");
	}

	public static java.util.List<CategorieChambre> getListInstances(String whereCondition){
		String query = "select * from categorieChambre";
		if (!whereCondition.trim().equals("")){
			if (!whereCondition.trim().toLowerCase().equals("") && !whereCondition.trim().toLowerCase().startsWith("where")){
				query += " where ";
			}
			query += " "+whereCondition;
		}
		return getListInstancesBySQLQuery(query);
	}

@SuppressWarnings("unchecked")
	public static java.util.List<CategorieChambre> getListInstancesBySQLQuery(String sqlQuery){
		java.util.List<CategorieChambre> list = new java.util.ArrayList<CategorieChambre>();
		Object response = communication.SocketCommunicator.getInstance().sendQuery(sqlQuery);
		if (response != null){
			if (response instanceof java.util.List<?>){
				list = (java.util.List<CategorieChambre>)response;
			}
			else{
				list.add((CategorieChambre)response);
			}
		}
		return list;
	}

	public static String getSQLUpdateForLibelle(CategorieChambre categorieChambre){
		return models.daos.server.DAOCategorieChambre.getSQLUpdateForLibelle(categorieChambre);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForLibelleByPreparedStatement(CategorieChambre categorieChambre){
		return models.daos.server.DAOCategorieChambre.getSQLUpdateForLibelleByPreparedStatement(categorieChambre);
	}

	public static void updateLibelle(CategorieChambre categorieChambre){
		String sqlUpdate = getSQLUpdateForLibelle(categorieChambre);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getConcatIDs(){
		return getConcatIDs("");
	}

	public static String getConcatIDs(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return (String)communication.SocketCommunicator.getInstance().sendQuery("select group_concat(id) from `categorieChambre` "+whereCondition);
	}

	public static int getCountInTable(){
		return getCountInTable("");
	}

	public static int getCountInTable(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return (Integer)communication.SocketCommunicator.getInstance().sendQuery("select count(id) from `categorieChambre` "+whereCondition);
	}

	public static int getCountInTableFromServer(){
		return getCountInTableFromServer("");
	}

	public static int getCountInTableFromServer(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return ((Integer)communication.SocketCommunicator.getInstance().sendQuery("select count(id) from `categorieChambre` "+whereCondition)).intValue();
	}

	public static String getSQLTuple(CategorieChambre categorieChambre){
		return models.daos.server.DAOCategorieChambre.getSQLTuple(categorieChambre);
	}

	public static String getSQLStructure(boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOCategorieChambre.getSQLStructure(foreignKeyCheck, addDropTable);
	}

	public static String getSQLStructure(boolean addDropTable){
		return models.daos.server.DAOCategorieChambre.getSQLStructure(addDropTable);
	}

	public static String getSQLStructure(){
		return getSQLStructure(true);
	}

	public static String getSQLContent(boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOCategorieChambre.getSQLContent(foreignKeyCheck, addDropTable);
	}

	public static String getSQLContent(java.util.List<CategorieChambre> list, boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOCategorieChambre.getSQLContent(list, foreignKeyCheck, addDropTable);
	}

	public static String showSQLStructure(){
		return models.daos.server.DAOCategorieChambre.showSQLStructure();
	}

	public static String getTableName(){
		return models.daos.server.DAOCategorieChambre.getTableName();
	}
	
	public static java.util.List<models.beans.Chambre> getListOfChambresCategorieChambre(models.beans.CategorieChambre categorieChambre){
		java.util.List<models.beans.Chambre> listOfChambresCategorieChambre = models.daos.client.DAOChambre.getListInstances("where idCategorieChambre='"+categorieChambre.getId().intValue()+"'");
		return listOfChambresCategorieChambre;
	}
}