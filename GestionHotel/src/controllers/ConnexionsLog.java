package controllers;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This class represents the Controller of the modele class <i><b>ConnexionsLog</b></i>
 *	<br/>It contains methods for handling and processes in the business layer
 */

public abstract class ConnexionsLog {
	public static void addEvent(models.beans.ConnexionsLog connexionsLog, String event){
		if (! event.toLowerCase().startsWith("<html>")){
			event ="<html>"+event+"</html>";
		}
		
		models.beans.EventsLog eventsLog = new models.beans.EventsLog();
		eventsLog.setConnexionsLog(connexionsLog);
		eventsLog.setEvenement(event);
		String timeStampString = (String)communication.SocketCommunicator.getInstance().sendQuery("getDateTimeFromServer");
		eventsLog.setDate(utils.StringUtils.getTimestampFromString(timeStampString));
		
		models.daos.client.DAOEventsLog.write(eventsLog);
	}
	
	public static java.util.List<models.beans.EventsLog> getListOfEventsLogsConnexionsLog(models.beans.ConnexionsLog connexionsLog){
		java.util.List<models.beans.EventsLog> listOfEventsLogsConnexionsLog = connexionsLog.getListOfEventsLogsConnexionsLogs();
		
		if (listOfEventsLogsConnexionsLog == null || listOfEventsLogsConnexionsLog.size()==0){
			listOfEventsLogsConnexionsLog = models.daos.client.DAOEventsLog.getListInstances("where idConnexionsLog='"+connexionsLog.getId()+"'");
			connexionsLog.setListOfEventsLogsConnexionsLogs(listOfEventsLogsConnexionsLog);
		}
		return listOfEventsLogsConnexionsLog;
	}
	
	public static java.util.List<models.beans.EventsLog> getListOfEventsLogsConnexionsLog(models.beans.ConnexionsLog connexionsLog, boolean update){
		if (update){
			connexionsLog.setListOfEventsLogsConnexionsLogs(null);
		}
		
		return getListOfEventsLogsConnexionsLog(connexionsLog);
	}
	
	public static String generateWhereConditionByExample(models.beans.ConnexionsLog connexionsLog, Integer first, Integer count, String orderByAttributs){
		String whereCondition = "";
		
		if (connexionsLog != null){
			if (connexionsLog.getLogin() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " login like '"+connexionsLog.getLogin()+"%' ";
			}
			if (connexionsLog.getUuid() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " uuid like '"+connexionsLog.getUuid()+"%' ";
			}
			if (connexionsLog.getDateDeConnexion() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " dateDeConnexion like '"+connexionsLog.getDateDeConnexion()+"%' ";
			}
			if (connexionsLog.getDateDeDeconnexion() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " dateDeDeconnexion like '"+connexionsLog.getDateDeDeconnexion()+"%' ";
			}
			if (connexionsLog.getIp() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " ip like '"+connexionsLog.getIp()+"%' ";
			}
			if (connexionsLog.getMac() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " mac like '"+connexionsLog.getMac()+"%' ";
			}
			if (connexionsLog.getMachine() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " machine like '"+connexionsLog.getMachine()+"%' ";
			}
			if (connexionsLog.getConnexionAccepted() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " connexionAccepted like '"+connexionsLog.getConnexionAccepted()+"%' ";
			}
			if (connexionsLog.getMotifError() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " motifError like '"+connexionsLog.getMotifError()+"%' ";
			}
			if (connexionsLog.getIdUtilisateur() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " idUtilisateur like '"+connexionsLog.getIdUtilisateur()+"%' ";
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
	
	public static java.util.List<models.beans.ConnexionsLog> getListByExemple(models.beans.ConnexionsLog connexionsLog){
		return getListByExemple(connexionsLog, null, null, null);
	}
	
	public static java.util.List<models.beans.ConnexionsLog> getListByExemple(models.beans.ConnexionsLog connexionsLog, Integer first, Integer count){
		return getListByExemple(connexionsLog, first, count, null);
	}
	
	public static java.util.List<models.beans.ConnexionsLog> getListByExemple(models.beans.ConnexionsLog connexionsLog, String orderByAttributs){
		return getListByExemple(connexionsLog, null, null, orderByAttributs);
	}
	
	public static java.util.List<models.beans.ConnexionsLog> getListByExemple(models.beans.ConnexionsLog connexionsLog, Integer first, Integer count, String orderByAttributs){
		String whereCondition = generateWhereConditionByExample(connexionsLog, first, count, orderByAttributs);
		
		java.util.List<models.beans.ConnexionsLog> list = models.daos.client.DAOConnexionsLog.getListInstances(whereCondition);
		return list;
	}
	
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}