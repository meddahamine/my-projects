package models.daos.client;

import models.beans.Role;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>Remote Access for DAO
 */

public abstract class DAORole {
	public static Role getRole(int id) {
		if (id == 0)
			return new Role();
		String query = "select * from `role` where `id`="+id+" limit 1";
		return (Role)communication.SocketCommunicator.getInstance().sendQuery(query);
	}

	public static String getSQLDeleting(Role role) {
		return models.daos.server.DAORole.getSQLDeleting(role);
	}

	public static String getSQLDeleting() {
		return models.daos.server.DAORole.getSQLDeleting();
	}

	public static String getSQLWriting(Role role) {
		return models.daos.server.DAORole.getSQLWriting(role);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLWritingByPreparedStatement(Role role) {
		return models.daos.server.DAORole.getSQLWritingByPreparedStatement(role);
	}

	public static int write(Role role) {
		int id = (Integer)communication.SocketCommunicator.getInstance().sendQuery(role);
		role.setId(id);
		return id;
	}

	public static void writeByPreparedStatement(Role role) {
		utils.SGBDConfig.InsertUpdateSQLQueries writingQuery = getSQLWritingByPreparedStatement(role);
		communication.SocketCommunicator.getInstance().sendQuery(writingQuery);
	}

	public static void delete(Role role){
		communication.SocketCommunicator.getInstance().sendQuery(getSQLDeleting(role));
	}

	public static void deleteAll(){
		communication.SocketCommunicator.getInstance().sendQuery(getSQLDeleting());
	}

	public static java.util.List<Role> getListInstances(){
		return getListInstances("");
	}

	public static java.util.List<Role> getListInstances(String whereCondition){
		String query = "select * from role";
		if (!whereCondition.trim().equals("")){
			if (!whereCondition.trim().toLowerCase().equals("") && !whereCondition.trim().toLowerCase().startsWith("where")){
				query += " where ";
			}
			query += " "+whereCondition;
		}
		return getListInstancesBySQLQuery(query);
	}

@SuppressWarnings("unchecked")
	public static java.util.List<Role> getListInstancesBySQLQuery(String sqlQuery){
		java.util.List<Role> list = new java.util.ArrayList<Role>();
		Object response = communication.SocketCommunicator.getInstance().sendQuery(sqlQuery);
		if (response != null){
			if (response instanceof java.util.List<?>){
				list = (java.util.List<Role>)response;
			}
			else{
				list.add((Role)response);
			}
		}
		return list;
	}

	public static String getSQLUpdateForDesignation(Role role){
		return models.daos.server.DAORole.getSQLUpdateForDesignation(role);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForDesignationByPreparedStatement(Role role){
		return models.daos.server.DAORole.getSQLUpdateForDesignationByPreparedStatement(role);
	}

	public static void updateDesignation(Role role){
		String sqlUpdate = getSQLUpdateForDesignation(role);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForIdPhoto(Role role){
		return models.daos.server.DAORole.getSQLUpdateForIdPhoto(role);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIdPhotoByPreparedStatement(Role role){
		return models.daos.server.DAORole.getSQLUpdateForIdPhotoByPreparedStatement(role);
	}

	public static void updateIdPhoto(Role role){
		String sqlUpdate = getSQLUpdateForIdPhoto(role);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForParametresOrganisme(Role role){
		return models.daos.server.DAORole.getSQLUpdateForParametresOrganisme(role);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForParametresOrganismeByPreparedStatement(Role role){
		return models.daos.server.DAORole.getSQLUpdateForParametresOrganismeByPreparedStatement(role);
	}

	public static void updateParametresOrganisme(Role role){
		String sqlUpdate = getSQLUpdateForParametresOrganisme(role);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForGestionRole(Role role){
		return models.daos.server.DAORole.getSQLUpdateForGestionRole(role);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForGestionRoleByPreparedStatement(Role role){
		return models.daos.server.DAORole.getSQLUpdateForGestionRoleByPreparedStatement(role);
	}

	public static void updateGestionRole(Role role){
		String sqlUpdate = getSQLUpdateForGestionRole(role);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForGestionUtilisateur(Role role){
		return models.daos.server.DAORole.getSQLUpdateForGestionUtilisateur(role);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForGestionUtilisateurByPreparedStatement(Role role){
		return models.daos.server.DAORole.getSQLUpdateForGestionUtilisateurByPreparedStatement(role);
	}

	public static void updateGestionUtilisateur(Role role){
		String sqlUpdate = getSQLUpdateForGestionUtilisateur(role);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getConcatIDs(){
		return getConcatIDs("");
	}

	public static String getConcatIDs(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return (String)communication.SocketCommunicator.getInstance().sendQuery("select group_concat(id) from `role` "+whereCondition);
	}

	public static int getCountInTable(){
		return getCountInTable("");
	}

	public static int getCountInTable(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return (Integer)communication.SocketCommunicator.getInstance().sendQuery("select count(id) from `role` "+whereCondition);
	}

	public static int getCountInTableFromServer(){
		return getCountInTableFromServer("");
	}

	public static int getCountInTableFromServer(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return ((Integer)communication.SocketCommunicator.getInstance().sendQuery("select count(id) from `role` "+whereCondition)).intValue();
	}

	public static String getSQLTuple(Role role){
		return models.daos.server.DAORole.getSQLTuple(role);
	}

	public static String getSQLStructure(boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAORole.getSQLStructure(foreignKeyCheck, addDropTable);
	}

	public static String getSQLStructure(boolean addDropTable){
		return models.daos.server.DAORole.getSQLStructure(addDropTable);
	}

	public static String getSQLStructure(){
		return getSQLStructure(true);
	}

	public static String getSQLContent(boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAORole.getSQLContent(foreignKeyCheck, addDropTable);
	}

	public static String getSQLContent(java.util.List<Role> list, boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAORole.getSQLContent(list, foreignKeyCheck, addDropTable);
	}

	public static String showSQLStructure(){
		return models.daos.server.DAORole.showSQLStructure();
	}

	public static String getTableName(){
		return models.daos.server.DAORole.getTableName();
	}
	
	public static java.util.List<models.beans.Utilisateur> getListOfUtilisateursRole(models.beans.Role role){
		java.util.List<models.beans.Utilisateur> listOfUtilisateursRole = models.daos.client.DAOUtilisateur.getListInstances("where idRole='"+role.getId().intValue()+"'");
		return listOfUtilisateursRole;
	}
}