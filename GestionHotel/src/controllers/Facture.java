package controllers;

import gui.utils.GUIReportEditor;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.daos.client.DAOClientConsommeService;

/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This class represents the Controller of the modele class <i><b>Facture</b></i>
 *	<br/>It contains methods for handling and processes in the business layer
 */

public abstract class Facture {
	
	public static String generateWhereConditionByExample(models.beans.Facture facture, Integer first, Integer count, String orderByAttributs){
		String whereCondition = "";
		
		if (facture != null){
			if (facture.getDateFacture() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " dateFacture like '"+facture.getDateFacture()+"%' ";
			}
			if (facture.getIdClient() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " idClient like '"+facture.getIdClient()+"%' ";
			}
			if (facture.getMontant() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " montant like '"+facture.getMontant()+"%' ";
			}
			if (facture.getTypePayementC() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " typePayementC like '"+facture.getTypePayementC()+"%' ";
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
	
	public static java.util.List<models.beans.Facture> getListByExemple(models.beans.Facture facture){
		return getListByExemple(facture, null, null, null);
	}
	
	public static java.util.List<models.beans.Facture> getListByExemple(models.beans.Facture facture, Integer first, Integer count){
		return getListByExemple(facture, first, count, null);
	}
	
	public static java.util.List<models.beans.Facture> getListByExemple(models.beans.Facture facture, String orderByAttributs){
		return getListByExemple(facture, null, null, orderByAttributs);
	}
	
	public static java.util.List<models.beans.Facture> getListByExemple(models.beans.Facture facture, Integer first, Integer count, String orderByAttributs){
		String whereCondition = generateWhereConditionByExample(facture, first, count, orderByAttributs);
		
		java.util.List<models.beans.Facture> list = models.daos.server.DAOFacture.getListInstances(whereCondition);
		return list;
	}
	
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/
	public static File generateFicheFacture(models.beans.Facture facture ){
		File model = utils.FilesAndLaunchUtils.createFileFromResource("/ressources/reports/facture.xml", "ficheFacture.xml");
		String sqlQuery = GUIReportEditor.getSQLQueryFromModel(model);
		
		Map<Object, Object> parameters = new HashMap<Object, Object>();
		models.beans.Client client = facture.getClient();
		parameters.put("numFacture", "Facutre N° : "+facture.getId());
		parameters.put("Nom", client.getNom());
		parameters.put("Prenom", client.getPrenom());
		parameters.put("Montant", facture.getMontant());
		parameters.put("Date", utils.StringUtils.formatDateFromMySQL(facture.getDateFacture().toString()));
		
		List<models.beans.ClientConsommeService> listServices = DAOClientConsommeService.getListInstances("where idClient = '"+client.getId()+"'");

		List<Object> dataServices = new ArrayList<Object>();
		
		int num = 1;
		for (models.beans.ClientConsommeService ccs : listServices){
			Map<String, String> map = new HashMap<String, String>();
			
			map.put("num", num+"");
			map.put("consommation", ccs.getService().getDesignation());
			map.put("prix", utils.StringUtils.formatMonetaire(ccs.getPrixService()));
			
			dataServices.add(map);
		}
		
		parameters.put("extraParams-1", utils.FilesAndLaunchUtils.getHexFromObject((Serializable)dataServices));
		
		File file = GUIReportEditor.generateReportFileFromServer(model, sqlQuery, parameters);
		
		return file;
	}
	
	public static void imprimerFicheFacture(models.beans.Facture facture){
		File file = generateFicheFacture(facture);
		utils.FilesAndLaunchUtils.openFile(file);
	}
	
//	public static void imprimerFichesFacture(List<models.beans.Facture> facture){
		//	List<File> listPDFFiles = new ArrayList<File>();
//		for (models.beans.Facture facture : facture){
//			File file = generateFicheFacture(facture);
//			listPDFFiles.add(file);
//		}
		
	//	File pdfFile = PDFCreator.concatenatePDFFiles(listPDFFiles);
	//	utils.FilesAndLaunchUtils.openFile(pdfFile);
//	}

}