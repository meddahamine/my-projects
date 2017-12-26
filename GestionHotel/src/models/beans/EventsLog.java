package models.beans;

import java.io.Serializable;
/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This classe is a Bean. 
 */

@SuppressWarnings("deprecation")
public class EventsLog implements Serializable {
	private static final long serialVersionUID = 1L;

	private java.lang.Integer id = 0;
	private java.lang.String evenement = "";
	private java.lang.Integer idConnexionsLog = 0;
	private java.sql.Timestamp date = new java.sql.Timestamp(1, 0, 1, 0, 0, 0, 0);

	private ConnexionsLog connexionsLog = null;

	public EventsLog() {
	}

	public EventsLog(java.lang.Integer id, java.lang.String evenement, java.lang.Integer idConnexionsLog, java.sql.Timestamp date) {
		this.setId(id);
		this.setEvenement(evenement);
		this.setIdConnexionsLog(idConnexionsLog);
		this.setDate(date);
	}

	public EventsLog(String xmlString) {
		org.jdom.Element element = utils.StringUtils.xmlStringToXmlElement(xmlString);
		fillByXMLElement(element);
	}

	public EventsLog(org.jdom.Element element) {
		fillByXMLElement(element);
	}

	public void fillByXMLElement(org.jdom.Element element) {
		this.setId(Integer.parseInt(element.getAttributeValue("id")));
		this.setEvenement(element.getAttributeValue("evenement"));
		this.setIdConnexionsLog(Integer.parseInt(element.getAttributeValue("idConnexionsLog")));
		this.setDate(utils.StringUtils.getTimestampFromString(element.getAttributeValue("date")));
	}

	public void fillByMap(java.util.Map<String, String> data) {
		this.setId(Integer.parseInt(data.get("id")));
		this.setEvenement(data.get("evenement"));
		this.setIdConnexionsLog(Integer.parseInt(data.get("idConnexionsLog")));
		this.setDate(utils.StringUtils.getTimestampFromString(data.get("date")));
	}

	public void fillByArray(String[] array) {
		this.setId(Integer.parseInt(array[0]));
		this.setEvenement(array[1]);
		this.setIdConnexionsLog(Integer.parseInt(array[2]));
		this.setDate(utils.StringUtils.getTimestampFromString(array[3]));
	}

	public boolean equals(Object obj) {
		if (obj == null){
			return false;
		}
		if (!(obj instanceof EventsLog)){
			return false;
		}
		EventsLog item = (EventsLog)obj;
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

	public java.lang.String getEvenement(){
		return this.evenement;
	}

	public void setEvenement(java.lang.String evenement){
		this.evenement = evenement;
	}

	public java.lang.Integer getIdConnexionsLog(){
		return this.idConnexionsLog;
	}

	public void setIdConnexionsLog(java.lang.Integer idConnexionsLog){
		this.idConnexionsLog = idConnexionsLog;
	}

	public ConnexionsLog getConnexionsLog(){
		return this.connexionsLog;
	}

	public void setConnexionsLog(ConnexionsLog connexionsLog){
		this.connexionsLog = connexionsLog;
		int idConnexionsLog = 0;
		if (connexionsLog != null){
			idConnexionsLog = connexionsLog.getId().intValue();
		}
		this.idConnexionsLog = idConnexionsLog;
	}

	public java.sql.Timestamp getDate(){
		return this.date;
	}

	public void setDate(java.sql.Timestamp date){
		this.date = date;
	}

	public String toXMLString(){
		String xml = "<EventsLog ";
		xml+= "id='"+this.getId()+"' ";
		xml+= "evenement='"+this.getEvenement()+"' ";
		xml+= "idConnexionsLog='"+this.getIdConnexionsLog()+"' ";
		xml+= "date='"+this.getDate()+"' ";
		xml+= ">";
		xml+= "\n	"+connexionsLog.toXMLString().replace("<ConnexionsLog", "<ConnexionsLog").replace("</ConnexionsLog", "</ConnexionsLog");
		xml+= "\n</EventsLog>";
		return xml;
	}

	public org.jdom.Element toXMLElement(){
		org.jdom.Element element = new org.jdom.Element("EventsLog");
		element.setAttribute("id", ""+this.getId());
		element.setAttribute("evenement", ""+this.getEvenement());
		element.setAttribute("idConnexionsLog", ""+this.getIdConnexionsLog());
		element.setAttribute("date", ""+this.getDate());

		element.addContent(connexionsLog.toXMLElement());

		return element;
	}

	public static EventsLog parseXMLElement(org.jdom.Element element){
		EventsLog eventsLog = new EventsLog();
		eventsLog.setId(Integer.parseInt(element.getAttributeValue("id")));
		eventsLog.setEvenement(element.getAttributeValue("evenement"));
		eventsLog.setIdConnexionsLog(Integer.parseInt(element.getAttributeValue("idConnexionsLog")));
		eventsLog.setDate(utils.StringUtils.getTimestampFromString(element.getAttributeValue("date")));

		eventsLog.setConnexionsLog(ConnexionsLog.parseXMLElement(element.getChild("ConnexionsLog")));

		return eventsLog;
	}

	public static EventsLog parseXMLString(String xmlString){
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
	public static java.util.List<EventsLog> parseXMLFile (java.io.File xmlFile){
		java.util.List<EventsLog> listEventsLogs = new java.util.ArrayList<EventsLog>();
		try{
			org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder();
			org.jdom.Document document = builder.build(xmlFile);
			org.jdom.Element rootNode = document.getRootElement();
			if (!rootNode.getName().equals("EventsLogs"))
				return null;
			java.util.List<?> listChildren = rootNode.getChildren("EventsLog");
			for (Object child : listChildren){
				if (! (child instanceof org.jdom.Element))
					continue;
				org.jdom.Element elementChild = (org.jdom.Element)child;
				if (! elementChild.getName().equals("EventsLog"))
					continue;
				listEventsLogs.add(parseXMLElement(elementChild));
			}
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
			return null;
		}
		return listEventsLogs;
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