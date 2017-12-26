package models.daos.client;

import models.beans.Client;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>Remote Access for DAO
 */

public abstract class DAOClient {
	public static Client getClient(int id) {
		if (id == 0)
			return new Client();
		String query = "select * from `client` where `id`="+id+" limit 1";
		return (Client)communication.SocketCommunicator.getInstance().sendQuery(query);
	}

	public static String getSQLDeleting(Client client) {
		return models.daos.server.DAOClient.getSQLDeleting(client);
	}

	public static String getSQLDeleting() {
		return models.daos.server.DAOClient.getSQLDeleting();
	}

	public static String getSQLWriting(Client client) {
		return models.daos.server.DAOClient.getSQLWriting(client);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLWritingByPreparedStatement(Client client) {
		return models.daos.server.DAOClient.getSQLWritingByPreparedStatement(client);
	}

	public static int write(Client client) {
		int id = (Integer)communication.SocketCommunicator.getInstance().sendQuery(client);
		client.setId(id);
		return id;
	}

	public static void writeByPreparedStatement(Client client) {
		utils.SGBDConfig.InsertUpdateSQLQueries writingQuery = getSQLWritingByPreparedStatement(client);
		communication.SocketCommunicator.getInstance().sendQuery(writingQuery);
	}

	public static void delete(Client client){
		communication.SocketCommunicator.getInstance().sendQuery(getSQLDeleting(client));
	}

	public static void deleteAll(){
		communication.SocketCommunicator.getInstance().sendQuery(getSQLDeleting());
	}

	public static java.util.List<Client> getListInstances(){
		return getListInstances("");
	}

	public static java.util.List<Client> getListInstances(String whereCondition){
		String query = "select * from client";
		if (!whereCondition.trim().equals("")){
			if (!whereCondition.trim().toLowerCase().equals("") && !whereCondition.trim().toLowerCase().startsWith("where")){
				query += " where ";
			}
			query += " "+whereCondition;
		}
		return getListInstancesBySQLQuery(query);
	}

@SuppressWarnings("unchecked")
	public static java.util.List<Client> getListInstancesBySQLQuery(String sqlQuery){
		java.util.List<Client> list = new java.util.ArrayList<Client>();
		Object response = communication.SocketCommunicator.getInstance().sendQuery(sqlQuery);
		if (response != null){
			if (response instanceof java.util.List<?>){
				list = (java.util.List<Client>)response;
			}
			else{
				list.add((Client)response);
			}
		}
		return list;
	}

	public static String getSQLUpdateForTypeClient(Client client){
		return models.daos.server.DAOClient.getSQLUpdateForTypeClient(client);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForTypeClientByPreparedStatement(Client client){
		return models.daos.server.DAOClient.getSQLUpdateForTypeClientByPreparedStatement(client);
	}

	public static void updateTypeClient(Client client){
		String sqlUpdate = getSQLUpdateForTypeClient(client);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForNom(Client client){
		return models.daos.server.DAOClient.getSQLUpdateForNom(client);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForNomByPreparedStatement(Client client){
		return models.daos.server.DAOClient.getSQLUpdateForNomByPreparedStatement(client);
	}

	public static void updateNom(Client client){
		String sqlUpdate = getSQLUpdateForNom(client);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForPrenom(Client client){
		return models.daos.server.DAOClient.getSQLUpdateForPrenom(client);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForPrenomByPreparedStatement(Client client){
		return models.daos.server.DAOClient.getSQLUpdateForPrenomByPreparedStatement(client);
	}

	public static void updatePrenom(Client client){
		String sqlUpdate = getSQLUpdateForPrenom(client);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForDateNaiss(Client client){
		return models.daos.server.DAOClient.getSQLUpdateForDateNaiss(client);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForDateNaissByPreparedStatement(Client client){
		return models.daos.server.DAOClient.getSQLUpdateForDateNaissByPreparedStatement(client);
	}

	public static void updateDateNaiss(Client client){
		String sqlUpdate = getSQLUpdateForDateNaiss(client);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForAdresse(Client client){
		return models.daos.server.DAOClient.getSQLUpdateForAdresse(client);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForAdresseByPreparedStatement(Client client){
		return models.daos.server.DAOClient.getSQLUpdateForAdresseByPreparedStatement(client);
	}

	public static void updateAdresse(Client client){
		String sqlUpdate = getSQLUpdateForAdresse(client);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForNationalite(Client client){
		return models.daos.server.DAOClient.getSQLUpdateForNationalite(client);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForNationaliteByPreparedStatement(Client client){
		return models.daos.server.DAOClient.getSQLUpdateForNationaliteByPreparedStatement(client);
	}

	public static void updateNationalite(Client client){
		String sqlUpdate = getSQLUpdateForNationalite(client);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForTelephone(Client client){
		return models.daos.server.DAOClient.getSQLUpdateForTelephone(client);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForTelephoneByPreparedStatement(Client client){
		return models.daos.server.DAOClient.getSQLUpdateForTelephoneByPreparedStatement(client);
	}

	public static void updateTelephone(Client client){
		String sqlUpdate = getSQLUpdateForTelephone(client);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForTypeCarte(Client client){
		return models.daos.server.DAOClient.getSQLUpdateForTypeCarte(client);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForTypeCarteByPreparedStatement(Client client){
		return models.daos.server.DAOClient.getSQLUpdateForTypeCarteByPreparedStatement(client);
	}

	public static void updateTypeCarte(Client client){
		String sqlUpdate = getSQLUpdateForTypeCarte(client);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForNumCarte(Client client){
		return models.daos.server.DAOClient.getSQLUpdateForNumCarte(client);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForNumCarteByPreparedStatement(Client client){
		return models.daos.server.DAOClient.getSQLUpdateForNumCarteByPreparedStatement(client);
	}

	public static void updateNumCarte(Client client){
		String sqlUpdate = getSQLUpdateForNumCarte(client);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForProfession(Client client){
		return models.daos.server.DAOClient.getSQLUpdateForProfession(client);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForProfessionByPreparedStatement(Client client){
		return models.daos.server.DAOClient.getSQLUpdateForProfessionByPreparedStatement(client);
	}

	public static void updateProfession(Client client){
		String sqlUpdate = getSQLUpdateForProfession(client);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForAutreTypeClient(Client client){
		return models.daos.server.DAOClient.getSQLUpdateForAutreTypeClient(client);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForAutreTypeClientByPreparedStatement(Client client){
		return models.daos.server.DAOClient.getSQLUpdateForAutreTypeClientByPreparedStatement(client);
	}

	public static void updateAutreTypeClient(Client client){
		String sqlUpdate = getSQLUpdateForAutreTypeClient(client);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForAutreTypeCarte(Client client){
		return models.daos.server.DAOClient.getSQLUpdateForAutreTypeCarte(client);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForAutreTypeCarteByPreparedStatement(Client client){
		return models.daos.server.DAOClient.getSQLUpdateForAutreTypeCarteByPreparedStatement(client);
	}

	public static void updateAutreTypeCarte(Client client){
		String sqlUpdate = getSQLUpdateForAutreTypeCarte(client);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForLieuDeNaiss(Client client){
		return models.daos.server.DAOClient.getSQLUpdateForLieuDeNaiss(client);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForLieuDeNaissByPreparedStatement(Client client){
		return models.daos.server.DAOClient.getSQLUpdateForLieuDeNaissByPreparedStatement(client);
	}

	public static void updateLieuDeNaiss(Client client){
		String sqlUpdate = getSQLUpdateForLieuDeNaiss(client);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForCivilite(Client client){
		return models.daos.server.DAOClient.getSQLUpdateForCivilite(client);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForCiviliteByPreparedStatement(Client client){
		return models.daos.server.DAOClient.getSQLUpdateForCiviliteByPreparedStatement(client);
	}

	public static void updateCivilite(Client client){
		String sqlUpdate = getSQLUpdateForCivilite(client);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getConcatIDs(){
		return getConcatIDs("");
	}

	public static String getConcatIDs(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return (String)communication.SocketCommunicator.getInstance().sendQuery("select group_concat(id) from `client` "+whereCondition);
	}

	public static int getCountInTable(){
		return getCountInTable("");
	}

	public static int getCountInTable(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return (Integer)communication.SocketCommunicator.getInstance().sendQuery("select count(id) from `client` "+whereCondition);
	}

	public static int getCountInTableFromServer(){
		return getCountInTableFromServer("");
	}

	public static int getCountInTableFromServer(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return ((Integer)communication.SocketCommunicator.getInstance().sendQuery("select count(id) from `client` "+whereCondition)).intValue();
	}

	public static String getSQLTuple(Client client){
		return models.daos.server.DAOClient.getSQLTuple(client);
	}

	public static String getSQLStructure(boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOClient.getSQLStructure(foreignKeyCheck, addDropTable);
	}

	public static String getSQLStructure(boolean addDropTable){
		return models.daos.server.DAOClient.getSQLStructure(addDropTable);
	}

	public static String getSQLStructure(){
		return getSQLStructure(true);
	}

	public static String getSQLContent(boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOClient.getSQLContent(foreignKeyCheck, addDropTable);
	}

	public static String getSQLContent(java.util.List<Client> list, boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOClient.getSQLContent(list, foreignKeyCheck, addDropTable);
	}

	public static String showSQLStructure(){
		return models.daos.server.DAOClient.showSQLStructure();
	}

	public static String getTableName(){
		return models.daos.server.DAOClient.getTableName();
	}
	
	public static java.util.List<models.beans.ClientConsommeService> getListOfClientConsommeServicesClient(models.beans.Client client){
		java.util.List<models.beans.ClientConsommeService> listOfClientConsommeServicesClient = models.daos.client.DAOClientConsommeService.getListInstances("where idClient='"+client.getId().intValue()+"'");
		return listOfClientConsommeServicesClient;
	}
	
	public static java.util.List<models.beans.ClientReseveChambre> getListOfClientReseveChambresClient(models.beans.Client client){
		java.util.List<models.beans.ClientReseveChambre> listOfClientReseveChambresClient = models.daos.client.DAOClientReseveChambre.getListInstances("where idClient='"+client.getId().intValue()+"'");
		return listOfClientReseveChambresClient;
	}
	
	public static java.util.List<models.beans.Facture> getListOfFacturesClient(models.beans.Client client){
		java.util.List<models.beans.Facture> listOfFacturesClient = models.daos.client.DAOFacture.getListInstances("where idClient='"+client.getId().intValue()+"'");
		return listOfFacturesClient;
	}
}