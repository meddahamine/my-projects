package controllers;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This class represents the Controller of the modele class <i><b>ClientReseveChambre</b></i>
 *	<br/>It contains methods for handling and processes in the business layer
 */

public abstract class ClientReseveChambre {
	
	public static String generateWhereConditionByExample(models.beans.ClientReseveChambre clientReseveChambre, Integer first, Integer count, String orderByAttributs){
		String whereCondition = "";
		
		if (clientReseveChambre != null){
			if (clientReseveChambre.getIdClient() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " idClient like '"+clientReseveChambre.getIdClient()+"%' ";
			}
			if (clientReseveChambre.getIdChambre() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " idChambre like '"+clientReseveChambre.getIdChambre()+"%' ";
			}
			if (clientReseveChambre.getDateDebutReservation() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " dateDebutReservation like '"+clientReseveChambre.getDateDebutReservation()+"%' ";
			}
			if (clientReseveChambre.getDateFinReservation() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " dateFinReservation like '"+clientReseveChambre.getDateFinReservation()+"%' ";
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
	
	public static java.util.List<models.beans.ClientReseveChambre> getListByExemple(models.beans.ClientReseveChambre clientReseveChambre){
		return getListByExemple(clientReseveChambre, null, null, null);
	}
	
	public static java.util.List<models.beans.ClientReseveChambre> getListByExemple(models.beans.ClientReseveChambre clientReseveChambre, Integer first, Integer count){
		return getListByExemple(clientReseveChambre, first, count, null);
	}
	
	public static java.util.List<models.beans.ClientReseveChambre> getListByExemple(models.beans.ClientReseveChambre clientReseveChambre, String orderByAttributs){
		return getListByExemple(clientReseveChambre, null, null, orderByAttributs);
	}
	
	public static java.util.List<models.beans.ClientReseveChambre> getListByExemple(models.beans.ClientReseveChambre clientReseveChambre, Integer first, Integer count, String orderByAttributs){
		String whereCondition = generateWhereConditionByExample(clientReseveChambre, first, count, orderByAttributs);
		
		java.util.List<models.beans.ClientReseveChambre> list = models.daos.client.DAOClientReseveChambre.getListInstances(whereCondition);
		return list;
	}
	
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}