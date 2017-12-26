package models.beans;

import java.io.Serializable;
/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This classe is a Bean. 
 */

public class CategorieChambre implements Serializable {
	private static final long serialVersionUID = 1L;

	private java.lang.Integer id = 0;
	private java.lang.String libelle = "";
	
	private static boolean autoFetchListOfListOfChambresCategorieChambre = false;
	private java.util.List<Chambre> listOfChambresCategorieChambre = new java.util.ArrayList<Chambre>();

	public CategorieChambre() {
	}

	public CategorieChambre(java.lang.Integer id, java.lang.String libelle) {
		this.setId(id);
		this.setLibelle(libelle);
	}

	public CategorieChambre(String xmlString) {
		org.jdom.Element element = utils.StringUtils.xmlStringToXmlElement(xmlString);
		fillByXMLElement(element);
	}

	public CategorieChambre(org.jdom.Element element) {
		fillByXMLElement(element);
	}

	public void fillByXMLElement(org.jdom.Element element) {
		this.setId(Integer.parseInt(element.getAttributeValue("id")));
		this.setLibelle(element.getAttributeValue("libelle"));
	}

	public void fillByMap(java.util.Map<String, String> data) {
		this.setId(Integer.parseInt(data.get("id")));
		this.setLibelle(data.get("libelle"));
	}

	public void fillByArray(String[] array) {
		this.setId(Integer.parseInt(array[0]));
		this.setLibelle(array[1]);
	}

	public boolean equals(Object obj) {
		if (obj == null){
			return false;
		}
		if (!(obj instanceof CategorieChambre)){
			return false;
		}
		CategorieChambre item = (CategorieChambre)obj;
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

	public java.lang.String getLibelle(){
		return this.libelle;
	}

	public void setLibelle(java.lang.String libelle){
		this.libelle = libelle;
	}

	public String toXMLString(){
		String xml = "<CategorieChambre ";
		xml+= "id='"+this.getId()+"' ";
		xml+= "libelle='"+this.getLibelle()+"' ";
		xml+= ">";
		xml+= "\n</CategorieChambre>";
		return xml;
	}

	public org.jdom.Element toXMLElement(){
		org.jdom.Element element = new org.jdom.Element("CategorieChambre");
		element.setAttribute("id", ""+this.getId());
		element.setAttribute("libelle", ""+this.getLibelle());


		return element;
	}

	public static CategorieChambre parseXMLElement(org.jdom.Element element){
		CategorieChambre categorieChambre = new CategorieChambre();
		categorieChambre.setId(Integer.parseInt(element.getAttributeValue("id")));
		categorieChambre.setLibelle(element.getAttributeValue("libelle"));


		return categorieChambre;
	}

	public static CategorieChambre parseXMLString(String xmlString){
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
	public static java.util.List<CategorieChambre> parseXMLFile (java.io.File xmlFile){
		java.util.List<CategorieChambre> listCategorieChambres = new java.util.ArrayList<CategorieChambre>();
		try{
			org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder();
			org.jdom.Document document = builder.build(xmlFile);
			org.jdom.Element rootNode = document.getRootElement();
			if (!rootNode.getName().equals("CategorieChambres"))
				return null;
			java.util.List<?> listChildren = rootNode.getChildren("CategorieChambre");
			for (Object child : listChildren){
				if (! (child instanceof org.jdom.Element))
					continue;
				org.jdom.Element elementChild = (org.jdom.Element)child;
				if (! elementChild.getName().equals("CategorieChambre"))
					continue;
				listCategorieChambres.add(parseXMLElement(elementChild));
			}
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
			return null;
		}
		return listCategorieChambres;
	}

	public java.util.List<Chambre> getListOfChambresCategorieChambres(){
		return this.listOfChambresCategorieChambre;
	}

	public void setListOfChambresCategorieChambres(java.util.List<Chambre> list){
		this.listOfChambresCategorieChambre.clear();
		for (Chambre item : list){
			this.listOfChambresCategorieChambre.add(item);
		}
	}

	public static boolean isAutoFetchListOfListOfChambresCategorieChambre(){
		return CategorieChambre.autoFetchListOfListOfChambresCategorieChambre;
	}

	public static void setAutoFetchListOfListOfChambresCategorieChambre(boolean autoFetchListOfListOfChambresCategorieChambre){
		CategorieChambre.autoFetchListOfListOfChambresCategorieChambre = autoFetchListOfListOfChambresCategorieChambre;
	}

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

	public String toString(){
		String html = "<html>";
		html+= ""+ this.getLibelle();
		html+= "</html>";
		return html;
	}
}