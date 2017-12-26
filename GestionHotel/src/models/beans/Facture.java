package models.beans;

import gui.utils.GUIReportEditor;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.PDFCreator;
/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This classe is a Bean. 
 */

@SuppressWarnings("deprecation")
public class Facture implements Serializable {
	private static final long serialVersionUID = 1L;

	public static enum TypePayementC {
		CB("CB"), Cheque("Cheque"), Espasse("Espasse");

		private utils.Item item;

		private TypePayementC(String value) {
			this.item = new utils.Item(this.name(), value);
		}

		public String toString() {
			return this.name()+"("+this.getValue()+")";
		}

		public String getValue() {
			return this.item.getValue();
		}

		public static TypePayementC getByValue(String value){
			for (TypePayementC c : TypePayementC.values()){
				if (c.getValue().equals(value))
					return c;
			}
			return null;
		}
	};

	private java.lang.Integer id = 0;
	private java.sql.Date dateFacture = new java.sql.Date(1, 0, 1);
	private java.lang.Integer idClient = 0;
	private java.lang.Double montant = 0.0;
	private TypePayementC typePayementC = null;

	private Client client = null;

	public Facture() {
	}

	public Facture(java.lang.Integer id, java.sql.Date dateFacture, java.lang.Integer idClient, java.lang.Double montant, TypePayementC typePayementC) {
		this.setId(id);
		this.setDateFacture(dateFacture);
		this.setIdClient(idClient);
		this.setMontant(montant);
		this.setTypePayementC(typePayementC);
	}

	public Facture(String xmlString) {
		org.jdom.Element element = utils.StringUtils.xmlStringToXmlElement(xmlString);
		fillByXMLElement(element);
	}

	public Facture(org.jdom.Element element) {
		fillByXMLElement(element);
	}

	public void fillByXMLElement(org.jdom.Element element) {
		this.setId(Integer.parseInt(element.getAttributeValue("id")));
		this.setDateFacture(utils.StringUtils.getDateFromString(element.getAttributeValue("dateFacture")));
		this.setIdClient(Integer.parseInt(element.getAttributeValue("idClient")));
		this.setMontant(Double.parseDouble(element.getAttributeValue("montant")));
		this.setTypePayementC(TypePayementC.getByValue(element.getAttributeValue("typePayementC")));
	}

	public void fillByMap(java.util.Map<String, String> data) {
		this.setId(Integer.parseInt(data.get("id")));
		this.setDateFacture(utils.StringUtils.getDateFromString(data.get("dateFacture")));
		this.setIdClient(Integer.parseInt(data.get("idClient")));
		this.setMontant(Double.parseDouble(data.get("montant")));
		this.setTypePayementC(TypePayementC.getByValue(data.get("typePayementC")));
	}

	public void fillByArray(String[] array) {
		this.setId(Integer.parseInt(array[0]));
		this.setDateFacture(utils.StringUtils.getDateFromString(array[1]));
		this.setIdClient(Integer.parseInt(array[2]));
		this.setMontant(Double.parseDouble(array[3]));
		this.setTypePayementC(TypePayementC.getByValue(array[4]));
	}

	public boolean equals(Object obj) {
		if (obj == null){
			return false;
		}
		if (!(obj instanceof Facture)){
			return false;
		}
		Facture item = (Facture)obj;
		if (item.getId() == null || this.getId() == null){
			return false;
		}
		if (item.getId().intValue() == 0 || this.getId().intValue() == 0){
			return false;
		}
		
		return (item.getId().intValue() == this.getId().intValue());
	}

	public java.lang.Integer getId(){
		return this.id;
	}

	public void setId(java.lang.Integer id){
		this.id = id;
	}

	public java.sql.Date getDateFacture(){
		return this.dateFacture;
	}

	public void setDateFacture(java.sql.Date dateFacture){
		this.dateFacture = dateFacture;
	}

	public java.lang.Integer getIdClient(){
		return this.idClient;
	}

	public void setIdClient(java.lang.Integer idClient){
		this.idClient = idClient;
	}

	public Client getClient(){
		return this.client;
	}

	public void setClient(Client client){
		this.client = client;
		int idClient = 0;
		if (client != null){
			idClient = client.getId().intValue();
		}
		this.idClient = idClient;
	}

	public java.lang.Double getMontant(){
		return this.montant;
	}

	public void setMontant(java.lang.Double montant){
		this.montant = montant;
	}

	public TypePayementC getTypePayementC(){
		return this.typePayementC;
	}

	public void setTypePayementC(TypePayementC typePayementC){
		this.typePayementC = typePayementC;
	}

	public String toXMLString(){
		String xml = "<Facture ";
		xml+= "id='"+this.getId()+"' ";
		xml+= "dateFacture='"+this.getDateFacture()+"' ";
		xml+= "idClient='"+this.getIdClient()+"' ";
		xml+= "montant='"+this.getMontant()+"' ";
		xml+= "typePayementC='"+this.getTypePayementC().getValue()+"' ";
		xml+= ">";
		xml+= "\n	"+client.toXMLString().replace("<Client", "<Client").replace("</Client", "</Client");
		xml+= "\n</Facture>";
		return xml;
	}

	public org.jdom.Element toXMLElement(){
		org.jdom.Element element = new org.jdom.Element("Facture");
		element.setAttribute("id", ""+this.getId());
		element.setAttribute("dateFacture", ""+this.getDateFacture());
		element.setAttribute("idClient", ""+this.getIdClient());
		element.setAttribute("montant", ""+this.getMontant());
		element.setAttribute("typePayementC", ""+this.getTypePayementC().getValue());

		element.addContent(client.toXMLElement());

		return element;
	}

	public static Facture parseXMLElement(org.jdom.Element element){
		Facture facture = new Facture();
		facture.setId(Integer.parseInt(element.getAttributeValue("id")));
		facture.setDateFacture(utils.StringUtils.getDateFromString(element.getAttributeValue("dateFacture")));
		facture.setIdClient(Integer.parseInt(element.getAttributeValue("idClient")));
		facture.setMontant(Double.parseDouble(element.getAttributeValue("montant")));
		facture.setTypePayementC(TypePayementC.getByValue(element.getAttributeValue("typePayementC")));

		facture.setClient(Client.parseXMLElement(element.getChild("Client")));

		return facture;
	}

	public static Facture parseXMLString(String xmlString){
		try{
			org.jdom.Element element = utils.StringUtils.xmlStringToXmlElement(xmlString);
			return parseXMLElement(element);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/*
	* XML File must have this structure :
	* <ClassNames>
	* 		<ClassName attribute1='value1' attribute2='value2' ... attributeN='valueN'/>
	* 		<ClassName attribute1='value1' attribute2='value2' ... attributeN='valueN'/>
	* 		<ClassName attribute1='value1' attribute2='value2' ... attributeN='valueN'/>
	* 		...
	* 		...
	* 		<ClassName attribute1='value1' attribute2='value2' ... attributeN='valueN'/>
	* </ClassNames>
	*/
	public static java.util.List<Facture> parseXMLFile (java.io.File xmlFile){
		java.util.List<Facture> listFactures = new java.util.ArrayList<Facture>();
		try{
			org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder();
			org.jdom.Document document = builder.build(xmlFile);
			org.jdom.Element rootNode = document.getRootElement();
			if (!rootNode.getName().equals("Factures"))
				return null;
			java.util.List<?> listChildren = rootNode.getChildren("Facture");
			for (Object child : listChildren){
				if (! (child instanceof org.jdom.Element))
					continue;
				org.jdom.Element elementChild = (org.jdom.Element)child;
				if (! elementChild.getName().equals("Facture"))
					continue;
				listFactures.add(parseXMLElement(elementChild));
			}
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
			return null;
		}
		return listFactures;
	}

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/
	public String toString(){
		String nomClient = "";
		
		Client client = this.getClient();
		if (client != null){
			nomClient = client.getNom()+" "+client.getPrenom();
		}
		
		String html = "<html>";
		html+= "Facture : "+this.getId()+"<br/>";
		html+= "Client : "+nomClient;
		html+= "</html>";
		return html;
	}
}