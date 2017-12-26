package net.tp.spring.validator;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class SimpleErrorHandler implements org.xml.sax.ErrorHandler{
	
	private boolean errorOccured;
	
	public SimpleErrorHandler() {
		// TODO Auto-generated constructor stub
		this.errorOccured = false;
	}

	public void error(SAXParseException arg0) throws SAXException {
		// TODO Auto-generated method stub
		this.errorOccured = true;
	}

	public void fatalError(SAXParseException arg0) throws SAXException {
		// TODO Auto-generated method stub
		this.errorOccured = true;
	}

	public void warning(SAXParseException arg0) throws SAXException {
		// TODO Auto-generated method stub
		this.errorOccured = true;
	}
	
	public boolean hasError(){
		return this.errorOccured;
	}

}