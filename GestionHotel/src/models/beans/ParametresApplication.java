package models.beans;

import java.io.Serializable;
/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This classe is a Bean. 
 */

public class ParametresApplication implements Serializable {
	private static final long serialVersionUID = 1L;

	private java.lang.Integer id = 0;
	private java.lang.String designation = "";
	private java.lang.String description = "";
	private java.lang.Integer idPhoto = 0;

	private Photo photo = null;

	public ParametresApplication() {
	}

	public ParametresApplication(java.lang.Integer id, java.lang.String designation, java.lang.String description, java.lang.Integer idPhoto) {
		this.setId(id);
		this.setDesignation(designation);
		this.setDescription(description);
		this.setIdPhoto(idPhoto);
	}

	public ParametresApplication(String xmlString) {
		org.jdom.Element element = utils.StringUtils.xmlStringToXmlElement(xmlString);
		fillByXMLElement(element);
	}

	public ParametresApplication(org.jdom.Element element) {
		fillByXMLElement(element);
	}

	public void fillByXMLElement(org.jdom.Element element) {
		this.setId(Integer.parseInt(element.getAttributeValue("id")));
		this.setDesignation(element.getAttributeValue("designation"));
		this.setDescription(element.getAttributeValue("description"));
		this.setIdPhoto(Integer.parseInt(element.getAttributeValue("idPhoto")));
	}

	public void fillByMap(java.util.Map<String, String> data) {
		this.setId(Integer.parseInt(data.get("id")));
		this.setDesignation(data.get("designation"));
		this.setDescription(data.get("description"));
		this.setIdPhoto(Integer.parseInt(data.get("idPhoto")));
	}

	public void fillByArray(String[] array) {
		this.setId(Integer.parseInt(array[0]));
		this.setDesignation(array[1]);
		this.setDescription(array[2]);
		this.setIdPhoto(Integer.parseInt(array[3]));
	}

	public boolean equals(Object obj) {
		if (obj == null){
			return false;
		}
		if (!(obj instanceof ParametresApplication)){
			return false;
		}
		ParametresApplication item = (ParametresApplication)obj;
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

	public java.lang.String getDescription(){
		return this.description;
	}

	public void setDescription(java.lang.String description){
		this.description = description;
	}

	public java.lang.Integer getIdPhoto(){
		return this.idPhoto;
	}

	public void setIdPhoto(java.lang.Integer idPhoto){
		this.idPhoto = idPhoto;
	}

	public Photo getPhoto(){
		return this.photo;
	}

	public void setPhoto(Photo photo){
		this.photo = photo;
		int idPhoto = 0;
		if (photo != null){
			idPhoto = photo.getId().intValue();
		}
		this.idPhoto = idPhoto;
	}

	public String toXMLString(){
		String xml = "<ParametresApplication ";
		xml+= "id='"+this.getId()+"' ";
		xml+= "designation='"+this.getDesignation()+"' ";
		xml+= "description='"+this.getDescription()+"' ";
		xml+= "idPhoto='"+this.getIdPhoto()+"' ";
		xml+= ">";
		xml+= "\n	"+photo.toXMLString().replace("<Photo", "<Photo").replace("</Photo", "</Photo");
		xml+= "\n</ParametresApplication>";
		return xml;
	}

	public org.jdom.Element toXMLElement(){
		org.jdom.Element element = new org.jdom.Element("ParametresApplication");
		element.setAttribute("id", ""+this.getId());
		element.setAttribute("designation", ""+this.getDesignation());
		element.setAttribute("description", ""+this.getDescription());
		element.setAttribute("idPhoto", ""+this.getIdPhoto());

		element.addContent(photo.toXMLElement());

		return element;
	}

	public static ParametresApplication parseXMLElement(org.jdom.Element element){
		ParametresApplication parametresApplication = new ParametresApplication();
		parametresApplication.setId(Integer.parseInt(element.getAttributeValue("id")));
		parametresApplication.setDesignation(element.getAttributeValue("designation"));
		parametresApplication.setDescription(element.getAttributeValue("description"));
		parametresApplication.setIdPhoto(Integer.parseInt(element.getAttributeValue("idPhoto")));

		parametresApplication.setPhoto(Photo.parseXMLElement(element.getChild("Photo")));

		return parametresApplication;
	}

	public static ParametresApplication parseXMLString(String xmlString){
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
	public static java.util.List<ParametresApplication> parseXMLFile (java.io.File xmlFile){
		java.util.List<ParametresApplication> listParametresApplications = new java.util.ArrayList<ParametresApplication>();
		try{
			org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder();
			org.jdom.Document document = builder.build(xmlFile);
			org.jdom.Element rootNode = document.getRootElement();
			if (!rootNode.getName().equals("ParametresApplications"))
				return null;
			java.util.List<?> listChildren = rootNode.getChildren("ParametresApplication");
			for (Object child : listChildren){
				if (! (child instanceof org.jdom.Element))
					continue;
				org.jdom.Element elementChild = (org.jdom.Element)child;
				if (! elementChild.getName().equals("ParametresApplication"))
					continue;
				listParametresApplications.add(parseXMLElement(elementChild));
			}
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
			return null;
		}
		return listParametresApplications;
	}

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

	public String toString(){
		String html = "<html>";
		html+= "";
		html+= "</html>";
		return html;
	}
}