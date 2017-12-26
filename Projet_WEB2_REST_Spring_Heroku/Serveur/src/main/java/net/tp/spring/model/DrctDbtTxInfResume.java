package net.tp.spring.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Transaction")
public class DrctDbtTxInfResume {

	@XmlElement
	String Num;
	
	@XmlElement
	String Identifiant;
	
	@XmlElement
	double Montant;
	
	@XmlElement
	String Date;
	
	public DrctDbtTxInfResume(){
		
	}

	public DrctDbtTxInfResume(String num, String identifiant, double montant, String date) {
		super();
		this.Num = num;
		Identifiant = identifiant;
		this.Montant = montant;
		this.Date = date;
	}
	
}
