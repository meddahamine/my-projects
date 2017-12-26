package controllers;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This class represents the Controller of the modele class <i><b>ParametresOrganisme</b></i>
 *	<br/>It contains methods for handling and processes in the business layer
 */

public abstract class ParametresOrganisme {
	
	public static String generateWhereConditionByExample(models.beans.ParametresOrganisme parametresOrganisme, Integer first, Integer count, String orderByAttributs){
		String whereCondition = "";
		
		if (parametresOrganisme != null){
			if (parametresOrganisme.getDesignationOrganisme() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " designationOrganisme like '"+parametresOrganisme.getDesignationOrganisme()+"%' ";
			}
			if (parametresOrganisme.getRaisonSocial() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " raisonSocial like '"+parametresOrganisme.getRaisonSocial()+"%' ";
			}
			if (parametresOrganisme.getCapitalSocial() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " capitalSocial like '"+parametresOrganisme.getCapitalSocial()+"%' ";
			}
			if (parametresOrganisme.getNumRC() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " numRC like '"+parametresOrganisme.getNumRC()+"%' ";
			}
			if (parametresOrganisme.getNumCB() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " numCB like '"+parametresOrganisme.getNumCB()+"%' ";
			}
			if (parametresOrganisme.getIdentificationFiscale() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " identificationFiscale like '"+parametresOrganisme.getIdentificationFiscale()+"%' ";
			}
			if (parametresOrganisme.getNumArticle() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " numArticle like '"+parametresOrganisme.getNumArticle()+"%' ";
			}
			if (parametresOrganisme.getNIS() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " NIS like '"+parametresOrganisme.getNIS()+"%' ";
			}
			if (parametresOrganisme.getAdresse() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " adresse like '"+parametresOrganisme.getAdresse()+"%' ";
			}
			if (parametresOrganisme.getBoitePostale() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " boitePostale like '"+parametresOrganisme.getBoitePostale()+"%' ";
			}
			if (parametresOrganisme.getNumTel() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " numTel like '"+parametresOrganisme.getNumTel()+"%' ";
			}
			if (parametresOrganisme.getNumFax() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " numFax like '"+parametresOrganisme.getNumFax()+"%' ";
			}
			if (parametresOrganisme.getEmail() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " Email like '"+parametresOrganisme.getEmail()+"%' ";
			}
			if (parametresOrganisme.getDescriptif() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " descriptif like '"+parametresOrganisme.getDescriptif()+"%' ";
			}
			if (parametresOrganisme.getIdPhoto() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " idPhoto like '"+parametresOrganisme.getIdPhoto()+"%' ";
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
	
	public static java.util.List<models.beans.ParametresOrganisme> getListByExemple(models.beans.ParametresOrganisme parametresOrganisme){
		return getListByExemple(parametresOrganisme, null, null, null);
	}
	
	public static java.util.List<models.beans.ParametresOrganisme> getListByExemple(models.beans.ParametresOrganisme parametresOrganisme, Integer first, Integer count){
		return getListByExemple(parametresOrganisme, first, count, null);
	}
	
	public static java.util.List<models.beans.ParametresOrganisme> getListByExemple(models.beans.ParametresOrganisme parametresOrganisme, String orderByAttributs){
		return getListByExemple(parametresOrganisme, null, null, orderByAttributs);
	}
	
	public static java.util.List<models.beans.ParametresOrganisme> getListByExemple(models.beans.ParametresOrganisme parametresOrganisme, Integer first, Integer count, String orderByAttributs){
		String whereCondition = generateWhereConditionByExample(parametresOrganisme, first, count, orderByAttributs);
		
		java.util.List<models.beans.ParametresOrganisme> list = models.daos.client.DAOParametresOrganisme.getListInstances(whereCondition);
		return list;
	}
	
	public static void deletePhoto(models.beans.ParametresOrganisme parametresOrganisme){
		java.util.List<String> listQueries = new java.util.ArrayList<String>();
		models.beans.Photo photo = parametresOrganisme.getPhoto();
		parametresOrganisme.setPhoto(null);
		listQueries.add(models.daos.client.DAOParametresOrganisme.getSQLUpdateForIdPhoto(parametresOrganisme));
		listQueries.add(models.daos.client.DAOPhoto.getSQLDeleting(photo));
		
		communication.SocketCommunicator.getInstance().sendQuery(new utils.SGBDConfig.UpdateDeleteSQLQueries(listQueries));
	}
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}