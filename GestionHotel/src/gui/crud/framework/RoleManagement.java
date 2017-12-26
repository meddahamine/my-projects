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

import models.beans.Role;
import models.daos.client.DAORole;

import gui.utils.GUIMessageByOptionPane;
import gui.utils.JInternalFrameManagementModel;

public class RoleManagement extends JInternalFrameManagementModel{
	private static final long serialVersionUID = 1L;

	private static RoleManagement instance = null;

	private JLabel labelDesignation;
	private JLabel labelParametresOrganisme;
	private JLabel labelGestionRole;
	private JLabel labelGestionUtilisateur;

	private javax.swing.JTextField componentDesignation;
	private javax.swing.JCheckBox componentParametresOrganisme;
	private javax.swing.JCheckBox componentGestionRole;
	private javax.swing.JCheckBox componentGestionUtilisateur;

	public static RoleManagement getInstance(String title){
		if (instance == null)
			instance = new RoleManagement(title);
		instance.initFields();
		return instance;
	}

	public static RoleManagement getInstanceWithoutCreation(){
		return instance;
	}

	public RoleManagement(String title) {
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
		
		labelDesignation = new JLabel(views.ViewRole.getCaptionForDesignation()+" : ");
		labelDesignation.setVerticalAlignment(JLabel.CENTER);
		
		labelParametresOrganisme = new JLabel(views.ViewRole.getCaptionForParametresOrganisme()+" : ");
		labelParametresOrganisme.setVerticalAlignment(JLabel.CENTER);
		
		labelGestionRole = new JLabel(views.ViewRole.getCaptionForGestionRole()+" : ");
		labelGestionRole.setVerticalAlignment(JLabel.CENTER);
		
		labelGestionUtilisateur = new JLabel(views.ViewRole.getCaptionForGestionUtilisateur()+" : ");
		labelGestionUtilisateur.setVerticalAlignment(JLabel.CENTER);
		

		componentDesignation = views.ViewRole.getViewForDesignation();
		pPhoto.setBorder(javax.swing.BorderFactory.createTitledBorder("Emblème : "));
		componentParametresOrganisme = views.ViewRole.getViewForParametresOrganisme();
		componentGestionRole = views.ViewRole.getViewForGestionRole();
		componentGestionUtilisateur = views.ViewRole.getViewForGestionUtilisateur();

//		stpListContent.setDividerLocation(300);
		
		bAjouter.setToolTipText("Ajouter un Rôles");
		bSupprimer.setToolTipText("Supprimer le Rôles sélectionné");
		bExporter.setToolTipText("Exporter la liste des roles vers un fichier Excel");
		bImporter.setToolTipText("Importer des roles à partir d'un fichier Excel");
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
				.addComponent(labelDesignation)
				.addComponent(labelParametresOrganisme)
				.addComponent(labelGestionRole)
				.addComponent(labelGestionUtilisateur)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layoutPFormulaire.createParallelGroup()
				.addComponent(componentDesignation, 300, 300, 300)
				.addComponent(componentParametresOrganisme, 300, 300, 300)
				.addComponent(componentGestionRole, 300, 300, 300)
				.addComponent(componentGestionUtilisateur, 300, 300, 300)
			)
			.addGap(1, 1, Short.MAX_VALUE)
		);
		layoutPFormulaire.setVerticalGroup(layoutPFormulaire.createSequentialGroup()
				.addGap(10, 10, 10)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelDesignation, 25, 25, 25)
					.addComponent(componentDesignation, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelParametresOrganisme, 25, 25, 25)
					.addComponent(componentParametresOrganisme, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelGestionRole, 25, 25, 25)
					.addComponent(componentGestionRole, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelGestionUtilisateur, 25, 25, 25)
					.addComponent(componentGestionUtilisateur, 25, 25, 25)
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
					updateListRoles();
				}
			});

			guiNavigator.setCBNumberOfItemsActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt){
					guiNavigator.updateNumberItemsInPage();
					int totalRoles = guiNavigator.getTotalNumberOfItems();
					int nbItemsInPage = guiNavigator.getNumberItemsInPage();
					int numberOfPage = totalRoles / nbItemsInPage;
					if (totalRoles % nbItemsInPage > 0)
						numberOfPage++;
					guiNavigator.setNumberOfPages(numberOfPage);
					guiNavigator.setNumeroPage(guiNavigator.getNumPageAsInt());
					
					updateListRoles(true);
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
		if (selectedItem == null || !(selectedItem instanceof Role)){
			return;
		}
		
		Role role = (Role)selectedItem;
		if (role.getPhoto() != null){
			if (!GUIMessageByOptionPane.showQuestionMessage(this, "Supprimer la photo", "Voulez-vous supprimer la photo de le(la/l') Rôles ?")){;
				return;
			}
			
			controllers.Role.deletePhoto(role);
			
			pPhoto.setPhoto(null, false);
		}
	}

	protected void tfFilterDocumentUpdated(DocumentEvent evt) {
		updateListRoles(true);
	}

	protected void bAjouterActionPerformed(ActionEvent evt) {
		if (selectedItem != null){
			if ((((Role)selectedItem)).getId() == null || (((Role)selectedItem)).getId() == 0){
				notifierErreur("Veuillez valider le role que vous venez d'ajouter ...");
				return;
			}
		}

		Role role = new Role();

		selectedItem = role;
		dlmListItems.addElement(role);
		listItems.setSelectedValue(selectedItem, true);
		fillFormulaireByRole(role);
	}

	protected void bFermerActionPerformed(ActionEvent evt) {
		if (dlgLayerFilter.isVisible()){
			dlgLayerFilter.setVisible(false);
			return;
		}

		if (selectedItem != null){
			if ((((Role)selectedItem)).getId() == null || (((Role)selectedItem)).getId() == 0){
				if (GUIMessageByOptionPane.showQuestionMessage("Un role non ajouté", "Voulez-vous quitter sans valider cet role ?"))
					fermer();
				else
					return;
			}
		}
		fermer();
	}

	protected void bExporterActionPerformed(ActionEvent evt) {
		fcExportImportXLS.setDialogType(JFileChooser.SAVE_DIALOG | JFileChooser.FILES_ONLY);
		fcExportImportXLS.setDialogTitle("Exporter la liste des roles sous format Excel 2003 (*.xls)");
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
		fcExportImportXLS.setDialogTitle("Importer des roles à partir d'un fichier Excel format 2003 (*.xls)");
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
		if (selectedItem == null || !(selectedItem instanceof Role)){
			notifierErreur("Veuillez sélectionner un role ...");
			return;
		}

		if (! GUIMessageByOptionPane.showQuestionMessage("Supprimer un role", "Êtes-vous sûr de la suppression de cet role ?")){
			return;
		}

		int index = listItems.getSelectedIndex();
		
		Role role = (Role)selectedItem;
		if (role.getId() != null || role.getId() > 0){
			DAORole.delete(role);
		}

		dlmListItems.remove(index);
		
		selectedItem = null;
		fillFormulaireByRole(null);
	}

	protected void bValiderActionPerformed(ActionEvent evt) {
		if (selectedItem == null || !(selectedItem instanceof Role)){
			notifierErreur("Veuillez sélectionner un Role ...");
			return;
		}

		if (componentDesignation.getText().trim().equals("")){
			notifierErreur("Veuillez remplir tous les champs, SVP");
			return;
		}

		Role selectedRole = (Role) selectedItem;

		if (selectedRole.getId() == null || selectedRole.getId() == 0){
			selectedRole.setId(0);

			selectedRole.setDesignation(componentDesignation.getText().trim());
			selectedRole.setParametresOrganisme(componentParametresOrganisme.isSelected());
			selectedRole.setGestionRole(componentGestionRole.isSelected());
			selectedRole.setGestionUtilisateur(componentGestionUtilisateur.isSelected());
			
			byte[] dataPhoto = getDataPhoto();
			if (dataPhoto != null){
				models.beans.Photo photo = selectedRole.getPhoto();
				if (photo == null)
					photo = new models.beans.Photo();

				photo.setData(dataPhoto);
				models.daos.client.DAOPhoto.write(photo);
				selectedRole.setPhoto(photo);

				setDataPhoto(null);
			}
			selectedRole.setId((Integer) communication.SocketCommunicator.getInstance().sendQuery(selectedRole));
			notifierConfirmation("Rôles sauvegardé avec succès");
		}else{
			utils.SGBDConfig.InsertUpdateSQLQueries listQueries = new utils.SGBDConfig.InsertUpdateSQLQueries((utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement[])null);
			if (! componentDesignation.getText().trim().equals(selectedRole.getDesignation())){
				selectedRole.setDesignation(componentDesignation.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAORole.getSQLUpdateForDesignationByPreparedStatement(selectedRole));
			}
			if (componentParametresOrganisme.isSelected() != selectedRole.isParametresOrganisme().booleanValue()){
				selectedRole.setParametresOrganisme(componentParametresOrganisme.isSelected());
				listQueries.addInsertUpdateSQLQueries(DAORole.getSQLUpdateForParametresOrganismeByPreparedStatement(selectedRole));
			}
			if (componentGestionRole.isSelected() != selectedRole.isGestionRole().booleanValue()){
				selectedRole.setGestionRole(componentGestionRole.isSelected());
				listQueries.addInsertUpdateSQLQueries(DAORole.getSQLUpdateForGestionRoleByPreparedStatement(selectedRole));
			}
			if (componentGestionUtilisateur.isSelected() != selectedRole.isGestionUtilisateur().booleanValue()){
				selectedRole.setGestionUtilisateur(componentGestionUtilisateur.isSelected());
				listQueries.addInsertUpdateSQLQueries(DAORole.getSQLUpdateForGestionUtilisateurByPreparedStatement(selectedRole));
			}
			byte[] dataPhoto = getDataPhoto();
			if (dataPhoto != null){
				models.beans.Photo photo = selectedRole.getPhoto();
				if (photo == null)
					photo = new models.beans.Photo();

				photo.setData(dataPhoto);
				photo.setId((Integer)communication.SocketCommunicator.getInstance().sendQuery(photo));
				if (selectedRole.getIdPhoto().intValue() != photo.getId().intValue()){
					selectedRole.setIdPhoto(photo.getId());
					listQueries.addInsertUpdateSQLQueries(DAORole.getSQLUpdateForIdPhotoByPreparedStatement(selectedRole));
				}
				
				selectedRole.setPhoto(photo);
				setDataPhoto(null);
			}
			
			communication.SocketCommunicator.getInstance().sendQuery(listQueries);
			notifierConfirmation("Role modifié avec succès");
		}
		
		dlmListItems.set(dlmListItems.indexOf(selectedItem), selectedRole);
	}

	protected void listItemsMouseClicked(MouseEvent evt) {
		selectedItem = listItems.getSelectedValue();
		if (selectedItem == null){
			fillFormulaireByRole(null);
			return;
		}

		fillFormulaireByRole((Role)selectedItem);
	}

	protected void miDeleteAllActionPerformed(ActionEvent evt) {
		if (GUIMessageByOptionPane.showQuestionMessage("Supprimer Tous ?", "Voulez-vous vraiment supprimer tous les Rôles ?")){
			DAORole.deleteAll();
			fillFormulaireByRole(null);
		}
	}

	private void exportToExcelFile(File file){
		try{
			views.ViewRole.exportToExcel(file);
		}
		catch (Exception e){
			GUIMessageByOptionPane.showErrorMessage("Erreur de génération d'Excel", "Errueur de génération de l'Excel");
			StringUtils.printDebug(e);
		}
	}

	private void importFromExcelFile(File file){
		views.ViewRole.importFromExcel(file);
	}

	protected void emptyFields() {
		this.componentDesignation.setText("");
		this.componentDesignation.setEnabled(false);
		this.labelDesignation.setEnabled(false);

		setPhoto(null, false);
		pPhoto.setEnabled(false);
		this.componentParametresOrganisme.setSelected(false);
		this.componentParametresOrganisme.setEnabled(false);
		this.labelParametresOrganisme.setEnabled(false);

		this.componentGestionRole.setSelected(false);
		this.componentGestionRole.setEnabled(false);
		this.labelGestionRole.setEnabled(false);

		this.componentGestionUtilisateur.setSelected(false);
		this.componentGestionUtilisateur.setEnabled(false);
		this.labelGestionUtilisateur.setEnabled(false);

	}

	protected void initFields() {
		this.pNotification.setVisible(JInternalFrameManagementModel.isVisibilityNotificationBar());

		selectedItem = null;

		setPhoto(null, false);

		if (guiNavigator.isVisible()){
			guiNavigator.updateNumberItemsInPage();
			int totalRoles = DAORole.getCountInTable();
			int nbItemsInPage = guiNavigator.getNumberItemsInPage();
			int numberOfPage = totalRoles / nbItemsInPage;
			if (totalRoles % nbItemsInPage > 0)
				numberOfPage++;
			guiNavigator.setTotalNumberOfItems(totalRoles);
			guiNavigator.setNumberOfPages(numberOfPage);
			guiNavigator.setNumeroPage(1);
		}

		updateListRoles(true);

		
		fillFormulaireByRole(null);
	}

	private synchronized void updateListRoles(){
		updateListRoles(false);
	}

	private synchronized void updateListRoles(boolean filtering){
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
				int totalRoles = DAORole.getCountInTable(condition);
				int nbItemsInPage = guiNavigator.getNumberItemsInPage();
				int numberOfPage = totalRoles / nbItemsInPage;
				if (totalRoles % nbItemsInPage > 0)
					numberOfPage++;
				guiNavigator.setTotalNumberOfItems(totalRoles);
				guiNavigator.setNumberOfPages(numberOfPage);
				guiNavigator.setNumeroPage(1);
			}
			if (guiNavigator.getFirst() < 0)
				return;
		}
		
		dlmListItems.clear();
		List<Role> listRoles = DAORole.getListInstances(condition);
		for (Role role : listRoles){
			dlmListItems.addElement(role);
		}
	}

	protected void fillFormulaireByRole(Role role) {
//		pFormulaire.setVisible(role != null);
		if (role == null){
			emptyFields();
			return;
		}

		this.componentDesignation.setText(String.valueOf(role.getDesignation()));
		this.componentDesignation.setEnabled(true);
		this.labelDesignation.setEnabled(true);

		if (role.getPhoto() != null){
			setPhoto(role.getPhoto().dataToFile(), false);
		}
		else{
			setPhoto(null, false);
		}
		pPhoto.setEnabled(true);
		this.componentParametresOrganisme.setSelected(role.isParametresOrganisme());
		this.componentParametresOrganisme.setEnabled(true);
		this.labelParametresOrganisme.setEnabled(true);

		this.componentGestionRole.setSelected(role.isGestionRole());
		this.componentGestionRole.setEnabled(true);
		this.labelGestionRole.setEnabled(true);

		this.componentGestionUtilisateur.setSelected(role.isGestionUtilisateur());
		this.componentGestionUtilisateur.setEnabled(true);
		this.labelGestionUtilisateur.setEnabled(true);

		
		listItems.setSelectedIndex(dlmListItems.indexOf(role));
	}

	private void generateAndShowPDF(){
		try{
			File file = File.createTempFile( "ListeRoles", ".pdf");
			file.deleteOnExit();
			
			views.ViewRole.exportToPDF(file);
			
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

		getInstance("Gestion des Rôles ...");
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
		javax.swing.JFrame frame = new javax.swing.JFrame("Test of access to Table : role");
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