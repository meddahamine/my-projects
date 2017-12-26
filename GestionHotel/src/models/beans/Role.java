package models.beans;

import java.io.Serializable;
/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This classe is a Bean. 
 */

public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	private java.lang.Integer id = 0;
	private java.lang.String designation = "";
	private java.lang.Integer idPhoto = 0;
	private java.lang.Boolean parametresOrganisme = Boolean.valueOf("false");
	private java.lang.Boolean gestionRole = Boolean.valueOf("false");
	private java.lang.Boolean gestionUtilisateur = Boolean.valueOf("false");

	private Photo photo = null;
	
	private static boolean autoFetchListOfListOfUtilisateursRole = false;
	private java.util.List<Utilisateur> listOfUtilisateursRole = new java.util.ArrayList<Utilisateur>();

	public Role() {
	}

	public Role(java.lang.Integer id, java.lang.String designation, java.lang.Integer idPhoto, java.lang.Boolean parametresOrganisme, java.lang.Boolean gestionRole, java.lang.Boolean gestionUtilisateur) {
		this.setId(id);
		this.setDesignation(designation);
		this.setIdPhoto(idPhoto);
		this.setParametresOrganisme(parametresOrganisme);
		this.setGestionRole(gestionRole);
		this.setGestionUtilisateur(gestionUtilisateur);
	}

	public Role(String xmlString) {
		org.jdom.Element element = utils.StringUtils.xmlStringToXmlElement(xmlString);
		fillByXMLElement(element);
	}

	public Role(org.jdom.Element element) {
		fillByXMLElement(element);
	}

	public void fillByXMLElement(org.jdom.Element element) {
		this.setId(Integer.parseInt(element.getAttributeValue("id")));
		this.setDesignation(element.getAttributeValue("designation"));
		this.setIdPhoto(Integer.parseInt(element.getAttributeValue("idPhoto")));
		this.setParametresOrganisme(Boolean.parseBoolean(element.getAttributeValue("parametresOrganisme")));
		this.setGestionRole(Boolean.parseBoolean(element.getAttributeValue("gestionRole")));
		this.setGestionUtilisateur(Boolean.parseBoolean(element.getAttributeValue("gestionUtilisateur")));
	}

	public void fillByMap(java.util.Map<String, String> data) {
		this.setId(Integer.parseInt(data.get("id")));
		this.setDesignation(data.get("designation"));
		this.setIdPhoto(Integer.parseInt(data.get("idPhoto")));
		this.setParametresOrganisme(Boolean.parseBoolean(data.get("parametresOrganisme")));
		this.setGestionRole(Boolean.parseBoolean(data.get("gestionRole")));
		this.setGestionUtilisateur(Boolean.parseBoolean(data.get("gestionUtilisateur")));
	}

	public void fillByArray(String[] array) {
		this.setId(Integer.parseInt(array[0]));
		this.setDesignation(array[1]);
		this.setIdPhoto(Integer.parseInt(array[2]));
		this.setParametresOrganisme(Boolean.parseBoolean(array[3]));
		this.setGestionRole(Boolean.parseBoolean(array[4]));
		this.setGestionUtilisateur(Boolean.parseBoolean(array[5]));
	}

	public boolean equals(Object obj) {
		if (obj == null){
			return false;
		}
		if (!(obj instanceof Role)){
			return false;
		}
		Role item = (Role)obj;
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

	public java.lang.Boolean isParametresOrganisme(){
		return this.parametresOrganisme;
	}

	public void setParametresOrganisme(java.lang.Boolean parametresOrganisme){
		this.parametresOrganisme = parametresOrganisme;
	}

	public java.lang.Boolean isGestionRole(){
		return this.gestionRole;
	}

	public void setGestionRole(java.lang.Boolean gestionRole){
		this.gestionRole = gestionRole;
	}

	public java.lang.Boolean isGestionUtilisateur(){
		return this.gestionUtilisateur;
	}

	public void setGestionUtilisateur(java.lang.Boolean gestionUtilisateur){
		this.gestionUtilisateur = gestionUtilisateur;
	}

	public String toXMLString(){
		String xml = "<Role ";
		xml+= "id='"+this.getId()+"' ";
		xml+= "designation='"+this.getDesignation()+"' ";
		xml+= "idPhoto='"+this.getIdPhoto()+"' ";
		xml+= "parametresOrganisme='"+this.isParametresOrganisme()+"' ";
		xml+= "gestionRole='"+this.isGestionRole()+"' ";
		xml+= "gestionUtilisateur='"+this.isGestionUtilisateur()+"' ";
		xml+= ">";
		xml+= "\n	"+photo.toXMLString().replace("<Photo", "<Photo").replace("</Photo", "</Photo");
		xml+= "\n</Role>";
		return xml;
	}

	public org.jdom.Element toXMLElement(){
		org.jdom.Element element = new org.jdom.Element("Role");
		element.setAttribute("id", ""+this.getId());
		element.setAttribute("designation", ""+this.getDesignation());
		element.setAttribute("idPhoto", ""+this.getIdPhoto());
		element.setAttribute("parametresOrganisme", ""+this.isParametresOrganisme());
		element.setAttribute("gestionRole", ""+this.isGestionRole());
		element.setAttribute("gestionUtilisateur", ""+this.isGestionUtilisateur());

		element.addContent(photo.toXMLElement());

		return element;
	}

	public static Role parseXMLElement(org.jdom.Element element){
		Role role = new Role();
		role.setId(Integer.parseInt(element.getAttributeValue("id")));
		role.setDesignation(element.getAttributeValue("designation"));
		role.setIdPhoto(Integer.parseInt(element.getAttributeValue("idPhoto")));
		role.setParametresOrganisme(Boolean.parseBoolean(element.getAttributeValue("parametresOrganisme")));
		role.setGestionRole(Boolean.parseBoolean(element.getAttributeValue("gestionRole")));
		role.setGestionUtilisateur(Boolean.parseBoolean(element.getAttributeValue("gestionUtilisateur")));

		role.setPhoto(Photo.parseXMLElement(element.getChild("Photo")));

		return role;
	}

	public static Role parseXMLString(String xmlString){
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
	public static java.util.List<Role> parseXMLFile (java.io.File xmlFile){
		java.util.List<Role> listRoles = new java.util.ArrayList<Role>();
		try{
			org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder();
			org.jdom.Document document = builder.build(xmlFile);
			org.jdom.Element rootNode = document.getRootElement();
			if (!rootNode.getName().equals("Roles"))
				return null;
			java.util.List<?> listChildren = rootNode.getChildren("Role");
			for (Object child : listChildren){
				if (! (child instanceof org.jdom.Element))
					continue;
				org.jdom.Element elementChild = (org.jdom.Element)child;
				if (! elementChild.getName().equals("Role"))
					continue;
				listRoles.add(parseXMLElement(elementChild));
			}
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
			return null;
		}
		return listRoles;
	}

	public java.util.List<Utilisateur> getListOfUtilisateursRoles(){
		return this.listOfUtilisateursRole;
	}

	public void setListOfUtilisateursRoles(java.util.List<Utilisateur> list){
		this.listOfUtilisateursRole.clear();
		for (Utilisateur item : list){
			this.listOfUtilisateursRole.add(item);
		}
	}

	public static boolean isAutoFetchListOfListOfUtilisateursRole(){
		return Role.autoFetchListOfListOfUtilisateursRole;
	}

	public static void setAutoFetchListOfListOfUtilisateursRole(boolean autoFetchListOfListOfUtilisateursRole){
		Role.autoFetchListOfListOfUtilisateursRole = autoFetchListOfListOfUtilisateursRole;
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