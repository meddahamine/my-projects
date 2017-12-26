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

import models.beans.CategorieChambre;
import models.daos.client.DAOCategorieChambre;

import gui.utils.GUIMessageByOptionPane;
import gui.utils.JInternalFrameManagementModel;

public class CategorieChambreManagement extends JInternalFrameManagementModel{
	private static final long serialVersionUID = 1L;

	private static CategorieChambreManagement instance = null;

	private JLabel labelLibelle;

	private javax.swing.JTextField componentLibelle;

	public static CategorieChambreManagement getInstance(String title){
		if (instance == null)
			instance = new CategorieChambreManagement(title);
		instance.initFields();
		return instance;
	}

	public static CategorieChambreManagement getInstanceWithoutCreation(){
		return instance;
	}

	public CategorieChambreManagement(String title) {
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
		
		labelLibelle = new JLabel(views.ViewCategorieChambre.getCaptionForLibelle()+" Libellé de la Catégorie Chambre : ");
		labelLibelle.setVerticalAlignment(JLabel.CENTER);
		

		componentLibelle = views.ViewCategorieChambre.getViewForLibelle();

//		stpListContent.setDividerLocation(300);
		
		bAjouter.setToolTipText("Ajouter une Catégorie ");
		bSupprimer.setToolTipText("Supprimer le  sélectionné");
		bExporter.setToolTipText("Exporter la liste des categorieChambres vers un fichier Excel");
		bImporter.setToolTipText("Importer des categorieChambres Γ  partir d'un fichier Excel");
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
				.addComponent(labelLibelle)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layoutPFormulaire.createParallelGroup()
				.addComponent(componentLibelle, 300, 300, 300)
			)
			.addGap(1, 1, Short.MAX_VALUE)
		);
		layoutPFormulaire.setVerticalGroup(layoutPFormulaire.createSequentialGroup()
				.addGap(10, 10, 10)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelLibelle, 25, 25, 25)
					.addComponent(componentLibelle, 25, 25, 25)
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
					updateListCategorieChambres();
				}
			});

			guiNavigator.setCBNumberOfItemsActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt){
					guiNavigator.updateNumberItemsInPage();
					int totalCategorieChambres = guiNavigator.getTotalNumberOfItems();
					int nbItemsInPage = guiNavigator.getNumberItemsInPage();
					int numberOfPage = totalCategorieChambres / nbItemsInPage;
					if (totalCategorieChambres % nbItemsInPage > 0)
						numberOfPage++;
					guiNavigator.setNumberOfPages(numberOfPage);
					guiNavigator.setNumeroPage(guiNavigator.getNumPageAsInt());
					
					updateListCategorieChambres(true);
				}
			});
		}
	}

	protected void tfFilterDocumentUpdated(DocumentEvent evt) {
		updateListCategorieChambres(true);
	}

	protected void bAjouterActionPerformed(ActionEvent evt) {
		if (selectedItem != null){
			if ((((CategorieChambre)selectedItem)).getId() == null || (((CategorieChambre)selectedItem)).getId() == 0){
				notifierErreur("Veuillez valider le categorieChambre que vous venez d'ajouter ...");
				return;
			}
		}

		CategorieChambre categorieChambre = new CategorieChambre();

		selectedItem = categorieChambre;
		dlmListItems.addElement(categorieChambre);
		listItems.setSelectedValue(selectedItem, true);
		fillFormulaireByCategorieChambre(categorieChambre);
	}

	protected void bFermerActionPerformed(ActionEvent evt) {
		if (dlgLayerFilter.isVisible()){
			dlgLayerFilter.setVisible(false);
			return;
		}

		if (selectedItem != null){
			if ((((CategorieChambre)selectedItem)).getId() == null || (((CategorieChambre)selectedItem)).getId() == 0){
				if (GUIMessageByOptionPane.showQuestionMessage("Un categorieChambre non ajouté", "Voulez-vous quitter sans valider cet categorieChambre ?"))
					fermer();
				else
					return;
			}
		}
		fermer();
	}

	protected void bExporterActionPerformed(ActionEvent evt) {
		fcExportImportXLS.setDialogType(JFileChooser.SAVE_DIALOG | JFileChooser.FILES_ONLY);
		fcExportImportXLS.setDialogTitle("Exporter la liste des categorieChambres sous format Excel 2003 (*.xls)");
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
		fcExportImportXLS.setDialogTitle("Importer des categorieChambres Γ  partir d'un fichier Excel format 2003 (*.xls)");
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
		if (selectedItem == null || !(selectedItem instanceof CategorieChambre)){
			notifierErreur("Veuillez sélectionner un categorieChambre ...");
			return;
		}

		if (! GUIMessageByOptionPane.showQuestionMessage("Supprimer un categorieChambre", "Γtes-vous sΓ»r de la suppression de cet categorieChambre ?")){
			return;
		}

		int index = listItems.getSelectedIndex();
		
		CategorieChambre categorieChambre = (CategorieChambre)selectedItem;
		if (categorieChambre.getId() != null || categorieChambre.getId() > 0){
			DAOCategorieChambre.delete(categorieChambre);
		}

		dlmListItems.remove(index);
		
		selectedItem = null;
		fillFormulaireByCategorieChambre(null);
	}

	protected void bValiderActionPerformed(ActionEvent evt) {
		if (selectedItem == null || !(selectedItem instanceof CategorieChambre)){
			notifierErreur("Veuillez sélectionner un CategorieChambre ...");
			return;
		}

		if (componentLibelle.getText().trim().equals("")){
			notifierErreur("Veuillez remplir tous les champs, SVP");
			return;
		}

		CategorieChambre selectedCategorieChambre = (CategorieChambre) selectedItem;

		if (selectedCategorieChambre.getId() == null || selectedCategorieChambre.getId() == 0){
			selectedCategorieChambre.setId(0);

			selectedCategorieChambre.setLibelle(componentLibelle.getText().trim());
			
			selectedCategorieChambre.setId((Integer) communication.SocketCommunicator.getInstance().sendQuery(selectedCategorieChambre));
			notifierConfirmation(" sauvegardé avec succès");
		}else{
			utils.SGBDConfig.InsertUpdateSQLQueries listQueries = new utils.SGBDConfig.InsertUpdateSQLQueries((utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement[])null);
			if (! componentLibelle.getText().trim().equals(selectedCategorieChambre.getLibelle())){
				selectedCategorieChambre.setLibelle(componentLibelle.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOCategorieChambre.getSQLUpdateForLibelleByPreparedStatement(selectedCategorieChambre));
			}
			
			communication.SocketCommunicator.getInstance().sendQuery(listQueries);
			notifierConfirmation("CategorieChambre modifié avec succès");
		}
		
		dlmListItems.set(dlmListItems.indexOf(selectedItem), selectedCategorieChambre);
	}

	protected void listItemsMouseClicked(MouseEvent evt) {
		selectedItem = listItems.getSelectedValue();
		if (selectedItem == null){
			fillFormulaireByCategorieChambre(null);
			return;
		}

		fillFormulaireByCategorieChambre((CategorieChambre)selectedItem);
	}

	protected void miDeleteAllActionPerformed(ActionEvent evt) {
		if (GUIMessageByOptionPane.showQuestionMessage("Supprimer Tous ?", "Voulez-vous vraiment supprimer tous les  ?")){
			DAOCategorieChambre.deleteAll();
			fillFormulaireByCategorieChambre(null);
		}
	}

	private void exportToExcelFile(File file){
		try{
			views.ViewCategorieChambre.exportToExcel(file);
		}
		catch (Exception e){
			GUIMessageByOptionPane.showErrorMessage("Erreur de génération d'Excel", "Errueur de génération de l'Excel");
			StringUtils.printDebug(e);
		}
	}

	private void importFromExcelFile(File file){
		views.ViewCategorieChambre.importFromExcel(file);
	}

	protected void emptyFields() {
		this.componentLibelle.setText("");
		this.componentLibelle.setEnabled(false);
		this.labelLibelle.setEnabled(false);

	}

	protected void initFields() {
		this.pNotification.setVisible(JInternalFrameManagementModel.isVisibilityNotificationBar());

		selectedItem = null;

		setPhoto(null, false);

		if (guiNavigator.isVisible()){
			guiNavigator.updateNumberItemsInPage();
			int totalCategorieChambres = DAOCategorieChambre.getCountInTable();
			int nbItemsInPage = guiNavigator.getNumberItemsInPage();
			int numberOfPage = totalCategorieChambres / nbItemsInPage;
			if (totalCategorieChambres % nbItemsInPage > 0)
				numberOfPage++;
			guiNavigator.setTotalNumberOfItems(totalCategorieChambres);
			guiNavigator.setNumberOfPages(numberOfPage);
			guiNavigator.setNumeroPage(1);
		}

		updateListCategorieChambres(true);

		
		fillFormulaireByCategorieChambre(null);
	}

	private synchronized void updateListCategorieChambres(){
		updateListCategorieChambres(false);
	}

	private synchronized void updateListCategorieChambres(boolean filtering){
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
			condition = "WHERE libelle like '"+filterText+"%' ";
		}
		
		int totalCategorieChambres = DAOCategorieChambre.getCountInTable(condition);
		condition += orderByLimit;
		
		if (guiNavigator.isVisible()){
			if (filtering){
				guiNavigator.updateNumberItemsInPage();
				int nbItemsInPage = guiNavigator.getNumberItemsInPage();
				int numberOfPage = totalCategorieChambres / nbItemsInPage;
				if (totalCategorieChambres % nbItemsInPage > 0)
					numberOfPage++;
				guiNavigator.setTotalNumberOfItems(totalCategorieChambres);
				guiNavigator.setNumberOfPages(numberOfPage);
				guiNavigator.setNumeroPage(1);
			}
			if (guiNavigator.getFirst() < 0)
				return;
		}
		
		dlmListItems.clear();
		List<CategorieChambre> listCategorieChambres = DAOCategorieChambre.getListInstances(condition);
		for (CategorieChambre categorieChambre : listCategorieChambres){
			dlmListItems.addElement(categorieChambre);
		}
		
		updateNbreItems(listCategorieChambres.size(), "", totalCategorieChambres);
	}

	protected void fillFormulaireByCategorieChambre(CategorieChambre categorieChambre) {
//		pFormulaire.setVisible(categorieChambre != null);
		if (categorieChambre == null){
			emptyFields();
			return;
		}

		this.componentLibelle.setText(String.valueOf(categorieChambre.getLibelle()));
		this.componentLibelle.setEnabled(true);
		this.labelLibelle.setEnabled(true);

		
		listItems.setSelectedIndex(dlmListItems.indexOf(categorieChambre));
	}

	private void generateAndShowPDF(){
		try{
			File file = File.createTempFile( "ListeCategorieChambres", ".pdf");
			file.deleteOnExit();
			
			views.ViewCategorieChambre.exportToPDF(file);
			
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

		getInstance("Gestion des Catégories");
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
		javax.swing.JFrame frame = new javax.swing.JFrame("Test of access to Table : categorieChambre");
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