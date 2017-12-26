package net.tp.spring.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Response")
public class Response {

	@XmlElement
	String Error;

	@XmlElement
	String success;

	@XmlElement
	String Num;
	
	public Response(){
		
	}
	public Response(String error, String msgSuccess, String num) {
		super();
		Error = error;
		success = msgSuccess;
		Num = num;
	}
	
	/*
	@XmlRootElement(name = "Success")
	class Success {

		@XmlElement
		String Message;

		@XmlElement
		String Num;

		public Success(){
			
		}
		public Success(String message, String num) {
			super();
			Message = message;
			Num = num;
		}
	}*/
}
