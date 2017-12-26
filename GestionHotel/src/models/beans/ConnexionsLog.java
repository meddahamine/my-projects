package models.beans;

import java.io.Serializable;
/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This classe is a Bean. 
 */

@SuppressWarnings("deprecation")
public class ConnexionsLog implements Serializable {
	private static final long serialVersionUID = 1L;

	public static enum ConnexionAccepted {
		oui("oui"), non("non");

		private utils.Item item;

		private ConnexionAccepted(String value) {
			this.item = new utils.Item(this.name(), value);
		}

		public String toString() {
			return this.name()+"("+this.getValue()+")";
		}

		public String getValue() {
			return this.item.getValue();
		}

		public static ConnexionAccepted getByValue(String value){
			for (ConnexionAccepted c : ConnexionAccepted.values()){
				if (c.getValue().equals(value))
					return c;
			}
			return null;
		}
	};

	private java.lang.Integer id = 0;
	private java.lang.String login = "";
	private java.lang.String uuid = "";
	private java.sql.Timestamp dateDeConnexion = new java.sql.Timestamp(1, 0, 1, 0, 0, 0, 0);
	private java.sql.Timestamp dateDeDeconnexion = new java.sql.Timestamp(1, 0, 1, 0, 0, 0, 0);
	private java.lang.String ip = "";
	private java.lang.String mac = "";
	private java.lang.String machine = "";
	private ConnexionAccepted connexionAccepted = null;
	private java.lang.String motifError = "";
	private java.lang.Integer idUtilisateur = 0;

	private Utilisateur utilisateur = null;
	
	private static boolean autoFetchListOfListOfEventsLogsConnexionsLog = false;
	private java.util.List<EventsLog> listOfEventsLogsConnexionsLog = new java.util.ArrayList<EventsLog>();

	public ConnexionsLog() {
	}

	public ConnexionsLog(java.lang.Integer id, java.lang.String login, java.lang.String uuid, java.sql.Timestamp dateDeConnexion, java.sql.Timestamp dateDeDeconnexion, java.lang.String ip, java.lang.String mac, java.lang.String machine, ConnexionAccepted connexionAccepted, java.lang.String motifError, java.lang.Integer idUtilisateur) {
		this.setId(id);
		this.setLogin(login);
		this.setUuid(uuid);
		this.setDateDeConnexion(dateDeConnexion);
		this.setDateDeDeconnexion(dateDeDeconnexion);
		this.setIp(ip);
		this.setMac(mac);
		this.setMachine(machine);
		this.setConnexionAccepted(connexionAccepted);
		this.setMotifError(motifError);
		this.setIdUtilisateur(idUtilisateur);
	}

	public ConnexionsLog(String xmlString) {
		org.jdom.Element element = utils.StringUtils.xmlStringToXmlElement(xmlString);
		fillByXMLElement(element);
	}

	public ConnexionsLog(org.jdom.Element element) {
		fillByXMLElement(element);
	}

	public void fillByXMLElement(org.jdom.Element element) {
		this.setId(Integer.parseInt(element.getAttributeValue("id")));
		this.setLogin(element.getAttributeValue("login"));
		this.setUuid(element.getAttributeValue("uuid"));
		this.setDateDeConnexion(utils.StringUtils.getTimestampFromString(element.getAttributeValue("dateDeConnexion")));
		this.setDateDeDeconnexion(utils.StringUtils.getTimestampFromString(element.getAttributeValue("dateDeDeconnexion")));
		this.setIp(element.getAttributeValue("ip"));
		this.setMac(element.getAttributeValue("mac"));
		this.setMachine(element.getAttributeValue("machine"));
		this.setConnexionAccepted(ConnexionAccepted.getByValue(element.getAttributeValue("connexionAccepted")));
		this.setMotifError(element.getAttributeValue("motifError"));
		this.setIdUtilisateur(Integer.parseInt(element.getAttributeValue("idUtilisateur")));
	}

	public void fillByMap(java.util.Map<String, String> data) {
		this.setId(Integer.parseInt(data.get("id")));
		this.setLogin(data.get("login"));
		this.setUuid(data.get("uuid"));
		this.setDateDeConnexion(utils.StringUtils.getTimestampFromString(data.get("dateDeConnexion")));
		this.setDateDeDeconnexion(utils.StringUtils.getTimestampFromString(data.get("dateDeDeconnexion")));
		this.setIp(data.get("ip"));
		this.setMac(data.get("mac"));
		this.setMachine(data.get("machine"));
		this.setConnexionAccepted(ConnexionAccepted.getByValue(data.get("connexionAccepted")));
		this.setMotifError(data.get("motifError"));
		this.setIdUtilisateur(Integer.parseInt(data.get("idUtilisateur")));
	}

	public void fillByArray(String[] array) {
		this.setId(Integer.parseInt(array[0]));
		this.setLogin(array[1]);
		this.setUuid(array[2]);
		this.setDateDeConnexion(utils.StringUtils.getTimestampFromString(array[3]));
		this.setDateDeDeconnexion(utils.StringUtils.getTimestampFromString(array[4]));
		this.setIp(array[5]);
		this.setMac(array[6]);
		this.setMachine(array[7]);
		this.setConnexionAccepted(ConnexionAccepted.getByValue(array[8]));
		this.setMotifError(array[9]);
		this.setIdUtilisateur(Integer.parseInt(array[10]));
	}

	public boolean equals(Object obj) {
		if (obj == null){
			return false;
		}
		if (!(obj instanceof ConnexionsLog)){
			return false;
		}
		ConnexionsLog item = (ConnexionsLog)obj;
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

	public java.lang.String getLogin(){
		return this.login;
	}

	public void setLogin(java.lang.String login){
		this.login = login;
	}

	public java.lang.String getUuid(){
		return this.uuid;
	}

	public void setUuid(java.lang.String uuid){
		this.uuid = uuid;
	}

	public java.sql.Timestamp getDateDeConnexion(){
		return this.dateDeConnexion;
	}

	public void setDateDeConnexion(java.sql.Timestamp dateDeConnexion){
		this.dateDeConnexion = dateDeConnexion;
	}

	public java.sql.Timestamp getDateDeDeconnexion(){
		return this.dateDeDeconnexion;
	}

	public void setDateDeDeconnexion(java.sql.Timestamp dateDeDeconnexion){
		this.dateDeDeconnexion = dateDeDeconnexion;
	}

	public java.lang.String getIp(){
		return this.ip;
	}

	public void setIp(java.lang.String ip){
		this.ip = ip;
	}

	public java.lang.String getMac(){
		return this.mac;
	}

	public void setMac(java.lang.String mac){
		this.mac = mac;
	}

	public java.lang.String getMachine(){
		return this.machine;
	}

	public void setMachine(java.lang.String machine){
		this.machine = machine;
	}

	public ConnexionAccepted getConnexionAccepted(){
		return this.connexionAccepted;
	}

	public void setConnexionAccepted(ConnexionAccepted connexionAccepted){
		this.connexionAccepted = connexionAccepted;
	}

	public java.lang.String getMotifError(){
		return this.motifError;
	}

	public void setMotifError(java.lang.String motifError){
		this.motifError = motifError;
	}

	public java.lang.Integer getIdUtilisateur(){
		return this.idUtilisateur;
	}

	public void setIdUtilisateur(java.lang.Integer idUtilisateur){
		this.idUtilisateur = idUtilisateur;
	}

	public Utilisateur getUtilisateur(){
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur){
		this.utilisateur = utilisateur;
		int idUtilisateur = 0;
		if (utilisateur != null){
			idUtilisateur = utilisateur.getId().intValue();
		}
		this.idUtilisateur = idUtilisateur;
	}

	public String toXMLString(){
		String xml = "<ConnexionsLog ";
		xml+= "id='"+this.getId()+"' ";
		xml+= "login='"+this.getLogin()+"' ";
		xml+= "uuid='"+this.getUuid()+"' ";
		xml+= "dateDeConnexion='"+this.getDateDeConnexion()+"' ";
		xml+= "dateDeDeconnexion='"+this.getDateDeDeconnexion()+"' ";
		xml+= "ip='"+this.getIp()+"' ";
		xml+= "mac='"+this.getMac()+"' ";
		xml+= "machine='"+this.getMachine()+"' ";
		xml+= "connexionAccepted='"+this.getConnexionAccepted().getValue()+"' ";
		xml+= "motifError='"+this.getMotifError()+"' ";
		xml+= "idUtilisateur='"+this.getIdUtilisateur()+"' ";
		xml+= ">";
		xml+= "\n	"+utilisateur.toXMLString().replace("<Utilisateur", "<Utilisateur").replace("</Utilisateur", "</Utilisateur");
		xml+= "\n</ConnexionsLog>";
		return xml;
	}

	public org.jdom.Element toXMLElement(){
		org.jdom.Element element = new org.jdom.Element("ConnexionsLog");
		element.setAttribute("id", ""+this.getId());
		element.setAttribute("login", ""+this.getLogin());
		element.setAttribute("uuid", ""+this.getUuid());
		element.setAttribute("dateDeConnexion", ""+this.getDateDeConnexion());
		element.setAttribute("dateDeDeconnexion", ""+this.getDateDeDeconnexion());
		element.setAttribute("ip", ""+this.getIp());
		element.setAttribute("mac", ""+this.getMac());
		element.setAttribute("machine", ""+this.getMachine());
		element.setAttribute("connexionAccepted", ""+this.getConnexionAccepted().getValue());
		element.setAttribute("motifError", ""+this.getMotifError());
		element.setAttribute("idUtilisateur", ""+this.getIdUtilisateur());

		element.addContent(utilisateur.toXMLElement());

		return element;
	}

	public static ConnexionsLog parseXMLElement(org.jdom.Element element){
		ConnexionsLog connexionsLog = new ConnexionsLog();
		connexionsLog.setId(Integer.parseInt(element.getAttributeValue("id")));
		connexionsLog.setLogin(element.getAttributeValue("login"));
		connexionsLog.setUuid(element.getAttributeValue("uuid"));
		connexionsLog.setDateDeConnexion(utils.StringUtils.getTimestampFromString(element.getAttributeValue("dateDeConnexion")));
		connexionsLog.setDateDeDeconnexion(utils.StringUtils.getTimestampFromString(element.getAttributeValue("dateDeDeconnexion")));
		connexionsLog.setIp(element.getAttributeValue("ip"));
		connexionsLog.setMac(element.getAttributeValue("mac"));
		connexionsLog.setMachine(element.getAttributeValue("machine"));
		connexionsLog.setConnexionAccepted(ConnexionAccepted.getByValue(element.getAttributeValue("connexionAccepted")));
		connexionsLog.setMotifError(element.getAttributeValue("motifError"));
		connexionsLog.setIdUtilisateur(Integer.parseInt(element.getAttributeValue("idUtilisateur")));

		connexionsLog.setUtilisateur(Utilisateur.parseXMLElement(element.getChild("Utilisateur")));

		return connexionsLog;
	}

	public static ConnexionsLog parseXMLString(String xmlString){
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
	public static java.util.List<ConnexionsLog> parseXMLFile (java.io.File xmlFile){
		java.util.List<ConnexionsLog> listConnexionsLogs = new java.util.ArrayList<ConnexionsLog>();
		try{
			org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder();
			org.jdom.Document document = builder.build(xmlFile);
			org.jdom.Element rootNode = document.getRootElement();
			if (!rootNode.getName().equals("ConnexionsLogs"))
				return null;
			java.util.List<?> listChildren = rootNode.getChildren("ConnexionsLog");
			for (Object child : listChildren){
				if (! (child instanceof org.jdom.Element))
					continue;
				org.jdom.Element elementChild = (org.jdom.Element)child;
				if (! elementChild.getName().equals("ConnexionsLog"))
					continue;
				listConnexionsLogs.add(parseXMLElement(elementChild));
			}
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
			return null;
		}
		return listConnexionsLogs;
	}

	public java.util.List<EventsLog> getListOfEventsLogsConnexionsLogs(){
		return this.listOfEventsLogsConnexionsLog;
	}

	public void setListOfEventsLogsConnexionsLogs(java.util.List<EventsLog> list){
		this.listOfEventsLogsConnexionsLog.clear();
		for (EventsLog item : list){
			this.listOfEventsLogsConnexionsLog.add(item);
		}
	}

	public static boolean isAutoFetchListOfListOfEventsLogsConnexionsLog(){
		return ConnexionsLog.autoFetchListOfListOfEventsLogsConnexionsLog;
	}

	public static void setAutoFetchListOfListOfEventsLogsConnexionsLog(boolean autoFetchListOfListOfEventsLogsConnexionsLog){
		ConnexionsLog.autoFetchListOfListOfEventsLogsConnexionsLog = autoFetchListOfListOfEventsLogsConnexionsLog;
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