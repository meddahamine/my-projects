package net.tp.spring.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Id")
public class Id {

	@XmlElement
	String IBAN;

	public Id() {
		
	}

	public Id(String iBAN) {
		super();
		IBAN = iBAN;
	}

	public String getIBAN() {
		return IBAN;
	}
}
