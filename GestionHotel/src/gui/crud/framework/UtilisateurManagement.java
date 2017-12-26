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

import models.beans.Utilisateur;
import models.daos.client.DAOUtilisateur;

import gui.utils.GUIMessageByOptionPane;
import gui.utils.JInternalFrameManagementModel;

public class UtilisateurManagement extends JInternalFrameManagementModel{
	private static final long serialVersionUID = 1L;

	private static UtilisateurManagement instance = null;

	private JLabel labelNom;
	private JLabel labelPrenom;
	private JLabel labelCivilite;
	private JLabel labelDateNaissance;
	private JLabel labelLieuNaissance;
	private JLabel labelLogin;
	private JLabel labelPassword;
	private JLabel labelRole;
	private JLabel labelParametres;

	private javax.swing.JTextField componentNom;
	private javax.swing.JTextField componentPrenom;
	private javax.swing.JComboBox componentCivilite;
	private gui.utils.GUIDate componentDateNaissance;
	private javax.swing.JTextField componentLieuNaissance;
	private javax.swing.JTextField componentLogin;
	private javax.swing.JPasswordField componentPassword;
	private javax.swing.JComboBox componentRole;
	private javax.swing.JComboBox componentParametres;

	public static UtilisateurManagement getInstance(String title){
		if (instance == null)
			instance = new UtilisateurManagement(title);
		instance.initFields();
		return instance;
	}

	public static UtilisateurManagement getInstanceWithoutCreation(){
		return instance;
	}

	public UtilisateurManagement(String title) {
		super(title);
		myInitComponents();
	}

	private void myInitComponents() {
		spTreeItems.setVisible(false);
		spListItems.setVisible(true);
		splitPaneTreeList.setDividerSize(0);
		pPhoto.setVisible(true);
		pNotification.setVisible(false);
		this.setFrameIcon(null);
		
		labelNom = new JLabel(views.ViewUtilisateur.getCaptionForNom()+" : ");
		labelNom.setVerticalAlignment(JLabel.CENTER);
		
		labelPrenom = new JLabel(views.ViewUtilisateur.getCaptionForPrenom()+" : ");
		labelPrenom.setVerticalAlignment(JLabel.CENTER);
		
		labelCivilite = new JLabel(views.ViewUtilisateur.getCaptionForCivilite()+" : ");
		labelCivilite.setVerticalAlignment(JLabel.CENTER);
		
		labelDateNaissance = new JLabel(views.ViewUtilisateur.getCaptionForDateNaissance()+" : ");
		labelDateNaissance.setVerticalAlignment(JLabel.CENTER);
		
		labelLieuNaissance = new JLabel(views.ViewUtilisateur.getCaptionForLieuNaissance()+" : ");
		labelLieuNaissance.setVerticalAlignment(JLabel.CENTER);
		
		labelLogin = new JLabel(views.ViewUtilisateur.getCaptionForLogin()+" : ");
		labelLogin.setVerticalAlignment(JLabel.CENTER);
		
		labelPassword = new JLabel(views.ViewUtilisateur.getCaptionForPassword()+" : ");
		labelPassword.setVerticalAlignment(JLabel.CENTER);
		
		labelRole = new JLabel(views.ViewUtilisateur.getCaptionForRole()+" : ");
		labelRole.setVerticalAlignment(JLabel.CENTER);
		
		labelParametres = new JLabel(views.ViewUtilisateur.getCaptionForParametres()+" : ");
		labelParametres.setVerticalAlignment(JLabel.CENTER);
		

		componentNom = views.ViewUtilisateur.getViewForNom();
		componentPrenom = views.ViewUtilisateur.getViewForPrenom();
		componentCivilite = (javax.swing.JComboBox)views.ViewUtilisateur.getViewForCivilite("JCOMBOBOXE");
		componentDateNaissance = views.ViewUtilisateur.getViewForDateNaissance();
		componentLieuNaissance = views.ViewUtilisateur.getViewForLieuNaissance();
		componentLogin = views.ViewUtilisateur.getViewForLogin();
		componentPassword = views.ViewUtilisateur.getViewForPassword();
		pPhoto.setBorder(javax.swing.BorderFactory.createTitledBorder("Photo : "));
		componentRole = views.ViewUtilisateur.getViewForRole();
		componentParametres = views.ViewUtilisateur.getViewForParametres();

//		stpListContent.setDividerLocation(300);
		
		bAjouter.setToolTipText("Ajouter un Utilisateurs");
		bSupprimer.setToolTipText("Supprimer le Utilisateurs sélectionné");
		bExporter.setToolTipText("Exporter la liste des utilisateurs vers un fichier Excel");
		bImporter.setToolTipText("Importer des utilisateurs à partir d'un fichier Excel");
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
				.addComponent(labelNom)
				.addComponent(labelPrenom)
				.addComponent(labelCivilite)
				.addComponent(labelDateNaissance)
				.addComponent(labelLieuNaissance)
				.addComponent(labelLogin)
				.addComponent(labelPassword)
				.addComponent(labelRole)
				.addComponent(labelParametres)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layoutPFormulaire.createParallelGroup()
				.addComponent(componentNom, 300, 300, 300)
				.addComponent(componentPrenom, 300, 300, 300)
				.addComponent(componentCivilite, 300, 300, 300)
				.addComponent(componentDateNaissance, 300, 300, 300)
				.addComponent(componentLieuNaissance, 300, 300, 300)
				.addComponent(componentLogin, 300, 300, 300)
				.addComponent(componentPassword, 300, 300, 300)
				.addComponent(componentRole, 300, 300, 300)
				.addComponent(componentParametres, 300, 300, 300)
			)
			.addGap(1, 1, Short.MAX_VALUE)
		);
		layoutPFormulaire.setVerticalGroup(layoutPFormulaire.createSequentialGroup()
				.addGap(10, 10, 10)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelNom, 25, 25, 25)
					.addComponent(componentNom, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelPrenom, 25, 25, 25)
					.addComponent(componentPrenom, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelCivilite, 25, 25, 25)
					.addComponent(componentCivilite, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelDateNaissance, 25, 25, 25)
					.addComponent(componentDateNaissance, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelLieuNaissance, 25, 25, 25)
					.addComponent(componentLieuNaissance, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelLogin, 25, 25, 25)
					.addComponent(componentLogin, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelPassword, 25, 25, 25)
					.addComponent(componentPassword, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelRole, 25, 25, 25)
					.addComponent(componentRole, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelParametres, 25, 25, 25)
					.addComponent(componentParametres, 25, 25, 25)
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
					updateListUtilisateurs();
				}
			});

			guiNavigator.setCBNumberOfItemsActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt){
					guiNavigator.updateNumberItemsInPage();
					int totalUtilisateurs = guiNavigator.getTotalNumberOfItems();
					int nbItemsInPage = guiNavigator.getNumberItemsInPage();
					int numberOfPage = totalUtilisateurs / nbItemsInPage;
					if (totalUtilisateurs % nbItemsInPage > 0)
						numberOfPage++;
					guiNavigator.setNumberOfPages(numberOfPage);
					guiNavigator.setNumeroPage(guiNavigator.getNumPageAsInt());
					
					updateListUtilisateurs(true);
				}
			});
		}
		
		pPhoto.setSupprimerActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				supprimerPhoto();
			}
		});
	}

	private void supprimerPhoto() {
		if (selectedItem == null || !(selectedItem instanceof Utilisateur)){
			return;
		}
		
		Utilisateur utilisateur = (Utilisateur)selectedItem;
		if (utilisateur.getPhoto() != null){
			if (!GUIMessageByOptionPane.showQuestionMessage(this, "Supprimer la photo", "Voulez-vous supprimer la photo de le(la/l') Utilisateurs ?")){;
				return;
			}
			
			controllers.Utilisateur.deletePhoto(utilisateur);
			
			pPhoto.setPhoto(null, false);
		}
	}

	protected void tfFilterDocumentUpdated(DocumentEvent evt) {
		updateListUtilisateurs(true);
	}

	protected void bAjouterActionPerformed(ActionEvent evt) {
		if (selectedItem != null){
			if ((((Utilisateur)selectedItem)).getId() == null || (((Utilisateur)selectedItem)).getId() == 0){
				notifierErreur("Veuillez valider le utilisateur que vous venez d'ajouter ...");
				return;
			}
		}

		Utilisateur utilisateur = new Utilisateur();

		selectedItem = utilisateur;
		dlmListItems.addElement(utilisateur);
		listItems.setSelectedValue(selectedItem, true);
		fillFormulaireByUtilisateur(utilisateur);
	}

	protected void bFermerActionPerformed(ActionEvent evt) {
		if (dlgLayerFilter.isVisible()){
			dlgLayerFilter.setVisible(false);
			return;
		}

		if (selectedItem != null){
			if ((((Utilisateur)selectedItem)).getId() == null || (((Utilisateur)selectedItem)).getId() == 0){
				if (GUIMessageByOptionPane.showQuestionMessage("Un utilisateur non ajouté", "Voulez-vous quitter sans valider cet utilisateur ?"))
					fermer();
				else
					return;
			}
		}
		fermer();
	}

	protected void bExporterActionPerformed(ActionEvent evt) {
		fcExportImportXLS.setDialogType(JFileChooser.SAVE_DIALOG | JFileChooser.FILES_ONLY);
		fcExportImportXLS.setDialogTitle("Exporter la liste des utilisateurs sous format Excel 2003 (*.xls)");
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
		fcExportImportXLS.setDialogTitle("Importer des utilisateurs à partir d'un fichier Excel format 2003 (*.xls)");
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
		if (selectedItem == null || !(selectedItem instanceof Utilisateur)){
			notifierErreur("Veuillez sélectionner un utilisateur ...");
			return;
		}

		if (! GUIMessageByOptionPane.showQuestionMessage("Supprimer un utilisateur", "Êtes-vous sûr de la suppression de cet utilisateur ?")){
			return;
		}

		int index = listItems.getSelectedIndex();
		
		Utilisateur utilisateur = (Utilisateur)selectedItem;
		if (utilisateur.getId() != null || utilisateur.getId() > 0){
			DAOUtilisateur.delete(utilisateur);
		}

		dlmListItems.remove(index);
		
		selectedItem = null;
		fillFormulaireByUtilisateur(null);
	}

	@SuppressWarnings("deprecation")
	protected void bValiderActionPerformed(ActionEvent evt) {
		if (selectedItem == null || !(selectedItem instanceof Utilisateur)){
			notifierErreur("Veuillez sélectionner un Utilisateur ...");
			return;
		}

		if (componentNom.getText().trim().equals("") || componentPrenom.getText().trim().equals("") || componentDateNaissance.getMySQLDate().equals("") || componentLieuNaissance.getText().trim().equals("") || componentLogin.getText().trim().equals("") || componentPassword.getText().trim().equals("")){
			notifierErreur("Veuillez remplir tous les champs, SVP");
			return;
		}

		Utilisateur selectedUtilisateur = (Utilisateur) selectedItem;
		
		Utilisateur oldUtilisateur = DAOUtilisateur.getUtilisateurByLogin(componentLogin.getText().trim());
		if (oldUtilisateur != null){
			if (selectedUtilisateur.getId().intValue() != oldUtilisateur.getId().intValue()){
				notifierErreur("Il y a un 'Utilisateurs' ayant déjà le même 'Login (Idnetifiant)', Veuillez donner une autre valeur au champs 'Login (Idnetifiant)'");
				return;
			}
		}

		if (selectedUtilisateur.getId() == null || selectedUtilisateur.getId() == 0){
			selectedUtilisateur.setId(0);

			selectedUtilisateur.setNom(componentNom.getText().trim());
			selectedUtilisateur.setPrenom(componentPrenom.getText().trim());
			selectedUtilisateur.setCivilite(Utilisateur.Civilite.getByValue(componentCivilite.getSelectedItem().toString()));
			selectedUtilisateur.setDateNaissance(StringUtils.getDateFromString(componentDateNaissance.getMySQLDate()));
			selectedUtilisateur.setLieuNaissance(componentLieuNaissance.getText().trim());
			selectedUtilisateur.setLogin(componentLogin.getText().trim());
			selectedUtilisateur.setPassword(componentPassword.getText().trim(), false);
			selectedUtilisateur.setIdRole((componentRole.getSelectedItem() instanceof models.beans.Role) ? ((models.beans.Role)componentRole.getSelectedItem()).getId() : 0);
			selectedUtilisateur.setIdParametres((componentParametres.getSelectedItem() instanceof models.beans.ParametresApplicationUtilisateur) ? ((models.beans.ParametresApplicationUtilisateur)componentParametres.getSelectedItem()).getId() : 0);
			
			byte[] dataPhoto = getDataPhoto();
			if (dataPhoto != null){
				models.beans.Photo photo = selectedUtilisateur.getPhoto();
				if (photo == null)
					photo = new models.beans.Photo();

				photo.setData(dataPhoto);
				models.daos.client.DAOPhoto.write(photo);
				selectedUtilisateur.setPhoto(photo);

				setDataPhoto(null);
			}
			selectedUtilisateur.setId((Integer) communication.SocketCommunicator.getInstance().sendQuery(selectedUtilisateur));
			notifierConfirmation("Utilisateurs sauvegardé avec succès");
		}else{
			utils.SGBDConfig.InsertUpdateSQLQueries listQueries = new utils.SGBDConfig.InsertUpdateSQLQueries((utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement[])null);
			if (! componentNom.getText().trim().equals(selectedUtilisateur.getNom())){
				selectedUtilisateur.setNom(componentNom.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOUtilisateur.getSQLUpdateForNomByPreparedStatement(selectedUtilisateur));
			}
			if (! componentPrenom.getText().trim().equals(selectedUtilisateur.getPrenom())){
				selectedUtilisateur.setPrenom(componentPrenom.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOUtilisateur.getSQLUpdateForPrenomByPreparedStatement(selectedUtilisateur));
			}
			if (selectedUtilisateur.getCivilite() == null || ! componentCivilite.getSelectedItem().toString().equals(selectedUtilisateur.getCivilite().getValue())){
				selectedUtilisateur.setCivilite(Utilisateur.Civilite.getByValue(componentCivilite.getSelectedItem().toString()));
				listQueries.addInsertUpdateSQLQueries(DAOUtilisateur.getSQLUpdateForCiviliteByPreparedStatement(selectedUtilisateur));
			}
			if (selectedUtilisateur.getDateNaissance() == null || ! selectedUtilisateur.getDateNaissance().toString().equals(StringUtils.getDateFromString(componentDateNaissance.getMySQLDate()))){
				selectedUtilisateur.setDateNaissance(StringUtils.getDateFromString(componentDateNaissance.getMySQLDate()));
				listQueries.addInsertUpdateSQLQueries(DAOUtilisateur.getSQLUpdateForDateNaissanceByPreparedStatement(selectedUtilisateur));
			}
			if (! componentLieuNaissance.getText().trim().equals(selectedUtilisateur.getLieuNaissance())){
				selectedUtilisateur.setLieuNaissance(componentLieuNaissance.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOUtilisateur.getSQLUpdateForLieuNaissanceByPreparedStatement(selectedUtilisateur));
			}
			if (! componentLogin.getText().trim().equals(selectedUtilisateur.getLogin())){
				selectedUtilisateur.setLogin(componentLogin.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOUtilisateur.getSQLUpdateForLoginByPreparedStatement(selectedUtilisateur));
			}
			if (! componentPassword.getText().trim().equals(selectedUtilisateur.getPassword())){
				selectedUtilisateur.setPassword(utils.StringUtils.encodeMD5(componentPassword.getText().trim()));
				listQueries.addInsertUpdateSQLQueries(DAOUtilisateur.getSQLUpdateForPasswordByPreparedStatement(selectedUtilisateur));
			}
			if (selectedUtilisateur.getIdRole() == null || !(componentRole.getSelectedItem() instanceof models.beans.Role) || ((models.beans.Role)componentRole.getSelectedItem()).getId().intValue() != selectedUtilisateur.getIdRole().intValue()){
				selectedUtilisateur.setIdRole(((models.beans.Role)componentRole.getSelectedItem()).getId());
				listQueries.addInsertUpdateSQLQueries(DAOUtilisateur.getSQLUpdateForIdRoleByPreparedStatement(selectedUtilisateur));
			}
			if (selectedUtilisateur.getIdParametres() == null || !(componentParametres.getSelectedItem() instanceof models.beans.ParametresApplicationUtilisateur) || ((models.beans.ParametresApplicationUtilisateur)componentParametres.getSelectedItem()).getId().intValue() != selectedUtilisateur.getIdParametres().intValue()){
				selectedUtilisateur.setIdParametres(((models.beans.ParametresApplicationUtilisateur)componentParametres.getSelectedItem()).getId());
				listQueries.addInsertUpdateSQLQueries(DAOUtilisateur.getSQLUpdateForIdParametresByPreparedStatement(selectedUtilisateur));
			}
			byte[] dataPhoto = getDataPhoto();
			if (dataPhoto != null){
				models.beans.Photo photo = selectedUtilisateur.getPhoto();
				if (photo == null)
					photo = new models.beans.Photo();

				photo.setData(dataPhoto);
				photo.setId((Integer)communication.SocketCommunicator.getInstance().sendQuery(photo));
				if (selectedUtilisateur.getIdPhoto().intValue() != photo.getId().intValue()){
					selectedUtilisateur.setIdPhoto(photo.getId());
					listQueries.addInsertUpdateSQLQueries(DAOUtilisateur.getSQLUpdateForIdPhotoByPreparedStatement(selectedUtilisateur));
				}
				
				selectedUtilisateur.setPhoto(photo);
				setDataPhoto(null);
			}
			
			communication.SocketCommunicator.getInstance().sendQuery(listQueries);
			notifierConfirmation("Utilisateur modifié avec succès");
		}
		
		dlmListItems.set(dlmListItems.indexOf(selectedItem), selectedUtilisateur);
	}

	protected void listItemsMouseClicked(MouseEvent evt) {
		selectedItem = listItems.getSelectedValue();
		if (selectedItem == null){
			fillFormulaireByUtilisateur(null);
			return;
		}

		fillFormulaireByUtilisateur((Utilisateur)selectedItem);
	}

	protected void miDeleteAllActionPerformed(ActionEvent evt) {
		if (GUIMessageByOptionPane.showQuestionMessage("Supprimer Tous ?", "Voulez-vous vraiment supprimer tous les Utilisateurs ?")){
			DAOUtilisateur.deleteAll();
			fillFormulaireByUtilisateur(null);
		}
	}

	private void exportToExcelFile(File file){
		try{
			views.ViewUtilisateur.exportToExcel(file);
		}
		catch (Exception e){
			GUIMessageByOptionPane.showErrorMessage("Erreur de génération d'Excel", "Errueur de génération de l'Excel");
			StringUtils.printDebug(e);
		}
	}

	private void importFromExcelFile(File file){
		views.ViewUtilisateur.importFromExcel(file);
	}

	protected void emptyFields() {
		this.componentNom.setText("");
		this.componentNom.setEnabled(false);
		this.labelNom.setEnabled(false);

		this.componentPrenom.setText("");
		this.componentPrenom.setEnabled(false);
		this.labelPrenom.setEnabled(false);

		this.componentCivilite.setSelectedIndex(0);
		this.componentCivilite.setEnabled(false);
		this.labelCivilite.setEnabled(false);

		this.componentDateNaissance.clear();
		this.componentDateNaissance.setEnabled(false);
		this.labelDateNaissance.setEnabled(false);

		this.componentLieuNaissance.setText("");
		this.componentLieuNaissance.setEnabled(false);
		this.labelLieuNaissance.setEnabled(false);

		this.componentLogin.setText("");
		this.componentLogin.setEnabled(false);
		this.labelLogin.setEnabled(false);

		this.componentPassword.setText("");
		this.componentPassword.setEnabled(false);
		this.labelPassword.setEnabled(false);

		setPhoto(null, false);
		pPhoto.setEnabled(false);
		this.componentRole.setSelectedIndex(0);
		this.componentRole.setEnabled(false);
		this.labelRole.setEnabled(false);

		this.componentParametres.setSelectedIndex(0);
		this.componentParametres.setEnabled(false);
		this.labelParametres.setEnabled(false);

	}

	protected void initFields() {
		this.pNotification.setVisible(JInternalFrameManagementModel.isVisibilityNotificationBar());

		selectedItem = null;

		setPhoto(null, false);

		if (guiNavigator.isVisible()){
			guiNavigator.updateNumberItemsInPage();
			int totalUtilisateurs = DAOUtilisateur.getCountInTable();
			int nbItemsInPage = guiNavigator.getNumberItemsInPage();
			int numberOfPage = totalUtilisateurs / nbItemsInPage;
			if (totalUtilisateurs % nbItemsInPage > 0)
				numberOfPage++;
			guiNavigator.setTotalNumberOfItems(totalUtilisateurs);
			guiNavigator.setNumberOfPages(numberOfPage);
			guiNavigator.setNumeroPage(1);
		}

		updateListUtilisateurs(true);

		
		componentRole.removeAllItems();
		List<models.beans.Role> listRoles = models.daos.client.DAORole.getListInstances();
		for (models.beans.Role instance : listRoles){
			componentRole.addItem(instance);
		}
		componentRole.insertItemAt("Choisir un Role", 0);
		
		componentParametres.removeAllItems();
		List<models.beans.ParametresApplicationUtilisateur> listParametress = models.daos.client.DAOParametresApplicationUtilisateur.getListInstances();
		for (models.beans.ParametresApplicationUtilisateur instance : listParametress){
			componentParametres.addItem(instance);
		}
		componentParametres.insertItemAt("Choisir un Paramètres Personnels", 0);
		
		fillFormulaireByUtilisateur(null);
	}

	private synchronized void updateListUtilisateurs(){
		updateListUtilisateurs(false);
	}

	private synchronized void updateListUtilisateurs(boolean filtering){
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
				int totalUtilisateurs = DAOUtilisateur.getCountInTable(condition);
				int nbItemsInPage = guiNavigator.getNumberItemsInPage();
				int numberOfPage = totalUtilisateurs / nbItemsInPage;
				if (totalUtilisateurs % nbItemsInPage > 0)
					numberOfPage++;
				guiNavigator.setTotalNumberOfItems(totalUtilisateurs);
				guiNavigator.setNumberOfPages(numberOfPage);
				guiNavigator.setNumeroPage(1);
			}
			if (guiNavigator.getFirst() < 0)
				return;
		}
		
		dlmListItems.clear();
		List<Utilisateur> listUtilisateurs = DAOUtilisateur.getListInstances(condition);
		for (Utilisateur utilisateur : listUtilisateurs){
			dlmListItems.addElement(utilisateur);
		}
	}

	protected void fillFormulaireByUtilisateur(Utilisateur utilisateur) {
//		pFormulaire.setVisible(utilisateur != null);
		if (utilisateur == null){
			emptyFields();
			return;
		}

		this.componentNom.setText(String.valueOf(utilisateur.getNom()));
		this.componentNom.setEnabled(true);
		this.labelNom.setEnabled(true);

		this.componentPrenom.setText(String.valueOf(utilisateur.getPrenom()));
		this.componentPrenom.setEnabled(true);
		this.labelPrenom.setEnabled(true);

		if (utilisateur.getCivilite() != null)
			this.componentCivilite.setSelectedItem(utilisateur.getCivilite().getValue());
		else
			this.componentCivilite.setSelectedIndex(0);
		this.componentCivilite.setEnabled(true);
		this.labelCivilite.setEnabled(true);

		if (utilisateur.getDateNaissance() == null)
			this.componentDateNaissance.clear();
		else
			this.componentDateNaissance.setMySQLDate(utilisateur.getDateNaissance().toString());
		this.componentDateNaissance.setEnabled(true);
		this.labelDateNaissance.setEnabled(true);

		this.componentLieuNaissance.setText(String.valueOf(utilisateur.getLieuNaissance()));
		this.componentLieuNaissance.setEnabled(true);
		this.labelLieuNaissance.setEnabled(true);

		this.componentLogin.setText(String.valueOf(utilisateur.getLogin()));
		this.componentLogin.setEnabled(true);
		this.labelLogin.setEnabled(true);

		this.componentPassword.setText(String.valueOf(utilisateur.getPassword()));
		this.componentPassword.setEnabled(true);
		this.labelPassword.setEnabled(true);

		if (utilisateur.getPhoto() != null){
			setPhoto(utilisateur.getPhoto().dataToFile(), false);
		}
		else{
			setPhoto(null, false);
		}
		pPhoto.setEnabled(true);
		this.componentRole.setSelectedIndex(0);
		for (int index=1; index<this.componentRole.getItemCount(); index++){
			models.beans.Role role = (models.beans.Role)this.componentRole.getItemAt(index);
			if (role.getId().intValue() == utilisateur.getIdRole().intValue()){
				this.componentRole.setSelectedIndex(index);
				break;
			}
		}
		this.labelRole.setEnabled(true);
		this.componentRole.setEnabled(true);
		this.componentParametres.setSelectedIndex(0);
		for (int index=1; index<this.componentParametres.getItemCount(); index++){
			models.beans.ParametresApplicationUtilisateur parametres = (models.beans.ParametresApplicationUtilisateur)this.componentParametres.getItemAt(index);
			if (parametres.getId().intValue() == utilisateur.getIdParametres().intValue()){
				this.componentParametres.setSelectedIndex(index);
				break;
			}
		}
		this.labelParametres.setEnabled(true);
		this.componentParametres.setEnabled(true);
		
		listItems.setSelectedIndex(dlmListItems.indexOf(utilisateur));
	}

	private void generateAndShowPDF(){
		try{
			File file = File.createTempFile( "ListeUtilisateurs", ".pdf");
			file.deleteOnExit();
			
			views.ViewUtilisateur.exportToPDF(file);
			
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

		getInstance("Gestion des Utilisateurs ...");
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
			
			try{
				instance.setMaximum(true);
			}
			catch (Exception e){}
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
		javax.swing.JFrame frame = new javax.swing.JFrame("Test of access to Table : utilisateur");
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