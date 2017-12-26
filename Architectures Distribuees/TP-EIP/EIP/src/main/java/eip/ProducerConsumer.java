package eip;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.ValueBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.log4j.BasicConfigurator;

public class ProducerConsumer {

	// Contexte Camel par d�faut
	private CamelContext context;
	private RouteBuilder routeBuilder;
	// Producteur
	private ProducerTemplate pt;
	
	public ProducerConsumer() throws Exception{
		// Configure le logger par d�faut
		BasicConfigurator.configure();

		context = new DefaultCamelContext();
		pt = context.createProducerTemplate();
		
		/************* Cr�e une route contenant le consommateur 1 *************/
		routeBuilder = new RouteBuilder() {
			@Override
			public void configure() throws Exception {
			// On d�finit un consommateur 'consumer-1'
			// qui va �crire le message
			from("direct:consumer-1").to("log:affiche-1-log");
		}
		};
		
		// On ajoute la route au contexte
		routeBuilder.addRoutesToCamelContext(context);
		
		/************* Cr�e une route contenant le consommateur 2 *************/
		routeBuilder = new RouteBuilder() {
			@Override
			public void configure() throws Exception {
			// On d�finit un consommateur 'consumer-2'
			// qui va �crire le message
			from("direct:consumer-2").to("file:messages");
		}
		};
		
		// On ajoute la route au contexte
		routeBuilder.addRoutesToCamelContext(context);
		
		/************* Cr�e une route contenant le consommateur All *************/
		routeBuilder = new RouteBuilder() {
			@Override
			public void configure() throws Exception {
			// On d�finit un consommateur 'consumer-all'
			// qui va �crire le message
			from("direct:consumer-all").choice()
				.when(header("destinataire").contains("�crire"))
				.to("direct:consumer-2")
				.otherwise()
				.to("direct:consumer-1");
		}
		};
		
		routeBuilder.addRoutesToCamelContext(context);
		
		// On d�marre le contexte pour activer les routes
		context.start();
	}
	
	//Fonction qui cr�e un Producteur pour le Consomateur 1
	public void producerConsumer1(String msg) throws Exception{
		
		// qui envoie un message au consommateur 'consumer-1'
		pt.sendBody("direct:consumer-1", msg);
	}
	
	//Fonction qui cr�e un Producteur pour le Consomateur 2
	public void producerConsumer2(String msg) throws Exception{
			
			// qui envoie un message au consommateur 'consumer-2'
			pt.sendBody("direct:consumer-2", msg);
		}
		
	//Fonction qui cr�e un Producteur pour le Consomateur All
		public void producerConsumerAll(String msg, String header) throws Exception{
			
			// qui envoie un message au consommateur 'consumer-all'
			if(msg.substring(0, 1).equals("w")){
				header = "�crire";
			}
			
			Map<String, Object> headers= new HashMap<String, Object>();
			headers.put("destinataire", header);
			
			pt.sendBodyAndHeaders("direct:consumer-all", msg,headers);
		}

		//Fonction qui cr�e un Consomateur get Animal by Name et un Producteur
		public void producerConsumerNameAnimal(final String name) throws Exception{
			
			// Cr�e une route contenant le consommateur 
			routeBuilder = new RouteBuilder() {
				@Override
				public void configure() throws Exception {
				// On d�finit un consommateur 'animal-name'
				// qui va �crire le message
				from("direct:animal-name")
					.setHeader(Exchange.HTTP_METHOD,constant("GET"))
					.to("http://localhost:8085/rest-service/animals/find/byName/"+name)
					.log("reponse received : ${body}");
			}
			};
			
			// On ajoute la route au contexte
			routeBuilder.addRoutesToCamelContext(context);
			
			pt.sendBody("direct:animal-name","");
		}

		//Fonction qui cr�e un Consomateur get Center Position of 
		//Animal by Name et un Producteur
		public void producerConsumerPositionCenter(final String name) throws Exception{
			
			// Cr�e une route contenant le consommateur 
			routeBuilder = new RouteBuilder() {
				@Override
				public void configure() throws Exception {
				// On d�finit un consommateur 'center-position'
				// qui va �crire le message
				from("direct:center-position")
					.setHeader(Exchange.HTTP_METHOD,constant("GET"))
					.to("http://localhost:8085/rest-service/center/"+name)
					.log("reponse received : ${body}");
			}
			};
			
			// On ajoute la route au contexte
			routeBuilder.addRoutesToCamelContext(context);
			
			pt.sendBody("direct:center-position","");
		}

		//Fonction qui cr�e un Consomateur get Animals de plusieurs serveurs
		public void producerConsumerAnimalsServers() throws Exception{
			
			// Cr�e une route contenant le consommateur 
			routeBuilder = new RouteBuilder() {
				@Override
				public void configure() throws Exception {
				// On d�finit un consommateur 'animals-servers'
				// qui va �crire le message
				from("direct:animals-servers")
					.setHeader(Exchange.HTTP_METHOD,constant("GET"))
					.to("http://localhost:8085/rest-service/animals/",
							"http://localhost:8080/rest-service/animals/",
							"http://localhost:8090/rest-service/animals/")
					.log("reponse received : ${body}");
			}
			};
			
			// On ajoute la route au contexte
			routeBuilder.addRoutesToCamelContext(context);
			
			pt.sendBody("direct:animals-servers","");
		}
		
	public static void main(String[] args) throws Exception {
		ProducerConsumer producerConsumer = new ProducerConsumer();
		String message="", head="";
		Scanner reader = new Scanner(System.in);
		
		/*while(!message.equals("exit")){
			System.out.println("Entrez un message : ");
			message = reader.nextLine();
			producerConsumer.producerConsumer1(message);
		}
		
		while(!message.equals("exit")){
			System.out.println("Entrez une ent�te : ");
			head = reader.nextLine();
			System.out.println("Entrez un message : ");
			message = reader.nextLine();
			producerConsumer.producerConsumerAll(message, head);
		}*/
		
		//producerConsumer.producerConsumerNameAnimal("Tac");
		producerConsumer.producerConsumerPositionCenter("Tac");
		//producerConsumer.producerConsumerAnimalsServers();
	}
}