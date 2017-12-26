package controllers;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This class represents the Controller of the modele class <i><b>Chambre</b></i>
 *	<br/>It contains methods for handling and processes in the business layer
 */

public abstract class Chambre {
	
	public static java.util.List<models.beans.ClientReseveChambre> getListOfClientReseveChambresChambre(models.beans.Chambre chambre){
		java.util.List<models.beans.ClientReseveChambre> listOfClientReseveChambresChambre = chambre.getListOfClientReseveChambresChambres();
		
		if (listOfClientReseveChambresChambre == null || listOfClientReseveChambresChambre.size()==0){
			listOfClientReseveChambresChambre = models.daos.client.DAOClientReseveChambre.getListInstances("where idChambre='"+chambre.getId()+"'");
			chambre.setListOfClientReseveChambresChambres(listOfClientReseveChambresChambre);
		}
		return listOfClientReseveChambresChambre;
	}
	
	public static java.util.List<models.beans.ClientReseveChambre> getListOfClientReseveChambresChambre(models.beans.Chambre chambre, boolean update){
		if (update){
			chambre.setListOfClientReseveChambresChambres(null);
		}
		
		return getListOfClientReseveChambresChambre(chambre);
	}
	
	public static String generateWhereConditionByExample(models.beans.Chambre chambre, Integer first, Integer count, String orderByAttributs){
		String whereCondition = "";
		
		if (chambre != null){
			if (chambre.getNumChamre() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " numChamre like '"+chambre.getNumChamre()+"%' ";
			}
			if (chambre.getNumEtage() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " numEtage like '"+chambre.getNumEtage()+"%' ";
			}
			if (chambre.getIdCategorieChambre() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " idCategorieChambre like '"+chambre.getIdCategorieChambre()+"%' ";
			}
			if (chambre.getPrix() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " prix like '"+chambre.getPrix()+"%' ";
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
	
	public static java.util.List<models.beans.Chambre> getListByExemple(models.beans.Chambre chambre){
		return getListByExemple(chambre, null, null, null);
	}
	
	public static java.util.List<models.beans.Chambre> getListByExemple(models.beans.Chambre chambre, Integer first, Integer count){
		return getListByExemple(chambre, first, count, null);
	}
	
	public static java.util.List<models.beans.Chambre> getListByExemple(models.beans.Chambre chambre, String orderByAttributs){
		return getListByExemple(chambre, null, null, orderByAttributs);
	}
	
	public static java.util.List<models.beans.Chambre> getListByExemple(models.beans.Chambre chambre, Integer first, Integer count, String orderByAttributs){
		String whereCondition = generateWhereConditionByExample(chambre, first, count, orderByAttributs);
		
		java.util.List<models.beans.Chambre> list = models.daos.client.DAOChambre.getListInstances(whereCondition);
		return list;
	}
	
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}