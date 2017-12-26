package controllers;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This class represents the Controller of the modele class <i><b>CategorieChambre</b></i>
 *	<br/>It contains methods for handling and processes in the business layer
 */

public abstract class CategorieChambre {
	
	public static java.util.List<models.beans.Chambre> getListOfChambresCategorieChambre(models.beans.CategorieChambre categorieChambre){
		java.util.List<models.beans.Chambre> listOfChambresCategorieChambre = categorieChambre.getListOfChambresCategorieChambres();
		
		if (listOfChambresCategorieChambre == null || listOfChambresCategorieChambre.size()==0){
			listOfChambresCategorieChambre = models.daos.client.DAOChambre.getListInstances("where idCategorieChambre='"+categorieChambre.getId()+"'");
			categorieChambre.setListOfChambresCategorieChambres(listOfChambresCategorieChambre);
		}
		return listOfChambresCategorieChambre;
	}
	
	public static java.util.List<models.beans.Chambre> getListOfChambresCategorieChambre(models.beans.CategorieChambre categorieChambre, boolean update){
		if (update){
			categorieChambre.setListOfChambresCategorieChambres(null);
		}
		
		return getListOfChambresCategorieChambre(categorieChambre);
	}
	
	public static String generateWhereConditionByExample(models.beans.CategorieChambre categorieChambre, Integer first, Integer count, String orderByAttributs){
		String whereCondition = "";
		
		if (categorieChambre != null){
			if (categorieChambre.getLibelle() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " libelle like '"+categorieChambre.getLibelle()+"%' ";
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
	
	public static java.util.List<models.beans.CategorieChambre> getListByExemple(models.beans.CategorieChambre categorieChambre){
		return getListByExemple(categorieChambre, null, null, null);
	}
	
	public static java.util.List<models.beans.CategorieChambre> getListByExemple(models.beans.CategorieChambre categorieChambre, Integer first, Integer count){
		return getListByExemple(categorieChambre, first, count, null);
	}
	
	public static java.util.List<models.beans.CategorieChambre> getListByExemple(models.beans.CategorieChambre categorieChambre, String orderByAttributs){
		return getListByExemple(categorieChambre, null, null, orderByAttributs);
	}
	
	public static java.util.List<models.beans.CategorieChambre> getListByExemple(models.beans.CategorieChambre categorieChambre, Integer first, Integer count, String orderByAttributs){
		String whereCondition = generateWhereConditionByExample(categorieChambre, first, count, orderByAttributs);
		
		java.util.List<models.beans.CategorieChambre> list = models.daos.client.DAOCategorieChambre.getListInstances(whereCondition);
		return list;
	}
	
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}