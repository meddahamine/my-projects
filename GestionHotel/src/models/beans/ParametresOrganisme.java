package models.beans;

import java.io.Serializable;
/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This classe is a Bean. 
 */

public class ParametresOrganisme implements Serializable {
	private static final long serialVersionUID = 1L;

	private java.lang.Integer id = 0;
	private java.lang.String designationOrganisme = "";
	private java.lang.String raisonSocial = "";
	private java.lang.String capitalSocial = "";
	private java.lang.String numRC = "";
	private java.lang.String numCB = "";
	private java.lang.String identificationFiscale = "";
	private java.lang.String numArticle = "";
	private java.lang.String NIS = "";
	private java.lang.String adresse = "";
	private java.lang.String boitePostale = "";
	private java.lang.String numTel = "";
	private java.lang.String numFax = "";
	private java.lang.String Email = "";
	private java.lang.String descriptif = "";
	private java.lang.Integer idPhoto = 0;

	private Photo photo = null;

	public ParametresOrganisme() {
	}

	public ParametresOrganisme(java.lang.Integer id, java.lang.String designationOrganisme, java.lang.String raisonSocial, java.lang.String capitalSocial, java.lang.String numRC, java.lang.String numCB, java.lang.String identificationFiscale, java.lang.String numArticle, java.lang.String NIS, java.lang.String adresse, java.lang.String boitePostale, java.lang.String numTel, java.lang.String numFax, java.lang.String Email, java.lang.String descriptif, java.lang.Integer idPhoto) {
		this.setId(id);
		this.setDesignationOrganisme(designationOrganisme);
		this.setRaisonSocial(raisonSocial);
		this.setCapitalSocial(capitalSocial);
		this.setNumRC(numRC);
		this.setNumCB(numCB);
		this.setIdentificationFiscale(identificationFiscale);
		this.setNumArticle(numArticle);
		this.setNIS(NIS);
		this.setAdresse(adresse);
		this.setBoitePostale(boitePostale);
		this.setNumTel(numTel);
		this.setNumFax(numFax);
		this.setEmail(Email);
		this.setDescriptif(descriptif);
		this.setIdPhoto(idPhoto);
	}

	public ParametresOrganisme(String xmlString) {
		org.jdom.Element element = utils.StringUtils.xmlStringToXmlElement(xmlString);
		fillByXMLElement(element);
	}

	public ParametresOrganisme(org.jdom.Element element) {
		fillByXMLElement(element);
	}

	public void fillByXMLElement(org.jdom.Element element) {
		this.setId(Integer.parseInt(element.getAttributeValue("id")));
		this.setDesignationOrganisme(element.getAttributeValue("designationOrganisme"));
		this.setRaisonSocial(element.getAttributeValue("raisonSocial"));
		this.setCapitalSocial(element.getAttributeValue("capitalSocial"));
		this.setNumRC(element.getAttributeValue("numRC"));
		this.setNumCB(element.getAttributeValue("numCB"));
		this.setIdentificationFiscale(element.getAttributeValue("identificationFiscale"));
		this.setNumArticle(element.getAttributeValue("numArticle"));
		this.setNIS(element.getAttributeValue("NIS"));
		this.setAdresse(element.getAttributeValue("adresse"));
		this.setBoitePostale(element.getAttributeValue("boitePostale"));
		this.setNumTel(element.getAttributeValue("numTel"));
		this.setNumFax(element.getAttributeValue("numFax"));
		this.setEmail(element.getAttributeValue("Email"));
		this.setDescriptif(element.getAttributeValue("descriptif"));
		this.setIdPhoto(Integer.parseInt(element.getAttributeValue("idPhoto")));
	}

	public void fillByMap(java.util.Map<String, String> data) {
		this.setId(Integer.parseInt(data.get("id")));
		this.setDesignationOrganisme(data.get("designationOrganisme"));
		this.setRaisonSocial(data.get("raisonSocial"));
		this.setCapitalSocial(data.get("capitalSocial"));
		this.setNumRC(data.get("numRC"));
		this.setNumCB(data.get("numCB"));
		this.setIdentificationFiscale(data.get("identificationFiscale"));
		this.setNumArticle(data.get("numArticle"));
		this.setNIS(data.get("NIS"));
		this.setAdresse(data.get("adresse"));
		this.setBoitePostale(data.get("boitePostale"));
		this.setNumTel(data.get("numTel"));
		this.setNumFax(data.get("numFax"));
		this.setEmail(data.get("Email"));
		this.setDescriptif(data.get("descriptif"));
		this.setIdPhoto(Integer.parseInt(data.get("idPhoto")));
	}

	public void fillByArray(String[] array) {
		this.setId(Integer.parseInt(array[0]));
		this.setDesignationOrganisme(array[1]);
		this.setRaisonSocial(array[2]);
		this.setCapitalSocial(array[3]);
		this.setNumRC(array[4]);
		this.setNumCB(array[5]);
		this.setIdentificationFiscale(array[6]);
		this.setNumArticle(array[7]);
		this.setNIS(array[8]);
		this.setAdresse(array[9]);
		this.setBoitePostale(array[10]);
		this.setNumTel(array[11]);
		this.setNumFax(array[12]);
		this.setEmail(array[13]);
		this.setDescriptif(array[14]);
		this.setIdPhoto(Integer.parseInt(array[15]));
	}

	public boolean equals(Object obj) {
		if (obj == null){
			return false;
		}
		if (!(obj instanceof ParametresOrganisme)){
			return false;
		}
		ParametresOrganisme item = (ParametresOrganisme)obj;
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

	public java.lang.String getDesignationOrganisme(){
		return this.designationOrganisme;
	}

	public void setDesignationOrganisme(java.lang.String designationOrganisme){
		this.designationOrganisme = designationOrganisme;
	}

	public java.lang.String getRaisonSocial(){
		return this.raisonSocial;
	}

	public void setRaisonSocial(java.lang.String raisonSocial){
		this.raisonSocial = raisonSocial;
	}

	public java.lang.String getCapitalSocial(){
		return this.capitalSocial;
	}

	public void setCapitalSocial(java.lang.String capitalSocial){
		this.capitalSocial = capitalSocial;
	}

	public java.lang.String getNumRC(){
		return this.numRC;
	}

	public void setNumRC(java.lang.String numRC){
		this.numRC = numRC;
	}

	public java.lang.String getNumCB(){
		return this.numCB;
	}

	public void setNumCB(java.lang.String numCB){
		this.numCB = numCB;
	}

	public java.lang.String getIdentificationFiscale(){
		return this.identificationFiscale;
	}

	public void setIdentificationFiscale(java.lang.String identificationFiscale){
		this.identificationFiscale = identificationFiscale;
	}

	public java.lang.String getNumArticle(){
		return this.numArticle;
	}

	public void setNumArticle(java.lang.String numArticle){
		this.numArticle = numArticle;
	}

	public java.lang.String getNIS(){
		return this.NIS;
	}

	public void setNIS(java.lang.String NIS){
		this.NIS = NIS;
	}

	public java.lang.String getAdresse(){
		return this.adresse;
	}

	public void setAdresse(java.lang.String adresse){
		this.adresse = adresse;
	}

	public java.lang.String getBoitePostale(){
		return this.boitePostale;
	}

	public void setBoitePostale(java.lang.String boitePostale){
		this.boitePostale = boitePostale;
	}

	public java.lang.String getNumTel(){
		return this.numTel;
	}

	public void setNumTel(java.lang.String numTel){
		this.numTel = numTel;
	}

	public java.lang.String getNumFax(){
		return this.numFax;
	}

	public void setNumFax(java.lang.String numFax){
		this.numFax = numFax;
	}

	public java.lang.String getEmail(){
		return this.Email;
	}

	public void setEmail(java.lang.String Email){
		this.Email = Email;
	}

	public java.lang.String getDescriptif(){
		return this.descriptif;
	}

	public void setDescriptif(java.lang.String descriptif){
		this.descriptif = descriptif;
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

	public String toXMLString(){
		String xml = "<ParametresOrganisme ";
		xml+= "id='"+this.getId()+"' ";
		xml+= "designationOrganisme='"+this.getDesignationOrganisme()+"' ";
		xml+= "raisonSocial='"+this.getRaisonSocial()+"' ";
		xml+= "capitalSocial='"+this.getCapitalSocial()+"' ";
		xml+= "numRC='"+this.getNumRC()+"' ";
		xml+= "numCB='"+this.getNumCB()+"' ";
		xml+= "identificationFiscale='"+this.getIdentificationFiscale()+"' ";
		xml+= "numArticle='"+this.getNumArticle()+"' ";
		xml+= "NIS='"+this.getNIS()+"' ";
		xml+= "adresse='"+this.getAdresse()+"' ";
		xml+= "boitePostale='"+this.getBoitePostale()+"' ";
		xml+= "numTel='"+this.getNumTel()+"' ";
		xml+= "numFax='"+this.getNumFax()+"' ";
		xml+= "Email='"+this.getEmail()+"' ";
		xml+= "descriptif='"+this.getDescriptif()+"' ";
		xml+= "idPhoto='"+this.getIdPhoto()+"' ";
		xml+= ">";
		xml+= "\n	"+photo.toXMLString().replace("<Photo", "<Photo").replace("</Photo", "</Photo");
		xml+= "\n</ParametresOrganisme>";
		return xml;
	}

	public org.jdom.Element toXMLElement(){
		org.jdom.Element element = new org.jdom.Element("ParametresOrganisme");
		element.setAttribute("id", ""+this.getId());
		element.setAttribute("designationOrganisme", ""+this.getDesignationOrganisme());
		element.setAttribute("raisonSocial", ""+this.getRaisonSocial());
		element.setAttribute("capitalSocial", ""+this.getCapitalSocial());
		element.setAttribute("numRC", ""+this.getNumRC());
		element.setAttribute("numCB", ""+this.getNumCB());
		element.setAttribute("identificationFiscale", ""+this.getIdentificationFiscale());
		element.setAttribute("numArticle", ""+this.getNumArticle());
		element.setAttribute("NIS", ""+this.getNIS());
		element.setAttribute("adresse", ""+this.getAdresse());
		element.setAttribute("boitePostale", ""+this.getBoitePostale());
		element.setAttribute("numTel", ""+this.getNumTel());
		element.setAttribute("numFax", ""+this.getNumFax());
		element.setAttribute("Email", ""+this.getEmail());
		element.setAttribute("descriptif", ""+this.getDescriptif());
		element.setAttribute("idPhoto", ""+this.getIdPhoto());

		element.addContent(photo.toXMLElement());

		return element;
	}

	public static ParametresOrganisme parseXMLElement(org.jdom.Element element){
		ParametresOrganisme parametresOrganisme = new ParametresOrganisme();
		parametresOrganisme.setId(Integer.parseInt(element.getAttributeValue("id")));
		parametresOrganisme.setDesignationOrganisme(element.getAttributeValue("designationOrganisme"));
		parametresOrganisme.setRaisonSocial(element.getAttributeValue("raisonSocial"));
		parametresOrganisme.setCapitalSocial(element.getAttributeValue("capitalSocial"));
		parametresOrganisme.setNumRC(element.getAttributeValue("numRC"));
		parametresOrganisme.setNumCB(element.getAttributeValue("numCB"));
		parametresOrganisme.setIdentificationFiscale(element.getAttributeValue("identificationFiscale"));
		parametresOrganisme.setNumArticle(element.getAttributeValue("numArticle"));
		parametresOrganisme.setNIS(element.getAttributeValue("NIS"));
		parametresOrganisme.setAdresse(element.getAttributeValue("adresse"));
		parametresOrganisme.setBoitePostale(element.getAttributeValue("boitePostale"));
		parametresOrganisme.setNumTel(element.getAttributeValue("numTel"));
		parametresOrganisme.setNumFax(element.getAttributeValue("numFax"));
		parametresOrganisme.setEmail(element.getAttributeValue("Email"));
		parametresOrganisme.setDescriptif(element.getAttributeValue("descriptif"));
		parametresOrganisme.setIdPhoto(Integer.parseInt(element.getAttributeValue("idPhoto")));

		parametresOrganisme.setPhoto(Photo.parseXMLElement(element.getChild("Photo")));

		return parametresOrganisme;
	}

	public static ParametresOrganisme parseXMLString(String xmlString){
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
	public static java.util.List<ParametresOrganisme> parseXMLFile (java.io.File xmlFile){
		java.util.List<ParametresOrganisme> listParametresOrganismes = new java.util.ArrayList<ParametresOrganisme>();
		try{
			org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder();
			org.jdom.Document document = builder.build(xmlFile);
			org.jdom.Element rootNode = document.getRootElement();
			if (!rootNode.getName().equals("ParametresOrganismes"))
				return null;
			java.util.List<?> listChildren = rootNode.getChildren("ParametresOrganisme");
			for (Object child : listChildren){
				if (! (child instanceof org.jdom.Element))
					continue;
				org.jdom.Element elementChild = (org.jdom.Element)child;
				if (! elementChild.getName().equals("ParametresOrganisme"))
					continue;
				listParametresOrganismes.add(parseXMLElement(elementChild));
			}
		}catch (Exception e){
			utils.StringUtils.printDebug(e);
			return null;
		}
		return listParametresOrganismes;
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