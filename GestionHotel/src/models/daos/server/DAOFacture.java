package models.daos.server;

import java.sql.Connection;
import java.sql.ResultSet;

import utils.SGBDConfig;

import models.beans.Facture;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This classe maps the Table <i><b>facture</b></i> in the Database. 
 *	<br/>It contains attributs which represents the columns of the table,
 *	<br/>and Methods for geting one row by id, updating, inserting and deleting rows.
 */

public abstract class DAOFacture {
	public static Facture getFacture(int id) {
		return getFacture(id, null);
	}

	public static Facture getFacture(int id, Connection connection) {
		Facture facture = new Facture();
		if (id == 0)
			return facture;
		String query = "select * from `facture` where `id`="+id+" limit 1";
		boolean toCloseConnection = false;
		if (connection == null){
			connection = SGBDConfig.getInstance().getConnexionWithoutSaving();
			toCloseConnection = true;
		}
		ResultSet data = SGBDConfig.getInstance().sendQueryForResults(query, connection);
		try{
			if (data.next()){
				facture.setId(data.getInt("id"));
				facture.setDateFacture(data.getDate("dateFacture"));
				facture.setIdClient(data.getInt("idClient"));
				facture.setMontant(data.getDouble("montant"));
				facture.setTypePayementC(Facture.TypePayementC.getByValue(data.getString("typePayementC")));
			}
			
			if (facture.getIdClient() != null && facture.getIdClient().intValue() > 0){
				facture.setClient(DAOClient.getClient(facture.getIdClient(), connection));
			}
			if (toCloseConnection){
				connection.close();
			}
		}
		catch (Exception e){
			utils.StringUtils.printDebug(e);
		}

		return facture;
	}

	public static String getSQLDeleting(Facture facture) {
		String query = "delete from `facture` where `id` = "+facture.getId();
		return query;
	}

	public static String getSQLDeleting() {
		String query = "delete from `facture` where 1 ";
		return query;
	}

	public static String getSQLWriting(Facture facture) {
		String query;
		if (facture.getId().intValue() == 0){
			query = "insert into `facture` (`id`, `dateFacture`, `idClient`, `montant`, `typePayementC`)";
			query += " values ('"+facture.getId()+"', '"+facture.getDateFacture()+"', "+ ((facture.getIdClient() > 0) ? "'"+facture.getIdClient()+"'" : "NULL" )+", '"+facture.getMontant()+"', '"+facture.getTypePayementC().getValue()+"')";
		}
		else{
			query ="update `facture` SET ";
			query += "id='"+facture.getId()+"',";
			query += "dateFacture='"+facture.getDateFacture()+"',";
			query += "`idClient`="+((facture.getIdClient() > 0) ? "'"+facture.getIdClient()+"'" : "NULL")+",";
			query += "montant='"+facture.getMontant()+"',";
			query += "`typePayementC`='"+facture.getTypePayementC().getValue()+"'";
			query += " where `id`="+facture.getId();
		}
		return query;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLWritingByPreparedStatement(Facture facture) {
		String query;
		if (facture.getId().intValue() == 0){
			query = "insert into ";
		}
		else{
			query = "update ";
		}

		query += " `facture` set ";
		query += "`id` = ?, ";
		query += "`dateFacture` = ?, ";
		query += "`idClient` = ?, ";
		query += "`montant` = ?, ";
		query += "`typePayementC` = ? ";

		if (facture.getId().intValue() > 0){
			query += " where `id` = "+facture.getId().intValue();
		}

		try{
			Object[] params = {facture.getId(), facture.getDateFacture().toString(), facture.getIdClient() == 0 ? null : facture.getIdClient(), facture.getMontant(), facture.getTypePayementC().getValue().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static int write(Facture facture) {
		String query = getSQLWriting(facture);
//		SGBDConfig.getInstance().sendQuery("alter table `facture` auto_increment =1");
		SGBDConfig.getInstance().sendQuery(query);
		if(facture.getId() == 0) {
			ResultSet data = SGBDConfig.getInstance().sendQueryForResults("select id from `facture` order by id desc limit 1");
			try{
				if (data.next()){
					facture.setId(data.getInt("id"));
				}
			} catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
		}
		return facture.getId();
	}

	public static int writeByPreparedStatement(Facture facture) {
		utils.SGBDConfig.InsertUpdateSQLQueries writingQuerie = getSQLWritingByPreparedStatement(facture);
//		SGBDConfig.getInstance().sendQuery("alter table `facture` auto_increment =1");
		utils.SGBDConfig.getInstance().sendQueriesByPreparedStatement(writingQuerie);
		
		if(facture.getId().intValue() == 0) {
			ResultSet data = SGBDConfig.getInstance().sendQueryForResults("select id from `facture` order by id desc limit 1");
			try{
				if (data.next()){
					facture.setId(data.getInt("id"));
				}
			} catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
		}
		return facture.getId();
	}

	public static void delete(Facture facture){
		SGBDConfig.getInstance().sendQuery(getSQLDeleting(facture));
	}

	public static void deleteAll(){
		SGBDConfig.getInstance().sendQuery(getSQLDeleting());
	}

	public static java.util.List<Facture> getListInstances(){
		return getListInstances("");
	}

	public static java.util.List<Facture> getListInstances(String whereCondition){
		String query = "select * from facture";
		if (!whereCondition.trim().equals("")){
			if (!whereCondition.trim().equals("") && !whereCondition.trim().toLowerCase().startsWith("where")){
				query += " where ";
			}
			query += " "+whereCondition;
		}
		return getListInstancesBySQLQuery(query);
	}

	public static java.util.List<Facture> getListInstancesBySQLQuery(String sqlQuery){
		java.util.List<Facture> list = new java.util.ArrayList<Facture>();
		
		Connection connection = SGBDConfig.getInstance().getConnexionWithoutSaving();
		ResultSet data = SGBDConfig.getInstance().sendQueryForResults(sqlQuery, connection);
		try{
			while (data.next()){
				Facture item = new Facture();
				item.setId(data.getInt("id"));
				item.setDateFacture(data.getDate("dateFacture"));
				item.setIdClient(data.getInt("idClient"));
				item.setMontant(data.getDouble("montant"));
				item.setTypePayementC(Facture.TypePayementC.getByValue(data.getString("typePayementC")));
				
				if (item.getIdClient() != null && item.getIdClient().intValue() > 0){
					item.setClient( DAOClient.getClient(item.getIdClient().intValue(), connection) );
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

	public static String getSQLUpdateForDateFacture(Facture facture){
		String sqlQuery ="update `facture` SET ";
		sqlQuery += "`dateFacture`='"+facture.getDateFacture()+"'";
		sqlQuery += "where id = "+facture.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForDateFactureByPreparedStatement(Facture facture){
		String query = "update `facture` set ";
		query += "`dateFacture` = ? ";
		query += "where id = "+facture.getId().intValue();
		
		try{
			Object[] params = {facture.getDateFacture().toString()};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForIdClient(Facture facture){
		String sqlQuery ="update `facture` SET ";
		sqlQuery += "`idClient`="+(facture.getIdClient() > 0 ? "'"+facture.getIdClient()+"' " : "NULL ");
		sqlQuery += "where id = "+facture.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIdClientByPreparedStatement(Facture facture){
		String query = "update `facture` set ";
		query += "`idClient` = ? ";
		query += "where id = "+facture.getId().intValue();
		
		try{
			Object[] params = {facture.getIdClient() > 0 ? facture.getIdClient() : null};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForMontant(Facture facture){
		String sqlQuery ="update `facture` SET ";
		sqlQuery += "`montant`='"+facture.getMontant()+"'";
		sqlQuery += "where id = "+facture.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForMontantByPreparedStatement(Facture facture){
		String query = "update `facture` set ";
		query += "`montant` = ? ";
		query += "where id = "+facture.getId().intValue();
		
		try{
			Object[] params = {facture.getMontant()};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForTypePayementC(Facture facture){
		String sqlQuery ="update `facture` SET ";
		sqlQuery += "`typePayementC`='"+facture.getTypePayementC().getValue()+"' ";
		sqlQuery += "where id = "+facture.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForTypePayementCByPreparedStatement(Facture facture){
		String query = "update `facture` set ";
		query += "`typePayementC` = ? ";
		query += "where id = "+facture.getId().intValue();
		
		try{
			Object[] params = {facture.getTypePayementC().getValue().getBytes("UTF-8")};
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
			ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("select group_concat(id) from `facture` "+whereCondition);
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
			ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("select count(id) from `facture` "+whereCondition);
			if (data.next())
				return data.getInt(1);
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		return 0;
	}

	public static String getSQLTuple(Facture facture){
		String sqlTuple = "";
		sqlTuple += "INSERT INTO `facture` ( `id`, `dateFacture`, `idClient`, `montant`, `typePayementC`) VALUES";
		sqlTuple += "( '"+facture.getId()+"', '"+facture.getDateFacture()+"', "+((facture.getIdClient() == 0) ? "NULL" : "'"+facture.getIdClient()+"'")+", '"+facture.getMontant()+"', '"+utils.StringUtils.addSQLSlashes(facture.getTypePayementC().getValue())+"');\n";

		return sqlTuple;
	}

	public static String getSQLStructure(boolean foreignKeyCheck, boolean addDropTable){
		String sqlStruct = "";
		if (foreignKeyCheck){
			sqlStruct += "SET FOREIGN_KEY_CHECKS=0;\n";
		}
		sqlStruct += "SET SQL_MODE=\"NO_AUTO_VALUE_ON_ZERO\";\n\n";
		
		if (addDropTable){
			sqlStruct += "DROP TABLE IF EXISTS `facture`;\n";
		}

		sqlStruct += "CREATE TABLE IF NOT EXISTS `facture` (\n";
		sqlStruct += "  `id` int(11) NOT NULL AUTO_INCREMENT,\n";
		sqlStruct += "  `dateFacture` date NOT NULL,\n";
		sqlStruct += "  `idClient` int(11) NOT NULL,\n";
		sqlStruct += "  `montant` double NOT NULL,\n";
		sqlStruct += "  `typePayementC` enum('CB','Chèque','Espasse') NOT NULL,\n";
		sqlStruct += "  PRIMARY KEY (`id`),\n";
		sqlStruct += "  KEY `idClient` (`idClient`)\n";
		sqlStruct += ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='' AUTO_INCREMENT=1 ;\n";

		sqlStruct += "ALTER TABLE `facture`\n";
		sqlStruct += "  ADD CONSTRAINT `facture_ibfk_1` FOREIGN KEY (`idClient`) REFERENCES `client` (`id`);\n";
		

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

	public static String getSQLContent(java.util.List<Facture> list, boolean foreignKeyCheck, boolean addDropTable){
		String sqlContent = getSQLStructure(foreignKeyCheck, addDropTable);
		
		sqlContent += "\n\n";
		for (Facture item : list){
			sqlContent += getSQLTuple(item);
		}
		
		if (foreignKeyCheck){
			sqlContent = "SET FOREIGN_KEY_CHECKS=0;\n" + sqlContent + "SET FOREIGN_KEY_CHECKS=1;";
		}
		
		return sqlContent;
	}

	public static String showSQLStructure(){
		try{
			java.sql.ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("show create table `facture`");
			if (data.next()){
				return data.getString(2);
			}
		}
		catch (Exception e){
		}
		
		return "";
	}

	public static String getTableName(){
		return "facture";
	}
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}