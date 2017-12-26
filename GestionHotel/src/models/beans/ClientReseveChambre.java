package models.beans;

import java.io.Serializable;
/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This classe is a Bean. 
 */

@SuppressWarnings("deprecation")
public class ClientReseveChambre implements Serializable {
	private static final long serialVersionUID = 1L;

	private java.lang.Integer id = 0;
	private java.lang.Integer idClient = 0;
	private java.lang.Integer idChambre = 0;
	private java.sql.Date dateDebutReservation = new java.sql.Date(1, 0, 1);
	private java.sql.Date dateFinReservation = new java.sql.Date(1, 0, 1);

	private Client client = null;

	private Chambre chambre = null;

	public ClientReseveChambre() {
	}

	public ClientReseveChambre(java.lang.Integer id, java.lang.Integer idClient, java.lang.Integer idChambre, java.sql.Date dateDebutReservation, java.sql.Date dateFinReservation) {
		this.setId(id);
		this.setIdClient(idClient);
		this.setIdChambre(idChambre);
		this.setDateDebutReservation(dateDebutReservation);
		this.setDateFinReservation(dateFinReservation);
	}

	public ClientReseveChambre(String xmlString) {
		org.jdom.Element element = utils.StringUtils.xmlStringToXmlElement(xmlString);
		fillByXMLElement(element);
	}

	public ClientReseveChambre(org.jdom.Element element) {
		fillByXMLElement(element);
	}

	public void fillByXMLElement(org.jdom.Element element) {
		this.setId(Integer.parseInt(element.getAttributeValue("id")));
		this.setIdClient(Integer.parseInt(element.getAttributeValue("idClient")));
		this.setIdChambre(Integer.parseInt(element.getAttributeValue("idChambre")));
		this.setDateDebutReservation(utils.StringUtils.getDateFromString(element.getAttributeValue("dateDebutReservation")));
		this.setDateFinReservation(utils.StringUtils.getDateFromString(element.getAttributeValue("dateFinReservation")));
	}

	public void fillByMap(java.util.Map<String, String> data) {
		this.setId(Integer.parseInt(data.get("id")));
		this.setIdClient(Integer.parseInt(data.get("idClient")));
		this.setIdChambre(Integer.parseInt(data.get("idChambre")));
		this.setDateDebutReservation(utils.StringUtils.getDateFromString(data.get("dateDebutReservation")));
		this.setDateFinReservation(utils.StringUtils.getDateFromString(data.get("dateFinReservation")));
	}

	public void fillByArray(String[] array) {
		this.setId(Integer.parseInt(array[0]));
		this.setIdClient(Integer.parseInt(array[1]));
		this.setIdChambre(Integer.parseInt(array[2]));
		this.setDateDebutReservation(utils.StringUtils.getDateFromString(array[3]));
		this.setDateFinReservation(utils.StringUtils.getDateFromString(array[4]));
	}

	public boolean equals(Object obj) {
		if (obj == null){
			return false;
		}
		if (!(obj instanceof ClientReseveChambre)){
			return false;
		}
		ClientReseveChambre item = (ClientReseveChambre)obj;
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

	public java.lang.Integer getIdChambre(){
		return this.idChambre;
	}

	public void setIdChambre(java.lang.Integer idChambre){
		this.idChambre = idChambre;
	}

	public Chambre getChambre(){
		return this.chambre;
	}

	public void setChambre(Chambre chambre){
		this.chambre = chambre;
		int idChambre = 0;
		if (chambre != null){
			idChambre = chambre.getId().intValue();
		}
		this.idChambre = idChambre;
	}

	public java.sql.Date getDateDebutReservation(){
		return this.dateDebutReservation;
	}

	public void setDateDebutReservation(java.sql.Date dateDebutReservation){
		this.dateDebutReservation = dateDebutReservation;
	}

	public java.sql.Date getDateFinReservation(){
		return this.dateFinReservation;
	}

	public void setDateFinReservation(java.sql.Date dateFinReservation){
		this.dateFinReservation = dateFinReservation;
	}

	public String toXMLString(){
		String xml = "<ClientReseveChambre ";
		xml+= "id='"+this.getId()+"' ";
		xml+= "idClient='"+this.getIdClient()+"' ";
		xml+= "idChambre='"+this.getIdChambre()+"' ";
		xml+= "dateDebutReservation='"+this.getDateDebutReservation()+"' ";
		xml+= "dateFinReservation='"+this.getDateFinReservation()+"' ";
		xml+= ">";
		xml+= "\n	"+client.toXMLString().replace("<Client", "<Client").replace("</Client", "</Client");
		xml+= "\n	"+chambre.toXMLString().replace("<Chambre", "<Chambre").replace("</Chambre", "</Chambre");
		xml+= "\n</ClientReseveChambre>";
		return xml;
	}

	public org.jdom.Element toXMLElement(){
		org.jdom.Element element = new org.jdom.Element("ClientReseveChambre");
		element.setAttribute("id", ""+this.getId());
		element.setAttribute("idClient", ""+this.getIdClient());
		element.setAttribute("idChambre", ""+this.getIdChambre());
		element.setAttribute("dateDebutReservation", ""+this.getDateDebutReservation());
		element.setAttribute("dateFinReservation", ""+this.getDateFinReservation());

		element.addContent(client.toXMLElement());
		element.addContent(chambre.toXMLElement());

		return element;
	}

	public static ClientReseveChambre parseXMLElement(org.jdom.Element element){
		ClientReseveChambre clientReseveChambre = new ClientReseveChambre();
		clientReseveChambre.setId(Integer.parseInt(element.getAttributeValue("id")));
		clientReseveChambre.setIdClient(Integer.parseInt(element.getAttributeValue("idClient")));
		clientReseveChambre.setIdChambre(Integer.parseInt(element.getAttributeValue("idChambre")));
		clientReseveChambre.setDateDebutReservation(utils.StringUtils.getDateFromString(element.getAttributeValue("dateDebutReservation")));
		clientReseveChambre.setDateFinReservation(utils.StringUtils.getDateFromString(element.getAttributeValue("dateFinReservation")));

		clientReseveChambre.setClient(Client.parseXMLElement(element.getChild("Client")));
		clientReseveChambre.setChambre(Chambre.parseXMLElement(element.getChild("Chambre")));

		return clientReseveChambre;
	}

	public static ClientReseveChambre parseXMLString(String xmlString){
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
	public static java.util.List<ClientReseveChambre> parseXMLFile (java.io.File xmlFile){
		java.util.List<ClientReseveChambre> listClientReseveChambres = new java.util.ArrayList<ClientReseveChambre>();
		try{
			org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder();
			org.jdom.Document document = builder.build(xmlFile);
			org.jdom.Element rootNode = document.getRootElement();
			if (!rootNode.getName().equals("ClientReseveChambres"))
				return null;
			java.util.List<?> listChildren = rootNode.getChildren("ClientReseveChambre");
			for (Object child : listChildren){
				if (! (child instanceof org.jdom.Element))
					continue;
				org.jdom.Element elementChild = (org.jdom.Element)child;
				if (! elementChild.getName().equals("ClientReseveChambre"))
					continue;
				listClientReseveChambres.add(parseXMLElement(elementChild));
			}
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
			return null;
		}
		return listClientReseveChambres;
	}

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

	public String toString(){
		
String nomClient = "";
String descrChambre = "";

		
		Client client = this.getClient();
		if (client != null){
			nomClient = client.getNom()+" "+client.getPrenom();
		}
		Chambre chambre = this.getChambre();
		if (chambre != null){
			descrChambre = chambre.getNumChamre()+" "+chambre.getNumEtage();
		}
		
		String html = "<html>";
		html+= "Nom & prénom : "+ nomClient +"<br/>";
		html+= "Chambre : "+descrChambre;
		html+= "</html>";
		return html;
	}
}