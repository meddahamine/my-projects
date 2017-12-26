package models.daos.server;

import java.sql.Connection;
import java.sql.ResultSet;

import utils.SGBDConfig;

import models.beans.ClientConsommeService;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This classe maps the Table <i><b>clientConsommeService</b></i> in the Database. 
 *	<br/>It contains attributs which represents the columns of the table,
 *	<br/>and Methods for geting one row by id, updating, inserting and deleting rows.
 */

public abstract class DAOClientConsommeService {
	public static ClientConsommeService getClientConsommeService(int id) {
		return getClientConsommeService(id, null);
	}

	public static ClientConsommeService getClientConsommeService(int id, Connection connection) {
		ClientConsommeService clientConsommeService = new ClientConsommeService();
		if (id == 0)
			return clientConsommeService;
		String query = "select * from `clientConsommeService` where `id`="+id+" limit 1";
		boolean toCloseConnection = false;
		if (connection == null){
			connection = SGBDConfig.getInstance().getConnexionWithoutSaving();
			toCloseConnection = true;
		}
		ResultSet data = SGBDConfig.getInstance().sendQueryForResults(query, connection);
		try{
			if (data.next()){
				clientConsommeService.setId(data.getInt("id"));
				clientConsommeService.setIdClient(data.getInt("idClient"));
				clientConsommeService.setIdService(data.getInt("idService"));
				clientConsommeService.setPrixService(data.getDouble("prixService"));
				clientConsommeService.setDateConsommationService(data.getDate("dateConsommationService"));
			}
			
			if (clientConsommeService.getIdClient() != null && clientConsommeService.getIdClient().intValue() > 0){
				clientConsommeService.setClient(DAOClient.getClient(clientConsommeService.getIdClient(), connection));
			}
			if (clientConsommeService.getIdService() != null && clientConsommeService.getIdService().intValue() > 0){
				clientConsommeService.setService(DAOService.getService(clientConsommeService.getIdService(), connection));
			}
			if (toCloseConnection){
				connection.close();
			}
		}
		catch (Exception e){
			utils.StringUtils.printDebug(e);
		}

		return clientConsommeService;
	}

	public static String getSQLDeleting(ClientConsommeService clientConsommeService) {
		String query = "delete from `clientConsommeService` where `id` = "+clientConsommeService.getId();
		return query;
	}

	public static String getSQLDeleting() {
		String query = "delete from `clientConsommeService` where 1 ";
		return query;
	}

	public static String getSQLWriting(ClientConsommeService clientConsommeService) {
		String query;
		if (clientConsommeService.getId().intValue() == 0){
			query = "insert into `clientConsommeService` (`id`, `idClient`, `idService`, `prixService`, `dateConsommationService`)";
			query += " values ('"+clientConsommeService.getId()+"', "+ ((clientConsommeService.getIdClient() > 0) ? "'"+clientConsommeService.getIdClient()+"'" : "NULL" )+", "+ ((clientConsommeService.getIdService() > 0) ? "'"+clientConsommeService.getIdService()+"'" : "NULL" )+", '"+clientConsommeService.getPrixService()+"', '"+clientConsommeService.getDateConsommationService()+"')";
		}
		else{
			query ="update `clientConsommeService` SET ";
			query += "id='"+clientConsommeService.getId()+"',";
			query += "`idClient`="+((clientConsommeService.getIdClient() > 0) ? "'"+clientConsommeService.getIdClient()+"'" : "NULL")+",";
			query += "`idService`="+((clientConsommeService.getIdService() > 0) ? "'"+clientConsommeService.getIdService()+"'" : "NULL")+",";
			query += "prixService='"+clientConsommeService.getPrixService()+"',";
			query += "dateConsommationService='"+clientConsommeService.getDateConsommationService()+"'";
			query += " where `id`="+clientConsommeService.getId();
		}
		return query;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLWritingByPreparedStatement(ClientConsommeService clientConsommeService) {
		String query;
		if (clientConsommeService.getId().intValue() == 0){
			query = "insert into ";
		}
		else{
			query = "update ";
		}

		query += " `clientConsommeService` set ";
		query += "`id` = ?, ";
		query += "`idClient` = ?, ";
		query += "`idService` = ?, ";
		query += "`prixService` = ?, ";
		query += "`dateConsommationService` = ? ";

		if (clientConsommeService.getId().intValue() > 0){
			query += " where `id` = "+clientConsommeService.getId().intValue();
		}

		try{
			Object[] params = {clientConsommeService.getId(), clientConsommeService.getIdClient() == 0 ? null : clientConsommeService.getIdClient(), clientConsommeService.getIdService() == 0 ? null : clientConsommeService.getIdService(), clientConsommeService.getPrixService(), clientConsommeService.getDateConsommationService().toString()};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static int write(ClientConsommeService clientConsommeService) {
		String query = getSQLWriting(clientConsommeService);
//		SGBDConfig.getInstance().sendQuery("alter table `clientConsommeService` auto_increment =1");
		SGBDConfig.getInstance().sendQuery(query);
		if(clientConsommeService.getId() == 0) {
			ResultSet data = SGBDConfig.getInstance().sendQueryForResults("select id from `clientConsommeService` order by id desc limit 1");
			try{
				if (data.next()){
					clientConsommeService.setId(data.getInt("id"));
				}
			} catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
		}
		return clientConsommeService.getId();
	}

	public static int writeByPreparedStatement(ClientConsommeService clientConsommeService) {
		utils.SGBDConfig.InsertUpdateSQLQueries writingQuerie = getSQLWritingByPreparedStatement(clientConsommeService);
//		SGBDConfig.getInstance().sendQuery("alter table `clientConsommeService` auto_increment =1");
		utils.SGBDConfig.getInstance().sendQueriesByPreparedStatement(writingQuerie);
		
		if(clientConsommeService.getId().intValue() == 0) {
			ResultSet data = SGBDConfig.getInstance().sendQueryForResults("select id from `clientConsommeService` order by id desc limit 1");
			try{
				if (data.next()){
					clientConsommeService.setId(data.getInt("id"));
				}
			} catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
		}
		return clientConsommeService.getId();
	}

	public static void delete(ClientConsommeService clientConsommeService){
		SGBDConfig.getInstance().sendQuery(getSQLDeleting(clientConsommeService));
	}

	public static void deleteAll(){
		SGBDConfig.getInstance().sendQuery(getSQLDeleting());
	}

	public static java.util.List<ClientConsommeService> getListInstances(){
		return getListInstances("");
	}

	public static java.util.List<ClientConsommeService> getListInstances(String whereCondition){
		String query = "select * from clientConsommeService";
		if (!whereCondition.trim().equals("")){
			if (!whereCondition.trim().equals("") && !whereCondition.trim().toLowerCase().startsWith("where")){
				query += " where ";
			}
			query += " "+whereCondition;
		}
		return getListInstancesBySQLQuery(query);
	}

	public static java.util.List<ClientConsommeService> getListInstancesBySQLQuery(String sqlQuery){
		java.util.List<ClientConsommeService> list = new java.util.ArrayList<ClientConsommeService>();
		
		Connection connection = SGBDConfig.getInstance().getConnexionWithoutSaving();
		ResultSet data = SGBDConfig.getInstance().sendQueryForResults(sqlQuery, connection);
		try{
			while (data.next()){
				ClientConsommeService item = new ClientConsommeService();
				item.setId(data.getInt("id"));
				item.setIdClient(data.getInt("idClient"));
				item.setIdService(data.getInt("idService"));
				item.setPrixService(data.getDouble("prixService"));
				item.setDateConsommationService(data.getDate("dateConsommationService"));
				
				if (item.getIdClient() != null && item.getIdClient().intValue() > 0){
					item.setClient( DAOClient.getClient(item.getIdClient().intValue(), connection) );
				}
				if (item.getIdService() != null && item.getIdService().intValue() > 0){
					item.setService( DAOService.getService(item.getIdService().intValue(), connection) );
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

	public static String getSQLUpdateForIdClient(ClientConsommeService clientConsommeService){
		String sqlQuery ="update `clientConsommeService` SET ";
		sqlQuery += "`idClient`="+(clientConsommeService.getIdClient() > 0 ? "'"+clientConsommeService.getIdClient()+"' " : "NULL ");
		sqlQuery += "where id = "+clientConsommeService.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIdClientByPreparedStatement(ClientConsommeService clientConsommeService){
		String query = "update `clientConsommeService` set ";
		query += "`idClient` = ? ";
		query += "where id = "+clientConsommeService.getId().intValue();
		
		try{
			Object[] params = {clientConsommeService.getIdClient() > 0 ? clientConsommeService.getIdClient() : null};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForIdService(ClientConsommeService clientConsommeService){
		String sqlQuery ="update `clientConsommeService` SET ";
		sqlQuery += "`idService`="+(clientConsommeService.getIdService() > 0 ? "'"+clientConsommeService.getIdService()+"' " : "NULL ");
		sqlQuery += "where id = "+clientConsommeService.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIdServiceByPreparedStatement(ClientConsommeService clientConsommeService){
		String query = "update `clientConsommeService` set ";
		query += "`idService` = ? ";
		query += "where id = "+clientConsommeService.getId().intValue();
		
		try{
			Object[] params = {clientConsommeService.getIdService() > 0 ? clientConsommeService.getIdService() : null};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForPrixService(ClientConsommeService clientConsommeService){
		String sqlQuery ="update `clientConsommeService` SET ";
		sqlQuery += "`prixService`='"+clientConsommeService.getPrixService()+"'";
		sqlQuery += "where id = "+clientConsommeService.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForPrixServiceByPreparedStatement(ClientConsommeService clientConsommeService){
		String query = "update `clientConsommeService` set ";
		query += "`prixService` = ? ";
		query += "where id = "+clientConsommeService.getId().intValue();
		
		try{
			Object[] params = {clientConsommeService.getPrixService()};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForDateConsommationService(ClientConsommeService clientConsommeService){
		String sqlQuery ="update `clientConsommeService` SET ";
		sqlQuery += "`dateConsommationService`='"+clientConsommeService.getDateConsommationService()+"'";
		sqlQuery += "where id = "+clientConsommeService.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForDateConsommationServiceByPreparedStatement(ClientConsommeService clientConsommeService){
		String query = "update `clientConsommeService` set ";
		query += "`dateConsommationService` = ? ";
		query += "where id = "+clientConsommeService.getId().intValue();
		
		try{
			Object[] params = {clientConsommeService.getDateConsommationService().toString()};
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
			ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("select group_concat(id) from `clientConsommeService` "+whereCondition);
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
			ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("select count(id) from `clientConsommeService` "+whereCondition);
			if (data.next())
				return data.getInt(1);
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		return 0;
	}

	public static String getSQLTuple(ClientConsommeService clientConsommeService){
		String sqlTuple = "";
		sqlTuple += "INSERT INTO `clientConsommeService` ( `id`, `idClient`, `idService`, `prixService`, `dateConsommationService`) VALUES";
		sqlTuple += "( '"+clientConsommeService.getId()+"', "+((clientConsommeService.getIdClient() == 0) ? "NULL" : "'"+clientConsommeService.getIdClient()+"'")+", "+((clientConsommeService.getIdService() == 0) ? "NULL" : "'"+clientConsommeService.getIdService()+"'")+", '"+clientConsommeService.getPrixService()+"', '"+clientConsommeService.getDateConsommationService()+"');\n";

		return sqlTuple;
	}

	public static String getSQLStructure(boolean foreignKeyCheck, boolean addDropTable){
		String sqlStruct = "";
		if (foreignKeyCheck){
			sqlStruct += "SET FOREIGN_KEY_CHECKS=0;\n";
		}
		sqlStruct += "SET SQL_MODE=\"NO_AUTO_VALUE_ON_ZERO\";\n\n";
		
		if (addDropTable){
			sqlStruct += "DROP TABLE IF EXISTS `clientConsommeService`;\n";
		}

		sqlStruct += "CREATE TABLE IF NOT EXISTS `clientConsommeService` (\n";
		sqlStruct += "  `id` int(11) NOT NULL AUTO_INCREMENT,\n";
		sqlStruct += "  `idClient` int(11) NOT NULL,\n";
		sqlStruct += "  `idService` int(11) NOT NULL,\n";
		sqlStruct += "  `prixService` double NOT NULL,\n";
		sqlStruct += "  `dateConsommationService` date NOT NULL,\n";
		sqlStruct += "  PRIMARY KEY (`id`),\n";
		sqlStruct += "  KEY `idClient` (`idClient`),\n";
		sqlStruct += "  KEY `idService` (`idService`)\n";
		sqlStruct += ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='' AUTO_INCREMENT=1 ;\n";

		sqlStruct += "ALTER TABLE `clientConsommeService`\n";
		sqlStruct += "  ADD CONSTRAINT `clientConsommeService_ibfk_1` FOREIGN KEY (`idClient`) REFERENCES `client` (`id`),\n";
		sqlStruct += "  ADD CONSTRAINT `clientConsommeService_ibfk_2` FOREIGN KEY (`idService`) REFERENCES `service` (`id`);\n";
		

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

	public static String getSQLContent(java.util.List<ClientConsommeService> list, boolean foreignKeyCheck, boolean addDropTable){
		String sqlContent = getSQLStructure(foreignKeyCheck, addDropTable);
		
		sqlContent += "\n\n";
		for (ClientConsommeService item : list){
			sqlContent += getSQLTuple(item);
		}
		
		if (foreignKeyCheck){
			sqlContent = "SET FOREIGN_KEY_CHECKS=0;\n" + sqlContent + "SET FOREIGN_KEY_CHECKS=1;";
		}
		
		return sqlContent;
	}

	public static String showSQLStructure(){
		try{
			java.sql.ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("show create table `clientConsommeService`");
			if (data.next()){
				return data.getString(2);
			}
		}
		catch (Exception e){
		}
		
		return "";
	}

	public static String getTableName(){
		return "clientConsommeService";
	}
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}