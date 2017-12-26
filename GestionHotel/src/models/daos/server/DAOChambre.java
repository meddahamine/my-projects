package models.daos.server;

import java.sql.Connection;
import java.sql.ResultSet;

import utils.SGBDConfig;

import models.beans.Chambre;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This classe maps the Table <i><b>chambre</b></i> in the Database. 
 *	<br/>It contains attributs which represents the columns of the table,
 *	<br/>and Methods for geting one row by id, updating, inserting and deleting rows.
 */

public abstract class DAOChambre {
	public static Chambre getChambre(int id) {
		return getChambre(id, null);
	}

	public static Chambre getChambre(int id, Connection connection) {
		Chambre chambre = new Chambre();
		if (id == 0)
			return chambre;
		String query = "select * from `chambre` where `id`="+id+" limit 1";
		boolean toCloseConnection = false;
		if (connection == null){
			connection = SGBDConfig.getInstance().getConnexionWithoutSaving();
			toCloseConnection = true;
		}
		ResultSet data = SGBDConfig.getInstance().sendQueryForResults(query, connection);
		try{
			if (data.next()){
				chambre.setId(data.getInt("id"));
				chambre.setNumChamre(data.getString("numChamre"));
				chambre.setNumEtage(data.getString("numEtage"));
				chambre.setIdCategorieChambre(data.getInt("idCategorieChambre"));
				chambre.setPrix(data.getDouble("prix"));
			}
			
			if (chambre.getIdCategorieChambre() != null && chambre.getIdCategorieChambre().intValue() > 0){
				chambre.setCategorieChambre(DAOCategorieChambre.getCategorieChambre(chambre.getIdCategorieChambre(), connection));
			}
			if (toCloseConnection){
				connection.close();
			}
		}
		catch (Exception e){
			utils.StringUtils.printDebug(e);
		}

		return chambre;
	}

	public static String getSQLDeleting(Chambre chambre) {
		String query = "delete from `chambre` where `id` = "+chambre.getId();
		return query;
	}

	public static String getSQLDeleting() {
		String query = "delete from `chambre` where 1 ";
		return query;
	}

	public static String getSQLWriting(Chambre chambre) {
		String query;
		if (chambre.getId().intValue() == 0){
			query = "insert into `chambre` (`id`, `numChamre`, `numEtage`, `idCategorieChambre`, `prix`)";
			query += " values ('"+chambre.getId()+"', '"+utils.StringUtils.addSQLSlashes(chambre.getNumChamre())+"', '"+utils.StringUtils.addSQLSlashes(chambre.getNumEtage())+"', "+ ((chambre.getIdCategorieChambre() > 0) ? "'"+chambre.getIdCategorieChambre()+"'" : "NULL" )+", '"+chambre.getPrix()+"')";
		}
		else{
			query ="update `chambre` SET ";
			query += "id='"+chambre.getId()+"',";
			query += "`numChamre`='"+utils.StringUtils.addSQLSlashes(chambre.getNumChamre())+"',";
			query += "`numEtage`='"+utils.StringUtils.addSQLSlashes(chambre.getNumEtage())+"',";
			query += "`idCategorieChambre`="+((chambre.getIdCategorieChambre() > 0) ? "'"+chambre.getIdCategorieChambre()+"'" : "NULL")+",";
			query += "prix='"+chambre.getPrix()+"'";
			query += " where `id`="+chambre.getId();
		}
		return query;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLWritingByPreparedStatement(Chambre chambre) {
		String query;
		if (chambre.getId().intValue() == 0){
			query = "insert into ";
		}
		else{
			query = "update ";
		}

		query += " `chambre` set ";
		query += "`id` = ?, ";
		query += "`numChamre` = ?, ";
		query += "`numEtage` = ?, ";
		query += "`idCategorieChambre` = ?, ";
		query += "`prix` = ? ";

		if (chambre.getId().intValue() > 0){
			query += " where `id` = "+chambre.getId().intValue();
		}

		try{
			Object[] params = {chambre.getId(), chambre.getNumChamre().getBytes("UTF-8"), chambre.getNumEtage().getBytes("UTF-8"), chambre.getIdCategorieChambre() == 0 ? null : chambre.getIdCategorieChambre(), chambre.getPrix()};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static int write(Chambre chambre) {
		String query = getSQLWriting(chambre);
//		SGBDConfig.getInstance().sendQuery("alter table `chambre` auto_increment =1");
		SGBDConfig.getInstance().sendQuery(query);
		if(chambre.getId() == 0) {
			ResultSet data = SGBDConfig.getInstance().sendQueryForResults("select id from `chambre` order by id desc limit 1");
			try{
				if (data.next()){
					chambre.setId(data.getInt("id"));
				}
			} catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
		}
		return chambre.getId();
	}

	public static int writeByPreparedStatement(Chambre chambre) {
		utils.SGBDConfig.InsertUpdateSQLQueries writingQuerie = getSQLWritingByPreparedStatement(chambre);
//		SGBDConfig.getInstance().sendQuery("alter table `chambre` auto_increment =1");
		utils.SGBDConfig.getInstance().sendQueriesByPreparedStatement(writingQuerie);
		
		if(chambre.getId().intValue() == 0) {
			ResultSet data = SGBDConfig.getInstance().sendQueryForResults("select id from `chambre` order by id desc limit 1");
			try{
				if (data.next()){
					chambre.setId(data.getInt("id"));
				}
			} catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
		}
		return chambre.getId();
	}

	public static void delete(Chambre chambre){
		SGBDConfig.getInstance().sendQuery(getSQLDeleting(chambre));
	}

	public static void deleteAll(){
		SGBDConfig.getInstance().sendQuery(getSQLDeleting());
	}

	public static java.util.List<Chambre> getListInstances(){
		return getListInstances("");
	}

	public static java.util.List<Chambre> getListInstances(String whereCondition){
		String query = "select * from chambre";
		if (!whereCondition.trim().equals("")){
			if (!whereCondition.trim().equals("") && !whereCondition.trim().toLowerCase().startsWith("where")){
				query += " where ";
			}
			query += " "+whereCondition;
		}
		return getListInstancesBySQLQuery(query);
	}

	public static java.util.List<Chambre> getListInstancesBySQLQuery(String sqlQuery){
		java.util.List<Chambre> list = new java.util.ArrayList<Chambre>();
		
		Connection connection = SGBDConfig.getInstance().getConnexionWithoutSaving();
		ResultSet data = SGBDConfig.getInstance().sendQueryForResults(sqlQuery, connection);
		try{
			while (data.next()){
				Chambre item = new Chambre();
				item.setId(data.getInt("id"));
				item.setNumChamre(data.getString("numChamre"));
				item.setNumEtage(data.getString("numEtage"));
				item.setIdCategorieChambre(data.getInt("idCategorieChambre"));
				item.setPrix(data.getDouble("prix"));
				
				if (item.getIdCategorieChambre() != null && item.getIdCategorieChambre().intValue() > 0){
					item.setCategorieChambre( DAOCategorieChambre.getCategorieChambre(item.getIdCategorieChambre().intValue(), connection) );
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

	public static String getSQLUpdateForNumChamre(Chambre chambre){
		String sqlQuery ="update `chambre` SET ";
		sqlQuery += "`numChamre`='"+utils.StringUtils.addSQLSlashes(chambre.getNumChamre())+"'";
		sqlQuery += "where id = "+chambre.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForNumChamreByPreparedStatement(Chambre chambre){
		String query = "update `chambre` set ";
		query += "`numChamre` = ? ";
		query += "where id = "+chambre.getId().intValue();
		
		try{
			Object[] params = {chambre.getNumChamre().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForNumEtage(Chambre chambre){
		String sqlQuery ="update `chambre` SET ";
		sqlQuery += "`numEtage`='"+utils.StringUtils.addSQLSlashes(chambre.getNumEtage())+"'";
		sqlQuery += "where id = "+chambre.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForNumEtageByPreparedStatement(Chambre chambre){
		String query = "update `chambre` set ";
		query += "`numEtage` = ? ";
		query += "where id = "+chambre.getId().intValue();
		
		try{
			Object[] params = {chambre.getNumEtage().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForIdCategorieChambre(Chambre chambre){
		String sqlQuery ="update `chambre` SET ";
		sqlQuery += "`idCategorieChambre`="+(chambre.getIdCategorieChambre() > 0 ? "'"+chambre.getIdCategorieChambre()+"' " : "NULL ");
		sqlQuery += "where id = "+chambre.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIdCategorieChambreByPreparedStatement(Chambre chambre){
		String query = "update `chambre` set ";
		query += "`idCategorieChambre` = ? ";
		query += "where id = "+chambre.getId().intValue();
		
		try{
			Object[] params = {chambre.getIdCategorieChambre() > 0 ? chambre.getIdCategorieChambre() : null};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForPrix(Chambre chambre){
		String sqlQuery ="update `chambre` SET ";
		sqlQuery += "`prix`='"+chambre.getPrix()+"'";
		sqlQuery += "where id = "+chambre.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForPrixByPreparedStatement(Chambre chambre){
		String query = "update `chambre` set ";
		query += "`prix` = ? ";
		query += "where id = "+chambre.getId().intValue();
		
		try{
			Object[] params = {chambre.getPrix()};
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
			ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("select group_concat(id) from `chambre` "+whereCondition);
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
			ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("select count(id) from `chambre` "+whereCondition);
			if (data.next())
				return data.getInt(1);
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		return 0;
	}

	public static String getSQLTuple(Chambre chambre){
		String sqlTuple = "";
		sqlTuple += "INSERT INTO `chambre` ( `id`, `numChamre`, `numEtage`, `idCategorieChambre`, `prix`) VALUES";
		sqlTuple += "( '"+chambre.getId()+"', '"+utils.StringUtils.addSQLSlashes(chambre.getNumChamre())+"', '"+utils.StringUtils.addSQLSlashes(chambre.getNumEtage())+"', "+((chambre.getIdCategorieChambre() == 0) ? "NULL" : "'"+chambre.getIdCategorieChambre()+"'")+", '"+chambre.getPrix()+"');\n";

		return sqlTuple;
	}

	public static String getSQLStructure(boolean foreignKeyCheck, boolean addDropTable){
		String sqlStruct = "";
		if (foreignKeyCheck){
			sqlStruct += "SET FOREIGN_KEY_CHECKS=0;\n";
		}
		sqlStruct += "SET SQL_MODE=\"NO_AUTO_VALUE_ON_ZERO\";\n\n";
		
		if (addDropTable){
			sqlStruct += "DROP TABLE IF EXISTS `chambre`;\n";
		}

		sqlStruct += "CREATE TABLE IF NOT EXISTS `chambre` (\n";
		sqlStruct += "  `id` int(11) NOT NULL AUTO_INCREMENT,\n";
		sqlStruct += "  `numChamre` varchar(10) NOT NULL,\n";
		sqlStruct += "  `numEtage` varchar(10) NOT NULL,\n";
		sqlStruct += "  `idCategorieChambre` int(11) NOT NULL,\n";
		sqlStruct += "  `prix` double NOT NULL,\n";
		sqlStruct += "  PRIMARY KEY (`id`),\n";
		sqlStruct += "  KEY `idCategorieChambre` (`idCategorieChambre`)\n";
		sqlStruct += ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='' AUTO_INCREMENT=1 ;\n";

		sqlStruct += "ALTER TABLE `chambre`\n";
		sqlStruct += "  ADD CONSTRAINT `chambre_ibfk_1` FOREIGN KEY (`idCategorieChambre`) REFERENCES `categorieChambre` (`id`);\n";
		

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

	public static String getSQLContent(java.util.List<Chambre> list, boolean foreignKeyCheck, boolean addDropTable){
		String sqlContent = getSQLStructure(foreignKeyCheck, addDropTable);
		
		sqlContent += "\n\n";
		for (Chambre item : list){
			sqlContent += getSQLTuple(item);
		}
		
		if (foreignKeyCheck){
			sqlContent = "SET FOREIGN_KEY_CHECKS=0;\n" + sqlContent + "SET FOREIGN_KEY_CHECKS=1;";
		}
		
		return sqlContent;
	}

	public static String showSQLStructure(){
		try{
			java.sql.ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("show create table `chambre`");
			if (data.next()){
				return data.getString(2);
			}
		}
		catch (Exception e){
		}
		
		return "";
	}

	public static String getTableName(){
		return "chambre";
	}
	
	public static java.util.List<models.beans.ClientReseveChambre> getListOfClientReseveChambresChambre(models.beans.Chambre chambre){
		java.util.List<models.beans.ClientReseveChambre> listOfClientReseveChambresChambre = models.daos.server.DAOClientReseveChambre.getListInstances("where idChambre='"+chambre.getId().intValue()+"'");
		return listOfClientReseveChambresChambre;
	}
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}