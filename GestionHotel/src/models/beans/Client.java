package models.beans;

import java.io.Serializable;
/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This classe is a Bean. 
 */

@SuppressWarnings("deprecation")
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	public static enum TypeClient {
		Heberge("Hébergé"), NonHeberge("Non Hébergé");

		private utils.Item item;

		private TypeClient(String value) {
			this.item = new utils.Item(this.name(), value);
		}

		public String toString() {
			return this.name()+"("+this.getValue()+")";
		}

		public String getValue() {
			return this.item.getValue();
		}

		public static TypeClient getByValue(String value){
			for (TypeClient c : TypeClient.values()){
				if (c.getValue().equals(value))
					return c;
			}
			return null;
		}
	};

	public static enum TypeCarte {
		CN("CN"), PC("PC"), Passeport("Passeport");

		private utils.Item item;

		private TypeCarte(String value) {
			this.item = new utils.Item(this.name(), value);
		}

		public String toString() {
			return this.name()+"("+this.getValue()+")";
		}

		public String getValue() {
			return this.item.getValue();
		}

		public static TypeCarte getByValue(String value){
			for (TypeCarte c : TypeCarte.values()){
				if (c.getValue().equals(value))
					return c;
			}
			return null;
		}
	};

	public static enum Civilite {
		Mr("Mr."), Mme("Mme."), Mlle("Mlle.");

		private utils.Item item;

		private Civilite(String value) {
			this.item = new utils.Item(this.name(), value);
		}

		public String toString() {
			return this.name()+"("+this.getValue()+")";
		}

		public String getValue() {
			return this.item.getValue();
		}

		public static Civilite getByValue(String value){
			for (Civilite c : Civilite.values()){
				if (c.getValue().equals(value))
					return c;
			}
			return null;
		}
	};

	private java.lang.Integer id = 0;
	private TypeClient typeClient = null;
	private java.lang.String nom = "";
	private java.lang.String prenom = "";
	private java.sql.Date dateNaiss = new java.sql.Date(1, 0, 1);
	private java.lang.String adresse = "";
	private java.lang.String nationalite = "";
	private java.lang.String telephone = "";
	private TypeCarte typeCarte = null;
	private java.lang.String numCarte = "";
	private java.lang.String profession = "";
	private java.lang.String autreTypeClient = "";
	private java.lang.String autreTypeCarte = "";
	private java.lang.String lieuDeNaiss = "";
	private Civilite civilite = null;
	
	private static boolean autoFetchListOfListOfClientConsommeServicesClient = false;
	private java.util.List<ClientConsommeService> listOfClientConsommeServicesClient = new java.util.ArrayList<ClientConsommeService>();
	
	private static boolean autoFetchListOfListOfClientReseveChambresClient = false;
	private java.util.List<ClientReseveChambre> listOfClientReseveChambresClient = new java.util.ArrayList<ClientReseveChambre>();
	
	private static boolean autoFetchListOfListOfFacturesClient = false;
	private java.util.List<Facture> listOfFacturesClient = new java.util.ArrayList<Facture>();

	public Client() {
	}

	public Client(java.lang.Integer id, TypeClient typeClient, java.lang.String nom, java.lang.String prenom, java.sql.Date dateNaiss, java.lang.String adresse, java.lang.String nationalite, java.lang.String telephone, TypeCarte typeCarte, java.lang.String numCarte, java.lang.String profession, java.lang.String autreTypeClient, java.lang.String autreTypeCarte, java.lang.String lieuDeNaiss, Civilite civilite) {
		this.setId(id);
		this.setTypeClient(typeClient);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setDateNaiss(dateNaiss);
		this.setAdresse(adresse);
		this.setNationalite(nationalite);
		this.setTelephone(telephone);
		this.setTypeCarte(typeCarte);
		this.setNumCarte(numCarte);
		this.setProfession(profession);
		this.setAutreTypeClient(autreTypeClient);
		this.setAutreTypeCarte(autreTypeCarte);
		this.setLieuDeNaiss(lieuDeNaiss);
		this.setCivilite(civilite);
	}

	public Client(String xmlString) {
		org.jdom.Element element = utils.StringUtils.xmlStringToXmlElement(xmlString);
		fillByXMLElement(element);
	}

	public Client(org.jdom.Element element) {
		fillByXMLElement(element);
	}

	public void fillByXMLElement(org.jdom.Element element) {
		this.setId(Integer.parseInt(element.getAttributeValue("id")));
		this.setTypeClient(TypeClient.getByValue(element.getAttributeValue("typeClient")));
		this.setNom(element.getAttributeValue("nom"));
		this.setPrenom(element.getAttributeValue("prenom"));
		this.setDateNaiss(utils.StringUtils.getDateFromString(element.getAttributeValue("dateNaiss")));
		this.setAdresse(element.getAttributeValue("adresse"));
		this.setNationalite(element.getAttributeValue("nationalite"));
		this.setTelephone(element.getAttributeValue("telephone"));
		this.setTypeCarte(TypeCarte.getByValue(element.getAttributeValue("typeCarte")));
		this.setNumCarte(element.getAttributeValue("numCarte"));
		this.setProfession(element.getAttributeValue("profession"));
		this.setAutreTypeClient(element.getAttributeValue("autreTypeClient"));
		this.setAutreTypeCarte(element.getAttributeValue("autreTypeCarte"));
		this.setLieuDeNaiss(element.getAttributeValue("lieuDeNaiss"));
		this.setCivilite(Civilite.getByValue(element.getAttributeValue("civilite")));
	}

	public void fillByMap(java.util.Map<String, String> data) {
		this.setId(Integer.parseInt(data.get("id")));
		this.setTypeClient(TypeClient.getByValue(data.get("typeClient")));
		this.setNom(data.get("nom"));
		this.setPrenom(data.get("prenom"));
		this.setDateNaiss(utils.StringUtils.getDateFromString(data.get("dateNaiss")));
		this.setAdresse(data.get("adresse"));
		this.setNationalite(data.get("nationalite"));
		this.setTelephone(data.get("telephone"));
		this.setTypeCarte(TypeCarte.getByValue(data.get("typeCarte")));
		this.setNumCarte(data.get("numCarte"));
		this.setProfession(data.get("profession"));
		this.setAutreTypeClient(data.get("autreTypeClient"));
		this.setAutreTypeCarte(data.get("autreTypeCarte"));
		this.setLieuDeNaiss(data.get("lieuDeNaiss"));
		this.setCivilite(Civilite.getByValue(data.get("civilite")));
	}

	public void fillByArray(String[] array) {
		this.setId(Integer.parseInt(array[0]));
		this.setTypeClient(TypeClient.getByValue(array[1]));
		this.setNom(array[2]);
		this.setPrenom(array[3]);
		this.setDateNaiss(utils.StringUtils.getDateFromString(array[4]));
		this.setAdresse(array[5]);
		this.setNationalite(array[6]);
		this.setTelephone(array[7]);
		this.setTypeCarte(TypeCarte.getByValue(array[8]));
		this.setNumCarte(array[9]);
		this.setProfession(array[10]);
		this.setAutreTypeClient(array[11]);
		this.setAutreTypeCarte(array[12]);
		this.setLieuDeNaiss(array[13]);
		this.setCivilite(Civilite.getByValue(array[14]));
	}

	public boolean equals(Object obj) {
		if (obj == null){
			return false;
		}
		if (!(obj instanceof Client)){
			return false;
		}
		Client item = (Client)obj;
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

	public TypeClient getTypeClient(){
		return this.typeClient;
	}

	public void setTypeClient(TypeClient typeClient){
		this.typeClient = typeClient;
	}

	public java.lang.String getNom(){
		return this.nom;
	}

	public void setNom(java.lang.String nom){
		this.nom = nom;
	}

	public java.lang.String getPrenom(){
		return this.prenom;
	}

	public void setPrenom(java.lang.String prenom){
		this.prenom = prenom;
	}

	public java.sql.Date getDateNaiss(){
		return this.dateNaiss;
	}

	public void setDateNaiss(java.sql.Date dateNaiss){
		this.dateNaiss = dateNaiss;
	}

	public java.lang.String getAdresse(){
		return this.adresse;
	}

	public void setAdresse(java.lang.String adresse){
		this.adresse = adresse;
	}

	public java.lang.String getNationalite(){
		return this.nationalite;
	}

	public void setNationalite(java.lang.String nationalite){
		this.nationalite = nationalite;
	}

	public java.lang.String getTelephone(){
		return this.telephone;
	}

	public void setTelephone(java.lang.String telephone){
		this.telephone = telephone;
	}

	public TypeCarte getTypeCarte(){
		return this.typeCarte;
	}

	public void setTypeCarte(TypeCarte typeCarte){
		this.typeCarte = typeCarte;
	}

	public java.lang.String getNumCarte(){
		return this.numCarte;
	}

	public void setNumCarte(java.lang.String numCarte){
		this.numCarte = numCarte;
	}

	public java.lang.String getProfession(){
		return this.profession;
	}

	public void setProfession(java.lang.String profession){
		this.profession = profession;
	}

	public java.lang.String getAutreTypeClient(){
		return this.autreTypeClient;
	}

	public void setAutreTypeClient(java.lang.String autreTypeClient){
		this.autreTypeClient = autreTypeClient;
	}

	public java.lang.String getAutreTypeCarte(){
		return this.autreTypeCarte;
	}

	public void setAutreTypeCarte(java.lang.String autreTypeCarte){
		this.autreTypeCarte = autreTypeCarte;
	}

	public java.lang.String getLieuDeNaiss(){
		return this.lieuDeNaiss;
	}

	public void setLieuDeNaiss(java.lang.String lieuDeNaiss){
		this.lieuDeNaiss = lieuDeNaiss;
	}

	public Civilite getCivilite(){
		return this.civilite;
	}

	public void setCivilite(Civilite civilite){
		this.civilite = civilite;
	}

	public String toXMLString(){
		String xml = "<Client ";
		xml+= "id='"+this.getId()+"' ";
		xml+= "typeClient='"+this.getTypeClient().getValue()+"' ";
		xml+= "nom='"+this.getNom()+"' ";
		xml+= "prenom='"+this.getPrenom()+"' ";
		xml+= "dateNaiss='"+this.getDateNaiss()+"' ";
		xml+= "adresse='"+this.getAdresse()+"' ";
		xml+= "nationalite='"+this.getNationalite()+"' ";
		xml+= "telephone='"+this.getTelephone()+"' ";
		xml+= "typeCarte='"+this.getTypeCarte().getValue()+"' ";
		xml+= "numCarte='"+this.getNumCarte()+"' ";
		xml+= "profession='"+this.getProfession()+"' ";
		xml+= "autreTypeClient='"+this.getAutreTypeClient()+"' ";
		xml+= "autreTypeCarte='"+this.getAutreTypeCarte()+"' ";
		xml+= "lieuDeNaiss='"+this.getLieuDeNaiss()+"' ";
		xml+= "civilite='"+this.getCivilite().getValue()+"' ";
		xml+= ">";
		xml+= "\n</Client>";
		return xml;
	}

	public org.jdom.Element toXMLElement(){
		org.jdom.Element element = new org.jdom.Element("Client");
		element.setAttribute("id", ""+this.getId());
		element.setAttribute("typeClient", ""+this.getTypeClient().getValue());
		element.setAttribute("nom", ""+this.getNom());
		element.setAttribute("prenom", ""+this.getPrenom());
		element.setAttribute("dateNaiss", ""+this.getDateNaiss());
		element.setAttribute("adresse", ""+this.getAdresse());
		element.setAttribute("nationalite", ""+this.getNationalite());
		element.setAttribute("telephone", ""+this.getTelephone());
		element.setAttribute("typeCarte", ""+this.getTypeCarte().getValue());
		element.setAttribute("numCarte", ""+this.getNumCarte());
		element.setAttribute("profession", ""+this.getProfession());
		element.setAttribute("autreTypeClient", ""+this.getAutreTypeClient());
		element.setAttribute("autreTypeCarte", ""+this.getAutreTypeCarte());
		element.setAttribute("lieuDeNaiss", ""+this.getLieuDeNaiss());
		element.setAttribute("civilite", ""+this.getCivilite().getValue());


		return element;
	}

	public static Client parseXMLElement(org.jdom.Element element){
		Client client = new Client();
		client.setId(Integer.parseInt(element.getAttributeValue("id")));
		client.setTypeClient(TypeClient.getByValue(element.getAttributeValue("typeClient")));
		client.setNom(element.getAttributeValue("nom"));
		client.setPrenom(element.getAttributeValue("prenom"));
		client.setDateNaiss(utils.StringUtils.getDateFromString(element.getAttributeValue("dateNaiss")));
		client.setAdresse(element.getAttributeValue("adresse"));
		client.setNationalite(element.getAttributeValue("nationalite"));
		client.setTelephone(element.getAttributeValue("telephone"));
		client.setTypeCarte(TypeCarte.getByValue(element.getAttributeValue("typeCarte")));
		client.setNumCarte(element.getAttributeValue("numCarte"));
		client.setProfession(element.getAttributeValue("profession"));
		client.setAutreTypeClient(element.getAttributeValue("autreTypeClient"));
		client.setAutreTypeCarte(element.getAttributeValue("autreTypeCarte"));
		client.setLieuDeNaiss(element.getAttributeValue("lieuDeNaiss"));
		client.setCivilite(Civilite.getByValue(element.getAttributeValue("civilite")));


		return client;
	}

	public static Client parseXMLString(String xmlString){
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
	public static java.util.List<Client> parseXMLFile (java.io.File xmlFile){
		java.util.List<Client> listClients = new java.util.ArrayList<Client>();
		try{
			org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder();
			org.jdom.Document document = builder.build(xmlFile);
			org.jdom.Element rootNode = document.getRootElement();
			if (!rootNode.getName().equals("Clients"))
				return null;
			java.util.List<?> listChildren = rootNode.getChildren("Client");
			for (Object child : listChildren){
				if (! (child instanceof org.jdom.Element))
					continue;
				org.jdom.Element elementChild = (org.jdom.Element)child;
				if (! elementChild.getName().equals("Client"))
					continue;
				listClients.add(parseXMLElement(elementChild));
			}
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
			return null;
		}
		return listClients;
	}

	public java.util.List<ClientConsommeService> getListOfClientConsommeServicesClients(){
		return this.listOfClientConsommeServicesClient;
	}

	public void setListOfClientConsommeServicesClients(java.util.List<ClientConsommeService> list){
		this.listOfClientConsommeServicesClient.clear();
		for (ClientConsommeService item : list){
			this.listOfClientConsommeServicesClient.add(item);
		}
	}

	public static boolean isAutoFetchListOfListOfClientConsommeServicesClient(){
		return Client.autoFetchListOfListOfClientConsommeServicesClient;
	}

	public static void setAutoFetchListOfListOfClientConsommeServicesClient(boolean autoFetchListOfListOfClientConsommeServicesClient){
		Client.autoFetchListOfListOfClientConsommeServicesClient = autoFetchListOfListOfClientConsommeServicesClient;
	}

	public java.util.List<ClientReseveChambre> getListOfClientReseveChambresClients(){
		return this.listOfClientReseveChambresClient;
	}

	public void setListOfClientReseveChambresClients(java.util.List<ClientReseveChambre> list){
		this.listOfClientReseveChambresClient.clear();
		for (ClientReseveChambre item : list){
			this.listOfClientReseveChambresClient.add(item);
		}
	}

	public static boolean isAutoFetchListOfListOfClientReseveChambresClient(){
		return Client.autoFetchListOfListOfClientReseveChambresClient;
	}

	public static void setAutoFetchListOfListOfClientReseveChambresClient(boolean autoFetchListOfListOfClientReseveChambresClient){
		Client.autoFetchListOfListOfClientReseveChambresClient = autoFetchListOfListOfClientReseveChambresClient;
	}

	public java.util.List<Facture> getListOfFacturesClients(){
		return this.listOfFacturesClient;
	}

	public void setListOfFacturesClients(java.util.List<Facture> list){
		this.listOfFacturesClient.clear();
		for (Facture item : list){
			this.listOfFacturesClient.add(item);
		}
	}

	public static boolean isAutoFetchListOfListOfFacturesClient(){
		return Client.autoFetchListOfListOfFacturesClient;
	}

	public static void setAutoFetchListOfListOfFacturesClient(boolean autoFetchListOfListOfFacturesClient){
		Client.autoFetchListOfListOfFacturesClient = autoFetchListOfListOfFacturesClient;
	}

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

	public String toString(){
		String html = "<html>";
		html+= ""+this.getNom()+" "+this.getPrenom() ;
		html+= " "+this.getTypeClient().getValue();
		html+= "</html>";
		return html;
	}
}