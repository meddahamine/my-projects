package controllers;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This class represents the Controller of the modele class <i><b>Utilisateur</b></i>
 *	<br/>It contains methods for handling and processes in the business layer
 */

public abstract class Utilisateur {
	
	public static java.util.List<models.beans.ConnexionsLog> getListOfConnexionsLogsUtilisateur(models.beans.Utilisateur utilisateur){
		java.util.List<models.beans.ConnexionsLog> listOfConnexionsLogsUtilisateur = utilisateur.getListOfConnexionsLogsUtilisateurs();
		
		if (listOfConnexionsLogsUtilisateur == null || listOfConnexionsLogsUtilisateur.size()==0){
			listOfConnexionsLogsUtilisateur = models.daos.client.DAOConnexionsLog.getListInstances("where idUtilisateur='"+utilisateur.getId()+"'");
			utilisateur.setListOfConnexionsLogsUtilisateurs(listOfConnexionsLogsUtilisateur);
		}
		return listOfConnexionsLogsUtilisateur;
	}
	
	public static java.util.List<models.beans.ConnexionsLog> getListOfConnexionsLogsUtilisateur(models.beans.Utilisateur utilisateur, boolean update){
		if (update){
			utilisateur.setListOfConnexionsLogsUtilisateurs(null);
		}
		
		return getListOfConnexionsLogsUtilisateur(utilisateur);
	}
	
	public static String generateWhereConditionByExample(models.beans.Utilisateur utilisateur, Integer first, Integer count, String orderByAttributs){
		String whereCondition = "";
		
		if (utilisateur != null){
			if (utilisateur.getNom() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " nom like '"+utilisateur.getNom()+"%' ";
			}
			if (utilisateur.getPrenom() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " prenom like '"+utilisateur.getPrenom()+"%' ";
			}
			if (utilisateur.getCivilite() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " civilite like '"+utilisateur.getCivilite()+"%' ";
			}
			if (utilisateur.getDateNaissance() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " dateNaissance like '"+utilisateur.getDateNaissance()+"%' ";
			}
			if (utilisateur.getLieuNaissance() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " lieuNaissance like '"+utilisateur.getLieuNaissance()+"%' ";
			}
			if (utilisateur.getLogin() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " login like '"+utilisateur.getLogin()+"%' ";
			}
			if (utilisateur.getPassword() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " password like '"+utilisateur.getPassword()+"%' ";
			}
			if (utilisateur.getIdPhoto() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " idPhoto like '"+utilisateur.getIdPhoto()+"%' ";
			}
			if (utilisateur.getIdRole() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " idRole like '"+utilisateur.getIdRole()+"%' ";
			}
			if (utilisateur.getIdParametres() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " idParametres like '"+utilisateur.getIdParametres()+"%' ";
			}
		}
		
		if (orderByAttributs != null){
			whereCondition += " ORDER BY "+orderByAttributs;
		}
		
		if (first != null && count != null){
			whereCondition += " LIMIT "+first+","+count;
		}
		
		return whereCondition;
	}
	
	public static java.util.List<models.beans.Utilisateur> getListByExemple(models.beans.Utilisateur utilisateur){
		return getListByExemple(utilisateur, null, null, null);
	}
	
	public static java.util.List<models.beans.Utilisateur> getListByExemple(models.beans.Utilisateur utilisateur, Integer first, Integer count){
		return getListByExemple(utilisateur, first, count, null);
	}
	
	public static java.util.List<models.beans.Utilisateur> getListByExemple(models.beans.Utilisateur utilisateur, String orderByAttributs){
		return getListByExemple(utilisateur, null, null, orderByAttributs);
	}
	
	public static java.util.List<models.beans.Utilisateur> getListByExemple(models.beans.Utilisateur utilisateur, Integer first, Integer count, String orderByAttributs){
		String whereCondition = generateWhereConditionByExample(utilisateur, first, count, orderByAttributs);
		
		java.util.List<models.beans.Utilisateur> list = models.daos.client.DAOUtilisateur.getListInstances(whereCondition);
		return list;
	}
	
	public static void deletePhoto(models.beans.Utilisateur utilisateur){
		java.util.List<String> listQueries = new java.util.ArrayList<String>();
		models.beans.Photo photo = utilisateur.getPhoto();
		utilisateur.setPhoto(null);
		listQueries.add(models.daos.client.DAOUtilisateur.getSQLUpdateForIdPhoto(utilisateur));
		listQueries.add(models.daos.client.DAOPhoto.getSQLDeleting(photo));
		
		communication.SocketCommunicator.getInstance().sendQuery(new utils.SGBDConfig.UpdateDeleteSQLQueries(listQueries));
	}
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}