package net.tp.spring.model;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "DrctDbtTxInf")
@XmlAccessorType(XmlAccessType.FIELD)
public class DrctDbtTxInf {

	@XmlTransient
	int id;
	
	@XmlTransient
	String Num;
	
	@XmlElement
	String PmtId;

	@XmlElement
	double InstdAmt;
	
	@XmlElement
	DrctDbtTx DrctDbtTx;
	
	@XmlElement
	DbtrAgt DbtrAgt;
	
	@XmlElement
	Dbtr Dbtr;
	
	@XmlElement
	DbtrAcct DbtrAcct;
	
	@XmlElement
	String RmtInf;

	public DrctDbtTxInf() {
		
	}

	public DrctDbtTxInf(int id, String num,String pmtId, double instdAmt, DrctDbtTx drctDbtTx,
			DbtrAgt dbtrAgt, Dbtr dbtr, DbtrAcct dbtrAcct,
			String rmtInf) {
		super();
		this.id = id;
		Num = verifNum(num);
		PmtId = pmtId;
		InstdAmt = instdAmt;
		DrctDbtTx = drctDbtTx;
		DbtrAgt = dbtrAgt;
		Dbtr = dbtr;
		DbtrAcct = dbtrAcct;
		RmtInf = rmtInf;
	}

	public int getId() {
		return id;
	}

	public String getNum() {
		return Num;
	}

	public String verifNum(String n) {
		while(n.length()<4){
			n = "0"+n;
		}
		n = "AM"+n;
		return n;
	}

	public String getPmtId() {
		return PmtId;
	}

	public double getInstdAmt() {
		return InstdAmt;
	}

	public DrctDbtTx getDrctDbtTx() {
		return DrctDbtTx;
	}

	public DbtrAgt getDbtrAgt() {
		return DbtrAgt;
	}

	public Dbtr getDbtr() {
		return Dbtr;
	}

	public DbtrAcct getDbtrAcct() {
		return DbtrAcct;
	}

	public String getRmtInf() {
		return RmtInf;
	}
	
}
