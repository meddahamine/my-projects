package controllers;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This class represents the Controller of the modele class <i><b>Photo</b></i>
 *	<br/>It contains methods for handling and processes in the business layer
 */

public abstract class Photo {
	
	public static java.util.List<models.beans.ParametresApplication> getListOfParametresApplicationsPhoto(models.beans.Photo photo){
		java.util.List<models.beans.ParametresApplication> listOfParametresApplicationsPhoto = photo.getListOfParametresApplicationsPhotos();
		
		if (listOfParametresApplicationsPhoto == null || listOfParametresApplicationsPhoto.size()==0){
			listOfParametresApplicationsPhoto = models.daos.client.DAOParametresApplication.getListInstances("where idPhoto='"+photo.getId()+"'");
			photo.setListOfParametresApplicationsPhotos(listOfParametresApplicationsPhoto);
		}
		return listOfParametresApplicationsPhoto;
	}
	
	public static java.util.List<models.beans.ParametresApplication> getListOfParametresApplicationsPhoto(models.beans.Photo photo, boolean update){
		if (update){
			photo.setListOfParametresApplicationsPhotos(null);
		}
		
		return getListOfParametresApplicationsPhoto(photo);
	}
	
	public static java.util.List<models.beans.ParametresOrganisme> getListOfParametresOrganismesPhoto(models.beans.Photo photo){
		java.util.List<models.beans.ParametresOrganisme> listOfParametresOrganismesPhoto = photo.getListOfParametresOrganismesPhotos();
		
		if (listOfParametresOrganismesPhoto == null || listOfParametresOrganismesPhoto.size()==0){
			listOfParametresOrganismesPhoto = models.daos.client.DAOParametresOrganisme.getListInstances("where idPhoto='"+photo.getId()+"'");
			photo.setListOfParametresOrganismesPhotos(listOfParametresOrganismesPhoto);
		}
		return listOfParametresOrganismesPhoto;
	}
	
	public static java.util.List<models.beans.ParametresOrganisme> getListOfParametresOrganismesPhoto(models.beans.Photo photo, boolean update){
		if (update){
			photo.setListOfParametresOrganismesPhotos(null);
		}
		
		return getListOfParametresOrganismesPhoto(photo);
	}
	
	public static java.util.List<models.beans.Role> getListOfRolesPhoto(models.beans.Photo photo){
		java.util.List<models.beans.Role> listOfRolesPhoto = photo.getListOfRolesPhotos();
		
		if (listOfRolesPhoto == null || listOfRolesPhoto.size()==0){
			listOfRolesPhoto = models.daos.client.DAORole.getListInstances("where idPhoto='"+photo.getId()+"'");
			photo.setListOfRolesPhotos(listOfRolesPhoto);
		}
		return listOfRolesPhoto;
	}
	
	public static java.util.List<models.beans.Role> getListOfRolesPhoto(models.beans.Photo photo, boolean update){
		if (update){
			photo.setListOfRolesPhotos(null);
		}
		
		return getListOfRolesPhoto(photo);
	}
	
	public static java.util.List<models.beans.Utilisateur> getListOfUtilisateursPhoto(models.beans.Photo photo){
		java.util.List<models.beans.Utilisateur> listOfUtilisateursPhoto = photo.getListOfUtilisateursPhotos();
		
		if (listOfUtilisateursPhoto == null || listOfUtilisateursPhoto.size()==0){
			listOfUtilisateursPhoto = models.daos.client.DAOUtilisateur.getListInstances("where idPhoto='"+photo.getId()+"'");
			photo.setListOfUtilisateursPhotos(listOfUtilisateursPhoto);
		}
		return listOfUtilisateursPhoto;
	}
	
	public static java.util.List<models.beans.Utilisateur> getListOfUtilisateursPhoto(models.beans.Photo photo, boolean update){
		if (update){
			photo.setListOfUtilisateursPhotos(null);
		}
		
		return getListOfUtilisateursPhoto(photo);
	}
	
	public static String generateWhereConditionByExample(models.beans.Photo photo, Integer first, Integer count, String orderByAttributs){
		String whereCondition = "";
		
		if (photo != null){
			if (photo.getData() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " data like '"+photo.getData()+"%' ";
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
	
	public static java.util.List<models.beans.Photo> getListByExemple(models.beans.Photo photo){
		return getListByExemple(photo, null, null, null);
	}
	
	public static java.util.List<models.beans.Photo> getListByExemple(models.beans.Photo photo, Integer first, Integer count){
		return getListByExemple(photo, first, count, null);
	}
	
	public static java.util.List<models.beans.Photo> getListByExemple(models.beans.Photo photo, String orderByAttributs){
		return getListByExemple(photo, null, null, orderByAttributs);
	}
	
	public static java.util.List<models.beans.Photo> getListByExemple(models.beans.Photo photo, Integer first, Integer count, String orderByAttributs){
		String whereCondition = generateWhereConditionByExample(photo, first, count, orderByAttributs);
		
		java.util.List<models.beans.Photo> list = models.daos.client.DAOPhoto.getListInstances(whereCondition);
		return list;
	}
	
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}