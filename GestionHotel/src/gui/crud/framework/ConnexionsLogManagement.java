package gui.crud.framework;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import utils.StringUtils;

import models.beans.ConnexionsLog;
import models.daos.client.DAOConnexionsLog;

import gui.utils.GUIMessageByOptionPane;
import gui.utils.JInternalFrameManagementModel;

public class ConnexionsLogManagement extends JInternalFrameManagementModel{
	private static final long serialVersionUID = 1L;

	private static ConnexionsLogManagement instance = null;

	private JLabel labelLogin;
	private JLabel labelUuid;
	private JLabel labelDateDeConnexion;
	private JLabel labelDateDeDeconnexion;
	private JLabel labelIp;
	private JLabel labelMac;
	private JLabel labelMachine;
	private JLabel labelConnexionAccepted;
	private JLabel labelMotifError;
	private JLabel labelUtilisateur;

	private javax.swing.JTextField componentLogin;
	private javax.swing.JTextField componentUuid;
	private gui.utils.GUIDate componentDateDeConnexion;
	private gui.utils.GUIDate componentDateDeDeconnexion;
	private javax.swing.JTextField componentIp;
	private javax.swing.JTextField componentMac;
	private javax.swing.JTextField componentMachine;
	private javax.swing.JComboBox componentConnexionAccepted;
	private javax.swing.JTextField componentMotifError;
	private javax.swing.JComboBox componentUtilisateur;

	public static ConnexionsLogManagement getInstance(String title){
		if (instance == null)
			instance = new ConnexionsLogManagement(title);
		instance.initFields();
		return instance;
	}

	public static ConnexionsLogManagement getInstanceWithoutCreation(){
		return instance;
	}

	public ConnexionsLogManagement(String title) {
		super(title);
		myInitComponents();
	}

	private void myInitComponents() {
		spTreeItems.setVisible(false);
		spListItems.setVisible(true);
		splitPaneTreeList.setDividerSize(0);
		pPhoto.setVisible(false);
		pNotification.setVisible(false);
		this.setFrameIcon(null);
		
		labelLogin = new JLabel(views.ViewConnexionsLog.getCaptionForLogin()+" : ");
		labelLogin.setVerticalAlignment(JLabel.CENTER);
		
		labelUuid = new JLabel(views.ViewConnexionsLog.getCaptionForUuid()+" : ");
		labelUuid.setVerticalAlignment(JLabel.CENTER);
		
		labelDateDeConnexion = new JLabel(views.ViewConnexionsLog.getCaptionForDateDeConnexion()+" : ");
		labelDateDeConnexion.setVerticalAlignment(JLabel.CENTER);
		
		labelDateDeDeconnexion = new JLabel(views.ViewConnexionsLog.getCaptionForDateDeDeconnexion()+" : ");
		labelDateDeDeconnexion.setVerticalAlignment(JLabel.CENTER);
		
		labelIp = new JLabel(views.ViewConnexionsLog.getCaptionForIp()+" : ");
		labelIp.setVerticalAlignment(JLabel.CENTER);
		
		labelMac = new JLabel(views.ViewConnexionsLog.getCaptionForMac()+" : ");
		labelMac.setVerticalAlignment(JLabel.CENTER);
		
		labelMachine = new JLabel(views.ViewConnexionsLog.getCaptionForMachine()+" : ");
		labelMachine.setVerticalAlignment(JLabel.CENTER);
		
		labelConnexionAccepted = new JLabel(views.ViewConnexionsLog.getCaptionForConnexionAccepted()+" : ");
		labelConnexionAccepted.setVerticalAlignment(JLabel.CENTER);
		
		labelMotifError = new JLabel(views.ViewConnexionsLog.getCaptionForMotifError()+" : ");
		labelMotifError.setVerticalAlignment(JLabel.CENTER);
		
		labelUtilisateur = new JLabel(views.ViewConnexionsLog.getCaptionForUtilisateur()+" : ");
		labelUtilisateur.setVerticalAlignment(JLabel.CENTER);
		

		componentLogin = views.ViewConnexionsLog.getViewForLogin();
		componentUuid = views.ViewConnexionsLog.getViewForUuid();
		componentDateDeConnexion = views.ViewConnexionsLog.getViewForDateDeConnexion();
		componentDateDeDeconnexion = views.ViewConnexionsLog.getViewForDateDeDeconnexion();
		componentIp = views.ViewConnexionsLog.getViewForIp();
		componentMac = views.ViewConnexionsLog.getViewForMac();
		componentMachine = views.ViewConnexionsLog.getViewForMachine();
		componentConnexionAccepted = (javax.swing.JComboBox)views.ViewConnexionsLog.getViewForConnexionAccepted("JCOMBOBOXE");
		componentMotifError = views.ViewConnexionsLog.getViewForMotifError();
		componentUtilisateur = views.ViewConnexionsLog.getViewForUtilisateur();

//		stpListContent.setDividerLocation(300);
		
		bAjouter.setToolTipText("Ajouter un Journal des Connexions");
		bSupprimer.setToolTipText("Supprimer le Journal des Connexions sélectionné");
		bExporter.setToolTipText("Exporter la liste des connexionsLogs vers un fichier Excel");
		bImporter.setToolTipText("Importer des connexionsLogs à partir d'un fichier Excel");
		bPrint.setEnabled(true);
		
		configureRootPane(this.getRootPane());

		myAllLayouts();
		myAllEvents();
	}

	private void configureRootPane(JRootPane rootPane) {
		InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escPressed");
		rootPane.getActionMap().put("escPressed",
			new AbstractAction("escPressed") {
				private static final long serialVersionUID = 1L;
				
				public void actionPerformed(ActionEvent actionEvent) {
					bFermerActionPerformed(null);
				}
			}
		);
	}

	private void myAllLayouts() {
		GroupLayout layoutPFormulaire = (GroupLayout)pFormulaire.getLayout();
		layoutPFormulaire.setHorizontalGroup(layoutPFormulaire.createSequentialGroup()
			.addGap(1, 1, Short.MAX_VALUE)
			.addGroup(layoutPFormulaire.createParallelGroup()
				.addComponent(labelLogin)
				.addComponent(labelUuid)
				.addComponent(labelDateDeConnexion)
				.addComponent(labelDateDeDeconnexion)
				.addComponent(labelIp)
				.addComponent(labelMac)
				.addComponent(labelMachine)
				.addComponent(labelConnexionAccepted)
				.addComponent(labelMotifError)
				.addComponent(labelUtilisateur)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layoutPFormulaire.createParallelGroup()
				.addComponent(componentLogin, 300, 300, 300)
				.addComponent(componentUuid, 300, 300, 300)
				.addComponent(componentDateDeConnexion, 300, 300, 300)
				.addComponent(componentDateDeDeconnexion, 300, 300, 300)
				.addComponent(componentIp, 300, 300, 300)
				.addComponent(componentMac, 300, 300, 300)
				.addComponent(componentMachine, 300, 300, 300)
				.addComponent(componentConnexionAccepted, 300, 300, 300)
				.addComponent(componentMotifError, 300, 300, 300)
				.addComponent(componentUtilisateur, 300, 300, 300)
			)
			.addGap(1, 1, Short.MAX_VALUE)
		);
		layoutPFormulaire.setVerticalGroup(layoutPFormulaire.createSequentialGroup()
				.addGap(10, 10, 10)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelLogin, 25, 25, 25)
					.addComponent(componentLogin, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelUuid, 25, 25, 25)
					.addComponent(componentUuid, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelDateDeConnexion, 25, 25, 25)
					.addComponent(componentDateDeConnexion, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelDateDeDeconnexion, 25, 25, 25)
					.addComponent(componentDateDeDeconnexion, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelIp, 25, 25, 25)
					.addComponent(componentIp, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelMac, 25, 25, 25)
					.addComponent(componentMac, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelMachine, 25, 25, 25)
					.addComponent(componentMachine, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelConnexionAccepted, 25, 25, 25)
					.addComponent(componentConnexionAccepted, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelMotifError, 25, 25, 25)
					.addComponent(componentMotifError, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelUtilisateur, 25, 25, 25)
					.addComponent(componentUtilisateur, 25, 25, 25)
				)
				.addGap(10, 10, 10)
		);
	}

	private void myAllEvents() {
		if (guiNavigator.isVisible()){
			guiNavigator.setTFNumPageDocumentListener(new DocumentListener() {
				public void removeUpdate(DocumentEvent evt) {
					changedUpdate(evt);
				}

				public void insertUpdate(DocumentEvent evt) {
					changedUpdate(evt);
				}

				public void changedUpdate(DocumentEvent evt) {
					updateListConnexionsLogs();
				}
			});

			guiNavigator.setCBNumberOfItemsActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt){
					guiNavigator.updateNumberItemsInPage();
					int totalConnexionsLogs = guiNavigator.getTotalNumberOfItems();
					int nbItemsInPage = guiNavigator.getNumberItemsInPage();
					int numberOfPage = totalConnexionsLogs / nbItemsInPage;
					if (totalConnexionsLogs % nbItemsInPage > 0)
						numberOfPage++;
					guiNavigator.setNumberOfPages(numberOfPage);
					guiNavigator.setNumeroPage(guiNavigator.getNumPageAsInt());
					
					updateListConnexionsLogs(true);
				}
			});
		}
	}

	protected void tfFilterDocumentUpdated(DocumentEvent evt) {
		updateListConnexionsLogs(true);
	}

	protected void bAjouterActionPerformed(ActionEvent evt) {
		if (selectedItem != null){
			if ((((ConnexionsLog)selectedItem)).getId() == null || (((ConnexionsLog)selectedItem)).getId() == 0){
				notifierErreur("Veuillez valider le connexionsLog que vous venez d'ajouter ...");
				return;
			}
		}

		ConnexionsLog connexionsLog = new ConnexionsLog();

		selectedItem = connexionsLog;
		dlmListItems.addElement(connexionsLog);
		listItems.setSelectedValue(selectedItem, true);
		fillFormulaireByConnexionsLog(connexionsLog);
	}

	protected void bFermerActionPerformed(ActionEvent evt) {
		if (dlgLayerFilter.isVisible()){
			dlgLayerFilter.setVisible(false);
			return;
		}

		if (selectedItem != null){
			if ((((ConnexionsLog)selectedItem)).getId() == null || (((ConnexionsLog)selectedItem)).getId() == 0){
				if (GUIMessageByOptionPane.showQuestionMessage("Un connexionsLog non ajouté", "Voulez-vous quitter sans valider cet connexionsLog ?"))
					fermer();
				else
					return;
			}
		}
		fermer();
	}

	protected void bExporterActionPerformed(ActionEvent evt) {
		fcExportImportXLS.setDialogType(JFileChooser.SAVE_DIALOG | JFileChooser.FILES_ONLY);
		fcExportImportXLS.setDialogTitle("Exporter la liste des connexionsLogs sous format Excel 2003 (*.xls)");
		int response = fcExportImportXLS.showSaveDialog(this);
		if (response != JFileChooser.APPROVE_OPTION){
			return;
		}
		File file = fcExportImportXLS.getSelectedFile();
		if (file.isDirectory()){
			GUIMessageByOptionPane.showErrorMessage("Format Excel 2003", "Veuillez choisir un fichier format Excel 2003 (*.xls)");
			return;
		}

		if (!file.getAbsolutePath().toLowerCase().endsWith(".xls")){
			file = new File(file.getAbsolutePath()+".xls");
		}

		exportToExcelFile(file);
	}

	protected void bImporterActionPerformed(ActionEvent evt) {
		fcExportImportXLS.setDialogType(JFileChooser.OPEN_DIALOG | JFileChooser.FILES_ONLY);
		fcExportImportXLS.setDialogTitle("Importer des connexionsLogs à partir d'un fichier Excel format 2003 (*.xls)");
		int response = fcExportImportXLS.showOpenDialog(this);
		if (response != JFileChooser.APPROVE_OPTION){
			return;
		}
		File file = fcExportImportXLS.getSelectedFile();
		if (file.isDirectory() || !file.getAbsolutePath().toLowerCase().endsWith(".xls")){
			GUIMessageByOptionPane.showErrorMessage("Format Excel 2003", "Veuillez choisir un fichier format Excel 2003 (*.xls)");
			return;
		}

		importFromExcelFile(file);
	}

	protected void bPrintActionPerformed(ActionEvent evt) {
		generateAndShowPDF();
	}

	protected void bSupprimerActionPerformed(ActionEvent evt) {
		if (selectedItem == null || !(selectedItem instanceof ConnexionsLog)){
			notifierErreur("Veuillez sélectionner un connexionsLog ...");
			return;
		}

		if (! GUIMessageByOptionPane.showQuestionMessage("Supprimer un connexionsLog", "Êtes-vous sûr de la suppression de cet connexionsLog ?")){
			return;
		}

		int index = listItems.getSelectedIndex();
		
		ConnexionsLog connexionsLog = (ConnexionsLog)selectedItem;
		if (connexionsLog.getId() != null || connexionsLog.getId() > 0){
			DAOConnexionsLog.delete(connexionsLog);
		}

		dlmListItems.remove(index);
		
		selectedItem = null;
		fillFormulaireByConnexionsLog(null);
	}

	protected void bValiderActionPerformed(ActionEvent evt) {
		if (selectedItem == null || !(selectedItem instanceof ConnexionsLog)){
			notifierErreur("Veuillez sélectionner un ConnexionsLog ...");
			return;
		}

		if (componentLogin.getText().trim().equals("") || componentUuid.getText().trim().equals("") || componentDateDeConnexion.getMySQLDate().equals("") || componentDateDeDeconnexion.getMySQLDate().equals("") || componentIp.getText().trim().equals("") || componentMac.getText().trim().equals("") || componentMachine.getText().trim().equals("") || componentMotifError.getText().trim().equals("")){
			notifierErreur("Veuillez remplir tous les champs, SVP");
			return;
		}

		ConnexionsLog selectedConnexionsLog = (ConnexionsLog) selectedItem;

		if (selectedConnexionsLog.getId() == null || selectedConnexionsLog.getId() == 0){
			selectedConnexionsLog.setId(0);

			selectedConnexionsLog.setLogin(componentLogin.getText().trim());
			selectedConnexionsLog.setUuid(componentUuid.getText().trim());
			selectedConnexionsLog.setDateDeConnexion(StringUtils.getTimestampFromString(componentDateDeConnexion.getMySQLDate()));
			selectedConnexionsLog.setDateDeDeconnexion(StringUtils.getTimestampFromString(componentDateDeDeconnexion.getMySQLDate()));
			selectedConnexionsLog.setIp(componentIp.getText().trim());
			selectedConnexionsLog.setMac(componentMac.getText().trim());
			selectedConnexionsLog.setMachine(componentMachine.getText().trim());
			selectedConnexionsLog.setConnexionAccepted(ConnexionsLog.ConnexionAccepted.getByValue(componentConnexionAccepted.getSelectedItem().toString()));
			selectedConnexionsLog.setMotifError(componentMotifError.getText().trim());
			selectedConnexionsLog.setIdUtilisateur((componentUtilisateur.getSelectedItem() instanceof models.beans.Utilisateur) ? ((models.beans.Utilisateur)componentUtilisateur.getSelectedItem()).getId() : 0);
			
			selectedConnexionsLog.setId((Integer) communication.SocketCommunicator.getInstance().sendQuery(selectedConnexionsLog));
			notifierConfirmation("Journal des Connexions sauvegardé avec succès");
		}else{
			utils.SGBDConfig.InsertUpdateSQLQueries listQueries = new utils.SGBDConfig.InsertUpdateSQLQueries((utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement[])null);
			if (! componentLogin.getText().trim().equals(selectedConnexionsLog.getLogin())){
				selectedConnexionsLog.setLogin(componentLogin.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOConnexionsLog.getSQLUpdateForLoginByPreparedStatement(selectedConnexionsLog));
			}
			if (! componentUuid.getText().trim().equals(selectedConnexionsLog.getUuid())){
				selectedConnexionsLog.setUuid(componentUuid.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOConnexionsLog.getSQLUpdateForUuidByPreparedStatement(selectedConnexionsLog));
			}
			if (selectedConnexionsLog.getDateDeConnexion() == null || ! selectedConnexionsLog.getDateDeConnexion().toString().equals(StringUtils.getTimestampFromString(componentDateDeConnexion.getMySQLDate()))){
				selectedConnexionsLog.setDateDeConnexion(StringUtils.getTimestampFromString(componentDateDeConnexion.getMySQLDate()));
				listQueries.addInsertUpdateSQLQueries(DAOConnexionsLog.getSQLUpdateForDateDeConnexionByPreparedStatement(selectedConnexionsLog));
			}
			if (selectedConnexionsLog.getDateDeDeconnexion() == null || ! selectedConnexionsLog.getDateDeDeconnexion().toString().equals(StringUtils.getTimestampFromString(componentDateDeDeconnexion.getMySQLDate()))){
				selectedConnexionsLog.setDateDeDeconnexion(StringUtils.getTimestampFromString(componentDateDeDeconnexion.getMySQLDate()));
				listQueries.addInsertUpdateSQLQueries(DAOConnexionsLog.getSQLUpdateForDateDeDeconnexionByPreparedStatement(selectedConnexionsLog));
			}
			if (! componentIp.getText().trim().equals(selectedConnexionsLog.getIp())){
				selectedConnexionsLog.setIp(componentIp.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOConnexionsLog.getSQLUpdateForIpByPreparedStatement(selectedConnexionsLog));
			}
			if (! componentMac.getText().trim().equals(selectedConnexionsLog.getMac())){
				selectedConnexionsLog.setMac(componentMac.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOConnexionsLog.getSQLUpdateForMacByPreparedStatement(selectedConnexionsLog));
			}
			if (! componentMachine.getText().trim().equals(selectedConnexionsLog.getMachine())){
				selectedConnexionsLog.setMachine(componentMachine.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOConnexionsLog.getSQLUpdateForMachineByPreparedStatement(selectedConnexionsLog));
			}
			if (selectedConnexionsLog.getConnexionAccepted() == null || ! componentConnexionAccepted.getSelectedItem().toString().equals(selectedConnexionsLog.getConnexionAccepted().getValue())){
				selectedConnexionsLog.setConnexionAccepted(ConnexionsLog.ConnexionAccepted.getByValue(componentConnexionAccepted.getSelectedItem().toString()));
				listQueries.addInsertUpdateSQLQueries(DAOConnexionsLog.getSQLUpdateForConnexionAcceptedByPreparedStatement(selectedConnexionsLog));
			}
			if (! componentMotifError.getText().trim().equals(selectedConnexionsLog.getMotifError())){
				selectedConnexionsLog.setMotifError(componentMotifError.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOConnexionsLog.getSQLUpdateForMotifErrorByPreparedStatement(selectedConnexionsLog));
			}
			if (selectedConnexionsLog.getIdUtilisateur() == null || !(componentUtilisateur.getSelectedItem() instanceof models.beans.Utilisateur) || ((models.beans.Utilisateur)componentUtilisateur.getSelectedItem()).getId().intValue() != selectedConnexionsLog.getIdUtilisateur().intValue()){
				selectedConnexionsLog.setIdUtilisateur(((models.beans.Utilisateur)componentUtilisateur.getSelectedItem()).getId());
				listQueries.addInsertUpdateSQLQueries(DAOConnexionsLog.getSQLUpdateForIdUtilisateurByPreparedStatement(selectedConnexionsLog));
			}
			
			communication.SocketCommunicator.getInstance().sendQuery(listQueries);
			notifierConfirmation("ConnexionsLog modifié avec succès");
		}
		
		dlmListItems.set(dlmListItems.indexOf(selectedItem), selectedConnexionsLog);
	}

	protected void listItemsMouseClicked(MouseEvent evt) {
		selectedItem = listItems.getSelectedValue();
		if (selectedItem == null){
			fillFormulaireByConnexionsLog(null);
			return;
		}

		fillFormulaireByConnexionsLog((ConnexionsLog)selectedItem);
	}

	protected void miDeleteAllActionPerformed(ActionEvent evt) {
		if (GUIMessageByOptionPane.showQuestionMessage("Supprimer Tous ?", "Voulez-vous vraiment supprimer tous les Journal des Connexions ?")){
			DAOConnexionsLog.deleteAll();
			fillFormulaireByConnexionsLog(null);
		}
	}

	private void exportToExcelFile(File file){
		try{
			views.ViewConnexionsLog.exportToExcel(file);
		}
		catch (Exception e){
			GUIMessageByOptionPane.showErrorMessage("Erreur de génération d'Excel", "Errueur de génération de l'Excel");
			StringUtils.printDebug(e);
		}
	}

	private void importFromExcelFile(File file){
		views.ViewConnexionsLog.importFromExcel(file);
	}

	protected void emptyFields() {
		this.componentLogin.setText("");
		this.componentLogin.setEnabled(false);
		this.labelLogin.setEnabled(false);

		this.componentUuid.setText("");
		this.componentUuid.setEnabled(false);
		this.labelUuid.setEnabled(false);

		this.componentDateDeConnexion.clear();
		this.componentDateDeConnexion.setEnabled(false);
		this.labelDateDeConnexion.setEnabled(false);

		this.componentDateDeDeconnexion.clear();
		this.componentDateDeDeconnexion.setEnabled(false);
		this.labelDateDeDeconnexion.setEnabled(false);

		this.componentIp.setText("");
		this.componentIp.setEnabled(false);
		this.labelIp.setEnabled(false);

		this.componentMac.setText("");
		this.componentMac.setEnabled(false);
		this.labelMac.setEnabled(false);

		this.componentMachine.setText("");
		this.componentMachine.setEnabled(false);
		this.labelMachine.setEnabled(false);

		this.componentConnexionAccepted.setSelectedIndex(0);
		this.componentConnexionAccepted.setEnabled(false);
		this.labelConnexionAccepted.setEnabled(false);

		this.componentMotifError.setText("");
		this.componentMotifError.setEnabled(false);
		this.labelMotifError.setEnabled(false);

		this.componentUtilisateur.setSelectedIndex(0);
		this.componentUtilisateur.setEnabled(false);
		this.labelUtilisateur.setEnabled(false);

	}

	protected void initFields() {
		this.pNotification.setVisible(JInternalFrameManagementModel.isVisibilityNotificationBar());

		selectedItem = null;

		setPhoto(null, false);

		if (guiNavigator.isVisible()){
			guiNavigator.updateNumberItemsInPage();
			int totalConnexionsLogs = DAOConnexionsLog.getCountInTable();
			int nbItemsInPage = guiNavigator.getNumberItemsInPage();
			int numberOfPage = totalConnexionsLogs / nbItemsInPage;
			if (totalConnexionsLogs % nbItemsInPage > 0)
				numberOfPage++;
			guiNavigator.setTotalNumberOfItems(totalConnexionsLogs);
			guiNavigator.setNumberOfPages(numberOfPage);
			guiNavigator.setNumeroPage(1);
		}

		updateListConnexionsLogs(true);

		
		componentUtilisateur.removeAllItems();
		List<models.beans.Utilisateur> listUtilisateurs = models.daos.client.DAOUtilisateur.getListInstances();
		for (models.beans.Utilisateur instance : listUtilisateurs){
			componentUtilisateur.addItem(instance);
		}
		componentUtilisateur.insertItemAt("Choisir un ", 0);
		
		fillFormulaireByConnexionsLog(null);
	}

	private synchronized void updateListConnexionsLogs(){
		updateListConnexionsLogs(false);
	}

	private synchronized void updateListConnexionsLogs(boolean filtering){
		int first = 0;
		int count = 0;
		
		boolean hasLimit = guiNavigator.isVisible();
		
		if (hasLimit){
			if (guiNavigator.getFirst() < 0)
				return;
			first = guiNavigator.getFirst();
			count = guiNavigator.getCount();
		}
		
		String orderByLimit = "ORDER BY `id` ASC "+(hasLimit ? (" limit "+first+", "+count) : "");
		String condition = "";
		
		String filterText = tfFilter.getText().trim();
		if (filterText.equals("")){
			condition = "WHERE 1 ";
		}
		else{
			condition = "WHERE 1 ";
		}
		
		condition += orderByLimit;
		
		if (guiNavigator.isVisible()){
			if (filtering){
				guiNavigator.updateNumberItemsInPage();
				int totalConnexionsLogs = DAOConnexionsLog.getCountInTable(condition);
				int nbItemsInPage = guiNavigator.getNumberItemsInPage();
				int numberOfPage = totalConnexionsLogs / nbItemsInPage;
				if (totalConnexionsLogs % nbItemsInPage > 0)
					numberOfPage++;
				guiNavigator.setTotalNumberOfItems(totalConnexionsLogs);
				guiNavigator.setNumberOfPages(numberOfPage);
				guiNavigator.setNumeroPage(1);
			}
			if (guiNavigator.getFirst() < 0)
				return;
		}
		
		dlmListItems.clear();
		List<ConnexionsLog> listConnexionsLogs = DAOConnexionsLog.getListInstances(condition);
		for (ConnexionsLog connexionsLog : listConnexionsLogs){
			dlmListItems.addElement(connexionsLog);
		}
	}

	protected void fillFormulaireByConnexionsLog(ConnexionsLog connexionsLog) {
//		pFormulaire.setVisible(connexionsLog != null);
		if (connexionsLog == null){
			emptyFields();
			return;
		}

		this.componentLogin.setText(String.valueOf(connexionsLog.getLogin()));
		this.componentLogin.setEnabled(true);
		this.labelLogin.setEnabled(true);

		this.componentUuid.setText(String.valueOf(connexionsLog.getUuid()));
		this.componentUuid.setEnabled(true);
		this.labelUuid.setEnabled(true);

		if (connexionsLog.getDateDeConnexion() == null)
			this.componentDateDeConnexion.clear();
		else
			this.componentDateDeConnexion.setMySQLDate(connexionsLog.getDateDeConnexion().toString());
		this.componentDateDeConnexion.setEnabled(true);
		this.labelDateDeConnexion.setEnabled(true);

		if (connexionsLog.getDateDeDeconnexion() == null)
			this.componentDateDeDeconnexion.clear();
		else
			this.componentDateDeDeconnexion.setMySQLDate(connexionsLog.getDateDeDeconnexion().toString());
		this.componentDateDeDeconnexion.setEnabled(true);
		this.labelDateDeDeconnexion.setEnabled(true);

		this.componentIp.setText(String.valueOf(connexionsLog.getIp()));
		this.componentIp.setEnabled(true);
		this.labelIp.setEnabled(true);

		this.componentMac.setText(String.valueOf(connexionsLog.getMac()));
		this.componentMac.setEnabled(true);
		this.labelMac.setEnabled(true);

		this.componentMachine.setText(String.valueOf(connexionsLog.getMachine()));
		this.componentMachine.setEnabled(true);
		this.labelMachine.setEnabled(true);

		if (connexionsLog.getConnexionAccepted() != null)
			this.componentConnexionAccepted.setSelectedItem(connexionsLog.getConnexionAccepted().getValue());
		else
			this.componentConnexionAccepted.setSelectedIndex(0);
		this.componentConnexionAccepted.setEnabled(true);
		this.labelConnexionAccepted.setEnabled(true);

		this.componentMotifError.setText(String.valueOf(connexionsLog.getMotifError()));
		this.componentMotifError.setEnabled(true);
		this.labelMotifError.setEnabled(true);

		this.componentUtilisateur.setSelectedIndex(0);
		for (int index=1; index<this.componentUtilisateur.getItemCount(); index++){
			models.beans.Utilisateur utilisateur = (models.beans.Utilisateur)this.componentUtilisateur.getItemAt(index);
			if (utilisateur.getId().intValue() == connexionsLog.getIdUtilisateur().intValue()){
				this.componentUtilisateur.setSelectedIndex(index);
				break;
			}
		}
		this.labelUtilisateur.setEnabled(true);
		this.componentUtilisateur.setEnabled(true);
		
		listItems.setSelectedIndex(dlmListItems.indexOf(connexionsLog));
	}

	private void generateAndShowPDF(){
		try{
			File file = File.createTempFile( "ListeConnexionsLogs", ".pdf");
			file.deleteOnExit();
			
			views.ViewConnexionsLog.exportToPDF(file);
			
			utils.FilesAndLaunchUtils.openPDFFile(file);
		}
		catch (Exception e){
			StringUtils.printDebug(e);
		}
	}
	
	public static void createAndShow(){
		createAndShow(null);
	}
	
	public static void createAndShow(JDesktopPane desktop){
		createAndShow(desktop, true);
	}
	
	public static void createAndShow(JDesktopPane desktop, boolean show){
		if (instance != null && instance.isVisible()){
			if (instance.isIcon){
				instance.getDesktopPane().getDesktopManager().deiconifyFrame(instance);
				try{
					instance.setIcon(false);
				}catch (Exception e){
				}
			}

			instance.toFront();
			instance.requestFocus();
			
			try{
				instance.setSelected(true);
			}
			catch (Exception e){
				utils.StringUtils.printDebug(e);
			}

			return;
		}

		getInstance("Gestion des Journal des Connexions ...");
		if (!instance.isAdded()){
			Dimension size = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
			
			if (desktop != null){
				size = desktop.getSize();
				instance.setAdded(true);
				desktop.add(instance);
			}
			
			int width = 1050;
			int height = 550;

			instance.setBounds((size.width - width) / 2, (size.height - height) / 2, width, height);
		}

		if (instance.isIcon){
			try{
				instance.getDesktopPane().getDesktopManager().deiconifyFrame(instance);
				instance.setIcon(false);
			}catch (Exception e){
				StringUtils.printDebug(e);
			}
		}

		if (show){
			instance.setVisible(true);
			instance.toFront();
			instance.requestFocus();
		}
	}
	
	public static void showInJFrame(){
		gui.utils.GUIRibbonFrameFactory.PrepareRibbonFrameConfig();
		
		createAndShow(null, false);
		javax.swing.JFrame frame = new javax.swing.JFrame("Test of access to Table : connexionsLog");
		frame.setSize(1000, 550);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(instance.getContentPane());
		frame.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
		
		frame.setVisible(true);
	}
	
	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				gui.utils.GUIRibbonFrameFactory.PrepareRibbonFrameConfig();
				
				gui.LoggerDlg.sAuthentifier("root", "root");
				showInJFrame();
			}
		});
	}

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/
}