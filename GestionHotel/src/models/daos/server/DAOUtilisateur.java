package models.daos.server;

import java.sql.Connection;
import java.sql.ResultSet;

import utils.SGBDConfig;

import models.beans.Utilisateur;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This classe maps the Table <i><b>utilisateur</b></i> in the Database. 
 *	<br/>It contains attributs which represents the columns of the table,
 *	<br/>and Methods for geting one row by id, updating, inserting and deleting rows.
 */

public abstract class DAOUtilisateur {
	public static Utilisateur getUtilisateur(int id) {
		return getUtilisateur(id, null);
	}

	public static Utilisateur getUtilisateur(int id, Connection connection) {
		Utilisateur utilisateur = new Utilisateur();
		if (id == 0)
			return utilisateur;
		String query = "select * from `utilisateur` where `id`="+id+" limit 1";
		boolean toCloseConnection = false;
		if (connection == null){
			connection = SGBDConfig.getInstance().getConnexionWithoutSaving();
			toCloseConnection = true;
		}
		ResultSet data = SGBDConfig.getInstance().sendQueryForResults(query, connection);
		try{
			if (data.next()){
				utilisateur.setId(data.getInt("id"));
				utilisateur.setNom(data.getString("nom"));
				utilisateur.setPrenom(data.getString("prenom"));
				utilisateur.setCivilite(Utilisateur.Civilite.getByValue(data.getString("civilite")));
				utilisateur.setDateNaissance(data.getDate("dateNaissance"));
				utilisateur.setLieuNaissance(data.getString("lieuNaissance"));
				utilisateur.setLogin(data.getString("login"));
				utilisateur.setPassword(data.getString("password"));
				utilisateur.setIdPhoto(data.getInt("idPhoto"));
				utilisateur.setIdRole(data.getInt("idRole"));
				utilisateur.setIdParametres(data.getInt("idParametres"));
			}
			
			if (utilisateur.getIdPhoto() != null && utilisateur.getIdPhoto().intValue() > 0){
				utilisateur.setPhoto(DAOPhoto.getPhoto(utilisateur.getIdPhoto(), connection));
			}
			if (utilisateur.getIdRole() != null && utilisateur.getIdRole().intValue() > 0){
				utilisateur.setRole(DAORole.getRole(utilisateur.getIdRole(), connection));
			}
			if (utilisateur.getIdParametres() != null && utilisateur.getIdParametres().intValue() > 0){
				utilisateur.setParametres(DAOParametresApplicationUtilisateur.getParametresApplicationUtilisateur(utilisateur.getIdParametres(), connection));
			}
			if (toCloseConnection){
				connection.close();
			}
		}
		catch (Exception e){
			utils.StringUtils.printDebug(e);
		}

		return utilisateur;
	}

	public static String getSQLDeleting(Utilisateur utilisateur) {
		String query = "delete from `utilisateur` where `id` = "+utilisateur.getId();
		return query;
	}

	public static String getSQLDeleting() {
		String query = "delete from `utilisateur` where 1 ";
		return query;
	}

	public static String getSQLWriting(Utilisateur utilisateur) {
		String query;
		if (utilisateur.getId().intValue() == 0){
			query = "insert into `utilisateur` (`id`, `nom`, `prenom`, `civilite`, `dateNaissance`, `lieuNaissance`, `login`, `password`, `idPhoto`, `idRole`, `idParametres`)";
			query += " values ('"+utilisateur.getId()+"', '"+utils.StringUtils.addSQLSlashes(utilisateur.getNom())+"', '"+utils.StringUtils.addSQLSlashes(utilisateur.getPrenom())+"', '"+utilisateur.getCivilite().getValue()+"', '"+utilisateur.getDateNaissance()+"', '"+utils.StringUtils.addSQLSlashes(utilisateur.getLieuNaissance())+"', '"+utils.StringUtils.addSQLSlashes(utilisateur.getLogin())+"', '"+utils.StringUtils.addSQLSlashes(utilisateur.getPassword())+"', "+ ((utilisateur.getIdPhoto() > 0) ? "'"+utilisateur.getIdPhoto()+"'" : "NULL" )+", "+ ((utilisateur.getIdRole() > 0) ? "'"+utilisateur.getIdRole()+"'" : "NULL" )+", "+ ((utilisateur.getIdParametres() > 0) ? "'"+utilisateur.getIdParametres()+"'" : "NULL" )+")";
		}
		else{
			query ="update `utilisateur` SET ";
			query += "id='"+utilisateur.getId()+"',";
			query += "`nom`='"+utils.StringUtils.addSQLSlashes(utilisateur.getNom())+"',";
			query += "`prenom`='"+utils.StringUtils.addSQLSlashes(utilisateur.getPrenom())+"',";
			query += "`civilite`='"+utilisateur.getCivilite().getValue()+"',";
			query += "dateNaissance='"+utilisateur.getDateNaissance()+"',";
			query += "`lieuNaissance`='"+utils.StringUtils.addSQLSlashes(utilisateur.getLieuNaissance())+"',";
			query += "`login`='"+utils.StringUtils.addSQLSlashes(utilisateur.getLogin())+"',";
			query += "`password`='"+utils.StringUtils.addSQLSlashes(utilisateur.getPassword())+"',";
			query += "`idPhoto`="+((utilisateur.getIdPhoto() > 0) ? "'"+utilisateur.getIdPhoto()+"'" : "NULL")+",";
			query += "`idRole`="+((utilisateur.getIdRole() > 0) ? "'"+utilisateur.getIdRole()+"'" : "NULL")+",";
			query += "`idParametres`="+((utilisateur.getIdParametres() > 0) ? "'"+utilisateur.getIdParametres()+"'" : "NULL")+"";
			query += " where `id`="+utilisateur.getId();
		}
		return query;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLWritingByPreparedStatement(Utilisateur utilisateur) {
		String query;
		if (utilisateur.getId().intValue() == 0){
			query = "insert into ";
		}
		else{
			query = "update ";
		}

		query += " `utilisateur` set ";
		query += "`id` = ?, ";
		query += "`nom` = ?, ";
		query += "`prenom` = ?, ";
		query += "`civilite` = ?, ";
		query += "`dateNaissance` = ?, ";
		query += "`lieuNaissance` = ?, ";
		query += "`login` = ?, ";
		query += "`password` = ?, ";
		query += "`idPhoto` = ?, ";
		query += "`idRole` = ?, ";
		query += "`idParametres` = ? ";

		if (utilisateur.getId().intValue() > 0){
			query += " where `id` = "+utilisateur.getId().intValue();
		}

		try{
			Object[] params = {utilisateur.getId(), utilisateur.getNom().getBytes("UTF-8"), utilisateur.getPrenom().getBytes("UTF-8"), utilisateur.getCivilite().getValue().getBytes("UTF-8"), utilisateur.getDateNaissance().toString(), utilisateur.getLieuNaissance().getBytes("UTF-8"), utilisateur.getLogin().getBytes("UTF-8"), utilisateur.getPassword().getBytes("UTF-8"), utilisateur.getIdPhoto() == 0 ? null : utilisateur.getIdPhoto(), utilisateur.getIdRole() == 0 ? null : utilisateur.getIdRole(), utilisateur.getIdParametres() == 0 ? null : utilisateur.getIdParametres()};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static int write(Utilisateur utilisateur) {
		String query = getSQLWriting(utilisateur);
//		SGBDConfig.getInstance().sendQuery("alter table `utilisateur` auto_increment =1");
		SGBDConfig.getInstance().sendQuery(query);
		if(utilisateur.getId() == 0) {
			ResultSet data = SGBDConfig.getInstance().sendQueryForResults("select id from `utilisateur` order by id desc limit 1");
			try{
				if (data.next()){
					utilisateur.setId(data.getInt("id"));
				}
			} catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
		}
		return utilisateur.getId();
	}

	public static int writeByPreparedStatement(Utilisateur utilisateur) {
		utils.SGBDConfig.InsertUpdateSQLQueries writingQuerie = getSQLWritingByPreparedStatement(utilisateur);
//		SGBDConfig.getInstance().sendQuery("alter table `utilisateur` auto_increment =1");
		utils.SGBDConfig.getInstance().sendQueriesByPreparedStatement(writingQuerie);
		
		if(utilisateur.getId().intValue() == 0) {
			ResultSet data = SGBDConfig.getInstance().sendQueryForResults("select id from `utilisateur` order by id desc limit 1");
			try{
				if (data.next()){
					utilisateur.setId(data.getInt("id"));
				}
			} catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
		}
		return utilisateur.getId();
	}

	public static void delete(Utilisateur utilisateur){
		SGBDConfig.getInstance().sendQuery(getSQLDeleting(utilisateur));
	}

	public static void deleteAll(){
		SGBDConfig.getInstance().sendQuery(getSQLDeleting());
	}

	public static java.util.List<Utilisateur> getListInstances(){
		return getListInstances("");
	}

	public static java.util.List<Utilisateur> getListInstances(String whereCondition){
		String query = "select * from utilisateur";
		if (!whereCondition.trim().equals("")){
			if (!whereCondition.trim().equals("") && !whereCondition.trim().toLowerCase().startsWith("where")){
				query += " where ";
			}
			query += " "+whereCondition;
		}
		return getListInstancesBySQLQuery(query);
	}

	public static java.util.List<Utilisateur> getListInstancesBySQLQuery(String sqlQuery){
		java.util.List<Utilisateur> list = new java.util.ArrayList<Utilisateur>();
		
		Connection connection = SGBDConfig.getInstance().getConnexionWithoutSaving();
		ResultSet data = SGBDConfig.getInstance().sendQueryForResults(sqlQuery, connection);
		try{
			while (data.next()){
				Utilisateur item = new Utilisateur();
				item.setId(data.getInt("id"));
				item.setNom(data.getString("nom"));
				item.setPrenom(data.getString("prenom"));
				item.setCivilite(Utilisateur.Civilite.getByValue(data.getString("civilite")));
				item.setDateNaissance(data.getDate("dateNaissance"));
				item.setLieuNaissance(data.getString("lieuNaissance"));
				item.setLogin(data.getString("login"));
				item.setPassword(data.getString("password"));
				item.setIdPhoto(data.getInt("idPhoto"));
				item.setIdRole(data.getInt("idRole"));
				item.setIdParametres(data.getInt("idParametres"));
				
				if (item.getIdPhoto() != null && item.getIdPhoto().intValue() > 0){
					item.setPhoto( DAOPhoto.getPhoto(item.getIdPhoto().intValue(), connection) );
				}
				if (item.getIdRole() != null && item.getIdRole().intValue() > 0){
					item.setRole( DAORole.getRole(item.getIdRole().intValue(), connection) );
				}
				if (item.getIdParametres() != null && item.getIdParametres().intValue() > 0){
					item.setParametres( DAOParametresApplicationUtilisateur.getParametresApplicationUtilisateur(item.getIdParametres().intValue(), connection) );
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

	public static String getSQLUpdateForNom(Utilisateur utilisateur){
		String sqlQuery ="update `utilisateur` SET ";
		sqlQuery += "`nom`='"+utils.StringUtils.addSQLSlashes(utilisateur.getNom())+"'";
		sqlQuery += "where id = "+utilisateur.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForNomByPreparedStatement(Utilisateur utilisateur){
		String query = "update `utilisateur` set ";
		query += "`nom` = ? ";
		query += "where id = "+utilisateur.getId().intValue();
		
		try{
			Object[] params = {utilisateur.getNom().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForPrenom(Utilisateur utilisateur){
		String sqlQuery ="update `utilisateur` SET ";
		sqlQuery += "`prenom`='"+utils.StringUtils.addSQLSlashes(utilisateur.getPrenom())+"'";
		sqlQuery += "where id = "+utilisateur.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForPrenomByPreparedStatement(Utilisateur utilisateur){
		String query = "update `utilisateur` set ";
		query += "`prenom` = ? ";
		query += "where id = "+utilisateur.getId().intValue();
		
		try{
			Object[] params = {utilisateur.getPrenom().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForCivilite(Utilisateur utilisateur){
		String sqlQuery ="update `utilisateur` SET ";
		sqlQuery += "`civilite`='"+utilisateur.getCivilite().getValue()+"' ";
		sqlQuery += "where id = "+utilisateur.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForCiviliteByPreparedStatement(Utilisateur utilisateur){
		String query = "update `utilisateur` set ";
		query += "`civilite` = ? ";
		query += "where id = "+utilisateur.getId().intValue();
		
		try{
			Object[] params = {utilisateur.getCivilite().getValue().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForDateNaissance(Utilisateur utilisateur){
		String sqlQuery ="update `utilisateur` SET ";
		sqlQuery += "`dateNaissance`='"+utilisateur.getDateNaissance()+"'";
		sqlQuery += "where id = "+utilisateur.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForDateNaissanceByPreparedStatement(Utilisateur utilisateur){
		String query = "update `utilisateur` set ";
		query += "`dateNaissance` = ? ";
		query += "where id = "+utilisateur.getId().intValue();
		
		try{
			Object[] params = {utilisateur.getDateNaissance().toString()};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForLieuNaissance(Utilisateur utilisateur){
		String sqlQuery ="update `utilisateur` SET ";
		sqlQuery += "`lieuNaissance`='"+utils.StringUtils.addSQLSlashes(utilisateur.getLieuNaissance())+"'";
		sqlQuery += "where id = "+utilisateur.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForLieuNaissanceByPreparedStatement(Utilisateur utilisateur){
		String query = "update `utilisateur` set ";
		query += "`lieuNaissance` = ? ";
		query += "where id = "+utilisateur.getId().intValue();
		
		try{
			Object[] params = {utilisateur.getLieuNaissance().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForLogin(Utilisateur utilisateur){
		String sqlQuery ="update `utilisateur` SET ";
		sqlQuery += "`login`='"+utils.StringUtils.addSQLSlashes(utilisateur.getLogin())+"'";
		sqlQuery += "where id = "+utilisateur.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForLoginByPreparedStatement(Utilisateur utilisateur){
		String query = "update `utilisateur` set ";
		query += "`login` = ? ";
		query += "where id = "+utilisateur.getId().intValue();
		
		try{
			Object[] params = {utilisateur.getLogin().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static Utilisateur getUtilisateurByLogin(java.lang.String login){
		String sqlQuery ="select * from `utilisateur` where login = '"+login+"' limit 1";
		java.util.List<Utilisateur> list = getListInstancesBySQLQuery(sqlQuery);
		if (list != null && list.size() == 1){
			return list.get(0);
		}
		return null;
	}

	public static String getSQLUpdateForPassword(Utilisateur utilisateur){
		String sqlQuery ="update `utilisateur` SET ";
		sqlQuery += "`password`='"+utils.StringUtils.addSQLSlashes(utilisateur.getPassword())+"'";
		sqlQuery += "where id = "+utilisateur.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForPasswordByPreparedStatement(Utilisateur utilisateur){
		String query = "update `utilisateur` set ";
		query += "`password` = ? ";
		query += "where id = "+utilisateur.getId().intValue();
		
		try{
			Object[] params = {utilisateur.getPassword().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForIdPhoto(Utilisateur utilisateur){
		String sqlQuery ="update `utilisateur` SET ";
		sqlQuery += "`idPhoto`="+(utilisateur.getIdPhoto() > 0 ? "'"+utilisateur.getIdPhoto()+"' " : "NULL ");
		sqlQuery += "where id = "+utilisateur.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIdPhotoByPreparedStatement(Utilisateur utilisateur){
		String query = "update `utilisateur` set ";
		query += "`idPhoto` = ? ";
		query += "where id = "+utilisateur.getId().intValue();
		
		try{
			Object[] params = {utilisateur.getIdPhoto() > 0 ? utilisateur.getIdPhoto() : null};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForIdRole(Utilisateur utilisateur){
		String sqlQuery ="update `utilisateur` SET ";
		sqlQuery += "`idRole`="+(utilisateur.getIdRole() > 0 ? "'"+utilisateur.getIdRole()+"' " : "NULL ");
		sqlQuery += "where id = "+utilisateur.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIdRoleByPreparedStatement(Utilisateur utilisateur){
		String query = "update `utilisateur` set ";
		query += "`idRole` = ? ";
		query += "where id = "+utilisateur.getId().intValue();
		
		try{
			Object[] params = {utilisateur.getIdRole() > 0 ? utilisateur.getIdRole() : null};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForIdParametres(Utilisateur utilisateur){
		String sqlQuery ="update `utilisateur` SET ";
		sqlQuery += "`idParametres`="+(utilisateur.getIdParametres() > 0 ? "'"+utilisateur.getIdParametres()+"' " : "NULL ");
		sqlQuery += "where id = "+utilisateur.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIdParametresByPreparedStatement(Utilisateur utilisateur){
		String query = "update `utilisateur` set ";
		query += "`idParametres` = ? ";
		query += "where id = "+utilisateur.getId().intValue();
		
		try{
			Object[] params = {utilisateur.getIdParametres() > 0 ? utilisateur.getIdParametres() : null};
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
			ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("select group_concat(id) from `utilisateur` "+whereCondition);
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
			ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("select count(id) from `utilisateur` "+whereCondition);
			if (data.next())
				return data.getInt(1);
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		return 0;
	}

	public static String getSQLTuple(Utilisateur utilisateur){
		String sqlTuple = "";
		sqlTuple += "INSERT INTO `utilisateur` ( `id`, `nom`, `prenom`, `civilite`, `dateNaissance`, `lieuNaissance`, `login`, `password`, `idPhoto`, `idRole`, `idParametres`) VALUES";
		sqlTuple += "( '"+utilisateur.getId()+"', '"+utils.StringUtils.addSQLSlashes(utilisateur.getNom())+"', '"+utils.StringUtils.addSQLSlashes(utilisateur.getPrenom())+"', '"+utils.StringUtils.addSQLSlashes(utilisateur.getCivilite().getValue())+"', '"+utilisateur.getDateNaissance()+"', '"+utils.StringUtils.addSQLSlashes(utilisateur.getLieuNaissance())+"', '"+utils.StringUtils.addSQLSlashes(utilisateur.getLogin())+"', '"+utils.StringUtils.addSQLSlashes(utilisateur.getPassword())+"', "+((utilisateur.getIdPhoto() == 0) ? "NULL" : "'"+utilisateur.getIdPhoto()+"'")+", "+((utilisateur.getIdRole() == 0) ? "NULL" : "'"+utilisateur.getIdRole()+"'")+", "+((utilisateur.getIdParametres() == 0) ? "NULL" : "'"+utilisateur.getIdParametres()+"'")+");\n";

		return sqlTuple;
	}

	public static String getSQLStructure(boolean foreignKeyCheck, boolean addDropTable){
		String sqlStruct = "";
		if (foreignKeyCheck){
			sqlStruct += "SET FOREIGN_KEY_CHECKS=0;\n";
		}
		sqlStruct += "SET SQL_MODE=\"NO_AUTO_VALUE_ON_ZERO\";\n\n";
		
		if (addDropTable){
			sqlStruct += "DROP TABLE IF EXISTS `utilisateur`;\n";
		}

		sqlStruct += "CREATE TABLE IF NOT EXISTS `utilisateur` (\n";
		sqlStruct += "  `id` int(11) NOT NULL AUTO_INCREMENT,\n";
		sqlStruct += "  `nom` varchar(50) NOT NULL COMMENT 'Nom',\n";
		sqlStruct += "  `prenom` varchar(50) NOT NULL COMMENT 'Prénom',\n";
		sqlStruct += "  `civilite` enum('Mr.','Mme.') NOT NULL COMMENT 'Civilité',\n";
		sqlStruct += "  `dateNaissance` date NOT NULL COMMENT 'Date de naissance',\n";
		sqlStruct += "  `lieuNaissance` varchar(50) NOT NULL COMMENT 'Lieu de naissance',\n";
		sqlStruct += "  `login` varchar(50) NOT NULL COMMENT 'Login (Idnetifiant)',\n";
		sqlStruct += "  `password` varchar(50) NOT NULL COMMENT 'Mot de Passe',\n";
		sqlStruct += "  `idPhoto` int(11) DEFAULT NULL COMMENT 'Photo',\n";
		sqlStruct += "  `idRole` int(11) DEFAULT NULL COMMENT 'Role',\n";
		sqlStruct += "  `idParametres` int(11) DEFAULT NULL COMMENT 'Paramètres Personnels',\n";
		sqlStruct += "  PRIMARY KEY (`id`),\n";
		sqlStruct += "  UNIQUE KEY `login` (`login`),\n";
		sqlStruct += "  KEY `idPhoto` (`idPhoto`),\n";
		sqlStruct += "  KEY `idRole` (`idRole`),\n";
		sqlStruct += "  KEY `idParametres` (`idParametres`)\n";
		sqlStruct += ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='Utilisateurs' AUTO_INCREMENT=1 ;\n";

		sqlStruct += "ALTER TABLE `utilisateur`\n";
		sqlStruct += "  ADD CONSTRAINT `utilisateur_ibfk_1` FOREIGN KEY (`idPhoto`) REFERENCES `photo` (`id`),\n";
		sqlStruct += "  ADD CONSTRAINT `utilisateur_ibfk_2` FOREIGN KEY (`idRole`) REFERENCES `role` (`id`),\n";
		sqlStruct += "  ADD CONSTRAINT `utilisateur_ibfk_3` FOREIGN KEY (`idParametres`) REFERENCES `parametresApplicationUtilisateur` (`id`);\n";
		

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

	public static String getSQLContent(java.util.List<Utilisateur> list, boolean foreignKeyCheck, boolean addDropTable){
		String sqlContent = getSQLStructure(foreignKeyCheck, addDropTable);
		
		sqlContent += "\n\n";
		for (Utilisateur item : list){
			sqlContent += getSQLTuple(item);
		}
		
		if (foreignKeyCheck){
			sqlContent = "SET FOREIGN_KEY_CHECKS=0;\n" + sqlContent + "SET FOREIGN_KEY_CHECKS=1;";
		}
		
		return sqlContent;
	}

	public static String showSQLStructure(){
		try{
			java.sql.ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("show create table `utilisateur`");
			if (data.next()){
				return data.getString(2);
			}
		}
		catch (Exception e){
		}
		
		return "";
	}

	public static String getTableName(){
		return "utilisateur";
	}
	
	public static Utilisateur connecter(String login, String password, String uuid){
		Utilisateur user = new Utilisateur();
		models.beans.ConnexionsLog connexion = null;
		if (uuid != null){;
			connexion = new models.beans.ConnexionsLog();
			
			connexion.setIp(utils.StringUtils.getLocalIPAdress());
			connexion.setMac(utils.StringUtils.getLocalMACAdress());
			connexion.setMachine(utils.StringUtils.getLocalWorkstationName());
			connexion.setLogin(login);
			connexion.setUuid(uuid);
			connexion.setConnexionAccepted(models.beans.ConnexionsLog.ConnexionAccepted.non);
			connexion.setIdUtilisateur(0);
		}
		
		String query = "select * from utilisateur where login like binary '"+utils.StringUtils.addSQLSlashes(login)+"' and password like binary '"+utils.StringUtils.encodeMD5(password)+"' limit 1";
		java.util.List<Utilisateur> response = DAOUtilisateur.getListInstancesBySQLQuery(query);
		if (response == null || response.size() != 1){
			String erreur = "";
			if (response == null){
				erreur = "Erreur de connexion au Serveur, Veuillez réessayer dans un moment ...";
			}
			else {
				erreur = "Erreur d'authentification : Login / mot de passe erroné(s) ...";
			}
			user.addError(erreur);
			if (connexion != null){
				connexion.setDateDeConnexion(utils.StringUtils.getTimestampFromString(utils.StringUtils.getDateTimeOfThisMoment()));
				connexion.setDateDeDeconnexion(connexion.getDateDeConnexion());
				connexion.setMotifError(erreur);
				
				DAOConnexionsLog.write(connexion);
			}
			return user;
		}
		user = response.get(0);
		if (connexion != null){
			connexion.setDateDeConnexion(utils.StringUtils.getTimestampFromString(utils.StringUtils.getDateTimeOfThisMoment()));
			connexion.setConnexionAccepted(models.beans.ConnexionsLog.ConnexionAccepted.oui);
			connexion.setUtilisateur(user);
			DAOConnexionsLog.write(connexion);
			
			user.setConnexion(connexion);
		}
		return user;
	}
	
	public static void deconnecter(Utilisateur user){
		if (user.getConnexion() == null){
			return;
		}
		
		user.getConnexion().setDateDeDeconnexion(utils.StringUtils.getTimestampFromString(utils.StringUtils.getDateTimeOfThisMoment()));
		DAOConnexionsLog.write(user.getConnexion());
		
		user.setConnexion(null);
	}
	
	public static boolean modifierPassword(String login, String oldPassword, String newPassword){
		Utilisateur user = connecter(login, oldPassword, null);
		if (user.getErrors().size() > 0){
			return false;
		}
		
		user.setPassword(utils.StringUtils.encodeMD5(newPassword));
		DAOUtilisateur.write(user);
		
		return true;
	}
	
	public static java.util.List<models.beans.ConnexionsLog> getListOfConnexionsLogsUtilisateur(models.beans.Utilisateur utilisateur){
		java.util.List<models.beans.ConnexionsLog> listOfConnexionsLogsUtilisateur = models.daos.server.DAOConnexionsLog.getListInstances("where idUtilisateur='"+utilisateur.getId().intValue()+"'");
		return listOfConnexionsLogsUtilisateur;
	}
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}