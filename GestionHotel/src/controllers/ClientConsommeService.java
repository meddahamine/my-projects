package controllers;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This class represents the Controller of the modele class <i><b>ClientConsommeService</b></i>
 *	<br/>It contains methods for handling and processes in the business layer
 */

public abstract class ClientConsommeService {
	
	public static String generateWhereConditionByExample(models.beans.ClientConsommeService clientConsommeService, Integer first, Integer count, String orderByAttributs){
		String whereCondition = "";
		
		if (clientConsommeService != null){
			if (clientConsommeService.getIdClient() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " idClient like '"+clientConsommeService.getIdClient()+"%' ";
			}
			if (clientConsommeService.getIdService() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " idService like '"+clientConsommeService.getIdService()+"%' ";
			}
			if (clientConsommeService.getPrixService() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " prixService like '"+clientConsommeService.getPrixService()+"%' ";
			}
			if (clientConsommeService.getDateConsommationService() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " dateConsommationService like '"+clientConsommeService.getDateConsommationService()+"%' ";
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
	
	public static java.util.List<models.beans.ClientConsommeService> getListByExemple(models.beans.ClientConsommeService clientConsommeService){
		return getListByExemple(clientConsommeService, null, null, null);
	}
	
	public static java.util.List<models.beans.ClientConsommeService> getListByExemple(models.beans.ClientConsommeService clientConsommeService, Integer first, Integer count){
		return getListByExemple(clientConsommeService, first, count, null);
	}
	
	public static java.util.List<models.beans.ClientConsommeService> getListByExemple(models.beans.ClientConsommeService clientConsommeService, String orderByAttributs){
		return getListByExemple(clientConsommeService, null, null, orderByAttributs);
	}
	
	public static java.util.List<models.beans.ClientConsommeService> getListByExemple(models.beans.ClientConsommeService clientConsommeService, Integer first, Integer count, String orderByAttributs){
		String whereCondition = generateWhereConditionByExample(clientConsommeService, first, count, orderByAttributs);
		
		java.util.List<models.beans.ClientConsommeService> list = models.daos.client.DAOClientConsommeService.getListInstances(whereCondition);
		return list;
	}
	
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}