package models.beans;

import java.io.Serializable;
/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This classe is a Bean. 
 */

public class Translation implements Serializable {
	private static final long serialVersionUID = 1L;

	private java.lang.Integer id = 0;
	private java.lang.Integer idLang = 0;
	private java.lang.Integer idMessage = 0;
	private java.lang.String traduction = "";

	private Lang lang = null;

	private LangMessage message = null;

	public Translation() {
	}

	public Translation(java.lang.Integer id, java.lang.Integer idLang, java.lang.Integer idMessage, java.lang.String traduction) {
		this.setId(id);
		this.setIdLang(idLang);
		this.setIdMessage(idMessage);
		this.setTraduction(traduction);
	}

	public Translation(String xmlString) {
		org.jdom.Element element = utils.StringUtils.xmlStringToXmlElement(xmlString);
		fillByXMLElement(element);
	}

	public Translation(org.jdom.Element element) {
		fillByXMLElement(element);
	}

	public void fillByXMLElement(org.jdom.Element element) {
		this.setId(Integer.parseInt(element.getAttributeValue("id")));
		this.setIdLang(Integer.parseInt(element.getAttributeValue("idLang")));
		this.setIdMessage(Integer.parseInt(element.getAttributeValue("idMessage")));
		this.setTraduction(element.getAttributeValue("traduction"));
	}

	public void fillByMap(java.util.Map<String, String> data) {
		this.setId(Integer.parseInt(data.get("id")));
		this.setIdLang(Integer.parseInt(data.get("idLang")));
		this.setIdMessage(Integer.parseInt(data.get("idMessage")));
		this.setTraduction(data.get("traduction"));
	}

	public void fillByArray(String[] array) {
		this.setId(Integer.parseInt(array[0]));
		this.setIdLang(Integer.parseInt(array[1]));
		this.setIdMessage(Integer.parseInt(array[2]));
		this.setTraduction(array[3]);
	}

	public boolean equals(Object obj) {
		if (obj == null){
			return false;
		}
		if (!(obj instanceof Translation)){
			return false;
		}
		Translation item = (Translation)obj;
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

	public java.lang.Integer getIdMessage(){
		return this.idMessage;
	}

	public void setIdMessage(java.lang.Integer idMessage){
		this.idMessage = idMessage;
	}

	public LangMessage getMessage(){
		return this.message;
	}

	public void setMessage(LangMessage message){
		this.message = message;
		int idMessage = 0;
		if (message != null){
			idMessage = message.getId().intValue();
		}
		this.idMessage = idMessage;
	}

	public java.lang.String getTraduction(){
		return this.traduction;
	}

	public void setTraduction(java.lang.String traduction){
		this.traduction = traduction;
	}

	public String toXMLString(){
		String xml = "<Translation ";
		xml+= "id='"+this.getId()+"' ";
		xml+= "idLang='"+this.getIdLang()+"' ";
		xml+= "idMessage='"+this.getIdMessage()+"' ";
		xml+= "traduction='"+this.getTraduction()+"' ";
		xml+= ">";
		xml+= "\n	"+lang.toXMLString().replace("<Lang", "<Lang").replace("</Lang", "</Lang");
		xml+= "\n	"+message.toXMLString().replace("<LangMessage", "<Message").replace("</LangMessage", "</Message");
		xml+= "\n</Translation>";
		return xml;
	}

	public org.jdom.Element toXMLElement(){
		org.jdom.Element element = new org.jdom.Element("Translation");
		element.setAttribute("id", ""+this.getId());
		element.setAttribute("idLang", ""+this.getIdLang());
		element.setAttribute("idMessage", ""+this.getIdMessage());
		element.setAttribute("traduction", ""+this.getTraduction());

		element.addContent(lang.toXMLElement());
		element.addContent(message.toXMLElement());

		return element;
	}

	public static Translation parseXMLElement(org.jdom.Element element){
		Translation translation = new Translation();
		translation.setId(Integer.parseInt(element.getAttributeValue("id")));
		translation.setIdLang(Integer.parseInt(element.getAttributeValue("idLang")));
		translation.setIdMessage(Integer.parseInt(element.getAttributeValue("idMessage")));
		translation.setTraduction(element.getAttributeValue("traduction"));

		translation.setLang(Lang.parseXMLElement(element.getChild("Lang")));
		translation.setMessage(LangMessage.parseXMLElement(element.getChild("Message")));

		return translation;
	}

	public static Translation parseXMLString(String xmlString){
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
	public static java.util.List<Translation> parseXMLFile (java.io.File xmlFile){
		java.util.List<Translation> listTranslations = new java.util.ArrayList<Translation>();
		try{
			org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder();
			org.jdom.Document document = builder.build(xmlFile);
			org.jdom.Element rootNode = document.getRootElement();
			if (!rootNode.getName().equals("Translations"))
				return null;
			java.util.List<?> listChildren = rootNode.getChildren("Translation");
			for (Object child : listChildren){
				if (! (child instanceof org.jdom.Element))
					continue;
				org.jdom.Element elementChild = (org.jdom.Element)child;
				if (! elementChild.getName().equals("Translation"))
					continue;
				listTranslations.add(parseXMLElement(elementChild));
			}
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
			return null;
		}
		return listTranslations;
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