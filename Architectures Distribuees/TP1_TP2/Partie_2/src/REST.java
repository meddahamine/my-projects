
import java.io.StringReader;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.*;
import javax.xml.ws.http.HTTPBinding;

@WebServiceProvider
@ServiceMode(value = Service.Mode.PAYLOAD)

public class REST implements Provider<Source>{
	/*La variable "message" va nous servir � cr�er le "StreamSource" qui
	 * correspond au service demand�*/
	private String message;
	
	public REST(String m){
		this.message = m;
	}
	
	@Override
	public Source invoke(Source request) {
		StreamSource reply = null;
		if(this.message=="hello"){
			String replyElement = new String("<p>hello world</p>");
			reply = new StreamSource(new StringReader(replyElement));
		}
		
		if(this.message=="rouen"){
			String replyElement = new String("<p>Universit� de Rouen</p>");
			reply = new StreamSource(new StringReader(replyElement));
		}
		
		if(this.message=="rest"){
			String replyElement = new String("<p>R�pose du service REST</p>");
			reply = new StreamSource(new StringReader(replyElement));
		}
		
		return reply;
	}
	
	public static void main(String[] args) {
		/*Cr�ation d'un Endpoint pour le service "hello"*/
		Endpoint e = Endpoint.create(HTTPBinding.HTTP_BINDING, new REST("hello"));
		e.publish("http://127.0.0.1:8080/hello/world");
		
		/*Cr�ation d'un nouveau Endpoint pour le service "rouen"*/
		e = Endpoint.create(HTTPBinding.HTTP_BINDING, new REST("rouen"));
		e.publish("http://127.0.0.1:8090/test");
		
		/*Cr�ation d'un nouveau Endpoint pour le service "rest"*/
		e = Endpoint.create(HTTPBinding.HTTP_BINDING, new REST("rest"));
		e.publish("http://127.0.0.1:8084/hello/world");
	}
}
