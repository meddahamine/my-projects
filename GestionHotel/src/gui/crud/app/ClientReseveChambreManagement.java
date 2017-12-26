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

import models.beans.ClientReseveChambre;
import models.daos.client.DAOClientReseveChambre;

import gui.utils.GUIMessageByOptionPane;
import gui.utils.JInternalFrameManagementModel;

public class ClientReseveChambreManagement extends JInternalFrameManagementModel{
	private static final long serialVersionUID = 1L;

	private static ClientReseveChambreManagement instance = null;

	private JLabel labelClient;
	private JLabel labelChambre;
	private JLabel labelDateDebutReservation;
	private JLabel labelDateFinReservation;

	private javax.swing.JComboBox componentClient;
	private javax.swing.JComboBox componentChambre;
	private gui.utils.GUIDate componentDateDebutReservation;
	private gui.utils.GUIDate componentDateFinReservation;

	public static ClientReseveChambreManagement getInstance(String title){
		if (instance == null)
			instance = new ClientReseveChambreManagement(title);
		instance.initFields();
		return instance;
	}

	public static ClientReseveChambreManagement getInstanceWithoutCreation(){
		return instance;
	}

	public ClientReseveChambreManagement(String title) {
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
		
		labelClient = new JLabel(views.ViewClientReseveChambre.getCaptionForClient()+" Client : ");
		labelClient.setVerticalAlignment(JLabel.CENTER);
		
		labelChambre = new JLabel(views.ViewClientReseveChambre.getCaptionForChambre()+"Chambre : ");
		labelChambre.setVerticalAlignment(JLabel.CENTER);
		
		labelDateDebutReservation = new JLabel(views.ViewClientReseveChambre.getCaptionForDateDebutReservation()+"Date Debut : ");
		labelDateDebutReservation.setVerticalAlignment(JLabel.CENTER);
		
		labelDateFinReservation = new JLabel(views.ViewClientReseveChambre.getCaptionForDateFinReservation()+"Date Fin : ");
		labelDateFinReservation.setVerticalAlignment(JLabel.CENTER);
		

		componentClient = views.ViewClientReseveChambre.getViewForClient();
		componentChambre = views.ViewClientReseveChambre.getViewForChambre();
		componentDateDebutReservation = views.ViewClientReseveChambre.getViewForDateDebutReservation();
		componentDateFinReservation = views.ViewClientReseveChambre.getViewForDateFinReservation();

//		stpListContent.setDividerLocation(300);
		
		bAjouter.setToolTipText("Ajouter un ");
		bSupprimer.setToolTipText("Supprimer le  sélectionné");
		bExporter.setToolTipText("Exporter la liste des clientReseveChambres vers un fichier Excel");
		bImporter.setToolTipText("Importer des clientReseveChambres Ã  partir d'un fichier Excel");
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
				.addComponent(labelChambre)
				.addComponent(labelDateDebutReservation)
				.addComponent(labelDateFinReservation)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layoutPFormulaire.createParallelGroup()
				.addComponent(componentClient, 300, 300, 300)
				.addComponent(componentChambre, 300, 300, 300)
				.addComponent(componentDateDebutReservation, 300, 300, 300)
				.addComponent(componentDateFinReservation, 300, 300, 300)
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
					.addComponent(labelChambre, 25, 25, 25)
					.addComponent(componentChambre, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelDateDebutReservation, 25, 25, 25)
					.addComponent(componentDateDebutReservation, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelDateFinReservation, 25, 25, 25)
					.addComponent(componentDateFinReservation, 25, 25, 25)
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
					updateListClientReseveChambres();
				}
			});

			guiNavigator.setCBNumberOfItemsActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt){
					guiNavigator.updateNumberItemsInPage();
					int totalClientReseveChambres = guiNavigator.getTotalNumberOfItems();
					int nbItemsInPage = guiNavigator.getNumberItemsInPage();
					int numberOfPage = totalClientReseveChambres / nbItemsInPage;
					if (totalClientReseveChambres % nbItemsInPage > 0)
						numberOfPage++;
					guiNavigator.setNumberOfPages(numberOfPage);
					guiNavigator.setNumeroPage(guiNavigator.getNumPageAsInt());
					
					updateListClientReseveChambres(true);
				}
			});
		}
	}

	protected void tfFilterDocumentUpdated(DocumentEvent evt) {
		updateListClientReseveChambres(true);
	}

	protected void bAjouterActionPerformed(ActionEvent evt) {
		if (selectedItem != null){
			if ((((ClientReseveChambre)selectedItem)).getId() == null || (((ClientReseveChambre)selectedItem)).getId() == 0){
				notifierErreur("Veuillez valider le clientReseveChambre que vous venez d'ajouter ...");
				return;
			}
		}

		ClientReseveChambre clientReseveChambre = new ClientReseveChambre();

		selectedItem = clientReseveChambre;
		dlmListItems.addElement(clientReseveChambre);
		listItems.setSelectedValue(selectedItem, true);
		fillFormulaireByClientReseveChambre(clientReseveChambre);
	}

	protected void bFermerActionPerformed(ActionEvent evt) {
		if (dlgLayerFilter.isVisible()){
			dlgLayerFilter.setVisible(false);
			return;
		}

		if (selectedItem != null){
			if ((((ClientReseveChambre)selectedItem)).getId() == null || (((ClientReseveChambre)selectedItem)).getId() == 0){
				if (GUIMessageByOptionPane.showQuestionMessage("Un clientReseveChambre non ajouté", "Voulez-vous quitter sans valider cet clientReseveChambre ?"))
					fermer();
				else
					return;
			}
		}
		fermer();
	}

	protected void bExporterActionPerformed(ActionEvent evt) {
		fcExportImportXLS.setDialogType(JFileChooser.SAVE_DIALOG | JFileChooser.FILES_ONLY);
		fcExportImportXLS.setDialogTitle("Exporter la liste des clientReseveChambres sous format Excel 2003 (*.xls)");
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
		fcExportImportXLS.setDialogTitle("Importer des clientReseveChambres Ã  partir d'un fichier Excel format 2003 (*.xls)");
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
		if (selectedItem == null || !(selectedItem instanceof ClientReseveChambre)){
			notifierErreur("Veuillez sélectionner un clientReseveChambre ...");
			return;
		}

		if (! GUIMessageByOptionPane.showQuestionMessage("Supprimer un clientReseveChambre", "ÃŠtes-vous sÃ»r de la suppression de cet clientReseveChambre ?")){
			return;
		}

		int index = listItems.getSelectedIndex();
		
		ClientReseveChambre clientReseveChambre = (ClientReseveChambre)selectedItem;
		if (clientReseveChambre.getId() != null || clientReseveChambre.getId() > 0){
			DAOClientReseveChambre.delete(clientReseveChambre);
		}

		dlmListItems.remove(index);
		
		selectedItem = null;
		fillFormulaireByClientReseveChambre(null);
	}

	protected void bValiderActionPerformed(ActionEvent evt) {
		if (selectedItem == null || !(selectedItem instanceof ClientReseveChambre)){
			notifierErreur("Veuillez sélectionner un ClientReseveChambre ...");
			return;
		}

		if (componentDateDebutReservation.getMySQLDate().equals("") || componentDateFinReservation.getMySQLDate().equals("")){
			notifierErreur("Veuillez remplir tous les champs, SVP");
			return;
		}

		ClientReseveChambre selectedClientReseveChambre = (ClientReseveChambre) selectedItem;

		if (selectedClientReseveChambre.getId() == null || selectedClientReseveChambre.getId() == 0){
			selectedClientReseveChambre.setId(0);

			selectedClientReseveChambre.setIdClient((componentClient.getSelectedItem() instanceof models.beans.Client) ? ((models.beans.Client)componentClient.getSelectedItem()).getId() : 0);
			selectedClientReseveChambre.setIdChambre((componentChambre.getSelectedItem() instanceof models.beans.Chambre) ? ((models.beans.Chambre)componentChambre.getSelectedItem()).getId() : 0);
			selectedClientReseveChambre.setDateDebutReservation(StringUtils.getDateFromString(componentDateDebutReservation.getMySQLDate()));
			selectedClientReseveChambre.setDateFinReservation(StringUtils.getDateFromString(componentDateFinReservation.getMySQLDate()));
			
			selectedClientReseveChambre.setId((Integer) communication.SocketCommunicator.getInstance().sendQuery(selectedClientReseveChambre));
			notifierConfirmation(" sauvegardé avec succès");
		}else{
			utils.SGBDConfig.InsertUpdateSQLQueries listQueries = new utils.SGBDConfig.InsertUpdateSQLQueries((utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement[])null);
			if (selectedClientReseveChambre.getIdClient() == null || !(componentClient.getSelectedItem() instanceof models.beans.Client) || ((models.beans.Client)componentClient.getSelectedItem()).getId().intValue() != selectedClientReseveChambre.getIdClient().intValue()){
				selectedClientReseveChambre.setIdClient(((models.beans.Client)componentClient.getSelectedItem()).getId());
				listQueries.addInsertUpdateSQLQueries(DAOClientReseveChambre.getSQLUpdateForIdClientByPreparedStatement(selectedClientReseveChambre));
			}
			if (selectedClientReseveChambre.getIdChambre() == null || !(componentChambre.getSelectedItem() instanceof models.beans.Chambre) || ((models.beans.Chambre)componentChambre.getSelectedItem()).getId().intValue() != selectedClientReseveChambre.getIdChambre().intValue()){
				selectedClientReseveChambre.setIdChambre(((models.beans.Chambre)componentChambre.getSelectedItem()).getId());
				listQueries.addInsertUpdateSQLQueries(DAOClientReseveChambre.getSQLUpdateForIdChambreByPreparedStatement(selectedClientReseveChambre));
			}
			if (selectedClientReseveChambre.getDateDebutReservation() == null || ! selectedClientReseveChambre.getDateDebutReservation().toString().equals(StringUtils.getDateFromString(componentDateDebutReservation.getMySQLDate()))){
				selectedClientReseveChambre.setDateDebutReservation(StringUtils.getDateFromString(componentDateDebutReservation.getMySQLDate()));
				listQueries.addInsertUpdateSQLQueries(DAOClientReseveChambre.getSQLUpdateForDateDebutReservationByPreparedStatement(selectedClientReseveChambre));
			}
			if (selectedClientReseveChambre.getDateFinReservation() == null || ! selectedClientReseveChambre.getDateFinReservation().toString().equals(StringUtils.getDateFromString(componentDateFinReservation.getMySQLDate()))){
				selectedClientReseveChambre.setDateFinReservation(StringUtils.getDateFromString(componentDateFinReservation.getMySQLDate()));
				listQueries.addInsertUpdateSQLQueries(DAOClientReseveChambre.getSQLUpdateForDateFinReservationByPreparedStatement(selectedClientReseveChambre));
			}
			
			communication.SocketCommunicator.getInstance().sendQuery(listQueries);
			notifierConfirmation("ClientReseveChambre modifié avec succès");
		}
		
		dlmListItems.set(dlmListItems.indexOf(selectedItem), selectedClientReseveChambre);
	}

	protected void listItemsMouseClicked(MouseEvent evt) {
		selectedItem = listItems.getSelectedValue();
		if (selectedItem == null){
			fillFormulaireByClientReseveChambre(null);
			return;
		}

		fillFormulaireByClientReseveChambre((ClientReseveChambre)selectedItem);
	}

	protected void miDeleteAllActionPerformed(ActionEvent evt) {
		if (GUIMessageByOptionPane.showQuestionMessage("Supprimer Tous ?", "Voulez-vous vraiment supprimer tous les  ?")){
			DAOClientReseveChambre.deleteAll();
			fillFormulaireByClientReseveChambre(null);
		}
	}

	private void exportToExcelFile(File file){
		try{
			views.ViewClientReseveChambre.exportToExcel(file);
		}
		catch (Exception e){
			GUIMessageByOptionPane.showErrorMessage("Erreur de génération d'Excel", "Errueur de génération de l'Excel");
			StringUtils.printDebug(e);
		}
	}

	private void importFromExcelFile(File file){
		views.ViewClientReseveChambre.importFromExcel(file);
	}

	protected void emptyFields() {
		this.componentClient.setSelectedIndex(0);
		this.componentClient.setEnabled(false);
		this.labelClient.setEnabled(false);

		this.componentChambre.setSelectedIndex(0);
		this.componentChambre.setEnabled(false);
		this.labelChambre.setEnabled(false);

		this.componentDateDebutReservation.clear();
		this.componentDateDebutReservation.setEnabled(false);
		this.labelDateDebutReservation.setEnabled(false);

		this.componentDateFinReservation.clear();
		this.componentDateFinReservation.setEnabled(false);
		this.labelDateFinReservation.setEnabled(false);

	}

	protected void initFields() {
		this.pNotification.setVisible(JInternalFrameManagementModel.isVisibilityNotificationBar());

		selectedItem = null;

		setPhoto(null, false);

		if (guiNavigator.isVisible()){
			guiNavigator.updateNumberItemsInPage();
			int totalClientReseveChambres = DAOClientReseveChambre.getCountInTable();
			int nbItemsInPage = guiNavigator.getNumberItemsInPage();
			int numberOfPage = totalClientReseveChambres / nbItemsInPage;
			if (totalClientReseveChambres % nbItemsInPage > 0)
				numberOfPage++;
			guiNavigator.setTotalNumberOfItems(totalClientReseveChambres);
			guiNavigator.setNumberOfPages(numberOfPage);
			guiNavigator.setNumeroPage(1);
		}

		updateListClientReseveChambres(true);

		
		componentClient.removeAllItems();
		List<models.beans.Client> listClients = models.daos.client.DAOClient.getListInstances();
		for (models.beans.Client instance : listClients){
			componentClient.addItem(instance);
		}
		componentClient.insertItemAt("Choisir un client", 0);
		
		componentChambre.removeAllItems();
		List<models.beans.Chambre> listChambres = models.daos.client.DAOChambre.getListInstances();
		for (models.beans.Chambre instance : listChambres){
			componentChambre.addItem(instance);
		}
		componentChambre.insertItemAt("Choisir une chambre", 0);
		
		fillFormulaireByClientReseveChambre(null);
	}

	private synchronized void updateListClientReseveChambres(){
		updateListClientReseveChambres(false);
	}

	private synchronized void updateListClientReseveChambres(boolean filtering){
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
		
		int totalClientReseveChambres = DAOClientReseveChambre.getCountInTable(condition);
		condition += orderByLimit;
		
		if (guiNavigator.isVisible()){
			if (filtering){
				guiNavigator.updateNumberItemsInPage();
				int nbItemsInPage = guiNavigator.getNumberItemsInPage();
				int numberOfPage = totalClientReseveChambres / nbItemsInPage;
				if (totalClientReseveChambres % nbItemsInPage > 0)
					numberOfPage++;
				guiNavigator.setTotalNumberOfItems(totalClientReseveChambres);
				guiNavigator.setNumberOfPages(numberOfPage);
				guiNavigator.setNumeroPage(1);
			}
			if (guiNavigator.getFirst() < 0)
				return;
		}
		
		dlmListItems.clear();
		List<ClientReseveChambre> listClientReseveChambres = DAOClientReseveChambre.getListInstances(condition);
		for (ClientReseveChambre clientReseveChambre : listClientReseveChambres){
			dlmListItems.addElement(clientReseveChambre);
		}
		
		updateNbreItems(listClientReseveChambres.size(), "", totalClientReseveChambres);
	}

	protected void fillFormulaireByClientReseveChambre(ClientReseveChambre clientReseveChambre) {
//		pFormulaire.setVisible(clientReseveChambre != null);
		if (clientReseveChambre == null){
			emptyFields();
			return;
		}

		this.componentClient.setSelectedIndex(0);
		for (int index=1; index<this.componentClient.getItemCount(); index++){
			models.beans.Client client = (models.beans.Client)this.componentClient.getItemAt(index);
			if (client.getId().intValue() == clientReseveChambre.getIdClient().intValue()){
				this.componentClient.setSelectedIndex(index);
				break;
			}
		}
		this.labelClient.setEnabled(true);
		this.componentClient.setEnabled(true);
		this.componentChambre.setSelectedIndex(0);
		for (int index=1; index<this.componentChambre.getItemCount(); index++){
			models.beans.Chambre chambre = (models.beans.Chambre)this.componentChambre.getItemAt(index);
			if (chambre.getId().intValue() == clientReseveChambre.getIdChambre().intValue()){
				this.componentChambre.setSelectedIndex(index);
				break;
			}
		}
		this.labelChambre.setEnabled(true);
		this.componentChambre.setEnabled(true);
		if (clientReseveChambre.getDateDebutReservation() == null)
			this.componentDateDebutReservation.clear();
		else
			this.componentDateDebutReservation.setMySQLDate(clientReseveChambre.getDateDebutReservation().toString());
		this.componentDateDebutReservation.setEnabled(true);
		this.labelDateDebutReservation.setEnabled(true);

		if (clientReseveChambre.getDateFinReservation() == null)
			this.componentDateFinReservation.clear();
		else
			this.componentDateFinReservation.setMySQLDate(clientReseveChambre.getDateFinReservation().toString());
		this.componentDateFinReservation.setEnabled(true);
		this.labelDateFinReservation.setEnabled(true);

		
		listItems.setSelectedIndex(dlmListItems.indexOf(clientReseveChambre));
	}

	private void generateAndShowPDF(){
		try{
			File file = File.createTempFile( "ListeClientReseveChambres", ".pdf");
			file.deleteOnExit();
			
			views.ViewClientReseveChambre.exportToPDF(file);
			
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

		getInstance("Gestion des Réservations");
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
		javax.swing.JFrame frame = new javax.swing.JFrame("Test of access to Table : clientReseveChambre");
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