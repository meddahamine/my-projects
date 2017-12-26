package net.codejava.spring.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@Controller
public class TransactionController {


	
	@RequestMapping(value="/detail", method = RequestMethod.GET)
	public ModelAndView transactionDetails(ModelAndView model) throws IOException, SAXException, ParserConfigurationException{
        
		model.addObject("listTransactions", getRequest("https://sepabank.herokuapp.com/detail"));
		model.setViewName("transactionDetail");
		
		return model;
	}
	
	@RequestMapping(value="/resume", method = RequestMethod.GET)
	public ModelAndView transactionResume(ModelAndView model) throws IOException, SAXException, ParserConfigurationException{
        
		model.addObject("listTransactions", getRequest("https://sepabank.herokuapp.com/resume"));
		model.setViewName("transactionResume");
		
		return model;
	}
	
	@RequestMapping(value="/stats", method = RequestMethod.GET)
	public ModelAndView transactionStats(ModelAndView model) throws IOException, SAXException, ParserConfigurationException{
        
		model.addObject("statistiques", getRequest("https://sepabank.herokuapp.com/stats"));
		model.setViewName("transactionStats");
		
		return model;
	}
	
	@RequestMapping(value="/search", method = RequestMethod.GET)
	public ModelAndView searchTransaction( ModelAndView model){
		
		model.setViewName("searchTransaction");
		
		return model;
	}
	
	@RequestMapping(value="/getsearch", method = RequestMethod.GET)
	public ModelAndView searchTransaction(HttpServletRequest request, ModelAndView model) throws IOException, SAXException, ParserConfigurationException{
		String id = new String(request.getParameter("identifiant"));
		
		Document d = getRequest("https://sepabank.herokuapp.com/trx/"+id.replace(" ", "%20"));
		
		if(d!=null){
			model.addObject("transaction",d);
		}else{
			model.addObject("error","Cet identifiant n'existe pas");
		}
		
		
		model.setViewName("searchTransaction");
		
		return model;
	}
	
	@RequestMapping(value="/depot", method = RequestMethod.GET)
	public ModelAndView depotTransaction(ModelAndView model) throws IOException{
		
		model.setViewName("depotTransaction");
		
		return model;
	}
	
	@RequestMapping(value="/addtrx", method = RequestMethod.POST)
	public ModelAndView addTransaction(HttpServletRequest request, ModelAndView model) throws IOException, SAXException, ParserConfigurationException{
		String trx = new String("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
								+"<DrctDbtTxInf>"+
								"<PmtId>"+request.getParameter("PmtId")+"</PmtId>"+
								"<InstdAmt>"+request.getParameter("InstdAmt")+"</InstdAmt>"+
								"<DrctDbtTx>"+
									"<MndtRltdInf>"+
										"<MndtId>"+request.getParameter("MndtId")+"</MndtId>"+
										"<DtOfSgntr>"+request.getParameter("DtOfSgntr")+"</DtOfSgntr>"+
									"</MndtRltdInf>"+
								"</DrctDbtTx>"+
								"<DbtrAgt>"+
									"<FinInstnId>"+
										"<BIC>"+request.getParameter("BIC")+"</BIC>"+
									"</FinInstnId>"+
								"</DbtrAgt>"+
								"<Dbtr>"+
									"<Nm>"+request.getParameter("Nm")+"</Nm>"+
								"</Dbtr>"+
								"<DbtrAcct>"+
									"<Id>"+
										"<IBAN>"+request.getParameter("IBAN")+"</IBAN>"+
									"</Id>"+
								"</DbtrAcct>"+
								"<RmtInf>"+request.getParameter("RmtInf")+"</RmtInf>"+
							"</DrctDbtTxInf>");
		
		Document d = postRequest("https://sepabank.herokuapp.com/depot/", trx);
		if(d!=null){
			model.addObject("response", d);
		}else{
			model.addObject("error","Valeurs non valides");
		}
		
		model.setViewName("depotTransaction");
		
		return model;
	}
	
	@RequestMapping(value = "/addXmlFile", method = RequestMethod.POST)
	public ModelAndView uploadFileHandler(@RequestParam("file") MultipartFile file, ModelAndView model) {
		try {
			byte[] bytes = file.getBytes();
			String trx = new String(bytes, StandardCharsets.UTF_8);
			
			Document d = postRequest("https://sepabank.herokuapp.com/depot/", trx);
			if(d!=null){
				model.addObject("response", d);
			}else{
				model.addObject("error","Valeurs non valides");
			}
				
		} catch (Exception e) {
			model.addObject("error", "You failed to upload  => " + e.getMessage());
		}
		
		model.setViewName("depotTransaction");
		
		return model;
	}
	
	public Document getRequest(String url) throws IOException, SAXException, ParserConfigurationException{
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		if(response.toString().contains("<?xml version=\"1.0\"")){
	        return (Document) DocumentBuilderFactory.newInstance().newDocumentBuilder()
		            .parse(new InputSource(new StringReader(response.toString())));
		}
		return null;
	}
	
	public Document postRequest(String url, String xml) throws SAXException, ParserConfigurationException{
		try{
	        URL obj = new URL(url);
	        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	        con.setRequestMethod("POST");
	        con.setRequestProperty("Content-Type",
	                "application/xml;charset=utf-8");
	        String urlParameters = xml;
	        con.setDoOutput(true);
	        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	        wr.writeBytes(urlParameters);
	        wr.flush();
	        wr.close();
	        String responseStatus = con.getResponseMessage();
	
	        BufferedReader in = new BufferedReader(new InputStreamReader(
	                con.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();
	        while ((inputLine = in.readLine()) != null) {
	            response.append(inputLine);
	        }
	        in.close();
	        return (Document) DocumentBuilderFactory.newInstance().newDocumentBuilder()
		            .parse(new InputSource(new StringReader(response.toString())));
		}catch(IOException e){
			return null;
		}
	}
}
