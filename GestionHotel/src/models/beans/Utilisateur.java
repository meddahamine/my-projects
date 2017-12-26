package models.beans;

import java.io.Serializable;
/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This classe is a Bean. 
 */

@SuppressWarnings("deprecation")
public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 1L;

	public static enum Civilite {
		Mr("Mr."), Mme("Mme.");

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
	private java.lang.String nom = "";
	private java.lang.String prenom = "";
	private Civilite civilite = null;
	private java.sql.Date dateNaissance = new java.sql.Date(1, 0, 1);
	private java.lang.String lieuNaissance = "";
	private java.lang.String login = "";
	private java.lang.String password = "";
	private java.lang.Integer idPhoto = 0;
	private java.lang.Integer idRole = 0;
	private java.lang.Integer idParametres = 0;

	private Photo photo = null;

	private Role role = null;

	private ParametresApplicationUtilisateur parametres = null;
	
	ConnexionsLog connexion = null;
	
	private final java.util.List<String> errors = new java.util.ArrayList<String>();
	
	private static boolean autoFetchListOfListOfConnexionsLogsUtilisateur = false;
	private java.util.List<ConnexionsLog> listOfConnexionsLogsUtilisateur = new java.util.ArrayList<ConnexionsLog>();

	public Utilisateur() {
	}

	public Utilisateur(java.lang.Integer id, java.lang.String nom, java.lang.String prenom, Civilite civilite, java.sql.Date dateNaissance, java.lang.String lieuNaissance, java.lang.String login, java.lang.String password, java.lang.Integer idPhoto, java.lang.Integer idRole, java.lang.Integer idParametres) {
		this.setId(id);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setCivilite(civilite);
		this.setDateNaissance(dateNaissance);
		this.setLieuNaissance(lieuNaissance);
		this.setLogin(login);
		this.setPassword(password);
		this.setIdPhoto(idPhoto);
		this.setIdRole(idRole);
		this.setIdParametres(idParametres);
	}

	public Utilisateur(String xmlString) {
		org.jdom.Element element = utils.StringUtils.xmlStringToXmlElement(xmlString);
		fillByXMLElement(element);
	}

	public Utilisateur(org.jdom.Element element) {
		fillByXMLElement(element);
	}

	public void fillByXMLElement(org.jdom.Element element) {
		this.setId(Integer.parseInt(element.getAttributeValue("id")));
		this.setNom(element.getAttributeValue("nom"));
		this.setPrenom(element.getAttributeValue("prenom"));
		this.setCivilite(Civilite.getByValue(element.getAttributeValue("civilite")));
		this.setDateNaissance(utils.StringUtils.getDateFromString(element.getAttributeValue("dateNaissance")));
		this.setLieuNaissance(element.getAttributeValue("lieuNaissance"));
		this.setLogin(element.getAttributeValue("login"));
		this.setPassword(element.getAttributeValue("password"));
		this.setIdPhoto(Integer.parseInt(element.getAttributeValue("idPhoto")));
		this.setIdRole(Integer.parseInt(element.getAttributeValue("idRole")));
		this.setIdParametres(Integer.parseInt(element.getAttributeValue("idParametres")));
	}

	public void fillByMap(java.util.Map<String, String> data) {
		this.setId(Integer.parseInt(data.get("id")));
		this.setNom(data.get("nom"));
		this.setPrenom(data.get("prenom"));
		this.setCivilite(Civilite.getByValue(data.get("civilite")));
		this.setDateNaissance(utils.StringUtils.getDateFromString(data.get("dateNaissance")));
		this.setLieuNaissance(data.get("lieuNaissance"));
		this.setLogin(data.get("login"));
		this.setPassword(data.get("password"));
		this.setIdPhoto(Integer.parseInt(data.get("idPhoto")));
		this.setIdRole(Integer.parseInt(data.get("idRole")));
		this.setIdParametres(Integer.parseInt(data.get("idParametres")));
	}

	public void fillByArray(String[] array) {
		this.setId(Integer.parseInt(array[0]));
		this.setNom(array[1]);
		this.setPrenom(array[2]);
		this.setCivilite(Civilite.getByValue(array[3]));
		this.setDateNaissance(utils.StringUtils.getDateFromString(array[4]));
		this.setLieuNaissance(array[5]);
		this.setLogin(array[6]);
		this.setPassword(array[7]);
		this.setIdPhoto(Integer.parseInt(array[8]));
		this.setIdRole(Integer.parseInt(array[9]));
		this.setIdParametres(Integer.parseInt(array[10]));
	}

	public boolean equals(Object obj) {
		if (obj == null){
			return false;
		}
		if (!(obj instanceof Utilisateur)){
			return false;
		}
		Utilisateur item = (Utilisateur)obj;
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

	public Civilite getCivilite(){
		return this.civilite;
	}

	public void setCivilite(Civilite civilite){
		this.civilite = civilite;
	}

	public java.sql.Date getDateNaissance(){
		return this.dateNaissance;
	}

	public void setDateNaissance(java.sql.Date dateNaissance){
		this.dateNaissance = dateNaissance;
	}

	public java.lang.String getLieuNaissance(){
		return this.lieuNaissance;
	}

	public void setLieuNaissance(java.lang.String lieuNaissance){
		this.lieuNaissance = lieuNaissance;
	}

	public java.lang.String getLogin(){
		return this.login;
	}

	public void setLogin(java.lang.String login){
		this.login = login;
	}

	public java.lang.String getPassword(){
		return this.password;
	}

	public void setPassword(java.lang.String password){
		this.setPassword(password, true);
	}
	public void setPassword(java.lang.String password, boolean md5){
		if (!md5){
			password = utils.StringUtils.encodeMD5(password);
		}
		this.password = password;
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

	public java.lang.Integer getIdRole(){
		return this.idRole;
	}

	public void setIdRole(java.lang.Integer idRole){
		this.idRole = idRole;
	}

	public Role getRole(){
		return this.role;
	}

	public void setRole(Role role){
		this.role = role;
		int idRole = 0;
		if (role != null){
			idRole = role.getId().intValue();
		}
		this.idRole = idRole;
	}

	public java.lang.Integer getIdParametres(){
		return this.idParametres;
	}

	public void setIdParametres(java.lang.Integer idParametres){
		this.idParametres = idParametres;
	}

	public ParametresApplicationUtilisateur getParametres(){
		return this.parametres;
	}

	public void setParametres(ParametresApplicationUtilisateur parametres){
		this.parametres = parametres;
		int idParametres = 0;
		if (parametres != null){
			idParametres = parametres.getId().intValue();
		}
		this.idParametres = idParametres;
	}

	public String toXMLString(){
		String xml = "<Utilisateur ";
		xml+= "id='"+this.getId()+"' ";
		xml+= "nom='"+this.getNom()+"' ";
		xml+= "prenom='"+this.getPrenom()+"' ";
		xml+= "civilite='"+this.getCivilite().getValue()+"' ";
		xml+= "dateNaissance='"+this.getDateNaissance()+"' ";
		xml+= "lieuNaissance='"+this.getLieuNaissance()+"' ";
		xml+= "login='"+this.getLogin()+"' ";
		xml+= "password='"+this.getPassword()+"' ";
		xml+= "idPhoto='"+this.getIdPhoto()+"' ";
		xml+= "idRole='"+this.getIdRole()+"' ";
		xml+= "idParametres='"+this.getIdParametres()+"' ";
		xml+= ">";
		xml+= "\n	"+photo.toXMLString().replace("<Photo", "<Photo").replace("</Photo", "</Photo");
		xml+= "\n	"+role.toXMLString().replace("<Role", "<Role").replace("</Role", "</Role");
		xml+= "\n	"+parametres.toXMLString().replace("<ParametresApplicationUtilisateur", "<Parametres").replace("</ParametresApplicationUtilisateur", "</Parametres");
		xml+= "\n</Utilisateur>";
		return xml;
	}

	public org.jdom.Element toXMLElement(){
		org.jdom.Element element = new org.jdom.Element("Utilisateur");
		element.setAttribute("id", ""+this.getId());
		element.setAttribute("nom", ""+this.getNom());
		element.setAttribute("prenom", ""+this.getPrenom());
		element.setAttribute("civilite", ""+this.getCivilite().getValue());
		element.setAttribute("dateNaissance", ""+this.getDateNaissance());
		element.setAttribute("lieuNaissance", ""+this.getLieuNaissance());
		element.setAttribute("login", ""+this.getLogin());
		element.setAttribute("password", ""+this.getPassword());
		element.setAttribute("idPhoto", ""+this.getIdPhoto());
		element.setAttribute("idRole", ""+this.getIdRole());
		element.setAttribute("idParametres", ""+this.getIdParametres());

		element.addContent(photo.toXMLElement());
		element.addContent(role.toXMLElement());
		element.addContent(parametres.toXMLElement());

		return element;
	}

	public static Utilisateur parseXMLElement(org.jdom.Element element){
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(Integer.parseInt(element.getAttributeValue("id")));
		utilisateur.setNom(element.getAttributeValue("nom"));
		utilisateur.setPrenom(element.getAttributeValue("prenom"));
		utilisateur.setCivilite(Civilite.getByValue(element.getAttributeValue("civilite")));
		utilisateur.setDateNaissance(utils.StringUtils.getDateFromString(element.getAttributeValue("dateNaissance")));
		utilisateur.setLieuNaissance(element.getAttributeValue("lieuNaissance"));
		utilisateur.setLogin(element.getAttributeValue("login"));
		utilisateur.setPassword(element.getAttributeValue("password"));
		utilisateur.setIdPhoto(Integer.parseInt(element.getAttributeValue("idPhoto")));
		utilisateur.setIdRole(Integer.parseInt(element.getAttributeValue("idRole")));
		utilisateur.setIdParametres(Integer.parseInt(element.getAttributeValue("idParametres")));

		utilisateur.setPhoto(Photo.parseXMLElement(element.getChild("Photo")));
		utilisateur.setRole(Role.parseXMLElement(element.getChild("Role")));
		utilisateur.setParametres(ParametresApplicationUtilisateur.parseXMLElement(element.getChild("Parametres")));

		return utilisateur;
	}

	public static Utilisateur parseXMLString(String xmlString){
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
	public static java.util.List<Utilisateur> parseXMLFile (java.io.File xmlFile){
		java.util.List<Utilisateur> listUtilisateurs = new java.util.ArrayList<Utilisateur>();
		try{
			org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder();
			org.jdom.Document document = builder.build(xmlFile);
			org.jdom.Element rootNode = document.getRootElement();
			if (!rootNode.getName().equals("Utilisateurs"))
				return null;
			java.util.List<?> listChildren = rootNode.getChildren("Utilisateur");
			for (Object child : listChildren){
				if (! (child instanceof org.jdom.Element))
					continue;
				org.jdom.Element elementChild = (org.jdom.Element)child;
				if (! elementChild.getName().equals("Utilisateur"))
					continue;
				listUtilisateurs.add(parseXMLElement(elementChild));
			}
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
			return null;
		}
		return listUtilisateurs;
	}

	public void setConnexion(ConnexionsLog connexion){
		this.connexion = connexion;
	}

	public ConnexionsLog getConnexion(){
		return this.connexion;
	}

	public java.util.List<String> getErrors(){
		return this.errors;
	}

	public void addError(String error){
		this.errors.add(error);
	}
	
	public String getErrorsAsString(){
		String errorsAsString = "";
		for (String error : errors){
			if (errorsAsString.equals(""))
				errorsAsString += "\n";
			errorsAsString += error;
		}
		return errorsAsString;
	}

	public boolean isSuperUser(){
		return this.getId().intValue() == 1;
	}

	public java.util.List<ConnexionsLog> getListOfConnexionsLogsUtilisateurs(){
		return this.listOfConnexionsLogsUtilisateur;
	}

	public void setListOfConnexionsLogsUtilisateurs(java.util.List<ConnexionsLog> list){
		this.listOfConnexionsLogsUtilisateur.clear();
		for (ConnexionsLog item : list){
			this.listOfConnexionsLogsUtilisateur.add(item);
		}
	}

	public static boolean isAutoFetchListOfListOfConnexionsLogsUtilisateur(){
		return Utilisateur.autoFetchListOfListOfConnexionsLogsUtilisateur;
	}

	public static void setAutoFetchListOfListOfConnexionsLogsUtilisateur(boolean autoFetchListOfListOfConnexionsLogsUtilisateur){
		Utilisateur.autoFetchListOfListOfConnexionsLogsUtilisateur = autoFetchListOfListOfConnexionsLogsUtilisateur;
	}

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

	public String toString(){
		String html = "<html>";
		html+= "Nom & Prénom : "+this.getNom()+" "+this.getPrenom()+"<br/>";
		html+= "Login : "+this.getLogin();
		html+= "</html>";
		return html;
	}
}