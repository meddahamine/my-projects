package gui;

import gui.utils.GUIButtonFactory;
import gui.utils.GUIFieldFactory;
import gui.utils.GUIMessageByOptionPane;
import gui.utils.GUIPanelFactory.BusyLayer.RunningClassMethod;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.UUID;

import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle;

import appClient.Spool;

public class LoggerDlg extends JDialog{
	private static final long serialVersionUID = 1L;
	
	private static LoggerDlg instance = null;
	
	private static String 								completeTitle = null;
	
	private static boolean connected = false;
	private static UUID uuid = UUID.randomUUID();

	private JLabel			lTitle;
	private JLabel			lLogin;
	private JLabel			lMDP;
	private JTextField 		tfLogin;
	private JPasswordField 	tfMDP;
	private JSeparator		sepBas, sepHaut, sepMid;
	private JButton			bOk;
	private JButton			bAnnuler;
	private JButton			bChangerMDP;
	
	public static LoggerDlg getInstance(boolean model){
		if (instance == null){
			instance = new LoggerDlg(null, model);
			if (Spool.getParametresApplication().getPhoto() != null){
				instance.setIconImage(Spool.getParametresApplication().getPhoto().getImageIcon().getImage());
			}
		}
		instance.initAll();
		return instance;
	}
	
	public static LoggerDlg getInstanceWithoutCreation(){
		return instance;
	}

	private LoggerDlg(JFrame parent, boolean model){
		super (parent, model);
		initComponents();
	}
	
	public String getCompleteTitle() {
		if (completeTitle != null){
			return completeTitle;
		}
		
		completeTitle = this.getTitle();
		if (appClient.Spool.getUser() == null || appClient.Spool.getUser().getRole() == null){
			return completeTitle;
		}
		
		models.beans.Role role = appClient.Spool.getUser().getRole();
		completeTitle += " - Vous vous êtes connecté en tantque "+role.getDesignation();
		
		return completeTitle;
	}
	
	public static void login(JFrame parent) {
		connected = false;
		LoggerDlg.getInstance(true);
		while (!connected){
			instance.initAll();
			instance.tfLogin.requestFocus();
			instance.setVisible(true);
		}
	}
	
	private void initComponents(){
		lTitle = new JLabel("Authentification");
		lTitle.setFont(new Font("", Font.BOLD, 18));
		lLogin = new JLabel("Nom d'utilisateur (Login) :");
		lMDP = new JLabel("Mot de passe :");
		tfLogin = GUIFieldFactory.createSimpleTextField();
		tfMDP = GUIFieldFactory.createSimplePasswordField();
		
		bOk = GUIButtonFactory.createValidateButton("OK");
		bAnnuler = GUIButtonFactory.createExitButton("Annuler");
		bChangerMDP = GUIButtonFactory.createPasswordButton("Changer le mot de passe");
		
		sepBas = new JSeparator();
		sepHaut = new JSeparator();
		sepMid = new JSeparator();
		
		this.getRootPane().setDefaultButton(bOk);
		
		getDateDuJourServeur();
		
		configureRootPane(this.getRootPane());
		
		allLayout();
		allEvents();
	}
	
	private void configureRootPane(JRootPane rootPane) {
		InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escPressed");
		rootPane.getActionMap().put(
		"escPressed",
		new AbstractAction("escPressed") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent actionEvent) {
				actionPerformedbAnnuler(null);
			}
		});
	}
	
	private void initAll(){
		this.getRootPane().setDefaultButton(bOk);
		tfMDP.setText("");
		tfLogin.setText("");
		
		tfLogin.requestFocus();
	}
	
	private void allLayout(){
		Container container = this.getContentPane();
		GroupLayout layout = new GroupLayout(container);
		container.setLayout(layout);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGap(2, 2, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup()
						.addGroup(layout.createSequentialGroup()
								.addGap(10, 10, Short.MAX_VALUE)
								.addComponent(lTitle)
								.addGap(10, 10, Short.MAX_VALUE)
						)
						.addGroup(layout.createSequentialGroup()
								.addGap(2, 2, 2)
								.addComponent(sepHaut, 400, 400, Short.MAX_VALUE)
								.addGap(2, 2, 2)
						)
						.addGroup(layout.createSequentialGroup()
								.addGap(10, 10, Short.MAX_VALUE)
								.addGroup(layout.createParallelGroup()
										.addGroup(layout.createSequentialGroup()
												.addGap(2, 2, Short.MAX_VALUE)
												.addComponent(lLogin)
												.addGap(2, 2, 2)
										)
										.addGroup(layout.createSequentialGroup()
												.addGap(2, 2, Short.MAX_VALUE)
												.addComponent(lMDP)
												.addGap(2, 2, 2)
										)
								)
								.addGroup(layout.createParallelGroup()
										.addComponent(tfLogin, 200, 200, 200)
										.addComponent(tfMDP, 200, 200, 200)
								)
								.addGap(10, 10, Short.MAX_VALUE)
						)
						.addGroup(layout.createSequentialGroup()
								.addGap(2, 2, 2)
								.addComponent(sepMid, 400, 400, Short.MAX_VALUE)
								.addGap(2, 2, 2)
						)
						.addGroup(layout.createSequentialGroup()
								.addGap(10, 10, Short.MAX_VALUE)
								.addComponent(bOk, 130, 130, 130)
								.addGap(10, 10, Short.MAX_VALUE)
								.addComponent(bAnnuler, 130, 130, 130)
								.addGap(10, 10, Short.MAX_VALUE)
						)
						.addGroup(layout.createSequentialGroup()
								.addGap(2, 2, 2)
								.addComponent(sepBas, 400, 400, Short.MAX_VALUE)
								.addGap(2, 2, 2)
						)
						.addGroup(layout.createSequentialGroup()
								.addGap(100, 100, Short.MAX_VALUE)
								.addComponent(bChangerMDP, 300, 300, 300)
								.addGap(100, 100, Short.MAX_VALUE)
						)
				)
				.addGap(2, 2, Short.MAX_VALUE)
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGap(10)
				.addComponent(lTitle)
				.addGap(10)
				.addComponent(sepHaut, 2, 2, 2)
				.addGap(10)
				.addGroup(layout.createParallelGroup()
						.addComponent(lLogin)
						.addComponent(tfLogin, 20, 20, 20)
				)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup()
						.addComponent(lMDP)
						.addComponent(tfMDP, 20, 20, 20)
				)
				.addGap(10)
				.addComponent(sepMid, 2, 2, 2)
				.addGap(10)
				.addGroup(layout.createParallelGroup()
						.addComponent(bOk, 30, 30, 30)
						.addComponent(bAnnuler, 30, 30, 30)
				)
				.addGap(10)
				.addComponent(sepBas, 2, 2, 2)
				.addGap(10)
				.addComponent(bChangerMDP, 30, 30, 30)
				.addGap(10)
		);
		
		this.setTitle(Spool.getParametresApplication().getDesignation()+" - "+utils.StringUtils.formatDateFromMySQL(Spool.getDateDuJour()));
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	private void allEvents(){
		this.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e){
				windowClosedThis(e);
			}
			
			public void windowClosing(WindowEvent e){
				windowClosingThis(e);
			}
		});
		
		bOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedbOk(e);
			}
		});
		
		bAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedbAnnuler(e);
			}
		});
		
		bChangerMDP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bChangerMDPActionPerformed(e);
			}
		});
	}
	
	private void windowClosedThis(WindowEvent e){
		this.dispose();
	}
	
	private void windowClosingThis(WindowEvent e){
		Runtime.getRuntime().exit(0);
	}
	
	@SuppressWarnings("deprecation")
	private void actionPerformedbOk(ActionEvent e){
		final String login = tfLogin.getText();
		final String mdp	= tfMDP.getText();
		
		if (login.equals("") || mdp.equals("")){
			GUIMessageByOptionPane.showErrorMessage(this, "Chmaps vides", "svp, veuillez remplir tous les champs");
			return;
		}
		
//		if (!StringUtils.isPasswordWellFormated(mdp)){
//			GUIMessageByOptionPane.showErrorMessage("Erreur lors d'authentification", "Erreur d'authentification : Login / mot de passe erroné ...");
//			return;
//		}
		
		sAuthentifier(login, mdp);
		
		if (!LoggerDlg.connected){
 			GUIMessageByOptionPane.showErrorMessage("Erreur lors d'authentification", appClient.Spool.getUser().getErrorsAsString());
 		}
 		else{
			this.setVisible(false);
			MainFrame.showFrame();
 		}
	}
	
	public static void sAuthentifier(String login, String mdp){
		String messageWaiting = "<html><body><center><font size='5'>Veuillez patienter, S.V.P !!!</font>";
		messageWaiting += "<br/><font size='2' color='blue'>Vérification du login et du mot de passe</font>";
		messageWaiting += "</center></body></html>";
		
		RunningClassMethod runningClassMethod = new RunningClassMethod();
		runningClassMethod.classMethodToRun = LoggerDlg.class;
		runningClassMethod.instanceOfClass = null;
		runningClassMethod.methodToRun = "authentifier";
		
		Class<?>[] paramsTypes = {String.class, String.class};
		runningClassMethod.paramsMethodeTypes = paramsTypes;

		Object[] paramsValues = {login, mdp};
		runningClassMethod.paramsMethodeValues = paramsValues;
		
 		gui.utils.GUIPanelFactory.BusyLayer.showBusyMessage(runningClassMethod, messageWaiting);
	}
	
	public static void authentifier(String login, String mdp){
		LoggerDlg.connected = false;
		models.beans.Utilisateur user = models.daos.client.DAOUtilisateur.connecter(login, mdp, uuid.toString());
		Spool.setUser(user);
		
		if (user.getErrors().size() > 0) {
			return;
		}

		LoggerDlg.connected = true;
	}
	
	private void actionPerformedbAnnuler(ActionEvent e){
		this.dispose();
		Runtime.getRuntime().exit(0);
	}
	
	public void bChangerMDPActionPerformed(ActionEvent e){
		ModifierPassWord.getInstance(this).setVisible(true);
	}
	
	public static String getUUID() {
		return uuid.toString();
	}
	
	public static void logOut() {
		models.daos.client.DAOUtilisateur.deconnecter(Spool.getUser());
		Spool.setUser(null);
		
		completeTitle = null;
		uuid = UUID.randomUUID();
		
//		if (MainFrame.getInstanceWithoutCreation() != null){
//			MainFrame.getInstanceWithoutCreation().setVisible(false);
//		}
//		
//		appClient.MTEE.createAndShowGUI();
	}
	
	private void getDateDuJourServeur(){
		if (Spool.getDateDuJour().equals("0000-00-00")){
			Spool.getDateDuJourServeur();
		}
	}
	
	public static String getDateTimeFromServer(){
		return (String)communication.SocketCommunicator.getInstance().sendQuery("getDateTimeFromServer");
	}
	
	public static String getDateDuJour() {
		return Spool.getDateDuJour();
	}

	public static class ModifierPassWord extends JDialog{
		private static final long serialVersionUID = 1L;
		
		private static ModifierPassWord instance = null;
		
		private JLabel			lTitle, lLogin, lOldMDP, lNewMDP, lNewMDP2;
		private JTextField		tfLogin;
		private JPasswordField	tfOldMDP, tfNewMDP, tfNewMDP2;
		private JButton 		bModifier, bAnnuler;
		private JSeparator		sepHaut, sepBas;
		
		private JPanel	gcpContent;
		
		private ModifierPassWord(JFrame parent, boolean model){
			super (parent, model);
			
			initCompoents();
		}
		
		private ModifierPassWord(JDialog parent, boolean model){
			super (parent, model);
			
			initCompoents();
		}
		
		public static ModifierPassWord getInstance(JFrame parent){
			if (instance == null)
				instance = new ModifierPassWord(parent, true);
			instance.initAll();
			return instance;
		}
		
		public static ModifierPassWord getInstance(JDialog parent){
			if (instance == null)
				instance = new ModifierPassWord(parent, true);
			instance.initAll();
			return instance;
		}
		
		private void initAll(){
			this.getRootPane().setDefaultButton(bModifier);
			
			this.tfLogin.setText("");
			this.tfOldMDP.setText("");
			this.tfNewMDP.setText("");
			this.tfNewMDP2.setText("");
			
			this.tfLogin.requestFocus();
		}
		
		private void initCompoents(){
			gcpContent = new JPanel();
			this.setContentPane(gcpContent);
			
			lTitle = new JLabel("Modifier Son Mot de Passe");
			lTitle.setFont(new Font("", Font.BOLD, 18));
			lLogin = new JLabel("Login : ");
			lOldMDP = new JLabel("Mot de passe en cours : ");
			lNewMDP = new JLabel("Nouveau mot de passe : ");
			lNewMDP2 = new JLabel("Nouveau mot de passe (confirmation) : ");
			
			tfLogin = GUIFieldFactory.createSimpleTextField();
			tfOldMDP = GUIFieldFactory.createSimplePasswordField();
			tfNewMDP = GUIFieldFactory.createSimplePasswordField();
			tfNewMDP2 = GUIFieldFactory.createSimplePasswordField();
			
			sepHaut = new JSeparator();
			sepBas = new JSeparator();
			
			bModifier = GUIButtonFactory.createValidateButton("Modifier");			
			bAnnuler = GUIButtonFactory.createCancelButton("Annuler");

			configureRootPane(this.getRootPane());

			allLayout();
			allEvents();
		}
		
		private void configureRootPane(JRootPane rootPane) {
			InputMap inputMap = rootPane
					.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
			inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
					"escPressed");
			rootPane.getActionMap().put("escPressed",
					new AbstractAction("escPressed") {
						private static final long serialVersionUID = 1L;

						public void actionPerformed(ActionEvent actionEvent) {
							bAnnulerActionListener(actionEvent);
						}
					});
		}
		
		private void allLayout(){
			Container container = this.getContentPane();
			GroupLayout layout = new GroupLayout(container);
			container.setLayout(layout);
			
			layout.setHorizontalGroup(layout.createSequentialGroup()
					.addGap(2, 2, Short.MAX_VALUE)
					.addGroup(layout.createParallelGroup()
							.addGroup(layout.createSequentialGroup()
									.addGap(10, 10, Short.MAX_VALUE)
									.addComponent(lTitle)
									.addGap(10, 10, Short.MAX_VALUE)
							)
							.addGroup(layout.createSequentialGroup()
									.addGap(2, 2, 2)
									.addComponent(sepHaut, 400, 400, Short.MAX_VALUE)
									.addGap(2, 2, 2)
							)
							.addGroup(layout.createSequentialGroup()
									.addGap(10, 10, Short.MAX_VALUE)
									.addGroup(layout.createParallelGroup()
											.addGroup(layout.createSequentialGroup()
													.addGap(2, 2, Short.MAX_VALUE)
													.addComponent(lLogin)
													.addGap(2, 2, 2)
											)
											.addGroup(layout.createSequentialGroup()
													.addGap(2, 2, Short.MAX_VALUE)
													.addComponent(lOldMDP)
													.addGap(2, 2, 2)
											)
											.addGroup(layout.createSequentialGroup()
													.addGap(2, 2, Short.MAX_VALUE)
													.addComponent(lNewMDP)
													.addGap(2, 2, 2)
											)
											.addGroup(layout.createSequentialGroup()
													.addGap(2, 2, Short.MAX_VALUE)
													.addComponent(lNewMDP2)
													.addGap(2, 2, 2)
											)
									)
									.addGroup(layout.createParallelGroup()
											.addComponent(tfLogin, 200, 200, 200)
											.addComponent(tfOldMDP, 200, 200, 200)
											.addComponent(tfNewMDP, 200, 200, 200)
											.addComponent(tfNewMDP2, 200, 200, 200)
									)
									.addGap(10, 10, Short.MAX_VALUE)
							)
							.addGroup(layout.createSequentialGroup()
									.addGap(2, 2, 2)
									.addComponent(sepBas, 400, 400, Short.MAX_VALUE)
									.addGap(2, 2, 2)
							)
							.addGroup(layout.createSequentialGroup()
									.addGap(10, 10, Short.MAX_VALUE)
									.addComponent(bModifier, 130, 130, 130)
									.addGap(10, 10, Short.MAX_VALUE)
									.addComponent(bAnnuler, 130, 130, 130)
									.addGap(10, 10, Short.MAX_VALUE)
							)
					)
					.addGap(2, 2, Short.MAX_VALUE)
			);
			
			layout.setVerticalGroup(layout.createSequentialGroup()
					.addGap(10)
					.addComponent(lTitle)
					.addGap(10)
					.addComponent(sepHaut, 2, 2, 2)
					.addGap(10)
					.addGroup(layout.createParallelGroup()
							.addComponent(lLogin)
							.addComponent(tfLogin, 20, 20, 20)
					)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup()
							.addComponent(lOldMDP)
							.addComponent(tfOldMDP, 20, 20, 20)
					)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup()
							.addComponent(lNewMDP)
							.addComponent(tfNewMDP, 20, 20, 20)
					)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup()
							.addComponent(lNewMDP2)
							.addComponent(tfNewMDP2, 20, 20, 20)
					)
					.addGap(10)
					.addComponent(sepBas, 2, 2, 2)
					.addGap(10)
					.addGroup(layout.createParallelGroup()
							.addComponent(bModifier, 30, 30, 30)
							.addComponent(bAnnuler, 30, 30, 30)
					)
					.addGap(10)
			);
			
			this.setTitle("Modification de mot de passe");
			this.getRootPane().setDefaultButton(bAnnuler);
			this.pack();
			this.setLocationRelativeTo(null);
		}
		
		private void allEvents(){
			this.addWindowListener(new WindowAdapter() {
				public void windowClosed(WindowEvent e){
					windowClosedThis(e);
				}
			});
			
			bModifier.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					bModifierActionListener(e);
				}
			});
			
			bAnnuler.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					bAnnulerActionListener(e);
				}
			});
		}
		
		private void windowClosedThis(WindowEvent e){
			this.dispose();
		}
		
		@SuppressWarnings("deprecation")
		private void bModifierActionListener(ActionEvent e){
			String login = tfLogin.getText();
			String oldMDP = tfOldMDP.getText();
			String newMDP = tfNewMDP.getText();
			String newMDP2 = tfNewMDP2.getText();
			
			if (login.equals("") || oldMDP.equals("") || newMDP.equals("") || newMDP2.equals("")){
				GUIMessageByOptionPane.showErrorMessage(this, "Chmaps vides", "Veuillez, SVP, remplir tous les champs");
				return;
			}
			
			if (!newMDP.equals(newMDP2)){
				GUIMessageByOptionPane.showErrorMessage(this, "Nouveau mot de passe erroné", "Erreur dans le nouveau mot de passe, Veuillez réintroduire le nouveau mot de passe");
				return;
			}
			
//			if (!StringUtils.isPasswordWellFormated(oldMDP) || !StringUtils.isPasswordWellFormated(newMDP)){
//				GUIMessageByOptionPane.showErrorMessage(this, "Erreur de Login/Mot de passe", "Le login et/ou le mot de passe sont erronés ...");
//				return;
//			}
			
			if (newMDP.equals(oldMDP)){
				GUIMessageByOptionPane.showErrorMessage(this, "Même mot de passe", "Vous n'avez pas modifié votre mot de passe !!!");
				return;
			}
			
			if (models.daos.client.DAOUtilisateur.modifierPassword(login, oldMDP, newMDP)){
				GUIMessageByOptionPane.showConfirmMessage("Succès", "Le mot de passe a été modifié avec succès ...");
				this.setVisible(false);
			}else{
				GUIMessageByOptionPane.showErrorMessage("Erreur", "Votre 'login/mot de passe' sont incorrectes ...");
				tfLogin.requestFocus();
			}
		}
		
		private void bAnnulerActionListener(ActionEvent e){
			this.tfOldMDP.setText("");
			this.tfNewMDP.setText("");
			this.tfNewMDP2.setText("");
			this.tfLogin.setText("");
			
			this.setVisible(false);
		}
	}
}