package models.daos.server;

import java.sql.Connection;
import java.sql.ResultSet;

import utils.SGBDConfig;

import models.beans.ParametresApplicationUtilisateur;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This classe maps the Table <i><b>parametresApplicationUtilisateur</b></i> in the Database. 
 *	<br/>It contains attributs which represents the columns of the table,
 *	<br/>and Methods for geting one row by id, updating, inserting and deleting rows.
 */

public abstract class DAOParametresApplicationUtilisateur {
	public static ParametresApplicationUtilisateur getParametresApplicationUtilisateur(int id) {
		return getParametresApplicationUtilisateur(id, null);
	}

	public static ParametresApplicationUtilisateur getParametresApplicationUtilisateur(int id, Connection connection) {
		ParametresApplicationUtilisateur parametresApplicationUtilisateur = new ParametresApplicationUtilisateur();
		if (id == 0)
			return parametresApplicationUtilisateur;
		String query = "select * from `parametresApplicationUtilisateur` where `id`="+id+" limit 1";
		boolean toCloseConnection = false;
		if (connection == null){
			connection = SGBDConfig.getInstance().getConnexionWithoutSaving();
			toCloseConnection = true;
		}
		ResultSet data = SGBDConfig.getInstance().sendQueryForResults(query, connection);
		try{
			if (data.next()){
				parametresApplicationUtilisateur.setId(data.getInt("id"));
				parametresApplicationUtilisateur.setPeriodeNotification(data.getInt("periodeNotification"));
				parametresApplicationUtilisateur.setVisibilityOfNotification(data.getBoolean("visibilityOfNotification"));
				parametresApplicationUtilisateur.setVisibilityOfMainToolBar(data.getBoolean("visibilityOfMainToolBar"));
				parametresApplicationUtilisateur.setIdLang(data.getInt("idLang"));
				parametresApplicationUtilisateur.setLookAndFeel(data.getString("lookAndFeel"));
			}
			
			if (parametresApplicationUtilisateur.getIdLang() != null && parametresApplicationUtilisateur.getIdLang().intValue() > 0){
				parametresApplicationUtilisateur.setLang(DAOLang.getLang(parametresApplicationUtilisateur.getIdLang(), connection));
			}
			if (toCloseConnection){
				connection.close();
			}
		}
		catch (Exception e){
			utils.StringUtils.printDebug(e);
		}

		return parametresApplicationUtilisateur;
	}

	public static String getSQLDeleting(ParametresApplicationUtilisateur parametresApplicationUtilisateur) {
		String query = "delete from `parametresApplicationUtilisateur` where `id` = "+parametresApplicationUtilisateur.getId();
		return query;
	}

	public static String getSQLDeleting() {
		String query = "delete from `parametresApplicationUtilisateur` where 1 ";
		return query;
	}

	public static String getSQLWriting(ParametresApplicationUtilisateur parametresApplicationUtilisateur) {
		String query;
		if (parametresApplicationUtilisateur.getId().intValue() == 0){
			query = "insert into `parametresApplicationUtilisateur` (`id`, `periodeNotification`, `visibilityOfNotification`, `visibilityOfMainToolBar`, `idLang`, `lookAndFeel`)";
			query += " values ('"+parametresApplicationUtilisateur.getId()+"', '"+parametresApplicationUtilisateur.getPeriodeNotification()+"', '"+(parametresApplicationUtilisateur.isVisibilityOfNotification() ? 1 : 0)+"', '"+(parametresApplicationUtilisateur.isVisibilityOfMainToolBar() ? 1 : 0)+"', "+ ((parametresApplicationUtilisateur.getIdLang() > 0) ? "'"+parametresApplicationUtilisateur.getIdLang()+"'" : "NULL" )+", '"+utils.StringUtils.addSQLSlashes(parametresApplicationUtilisateur.getLookAndFeel())+"')";
		}
		else{
			query ="update `parametresApplicationUtilisateur` SET ";
			query += "id='"+parametresApplicationUtilisateur.getId()+"',";
			query += "periodeNotification='"+parametresApplicationUtilisateur.getPeriodeNotification()+"',";
			query += "`visibilityOfNotification`="+(parametresApplicationUtilisateur.isVisibilityOfNotification() ? 1 : 0)+",";
			query += "`visibilityOfMainToolBar`="+(parametresApplicationUtilisateur.isVisibilityOfMainToolBar() ? 1 : 0)+",";
			query += "`idLang`="+((parametresApplicationUtilisateur.getIdLang() > 0) ? "'"+parametresApplicationUtilisateur.getIdLang()+"'" : "NULL")+",";
			query += "`lookAndFeel`='"+utils.StringUtils.addSQLSlashes(parametresApplicationUtilisateur.getLookAndFeel())+"'";
			query += " where `id`="+parametresApplicationUtilisateur.getId();
		}
		return query;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLWritingByPreparedStatement(ParametresApplicationUtilisateur parametresApplicationUtilisateur) {
		String query;
		if (parametresApplicationUtilisateur.getId().intValue() == 0){
			query = "insert into ";
		}
		else{
			query = "update ";
		}

		query += " `parametresApplicationUtilisateur` set ";
		query += "`id` = ?, ";
		query += "`periodeNotification` = ?, ";
		query += "`visibilityOfNotification` = ?, ";
		query += "`visibilityOfMainToolBar` = ?, ";
		query += "`idLang` = ?, ";
		query += "`lookAndFeel` = ? ";

		if (parametresApplicationUtilisateur.getId().intValue() > 0){
			query += " where `id` = "+parametresApplicationUtilisateur.getId().intValue();
		}

		try{
			Object[] params = {parametresApplicationUtilisateur.getId(), parametresApplicationUtilisateur.getPeriodeNotification(), parametresApplicationUtilisateur.isVisibilityOfNotification() == true ? 1 : 0, parametresApplicationUtilisateur.isVisibilityOfMainToolBar() == true ? 1 : 0, parametresApplicationUtilisateur.getIdLang() == 0 ? null : parametresApplicationUtilisateur.getIdLang(), parametresApplicationUtilisateur.getLookAndFeel().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static int write(ParametresApplicationUtilisateur parametresApplicationUtilisateur) {
		String query = getSQLWriting(parametresApplicationUtilisateur);
//		SGBDConfig.getInstance().sendQuery("alter table `parametresApplicationUtilisateur` auto_increment =1");
		SGBDConfig.getInstance().sendQuery(query);
		if(parametresApplicationUtilisateur.getId() == 0) {
			ResultSet data = SGBDConfig.getInstance().sendQueryForResults("select id from `parametresApplicationUtilisateur` order by id desc limit 1");
			try{
				if (data.next()){
					parametresApplicationUtilisateur.setId(data.getInt("id"));
				}
			} catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
		}
		return parametresApplicationUtilisateur.getId();
	}

	public static int writeByPreparedStatement(ParametresApplicationUtilisateur parametresApplicationUtilisateur) {
		utils.SGBDConfig.InsertUpdateSQLQueries writingQuerie = getSQLWritingByPreparedStatement(parametresApplicationUtilisateur);
//		SGBDConfig.getInstance().sendQuery("alter table `parametresApplicationUtilisateur` auto_increment =1");
		utils.SGBDConfig.getInstance().sendQueriesByPreparedStatement(writingQuerie);
		
		if(parametresApplicationUtilisateur.getId().intValue() == 0) {
			ResultSet data = SGBDConfig.getInstance().sendQueryForResults("select id from `parametresApplicationUtilisateur` order by id desc limit 1");
			try{
				if (data.next()){
					parametresApplicationUtilisateur.setId(data.getInt("id"));
				}
			} catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
		}
		return parametresApplicationUtilisateur.getId();
	}

	public static void delete(ParametresApplicationUtilisateur parametresApplicationUtilisateur){
		SGBDConfig.getInstance().sendQuery(getSQLDeleting(parametresApplicationUtilisateur));
	}

	public static void deleteAll(){
		SGBDConfig.getInstance().sendQuery(getSQLDeleting());
	}

	public static java.util.List<ParametresApplicationUtilisateur> getListInstances(){
		return getListInstances("");
	}

	public static java.util.List<ParametresApplicationUtilisateur> getListInstances(String whereCondition){
		String query = "select * from parametresApplicationUtilisateur";
		if (!whereCondition.trim().equals("")){
			if (!whereCondition.trim().equals("") && !whereCondition.trim().toLowerCase().startsWith("where")){
				query += " where ";
			}
			query += " "+whereCondition;
		}
		return getListInstancesBySQLQuery(query);
	}

	public static java.util.List<ParametresApplicationUtilisateur> getListInstancesBySQLQuery(String sqlQuery){
		java.util.List<ParametresApplicationUtilisateur> list = new java.util.ArrayList<ParametresApplicationUtilisateur>();
		
		Connection connection = SGBDConfig.getInstance().getConnexionWithoutSaving();
		ResultSet data = SGBDConfig.getInstance().sendQueryForResults(sqlQuery, connection);
		try{
			while (data.next()){
				ParametresApplicationUtilisateur item = new ParametresApplicationUtilisateur();
				item.setId(data.getInt("id"));
				item.setPeriodeNotification(data.getInt("periodeNotification"));
				item.setVisibilityOfNotification(data.getBoolean("visibilityOfNotification"));
				item.setVisibilityOfMainToolBar(data.getBoolean("visibilityOfMainToolBar"));
				item.setIdLang(data.getInt("idLang"));
				item.setLookAndFeel(data.getString("lookAndFeel"));
				
				if (item.getIdLang() != null && item.getIdLang().intValue() > 0){
					item.setLang( DAOLang.getLang(item.getIdLang().intValue(), connection) );
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

	public static String getSQLUpdateForPeriodeNotification(ParametresApplicationUtilisateur parametresApplicationUtilisateur){
		String sqlQuery ="update `parametresApplicationUtilisateur` SET ";
		sqlQuery += "`periodeNotification`='"+parametresApplicationUtilisateur.getPeriodeNotification()+"'";
		sqlQuery += "where id = "+parametresApplicationUtilisateur.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForPeriodeNotificationByPreparedStatement(ParametresApplicationUtilisateur parametresApplicationUtilisateur){
		String query = "update `parametresApplicationUtilisateur` set ";
		query += "`periodeNotification` = ? ";
		query += "where id = "+parametresApplicationUtilisateur.getId().intValue();
		
		try{
			Object[] params = {parametresApplicationUtilisateur.getPeriodeNotification()};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForVisibilityOfNotification(ParametresApplicationUtilisateur parametresApplicationUtilisateur){
		String sqlQuery ="update `parametresApplicationUtilisateur` SET ";
		sqlQuery += "`visibilityOfNotification`="+(parametresApplicationUtilisateur.isVisibilityOfNotification() ? 1 : 0)+" ";
		sqlQuery += "where id = "+parametresApplicationUtilisateur.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForVisibilityOfNotificationByPreparedStatement(ParametresApplicationUtilisateur parametresApplicationUtilisateur){
		String query = "update `parametresApplicationUtilisateur` set ";
		query += "`visibilityOfNotification` = ? ";
		query += "where id = "+parametresApplicationUtilisateur.getId().intValue();
		
		try{
			Object[] params = {parametresApplicationUtilisateur.isVisibilityOfNotification()};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForVisibilityOfMainToolBar(ParametresApplicationUtilisateur parametresApplicationUtilisateur){
		String sqlQuery ="update `parametresApplicationUtilisateur` SET ";
		sqlQuery += "`visibilityOfMainToolBar`="+(parametresApplicationUtilisateur.isVisibilityOfMainToolBar() ? 1 : 0)+" ";
		sqlQuery += "where id = "+parametresApplicationUtilisateur.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForVisibilityOfMainToolBarByPreparedStatement(ParametresApplicationUtilisateur parametresApplicationUtilisateur){
		String query = "update `parametresApplicationUtilisateur` set ";
		query += "`visibilityOfMainToolBar` = ? ";
		query += "where id = "+parametresApplicationUtilisateur.getId().intValue();
		
		try{
			Object[] params = {parametresApplicationUtilisateur.isVisibilityOfMainToolBar()};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForIdLang(ParametresApplicationUtilisateur parametresApplicationUtilisateur){
		String sqlQuery ="update `parametresApplicationUtilisateur` SET ";
		sqlQuery += "`idLang`="+(parametresApplicationUtilisateur.getIdLang() > 0 ? "'"+parametresApplicationUtilisateur.getIdLang()+"' " : "NULL ");
		sqlQuery += "where id = "+parametresApplicationUtilisateur.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIdLangByPreparedStatement(ParametresApplicationUtilisateur parametresApplicationUtilisateur){
		String query = "update `parametresApplicationUtilisateur` set ";
		query += "`idLang` = ? ";
		query += "where id = "+parametresApplicationUtilisateur.getId().intValue();
		
		try{
			Object[] params = {parametresApplicationUtilisateur.getIdLang() > 0 ? parametresApplicationUtilisateur.getIdLang() : null};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForLookAndFeel(ParametresApplicationUtilisateur parametresApplicationUtilisateur){
		String sqlQuery ="update `parametresApplicationUtilisateur` SET ";
		sqlQuery += "`lookAndFeel`='"+utils.StringUtils.addSQLSlashes(parametresApplicationUtilisateur.getLookAndFeel())+"'";
		sqlQuery += "where id = "+parametresApplicationUtilisateur.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForLookAndFeelByPreparedStatement(ParametresApplicationUtilisateur parametresApplicationUtilisateur){
		String query = "update `parametresApplicationUtilisateur` set ";
		query += "`lookAndFeel` = ? ";
		query += "where id = "+parametresApplicationUtilisateur.getId().intValue();
		
		try{
			Object[] params = {parametresApplicationUtilisateur.getLookAndFeel().getBytes("UTF-8")};
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
			ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("select group_concat(id) from `parametresApplicationUtilisateur` "+whereCondition);
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
			ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("select count(id) from `parametresApplicationUtilisateur` "+whereCondition);
			if (data.next())
				return data.getInt(1);
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		return 0;
	}

	public static String getSQLTuple(ParametresApplicationUtilisateur parametresApplicationUtilisateur){
		String sqlTuple = "";
		sqlTuple += "INSERT INTO `parametresApplicationUtilisateur` ( `id`, `periodeNotification`, `visibilityOfNotification`, `visibilityOfMainToolBar`, `idLang`, `lookAndFeel`) VALUES";
		sqlTuple += "( '"+parametresApplicationUtilisateur.getId()+"', '"+parametresApplicationUtilisateur.getPeriodeNotification()+"', '"+(parametresApplicationUtilisateur.isVisibilityOfNotification() ? 1 : 0)+"', '"+(parametresApplicationUtilisateur.isVisibilityOfMainToolBar() ? 1 : 0)+"', "+((parametresApplicationUtilisateur.getIdLang() == 0) ? "NULL" : "'"+parametresApplicationUtilisateur.getIdLang()+"'")+", '"+utils.StringUtils.addSQLSlashes(parametresApplicationUtilisateur.getLookAndFeel())+"');\n";

		return sqlTuple;
	}

	public static String getSQLStructure(boolean foreignKeyCheck, boolean addDropTable){
		String sqlStruct = "";
		if (foreignKeyCheck){
			sqlStruct += "SET FOREIGN_KEY_CHECKS=0;\n";
		}
		sqlStruct += "SET SQL_MODE=\"NO_AUTO_VALUE_ON_ZERO\";\n\n";
		
		if (addDropTable){
			sqlStruct += "DROP TABLE IF EXISTS `parametresApplicationUtilisateur`;\n";
		}

		sqlStruct += "CREATE TABLE IF NOT EXISTS `parametresApplicationUtilisateur` (\n";
		sqlStruct += "  `id` int(11) NOT NULL,\n";
		sqlStruct += "  `periodeNotification` int(11) NOT NULL DEFAULT '5' COMMENT 'Période de Notification (Seconds)',\n";
		sqlStruct += "  `visibilityOfNotification` tinyint(1) NOT NULL DEFAULT '1' COMMENT 'Barre de Notification',\n";
		sqlStruct += "  `visibilityOfMainToolBar` tinyint(1) NOT NULL DEFAULT '1' COMMENT 'Barre d''Outil Principale',\n";
		sqlStruct += "  `idLang` int(11) DEFAULT NULL COMMENT 'Language de l''Interface',\n";
		sqlStruct += "  `lookAndFeel` varchar(250) NOT NULL DEFAULT 'com.sun.java.swing.plaf.motif.MotifLookAndFeel',\n";
		sqlStruct += "  PRIMARY KEY (`id`),\n";
		sqlStruct += "  KEY `idLang` (`idLang`)\n";
		sqlStruct += ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='Paramètres de l''Utilisateur' AUTO_INCREMENT=1 ;\n";

		sqlStruct += "ALTER TABLE `parametresApplicationUtilisateur`\n";
		sqlStruct += "  ADD CONSTRAINT `parametresApplicationUtilisateur_ibfk_1` FOREIGN KEY (`idLang`) REFERENCES `lang` (`id`);\n";
		

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

	public static String getSQLContent(java.util.List<ParametresApplicationUtilisateur> list, boolean foreignKeyCheck, boolean addDropTable){
		String sqlContent = getSQLStructure(foreignKeyCheck, addDropTable);
		
		sqlContent += "\n\n";
		for (ParametresApplicationUtilisateur item : list){
			sqlContent += getSQLTuple(item);
		}
		
		if (foreignKeyCheck){
			sqlContent = "SET FOREIGN_KEY_CHECKS=0;\n" + sqlContent + "SET FOREIGN_KEY_CHECKS=1;";
		}
		
		return sqlContent;
	}

	public static String showSQLStructure(){
		try{
			java.sql.ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("show create table `parametresApplicationUtilisateur`");
			if (data.next()){
				return data.getString(2);
			}
		}
		catch (Exception e){
		}
		
		return "";
	}

	public static String getTableName(){
		return "parametresApplicationUtilisateur";
	}
	
	public static java.util.List<models.beans.Utilisateur> getListOfUtilisateursParametres(models.beans.ParametresApplicationUtilisateur parametresApplicationUtilisateur){
		java.util.List<models.beans.Utilisateur> listOfUtilisateursParametres = models.daos.server.DAOUtilisateur.getListInstances("where idParametres='"+parametresApplicationUtilisateur.getId().intValue()+"'");
		return listOfUtilisateursParametres;
	}
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}