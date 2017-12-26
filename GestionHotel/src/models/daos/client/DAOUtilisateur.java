package models.daos.client;

import models.beans.Utilisateur;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>Remote Access for DAO
 */

public abstract class DAOUtilisateur {
	public static Utilisateur getUtilisateur(int id) {
		if (id == 0)
			return new Utilisateur();
		String query = "select * from `utilisateur` where `id`="+id+" limit 1";
		return (Utilisateur)communication.SocketCommunicator.getInstance().sendQuery(query);
	}

	public static String getSQLDeleting(Utilisateur utilisateur) {
		return models.daos.server.DAOUtilisateur.getSQLDeleting(utilisateur);
	}

	public static String getSQLDeleting() {
		return models.daos.server.DAOUtilisateur.getSQLDeleting();
	}

	public static String getSQLWriting(Utilisateur utilisateur) {
		return models.daos.server.DAOUtilisateur.getSQLWriting(utilisateur);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLWritingByPreparedStatement(Utilisateur utilisateur) {
		return models.daos.server.DAOUtilisateur.getSQLWritingByPreparedStatement(utilisateur);
	}

	public static int write(Utilisateur utilisateur) {
		int id = (Integer)communication.SocketCommunicator.getInstance().sendQuery(utilisateur);
		utilisateur.setId(id);
		return id;
	}

	public static void writeByPreparedStatement(Utilisateur utilisateur) {
		utils.SGBDConfig.InsertUpdateSQLQueries writingQuery = getSQLWritingByPreparedStatement(utilisateur);
		communication.SocketCommunicator.getInstance().sendQuery(writingQuery);
	}

	public static void delete(Utilisateur utilisateur){
		communication.SocketCommunicator.getInstance().sendQuery(getSQLDeleting(utilisateur));
	}

	public static void deleteAll(){
		communication.SocketCommunicator.getInstance().sendQuery(getSQLDeleting());
	}

	public static java.util.List<Utilisateur> getListInstances(){
		return getListInstances("");
	}

	public static java.util.List<Utilisateur> getListInstances(String whereCondition){
		String query = "select * from utilisateur";
		if (!whereCondition.trim().equals("")){
			if (!whereCondition.trim().toLowerCase().equals("") && !whereCondition.trim().toLowerCase().startsWith("where")){
				query += " where ";
			}
			query += " "+whereCondition;
		}
		return getListInstancesBySQLQuery(query);
	}

@SuppressWarnings("unchecked")
	public static java.util.List<Utilisateur> getListInstancesBySQLQuery(String sqlQuery){
		java.util.List<Utilisateur> list = new java.util.ArrayList<Utilisateur>();
		Object response = communication.SocketCommunicator.getInstance().sendQuery(sqlQuery);
		if (response != null){
			if (response instanceof java.util.List<?>){
				list = (java.util.List<Utilisateur>)response;
			}
			else{
				list.add((Utilisateur)response);
			}
		}
		return list;
	}

	public static String getSQLUpdateForNom(Utilisateur utilisateur){
		return models.daos.server.DAOUtilisateur.getSQLUpdateForNom(utilisateur);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForNomByPreparedStatement(Utilisateur utilisateur){
		return models.daos.server.DAOUtilisateur.getSQLUpdateForNomByPreparedStatement(utilisateur);
	}

	public static void updateNom(Utilisateur utilisateur){
		String sqlUpdate = getSQLUpdateForNom(utilisateur);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForPrenom(Utilisateur utilisateur){
		return models.daos.server.DAOUtilisateur.getSQLUpdateForPrenom(utilisateur);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForPrenomByPreparedStatement(Utilisateur utilisateur){
		return models.daos.server.DAOUtilisateur.getSQLUpdateForPrenomByPreparedStatement(utilisateur);
	}

	public static void updatePrenom(Utilisateur utilisateur){
		String sqlUpdate = getSQLUpdateForPrenom(utilisateur);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForCivilite(Utilisateur utilisateur){
		return models.daos.server.DAOUtilisateur.getSQLUpdateForCivilite(utilisateur);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForCiviliteByPreparedStatement(Utilisateur utilisateur){
		return models.daos.server.DAOUtilisateur.getSQLUpdateForCiviliteByPreparedStatement(utilisateur);
	}

	public static void updateCivilite(Utilisateur utilisateur){
		String sqlUpdate = getSQLUpdateForCivilite(utilisateur);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForDateNaissance(Utilisateur utilisateur){
		return models.daos.server.DAOUtilisateur.getSQLUpdateForDateNaissance(utilisateur);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForDateNaissanceByPreparedStatement(Utilisateur utilisateur){
		return models.daos.server.DAOUtilisateur.getSQLUpdateForDateNaissanceByPreparedStatement(utilisateur);
	}

	public static void updateDateNaissance(Utilisateur utilisateur){
		String sqlUpdate = getSQLUpdateForDateNaissance(utilisateur);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForLieuNaissance(Utilisateur utilisateur){
		return models.daos.server.DAOUtilisateur.getSQLUpdateForLieuNaissance(utilisateur);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForLieuNaissanceByPreparedStatement(Utilisateur utilisateur){
		return models.daos.server.DAOUtilisateur.getSQLUpdateForLieuNaissanceByPreparedStatement(utilisateur);
	}

	public static void updateLieuNaissance(Utilisateur utilisateur){
		String sqlUpdate = getSQLUpdateForLieuNaissance(utilisateur);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForLogin(Utilisateur utilisateur){
		return models.daos.server.DAOUtilisateur.getSQLUpdateForLogin(utilisateur);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForLoginByPreparedStatement(Utilisateur utilisateur){
		return models.daos.server.DAOUtilisateur.getSQLUpdateForLoginByPreparedStatement(utilisateur);
	}

	public static Utilisateur getUtilisateurByLogin(java.lang.String login){
		String sqlQuery ="select * from `utilisateur` where login = '"+login+"' limit 1";
		Object response = communication.SocketCommunicator.getInstance().sendQuery(sqlQuery);
		if (response != null && response instanceof Utilisateur){
			return ((Utilisateur)response);
		}
		return null;
	}

	public static void updateLogin(Utilisateur utilisateur){
		String sqlUpdate = getSQLUpdateForLogin(utilisateur);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForPassword(Utilisateur utilisateur){
		return models.daos.server.DAOUtilisateur.getSQLUpdateForPassword(utilisateur);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForPasswordByPreparedStatement(Utilisateur utilisateur){
		return models.daos.server.DAOUtilisateur.getSQLUpdateForPasswordByPreparedStatement(utilisateur);
	}

	public static void updatePassword(Utilisateur utilisateur){
		String sqlUpdate = getSQLUpdateForPassword(utilisateur);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForIdPhoto(Utilisateur utilisateur){
		return models.daos.server.DAOUtilisateur.getSQLUpdateForIdPhoto(utilisateur);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIdPhotoByPreparedStatement(Utilisateur utilisateur){
		return models.daos.server.DAOUtilisateur.getSQLUpdateForIdPhotoByPreparedStatement(utilisateur);
	}

	public static void updateIdPhoto(Utilisateur utilisateur){
		String sqlUpdate = getSQLUpdateForIdPhoto(utilisateur);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForIdRole(Utilisateur utilisateur){
		return models.daos.server.DAOUtilisateur.getSQLUpdateForIdRole(utilisateur);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIdRoleByPreparedStatement(Utilisateur utilisateur){
		return models.daos.server.DAOUtilisateur.getSQLUpdateForIdRoleByPreparedStatement(utilisateur);
	}

	public static void updateIdRole(Utilisateur utilisateur){
		String sqlUpdate = getSQLUpdateForIdRole(utilisateur);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getSQLUpdateForIdParametres(Utilisateur utilisateur){
		return models.daos.server.DAOUtilisateur.getSQLUpdateForIdParametres(utilisateur);
	}

	public static utils.SGBDConfig.InsertUpdateSQLQueries getSQLUpdateForIdParametresByPreparedStatement(Utilisateur utilisateur){
		return models.daos.server.DAOUtilisateur.getSQLUpdateForIdParametresByPreparedStatement(utilisateur);
	}

	public static void updateIdParametres(Utilisateur utilisateur){
		String sqlUpdate = getSQLUpdateForIdParametres(utilisateur);
		communication.SocketCommunicator.getInstance().sendQuery(sqlUpdate);
	}

	public static String getConcatIDs(){
		return getConcatIDs("");
	}

	public static String getConcatIDs(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return (String)communication.SocketCommunicator.getInstance().sendQuery("select group_concat(id) from `utilisateur` "+whereCondition);
	}

	public static int getCountInTable(){
		return getCountInTable("");
	}

	public static int getCountInTable(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return (Integer)communication.SocketCommunicator.getInstance().sendQuery("select count(id) from `utilisateur` "+whereCondition);
	}

	public static int getCountInTableFromServer(){
		return getCountInTableFromServer("");
	}

	public static int getCountInTableFromServer(String whereCondition){
		if (!whereCondition.toLowerCase().trim().startsWith("where") && !whereCondition.trim().equals("")){
			whereCondition = " where "+whereCondition;
		}
		return ((Integer)communication.SocketCommunicator.getInstance().sendQuery("select count(id) from `utilisateur` "+whereCondition)).intValue();
	}

	public static String getSQLTuple(Utilisateur utilisateur){
		return models.daos.server.DAOUtilisateur.getSQLTuple(utilisateur);
	}

	public static String getSQLStructure(boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOUtilisateur.getSQLStructure(foreignKeyCheck, addDropTable);
	}

	public static String getSQLStructure(boolean addDropTable){
		return models.daos.server.DAOUtilisateur.getSQLStructure(addDropTable);
	}

	public static String getSQLStructure(){
		return getSQLStructure(true);
	}

	public static String getSQLContent(boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOUtilisateur.getSQLContent(foreignKeyCheck, addDropTable);
	}

	public static String getSQLContent(java.util.List<Utilisateur> list, boolean foreignKeyCheck, boolean addDropTable){
		return models.daos.server.DAOUtilisateur.getSQLContent(list, foreignKeyCheck, addDropTable);
	}

	public static String showSQLStructure(){
		return models.daos.server.DAOUtilisateur.showSQLStructure();
	}

	public static String getTableName(){
		return models.daos.server.DAOUtilisateur.getTableName();
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
		Object response = communication.SocketCommunicator.getInstance().sendQuery(query);
		if (response == null || response.equals("") || !(response instanceof Utilisateur)){
			String erreur = "";
			if (response == null){
				erreur = "Erreur de connexion au Serveur, Veuillez réessayer dans un moment ...";
			}
			else {
				erreur = "Erreur d'authentification : Login / mot de passe erroné(s) ...";
			}
			user.addError(erreur);
			if (connexion != null){
				connexion.setDateDeConnexion(utils.StringUtils.getTimestampFromString((String)communication.SocketCommunicator.getInstance().sendQuery("getDateTimeFromServer")));
				connexion.setDateDeDeconnexion(connexion.getDateDeConnexion());
				connexion.setMotifError(erreur);
				
//				communication.SocketCommunicator.getInstance().sendQuery(models.daos.client.DAOConnexionsLog.getSQLWriting(connexion));
				DAOConnexionsLog.write(connexion);
				controllers.ConnexionsLog.addEvent(connexion, "Erreur de connexion au Système avec login : "+login+". Motif : "+erreur);
			}
			return user;
		}
		user = (Utilisateur) response;
		if (connexion != null){
			connexion.setDateDeConnexion(utils.StringUtils.getTimestampFromString((String)communication.SocketCommunicator.getInstance().sendQuery("getDateTimeFromServer")));
			connexion.setConnexionAccepted(models.beans.ConnexionsLog.ConnexionAccepted.oui);
			connexion.setUtilisateur(user);
			connexion.setId((Integer)communication.SocketCommunicator.getInstance().sendQuery(connexion));
			
			user.setConnexion(connexion);
			controllers.ConnexionsLog.addEvent(user.getConnexion(), "Connexion au Système");
		}
		return user;
	}
	
	public static void deconnecter(Utilisateur user){
		if (user.getConnexion() == null){
			return;
		}
		
		user.getConnexion().setDateDeDeconnexion(utils.StringUtils.getTimestampFromString((String)communication.SocketCommunicator.getInstance().sendQuery("getDateTimeFromServer")));
		communication.SocketCommunicator.getInstance().sendQuery(user.getConnexion());
		
		user.setConnexion(null);
	}
	
	public static boolean modifierPassword(String login, String oldPassword, String newPassword){
		Utilisateur user = connecter(login, oldPassword, null);
		if (user.getErrors().size() > 0){
			return false;
		}
		
		user.setPassword(utils.StringUtils.encodeMD5(newPassword));
		user.setId((Integer)communication.SocketCommunicator.getInstance().sendQuery(user));
		
		controllers.ConnexionsLog.addEvent(user.getConnexion(), "Déconnexion du Système");		
		return true;
	}
	
	public static java.util.List<models.beans.ConnexionsLog> getListOfConnexionsLogsUtilisateur(models.beans.Utilisateur utilisateur){
		java.util.List<models.beans.ConnexionsLog> listOfConnexionsLogsUtilisateur = models.daos.client.DAOConnexionsLog.getListInstances("where idUtilisateur='"+utilisateur.getId().intValue()+"'");
		return listOfConnexionsLogsUtilisateur;
	}
}