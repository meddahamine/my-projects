package net.tp.spring.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DrctDbtTx")
public class DrctDbtTx{

	@XmlElement
	MndtRltdInf MndtRltdInf;
	
	public DrctDbtTx(){
		
	}
	
	public DrctDbtTx(MndtRltdInf mndtRltdInf) {
		super();
		this.MndtRltdInf = mndtRltdInf;
	}

	public MndtRltdInf getMndtRltdInf() {
		return MndtRltdInf;
	}
}