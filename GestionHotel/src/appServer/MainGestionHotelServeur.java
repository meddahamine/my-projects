package appServer;

import java.net.ServerSocket;
import java.net.Socket;

import communication.ConfigurationSocket;

import utils.StringUtils;

public class MainGestionHotelServeur {
	private static boolean serverThreadLunched = true;
	public static final String DATA_BASE_DIR = "./DataBase/";
	
	public static void main(String args[]){
		try {
			utils.StringUtils.setDebug(true);
			
			utils.SGBDConfig.getInstance("localhost", "3306", "gestionHotel", "gestHotelUser", "gestHotelUser");
			ConfigurationSocket cs = ConfigurationSocket.getInstance();
			
			ServerSocket server = null;
			int numPort = cs.getPort();
			
			do{
				try{
					server = new ServerSocket(numPort);
					cs.setPort(numPort);
				}
				catch (Exception e){
					if (args.length == 1 && args[0].equals("MONO-POST")){
						numPort++;
					}
					else{
						StringUtils.printDebug("Error of Server ...");
						return;
					}
				}
			}while (server == null);
			
			if (utils.FilesAndLaunchUtils.isWindows()){
				utils.SGBDConfig.getInstance().setPathBinMySQL("");
			}
			
			while (true){
				StringUtils.printDebug ("\nServer Says - Waiting for request on : " + cs.getSocketAsString());
				Socket	socket = server.accept();
				SocketReader socketReader = new SocketReader(socket);
				socketReader.start();
			}
		}catch (Exception e){
			StringUtils.printDebug("Server Says (Exception) - ");
			StringUtils.printDebug(e);
		}
	}
	
	public static boolean startServerAsThread(){
		Thread serverThread = new Thread(new Runnable() {
			public void run() {
				try {
					serverThreadLunched = true;
					String[] args = {"MONO-POST"};
					main(args);
				}catch (Exception e){
					serverThreadLunched = false;
					StringUtils.printDebug("Server Says (Exception) : ");
					StringUtils.printDebug(e);
				}
			}
		});
		
		serverThread.start();
		return serverThreadLunched;
	}
}