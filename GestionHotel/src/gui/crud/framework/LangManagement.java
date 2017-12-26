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

import models.beans.Lang;
import models.daos.client.DAOLang;

import gui.utils.GUIMessageByOptionPane;
import gui.utils.JInternalFrameManagementModel;

public class LangManagement extends JInternalFrameManagementModel{
	private static final long serialVersionUID = 1L;

	private static LangManagement instance = null;

	private JLabel labelLangue;
	private JLabel labelCodeLang;
	private JLabel labelOrientation;

	private javax.swing.JTextField componentLangue;
	private javax.swing.JTextField componentCodeLang;
	private javax.swing.JComboBox componentOrientation;

	public static LangManagement getInstance(String title){
		if (instance == null)
			instance = new LangManagement(title);
		instance.initFields();
		return instance;
	}

	public static LangManagement getInstanceWithoutCreation(){
		return instance;
	}

	public LangManagement(String title) {
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
		
		labelLangue = new JLabel(views.ViewLang.getCaptionForLangue()+" : ");
		labelLangue.setVerticalAlignment(JLabel.CENTER);
		
		labelCodeLang = new JLabel(views.ViewLang.getCaptionForCodeLang()+" : ");
		labelCodeLang.setVerticalAlignment(JLabel.CENTER);
		
		labelOrientation = new JLabel(views.ViewLang.getCaptionForOrientation()+" : ");
		labelOrientation.setVerticalAlignment(JLabel.CENTER);
		

		componentLangue = views.ViewLang.getViewForLangue();
		componentCodeLang = views.ViewLang.getViewForCodeLang();
		componentOrientation = (javax.swing.JComboBox)views.ViewLang.getViewForOrientation("JCOMBOBOXE");

//		stpListContent.setDividerLocation(300);
		
		bAjouter.setToolTipText("Ajouter un Langues");
		bSupprimer.setToolTipText("Supprimer le Langues sélectionné");
		bExporter.setToolTipText("Exporter la liste des langs vers un fichier Excel");
		bImporter.setToolTipText("Importer des langs à partir d'un fichier Excel");
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
				.addComponent(labelLangue)
				.addComponent(labelCodeLang)
				.addComponent(labelOrientation)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layoutPFormulaire.createParallelGroup()
				.addComponent(componentLangue, 300, 300, 300)
				.addComponent(componentCodeLang, 300, 300, 300)
				.addComponent(componentOrientation, 300, 300, 300)
			)
			.addGap(1, 1, Short.MAX_VALUE)
		);
		layoutPFormulaire.setVerticalGroup(layoutPFormulaire.createSequentialGroup()
				.addGap(10, 10, 10)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelLangue, 25, 25, 25)
					.addComponent(componentLangue, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelCodeLang, 25, 25, 25)
					.addComponent(componentCodeLang, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelOrientation, 25, 25, 25)
					.addComponent(componentOrientation, 25, 25, 25)
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
					updateListLangs();
				}
			});

			guiNavigator.setCBNumberOfItemsActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt){
					guiNavigator.updateNumberItemsInPage();
					int totalLangs = guiNavigator.getTotalNumberOfItems();
					int nbItemsInPage = guiNavigator.getNumberItemsInPage();
					int numberOfPage = totalLangs / nbItemsInPage;
					if (totalLangs % nbItemsInPage > 0)
						numberOfPage++;
					guiNavigator.setNumberOfPages(numberOfPage);
					guiNavigator.setNumeroPage(guiNavigator.getNumPageAsInt());
					
					updateListLangs(true);
				}
			});
		}
	}

	protected void tfFilterDocumentUpdated(DocumentEvent evt) {
		updateListLangs(true);
	}

	protected void bAjouterActionPerformed(ActionEvent evt) {
		if (selectedItem != null){
			if ((((Lang)selectedItem)).getId() == null || (((Lang)selectedItem)).getId() == 0){
				notifierErreur("Veuillez valider le lang que vous venez d'ajouter ...");
				return;
			}
		}

		Lang lang = new Lang();

		selectedItem = lang;
		dlmListItems.addElement(lang);
		listItems.setSelectedValue(selectedItem, true);
		fillFormulaireByLang(lang);
	}

	protected void bFermerActionPerformed(ActionEvent evt) {
		if (dlgLayerFilter.isVisible()){
			dlgLayerFilter.setVisible(false);
			return;
		}

		if (selectedItem != null){
			if ((((Lang)selectedItem)).getId() == null || (((Lang)selectedItem)).getId() == 0){
				if (GUIMessageByOptionPane.showQuestionMessage("Un lang non ajouté", "Voulez-vous quitter sans valider cet lang ?"))
					fermer();
				else
					return;
			}
		}
		fermer();
	}

	protected void bExporterActionPerformed(ActionEvent evt) {
		fcExportImportXLS.setDialogType(JFileChooser.SAVE_DIALOG | JFileChooser.FILES_ONLY);
		fcExportImportXLS.setDialogTitle("Exporter la liste des langs sous format Excel 2003 (*.xls)");
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
		fcExportImportXLS.setDialogTitle("Importer des langs à partir d'un fichier Excel format 2003 (*.xls)");
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
		if (selectedItem == null || !(selectedItem instanceof Lang)){
			notifierErreur("Veuillez sélectionner un lang ...");
			return;
		}

		if (! GUIMessageByOptionPane.showQuestionMessage("Supprimer un lang", "Êtes-vous sûr de la suppression de cet lang ?")){
			return;
		}

		int index = listItems.getSelectedIndex();
		
		Lang lang = (Lang)selectedItem;
		if (lang.getId() != null || lang.getId() > 0){
			DAOLang.delete(lang);
		}

		dlmListItems.remove(index);
		
		selectedItem = null;
		fillFormulaireByLang(null);
	}

	protected void bValiderActionPerformed(ActionEvent evt) {
		if (selectedItem == null || !(selectedItem instanceof Lang)){
			notifierErreur("Veuillez sélectionner un Lang ...");
			return;
		}

		if (componentLangue.getText().trim().equals("") || componentCodeLang.getText().trim().equals("")){
			notifierErreur("Veuillez remplir tous les champs, SVP");
			return;
		}

		Lang selectedLang = (Lang) selectedItem;
		
		Lang oldLang = DAOLang.getLangByCodeLang(componentCodeLang.getText().trim());
		if (oldLang != null){
			if (selectedLang.getId().intValue() != oldLang.getId().intValue()){
				notifierErreur("Il y a un 'Langues' ayant déjà le même 'Code de la langue', Veuillez donner une autre valeur au champs 'Code de la langue'");
				return;
			}
		}

		if (selectedLang.getId() == null || selectedLang.getId() == 0){
			selectedLang.setId(0);

			selectedLang.setLangue(componentLangue.getText().trim());
			selectedLang.setCodeLang(componentCodeLang.getText().trim());
			selectedLang.setOrientation(Lang.Orientation.getByValue(componentOrientation.getSelectedItem().toString()));
			
			selectedLang.setId((Integer) communication.SocketCommunicator.getInstance().sendQuery(selectedLang));
			notifierConfirmation("Langues sauvegardé avec succès");
		}else{
			utils.SGBDConfig.InsertUpdateSQLQueries listQueries = new utils.SGBDConfig.InsertUpdateSQLQueries((utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement[])null);
			if (! componentLangue.getText().trim().equals(selectedLang.getLangue())){
				selectedLang.setLangue(componentLangue.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOLang.getSQLUpdateForLangueByPreparedStatement(selectedLang));
			}
			if (! componentCodeLang.getText().trim().equals(selectedLang.getCodeLang())){
				selectedLang.setCodeLang(componentCodeLang.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOLang.getSQLUpdateForCodeLangByPreparedStatement(selectedLang));
			}
			if (selectedLang.getOrientation() == null || ! componentOrientation.getSelectedItem().toString().equals(selectedLang.getOrientation().getValue())){
				selectedLang.setOrientation(Lang.Orientation.getByValue(componentOrientation.getSelectedItem().toString()));
				listQueries.addInsertUpdateSQLQueries(DAOLang.getSQLUpdateForOrientationByPreparedStatement(selectedLang));
			}
			
			communication.SocketCommunicator.getInstance().sendQuery(listQueries);
			notifierConfirmation("Lang modifié avec succès");
		}
		
		dlmListItems.set(dlmListItems.indexOf(selectedItem), selectedLang);
	}

	protected void listItemsMouseClicked(MouseEvent evt) {
		selectedItem = listItems.getSelectedValue();
		if (selectedItem == null){
			fillFormulaireByLang(null);
			return;
		}

		fillFormulaireByLang((Lang)selectedItem);
	}

	protected void miDeleteAllActionPerformed(ActionEvent evt) {
		if (GUIMessageByOptionPane.showQuestionMessage("Supprimer Tous ?", "Voulez-vous vraiment supprimer tous les Langues ?")){
			DAOLang.deleteAll();
			fillFormulaireByLang(null);
		}
	}

	private void exportToExcelFile(File file){
		try{
			views.ViewLang.exportToExcel(file);
		}
		catch (Exception e){
			GUIMessageByOptionPane.showErrorMessage("Erreur de génération d'Excel", "Errueur de génération de l'Excel");
			StringUtils.printDebug(e);
		}
	}

	private void importFromExcelFile(File file){
		views.ViewLang.importFromExcel(file);
	}

	protected void emptyFields() {
		this.componentLangue.setText("");
		this.componentLangue.setEnabled(false);
		this.labelLangue.setEnabled(false);

		this.componentCodeLang.setText("");
		this.componentCodeLang.setEnabled(false);
		this.labelCodeLang.setEnabled(false);

		this.componentOrientation.setSelectedIndex(0);
		this.componentOrientation.setEnabled(false);
		this.labelOrientation.setEnabled(false);

	}

	protected void initFields() {
		this.pNotification.setVisible(JInternalFrameManagementModel.isVisibilityNotificationBar());

		selectedItem = null;

		setPhoto(null, false);

		if (guiNavigator.isVisible()){
			guiNavigator.updateNumberItemsInPage();
			int totalLangs = DAOLang.getCountInTable();
			int nbItemsInPage = guiNavigator.getNumberItemsInPage();
			int numberOfPage = totalLangs / nbItemsInPage;
			if (totalLangs % nbItemsInPage > 0)
				numberOfPage++;
			guiNavigator.setTotalNumberOfItems(totalLangs);
			guiNavigator.setNumberOfPages(numberOfPage);
			guiNavigator.setNumeroPage(1);
		}

		updateListLangs(true);

		
		fillFormulaireByLang(null);
	}

	private synchronized void updateListLangs(){
		updateListLangs(false);
	}

	private synchronized void updateListLangs(boolean filtering){
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
				int totalLangs = DAOLang.getCountInTable(condition);
				int nbItemsInPage = guiNavigator.getNumberItemsInPage();
				int numberOfPage = totalLangs / nbItemsInPage;
				if (totalLangs % nbItemsInPage > 0)
					numberOfPage++;
				guiNavigator.setTotalNumberOfItems(totalLangs);
				guiNavigator.setNumberOfPages(numberOfPage);
				guiNavigator.setNumeroPage(1);
			}
			if (guiNavigator.getFirst() < 0)
				return;
		}
		
		dlmListItems.clear();
		List<Lang> listLangs = DAOLang.getListInstances(condition);
		for (Lang lang : listLangs){
			dlmListItems.addElement(lang);
		}
	}

	protected void fillFormulaireByLang(Lang lang) {
//		pFormulaire.setVisible(lang != null);
		if (lang == null){
			emptyFields();
			return;
		}

		this.componentLangue.setText(String.valueOf(lang.getLangue()));
		this.componentLangue.setEnabled(true);
		this.labelLangue.setEnabled(true);

		this.componentCodeLang.setText(String.valueOf(lang.getCodeLang()));
		this.componentCodeLang.setEnabled(true);
		this.labelCodeLang.setEnabled(true);

		if (lang.getOrientation() != null)
			this.componentOrientation.setSelectedItem(lang.getOrientation().getValue());
		else
			this.componentOrientation.setSelectedIndex(0);
		this.componentOrientation.setEnabled(true);
		this.labelOrientation.setEnabled(true);

		
		listItems.setSelectedIndex(dlmListItems.indexOf(lang));
	}

	private void generateAndShowPDF(){
		try{
			File file = File.createTempFile( "ListeLangs", ".pdf");
			file.deleteOnExit();
			
			views.ViewLang.exportToPDF(file);
			
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

		getInstance("Gestion des Langues ...");
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
		javax.swing.JFrame frame = new javax.swing.JFrame("Test of access to Table : lang");
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