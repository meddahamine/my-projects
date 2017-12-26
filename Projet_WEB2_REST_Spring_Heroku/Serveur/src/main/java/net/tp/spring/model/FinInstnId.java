package net.tp.spring.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "FinInstnId")
public class FinInstnId {

	@XmlElement
	String BIC;
	
	public FinInstnId() {
		
	}

	public FinInstnId(String bic) {
		super();
		this.BIC = bic;
	}

	public String getBIC() {
		return BIC;
	}
}
