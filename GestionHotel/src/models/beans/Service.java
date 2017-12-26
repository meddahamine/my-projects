package models.beans;

import java.io.Serializable;
/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This classe is a Bean. 
 */

public class Service implements Serializable {
	private static final long serialVersionUID = 1L;

	private java.lang.Integer id = 0;
	private java.lang.String designation = "";
	private java.lang.Double prix = 0.0;
	
	private static boolean autoFetchListOfListOfClientConsommeServicesService = false;
	private java.util.List<ClientConsommeService> listOfClientConsommeServicesService = new java.util.ArrayList<ClientConsommeService>();

	public Service() {
	}

	public Service(java.lang.Integer id, java.lang.String designation, java.lang.Double prix) {
		this.setId(id);
		this.setDesignation(designation);
		this.setPrix(prix);
	}

	public Service(String xmlString) {
		org.jdom.Element element = utils.StringUtils.xmlStringToXmlElement(xmlString);
		fillByXMLElement(element);
	}

	public Service(org.jdom.Element element) {
		fillByXMLElement(element);
	}

	public void fillByXMLElement(org.jdom.Element element) {
		this.setId(Integer.parseInt(element.getAttributeValue("id")));
		this.setDesignation(element.getAttributeValue("designation"));
		this.setPrix(Double.parseDouble(element.getAttributeValue("prix")));
	}

	public void fillByMap(java.util.Map<String, String> data) {
		this.setId(Integer.parseInt(data.get("id")));
		this.setDesignation(data.get("designation"));
		this.setPrix(Double.parseDouble(data.get("prix")));
	}

	public void fillByArray(String[] array) {
		this.setId(Integer.parseInt(array[0]));
		this.setDesignation(array[1]);
		this.setPrix(Double.parseDouble(array[2]));
	}

	public boolean equals(Object obj) {
		if (obj == null){
			return false;
		}
		if (!(obj instanceof Service)){
			return false;
		}
		Service item = (Service)obj;
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

	public java.lang.String getDesignation(){
		return this.designation;
	}

	public void setDesignation(java.lang.String designation){
		this.designation = designation;
	}

	public java.lang.Double getPrix(){
		return this.prix;
	}

	public void setPrix(java.lang.Double prix){
		this.prix = prix;
	}

	public String toXMLString(){
		String xml = "<Service ";
		xml+= "id='"+this.getId()+"' ";
		xml+= "designation='"+this.getDesignation()+"' ";
		xml+= "prix='"+this.getPrix()+"' ";
		xml+= ">";
		xml+= "\n</Service>";
		return xml;
	}

	public org.jdom.Element toXMLElement(){
		org.jdom.Element element = new org.jdom.Element("Service");
		element.setAttribute("id", ""+this.getId());
		element.setAttribute("designation", ""+this.getDesignation());
		element.setAttribute("prix", ""+this.getPrix());


		return element;
	}

	public static Service parseXMLElement(org.jdom.Element element){
		Service service = new Service();
		service.setId(Integer.parseInt(element.getAttributeValue("id")));
		service.setDesignation(element.getAttributeValue("designation"));
		service.setPrix(Double.parseDouble(element.getAttributeValue("prix")));


		return service;
	}

	public static Service parseXMLString(String xmlString){
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
	public static java.util.List<Service> parseXMLFile (java.io.File xmlFile){
		java.util.List<Service> listServices = new java.util.ArrayList<Service>();
		try{
			org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder();
			org.jdom.Document document = builder.build(xmlFile);
			org.jdom.Element rootNode = document.getRootElement();
			if (!rootNode.getName().equals("Services"))
				return null;
			java.util.List<?> listChildren = rootNode.getChildren("Service");
			for (Object child : listChildren){
				if (! (child instanceof org.jdom.Element))
					continue;
				org.jdom.Element elementChild = (org.jdom.Element)child;
				if (! elementChild.getName().equals("Service"))
					continue;
				listServices.add(parseXMLElement(elementChild));
			}
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
			return null;
		}
		return listServices;
	}

	public java.util.List<ClientConsommeService> getListOfClientConsommeServicesServices(){
		return this.listOfClientConsommeServicesService;
	}

	public void setListOfClientConsommeServicesServices(java.util.List<ClientConsommeService> list){
		this.listOfClientConsommeServicesService.clear();
		for (ClientConsommeService item : list){
			this.listOfClientConsommeServicesService.add(item);
		}
	}

	public static boolean isAutoFetchListOfListOfClientConsommeServicesService(){
		return Service.autoFetchListOfListOfClientConsommeServicesService;
	}

	public static void setAutoFetchListOfListOfClientConsommeServicesService(boolean autoFetchListOfListOfClientConsommeServicesService){
		Service.autoFetchListOfListOfClientConsommeServicesService = autoFetchListOfListOfClientConsommeServicesService;
	}

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

	public String toString(){
		String html = "<html>";
		html+= ""+this.getDesignation();
		html+= "</html>";
		return html;
	}
}