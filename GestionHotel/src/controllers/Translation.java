package controllers;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This class represents the Controller of the modele class <i><b>Translation</b></i>
 *	<br/>It contains methods for handling and processes in the business layer
 */

public abstract class Translation {
	
	public static String generateWhereConditionByExample(models.beans.Translation translation, Integer first, Integer count, String orderByAttributs){
		String whereCondition = "";
		
		if (translation != null){
			if (translation.getIdLang() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " idLang like '"+translation.getIdLang()+"%' ";
			}
			if (translation.getIdMessage() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " idMessage like '"+translation.getIdMessage()+"%' ";
			}
			if (translation.getTraduction() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " traduction like '"+translation.getTraduction()+"%' ";
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
	
	public static java.util.List<models.beans.Translation> getListByExemple(models.beans.Translation translation){
		return getListByExemple(translation, null, null, null);
	}
	
	public static java.util.List<models.beans.Translation> getListByExemple(models.beans.Translation translation, Integer first, Integer count){
		return getListByExemple(translation, first, count, null);
	}
	
	public static java.util.List<models.beans.Translation> getListByExemple(models.beans.Translation translation, String orderByAttributs){
		return getListByExemple(translation, null, null, orderByAttributs);
	}
	
	public static java.util.List<models.beans.Translation> getListByExemple(models.beans.Translation translation, Integer first, Integer count, String orderByAttributs){
		String whereCondition = generateWhereConditionByExample(translation, first, count, orderByAttributs);
		
		java.util.List<models.beans.Translation> list = models.daos.client.DAOTranslation.getListInstances(whereCondition);
		return list;
	}
	
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}