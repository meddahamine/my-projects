package models.daos.server;

import java.sql.Connection;
import java.sql.ResultSet;

import utils.SGBDConfig;

import models.beans.Translation;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This classe maps the Table <i><b>translation</b></i> in the Database. 
 *	<br/>It contains attributs which represents the columns of the table,
 *	<br/>and Methods for geting one row by id, updating, inserting and deleting rows.
 */

public abstract class DAOTranslation {
	public static Translation getTranslation(int id) {
		return getTranslation(id, null);
	}

	public static Translation getTranslation(int id, Connection connection) {
		Translation translation = new Translation();
		if (id == 0)
			return translation;
		String query = "select * from `translation` where `id`="+id+" limit 1";
		boolean toCloseConnection = false;
		if (connection == null){
			connection = SGBDConfig.getInstance().getConnexionWithoutSaving();
			toCloseConnection = true;
		}
		ResultSet data = SGBDConfig.getInstance().sendQueryForResults(query, connection);
		try{
			if (data.next()){
				translation.setId(data.getInt("id"));
				translation.setIdLang(data.getInt("idLang"));
				translation.setIdMessage(data.getInt("idMessage"));
				translation.setTraduction(data.getString("traduction"));
			}
			
			if (translation.getIdLang() != null && translation.getIdLang().intValue() > 0){
				translation.setLang(DAOLang.getLang(translation.getIdLang(), connection));
			}
			if (translation.getIdMessage() != null && translation.getIdMessage().intValue() > 0){
				translation.setMessage(DAOLangMessage.getLangMessage(translation.getIdMessage(), connection));
			}
			if (toCloseConnection){
				connection.close();
			}
		}
		catch (Exception e){
			utils.StringUtils.printDebug(e);
		}

		return translation;
	}

	public static String getSQLDeleting(Translation translation) {
		String query = "delete from `translation` where `id` = "+translation.getId();
		return query;
	}

	public static String getSQLDeleting() {
		String query = "delete from `translation` where 1 ";
		return query;
	}

	public static String getSQLWriting(Translation translation) {
		String query;
		if (translation.getId().intValue() == 0){
			query = "insert into `translation` (`id`, `idLang`, `idMessage`, `traduction`)";
			query += " values ('"+translation.getId()+"', "+ ((translation.getIdLang() > 0) ? "'"+translation.getIdLang()+"'" : "NULL" )+", "+ ((translation.getIdMessage() > 0) ? "'"+translation.getIdMessage()+"'" : "NULL" )+", '"+utils.StringUtils.addSQLSlashes(translation.getTraduction())+"')";
		}
		else{
			query ="update `translation` SET ";
			query += "id='"+translation.getId()+"',";
			query += "`idLang`="+((translation.getIdLang() > 0) ? "'"+translation.getIdLang()+"'" : "NULL")+",";
			query += "`idMessage`="+((translation.getIdMessage() > 0) ? "'"+translation.getIdMessage()+"'" : "NULL")+",";
			query += "`traduction`='"+utils.StringUtils.addSQLSlashes(translation.getTraduction())+"'";
			query += " where `id`="+translation.getId();
		}
		return query;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLWritingByPreparedStatement(Translation translation) {
		String query;
		if (translation.getId().intValue() == 0){
			query = "insert into ";
		}
		else{
			query = "update ";
		}

		query += " `translation` set ";
		query += "`id` = ?, ";
		query += "`idLang` = ?, ";
		query += "`idMessage` = ?, ";
		query += "`traduction` = ? ";

		if (translation.getId().intValue() > 0){
			query += " where `id` = "+translation.getId().intValue();
		}

		try{
			Object[] params = {translation.getId(), translation.getIdLang() == 0 ? null : translation.getIdLang(), translation.getIdMessage() == 0 ? null : translation.getIdMessage(), translation.getTraduction().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static int write(Translation translation) {
		String query = getSQLWriting(translation);
//		SGBDConfig.getInstance().sendQuery("alter table `translation` auto_increment =1");
		SGBDConfig.getInstance().sendQuery(query);
		if(translation.getId() == 0) {
			ResultSet data = SGBDConfig.getInstance().sendQueryForResults("select id from `translation` order by id desc limit 1");
			try{
				if (data.next()){
					translation.setId(data.getInt("id"));
				}
			} catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
		}
		return translation.getId();
	}

	public static int writeByPreparedStatement(Translation translation) {
		utils.SGBDConfig.InsertUpdateSQLQueries writingQuerie = getSQLWritingByPreparedStatement(translation);
//		SGBDConfig.getInstance().sendQuery("alter table `translation` auto_increment =1");
		utils.SGBDConfig.getInstance().sendQueriesByPreparedStatement(writingQuerie);
		
		if(translation.getId().intValue() == 0) {
			ResultSet data = SGBDConfig.getInstance().sendQueryForResults("select id from `translation` order by id desc limit 1");
			try{
				if (data.next()){
					translation.setId(data.getInt("id"));
				}
			} catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
		}
		return translation.getId();
	}

	public static void delete(Translation translation){
		SGBDConfig.getInstance().sendQuery(getSQLDeleting(translation));
	}

	public static void deleteAll(){
		SGBDConfig.getInstance().sendQuery(getSQLDeleting());
	}

	public static java.util.List<Translation> getListInstances(){
		return getListInstances("");
	}

	public static java.util.List<Translation> getListInstances(String whereCondition){
		String query = "select * from translation";
		if (!whereCondition.trim().equals("")){
			if (!whereCondition.trim().equals("") && !whereCondition.trim().toLowerCase().startsWith("where")){
				query += " where ";
			}
			query += " "+whereCondition;
		}
		return getListInstancesBySQLQuery(query);
	}

	public static java.util.List<Translation> getListInstancesBySQLQuery(String sqlQuery){
		java.util.List<Translation> list = new java.util.ArrayList<Translation>();
		
		Connection connection = SGBDConfig.getInstance().getConnexionWithoutSaving();
		ResultSet data = SGBDConfig.getInstance().sendQueryForResults(sqlQuery, connection);
		try{
			while (data.next()){
				Translation item = new Translation();
				item.setId(data.getInt("id"));
				item.setIdLang(data.getInt("idLang"));
				item.setIdMessage(data.getInt("idMessage"));
				item.setTraduction(data.getString("traduction"));
				
				if (item.getIdLang() != null && item.getIdLang().intValue() > 0){
					item.setLang( DAOLang.getLang(item.getIdLang().intValue(), connection) );
				}
				if (item.getIdMessage() != null && item.getIdMessage().intValue() > 0){
					item.setMessage( DAOLangMessage.getLangMessage(item.getIdMessage().intValue(), connection) );
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

	public static String getSQLUpdateForIdLang(Translation translation){
		String sqlQuery ="update `translation` SET ";
		sqlQuery += "`idLang`="+(translation.getIdLang() > 0 ? "'"+translation.getIdLang()+"' " : "NULL ");
		sqlQuery += "where id = "+translation.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIdLangByPreparedStatement(Translation translation){
		String query = "update `translation` set ";
		query += "`idLang` = ? ";
		query += "where id = "+translation.getId().intValue();
		
		try{
			Object[] params = {translation.getIdLang() > 0 ? translation.getIdLang() : null};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForIdMessage(Translation translation){
		String sqlQuery ="update `translation` SET ";
		sqlQuery += "`idMessage`="+(translation.getIdMessage() > 0 ? "'"+translation.getIdMessage()+"' " : "NULL ");
		sqlQuery += "where id = "+translation.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIdMessageByPreparedStatement(Translation translation){
		String query = "update `translation` set ";
		query += "`idMessage` = ? ";
		query += "where id = "+translation.getId().intValue();
		
		try{
			Object[] params = {translation.getIdMessage() > 0 ? translation.getIdMessage() : null};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForTraduction(Translation translation){
		String sqlQuery ="update `translation` SET ";
		sqlQuery += "`traduction`='"+utils.StringUtils.addSQLSlashes(translation.getTraduction())+"'";
		sqlQuery += "where id = "+translation.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForTraductionByPreparedStatement(Translation translation){
		String query = "update `translation` set ";
		query += "`traduction` = ? ";
		query += "where id = "+translation.getId().intValue();
		
		try{
			Object[] params = {translation.getTraduction().getBytes("UTF-8")};
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
			ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("select group_concat(id) from `translation` "+whereCondition);
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
			ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("select count(id) from `translation` "+whereCondition);
			if (data.next())
				return data.getInt(1);
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		return 0;
	}

	public static String getSQLTuple(Translation translation){
		String sqlTuple = "";
		sqlTuple += "INSERT INTO `translation` ( `id`, `idLang`, `idMessage`, `traduction`) VALUES";
		sqlTuple += "( '"+translation.getId()+"', "+((translation.getIdLang() == 0) ? "NULL" : "'"+translation.getIdLang()+"'")+", "+((translation.getIdMessage() == 0) ? "NULL" : "'"+translation.getIdMessage()+"'")+", '"+utils.StringUtils.addSQLSlashes(translation.getTraduction())+"');\n";

		return sqlTuple;
	}

	public static String getSQLStructure(boolean foreignKeyCheck, boolean addDropTable){
		String sqlStruct = "";
		if (foreignKeyCheck){
			sqlStruct += "SET FOREIGN_KEY_CHECKS=0;\n";
		}
		sqlStruct += "SET SQL_MODE=\"NO_AUTO_VALUE_ON_ZERO\";\n\n";
		
		if (addDropTable){
			sqlStruct += "DROP TABLE IF EXISTS `translation`;\n";
		}

		sqlStruct += "CREATE TABLE IF NOT EXISTS `translation` (\n";
		sqlStruct += "  `id` int(11) NOT NULL AUTO_INCREMENT,\n";
		sqlStruct += "  `idLang` int(11) NOT NULL DEFAULT '1' COMMENT 'Langues de Translation',\n";
		sqlStruct += "  `idMessage` int(11) NOT NULL COMMENT 'Text',\n";
		sqlStruct += "  `traduction` varchar(1000) NOT NULL COMMENT 'Traduction',\n";
		sqlStruct += "  PRIMARY KEY (`id`),\n";
		sqlStruct += "  KEY `idLang` (`idLang`),\n";
		sqlStruct += "  KEY `idMessage` (`idMessage`)\n";
		sqlStruct += ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='Traduction des textes' AUTO_INCREMENT=1 ;\n";

		sqlStruct += "ALTER TABLE `translation`\n";
		sqlStruct += "  ADD CONSTRAINT `translation_ibfk_1` FOREIGN KEY (`idLang`) REFERENCES `lang` (`id`),\n";
		sqlStruct += "  ADD CONSTRAINT `translation_ibfk_2` FOREIGN KEY (`idMessage`) REFERENCES `langMessage` (`id`);\n";
		

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

	public static String getSQLContent(java.util.List<Translation> list, boolean foreignKeyCheck, boolean addDropTable){
		String sqlContent = getSQLStructure(foreignKeyCheck, addDropTable);
		
		sqlContent += "\n\n";
		for (Translation item : list){
			sqlContent += getSQLTuple(item);
		}
		
		if (foreignKeyCheck){
			sqlContent = "SET FOREIGN_KEY_CHECKS=0;\n" + sqlContent + "SET FOREIGN_KEY_CHECKS=1;";
		}
		
		return sqlContent;
	}

	public static String showSQLStructure(){
		try{
			java.sql.ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("show create table `translation`");
			if (data.next()){
				return data.getString(2);
			}
		}
		catch (Exception e){
		}
		
		return "";
	}

	public static String getTableName(){
		return "translation";
	}
	
	public static String translate(String text, String lang){
		if (lang == null || lang.equals("")){
			return text;
		}
		
		java.util.List<models.beans.LangMessage> messages = DAOLangMessage.getListInstances(" where message = '"+text+"' limit 1");
		models.beans.LangMessage message;
		if (messages == null || messages.size() == 0){
			message = new models.beans.LangMessage();
			message.setMessage(text);
			DAOLangMessage.write(message);
		}
		else{
			message = messages.get(0);
		}
		
		models.beans.Lang langItem = DAOLang.getLangByCodeLang(lang);
		java.util.List<models.beans.Translation> translations = DAOTranslation.getListInstances(" where idMessage = '"+message.getId().intValue()+"' and idLang='"+langItem.getId().intValue()+"' limit 1");
		
		if (translations == null || translations.size() != 1){
			models.beans.Translation newTranslation = new models.beans.Translation();
			newTranslation.setIdMessage(message.getId().intValue());
			newTranslation.setIdLang(langItem.getId().intValue());
			
			DAOTranslation.write(newTranslation);
			return text;
		}
		else{
			return translations.get(0).getTraduction().toString();
		}
	}
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}