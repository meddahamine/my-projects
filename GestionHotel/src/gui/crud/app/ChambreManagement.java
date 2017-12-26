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

import models.beans.Chambre;
import models.daos.client.DAOChambre;

import gui.utils.GUIMessageByOptionPane;
import gui.utils.JInternalFrameManagementModel;

public class ChambreManagement extends JInternalFrameManagementModel{
	private static final long serialVersionUID = 1L;

	private static ChambreManagement instance = null;

	private JLabel labelNumChamre;
	private JLabel labelNumEtage;
	private JLabel labelCategorieChambre;
	private JLabel labelPrix;

	private javax.swing.JTextField componentNumChamre;
	private javax.swing.JTextField componentNumEtage;
	private javax.swing.JComboBox componentCategorieChambre;
	private javax.swing.JTextField componentPrix;

	public static ChambreManagement getInstance(String title){
		if (instance == null)
			instance = new ChambreManagement(title);
		instance.initFields();
		return instance;
	}

	public static ChambreManagement getInstanceWithoutCreation(){
		return instance;
	}

	public ChambreManagement(String title) {
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
		
		labelNumChamre = new JLabel(views.ViewChambre.getCaptionForNumChamre()+"Numéro de chambre : ");
		labelNumChamre.setVerticalAlignment(JLabel.CENTER);
		
		labelNumEtage = new JLabel(views.ViewChambre.getCaptionForNumEtage()+" Numéro de l'étage : ");
		labelNumEtage.setVerticalAlignment(JLabel.CENTER);
		
		labelCategorieChambre = new JLabel(views.ViewChambre.getCaptionForCategorieChambre()+"Catégorie de la Chambre : ");
		labelCategorieChambre.setVerticalAlignment(JLabel.CENTER);
		
		labelPrix = new JLabel(views.ViewChambre.getCaptionForPrix()+" Tarif : ");
		labelPrix.setVerticalAlignment(JLabel.CENTER);
		

		componentNumChamre = views.ViewChambre.getViewForNumChamre();
		componentNumEtage = views.ViewChambre.getViewForNumEtage();
		componentCategorieChambre = views.ViewChambre.getViewForCategorieChambre();
		componentPrix = views.ViewChambre.getViewForPrix();

//		stpListContent.setDividerLocation(300);
		
		bAjouter.setToolTipText("Ajouter un ");
		bSupprimer.setToolTipText("Supprimer le  sélectionné");
		bExporter.setToolTipText("Exporter la liste des chambres vers un fichier Excel");
		bImporter.setToolTipText("Importer des chambres à partir d'un fichier Excel");
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
				.addComponent(labelNumChamre)
				.addComponent(labelNumEtage)
				.addComponent(labelCategorieChambre)
				.addComponent(labelPrix)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layoutPFormulaire.createParallelGroup()
				.addComponent(componentNumChamre, 300, 300, 300)
				.addComponent(componentNumEtage, 300, 300, 300)
				.addComponent(componentCategorieChambre, 300, 300, 300)
				.addComponent(componentPrix, 300, 300, 300)
			)
			.addGap(1, 1, Short.MAX_VALUE)
		);
		layoutPFormulaire.setVerticalGroup(layoutPFormulaire.createSequentialGroup()
				.addGap(10, 10, 10)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelNumChamre, 25, 25, 25)
					.addComponent(componentNumChamre, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelNumEtage, 25, 25, 25)
					.addComponent(componentNumEtage, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelCategorieChambre, 25, 25, 25)
					.addComponent(componentCategorieChambre, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelPrix, 25, 25, 25)
					.addComponent(componentPrix, 25, 25, 25)
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
					updateListChambres();
				}
			});

			guiNavigator.setCBNumberOfItemsActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt){
					guiNavigator.updateNumberItemsInPage();
					int totalChambres = guiNavigator.getTotalNumberOfItems();
					int nbItemsInPage = guiNavigator.getNumberItemsInPage();
					int numberOfPage = totalChambres / nbItemsInPage;
					if (totalChambres % nbItemsInPage > 0)
						numberOfPage++;
					guiNavigator.setNumberOfPages(numberOfPage);
					guiNavigator.setNumeroPage(guiNavigator.getNumPageAsInt());
					
					updateListChambres(true);
				}
			});
		}
	}

	protected void tfFilterDocumentUpdated(DocumentEvent evt) {
		updateListChambres(true);
	}

	protected void bAjouterActionPerformed(ActionEvent evt) {
		if (selectedItem != null){
			if ((((Chambre)selectedItem)).getId() == null || (((Chambre)selectedItem)).getId() == 0){
				notifierErreur("Veuillez valider le chambre que vous venez d'ajouter ...");
				return;
			}
		}

		Chambre chambre = new Chambre();

		selectedItem = chambre;
		dlmListItems.addElement(chambre);
		listItems.setSelectedValue(selectedItem, true);
		fillFormulaireByChambre(chambre);
	}

	protected void bFermerActionPerformed(ActionEvent evt) {
		if (dlgLayerFilter.isVisible()){
			dlgLayerFilter.setVisible(false);
			return;
		}

		if (selectedItem != null){
			if ((((Chambre)selectedItem)).getId() == null || (((Chambre)selectedItem)).getId() == 0){
				if (GUIMessageByOptionPane.showQuestionMessage("Un chambre non ajouté", "Voulez-vous quitter sans valider cet chambre ?"))
					fermer();
				else
					return;
			}
		}
		fermer();
	}

	protected void bExporterActionPerformed(ActionEvent evt) {
		fcExportImportXLS.setDialogType(JFileChooser.SAVE_DIALOG | JFileChooser.FILES_ONLY);
		fcExportImportXLS.setDialogTitle("Exporter la liste des chambres sous format Excel 2003 (*.xls)");
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
		fcExportImportXLS.setDialogTitle("Importer des chambres à partir d'un fichier Excel format 2003 (*.xls)");
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
		if (selectedItem == null || !(selectedItem instanceof Chambre)){
			notifierErreur("Veuillez sélectionner un chambre ...");
			return;
		}

		if (! GUIMessageByOptionPane.showQuestionMessage("Supprimer un chambre", "Êtes-vous sûr de la suppression de cet chambre ?")){
			return;
		}

		int index = listItems.getSelectedIndex();
		
		Chambre chambre = (Chambre)selectedItem;
		if (chambre.getId() != null || chambre.getId() > 0){
			DAOChambre.delete(chambre);
		}

		dlmListItems.remove(index);
		
		selectedItem = null;
		fillFormulaireByChambre(null);
	}

	protected void bValiderActionPerformed(ActionEvent evt) {
		if (selectedItem == null || !(selectedItem instanceof Chambre)){
			notifierErreur("Veuillez sélectionner un Chambre ...");
			return;
		}

		if (componentNumChamre.getText().trim().equals("") || componentNumEtage.getText().trim().equals("") || componentPrix.getText().trim().equals("")){
			notifierErreur("Veuillez remplir tous les champs, SVP");
			return;
		}

		Chambre selectedChambre = (Chambre) selectedItem;

		if (selectedChambre.getId() == null || selectedChambre.getId() == 0){
			selectedChambre.setId(0);

			selectedChambre.setNumChamre(componentNumChamre.getText().trim());
			selectedChambre.setNumEtage(componentNumEtage.getText().trim());
			selectedChambre.setIdCategorieChambre((componentCategorieChambre.getSelectedItem() instanceof models.beans.CategorieChambre) ? ((models.beans.CategorieChambre)componentCategorieChambre.getSelectedItem()).getId() : 0);
			selectedChambre.setPrix(Double.parseDouble(componentPrix.getText().trim()));
			
			selectedChambre.setId((Integer) communication.SocketCommunicator.getInstance().sendQuery(selectedChambre));
			notifierConfirmation(" sauvegardé avec succès");
		}else{
			utils.SGBDConfig.InsertUpdateSQLQueries listQueries = new utils.SGBDConfig.InsertUpdateSQLQueries((utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement[])null);
			if (! componentNumChamre.getText().trim().equals(selectedChambre.getNumChamre())){
				selectedChambre.setNumChamre(componentNumChamre.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOChambre.getSQLUpdateForNumChamreByPreparedStatement(selectedChambre));
			}
			if (! componentNumEtage.getText().trim().equals(selectedChambre.getNumEtage())){
				selectedChambre.setNumEtage(componentNumEtage.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOChambre.getSQLUpdateForNumEtageByPreparedStatement(selectedChambre));
			}
			if (selectedChambre.getIdCategorieChambre() == null || !(componentCategorieChambre.getSelectedItem() instanceof models.beans.CategorieChambre) || ((models.beans.CategorieChambre)componentCategorieChambre.getSelectedItem()).getId().intValue() != selectedChambre.getIdCategorieChambre().intValue()){
				selectedChambre.setIdCategorieChambre(((models.beans.CategorieChambre)componentCategorieChambre.getSelectedItem()).getId());
				listQueries.addInsertUpdateSQLQueries(DAOChambre.getSQLUpdateForIdCategorieChambreByPreparedStatement(selectedChambre));
			}
			if (! componentPrix.getText().trim().equals(String.valueOf(selectedChambre.getPrix()))){
				selectedChambre.setPrix(Double.parseDouble(componentPrix.getText().trim()));
				listQueries.addInsertUpdateSQLQueries(DAOChambre.getSQLUpdateForPrixByPreparedStatement(selectedChambre));
			}
			
			communication.SocketCommunicator.getInstance().sendQuery(listQueries);
			notifierConfirmation("Chambre modifié avec succès");
		}
		
		dlmListItems.set(dlmListItems.indexOf(selectedItem), selectedChambre);
	}

	protected void listItemsMouseClicked(MouseEvent evt) {
		selectedItem = listItems.getSelectedValue();
		if (selectedItem == null){
			fillFormulaireByChambre(null);
			return;
		}

		fillFormulaireByChambre((Chambre)selectedItem);
	}

	protected void miDeleteAllActionPerformed(ActionEvent evt) {
		if (GUIMessageByOptionPane.showQuestionMessage("Supprimer Tous ?", "Voulez-vous vraiment supprimer tous les  ?")){
			DAOChambre.deleteAll();
			fillFormulaireByChambre(null);
		}
	}

	private void exportToExcelFile(File file){
		try{
			views.ViewChambre.exportToExcel(file);
		}
		catch (Exception e){
			GUIMessageByOptionPane.showErrorMessage("Erreur de génération d'Excel", "Errueur de génération de l'Excel");
			StringUtils.printDebug(e);
		}
	}

	private void importFromExcelFile(File file){
		views.ViewChambre.importFromExcel(file);
	}

	protected void emptyFields() {
		this.componentNumChamre.setText("");
		this.componentNumChamre.setEnabled(false);
		this.labelNumChamre.setEnabled(false);

		this.componentNumEtage.setText("");
		this.componentNumEtage.setEnabled(false);
		this.labelNumEtage.setEnabled(false);

		this.componentCategorieChambre.setSelectedIndex(0);
		this.componentCategorieChambre.setEnabled(false);
		this.labelCategorieChambre.setEnabled(false);

		this.componentPrix.setText("");
		this.componentPrix.setEnabled(false);
		this.labelPrix.setEnabled(false);

	}

	protected void initFields() {
		this.pNotification.setVisible(JInternalFrameManagementModel.isVisibilityNotificationBar());

		selectedItem = null;

		setPhoto(null, false);

		if (guiNavigator.isVisible()){
			guiNavigator.updateNumberItemsInPage();
			int totalChambres = DAOChambre.getCountInTable();
			int nbItemsInPage = guiNavigator.getNumberItemsInPage();
			int numberOfPage = totalChambres / nbItemsInPage;
			if (totalChambres % nbItemsInPage > 0)
				numberOfPage++;
			guiNavigator.setTotalNumberOfItems(totalChambres);
			guiNavigator.setNumberOfPages(numberOfPage);
			guiNavigator.setNumeroPage(1);
		}

		updateListChambres(true);

		
		componentCategorieChambre.removeAllItems();
		List<models.beans.CategorieChambre> listCategorieChambres = models.daos.client.DAOCategorieChambre.getListInstances();
		for (models.beans.CategorieChambre instance : listCategorieChambres){
			componentCategorieChambre.addItem(instance);
		}
		componentCategorieChambre.insertItemAt("Choisir une categorie ", 0);
		
		fillFormulaireByChambre(null);
	}

	private synchronized void updateListChambres(){
		updateListChambres(false);
	}

	private synchronized void updateListChambres(boolean filtering){
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
			condition = "WHERE numChamre like '"+filterText+"%'  ";/*filtre par num chambre*/
		}
		
		int totalChambres = DAOChambre.getCountInTable(condition);
		condition += orderByLimit;
		
		if (guiNavigator.isVisible()){
			if (filtering){
				guiNavigator.updateNumberItemsInPage();
				int nbItemsInPage = guiNavigator.getNumberItemsInPage();
				int numberOfPage = totalChambres / nbItemsInPage;
				if (totalChambres % nbItemsInPage > 0)
					numberOfPage++;
				guiNavigator.setTotalNumberOfItems(totalChambres);
				guiNavigator.setNumberOfPages(numberOfPage);
				guiNavigator.setNumeroPage(1);
			}
			if (guiNavigator.getFirst() < 0)
				return;
		}
		
		dlmListItems.clear();
		List<Chambre> listChambres = DAOChambre.getListInstances(condition);
		for (Chambre chambre : listChambres){
			dlmListItems.addElement(chambre);
		}
		
		updateNbreItems(listChambres.size(), "", totalChambres);
	}

	protected void fillFormulaireByChambre(Chambre chambre) {
//		pFormulaire.setVisible(chambre != null);
		if (chambre == null){
			emptyFields();
			return;
		}

		this.componentNumChamre.setText(String.valueOf(chambre.getNumChamre()));
		this.componentNumChamre.setEnabled(true);
		this.labelNumChamre.setEnabled(true);

		this.componentNumEtage.setText(String.valueOf(chambre.getNumEtage()));
		this.componentNumEtage.setEnabled(true);
		this.labelNumEtage.setEnabled(true);

		this.componentCategorieChambre.setSelectedIndex(0);
		for (int index=1; index<this.componentCategorieChambre.getItemCount(); index++){
			models.beans.CategorieChambre categoriechambre = (models.beans.CategorieChambre)this.componentCategorieChambre.getItemAt(index);
			if (categoriechambre.getId().intValue() == chambre.getIdCategorieChambre().intValue()){
				this.componentCategorieChambre.setSelectedIndex(index);
				break;
			}
		}
		this.labelCategorieChambre.setEnabled(true);
		this.componentCategorieChambre.setEnabled(true);
		this.componentPrix.setText(String.valueOf(chambre.getPrix()));
		this.componentPrix.setEnabled(true);
		this.labelPrix.setEnabled(true);

		
		listItems.setSelectedIndex(dlmListItems.indexOf(chambre));
	}

	private void generateAndShowPDF(){
		try{
			File file = File.createTempFile( "ListeChambres", ".pdf");
			file.deleteOnExit();
			
			views.ViewChambre.exportToPDF(file);
			
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

		getInstance("Gestion des Chambres");
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
		javax.swing.JFrame frame = new javax.swing.JFrame("Test of access to Table : chambre");
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