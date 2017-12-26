package net.tp.spring.controller;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;

import net.tp.spring.dao.ITransactionDAO;
import net.tp.spring.model.*;
import net.tp.spring.validator.ValidateXML;

@Controller
public class SepaController {
	
	@Autowired
	protected ITransactionDAO transactionDAO;
	
	private SEPA sepa;
	
	private SEPAResume sepaResume;
	
	public SepaController(){
		sepa = new SEPA();
		
		sepaResume = new SEPAResume();
		
	}
	
	/*Renvoie un flux XML contenant la liste des transactions détaillées*/
	@RequestMapping(value="/detail", method = RequestMethod.GET)
	public @ResponseBody SEPA getSEPAInXML() {
		this.sepa.setTransactions(transactionDAO.list());
		return this.sepa;
	}
	
	/*Renvoie un flux XML contenant la liste des transactions résumées*/
	@RequestMapping(value="/resume", method = RequestMethod.GET)
	public @ResponseBody SEPAResume getSEPAInXMLResume() {
		this.sepaResume.setTransactions(transactionDAO.listResume());
		return this.sepaResume;
	}
	
	/*Affiche une synthèse des transactions stockées, avec les informations suivantes :
	 * Nombre de transactions, montant total des transactions*/
	@RequestMapping(value="/stats", method = RequestMethod.GET)
	public @ResponseBody Statistique getSEPAStats() {
		return transactionDAO.getStats();
	}
	
	/*Renvoie un flux XML décrivant le détail de la transaction d’identifiant id 
	 * avec id = PmtId */
	@RequestMapping(value="/trx/{id}", method = RequestMethod.GET)
	public @ResponseBody DrctDbtTxInf getTransactionById(@PathVariable("id") String id) {
		return transactionDAO.get(id);
	}
	
	/*Reçoit un flux XML décrivant une transaction, 
	 * crée l'objet correspondant et retourne la valeur PmtId*/
	@RequestMapping(value="/depot", method = RequestMethod.POST)
	public @ResponseBody Response addTransaction(@RequestBody String body) throws SAXException, ParserConfigurationException, IOException {
		
		InputSource inputSource = new InputSource(new StringReader(body));
		//InputSource source ;
		
		ValidateXML validator = new ValidateXML();
		
		if(validator.validate_XML("/sepa.xsd", inputSource)==0){
			return new Response("Fichier XML non valide.", null, null);
		}
		
		Document doc = (Document) DocumentBuilderFactory.newInstance().newDocumentBuilder()
	            .parse(inputSource);
		
		int id = transactionDAO.getMaxId()+1;
		
		try{
			DrctDbtTxInf drctDbtTxInf = new DrctDbtTxInf(id,Integer.toString(id),
				doc.getElementsByTagName("PmtId").item(0).getTextContent(),
				Double.parseDouble(doc.getElementsByTagName("InstdAmt").item(0).getTextContent()), 
				new DrctDbtTx((new MndtRltdInf(doc.getElementsByTagName("MndtId").item(0).getTextContent(),
						doc.getElementsByTagName("DtOfSgntr").item(0).getTextContent()))),
				new DbtrAgt(new FinInstnId(doc.getElementsByTagName("BIC").item(0).getTextContent())),
				new Dbtr(doc.getElementsByTagName("Nm").item(0).getTextContent()), 
				new DbtrAcct(new Id(doc.getElementsByTagName("IBAN").item(0).getTextContent())),
				doc.getElementsByTagName("RmtInf").item(0).getTextContent());
		
				if(transactionDAO.get(drctDbtTxInf.getPmtId())!=null){
					return new Response("L'identifiant de votre transaction existe déjà.", null, null);
				}
				
				transactionDAO.add(drctDbtTxInf);
				
				return new Response(null, "Transaction enregistrée.", drctDbtTxInf.getNum());
		}
		catch(NullPointerException e){
			return new Response("Fichier XML non valide !", null, null);
		}
	}
	
	
}