package models.daos.server;

import java.sql.Connection;
import java.sql.ResultSet;

import utils.SGBDConfig;

import models.beans.Client;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This classe maps the Table <i><b>client</b></i> in the Database. 
 *	<br/>It contains attributs which represents the columns of the table,
 *	<br/>and Methods for geting one row by id, updating, inserting and deleting rows.
 */

public abstract class DAOClient {
	public static Client getClient(int id) {
		return getClient(id, null);
	}

	public static Client getClient(int id, Connection connection) {
		Client client = new Client();
		if (id == 0)
			return client;
		String query = "select * from `client` where `id`="+id+" limit 1";
		boolean toCloseConnection = false;
		if (connection == null){
			connection = SGBDConfig.getInstance().getConnexionWithoutSaving();
			toCloseConnection = true;
		}
		ResultSet data = SGBDConfig.getInstance().sendQueryForResults(query, connection);
		try{
			if (data.next()){
				client.setId(data.getInt("id"));
				client.setTypeClient(Client.TypeClient.getByValue(data.getString("typeClient")));
				client.setNom(data.getString("nom"));
				client.setPrenom(data.getString("prenom"));
				client.setDateNaiss(data.getDate("dateNaiss"));
				client.setAdresse(data.getString("adresse"));
				client.setNationalite(data.getString("nationalite"));
				client.setTelephone(data.getString("telephone"));
				client.setTypeCarte(Client.TypeCarte.getByValue(data.getString("typeCarte")));
				client.setNumCarte(data.getString("numCarte"));
				client.setProfession(data.getString("profession"));
				client.setAutreTypeClient(data.getString("autreTypeClient"));
				client.setAutreTypeCarte(data.getString("autreTypeCarte"));
				client.setLieuDeNaiss(data.getString("lieuDeNaiss"));
				client.setCivilite(Client.Civilite.getByValue(data.getString("civilite")));
			}
			
			if (toCloseConnection){
				connection.close();
			}
		}
		catch (Exception e){
			utils.StringUtils.printDebug(e);
		}

		return client;
	}

	public static String getSQLDeleting(Client client) {
		String query = "delete from `client` where `id` = "+client.getId();
		return query;
	}

	public static String getSQLDeleting() {
		String query = "delete from `client` where 1 ";
		return query;
	}

	public static String getSQLWriting(Client client) {
		String query;
		if (client.getId().intValue() == 0){
			query = "insert into `client` (`id`, `typeClient`, `nom`, `prenom`, `dateNaiss`, `adresse`, `nationalite`, `telephone`, `typeCarte`, `numCarte`, `profession`, `autreTypeClient`, `autreTypeCarte`, `lieuDeNaiss`, `civilite`)";
			query += " values ('"+client.getId()+"', '"+client.getTypeClient().getValue()+"', '"+utils.StringUtils.addSQLSlashes(client.getNom())+"', '"+utils.StringUtils.addSQLSlashes(client.getPrenom())+"', '"+client.getDateNaiss()+"', '"+utils.StringUtils.addSQLSlashes(client.getAdresse())+"', '"+utils.StringUtils.addSQLSlashes(client.getNationalite())+"', '"+utils.StringUtils.addSQLSlashes(client.getTelephone())+"', '"+client.getTypeCarte().getValue()+"', '"+utils.StringUtils.addSQLSlashes(client.getNumCarte())+"', '"+utils.StringUtils.addSQLSlashes(client.getProfession())+"', '"+utils.StringUtils.addSQLSlashes(client.getAutreTypeClient())+"', '"+utils.StringUtils.addSQLSlashes(client.getAutreTypeCarte())+"', '"+utils.StringUtils.addSQLSlashes(client.getLieuDeNaiss())+"', '"+client.getCivilite().getValue()+"')";
		}
		else{
			query ="update `client` SET ";
			query += "id='"+client.getId()+"',";
			query += "`typeClient`='"+client.getTypeClient().getValue()+"',";
			query += "`nom`='"+utils.StringUtils.addSQLSlashes(client.getNom())+"',";
			query += "`prenom`='"+utils.StringUtils.addSQLSlashes(client.getPrenom())+"',";
			query += "dateNaiss='"+client.getDateNaiss()+"',";
			query += "`adresse`='"+utils.StringUtils.addSQLSlashes(client.getAdresse())+"',";
			query += "`nationalite`='"+utils.StringUtils.addSQLSlashes(client.getNationalite())+"',";
			query += "`telephone`='"+utils.StringUtils.addSQLSlashes(client.getTelephone())+"',";
			query += "`typeCarte`='"+client.getTypeCarte().getValue()+"',";
			query += "`numCarte`='"+utils.StringUtils.addSQLSlashes(client.getNumCarte())+"',";
			query += "`profession`='"+utils.StringUtils.addSQLSlashes(client.getProfession())+"',";
			query += "`autreTypeClient`='"+utils.StringUtils.addSQLSlashes(client.getAutreTypeClient())+"',";
			query += "`autreTypeCarte`='"+utils.StringUtils.addSQLSlashes(client.getAutreTypeCarte())+"',";
			query += "`lieuDeNaiss`='"+utils.StringUtils.addSQLSlashes(client.getLieuDeNaiss())+"',";
			query += "`civilite`='"+client.getCivilite().getValue()+"'";
			query += " where `id`="+client.getId();
		}
		return query;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLWritingByPreparedStatement(Client client) {
		String query;
		if (client.getId().intValue() == 0){
			query = "insert into ";
		}
		else{
			query = "update ";
		}

		query += " `client` set ";
		query += "`id` = ?, ";
		query += "`typeClient` = ?, ";
		query += "`nom` = ?, ";
		query += "`prenom` = ?, ";
		query += "`dateNaiss` = ?, ";
		query += "`adresse` = ?, ";
		query += "`nationalite` = ?, ";
		query += "`telephone` = ?, ";
		query += "`typeCarte` = ?, ";
		query += "`numCarte` = ?, ";
		query += "`profession` = ?, ";
		query += "`autreTypeClient` = ?, ";
		query += "`autreTypeCarte` = ?, ";
		query += "`lieuDeNaiss` = ?, ";
		query += "`civilite` = ? ";

		if (client.getId().intValue() > 0){
			query += " where `id` = "+client.getId().intValue();
		}

		try{
			Object[] params = {client.getId(), client.getTypeClient().getValue().getBytes("UTF-8"), client.getNom().getBytes("UTF-8"), client.getPrenom().getBytes("UTF-8"), client.getDateNaiss().toString(), client.getAdresse().getBytes("UTF-8"), client.getNationalite().getBytes("UTF-8"), client.getTelephone().getBytes("UTF-8"), client.getTypeCarte().getValue().getBytes("UTF-8"), client.getNumCarte().getBytes("UTF-8"), client.getProfession().getBytes("UTF-8"), client.getAutreTypeClient().getBytes("UTF-8"), client.getAutreTypeCarte().getBytes("UTF-8"), client.getLieuDeNaiss().getBytes("UTF-8"), client.getCivilite().getValue().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static int write(Client client) {
		String query = getSQLWriting(client);
//		SGBDConfig.getInstance().sendQuery("alter table `client` auto_increment =1");
		SGBDConfig.getInstance().sendQuery(query);
		if(client.getId() == 0) {
			ResultSet data = SGBDConfig.getInstance().sendQueryForResults("select id from `client` order by id desc limit 1");
			try{
				if (data.next()){
					client.setId(data.getInt("id"));
				}
			} catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
		}
		return client.getId();
	}

	public static int writeByPreparedStatement(Client client) {
		utils.SGBDConfig.InsertUpdateSQLQueries writingQuerie = getSQLWritingByPreparedStatement(client);
//		SGBDConfig.getInstance().sendQuery("alter table `client` auto_increment =1");
		utils.SGBDConfig.getInstance().sendQueriesByPreparedStatement(writingQuerie);
		
		if(client.getId().intValue() == 0) {
			ResultSet data = SGBDConfig.getInstance().sendQueryForResults("select id from `client` order by id desc limit 1");
			try{
				if (data.next()){
					client.setId(data.getInt("id"));
				}
			} catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
		}
		return client.getId();
	}

	public static void delete(Client client){
		SGBDConfig.getInstance().sendQuery(getSQLDeleting(client));
	}

	public static void deleteAll(){
		SGBDConfig.getInstance().sendQuery(getSQLDeleting());
	}

	public static java.util.List<Client> getListInstances(){
		return getListInstances("");
	}

	public static java.util.List<Client> getListInstances(String whereCondition){
		String query = "select * from client";
		if (!whereCondition.trim().equals("")){
			if (!whereCondition.trim().equals("") && !whereCondition.trim().toLowerCase().startsWith("where")){
				query += " where ";
			}
			query += " "+whereCondition;
		}
		return getListInstancesBySQLQuery(query);
	}

	public static java.util.List<Client> getListInstancesBySQLQuery(String sqlQuery){
		java.util.List<Client> list = new java.util.ArrayList<Client>();
		
		Connection connection = SGBDConfig.getInstance().getConnexionWithoutSaving();
		ResultSet data = SGBDConfig.getInstance().sendQueryForResults(sqlQuery, connection);
		try{
			while (data.next()){
				Client item = new Client();
				item.setId(data.getInt("id"));
				item.setTypeClient(Client.TypeClient.getByValue(data.getString("typeClient")));
				item.setNom(data.getString("nom"));
				item.setPrenom(data.getString("prenom"));
				item.setDateNaiss(data.getDate("dateNaiss"));
				item.setAdresse(data.getString("adresse"));
				item.setNationalite(data.getString("nationalite"));
				item.setTelephone(data.getString("telephone"));
				item.setTypeCarte(Client.TypeCarte.getByValue(data.getString("typeCarte")));
				item.setNumCarte(data.getString("numCarte"));
				item.setProfession(data.getString("profession"));
				item.setAutreTypeClient(data.getString("autreTypeClient"));
				item.setAutreTypeCarte(data.getString("autreTypeCarte"));
				item.setLieuDeNaiss(data.getString("lieuDeNaiss"));
				item.setCivilite(Client.Civilite.getByValue(data.getString("civilite")));
				
				
				list.add(item);
			}
			connection.close();
		}
		catch(Exception e){
			utils.StringUtils.printDebug(e);
		}
		return list;
	}

	public static String getSQLUpdateForTypeClient(Client client){
		String sqlQuery ="update `client` SET ";
		sqlQuery += "`typeClient`='"+client.getTypeClient().getValue()+"' ";
		sqlQuery += "where id = "+client.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForTypeClientByPreparedStatement(Client client){
		String query = "update `client` set ";
		query += "`typeClient` = ? ";
		query += "where id = "+client.getId().intValue();
		
		try{
			Object[] params = {client.getTypeClient().getValue().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForNom(Client client){
		String sqlQuery ="update `client` SET ";
		sqlQuery += "`nom`='"+utils.StringUtils.addSQLSlashes(client.getNom())+"'";
		sqlQuery += "where id = "+client.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForNomByPreparedStatement(Client client){
		String query = "update `client` set ";
		query += "`nom` = ? ";
		query += "where id = "+client.getId().intValue();
		
		try{
			Object[] params = {client.getNom().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForPrenom(Client client){
		String sqlQuery ="update `client` SET ";
		sqlQuery += "`prenom`='"+utils.StringUtils.addSQLSlashes(client.getPrenom())+"'";
		sqlQuery += "where id = "+client.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForPrenomByPreparedStatement(Client client){
		String query = "update `client` set ";
		query += "`prenom` = ? ";
		query += "where id = "+client.getId().intValue();
		
		try{
			Object[] params = {client.getPrenom().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForDateNaiss(Client client){
		String sqlQuery ="update `client` SET ";
		sqlQuery += "`dateNaiss`='"+client.getDateNaiss()+"'";
		sqlQuery += "where id = "+client.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForDateNaissByPreparedStatement(Client client){
		String query = "update `client` set ";
		query += "`dateNaiss` = ? ";
		query += "where id = "+client.getId().intValue();
		
		try{
			Object[] params = {client.getDateNaiss().toString()};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForAdresse(Client client){
		String sqlQuery ="update `client` SET ";
		sqlQuery += "`adresse`='"+utils.StringUtils.addSQLSlashes(client.getAdresse())+"'";
		sqlQuery += "where id = "+client.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForAdresseByPreparedStatement(Client client){
		String query = "update `client` set ";
		query += "`adresse` = ? ";
		query += "where id = "+client.getId().intValue();
		
		try{
			Object[] params = {client.getAdresse().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForNationalite(Client client){
		String sqlQuery ="update `client` SET ";
		sqlQuery += "`nationalite`='"+utils.StringUtils.addSQLSlashes(client.getNationalite())+"'";
		sqlQuery += "where id = "+client.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForNationaliteByPreparedStatement(Client client){
		String query = "update `client` set ";
		query += "`nationalite` = ? ";
		query += "where id = "+client.getId().intValue();
		
		try{
			Object[] params = {client.getNationalite().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForTelephone(Client client){
		String sqlQuery ="update `client` SET ";
		sqlQuery += "`telephone`='"+utils.StringUtils.addSQLSlashes(client.getTelephone())+"'";
		sqlQuery += "where id = "+client.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForTelephoneByPreparedStatement(Client client){
		String query = "update `client` set ";
		query += "`telephone` = ? ";
		query += "where id = "+client.getId().intValue();
		
		try{
			Object[] params = {client.getTelephone().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForTypeCarte(Client client){
		String sqlQuery ="update `client` SET ";
		sqlQuery += "`typeCarte`='"+client.getTypeCarte().getValue()+"' ";
		sqlQuery += "where id = "+client.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForTypeCarteByPreparedStatement(Client client){
		String query = "update `client` set ";
		query += "`typeCarte` = ? ";
		query += "where id = "+client.getId().intValue();
		
		try{
			Object[] params = {client.getTypeCarte().getValue().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForNumCarte(Client client){
		String sqlQuery ="update `client` SET ";
		sqlQuery += "`numCarte`='"+utils.StringUtils.addSQLSlashes(client.getNumCarte())+"'";
		sqlQuery += "where id = "+client.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForNumCarteByPreparedStatement(Client client){
		String query = "update `client` set ";
		query += "`numCarte` = ? ";
		query += "where id = "+client.getId().intValue();
		
		try{
			Object[] params = {client.getNumCarte().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForProfession(Client client){
		String sqlQuery ="update `client` SET ";
		sqlQuery += "`profession`='"+utils.StringUtils.addSQLSlashes(client.getProfession())+"'";
		sqlQuery += "where id = "+client.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForProfessionByPreparedStatement(Client client){
		String query = "update `client` set ";
		query += "`profession` = ? ";
		query += "where id = "+client.getId().intValue();
		
		try{
			Object[] params = {client.getProfession().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForAutreTypeClient(Client client){
		String sqlQuery ="update `client` SET ";
		sqlQuery += "`autreTypeClient`='"+utils.StringUtils.addSQLSlashes(client.getAutreTypeClient())+"'";
		sqlQuery += "where id = "+client.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForAutreTypeClientByPreparedStatement(Client client){
		String query = "update `client` set ";
		query += "`autreTypeClient` = ? ";
		query += "where id = "+client.getId().intValue();
		
		try{
			Object[] params = {client.getAutreTypeClient().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForAutreTypeCarte(Client client){
		String sqlQuery ="update `client` SET ";
		sqlQuery += "`autreTypeCarte`='"+utils.StringUtils.addSQLSlashes(client.getAutreTypeCarte())+"'";
		sqlQuery += "where id = "+client.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForAutreTypeCarteByPreparedStatement(Client client){
		String query = "update `client` set ";
		query += "`autreTypeCarte` = ? ";
		query += "where id = "+client.getId().intValue();
		
		try{
			Object[] params = {client.getAutreTypeCarte().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForLieuDeNaiss(Client client){
		String sqlQuery ="update `client` SET ";
		sqlQuery += "`lieuDeNaiss`='"+utils.StringUtils.addSQLSlashes(client.getLieuDeNaiss())+"'";
		sqlQuery += "where id = "+client.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForLieuDeNaissByPreparedStatement(Client client){
		String query = "update `client` set ";
		query += "`lieuDeNaiss` = ? ";
		query += "where id = "+client.getId().intValue();
		
		try{
			Object[] params = {client.getLieuDeNaiss().getBytes("UTF-8")};
			utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement ps = new utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement(query, params);
			utils.SGBDConfig.InsertUpdateSQLQueries iuSQLQuery = new utils.SGBDConfig.InsertUpdateSQLQueries(ps);
			
			return iuSQLQuery;
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		
		return null;
	}

	public static String getSQLUpdateForCivilite(Client client){
		String sqlQuery ="update `client` SET ";
		sqlQuery += "`civilite`='"+client.getCivilite().getValue()+"' ";
		sqlQuery += "where id = "+client.getId().intValue();
		return sqlQuery;
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForCiviliteByPreparedStatement(Client client){
		String query = "update `client` set ";
		query += "`civilite` = ? ";
		query += "where id = "+client.getId().intValue();
		
		try{
			Object[] params = {client.getCivilite().getValue().getBytes("UTF-8")};
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
			ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("select group_concat(id) from `client` "+whereCondition);
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
			ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("select count(id) from `client` "+whereCondition);
			if (data.next())
				return data.getInt(1);
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		return 0;
	}

	public static String getSQLTuple(Client client){
		String sqlTuple = "";
		sqlTuple += "INSERT INTO `client` ( `id`, `typeClient`, `nom`, `prenom`, `dateNaiss`, `adresse`, `nationalite`, `telephone`, `typeCarte`, `numCarte`, `profession`, `autreTypeClient`, `autreTypeCarte`, `lieuDeNaiss`, `civilite`) VALUES";
		sqlTuple += "( '"+client.getId()+"', '"+utils.StringUtils.addSQLSlashes(client.getTypeClient().getValue())+"', '"+utils.StringUtils.addSQLSlashes(client.getNom())+"', '"+utils.StringUtils.addSQLSlashes(client.getPrenom())+"', '"+client.getDateNaiss()+"', '"+utils.StringUtils.addSQLSlashes(client.getAdresse())+"', '"+utils.StringUtils.addSQLSlashes(client.getNationalite())+"', '"+utils.StringUtils.addSQLSlashes(client.getTelephone())+"', '"+utils.StringUtils.addSQLSlashes(client.getTypeCarte().getValue())+"', '"+utils.StringUtils.addSQLSlashes(client.getNumCarte())+"', '"+utils.StringUtils.addSQLSlashes(client.getProfession())+"', '"+utils.StringUtils.addSQLSlashes(client.getAutreTypeClient())+"', '"+utils.StringUtils.addSQLSlashes(client.getAutreTypeCarte())+"', '"+utils.StringUtils.addSQLSlashes(client.getLieuDeNaiss())+"', '"+utils.StringUtils.addSQLSlashes(client.getCivilite().getValue())+"');\n";

		return sqlTuple;
	}

	public static String getSQLStructure(boolean foreignKeyCheck, boolean addDropTable){
		String sqlStruct = "";
		if (foreignKeyCheck){
			sqlStruct += "SET FOREIGN_KEY_CHECKS=0;\n";
		}
		sqlStruct += "SET SQL_MODE=\"NO_AUTO_VALUE_ON_ZERO\";\n\n";
		
		if (addDropTable){
			sqlStruct += "DROP TABLE IF EXISTS `client`;\n";
		}

		sqlStruct += "CREATE TABLE IF NOT EXISTS `client` (\n";
		sqlStruct += "  `id` int(11) NOT NULL AUTO_INCREMENT,\n";
		sqlStruct += "  `typeClient` enum('Hébergé','Non Hébergé') NOT NULL,\n";
		sqlStruct += "  `nom` varchar(50) NOT NULL,\n";
		sqlStruct += "  `prenom` varchar(50) NOT NULL,\n";
		sqlStruct += "  `dateNaiss` date NOT NULL,\n";
		sqlStruct += "  `adresse` varchar(100) NOT NULL,\n";
		sqlStruct += "  `nationalite` varchar(50) NOT NULL,\n";
		sqlStruct += "  `telephone` varchar(20) NOT NULL,\n";
		sqlStruct += "  `typeCarte` enum('CN','PC','Passeport') NOT NULL,\n";
		sqlStruct += "  `numCarte` varchar(10) NOT NULL,\n";
		sqlStruct += "  `profession` varchar(50) NOT NULL,\n";
		sqlStruct += "  `autreTypeClient` varchar(50) NOT NULL,\n";
		sqlStruct += "  `autreTypeCarte` varchar(50) NOT NULL,\n";
		sqlStruct += "  `lieuDeNaiss` varchar(50) NOT NULL,\n";
		sqlStruct += "  `civilite` enum('Mr.','Mme.','Mlle.') NOT NULL,\n";
		sqlStruct += "  PRIMARY KEY (`id`)\n";
		sqlStruct += ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='' AUTO_INCREMENT=1 ;\n";
		

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

	public static String getSQLContent(java.util.List<Client> list, boolean foreignKeyCheck, boolean addDropTable){
		String sqlContent = getSQLStructure(foreignKeyCheck, addDropTable);
		
		sqlContent += "\n\n";
		for (Client item : list){
			sqlContent += getSQLTuple(item);
		}
		
		if (foreignKeyCheck){
			sqlContent = "SET FOREIGN_KEY_CHECKS=0;\n" + sqlContent + "SET FOREIGN_KEY_CHECKS=1;";
		}
		
		return sqlContent;
	}

	public static String showSQLStructure(){
		try{
			java.sql.ResultSet data = utils.SGBDConfig.getInstance().sendQueryForResults("show create table `client`");
			if (data.next()){
				return data.getString(2);
			}
		}
		catch (Exception e){
		}
		
		return "";
	}

	public static String getTableName(){
		return "client";
	}
	
	public static java.util.List<models.beans.ClientConsommeService> getListOfClientConsommeServicesClient(models.beans.Client client){
		java.util.List<models.beans.ClientConsommeService> listOfClientConsommeServicesClient = models.daos.server.DAOClientConsommeService.getListInstances("where idClient='"+client.getId().intValue()+"'");
		return listOfClientConsommeServicesClient;
	}
	
	public static java.util.List<models.beans.ClientReseveChambre> getListOfClientReseveChambresClient(models.beans.Client client){
		java.util.List<models.beans.ClientReseveChambre> listOfClientReseveChambresClient = models.daos.server.DAOClientReseveChambre.getListInstances("where idClient='"+client.getId().intValue()+"'");
		return listOfClientReseveChambresClient;
	}
	
	public static java.util.List<models.beans.Facture> getListOfFacturesClient(models.beans.Client client){
		java.util.List<models.beans.Facture> listOfFacturesClient = models.daos.server.DAOFacture.getListInstances("where idClient='"+client.getId().intValue()+"'");
		return listOfFacturesClient;
	}
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}