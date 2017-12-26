package controllers;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This class represents the Controller of the modele class <i><b>Service</b></i>
 *	<br/>It contains methods for handling and processes in the business layer
 */

public abstract class Service {
	
	public static java.util.List<models.beans.ClientConsommeService> getListOfClientConsommeServicesService(models.beans.Service service){
		java.util.List<models.beans.ClientConsommeService> listOfClientConsommeServicesService = service.getListOfClientConsommeServicesServices();
		
		if (listOfClientConsommeServicesService == null || listOfClientConsommeServicesService.size()==0){
			listOfClientConsommeServicesService = models.daos.client.DAOClientConsommeService.getListInstances("where idService='"+service.getId()+"'");
			service.setListOfClientConsommeServicesServices(listOfClientConsommeServicesService);
		}
		return listOfClientConsommeServicesService;
	}
	
	public static java.util.List<models.beans.ClientConsommeService> getListOfClientConsommeServicesService(models.beans.Service service, boolean update){
		if (update){
			service.setListOfClientConsommeServicesServices(null);
		}
		
		return getListOfClientConsommeServicesService(service);
	}
	
	public static String generateWhereConditionByExample(models.beans.Service service, Integer first, Integer count, String orderByAttributs){
		String whereCondition = "";
		
		if (service != null){
			if (service.getDesignation() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " designation like '"+service.getDesignation()+"%' ";
			}
			if (service.getPrix() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " prix like '"+service.getPrix()+"%' ";
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
	
	public static java.util.List<models.beans.Service> getListByExemple(models.beans.Service service){
		return getListByExemple(service, null, null, null);
	}
	
	public static java.util.List<models.beans.Service> getListByExemple(models.beans.Service service, Integer first, Integer count){
		return getListByExemple(service, first, count, null);
	}
	
	public static java.util.List<models.beans.Service> getListByExemple(models.beans.Service service, String orderByAttributs){
		return getListByExemple(service, null, null, orderByAttributs);
	}
	
	public static java.util.List<models.beans.Service> getListByExemple(models.beans.Service service, Integer first, Integer count, String orderByAttributs){
		String whereCondition = generateWhereConditionByExample(service, first, count, orderByAttributs);
		
		java.util.List<models.beans.Service> list = models.daos.client.DAOService.getListInstances(whereCondition);
		return list;
	}
	
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}