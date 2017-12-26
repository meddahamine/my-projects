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

import models.beans.Translation;
import models.daos.client.DAOTranslation;

import gui.utils.GUIMessageByOptionPane;
import gui.utils.JInternalFrameManagementModel;

public class TranslationManagement extends JInternalFrameManagementModel{
	private static final long serialVersionUID = 1L;

	private static TranslationManagement instance = null;

	private JLabel labelLang;
	private JLabel labelMessage;
	private JLabel labelTraduction;

	private javax.swing.JComboBox componentLang;
	private javax.swing.JComboBox componentMessage;
	private javax.swing.JTextField componentTraduction;

	public static TranslationManagement getInstance(String title){
		if (instance == null)
			instance = new TranslationManagement(title);
		instance.initFields();
		return instance;
	}

	public static TranslationManagement getInstanceWithoutCreation(){
		return instance;
	}

	public TranslationManagement(String title) {
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
		
		labelLang = new JLabel(views.ViewTranslation.getCaptionForLang()+" : ");
		labelLang.setVerticalAlignment(JLabel.CENTER);
		
		labelMessage = new JLabel(views.ViewTranslation.getCaptionForMessage()+" : ");
		labelMessage.setVerticalAlignment(JLabel.CENTER);
		
		labelTraduction = new JLabel(views.ViewTranslation.getCaptionForTraduction()+" : ");
		labelTraduction.setVerticalAlignment(JLabel.CENTER);
		

		componentLang = views.ViewTranslation.getViewForLang();
		componentMessage = views.ViewTranslation.getViewForMessage();
		componentTraduction = views.ViewTranslation.getViewForTraduction();

//		stpListContent.setDividerLocation(300);
		
		bAjouter.setToolTipText("Ajouter un Traduction des textes");
		bSupprimer.setToolTipText("Supprimer le Traduction des textes sélectionné");
		bExporter.setToolTipText("Exporter la liste des translations vers un fichier Excel");
		bImporter.setToolTipText("Importer des translations à partir d'un fichier Excel");
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
				.addComponent(labelLang)
				.addComponent(labelMessage)
				.addComponent(labelTraduction)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layoutPFormulaire.createParallelGroup()
				.addComponent(componentLang, 300, 300, 300)
				.addComponent(componentMessage, 300, 300, 300)
				.addComponent(componentTraduction, 300, 300, 300)
			)
			.addGap(1, 1, Short.MAX_VALUE)
		);
		layoutPFormulaire.setVerticalGroup(layoutPFormulaire.createSequentialGroup()
				.addGap(10, 10, 10)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelLang, 25, 25, 25)
					.addComponent(componentLang, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelMessage, 25, 25, 25)
					.addComponent(componentMessage, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelTraduction, 25, 25, 25)
					.addComponent(componentTraduction, 25, 25, 25)
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
					updateListTranslations();
				}
			});

			guiNavigator.setCBNumberOfItemsActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt){
					guiNavigator.updateNumberItemsInPage();
					int totalTranslations = guiNavigator.getTotalNumberOfItems();
					int nbItemsInPage = guiNavigator.getNumberItemsInPage();
					int numberOfPage = totalTranslations / nbItemsInPage;
					if (totalTranslations % nbItemsInPage > 0)
						numberOfPage++;
					guiNavigator.setNumberOfPages(numberOfPage);
					guiNavigator.setNumeroPage(guiNavigator.getNumPageAsInt());
					
					updateListTranslations(true);
				}
			});
		}
	}

	protected void tfFilterDocumentUpdated(DocumentEvent evt) {
		updateListTranslations(true);
	}

	protected void bAjouterActionPerformed(ActionEvent evt) {
		if (selectedItem != null){
			if ((((Translation)selectedItem)).getId() == null || (((Translation)selectedItem)).getId() == 0){
				notifierErreur("Veuillez valider le translation que vous venez d'ajouter ...");
				return;
			}
		}

		Translation translation = new Translation();

		selectedItem = translation;
		dlmListItems.addElement(translation);
		listItems.setSelectedValue(selectedItem, true);
		fillFormulaireByTranslation(translation);
	}

	protected void bFermerActionPerformed(ActionEvent evt) {
		if (dlgLayerFilter.isVisible()){
			dlgLayerFilter.setVisible(false);
			return;
		}

		if (selectedItem != null){
			if ((((Translation)selectedItem)).getId() == null || (((Translation)selectedItem)).getId() == 0){
				if (GUIMessageByOptionPane.showQuestionMessage("Un translation non ajouté", "Voulez-vous quitter sans valider cet translation ?"))
					fermer();
				else
					return;
			}
		}
		fermer();
	}

	protected void bExporterActionPerformed(ActionEvent evt) {
		fcExportImportXLS.setDialogType(JFileChooser.SAVE_DIALOG | JFileChooser.FILES_ONLY);
		fcExportImportXLS.setDialogTitle("Exporter la liste des translations sous format Excel 2003 (*.xls)");
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
		fcExportImportXLS.setDialogTitle("Importer des translations à partir d'un fichier Excel format 2003 (*.xls)");
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
		if (selectedItem == null || !(selectedItem instanceof Translation)){
			notifierErreur("Veuillez sélectionner un translation ...");
			return;
		}

		if (! GUIMessageByOptionPane.showQuestionMessage("Supprimer un translation", "Êtes-vous sûr de la suppression de cet translation ?")){
			return;
		}

		int index = listItems.getSelectedIndex();
		
		Translation translation = (Translation)selectedItem;
		if (translation.getId() != null || translation.getId() > 0){
			DAOTranslation.delete(translation);
		}

		dlmListItems.remove(index);
		
		selectedItem = null;
		fillFormulaireByTranslation(null);
	}

	protected void bValiderActionPerformed(ActionEvent evt) {
		if (selectedItem == null || !(selectedItem instanceof Translation)){
			notifierErreur("Veuillez sélectionner un Translation ...");
			return;
		}

		if (componentTraduction.getText().trim().equals("")){
			notifierErreur("Veuillez remplir tous les champs, SVP");
			return;
		}

		Translation selectedTranslation = (Translation) selectedItem;

		if (selectedTranslation.getId() == null || selectedTranslation.getId() == 0){
			selectedTranslation.setId(0);

			selectedTranslation.setIdLang((componentLang.getSelectedItem() instanceof models.beans.Lang) ? ((models.beans.Lang)componentLang.getSelectedItem()).getId() : 0);
			selectedTranslation.setIdMessage((componentMessage.getSelectedItem() instanceof models.beans.LangMessage) ? ((models.beans.LangMessage)componentMessage.getSelectedItem()).getId() : 0);
			selectedTranslation.setTraduction(componentTraduction.getText().trim());
			
			selectedTranslation.setId((Integer) communication.SocketCommunicator.getInstance().sendQuery(selectedTranslation));
			notifierConfirmation("Traduction des textes sauvegardé avec succès");
		}else{
			utils.SGBDConfig.InsertUpdateSQLQueries listQueries = new utils.SGBDConfig.InsertUpdateSQLQueries((utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement[])null);
			if (selectedTranslation.getIdLang() == null || !(componentLang.getSelectedItem() instanceof models.beans.Lang) || ((models.beans.Lang)componentLang.getSelectedItem()).getId().intValue() != selectedTranslation.getIdLang().intValue()){
				selectedTranslation.setIdLang(((models.beans.Lang)componentLang.getSelectedItem()).getId());
				listQueries.addInsertUpdateSQLQueries(DAOTranslation.getSQLUpdateForIdLangByPreparedStatement(selectedTranslation));
			}
			if (selectedTranslation.getIdMessage() == null || !(componentMessage.getSelectedItem() instanceof models.beans.LangMessage) || ((models.beans.LangMessage)componentMessage.getSelectedItem()).getId().intValue() != selectedTranslation.getIdMessage().intValue()){
				selectedTranslation.setIdMessage(((models.beans.LangMessage)componentMessage.getSelectedItem()).getId());
				listQueries.addInsertUpdateSQLQueries(DAOTranslation.getSQLUpdateForIdMessageByPreparedStatement(selectedTranslation));
			}
			if (! componentTraduction.getText().trim().equals(selectedTranslation.getTraduction())){
				selectedTranslation.setTraduction(componentTraduction.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOTranslation.getSQLUpdateForTraductionByPreparedStatement(selectedTranslation));
			}
			
			communication.SocketCommunicator.getInstance().sendQuery(listQueries);
			notifierConfirmation("Translation modifié avec succès");
		}
		
		dlmListItems.set(dlmListItems.indexOf(selectedItem), selectedTranslation);
	}

	protected void listItemsMouseClicked(MouseEvent evt) {
		selectedItem = listItems.getSelectedValue();
		if (selectedItem == null){
			fillFormulaireByTranslation(null);
			return;
		}

		fillFormulaireByTranslation((Translation)selectedItem);
	}

	protected void miDeleteAllActionPerformed(ActionEvent evt) {
		if (GUIMessageByOptionPane.showQuestionMessage("Supprimer Tous ?", "Voulez-vous vraiment supprimer tous les Traduction des textes ?")){
			DAOTranslation.deleteAll();
			fillFormulaireByTranslation(null);
		}
	}

	private void exportToExcelFile(File file){
		try{
			views.ViewTranslation.exportToExcel(file);
		}
		catch (Exception e){
			GUIMessageByOptionPane.showErrorMessage("Erreur de génération d'Excel", "Errueur de génération de l'Excel");
			StringUtils.printDebug(e);
		}
	}

	private void importFromExcelFile(File file){
		views.ViewTranslation.importFromExcel(file);
	}

	protected void emptyFields() {
		this.componentLang.setSelectedIndex(0);
		this.componentLang.setEnabled(false);
		this.labelLang.setEnabled(false);

		this.componentMessage.setSelectedIndex(0);
		this.componentMessage.setEnabled(false);
		this.labelMessage.setEnabled(false);

		this.componentTraduction.setText("");
		this.componentTraduction.setEnabled(false);
		this.labelTraduction.setEnabled(false);

	}

	protected void initFields() {
		this.pNotification.setVisible(JInternalFrameManagementModel.isVisibilityNotificationBar());

		selectedItem = null;

		setPhoto(null, false);

		if (guiNavigator.isVisible()){
			guiNavigator.updateNumberItemsInPage();
			int totalTranslations = DAOTranslation.getCountInTable();
			int nbItemsInPage = guiNavigator.getNumberItemsInPage();
			int numberOfPage = totalTranslations / nbItemsInPage;
			if (totalTranslations % nbItemsInPage > 0)
				numberOfPage++;
			guiNavigator.setTotalNumberOfItems(totalTranslations);
			guiNavigator.setNumberOfPages(numberOfPage);
			guiNavigator.setNumeroPage(1);
		}

		updateListTranslations(true);

		
		componentLang.removeAllItems();
		List<models.beans.Lang> listLangs = models.daos.client.DAOLang.getListInstances();
		for (models.beans.Lang instance : listLangs){
			componentLang.addItem(instance);
		}
		componentLang.insertItemAt("Choisir un Langues de Translation", 0);
		
		componentMessage.removeAllItems();
		List<models.beans.LangMessage> listMessages = models.daos.client.DAOLangMessage.getListInstances();
		for (models.beans.LangMessage instance : listMessages){
			componentMessage.addItem(instance);
		}
		componentMessage.insertItemAt("Choisir un Text", 0);
		
		fillFormulaireByTranslation(null);
	}

	private synchronized void updateListTranslations(){
		updateListTranslations(false);
	}

	private synchronized void updateListTranslations(boolean filtering){
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
				int totalTranslations = DAOTranslation.getCountInTable(condition);
				int nbItemsInPage = guiNavigator.getNumberItemsInPage();
				int numberOfPage = totalTranslations / nbItemsInPage;
				if (totalTranslations % nbItemsInPage > 0)
					numberOfPage++;
				guiNavigator.setTotalNumberOfItems(totalTranslations);
				guiNavigator.setNumberOfPages(numberOfPage);
				guiNavigator.setNumeroPage(1);
			}
			if (guiNavigator.getFirst() < 0)
				return;
		}
		
		dlmListItems.clear();
		List<Translation> listTranslations = DAOTranslation.getListInstances(condition);
		for (Translation translation : listTranslations){
			dlmListItems.addElement(translation);
		}
	}

	protected void fillFormulaireByTranslation(Translation translation) {
//		pFormulaire.setVisible(translation != null);
		if (translation == null){
			emptyFields();
			return;
		}

		this.componentLang.setSelectedIndex(0);
		for (int index=1; index<this.componentLang.getItemCount(); index++){
			models.beans.Lang lang = (models.beans.Lang)this.componentLang.getItemAt(index);
			if (lang.getId().intValue() == translation.getIdLang().intValue()){
				this.componentLang.setSelectedIndex(index);
				break;
			}
		}
		this.labelLang.setEnabled(true);
		this.componentLang.setEnabled(true);
		this.componentMessage.setSelectedIndex(0);
		for (int index=1; index<this.componentMessage.getItemCount(); index++){
			models.beans.LangMessage message = (models.beans.LangMessage)this.componentMessage.getItemAt(index);
			if (message.getId().intValue() == translation.getIdMessage().intValue()){
				this.componentMessage.setSelectedIndex(index);
				break;
			}
		}
		this.labelMessage.setEnabled(true);
		this.componentMessage.setEnabled(true);
		this.componentTraduction.setText(String.valueOf(translation.getTraduction()));
		this.componentTraduction.setEnabled(true);
		this.labelTraduction.setEnabled(true);

		
		listItems.setSelectedIndex(dlmListItems.indexOf(translation));
	}

	private void generateAndShowPDF(){
		try{
			File file = File.createTempFile( "ListeTranslations", ".pdf");
			file.deleteOnExit();
			
			views.ViewTranslation.exportToPDF(file);
			
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

		getInstance("Gestion des Traduction des textes ...");
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
		javax.swing.JFrame frame = new javax.swing.JFrame("Test of access to Table : translation");
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