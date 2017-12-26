package models.daos.server;

import java.sql.Connection;
import java.sql.ResultSet;

import utils.SGBDConfig;

import models.beans.Lang;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This classe maps the Table <i><b>lang</b></i> in the Database. 
 *	<br/>It contains attributs which represents the columns of the table,
 *	<br/>and Methods for geting one row by id, updating, inserting and deleting rows.
 */

public abstract class DAOLang {
	public static Lang getLang(int id) {
		return getLang(id, null);
	}

	public static Lang getLang(int id, Connection connection) {
		Lang lang = new Lang();
		if (id == 0)
			return lang;
		String query = "select * from `lang` where `id`="+id+" limit 1";
		boolean toCloseConnection = false;
		if (connection == null){
			connection = SGBDConfig.getInstance().getConnexionWithoutSaving();
			toCloseConnection = true;
		}
		ResultSet data = SGBDConfig.getInstance().sendQueryForResults(query, connection);
		try{
			if (data.next()){
				lang.setId(data.getInt("id"));
				lang.setLangue(data.getString("langue"));
				lang.setCodeLang(data.getString("codeLang"));
				lang.setOrientation(Lang.Orientation.getByValue(data.getString("orientation")));
			}
			
			if (toCloseConnection){
				connection.close();
			}
		}
		catch (Exception e){
			utils.StringUtils.printDebug(e);
		}

		return lang;
	}

	public static String getSQLDeleting(Lang lang) {
		String query = "delete from `lang` where `id` = "+lang.getId();
		return query;
	}

	public static String getSQLDeleting() {
		String query = "delete from `lang` where 1 ";
		return query;
	}

	public static String getSQLWriting(Lang lang) {
		String query;
		if (lang.getId().intValue() == 0){
			query = "insert into `lang` (`id`, `langue`, `codeLang`, `orientation`)";
			query += " values ('"+lang.getId()+"', '"+utils.StringUtils.addSQLSlashes(lang.getLangue())+"', '"+utils.StringUtils.addSQLSlashes(lang.getCodeLang())+"', '"+lang.getOrientation().getValue()+"')";
		}
		else{
			query ="update `lang` SET ";
			query += "id='"+lang.getId()+"',";
			query += "`langue`='"+utils.StringUtils.addSQLSlashes(lang.getLangue())+"',";
			query += "`codeLang`='"+utils.StringUtils.addSQLSlashes(lang.getCodeLang())+"',";
			query += "`orientation`='"+lang.getOrientation().getValue()+"'";
			query += " where `id`="+lang.getId();
		}
		return query;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLWritingByPreparedStatement(Lang lang) {
		String query;
		if (lang.getId().intValue() == 0){
			query = "insert into ";
		}
		else{
			query = "update ";
		}

		query += " `lang` set ";
		query += "`id` = ?, ";
		query += "`langue` = ?, ";
		query += "`codeLang` = ?, ";
		query += "`orientation` = ? ";

		if (lang.getId().intValue() > 0){
			query += " where `id` = "+lang.getId().intValue();
		}

		try{
			Object[] params = {lang.getId(), lang.getLangue().getBytes("UTF-8"), lang.getCodeLang().getBytes("UTF-8"), lang.getOrientation().getValue().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static int write(Lang lang) {
		String query = getSQLWriting(lang);
//		SGBDConfig.getInstance().sendQuery("alter table `lang` auto_increment =1");
		SGBDConfig.getInstance().sendQuery(query);
		if(lang.getId() == 0) {
			ResultSet data = SGBDConfig.getInstance().sendQueryForResults("select id from `lang` order by id desc limit 1");
			try{
				if (data.next()){
					lang.setId(data.getInt("id"));
				}
			} catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
		}
		return lang.getId();
	}

	public static int writeByPreparedStatement(Lang lang) {
		utils.SGBDConfig.InsertUpdateSQLQueries writingQuerie = getSQLWritingByPreparedStatement(lang);
//		SGBDConfig.getInstance().sendQuery("alter table `lang` auto_increment =1");
		utils.SGBDConfig.getInstance().sendQueriesByPreparedStatement(writingQuerie);
		
		if(lang.getId().intValue() == 0) {
			ResultSet data = SGBDConfig.getInstance().sendQueryForResults("select id from `lang` order by id desc limit 1");
			try{
				if (data.next()){
					lang.setId(data.getInt("id"));
				}
			} catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
		}
		return lang.getId();
	}

	public static void delete(Lang lang){
		SGBDConfig.getInstance().sendQuery(getSQLDeleting(lang));
	}

	public static void deleteAll(){
		SGBDConfig.getInstance().sendQuery(getSQLDeleting());
	}

	public static java.util.List<Lang> getListInstances(){
		return getListInstances("");
	}

	public static java.util.List<Lang> getListInstances(String whereCondition){
		String query = "select * from lang";
		if (!whereCondition.trim().equals("")){
			if (!whereCondition.trim().equals("") && !whereCondition.trim().toLowerCase().startsWith("where")){
				query += " where ";
			}
			query += " "+whereCondition;
		}
		return getListInstancesBySQLQuery(query);
	}

	public static java.util.List<Lang> getListInstancesBySQLQuery(String sqlQuery){
		java.util.List<Lang> list = new java.util.ArrayList<Lang>();
		
		Connection connection = SGBDConfig.getInstance().getConnexionWithoutSaving();
		ResultSet data = SGBDConfig.getInstance().sendQueryForResults(sqlQuery, connection);
		try{
			while (data.next()){
				Lang item = new Lang();
				item.setId(data.getInt("id"));
				item.setLangue(data.getString("langue"));
				item.setCodeLang(data.getString("codeLang"));
				item.setOrientation(Lang.Orientation.getByValue(data.getString("orientation")));
				
				
				list.add(item);
			}
			connection.close();
		}
		catch(Exception e){
			utils.StringUtils.printDebug(e);
		}
		return list;
	}

	public static String getSQLUpdateForLangue(Lang lang){
		String sqlQuery ="update `lang` SET ";
		sqlQuery += "`langue`='"+utils.StringUtils.addSQLSlashes(lang.getLangue())+"'";
		sqlQuery += "where id = "+lang.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForLangueByPreparedStatement(Lang lang){
		String query = "update `lang` set ";
		query += "`langue` = ? ";
		query += "where id = "+lang.getId().intValue();
		
		try{
			Object[] params = {lang.getLangue().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForCodeLang(Lang lang){
		String sqlQuery ="update `lang` SET ";
		sqlQuery += "`codeLang`='"+utils.StringUtils.addSQLSlashes(lang.getCodeLang())+"'";
		sqlQuery += "where id = "+lang.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForCodeLangByPreparedStatement(Lang lang){
		String query = "update `lang` set ";
		query += "`codeLang` = ? ";
		query += "where id = "+lang.getId().intValue();
		
		try{
			Object[] params = {lang.getCodeLang().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static Lang getLangByCodeLang(java.lang.String codeLang){
		String sqlQuery ="select * from `lang` where codeLang = '"+codeLang+"' limit 1";
		java.util.List<Lang> list = getListInstancesBySQLQuery(sqlQuery);
		if (list != null && list.size() == 1){
			return list.get(0);
		}
		return null;
	}

	public static String getSQLUpdateForOrientation(Lang lang){
		String sqlQuery ="update `lang` SET ";
		sqlQuery += "`orientation`='"+lang.getOrientation().getValue()+"' ";
		sqlQuery += "where id = "+lang.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForOrientationByPreparedStatement(Lang lang){
		String query = "update `lang` set ";
		query += "`orientation` = ? ";
		query += "where id = "+lang.getId().intValue();
		
		try{
			Object[] params = {lang.getOrientation().getValue().getBytes("UTF-8")};
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
			ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("select group_concat(id) from `lang` "+whereCondition);
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
			ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("select count(id) from `lang` "+whereCondition);
			if (data.next())
				return data.getInt(1);
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		return 0;
	}

	public static String getSQLTuple(Lang lang){
		String sqlTuple = "";
		sqlTuple += "INSERT INTO `lang` ( `id`, `langue`, `codeLang`, `orientation`) VALUES";
		sqlTuple += "( '"+lang.getId()+"', '"+utils.StringUtils.addSQLSlashes(lang.getLangue())+"', '"+utils.StringUtils.addSQLSlashes(lang.getCodeLang())+"', '"+utils.StringUtils.addSQLSlashes(lang.getOrientation().getValue())+"');\n";

		return sqlTuple;
	}

	public static String getSQLStructure(boolean foreignKeyCheck, boolean addDropTable){
		String sqlStruct = "";
		if (foreignKeyCheck){
			sqlStruct += "SET FOREIGN_KEY_CHECKS=0;\n";
		}
		sqlStruct += "SET SQL_MODE=\"NO_AUTO_VALUE_ON_ZERO\";\n\n";
		
		if (addDropTable){
			sqlStruct += "DROP TABLE IF EXISTS `lang`;\n";
		}

		sqlStruct += "CREATE TABLE IF NOT EXISTS `lang` (\n";
		sqlStruct += "  `id` int(11) NOT NULL AUTO_INCREMENT,\n";
		sqlStruct += "  `langue` varchar(20) NOT NULL,\n";
		sqlStruct += "  `codeLang` varchar(5) NOT NULL COMMENT 'Code de la langue',\n";
		sqlStruct += "  `orientation` enum('RTL','LTR') NOT NULL COMMENT 'Orientation',\n";
		sqlStruct += "  PRIMARY KEY (`id`),\n";
		sqlStruct += "  UNIQUE KEY `codeLang` (`codeLang`)\n";
		sqlStruct += ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='Langues' AUTO_INCREMENT=1 ;\n";
		

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

	public static String getSQLContent(java.util.List<Lang> list, boolean foreignKeyCheck, boolean addDropTable){
		String sqlContent = getSQLStructure(foreignKeyCheck, addDropTable);
		
		sqlContent += "\n\n";
		for (Lang item : list){
			sqlContent += getSQLTuple(item);
		}
		
		if (foreignKeyCheck){
			sqlContent = "SET FOREIGN_KEY_CHECKS=0;\n" + sqlContent + "SET FOREIGN_KEY_CHECKS=1;";
		}
		
		return sqlContent;
	}

	public static String showSQLStructure(){
		try{
			java.sql.ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("show create table `lang`");
			if (data.next()){
				return data.getString(2);
			}
		}
		catch (Exception e){
		}
		
		return "";
	}

	public static String getTableName(){
		return "lang";
	}
	
	public static java.util.List<models.beans.ParametresApplicationUtilisateur> getListOfParametresApplicationUtilisateursLang(models.beans.Lang lang){
		java.util.List<models.beans.ParametresApplicationUtilisateur> listOfParametresApplicationUtilisateursLang = models.daos.server.DAOParametresApplicationUtilisateur.getListInstances("where idLang='"+lang.getId().intValue()+"'");
		return listOfParametresApplicationUtilisateursLang;
	}
	
	public static java.util.List<models.beans.Translation> getListOfTranslationsLang(models.beans.Lang lang){
		java.util.List<models.beans.Translation> listOfTranslationsLang = models.daos.server.DAOTranslation.getListInstances("where idLang='"+lang.getId().intValue()+"'");
		return listOfTranslationsLang;
	}
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}