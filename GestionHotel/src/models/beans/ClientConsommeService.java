package models.beans;

import java.io.Serializable;
/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This classe is a Bean. 
 */

@SuppressWarnings("deprecation")
public class ClientConsommeService implements Serializable {
	private static final long serialVersionUID = 1L;

	private java.lang.Integer id = 0;
	private java.lang.Integer idClient = 0;
	private java.lang.Integer idService = 0;
	private java.lang.Double prixService = 0.0;
	private java.sql.Date dateConsommationService = new java.sql.Date(1, 0, 1);

	private Client client = null;

	private Service service = null;

	public ClientConsommeService() {
	}

	public ClientConsommeService(java.lang.Integer id, java.lang.Integer idClient, java.lang.Integer idService, java.lang.Double prixService, java.sql.Date dateConsommationService) {
		this.setId(id);
		this.setIdClient(idClient);
		this.setIdService(idService);
		this.setPrixService(prixService);
		this.setDateConsommationService(dateConsommationService);
	}

	public ClientConsommeService(String xmlString) {
		org.jdom.Element element = utils.StringUtils.xmlStringToXmlElement(xmlString);
		fillByXMLElement(element);
	}

	public ClientConsommeService(org.jdom.Element element) {
		fillByXMLElement(element);
	}

	public void fillByXMLElement(org.jdom.Element element) {
		this.setId(Integer.parseInt(element.getAttributeValue("id")));
		this.setIdClient(Integer.parseInt(element.getAttributeValue("idClient")));
		this.setIdService(Integer.parseInt(element.getAttributeValue("idService")));
		this.setPrixService(Double.parseDouble(element.getAttributeValue("prixService")));
		this.setDateConsommationService(utils.StringUtils.getDateFromString(element.getAttributeValue("dateConsommationService")));
	}

	public void fillByMap(java.util.Map<String, String> data) {
		this.setId(Integer.parseInt(data.get("id")));
		this.setIdClient(Integer.parseInt(data.get("idClient")));
		this.setIdService(Integer.parseInt(data.get("idService")));
		this.setPrixService(Double.parseDouble(data.get("prixService")));
		this.setDateConsommationService(utils.StringUtils.getDateFromString(data.get("dateConsommationService")));
	}

	public void fillByArray(String[] array) {
		this.setId(Integer.parseInt(array[0]));
		this.setIdClient(Integer.parseInt(array[1]));
		this.setIdService(Integer.parseInt(array[2]));
		this.setPrixService(Double.parseDouble(array[3]));
		this.setDateConsommationService(utils.StringUtils.getDateFromString(array[4]));
	}

	public boolean equals(Object obj) {
		if (obj == null){
			return false;
		}
		if (!(obj instanceof ClientConsommeService)){
			return false;
		}
		ClientConsommeService item = (ClientConsommeService)obj;
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

	public java.lang.Integer getIdService(){
		return this.idService;
	}

	public void setIdService(java.lang.Integer idService){
		this.idService = idService;
	}

	public Service getService(){
		return this.service;
	}

	public void setService(Service service){
		this.service = service;
		int idService = 0;
		if (service != null){
			idService = service.getId().intValue();
		}
		this.idService = idService;
	}

	public java.lang.Double getPrixService(){
		return this.prixService;
	}

	public void setPrixService(java.lang.Double prixService){
		this.prixService = prixService;
	}

	public java.sql.Date getDateConsommationService(){
		return this.dateConsommationService;
	}

	public void setDateConsommationService(java.sql.Date dateConsommationService){
		this.dateConsommationService = dateConsommationService;
	}

	public String toXMLString(){
		String xml = "<ClientConsommeService ";
		xml+= "id='"+this.getId()+"' ";
		xml+= "idClient='"+this.getIdClient()+"' ";
		xml+= "idService='"+this.getIdService()+"' ";
		xml+= "prixService='"+this.getPrixService()+"' ";
		xml+= "dateConsommationService='"+this.getDateConsommationService()+"' ";
		xml+= ">";
		xml+= "\n	"+client.toXMLString().replace("<Client", "<Client").replace("</Client", "</Client");
		xml+= "\n	"+service.toXMLString().replace("<Service", "<Service").replace("</Service", "</Service");
		xml+= "\n</ClientConsommeService>";
		return xml;
	}

	public org.jdom.Element toXMLElement(){
		org.jdom.Element element = new org.jdom.Element("ClientConsommeService");
		element.setAttribute("id", ""+this.getId());
		element.setAttribute("idClient", ""+this.getIdClient());
		element.setAttribute("idService", ""+this.getIdService());
		element.setAttribute("prixService", ""+this.getPrixService());
		element.setAttribute("dateConsommationService", ""+this.getDateConsommationService());

		element.addContent(client.toXMLElement());
		element.addContent(service.toXMLElement());

		return element;
	}

	public static ClientConsommeService parseXMLElement(org.jdom.Element element){
		ClientConsommeService clientConsommeService = new ClientConsommeService();
		clientConsommeService.setId(Integer.parseInt(element.getAttributeValue("id")));
		clientConsommeService.setIdClient(Integer.parseInt(element.getAttributeValue("idClient")));
		clientConsommeService.setIdService(Integer.parseInt(element.getAttributeValue("idService")));
		clientConsommeService.setPrixService(Double.parseDouble(element.getAttributeValue("prixService")));
		clientConsommeService.setDateConsommationService(utils.StringUtils.getDateFromString(element.getAttributeValue("dateConsommationService")));

		clientConsommeService.setClient(Client.parseXMLElement(element.getChild("Client")));
		clientConsommeService.setService(Service.parseXMLElement(element.getChild("Service")));

		return clientConsommeService;
	}

	public static ClientConsommeService parseXMLString(String xmlString){
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
	public static java.util.List<ClientConsommeService> parseXMLFile (java.io.File xmlFile){
		java.util.List<ClientConsommeService> listClientConsommeServices = new java.util.ArrayList<ClientConsommeService>();
		try{
			org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder();
			org.jdom.Document document = builder.build(xmlFile);
			org.jdom.Element rootNode = document.getRootElement();
			if (!rootNode.getName().equals("ClientConsommeServices"))
				return null;
			java.util.List<?> listChildren = rootNode.getChildren("ClientConsommeService");
			for (Object child : listChildren){
				if (! (child instanceof org.jdom.Element))
					continue;
				org.jdom.Element elementChild = (org.jdom.Element)child;
				if (! elementChild.getName().equals("ClientConsommeService"))
					continue;
				listClientConsommeServices.add(parseXMLElement(elementChild));
			}
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
			return null;
		}
		return listClientConsommeServices;
	}

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

	public String toString(){
		String nomClient = "";
		String descrService = "";

				
				Client client = this.getClient();
				if (client != null){
					nomClient = client.getNom()+" "+client.getPrenom();
				}
				Service service = this.getService();
				if (service != null){
					descrService = service.getDesignation();
				}
				
				String html = "<html>";
				html+= "Nom & prénom : "+ nomClient +"<br/>";
				html+= "Service : "+descrService;
				html+= "</html>";
				return html;
	}
}