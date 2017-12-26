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
import models.beans.ClientConsommeService;
import models.daos.client.DAOClientConsommeService;
import gui.utils.GUIMessageByOptionPane;
import gui.utils.JInternalFrameManagementModel;

public class ClientConsommeServiceManagement extends JInternalFrameManagementModel{
	private static final long serialVersionUID = 1L;

	private static ClientConsommeServiceManagement instance = null;

	private JLabel labelClient;
	private JLabel labelService;
	private JLabel labelPrixService;
	private JLabel labelDateConsommationService;

	private javax.swing.JComboBox componentClient;
	private javax.swing.JComboBox componentService;
	private javax.swing.JTextField componentPrixService;
	private gui.utils.GUIDate componentDateConsommationService;

	public static ClientConsommeServiceManagement getInstance(String title){
		if (instance == null)
			instance = new ClientConsommeServiceManagement(title);
		instance.initFields();
		return instance;
	}

	public static ClientConsommeServiceManagement getInstanceWithoutCreation(){
		return instance;
	}

	public ClientConsommeServiceManagement(String title) {
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
		
		labelClient = new JLabel(views.ViewClientConsommeService.getCaptionForClient()+"Client : ");
		labelClient.setVerticalAlignment(JLabel.CENTER);
		
		labelService = new JLabel(views.ViewClientConsommeService.getCaptionForService()+"Service : ");
		labelService.setVerticalAlignment(JLabel.CENTER);
		
		labelPrixService = new JLabel(views.ViewClientConsommeService.getCaptionForPrixService()+"Tarif : ");
		labelPrixService.setVerticalAlignment(JLabel.CENTER);
		
		labelDateConsommationService = new JLabel(views.ViewClientConsommeService.getCaptionForDateConsommationService()+"Date : ");
		labelDateConsommationService.setVerticalAlignment(JLabel.CENTER);
		

		componentClient = views.ViewClientConsommeService.getViewForClient();
		componentService = views.ViewClientConsommeService.getViewForService();
		componentPrixService = views.ViewClientConsommeService.getViewForPrixService();
		componentDateConsommationService = views.ViewClientConsommeService.getViewForDateConsommationService();

//		stpListContent.setDividerLocation(300);
		
		bAjouter.setToolTipText("Ajouter un ");
		bSupprimer.setToolTipText("Supprimer le  sélectionné");
		bExporter.setToolTipText("Exporter la liste des clientConsommeServices vers un fichier Excel");
		bImporter.setToolTipText("Importer des clientConsommeServices Ã  partir d'un fichier Excel");
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
				.addComponent(labelClient)
				.addComponent(labelService)
				.addComponent(labelPrixService)
				.addComponent(labelDateConsommationService)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layoutPFormulaire.createParallelGroup()
				.addComponent(componentClient, 300, 300, 300)
				.addComponent(componentService, 300, 300, 300)
				.addComponent(componentPrixService, 300, 300, 300)
				.addComponent(componentDateConsommationService, 300, 300, 300)
			)
			.addGap(1, 1, Short.MAX_VALUE)
		);
		layoutPFormulaire.setVerticalGroup(layoutPFormulaire.createSequentialGroup()
				.addGap(10, 10, 10)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelClient, 25, 25, 25)
					.addComponent(componentClient, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelService, 25, 25, 25)
					.addComponent(componentService, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelPrixService, 25, 25, 25)
					.addComponent(componentPrixService, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelDateConsommationService, 25, 25, 25)
					.addComponent(componentDateConsommationService, 25, 25, 25)
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
					updateListClientConsommeServices();
				}
			});

			guiNavigator.setCBNumberOfItemsActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt){
					guiNavigator.updateNumberItemsInPage();
					int totalClientConsommeServices = guiNavigator.getTotalNumberOfItems();
					int nbItemsInPage = guiNavigator.getNumberItemsInPage();
					int numberOfPage = totalClientConsommeServices / nbItemsInPage;
					if (totalClientConsommeServices % nbItemsInPage > 0)
						numberOfPage++;
					guiNavigator.setNumberOfPages(numberOfPage);
					guiNavigator.setNumeroPage(guiNavigator.getNumPageAsInt());
					
					updateListClientConsommeServices(true);
				}
			});
		}
	}

	protected void tfFilterDocumentUpdated(DocumentEvent evt) {
		updateListClientConsommeServices(true);
	}

	protected void bAjouterActionPerformed(ActionEvent evt) {
		if (selectedItem != null){
			if ((((ClientConsommeService)selectedItem)).getId() == null || (((ClientConsommeService)selectedItem)).getId() == 0){
				notifierErreur("Veuillez valider le clientConsommeService que vous venez d'ajouter ...");
				return;
			}
		}

		ClientConsommeService clientConsommeService = new ClientConsommeService();

		selectedItem = clientConsommeService;
		dlmListItems.addElement(clientConsommeService);
		listItems.setSelectedValue(selectedItem, true);
		fillFormulaireByClientConsommeService(clientConsommeService);
	}

	protected void bFermerActionPerformed(ActionEvent evt) {
		if (dlgLayerFilter.isVisible()){
			dlgLayerFilter.setVisible(false);
			return;
		}

		if (selectedItem != null){
			if ((((ClientConsommeService)selectedItem)).getId() == null || (((ClientConsommeService)selectedItem)).getId() == 0){
				if (GUIMessageByOptionPane.showQuestionMessage("Un clientConsommeService non ajouté", "Voulez-vous quitter sans valider cet clientConsommeService ?"))
					fermer();
				else
					return;
			}
		}
		fermer();
	}

	protected void bExporterActionPerformed(ActionEvent evt) {
		fcExportImportXLS.setDialogType(JFileChooser.SAVE_DIALOG | JFileChooser.FILES_ONLY);
		fcExportImportXLS.setDialogTitle("Exporter la liste des clientConsommeServices sous format Excel 2003 (*.xls)");
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
		fcExportImportXLS.setDialogTitle("Importer des clientConsommeServices Ã  partir d'un fichier Excel format 2003 (*.xls)");
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
		if (selectedItem == null || !(selectedItem instanceof ClientConsommeService)){
			notifierErreur("Veuillez sélectionner un clientConsommeService ...");
			return;
		}

		if (! GUIMessageByOptionPane.showQuestionMessage("Supprimer un clientConsommeService", "ÃŠtes-vous sÃ»r de la suppression de cet clientConsommeService ?")){
			return;
		}

		int index = listItems.getSelectedIndex();
		
		ClientConsommeService clientConsommeService = (ClientConsommeService)selectedItem;
		if (clientConsommeService.getId() != null || clientConsommeService.getId() > 0){
			DAOClientConsommeService.delete(clientConsommeService);
		}

		dlmListItems.remove(index);
		
		selectedItem = null;
		fillFormulaireByClientConsommeService(null);
	}

	protected void bValiderActionPerformed(ActionEvent evt) {
		if (selectedItem == null || !(selectedItem instanceof ClientConsommeService)){
			notifierErreur("Veuillez sélectionner un ClientConsommeService ...");
			return;
		}

		if (componentPrixService.getText().trim().equals("") || componentDateConsommationService.getMySQLDate().equals("")){
			notifierErreur("Veuillez remplir tous les champs, SVP");
			return;
		}

		ClientConsommeService selectedClientConsommeService = (ClientConsommeService) selectedItem;

		if (selectedClientConsommeService.getId() == null || selectedClientConsommeService.getId() == 0){
			selectedClientConsommeService.setId(0);

			selectedClientConsommeService.setIdClient((componentClient.getSelectedItem() instanceof models.beans.Client) ? ((models.beans.Client)componentClient.getSelectedItem()).getId() : 0);
			selectedClientConsommeService.setIdService((componentService.getSelectedItem() instanceof models.beans.Service) ? ((models.beans.Service)componentService.getSelectedItem()).getId() : 0);
			selectedClientConsommeService.setPrixService(Double.parseDouble(componentPrixService.getText().trim()));
			selectedClientConsommeService.setDateConsommationService(StringUtils.getDateFromString(componentDateConsommationService.getMySQLDate()));
			
			selectedClientConsommeService.setId((Integer) communication.SocketCommunicator.getInstance().sendQuery(selectedClientConsommeService));
			notifierConfirmation(" sauvegardé avec succès");
		}else{
			utils.SGBDConfig.InsertUpdateSQLQueries listQueries = new utils.SGBDConfig.InsertUpdateSQLQueries((utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement[])null);
			if (selectedClientConsommeService.getIdClient() == null || !(componentClient.getSelectedItem() instanceof models.beans.Client) || ((models.beans.Client)componentClient.getSelectedItem()).getId().intValue() != selectedClientConsommeService.getIdClient().intValue()){
				selectedClientConsommeService.setIdClient(((models.beans.Client)componentClient.getSelectedItem()).getId());
				listQueries.addInsertUpdateSQLQueries(DAOClientConsommeService.getSQLUpdateForIdClientByPreparedStatement(selectedClientConsommeService));
			}
			if (selectedClientConsommeService.getIdService() == null || !(componentService.getSelectedItem() instanceof models.beans.Service) || ((models.beans.Service)componentService.getSelectedItem()).getId().intValue() != selectedClientConsommeService.getIdService().intValue()){
				selectedClientConsommeService.setIdService(((models.beans.Service)componentService.getSelectedItem()).getId());
				listQueries.addInsertUpdateSQLQueries(DAOClientConsommeService.getSQLUpdateForIdServiceByPreparedStatement(selectedClientConsommeService));
			}
			if (! componentPrixService.getText().trim().equals(String.valueOf(selectedClientConsommeService.getPrixService()))){
				selectedClientConsommeService.setPrixService(Double.parseDouble(componentPrixService.getText().trim()));
				listQueries.addInsertUpdateSQLQueries(DAOClientConsommeService.getSQLUpdateForPrixServiceByPreparedStatement(selectedClientConsommeService));
			}
			if (selectedClientConsommeService.getDateConsommationService() == null || ! selectedClientConsommeService.getDateConsommationService().toString().equals(StringUtils.getDateFromString(componentDateConsommationService.getMySQLDate()))){
				selectedClientConsommeService.setDateConsommationService(StringUtils.getDateFromString(componentDateConsommationService.getMySQLDate()));
				listQueries.addInsertUpdateSQLQueries(DAOClientConsommeService.getSQLUpdateForDateConsommationServiceByPreparedStatement(selectedClientConsommeService));
			}
			
			communication.SocketCommunicator.getInstance().sendQuery(listQueries);
			notifierConfirmation("ClientConsommeService modifié avec succès");
		}
		
		dlmListItems.set(dlmListItems.indexOf(selectedItem), selectedClientConsommeService);
	}

	protected void listItemsMouseClicked(MouseEvent evt) {
		selectedItem = listItems.getSelectedValue();
		if (selectedItem == null){
			fillFormulaireByClientConsommeService(null);
			return;
		}

		fillFormulaireByClientConsommeService((ClientConsommeService)selectedItem);
	}

	protected void miDeleteAllActionPerformed(ActionEvent evt) {
		if (GUIMessageByOptionPane.showQuestionMessage("Supprimer Tous ?", "Voulez-vous vraiment supprimer tous les  ?")){
			DAOClientConsommeService.deleteAll();
			fillFormulaireByClientConsommeService(null);
		}
	}

	private void exportToExcelFile(File file){
		try{
			views.ViewClientConsommeService.exportToExcel(file);
		}
		catch (Exception e){
			GUIMessageByOptionPane.showErrorMessage("Erreur de génération d'Excel", "Errueur de génération de l'Excel");
			StringUtils.printDebug(e);
		}
	}

	private void importFromExcelFile(File file){
		views.ViewClientConsommeService.importFromExcel(file);
	}

	protected void emptyFields() {
		this.componentClient.setSelectedIndex(0);
		this.componentClient.setEnabled(false);
		this.labelClient.setEnabled(false);

		this.componentService.setSelectedIndex(0);
		this.componentService.setEnabled(false);
		this.labelService.setEnabled(false);

		this.componentPrixService.setText("");
		this.componentPrixService.setEnabled(false);
		this.labelPrixService.setEnabled(false);

		this.componentDateConsommationService.clear();
		this.componentDateConsommationService.setEnabled(false);
		this.labelDateConsommationService.setEnabled(false);

	}

	protected void initFields() {
		this.pNotification.setVisible(JInternalFrameManagementModel.isVisibilityNotificationBar());

		selectedItem = null;

		setPhoto(null, false);

		if (guiNavigator.isVisible()){
			guiNavigator.updateNumberItemsInPage();
			int totalClientConsommeServices = DAOClientConsommeService.getCountInTable();
			int nbItemsInPage = guiNavigator.getNumberItemsInPage();
			int numberOfPage = totalClientConsommeServices / nbItemsInPage;
			if (totalClientConsommeServices % nbItemsInPage > 0)
				numberOfPage++;
			guiNavigator.setTotalNumberOfItems(totalClientConsommeServices);
			guiNavigator.setNumberOfPages(numberOfPage);
			guiNavigator.setNumeroPage(1);
		}

		updateListClientConsommeServices(true);

		
		componentClient.removeAllItems();
		List<models.beans.Client> listClients = models.daos.client.DAOClient.getListInstances();
		for (models.beans.Client instance : listClients){
			componentClient.addItem(instance);
		}
		componentClient.insertItemAt("Choisir un client", 0);
		
		componentService.removeAllItems();
		List<models.beans.Service> listServices = models.daos.client.DAOService.getListInstances();
		for (models.beans.Service instance : listServices){
			componentService.addItem(instance);
		}
		componentService.insertItemAt("Choisir un service", 0);
		
		fillFormulaireByClientConsommeService(null);
	}

	private synchronized void updateListClientConsommeServices(){
		updateListClientConsommeServices(false);
	}

	private synchronized void updateListClientConsommeServices(boolean filtering){
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
			condition = "WHERE  1 ";
		}
		else{
			condition = "WHERE id like '"+filterText+"%' ";
		}
		
		int totalClientConsommeServices = DAOClientConsommeService.getCountInTable(condition);
		condition += orderByLimit;
		
		if (guiNavigator.isVisible()){
			if (filtering){
				guiNavigator.updateNumberItemsInPage();
				int nbItemsInPage = guiNavigator.getNumberItemsInPage();
				int numberOfPage = totalClientConsommeServices / nbItemsInPage;
				if (totalClientConsommeServices % nbItemsInPage > 0)
					numberOfPage++;
				guiNavigator.setTotalNumberOfItems(totalClientConsommeServices);
				guiNavigator.setNumberOfPages(numberOfPage);
				guiNavigator.setNumeroPage(1);
			}
			if (guiNavigator.getFirst() < 0)
				return;
		}
		
		dlmListItems.clear();
		List<ClientConsommeService> listClientConsommeServices = DAOClientConsommeService.getListInstances(condition);
		for (ClientConsommeService clientConsommeService : listClientConsommeServices){
			dlmListItems.addElement(clientConsommeService);
		}
		
		updateNbreItems(listClientConsommeServices.size(), "", totalClientConsommeServices);
	}

	protected void fillFormulaireByClientConsommeService(ClientConsommeService clientConsommeService) {
//		pFormulaire.setVisible(clientConsommeService != null);
		if (clientConsommeService == null){
			emptyFields();
			return;
		}

		this.componentClient.setSelectedIndex(0);
		for (int index=1; index<this.componentClient.getItemCount(); index++){
			models.beans.Client client = (models.beans.Client)this.componentClient.getItemAt(index);
			if (client.getId().intValue() == clientConsommeService.getIdClient().intValue()){
				this.componentClient.setSelectedIndex(index);
				break;
			}
		}
		this.labelClient.setEnabled(true);
		this.componentClient.setEnabled(true);
		this.componentService.setSelectedIndex(0);
		for (int index=1; index<this.componentService.getItemCount(); index++){
			models.beans.Service service = (models.beans.Service)this.componentService.getItemAt(index);
			if (service.getId().intValue() == clientConsommeService.getIdService().intValue()){
				this.componentService.setSelectedIndex(index);
				break;
			}
		}
		this.labelService.setEnabled(true);
		this.componentService.setEnabled(true);
		this.componentPrixService.setText(String.valueOf(clientConsommeService.getPrixService()));
		this.componentPrixService.setEnabled(true);
		this.labelPrixService.setEnabled(true);

		if (clientConsommeService.getDateConsommationService() == null)
			this.componentDateConsommationService.clear();
		else
			this.componentDateConsommationService.setMySQLDate(clientConsommeService.getDateConsommationService().toString());
		this.componentDateConsommationService.setEnabled(true);
		this.labelDateConsommationService.setEnabled(true);

		
		listItems.setSelectedIndex(dlmListItems.indexOf(clientConsommeService));
	}

	private void generateAndShowPDF(){
		try{
			File file = File.createTempFile( "ListeClientConsommeServices", ".pdf");
			file.deleteOnExit();
			
			views.ViewClientConsommeService.exportToPDF(file);
			
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

		getInstance("Gestion des Consommations");
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
		javax.swing.JFrame frame = new javax.swing.JFrame("Test of access to Table : clientConsommeService");
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