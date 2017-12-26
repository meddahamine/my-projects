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

import models.beans.ParametresApplicationUtilisateur;
import models.daos.client.DAOParametresApplicationUtilisateur;

import gui.utils.GUIMessageByOptionPane;
import gui.utils.JInternalFrameManagementModel;

public class ParametresApplicationUtilisateurManagement extends JInternalFrameManagementModel{
	private static final long serialVersionUID = 1L;

	private static ParametresApplicationUtilisateurManagement instance = null;

	private JLabel labelPeriodeNotification;
	private JLabel labelVisibilityOfNotification;
	private JLabel labelVisibilityOfMainToolBar;
	private JLabel labelLang;
	private JLabel labelLookAndFeel;

	private javax.swing.JTextField componentPeriodeNotification;
	private javax.swing.JCheckBox componentVisibilityOfNotification;
	private javax.swing.JCheckBox componentVisibilityOfMainToolBar;
	private javax.swing.JComboBox componentLang;
	private javax.swing.JTextField componentLookAndFeel;

	public static ParametresApplicationUtilisateurManagement getInstance(String title){
		if (instance == null)
			instance = new ParametresApplicationUtilisateurManagement(title);
		instance.initFields();
		return instance;
	}

	public static ParametresApplicationUtilisateurManagement getInstanceWithoutCreation(){
		return instance;
	}

	public ParametresApplicationUtilisateurManagement(String title) {
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
		
		labelPeriodeNotification = new JLabel(views.ViewParametresApplicationUtilisateur.getCaptionForPeriodeNotification()+" : ");
		labelPeriodeNotification.setVerticalAlignment(JLabel.CENTER);
		
		labelVisibilityOfNotification = new JLabel(views.ViewParametresApplicationUtilisateur.getCaptionForVisibilityOfNotification()+" : ");
		labelVisibilityOfNotification.setVerticalAlignment(JLabel.CENTER);
		
		labelVisibilityOfMainToolBar = new JLabel(views.ViewParametresApplicationUtilisateur.getCaptionForVisibilityOfMainToolBar()+" : ");
		labelVisibilityOfMainToolBar.setVerticalAlignment(JLabel.CENTER);
		
		labelLang = new JLabel(views.ViewParametresApplicationUtilisateur.getCaptionForLang()+" : ");
		labelLang.setVerticalAlignment(JLabel.CENTER);
		
		labelLookAndFeel = new JLabel(views.ViewParametresApplicationUtilisateur.getCaptionForLookAndFeel()+" : ");
		labelLookAndFeel.setVerticalAlignment(JLabel.CENTER);
		

		componentPeriodeNotification = views.ViewParametresApplicationUtilisateur.getViewForPeriodeNotification();
		componentVisibilityOfNotification = views.ViewParametresApplicationUtilisateur.getViewForVisibilityOfNotification();
		componentVisibilityOfMainToolBar = views.ViewParametresApplicationUtilisateur.getViewForVisibilityOfMainToolBar();
		componentLang = views.ViewParametresApplicationUtilisateur.getViewForLang();
		componentLookAndFeel = views.ViewParametresApplicationUtilisateur.getViewForLookAndFeel();

//		stpListContent.setDividerLocation(300);
		
		bAjouter.setToolTipText("Ajouter un Paramètres de l'Utilisateur");
		bSupprimer.setToolTipText("Supprimer le Paramètres de l'Utilisateur sélectionné");
		bExporter.setToolTipText("Exporter la liste des parametresApplicationUtilisateurs vers un fichier Excel");
		bImporter.setToolTipText("Importer des parametresApplicationUtilisateurs à partir d'un fichier Excel");
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
				.addComponent(labelPeriodeNotification)
				.addComponent(labelVisibilityOfNotification)
				.addComponent(labelVisibilityOfMainToolBar)
				.addComponent(labelLang)
				.addComponent(labelLookAndFeel)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layoutPFormulaire.createParallelGroup()
				.addComponent(componentPeriodeNotification, 300, 300, 300)
				.addComponent(componentVisibilityOfNotification, 300, 300, 300)
				.addComponent(componentVisibilityOfMainToolBar, 300, 300, 300)
				.addComponent(componentLang, 300, 300, 300)
				.addComponent(componentLookAndFeel, 300, 300, 300)
			)
			.addGap(1, 1, Short.MAX_VALUE)
		);
		layoutPFormulaire.setVerticalGroup(layoutPFormulaire.createSequentialGroup()
				.addGap(10, 10, 10)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelPeriodeNotification, 25, 25, 25)
					.addComponent(componentPeriodeNotification, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelVisibilityOfNotification, 25, 25, 25)
					.addComponent(componentVisibilityOfNotification, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelVisibilityOfMainToolBar, 25, 25, 25)
					.addComponent(componentVisibilityOfMainToolBar, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelLang, 25, 25, 25)
					.addComponent(componentLang, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelLookAndFeel, 25, 25, 25)
					.addComponent(componentLookAndFeel, 25, 25, 25)
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
					updateListParametresApplicationUtilisateurs();
				}
			});

			guiNavigator.setCBNumberOfItemsActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt){
					guiNavigator.updateNumberItemsInPage();
					int totalParametresApplicationUtilisateurs = guiNavigator.getTotalNumberOfItems();
					int nbItemsInPage = guiNavigator.getNumberItemsInPage();
					int numberOfPage = totalParametresApplicationUtilisateurs / nbItemsInPage;
					if (totalParametresApplicationUtilisateurs % nbItemsInPage > 0)
						numberOfPage++;
					guiNavigator.setNumberOfPages(numberOfPage);
					guiNavigator.setNumeroPage(guiNavigator.getNumPageAsInt());
					
					updateListParametresApplicationUtilisateurs(true);
				}
			});
		}
	}

	protected void tfFilterDocumentUpdated(DocumentEvent evt) {
		updateListParametresApplicationUtilisateurs(true);
	}

	protected void bAjouterActionPerformed(ActionEvent evt) {
		if (selectedItem != null){
			if ((((ParametresApplicationUtilisateur)selectedItem)).getId() == null || (((ParametresApplicationUtilisateur)selectedItem)).getId() == 0){
				notifierErreur("Veuillez valider le parametresApplicationUtilisateur que vous venez d'ajouter ...");
				return;
			}
		}

		ParametresApplicationUtilisateur parametresApplicationUtilisateur = new ParametresApplicationUtilisateur();

		selectedItem = parametresApplicationUtilisateur;
		dlmListItems.addElement(parametresApplicationUtilisateur);
		listItems.setSelectedValue(selectedItem, true);
		fillFormulaireByParametresApplicationUtilisateur(parametresApplicationUtilisateur);
	}

	protected void bFermerActionPerformed(ActionEvent evt) {
		if (dlgLayerFilter.isVisible()){
			dlgLayerFilter.setVisible(false);
			return;
		}

		if (selectedItem != null){
			if ((((ParametresApplicationUtilisateur)selectedItem)).getId() == null || (((ParametresApplicationUtilisateur)selectedItem)).getId() == 0){
				if (GUIMessageByOptionPane.showQuestionMessage("Un parametresApplicationUtilisateur non ajouté", "Voulez-vous quitter sans valider cet parametresApplicationUtilisateur ?"))
					fermer();
				else
					return;
			}
		}
		fermer();
	}

	protected void bExporterActionPerformed(ActionEvent evt) {
		fcExportImportXLS.setDialogType(JFileChooser.SAVE_DIALOG | JFileChooser.FILES_ONLY);
		fcExportImportXLS.setDialogTitle("Exporter la liste des parametresApplicationUtilisateurs sous format Excel 2003 (*.xls)");
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
		fcExportImportXLS.setDialogTitle("Importer des parametresApplicationUtilisateurs à partir d'un fichier Excel format 2003 (*.xls)");
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
		if (selectedItem == null || !(selectedItem instanceof ParametresApplicationUtilisateur)){
			notifierErreur("Veuillez sélectionner un parametresApplicationUtilisateur ...");
			return;
		}

		if (! GUIMessageByOptionPane.showQuestionMessage("Supprimer un parametresApplicationUtilisateur", "Êtes-vous sûr de la suppression de cet parametresApplicationUtilisateur ?")){
			return;
		}

		int index = listItems.getSelectedIndex();
		
		ParametresApplicationUtilisateur parametresApplicationUtilisateur = (ParametresApplicationUtilisateur)selectedItem;
		if (parametresApplicationUtilisateur.getId() != null || parametresApplicationUtilisateur.getId() > 0){
			DAOParametresApplicationUtilisateur.delete(parametresApplicationUtilisateur);
		}

		dlmListItems.remove(index);
		
		selectedItem = null;
		fillFormulaireByParametresApplicationUtilisateur(null);
	}

	protected void bValiderActionPerformed(ActionEvent evt) {
		if (selectedItem == null || !(selectedItem instanceof ParametresApplicationUtilisateur)){
			notifierErreur("Veuillez sélectionner un ParametresApplicationUtilisateur ...");
			return;
		}

		if (componentPeriodeNotification.getText().trim().equals("") || componentLookAndFeel.getText().trim().equals("")){
			notifierErreur("Veuillez remplir tous les champs, SVP");
			return;
		}

		ParametresApplicationUtilisateur selectedParametresApplicationUtilisateur = (ParametresApplicationUtilisateur) selectedItem;

		if (selectedParametresApplicationUtilisateur.getId() == null || selectedParametresApplicationUtilisateur.getId() == 0){
			selectedParametresApplicationUtilisateur.setId(0);

			selectedParametresApplicationUtilisateur.setPeriodeNotification(Integer.parseInt(componentPeriodeNotification.getText().trim()));
			selectedParametresApplicationUtilisateur.setVisibilityOfNotification(componentVisibilityOfNotification.isSelected());
			selectedParametresApplicationUtilisateur.setVisibilityOfMainToolBar(componentVisibilityOfMainToolBar.isSelected());
			selectedParametresApplicationUtilisateur.setIdLang((componentLang.getSelectedItem() instanceof models.beans.Lang) ? ((models.beans.Lang)componentLang.getSelectedItem()).getId() : 0);
			selectedParametresApplicationUtilisateur.setLookAndFeel(componentLookAndFeel.getText().trim());
			
			selectedParametresApplicationUtilisateur.setId((Integer) communication.SocketCommunicator.getInstance().sendQuery(selectedParametresApplicationUtilisateur));
			notifierConfirmation("Paramètres de l'Utilisateur sauvegardé avec succès");
		}else{
			utils.SGBDConfig.InsertUpdateSQLQueries listQueries = new utils.SGBDConfig.InsertUpdateSQLQueries((utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement[])null);
			if (! componentPeriodeNotification.getText().trim().equals(String.valueOf(selectedParametresApplicationUtilisateur.getPeriodeNotification()))){
				selectedParametresApplicationUtilisateur.setPeriodeNotification(Integer.parseInt(componentPeriodeNotification.getText().trim()));
				listQueries.addInsertUpdateSQLQueries(DAOParametresApplicationUtilisateur.getSQLUpdateForPeriodeNotificationByPreparedStatement(selectedParametresApplicationUtilisateur));
			}
			if (componentVisibilityOfNotification.isSelected() != selectedParametresApplicationUtilisateur.isVisibilityOfNotification().booleanValue()){
				selectedParametresApplicationUtilisateur.setVisibilityOfNotification(componentVisibilityOfNotification.isSelected());
				listQueries.addInsertUpdateSQLQueries(DAOParametresApplicationUtilisateur.getSQLUpdateForVisibilityOfNotificationByPreparedStatement(selectedParametresApplicationUtilisateur));
			}
			if (componentVisibilityOfMainToolBar.isSelected() != selectedParametresApplicationUtilisateur.isVisibilityOfMainToolBar().booleanValue()){
				selectedParametresApplicationUtilisateur.setVisibilityOfMainToolBar(componentVisibilityOfMainToolBar.isSelected());
				listQueries.addInsertUpdateSQLQueries(DAOParametresApplicationUtilisateur.getSQLUpdateForVisibilityOfMainToolBarByPreparedStatement(selectedParametresApplicationUtilisateur));
			}
			if (selectedParametresApplicationUtilisateur.getIdLang() == null || !(componentLang.getSelectedItem() instanceof models.beans.Lang) || ((models.beans.Lang)componentLang.getSelectedItem()).getId().intValue() != selectedParametresApplicationUtilisateur.getIdLang().intValue()){
				selectedParametresApplicationUtilisateur.setIdLang(((models.beans.Lang)componentLang.getSelectedItem()).getId());
				listQueries.addInsertUpdateSQLQueries(DAOParametresApplicationUtilisateur.getSQLUpdateForIdLangByPreparedStatement(selectedParametresApplicationUtilisateur));
			}
			if (! componentLookAndFeel.getText().trim().equals(selectedParametresApplicationUtilisateur.getLookAndFeel())){
				selectedParametresApplicationUtilisateur.setLookAndFeel(componentLookAndFeel.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOParametresApplicationUtilisateur.getSQLUpdateForLookAndFeelByPreparedStatement(selectedParametresApplicationUtilisateur));
			}
			
			communication.SocketCommunicator.getInstance().sendQuery(listQueries);
			notifierConfirmation("ParametresApplicationUtilisateur modifié avec succès");
		}
		
		dlmListItems.set(dlmListItems.indexOf(selectedItem), selectedParametresApplicationUtilisateur);
	}

	protected void listItemsMouseClicked(MouseEvent evt) {
		selectedItem = listItems.getSelectedValue();
		if (selectedItem == null){
			fillFormulaireByParametresApplicationUtilisateur(null);
			return;
		}

		fillFormulaireByParametresApplicationUtilisateur((ParametresApplicationUtilisateur)selectedItem);
	}

	protected void miDeleteAllActionPerformed(ActionEvent evt) {
		if (GUIMessageByOptionPane.showQuestionMessage("Supprimer Tous ?", "Voulez-vous vraiment supprimer tous les Paramètres de l'Utilisateur ?")){
			DAOParametresApplicationUtilisateur.deleteAll();
			fillFormulaireByParametresApplicationUtilisateur(null);
		}
	}

	private void exportToExcelFile(File file){
		try{
			views.ViewParametresApplicationUtilisateur.exportToExcel(file);
		}
		catch (Exception e){
			GUIMessageByOptionPane.showErrorMessage("Erreur de génération d'Excel", "Errueur de génération de l'Excel");
			StringUtils.printDebug(e);
		}
	}

	private void importFromExcelFile(File file){
		views.ViewParametresApplicationUtilisateur.importFromExcel(file);
	}

	protected void emptyFields() {
		this.componentPeriodeNotification.setText("");
		this.componentPeriodeNotification.setEnabled(false);
		this.labelPeriodeNotification.setEnabled(false);

		this.componentVisibilityOfNotification.setSelected(false);
		this.componentVisibilityOfNotification.setEnabled(false);
		this.labelVisibilityOfNotification.setEnabled(false);

		this.componentVisibilityOfMainToolBar.setSelected(false);
		this.componentVisibilityOfMainToolBar.setEnabled(false);
		this.labelVisibilityOfMainToolBar.setEnabled(false);

		this.componentLang.setSelectedIndex(0);
		this.componentLang.setEnabled(false);
		this.labelLang.setEnabled(false);

		this.componentLookAndFeel.setText("");
		this.componentLookAndFeel.setEnabled(false);
		this.labelLookAndFeel.setEnabled(false);

	}

	protected void initFields() {
		this.pNotification.setVisible(JInternalFrameManagementModel.isVisibilityNotificationBar());

		selectedItem = null;

		setPhoto(null, false);

		if (guiNavigator.isVisible()){
			guiNavigator.updateNumberItemsInPage();
			int totalParametresApplicationUtilisateurs = DAOParametresApplicationUtilisateur.getCountInTable();
			int nbItemsInPage = guiNavigator.getNumberItemsInPage();
			int numberOfPage = totalParametresApplicationUtilisateurs / nbItemsInPage;
			if (totalParametresApplicationUtilisateurs % nbItemsInPage > 0)
				numberOfPage++;
			guiNavigator.setTotalNumberOfItems(totalParametresApplicationUtilisateurs);
			guiNavigator.setNumberOfPages(numberOfPage);
			guiNavigator.setNumeroPage(1);
		}

		updateListParametresApplicationUtilisateurs(true);

		
		componentLang.removeAllItems();
		List<models.beans.Lang> listLangs = models.daos.client.DAOLang.getListInstances();
		for (models.beans.Lang instance : listLangs){
			componentLang.addItem(instance);
		}
		componentLang.insertItemAt("Choisir un Language de l'Interface", 0);
		
		fillFormulaireByParametresApplicationUtilisateur(null);
	}

	private synchronized void updateListParametresApplicationUtilisateurs(){
		updateListParametresApplicationUtilisateurs(false);
	}

	private synchronized void updateListParametresApplicationUtilisateurs(boolean filtering){
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
				int totalParametresApplicationUtilisateurs = DAOParametresApplicationUtilisateur.getCountInTable(condition);
				int nbItemsInPage = guiNavigator.getNumberItemsInPage();
				int numberOfPage = totalParametresApplicationUtilisateurs / nbItemsInPage;
				if (totalParametresApplicationUtilisateurs % nbItemsInPage > 0)
					numberOfPage++;
				guiNavigator.setTotalNumberOfItems(totalParametresApplicationUtilisateurs);
				guiNavigator.setNumberOfPages(numberOfPage);
				guiNavigator.setNumeroPage(1);
			}
			if (guiNavigator.getFirst() < 0)
				return;
		}
		
		dlmListItems.clear();
		List<ParametresApplicationUtilisateur> listParametresApplicationUtilisateurs = DAOParametresApplicationUtilisateur.getListInstances(condition);
		for (ParametresApplicationUtilisateur parametresApplicationUtilisateur : listParametresApplicationUtilisateurs){
			dlmListItems.addElement(parametresApplicationUtilisateur);
		}
	}

	protected void fillFormulaireByParametresApplicationUtilisateur(ParametresApplicationUtilisateur parametresApplicationUtilisateur) {
//		pFormulaire.setVisible(parametresApplicationUtilisateur != null);
		if (parametresApplicationUtilisateur == null){
			emptyFields();
			return;
		}

		this.componentPeriodeNotification.setText(String.valueOf(parametresApplicationUtilisateur.getPeriodeNotification()));
		this.componentPeriodeNotification.setEnabled(true);
		this.labelPeriodeNotification.setEnabled(true);

		this.componentVisibilityOfNotification.setSelected(parametresApplicationUtilisateur.isVisibilityOfNotification());
		this.componentVisibilityOfNotification.setEnabled(true);
		this.labelVisibilityOfNotification.setEnabled(true);

		this.componentVisibilityOfMainToolBar.setSelected(parametresApplicationUtilisateur.isVisibilityOfMainToolBar());
		this.componentVisibilityOfMainToolBar.setEnabled(true);
		this.labelVisibilityOfMainToolBar.setEnabled(true);

		this.componentLang.setSelectedIndex(0);
		for (int index=1; index<this.componentLang.getItemCount(); index++){
			models.beans.Lang lang = (models.beans.Lang)this.componentLang.getItemAt(index);
			if (lang.getId().intValue() == parametresApplicationUtilisateur.getIdLang().intValue()){
				this.componentLang.setSelectedIndex(index);
				break;
			}
		}
		this.labelLang.setEnabled(true);
		this.componentLang.setEnabled(true);
		this.componentLookAndFeel.setText(String.valueOf(parametresApplicationUtilisateur.getLookAndFeel()));
		this.componentLookAndFeel.setEnabled(true);
		this.labelLookAndFeel.setEnabled(true);

		
		listItems.setSelectedIndex(dlmListItems.indexOf(parametresApplicationUtilisateur));
	}

	private void generateAndShowPDF(){
		try{
			File file = File.createTempFile( "ListeParametresApplicationUtilisateurs", ".pdf");
			file.deleteOnExit();
			
			views.ViewParametresApplicationUtilisateur.exportToPDF(file);
			
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

		getInstance("Gestion des Paramètres de l'Utilisateur ...");
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
		javax.swing.JFrame frame = new javax.swing.JFrame("Test of access to Table : parametresApplicationUtilisateur");
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