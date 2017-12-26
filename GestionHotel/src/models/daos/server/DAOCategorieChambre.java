package models.daos.server;

import java.sql.Connection;
import java.sql.ResultSet;

import utils.SGBDConfig;

import models.beans.CategorieChambre;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This classe maps the Table <i><b>categorieChambre</b></i> in the Database. 
 *	<br/>It contains attributs which represents the columns of the table,
 *	<br/>and Methods for geting one row by id, updating, inserting and deleting rows.
 */

public abstract class DAOCategorieChambre {
	public static CategorieChambre getCategorieChambre(int id) {
		return getCategorieChambre(id, null);
	}

	public static CategorieChambre getCategorieChambre(int id, Connection connection) {
		CategorieChambre categorieChambre = new CategorieChambre();
		if (id == 0)
			return categorieChambre;
		String query = "select * from `categorieChambre` where `id`="+id+" limit 1";
		boolean toCloseConnection = false;
		if (connection == null){
			connection = SGBDConfig.getInstance().getConnexionWithoutSaving();
			toCloseConnection = true;
		}
		ResultSet data = SGBDConfig.getInstance().sendQueryForResults(query, connection);
		try{
			if (data.next()){
				categorieChambre.setId(data.getInt("id"));
				categorieChambre.setLibelle(data.getString("libelle"));
			}
			
			if (toCloseConnection){
				connection.close();
			}
		}
		catch (Exception e){
			utils.StringUtils.printDebug(e);
		}

		return categorieChambre;
	}

	public static String getSQLDeleting(CategorieChambre categorieChambre) {
		String query = "delete from `categorieChambre` where `id` = "+categorieChambre.getId();
		return query;
	}

	public static String getSQLDeleting() {
		String query = "delete from `categorieChambre` where 1 ";
		return query;
	}

	public static String getSQLWriting(CategorieChambre categorieChambre) {
		String query;
		if (categorieChambre.getId().intValue() == 0){
			query = "insert into `categorieChambre` (`id`, `libelle`)";
			query += " values ('"+categorieChambre.getId()+"', '"+utils.StringUtils.addSQLSlashes(categorieChambre.getLibelle())+"')";
		}
		else{
			query ="update `categorieChambre` SET ";
			query += "id='"+categorieChambre.getId()+"',";
			query += "`libelle`='"+utils.StringUtils.addSQLSlashes(categorieChambre.getLibelle())+"'";
			query += " where `id`="+categorieChambre.getId();
		}
		return query;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLWritingByPreparedStatement(CategorieChambre categorieChambre) {
		String query;
		if (categorieChambre.getId().intValue() == 0){
			query = "insert into ";
		}
		else{
			query = "update ";
		}

		query += " `categorieChambre` set ";
		query += "`id` = ?, ";
		query += "`libelle` = ? ";

		if (categorieChambre.getId().intValue() > 0){
			query += " where `id` = "+categorieChambre.getId().intValue();
		}

		try{
			Object[] params = {categorieChambre.getId(), categorieChambre.getLibelle().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static int write(CategorieChambre categorieChambre) {
		String query = getSQLWriting(categorieChambre);
//		SGBDConfig.getInstance().sendQuery("alter table `categorieChambre` auto_increment =1");
		SGBDConfig.getInstance().sendQuery(query);
		if(categorieChambre.getId() == 0) {
			ResultSet data = SGBDConfig.getInstance().sendQueryForResults("select id from `categorieChambre` order by id desc limit 1");
			try{
				if (data.next()){
					categorieChambre.setId(data.getInt("id"));
				}
			} catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
		}
		return categorieChambre.getId();
	}

	public static int writeByPreparedStatement(CategorieChambre categorieChambre) {
		utils.SGBDConfig.InsertUpdateSQLQueries writingQuerie = getSQLWritingByPreparedStatement(categorieChambre);
//		SGBDConfig.getInstance().sendQuery("alter table `categorieChambre` auto_increment =1");
		utils.SGBDConfig.getInstance().sendQueriesByPreparedStatement(writingQuerie);
		
		if(categorieChambre.getId().intValue() == 0) {
			ResultSet data = SGBDConfig.getInstance().sendQueryForResults("select id from `categorieChambre` order by id desc limit 1");
			try{
				if (data.next()){
					categorieChambre.setId(data.getInt("id"));
				}
			} catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
		}
		return categorieChambre.getId();
	}

	public static void delete(CategorieChambre categorieChambre){
		SGBDConfig.getInstance().sendQuery(getSQLDeleting(categorieChambre));
	}

	public static void deleteAll(){
		SGBDConfig.getInstance().sendQuery(getSQLDeleting());
	}

	public static java.util.List<CategorieChambre> getListInstances(){
		return getListInstances("");
	}

	public static java.util.List<CategorieChambre> getListInstances(String whereCondition){
		String query = "select * from categorieChambre";
		if (!whereCondition.trim().equals("")){
			if (!whereCondition.trim().equals("") && !whereCondition.trim().toLowerCase().startsWith("where")){
				query += " where ";
			}
			query += " "+whereCondition;
		}
		return getListInstancesBySQLQuery(query);
	}

	public static java.util.List<CategorieChambre> getListInstancesBySQLQuery(String sqlQuery){
		java.util.List<CategorieChambre> list = new java.util.ArrayList<CategorieChambre>();
		
		Connection connection = SGBDConfig.getInstance().getConnexionWithoutSaving();
		ResultSet data = SGBDConfig.getInstance().sendQueryForResults(sqlQuery, connection);
		try{
			while (data.next()){
				CategorieChambre item = new CategorieChambre();
				item.setId(data.getInt("id"));
				item.setLibelle(data.getString("libelle"));
				
				
				list.add(item);
			}
			connection.close();
		}
		catch(Exception e){
			utils.StringUtils.printDebug(e);
		}
		return list;
	}

	public static String getSQLUpdateForLibelle(CategorieChambre categorieChambre){
		String sqlQuery ="update `categorieChambre` SET ";
		sqlQuery += "`libelle`='"+utils.StringUtils.addSQLSlashes(categorieChambre.getLibelle())+"'";
		sqlQuery += "where id = "+categorieChambre.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForLibelleByPreparedStatement(CategorieChambre categorieChambre){
		String query = "update `categorieChambre` set ";
		query += "`libelle` = ? ";
		query += "where id = "+categorieChambre.getId().intValue();
		
		try{
			Object[] params = {categorieChambre.getLibelle().getBytes("UTF-8")};
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
			ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("select group_concat(id) from `categorieChambre` "+whereCondition);
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
			ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("select count(id) from `categorieChambre` "+whereCondition);
			if (data.next())
				return data.getInt(1);
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		return 0;
	}

	public static String getSQLTuple(CategorieChambre categorieChambre){
		String sqlTuple = "";
		sqlTuple += "INSERT INTO `categorieChambre` ( `id`, `libelle`) VALUES";
		sqlTuple += "( '"+categorieChambre.getId()+"', '"+utils.StringUtils.addSQLSlashes(categorieChambre.getLibelle())+"');\n";

		return sqlTuple;
	}

	public static String getSQLStructure(boolean foreignKeyCheck, boolean addDropTable){
		String sqlStruct = "";
		if (foreignKeyCheck){
			sqlStruct += "SET FOREIGN_KEY_CHECKS=0;\n";
		}
		sqlStruct += "SET SQL_MODE=\"NO_AUTO_VALUE_ON_ZERO\";\n\n";
		
		if (addDropTable){
			sqlStruct += "DROP TABLE IF EXISTS `categorieChambre`;\n";
		}

		sqlStruct += "CREATE TABLE IF NOT EXISTS `categorieChambre` (\n";
		sqlStruct += "  `id` int(11) NOT NULL,\n";
		sqlStruct += "  `libelle` varchar(55) NOT NULL,\n";
		sqlStruct += "  PRIMARY KEY (`id`)\n";
		sqlStruct += ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='' AUTO_INCREMENT=1 ;\n";
		

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

	public static String getSQLContent(java.util.List<CategorieChambre> list, boolean foreignKeyCheck, boolean addDropTable){
		String sqlContent = getSQLStructure(foreignKeyCheck, addDropTable);
		
		sqlContent += "\n\n";
		for (CategorieChambre item : list){
			sqlContent += getSQLTuple(item);
		}
		
		if (foreignKeyCheck){
			sqlContent = "SET FOREIGN_KEY_CHECKS=0;\n" + sqlContent + "SET FOREIGN_KEY_CHECKS=1;";
		}
		
		return sqlContent;
	}

	public static String showSQLStructure(){
		try{
			java.sql.ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("show create table `categorieChambre`");
			if (data.next()){
				return data.getString(2);
			}
		}
		catch (Exception e){
		}
		
		return "";
	}

	public static String getTableName(){
		return "categorieChambre";
	}
	
	public static java.util.List<models.beans.Chambre> getListOfChambresCategorieChambre(models.beans.CategorieChambre categorieChambre){
		java.util.List<models.beans.Chambre> listOfChambresCategorieChambre = models.daos.server.DAOChambre.getListInstances("where idCategorieChambre='"+categorieChambre.getId().intValue()+"'");
		return listOfChambresCategorieChambre;
	}
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}