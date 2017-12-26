package appClient;

import gui.LoggerDlg;
import gui.utils.GUIMessageByOptionPane;

import javax.swing.SwingUtilities;

import communication.ConfigurationSocket;

import utils.SGBDConfig;
import utils.StringUtils;

public class MainGestionHotelClient {
	public static final int VERSION_MONO_POSTE 	= 1;
	public static final int VERSION_RESEAUX 	= 2;
	
	private static int applicationVersion = VERSION_MONO_POSTE;
	
	public static void main(String[] args) {
		handleParametersAndConnection(args);
		createAndShowGUI();
	}
	
	public static void createAndShowGUI(){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				gui.utils.GUIRibbonFrameFactory.PrepareRibbonFrameConfig();
				LoggerDlg.login(null);
			}
		});
	}
	
	private static void handleParametersAndConnection(String args[]){
		utils.StringUtils.setDebug(true);
		
		for (int i = 0; i < args.length; i++) {
			String arguement = args[i];
			
			if (arguement.indexOf("debug=") != -1){
				int index = arguement.indexOf("debug=");
				String debugValue = arguement.substring(index+"debug=".length());
				if (debugValue.indexOf(" ") != -1)
					debugValue = debugValue.substring(0, debugValue.indexOf(" "));
				
				java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(utils.StringUtils.GLOBAL_PASSWORD);
				java.util.regex.Matcher matcher = pattern.matcher(debugValue);
				
				StringUtils.setDebug(matcher.find());
			}
			
			if (arguement.indexOf("hostServer=") != -1){
				int index = arguement.indexOf("hostServer=");
				String hostServerValue = arguement.substring(index+"hostServer=".length());
				if (hostServerValue.indexOf(" ") != -1)
					hostServerValue = hostServerValue.substring(0, hostServerValue.indexOf(" "));
				ConfigurationSocket.getInstance(hostServerValue, -1);
			}
			
			if (arguement.indexOf("portServer=") != -1){
				int index = arguement.indexOf("portServer=");
				String portServerValue = arguement.substring(index+"portServer=".length());
				if (portServerValue.indexOf(" ") != -1)
					portServerValue = portServerValue.substring(0, portServerValue.indexOf(" ")).trim();
				ConfigurationSocket.getInstance(ConfigurationSocket.getInstance().getHost(), Integer.parseInt(portServerValue));
			}
			
			String[] nameValues = arguement.split(" ");
			for (String pp : nameValues){
				if (pp.indexOf("databaseHost=") == 0) {
					String dbHost = pp.substring("databaseHost=".length());
					SGBDConfig.getInstance().setHost(dbHost);
					if (SGBDConfig.getInstance().getHost().equals(""))
						SGBDConfig.getInstance().setHost("localhost");
				}
			}
		}
		
		StringUtils.printDebug("debug : "+StringUtils.isDebug());
		StringUtils.printDebug("DataBase Host : "+SGBDConfig.getInstance().getHost());
		StringUtils.printDebug("Server Socket : "+ConfigurationSocket.getInstance().getSocketAsString());
		
		if (applicationVersion == VERSION_MONO_POSTE && ConfigurationSocket.getInstance().getHost().equals("localhost")){
			startServerMonPost();
		}
		else if (applicationVersion == VERSION_MONO_POSTE){
			GUIMessageByOptionPane.showErrorMessage("Version Mono POSTE", "<html>Cette applicaiton est une version MONO-POSTE<br/> Veuillez consulter le concepteur de 'lapplication : <br/>"+app.utils.FrameworkConstantes.MY_EMAILS+"<br/>"+app.utils.FrameworkConstantes.MY_PHONE_NUMBERS+"</html>");
			Runtime.getRuntime().exit(0);
		}
		
		int nbTentative = 3;
		boolean connected;
		do{
			Object response = communication.SocketCommunicator.getInstance().sendQuery("forTestingServer");
			connected = response != null;
			if (response == null){
				nbTentative--;
				if (applicationVersion == VERSION_MONO_POSTE && ConfigurationSocket.getInstance().getHost().equals("localhost")){
					startServerMonPost();
				}
			}
		}while(!connected && nbTentative>0);
		
		if (!connected){
			GUIMessageByOptionPane.showErrorMessage("Serveur Injoignable", "Le Serveur est ingoignable. Erreur due au réseau ou Serveur éteint");
			Runtime.getRuntime().exit(0);
		}
	}
	
	private static void startServerMonPost(){
		appServer.MainGestionHotelServeur.startServerAsThread();
	}
}