package net.tp.spring.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Dbtr")
public class Dbtr {

	@XmlElement
	String Nm;

	public Dbtr() {
		
	}

	public Dbtr(String nm) {
		super();
		Nm = nm;
	}

	public String getNm() {
		return Nm;
	}
	
}