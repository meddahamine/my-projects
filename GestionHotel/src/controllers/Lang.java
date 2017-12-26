package controllers;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This class represents the Controller of the modele class <i><b>Lang</b></i>
 *	<br/>It contains methods for handling and processes in the business layer
 */

public abstract class Lang {
	
	public static java.util.List<models.beans.ParametresApplicationUtilisateur> getListOfParametresApplicationUtilisateursLang(models.beans.Lang lang){
		java.util.List<models.beans.ParametresApplicationUtilisateur> listOfParametresApplicationUtilisateursLang = lang.getListOfParametresApplicationUtilisateursLangs();
		
		if (listOfParametresApplicationUtilisateursLang == null || listOfParametresApplicationUtilisateursLang.size()==0){
			listOfParametresApplicationUtilisateursLang = models.daos.client.DAOParametresApplicationUtilisateur.getListInstances("where idLang='"+lang.getId()+"'");
			lang.setListOfParametresApplicationUtilisateursLangs(listOfParametresApplicationUtilisateursLang);
		}
		return listOfParametresApplicationUtilisateursLang;
	}
	
	public static java.util.List<models.beans.ParametresApplicationUtilisateur> getListOfParametresApplicationUtilisateursLang(models.beans.Lang lang, boolean update){
		if (update){
			lang.setListOfParametresApplicationUtilisateursLangs(null);
		}
		
		return getListOfParametresApplicationUtilisateursLang(lang);
	}
	
	public static java.util.List<models.beans.Translation> getListOfTranslationsLang(models.beans.Lang lang){
		java.util.List<models.beans.Translation> listOfTranslationsLang = lang.getListOfTranslationsLangs();
		
		if (listOfTranslationsLang == null || listOfTranslationsLang.size()==0){
			listOfTranslationsLang = models.daos.client.DAOTranslation.getListInstances("where idLang='"+lang.getId()+"'");
			lang.setListOfTranslationsLangs(listOfTranslationsLang);
		}
		return listOfTranslationsLang;
	}
	
	public static java.util.List<models.beans.Translation> getListOfTranslationsLang(models.beans.Lang lang, boolean update){
		if (update){
			lang.setListOfTranslationsLangs(null);
		}
		
		return getListOfTranslationsLang(lang);
	}
	
	public static String generateWhereConditionByExample(models.beans.Lang lang, Integer first, Integer count, String orderByAttributs){
		String whereCondition = "";
		
		if (lang != null){
			if (lang.getLangue() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " langue like '"+lang.getLangue()+"%' ";
			}
			if (lang.getCodeLang() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " codeLang like '"+lang.getCodeLang()+"%' ";
			}
			if (lang.getOrientation() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " orientation like '"+lang.getOrientation()+"%' ";
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
	
	public static java.util.List<models.beans.Lang> getListByExemple(models.beans.Lang lang){
		return getListByExemple(lang, null, null, null);
	}
	
	public static java.util.List<models.beans.Lang> getListByExemple(models.beans.Lang lang, Integer first, Integer count){
		return getListByExemple(lang, first, count, null);
	}
	
	public static java.util.List<models.beans.Lang> getListByExemple(models.beans.Lang lang, String orderByAttributs){
		return getListByExemple(lang, null, null, orderByAttributs);
	}
	
	public static java.util.List<models.beans.Lang> getListByExemple(models.beans.Lang lang, Integer first, Integer count, String orderByAttributs){
		String whereCondition = generateWhereConditionByExample(lang, first, count, orderByAttributs);
		
		java.util.List<models.beans.Lang> list = models.daos.client.DAOLang.getListInstances(whereCondition);
		return list;
	}
	
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}