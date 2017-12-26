package models.daos.server;

import java.sql.Connection;
import java.sql.ResultSet;

import utils.SGBDConfig;

import models.beans.Photo;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This classe maps the Table <i><b>photo</b></i> in the Database. 
 *	<br/>It contains attributs which represents the columns of the table,
 *	<br/>and Methods for geting one row by id, updating, inserting and deleting rows.
 */

public abstract class DAOPhoto {
	public static Photo getPhoto(int id) {
		return getPhoto(id, null);
	}

	public static Photo getPhoto(int id, Connection connection) {
		Photo photo = new Photo();
		if (id == 0)
			return photo;
		String query = "select * from `photo` where `id`="+id+" limit 1";
		boolean toCloseConnection = false;
		if (connection == null){
			connection = SGBDConfig.getInstance().getConnexionWithoutSaving();
			toCloseConnection = true;
		}
		ResultSet data = SGBDConfig.getInstance().sendQueryForResults(query, connection);
		try{
			if (data.next()){
				photo.setId(data.getInt("id"));
				photo.setData(data.getBytes("data"));
			}
			
			if (toCloseConnection){
				connection.close();
			}
		}
		catch (Exception e){
			utils.StringUtils.printDebug(e);
		}

		return photo;
	}

	public static String getSQLDeleting(Photo photo) {
		String query = "delete from `photo` where `id` = "+photo.getId();
		return query;
	}

	public static String getSQLDeleting() {
		String query = "delete from `photo` where 1 ";
		return query;
	}

	public static String getSQLWriting(Photo photo) {
		String query;
		if (photo.getId().intValue() == 0){
			query = "insert into `photo` (`id`, `data`)";
			query += " values ('"+photo.getId()+"', "+utils.FilesAndLaunchUtils.getDataContentAsHex(photo.getData())+")";
		}
		else{
			query ="update `photo` SET ";
			query += "id='"+photo.getId()+"',";
			query += "`data`="+utils.FilesAndLaunchUtils.getDataContentAsHex(photo.getData())+"";
			query += " where `id`="+photo.getId();
		}
		return query;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLWritingByPreparedStatement(Photo photo) {
		String query;
		if (photo.getId().intValue() == 0){
			query = "insert into ";
		}
		else{
			query = "update ";
		}

		query += " `photo` set ";
		query += "`id` = ?, ";
		query += "`data` = ? ";

		if (photo.getId().intValue() > 0){
			query += " where `id` = "+photo.getId().intValue();
		}

		try{
			Object[] params = {photo.getId(), photo.getData()};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static int write(Photo photo) {
		String query = getSQLWriting(photo);
//		SGBDConfig.getInstance().sendQuery("alter table `photo` auto_increment =1");
		SGBDConfig.getInstance().sendQuery(query);
		if(photo.getId() == 0) {
			ResultSet data = SGBDConfig.getInstance().sendQueryForResults("select id from `photo` order by id desc limit 1");
			try{
				if (data.next()){
					photo.setId(data.getInt("id"));
				}
			} catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
		}
		return photo.getId();
	}

	public static int writeByPreparedStatement(Photo photo) {
		utils.SGBDConfig.InsertUpdateSQLQueries writingQuerie = getSQLWritingByPreparedStatement(photo);
//		SGBDConfig.getInstance().sendQuery("alter table `photo` auto_increment =1");
		utils.SGBDConfig.getInstance().sendQueriesByPreparedStatement(writingQuerie);
		
		if(photo.getId().intValue() == 0) {
			ResultSet data = SGBDConfig.getInstance().sendQueryForResults("select id from `photo` order by id desc limit 1");
			try{
				if (data.next()){
					photo.setId(data.getInt("id"));
				}
			} catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
		}
		return photo.getId();
	}

	public static void delete(Photo photo){
		SGBDConfig.getInstance().sendQuery(getSQLDeleting(photo));
	}

	public static void deleteAll(){
		SGBDConfig.getInstance().sendQuery(getSQLDeleting());
	}

	public static java.util.List<Photo> getListInstances(){
		return getListInstances("");
	}

	public static java.util.List<Photo> getListInstances(String whereCondition){
		String query = "select * from photo";
		if (!whereCondition.trim().equals("")){
			if (!whereCondition.trim().equals("") && !whereCondition.trim().toLowerCase().startsWith("where")){
				query += " where ";
			}
			query += " "+whereCondition;
		}
		return getListInstancesBySQLQuery(query);
	}

	public static java.util.List<Photo> getListInstancesBySQLQuery(String sqlQuery){
		java.util.List<Photo> list = new java.util.ArrayList<Photo>();
		
		Connection connection = SGBDConfig.getInstance().getConnexionWithoutSaving();
		ResultSet data = SGBDConfig.getInstance().sendQueryForResults(sqlQuery, connection);
		try{
			while (data.next()){
				Photo item = new Photo();
				item.setId(data.getInt("id"));
				item.setData(data.getBytes("data"));
				
				
				list.add(item);
			}
			connection.close();
		}
		catch(Exception e){
			utils.StringUtils.printDebug(e);
		}
		return list;
	}

	public static void writeData(Photo photo){
		if (photo.getId().intValue() == 0)
			return;
		try{
			java.sql.Connection connexion = SGBDConfig.getInstance().getConnexion();
			String query = "UPDATE photo SET data = ? where id="+photo.getId();
			java.sql.PreparedStatement stmt = connexion.prepareStatement(query);
			java.io.ByteArrayInputStream is = new java.io.ByteArrayInputStream(photo.getData());
			stmt.setBinaryStream(1, is, photo.getData().length);
			stmt.execute();
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
	}

	public static String getSQLUpdateForData(Photo photo){
		String sqlQuery ="update `photo` SET ";
		sqlQuery += "`data`="+utils.FilesAndLaunchUtils.getDataContentAsHex(photo.getData())+" ";
		sqlQuery += "where id = "+photo.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForDataByPreparedStatement(Photo photo){
		String query = "update `photo` set ";
		query += "`data` = ? ";
		query += "where id = "+photo.getId().intValue();
		
		try{
			Object[] params = {photo.getData()};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getConcatIDs(){
		return getConcatIDs("");
	}

	public static String getConcatIDs(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		try {
			ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("select group_concat(id) from `photo` "+whereCondition);
			if (data.next())
				return data.getString(1);
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		return "0";
	}

	public static int getCountInTable(){
		return getCountInTable("");
	}

	public static int getCountInTable(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		try {
			ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("select count(id) from `photo` "+whereCondition);
			if (data.next())
				return data.getInt(1);
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		return 0;
	}

	public static String getSQLTuple(Photo photo){
		String sqlTuple = "";
		sqlTuple += "INSERT INTO `photo` ( `id`, `data`) VALUES";
		sqlTuple += "( '"+photo.getId()+"', "+utils.FilesAndLaunchUtils.getDataContentAsHex(photo.getData())+");\n";

		return sqlTuple;
	}

	public static String getSQLStructure(boolean foreignKeyCheck, boolean addDropTable){
		String sqlStruct = "";
		if (foreignKeyCheck){
			sqlStruct += "SET FOREIGN_KEY_CHECKS=0;\n";
		}
		sqlStruct += "SET SQL_MODE=\"NO_AUTO_VALUE_ON_ZERO\";\n\n";
		
		if (addDropTable){
			sqlStruct += "DROP TABLE IF EXISTS `photo`;\n";
		}

		sqlStruct += "CREATE TABLE IF NOT EXISTS `photo` (\n";
		sqlStruct += "  `id` int(11) NOT NULL AUTO_INCREMENT,\n";
		sqlStruct += "  `data` mediumblob DEFAULT NULL,\n";
		sqlStruct += "  PRIMARY KEY (`id`)\n";
		sqlStruct += ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='Photos' AUTO_INCREMENT=1 ;\n";
		

		if (foreignKeyCheck){
			sqlStruct += "SET FOREIGN_KEY_CHECKS=1;\n";
		}
		return sqlStruct;
	}

	public static String getSQLStructure(boolean addDropTable){
		String sqlStruct = getSQLStructure(false, addDropTable);
		return sqlStruct;
	}

	public static String getSQLStructure(){
		return getSQLStructure(true);
	}

	public static String getSQLContent(boolean foreignKeyCheck, boolean addDropTable){
		return getSQLContent(getListInstances(), foreignKeyCheck, addDropTable);
	}

	public static String getSQLContent(java.util.List<Photo> list, boolean foreignKeyCheck, boolean addDropTable){
		String sqlContent = getSQLStructure(foreignKeyCheck, addDropTable);
		
		sqlContent += "\n\n";
		for (Photo item : list){
			sqlContent += getSQLTuple(item);
		}
		
		if (foreignKeyCheck){
			sqlContent = "SET FOREIGN_KEY_CHECKS=0;\n" + sqlContent + "SET FOREIGN_KEY_CHECKS=1;";
		}
		
		return sqlContent;
	}

	public static String showSQLStructure(){
		try{
			java.sql.ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("show create table `photo`");
			if (data.next()){
				return data.getString(2);
			}
		}
		catch (Exception e){
		}
		
		return "";
	}

	public static String getTableName(){
		return "photo";
	}
	
	public static java.util.List<models.beans.ParametresApplication> getListOfParametresApplicationsPhoto(models.beans.Photo photo){
		java.util.List<models.beans.ParametresApplication> listOfParametresApplicationsPhoto = models.daos.server.DAOParametresApplication.getListInstances("where idPhoto='"+photo.getId().intValue()+"'");
		return listOfParametresApplicationsPhoto;
	}
	
	public static java.util.List<models.beans.ParametresOrganisme> getListOfParametresOrganismesPhoto(models.beans.Photo photo){
		java.util.List<models.beans.ParametresOrganisme> listOfParametresOrganismesPhoto = models.daos.server.DAOParametresOrganisme.getListInstances("where idPhoto='"+photo.getId().intValue()+"'");
		return listOfParametresOrganismesPhoto;
	}
	
	public static java.util.List<models.beans.Role> getListOfRolesPhoto(models.beans.Photo photo){
		java.util.List<models.beans.Role> listOfRolesPhoto = models.daos.server.DAORole.getListInstances("where idPhoto='"+photo.getId().intValue()+"'");
		return listOfRolesPhoto;
	}
	
	public static java.util.List<models.beans.Utilisateur> getListOfUtilisateursPhoto(models.beans.Photo photo){
		java.util.List<models.beans.Utilisateur> listOfUtilisateursPhoto = models.daos.server.DAOUtilisateur.getListInstances("where idPhoto='"+photo.getId().intValue()+"'");
		return listOfUtilisateursPhoto;
	}
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}