package net.tp.spring.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DbtrAcct")
public class DbtrAcct {

	@XmlElement
	Id Id;

	public DbtrAcct() {
		
	}

	public DbtrAcct(Id id) {
		super();
		Id = id;
	}

	public Id getId() {
		return Id;
	}
}
