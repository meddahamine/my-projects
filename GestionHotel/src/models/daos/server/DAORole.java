package models.daos.server;

import java.sql.Connection;
import java.sql.ResultSet;

import utils.SGBDConfig;

import models.beans.Role;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This classe maps the Table <i><b>role</b></i> in the Database. 
 *	<br/>It contains attributs which represents the columns of the table,
 *	<br/>and Methods for geting one row by id, updating, inserting and deleting rows.
 */

public abstract class DAORole {
	public static Role getRole(int id) {
		return getRole(id, null);
	}

	public static Role getRole(int id, Connection connection) {
		Role role = new Role();
		if (id == 0)
			return role;
		String query = "select * from `role` where `id`="+id+" limit 1";
		boolean toCloseConnection = false;
		if (connection == null){
			connection = SGBDConfig.getInstance().getConnexionWithoutSaving();
			toCloseConnection = true;
		}
		ResultSet data = SGBDConfig.getInstance().sendQueryForResults(query, connection);
		try{
			if (data.next()){
				role.setId(data.getInt("id"));
				role.setDesignation(data.getString("designation"));
				role.setIdPhoto(data.getInt("idPhoto"));
				role.setParametresOrganisme(data.getBoolean("parametresOrganisme"));
				role.setGestionRole(data.getBoolean("gestionRole"));
				role.setGestionUtilisateur(data.getBoolean("gestionUtilisateur"));
			}
			
			if (role.getIdPhoto() != null && role.getIdPhoto().intValue() > 0){
				role.setPhoto(DAOPhoto.getPhoto(role.getIdPhoto(), connection));
			}
			if (toCloseConnection){
				connection.close();
			}
		}
		catch (Exception e){
			utils.StringUtils.printDebug(e);
		}

		return role;
	}

	public static String getSQLDeleting(Role role) {
		String query = "delete from `role` where `id` = "+role.getId();
		return query;
	}

	public static String getSQLDeleting() {
		String query = "delete from `role` where 1 ";
		return query;
	}

	public static String getSQLWriting(Role role) {
		String query;
		if (role.getId().intValue() == 0){
			query = "insert into `role` (`id`, `designation`, `idPhoto`, `parametresOrganisme`, `gestionRole`, `gestionUtilisateur`)";
			query += " values ('"+role.getId()+"', '"+utils.StringUtils.addSQLSlashes(role.getDesignation())+"', "+ ((role.getIdPhoto() > 0) ? "'"+role.getIdPhoto()+"'" : "NULL" )+", '"+(role.isParametresOrganisme() ? 1 : 0)+"', '"+(role.isGestionRole() ? 1 : 0)+"', '"+(role.isGestionUtilisateur() ? 1 : 0)+"')";
		}
		else{
			query ="update `role` SET ";
			query += "id='"+role.getId()+"',";
			query += "`designation`='"+utils.StringUtils.addSQLSlashes(role.getDesignation())+"',";
			query += "`idPhoto`="+((role.getIdPhoto() > 0) ? "'"+role.getIdPhoto()+"'" : "NULL")+",";
			query += "`parametresOrganisme`="+(role.isParametresOrganisme() ? 1 : 0)+",";
			query += "`gestionRole`="+(role.isGestionRole() ? 1 : 0)+",";
			query += "`gestionUtilisateur`="+(role.isGestionUtilisateur() ? 1 : 0)+"";
			query += " where `id`="+role.getId();
		}
		return query;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLWritingByPreparedStatement(Role role) {
		String query;
		if (role.getId().intValue() == 0){
			query = "insert into ";
		}
		else{
			query = "update ";
		}

		query += " `role` set ";
		query += "`id` = ?, ";
		query += "`designation` = ?, ";
		query += "`idPhoto` = ?, ";
		query += "`parametresOrganisme` = ?, ";
		query += "`gestionRole` = ?, ";
		query += "`gestionUtilisateur` = ? ";

		if (role.getId().intValue() > 0){
			query += " where `id` = "+role.getId().intValue();
		}

		try{
			Object[] params = {role.getId(), role.getDesignation().getBytes("UTF-8"), role.getIdPhoto() == 0 ? null : role.getIdPhoto(), role.isParametresOrganisme() == true ? 1 : 0, role.isGestionRole() == true ? 1 : 0, role.isGestionUtilisateur() == true ? 1 : 0};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static int write(Role role) {
		String query = getSQLWriting(role);
//		SGBDConfig.getInstance().sendQuery("alter table `role` auto_increment =1");
		SGBDConfig.getInstance().sendQuery(query);
		if(role.getId() == 0) {
			ResultSet data = SGBDConfig.getInstance().sendQueryForResults("select id from `role` order by id desc limit 1");
			try{
				if (data.next()){
					role.setId(data.getInt("id"));
				}
			} catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
		}
		return role.getId();
	}

	public static int writeByPreparedStatement(Role role) {
		utils.SGBDConfig.InsertUpdateSQLQueries writingQuerie = getSQLWritingByPreparedStatement(role);
//		SGBDConfig.getInstance().sendQuery("alter table `role` auto_increment =1");
		utils.SGBDConfig.getInstance().sendQueriesByPreparedStatement(writingQuerie);
		
		if(role.getId().intValue() == 0) {
			ResultSet data = SGBDConfig.getInstance().sendQueryForResults("select id from `role` order by id desc limit 1");
			try{
				if (data.next()){
					role.setId(data.getInt("id"));
				}
			} catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
		}
		return role.getId();
	}

	public static void delete(Role role){
		SGBDConfig.getInstance().sendQuery(getSQLDeleting(role));
	}

	public static void deleteAll(){
		SGBDConfig.getInstance().sendQuery(getSQLDeleting());
	}

	public static java.util.List<Role> getListInstances(){
		return getListInstances("");
	}

	public static java.util.List<Role> getListInstances(String whereCondition){
		String query = "select * from role";
		if (!whereCondition.trim().equals("")){
			if (!whereCondition.trim().equals("") && !whereCondition.trim().toLowerCase().startsWith("where")){
				query += " where ";
			}
			query += " "+whereCondition;
		}
		return getListInstancesBySQLQuery(query);
	}

	public static java.util.List<Role> getListInstancesBySQLQuery(String sqlQuery){
		java.util.List<Role> list = new java.util.ArrayList<Role>();
		
		Connection connection = SGBDConfig.getInstance().getConnexionWithoutSaving();
		ResultSet data = SGBDConfig.getInstance().sendQueryForResults(sqlQuery, connection);
		try{
			while (data.next()){
				Role item = new Role();
				item.setId(data.getInt("id"));
				item.setDesignation(data.getString("designation"));
				item.setIdPhoto(data.getInt("idPhoto"));
				item.setParametresOrganisme(data.getBoolean("parametresOrganisme"));
				item.setGestionRole(data.getBoolean("gestionRole"));
				item.setGestionUtilisateur(data.getBoolean("gestionUtilisateur"));
				
				if (item.getIdPhoto() != null && item.getIdPhoto().intValue() > 0){
					item.setPhoto( DAOPhoto.getPhoto(item.getIdPhoto().intValue(), connection) );
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

	public static String getSQLUpdateForDesignation(Role role){
		String sqlQuery ="update `role` SET ";
		sqlQuery += "`designation`='"+utils.StringUtils.addSQLSlashes(role.getDesignation())+"'";
		sqlQuery += "where id = "+role.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForDesignationByPreparedStatement(Role role){
		String query = "update `role` set ";
		query += "`designation` = ? ";
		query += "where id = "+role.getId().intValue();
		
		try{
			Object[] params = {role.getDesignation().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForIdPhoto(Role role){
		String sqlQuery ="update `role` SET ";
		sqlQuery += "`idPhoto`="+(role.getIdPhoto() > 0 ? "'"+role.getIdPhoto()+"' " : "NULL ");
		sqlQuery += "where id = "+role.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIdPhotoByPreparedStatement(Role role){
		String query = "update `role` set ";
		query += "`idPhoto` = ? ";
		query += "where id = "+role.getId().intValue();
		
		try{
			Object[] params = {role.getIdPhoto() > 0 ? role.getIdPhoto() : null};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForParametresOrganisme(Role role){
		String sqlQuery ="update `role` SET ";
		sqlQuery += "`parametresOrganisme`="+(role.isParametresOrganisme() ? 1 : 0)+" ";
		sqlQuery += "where id = "+role.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForParametresOrganismeByPreparedStatement(Role role){
		String query = "update `role` set ";
		query += "`parametresOrganisme` = ? ";
		query += "where id = "+role.getId().intValue();
		
		try{
			Object[] params = {role.isParametresOrganisme()};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForGestionRole(Role role){
		String sqlQuery ="update `role` SET ";
		sqlQuery += "`gestionRole`="+(role.isGestionRole() ? 1 : 0)+" ";
		sqlQuery += "where id = "+role.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForGestionRoleByPreparedStatement(Role role){
		String query = "update `role` set ";
		query += "`gestionRole` = ? ";
		query += "where id = "+role.getId().intValue();
		
		try{
			Object[] params = {role.isGestionRole()};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForGestionUtilisateur(Role role){
		String sqlQuery ="update `role` SET ";
		sqlQuery += "`gestionUtilisateur`="+(role.isGestionUtilisateur() ? 1 : 0)+" ";
		sqlQuery += "where id = "+role.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForGestionUtilisateurByPreparedStatement(Role role){
		String query = "update `role` set ";
		query += "`gestionUtilisateur` = ? ";
		query += "where id = "+role.getId().intValue();
		
		try{
			Object[] params = {role.isGestionUtilisateur()};
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
			ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("select group_concat(id) from `role` "+whereCondition);
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
			ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("select count(id) from `role` "+whereCondition);
			if (data.next())
				return data.getInt(1);
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		return 0;
	}

	public static String getSQLTuple(Role role){
		String sqlTuple = "";
		sqlTuple += "INSERT INTO `role` ( `id`, `designation`, `idPhoto`, `parametresOrganisme`, `gestionRole`, `gestionUtilisateur`) VALUES";
		sqlTuple += "( '"+role.getId()+"', '"+utils.StringUtils.addSQLSlashes(role.getDesignation())+"', "+((role.getIdPhoto() == 0) ? "NULL" : "'"+role.getIdPhoto()+"'")+", '"+(role.isParametresOrganisme() ? 1 : 0)+"', '"+(role.isGestionRole() ? 1 : 0)+"', '"+(role.isGestionUtilisateur() ? 1 : 0)+"');\n";

		return sqlTuple;
	}

	public static String getSQLStructure(boolean foreignKeyCheck, boolean addDropTable){
		String sqlStruct = "";
		if (foreignKeyCheck){
			sqlStruct += "SET FOREIGN_KEY_CHECKS=0;\n";
		}
		sqlStruct += "SET SQL_MODE=\"NO_AUTO_VALUE_ON_ZERO\";\n\n";
		
		if (addDropTable){
			sqlStruct += "DROP TABLE IF EXISTS `role`;\n";
		}

		sqlStruct += "CREATE TABLE IF NOT EXISTS `role` (\n";
		sqlStruct += "  `id` int(11) NOT NULL AUTO_INCREMENT,\n";
		sqlStruct += "  `designation` varchar(40) NOT NULL COMMENT 'Désignation',\n";
		sqlStruct += "  `idPhoto` int(11) DEFAULT NULL COMMENT 'Emblème',\n";
		sqlStruct += "  `parametresOrganisme` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Paramètres de l''Organisme',\n";
		sqlStruct += "  `gestionRole` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Gestion des Rôles',\n";
		sqlStruct += "  `gestionUtilisateur` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Gestion des Utilisateurs',\n";
		sqlStruct += "  PRIMARY KEY (`id`),\n";
		sqlStruct += "  KEY `idPhoto` (`idPhoto`)\n";
		sqlStruct += ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='Rôles' AUTO_INCREMENT=1 ;\n";

		sqlStruct += "ALTER TABLE `role`\n";
		sqlStruct += "  ADD CONSTRAINT `role_ibfk_1` FOREIGN KEY (`idPhoto`) REFERENCES `photo` (`id`);\n";
		

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

	public static String getSQLContent(java.util.List<Role> list, boolean foreignKeyCheck, boolean addDropTable){
		String sqlContent = getSQLStructure(foreignKeyCheck, addDropTable);
		
		sqlContent += "\n\n";
		for (Role item : list){
			sqlContent += getSQLTuple(item);
		}
		
		if (foreignKeyCheck){
			sqlContent = "SET FOREIGN_KEY_CHECKS=0;\n" + sqlContent + "SET FOREIGN_KEY_CHECKS=1;";
		}
		
		return sqlContent;
	}

	public static String showSQLStructure(){
		try{
			java.sql.ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("show create table `role`");
			if (data.next()){
				return data.getString(2);
			}
		}
		catch (Exception e){
		}
		
		return "";
	}

	public static String getTableName(){
		return "role";
	}
	
	public static java.util.List<models.beans.Utilisateur> getListOfUtilisateursRole(models.beans.Role role){
		java.util.List<models.beans.Utilisateur> listOfUtilisateursRole = models.daos.server.DAOUtilisateur.getListInstances("where idRole='"+role.getId().intValue()+"'");
		return listOfUtilisateursRole;
	}
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}