package models.daos.server;

import java.sql.Connection;
import java.sql.ResultSet;

import utils.SGBDConfig;

import models.beans.ParametresOrganisme;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This classe maps the Table <i><b>parametresOrganisme</b></i> in the Database. 
 *	<br/>It contains attributs which represents the columns of the table,
 *	<br/>and Methods for geting one row by id, updating, inserting and deleting rows.
 */

public abstract class DAOParametresOrganisme {
	public static ParametresOrganisme getParametresOrganisme(int id) {
		return getParametresOrganisme(id, null);
	}

	public static ParametresOrganisme getParametresOrganisme(int id, Connection connection) {
		ParametresOrganisme parametresOrganisme = new ParametresOrganisme();
		if (id == 0)
			return parametresOrganisme;
		String query = "select * from `parametresOrganisme` where `id`="+id+" limit 1";
		boolean toCloseConnection = false;
		if (connection == null){
			connection = SGBDConfig.getInstance().getConnexionWithoutSaving();
			toCloseConnection = true;
		}
		ResultSet data = SGBDConfig.getInstance().sendQueryForResults(query, connection);
		try{
			if (data.next()){
				parametresOrganisme.setId(data.getInt("id"));
				parametresOrganisme.setDesignationOrganisme(data.getString("designationOrganisme"));
				parametresOrganisme.setRaisonSocial(data.getString("raisonSocial"));
				parametresOrganisme.setCapitalSocial(data.getString("capitalSocial"));
				parametresOrganisme.setNumRC(data.getString("numRC"));
				parametresOrganisme.setNumCB(data.getString("numCB"));
				parametresOrganisme.setIdentificationFiscale(data.getString("identificationFiscale"));
				parametresOrganisme.setNumArticle(data.getString("numArticle"));
				parametresOrganisme.setNIS(data.getString("NIS"));
				parametresOrganisme.setAdresse(data.getString("adresse"));
				parametresOrganisme.setBoitePostale(data.getString("boitePostale"));
				parametresOrganisme.setNumTel(data.getString("numTel"));
				parametresOrganisme.setNumFax(data.getString("numFax"));
				parametresOrganisme.setEmail(data.getString("Email"));
				parametresOrganisme.setDescriptif(data.getString("descriptif"));
				parametresOrganisme.setIdPhoto(data.getInt("idPhoto"));
			}
			
			if (parametresOrganisme.getIdPhoto() != null && parametresOrganisme.getIdPhoto().intValue() > 0){
				parametresOrganisme.setPhoto(DAOPhoto.getPhoto(parametresOrganisme.getIdPhoto(), connection));
			}
			if (toCloseConnection){
				connection.close();
			}
		}
		catch (Exception e){
			utils.StringUtils.printDebug(e);
		}

		return parametresOrganisme;
	}

	public static String getSQLDeleting(ParametresOrganisme parametresOrganisme) {
		String query = "delete from `parametresOrganisme` where `id` = "+parametresOrganisme.getId();
		return query;
	}

	public static String getSQLDeleting() {
		String query = "delete from `parametresOrganisme` where 1 ";
		return query;
	}

	public static String getSQLWriting(ParametresOrganisme parametresOrganisme) {
		String query;
		if (parametresOrganisme.getId().intValue() == 0){
			query = "insert into `parametresOrganisme` (`id`, `designationOrganisme`, `raisonSocial`, `capitalSocial`, `numRC`, `numCB`, `identificationFiscale`, `numArticle`, `NIS`, `adresse`, `boitePostale`, `numTel`, `numFax`, `Email`, `descriptif`, `idPhoto`)";
			query += " values ('"+parametresOrganisme.getId()+"', '"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getDesignationOrganisme())+"', '"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getRaisonSocial())+"', '"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getCapitalSocial())+"', '"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getNumRC())+"', '"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getNumCB())+"', '"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getIdentificationFiscale())+"', '"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getNumArticle())+"', '"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getNIS())+"', '"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getAdresse())+"', '"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getBoitePostale())+"', '"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getNumTel())+"', '"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getNumFax())+"', '"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getEmail())+"', '"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getDescriptif())+"', "+ ((parametresOrganisme.getIdPhoto() > 0) ? "'"+parametresOrganisme.getIdPhoto()+"'" : "NULL" )+")";
		}
		else{
			query ="update `parametresOrganisme` SET ";
			query += "id='"+parametresOrganisme.getId()+"',";
			query += "`designationOrganisme`='"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getDesignationOrganisme())+"',";
			query += "`raisonSocial`='"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getRaisonSocial())+"',";
			query += "`capitalSocial`='"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getCapitalSocial())+"',";
			query += "`numRC`='"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getNumRC())+"',";
			query += "`numCB`='"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getNumCB())+"',";
			query += "`identificationFiscale`='"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getIdentificationFiscale())+"',";
			query += "`numArticle`='"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getNumArticle())+"',";
			query += "`NIS`='"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getNIS())+"',";
			query += "`adresse`='"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getAdresse())+"',";
			query += "`boitePostale`='"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getBoitePostale())+"',";
			query += "`numTel`='"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getNumTel())+"',";
			query += "`numFax`='"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getNumFax())+"',";
			query += "`Email`='"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getEmail())+"',";
			query += "`descriptif`='"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getDescriptif())+"',";
			query += "`idPhoto`="+((parametresOrganisme.getIdPhoto() > 0) ? "'"+parametresOrganisme.getIdPhoto()+"'" : "NULL")+"";
			query += " where `id`="+parametresOrganisme.getId();
		}
		return query;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLWritingByPreparedStatement(ParametresOrganisme parametresOrganisme) {
		String query;
		if (parametresOrganisme.getId().intValue() == 0){
			query = "insert into ";
		}
		else{
			query = "update ";
		}

		query += " `parametresOrganisme` set ";
		query += "`id` = ?, ";
		query += "`designationOrganisme` = ?, ";
		query += "`raisonSocial` = ?, ";
		query += "`capitalSocial` = ?, ";
		query += "`numRC` = ?, ";
		query += "`numCB` = ?, ";
		query += "`identificationFiscale` = ?, ";
		query += "`numArticle` = ?, ";
		query += "`NIS` = ?, ";
		query += "`adresse` = ?, ";
		query += "`boitePostale` = ?, ";
		query += "`numTel` = ?, ";
		query += "`numFax` = ?, ";
		query += "`Email` = ?, ";
		query += "`descriptif` = ?, ";
		query += "`idPhoto` = ? ";

		if (parametresOrganisme.getId().intValue() > 0){
			query += " where `id` = "+parametresOrganisme.getId().intValue();
		}

		try{
			Object[] params = {parametresOrganisme.getId(), parametresOrganisme.getDesignationOrganisme().getBytes("UTF-8"), parametresOrganisme.getRaisonSocial().getBytes("UTF-8"), parametresOrganisme.getCapitalSocial().getBytes("UTF-8"), parametresOrganisme.getNumRC().getBytes("UTF-8"), parametresOrganisme.getNumCB().getBytes("UTF-8"), parametresOrganisme.getIdentificationFiscale().getBytes("UTF-8"), parametresOrganisme.getNumArticle().getBytes("UTF-8"), parametresOrganisme.getNIS().getBytes("UTF-8"), parametresOrganisme.getAdresse().getBytes("UTF-8"), parametresOrganisme.getBoitePostale().getBytes("UTF-8"), parametresOrganisme.getNumTel().getBytes("UTF-8"), parametresOrganisme.getNumFax().getBytes("UTF-8"), parametresOrganisme.getEmail().getBytes("UTF-8"), parametresOrganisme.getDescriptif().getBytes("UTF-8"), parametresOrganisme.getIdPhoto() == 0 ? null : parametresOrganisme.getIdPhoto()};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static int write(ParametresOrganisme parametresOrganisme) {
		String query = getSQLWriting(parametresOrganisme);
//		SGBDConfig.getInstance().sendQuery("alter table `parametresOrganisme` auto_increment =1");
		SGBDConfig.getInstance().sendQuery(query);
		if(parametresOrganisme.getId() == 0) {
			ResultSet data = SGBDConfig.getInstance().sendQueryForResults("select id from `parametresOrganisme` order by id desc limit 1");
			try{
				if (data.next()){
					parametresOrganisme.setId(data.getInt("id"));
				}
			} catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
		}
		return parametresOrganisme.getId();
	}

	public static int writeByPreparedStatement(ParametresOrganisme parametresOrganisme) {
		utils.SGBDConfig.InsertUpdateSQLQueries writingQuerie = getSQLWritingByPreparedStatement(parametresOrganisme);
//		SGBDConfig.getInstance().sendQuery("alter table `parametresOrganisme` auto_increment =1");
		utils.SGBDConfig.getInstance().sendQueriesByPreparedStatement(writingQuerie);
		
		if(parametresOrganisme.getId().intValue() == 0) {
			ResultSet data = SGBDConfig.getInstance().sendQueryForResults("select id from `parametresOrganisme` order by id desc limit 1");
			try{
				if (data.next()){
					parametresOrganisme.setId(data.getInt("id"));
				}
			} catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
		}
		return parametresOrganisme.getId();
	}

	public static void delete(ParametresOrganisme parametresOrganisme){
		SGBDConfig.getInstance().sendQuery(getSQLDeleting(parametresOrganisme));
	}

	public static void deleteAll(){
		SGBDConfig.getInstance().sendQuery(getSQLDeleting());
	}

	public static java.util.List<ParametresOrganisme> getListInstances(){
		return getListInstances("");
	}

	public static java.util.List<ParametresOrganisme> getListInstances(String whereCondition){
		String query = "select * from parametresOrganisme";
		if (!whereCondition.trim().equals("")){
			if (!whereCondition.trim().equals("") && !whereCondition.trim().toLowerCase().startsWith("where")){
				query += " where ";
			}
			query += " "+whereCondition;
		}
		return getListInstancesBySQLQuery(query);
	}

	public static java.util.List<ParametresOrganisme> getListInstancesBySQLQuery(String sqlQuery){
		java.util.List<ParametresOrganisme> list = new java.util.ArrayList<ParametresOrganisme>();
		
		Connection connection = SGBDConfig.getInstance().getConnexionWithoutSaving();
		ResultSet data = SGBDConfig.getInstance().sendQueryForResults(sqlQuery, connection);
		try{
			while (data.next()){
				ParametresOrganisme item = new ParametresOrganisme();
				item.setId(data.getInt("id"));
				item.setDesignationOrganisme(data.getString("designationOrganisme"));
				item.setRaisonSocial(data.getString("raisonSocial"));
				item.setCapitalSocial(data.getString("capitalSocial"));
				item.setNumRC(data.getString("numRC"));
				item.setNumCB(data.getString("numCB"));
				item.setIdentificationFiscale(data.getString("identificationFiscale"));
				item.setNumArticle(data.getString("numArticle"));
				item.setNIS(data.getString("NIS"));
				item.setAdresse(data.getString("adresse"));
				item.setBoitePostale(data.getString("boitePostale"));
				item.setNumTel(data.getString("numTel"));
				item.setNumFax(data.getString("numFax"));
				item.setEmail(data.getString("Email"));
				item.setDescriptif(data.getString("descriptif"));
				item.setIdPhoto(data.getInt("idPhoto"));
				
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

	public static String getSQLUpdateForDesignationOrganisme(ParametresOrganisme parametresOrganisme){
		String sqlQuery ="update `parametresOrganisme` SET ";
		sqlQuery += "`designationOrganisme`='"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getDesignationOrganisme())+"'";
		sqlQuery += "where id = "+parametresOrganisme.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForDesignationOrganismeByPreparedStatement(ParametresOrganisme parametresOrganisme){
		String query = "update `parametresOrganisme` set ";
		query += "`designationOrganisme` = ? ";
		query += "where id = "+parametresOrganisme.getId().intValue();
		
		try{
			Object[] params = {parametresOrganisme.getDesignationOrganisme().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForRaisonSocial(ParametresOrganisme parametresOrganisme){
		String sqlQuery ="update `parametresOrganisme` SET ";
		sqlQuery += "`raisonSocial`='"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getRaisonSocial())+"'";
		sqlQuery += "where id = "+parametresOrganisme.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForRaisonSocialByPreparedStatement(ParametresOrganisme parametresOrganisme){
		String query = "update `parametresOrganisme` set ";
		query += "`raisonSocial` = ? ";
		query += "where id = "+parametresOrganisme.getId().intValue();
		
		try{
			Object[] params = {parametresOrganisme.getRaisonSocial().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForCapitalSocial(ParametresOrganisme parametresOrganisme){
		String sqlQuery ="update `parametresOrganisme` SET ";
		sqlQuery += "`capitalSocial`='"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getCapitalSocial())+"'";
		sqlQuery += "where id = "+parametresOrganisme.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForCapitalSocialByPreparedStatement(ParametresOrganisme parametresOrganisme){
		String query = "update `parametresOrganisme` set ";
		query += "`capitalSocial` = ? ";
		query += "where id = "+parametresOrganisme.getId().intValue();
		
		try{
			Object[] params = {parametresOrganisme.getCapitalSocial().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForNumRC(ParametresOrganisme parametresOrganisme){
		String sqlQuery ="update `parametresOrganisme` SET ";
		sqlQuery += "`numRC`='"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getNumRC())+"'";
		sqlQuery += "where id = "+parametresOrganisme.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForNumRCByPreparedStatement(ParametresOrganisme parametresOrganisme){
		String query = "update `parametresOrganisme` set ";
		query += "`numRC` = ? ";
		query += "where id = "+parametresOrganisme.getId().intValue();
		
		try{
			Object[] params = {parametresOrganisme.getNumRC().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForNumCB(ParametresOrganisme parametresOrganisme){
		String sqlQuery ="update `parametresOrganisme` SET ";
		sqlQuery += "`numCB`='"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getNumCB())+"'";
		sqlQuery += "where id = "+parametresOrganisme.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForNumCBByPreparedStatement(ParametresOrganisme parametresOrganisme){
		String query = "update `parametresOrganisme` set ";
		query += "`numCB` = ? ";
		query += "where id = "+parametresOrganisme.getId().intValue();
		
		try{
			Object[] params = {parametresOrganisme.getNumCB().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForIdentificationFiscale(ParametresOrganisme parametresOrganisme){
		String sqlQuery ="update `parametresOrganisme` SET ";
		sqlQuery += "`identificationFiscale`='"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getIdentificationFiscale())+"'";
		sqlQuery += "where id = "+parametresOrganisme.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIdentificationFiscaleByPreparedStatement(ParametresOrganisme parametresOrganisme){
		String query = "update `parametresOrganisme` set ";
		query += "`identificationFiscale` = ? ";
		query += "where id = "+parametresOrganisme.getId().intValue();
		
		try{
			Object[] params = {parametresOrganisme.getIdentificationFiscale().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForNumArticle(ParametresOrganisme parametresOrganisme){
		String sqlQuery ="update `parametresOrganisme` SET ";
		sqlQuery += "`numArticle`='"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getNumArticle())+"'";
		sqlQuery += "where id = "+parametresOrganisme.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForNumArticleByPreparedStatement(ParametresOrganisme parametresOrganisme){
		String query = "update `parametresOrganisme` set ";
		query += "`numArticle` = ? ";
		query += "where id = "+parametresOrganisme.getId().intValue();
		
		try{
			Object[] params = {parametresOrganisme.getNumArticle().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForNIS(ParametresOrganisme parametresOrganisme){
		String sqlQuery ="update `parametresOrganisme` SET ";
		sqlQuery += "`NIS`='"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getNIS())+"'";
		sqlQuery += "where id = "+parametresOrganisme.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForNISByPreparedStatement(ParametresOrganisme parametresOrganisme){
		String query = "update `parametresOrganisme` set ";
		query += "`NIS` = ? ";
		query += "where id = "+parametresOrganisme.getId().intValue();
		
		try{
			Object[] params = {parametresOrganisme.getNIS().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForAdresse(ParametresOrganisme parametresOrganisme){
		String sqlQuery ="update `parametresOrganisme` SET ";
		sqlQuery += "`adresse`='"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getAdresse())+"'";
		sqlQuery += "where id = "+parametresOrganisme.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForAdresseByPreparedStatement(ParametresOrganisme parametresOrganisme){
		String query = "update `parametresOrganisme` set ";
		query += "`adresse` = ? ";
		query += "where id = "+parametresOrganisme.getId().intValue();
		
		try{
			Object[] params = {parametresOrganisme.getAdresse().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForBoitePostale(ParametresOrganisme parametresOrganisme){
		String sqlQuery ="update `parametresOrganisme` SET ";
		sqlQuery += "`boitePostale`='"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getBoitePostale())+"'";
		sqlQuery += "where id = "+parametresOrganisme.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForBoitePostaleByPreparedStatement(ParametresOrganisme parametresOrganisme){
		String query = "update `parametresOrganisme` set ";
		query += "`boitePostale` = ? ";
		query += "where id = "+parametresOrganisme.getId().intValue();
		
		try{
			Object[] params = {parametresOrganisme.getBoitePostale().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForNumTel(ParametresOrganisme parametresOrganisme){
		String sqlQuery ="update `parametresOrganisme` SET ";
		sqlQuery += "`numTel`='"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getNumTel())+"'";
		sqlQuery += "where id = "+parametresOrganisme.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForNumTelByPreparedStatement(ParametresOrganisme parametresOrganisme){
		String query = "update `parametresOrganisme` set ";
		query += "`numTel` = ? ";
		query += "where id = "+parametresOrganisme.getId().intValue();
		
		try{
			Object[] params = {parametresOrganisme.getNumTel().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForNumFax(ParametresOrganisme parametresOrganisme){
		String sqlQuery ="update `parametresOrganisme` SET ";
		sqlQuery += "`numFax`='"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getNumFax())+"'";
		sqlQuery += "where id = "+parametresOrganisme.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForNumFaxByPreparedStatement(ParametresOrganisme parametresOrganisme){
		String query = "update `parametresOrganisme` set ";
		query += "`numFax` = ? ";
		query += "where id = "+parametresOrganisme.getId().intValue();
		
		try{
			Object[] params = {parametresOrganisme.getNumFax().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForEmail(ParametresOrganisme parametresOrganisme){
		String sqlQuery ="update `parametresOrganisme` SET ";
		sqlQuery += "`Email`='"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getEmail())+"'";
		sqlQuery += "where id = "+parametresOrganisme.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForEmailByPreparedStatement(ParametresOrganisme parametresOrganisme){
		String query = "update `parametresOrganisme` set ";
		query += "`Email` = ? ";
		query += "where id = "+parametresOrganisme.getId().intValue();
		
		try{
			Object[] params = {parametresOrganisme.getEmail().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForDescriptif(ParametresOrganisme parametresOrganisme){
		String sqlQuery ="update `parametresOrganisme` SET ";
		sqlQuery += "`descriptif`='"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getDescriptif())+"'";
		sqlQuery += "where id = "+parametresOrganisme.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForDescriptifByPreparedStatement(ParametresOrganisme parametresOrganisme){
		String query = "update `parametresOrganisme` set ";
		query += "`descriptif` = ? ";
		query += "where id = "+parametresOrganisme.getId().intValue();
		
		try{
			Object[] params = {parametresOrganisme.getDescriptif().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForIdPhoto(ParametresOrganisme parametresOrganisme){
		String sqlQuery ="update `parametresOrganisme` SET ";
		sqlQuery += "`idPhoto`="+(parametresOrganisme.getIdPhoto() > 0 ? "'"+parametresOrganisme.getIdPhoto()+"' " : "NULL ");
		sqlQuery += "where id = "+parametresOrganisme.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIdPhotoByPreparedStatement(ParametresOrganisme parametresOrganisme){
		String query = "update `parametresOrganisme` set ";
		query += "`idPhoto` = ? ";
		query += "where id = "+parametresOrganisme.getId().intValue();
		
		try{
			Object[] params = {parametresOrganisme.getIdPhoto() > 0 ? parametresOrganisme.getIdPhoto() : null};
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
			ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("select group_concat(id) from `parametresOrganisme` "+whereCondition);
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
			ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("select count(id) from `parametresOrganisme` "+whereCondition);
			if (data.next())
				return data.getInt(1);
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		return 0;
	}

	public static String getSQLTuple(ParametresOrganisme parametresOrganisme){
		String sqlTuple = "";
		sqlTuple += "INSERT INTO `parametresOrganisme` ( `id`, `designationOrganisme`, `raisonSocial`, `capitalSocial`, `numRC`, `numCB`, `identificationFiscale`, `numArticle`, `NIS`, `adresse`, `boitePostale`, `numTel`, `numFax`, `Email`, `descriptif`, `idPhoto`) VALUES";
		sqlTuple += "( '"+parametresOrganisme.getId()+"', '"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getDesignationOrganisme())+"', '"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getRaisonSocial())+"', '"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getCapitalSocial())+"', '"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getNumRC())+"', '"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getNumCB())+"', '"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getIdentificationFiscale())+"', '"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getNumArticle())+"', '"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getNIS())+"', '"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getAdresse())+"', '"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getBoitePostale())+"', '"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getNumTel())+"', '"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getNumFax())+"', '"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getEmail())+"', '"+utils.StringUtils.addSQLSlashes(parametresOrganisme.getDescriptif())+"', "+((parametresOrganisme.getIdPhoto() == 0) ? "NULL" : "'"+parametresOrganisme.getIdPhoto()+"'")+");\n";

		return sqlTuple;
	}

	public static String getSQLStructure(boolean foreignKeyCheck, boolean addDropTable){
		String sqlStruct = "";
		if (foreignKeyCheck){
			sqlStruct += "SET FOREIGN_KEY_CHECKS=0;\n";
		}
		sqlStruct += "SET SQL_MODE=\"NO_AUTO_VALUE_ON_ZERO\";\n\n";
		
		if (addDropTable){
			sqlStruct += "DROP TABLE IF EXISTS `parametresOrganisme`;\n";
		}

		sqlStruct += "CREATE TABLE IF NOT EXISTS `parametresOrganisme` (\n";
		sqlStruct += "  `id` int(11) NOT NULL,\n";
		sqlStruct += "  `designationOrganisme` varchar(200) NOT NULL COMMENT 'Désignation de l''Organisme',\n";
		sqlStruct += "  `raisonSocial` varchar(100) NOT NULL COMMENT 'Raison Social',\n";
		sqlStruct += "  `capitalSocial` varchar(30) NOT NULL COMMENT 'Capital Social',\n";
		sqlStruct += "  `numRC` varchar(50) NOT NULL COMMENT 'N? de Registre Commercial',\n";
		sqlStruct += "  `numCB` varchar(50) NOT NULL COMMENT 'N? du Compte Bancaire',\n";
		sqlStruct += "  `identificationFiscale` varchar(50) NOT NULL COMMENT 'Identification Fiscale',\n";
		sqlStruct += "  `numArticle` varchar(50) NOT NULL COMMENT 'N? Article',\n";
		sqlStruct += "  `NIS` varchar(20) NOT NULL COMMENT 'NIS',\n";
		sqlStruct += "  `adresse` varchar(100) NOT NULL COMMENT 'Adresse',\n";
		sqlStruct += "  `boitePostale` varchar(50) NOT NULL COMMENT 'Boite Postale',\n";
		sqlStruct += "  `numTel` varchar(17) NOT NULL COMMENT 'TEL',\n";
		sqlStruct += "  `numFax` varchar(17) NOT NULL COMMENT 'FAX',\n";
		sqlStruct += "  `Email` varchar(40) NOT NULL COMMENT 'Email',\n";
		sqlStruct += "  `descriptif` text NOT NULL COMMENT 'Descriptif de l''Organisme',\n";
		sqlStruct += "  `idPhoto` int(11) DEFAULT NULL COMMENT 'Logo de l''entreprerise',\n";
		sqlStruct += "  KEY `idPhoto` (`idPhoto`)\n";
		sqlStruct += ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='Paramètres de l''Organisme' AUTO_INCREMENT=1 ;\n";

		sqlStruct += "ALTER TABLE `parametresOrganisme`\n";
		sqlStruct += "  ADD CONSTRAINT `parametresOrganisme_ibfk_1` FOREIGN KEY (`idPhoto`) REFERENCES `photo` (`id`);\n";
		

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

	public static String getSQLContent(java.util.List<ParametresOrganisme> list, boolean foreignKeyCheck, boolean addDropTable){
		String sqlContent = getSQLStructure(foreignKeyCheck, addDropTable);
		
		sqlContent += "\n\n";
		for (ParametresOrganisme item : list){
			sqlContent += getSQLTuple(item);
		}
		
		if (foreignKeyCheck){
			sqlContent = "SET FOREIGN_KEY_CHECKS=0;\n" + sqlContent + "SET FOREIGN_KEY_CHECKS=1;";
		}
		
		return sqlContent;
	}

	public static String showSQLStructure(){
		try{
			java.sql.ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("show create table `parametresOrganisme`");
			if (data.next()){
				return data.getString(2);
			}
		}
		catch (Exception e){
		}
		
		return "";
	}

	public static String getTableName(){
		return "parametresOrganisme";
	}
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}