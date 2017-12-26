package gui.crud.app;

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
import models.beans.Client;
import models.beans.Facture;
import models.daos.server.DAOFacture;
import gui.utils.GUIMessageByOptionPane;
import gui.utils.JInternalFrameManagementModel;

public class FactureManagement extends JInternalFrameManagementModel{
	private static final long serialVersionUID = 1L;

	private static FactureManagement instance = null;

	private JLabel labelDateFacture;
	private JLabel labelClient;
	private JLabel labelMontant;
	private JLabel labelTypePayementC;

	private gui.utils.GUIDate componentDateFacture;
	private javax.swing.JComboBox componentClient;
	private javax.swing.JTextField componentMontant;
	private javax.swing.JComboBox componentTypePayementC;

	public static FactureManagement getInstance(String title){
		if (instance == null)
			instance = new FactureManagement(title);
		instance.initFields();
		return instance;
	}

	public static FactureManagement getInstanceWithoutCreation(){
		return instance;
	}

	public FactureManagement(String title) {
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
		
		labelDateFacture = new JLabel(views.ViewFacture.getCaptionForDateFacture()+" Date de la facture : ");
		labelDateFacture.setVerticalAlignment(JLabel.CENTER);
		
		labelClient = new JLabel(views.ViewFacture.getCaptionForClient()+" Client : ");
		labelClient.setVerticalAlignment(JLabel.CENTER);
		
		labelMontant = new JLabel(views.ViewFacture.getCaptionForMontant()+" Montant: ");
		labelMontant.setVerticalAlignment(JLabel.CENTER);
		
		labelTypePayementC = new JLabel(views.ViewFacture.getCaptionForTypePayementC()+" Modalité de payement : ");
		labelTypePayementC.setVerticalAlignment(JLabel.CENTER);
		

		componentDateFacture = views.ViewFacture.getViewForDateFacture();
		componentClient = views.ViewFacture.getViewForClient();
		componentMontant = views.ViewFacture.getViewForMontant();
		componentTypePayementC = (javax.swing.JComboBox)views.ViewFacture.getViewForTypePayementC("JCOMBOBOXE");

//		stpListContent.setDividerLocation(300);
		
		bAjouter.setToolTipText("Ajouter un ");
		bSupprimer.setToolTipText("Supprimer le  sélectionné");
		bExporter.setToolTipText("Exporter la liste des factures vers un fichier Excel");
		bImporter.setToolTipText("Importer des factures à partir d'un fichier Excel");
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
				.addComponent(labelDateFacture)
				.addComponent(labelClient)
				.addComponent(labelMontant)
				.addComponent(labelTypePayementC)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layoutPFormulaire.createParallelGroup()
				.addComponent(componentDateFacture, 300, 300, 300)
				.addComponent(componentClient, 300, 300, 300)
				.addComponent(componentMontant, 300, 300, 300)
				.addComponent(componentTypePayementC, 300, 300, 300)
			)
			.addGap(1, 1, Short.MAX_VALUE)
		);
		layoutPFormulaire.setVerticalGroup(layoutPFormulaire.createSequentialGroup()
				.addGap(10, 10, 10)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelDateFacture, 25, 25, 25)
					.addComponent(componentDateFacture, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelClient, 25, 25, 25)
					.addComponent(componentClient, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelMontant, 25, 25, 25)
					.addComponent(componentMontant, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelTypePayementC, 25, 25, 25)
					.addComponent(componentTypePayementC, 25, 25, 25)
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
					updateListFactures();
				}
			});

			guiNavigator.setCBNumberOfItemsActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt){
					guiNavigator.updateNumberItemsInPage();
					int totalFactures = guiNavigator.getTotalNumberOfItems();
					int nbItemsInPage = guiNavigator.getNumberItemsInPage();
					int numberOfPage = totalFactures / nbItemsInPage;
					if (totalFactures % nbItemsInPage > 0)
						numberOfPage++;
					guiNavigator.setNumberOfPages(numberOfPage);
					guiNavigator.setNumeroPage(guiNavigator.getNumPageAsInt());
					
					updateListFactures(true);
				}
			});
		}
	}

	protected void tfFilterDocumentUpdated(DocumentEvent evt) {
		updateListFactures(true);
	}

	protected void bAjouterActionPerformed(ActionEvent evt) {
		if (selectedItem != null){
			if ((((Facture)selectedItem)).getId() == null || (((Facture)selectedItem)).getId() == 0){
				notifierErreur("Veuillez valider le facture que vous venez d'ajouter ...");
				return;
			}
		}

		Facture facture = new Facture();

		selectedItem = facture;
		dlmListItems.addElement(facture);
		listItems.setSelectedValue(selectedItem, true);
		fillFormulaireByFacture(facture);
	}

	protected void bFermerActionPerformed(ActionEvent evt) {
		if (dlgLayerFilter.isVisible()){
			dlgLayerFilter.setVisible(false);
			return;
		}

		if (selectedItem != null){
			if ((((Facture)selectedItem)).getId() == null || (((Facture)selectedItem)).getId() == 0){
				if (GUIMessageByOptionPane.showQuestionMessage("Un facture non ajouté", "Voulez-vous quitter sans valider cet facture ?"))
					fermer();
				else
					return;
			}
		}
		fermer();
	}

	protected void bExporterActionPerformed(ActionEvent evt) {
		fcExportImportXLS.setDialogType(JFileChooser.SAVE_DIALOG | JFileChooser.FILES_ONLY);
		fcExportImportXLS.setDialogTitle("Exporter la liste des factures sous format Excel 2003 (*.xls)");
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
		fcExportImportXLS.setDialogTitle("Importer des factures à partir d'un fichier Excel format 2003 (*.xls)");
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
		if (selectedItem == null || !(selectedItem instanceof Facture)){
			notifierErreur("Veuillez sélectionner un facture ...");
			return;
		}

		if (! GUIMessageByOptionPane.showQuestionMessage("Supprimer un facture", "Êtes-vous sûr de la suppression de cet facture ?")){
			return;
		}

		int index = listItems.getSelectedIndex();
		
		Facture facture = (Facture)selectedItem;
		if (facture.getId() != null || facture.getId() > 0){
			DAOFacture.delete(facture);
		}

		dlmListItems.remove(index);
		
		selectedItem = null;
		fillFormulaireByFacture(null);
	}

	protected void bValiderActionPerformed(ActionEvent evt) {
		if (selectedItem == null || !(selectedItem instanceof Facture)){
			notifierErreur("Veuillez sélectionner un Facture ...");
			return;
		}

		if (componentDateFacture.getMySQLDate().equals("") || componentMontant.getText().trim().equals("")){
			notifierErreur("Veuillez remplir tous les champs, SVP");
			return;
		}

		Facture selectedFacture = (Facture) selectedItem;

		if (selectedFacture.getId() == null || selectedFacture.getId() == 0){
			selectedFacture.setId(0);

			selectedFacture.setDateFacture(StringUtils.getDateFromString(componentDateFacture.getMySQLDate()));
			selectedFacture.setIdClient((componentClient.getSelectedItem() instanceof models.beans.Client) ? ((models.beans.Client)componentClient.getSelectedItem()).getId() : 0);
			selectedFacture.setMontant(Double.parseDouble(componentMontant.getText().trim()));
			selectedFacture.setTypePayementC(Facture.TypePayementC.getByValue(componentTypePayementC.getSelectedItem().toString()));
			
			selectedFacture.setId((Integer) communication.SocketCommunicator.getInstance().sendQuery(selectedFacture));
			notifierConfirmation(" sauvegardé avec succès");
		}else{
			utils.SGBDConfig.InsertUpdateSQLQueries listQueries = new utils.SGBDConfig.InsertUpdateSQLQueries((utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement[])null);
			if (selectedFacture.getDateFacture() == null || ! selectedFacture.getDateFacture().toString().equals(StringUtils.getDateFromString(componentDateFacture.getMySQLDate()))){
				selectedFacture.setDateFacture(StringUtils.getDateFromString(componentDateFacture.getMySQLDate()));
				listQueries.addInsertUpdateSQLQueries(DAOFacture.getSQLUpdateForDateFactureByPreparedStatement(selectedFacture));
			}
			if (selectedFacture.getIdClient() == null || !(componentClient.getSelectedItem() instanceof models.beans.Client) || ((models.beans.Client)componentClient.getSelectedItem()).getId().intValue() != selectedFacture.getIdClient().intValue()){
				selectedFacture.setIdClient(((models.beans.Client)componentClient.getSelectedItem()).getId());
				listQueries.addInsertUpdateSQLQueries(DAOFacture.getSQLUpdateForIdClientByPreparedStatement(selectedFacture));
			}
			if (! componentMontant.getText().trim().equals(String.valueOf(selectedFacture.getMontant()))){
				selectedFacture.setMontant(Double.parseDouble(componentMontant.getText().trim()));
				listQueries.addInsertUpdateSQLQueries(DAOFacture.getSQLUpdateForMontantByPreparedStatement(selectedFacture));
			}
			if (selectedFacture.getTypePayementC() == null || ! componentTypePayementC.getSelectedItem().toString().equals(selectedFacture.getTypePayementC().getValue())){
				selectedFacture.setTypePayementC(Facture.TypePayementC.getByValue(componentTypePayementC.getSelectedItem().toString()));
				listQueries.addInsertUpdateSQLQueries(DAOFacture.getSQLUpdateForTypePayementCByPreparedStatement(selectedFacture));
			}
			
			communication.SocketCommunicator.getInstance().sendQuery(listQueries);
			notifierConfirmation("Facture modifié avec succès");
		}
		
		dlmListItems.set(dlmListItems.indexOf(selectedItem), selectedFacture);
	}

	protected void listItemsMouseClicked(MouseEvent evt) {
		selectedItem = listItems.getSelectedValue();
		if (selectedItem == null){
			fillFormulaireByFacture(null);
			return;
		}

		fillFormulaireByFacture((Facture)selectedItem);
	}

	protected void miDeleteAllActionPerformed(ActionEvent evt) {
		if (GUIMessageByOptionPane.showQuestionMessage("Supprimer Tous ?", "Voulez-vous vraiment supprimer tous les  ?")){
			DAOFacture.deleteAll();
			fillFormulaireByFacture(null);
		}
	}

	private void exportToExcelFile(File file){
		try{
			views.ViewFacture.exportToExcel(file);
		}
		catch (Exception e){
			GUIMessageByOptionPane.showErrorMessage("Erreur de génération d'Excel", "Errueur de génération de l'Excel");
			StringUtils.printDebug(e);
		}
	}

	private void importFromExcelFile(File file){
		views.ViewFacture.importFromExcel(file);
	}

	protected void emptyFields() {
		this.componentDateFacture.clear();
		this.componentDateFacture.setEnabled(false);
		this.labelDateFacture.setEnabled(false);

		this.componentClient.setSelectedIndex(0);
		this.componentClient.setEnabled(false);
		this.labelClient.setEnabled(false);

		this.componentMontant.setText("");
		this.componentMontant.setEnabled(false);
		this.labelMontant.setEnabled(false);

		this.componentTypePayementC.setSelectedIndex(0);
		this.componentTypePayementC.setEnabled(false);
		this.labelTypePayementC.setEnabled(false);

	}

	protected void initFields() {
		this.pNotification.setVisible(JInternalFrameManagementModel.isVisibilityNotificationBar());

		selectedItem = null;

		setPhoto(null, false);

		if (guiNavigator.isVisible()){
			guiNavigator.updateNumberItemsInPage();
			int totalFactures = DAOFacture.getCountInTable();
			int nbItemsInPage = guiNavigator.getNumberItemsInPage();
			int numberOfPage = totalFactures / nbItemsInPage;
			if (totalFactures % nbItemsInPage > 0)
				numberOfPage++;
			guiNavigator.setTotalNumberOfItems(totalFactures);
			guiNavigator.setNumberOfPages(numberOfPage);
			guiNavigator.setNumeroPage(1);
		}

		updateListFactures(true);

		
		componentClient.removeAllItems();
		List<models.beans.Client> listClients = models.daos.client.DAOClient.getListInstances();
		for (models.beans.Client instance : listClients){
			componentClient.addItem(instance);
		}
		componentClient.insertItemAt("Choisir un client", 0);
		
		fillFormulaireByFacture(null);
	}

	private synchronized void updateListFactures(){
		updateListFactures(false);
	}

	private synchronized void updateListFactures(boolean filtering){
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
			condition = "WHERE id like '"+filterText+"%' ";
		}
		
		int totalFactures = DAOFacture.getCountInTable(condition);
		condition += orderByLimit;
		
		if (guiNavigator.isVisible()){
			if (filtering){
				guiNavigator.updateNumberItemsInPage();
				int nbItemsInPage = guiNavigator.getNumberItemsInPage();
				int numberOfPage = totalFactures / nbItemsInPage;
				if (totalFactures % nbItemsInPage > 0)
					numberOfPage++;
				guiNavigator.setTotalNumberOfItems(totalFactures);
				guiNavigator.setNumberOfPages(numberOfPage);
				guiNavigator.setNumeroPage(1);
			}
			if (guiNavigator.getFirst() < 0)
				return;
		}
		
		dlmListItems.clear();
		List<Facture> listFactures = DAOFacture.getListInstances(condition);
		for (Facture facture : listFactures){
			dlmListItems.addElement(facture);
		}
		
		updateNbreItems(listFactures.size(), "", totalFactures);
	}

	protected void fillFormulaireByFacture(Facture facture) {
//		pFormulaire.setVisible(facture != null);
		if (facture == null){
			emptyFields();
			return;
		}

		if (facture.getDateFacture() == null)
			this.componentDateFacture.clear();
		else
			this.componentDateFacture.setMySQLDate(facture.getDateFacture().toString());
		this.componentDateFacture.setEnabled(true);
		this.labelDateFacture.setEnabled(true);

		this.componentClient.setSelectedIndex(0);
		for (int index=1; index<this.componentClient.getItemCount(); index++){
			models.beans.Client client = (models.beans.Client)this.componentClient.getItemAt(index);
			if (client.getId().intValue() == facture.getIdClient().intValue()){
				this.componentClient.setSelectedIndex(index);
				break;
			}
		}
		this.labelClient.setEnabled(true);
		this.componentClient.setEnabled(true);
		this.componentMontant.setText(String.valueOf(facture.getMontant()));
		this.componentMontant.setEnabled(true);
		this.labelMontant.setEnabled(true);

		if (facture.getTypePayementC() != null)
			this.componentTypePayementC.setSelectedItem(facture.getTypePayementC().getValue());
		else
			this.componentTypePayementC.setSelectedIndex(0);
		this.componentTypePayementC.setEnabled(true);
		this.labelTypePayementC.setEnabled(true);

		
		listItems.setSelectedIndex(dlmListItems.indexOf(facture));
	}

	private void generateAndShowPDF(){
//		try{
//			File file = File.createTempFile( "ListeFactures", ".pdf");
//			file.deleteOnExit();
//			
//			views.ViewFacture.exportToPDF(file);
//			
//			utils.FilesAndLaunchUtils.openPDFFile(file);
//		}
//		catch (Exception e){
//			StringUtils.printDebug(e);
//		}

		if (selectedItem == null || !(selectedItem instanceof Facture)){
			notifierErreur("Veuillez sélectionner un Facture ...");
			return;
		}
		
		Facture facture = (Facture)selectedItem;
		
		controllers.Facture.imprimerFicheFacture(facture); 
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

		getInstance("Gestion des Factures");
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
		javax.swing.JFrame frame = new javax.swing.JFrame("Test of access to Table : facture");
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