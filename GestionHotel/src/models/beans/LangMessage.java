package models.beans;

import java.io.Serializable;
/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This classe is a Bean. 
 */

public class LangMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	private java.lang.Integer id = 0;
	private java.lang.String message = "";
	
	private static boolean autoFetchListOfListOfTranslationsMessage = false;
	private java.util.List<Translation> listOfTranslationsMessage = new java.util.ArrayList<Translation>();

	public LangMessage() {
	}

	public LangMessage(java.lang.Integer id, java.lang.String message) {
		this.setId(id);
		this.setMessage(message);
	}

	public LangMessage(String xmlString) {
		org.jdom.Element element = utils.StringUtils.xmlStringToXmlElement(xmlString);
		fillByXMLElement(element);
	}

	public LangMessage(org.jdom.Element element) {
		fillByXMLElement(element);
	}

	public void fillByXMLElement(org.jdom.Element element) {
		this.setId(Integer.parseInt(element.getAttributeValue("id")));
		this.setMessage(element.getAttributeValue("message"));
	}

	public void fillByMap(java.util.Map<String, String> data) {
		this.setId(Integer.parseInt(data.get("id")));
		this.setMessage(data.get("message"));
	}

	public void fillByArray(String[] array) {
		this.setId(Integer.parseInt(array[0]));
		this.setMessage(array[1]);
	}

	public boolean equals(Object obj) {
		if (obj == null){
			return false;
		}
		if (!(obj instanceof LangMessage)){
			return false;
		}
		LangMessage item = (LangMessage)obj;
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

	public java.lang.String getMessage(){
		return this.message;
	}

	public void setMessage(java.lang.String message){
		this.message = message;
	}

	public String toXMLString(){
		String xml = "<LangMessage ";
		xml+= "id='"+this.getId()+"' ";
		xml+= "message='"+this.getMessage()+"' ";
		xml+= ">";
		xml+= "\n</LangMessage>";
		return xml;
	}

	public org.jdom.Element toXMLElement(){
		org.jdom.Element element = new org.jdom.Element("LangMessage");
		element.setAttribute("id", ""+this.getId());
		element.setAttribute("message", ""+this.getMessage());


		return element;
	}

	public static LangMessage parseXMLElement(org.jdom.Element element){
		LangMessage langMessage = new LangMessage();
		langMessage.setId(Integer.parseInt(element.getAttributeValue("id")));
		langMessage.setMessage(element.getAttributeValue("message"));


		return langMessage;
	}

	public static LangMessage parseXMLString(String xmlString){
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
	public static java.util.List<LangMessage> parseXMLFile (java.io.File xmlFile){
		java.util.List<LangMessage> listLangMessages = new java.util.ArrayList<LangMessage>();
		try{
			org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder();
			org.jdom.Document document = builder.build(xmlFile);
			org.jdom.Element rootNode = document.getRootElement();
			if (!rootNode.getName().equals("LangMessages"))
				return null;
			java.util.List<?> listChildren = rootNode.getChildren("LangMessage");
			for (Object child : listChildren){
				if (! (child instanceof org.jdom.Element))
					continue;
				org.jdom.Element elementChild = (org.jdom.Element)child;
				if (! elementChild.getName().equals("LangMessage"))
					continue;
				listLangMessages.add(parseXMLElement(elementChild));
			}
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
			return null;
		}
		return listLangMessages;
	}

	public java.util.List<Translation> getListOfTranslationsMessages(){
		return this.listOfTranslationsMessage;
	}

	public void setListOfTranslationsMessages(java.util.List<Translation> list){
		this.listOfTranslationsMessage.clear();
		for (Translation item : list){
			this.listOfTranslationsMessage.add(item);
		}
	}

	public static boolean isAutoFetchListOfListOfTranslationsMessage(){
		return LangMessage.autoFetchListOfListOfTranslationsMessage;
	}

	public static void setAutoFetchListOfListOfTranslationsMessage(boolean autoFetchListOfListOfTranslationsMessage){
		LangMessage.autoFetchListOfListOfTranslationsMessage = autoFetchListOfListOfTranslationsMessage;
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