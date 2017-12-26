package models.beans;

import java.io.Serializable;
/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This classe is a Bean. 
 */

public class ParametresApplicationUtilisateur implements Serializable {
	private static final long serialVersionUID = 1L;

	private java.lang.Integer id = 0;
	private java.lang.Integer periodeNotification = 0;
	private java.lang.Boolean visibilityOfNotification = Boolean.valueOf("false");
	private java.lang.Boolean visibilityOfMainToolBar = Boolean.valueOf("false");
	private java.lang.Integer idLang = 0;
	private java.lang.String lookAndFeel = "";

	private Lang lang = null;
	
	private static boolean autoFetchListOfListOfUtilisateursParametres = false;
	private java.util.List<Utilisateur> listOfUtilisateursParametres = new java.util.ArrayList<Utilisateur>();

	public ParametresApplicationUtilisateur() {
	}

	public ParametresApplicationUtilisateur(java.lang.Integer id, java.lang.Integer periodeNotification, java.lang.Boolean visibilityOfNotification, java.lang.Boolean visibilityOfMainToolBar, java.lang.Integer idLang, java.lang.String lookAndFeel) {
		this.setId(id);
		this.setPeriodeNotification(periodeNotification);
		this.setVisibilityOfNotification(visibilityOfNotification);
		this.setVisibilityOfMainToolBar(visibilityOfMainToolBar);
		this.setIdLang(idLang);
		this.setLookAndFeel(lookAndFeel);
	}

	public ParametresApplicationUtilisateur(String xmlString) {
		org.jdom.Element element = utils.StringUtils.xmlStringToXmlElement(xmlString);
		fillByXMLElement(element);
	}

	public ParametresApplicationUtilisateur(org.jdom.Element element) {
		fillByXMLElement(element);
	}

	public void fillByXMLElement(org.jdom.Element element) {
		this.setId(Integer.parseInt(element.getAttributeValue("id")));
		this.setPeriodeNotification(Integer.parseInt(element.getAttributeValue("periodeNotification")));
		this.setVisibilityOfNotification(Boolean.parseBoolean(element.getAttributeValue("visibilityOfNotification")));
		this.setVisibilityOfMainToolBar(Boolean.parseBoolean(element.getAttributeValue("visibilityOfMainToolBar")));
		this.setIdLang(Integer.parseInt(element.getAttributeValue("idLang")));
		this.setLookAndFeel(element.getAttributeValue("lookAndFeel"));
	}

	public void fillByMap(java.util.Map<String, String> data) {
		this.setId(Integer.parseInt(data.get("id")));
		this.setPeriodeNotification(Integer.parseInt(data.get("periodeNotification")));
		this.setVisibilityOfNotification(Boolean.parseBoolean(data.get("visibilityOfNotification")));
		this.setVisibilityOfMainToolBar(Boolean.parseBoolean(data.get("visibilityOfMainToolBar")));
		this.setIdLang(Integer.parseInt(data.get("idLang")));
		this.setLookAndFeel(data.get("lookAndFeel"));
	}

	public void fillByArray(String[] array) {
		this.setId(Integer.parseInt(array[0]));
		this.setPeriodeNotification(Integer.parseInt(array[1]));
		this.setVisibilityOfNotification(Boolean.parseBoolean(array[2]));
		this.setVisibilityOfMainToolBar(Boolean.parseBoolean(array[3]));
		this.setIdLang(Integer.parseInt(array[4]));
		this.setLookAndFeel(array[5]);
	}

	public boolean equals(Object obj) {
		if (obj == null){
			return false;
		}
		if (!(obj instanceof ParametresApplicationUtilisateur)){
			return false;
		}
		ParametresApplicationUtilisateur item = (ParametresApplicationUtilisateur)obj;
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

	public java.lang.Integer getPeriodeNotification(){
		return this.periodeNotification;
	}

	public void setPeriodeNotification(java.lang.Integer periodeNotification){
		this.periodeNotification = periodeNotification;
	}

	public java.lang.Boolean isVisibilityOfNotification(){
		return this.visibilityOfNotification;
	}

	public void setVisibilityOfNotification(java.lang.Boolean visibilityOfNotification){
		this.visibilityOfNotification = visibilityOfNotification;
	}

	public java.lang.Boolean isVisibilityOfMainToolBar(){
		return this.visibilityOfMainToolBar;
	}

	public void setVisibilityOfMainToolBar(java.lang.Boolean visibilityOfMainToolBar){
		this.visibilityOfMainToolBar = visibilityOfMainToolBar;
	}

	public java.lang.Integer getIdLang(){
		return this.idLang;
	}

	public void setIdLang(java.lang.Integer idLang){
		this.idLang = idLang;
	}

	public Lang getLang(){
		return this.lang;
	}

	public void setLang(Lang lang){
		this.lang = lang;
		int idLang = 0;
		if (lang != null){
			idLang = lang.getId().intValue();
		}
		this.idLang = idLang;
	}

	public java.lang.String getLookAndFeel(){
		return this.lookAndFeel;
	}

	public void setLookAndFeel(java.lang.String lookAndFeel){
		this.lookAndFeel = lookAndFeel;
	}

	public String toXMLString(){
		String xml = "<ParametresApplicationUtilisateur ";
		xml+= "id='"+this.getId()+"' ";
		xml+= "periodeNotification='"+this.getPeriodeNotification()+"' ";
		xml+= "visibilityOfNotification='"+this.isVisibilityOfNotification()+"' ";
		xml+= "visibilityOfMainToolBar='"+this.isVisibilityOfMainToolBar()+"' ";
		xml+= "idLang='"+this.getIdLang()+"' ";
		xml+= "lookAndFeel='"+this.getLookAndFeel()+"' ";
		xml+= ">";
		xml+= "\n	"+lang.toXMLString().replace("<Lang", "<Lang").replace("</Lang", "</Lang");
		xml+= "\n</ParametresApplicationUtilisateur>";
		return xml;
	}

	public org.jdom.Element toXMLElement(){
		org.jdom.Element element = new org.jdom.Element("ParametresApplicationUtilisateur");
		element.setAttribute("id", ""+this.getId());
		element.setAttribute("periodeNotification", ""+this.getPeriodeNotification());
		element.setAttribute("visibilityOfNotification", ""+this.isVisibilityOfNotification());
		element.setAttribute("visibilityOfMainToolBar", ""+this.isVisibilityOfMainToolBar());
		element.setAttribute("idLang", ""+this.getIdLang());
		element.setAttribute("lookAndFeel", ""+this.getLookAndFeel());

		element.addContent(lang.toXMLElement());

		return element;
	}

	public static ParametresApplicationUtilisateur parseXMLElement(org.jdom.Element element){
		ParametresApplicationUtilisateur parametresApplicationUtilisateur = new ParametresApplicationUtilisateur();
		parametresApplicationUtilisateur.setId(Integer.parseInt(element.getAttributeValue("id")));
		parametresApplicationUtilisateur.setPeriodeNotification(Integer.parseInt(element.getAttributeValue("periodeNotification")));
		parametresApplicationUtilisateur.setVisibilityOfNotification(Boolean.parseBoolean(element.getAttributeValue("visibilityOfNotification")));
		parametresApplicationUtilisateur.setVisibilityOfMainToolBar(Boolean.parseBoolean(element.getAttributeValue("visibilityOfMainToolBar")));
		parametresApplicationUtilisateur.setIdLang(Integer.parseInt(element.getAttributeValue("idLang")));
		parametresApplicationUtilisateur.setLookAndFeel(element.getAttributeValue("lookAndFeel"));

		parametresApplicationUtilisateur.setLang(Lang.parseXMLElement(element.getChild("Lang")));

		return parametresApplicationUtilisateur;
	}

	public static ParametresApplicationUtilisateur parseXMLString(String xmlString){
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
	public static java.util.List<ParametresApplicationUtilisateur> parseXMLFile (java.io.File xmlFile){
		java.util.List<ParametresApplicationUtilisateur> listParametresApplicationUtilisateurs = new java.util.ArrayList<ParametresApplicationUtilisateur>();
		try{
			org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder();
			org.jdom.Document document = builder.build(xmlFile);
			org.jdom.Element rootNode = document.getRootElement();
			if (!rootNode.getName().equals("ParametresApplicationUtilisateurs"))
				return null;
			java.util.List<?> listChildren = rootNode.getChildren("ParametresApplicationUtilisateur");
			for (Object child : listChildren){
				if (! (child instanceof org.jdom.Element))
					continue;
				org.jdom.Element elementChild = (org.jdom.Element)child;
				if (! elementChild.getName().equals("ParametresApplicationUtilisateur"))
					continue;
				listParametresApplicationUtilisateurs.add(parseXMLElement(elementChild));
			}
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
			return null;
		}
		return listParametresApplicationUtilisateurs;
	}

	public java.util.List<Utilisateur> getListOfUtilisateursParametress(){
		return this.listOfUtilisateursParametres;
	}

	public void setListOfUtilisateursParametress(java.util.List<Utilisateur> list){
		this.listOfUtilisateursParametres.clear();
		for (Utilisateur item : list){
			this.listOfUtilisateursParametres.add(item);
		}
	}

	public static boolean isAutoFetchListOfListOfUtilisateursParametres(){
		return ParametresApplicationUtilisateur.autoFetchListOfListOfUtilisateursParametres;
	}

	public static void setAutoFetchListOfListOfUtilisateursParametres(boolean autoFetchListOfListOfUtilisateursParametres){
		ParametresApplicationUtilisateur.autoFetchListOfListOfUtilisateursParametres = autoFetchListOfListOfUtilisateursParametres;
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