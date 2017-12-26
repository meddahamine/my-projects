package appClient;

import java.awt.Color;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import app.utils.FrameworkConstantes;
import utils.StringUtils;
import models.beans.ParametresApplication;
import models.beans.ParametresOrganisme;
import models.beans.Utilisateur;

import models.daos.client.DAOParametresApplication;
import models.daos.client.DAOParametresOrganisme;

/**
 * 
 * @author OUZEGGANE Redouane
 * <br/>Cette classe permet de garder dans le cache du client quelque données nécessaires sans passer par le serveur
 */
public abstract class Spool{
	private static Utilisateur 				user = null;
	private static ParametresOrganisme		parametresOrganisme = null;
	private static ParametresApplication 		parametresApplication = null;
	
	private static boolean yetConnected = false;
	
	public static Color BG_COLOR_BTN = null;
	
	public static final String				CODE = ".*((redouane)+.*(Redouane).*(nacéra)+.*(Nacéra).*(loves)+.*).*";
	
	public static File				fileDebugLog = null;
	
	private static String dateDuJour = "0000-00-00";
	
	public static void initNbreOfServerConnections(){
		try{
			if (!FrameworkConstantes.FILE_NUMBER_OF_CONNECTIONS.exists()){
				FrameworkConstantes.FILE_NUMBER_OF_CONNECTIONS.createNewFile();
			}
			
			utils.FilesAndLaunchUtils.saveObjectInFile(new Integer(0), FrameworkConstantes.FILE_NUMBER_OF_CONNECTIONS);
		}
		catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
	}
	
	public static int getNbreOfServerConnections(){
		try{
			if (!FrameworkConstantes.FILE_NUMBER_OF_CONNECTIONS.exists()){
				FrameworkConstantes.FILE_NUMBER_OF_CONNECTIONS.createNewFile();
				utils.FilesAndLaunchUtils.saveObjectInFile(new Integer(0), FrameworkConstantes.FILE_NUMBER_OF_CONNECTIONS);
			}
			
			Integer nbre = (Integer)utils.FilesAndLaunchUtils.readObjectInFile(FrameworkConstantes.FILE_NUMBER_OF_CONNECTIONS);
			return nbre;
		}
		catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
		return 0;
	}
	
	public static int decrementNbreOfServerConnections(){
		int nbreConnections = getNbreOfServerConnections();
		nbreConnections = Math.max(nbreConnections-1, 0);
		utils.FilesAndLaunchUtils.saveObjectInFile(new Integer(nbreConnections), FrameworkConstantes.FILE_NUMBER_OF_CONNECTIONS);
		
		return nbreConnections;
	}
	
	public static boolean isYetConnected() {
		return yetConnected;
	}
	
	public static void setYetConnected(boolean yetConnected) {
		Spool.yetConnected = yetConnected;
	}
	
	public static Utilisateur getUser() {
		return user;
	}
	
	public static void setUser(Utilisateur user) {
		Spool.user = user;
	}
	
	public static ParametresApplication getParametresApplication() {
		if (parametresApplication == null){
			parametresApplication = DAOParametresApplication.getParametresApplication(1);
		}
		return parametresApplication;
	}
	
	public static ParametresApplication getParametresApplication(boolean update) {
		if (update)
			parametresApplication = null;
		return getParametresApplication();
	}

	public static void setParametresApplication(ParametresApplication parametresApplication) {
		Spool.parametresApplication = parametresApplication;
	}
	
	public static void setParametresOrganisme(ParametresOrganisme parametresOrganisme) {
		Spool.parametresOrganisme = parametresOrganisme;
	}
	
	public static ParametresOrganisme getParametresOrganisme() {
		if (parametresOrganisme == null){
			parametresOrganisme = DAOParametresOrganisme.getParametresOrganisme(1);
		}
		return parametresOrganisme;
	}
	
	public static ParametresOrganisme getParametresOrganisme(boolean update) {
		if (update){
			parametresOrganisme = null;
		}
		return getParametresOrganisme();
	}
	
	public static boolean isMonoVersion(){
		return false;//; getParametresApplication().isMonoVersion();
	}
	
	public static boolean isServerVersion(){
		return true;//getParametresApplication().isServerVersion();
	}
	
	public static String getAutoSaveDir(){
		if (!FrameworkConstantes.FILE_MY_ALL_APPLICATIONS.exists()){
			FrameworkConstantes.FILE_MY_ALL_APPLICATIONS.mkdir();
		}
		if (!FrameworkConstantes.FILE_MY_ALL_APPLICATIONS_AUTOSAVE_DIR.exists()){
			FrameworkConstantes.FILE_MY_ALL_APPLICATIONS_AUTOSAVE_DIR.mkdir();
		}
		
		ParametresApplication pa = getParametresApplication();
		
		return FrameworkConstantes.FILE_MY_ALL_APPLICATIONS_AUTOSAVE_DIR.getAbsolutePath()+"/"+pa.getDesignation()+"/";
	}
	
	public static File getFileDebugLog(){
		if (parametresApplication == null){
			return null;
		}
		
		if (fileDebugLog == null){
			fileDebugLog = new File(getAutoSaveDir()+"/.debug"+getDateTimeFromServer()+".log");
			if (!fileDebugLog.exists()){
				try{
					fileDebugLog.createNewFile();
				}
				catch (Exception e){
				}
			}
		}
		
		return fileDebugLog;
	}
	
	public static void getDateDuJourServeur() {
		dateDuJour = StringUtils.normalizeDateMySQL((String) communication.SocketCommunicator.getInstance().sendQuery("getDateFromServer"));
	}

	public static String getDateTimeFromServer() {
		return (String) communication.SocketCommunicator.getInstance().sendQuery("getDateTimeFromServer");
	}

	public static String getDateDuJour() {
		return dateDuJour;
	}
	
	/**
	 * 
	 * @author OUZEGGANE Redouane
	 *Cette classe represnte les données exportées et qui sont prêtes à être importées
	 */
	public static class DataExport implements Serializable{
		private static final long serialVersionUID = 1L;
		
		private boolean					autoSaveData = true;
		
		public DataExport(){
		}
		
		public void setAutoSaveData(boolean autoSaveData) {
			this.autoSaveData = autoSaveData;
		}
		
		public boolean isAutoSaveData() {
			return autoSaveData;
		}
		
		public void addListDataObject(List<?> listDataObject){
			for (Object dataObject : listDataObject){
				this.addDataObject(dataObject);
			}
		}
		
		public void addDataObject(Object dataObject){
			
		}
		
		public List<Object> importData(){
			List<Object> dataNonImported = new ArrayList<Object>();
			return dataNonImported;
		}
	}
}