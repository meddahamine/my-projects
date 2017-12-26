package models.daos.server;

import java.sql.Connection;
import java.sql.ResultSet;

import utils.SGBDConfig;

import models.beans.ParametresApplication;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This classe maps the Table <i><b>parametresApplication</b></i> in the Database. 
 *	<br/>It contains attributs which represents the columns of the table,
 *	<br/>and Methods for geting one row by id, updating, inserting and deleting rows.
 */

public abstract class DAOParametresApplication {
	public static ParametresApplication getParametresApplication(int id) {
		return getParametresApplication(id, null);
	}

	public static ParametresApplication getParametresApplication(int id, Connection connection) {
		ParametresApplication parametresApplication = new ParametresApplication();
		if (id == 0)
			return parametresApplication;
		String query = "select * from `parametresApplication` where `id`="+id+" limit 1";
		boolean toCloseConnection = false;
		if (connection == null){
			connection = SGBDConfig.getInstance().getConnexionWithoutSaving();
			toCloseConnection = true;
		}
		ResultSet data = SGBDConfig.getInstance().sendQueryForResults(query, connection);
		try{
			if (data.next()){
				parametresApplication.setId(data.getInt("id"));
				parametresApplication.setDesignation(data.getString("designation"));
				parametresApplication.setDescription(data.getString("description"));
				parametresApplication.setIdPhoto(data.getInt("idPhoto"));
			}
			
			if (parametresApplication.getIdPhoto() != null && parametresApplication.getIdPhoto().intValue() > 0){
				parametresApplication.setPhoto(DAOPhoto.getPhoto(parametresApplication.getIdPhoto(), connection));
			}
			if (toCloseConnection){
				connection.close();
			}
		}
		catch (Exception e){
			utils.StringUtils.printDebug(e);
		}

		return parametresApplication;
	}

	public static String getSQLDeleting(ParametresApplication parametresApplication) {
		String query = "delete from `parametresApplication` where `id` = "+parametresApplication.getId();
		return query;
	}

	public static String getSQLDeleting() {
		String query = "delete from `parametresApplication` where 1 ";
		return query;
	}

	public static String getSQLWriting(ParametresApplication parametresApplication) {
		String query;
		if (parametresApplication.getId().intValue() == 0){
			query = "insert into `parametresApplication` (`id`, `designation`, `description`, `idPhoto`)";
			query += " values ('"+parametresApplication.getId()+"', '"+utils.StringUtils.addSQLSlashes(parametresApplication.getDesignation())+"', '"+utils.StringUtils.addSQLSlashes(parametresApplication.getDescription())+"', "+ ((parametresApplication.getIdPhoto() > 0) ? "'"+parametresApplication.getIdPhoto()+"'" : "NULL" )+")";
		}
		else{
			query ="update `parametresApplication` SET ";
			query += "id='"+parametresApplication.getId()+"',";
			query += "`designation`='"+utils.StringUtils.addSQLSlashes(parametresApplication.getDesignation())+"',";
			query += "`description`='"+utils.StringUtils.addSQLSlashes(parametresApplication.getDescription())+"',";
			query += "`idPhoto`="+((parametresApplication.getIdPhoto() > 0) ? "'"+parametresApplication.getIdPhoto()+"'" : "NULL")+"";
			query += " where `id`="+parametresApplication.getId();
		}
		return query;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLWritingByPreparedStatement(ParametresApplication parametresApplication) {
		String query;
		if (parametresApplication.getId().intValue() == 0){
			query = "insert into ";
		}
		else{
			query = "update ";
		}

		query += " `parametresApplication` set ";
		query += "`id` = ?, ";
		query += "`designation` = ?, ";
		query += "`description` = ?, ";
		query += "`idPhoto` = ? ";

		if (parametresApplication.getId().intValue() > 0){
			query += " where `id` = "+parametresApplication.getId().intValue();
		}

		try{
			Object[] params = {parametresApplication.getId(), parametresApplication.getDesignation().getBytes("UTF-8"), parametresApplication.getDescription().getBytes("UTF-8"), parametresApplication.getIdPhoto() == 0 ? null : parametresApplication.getIdPhoto()};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static int write(ParametresApplication parametresApplication) {
		String query = getSQLWriting(parametresApplication);
//		SGBDConfig.getInstance().sendQuery("alter table `parametresApplication` auto_increment =1");
		SGBDConfig.getInstance().sendQuery(query);
		if(parametresApplication.getId() == 0) {
			ResultSet data = SGBDConfig.getInstance().sendQueryForResults("select id from `parametresApplication` order by id desc limit 1");
			try{
				if (data.next()){
					parametresApplication.setId(data.getInt("id"));
				}
			} catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
		}
		return parametresApplication.getId();
	}

	public static int writeByPreparedStatement(ParametresApplication parametresApplication) {
		utils.SGBDConfig.InsertUpdateSQLQueries writingQuerie = getSQLWritingByPreparedStatement(parametresApplication);
//		SGBDConfig.getInstance().sendQuery("alter table `parametresApplication` auto_increment =1");
		utils.SGBDConfig.getInstance().sendQueriesByPreparedStatement(writingQuerie);
		
		if(parametresApplication.getId().intValue() == 0) {
			ResultSet data = SGBDConfig.getInstance().sendQueryForResults("select id from `parametresApplication` order by id desc limit 1");
			try{
				if (data.next()){
					parametresApplication.setId(data.getInt("id"));
				}
			} catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
		}
		return parametresApplication.getId();
	}

	public static void delete(ParametresApplication parametresApplication){
		SGBDConfig.getInstance().sendQuery(getSQLDeleting(parametresApplication));
	}

	public static void deleteAll(){
		SGBDConfig.getInstance().sendQuery(getSQLDeleting());
	}

	public static java.util.List<ParametresApplication> getListInstances(){
		return getListInstances("");
	}

	public static java.util.List<ParametresApplication> getListInstances(String whereCondition){
		String query = "select * from parametresApplication";
		if (!whereCondition.trim().equals("")){
			if (!whereCondition.trim().equals("") && !whereCondition.trim().toLowerCase().startsWith("where")){
				query += " where ";
			}
			query += " "+whereCondition;
		}
		return getListInstancesBySQLQuery(query);
	}

	public static java.util.List<ParametresApplication> getListInstancesBySQLQuery(String sqlQuery){
		java.util.List<ParametresApplication> list = new java.util.ArrayList<ParametresApplication>();
		
		Connection connection = SGBDConfig.getInstance().getConnexionWithoutSaving();
		ResultSet data = SGBDConfig.getInstance().sendQueryForResults(sqlQuery, connection);
		try{
			while (data.next()){
				ParametresApplication item = new ParametresApplication();
				item.setId(data.getInt("id"));
				item.setDesignation(data.getString("designation"));
				item.setDescription(data.getString("description"));
				item.setIdPhoto(data.getInt("idPhoto"));
				
				if (item.getIdPhoto() != null && item.getIdPhoto().intValue() > 0){
					item.setPhoto( DAOPhoto.getPhoto(item.getIdPhoto().intValue(), connection) );
				}
				
				list.add(item);
			}
			connection.close();
		}
		catch(Exception e){
			utils.StringUtils.printDebug(e);
		}
		return list;
	}

	public static String getSQLUpdateForDesignation(ParametresApplication parametresApplication){
		String sqlQuery ="update `parametresApplication` SET ";
		sqlQuery += "`designation`='"+utils.StringUtils.addSQLSlashes(parametresApplication.getDesignation())+"'";
		sqlQuery += "where id = "+parametresApplication.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForDesignationByPreparedStatement(ParametresApplication parametresApplication){
		String query = "update `parametresApplication` set ";
		query += "`designation` = ? ";
		query += "where id = "+parametresApplication.getId().intValue();
		
		try{
			Object[] params = {parametresApplication.getDesignation().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForDescription(ParametresApplication parametresApplication){
		String sqlQuery ="update `parametresApplication` SET ";
		sqlQuery += "`description`='"+utils.StringUtils.addSQLSlashes(parametresApplication.getDescription())+"'";
		sqlQuery += "where id = "+parametresApplication.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForDescriptionByPreparedStatement(ParametresApplication parametresApplication){
		String query = "update `parametresApplication` set ";
		query += "`description` = ? ";
		query += "where id = "+parametresApplication.getId().intValue();
		
		try{
			Object[] params = {parametresApplication.getDescription().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForIdPhoto(ParametresApplication parametresApplication){
		String sqlQuery ="update `parametresApplication` SET ";
		sqlQuery += "`idPhoto`="+(parametresApplication.getIdPhoto() > 0 ? "'"+parametresApplication.getIdPhoto()+"' " : "NULL ");
		sqlQuery += "where id = "+parametresApplication.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIdPhotoByPreparedStatement(ParametresApplication parametresApplication){
		String query = "update `parametresApplication` set ";
		query += "`idPhoto` = ? ";
		query += "where id = "+parametresApplication.getId().intValue();
		
		try{
			Object[] params = {parametresApplication.getIdPhoto() > 0 ? parametresApplication.getIdPhoto() : null};
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
			ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("select group_concat(id) from `parametresApplication` "+whereCondition);
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
			ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("select count(id) from `parametresApplication` "+whereCondition);
			if (data.next())
				return data.getInt(1);
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		return 0;
	}

	public static String getSQLTuple(ParametresApplication parametresApplication){
		String sqlTuple = "";
		sqlTuple += "INSERT INTO `parametresApplication` ( `id`, `designation`, `description`, `idPhoto`) VALUES";
		sqlTuple += "( '"+parametresApplication.getId()+"', '"+utils.StringUtils.addSQLSlashes(parametresApplication.getDesignation())+"', '"+utils.StringUtils.addSQLSlashes(parametresApplication.getDescription())+"', "+((parametresApplication.getIdPhoto() == 0) ? "NULL" : "'"+parametresApplication.getIdPhoto()+"'")+");\n";

		return sqlTuple;
	}

	public static String getSQLStructure(boolean foreignKeyCheck, boolean addDropTable){
		String sqlStruct = "";
		if (foreignKeyCheck){
			sqlStruct += "SET FOREIGN_KEY_CHECKS=0;\n";
		}
		sqlStruct += "SET SQL_MODE=\"NO_AUTO_VALUE_ON_ZERO\";\n\n";
		
		if (addDropTable){
			sqlStruct += "DROP TABLE IF EXISTS `parametresApplication`;\n";
		}

		sqlStruct += "CREATE TABLE IF NOT EXISTS `parametresApplication` (\n";
		sqlStruct += "  `id` int(11) NOT NULL AUTO_INCREMENT,\n";
		sqlStruct += "  `designation` varchar(200) NOT NULL COMMENT 'Désignation de l''Application',\n";
		sqlStruct += "  `description` text NOT NULL COMMENT 'Description de l''Application',\n";
		sqlStruct += "  `idPhoto` int(11) DEFAULT NULL COMMENT 'Icone de l''Application',\n";
		sqlStruct += "  PRIMARY KEY (`id`),\n";
		sqlStruct += "  KEY `idPhoto` (`idPhoto`)\n";
		sqlStruct += ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='Paramètres de l''Application' AUTO_INCREMENT=1 ;\n";

		sqlStruct += "ALTER TABLE `parametresApplication`\n";
		sqlStruct += "  ADD CONSTRAINT `parametresApplication_ibfk_1` FOREIGN KEY (`idPhoto`) REFERENCES `photo` (`id`);\n";
		

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

	public static String getSQLContent(java.util.List<ParametresApplication> list, boolean foreignKeyCheck, boolean addDropTable){
		String sqlContent = getSQLStructure(foreignKeyCheck, addDropTable);
		
		sqlContent += "\n\n";
		for (ParametresApplication item : list){
			sqlContent += getSQLTuple(item);
		}
		
		if (foreignKeyCheck){
			sqlContent = "SET FOREIGN_KEY_CHECKS=0;\n" + sqlContent + "SET FOREIGN_KEY_CHECKS=1;";
		}
		
		return sqlContent;
	}

	public static String showSQLStructure(){
		try{
			java.sql.ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("show create table `parametresApplication`");
			if (data.next()){
				return data.getString(2);
			}
		}
		catch (Exception e){
		}
		
		return "";
	}

	public static String getTableName(){
		return "parametresApplication";
	}
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}