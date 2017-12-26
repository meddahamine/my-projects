package controllers;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This class represents the Controller of the modele class <i><b>Role</b></i>
 *	<br/>It contains methods for handling and processes in the business layer
 */

public abstract class Role {
	
	public static java.util.List<models.beans.Utilisateur> getListOfUtilisateursRole(models.beans.Role role){
		java.util.List<models.beans.Utilisateur> listOfUtilisateursRole = role.getListOfUtilisateursRoles();
		
		if (listOfUtilisateursRole == null || listOfUtilisateursRole.size()==0){
			listOfUtilisateursRole = models.daos.client.DAOUtilisateur.getListInstances("where idRole='"+role.getId()+"'");
			role.setListOfUtilisateursRoles(listOfUtilisateursRole);
		}
		return listOfUtilisateursRole;
	}
	
	public static java.util.List<models.beans.Utilisateur> getListOfUtilisateursRole(models.beans.Role role, boolean update){
		if (update){
			role.setListOfUtilisateursRoles(null);
		}
		
		return getListOfUtilisateursRole(role);
	}
	
	public static String generateWhereConditionByExample(models.beans.Role role, Integer first, Integer count, String orderByAttributs){
		String whereCondition = "";
		
		if (role != null){
			if (role.getDesignation() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " designation like '"+role.getDesignation()+"%' ";
			}
			if (role.getIdPhoto() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " idPhoto like '"+role.getIdPhoto()+"%' ";
			}
			if (role.isParametresOrganisme() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " parametresOrganisme like '"+role.isParametresOrganisme()+"%' ";
			}
			if (role.isGestionRole() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " gestionRole like '"+role.isGestionRole()+"%' ";
			}
			if (role.isGestionUtilisateur() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " gestionUtilisateur like '"+role.isGestionUtilisateur()+"%' ";
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
	
	public static java.util.List<models.beans.Role> getListByExemple(models.beans.Role role){
		return getListByExemple(role, null, null, null);
	}
	
	public static java.util.List<models.beans.Role> getListByExemple(models.beans.Role role, Integer first, Integer count){
		return getListByExemple(role, first, count, null);
	}
	
	public static java.util.List<models.beans.Role> getListByExemple(models.beans.Role role, String orderByAttributs){
		return getListByExemple(role, null, null, orderByAttributs);
	}
	
	public static java.util.List<models.beans.Role> getListByExemple(models.beans.Role role, Integer first, Integer count, String orderByAttributs){
		String whereCondition = generateWhereConditionByExample(role, first, count, orderByAttributs);
		
		java.util.List<models.beans.Role> list = models.daos.client.DAORole.getListInstances(whereCondition);
		return list;
	}
	
	public static void deletePhoto(models.beans.Role role){
		java.util.List<String> listQueries = new java.util.ArrayList<String>();
		models.beans.Photo photo = role.getPhoto();
		role.setPhoto(null);
		listQueries.add(models.daos.client.DAORole.getSQLUpdateForIdPhoto(role));
		listQueries.add(models.daos.client.DAOPhoto.getSQLDeleting(photo));
		
		communication.SocketCommunicator.getInstance().sendQuery(new utils.SGBDConfig.UpdateDeleteSQLQueries(listQueries));
	}
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}