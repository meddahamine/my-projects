package controllers;

import gui.utils.GUIReportEditor;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.PDFCreator;
import models.daos.server.DAOFacture;



/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This class represents the Controller of the modele class <i><b>Client</b></i>
 *	<br/>It contains methods for handling and processes in the business layer
 */

public abstract class Client {
	
	public static java.util.List<models.beans.ClientConsommeService> getListOfClientConsommeServicesClient(models.beans.Client client){
		java.util.List<models.beans.ClientConsommeService> listOfClientConsommeServicesClient = client.getListOfClientConsommeServicesClients();
		
		if (listOfClientConsommeServicesClient == null || listOfClientConsommeServicesClient.size()==0){
			listOfClientConsommeServicesClient = models.daos.client.DAOClientConsommeService.getListInstances("where idClient='"+client.getId()+"'");
			client.setListOfClientConsommeServicesClients(listOfClientConsommeServicesClient);
		}
		return listOfClientConsommeServicesClient;
	}
	
	public static java.util.List<models.beans.ClientConsommeService> getListOfClientConsommeServicesClient(models.beans.Client client, boolean update){
		if (update){
			client.setListOfClientConsommeServicesClients(null);
		}
		
		return getListOfClientConsommeServicesClient(client);
	}
	
	public static java.util.List<models.beans.ClientReseveChambre> getListOfClientReseveChambresClient(models.beans.Client client){
		java.util.List<models.beans.ClientReseveChambre> listOfClientReseveChambresClient = client.getListOfClientReseveChambresClients();
		
		if (listOfClientReseveChambresClient == null || listOfClientReseveChambresClient.size()==0){
			listOfClientReseveChambresClient = models.daos.client.DAOClientReseveChambre.getListInstances("where idClient='"+client.getId()+"'");
			client.setListOfClientReseveChambresClients(listOfClientReseveChambresClient);
		}
		return listOfClientReseveChambresClient;
	}
	
	public static java.util.List<models.beans.ClientReseveChambre> getListOfClientReseveChambresClient(models.beans.Client client, boolean update){
		if (update){
			client.setListOfClientReseveChambresClients(null);
		}
		
		return getListOfClientReseveChambresClient(client);
	}
	
	public static java.util.List<models.beans.Facture> getListOfFacturesClient(models.beans.Client client){
		java.util.List<models.beans.Facture> listOfFacturesClient = client.getListOfFacturesClients();
		
		if (listOfFacturesClient == null || listOfFacturesClient.size()==0){
			listOfFacturesClient = DAOFacture.getListInstances("where idClient='"+client.getId()+"'");
			client.setListOfFacturesClients(listOfFacturesClient);
		}
		return listOfFacturesClient;
	}
	
	public static java.util.List<models.beans.Facture> getListOfFacturesClient(models.beans.Client client, boolean update){
		if (update){
			client.setListOfFacturesClients(null);
		}
		
		return getListOfFacturesClient(client);
	}
	
	public static String generateWhereConditionByExample(models.beans.Client client, Integer first, Integer count, String orderByAttributs){
		String whereCondition = "";
		
		if (client != null){
			if (client.getTypeClient() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " typeClient like '"+client.getTypeClient()+"%' ";
			}
			if (client.getNom() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " nom like '"+client.getNom()+"%' ";
			}
			if (client.getPrenom() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " prenom like '"+client.getPrenom()+"%' ";
			}
			if (client.getDateNaiss() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " dateNaiss like '"+client.getDateNaiss()+"%' ";
			}
			if (client.getAdresse() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " adresse like '"+client.getAdresse()+"%' ";
			}
			if (client.getNationalite() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " nationalite like '"+client.getNationalite()+"%' ";
			}
			if (client.getTelephone() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " telephone like '"+client.getTelephone()+"%' ";
			}
			if (client.getTypeCarte() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " typeCarte like '"+client.getTypeCarte()+"%' ";
			}
			if (client.getNumCarte() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " numCarte like '"+client.getNumCarte()+"%' ";
			}
			if (client.getProfession() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " profession like '"+client.getProfession()+"%' ";
			}
			if (client.getAutreTypeClient() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " autreTypeClient like '"+client.getAutreTypeClient()+"%' ";
			}
			if (client.getAutreTypeCarte() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " autreTypeCarte like '"+client.getAutreTypeCarte()+"%' ";
			}
			if (client.getLieuDeNaiss() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " lieuDeNaiss like '"+client.getLieuDeNaiss()+"%' ";
			}
			if (client.getCivilite() != null){
				if (!whereCondition.equals("")){
					whereCondition += " AND ";
				}
				whereCondition += " civilite like '"+client.getCivilite()+"%' ";
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
	
	public static java.util.List<models.beans.Client> getListByExemple(models.beans.Client client){
		return getListByExemple(client, null, null, null);
	}
	
	public static java.util.List<models.beans.Client> getListByExemple(models.beans.Client client, Integer first, Integer count){
		return getListByExemple(client, first, count, null);
	}
	
	public static java.util.List<models.beans.Client> getListByExemple(models.beans.Client client, String orderByAttributs){
		return getListByExemple(client, null, null, orderByAttributs);
	}
	
	public static java.util.List<models.beans.Client> getListByExemple(models.beans.Client client, Integer first, Integer count, String orderByAttributs){
		String whereCondition = generateWhereConditionByExample(client, first, count, orderByAttributs);
		
		java.util.List<models.beans.Client> list = models.daos.client.DAOClient.getListInstances(whereCondition);
		return list;
	}
	
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

	public static File generateFicheClient(models.beans.Client client){
		File model = utils.FilesAndLaunchUtils.createFileFromResource("/ressources/reports/client.xml", "ficheClient.xml");
		String sqlQuery = GUIReportEditor.getSQLQueryFromModel(model);
		
		Map<Object, Object> parameters = new HashMap<Object, Object>();
		
		parameters.put("Nom", client.getNom());
		parameters.put("Prenom", client.getPrenom());
		
		File file = GUIReportEditor.generateReportFileFromServer(model, sqlQuery, parameters);
		
		return file;
	}
	
	public static void imprimerFicheClient(models.beans.Client client){
		File file = generateFicheClient(client);
		utils.FilesAndLaunchUtils.openFile(file);
	}
	
	public static void imprimerFichesClients(List<models.beans.Client> clients){
		List<File> listPDFFiles = new ArrayList<File>();
		for (models.beans.Client client : clients){
			File file = generateFicheClient(client);
			listPDFFiles.add(file);
		}
		
		File pdfFile = PDFCreator.concatenatePDFFiles(listPDFFiles);
		utils.FilesAndLaunchUtils.openFile(pdfFile);
	}
}