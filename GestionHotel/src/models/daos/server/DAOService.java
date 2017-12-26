package models.daos.server;

import java.sql.Connection;
import java.sql.ResultSet;

import utils.SGBDConfig;

import models.beans.Service;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This classe maps the Table <i><b>service</b></i> in the Database. 
 *	<br/>It contains attributs which represents the columns of the table,
 *	<br/>and Methods for geting one row by id, updating, inserting and deleting rows.
 */

public abstract class DAOService {
	public static Service getService(int id) {
		return getService(id, null);
	}

	public static Service getService(int id, Connection connection) {
		Service service = new Service();
		if (id == 0)
			return service;
		String query = "select * from `service` where `id`="+id+" limit 1";
		boolean toCloseConnection = false;
		if (connection == null){
			connection = SGBDConfig.getInstance().getConnexionWithoutSaving();
			toCloseConnection = true;
		}
		ResultSet data = SGBDConfig.getInstance().sendQueryForResults(query, connection);
		try{
			if (data.next()){
				service.setId(data.getInt("id"));
				service.setDesignation(data.getString("designation"));
				service.setPrix(data.getDouble("prix"));
			}
			
			if (toCloseConnection){
				connection.close();
			}
		}
		catch (Exception e){
			utils.StringUtils.printDebug(e);
		}

		return service;
	}

	public static String getSQLDeleting(Service service) {
		String query = "delete from `service` where `id` = "+service.getId();
		return query;
	}

	public static String getSQLDeleting() {
		String query = "delete from `service` where 1 ";
		return query;
	}

	public static String getSQLWriting(Service service) {
		String query;
		if (service.getId().intValue() == 0){
			query = "insert into `service` (`id`, `designation`, `prix`)";
			query += " values ('"+service.getId()+"', '"+utils.StringUtils.addSQLSlashes(service.getDesignation())+"', '"+service.getPrix()+"')";
		}
		else{
			query ="update `service` SET ";
			query += "id='"+service.getId()+"',";
			query += "`designation`='"+utils.StringUtils.addSQLSlashes(service.getDesignation())+"',";
			query += "prix='"+service.getPrix()+"'";
			query += " where `id`="+service.getId();
		}
		return query;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLWritingByPreparedStatement(Service service) {
		String query;
		if (service.getId().intValue() == 0){
			query = "insert into ";
		}
		else{
			query = "update ";
		}

		query += " `service` set ";
		query += "`id` = ?, ";
		query += "`designation` = ?, ";
		query += "`prix` = ? ";

		if (service.getId().intValue() > 0){
			query += " where `id` = "+service.getId().intValue();
		}

		try{
			Object[] params = {service.getId(), service.getDesignation().getBytes("UTF-8"), service.getPrix()};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static int write(Service service) {
		String query = getSQLWriting(service);
//		SGBDConfig.getInstance().sendQuery("alter table `service` auto_increment =1");
		SGBDConfig.getInstance().sendQuery(query);
		if(service.getId() == 0) {
			ResultSet data = SGBDConfig.getInstance().sendQueryForResults("select id from `service` order by id desc limit 1");
			try{
				if (data.next()){
					service.setId(data.getInt("id"));
				}
			} catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
		}
		return service.getId();
	}

	public static int writeByPreparedStatement(Service service) {
		utils.SGBDConfig.InsertUpdateSQLQueries writingQuerie = getSQLWritingByPreparedStatement(service);
//		SGBDConfig.getInstance().sendQuery("alter table `service` auto_increment =1");
		utils.SGBDConfig.getInstance().sendQueriesByPreparedStatement(writingQuerie);
		
		if(service.getId().intValue() == 0) {
			ResultSet data = SGBDConfig.getInstance().sendQueryForResults("select id from `service` order by id desc limit 1");
			try{
				if (data.next()){
					service.setId(data.getInt("id"));
				}
			} catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
		}
		return service.getId();
	}

	public static void delete(Service service){
		SGBDConfig.getInstance().sendQuery(getSQLDeleting(service));
	}

	public static void deleteAll(){
		SGBDConfig.getInstance().sendQuery(getSQLDeleting());
	}

	public static java.util.List<Service> getListInstances(){
		return getListInstances("");
	}

	public static java.util.List<Service> getListInstances(String whereCondition){
		String query = "select * from service";
		if (!whereCondition.trim().equals("")){
			if (!whereCondition.trim().equals("") && !whereCondition.trim().toLowerCase().startsWith("where")){
				query += " where ";
			}
			query += " "+whereCondition;
		}
		return getListInstancesBySQLQuery(query);
	}

	public static java.util.List<Service> getListInstancesBySQLQuery(String sqlQuery){
		java.util.List<Service> list = new java.util.ArrayList<Service>();
		
		Connection connection = SGBDConfig.getInstance().getConnexionWithoutSaving();
		ResultSet data = SGBDConfig.getInstance().sendQueryForResults(sqlQuery, connection);
		try{
			while (data.next()){
				Service item = new Service();
				item.setId(data.getInt("id"));
				item.setDesignation(data.getString("designation"));
				item.setPrix(data.getDouble("prix"));
				
				
				list.add(item);
			}
			connection.close();
		}
		catch(Exception e){
			utils.StringUtils.printDebug(e);
		}
		return list;
	}

	public static String getSQLUpdateForDesignation(Service service){
		String sqlQuery ="update `service` SET ";
		sqlQuery += "`designation`='"+utils.StringUtils.addSQLSlashes(service.getDesignation())+"'";
		sqlQuery += "where id = "+service.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForDesignationByPreparedStatement(Service service){
		String query = "update `service` set ";
		query += "`designation` = ? ";
		query += "where id = "+service.getId().intValue();
		
		try{
			Object[] params = {service.getDesignation().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForPrix(Service service){
		String sqlQuery ="update `service` SET ";
		sqlQuery += "`prix`='"+service.getPrix()+"'";
		sqlQuery += "where id = "+service.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForPrixByPreparedStatement(Service service){
		String query = "update `service` set ";
		query += "`prix` = ? ";
		query += "where id = "+service.getId().intValue();
		
		try{
			Object[] params = {service.getPrix()};
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
			ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("select group_concat(id) from `service` "+whereCondition);
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
			ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("select count(id) from `service` "+whereCondition);
			if (data.next())
				return data.getInt(1);
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		return 0;
	}

	public static String getSQLTuple(Service service){
		String sqlTuple = "";
		sqlTuple += "INSERT INTO `service` ( `id`, `designation`, `prix`) VALUES";
		sqlTuple += "( '"+service.getId()+"', '"+utils.StringUtils.addSQLSlashes(service.getDesignation())+"', '"+service.getPrix()+"');\n";

		return sqlTuple;
	}

	public static String getSQLStructure(boolean foreignKeyCheck, boolean addDropTable){
		String sqlStruct = "";
		if (foreignKeyCheck){
			sqlStruct += "SET FOREIGN_KEY_CHECKS=0;\n";
		}
		sqlStruct += "SET SQL_MODE=\"NO_AUTO_VALUE_ON_ZERO\";\n\n";
		
		if (addDropTable){
			sqlStruct += "DROP TABLE IF EXISTS `service`;\n";
		}

		sqlStruct += "CREATE TABLE IF NOT EXISTS `service` (\n";
		sqlStruct += "  `id` int(11) NOT NULL AUTO_INCREMENT,\n";
		sqlStruct += "  `designation` varchar(50) NOT NULL,\n";
		sqlStruct += "  `prix` double NOT NULL,\n";
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

	public static String getSQLContent(java.util.List<Service> list, boolean foreignKeyCheck, boolean addDropTable){
		String sqlContent = getSQLStructure(foreignKeyCheck, addDropTable);
		
		sqlContent += "\n\n";
		for (Service item : list){
			sqlContent += getSQLTuple(item);
		}
		
		if (foreignKeyCheck){
			sqlContent = "SET FOREIGN_KEY_CHECKS=0;\n" + sqlContent + "SET FOREIGN_KEY_CHECKS=1;";
		}
		
		return sqlContent;
	}

	public static String showSQLStructure(){
		try{
			java.sql.ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("show create table `service`");
			if (data.next()){
				return data.getString(2);
			}
		}
		catch (Exception e){
		}
		
		return "";
	}

	public static String getTableName(){
		return "service";
	}
	
	public static java.util.List<models.beans.ClientConsommeService> getListOfClientConsommeServicesService(models.beans.Service service){
		java.util.List<models.beans.ClientConsommeService> listOfClientConsommeServicesService = models.daos.server.DAOClientConsommeService.getListInstances("where idService='"+service.getId().intValue()+"'");
		return listOfClientConsommeServicesService;
	}
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}