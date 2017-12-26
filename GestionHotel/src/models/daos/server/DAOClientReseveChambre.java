package models.daos.server;

import java.sql.Connection;
import java.sql.ResultSet;

import utils.SGBDConfig;

import models.beans.ClientReseveChambre;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This classe maps the Table <i><b>clientReseveChambre</b></i> in the Database. 
 *	<br/>It contains attributs which represents the columns of the table,
 *	<br/>and Methods for geting one row by id, updating, inserting and deleting rows.
 */

public abstract class DAOClientReseveChambre {
	public static ClientReseveChambre getClientReseveChambre(int id) {
		return getClientReseveChambre(id, null);
	}

	public static ClientReseveChambre getClientReseveChambre(int id, Connection connection) {
		ClientReseveChambre clientReseveChambre = new ClientReseveChambre();
		if (id == 0)
			return clientReseveChambre;
		String query = "select * from `clientReseveChambre` where `id`="+id+" limit 1";
		boolean toCloseConnection = false;
		if (connection == null){
			connection = SGBDConfig.getInstance().getConnexionWithoutSaving();
			toCloseConnection = true;
		}
		ResultSet data = SGBDConfig.getInstance().sendQueryForResults(query, connection);
		try{
			if (data.next()){
				clientReseveChambre.setId(data.getInt("id"));
				clientReseveChambre.setIdClient(data.getInt("idClient"));
				clientReseveChambre.setIdChambre(data.getInt("idChambre"));
				clientReseveChambre.setDateDebutReservation(data.getDate("dateDebutReservation"));
				clientReseveChambre.setDateFinReservation(data.getDate("dateFinReservation"));
			}
			
			if (clientReseveChambre.getIdClient() != null && clientReseveChambre.getIdClient().intValue() > 0){
				clientReseveChambre.setClient(DAOClient.getClient(clientReseveChambre.getIdClient(), connection));
			}
			if (clientReseveChambre.getIdChambre() != null && clientReseveChambre.getIdChambre().intValue() > 0){
				clientReseveChambre.setChambre(DAOChambre.getChambre(clientReseveChambre.getIdChambre(), connection));
			}
			if (toCloseConnection){
				connection.close();
			}
		}
		catch (Exception e){
			utils.StringUtils.printDebug(e);
		}

		return clientReseveChambre;
	}

	public static String getSQLDeleting(ClientReseveChambre clientReseveChambre) {
		String query = "delete from `clientReseveChambre` where `id` = "+clientReseveChambre.getId();
		return query;
	}

	public static String getSQLDeleting() {
		String query = "delete from `clientReseveChambre` where 1 ";
		return query;
	}

	public static String getSQLWriting(ClientReseveChambre clientReseveChambre) {
		String query;
		if (clientReseveChambre.getId().intValue() == 0){
			query = "insert into `clientReseveChambre` (`id`, `idClient`, `idChambre`, `dateDebutReservation`, `dateFinReservation`)";
			query += " values ('"+clientReseveChambre.getId()+"', "+ ((clientReseveChambre.getIdClient() > 0) ? "'"+clientReseveChambre.getIdClient()+"'" : "NULL" )+", "+ ((clientReseveChambre.getIdChambre() > 0) ? "'"+clientReseveChambre.getIdChambre()+"'" : "NULL" )+", '"+clientReseveChambre.getDateDebutReservation()+"', '"+clientReseveChambre.getDateFinReservation()+"')";
		}
		else{
			query ="update `clientReseveChambre` SET ";
			query += "id='"+clientReseveChambre.getId()+"',";
			query += "`idClient`="+((clientReseveChambre.getIdClient() > 0) ? "'"+clientReseveChambre.getIdClient()+"'" : "NULL")+",";
			query += "`idChambre`="+((clientReseveChambre.getIdChambre() > 0) ? "'"+clientReseveChambre.getIdChambre()+"'" : "NULL")+",";
			query += "dateDebutReservation='"+clientReseveChambre.getDateDebutReservation()+"',";
			query += "dateFinReservation='"+clientReseveChambre.getDateFinReservation()+"'";
			query += " where `id`="+clientReseveChambre.getId();
		}
		return query;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLWritingByPreparedStatement(ClientReseveChambre clientReseveChambre) {
		String query;
		if (clientReseveChambre.getId().intValue() == 0){
			query = "insert into ";
		}
		else{
			query = "update ";
		}

		query += " `clientReseveChambre` set ";
		query += "`id` = ?, ";
		query += "`idClient` = ?, ";
		query += "`idChambre` = ?, ";
		query += "`dateDebutReservation` = ?, ";
		query += "`dateFinReservation` = ? ";

		if (clientReseveChambre.getId().intValue() > 0){
			query += " where `id` = "+clientReseveChambre.getId().intValue();
		}

		try{
			Object[] params = {clientReseveChambre.getId(), clientReseveChambre.getIdClient() == 0 ? null : clientReseveChambre.getIdClient(), clientReseveChambre.getIdChambre() == 0 ? null : clientReseveChambre.getIdChambre(), clientReseveChambre.getDateDebutReservation().toString(), clientReseveChambre.getDateFinReservation().toString()};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static int write(ClientReseveChambre clientReseveChambre) {
		String query = getSQLWriting(clientReseveChambre);
//		SGBDConfig.getInstance().sendQuery("alter table `clientReseveChambre` auto_increment =1");
		SGBDConfig.getInstance().sendQuery(query);
		if(clientReseveChambre.getId() == 0) {
			ResultSet data = SGBDConfig.getInstance().sendQueryForResults("select id from `clientReseveChambre` order by id desc limit 1");
			try{
				if (data.next()){
					clientReseveChambre.setId(data.getInt("id"));
				}
			} catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
		}
		return clientReseveChambre.getId();
	}

	public static int writeByPreparedStatement(ClientReseveChambre clientReseveChambre) {
		utils.SGBDConfig.InsertUpdateSQLQueries writingQuerie = getSQLWritingByPreparedStatement(clientReseveChambre);
//		SGBDConfig.getInstance().sendQuery("alter table `clientReseveChambre` auto_increment =1");
		utils.SGBDConfig.getInstance().sendQueriesByPreparedStatement(writingQuerie);
		
		if(clientReseveChambre.getId().intValue() == 0) {
			ResultSet data = SGBDConfig.getInstance().sendQueryForResults("select id from `clientReseveChambre` order by id desc limit 1");
			try{
				if (data.next()){
					clientReseveChambre.setId(data.getInt("id"));
				}
			} catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
		}
		return clientReseveChambre.getId();
	}

	public static void delete(ClientReseveChambre clientReseveChambre){
		SGBDConfig.getInstance().sendQuery(getSQLDeleting(clientReseveChambre));
	}

	public static void deleteAll(){
		SGBDConfig.getInstance().sendQuery(getSQLDeleting());
	}

	public static java.util.List<ClientReseveChambre> getListInstances(){
		return getListInstances("");
	}

	public static java.util.List<ClientReseveChambre> getListInstances(String whereCondition){
		String query = "select * from clientReseveChambre";
		if (!whereCondition.trim().equals("")){
			if (!whereCondition.trim().equals("") && !whereCondition.trim().toLowerCase().startsWith("where")){
				query += " where ";
			}
			query += " "+whereCondition;
		}
		return getListInstancesBySQLQuery(query);
	}

	public static java.util.List<ClientReseveChambre> getListInstancesBySQLQuery(String sqlQuery){
		java.util.List<ClientReseveChambre> list = new java.util.ArrayList<ClientReseveChambre>();
		
		Connection connection = SGBDConfig.getInstance().getConnexionWithoutSaving();
		ResultSet data = SGBDConfig.getInstance().sendQueryForResults(sqlQuery, connection);
		try{
			while (data.next()){
				ClientReseveChambre item = new ClientReseveChambre();
				item.setId(data.getInt("id"));
				item.setIdClient(data.getInt("idClient"));
				item.setIdChambre(data.getInt("idChambre"));
				item.setDateDebutReservation(data.getDate("dateDebutReservation"));
				item.setDateFinReservation(data.getDate("dateFinReservation"));
				
				if (item.getIdClient() != null && item.getIdClient().intValue() > 0){
					item.setClient( DAOClient.getClient(item.getIdClient().intValue(), connection) );
				}
				if (item.getIdChambre() != null && item.getIdChambre().intValue() > 0){
					item.setChambre( DAOChambre.getChambre(item.getIdChambre().intValue(), connection) );
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

	public static String getSQLUpdateForIdClient(ClientReseveChambre clientReseveChambre){
		String sqlQuery ="update `clientReseveChambre` SET ";
		sqlQuery += "`idClient`="+(clientReseveChambre.getIdClient() > 0 ? "'"+clientReseveChambre.getIdClient()+"' " : "NULL ");
		sqlQuery += "where id = "+clientReseveChambre.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIdClientByPreparedStatement(ClientReseveChambre clientReseveChambre){
		String query = "update `clientReseveChambre` set ";
		query += "`idClient` = ? ";
		query += "where id = "+clientReseveChambre.getId().intValue();
		
		try{
			Object[] params = {clientReseveChambre.getIdClient() > 0 ? clientReseveChambre.getIdClient() : null};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForIdChambre(ClientReseveChambre clientReseveChambre){
		String sqlQuery ="update `clientReseveChambre` SET ";
		sqlQuery += "`idChambre`="+(clientReseveChambre.getIdChambre() > 0 ? "'"+clientReseveChambre.getIdChambre()+"' " : "NULL ");
		sqlQuery += "where id = "+clientReseveChambre.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIdChambreByPreparedStatement(ClientReseveChambre clientReseveChambre){
		String query = "update `clientReseveChambre` set ";
		query += "`idChambre` = ? ";
		query += "where id = "+clientReseveChambre.getId().intValue();
		
		try{
			Object[] params = {clientReseveChambre.getIdChambre() > 0 ? clientReseveChambre.getIdChambre() : null};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForDateDebutReservation(ClientReseveChambre clientReseveChambre){
		String sqlQuery ="update `clientReseveChambre` SET ";
		sqlQuery += "`dateDebutReservation`='"+clientReseveChambre.getDateDebutReservation()+"'";
		sqlQuery += "where id = "+clientReseveChambre.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForDateDebutReservationByPreparedStatement(ClientReseveChambre clientReseveChambre){
		String query = "update `clientReseveChambre` set ";
		query += "`dateDebutReservation` = ? ";
		query += "where id = "+clientReseveChambre.getId().intValue();
		
		try{
			Object[] params = {clientReseveChambre.getDateDebutReservation().toString()};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForDateFinReservation(ClientReseveChambre clientReseveChambre){
		String sqlQuery ="update `clientReseveChambre` SET ";
		sqlQuery += "`dateFinReservation`='"+clientReseveChambre.getDateFinReservation()+"'";
		sqlQuery += "where id = "+clientReseveChambre.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForDateFinReservationByPreparedStatement(ClientReseveChambre clientReseveChambre){
		String query = "update `clientReseveChambre` set ";
		query += "`dateFinReservation` = ? ";
		query += "where id = "+clientReseveChambre.getId().intValue();
		
		try{
			Object[] params = {clientReseveChambre.getDateFinReservation().toString()};
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
			ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("select group_concat(id) from `clientReseveChambre` "+whereCondition);
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
			ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("select count(id) from `clientReseveChambre` "+whereCondition);
			if (data.next())
				return data.getInt(1);
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		return 0;
	}

	public static String getSQLTuple(ClientReseveChambre clientReseveChambre){
		String sqlTuple = "";
		sqlTuple += "INSERT INTO `clientReseveChambre` ( `id`, `idClient`, `idChambre`, `dateDebutReservation`, `dateFinReservation`) VALUES";
		sqlTuple += "( '"+clientReseveChambre.getId()+"', "+((clientReseveChambre.getIdClient() == 0) ? "NULL" : "'"+clientReseveChambre.getIdClient()+"'")+", "+((clientReseveChambre.getIdChambre() == 0) ? "NULL" : "'"+clientReseveChambre.getIdChambre()+"'")+", '"+clientReseveChambre.getDateDebutReservation()+"', '"+clientReseveChambre.getDateFinReservation()+"');\n";

		return sqlTuple;
	}

	public static String getSQLStructure(boolean foreignKeyCheck, boolean addDropTable){
		String sqlStruct = "";
		if (foreignKeyCheck){
			sqlStruct += "SET FOREIGN_KEY_CHECKS=0;\n";
		}
		sqlStruct += "SET SQL_MODE=\"NO_AUTO_VALUE_ON_ZERO\";\n\n";
		
		if (addDropTable){
			sqlStruct += "DROP TABLE IF EXISTS `clientReseveChambre`;\n";
		}

		sqlStruct += "CREATE TABLE IF NOT EXISTS `clientReseveChambre` (\n";
		sqlStruct += "  `id` int(11) NOT NULL AUTO_INCREMENT,\n";
		sqlStruct += "  `idClient` int(11) NOT NULL,\n";
		sqlStruct += "  `idChambre` int(11) NOT NULL,\n";
		sqlStruct += "  `dateDebutReservation` date NOT NULL,\n";
		sqlStruct += "  `dateFinReservation` date NOT NULL,\n";
		sqlStruct += "  PRIMARY KEY (`id`),\n";
		sqlStruct += "  KEY `idClient` (`idClient`),\n";
		sqlStruct += "  KEY `idChambre` (`idChambre`)\n";
		sqlStruct += ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='' AUTO_INCREMENT=1 ;\n";

		sqlStruct += "ALTER TABLE `clientReseveChambre`\n";
		sqlStruct += "  ADD CONSTRAINT `clientReseveChambre_ibfk_1` FOREIGN KEY (`idClient`) REFERENCES `client` (`id`),\n";
		sqlStruct += "  ADD CONSTRAINT `clientReseveChambre_ibfk_2` FOREIGN KEY (`idChambre`) REFERENCES `chambre` (`id`);\n";
		

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

	public static String getSQLContent(java.util.List<ClientReseveChambre> list, boolean foreignKeyCheck, boolean addDropTable){
		String sqlContent = getSQLStructure(foreignKeyCheck, addDropTable);
		
		sqlContent += "\n\n";
		for (ClientReseveChambre item : list){
			sqlContent += getSQLTuple(item);
		}
		
		if (foreignKeyCheck){
			sqlContent = "SET FOREIGN_KEY_CHECKS=0;\n" + sqlContent + "SET FOREIGN_KEY_CHECKS=1;";
		}
		
		return sqlContent;
	}

	public static String showSQLStructure(){
		try{
			java.sql.ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("show create table `clientReseveChambre`");
			if (data.next()){
				return data.getString(2);
			}
		}
		catch (Exception e){
		}
		
		return "";
	}

	public static String getTableName(){
		return "clientReseveChambre";
	}
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}