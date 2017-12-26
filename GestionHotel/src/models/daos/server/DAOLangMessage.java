package models.daos.server;

import java.sql.Connection;
import java.sql.ResultSet;

import utils.SGBDConfig;

import models.beans.LangMessage;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This classe maps the Table <i><b>langMessage</b></i> in the Database. 
 *	<br/>It contains attributs which represents the columns of the table,
 *	<br/>and Methods for geting one row by id, updating, inserting and deleting rows.
 */

public abstract class DAOLangMessage {
	public static LangMessage getLangMessage(int id) {
		return getLangMessage(id, null);
	}

	public static LangMessage getLangMessage(int id, Connection connection) {
		LangMessage langMessage = new LangMessage();
		if (id == 0)
			return langMessage;
		String query = "select * from `langMessage` where `id`="+id+" limit 1";
		boolean toCloseConnection = false;
		if (connection == null){
			connection = SGBDConfig.getInstance().getConnexionWithoutSaving();
			toCloseConnection = true;
		}
		ResultSet data = SGBDConfig.getInstance().sendQueryForResults(query, connection);
		try{
			if (data.next()){
				langMessage.setId(data.getInt("id"));
				langMessage.setMessage(data.getString("message"));
			}
			
			if (toCloseConnection){
				connection.close();
			}
		}
		catch (Exception e){
			utils.StringUtils.printDebug(e);
		}

		return langMessage;
	}

	public static String getSQLDeleting(LangMessage langMessage) {
		String query = "delete from `langMessage` where `id` = "+langMessage.getId();
		return query;
	}

	public static String getSQLDeleting() {
		String query = "delete from `langMessage` where 1 ";
		return query;
	}

	public static String getSQLWriting(LangMessage langMessage) {
		String query;
		if (langMessage.getId().intValue() == 0){
			query = "insert into `langMessage` (`id`, `message`)";
			query += " values ('"+langMessage.getId()+"', '"+utils.StringUtils.addSQLSlashes(langMessage.getMessage())+"')";
		}
		else{
			query ="update `langMessage` SET ";
			query += "id='"+langMessage.getId()+"',";
			query += "`message`='"+utils.StringUtils.addSQLSlashes(langMessage.getMessage())+"'";
			query += " where `id`="+langMessage.getId();
		}
		return query;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLWritingByPreparedStatement(LangMessage langMessage) {
		String query;
		if (langMessage.getId().intValue() == 0){
			query = "insert into ";
		}
		else{
			query = "update ";
		}

		query += " `langMessage` set ";
		query += "`id` = ?, ";
		query += "`message` = ? ";

		if (langMessage.getId().intValue() > 0){
			query += " where `id` = "+langMessage.getId().intValue();
		}

		try{
			Object[] params = {langMessage.getId(), langMessage.getMessage().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static int write(LangMessage langMessage) {
		String query = getSQLWriting(langMessage);
//		SGBDConfig.getInstance().sendQuery("alter table `langMessage` auto_increment =1");
		SGBDConfig.getInstance().sendQuery(query);
		if(langMessage.getId() == 0) {
			ResultSet data = SGBDConfig.getInstance().sendQueryForResults("select id from `langMessage` order by id desc limit 1");
			try{
				if (data.next()){
					langMessage.setId(data.getInt("id"));
				}
			} catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
		}
		return langMessage.getId();
	}

	public static int writeByPreparedStatement(LangMessage langMessage) {
		utils.SGBDConfig.InsertUpdateSQLQueries writingQuerie = getSQLWritingByPreparedStatement(langMessage);
//		SGBDConfig.getInstance().sendQuery("alter table `langMessage` auto_increment =1");
		utils.SGBDConfig.getInstance().sendQueriesByPreparedStatement(writingQuerie);
		
		if(langMessage.getId().intValue() == 0) {
			ResultSet data = SGBDConfig.getInstance().sendQueryForResults("select id from `langMessage` order by id desc limit 1");
			try{
				if (data.next()){
					langMessage.setId(data.getInt("id"));
				}
			} catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
		}
		return langMessage.getId();
	}

	public static void delete(LangMessage langMessage){
		SGBDConfig.getInstance().sendQuery(getSQLDeleting(langMessage));
	}

	public static void deleteAll(){
		SGBDConfig.getInstance().sendQuery(getSQLDeleting());
	}

	public static java.util.List<LangMessage> getListInstances(){
		return getListInstances("");
	}

	public static java.util.List<LangMessage> getListInstances(String whereCondition){
		String query = "select * from langMessage";
		if (!whereCondition.trim().equals("")){
			if (!whereCondition.trim().equals("") && !whereCondition.trim().toLowerCase().startsWith("where")){
				query += " where ";
			}
			query += " "+whereCondition;
		}
		return getListInstancesBySQLQuery(query);
	}

	public static java.util.List<LangMessage> getListInstancesBySQLQuery(String sqlQuery){
		java.util.List<LangMessage> list = new java.util.ArrayList<LangMessage>();
		
		Connection connection = SGBDConfig.getInstance().getConnexionWithoutSaving();
		ResultSet data = SGBDConfig.getInstance().sendQueryForResults(sqlQuery, connection);
		try{
			while (data.next()){
				LangMessage item = new LangMessage();
				item.setId(data.getInt("id"));
				item.setMessage(data.getString("message"));
				
				
				list.add(item);
			}
			connection.close();
		}
		catch(Exception e){
			utils.StringUtils.printDebug(e);
		}
		return list;
	}

	public static String getSQLUpdateForMessage(LangMessage langMessage){
		String sqlQuery ="update `langMessage` SET ";
		sqlQuery += "`message`='"+utils.StringUtils.addSQLSlashes(langMessage.getMessage())+"'";
		sqlQuery += "where id = "+langMessage.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForMessageByPreparedStatement(LangMessage langMessage){
		String query = "update `langMessage` set ";
		query += "`message` = ? ";
		query += "where id = "+langMessage.getId().intValue();
		
		try{
			Object[] params = {langMessage.getMessage().getBytes("UTF-8")};
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
			ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("select group_concat(id) from `langMessage` "+whereCondition);
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
			ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("select count(id) from `langMessage` "+whereCondition);
			if (data.next())
				return data.getInt(1);
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		return 0;
	}

	public static String getSQLTuple(LangMessage langMessage){
		String sqlTuple = "";
		sqlTuple += "INSERT INTO `langMessage` ( `id`, `message`) VALUES";
		sqlTuple += "( '"+langMessage.getId()+"', '"+utils.StringUtils.addSQLSlashes(langMessage.getMessage())+"');\n";

		return sqlTuple;
	}

	public static String getSQLStructure(boolean foreignKeyCheck, boolean addDropTable){
		String sqlStruct = "";
		if (foreignKeyCheck){
			sqlStruct += "SET FOREIGN_KEY_CHECKS=0;\n";
		}
		sqlStruct += "SET SQL_MODE=\"NO_AUTO_VALUE_ON_ZERO\";\n\n";
		
		if (addDropTable){
			sqlStruct += "DROP TABLE IF EXISTS `langMessage`;\n";
		}

		sqlStruct += "CREATE TABLE IF NOT EXISTS `langMessage` (\n";
		sqlStruct += "  `id` int(11) NOT NULL AUTO_INCREMENT,\n";
		sqlStruct += "  `message` varchar(1000) NOT NULL COMMENT 'Message',\n";
		sqlStruct += "  PRIMARY KEY (`id`)\n";
		sqlStruct += ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='Messages En Français' AUTO_INCREMENT=1 ;\n";
		

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

	public static String getSQLContent(java.util.List<LangMessage> list, boolean foreignKeyCheck, boolean addDropTable){
		String sqlContent = getSQLStructure(foreignKeyCheck, addDropTable);
		
		sqlContent += "\n\n";
		for (LangMessage item : list){
			sqlContent += getSQLTuple(item);
		}
		
		if (foreignKeyCheck){
			sqlContent = "SET FOREIGN_KEY_CHECKS=0;\n" + sqlContent + "SET FOREIGN_KEY_CHECKS=1;";
		}
		
		return sqlContent;
	}

	public static String showSQLStructure(){
		try{
			java.sql.ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("show create table `langMessage`");
			if (data.next()){
				return data.getString(2);
			}
		}
		catch (Exception e){
		}
		
		return "";
	}

	public static String getTableName(){
		return "langMessage";
	}
	
	public static java.util.List<models.beans.Translation> getListOfTranslationsMessage(models.beans.LangMessage langMessage){
		java.util.List<models.beans.Translation> listOfTranslationsMessage = models.daos.server.DAOTranslation.getListInstances("where idMessage='"+langMessage.getId().intValue()+"'");
		return listOfTranslationsMessage;
	}
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}