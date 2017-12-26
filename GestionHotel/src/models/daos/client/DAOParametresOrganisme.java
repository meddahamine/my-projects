package models.daos.client;

import models.beans.ParametresOrganisme;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>Remote Access for DAO
 */

public abstract class DAOParametresOrganisme {
	public static ParametresOrganisme getParametresOrganisme(int id) {
		if (id == 0)
			return new ParametresOrganisme();
		String query = "select * from `parametresOrganisme` where `id`="+id+" limit 1";
		return (ParametresOrganisme)communication.SocketCommunicator.getInstance().sendQuery(query);
	}

	public static String getSQLDeleting(ParametresOrganisme parametresOrganisme) {
		return models.daos.server.DAOParametresOrganisme.getSQLDeleting(parametresOrganisme);
	}

	public static String getSQLDeleting() {
		return models.daos.server.DAOParametresOrganisme.getSQLDeleting();
	}

	public static String getSQLWriting(ParametresOrganisme parametresOrganisme) {
		return models.daos.server.DAOParametresOrganisme.getSQLWriting(parametresOrganisme);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLWritingByPreparedStatement(ParametresOrganisme parametresOrganisme) {
		return models.daos.server.DAOParametresOrganisme.getSQLWritingByPreparedStatement(parametresOrganisme);
	}

	public static int write(ParametresOrganisme parametresOrganisme) {
		int id = (Integer)communication.SocketCommunicator.getInstance().sendQuery(parametresOrganisme);
		parametresOrganisme.setId(id);
		return id;
	}

	public static void writeByPreparedStatement(ParametresOrganisme parametresOrganisme) {
		utils.SGBDConfig.InsertUpdateSQLQueries writingQuery = getSQLWritingByPreparedStatement(parametresOrganisme);
		communication.SocketCommunicator.getInstance().sendQuery(writingQuery);
	}

	public static void delete(ParametresOrganisme parametresOrganisme){
		communication.SocketCommunicator.getInstance().sendQuery(getSQLDeleting(parametresOrganisme));
	}

	public static void deleteAll(){
		communication.SocketCommunicator.getInstance().sendQuery(getSQLDeleting());
	}

	public static java.util.List<ParametresOrganisme> getListInstances(){
		return getListInstances("");
	}

	public static java.util.List<ParametresOrganisme> getListInstances(String whereCondition){
		String query = "select * from parametresOrganisme";
		if (!whereCondition.trim().equals("")){
			if (!whereCondition.trim().toLowerCase().equals("") && !whereCondition.trim().toLowerCase().startsWith("where")){
				query += " where ";
			}
			query += " "+whereCondition;
		}
		return getListInstancesBySQLQuery(query);
	}

@SuppressWarnings("unchecked")
	public static java.util.List<ParametresOrganisme> getListInstancesBySQLQuery(String sqlQuery){
		java.util.List<ParametresOrganisme> list = new java.util.ArrayList<ParametresOrganisme>();
		Object response = communication.SocketCommunicator.getInstance().sendQuery(sqlQuery);
		if (response != null){
			if (response instanceof java.util.List<?>){
				list = (java.util.List<ParametresOrganisme>)response;
			}
			else{
				list.add((ParametresOrganisme)response);
			}
		}
		return list;
	}

	public static String getSQLUpdateForDesignationOrganisme(ParametresOrganisme parametresOrganisme){
		return models.daos.server.DAOParametresOrganisme.getSQLUpdateForDesignationOrganisme(parametresOrganisme);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForDesignationOrganismeByPreparedStatement(ParametresOrganisme parametresOrganisme){
		return models.daos.server.DAOParametresOrganisme.getSQLUpdateForDesignationOrganismeByPreparedStatement(parametresOrganisme);
	}

	public static void updateDesignationOrganisme(ParametresOrganisme parametresOrganisme){
		String sqlUpdate = getSQLUpdateForDesignationOrganisme(parametresOrganisme);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForRaisonSocial(ParametresOrganisme parametresOrganisme){
		return models.daos.server.DAOParametresOrganisme.getSQLUpdateForRaisonSocial(parametresOrganisme);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForRaisonSocialByPreparedStatement(ParametresOrganisme parametresOrganisme){
		return models.daos.server.DAOParametresOrganisme.getSQLUpdateForRaisonSocialByPreparedStatement(parametresOrganisme);
	}

	public static void updateRaisonSocial(ParametresOrganisme parametresOrganisme){
		String sqlUpdate = getSQLUpdateForRaisonSocial(parametresOrganisme);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForCapitalSocial(ParametresOrganisme parametresOrganisme){
		return models.daos.server.DAOParametresOrganisme.getSQLUpdateForCapitalSocial(parametresOrganisme);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForCapitalSocialByPreparedStatement(ParametresOrganisme parametresOrganisme){
		return models.daos.server.DAOParametresOrganisme.getSQLUpdateForCapitalSocialByPreparedStatement(parametresOrganisme);
	}

	public static void updateCapitalSocial(ParametresOrganisme parametresOrganisme){
		String sqlUpdate = getSQLUpdateForCapitalSocial(parametresOrganisme);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForNumRC(ParametresOrganisme parametresOrganisme){
		return models.daos.server.DAOParametresOrganisme.getSQLUpdateForNumRC(parametresOrganisme);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForNumRCByPreparedStatement(ParametresOrganisme parametresOrganisme){
		return models.daos.server.DAOParametresOrganisme.getSQLUpdateForNumRCByPreparedStatement(parametresOrganisme);
	}

	public static void updateNumRC(ParametresOrganisme parametresOrganisme){
		String sqlUpdate = getSQLUpdateForNumRC(parametresOrganisme);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForNumCB(ParametresOrganisme parametresOrganisme){
		return models.daos.server.DAOParametresOrganisme.getSQLUpdateForNumCB(parametresOrganisme);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForNumCBByPreparedStatement(ParametresOrganisme parametresOrganisme){
		return models.daos.server.DAOParametresOrganisme.getSQLUpdateForNumCBByPreparedStatement(parametresOrganisme);
	}

	public static void updateNumCB(ParametresOrganisme parametresOrganisme){
		String sqlUpdate = getSQLUpdateForNumCB(parametresOrganisme);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForIdentificationFiscale(ParametresOrganisme parametresOrganisme){
		return models.daos.server.DAOParametresOrganisme.getSQLUpdateForIdentificationFiscale(parametresOrganisme);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIdentificationFiscaleByPreparedStatement(ParametresOrganisme parametresOrganisme){
		return models.daos.server.DAOParametresOrganisme.getSQLUpdateForIdentificationFiscaleByPreparedStatement(parametresOrganisme);
	}

	public static void updateIdentificationFiscale(ParametresOrganisme parametresOrganisme){
		String sqlUpdate = getSQLUpdateForIdentificationFiscale(parametresOrganisme);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForNumArticle(ParametresOrganisme parametresOrganisme){
		return models.daos.server.DAOParametresOrganisme.getSQLUpdateForNumArticle(parametresOrganisme);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForNumArticleByPreparedStatement(ParametresOrganisme parametresOrganisme){
		return models.daos.server.DAOParametresOrganisme.getSQLUpdateForNumArticleByPreparedStatement(parametresOrganisme);
	}

	public static void updateNumArticle(ParametresOrganisme parametresOrganisme){
		String sqlUpdate = getSQLUpdateForNumArticle(parametresOrganisme);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForNIS(ParametresOrganisme parametresOrganisme){
		return models.daos.server.DAOParametresOrganisme.getSQLUpdateForNIS(parametresOrganisme);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForNISByPreparedStatement(ParametresOrganisme parametresOrganisme){
		return models.daos.server.DAOParametresOrganisme.getSQLUpdateForNISByPreparedStatement(parametresOrganisme);
	}

	public static void updateNIS(ParametresOrganisme parametresOrganisme){
		String sqlUpdate = getSQLUpdateForNIS(parametresOrganisme);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForAdresse(ParametresOrganisme parametresOrganisme){
		return models.daos.server.DAOParametresOrganisme.getSQLUpdateForAdresse(parametresOrganisme);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForAdresseByPreparedStatement(ParametresOrganisme parametresOrganisme){
		return models.daos.server.DAOParametresOrganisme.getSQLUpdateForAdresseByPreparedStatement(parametresOrganisme);
	}

	public static void updateAdresse(ParametresOrganisme parametresOrganisme){
		String sqlUpdate = getSQLUpdateForAdresse(parametresOrganisme);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForBoitePostale(ParametresOrganisme parametresOrganisme){
		return models.daos.server.DAOParametresOrganisme.getSQLUpdateForBoitePostale(parametresOrganisme);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForBoitePostaleByPreparedStatement(ParametresOrganisme parametresOrganisme){
		return models.daos.server.DAOParametresOrganisme.getSQLUpdateForBoitePostaleByPreparedStatement(parametresOrganisme);
	}

	public static void updateBoitePostale(ParametresOrganisme parametresOrganisme){
		String sqlUpdate = getSQLUpdateForBoitePostale(parametresOrganisme);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForNumTel(ParametresOrganisme parametresOrganisme){
		return models.daos.server.DAOParametresOrganisme.getSQLUpdateForNumTel(parametresOrganisme);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForNumTelByPreparedStatement(ParametresOrganisme parametresOrganisme){
		return models.daos.server.DAOParametresOrganisme.getSQLUpdateForNumTelByPreparedStatement(parametresOrganisme);
	}

	public static void updateNumTel(ParametresOrganisme parametresOrganisme){
		String sqlUpdate = getSQLUpdateForNumTel(parametresOrganisme);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForNumFax(ParametresOrganisme parametresOrganisme){
		return models.daos.server.DAOParametresOrganisme.getSQLUpdateForNumFax(parametresOrganisme);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForNumFaxByPreparedStatement(ParametresOrganisme parametresOrganisme){
		return models.daos.server.DAOParametresOrganisme.getSQLUpdateForNumFaxByPreparedStatement(parametresOrganisme);
	}

	public static void updateNumFax(ParametresOrganisme parametresOrganisme){
		String sqlUpdate = getSQLUpdateForNumFax(parametresOrganisme);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForEmail(ParametresOrganisme parametresOrganisme){
		return models.daos.server.DAOParametresOrganisme.getSQLUpdateForEmail(parametresOrganisme);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForEmailByPreparedStatement(ParametresOrganisme parametresOrganisme){
		return models.daos.server.DAOParametresOrganisme.getSQLUpdateForEmailByPreparedStatement(parametresOrganisme);
	}

	public static void updateEmail(ParametresOrganisme parametresOrganisme){
		String sqlUpdate = getSQLUpdateForEmail(parametresOrganisme);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForDescriptif(ParametresOrganisme parametresOrganisme){
		return models.daos.server.DAOParametresOrganisme.getSQLUpdateForDescriptif(parametresOrganisme);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForDescriptifByPreparedStatement(ParametresOrganisme parametresOrganisme){
		return models.daos.server.DAOParametresOrganisme.getSQLUpdateForDescriptifByPreparedStatement(parametresOrganisme);
	}

	public static void updateDescriptif(ParametresOrganisme parametresOrganisme){
		String sqlUpdate = getSQLUpdateForDescriptif(parametresOrganisme);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForIdPhoto(ParametresOrganisme parametresOrganisme){
		return models.daos.server.DAOParametresOrganisme.getSQLUpdateForIdPhoto(parametresOrganisme);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIdPhotoByPreparedStatement(ParametresOrganisme parametresOrganisme){
		return models.daos.server.DAOParametresOrganisme.getSQLUpdateForIdPhotoByPreparedStatement(parametresOrganisme);
	}

	public static void updateIdPhoto(ParametresOrganisme parametresOrganisme){
		String sqlUpdate = getSQLUpdateForIdPhoto(parametresOrganisme);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getConcatIDs(){
		return getConcatIDs("");
	}

	public static String getConcatIDs(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return (String)communication.SocketCommunicator.getInstance().sendQuery("select group_concat(id) from `parametresOrganisme` "+whereCondition);
	}

	public static int getCountInTable(){
		return getCountInTable("");
	}

	public static int getCountInTable(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return (Integer)communication.SocketCommunicator.getInstance().sendQuery("select count(id) from `parametresOrganisme` "+whereCondition);
	}

	public static int getCountInTableFromServer(){
		return getCountInTableFromServer("");
	}

	public static int getCountInTableFromServer(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return ((Integer)communication.SocketCommunicator.getInstance().sendQuery("select count(id) from `parametresOrganisme` "+whereCondition)).intValue();
	}

	public static String getSQLTuple(ParametresOrganisme parametresOrganisme){
		return models.daos.server.DAOParametresOrganisme.getSQLTuple(parametresOrganisme);
	}

	public static String getSQLStructure(boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOParametresOrganisme.getSQLStructure(foreignKeyCheck, addDropTable);
	}

	public static String getSQLStructure(boolean addDropTable){
		return models.daos.server.DAOParametresOrganisme.getSQLStructure(addDropTable);
	}

	public static String getSQLStructure(){
		return getSQLStructure(true);
	}

	public static String getSQLContent(boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOParametresOrganisme.getSQLContent(foreignKeyCheck, addDropTable);
	}

	public static String getSQLContent(java.util.List<ParametresOrganisme> list, boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOParametresOrganisme.getSQLContent(list, foreignKeyCheck, addDropTable);
	}

	public static String showSQLStructure(){
		return models.daos.server.DAOParametresOrganisme.showSQLStructure();
	}

	public static String getTableName(){
		return models.daos.server.DAOParametresOrganisme.getTableName();
	}
}