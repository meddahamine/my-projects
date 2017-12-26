package models.daos.client;

import models.beans.Photo;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>Remote Access for DAO
 */

public abstract class DAOPhoto {
	public static Photo getPhoto(int id) {
		if (id == 0)
			return new Photo();
		String query = "select * from `photo` where `id`="+id+" limit 1";
		return (Photo)communication.SocketCommunicator.getInstance().sendQuery(query);
	}

	public static String getSQLDeleting(Photo photo) {
		return models.daos.server.DAOPhoto.getSQLDeleting(photo);
	}

	public static String getSQLDeleting() {
		return models.daos.server.DAOPhoto.getSQLDeleting();
	}

	public static String getSQLWriting(Photo photo) {
		return models.daos.server.DAOPhoto.getSQLWriting(photo);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLWritingByPreparedStatement(Photo photo) {
		return models.daos.server.DAOPhoto.getSQLWritingByPreparedStatement(photo);
	}

	public static int write(Photo photo) {
		int id = (Integer)communication.SocketCommunicator.getInstance().sendQuery(photo);
		photo.setId(id);
		return id;
	}

	public static void writeByPreparedStatement(Photo photo) {
		utils.SGBDConfig.InsertUpdateSQLQueries writingQuery = getSQLWritingByPreparedStatement(photo);
		communication.SocketCommunicator.getInstance().sendQuery(writingQuery);
	}

	public static void delete(Photo photo){
		communication.SocketCommunicator.getInstance().sendQuery(getSQLDeleting(photo));
	}

	public static void deleteAll(){
		communication.SocketCommunicator.getInstance().sendQuery(getSQLDeleting());
	}

	public static java.util.List<Photo> getListInstances(){
		return getListInstances("");
	}

	public static java.util.List<Photo> getListInstances(String whereCondition){
		String query = "select * from photo";
		if (!whereCondition.trim().equals("")){
			if (!whereCondition.trim().toLowerCase().equals("") && !whereCondition.trim().toLowerCase().startsWith("where")){
				query += " where ";
			}
			query += " "+whereCondition;
		}
		return getListInstancesBySQLQuery(query);
	}

@SuppressWarnings("unchecked")
	public static java.util.List<Photo> getListInstancesBySQLQuery(String sqlQuery){
		java.util.List<Photo> list = new java.util.ArrayList<Photo>();
		Object response = communication.SocketCommunicator.getInstance().sendQuery(sqlQuery);
		if (response != null){
			if (response instanceof java.util.List<?>){
				list = (java.util.List<Photo>)response;
			}
			else{
				list.add((Photo)response);
			}
		}
		return list;
	}

	public static String getSQLUpdateForData(Photo photo){
		return models.daos.server.DAOPhoto.getSQLUpdateForData(photo);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForDataByPreparedStatement(Photo photo){
		return models.daos.server.DAOPhoto.getSQLUpdateForDataByPreparedStatement(photo);
	}

	public static void updateData(Photo photo){
		String sqlUpdate = getSQLUpdateForData(photo);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getConcatIDs(){
		return getConcatIDs("");
	}

	public static String getConcatIDs(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return (String)communication.SocketCommunicator.getInstance().sendQuery("select group_concat(id) from `photo` "+whereCondition);
	}

	public static int getCountInTable(){
		return getCountInTable("");
	}

	public static int getCountInTable(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return (Integer)communication.SocketCommunicator.getInstance().sendQuery("select count(id) from `photo` "+whereCondition);
	}

	public static int getCountInTableFromServer(){
		return getCountInTableFromServer("");
	}

	public static int getCountInTableFromServer(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return ((Integer)communication.SocketCommunicator.getInstance().sendQuery("select count(id) from `photo` "+whereCondition)).intValue();
	}

	public static String getSQLTuple(Photo photo){
		return models.daos.server.DAOPhoto.getSQLTuple(photo);
	}

	public static String getSQLStructure(boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOPhoto.getSQLStructure(foreignKeyCheck, addDropTable);
	}

	public static String getSQLStructure(boolean addDropTable){
		return models.daos.server.DAOPhoto.getSQLStructure(addDropTable);
	}

	public static String getSQLStructure(){
		return getSQLStructure(true);
	}

	public static String getSQLContent(boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOPhoto.getSQLContent(foreignKeyCheck, addDropTable);
	}

	public static String getSQLContent(java.util.List<Photo> list, boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOPhoto.getSQLContent(list, foreignKeyCheck, addDropTable);
	}

	public static String showSQLStructure(){
		return models.daos.server.DAOPhoto.showSQLStructure();
	}

	public static String getTableName(){
		return models.daos.server.DAOPhoto.getTableName();
	}
	
	public static java.util.List<models.beans.ParametresApplication> getListOfParametresApplicationsPhoto(models.beans.Photo photo){
		java.util.List<models.beans.ParametresApplication> listOfParametresApplicationsPhoto = models.daos.client.DAOParametresApplication.getListInstances("where idPhoto='"+photo.getId().intValue()+"'");
		return listOfParametresApplicationsPhoto;
	}
	
	public static java.util.List<models.beans.ParametresOrganisme> getListOfParametresOrganismesPhoto(models.beans.Photo photo){
		java.util.List<models.beans.ParametresOrganisme> listOfParametresOrganismesPhoto = models.daos.client.DAOParametresOrganisme.getListInstances("where idPhoto='"+photo.getId().intValue()+"'");
		return listOfParametresOrganismesPhoto;
	}
	
	public static java.util.List<models.beans.Role> getListOfRolesPhoto(models.beans.Photo photo){
		java.util.List<models.beans.Role> listOfRolesPhoto = models.daos.client.DAORole.getListInstances("where idPhoto='"+photo.getId().intValue()+"'");
		return listOfRolesPhoto;
	}
	
	public static java.util.List<models.beans.Utilisateur> getListOfUtilisateursPhoto(models.beans.Photo photo){
		java.util.List<models.beans.Utilisateur> listOfUtilisateursPhoto = models.daos.client.DAOUtilisateur.getListInstances("where idPhoto='"+photo.getId().intValue()+"'");
		return listOfUtilisateursPhoto;
	}
}