package models.beans;

import java.io.Serializable;
/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This classe is a Bean. 
 */

public class Chambre implements Serializable {
	private static final long serialVersionUID = 1L;

	private java.lang.Integer id = 0;
	private java.lang.String numChamre = "";
	private java.lang.String numEtage = "";
	private java.lang.Integer idCategorieChambre = 0;
	private java.lang.Double prix = 0.0;

	private CategorieChambre categorieChambre = null;
	
	private static boolean autoFetchListOfListOfClientReseveChambresChambre = false;
	private java.util.List<ClientReseveChambre> listOfClientReseveChambresChambre = new java.util.ArrayList<ClientReseveChambre>();

	public Chambre() {
	}

	public Chambre(java.lang.Integer id, java.lang.String numChamre, java.lang.String numEtage, java.lang.Integer idCategorieChambre, java.lang.Double prix) {
		this.setId(id);
		this.setNumChamre(numChamre);
		this.setNumEtage(numEtage);
		this.setIdCategorieChambre(idCategorieChambre);
		this.setPrix(prix);
	}

	public Chambre(String xmlString) {
		org.jdom.Element element = utils.StringUtils.xmlStringToXmlElement(xmlString);
		fillByXMLElement(element);
	}

	public Chambre(org.jdom.Element element) {
		fillByXMLElement(element);
	}

	public void fillByXMLElement(org.jdom.Element element) {
		this.setId(Integer.parseInt(element.getAttributeValue("id")));
		this.setNumChamre(element.getAttributeValue("numChamre"));
		this.setNumEtage(element.getAttributeValue("numEtage"));
		this.setIdCategorieChambre(Integer.parseInt(element.getAttributeValue("idCategorieChambre")));
		this.setPrix(Double.parseDouble(element.getAttributeValue("prix")));
	}

	public void fillByMap(java.util.Map<String, String> data) {
		this.setId(Integer.parseInt(data.get("id")));
		this.setNumChamre(data.get("numChamre"));
		this.setNumEtage(data.get("numEtage"));
		this.setIdCategorieChambre(Integer.parseInt(data.get("idCategorieChambre")));
		this.setPrix(Double.parseDouble(data.get("prix")));
	}

	public void fillByArray(String[] array) {
		this.setId(Integer.parseInt(array[0]));
		this.setNumChamre(array[1]);
		this.setNumEtage(array[2]);
		this.setIdCategorieChambre(Integer.parseInt(array[3]));
		this.setPrix(Double.parseDouble(array[4]));
	}

	public boolean equals(Object obj) {
		if (obj == null){
			return false;
		}
		if (!(obj instanceof Chambre)){
			return false;
		}
		Chambre item = (Chambre)obj;
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

	public java.lang.String getNumChamre(){
		return this.numChamre;
	}

	public void setNumChamre(java.lang.String numChamre){
		this.numChamre = numChamre;
	}

	public java.lang.String getNumEtage(){
		return this.numEtage;
	}

	public void setNumEtage(java.lang.String numEtage){
		this.numEtage = numEtage;
	}

	public java.lang.Integer getIdCategorieChambre(){
		return this.idCategorieChambre;
	}

	public void setIdCategorieChambre(java.lang.Integer idCategorieChambre){
		this.idCategorieChambre = idCategorieChambre;
	}

	public CategorieChambre getCategorieChambre(){
		return this.categorieChambre;
	}

	public void setCategorieChambre(CategorieChambre categorieChambre){
		this.categorieChambre = categorieChambre;
		int idCategorieChambre = 0;
		if (categorieChambre != null){
			idCategorieChambre = categorieChambre.getId().intValue();
		}
		this.idCategorieChambre = idCategorieChambre;
	}

	public java.lang.Double getPrix(){
		return this.prix;
	}

	public void setPrix(java.lang.Double prix){
		this.prix = prix;
	}

	public String toXMLString(){
		String xml = "<Chambre ";
		xml+= "id='"+this.getId()+"' ";
		xml+= "numChamre='"+this.getNumChamre()+"' ";
		xml+= "numEtage='"+this.getNumEtage()+"' ";
		xml+= "idCategorieChambre='"+this.getIdCategorieChambre()+"' ";
		xml+= "prix='"+this.getPrix()+"' ";
		xml+= ">";
		xml+= "\n	"+categorieChambre.toXMLString().replace("<CategorieChambre", "<CategorieChambre").replace("</CategorieChambre", "</CategorieChambre");
		xml+= "\n</Chambre>";
		return xml;
	}

	public org.jdom.Element toXMLElement(){
		org.jdom.Element element = new org.jdom.Element("Chambre");
		element.setAttribute("id", ""+this.getId());
		element.setAttribute("numChamre", ""+this.getNumChamre());
		element.setAttribute("numEtage", ""+this.getNumEtage());
		element.setAttribute("idCategorieChambre", ""+this.getIdCategorieChambre());
		element.setAttribute("prix", ""+this.getPrix());

		element.addContent(categorieChambre.toXMLElement());

		return element;
	}

	public static Chambre parseXMLElement(org.jdom.Element element){
		Chambre chambre = new Chambre();
		chambre.setId(Integer.parseInt(element.getAttributeValue("id")));
		chambre.setNumChamre(element.getAttributeValue("numChamre"));
		chambre.setNumEtage(element.getAttributeValue("numEtage"));
		chambre.setIdCategorieChambre(Integer.parseInt(element.getAttributeValue("idCategorieChambre")));
		chambre.setPrix(Double.parseDouble(element.getAttributeValue("prix")));

		chambre.setCategorieChambre(CategorieChambre.parseXMLElement(element.getChild("CategorieChambre")));

		return chambre;
	}

	public static Chambre parseXMLString(String xmlString){
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
	public static java.util.List<Chambre> parseXMLFile (java.io.File xmlFile){
		java.util.List<Chambre> listChambres = new java.util.ArrayList<Chambre>();
		try{
			org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder();
			org.jdom.Document document = builder.build(xmlFile);
			org.jdom.Element rootNode = document.getRootElement();
			if (!rootNode.getName().equals("Chambres"))
				return null;
			java.util.List<?> listChildren = rootNode.getChildren("Chambre");
			for (Object child : listChildren){
				if (! (child instanceof org.jdom.Element))
					continue;
				org.jdom.Element elementChild = (org.jdom.Element)child;
				if (! elementChild.getName().equals("Chambre"))
					continue;
				listChambres.add(parseXMLElement(elementChild));
			}
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
			return null;
		}
		return listChambres;
	}

	public java.util.List<ClientReseveChambre> getListOfClientReseveChambresChambres(){
		return this.listOfClientReseveChambresChambre;
	}

	public void setListOfClientReseveChambresChambres(java.util.List<ClientReseveChambre> list){
		this.listOfClientReseveChambresChambre.clear();
		for (ClientReseveChambre item : list){
			this.listOfClientReseveChambresChambre.add(item);
		}
	}

	public static boolean isAutoFetchListOfListOfClientReseveChambresChambre(){
		return Chambre.autoFetchListOfListOfClientReseveChambresChambre;
	}

	public static void setAutoFetchListOfListOfClientReseveChambresChambre(boolean autoFetchListOfListOfClientReseveChambresChambre){
		Chambre.autoFetchListOfListOfClientReseveChambresChambre = autoFetchListOfListOfClientReseveChambresChambre;
	}

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/


	public String toString(){
		String html = "<html>";
		html+= "Chambre : "+this.getNumChamre()+" ";
		html+= "Etage : "+this.getNumEtage();
		html+= "</html>";
		return html;
	}
}