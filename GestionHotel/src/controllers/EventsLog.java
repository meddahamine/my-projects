package controllers;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This class represents the Controller of the modele class <i><b>EventsLog</b></i>
 *	<br/>It contains methods for handling and processes in the business layer
 */

public abstract class EventsLog {
	
	public static String generateWhereConditionByExample(models.beans.EventsLog eventsLog, Integer first, Integer count, String orderByAttributs){
		String whereCondition = "";
		
		if (eventsLog != null){
			if (eventsLog.getEvenement() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " evenement like '"+eventsLog.getEvenement()+"%' ";
			}
			if (eventsLog.getIdConnexionsLog() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " idConnexionsLog like '"+eventsLog.getIdConnexionsLog()+"%' ";
			}
			if (eventsLog.getDate() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " date like '"+eventsLog.getDate()+"%' ";
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
	
	public static java.util.List<models.beans.EventsLog> getListByExemple(models.beans.EventsLog eventsLog){
		return getListByExemple(eventsLog, null, null, null);
	}
	
	public static java.util.List<models.beans.EventsLog> getListByExemple(models.beans.EventsLog eventsLog, Integer first, Integer count){
		return getListByExemple(eventsLog, first, count, null);
	}
	
	public static java.util.List<models.beans.EventsLog> getListByExemple(models.beans.EventsLog eventsLog, String orderByAttributs){
		return getListByExemple(eventsLog, null, null, orderByAttributs);
	}
	
	public static java.util.List<models.beans.EventsLog> getListByExemple(models.beans.EventsLog eventsLog, Integer first, Integer count, String orderByAttributs){
		String whereCondition = generateWhereConditionByExample(eventsLog, first, count, orderByAttributs);
		
		java.util.List<models.beans.EventsLog> list = models.daos.client.DAOEventsLog.getListInstances(whereCondition);
		return list;
	}
	
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}