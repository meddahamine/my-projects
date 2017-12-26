package gui;

import gui.crud.framework.AutoSaveManagement;
import gui.crud.framework.ConnexionsLogManagement;
import gui.crud.framework.DlgSauvegardeRestaurationSelectiveData;
import gui.utils.GUIButtonFactory;
import gui.utils.GUIDate;
import gui.utils.GUIMessageByOptionPane;
import gui.utils.GUIPanelFactory;
import gui.utils.JDesktopPaneModified;
import gui.utils.JInternalFrameManagementModel;
import gui.utils.GUIButtonFactory.GradientCircularButton;
import gui.utils.GUIButtonFactory.GradientRoundRectButton;
import gui.utils.GUIButtonFactory.TowStateButton;
import gui.utils.GUIPanelFactory.BusyLayer;
import gui.utils.GUIPanelFactory.MyJLabel;
import gui.utils.GUIPanelFactory.BusyLayer.RunningClassMethod;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Paint;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRootPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.border.BevelBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import org.jvnet.substance.SubstanceLegacyDefaultLookAndFeel;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.SubstanceAutumnLookAndFeel;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;
import org.jvnet.substance.skin.SubstanceBusinessBlueSteelLookAndFeel;
import org.jvnet.substance.skin.SubstanceBusinessLookAndFeel;
import org.jvnet.substance.skin.SubstanceChallengerDeepLookAndFeel;
import org.jvnet.substance.skin.SubstanceCremeCoffeeLookAndFeel;
import org.jvnet.substance.skin.SubstanceCremeLookAndFeel;
import org.jvnet.substance.skin.SubstanceDustCoffeeLookAndFeel;
import org.jvnet.substance.skin.SubstanceDustLookAndFeel;
import org.jvnet.substance.skin.SubstanceEmeraldDuskLookAndFeel;
import org.jvnet.substance.skin.SubstanceMagmaLookAndFeel;
import org.jvnet.substance.skin.SubstanceMistAquaLookAndFeel;
import org.jvnet.substance.skin.SubstanceMistSilverLookAndFeel;
import org.jvnet.substance.skin.SubstanceModerateLookAndFeel;
import org.jvnet.substance.skin.SubstanceNebulaBrickWallLookAndFeel;
import org.jvnet.substance.skin.SubstanceNebulaLookAndFeel;
import org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel;
import org.jvnet.substance.skin.SubstanceOfficeSilver2007LookAndFeel;
import org.jvnet.substance.skin.SubstanceRavenGraphiteGlassLookAndFeel;
import org.jvnet.substance.skin.SubstanceRavenGraphiteLookAndFeel;
import org.jvnet.substance.skin.SubstanceRavenLookAndFeel;
import org.jvnet.substance.skin.SubstanceSaharaLookAndFeel;
import org.jvnet.substance.skin.SubstanceTwilightLookAndFeel;

import app.utils.FrameworkConstantes;
import appClient.Spool;
import models.beans.Lang;
import models.beans.ParametresApplicationUtilisateur;
import models.beans.ParametresOrganisme;
import models.beans.Role;
import utils.FontUtils;
import utils.SGBDConfig;
import utils.StringUtils;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private static MainFrame instance = null;
	
	public static final String DEFAULT_LOOK_AND_FEEL = javax.swing.UIManager.getCrossPlatformLookAndFeelClassName();
	
	private JDesktopPaneModified	desktopPane;
	
	private javax.swing.JMenuBar	menuBar;
	
	private JCheckBoxMenuItem 		miShowHideToolBar;
	private JMenuItem 				miMyProfils;

	private javax.swing.JMenu		mParametrage;
	private javax.swing.JMenuItem	miParametresOrganismeManagement, miRoleManagement, miUtilisateurManagement,miClientManagement,
	miChambreMangement,miFactureManagement,miServiceManagement,miCategorieChambreManagement,miConsommationManagement,miReservationManagement ;
	private javax.swing.JMenuItem	miParametresApplicaiton;
	private javax.swing.JSeparator	sepMiParametresApplicaiton;
	private javax.swing.JMenuItem	miLock, miLogout, miQuit;
	
	private javax.swing.JMenu		mEditions;
	private javax.swing.JMenuItem   miCouper,miCopier,miSupprimer  ;
	private javax.swing.JMenu		mDonnees;
	private javax.swing.JMenuItem	miSauvegardeSelective, miRestaurationSelective, miSaveData, miRestaureData, miAutoSaveData;

	private javax.swing.JMenu				mWindows;
	private javax.swing.JMenuItem			miIconifyAll, miRestaureAll, miCloseAll;
	private javax.swing.JCheckBoxMenuItem	miCascade, miMosaique, miWithoutLayout;
	private javax.swing.JMenu				mWindowsThemes;
	
//	private javax.swing.JCheckBoxMenuItem	miThemeCrossPlatform, miThemeSystem, miThemeNimbus, miThemeMotif;
//	private javax.swing.JCheckBoxMenuItem	miThemeEaSynth, miThemeIDW, miThemeNIMODLF, miThemeSmoothMetal;
//	private javax.swing.JCheckBoxMenuItem	miThemeTonic, miThemeTonicSlim, miThemeWebLook;
	
	private javax.swing.JMenu				mThemeSubstance;
	
	private List<JCheckBoxMenuItem>			listThemesMenuItems = new ArrayList<JCheckBoxMenuItem>();
	
	private Map<JInternalFrame, JRadioButtonMenuItem> mapRBMIFrames = new HashMap<JInternalFrame, JRadioButtonMenuItem>();
	private ButtonGroup						bgRadioButtonMenuItemFrames;
	private ActionListener					alRadioButtonMenuItemFrames;
	private ComponentListener				clInternalFramesVisibilty;
	private InternalFrameListener 			iflInternalFramesToFront;
	
	private Map<JInternalFrame, TowStateButton>		mapTSBFrames = new LinkedHashMap<JInternalFrame, TowStateButton>();
	
	private javax.swing.JMenu		mHelp;
	private javax.swing.JMenuItem	miHelpDocumentation, miHelpAbout;

	private javax.swing.JToolBar	toolBar;
	
	private JPanel					pTBPanelDateHeure;
	private MyJLabel				lTBlabelJour;
	private MyJLabel				lTBlabelHeure;
	
	private javax.swing.JButton 	bTBParametresOrganisme, bTBRole, bTBUtilisateur,bTBClient,bTBChambre,bTBFacture,bTBService,bTBCategorieChambre,bTBConsommation,bTBReservation;
	
	private javax.swing.JButton		bTBSauvegardeSelective, bTBRestaurationSelective, bTBSaveData, bTBRestaureData, bTBAutoSaveData;
	
	private javax.swing.JButton		bTBLock, bTBLogout, bTBQuit, bTBLang;
	private javax.swing.JButton		bTBCouper, bTBCopier, bTBSuppimer;
	
	private javax.swing.JPopupMenu				popupMenuLangs;
	private Map<javax.swing.JMenuItem, Lang> 	mapMenuItemsLangs;
	private ActionListener						alMILang = null;

	private javax.swing.JTabbedPane	tabbedPaneToolBar;
	private javax.swing.JToolBar	tbParameters, tbGestion, tbEditions, tbDonnees;

	private javax.swing.JPanel		pStateBar;
	
	private javax.swing.JLabel		lAlerts	, lAlerts2;
	private javax.swing.JLabel		lAllRightsReserved;
	
	private JPopupMenu				popupMenuEdition;
	
	private File 					docFile = null;

	private static ParametresOrganisme parametreOrganisme = null;
	private static JLabel lOrganisme = null;
	private static JLabel lTitleApplication = null;
	private static JLabel lBackgroundImage = null;
	private static JPanel	pLinearGradient = null;
	private static MyJLabel lDayTime = null;
	private static long	  timeMiliSeconds;
	private static JSeparator separator1, separator2 = null;
	
	private static JButton		bSessions = null;
	
	@SuppressWarnings("unused")
	private String codeGUIActivation = "";
	private Timer timerCodeCommercial = null;
	
	private Timer timerAutoSave = null;
	
	public static MainFrame getInstance() {
		if (instance == null){
			instance = new MainFrame(LoggerDlg.getInstanceWithoutCreation().getCompleteTitle());
			if (Spool.getParametresApplication().getPhoto() != null){
				instance.setIconImage(Spool.getParametresApplication().getPhoto().getImageIcon().getImage());
			}
		}
		
		return instance;
	}
	
	public static MainFrame getInstanceWithoutCreation() {
		return instance;
	}
	
	public static boolean isCreated(){
		return (instance != null);
	}
	
	private void initAllPrivileges(boolean allPrivileges){
		bTBParametresOrganisme.setEnabled(allPrivileges);
		miParametresOrganismeManagement.setEnabled(allPrivileges);
		
		bTBRole.setEnabled(allPrivileges);
		miRoleManagement.setEnabled(allPrivileges);
		
		bTBUtilisateur.setEnabled(allPrivileges);
		miUtilisateurManagement.setEnabled(allPrivileges);
		
		bTBChambre.setEnabled(allPrivileges);
		miChambreMangement.setEnabled(allPrivileges);
		
		
//		bArticle.setEnabled(allPrivileges);
//		
//		bFicheStock.setEnabled(allPrivileges);
//		
//		bBenifices.setEnabled(allPrivileges);
//		
//		bInventaire.setEnabled(allPrivileges);
//		
//		bCommandeFournisseur.setEnabled(allPrivileges);
//		
//		bCommandeClient.setEnabled(allPrivileges);
//		
//		bAchats.setEnabled(allPrivileges);
//		
//		bVentes.setEnabled(allPrivileges);
		
		bTBSauvegardeSelective.setEnabled(allPrivileges);
		miSauvegardeSelective.setEnabled(allPrivileges);
		
		bTBRestaurationSelective.setEnabled(allPrivileges);
		miRestaurationSelective.setEnabled(allPrivileges);
		
		bTBSaveData.setEnabled(allPrivileges);
		miSaveData.setEnabled(allPrivileges);
		
		bTBAutoSaveData.setEnabled(allPrivileges);
		miAutoSaveData.setEnabled(allPrivileges);
		
		bTBRestaureData.setEnabled(allPrivileges);
		miRestaureData.setEnabled(allPrivileges);
	}
	
	private boolean initGUIWithRole(){
		initAllPrivileges(true);
		
		Role role = Spool.getUser().getRole();
		if (role == null){
			return false;
		}
		
		bTBParametresOrganisme.setEnabled(role.isParametresOrganisme());
		miParametresOrganismeManagement.setEnabled(role.isParametresOrganisme());
		
		bTBRole.setEnabled(role.isGestionRole());
		miRoleManagement.setEnabled(role.isGestionRole());
		
		bTBUtilisateur.setEnabled(role.isGestionUtilisateur());
		miUtilisateurManagement.setEnabled(role.isGestionUtilisateur());
		
		bTBUtilisateur.setEnabled(role.isGestionUtilisateur());
		miUtilisateurManagement.setEnabled(role.isGestionUtilisateur());
		
		
		bTBChambre.setEnabled(role.isGestionUtilisateur());
		miChambreMangement.setEnabled(role.isGestionUtilisateur());
		return true;
	}
	
	public void initGUIWithApplicationParameters(){
		miParametresApplicaiton.setVisible(false);
		sepMiParametresApplicaiton.setVisible(miParametresApplicaiton.isVisible());
	}
	
	public void updateGUIWithUserParameters(){
		ParametresApplicationUtilisateur userParameters = Spool.getUser().getParametres();
		
		for (JCheckBoxMenuItem chbMI : this.listThemesMenuItems){
			if (chbMI.getName().equals(userParameters.getLookAndFeel())){
				chbMI.setSelected(true);
				break;
			}
		}
		
		try{
			UIManager.setLookAndFeel(userParameters.getLookAndFeel());
		}
		catch (Exception e){
		}
		
		this.miShowHideToolBar.setSelected(userParameters.isVisibilityOfMainToolBar());
		this.toolBar.setVisible(userParameters.isVisibilityOfMainToolBar());
		
		JInternalFrameManagementModel.setTimeOfNotificaiton(userParameters.getPeriodeNotification()*1000);
		JInternalFrameManagementModel.setVisibilityNotificationBar(userParameters.isVisibilityOfNotification());
		JInternalFrameManagementModel.updateVisibilityNotificationBarOfDesktopPane(this.getDesktopPane());
		
		this.getDesktopPane();
	}
	
//	private static void updateEvents(boolean toShow){
//		if (instance == null){
//			return;
//		}
//		
//		MainFrame.getInstanceWithoutCreation().lAlerts.setVisible(false);
//		MainFrame.getInstanceWithoutCreation().lAlerts2.setVisible(false);
//		if (JFNotification.isCreated()){
//			JFNotification.getInstanceWihtoutCreation().clearEvents();
//		}
//		
//		if (LoggerDlg.getUser().getRole().isVoirNotificationDemanderFacture()){
//			List<CommandeFournisseur> listCommandesSatisfaites = CommandeFournisseur.getCommandesFournisseurNonFacturees();
//			for (CommandeFournisseur cf : listCommandesSatisfaites){
//				String message 	= controllers.Translation.translate("Demander la facture auprès du fournisseur", LoggerDlg.getUser().getMyParameters().getLang()) +" <font color='red'>"+cf.getFournisseur().getRaisonSocial()+"</font>";
//				message			+= ", "+controllers.Translation.translate("pour la commande N°:", LoggerDlg.getUser().getMyParameters().getLang())+"<font color='red'>"+cf.getNumBC()+"</font>";
//				JFNotification.addEvent(message, EventItem.TYPE_EVENT_DEMANDE_FACTURE);
//			}
//		}
//		
//		int nbreNotifications = JFNotification.getInstance().dlmListEvents.size();
//		
//		String toolTip = controllers.Translation.translate("Il y a", LoggerDlg.getUser().getParametres().getLang().getCodeLang())+" "+nbreNotifications+" "+controllers.Translation.translate("notification(s). Cliquez ici pour les afficher", LoggerDlg.getUser().getParametres().getLang().getCodeLang());
//		MainFrame.getInstanceWithoutCreation().lAlerts.setVisible(JFNotification.getInstance().dlmListEvents.size() > 0);
//		MainFrame.getInstanceWithoutCreation().lAlerts.setToolTipText(toolTip);
//		
//		MainFrame.getInstanceWithoutCreation().lAlerts2.setVisible(JFNotification.getInstance().dlmListEvents.size() > 0);
//		MainFrame.getInstanceWithoutCreation().lAlerts2.setToolTipText(toolTip);
//	}

	private MainFrame(String title) {
		super(title);
		initComponents();
	}
	
	private void initComponents(){
		javax.swing.ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);
//		javax.swing.ToolTipManager.sharedInstance().setInitialDelay(100);
//		javax.swing.UIManager.put("ToolTip.background", new ColorUIResource(new Color(0xCACACA)));
		
		timerCodeCommercial = new Timer(0, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				verifierCodeGUIActivation();
			}
		});
		timerCodeCommercial.setRepeats(false);
		
		timerAutoSave = new Timer(1000*60*5, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String dateTime = appClient.Spool.getDateTimeFromServer();
					String thisDay = dateTime.split(" ")[0];
					
					File autoSavesDir = new File(Spool.getAutoSaveDir());
					autoSavesDir.mkdir();
					
					File fileLog = new File(autoSavesDir.getAbsolutePath()+"/saves.log");
					String estampilleSave = ""; 
					if (fileLog.exists()){
						estampilleSave = utils.FilesAndLaunchUtils.readTextFile(fileLog);
					}
					
					if (estampilleSave.equals("") || utils.StringUtils.compareDates(thisDay, estampilleSave.split(" ")[0]) == 1){
						if (estampilleSave.equals("") || utils.StringUtils.compareDates(utils.StringUtils.getDateFromCalendar(utils.StringUtils.getPreviousDateByDay(thisDay)), estampilleSave.split(" ")[0]) == 1){
							estampilleSave = dateTime;
						}
						else{
							if (Integer.parseInt(dateTime.split(" ")[1].trim().substring(0, 2))<16)
								estampilleSave = "";
							else
								estampilleSave = dateTime;
						}
					}
					else{
						if (utils.StringUtils.compareDates(thisDay, estampilleSave.split(" ")[0]) == 0){
							if (Integer.parseInt(estampilleSave.split(" ")[1].substring(0, 2)) < 16 && dateTime.split(" ")[1].trim().startsWith("16:")){
								estampilleSave = dateTime;
							}
							else{
								estampilleSave = "";
							}
						}
						else{
							estampilleSave = "";
						}
					}
					
					if (!estampilleSave.equals("")){
						savingAllDataApplication("Sauvegarde Périodique des Données ....");
					}
				}
				catch (Exception e){
				}
			}
		});
		timerAutoSave.setRepeats(true);
		timerAutoSave.start();
		
		alRadioButtonMenuItemFrames = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				radioButtonMenuItemFramesActionPerformed(evt);
			}
		};
		bgRadioButtonMenuItemFrames = new ButtonGroup();
		
		clInternalFramesVisibilty = new ComponentAdapter() {
			public void componentShown(ComponentEvent evt) {
				internalFrameSetVisibilty(evt, true);
			}
			
			public void componentHidden(ComponentEvent evt) {
				internalFrameSetVisibilty(evt, false);
			}
		};
		
		iflInternalFramesToFront = new InternalFrameAdapter() {
			public void internalFrameActivated(InternalFrameEvent evt){
				internalFrameToFront(evt);
			}
		};
		
		desktopPane = new JDesktopPaneModified();
		desktopPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		
		menuBar = new javax.swing.JMenuBar();

		mParametrage = new javax.swing.JMenu();  mParametrage.setIcon(app.utils.FrameworkRessources.CONFIGURATION_ICON_20_20);
		miParametresOrganismeManagement = new javax.swing.JMenuItem("Paramètres de l'Organisme", app.utils.FrameworkRessources.PARAMETRES_ORGANISME_ICON_20_20);
		miRoleManagement = new javax.swing.JMenuItem("Rôles", app.utils.FrameworkRessources.ROLE_ICON_20_20);
		miUtilisateurManagement = new javax.swing.JMenuItem("Utilisateurs", app.utils.FrameworkRessources.UTILISATEUR3_ICON_20_20);
		miChambreMangement = new javax.swing.JMenuItem("Chambre", app.utils.Ressources.CHAMBRE_ICON_20_20);
		miClientManagement= new javax.swing.JMenuItem("Client", app.utils.Ressources.CLIENT_ICON_20_20);
		miFactureManagement=new javax.swing.JMenuItem("Facture", app.utils.Ressources.FACTURE_ICON_20_20);
		miServiceManagement=new javax.swing.JMenuItem("Service", app.utils.Ressources.SERVICE_ICON_20_20);
		miCategorieChambreManagement=new javax.swing.JMenuItem("Catégorie Chambre", app.utils.Ressources.CATEGORIE_ICON_20_20);
		miFactureManagement=new javax.swing.JMenuItem("Facture", app.utils.Ressources.FACTURE_ICON_20_20);
		miConsommationManagement=new JMenuItem("Consomation",app.utils.Ressources.CONSOMMATION_ICON_20_20);
		miReservationManagement=new JMenuItem("Consomation",app.utils.Ressources.RESERVATION_ICON_20_20);
		miLock = new javax.swing.JMenuItem("Vérouiller la session", app.utils.FrameworkRessources.LOCK_ICON_5_20_20);
		
		miLogout = new javax.swing.JMenuItem("Se déconnecter", app.utils.FrameworkRessources.LOGOUT_ICON_20_20);
		miQuit = new javax.swing.JMenuItem("Quitter", app.utils.FrameworkRessources.EXIT_ICON_20_20);
		miParametresApplicaiton = new javax.swing.JMenuItem("Paramètres Application", app.utils.FrameworkRessources.PARAMETRES_APPLICATION_ICON_20_20);
		sepMiParametresApplicaiton = new javax.swing.JSeparator();

		mParametrage.add(miParametresApplicaiton);
		mParametrage.add(sepMiParametresApplicaiton);
		
		mParametrage.add(miParametresOrganismeManagement);
		mParametrage.addSeparator();
		
		mParametrage.add(miRoleManagement);
		mParametrage.add(miUtilisateurManagement);
		mParametrage.add(new JSeparator());
		
		mParametrage.add(miChambreMangement);
		mParametrage.add(miClientManagement);
	    mParametrage.add(miFactureManagement);
	    mParametrage.add(miServiceManagement);
	    mParametrage.add(miCategorieChambreManagement);
	    mParametrage.add(miConsommationManagement);
	    mParametrage.add(miReservationManagement);
	
		mParametrage.add(new JSeparator());
		
		mParametrage.add(miLock);
		mParametrage.add(miLogout);
		mParametrage.add(miQuit);
		
		mEditions = new JMenu("Édition"); mEditions.setIcon(app.utils.FrameworkRessources.PRINT_ICON_20_20); mEditions.setMnemonic('E');
		miCouper = new JMenuItem("Couper", app.utils.FrameworkRessources.SAVE_SELECTED_20_20);
		miCopier = new JMenuItem("Copier", app.utils.FrameworkRessources.SAVE_SELECTED_20_20);
		miSupprimer=new javax.swing.JMenuItem("Supprimer", app.utils.Ressources.SUPPRIMER_ICON_20_20);
		
		mEditions.add(miCouper);
		mEditions.add(miCopier);
		mEditions.add(miSupprimer);
		
		mDonnees = new JMenu("Données"); mDonnees.setIcon(app.utils.FrameworkRessources.DATA_ICON_20_20); mDonnees.setMnemonic('D');
		miSauvegardeSelective = new JMenuItem("Sauvegarde Sélective", app.utils.FrameworkRessources.SAVE_SELECTED_20_20);
		miRestaurationSelective = new JMenuItem("Restauration Selective", app.utils.FrameworkRessources.RESTAURE_SELECTED_ICON_20_20);
		miSaveData = new JMenuItem("Sauvegarde de données", app.utils.FrameworkRessources.SAVE_ICON_2_20_20);
		miRestaureData = new JMenuItem("Restauration de données", app.utils.FrameworkRessources.UPDATE_ICON_3_20_20);
		miAutoSaveData = new JMenuItem("Sauvegardes Automaitques", app.utils.FrameworkRessources.SAVE_AUTO_ICON_20_20);
		
		mDonnees.add(miSauvegardeSelective);
		mDonnees.add(miRestaurationSelective);
		mDonnees.add(new JSeparator());
		mDonnees.add(miSaveData);
		mDonnees.add(miRestaureData);
		mDonnees.add(new JSeparator());
		mDonnees.add(miAutoSaveData);

		mWindows = new javax.swing.JMenu("Fenêtres"); mWindows.setIcon(app.utils.FrameworkRessources.FRAME_ICON_20_20); mWindows.setMnemonic('F');
		miWithoutLayout = new javax.swing.JCheckBoxMenuItem("Fenêtres libres");
		miCascade = new javax.swing.JCheckBoxMenuItem("Cascade");
		miMosaique = new javax.swing.JCheckBoxMenuItem("Mosaïque");
		miIconifyAll = new javax.swing.JMenuItem("Tous iconifier (Réduire)", app.utils.FrameworkRessources.FRAME_ICONIFY_ICON);
		miRestaureAll = new javax.swing.JMenuItem("Tous restaurer");
		miCloseAll = new javax.swing.JMenuItem("Tous fermer", app.utils.FrameworkRessources.FRAME_CLOSE_ICON);
		
		listThemesMenuItems.clear();
		
		mWindowsThemes = new JMenu("Thèmes");
		
		mThemeSubstance = new JMenu("Substance");
		ButtonGroup bgThemes = new ButtonGroup();
		
		JCheckBoxMenuItem chbMI = new JCheckBoxMenuItem("CrossPlatform");
		chbMI.setName(javax.swing.UIManager.getCrossPlatformLookAndFeelClassName());
		listThemesMenuItems.add(chbMI);
		bgThemes.add(chbMI);
		mWindowsThemes.add(chbMI);
		
		chbMI = new JCheckBoxMenuItem("System");
		chbMI.setName(javax.swing.UIManager.getSystemLookAndFeelClassName());
		listThemesMenuItems.add(chbMI);
		bgThemes.add(chbMI);
		mWindowsThemes.add(chbMI);
		
		chbMI = new JCheckBoxMenuItem("Nimbus");
		chbMI.setName("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		listThemesMenuItems.add(chbMI);
		bgThemes.add(chbMI);
		mWindowsThemes.add(chbMI);
		
		chbMI = new JCheckBoxMenuItem("Motif");
		chbMI.setName("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		listThemesMenuItems.add(chbMI);
		bgThemes.add(chbMI);
		mWindowsThemes.add(chbMI);
		
		chbMI = new JCheckBoxMenuItem("EaSynth");
		chbMI.setName(com.easynth.lookandfeel.EaSynthLookAndFeel.class.getName());
		listThemesMenuItems.add(chbMI);
		bgThemes.add(chbMI);
		mWindowsThemes.add(chbMI);
		
		chbMI = new JCheckBoxMenuItem("IDW");
		chbMI.setName(net.infonode.gui.laf.InfoNodeLookAndFeel.class.getName());
		listThemesMenuItems.add(chbMI);
		bgThemes.add(chbMI);
		mWindowsThemes.add(chbMI);
		
		chbMI = new JCheckBoxMenuItem("NimoDLF");
		chbMI.setName(com.nilo.plaf.nimrod.NimRODLookAndFeel.class.getName());
		listThemesMenuItems.add(chbMI);
		bgThemes.add(chbMI);
		mWindowsThemes.add(chbMI);
		
		chbMI = new JCheckBoxMenuItem("Smooth Metal");
		chbMI.setName(smoothmetal.SmoothLookAndFeel.class.getName());
		listThemesMenuItems.add(chbMI);
		bgThemes.add(chbMI);
		mWindowsThemes.add(chbMI);
		
		chbMI = new JCheckBoxMenuItem("Tonic");
		chbMI.setName(com.digitprop.tonic.TonicLookAndFeel.class.getName());
		listThemesMenuItems.add(chbMI);
		bgThemes.add(chbMI);
		mWindowsThemes.add(chbMI);
		
//		chbMI = new JCheckBoxMenuItem("Tonic Slim");
//		chbMI.setName(com.digitprop.tonic.TonicLookAndFeel.class.getName());
//		listThemesMenuItems.add(chbMI);
//		bgThemes.add(chbMI);
//		mWindowsThemes.add(chbMI);
		
		chbMI = new JCheckBoxMenuItem("Web Look");
		chbMI.setName(com.alee.laf.WebLookAndFeel.class.getName());
		listThemesMenuItems.add(chbMI);
		bgThemes.add(chbMI);
		mWindowsThemes.add(chbMI);
		
		chbMI = new JCheckBoxMenuItem("Autumn");
		chbMI.setName(SubstanceAutumnLookAndFeel.class.getName());
		listThemesMenuItems.add(chbMI);
		mThemeSubstance.add(chbMI);
		bgThemes.add(chbMI);

		chbMI = new JCheckBoxMenuItem("Business Steel");
		chbMI.setName(SubstanceBusinessLookAndFeel.class.getName());
		listThemesMenuItems.add(chbMI);
		mThemeSubstance.add(chbMI);
		bgThemes.add(chbMI);
		
		chbMI = new JCheckBoxMenuItem("Business Black Steel");
		chbMI.setName(SubstanceBusinessBlackSteelLookAndFeel.class.getName());
		listThemesMenuItems.add(chbMI);
		mThemeSubstance.add(chbMI);
		bgThemes.add(chbMI);
		
		chbMI = new JCheckBoxMenuItem("Business Blue Steel");
		chbMI.setName(SubstanceBusinessBlueSteelLookAndFeel.class.getName());
		listThemesMenuItems.add(chbMI);
		mThemeSubstance.add(chbMI);
		bgThemes.add(chbMI);
		
		chbMI = new JCheckBoxMenuItem("Challenger Deep");
		chbMI.setName(SubstanceChallengerDeepLookAndFeel.class.getName());
		listThemesMenuItems.add(chbMI);
		mThemeSubstance.add(chbMI);
		bgThemes.add(chbMI);
		
		chbMI = new JCheckBoxMenuItem("Creme Coffee");
		chbMI.setName(SubstanceCremeCoffeeLookAndFeel.class.getName());
		listThemesMenuItems.add(chbMI);
		mThemeSubstance.add(chbMI);
		bgThemes.add(chbMI);
		
		chbMI = new JCheckBoxMenuItem("Creme");
		chbMI.setName(SubstanceCremeLookAndFeel.class.getName());
		listThemesMenuItems.add(chbMI);
		mThemeSubstance.add(chbMI);
		bgThemes.add(chbMI);
		
		chbMI = new JCheckBoxMenuItem("Dust");
		chbMI.setName(SubstanceDustLookAndFeel.class.getName());
		listThemesMenuItems.add(chbMI);
		mThemeSubstance.add(chbMI);
		bgThemes.add(chbMI);
		
		chbMI = new JCheckBoxMenuItem("Dust Coffee");
		chbMI.setName(SubstanceDustCoffeeLookAndFeel.class.getName());
		listThemesMenuItems.add(chbMI);
		mThemeSubstance.add(chbMI);
		bgThemes.add(chbMI);
		
		chbMI = new JCheckBoxMenuItem("Dust Coffee");
		chbMI.setName(SubstanceDustCoffeeLookAndFeel.class.getName());
		listThemesMenuItems.add(chbMI);
		mThemeSubstance.add(chbMI);
		bgThemes.add(chbMI);
		
		chbMI = new JCheckBoxMenuItem("Emeral Dust");
		chbMI.setName(SubstanceEmeraldDuskLookAndFeel.class.getName());
		listThemesMenuItems.add(chbMI);
		mThemeSubstance.add(chbMI);
		bgThemes.add(chbMI);
		
		chbMI = new JCheckBoxMenuItem("Leagcy");
		chbMI.setName(SubstanceLegacyDefaultLookAndFeel.class.getName());
		listThemesMenuItems.add(chbMI);
		mThemeSubstance.add(chbMI);
		bgThemes.add(chbMI);
		
		chbMI = new JCheckBoxMenuItem("Substance");
		chbMI.setName(SubstanceLookAndFeel.class.getName());
		listThemesMenuItems.add(chbMI);
		mThemeSubstance.add(chbMI);
		bgThemes.add(chbMI);
		
		chbMI = new JCheckBoxMenuItem("Magma");
		chbMI.setName(SubstanceMagmaLookAndFeel.class.getName());
		listThemesMenuItems.add(chbMI);
		mThemeSubstance.add(chbMI);
		bgThemes.add(chbMI);
		
		chbMI = new JCheckBoxMenuItem("Mist Aqua");
		chbMI.setName(SubstanceMistAquaLookAndFeel.class.getName());
		listThemesMenuItems.add(chbMI);
		mThemeSubstance.add(chbMI);
		bgThemes.add(chbMI);
		
		chbMI = new JCheckBoxMenuItem("Mist Silver");
		chbMI.setName(SubstanceMistSilverLookAndFeel.class.getName());
		listThemesMenuItems.add(chbMI);
		mThemeSubstance.add(chbMI);
		bgThemes.add(chbMI);
		
		chbMI = new JCheckBoxMenuItem("Nebula");
		chbMI.setName(SubstanceNebulaLookAndFeel.class.getName());
		listThemesMenuItems.add(chbMI);
		mThemeSubstance.add(chbMI);
		bgThemes.add(chbMI);
		
		chbMI = new JCheckBoxMenuItem("Nebula Brick Wall");
		chbMI.setName(SubstanceNebulaBrickWallLookAndFeel.class.getName());
		listThemesMenuItems.add(chbMI);
		mThemeSubstance.add(chbMI);
		bgThemes.add(chbMI);
		
		chbMI = new JCheckBoxMenuItem("Modrate");
		chbMI.setName(SubstanceModerateLookAndFeel.class.getName());
		listThemesMenuItems.add(chbMI);
		mThemeSubstance.add(chbMI);
		bgThemes.add(chbMI);
		
		chbMI = new JCheckBoxMenuItem("Office Blue 2007");
		chbMI.setName(SubstanceOfficeBlue2007LookAndFeel.class.getName());
		listThemesMenuItems.add(chbMI);
		mThemeSubstance.add(chbMI);
		bgThemes.add(chbMI);
		
		chbMI = new JCheckBoxMenuItem("Office Silver 2007");
		chbMI.setName(SubstanceOfficeSilver2007LookAndFeel.class.getName());
		listThemesMenuItems.add(chbMI);
		mThemeSubstance.add(chbMI);
		bgThemes.add(chbMI);
		
		chbMI = new JCheckBoxMenuItem("Raven");
		chbMI.setName(SubstanceRavenLookAndFeel.class.getName());
		listThemesMenuItems.add(chbMI);
		mThemeSubstance.add(chbMI);
		bgThemes.add(chbMI);
		
		chbMI = new JCheckBoxMenuItem("Raven Graite");
		chbMI.setName(SubstanceRavenGraphiteLookAndFeel.class.getName());
		listThemesMenuItems.add(chbMI);
		mThemeSubstance.add(chbMI);
		bgThemes.add(chbMI);
		
		chbMI = new JCheckBoxMenuItem("Raven Graite Glass");
		chbMI.setName(SubstanceRavenGraphiteGlassLookAndFeel.class.getName());
		listThemesMenuItems.add(chbMI);
		mThemeSubstance.add(chbMI);
		bgThemes.add(chbMI);
		
		chbMI = new JCheckBoxMenuItem("Sahara");
		chbMI.setName(SubstanceSaharaLookAndFeel.class.getName());
		listThemesMenuItems.add(chbMI);
		mThemeSubstance.add(chbMI);
		bgThemes.add(chbMI);
		
		chbMI = new JCheckBoxMenuItem("Twi Light");
		chbMI.setName(SubstanceTwilightLookAndFeel.class.getName());
		listThemesMenuItems.add(chbMI);
		mThemeSubstance.add(chbMI);
		bgThemes.add(chbMI);
		
		mWindowsThemes.add(mThemeSubstance);
		
		javax.swing.ButtonGroup bg = new javax.swing.ButtonGroup();
		bg.add(miWithoutLayout);
		bg.add(miCascade);
		bg.add(miMosaique);
		
		miWithoutLayout.setSelected(true);

		mWindows.add(miWithoutLayout);
		mWindows.add(miCascade);
		mWindows.add(miMosaique);
		mWindows.add(new javax.swing.JSeparator());
		mWindows.add(miIconifyAll);
		mWindows.add(miRestaureAll);
		mWindows.add(new javax.swing.JSeparator());
		mWindows.add(miCloseAll);
		mWindows.addSeparator();
		mWindows.add(mWindowsThemes);
		mWindows.addSeparator();
		
		mHelp = new JMenu("Aide"); mHelp.setIcon(app.utils.FrameworkRessources.HELP_ICON_20_20); mHelp.setMnemonic('i');
		
		miHelpDocumentation = new JMenuItem("Documentaion (Manuel d'Utilisation)", app.utils.FrameworkRessources.HELP_ICON_20_20_2);
		
		miHelpAbout = new JMenuItem("À Propos ...", app.utils.FrameworkRessources.ABOUT_ICON_20_20_2);
		
		mHelp.add(miHelpDocumentation);
		mHelp.add(new JSeparator());
		mHelp.add(miHelpAbout);

		menuBar.add(mParametrage);
		menuBar.add(mEditions);
		menuBar.add(mDonnees);
		menuBar.add(mWindows);
		menuBar.add(mHelp);

		miShowHideToolBar = new JCheckBoxMenuItem("Barre d'Outils"){
			private static final long serialVersionUID = 1L;
			
			public void setSelected(boolean selected) {
				super.setSelected(selected);
				
				if (selected){
					this.setText("Cacher la Barre d'Outils");
				}
				else{
					this.setText("Afficher la Barre d'Outils");
				}
			}
		};
		miMyProfils = new JMenuItem("Mon Profil", app.utils.FrameworkRessources.UTILISATEUR_20_ICON);
		
		menuBar.add(miShowHideToolBar);
		menuBar.add(miMyProfils);
		
		this.setJMenuBar(menuBar);
		
		miParametresOrganismeManagement.setAccelerator(KeyStroke.getKeyStroke("ctrl pressed E"));
		miRoleManagement.setAccelerator(KeyStroke.getKeyStroke("ctrl pressed R"));
		miUtilisateurManagement.setAccelerator(KeyStroke.getKeyStroke("ctrl pressed U"));
		miChambreMangement.setAccelerator(KeyStroke.getKeyStroke("ctrl  pressed F1"));
		miClientManagement.setAccelerator(KeyStroke.getKeyStroke("ctrl pressed F2"));
		miFactureManagement.setAccelerator(KeyStroke.getKeyStroke("ctrl pressed F3"));
		miServiceManagement.setAccelerator(KeyStroke.getKeyStroke("ctrl pressed F4"));
		
		miCategorieChambreManagement.setAccelerator(KeyStroke.getKeyStroke("ctrl pressed F5"));
		miConsommationManagement.setAccelerator(KeyStroke.getKeyStroke("ctrl pressed F6"));
		miReservationManagement.setAccelerator(KeyStroke.getKeyStroke("ctrl pressed F7"));
		
		miSaveData.setAccelerator(KeyStroke.getKeyStroke("pressed F3"));
		miRestaureData.setAccelerator(KeyStroke.getKeyStroke("pressed F4"));
		miAutoSaveData.setAccelerator(KeyStroke.getKeyStroke("pressed F5"));
		miHelpDocumentation.setAccelerator(KeyStroke.getKeyStroke("pressed F1"));
		miHelpAbout.setAccelerator(KeyStroke.getKeyStroke("pressed F2"));
		miCouper.setAccelerator(KeyStroke.getKeyStroke("ctrl pressed X"));
		miCopier.setAccelerator(KeyStroke.getKeyStroke("ctrl pressed C"));
		miSupprimer.setAccelerator(KeyStroke.getKeyStroke("ctrl pressed D"));
		
		miLogout.setAccelerator(KeyStroke.getKeyStroke("pressed END"));
		miLock.setAccelerator(KeyStroke.getKeyStroke("ctrl pressed L"));
		miQuit.setAccelerator(KeyStroke.getKeyStroke("ctrl pressed Q"));

		toolBar = new javax.swing.JToolBar("MTEE - Mutuelle de Travail de l'Enrivonnemnt Économique - Barre d'outils");
		
//		toolBar.setFloatable(false);
//		toolBar.setRollover(true);
		
		pTBPanelDateHeure = new JPanel();
		lTBlabelJour = new MyJLabel();
		lTBlabelHeure = new MyJLabel();
		
		lTBlabelJour.getLabel().setHorizontalAlignment(JLabel.CENTER);
		lTBlabelJour.getLabel().setVerticalAlignment(JLabel.CENTER);
		
		lTBlabelHeure.getLabel().setHorizontalAlignment(JLabel.CENTER);
		lTBlabelHeure.getLabel().setVerticalAlignment(JLabel.CENTER);
		
		pTBPanelDateHeure.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		
		lTBlabelHeure.setBackground(new Color(0xb0caf4));
		lTBlabelJour.setBackground(new Color(0xb0caf4));
		
		lTBlabelHeure.getLabel().setForeground(Color.BLACK);
		lTBlabelJour.getLabel().setForeground(Color.BLACK);
		
		lTBlabelJour.getLabel().setFont(FontUtils.getListFonts().get(28).getFont().deriveFont(13f));
		lTBlabelHeure.getLabel().setFont(FontUtils.getListFonts().get(28).getFont().deriveFont(27f));
		
//		TBlabelJour.setBackground();
//		TBlabelHeure.setBackground();

		tabbedPaneToolBar = new JTabbedPane();
//		tabbedPaneToolBar = gui.utils.GUIPanelFactory.createTabbedPane(gui.utils.GUIPanelFactory.PLASTIQUE_PANE_TYPE);
		tabbedPaneToolBar.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
		tbParameters = new JToolBar("Paramètres");
		
		//tbGestion = new JToolBar("Gestion");
		
		tbEditions = new JToolBar("Éditions");
		
		tbDonnees = new JToolBar("Donnees - Sauvegarde, restauration, exportation et importation");
		
		int i=0;
		tabbedPaneToolBar.add("Paramètres", tbParameters);
		tabbedPaneToolBar.setIconAt(i++, app.utils.FrameworkRessources.CONFIGURATION_ICON_20_20);
		
//		tabbedPaneToolBar.add("Gestion", tbGestion);
//		tabbedPaneToolBar.setIconAt(i++, app.utils.Ressources.CHAMBRE_ICON_20_20);
		
		tabbedPaneToolBar.add("Éditions", tbEditions);
		tabbedPaneToolBar.setIconAt(i++, app.utils.FrameworkRessources.PRINT_ICON_20_20);
		
		tabbedPaneToolBar.add(models.daos.client.DAOTranslation.translate("Données", Spool.getUser().getParametres().getLang().getCodeLang()), tbDonnees);
		tabbedPaneToolBar.setIconAt(i++, app.utils.FrameworkRessources.DATA_ICON_20_20);
		
		tabbedPaneToolBar.setSelectedIndex(0);
		
		bTBLock = GUIButtonFactory.createSimpleButton(app.utils.FrameworkRessources.LOCK_ICON_5_30_30);
		bTBLock.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		bTBLock.setFocusable(false);
		bTBLock.setToolTipText("Dévérouiller la session");
		
		bTBLogout = GUIButtonFactory.createSimpleButton(app.utils.FrameworkRessources.LOGOUT_ICON_32_32);
		bTBLogout.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		bTBLogout.setFocusable(false);
		bTBLogout.setToolTipText("Se déconnecter");
		
		bTBQuit = GUIButtonFactory.createSimpleButton(app.utils.FrameworkRessources.EXIT_ICON_2_32_32);
		bTBQuit.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		bTBQuit.setFocusable(false);
		bTBQuit.setToolTipText("Quitter l'Application");
		
		bTBLang = GUIButtonFactory.createSimpleButton(Spool.getUser().getParametres().getLang().getCodeLang());
		bTBLang.setFont(bTBLang.getFont().deriveFont(20.0f).deriveFont(Font.ITALIC | Font.BOLD));
		bTBLang.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		bTBLang.setFocusable(false);
		bTBLang.setToolTipText("Modifier la langue");
		bTBLang.setVisible(false);
		
		popupMenuLangs = new JPopupMenu();
		mapMenuItemsLangs = new HashMap<JMenuItem, Lang>();
		
		

		
		
//		bTBParametresOrganisme = gui.utils.GUIButtonFactory.createSimpleButton("Paramètres de l'Organisme", utils.Ressources.PARAMETRES_ORGANISME_ICON_30_30, 'E');
		bTBParametresOrganisme = gui.utils.GUIButtonFactory
		.createSimpleButton(app.utils.FrameworkRessources.PARAMETRES_ORGANISME_ICON_40_40);
		bTBParametresOrganisme.setToolTipText("Paramètres de l'Organisme");
		bTBParametresOrganisme.setFocusable(false);
		bTBParametresOrganisme.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		bTBParametresOrganisme.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		bTBParametresOrganisme.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		tbParameters.add(bTBParametresOrganisme);
		
	    tbParameters.addSeparator();
		
//		bTBRole = gui.utils.GUIButtonFactory.createSimpleButton("Réles", utils.Ressources.ROLE_ICON_30_30, 'R');
		bTBRole = gui.utils.GUIButtonFactory.createSimpleButton(app.utils.FrameworkRessources.ROLE_ICON_40_40);
		bTBRole.setToolTipText("Gérer les Rôles");
		bTBRole.setFocusable(false);
		bTBRole.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		bTBRole.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		bTBRole.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		tbParameters.add(bTBRole);
		
		  tbParameters.addSeparator();
//		bTBUtilisateur = gui.utils.GUIButtonFactory.createSimpleButton("Utilisateurs", utils.Ressources.UTILISATEUR2_ICON_30_30, 'U');
		bTBUtilisateur = gui.utils.GUIButtonFactory.createSimpleButton(app.utils.FrameworkRessources.UTILISATEUR3_ICON_40_40);
		bTBUtilisateur.setToolTipText("Gérer les Utilisateur");
		bTBUtilisateur.setFocusable(false);
		bTBUtilisateur.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		bTBUtilisateur.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		bTBUtilisateur.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		tbParameters.add(bTBUtilisateur);
		  tbParameters.addSeparator();
		  
		bTBChambre = gui.utils.GUIButtonFactory
		.createSimpleButton(app.utils.Ressources.CHAMBRE_ICON_40_40);
		bTBChambre.setToolTipText("Gérer les Chambres");
		bTBChambre.setFocusable(false);
		bTBChambre.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		bTBChambre.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		bTBChambre.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		tbParameters.add(bTBChambre);
		  tbParameters.addSeparator();
		  
        bTBClient =  gui.utils.GUIButtonFactory
		.createSimpleButton(app.utils.Ressources.CLIENT_ICON_40_40);
		bTBClient.setToolTipText("Gérer les Clients");
		bTBClient.setFocusable(false);
		bTBClient.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		bTBClient.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		bTBClient.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		tbParameters.add(bTBClient);
		  tbParameters.addSeparator();
		
		bTBFacture  =  gui.utils.GUIButtonFactory
			.createSimpleButton(app.utils.Ressources.FACTURE_ICON_40_40);
		bTBFacture.setToolTipText("Gérer les Factures");
		bTBFacture .setFocusable(false);
		bTBFacture .setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		bTBFacture .setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		bTBFacture .setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
			tbParameters.add(bTBFacture);
			  tbParameters.addSeparator();
		
			bTBService  =  gui.utils.GUIButtonFactory
			.createSimpleButton(app.utils.Ressources.SERVICE_ICON_40_40);
		bTBService.setToolTipText("Gérer les Services");
		bTBService .setFocusable(false);
		bTBService .setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		bTBService .setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		bTBService .setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
			tbParameters.add(bTBService);
			  tbParameters.addSeparator();
			
			
			 bTBCategorieChambre =  gui.utils.GUIButtonFactory
				.createSimpleButton(app.utils.Ressources.CATEGORIE_ICON_40_40);
				bTBCategorieChambre.setToolTipText("Gérer les Catégories des chambres");
				bTBCategorieChambre.setFocusable(false);
				bTBCategorieChambre.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				bTBCategorieChambre.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
				bTBCategorieChambre.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
				tbParameters.add(bTBCategorieChambre);
				tbParameters.addSeparator();
				
				   bTBConsommation =  gui.utils.GUIButtonFactory
					.createSimpleButton(app.utils.Ressources.CONSOMMATION_ICON_40_40);
					bTBConsommation.setToolTipText("Gérer les Consommations");
					bTBConsommation.setFocusable(false);
					bTBConsommation.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
					bTBConsommation.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
					bTBConsommation.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
					tbParameters.add(bTBConsommation);
					tbParameters.addSeparator();
					

					bTBReservation =  gui.utils.GUIButtonFactory
					.createSimpleButton(app.utils.Ressources.RESERVATION_ICON_40_40);
					bTBReservation.setToolTipText("Gérer les Réservations");
					bTBReservation.setFocusable(false);
					bTBReservation.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
					bTBReservation.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
					bTBReservation.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
					tbParameters.add(bTBReservation);
					

		JSeparator separator = new JSeparator();
		separator.setSize(10, separator.getHeight());
//		toolBar.add(separator);
		
//		toolBar.add(new JSeparator());
		bTBCouper = gui.utils.GUIButtonFactory.createSimpleButton(app.utils.FrameworkRessources.SAVE_ICON_2_40_40);
		bTBCouper.setToolTipText("Couper les données");
		bTBCouper.setFocusable(false);
		bTBCouper.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		bTBCouper.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		bTBCouper.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		tbEditions.add(bTBCouper);
		
		tbEditions.addSeparator();
		
		bTBCopier = gui.utils.GUIButtonFactory.createSimpleButton(app.utils.FrameworkRessources.SAVE_ICON_2_40_40);
		bTBCopier.setToolTipText("Copier les données");
		bTBCopier.setFocusable(false);
		bTBCopier.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		bTBCopier.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		bTBCopier.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		tbEditions.add(bTBCopier);
		
		tbEditions.addSeparator();
		
		bTBSuppimer = gui.utils.GUIButtonFactory
				.createSimpleButton(app.utils.Ressources.SUPPRIMER_ICON_40_40);
		bTBSuppimer.setToolTipText("Supprimer les données");
		bTBSuppimer.setFocusable(false);
		bTBSuppimer.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		bTBSuppimer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		bTBSuppimer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		tbEditions.add(bTBSuppimer);

		
		
		bTBSauvegardeSelective = gui.utils.GUIButtonFactory.createSimpleButton(app.utils.FrameworkRessources.SAVE_SELECTED_40_40);
		bTBSauvegardeSelective.setToolTipText("Sauvegarder les données sélectivement");
		bTBSauvegardeSelective.setFocusable(false);
		bTBSauvegardeSelective.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		bTBSauvegardeSelective.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		bTBSauvegardeSelective.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		tbDonnees.add(bTBSauvegardeSelective);

		bTBRestaurationSelective = gui.utils.GUIButtonFactory.createSimpleButton(app.utils.FrameworkRessources.RESTAURE_SELECTED_ICON_40_40);
		bTBRestaurationSelective.setToolTipText("Importer les données sélectivement");
		bTBRestaurationSelective.setFocusable(false);
		bTBRestaurationSelective.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		bTBRestaurationSelective.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		bTBRestaurationSelective.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		tbDonnees.add(bTBRestaurationSelective);
		
		tbDonnees.addSeparator();
		
		bTBSaveData = gui.utils.GUIButtonFactory.createSimpleButton(app.utils.FrameworkRessources.SAVE_ICON_2_40_40);
		bTBSaveData.setToolTipText("Sauvegarder toutes les données");
		bTBSaveData.setFocusable(false);
		bTBSaveData.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		bTBSaveData.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		bTBSaveData.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		tbDonnees.add(bTBSaveData);
		
		bTBRestaureData = gui.utils.GUIButtonFactory.createSimpleButton(app.utils.FrameworkRessources.UPDATE_ICON_3_40_40);
		bTBRestaureData.setToolTipText("Restaurer les données Sauvegardées");
		bTBRestaureData.setFocusable(false);
		bTBRestaureData.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		bTBRestaureData.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		bTBRestaureData.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		tbDonnees.add(bTBRestaureData);
		
		tbDonnees.addSeparator();
		
		bTBAutoSaveData = gui.utils.GUIButtonFactory.createSimpleButton(app.utils.FrameworkRessources.SAVE_AUTO_ICON_40_40);
		bTBAutoSaveData.setToolTipText("Sauvegardes Automatiques des Données");
		bTBAutoSaveData.setFocusable(false);
		bTBAutoSaveData.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		bTBAutoSaveData.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		bTBAutoSaveData.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		tbDonnees.add(bTBAutoSaveData);
		
		pStateBar = new javax.swing.JPanel();
		pStateBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		
		lAlerts = new JLabel(app.utils.FrameworkRessources.ALERT_ANIM_ICON);
		lAlerts.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lAlerts2 = new JLabel(app.utils.FrameworkRessources.ALERT_ANIM_ICON_20_20);
		lAlerts2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lAlerts2.setVisible(false);
		lAlerts.setVisible(false);
		
//		lAllRightsReserved = new javax.swing.JLabel("Tous Droits Réservés ("+FrameworkConstantes.MY_EMAILS+"   -   "+FrameworkConstantes.MY_PHONE_NUMBERS+")");
		lAllRightsReserved = new javax.swing.JLabel("Tous Droits Réservés par : MEDDAH Amine et MEHDAOUI Larbi - 2015");
		lAllRightsReserved.setHorizontalAlignment(javax.swing.JLabel.CENTER);
		lAllRightsReserved.setForeground(Color.BLUE.brighter().brighter().brighter().brighter());
		
		
//		popupMenuEdition = new JPopupMenu();
//		miEditionListAdherents = new JMenuItem("Liste des adhérents", app.utils.Ressources.LIST_ADHERENTS_ICON_40_40);
//		
//		popupMenuEdition.add(miEditionListAdherents);
		
		//tabbedPaneToolBar.setSelectedIndex(0);//pour afficher l'onglet paramètres a l"execution de l'application
		
		configureRootPane(this.getRootPane());
		
		this.updateGUIForLang(Spool.getUser().getParametres().getLang());
		
		allLayout();
		allEvents();
		
		this.desktopBacktground();
	}
	
	public void autoSaveAllData(){
		String dateTime = appClient.Spool.getDateTimeFromServer();
		
		File autoSavesDir = new File(appClient.Spool.getAutoSaveDir());
		autoSavesDir.mkdir();
		
		File fileLog = new File(autoSavesDir.getAbsolutePath()+"/saves.log");
		autoSaveAllData(autoSavesDir, fileLog, dateTime, dateTime);
	}
	
	private void autoSaveAllData(File autoSavesDir, File fileLog, String dateTime, String estampilleSave){
		String fileName = autoSavesDir.getAbsolutePath()+"/Sauvegarde_"+dateTime.replaceAll(":", "_")+".zip";
		File file = new File(fileName);
		if (!file.exists()){
			saveAllData(file);
			
			utils.FilesAndLaunchUtils.createFileFromText(fileLog, estampilleSave);
		}
	}
	
	public void setGUILang(Lang lang){
		
	}
	
	private void verifierCodeGUIActivation(){
		String code = GUIPanelFactory.LayerInputPassword.showPasswordInputPane("Enter a Password, Please");
		if (code != null){
		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(Spool.CODE);
		java.util.regex.Matcher matcher = pattern.matcher(code);
		
	        if (matcher.find()){
	        	gui.crud.framework.ParametresApplicationManagement.createAndShow(MainFrame.getInstanceWithoutCreation().desktopPane);
	        }
		}
	}
	
	private void configureRootPane(JRootPane rootPane) {
		InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		
		char[] charCodes = {'A', 'B', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		
		for (final char c : charCodes){
			inputMap.put(KeyStroke.getKeyStroke("ctrl alt pressed "+c), c+"_commercialVisibility");
			
			rootPane.getActionMap().put(c+"_commercialVisibility",
					new AbstractAction(c+"_commercialVisibility") {
						private static final long serialVersionUID = 1L;
			
						public void actionPerformed(ActionEvent actionEvent) {
							actionEvent.setSource(String.valueOf(c));
							setComercialVisiblity(actionEvent);
						}
					}
			);
		}
		
		inputMap.put(KeyStroke.getKeyStroke("ctrl alt pressed C"), "effaceCodeCommercial");
		rootPane.getActionMap().put("effaceCodeCommercial",
			new AbstractAction("effaceCodeCommercial") {
				private static final long serialVersionUID = 1L;
			
				public void actionPerformed(ActionEvent actionEvent) {
					codeGUIActivation = "";
				}
			}
		);
	}
	
	private void showHideToolBar(){
		boolean visibility = !this.toolBar.isVisible();
		
		this.toolBar.setVisible(visibility);
		this.miShowHideToolBar.setSelected(visibility);
		
		Spool.getUser().getParametres().setVisibilityOfMainToolBar(Boolean.valueOf(visibility));
		communication.SocketCommunicator.getInstance().sendQueryAsynchrone(models.daos.client.DAOParametresApplicationUtilisateur.getSQLWritingByPreparedStatement(Spool.getUser().getParametres()));
	}
	
	public JDesktopPaneModified getDesktopPane(){
		return this.desktopPane;
	}
	
	public void desktopBackground(boolean update){
		if (update){
			parametreOrganisme = null;
		}
		
		desktopBacktground();
	}
	
	private void desktopBacktground(){
		JInternalFrame[] internalFrames = desktopPane.getAllFrames();
		desktopPane.removeAll();
		
		for (JInternalFrame iF : internalFrames){
			boolean visibility = iF.isVisible();
			desktopPane.add(iF);
			iF.setVisible(visibility);
		}
		
		desktopPane.setBackground(Color.GRAY.brighter().brighter());
		
		
		
		int width = desktopPane.getWidth();
		int height = desktopPane.getHeight();
		
		if (parametreOrganisme == null){
			parametreOrganisme = models.daos.client.DAOParametresOrganisme.getParametresOrganisme(1);
			
			lBackgroundImage = new JLabel();
			lBackgroundImage.setHorizontalAlignment(JLabel.CENTER);
			lBackgroundImage.setVerticalAlignment(JLabel.CENTER);
			if (Spool.getParametresApplication().getPhoto() != null){
				lBackgroundImage.setIcon(Spool.getParametresApplication().getPhoto().getImageIcon());
			}
			
			if (lOrganisme == null){
				lOrganisme = new JLabel(parametreOrganisme.getRaisonSocial());
			}
			else{
				lOrganisme.setText(parametreOrganisme.getRaisonSocial());
			}
			
			Font font = FontUtils.getListFonts().get(0).getFont().deriveFont(35f);
			lOrganisme.setFont(font);
			lOrganisme.setHorizontalAlignment(JLabel.CENTER);
			lOrganisme.setVerticalAlignment(JLabel.CENTER);
			
			if (lTitleApplication == null)
				lTitleApplication = new JLabel(Spool.getParametresOrganisme().getDesignationOrganisme());
			else
				lTitleApplication.setText(Spool.getParametresApplication().getDesignation());
			
			if (lDayTime == null){
				lDayTime = new MyJLabel();
				lDayTime.setBackground(new Color(0xc2ced9));
				lDayTime.getLabel().setForeground(Color.BLUE.brighter().brighter().brighter().brighter());

				String dateTime = appClient.Spool.getDateTimeFromServer();
				
				Timestamp timestamp = utils.StringUtils.getTimestampFromString(dateTime);
				timeMiliSeconds = timestamp.getTime();
				
				final Calendar calendar = Calendar.getInstance();
				
				Timer timerHeure = new Timer(1000, new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
							try{
								String dateTime =  (new Timestamp(timeMiliSeconds)).toString();
								calendar.setTimeInMillis(timeMiliSeconds);
								int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // 1 pour Dim., 2 pour Lun. ... 7 pour Sam.
								dayOfWeek = (dayOfWeek+5) % 7; // 0 pour Lun., 1 pour Mar., ..., 6 pour Dim.
								
								String date = dateTime.split(" ")[0];
								String time = dateTime.split(" ")[1];
								
								lDayTime.setText(GUIDate.DAYS_OF_WEEK_LONG[dayOfWeek] + " le : "+utils.StringUtils.formatDateFromMySQL(date)+" - "+time.substring(0, time.indexOf(".")));
								
								lTBlabelHeure.setText(time.substring(0, time.indexOf(".")));
								lTBlabelJour.setText(GUIDate.DAYS_OF_WEEK_SHORT[dayOfWeek]+" "+utils.StringUtils.formatDateFromMySQL(date));
								
								lTBlabelJour.getLabel().setToolTipText(lDayTime.getText());
								lTBlabelHeure.getLabel().setToolTipText(lDayTime.getText());
								
								timeMiliSeconds += 1000;
							}
							catch (Exception e){
							}
					}
				});
				timerHeure.setRepeats(true);
				timerHeure.start();
				
				lDayTime.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt){
						if (evt.getClickCount() >= 3 && evt.getButton() == MouseEvent.BUTTON3){
							verifierCodeGUIActivation();
						}
					}
				});
			}
			
			lTBlabelJour.getLabel().setToolTipText(lDayTime.getText());
			lTBlabelHeure.getLabel().setToolTipText(lDayTime.getText());
			
			lTitleApplication.setFont(font.deriveFont(Font.ITALIC));
			lTitleApplication.setHorizontalAlignment(JLabel.CENTER);
			lTitleApplication.setVerticalAlignment(JLabel.CENTER);
			
			separator1 = new JSeparator();
			separator2 = new JSeparator();
			
			bSessions = new GUIButtonFactory.GradientRoundRectButton("Sessions de Connexions");
			bSessions.setToolTipText("Vérification de mes sessions de connections et les différents évènements");
			bSessions.setText("<html><center>Sessions de <br/>Connexions</center><html>");
			
			bSessions.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					bSessionsActionPerformed(evt);
				}
			});
			
			bSessions.setFont(bSessions.getFont().deriveFont(12f));
			
			bSessions.setForeground(Color.BLACK);
			((GradientRoundRectButton)bSessions).setUnselectedHigh(new Color(0xf4f8ff));
			((GradientRoundRectButton)bSessions).setUnselectedLow(new Color(0x72a6fc));
			
			pLinearGradient = new JPanel(){
				private static final long serialVersionUID = 1L;

				private LinearGradientPaint lgp = null;
				
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					
					int width = getWidth();
					int height = getHeight();
					
					Graphics2D g2d = (Graphics2D)g;
					Paint oldPaint = g2d.getPaint();
					
					if (lgp == null){
						Point2D start = new Point2D.Float(0f, 0f);
						Point2D end = new Point2D.Float(width, height);
						
						Color color1 = new Color(0xb0caf4);
						Color color2 = Color.WHITE;
						
						Color[] colors = {color1, color2, color1};
						float[] dist = {.33f, .66f, 1.0f};
						
						lgp = new LinearGradientPaint(start, end, dist, colors);
					}
					
					g2d.setPaint(lgp);
					g2d.fillRect(0, 0, width, height);
					
					g2d.setPaint(oldPaint);
				}
			};
		}
		
		lDayTime.setBounds(10, 5, width - 20, 20);
		lTitleApplication.setBounds(10, 10, width - 20, 100);
		lOrganisme.setBounds(10, 80, width - 20, 100);
		lBackgroundImage.setBounds(10, 200, width - 20, 400);
		separator1.setBounds(2, 85, width - 4, 3);
		separator2.setBounds(2, 239, width - 4, 3);
		
		bSessions.setBounds(width-160, height - 55, 150, 45);
		
		pLinearGradient.setBounds(2, 200, width - 4, 35);
		
		int w = lAlerts.getIcon().getIconWidth();
		int h = lAlerts.getIcon().getIconHeight();
		lAlerts.setBounds(width-w, height-h, w, h);
		
		boolean visibilityAlert  = lAlerts.isVisible();
		boolean visibilityBSessions = bSessions.isVisible();
		
		int size = 100;
		
		int baseY = 100;
		int stepY = 150;
		
		int baseX = 200;
		int stepX = 75;
		
//		int dx = 50;
//		bVentes.setBounds(width-baseX - stepX - size + dx, baseY, size, size);
//		bAchats.setBounds(width-baseX - size + dx, baseY+stepY, size, size);
//		bCommandeFournisseur.setBounds(width-baseX - size + dx, baseY+2*stepY, size, size);
//		bCommandeClient.setBounds(width-baseX - stepX - size + dx, baseY+3*stepY, size, size);
		
		boolean exception = true;
		while (exception){
			try{
				
				exception = false;
			}
			catch (Exception e){
				
			}
		}
		
		exception = true;
		while (exception){
			try{
				desktopPane.add(lBackgroundImage);
				desktopPane.add(lDayTime);
				desktopPane.add(lTitleApplication);
				desktopPane.add(separator1);
//				desktopPane.add(separator2);
//				desktopPane.add(pLinearGradient);
				desktopPane.add(lOrganisme);
				desktopPane.add(lAlerts);
				desktopPane.add(bSessions);
				
				exception = false;
			}
			catch (Exception e){
			}
		}
		
		lAlerts.setVisible(visibilityAlert);
		lAlerts2.setVisible(visibilityAlert);
		
		bSessions.setVisible(visibilityBSessions);
	}
	
	private void updateLayoutTooBar(boolean updateSize){
		toolBar.removeAll();
		GroupLayout layoutToolBar = (GroupLayout)toolBar.getLayout();
		layoutToolBar.setHorizontalGroup(layoutToolBar.createSequentialGroup()
				.addGap(1, 1, 1)
				.addComponent(tabbedPaneToolBar, 500, 500, Short.MAX_VALUE)
				.addGap(1, 1, 1)
				.addComponent(pTBPanelDateHeure, 100, 100, 100)
				.addGap(1, 1, 1)
				.addComponent(bTBLang, 50, 50, 50)
				.addComponent(bTBLock, 50, 50, 50)
				.addComponent(bTBLogout, 50, 50, 50)
				.addComponent(bTBQuit, 50, 50, 50)
				.addGap(1, 1, 1)
		);
		layoutToolBar.setVerticalGroup(layoutToolBar.createSequentialGroup()
				.addGroup(layoutToolBar.createParallelGroup()
						.addComponent(tabbedPaneToolBar, 50, 50, Short.MAX_VALUE)
						.addGroup(layoutToolBar.createSequentialGroup()
								.addGap(0, 0, Short.MAX_VALUE)
								.addComponent(pTBPanelDateHeure, 50, 50, 50)	
						)
						.addGroup(layoutToolBar.createSequentialGroup()
								.addGap(0, 0, Short.MAX_VALUE)
								.addComponent(bTBLang, 50, 50, 50)
						)
						.addGroup(layoutToolBar.createSequentialGroup()
								.addGap(0, 0, Short.MAX_VALUE)
								.addComponent(bTBLock, 50, 50, 50)
						)
						.addGroup(layoutToolBar.createSequentialGroup()
								.addGap(0, 0, Short.MAX_VALUE)
								.addComponent(bTBLogout, 50, 50, 50)
						)
						.addGroup(layoutToolBar.createSequentialGroup()
								.addGap(0, 0, Short.MAX_VALUE)
								.addComponent(bTBQuit, 50, 50, 50)
						)
				)
		);
		
		toolBar.updateUI();
		this.validate();
	}
	
	private void updateLayoutPStateBar(){
			GroupLayout layoutPStateBar = (GroupLayout)pStateBar.getLayout();
			
			SequentialGroup hg = layoutPStateBar.createSequentialGroup();
			ParallelGroup	vg = layoutPStateBar.createParallelGroup();
			
			hg.addGap(1, 1, 1);
			hg.addComponent(lAlerts2, 20, 20, 20);
			hg.addComponent(lAllRightsReserved, 100, 100, Short.MAX_VALUE);
			
			vg.addComponent(lAllRightsReserved, 20, 20, Short.MAX_VALUE);
			vg.addComponent(lAlerts2, 20, 20, 20);
			
			for (JInternalFrame iframe : mapTSBFrames.keySet()){
				hg.addComponent(mapTSBFrames.get(iframe), 26, 26, 26);
				vg.addComponent(mapTSBFrames.get(iframe), 26, 26, 26);
			}
			hg.addGap(1, 1, 1);
			
			layoutPStateBar.setHorizontalGroup(hg);
			layoutPStateBar.setVerticalGroup(vg);
	}
	
	private void allLayout(){
		GroupLayout layoutTBPanelDateHeure = new GroupLayout(pTBPanelDateHeure);
		pTBPanelDateHeure.setLayout(layoutTBPanelDateHeure);
		layoutTBPanelDateHeure.setHorizontalGroup(layoutTBPanelDateHeure.createParallelGroup()
				.addGroup(layoutTBPanelDateHeure.createSequentialGroup()
						.addComponent(lTBlabelJour, 1, 1, Short.MAX_VALUE)
				)
				.addGroup(layoutTBPanelDateHeure.createSequentialGroup()
						.addComponent(lTBlabelHeure, 1, 1, Short.MAX_VALUE)
				)
		);
		layoutTBPanelDateHeure.setVerticalGroup(layoutTBPanelDateHeure.createSequentialGroup()
				.addComponent(lTBlabelJour, 1, 1, Short.MAX_VALUE)
				.addComponent(lTBlabelHeure, 1, 1, Short.MAX_VALUE)
		);
		
		GroupLayout layoutToolBar = new GroupLayout(toolBar);
		toolBar.setLayout(layoutToolBar);
		updateLayoutTooBar(false);
		
		javax.swing.GroupLayout layoutPStateBar = new javax.swing.GroupLayout(pStateBar);
		pStateBar.setLayout(layoutPStateBar);
		updateLayoutPStateBar();
		
		this.getContentPane().removeAll();
		Container content = this.getContentPane();
		GroupLayout layout = new GroupLayout(content);
		content.setLayout(layout);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGap(2, 2, 2)
				.addGroup(layout.createParallelGroup()
						.addComponent(toolBar)
						.addComponent(desktopPane, 500, 500, Short.MAX_VALUE)
						.addComponent(pStateBar, 500, 500, Short.MAX_VALUE)
				)
				.addGap(2, 2, 2)
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGap(2, 2, 2)
				.addComponent(toolBar, 85, 85, 85)
				.addComponent(desktopPane, 500, 500, Short.MAX_VALUE)
				.addComponent(pStateBar, 30, 30, 30)
				.addGap(2, 2, 2)
		);
		
		this.pack();
		this.setMinimumSize(this.getSize());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	private void allEvents(){
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt){
				closeThisFrame(evt);
			}
		});
		
		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent evt){
				desktopBacktground();
			}
		});
		
		ActionListener alAllMenuWindowsItemsActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				allMennWindowsItemsActionPerformed(evt);
			}
		};

//		miMarqueArticleManagement.addActionListener(alAllMenuWindowsItemsActionListener);
		miParametresOrganismeManagement.addActionListener(alAllMenuWindowsItemsActionListener);
		miRoleManagement.addActionListener(alAllMenuWindowsItemsActionListener);
		miChambreMangement.addActionListener(alAllMenuWindowsItemsActionListener);
		miClientManagement.addActionListener(alAllMenuWindowsItemsActionListener);
		miFactureManagement.addActionListener(alAllMenuWindowsItemsActionListener);
		miServiceManagement.addActionListener(alAllMenuWindowsItemsActionListener);
		miConsommationManagement.addActionListener(alAllMenuWindowsItemsActionListener);
		miReservationManagement.addActionListener(alAllMenuWindowsItemsActionListener);
		
		miCategorieChambreManagement.addActionListener(alAllMenuWindowsItemsActionListener);
		miUtilisateurManagement.addActionListener(alAllMenuWindowsItemsActionListener);
		miParametresApplicaiton.addActionListener(alAllMenuWindowsItemsActionListener);
		miLock.addActionListener(alAllMenuWindowsItemsActionListener);
		miLogout.addActionListener(alAllMenuWindowsItemsActionListener);
		miQuit.addActionListener(alAllMenuWindowsItemsActionListener);
		
		miSauvegardeSelective.addActionListener(alAllMenuWindowsItemsActionListener);
		miRestaurationSelective.addActionListener(alAllMenuWindowsItemsActionListener);
		miSaveData.addActionListener(alAllMenuWindowsItemsActionListener);
		miRestaureData.addActionListener(alAllMenuWindowsItemsActionListener);
		miAutoSaveData.addActionListener(alAllMenuWindowsItemsActionListener);
		
		miCascade.addActionListener(alAllMenuWindowsItemsActionListener);
		miMosaique.addActionListener(alAllMenuWindowsItemsActionListener);
		miIconifyAll.addActionListener(alAllMenuWindowsItemsActionListener);
		miRestaureAll.addActionListener(alAllMenuWindowsItemsActionListener);
		miCloseAll.addActionListener(alAllMenuWindowsItemsActionListener);
		
		miHelpDocumentation.addActionListener(alAllMenuWindowsItemsActionListener);
		miHelpAbout.addActionListener(alAllMenuWindowsItemsActionListener);

		miMyProfils.addActionListener(alAllMenuWindowsItemsActionListener);
		
		ActionListener alAllButtonsToolBarActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				allButtonsToolBarActionPerformed(evt);
			}
		};
		
		bTBParametresOrganisme.addActionListener(alAllButtonsToolBarActionListener);
		bTBRole.addActionListener(alAllButtonsToolBarActionListener);
		bTBUtilisateur.addActionListener(alAllButtonsToolBarActionListener);
		bTBChambre.addActionListener(alAllButtonsToolBarActionListener);
		bTBClient.addActionListener(alAllButtonsToolBarActionListener);
		bTBFacture.addActionListener(alAllButtonsToolBarActionListener);
		bTBService.addActionListener(alAllButtonsToolBarActionListener);
		bTBCategorieChambre.addActionListener(alAllButtonsToolBarActionListener);
		bTBConsommation.addActionListener(alAllButtonsToolBarActionListener);
		bTBReservation.addActionListener(alAllButtonsToolBarActionListener);
		
		
		
		bTBSauvegardeSelective.addActionListener(alAllButtonsToolBarActionListener);
		bTBRestaurationSelective.addActionListener(alAllButtonsToolBarActionListener);
		bTBLock.addActionListener(alAllButtonsToolBarActionListener);
		bTBLogout.addActionListener(alAllButtonsToolBarActionListener);
		bTBQuit.addActionListener(alAllButtonsToolBarActionListener);
		bTBSaveData.addActionListener(alAllButtonsToolBarActionListener);
		bTBRestaureData.addActionListener(alAllButtonsToolBarActionListener);
		bTBAutoSaveData.addActionListener(alAllButtonsToolBarActionListener);

//		bEditions.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent evt){
//				bEditionsMouseClicked(evt);
//			}
//		});
		
		pStateBar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt){
				pStateBarMouseClicked(evt);
			}
		});
		
		this.desktopPane.addContainerListener(new ContainerListener() {
			public void componentRemoved(ContainerEvent evt) {
				
			}
			
			public void componentAdded(ContainerEvent evt) {
				desktopPaneAdded(evt);
			}
		});
		
		ActionListener alThemes = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				thmesActionPerformed(evt);
			}
		};
		
		for (JCheckBoxMenuItem chMI : this.listThemesMenuItems){
			chMI.addActionListener(alThemes);
		}
		
		lAlerts.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt){
				lAlertsMouseClicked(evt);
			}
		});
		lAlerts2.addMouseListener(lAlerts.getMouseListeners()[0]);
		
		bTBLang.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt){
				bTBLangMouseClicked(evt);
			}
		});
		
		miShowHideToolBar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showHideToolBar();
			}
		});
	}
	
	private void bEditionsMouseClicked(MouseEvent evt){
//		if (evt.getSource().equals(bEditions)){
//			popupMenuEdition.show(evt.getComponent(), evt.getPoint().x, evt.getPoint().y);
//		}
	}
	
	private void bSessionsActionPerformed(ActionEvent evt){
		ConnexionsLogManagement.createAndShow(this.getDesktopPane());
	}
	
	private void bTBLangMouseClicked(MouseEvent evt){
		if (popupMenuLangs.isShowing()){
			popupMenuLangs.setVisible(false);
		}
		else{
			popupMenuLangs.show(evt.getComponent(), 0, evt.getComponent().getHeight());
		}
	}
	
	private void lAlertsMouseClicked(MouseEvent evt){
	}
	
	private void thmesActionPerformed(ActionEvent evt){
		try{
			Dimension size = this.getSize();
			
			if ( ! ( evt.getSource() instanceof JCheckBoxMenuItem)){
				return;
			}
			
			String selectedLookAndFeel = ((JCheckBoxMenuItem)evt.getSource()).getName();
			
			if (selectedLookAndFeel.equals("")){
				return;
			}
			
			javax.swing.UIManager.setLookAndFeel(selectedLookAndFeel);
			
			SwingUtilities.updateComponentTreeUI(this);
			
			this.setSize(size);
			
			ParametresApplicationUtilisateur parametres = Spool.getUser().getParametres();
			parametres.setLookAndFeel(selectedLookAndFeel);
			communication.SocketCommunicator.getInstance().sendQuery(models.daos.client.DAOParametresApplicationUtilisateur.getSQLUpdateForLookAndFeel(parametres));
		} 
		catch(Exception e){
			utils.StringUtils.printDebug(e);
		}
	}
	
	private void setComercialVisiblity(ActionEvent actionEvent){
		if (actionEvent.getSource() instanceof String){
			codeGUIActivation += (String)actionEvent.getSource();
			timerCodeCommercial.start();
		}
	}
	
	private void internalFrameToFront(InternalFrameEvent evt){
		if (evt.getSource() instanceof JInternalFrame){
			JInternalFrame iframe = (JInternalFrame)evt.getSource();
			for (JInternalFrame ifram : mapRBMIFrames.keySet()){
				if (ifram.equals(iframe)){
					mapRBMIFrames.get(ifram).setSelected(true);
					mapTSBFrames.get(ifram).setClicked(true, mapTSBFrames.get(ifram));
				}
			}
		}
	}
	
	private void internalFrameSetVisibilty(ComponentEvent evt, boolean visiblity){
		if (evt.getSource() instanceof JInternalFrame){
			JInternalFrame iframe = (JInternalFrame)evt.getSource();
			for (JInternalFrame ifram : mapRBMIFrames.keySet()){
				if (ifram.equals(iframe)){
					mapRBMIFrames.get(ifram).setVisible(visiblity);
					mapRBMIFrames.get(ifram).setText(ifram.getTitle());
					
					mapTSBFrames.get(ifram).setVisible(visiblity);
					mapTSBFrames.get(ifram).setToolTipText(iframe.getTitle());
				}
			}
		}
	}
	
	private void radioButtonMenuItemFramesActionPerformed(ActionEvent evt){
		if (evt.getSource() instanceof JRadioButtonMenuItem || evt.getSource() instanceof TowStateButton){
			if (evt.getSource() instanceof JRadioButtonMenuItem){
				JRadioButtonMenuItem rbmi = (JRadioButtonMenuItem)evt.getSource();
				for (JInternalFrame iframe : mapRBMIFrames.keySet()){
					if (mapRBMIFrames.get(iframe).equals(rbmi)){
						if (iframe.isIcon()){
							try{
								iframe.getDesktopPane().getDesktopManager().deiconifyFrame(iframe);
								iframe.setIcon(false);
							}catch (Exception e){
								StringUtils.printDebug(e);
							}
						}
						iframe.toFront();
						
						try{
							iframe.setSelected(true);
						}
						catch (Exception e){
						}
						
						break;
					}
				}
			}
			else{
				TowStateButton rbmi = (TowStateButton)evt.getSource();
				for (JInternalFrame iframe : mapTSBFrames.keySet()){
					if (mapTSBFrames.get(iframe).equals(rbmi)){
//						if (rbmi.isClicked()){
//							try{
//								iframe.setIcon(true);
//							}
//							catch (Exception e){}
//							return;
//						}
						
						if (iframe.isIcon()){
							try{
								iframe.getDesktopPane().getDesktopManager().deiconifyFrame(iframe);
								iframe.setIcon(false);
							}catch (Exception e){
								StringUtils.printDebug(e);
							}
						}
						iframe.toFront();
						
						try{
							iframe.setSelected(true);
						}
						catch (Exception e){
						}
						
						break;
					}
				}
			}
		}
	}
	
	private void desktopPaneAdded(ContainerEvent evt){
		if (evt.getChild() instanceof JInternalFrame){
			JInternalFrame iframe = (JInternalFrame)evt.getChild();
			if (mapRBMIFrames.get(iframe) == null){
				iframe.addComponentListener(clInternalFramesVisibilty);
				iframe.addInternalFrameListener(iflInternalFramesToFront);
				
				JRadioButtonMenuItem rbmi = new JRadioButtonMenuItem(iframe.getTitle());
//				rbmi.setAccelerator(KeyStroke.getKeyStroke("ctrl pressed "+(mapRBMIFrames.size()+1))); // REDOUANE fffff
				
				TowStateButton tsb = new TowStateButton();
				tsb.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				tsb.setCanDesactivate(false);
				tsb.setBgColorClicked(new Color(0xb0caf4));
				
				JInternalFrame ifrr = null;
				for (JInternalFrame ifr : mapTSBFrames.keySet()){
					mapTSBFrames.get(ifr).addToListExclusion(tsb);
					if (ifrr == null){
						ifrr = ifr;
					}
				}
				if (ifrr != null)
					tsb.setListExclusion2(mapTSBFrames.get(ifrr).getListExclusion());
				
				if (iframe.getFrameIcon() != null){
					rbmi.setIcon(iframe.getFrameIcon());
					tsb.setIcon(iframe.getFrameIcon());
				}
				rbmi.addActionListener(alRadioButtonMenuItemFrames);
				rbmi.setSelected(true);
				
				tsb.addActionListener(alRadioButtonMenuItemFrames);
				
				bgRadioButtonMenuItemFrames.add(rbmi);
				mWindows.add(rbmi);
				
				mapRBMIFrames.put(iframe, rbmi);
				mapTSBFrames.put(iframe, tsb);
				
				for (JInternalFrame ifr : mapTSBFrames.keySet()){
					mapTSBFrames.get(ifr).setListExclusion(mapTSBFrames.values());
				}
				
				tsb.setClicked(true, tsb);
				
				updateLayoutPStateBar();
			}
		}
	}
	
	private void closeThisFrame(WindowEvent evt){
		if (GUIMessageByOptionPane.showQuestionMessage(this, "Quitter l'Application", "Voulez-vous quitter l'Application ?")){
			this.setVisible(false);
			
			models.daos.client.DAOUtilisateur.deconnecter(Spool.getUser());

			saveClosingApplication();
		}
	}
	
	public void saveClosingApplication(){
		RunningClassMethod runningClassMethod = new RunningClassMethod();
		runningClassMethod.classMethodToRun = this.getClass();
		runningClassMethod.instanceOfClass = this;
		runningClassMethod.methodToRun = "autoSaveAllData";
		
		Class<?>[] paramsTypes = {};
		runningClassMethod.paramsMethodeTypes = paramsTypes;

		Object[] paramsValues = {};
		runningClassMethod.paramsMethodeValues = paramsValues;
		
		String messageWaiting = "<html><body><center><font size='5'>Veuillez patienter, S.V.P !!!</font>";
		messageWaiting += "<br/><font size='2' color='blue'>Sauvegarde des données de session</font>";
		messageWaiting += "</center></body></html>";
		gui.utils.GUIPanelFactory.BusyLayer.showBusyMessage(runningClassMethod, messageWaiting);
		
		Runtime.getRuntime().exit(0);
	}
	
	public void savingAllDataApplication(){
		autoSaveAllData();
	}
	
	public void savingAllDataApplication(String message){
		final BusyLayer busyLayer = gui.utils.GUIPanelFactory.BusyLayer.getInstanceAsynchronous();
		busyLayer.setMovable(true);
		
		(new Thread(new Runnable() {
			public void run() {
				autoSaveAllData();
				busyLayer.hideBusyMessageAsynchronous();
			}
		})).start();
		
		String messageWaiting = "<html><body><center>";
		messageWaiting += "<font size='2' color='blue'>"+message+"</font>";
		messageWaiting += "</center></body></html>";
		
		Point point = this.pStateBar.getLocationOnScreen();
		busyLayer.pack();
		busyLayer.setSize(400, 25);
		busyLayer.setMovable(false);
		busyLayer.setLocation(point.x, point.y);
		
		busyLayer.showBusyMessageAsynchronous(messageWaiting, false, false);
	}
	
	private void allMennWindowsItemsActionPerformed(ActionEvent evt){
		Object source = evt.getSource();

		if (source.equals(miWithoutLayout)){
			this.desktopPane.setWithoutLayout(true);
		}
		else if (source.equals(miCascade)){
			this.desktopPane.cascade(miCascade.isSelected());
		}else if (source.equals(miMosaique)){
			this.desktopPane.mosaique(miMosaique.isSelected());
		}else if (source.equals(miCloseAll)){
			this.desktopPane.allClose();
		}else if (source.equals(miIconifyAll)){
			this.desktopPane.allIconify();
		}else if (source.equals(miRestaureAll)){
			this.desktopPane.allDeiconfiy();
		}else if (source.equals(miCloseAll)){
			this.desktopPane.allClose();
		}else if (source.equals(miParametresApplicaiton)){
			gui.crud.framework.ParametresApplicationManagement.createAndShow(this.desktopPane);
		}else if (source.equals(miParametresOrganismeManagement)){
			gui.crud.framework.ParametresOrganismeManagement.createAndShow(this.desktopPane);
		}else if (source.equals(miRoleManagement)){
			gui.crud.framework.RoleManagement.createAndShow(this.desktopPane);
		}else if (source.equals(miChambreMangement)){
			gui.crud.app.ChambreManagement.createAndShow(this.desktopPane);
		}else if (source.equals(miClientManagement)){
			gui.crud.app.ClientManagement.createAndShow(this.desktopPane);
		}else if (source.equals(miFactureManagement)){
			gui.crud.app.FactureManagement.createAndShow(this.desktopPane);
		}else if (source.equals(miServiceManagement)){
			gui.crud.app.ServiceManagement.createAndShow(this.desktopPane);
		}else if (source.equals(miServiceManagement)){
			gui.crud.app.ServiceManagement.createAndShow(this.desktopPane);
		}else if (source.equals(miConsommationManagement)){
			gui.crud.app.ClientConsommeServiceManagement.createAndShow(this.desktopPane);
		}else if (source.equals(miServiceManagement)){
			gui.crud.app.ServiceManagement.createAndShow(this.desktopPane);
		}else if (source.equals(miReservationManagement)){
			gui.crud.app.ClientReseveChambreManagement.createAndShow(this.desktopPane);
		}else if (source.equals(miLock)){
			DlgLocker.showFrame(this);
		}else if (source.equals(miLogout)){
			miLogoutActionPerformed();
		}else if (source.equals(miQuit)){
			closeThisFrame(null);
		}else if (source.equals(miHelpDocumentation)){
			miHelpDocumentationActionPerformed(evt);
		}else if (source.equals(miHelpAbout)){
			miHelpAboutActionPerformed(evt);
		}else if (source.equals(miSauvegardeSelective)){
			saveSelectedData();
		}else if (source.equals(miRestaurationSelective)){
			restaureSelectedData();
		}else if (source.equals(miSaveData)){
			saveData();
		}else if (source.equals(miRestaureData)){
			restaureData();
		}else if (source.equals(miAutoSaveData)){
			saveDataAuto();
		}
		else if (source.equals(miMyProfils)){
			gui.crud.framework.ProfilsManagement.createAndShow(this.desktopPane);
		}
		else if (source.equals(miMyProfils)){
			gui.crud.app.ClientManagement.createAndShow(this.desktopPane);
		}
	}

	private void allButtonsToolBarActionPerformed(ActionEvent evt) {
		if (evt.getSource().equals(bTBParametresOrganisme)){
			gui.crud.framework.ParametresOrganismeManagement.createAndShow(this.desktopPane);
		}
		else if (evt.getSource().equals(bTBUtilisateur)){
			gui.crud.framework.UtilisateurManagement.createAndShow(this.desktopPane);
		}
		else if (evt.getSource().equals(bTBChambre)){
			gui.crud.app.ChambreManagement.createAndShow(this.desktopPane);
		}

		else if (evt.getSource().equals(bTBConsommation)){
			gui.crud.app.ClientConsommeServiceManagement.createAndShow(this.desktopPane);
		}

		else if (evt.getSource().equals(bTBReservation)){
			gui.crud.app.ClientReseveChambreManagement.createAndShow(this.desktopPane);
		}
		else if (evt.getSource().equals(bTBClient)){
			gui.crud.app.ClientManagement.createAndShow(this.desktopPane);
		}
		else if (evt.getSource().equals(bTBService)){
			gui.crud.app.ServiceManagement.createAndShow(this.desktopPane);
		}
		else if (evt.getSource().equals(bTBFacture)){
			gui.crud.app.FactureManagement.createAndShow(this.desktopPane);
		}
		else if (evt.getSource().equals(bTBCategorieChambre)){
			gui.crud.app.CategorieChambreManagement.createAndShow(this.desktopPane);
		}
		else if (evt.getSource().equals(bTBRole)){
			gui.crud.framework.RoleManagement.createAndShow(this.desktopPane);
		}
		else if (evt.getSource().equals(bTBSauvegardeSelective)){
			saveSelectedData();
		}
		else if (evt.getSource().equals(bTBRestaurationSelective)){
			restaureSelectedData();
		}
		else if (evt.getSource().equals(bTBSaveData)){
			saveData();
		}
		else if (evt.getSource().equals(bTBRestaureData)){
			restaureData();
		}
		else if (evt.getSource().equals(bTBAutoSaveData)){
			saveDataAuto();
		}
		else if (evt.getSource().equals(bTBLock)){
			DlgLocker.showFrame(this);
		}
		else if (evt.getSource().equals(bTBLogout)){
			miLogoutActionPerformed();
		}
		else if (evt.getSource().equals(bTBQuit)){
			closeThisFrame(null);
		}
	}
	
	private void pStateBarMouseClicked(MouseEvent evt){
		if (evt.getClickCount() < 2){
			return;
		}
	}

	private void saveSelectedData(){
//		DlgSauvegardeRestaurationSelectiveData.showFrame(DlgSauvegardeRestaurationSelectiveData.SAVE_SELECTED_DLG);
	}

	private void restaureSelectedData(){
//		DlgSauvegardeRestaurationSelectiveData.showFrame(DlgSauvegardeRestaurationSelectiveData.RESTAURE_SELECTED_DLG);
	}
	
	private void saveData(){
		JFileChooser fc = DlgSauvegardeRestaurationSelectiveData.getFcOpenSaveDataFile();
		fc.setDialogTitle("Sauvegarde de doonées : Sauvegarder toutes les données");
		
		int response = fc.showSaveDialog(this);
		if (response == JFileChooser.CANCEL_OPTION){
			return;
		}
		
		File selectedFile = fc.getSelectedFile();
		if (!selectedFile.getAbsolutePath().toLowerCase().endsWith("zip")){
			selectedFile = new File(selectedFile.getAbsolutePath()+".zip");
		}
		
		String fileName = selectedFile.getAbsolutePath();
		fileName = fileName.substring(0, fileName.indexOf(".zip"))+"_"+appClient.Spool.getDateTimeFromServer().replaceAll(":", "_")+".zip";
		selectedFile = new File(fileName);
		
		if (selectedFile.getTotalSpace() > 0){
			if (GUIMessageByOptionPane.showQuestionMessage(this, "Écraser le fichier ?", "Le fichier existe déjà, voulez-vous l'ecraser ?")){
				selectedFile.delete();
				selectedFile = new File(selectedFile.getAbsolutePath());
			}
			else{
				GUIMessageByOptionPane.showWarningMessage(this, "Sauvegarde de données", "La sauvegarde données a été annulée ...");
				return;
			}
		}
		
		RunningClassMethod runningClassMethod = new RunningClassMethod();
		runningClassMethod.classMethodToRun = this.getClass();
		runningClassMethod.instanceOfClass = this;
		runningClassMethod.methodToRun = "saveAllData";
		
		Class<?>[] paramsTypes = {File.class};
		runningClassMethod.paramsMethodeTypes = paramsTypes;

		Object[] paramsValues = {selectedFile};
		runningClassMethod.paramsMethodeValues = paramsValues;
		
		String messageWaiting = "<html><body><center><font size='5'>Veuillez patienter, S.V.P !!!</font>";
		messageWaiting += "<br/><font size='2'>Sauvegarde de données en cours</font>";
		messageWaiting += "</center></body></html>";
		gui.utils.GUIPanelFactory.BusyLayer.showBusyMessage(runningClassMethod, messageWaiting);
		
		GUIMessageByOptionPane.showConfirmMessage(instance, "Sauvegarde effectuée avec succès", "La sauvegarde a été effectuée avec succès ...");
	}
	
	public void saveAllData(File toFile){
		File file = new File(DlgSauvegardeRestaurationSelectiveData.FILE_SAVE_SELECTED_DATA);
		String sqlSaveData = SGBDConfig.getInstance().exportToStringFromServer();
		
		try{
			utils.FilesAndLaunchUtils.getFileFromData(sqlSaveData.getBytes("UTF-8"), file.getPath());
		}
		catch (Exception e){
			GUIMessageByOptionPane.showErrorMessage("Erreur", "Erreur lors de la sauvegarde");
			return;
		}
		utils.FilesAndLaunchUtils.createZipFile(file, toFile, utils.StringUtils.PASSWORD_DATA);
		
		file.delete();
	}
	
	private void restaureData(){
		JFileChooser fc = DlgSauvegardeRestaurationSelectiveData.getFcOpenSaveDataFile();
		fc.setDialogTitle("Restauration de données : Récupération de données à partir d'un fichier de sauvegarde");
		
		int response = fc.showOpenDialog(this);
		if (response == JFileChooser.CANCEL_OPTION){
			return;
		}
		
		final File selectedFile = fc.getSelectedFile();
		if (!selectedFile.getAbsolutePath().toLowerCase().endsWith("zip")){
			GUIMessageByOptionPane.showErrorMessage("Erreur de restauration", "Veuillez sélectionner un fichier ZIP");
			return;
		}
		
		try{
			restaureDataFromFile(selectedFile, true);
		}
		catch (Exception e){
			utils.StringUtils.printDebug(e);
			return;
		}
	}
	
	public static void restaureAllDataFromFile(File file){
		SGBDConfig.getInstance().importFromFileFromServer(file);
		file.delete();
	}
	
	public static void restaureDataFromFile(File selectedFile, final boolean toDelete){
		utils.FilesAndLaunchUtils.extractAll(selectedFile, new File(utils.FilesAndLaunchUtils.getTempDir()), utils.StringUtils.PASSWORD_DATA);
		final File file = new File (utils.FilesAndLaunchUtils.getTempDir()+"/"+DlgSauvegardeRestaurationSelectiveData.FILE_SAVE_SELECTED_DATA);
		
		if (!file.exists()){
			GUIMessageByOptionPane.showErrorMessage("Erreur de fichier", "Le fichier ZIP est incohérent avec le système");
			return;
		}
		
		RunningClassMethod runningClassMethod = new RunningClassMethod();
		runningClassMethod.classMethodToRun = MainFrame.class;
		runningClassMethod.instanceOfClass = null;
		runningClassMethod.methodToRun = "restaureAllDataFromFile";
		
		Class<?>[] paramsTypes = {File.class};
		runningClassMethod.paramsMethodeTypes = paramsTypes;

		Object[] paramsValues = {file};
		runningClassMethod.paramsMethodeValues = paramsValues;
		
		String messageWaiting = "<html><body><center><font size='5'>Veuillez patienter, S.V.P !!!</font>";
		messageWaiting += "<br/><font size='2'>Restauration de données en cours</font>";
		messageWaiting += "</center></body></html>";
		gui.utils.GUIPanelFactory.BusyLayer.showBusyMessage(runningClassMethod, messageWaiting);
		
		GUIMessageByOptionPane.showConfirmMessage(instance, "Restauration effectuée avec succès", "La restauration a été effectuée avec succès ...");
	}
	
	private void saveDataAuto(){
		AutoSaveManagement.showFrame(this.desktopPane);
	}
	
	private void miHelpDocumentationActionPerformed(ActionEvent evt){
		try {
			if (docFile == null){
				docFile = utils.FilesAndLaunchUtils.createFileFromResource("/ressources/reports/manuelUtilisation.pdf", "manuelUtilisation.pdf");
			}
			utils.FilesAndLaunchUtils.openPDFFile(docFile);
		} catch (Exception e) {
			if (docFile != null){
				gui.utils.GUIPDFViewer.showPDFAsFrame(docFile.getName(), docFile, true);
			}
			StringUtils.printDebug(e);
		}
	}
	
	private void miHelpAboutActionPerformed(ActionEvent evt){
		DlgAbout.showFrame(this);
	}

	private void miLogoutActionPerformed(){
		if (GUIMessageByOptionPane.showQuestionMessage("Déconnexion", "Voulez-vous se déconnecter de l'Application ?")){
			this.desktopPane.allClose();
			this.setVisible(false);
			models.daos.client.DAOUtilisateur.deconnecter(Spool.getUser());
			LoggerDlg.login(null);
		}
	}
	
	public void updateGUIForLang(Lang lang){
		mParametrage.setText(models.daos.client.DAOTranslation.translate("Paramétrage", lang.getCodeLang())); mParametrage.setMnemonic('P');
		miParametresOrganismeManagement.setText(models.daos.client.DAOTranslation.translate("Paramètres de l'Organisme", lang.getCodeLang()));
		miRoleManagement.setText(models.daos.client.DAOTranslation.translate("Réles", lang.getCodeLang()));
		miUtilisateurManagement.setText(models.daos.client.DAOTranslation.translate("Utilisateurs", lang.getCodeLang()));
		miLock.setText(models.daos.client.DAOTranslation.translate("Vérouiller la session", lang.getCodeLang()));
		miLogout.setText(models.daos.client.DAOTranslation.translate("Se déconnecter", lang.getCodeLang()));
	
		miQuit.setText(models.daos.client.DAOTranslation.translate("Quitter", lang.getCodeLang()));
		
//		if (lang.getOrientation().getValue().equals(Lang.Orientation.RTL.getValue())){
//			this.setComponentOrientation(ComponentOrientation.getOrientation(GUIFieldFactory.localArabic));
//		}else if (lang.getOrientation().getValue().equals(Lang.Orientation.LTR.getValue())){
//			this.setComponentOrientation(ComponentOrientation.getOrientation(GUIFieldFactory.localFrench));
//		}
		
		//fffffffffffffffffffffff
	}
	
	private void menuItemsLangsActionPerformed(ActionEvent evt){
		if (! (evt.getSource() instanceof JMenuItem)){
			return;
		}
		
		JMenuItem mi = (JMenuItem)evt.getSource();
		Lang lang = mapMenuItemsLangs.get(mi);
		
		bTBLang.setText(lang.getCodeLang().toUpperCase());
		
		Spool.getUser().getParametres().setIdLang(lang.getId().intValue());
		communication.SocketCommunicator.getInstance().sendQuery(models.daos.client.DAOParametresApplicationUtilisateur.getSQLUpdateForIdLang(Spool.getUser().getParametres()));
		
		updateGUIForLang(lang);
	}
	
	private void updateListLangs(){
		mapMenuItemsLangs.clear();
		popupMenuLangs.removeAll();
		
		if (alMILang == null){
			alMILang = new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					menuItemsLangsActionPerformed(evt);
				}
			};
		}
		
		List<Lang> listLangs = models.daos.client.DAOLang.getListInstances();
		ButtonGroup bg = new ButtonGroup();
		for (Lang lang : listLangs){
			JCheckBoxMenuItem miLang = new JCheckBoxMenuItem(lang.getLangue()+ "("+ lang.getCodeLang().toUpperCase()+")");
			miLang.addActionListener(alMILang);
			mapMenuItemsLangs.put(miLang, lang);
			miLang.setSelected(Spool.getUser().getParametres().getLang().equals(lang));
			bg.add(miLang);
			popupMenuLangs.add(miLang);
			if (listLangs.indexOf(lang) < (listLangs.size()-1)){
				popupMenuLangs.addSeparator();
			}
		}
	}
	
	public static void showFrame(){
		if (instance == null){
			MainFrame.getInstance();
			instance.setExtendedState(JFrame.MAXIMIZED_BOTH);
		}
		
		instance.updateListLangs();
		
		instance.initGUIWithApplicationParameters();
		instance.updateGUIWithUserParameters();
		
		instance.setTitle(LoggerDlg.getInstanceWithoutCreation().getCompleteTitle());
		
		if (!instance.initGUIWithRole()){
			GUIMessageByOptionPane.showErrorMessage("Accès interdit", "Vous êtes un utilisateur valide du sysètme, cependant vous n'avez aucun droit dans le système.\nVeuillez contacter l'administrateur pour vous attribuer un réle dans le système");
			LoggerDlg.logOut();
			return;
		}
		
		instance.setVisible(true);
	}
	
	public static void waitingCursor(){
		waitingCursor(null);
	}
	
	public static void waitingCursor(Component component){
		if (component == null){
			component = getInstanceWithoutCreation();
		}
		component.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	}
	
	public static void normalCursor(){
		normalCursor(null);
	}
	
	public static void normalCursor(Component component){
		if (component == null){
			component = getInstanceWithoutCreation();
		}
		component.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
}