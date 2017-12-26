package net.tp.spring.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Statistiques")
public class Statistique {

	@XmlElement
	int NombreTransactions;
	
	@XmlElement
	double MontantTotal;
	
	public Statistique(){
		
	}

	public Statistique(int nombreTransactions, double montantTotal) {
		super();
		NombreTransactions = nombreTransactions;
		MontantTotal = montantTotal;
	}
	
}