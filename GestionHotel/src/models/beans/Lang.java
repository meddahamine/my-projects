package models.beans;

import java.io.Serializable;
/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This classe is a Bean. 
 */

public class Lang implements Serializable {
	private static final long serialVersionUID = 1L;

	public static enum Orientation {
		RTL("RTL"), LTR("LTR");

		private utils.Item item;

		private Orientation(String value) {
			this.item = new utils.Item(this.name(), value);
		}

		public String toString() {
			return this.name()+"("+this.getValue()+")";
		}

		public String getValue() {
			return this.item.getValue();
		}

		public static Orientation getByValue(String value){
			for (Orientation c : Orientation.values()){
				if (c.getValue().equals(value))
					return c;
			}
			return null;
		}
	};

	private java.lang.Integer id = 0;
	private java.lang.String langue = "";
	private java.lang.String codeLang = "";
	private Orientation orientation = null;
	
	private static boolean autoFetchListOfListOfParametresApplicationUtilisateursLang = false;
	private java.util.List<ParametresApplicationUtilisateur> listOfParametresApplicationUtilisateursLang = new java.util.ArrayList<ParametresApplicationUtilisateur>();
	
	private static boolean autoFetchListOfListOfTranslationsLang = false;
	private java.util.List<Translation> listOfTranslationsLang = new java.util.ArrayList<Translation>();

	public Lang() {
	}

	public Lang(java.lang.Integer id, java.lang.String langue, java.lang.String codeLang, Orientation orientation) {
		this.setId(id);
		this.setLangue(langue);
		this.setCodeLang(codeLang);
		this.setOrientation(orientation);
	}

	public Lang(String xmlString) {
		org.jdom.Element element = utils.StringUtils.xmlStringToXmlElement(xmlString);
		fillByXMLElement(element);
	}

	public Lang(org.jdom.Element element) {
		fillByXMLElement(element);
	}

	public void fillByXMLElement(org.jdom.Element element) {
		this.setId(Integer.parseInt(element.getAttributeValue("id")));
		this.setLangue(element.getAttributeValue("langue"));
		this.setCodeLang(element.getAttributeValue("codeLang"));
		this.setOrientation(Orientation.getByValue(element.getAttributeValue("orientation")));
	}

	public void fillByMap(java.util.Map<String, String> data) {
		this.setId(Integer.parseInt(data.get("id")));
		this.setLangue(data.get("langue"));
		this.setCodeLang(data.get("codeLang"));
		this.setOrientation(Orientation.getByValue(data.get("orientation")));
	}

	public void fillByArray(String[] array) {
		this.setId(Integer.parseInt(array[0]));
		this.setLangue(array[1]);
		this.setCodeLang(array[2]);
		this.setOrientation(Orientation.getByValue(array[3]));
	}

	public boolean equals(Object obj) {
		if (obj == null){
			return false;
		}
		if (!(obj instanceof Lang)){
			return false;
		}
		Lang item = (Lang)obj;
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

	public java.lang.String getLangue(){
		return this.langue;
	}

	public void setLangue(java.lang.String langue){
		this.langue = langue;
	}

	public java.lang.String getCodeLang(){
		return this.codeLang;
	}

	public void setCodeLang(java.lang.String codeLang){
		this.codeLang = codeLang;
	}

	public Orientation getOrientation(){
		return this.orientation;
	}

	public void setOrientation(Orientation orientation){
		this.orientation = orientation;
	}

	public String toXMLString(){
		String xml = "<Lang ";
		xml+= "id='"+this.getId()+"' ";
		xml+= "langue='"+this.getLangue()+"' ";
		xml+= "codeLang='"+this.getCodeLang()+"' ";
		xml+= "orientation='"+this.getOrientation().getValue()+"' ";
		xml+= ">";
		xml+= "\n</Lang>";
		return xml;
	}

	public org.jdom.Element toXMLElement(){
		org.jdom.Element element = new org.jdom.Element("Lang");
		element.setAttribute("id", ""+this.getId());
		element.setAttribute("langue", ""+this.getLangue());
		element.setAttribute("codeLang", ""+this.getCodeLang());
		element.setAttribute("orientation", ""+this.getOrientation().getValue());


		return element;
	}

	public static Lang parseXMLElement(org.jdom.Element element){
		Lang lang = new Lang();
		lang.setId(Integer.parseInt(element.getAttributeValue("id")));
		lang.setLangue(element.getAttributeValue("langue"));
		lang.setCodeLang(element.getAttributeValue("codeLang"));
		lang.setOrientation(Orientation.getByValue(element.getAttributeValue("orientation")));


		return lang;
	}

	public static Lang parseXMLString(String xmlString){
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
	public static java.util.List<Lang> parseXMLFile (java.io.File xmlFile){
		java.util.List<Lang> listLangs = new java.util.ArrayList<Lang>();
		try{
			org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder();
			org.jdom.Document document = builder.build(xmlFile);
			org.jdom.Element rootNode = document.getRootElement();
			if (!rootNode.getName().equals("Langs"))
				return null;
			java.util.List<?> listChildren = rootNode.getChildren("Lang");
			for (Object child : listChildren){
				if (! (child instanceof org.jdom.Element))
					continue;
				org.jdom.Element elementChild = (org.jdom.Element)child;
				if (! elementChild.getName().equals("Lang"))
					continue;
				listLangs.add(parseXMLElement(elementChild));
			}
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
			return null;
		}
		return listLangs;
	}

	public java.util.List<ParametresApplicationUtilisateur> getListOfParametresApplicationUtilisateursLangs(){
		return this.listOfParametresApplicationUtilisateursLang;
	}

	public void setListOfParametresApplicationUtilisateursLangs(java.util.List<ParametresApplicationUtilisateur> list){
		this.listOfParametresApplicationUtilisateursLang.clear();
		for (ParametresApplicationUtilisateur item : list){
			this.listOfParametresApplicationUtilisateursLang.add(item);
		}
	}

	public static boolean isAutoFetchListOfListOfParametresApplicationUtilisateursLang(){
		return Lang.autoFetchListOfListOfParametresApplicationUtilisateursLang;
	}

	public static void setAutoFetchListOfListOfParametresApplicationUtilisateursLang(boolean autoFetchListOfListOfParametresApplicationUtilisateursLang){
		Lang.autoFetchListOfListOfParametresApplicationUtilisateursLang = autoFetchListOfListOfParametresApplicationUtilisateursLang;
	}

	public java.util.List<Translation> getListOfTranslationsLangs(){
		return this.listOfTranslationsLang;
	}

	public void setListOfTranslationsLangs(java.util.List<Translation> list){
		this.listOfTranslationsLang.clear();
		for (Translation item : list){
			this.listOfTranslationsLang.add(item);
		}
	}

	public static boolean isAutoFetchListOfListOfTranslationsLang(){
		return Lang.autoFetchListOfListOfTranslationsLang;
	}

	public static void setAutoFetchListOfListOfTranslationsLang(boolean autoFetchListOfListOfTranslationsLang){
		Lang.autoFetchListOfListOfTranslationsLang = autoFetchListOfListOfTranslationsLang;
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