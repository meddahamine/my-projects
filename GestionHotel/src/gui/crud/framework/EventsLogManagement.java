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

import models.beans.EventsLog;
import models.daos.client.DAOEventsLog;

import gui.utils.GUIMessageByOptionPane;
import gui.utils.JInternalFrameManagementModel;

public class EventsLogManagement extends JInternalFrameManagementModel{
	private static final long serialVersionUID = 1L;

	private static EventsLogManagement instance = null;

	private JLabel labelEvenement;
	private JLabel labelConnexionsLog;
	private JLabel labelDate;

	private javax.swing.JScrollPane componentEvenement;
	private javax.swing.JComboBox componentConnexionsLog;
	private gui.utils.GUIDate componentDate;

	public static EventsLogManagement getInstance(String title){
		if (instance == null)
			instance = new EventsLogManagement(title);
		instance.initFields();
		return instance;
	}

	public static EventsLogManagement getInstanceWithoutCreation(){
		return instance;
	}

	public EventsLogManagement(String title) {
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
		
		labelEvenement = new JLabel(views.ViewEventsLog.getCaptionForEvenement()+" : ");
		labelEvenement.setVerticalAlignment(JLabel.CENTER);
		
		labelConnexionsLog = new JLabel(views.ViewEventsLog.getCaptionForConnexionsLog()+" : ");
		labelConnexionsLog.setVerticalAlignment(JLabel.CENTER);
		
		labelDate = new JLabel(views.ViewEventsLog.getCaptionForDate()+" : ");
		labelDate.setVerticalAlignment(JLabel.CENTER);
		

		componentEvenement = views.ViewEventsLog.getViewForEvenement();
		componentConnexionsLog = views.ViewEventsLog.getViewForConnexionsLog();
		componentDate = views.ViewEventsLog.getViewForDate();

//		stpListContent.setDividerLocation(300);
		
		bAjouter.setToolTipText("Ajouter un Journal des Évènements");
		bSupprimer.setToolTipText("Supprimer le Journal des Évènements sélectionné");
		bExporter.setToolTipText("Exporter la liste des eventsLogs vers un fichier Excel");
		bImporter.setToolTipText("Importer des eventsLogs à partir d'un fichier Excel");
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
				.addComponent(labelEvenement)
				.addComponent(labelConnexionsLog)
				.addComponent(labelDate)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layoutPFormulaire.createParallelGroup()
				.addComponent(componentEvenement, 300, 300, 300)
				.addComponent(componentConnexionsLog, 300, 300, 300)
				.addComponent(componentDate, 300, 300, 300)
			)
			.addGap(1, 1, Short.MAX_VALUE)
		);
		layoutPFormulaire.setVerticalGroup(layoutPFormulaire.createSequentialGroup()
				.addGap(10, 10, 10)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelEvenement, 25, 25, 25)
					.addComponent(componentEvenement, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelConnexionsLog, 25, 25, 25)
					.addComponent(componentConnexionsLog, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelDate, 25, 25, 25)
					.addComponent(componentDate, 25, 25, 25)
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
					updateListEventsLogs();
				}
			});

			guiNavigator.setCBNumberOfItemsActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt){
					guiNavigator.updateNumberItemsInPage();
					int totalEventsLogs = guiNavigator.getTotalNumberOfItems();
					int nbItemsInPage = guiNavigator.getNumberItemsInPage();
					int numberOfPage = totalEventsLogs / nbItemsInPage;
					if (totalEventsLogs % nbItemsInPage > 0)
						numberOfPage++;
					guiNavigator.setNumberOfPages(numberOfPage);
					guiNavigator.setNumeroPage(guiNavigator.getNumPageAsInt());
					
					updateListEventsLogs(true);
				}
			});
		}
	}

	protected void tfFilterDocumentUpdated(DocumentEvent evt) {
		updateListEventsLogs(true);
	}

	protected void bAjouterActionPerformed(ActionEvent evt) {
		if (selectedItem != null){
			if ((((EventsLog)selectedItem)).getId() == null || (((EventsLog)selectedItem)).getId() == 0){
				notifierErreur("Veuillez valider le eventsLog que vous venez d'ajouter ...");
				return;
			}
		}

		EventsLog eventsLog = new EventsLog();

		selectedItem = eventsLog;
		dlmListItems.addElement(eventsLog);
		listItems.setSelectedValue(selectedItem, true);
		fillFormulaireByEventsLog(eventsLog);
	}

	protected void bFermerActionPerformed(ActionEvent evt) {
		if (dlgLayerFilter.isVisible()){
			dlgLayerFilter.setVisible(false);
			return;
		}

		if (selectedItem != null){
			if ((((EventsLog)selectedItem)).getId() == null || (((EventsLog)selectedItem)).getId() == 0){
				if (GUIMessageByOptionPane.showQuestionMessage("Un eventsLog non ajouté", "Voulez-vous quitter sans valider cet eventsLog ?"))
					fermer();
				else
					return;
			}
		}
		fermer();
	}

	protected void bExporterActionPerformed(ActionEvent evt) {
		fcExportImportXLS.setDialogType(JFileChooser.SAVE_DIALOG | JFileChooser.FILES_ONLY);
		fcExportImportXLS.setDialogTitle("Exporter la liste des eventsLogs sous format Excel 2003 (*.xls)");
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
		fcExportImportXLS.setDialogTitle("Importer des eventsLogs à partir d'un fichier Excel format 2003 (*.xls)");
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
		if (selectedItem == null || !(selectedItem instanceof EventsLog)){
			notifierErreur("Veuillez sélectionner un eventsLog ...");
			return;
		}

		if (! GUIMessageByOptionPane.showQuestionMessage("Supprimer un eventsLog", "Êtes-vous sûr de la suppression de cet eventsLog ?")){
			return;
		}

		int index = listItems.getSelectedIndex();
		
		EventsLog eventsLog = (EventsLog)selectedItem;
		if (eventsLog.getId() != null || eventsLog.getId() > 0){
			DAOEventsLog.delete(eventsLog);
		}

		dlmListItems.remove(index);
		
		selectedItem = null;
		fillFormulaireByEventsLog(null);
	}

	protected void bValiderActionPerformed(ActionEvent evt) {
		if (selectedItem == null || !(selectedItem instanceof EventsLog)){
			notifierErreur("Veuillez sélectionner un EventsLog ...");
			return;
		}

		if (((javax.swing.JTextArea)componentEvenement.getViewport().getComponent(0)).getText().trim().equals("") || componentDate.getMySQLDate().equals("")){
			notifierErreur("Veuillez remplir tous les champs, SVP");
			return;
		}

		EventsLog selectedEventsLog = (EventsLog) selectedItem;

		if (selectedEventsLog.getId() == null || selectedEventsLog.getId() == 0){
			selectedEventsLog.setId(0);

			selectedEventsLog.setEvenement(((javax.swing.JTextArea)componentEvenement.getViewport().getComponent(0)).getText());
			selectedEventsLog.setIdConnexionsLog((componentConnexionsLog.getSelectedItem() instanceof models.beans.ConnexionsLog) ? ((models.beans.ConnexionsLog)componentConnexionsLog.getSelectedItem()).getId() : 0);
			selectedEventsLog.setDate(StringUtils.getTimestampFromString(componentDate.getMySQLDate()));
			
			selectedEventsLog.setId((Integer) communication.SocketCommunicator.getInstance().sendQuery(selectedEventsLog));
			notifierConfirmation("Journal des Évènements sauvegardé avec succès");
		}else{
			utils.SGBDConfig.InsertUpdateSQLQueries listQueries = new utils.SGBDConfig.InsertUpdateSQLQueries((utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement[])null);
			if (! ((javax.swing.JTextArea)componentEvenement.getViewport().getComponent(0)).getText().trim().equals(selectedEventsLog.getEvenement())){
				selectedEventsLog.setEvenement(((javax.swing.JTextArea)componentEvenement.getViewport().getComponent(0)).getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOEventsLog.getSQLUpdateForEvenementByPreparedStatement(selectedEventsLog));
			}
			if (selectedEventsLog.getIdConnexionsLog() == null || !(componentConnexionsLog.getSelectedItem() instanceof models.beans.ConnexionsLog) || ((models.beans.ConnexionsLog)componentConnexionsLog.getSelectedItem()).getId().intValue() != selectedEventsLog.getIdConnexionsLog().intValue()){
				selectedEventsLog.setIdConnexionsLog(((models.beans.ConnexionsLog)componentConnexionsLog.getSelectedItem()).getId());
				listQueries.addInsertUpdateSQLQueries(DAOEventsLog.getSQLUpdateForIdConnexionsLogByPreparedStatement(selectedEventsLog));
			}
			if (selectedEventsLog.getDate() == null || ! selectedEventsLog.getDate().toString().equals(StringUtils.getTimestampFromString(componentDate.getMySQLDate()))){
				selectedEventsLog.setDate(StringUtils.getTimestampFromString(componentDate.getMySQLDate()));
				listQueries.addInsertUpdateSQLQueries(DAOEventsLog.getSQLUpdateForDateByPreparedStatement(selectedEventsLog));
			}
			
			communication.SocketCommunicator.getInstance().sendQuery(listQueries);
			notifierConfirmation("EventsLog modifié avec succès");
		}
		
		dlmListItems.set(dlmListItems.indexOf(selectedItem), selectedEventsLog);
	}

	protected void listItemsMouseClicked(MouseEvent evt) {
		selectedItem = listItems.getSelectedValue();
		if (selectedItem == null){
			fillFormulaireByEventsLog(null);
			return;
		}

		fillFormulaireByEventsLog((EventsLog)selectedItem);
	}

	protected void miDeleteAllActionPerformed(ActionEvent evt) {
		if (GUIMessageByOptionPane.showQuestionMessage("Supprimer Tous ?", "Voulez-vous vraiment supprimer tous les Journal des Évènements ?")){
			DAOEventsLog.deleteAll();
			fillFormulaireByEventsLog(null);
		}
	}

	private void exportToExcelFile(File file){
		try{
			views.ViewEventsLog.exportToExcel(file);
		}
		catch (Exception e){
			GUIMessageByOptionPane.showErrorMessage("Erreur de génération d'Excel", "Errueur de génération de l'Excel");
			StringUtils.printDebug(e);
		}
	}

	private void importFromExcelFile(File file){
		views.ViewEventsLog.importFromExcel(file);
	}

	protected void emptyFields() {
		((javax.swing.JTextArea)this.componentEvenement.getViewport().getComponent(0)).setText("");
		((javax.swing.JTextArea)this.componentEvenement.getViewport().getComponent(0)).setEnabled(false);
		this.labelEvenement.setEnabled(false);

		this.componentConnexionsLog.setSelectedIndex(0);
		this.componentConnexionsLog.setEnabled(false);
		this.labelConnexionsLog.setEnabled(false);

		this.componentDate.clear();
		this.componentDate.setEnabled(false);
		this.labelDate.setEnabled(false);

	}

	protected void initFields() {
		this.pNotification.setVisible(JInternalFrameManagementModel.isVisibilityNotificationBar());

		selectedItem = null;

		setPhoto(null, false);

		if (guiNavigator.isVisible()){
			guiNavigator.updateNumberItemsInPage();
			int totalEventsLogs = DAOEventsLog.getCountInTable();
			int nbItemsInPage = guiNavigator.getNumberItemsInPage();
			int numberOfPage = totalEventsLogs / nbItemsInPage;
			if (totalEventsLogs % nbItemsInPage > 0)
				numberOfPage++;
			guiNavigator.setTotalNumberOfItems(totalEventsLogs);
			guiNavigator.setNumberOfPages(numberOfPage);
			guiNavigator.setNumeroPage(1);
		}

		updateListEventsLogs(true);

		
		componentConnexionsLog.removeAllItems();
		List<models.beans.ConnexionsLog> listConnexionsLogs = models.daos.client.DAOConnexionsLog.getListInstances();
		for (models.beans.ConnexionsLog instance : listConnexionsLogs){
			componentConnexionsLog.addItem(instance);
		}
		componentConnexionsLog.insertItemAt("Choisir un ", 0);
		
		fillFormulaireByEventsLog(null);
	}

	private synchronized void updateListEventsLogs(){
		updateListEventsLogs(false);
	}

	private synchronized void updateListEventsLogs(boolean filtering){
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
				int totalEventsLogs = DAOEventsLog.getCountInTable(condition);
				int nbItemsInPage = guiNavigator.getNumberItemsInPage();
				int numberOfPage = totalEventsLogs / nbItemsInPage;
				if (totalEventsLogs % nbItemsInPage > 0)
					numberOfPage++;
				guiNavigator.setTotalNumberOfItems(totalEventsLogs);
				guiNavigator.setNumberOfPages(numberOfPage);
				guiNavigator.setNumeroPage(1);
			}
			if (guiNavigator.getFirst() < 0)
				return;
		}
		
		dlmListItems.clear();
		List<EventsLog> listEventsLogs = DAOEventsLog.getListInstances(condition);
		for (EventsLog eventsLog : listEventsLogs){
			dlmListItems.addElement(eventsLog);
		}
	}

	protected void fillFormulaireByEventsLog(EventsLog eventsLog) {
//		pFormulaire.setVisible(eventsLog != null);
		if (eventsLog == null){
			emptyFields();
			return;
		}

		((javax.swing.JTextArea)this.componentEvenement.getViewport().getComponent(0)).setText(String.valueOf(eventsLog.getEvenement()));
		((javax.swing.JTextArea)this.componentEvenement.getViewport().getComponent(0)).setEnabled(true);
		this.labelEvenement.setEnabled(true);

		this.componentConnexionsLog.setSelectedIndex(0);
		for (int index=1; index<this.componentConnexionsLog.getItemCount(); index++){
			models.beans.ConnexionsLog connexionslog = (models.beans.ConnexionsLog)this.componentConnexionsLog.getItemAt(index);
			if (connexionslog.getId().intValue() == eventsLog.getIdConnexionsLog().intValue()){
				this.componentConnexionsLog.setSelectedIndex(index);
				break;
			}
		}
		this.labelConnexionsLog.setEnabled(true);
		this.componentConnexionsLog.setEnabled(true);
		if (eventsLog.getDate() == null)
			this.componentDate.clear();
		else
			this.componentDate.setMySQLDate(eventsLog.getDate().toString());
		this.componentDate.setEnabled(true);
		this.labelDate.setEnabled(true);

		
		listItems.setSelectedIndex(dlmListItems.indexOf(eventsLog));
	}

	private void generateAndShowPDF(){
		try{
			File file = File.createTempFile( "ListeEventsLogs", ".pdf");
			file.deleteOnExit();
			
			views.ViewEventsLog.exportToPDF(file);
			
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

		getInstance("Gestion des Journal des Évènements ...");
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
		javax.swing.JFrame frame = new javax.swing.JFrame("Test of access to Table : eventsLog");
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