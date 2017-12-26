package net.tp.spring.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DbtrAgt")
public class DbtrAgt {

	@XmlElement
	FinInstnId FinInstnId;

	public DbtrAgt() {
		
	}
	
	public DbtrAgt(FinInstnId finInstnId) {
		super();
		this.FinInstnId = finInstnId;
	}

	public FinInstnId getFinInstnId() {
		return FinInstnId;
	}
}
