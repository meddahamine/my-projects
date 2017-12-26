package models.beans;

import java.io.Serializable;
/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This classe is a Bean. 
 */

public class Photo implements Serializable {
	private static final long serialVersionUID = 1L;

	private java.lang.Integer id = 0;
	private byte[] data = null;
	
	private java.io.File 			filePhoto = null;
	private javax.swing.ImageIcon 	imageIcon = null;
	
	private static boolean autoFetchListOfListOfParametresApplicationsPhoto = false;
	private java.util.List<ParametresApplication> listOfParametresApplicationsPhoto = new java.util.ArrayList<ParametresApplication>();
	
	private static boolean autoFetchListOfListOfParametresOrganismesPhoto = false;
	private java.util.List<ParametresOrganisme> listOfParametresOrganismesPhoto = new java.util.ArrayList<ParametresOrganisme>();
	
	private static boolean autoFetchListOfListOfRolesPhoto = false;
	private java.util.List<Role> listOfRolesPhoto = new java.util.ArrayList<Role>();
	
	private static boolean autoFetchListOfListOfUtilisateursPhoto = false;
	private java.util.List<Utilisateur> listOfUtilisateursPhoto = new java.util.ArrayList<Utilisateur>();

	public Photo() {
	}

	public Photo(java.lang.Integer id, byte[] data) {
		this.setId(id);
		this.setData(data);
	}

	public Photo(String xmlString) {
		org.jdom.Element element = utils.StringUtils.xmlStringToXmlElement(xmlString);
		fillByXMLElement(element);
	}

	public Photo(org.jdom.Element element) {
		fillByXMLElement(element);
	}

	public void fillByXMLElement(org.jdom.Element element) {
		this.setId(Integer.parseInt(element.getAttributeValue("id")));
		this.setData(utils.FilesAndLaunchUtils.hexStringToByteArray(element.getAttributeValue("data")));
	}

	public void fillByMap(java.util.Map<String, String> data) {
		this.setId(Integer.parseInt(data.get("id")));
		this.setData(utils.FilesAndLaunchUtils.hexStringToByteArray(data.get("data")));
	}

	public void fillByArray(String[] array) {
		this.setId(Integer.parseInt(array[0]));
		this.setData(utils.FilesAndLaunchUtils.hexStringToByteArray(array[1]));
	}

	public boolean equals(Object obj) {
		if (obj == null){
			return false;
		}
		if (!(obj instanceof Photo)){
			return false;
		}
		Photo item = (Photo)obj;
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

	public byte[] getData(){
		return this.data;
	}

	public void setData(byte[] data){
		this.data = data;
	}

	public String toXMLString(){
		String xml = "<Photo ";
		xml+= "id='"+this.getId()+"' ";
		xml+= "data='"+utils.FilesAndLaunchUtils.getDataContentAsHex(this.getData())+"' ";
		xml+= ">";
		xml+= "\n</Photo>";
		return xml;
	}

	public org.jdom.Element toXMLElement(){
		org.jdom.Element element = new org.jdom.Element("Photo");
		element.setAttribute("id", ""+this.getId());
		element.setAttribute("data", ""+utils.FilesAndLaunchUtils.getDataContentAsHex(this.getData()));


		return element;
	}

	public static Photo parseXMLElement(org.jdom.Element element){
		Photo photo = new Photo();
		photo.setId(Integer.parseInt(element.getAttributeValue("id")));
		photo.setData(utils.FilesAndLaunchUtils.hexStringToByteArray(element.getAttributeValue("data")));


		return photo;
	}

	public static Photo parseXMLString(String xmlString){
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
	public static java.util.List<Photo> parseXMLFile (java.io.File xmlFile){
		java.util.List<Photo> listPhotos = new java.util.ArrayList<Photo>();
		try{
			org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder();
			org.jdom.Document document = builder.build(xmlFile);
			org.jdom.Element rootNode = document.getRootElement();
			if (!rootNode.getName().equals("Photos"))
				return null;
			java.util.List<?> listChildren = rootNode.getChildren("Photo");
			for (Object child : listChildren){
				if (! (child instanceof org.jdom.Element))
					continue;
				org.jdom.Element elementChild = (org.jdom.Element)child;
				if (! elementChild.getName().equals("Photo"))
					continue;
				listPhotos.add(parseXMLElement(elementChild));
			}
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
			return null;
		}
		return listPhotos;
	}

	public java.io.File dataToFile(){
		try{
			java.io.File file = java.io.File.createTempFile("data", ".ext");
			file.deleteOnExit();
			
			java.io.FileOutputStream writer = new java.io.FileOutputStream(file);
			
			writer.write(this.getData());
			writer.close();
			
			return file;
		}
		catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		return null;
	}

	public java.io.File getFilePhoto(){
		if (filePhoto == null && this.getId().intValue() > 0){
			this.filePhoto = this.dataToFile();
		}
		
		return filePhoto;
	}

	public javax.swing.ImageIcon getImageIcon(){
		if (imageIcon == null && this.getId().intValue() > 0){
			imageIcon = new javax.swing.ImageIcon(this.getData());
		}
		
		return imageIcon;
	}

	public java.util.List<ParametresApplication> getListOfParametresApplicationsPhotos(){
		return this.listOfParametresApplicationsPhoto;
	}

	public void setListOfParametresApplicationsPhotos(java.util.List<ParametresApplication> list){
		this.listOfParametresApplicationsPhoto.clear();
		for (ParametresApplication item : list){
			this.listOfParametresApplicationsPhoto.add(item);
		}
	}

	public static boolean isAutoFetchListOfListOfParametresApplicationsPhoto(){
		return Photo.autoFetchListOfListOfParametresApplicationsPhoto;
	}

	public static void setAutoFetchListOfListOfParametresApplicationsPhoto(boolean autoFetchListOfListOfParametresApplicationsPhoto){
		Photo.autoFetchListOfListOfParametresApplicationsPhoto = autoFetchListOfListOfParametresApplicationsPhoto;
	}

	public java.util.List<ParametresOrganisme> getListOfParametresOrganismesPhotos(){
		return this.listOfParametresOrganismesPhoto;
	}

	public void setListOfParametresOrganismesPhotos(java.util.List<ParametresOrganisme> list){
		this.listOfParametresOrganismesPhoto.clear();
		for (ParametresOrganisme item : list){
			this.listOfParametresOrganismesPhoto.add(item);
		}
	}

	public static boolean isAutoFetchListOfListOfParametresOrganismesPhoto(){
		return Photo.autoFetchListOfListOfParametresOrganismesPhoto;
	}

	public static void setAutoFetchListOfListOfParametresOrganismesPhoto(boolean autoFetchListOfListOfParametresOrganismesPhoto){
		Photo.autoFetchListOfListOfParametresOrganismesPhoto = autoFetchListOfListOfParametresOrganismesPhoto;
	}

	public java.util.List<Role> getListOfRolesPhotos(){
		return this.listOfRolesPhoto;
	}

	public void setListOfRolesPhotos(java.util.List<Role> list){
		this.listOfRolesPhoto.clear();
		for (Role item : list){
			this.listOfRolesPhoto.add(item);
		}
	}

	public static boolean isAutoFetchListOfListOfRolesPhoto(){
		return Photo.autoFetchListOfListOfRolesPhoto;
	}

	public static void setAutoFetchListOfListOfRolesPhoto(boolean autoFetchListOfListOfRolesPhoto){
		Photo.autoFetchListOfListOfRolesPhoto = autoFetchListOfListOfRolesPhoto;
	}

	public java.util.List<Utilisateur> getListOfUtilisateursPhotos(){
		return this.listOfUtilisateursPhoto;
	}

	public void setListOfUtilisateursPhotos(java.util.List<Utilisateur> list){
		this.listOfUtilisateursPhoto.clear();
		for (Utilisateur item : list){
			this.listOfUtilisateursPhoto.add(item);
		}
	}

	public static boolean isAutoFetchListOfListOfUtilisateursPhoto(){
		return Photo.autoFetchListOfListOfUtilisateursPhoto;
	}

	public static void setAutoFetchListOfListOfUtilisateursPhoto(boolean autoFetchListOfListOfUtilisateursPhoto){
		Photo.autoFetchListOfListOfUtilisateursPhoto = autoFetchListOfListOfUtilisateursPhoto;
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