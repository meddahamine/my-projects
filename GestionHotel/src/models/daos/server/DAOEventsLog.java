package models.daos.server;

import java.sql.Connection;
import java.sql.ResultSet;

import utils.SGBDConfig;

import models.beans.EventsLog;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This classe maps the Table <i><b>eventsLog</b></i> in the Database. 
 *	<br/>It contains attributs which represents the columns of the table,
 *	<br/>and Methods for geting one row by id, updating, inserting and deleting rows.
 */

public abstract class DAOEventsLog {
	public static EventsLog getEventsLog(int id) {
		return getEventsLog(id, null);
	}

	public static EventsLog getEventsLog(int id, Connection connection) {
		EventsLog eventsLog = new EventsLog();
		if (id == 0)
			return eventsLog;
		String query = "select * from `eventsLog` where `id`="+id+" limit 1";
		boolean toCloseConnection = false;
		if (connection == null){
			connection = SGBDConfig.getInstance().getConnexionWithoutSaving();
			toCloseConnection = true;
		}
		ResultSet data = SGBDConfig.getInstance().sendQueryForResults(query, connection);
		try{
			if (data.next()){
				eventsLog.setId(data.getInt("id"));
				eventsLog.setEvenement(data.getString("evenement"));
				eventsLog.setIdConnexionsLog(data.getInt("idConnexionsLog"));
				eventsLog.setDate(data.getTimestamp("date"));
			}
			
			if (eventsLog.getIdConnexionsLog() != null && eventsLog.getIdConnexionsLog().intValue() > 0){
				eventsLog.setConnexionsLog(DAOConnexionsLog.getConnexionsLog(eventsLog.getIdConnexionsLog(), connection));
			}
			if (toCloseConnection){
				connection.close();
			}
		}
		catch (Exception e){
			utils.StringUtils.printDebug(e);
		}

		return eventsLog;
	}

	public static String getSQLDeleting(EventsLog eventsLog) {
		String query = "delete from `eventsLog` where `id` = "+eventsLog.getId();
		return query;
	}

	public static String getSQLDeleting() {
		String query = "delete from `eventsLog` where 1 ";
		return query;
	}

	public static String getSQLWriting(EventsLog eventsLog) {
		String query;
		if (eventsLog.getId().intValue() == 0){
			query = "insert into `eventsLog` (`id`, `evenement`, `idConnexionsLog`, `date`)";
			query += " values ('"+eventsLog.getId()+"', '"+utils.StringUtils.addSQLSlashes(eventsLog.getEvenement())+"', "+ ((eventsLog.getIdConnexionsLog() > 0) ? "'"+eventsLog.getIdConnexionsLog()+"'" : "NULL" )+", '"+eventsLog.getDate()+"')";
		}
		else{
			query ="update `eventsLog` SET ";
			query += "id='"+eventsLog.getId()+"',";
			query += "`evenement`='"+utils.StringUtils.addSQLSlashes(eventsLog.getEvenement())+"',";
			query += "`idConnexionsLog`="+((eventsLog.getIdConnexionsLog() > 0) ? "'"+eventsLog.getIdConnexionsLog()+"'" : "NULL")+",";
			query += "date='"+eventsLog.getDate()+"'";
			query += " where `id`="+eventsLog.getId();
		}
		return query;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLWritingByPreparedStatement(EventsLog eventsLog) {
		String query;
		if (eventsLog.getId().intValue() == 0){
			query = "insert into ";
		}
		else{
			query = "update ";
		}

		query += " `eventsLog` set ";
		query += "`id` = ?, ";
		query += "`evenement` = ?, ";
		query += "`idConnexionsLog` = ?, ";
		query += "`date` = ? ";

		if (eventsLog.getId().intValue() > 0){
			query += " where `id` = "+eventsLog.getId().intValue();
		}

		try{
			Object[] params = {eventsLog.getId(), eventsLog.getEvenement().getBytes("UTF-8"), eventsLog.getIdConnexionsLog() == 0 ? null : eventsLog.getIdConnexionsLog(), eventsLog.getDate().toString()};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static int write(EventsLog eventsLog) {
		String query = getSQLWriting(eventsLog);
//		SGBDConfig.getInstance().sendQuery("alter table `eventsLog` auto_increment =1");
		SGBDConfig.getInstance().sendQuery(query);
		if(eventsLog.getId() == 0) {
			ResultSet data = SGBDConfig.getInstance().sendQueryForResults("select id from `eventsLog` order by id desc limit 1");
			try{
				if (data.next()){
					eventsLog.setId(data.getInt("id"));
				}
			} catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
		}
		return eventsLog.getId();
	}

	public static int writeByPreparedStatement(EventsLog eventsLog) {
		utils.SGBDConfig.InsertUpdateSQLQueries writingQuerie = getSQLWritingByPreparedStatement(eventsLog);
//		SGBDConfig.getInstance().sendQuery("alter table `eventsLog` auto_increment =1");
		utils.SGBDConfig.getInstance().sendQueriesByPreparedStatement(writingQuerie);
		
		if(eventsLog.getId().intValue() == 0) {
			ResultSet data = SGBDConfig.getInstance().sendQueryForResults("select id from `eventsLog` order by id desc limit 1");
			try{
				if (data.next()){
					eventsLog.setId(data.getInt("id"));
				}
			} catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
		}
		return eventsLog.getId();
	}

	public static void delete(EventsLog eventsLog){
		SGBDConfig.getInstance().sendQuery(getSQLDeleting(eventsLog));
	}

	public static void deleteAll(){
		SGBDConfig.getInstance().sendQuery(getSQLDeleting());
	}

	public static java.util.List<EventsLog> getListInstances(){
		return getListInstances("");
	}

	public static java.util.List<EventsLog> getListInstances(String whereCondition){
		String query = "select * from eventsLog";
		if (!whereCondition.trim().equals("")){
			if (!whereCondition.trim().equals("") && !whereCondition.trim().toLowerCase().startsWith("where")){
				query += " where ";
			}
			query += " "+whereCondition;
		}
		return getListInstancesBySQLQuery(query);
	}

	public static java.util.List<EventsLog> getListInstancesBySQLQuery(String sqlQuery){
		java.util.List<EventsLog> list = new java.util.ArrayList<EventsLog>();
		
		Connection connection = SGBDConfig.getInstance().getConnexionWithoutSaving();
		ResultSet data = SGBDConfig.getInstance().sendQueryForResults(sqlQuery, connection);
		try{
			while (data.next()){
				EventsLog item = new EventsLog();
				item.setId(data.getInt("id"));
				item.setEvenement(data.getString("evenement"));
				item.setIdConnexionsLog(data.getInt("idConnexionsLog"));
				item.setDate(data.getTimestamp("date"));
				
				if (item.getIdConnexionsLog() != null && item.getIdConnexionsLog().intValue() > 0){
					item.setConnexionsLog( DAOConnexionsLog.getConnexionsLog(item.getIdConnexionsLog().intValue(), connection) );
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

	public static String getSQLUpdateForEvenement(EventsLog eventsLog){
		String sqlQuery ="update `eventsLog` SET ";
		sqlQuery += "`evenement`='"+utils.StringUtils.addSQLSlashes(eventsLog.getEvenement())+"'";
		sqlQuery += "where id = "+eventsLog.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForEvenementByPreparedStatement(EventsLog eventsLog){
		String query = "update `eventsLog` set ";
		query += "`evenement` = ? ";
		query += "where id = "+eventsLog.getId().intValue();
		
		try{
			Object[] params = {eventsLog.getEvenement().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForIdConnexionsLog(EventsLog eventsLog){
		String sqlQuery ="update `eventsLog` SET ";
		sqlQuery += "`idConnexionsLog`="+(eventsLog.getIdConnexionsLog() > 0 ? "'"+eventsLog.getIdConnexionsLog()+"' " : "NULL ");
		sqlQuery += "where id = "+eventsLog.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIdConnexionsLogByPreparedStatement(EventsLog eventsLog){
		String query = "update `eventsLog` set ";
		query += "`idConnexionsLog` = ? ";
		query += "where id = "+eventsLog.getId().intValue();
		
		try{
			Object[] params = {eventsLog.getIdConnexionsLog() > 0 ? eventsLog.getIdConnexionsLog() : null};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForDate(EventsLog eventsLog){
		String sqlQuery ="update `eventsLog` SET ";
		sqlQuery += "`date`='"+eventsLog.getDate()+"'";
		sqlQuery += "where id = "+eventsLog.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForDateByPreparedStatement(EventsLog eventsLog){
		String query = "update `eventsLog` set ";
		query += "`date` = ? ";
		query += "where id = "+eventsLog.getId().intValue();
		
		try{
			Object[] params = {eventsLog.getDate().toString()};
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
			ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("select group_concat(id) from `eventsLog` "+whereCondition);
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
			ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("select count(id) from `eventsLog` "+whereCondition);
			if (data.next())
				return data.getInt(1);
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		return 0;
	}

	public static String getSQLTuple(EventsLog eventsLog){
		String sqlTuple = "";
		sqlTuple += "INSERT INTO `eventsLog` ( `id`, `evenement`, `idConnexionsLog`, `date`) VALUES";
		sqlTuple += "( '"+eventsLog.getId()+"', '"+utils.StringUtils.addSQLSlashes(eventsLog.getEvenement())+"', "+((eventsLog.getIdConnexionsLog() == 0) ? "NULL" : "'"+eventsLog.getIdConnexionsLog()+"'")+", '"+eventsLog.getDate()+"');\n";

		return sqlTuple;
	}

	public static String getSQLStructure(boolean foreignKeyCheck, boolean addDropTable){
		String sqlStruct = "";
		if (foreignKeyCheck){
			sqlStruct += "SET FOREIGN_KEY_CHECKS=0;\n";
		}
		sqlStruct += "SET SQL_MODE=\"NO_AUTO_VALUE_ON_ZERO\";\n\n";
		
		if (addDropTable){
			sqlStruct += "DROP TABLE IF EXISTS `eventsLog`;\n";
		}

		sqlStruct += "CREATE TABLE IF NOT EXISTS `eventsLog` (\n";
		sqlStruct += "  `id` int(11) NOT NULL AUTO_INCREMENT,\n";
		sqlStruct += "  `evenement` text NOT NULL,\n";
		sqlStruct += "  `idConnexionsLog` int(11) NOT NULL,\n";
		sqlStruct += "  `date` datetime NOT NULL,\n";
		sqlStruct += "  PRIMARY KEY (`id`),\n";
		sqlStruct += "  KEY `idConnexionsLog` (`idConnexionsLog`)\n";
		sqlStruct += ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='Journal des Évènements' AUTO_INCREMENT=1 ;\n";

		sqlStruct += "ALTER TABLE `eventsLog`\n";
		sqlStruct += "  ADD CONSTRAINT `eventsLog_ibfk_1` FOREIGN KEY (`idConnexionsLog`) REFERENCES `connexionsLog` (`id`);\n";
		

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

	public static String getSQLContent(java.util.List<EventsLog> list, boolean foreignKeyCheck, boolean addDropTable){
		String sqlContent = getSQLStructure(foreignKeyCheck, addDropTable);
		
		sqlContent += "\n\n";
		for (EventsLog item : list){
			sqlContent += getSQLTuple(item);
		}
		
		if (foreignKeyCheck){
			sqlContent = "SET FOREIGN_KEY_CHECKS=0;\n" + sqlContent + "SET FOREIGN_KEY_CHECKS=1;";
		}
		
		return sqlContent;
	}

	public static String showSQLStructure(){
		try{
			java.sql.ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("show create table `eventsLog`");
			if (data.next()){
				return data.getString(2);
			}
		}
		catch (Exception e){
		}
		
		return "";
	}

	public static String getTableName(){
		return "eventsLog";
	}
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}