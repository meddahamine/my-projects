package controllers;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This class represents the Controller of the modele class <i><b>ParametresApplicationUtilisateur</b></i>
 *	<br/>It contains methods for handling and processes in the business layer
 */

public abstract class ParametresApplicationUtilisateur {
	
	public static java.util.List<models.beans.Utilisateur> getListOfUtilisateursParametres(models.beans.ParametresApplicationUtilisateur parametresApplicationUtilisateur){
		java.util.List<models.beans.Utilisateur> listOfUtilisateursParametres = parametresApplicationUtilisateur.getListOfUtilisateursParametress();
		
		if (listOfUtilisateursParametres == null || listOfUtilisateursParametres.size()==0){
			listOfUtilisateursParametres = models.daos.client.DAOUtilisateur.getListInstances("where idParametres='"+parametresApplicationUtilisateur.getId()+"'");
			parametresApplicationUtilisateur.setListOfUtilisateursParametress(listOfUtilisateursParametres);
		}
		return listOfUtilisateursParametres;
	}
	
	public static java.util.List<models.beans.Utilisateur> getListOfUtilisateursParametres(models.beans.ParametresApplicationUtilisateur parametresApplicationUtilisateur, boolean update){
		if (update){
			parametresApplicationUtilisateur.setListOfUtilisateursParametress(null);
		}
		
		return getListOfUtilisateursParametres(parametresApplicationUtilisateur);
	}
	
	public static String generateWhereConditionByExample(models.beans.ParametresApplicationUtilisateur parametresApplicationUtilisateur, Integer first, Integer count, String orderByAttributs){
		String whereCondition = "";
		
		if (parametresApplicationUtilisateur != null){
			if (parametresApplicationUtilisateur.getPeriodeNotification() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " periodeNotification like '"+parametresApplicationUtilisateur.getPeriodeNotification()+"%' ";
			}
			if (parametresApplicationUtilisateur.isVisibilityOfNotification() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " visibilityOfNotification like '"+parametresApplicationUtilisateur.isVisibilityOfNotification()+"%' ";
			}
			if (parametresApplicationUtilisateur.isVisibilityOfMainToolBar() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " visibilityOfMainToolBar like '"+parametresApplicationUtilisateur.isVisibilityOfMainToolBar()+"%' ";
			}
			if (parametresApplicationUtilisateur.getIdLang() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " idLang like '"+parametresApplicationUtilisateur.getIdLang()+"%' ";
			}
			if (parametresApplicationUtilisateur.getLookAndFeel() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " lookAndFeel like '"+parametresApplicationUtilisateur.getLookAndFeel()+"%' ";
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
	
	public static java.util.List<models.beans.ParametresApplicationUtilisateur> getListByExemple(models.beans.ParametresApplicationUtilisateur parametresApplicationUtilisateur){
		return getListByExemple(parametresApplicationUtilisateur, null, null, null);
	}
	
	public static java.util.List<models.beans.ParametresApplicationUtilisateur> getListByExemple(models.beans.ParametresApplicationUtilisateur parametresApplicationUtilisateur, Integer first, Integer count){
		return getListByExemple(parametresApplicationUtilisateur, first, count, null);
	}
	
	public static java.util.List<models.beans.ParametresApplicationUtilisateur> getListByExemple(models.beans.ParametresApplicationUtilisateur parametresApplicationUtilisateur, String orderByAttributs){
		return getListByExemple(parametresApplicationUtilisateur, null, null, orderByAttributs);
	}
	
	public static java.util.List<models.beans.ParametresApplicationUtilisateur> getListByExemple(models.beans.ParametresApplicationUtilisateur parametresApplicationUtilisateur, Integer first, Integer count, String orderByAttributs){
		String whereCondition = generateWhereConditionByExample(parametresApplicationUtilisateur, first, count, orderByAttributs);
		
		java.util.List<models.beans.ParametresApplicationUtilisateur> list = models.daos.client.DAOParametresApplicationUtilisateur.getListInstances(whereCondition);
		return list;
	}
	
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}