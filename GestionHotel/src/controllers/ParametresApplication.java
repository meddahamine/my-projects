package controllers;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This class represents the Controller of the modele class <i><b>ParametresApplication</b></i>
 *	<br/>It contains methods for handling and processes in the business layer
 */

public abstract class ParametresApplication {
	
	public static String generateWhereConditionByExample(models.beans.ParametresApplication parametresApplication, Integer first, Integer count, String orderByAttributs){
		String whereCondition = "";
		
		if (parametresApplication != null){
			if (parametresApplication.getDesignation() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " designation like '"+parametresApplication.getDesignation()+"%' ";
			}
			if (parametresApplication.getDescription() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " description like '"+parametresApplication.getDescription()+"%' ";
			}
			if (parametresApplication.getIdPhoto() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " idPhoto like '"+parametresApplication.getIdPhoto()+"%' ";
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
	
	public static java.util.List<models.beans.ParametresApplication> getListByExemple(models.beans.ParametresApplication parametresApplication){
		return getListByExemple(parametresApplication, null, null, null);
	}
	
	public static java.util.List<models.beans.ParametresApplication> getListByExemple(models.beans.ParametresApplication parametresApplication, Integer first, Integer count){
		return getListByExemple(parametresApplication, first, count, null);
	}
	
	public static java.util.List<models.beans.ParametresApplication> getListByExemple(models.beans.ParametresApplication parametresApplication, String orderByAttributs){
		return getListByExemple(parametresApplication, null, null, orderByAttributs);
	}
	
	public static java.util.List<models.beans.ParametresApplication> getListByExemple(models.beans.ParametresApplication parametresApplication, Integer first, Integer count, String orderByAttributs){
		String whereCondition = generateWhereConditionByExample(parametresApplication, first, count, orderByAttributs);
		
		java.util.List<models.beans.ParametresApplication> list = models.daos.client.DAOParametresApplication.getListInstances(whereCondition);
		return list;
	}
	
	public static void deletePhoto(models.beans.ParametresApplication parametresApplication){
		java.util.List<String> listQueries = new java.util.ArrayList<String>();
		models.beans.Photo photo = parametresApplication.getPhoto();
		parametresApplication.setPhoto(null);
		listQueries.add(models.daos.client.DAOParametresApplication.getSQLUpdateForIdPhoto(parametresApplication));
		listQueries.add(models.daos.client.DAOPhoto.getSQLDeleting(photo));
		
		communication.SocketCommunicator.getInstance().sendQuery(new utils.SGBDConfig.UpdateDeleteSQLQueries(listQueries));
	}
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}