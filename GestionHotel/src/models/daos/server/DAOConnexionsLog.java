package models.daos.server;

import java.sql.Connection;
import java.sql.ResultSet;

import utils.SGBDConfig;

import models.beans.ConnexionsLog;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This classe maps the Table <i><b>connexionsLog</b></i> in the Database. 
 *	<br/>It contains attributs which represents the columns of the table,
 *	<br/>and Methods for geting one row by id, updating, inserting and deleting rows.
 */

public abstract class DAOConnexionsLog {
	public static ConnexionsLog getConnexionsLog(int id) {
		return getConnexionsLog(id, null);
	}

	public static ConnexionsLog getConnexionsLog(int id, Connection connection) {
		ConnexionsLog connexionsLog = new ConnexionsLog();
		if (id == 0)
			return connexionsLog;
		String query = "select * from `connexionsLog` where `id`="+id+" limit 1";
		boolean toCloseConnection = false;
		if (connection == null){
			connection = SGBDConfig.getInstance().getConnexionWithoutSaving();
			toCloseConnection = true;
		}
		ResultSet data = SGBDConfig.getInstance().sendQueryForResults(query, connection);
		try{
			if (data.next()){
				connexionsLog.setId(data.getInt("id"));
				connexionsLog.setLogin(data.getString("login"));
				connexionsLog.setUuid(data.getString("uuid"));
				connexionsLog.setDateDeConnexion(data.getTimestamp("dateDeConnexion"));
				connexionsLog.setDateDeDeconnexion(data.getTimestamp("dateDeDeconnexion"));
				connexionsLog.setIp(data.getString("ip"));
				connexionsLog.setMac(data.getString("mac"));
				connexionsLog.setMachine(data.getString("machine"));
				connexionsLog.setConnexionAccepted(ConnexionsLog.ConnexionAccepted.getByValue(data.getString("connexionAccepted")));
				connexionsLog.setMotifError(data.getString("motifError"));
				connexionsLog.setIdUtilisateur(data.getInt("idUtilisateur"));
			}
			
			if (connexionsLog.getIdUtilisateur() != null && connexionsLog.getIdUtilisateur().intValue() > 0){
				connexionsLog.setUtilisateur(DAOUtilisateur.getUtilisateur(connexionsLog.getIdUtilisateur(), connection));
			}
			if (toCloseConnection){
				connection.close();
			}
		}
		catch (Exception e){
			utils.StringUtils.printDebug(e);
		}

		return connexionsLog;
	}

	public static String getSQLDeleting(ConnexionsLog connexionsLog) {
		String query = "delete from `connexionsLog` where `id` = "+connexionsLog.getId();
		return query;
	}

	public static String getSQLDeleting() {
		String query = "delete from `connexionsLog` where 1 ";
		return query;
	}

	public static String getSQLWriting(ConnexionsLog connexionsLog) {
		String query;
		if (connexionsLog.getId().intValue() == 0){
			query = "insert into `connexionsLog` (`id`, `login`, `uuid`, `dateDeConnexion`, `dateDeDeconnexion`, `ip`, `mac`, `machine`, `connexionAccepted`, `motifError`, `idUtilisateur`)";
			query += " values ('"+connexionsLog.getId()+"', '"+utils.StringUtils.addSQLSlashes(connexionsLog.getLogin())+"', '"+utils.StringUtils.addSQLSlashes(connexionsLog.getUuid())+"', '"+connexionsLog.getDateDeConnexion()+"', '"+connexionsLog.getDateDeDeconnexion()+"', '"+utils.StringUtils.addSQLSlashes(connexionsLog.getIp())+"', '"+utils.StringUtils.addSQLSlashes(connexionsLog.getMac())+"', '"+utils.StringUtils.addSQLSlashes(connexionsLog.getMachine())+"', '"+connexionsLog.getConnexionAccepted().getValue()+"', '"+utils.StringUtils.addSQLSlashes(connexionsLog.getMotifError())+"', "+ ((connexionsLog.getIdUtilisateur() > 0) ? "'"+connexionsLog.getIdUtilisateur()+"'" : "NULL" )+")";
		}
		else{
			query ="update `connexionsLog` SET ";
			query += "id='"+connexionsLog.getId()+"',";
			query += "`login`='"+utils.StringUtils.addSQLSlashes(connexionsLog.getLogin())+"',";
			query += "`uuid`='"+utils.StringUtils.addSQLSlashes(connexionsLog.getUuid())+"',";
			query += "dateDeConnexion='"+connexionsLog.getDateDeConnexion()+"',";
			query += "dateDeDeconnexion='"+connexionsLog.getDateDeDeconnexion()+"',";
			query += "`ip`='"+utils.StringUtils.addSQLSlashes(connexionsLog.getIp())+"',";
			query += "`mac`='"+utils.StringUtils.addSQLSlashes(connexionsLog.getMac())+"',";
			query += "`machine`='"+utils.StringUtils.addSQLSlashes(connexionsLog.getMachine())+"',";
			query += "`connexionAccepted`='"+connexionsLog.getConnexionAccepted().getValue()+"',";
			query += "`motifError`='"+utils.StringUtils.addSQLSlashes(connexionsLog.getMotifError())+"',";
			query += "`idUtilisateur`="+((connexionsLog.getIdUtilisateur() > 0) ? "'"+connexionsLog.getIdUtilisateur()+"'" : "NULL")+"";
			query += " where `id`="+connexionsLog.getId();
		}
		return query;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLWritingByPreparedStatement(ConnexionsLog connexionsLog) {
		String query;
		if (connexionsLog.getId().intValue() == 0){
			query = "insert into ";
		}
		else{
			query = "update ";
		}

		query += " `connexionsLog` set ";
		query += "`id` = ?, ";
		query += "`login` = ?, ";
		query += "`uuid` = ?, ";
		query += "`dateDeConnexion` = ?, ";
		query += "`dateDeDeconnexion` = ?, ";
		query += "`ip` = ?, ";
		query += "`mac` = ?, ";
		query += "`machine` = ?, ";
		query += "`connexionAccepted` = ?, ";
		query += "`motifError` = ?, ";
		query += "`idUtilisateur` = ? ";

		if (connexionsLog.getId().intValue() > 0){
			query += " where `id` = "+connexionsLog.getId().intValue();
		}

		try{
			Object[] params = {connexionsLog.getId(), connexionsLog.getLogin().getBytes("UTF-8"), connexionsLog.getUuid().getBytes("UTF-8"), connexionsLog.getDateDeConnexion().toString(), connexionsLog.getDateDeDeconnexion().toString(), connexionsLog.getIp().getBytes("UTF-8"), connexionsLog.getMac().getBytes("UTF-8"), connexionsLog.getMachine().getBytes("UTF-8"), connexionsLog.getConnexionAccepted().getValue().getBytes("UTF-8"), connexionsLog.getMotifError().getBytes("UTF-8"), connexionsLog.getIdUtilisateur() == 0 ? null : connexionsLog.getIdUtilisateur()};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static int write(ConnexionsLog connexionsLog) {
		String query = getSQLWriting(connexionsLog);
//		SGBDConfig.getInstance().sendQuery("alter table `connexionsLog` auto_increment =1");
		SGBDConfig.getInstance().sendQuery(query);
		if(connexionsLog.getId() == 0) {
			ResultSet data = SGBDConfig.getInstance().sendQueryForResults("select id from `connexionsLog` order by id desc limit 1");
			try{
				if (data.next()){
					connexionsLog.setId(data.getInt("id"));
				}
			} catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
		}
		return connexionsLog.getId();
	}

	public static int writeByPreparedStatement(ConnexionsLog connexionsLog) {
		utils.SGBDConfig.InsertUpdateSQLQueries writingQuerie = getSQLWritingByPreparedStatement(connexionsLog);
//		SGBDConfig.getInstance().sendQuery("alter table `connexionsLog` auto_increment =1");
		utils.SGBDConfig.getInstance().sendQueriesByPreparedStatement(writingQuerie);
		
		if(connexionsLog.getId().intValue() == 0) {
			ResultSet data = SGBDConfig.getInstance().sendQueryForResults("select id from `connexionsLog` order by id desc limit 1");
			try{
				if (data.next()){
					connexionsLog.setId(data.getInt("id"));
				}
			} catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
		}
		return connexionsLog.getId();
	}

	public static void delete(ConnexionsLog connexionsLog){
		SGBDConfig.getInstance().sendQuery(getSQLDeleting(connexionsLog));
	}

	public static void deleteAll(){
		SGBDConfig.getInstance().sendQuery(getSQLDeleting());
	}

	public static java.util.List<ConnexionsLog> getListInstances(){
		return getListInstances("");
	}

	public static java.util.List<ConnexionsLog> getListInstances(String whereCondition){
		String query = "select * from connexionsLog";
		if (!whereCondition.trim().equals("")){
			if (!whereCondition.trim().equals("") && !whereCondition.trim().toLowerCase().startsWith("where")){
				query += " where ";
			}
			query += " "+whereCondition;
		}
		return getListInstancesBySQLQuery(query);
	}

	public static java.util.List<ConnexionsLog> getListInstancesBySQLQuery(String sqlQuery){
		java.util.List<ConnexionsLog> list = new java.util.ArrayList<ConnexionsLog>();
		
		Connection connection = SGBDConfig.getInstance().getConnexionWithoutSaving();
		ResultSet data = SGBDConfig.getInstance().sendQueryForResults(sqlQuery, connection);
		try{
			while (data.next()){
				ConnexionsLog item = new ConnexionsLog();
				item.setId(data.getInt("id"));
				item.setLogin(data.getString("login"));
				item.setUuid(data.getString("uuid"));
				item.setDateDeConnexion(data.getTimestamp("dateDeConnexion"));
				item.setDateDeDeconnexion(data.getTimestamp("dateDeDeconnexion"));
				item.setIp(data.getString("ip"));
				item.setMac(data.getString("mac"));
				item.setMachine(data.getString("machine"));
				item.setConnexionAccepted(ConnexionsLog.ConnexionAccepted.getByValue(data.getString("connexionAccepted")));
				item.setMotifError(data.getString("motifError"));
				item.setIdUtilisateur(data.getInt("idUtilisateur"));
				
				if (item.getIdUtilisateur() != null && item.getIdUtilisateur().intValue() > 0){
					item.setUtilisateur( DAOUtilisateur.getUtilisateur(item.getIdUtilisateur().intValue(), connection) );
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

	public static String getSQLUpdateForLogin(ConnexionsLog connexionsLog){
		String sqlQuery ="update `connexionsLog` SET ";
		sqlQuery += "`login`='"+utils.StringUtils.addSQLSlashes(connexionsLog.getLogin())+"'";
		sqlQuery += "where id = "+connexionsLog.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForLoginByPreparedStatement(ConnexionsLog connexionsLog){
		String query = "update `connexionsLog` set ";
		query += "`login` = ? ";
		query += "where id = "+connexionsLog.getId().intValue();
		
		try{
			Object[] params = {connexionsLog.getLogin().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForUuid(ConnexionsLog connexionsLog){
		String sqlQuery ="update `connexionsLog` SET ";
		sqlQuery += "`uuid`='"+utils.StringUtils.addSQLSlashes(connexionsLog.getUuid())+"'";
		sqlQuery += "where id = "+connexionsLog.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForUuidByPreparedStatement(ConnexionsLog connexionsLog){
		String query = "update `connexionsLog` set ";
		query += "`uuid` = ? ";
		query += "where id = "+connexionsLog.getId().intValue();
		
		try{
			Object[] params = {connexionsLog.getUuid().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForDateDeConnexion(ConnexionsLog connexionsLog){
		String sqlQuery ="update `connexionsLog` SET ";
		sqlQuery += "`dateDeConnexion`='"+connexionsLog.getDateDeConnexion()+"'";
		sqlQuery += "where id = "+connexionsLog.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForDateDeConnexionByPreparedStatement(ConnexionsLog connexionsLog){
		String query = "update `connexionsLog` set ";
		query += "`dateDeConnexion` = ? ";
		query += "where id = "+connexionsLog.getId().intValue();
		
		try{
			Object[] params = {connexionsLog.getDateDeConnexion().toString()};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForDateDeDeconnexion(ConnexionsLog connexionsLog){
		String sqlQuery ="update `connexionsLog` SET ";
		sqlQuery += "`dateDeDeconnexion`='"+connexionsLog.getDateDeDeconnexion()+"'";
		sqlQuery += "where id = "+connexionsLog.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForDateDeDeconnexionByPreparedStatement(ConnexionsLog connexionsLog){
		String query = "update `connexionsLog` set ";
		query += "`dateDeDeconnexion` = ? ";
		query += "where id = "+connexionsLog.getId().intValue();
		
		try{
			Object[] params = {connexionsLog.getDateDeDeconnexion().toString()};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForIp(ConnexionsLog connexionsLog){
		String sqlQuery ="update `connexionsLog` SET ";
		sqlQuery += "`ip`='"+utils.StringUtils.addSQLSlashes(connexionsLog.getIp())+"'";
		sqlQuery += "where id = "+connexionsLog.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIpByPreparedStatement(ConnexionsLog connexionsLog){
		String query = "update `connexionsLog` set ";
		query += "`ip` = ? ";
		query += "where id = "+connexionsLog.getId().intValue();
		
		try{
			Object[] params = {connexionsLog.getIp().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForMac(ConnexionsLog connexionsLog){
		String sqlQuery ="update `connexionsLog` SET ";
		sqlQuery += "`mac`='"+utils.StringUtils.addSQLSlashes(connexionsLog.getMac())+"'";
		sqlQuery += "where id = "+connexionsLog.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForMacByPreparedStatement(ConnexionsLog connexionsLog){
		String query = "update `connexionsLog` set ";
		query += "`mac` = ? ";
		query += "where id = "+connexionsLog.getId().intValue();
		
		try{
			Object[] params = {connexionsLog.getMac().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForMachine(ConnexionsLog connexionsLog){
		String sqlQuery ="update `connexionsLog` SET ";
		sqlQuery += "`machine`='"+utils.StringUtils.addSQLSlashes(connexionsLog.getMachine())+"'";
		sqlQuery += "where id = "+connexionsLog.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForMachineByPreparedStatement(ConnexionsLog connexionsLog){
		String query = "update `connexionsLog` set ";
		query += "`machine` = ? ";
		query += "where id = "+connexionsLog.getId().intValue();
		
		try{
			Object[] params = {connexionsLog.getMachine().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForConnexionAccepted(ConnexionsLog connexionsLog){
		String sqlQuery ="update `connexionsLog` SET ";
		sqlQuery += "`connexionAccepted`='"+connexionsLog.getConnexionAccepted().getValue()+"' ";
		sqlQuery += "where id = "+connexionsLog.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForConnexionAcceptedByPreparedStatement(ConnexionsLog connexionsLog){
		String query = "update `connexionsLog` set ";
		query += "`connexionAccepted` = ? ";
		query += "where id = "+connexionsLog.getId().intValue();
		
		try{
			Object[] params = {connexionsLog.getConnexionAccepted().getValue().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForMotifError(ConnexionsLog connexionsLog){
		String sqlQuery ="update `connexionsLog` SET ";
		sqlQuery += "`motifError`='"+utils.StringUtils.addSQLSlashes(connexionsLog.getMotifError())+"'";
		sqlQuery += "where id = "+connexionsLog.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForMotifErrorByPreparedStatement(ConnexionsLog connexionsLog){
		String query = "update `connexionsLog` set ";
		query += "`motifError` = ? ";
		query += "where id = "+connexionsLog.getId().intValue();
		
		try{
			Object[] params = {connexionsLog.getMotifError().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForIdUtilisateur(ConnexionsLog connexionsLog){
		String sqlQuery ="update `connexionsLog` SET ";
		sqlQuery += "`idUtilisateur`="+(connexionsLog.getIdUtilisateur() > 0 ? "'"+connexionsLog.getIdUtilisateur()+"' " : "NULL ");
		sqlQuery += "where id = "+connexionsLog.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIdUtilisateurByPreparedStatement(ConnexionsLog connexionsLog){
		String query = "update `connexionsLog` set ";
		query += "`idUtilisateur` = ? ";
		query += "where id = "+connexionsLog.getId().intValue();
		
		try{
			Object[] params = {connexionsLog.getIdUtilisateur() > 0 ? connexionsLog.getIdUtilisateur() : null};
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
			ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("select group_concat(id) from `connexionsLog` "+whereCondition);
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
			ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("select count(id) from `connexionsLog` "+whereCondition);
			if (data.next())
				return data.getInt(1);
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		return 0;
	}

	public static String getSQLTuple(ConnexionsLog connexionsLog){
		String sqlTuple = "";
		sqlTuple += "INSERT INTO `connexionsLog` ( `id`, `login`, `uuid`, `dateDeConnexion`, `dateDeDeconnexion`, `ip`, `mac`, `machine`, `connexionAccepted`, `motifError`, `idUtilisateur`) VALUES";
		sqlTuple += "( '"+connexionsLog.getId()+"', '"+utils.StringUtils.addSQLSlashes(connexionsLog.getLogin())+"', '"+utils.StringUtils.addSQLSlashes(connexionsLog.getUuid())+"', '"+connexionsLog.getDateDeConnexion()+"', '"+connexionsLog.getDateDeDeconnexion()+"', '"+utils.StringUtils.addSQLSlashes(connexionsLog.getIp())+"', '"+utils.StringUtils.addSQLSlashes(connexionsLog.getMac())+"', '"+utils.StringUtils.addSQLSlashes(connexionsLog.getMachine())+"', '"+utils.StringUtils.addSQLSlashes(connexionsLog.getConnexionAccepted().getValue())+"', '"+utils.StringUtils.addSQLSlashes(connexionsLog.getMotifError())+"', "+((connexionsLog.getIdUtilisateur() == 0) ? "NULL" : "'"+connexionsLog.getIdUtilisateur()+"'")+");\n";

		return sqlTuple;
	}

	public static String getSQLStructure(boolean foreignKeyCheck, boolean addDropTable){
		String sqlStruct = "";
		if (foreignKeyCheck){
			sqlStruct += "SET FOREIGN_KEY_CHECKS=0;\n";
		}
		sqlStruct += "SET SQL_MODE=\"NO_AUTO_VALUE_ON_ZERO\";\n\n";
		
		if (addDropTable){
			sqlStruct += "DROP TABLE IF EXISTS `connexionsLog`;\n";
		}

		sqlStruct += "CREATE TABLE IF NOT EXISTS `connexionsLog` (\n";
		sqlStruct += "  `id` int(11) NOT NULL AUTO_INCREMENT,\n";
		sqlStruct += "  `login` varchar(100) NOT NULL,\n";
		sqlStruct += "  `uuid` varchar(36) DEFAULT NULL,\n";
		sqlStruct += "  `dateDeConnexion` datetime DEFAULT NULL,\n";
		sqlStruct += "  `dateDeDeconnexion` datetime DEFAULT NULL,\n";
		sqlStruct += "  `ip` varchar(15) DEFAULT NULL,\n";
		sqlStruct += "  `mac` varchar(17) DEFAULT NULL,\n";
		sqlStruct += "  `machine` varchar(100) DEFAULT NULL,\n";
		sqlStruct += "  `connexionAccepted` enum('oui','non') DEFAULT NULL,\n";
		sqlStruct += "  `motifError` varchar(500) DEFAULT NULL,\n";
		sqlStruct += "  `idUtilisateur` int(11) DEFAULT NULL,\n";
		sqlStruct += "  PRIMARY KEY (`id`),\n";
		sqlStruct += "  KEY `idUtilisateur` (`idUtilisateur`)\n";
		sqlStruct += ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='Journal des Connexions' AUTO_INCREMENT=1 ;\n";

		sqlStruct += "ALTER TABLE `connexionsLog`\n";
		sqlStruct += "  ADD CONSTRAINT `connexionsLog_ibfk_1` FOREIGN KEY (`idUtilisateur`) REFERENCES `utilisateur` (`id`);\n";
		

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

	public static String getSQLContent(java.util.List<ConnexionsLog> list, boolean foreignKeyCheck, boolean addDropTable){
		String sqlContent = getSQLStructure(foreignKeyCheck, addDropTable);
		
		sqlContent += "\n\n";
		for (ConnexionsLog item : list){
			sqlContent += getSQLTuple(item);
		}
		
		if (foreignKeyCheck){
			sqlContent = "SET FOREIGN_KEY_CHECKS=0;\n" + sqlContent + "SET FOREIGN_KEY_CHECKS=1;";
		}
		
		return sqlContent;
	}

	public static String showSQLStructure(){
		try{
			java.sql.ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("show create table `connexionsLog`");
			if (data.next()){
				return data.getString(2);
			}
		}
		catch (Exception e){
		}
		
		return "";
	}

	public static String getTableName(){
		return "connexionsLog";
	}
	
	public static java.util.List<models.beans.EventsLog> getListOfEventsLogsConnexionsLog(models.beans.ConnexionsLog connexionsLog){
		java.util.List<models.beans.EventsLog> listOfEventsLogsConnexionsLog = models.daos.server.DAOEventsLog.getListInstances("where idConnexionsLog='"+connexionsLog.getId().intValue()+"'");
		return listOfEventsLogsConnexionsLog;
	}
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}