package controllers;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This class represents the Controller of the modele class <i><b>LangMessage</b></i>
 *	<br/>It contains methods for handling and processes in the business layer
 */

public abstract class LangMessage {
	
	public static java.util.List<models.beans.Translation> getListOfTranslationsMessage(models.beans.LangMessage langMessage){
		java.util.List<models.beans.Translation> listOfTranslationsMessage = langMessage.getListOfTranslationsMessages();
		
		if (listOfTranslationsMessage == null || listOfTranslationsMessage.size()==0){
			listOfTranslationsMessage = models.daos.client.DAOTranslation.getListInstances("where idMessage='"+langMessage.getId()+"'");
			langMessage.setListOfTranslationsMessages(listOfTranslationsMessage);
		}
		return listOfTranslationsMessage;
	}
	
	public static java.util.List<models.beans.Translation> getListOfTranslationsMessage(models.beans.LangMessage langMessage, boolean update){
		if (update){
			langMessage.setListOfTranslationsMessages(null);
		}
		
		return getListOfTranslationsMessage(langMessage);
	}
	
	public static String generateWhereConditionByExample(models.beans.LangMessage langMessage, Integer first, Integer count, String orderByAttributs){
		String whereCondition = "";
		
		if (langMessage != null){
			if (langMessage.getMessage() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " message like '"+langMessage.getMessage()+"%' ";
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
	
	public static java.util.List<models.beans.LangMessage> getListByExemple(models.beans.LangMessage langMessage){
		return getListByExemple(langMessage, null, null, null);
	}
	
	public static java.util.List<models.beans.LangMessage> getListByExemple(models.beans.LangMessage langMessage, Integer first, Integer count){
		return getListByExemple(langMessage, first, count, null);
	}
	
	public static java.util.List<models.beans.LangMessage> getListByExemple(models.beans.LangMessage langMessage, String orderByAttributs){
		return getListByExemple(langMessage, null, null, orderByAttributs);
	}
	
	public static java.util.List<models.beans.LangMessage> getListByExemple(models.beans.LangMessage langMessage, Integer first, Integer count, String orderByAttributs){
		String whereCondition = generateWhereConditionByExample(langMessage, first, count, orderByAttributs);
		
		java.util.List<models.beans.LangMessage> list = models.daos.client.DAOLangMessage.getListInstances(whereCondition);
		return list;
	}
	
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}