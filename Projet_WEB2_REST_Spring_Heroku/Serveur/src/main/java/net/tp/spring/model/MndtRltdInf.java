package net.tp.spring.model;

import javax.xml.bind.annotation.XmlElement;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MndtRltdInf")
public class MndtRltdInf {
	
	@XmlElement
	String MndtId;

	@XmlElement
	String DtOfSgntr;

	public MndtRltdInf() {
		
	}
	
	public MndtRltdInf(String mndtId, String dtOfSgntr) {
		super();
		MndtId = mndtId;
		DtOfSgntr = dtOfSgntr;
	}
	
	public String getDtOfSgntr() {
		return DtOfSgntr;
	}

	public String getMndtId() {
		return MndtId;
	}
	
}
