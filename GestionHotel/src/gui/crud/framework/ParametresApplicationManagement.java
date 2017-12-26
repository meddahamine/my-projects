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

import models.beans.ParametresApplication;
import models.daos.client.DAOParametresApplication;

import gui.utils.GUIMessageByOptionPane;
import gui.utils.JInternalFrameManagementModel;

public class ParametresApplicationManagement extends JInternalFrameManagementModel{
	private static final long serialVersionUID = 1L;

	private static ParametresApplicationManagement instance = null;

	private JLabel labelDesignation;
	private JLabel labelDescription;

	private javax.swing.JTextField componentDesignation;
	private javax.swing.JScrollPane componentDescription;

	public static ParametresApplicationManagement getInstance(String title){
		if (instance == null)
			instance = new ParametresApplicationManagement(title);
		instance.initFields();
		return instance;
	}

	public static ParametresApplicationManagement getInstanceWithoutCreation(){
		return instance;
	}

	public ParametresApplicationManagement(String title) {
		super(title);
		myInitComponents();
	}

	private void myInitComponents() {
		this.spTreeItems.setVisible(false);
		this.spListItems.setVisible(false);
		this.pListButtons.setVisible(false);
		this.stpListContent.setDividerSize(0);		pPhoto.setVisible(true);
		pNotification.setVisible(false);
		this.setFrameIcon(null);
		
		labelDesignation = new JLabel(views.ViewParametresApplication.getCaptionForDesignation()+" : ");
		labelDesignation.setVerticalAlignment(JLabel.CENTER);
		
		labelDescription = new JLabel(views.ViewParametresApplication.getCaptionForDescription()+" : ");
		labelDescription.setVerticalAlignment(JLabel.CENTER);
		

		componentDesignation = views.ViewParametresApplication.getViewForDesignation();
		componentDescription = views.ViewParametresApplication.getViewForDescription();
		pPhoto.setBorder(javax.swing.BorderFactory.createTitledBorder("Icone de l'Application : "));

//		stpListContent.setDividerLocation(300);
		
		bAjouter.setToolTipText("Ajouter un Paramètres de l'Application");
		bSupprimer.setToolTipText("Supprimer le Paramètres de l'Application sélectionné");
		bExporter.setToolTipText("Exporter la liste des parametresApplications vers un fichier Excel");
		bImporter.setToolTipText("Importer des parametresApplications à partir d'un fichier Excel");
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
				.addComponent(labelDescription)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layoutPFormulaire.createParallelGroup()
				.addComponent(componentDesignation, 300, 300, 300)
				.addComponent(componentDescription, 300, 300, 300)
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
					.addComponent(labelDescription, 25, 25, 25)
					.addComponent(componentDescription, 25, 25, 25)
				)
				.addGap(5, 5, 5)
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
					updateListParametresApplications();
				}
			});

			guiNavigator.setCBNumberOfItemsActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt){
					guiNavigator.updateNumberItemsInPage();
					int totalParametresApplications = guiNavigator.getTotalNumberOfItems();
					int nbItemsInPage = guiNavigator.getNumberItemsInPage();
					int numberOfPage = totalParametresApplications / nbItemsInPage;
					if (totalParametresApplications % nbItemsInPage > 0)
						numberOfPage++;
					guiNavigator.setNumberOfPages(numberOfPage);
					guiNavigator.setNumeroPage(guiNavigator.getNumPageAsInt());
					
					updateListParametresApplications(true);
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
		if (selectedItem == null || !(selectedItem instanceof ParametresApplication)){
			return;
		}
		
		ParametresApplication parametresApplication = (ParametresApplication)selectedItem;
		if (parametresApplication.getPhoto() != null){
			if (!GUIMessageByOptionPane.showQuestionMessage(this, "Supprimer la photo", "Voulez-vous supprimer la photo de le(la/l') Paramètres de l'Application ?")){;
				return;
			}
			
			controllers.ParametresApplication.deletePhoto(parametresApplication);
			
			pPhoto.setPhoto(null, false);
		}
	}

	protected void tfFilterDocumentUpdated(DocumentEvent evt) {
		updateListParametresApplications(true);
	}

	protected void bAjouterActionPerformed(ActionEvent evt) {
		if (selectedItem != null){
			if ((((ParametresApplication)selectedItem)).getId() == null || (((ParametresApplication)selectedItem)).getId() == 0){
				notifierErreur("Veuillez valider le parametresApplication que vous venez d'ajouter ...");
				return;
			}
		}

		ParametresApplication parametresApplication = new ParametresApplication();

		selectedItem = parametresApplication;
		dlmListItems.addElement(parametresApplication);
		listItems.setSelectedValue(selectedItem, true);
		fillFormulaireByParametresApplication(parametresApplication);
	}

	protected void bFermerActionPerformed(ActionEvent evt) {
		if (dlgLayerFilter.isVisible()){
			dlgLayerFilter.setVisible(false);
			return;
		}

		if (selectedItem != null){
			if ((((ParametresApplication)selectedItem)).getId() == null || (((ParametresApplication)selectedItem)).getId() == 0){
				if (GUIMessageByOptionPane.showQuestionMessage("Un parametresApplication non ajouté", "Voulez-vous quitter sans valider cet parametresApplication ?"))
					fermer();
				else
					return;
			}
		}
		fermer();
	}

	protected void bExporterActionPerformed(ActionEvent evt) {
		fcExportImportXLS.setDialogType(JFileChooser.SAVE_DIALOG | JFileChooser.FILES_ONLY);
		fcExportImportXLS.setDialogTitle("Exporter la liste des parametresApplications sous format Excel 2003 (*.xls)");
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
		fcExportImportXLS.setDialogTitle("Importer des parametresApplications à partir d'un fichier Excel format 2003 (*.xls)");
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
		if (selectedItem == null || !(selectedItem instanceof ParametresApplication)){
			notifierErreur("Veuillez sélectionner un parametresApplication ...");
			return;
		}

		if (! GUIMessageByOptionPane.showQuestionMessage("Supprimer un parametresApplication", "Êtes-vous sûr de la suppression de cet parametresApplication ?")){
			return;
		}

		int index = listItems.getSelectedIndex();
		
		ParametresApplication parametresApplication = (ParametresApplication)selectedItem;
		if (parametresApplication.getId() != null || parametresApplication.getId() > 0){
			DAOParametresApplication.delete(parametresApplication);
		}

		dlmListItems.remove(index);
		
		selectedItem = null;
		fillFormulaireByParametresApplication(null);
	}

	protected void bValiderActionPerformed(ActionEvent evt) {
		if (selectedItem == null || !(selectedItem instanceof ParametresApplication)){
			notifierErreur("Veuillez sélectionner un ParametresApplication ...");
			return;
		}

		if (componentDesignation.getText().trim().equals("") || ((javax.swing.JTextArea)componentDescription.getViewport().getComponent(0)).getText().trim().equals("")){
			notifierErreur("Veuillez remplir tous les champs, SVP");
			return;
		}

		ParametresApplication selectedParametresApplication = (ParametresApplication) selectedItem;

		if (selectedParametresApplication.getId() == null || selectedParametresApplication.getId() == 0){
			selectedParametresApplication.setId(0);

			selectedParametresApplication.setDesignation(componentDesignation.getText().trim());
			selectedParametresApplication.setDescription(((javax.swing.JTextArea)componentDescription.getViewport().getComponent(0)).getText());
			
			byte[] dataPhoto = getDataPhoto();
			if (dataPhoto != null){
				models.beans.Photo photo = selectedParametresApplication.getPhoto();
				if (photo == null)
					photo = new models.beans.Photo();

				photo.setData(dataPhoto);
				models.daos.client.DAOPhoto.write(photo);
				selectedParametresApplication.setPhoto(photo);

				setDataPhoto(null);
			}
			selectedParametresApplication.setId((Integer) communication.SocketCommunicator.getInstance().sendQuery(selectedParametresApplication));
			notifierConfirmation("Paramètres de l'Application sauvegardé avec succès");
		}else{
			utils.SGBDConfig.InsertUpdateSQLQueries listQueries = new utils.SGBDConfig.InsertUpdateSQLQueries((utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement[])null);
			if (! componentDesignation.getText().trim().equals(selectedParametresApplication.getDesignation())){
				selectedParametresApplication.setDesignation(componentDesignation.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOParametresApplication.getSQLUpdateForDesignationByPreparedStatement(selectedParametresApplication));
			}
			if (! ((javax.swing.JTextArea)componentDescription.getViewport().getComponent(0)).getText().trim().equals(selectedParametresApplication.getDescription())){
				selectedParametresApplication.setDescription(((javax.swing.JTextArea)componentDescription.getViewport().getComponent(0)).getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOParametresApplication.getSQLUpdateForDescriptionByPreparedStatement(selectedParametresApplication));
			}
			byte[] dataPhoto = getDataPhoto();
			if (dataPhoto != null){
				models.beans.Photo photo = selectedParametresApplication.getPhoto();
				if (photo == null)
					photo = new models.beans.Photo();

				photo.setData(dataPhoto);
				photo.setId((Integer)communication.SocketCommunicator.getInstance().sendQuery(photo));
				if (selectedParametresApplication.getIdPhoto().intValue() != photo.getId().intValue()){
					selectedParametresApplication.setIdPhoto(photo.getId());
					listQueries.addInsertUpdateSQLQueries(DAOParametresApplication.getSQLUpdateForIdPhotoByPreparedStatement(selectedParametresApplication));
				}
				
				selectedParametresApplication.setPhoto(photo);
				setDataPhoto(null);
			}
			
			communication.SocketCommunicator.getInstance().sendQuery(listQueries);
			notifierConfirmation("ParametresApplication modifié avec succès");
		}
		
		dlmListItems.set(dlmListItems.indexOf(selectedItem), selectedParametresApplication);
	}

	protected void listItemsMouseClicked(MouseEvent evt) {
		selectedItem = listItems.getSelectedValue();
		if (selectedItem == null){
			fillFormulaireByParametresApplication(null);
			return;
		}

		fillFormulaireByParametresApplication((ParametresApplication)selectedItem);
	}

	protected void miDeleteAllActionPerformed(ActionEvent evt) {
		if (GUIMessageByOptionPane.showQuestionMessage("Supprimer Tous ?", "Voulez-vous vraiment supprimer tous les Paramètres de l'Application ?")){
			DAOParametresApplication.deleteAll();
			fillFormulaireByParametresApplication(null);
		}
	}

	private void exportToExcelFile(File file){
		try{
			views.ViewParametresApplication.exportToExcel(file);
		}
		catch (Exception e){
			GUIMessageByOptionPane.showErrorMessage("Erreur de génération d'Excel", "Errueur de génération de l'Excel");
			StringUtils.printDebug(e);
		}
	}

	private void importFromExcelFile(File file){
		views.ViewParametresApplication.importFromExcel(file);
	}

	protected void emptyFields() {
		this.componentDesignation.setText("");
		this.componentDesignation.setEnabled(false);
		this.labelDesignation.setEnabled(false);

		((javax.swing.JTextArea)this.componentDescription.getViewport().getComponent(0)).setText("");
		((javax.swing.JTextArea)this.componentDescription.getViewport().getComponent(0)).setEnabled(false);
		this.labelDescription.setEnabled(false);

		setPhoto(null, false);
		pPhoto.setEnabled(false);
	}

	protected void initFields() {
		this.pNotification.setVisible(JInternalFrameManagementModel.isVisibilityNotificationBar());

		selectedItem = null;

		setPhoto(null, false);

		if (guiNavigator.isVisible()){
			guiNavigator.updateNumberItemsInPage();
			int totalParametresApplications = DAOParametresApplication.getCountInTable();
			int nbItemsInPage = guiNavigator.getNumberItemsInPage();
			int numberOfPage = totalParametresApplications / nbItemsInPage;
			if (totalParametresApplications % nbItemsInPage > 0)
				numberOfPage++;
			guiNavigator.setTotalNumberOfItems(totalParametresApplications);
			guiNavigator.setNumberOfPages(numberOfPage);
			guiNavigator.setNumeroPage(1);
		}

		
		selectedItem = models.daos.client.DAOParametresApplication.getParametresApplication(1);
		fillFormulaireByParametresApplication((ParametresApplication)selectedItem);
	}

	private synchronized void updateListParametresApplications(){
		updateListParametresApplications(false);
	}

	private synchronized void updateListParametresApplications(boolean filtering){
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
				int totalParametresApplications = DAOParametresApplication.getCountInTable(condition);
				int nbItemsInPage = guiNavigator.getNumberItemsInPage();
				int numberOfPage = totalParametresApplications / nbItemsInPage;
				if (totalParametresApplications % nbItemsInPage > 0)
					numberOfPage++;
				guiNavigator.setTotalNumberOfItems(totalParametresApplications);
				guiNavigator.setNumberOfPages(numberOfPage);
				guiNavigator.setNumeroPage(1);
			}
			if (guiNavigator.getFirst() < 0)
				return;
		}
		
		dlmListItems.clear();
		List<ParametresApplication> listParametresApplications = DAOParametresApplication.getListInstances(condition);
		for (ParametresApplication parametresApplication : listParametresApplications){
			dlmListItems.addElement(parametresApplication);
		}
	}

	protected void fillFormulaireByParametresApplication(ParametresApplication parametresApplication) {
//		pFormulaire.setVisible(parametresApplication != null);
		if (parametresApplication == null){
			emptyFields();
			return;
		}

		this.componentDesignation.setText(String.valueOf(parametresApplication.getDesignation()));
		this.componentDesignation.setEnabled(true);
		this.labelDesignation.setEnabled(true);

		((javax.swing.JTextArea)this.componentDescription.getViewport().getComponent(0)).setText(String.valueOf(parametresApplication.getDescription()));
		((javax.swing.JTextArea)this.componentDescription.getViewport().getComponent(0)).setEnabled(true);
		this.labelDescription.setEnabled(true);

		if (parametresApplication.getPhoto() != null){
			setPhoto(parametresApplication.getPhoto().dataToFile(), false);
		}
		else{
			setPhoto(null, false);
		}
		pPhoto.setEnabled(true);
		
		listItems.setSelectedIndex(dlmListItems.indexOf(parametresApplication));
	}

	private void generateAndShowPDF(){
		try{
			File file = File.createTempFile( "ListeParametresApplications", ".pdf");
			file.deleteOnExit();
			
			views.ViewParametresApplication.exportToPDF(file);
			
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

		getInstance("Gestion des Paramètres de l'Application ...");
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
		javax.swing.JFrame frame = new javax.swing.JFrame("Test of access to Table : parametresApplication");
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