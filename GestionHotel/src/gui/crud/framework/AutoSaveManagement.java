package gui.crud.framework;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import utils.StringUtils;

import gui.MainFrame;
import gui.utils.GUIButtonFactory;
import gui.utils.GUIMessageByOptionPane;
import gui.utils.GUIXTable;
import gui.utils.JInternalFrameManagementModel;

public class AutoSaveManagement extends JInternalFrameManagementModel{
	private static final long serialVersionUID = 1L;

	private static AutoSaveManagement instance = null;

	private JDialog			dlgWaitPlease;
	private JLabel			lWaitPlease;

	private JSeparator		separator1, separator2;
	private GUIXTable		tableSauvegardes;
	private JButton			bRealiserSauvegarde, bCopierSauvegarde;
	
	public static AutoSaveManagement getInstanceWithoutCreation(){
		return instance;
	}
	
	public static AutoSaveManagement getInstance(String title){
		if (instance == null)
			instance = new AutoSaveManagement(title);
		
		instance.initFields();
		return instance;
	}

	public AutoSaveManagement(String title) {
		super(title);
		myInitComponents();
	}
	
	private void myInitComponents() {
		this.setFrameIcon(app.utils.FrameworkRessources.SAVE_AUTO_ICON_20_20);
		
		dlgWaitPlease = new JDialog(MainFrame.getInstanceWithoutCreation(), true);
		dlgWaitPlease.setUndecorated(true);
		
		lWaitPlease = new JLabel("<html><body><font size='5'>Veuillez patienter, S.V.P !!!</font></body></html>");
		lWaitPlease.setSize(400, 100);
		lWaitPlease.setHorizontalAlignment(JLabel.CENTER);
		lWaitPlease.setVerticalAlignment(JLabel.CENTER);
		lWaitPlease.setBorder(BorderFactory.createEtchedBorder());
		
		dlgWaitPlease.setContentPane(lWaitPlease);
		dlgWaitPlease.setSize(400, 100);
		dlgWaitPlease.setLocationRelativeTo(null);
		
		tableSauvegardes = new GUIXTable(SaveData.getTableHeaderAutoSave(true), SaveData.getDataAutoSave((File)null, true));
		
		tableSauvegardes.setColumnWidth(0, 35, 35, 35);
		tableSauvegardes.setColumnWidth(2, 120, 120, 120);
		tableSauvegardes.setColumnWidth(3, 120, 120, 120);
		
		separator1 = new JSeparator();
		separator2 = new JSeparator();
		
		bRealiserSauvegarde = GUIButtonFactory.createSimpleButton("Provoquer une Sauvgarde", app.utils.FrameworkRessources.SAVE_AUTO_ICON_20_20);
		bCopierSauvegarde = GUIButtonFactory.createSimpleButtonWithBorder("Copier la Sauvegarde", app.utils.FrameworkRessources.SAVE_ICON_20_20);
		
		pListButtons.removeAll();
		
		bAjouter.setToolTipText("Ajouter un stock");
		bSupprimer.setToolTipText("Supprimer le stock sélectionné");
		bExporter.setToolTipText("Exporter la liste des stocks vers un fichier Excel");
		bImporter.setToolTipText("Importer des stocks à partir d'un fichier Excel");
		
		this.pPhoto.setVisible(false);
		this.bValider.setVisible(false);
		this.spListItems.setVisible(false);
		this.splitPaneTreeList.setDividerSize(0);
		this.stpListContent.setDividerSize(0);
		this.pListButtons.setVisible(false);
		
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
			.addGap(5, 5, 5)
			.addGroup(layoutPFormulaire.createParallelGroup()
					.addGroup(layoutPFormulaire.createSequentialGroup()
							.addComponent(guiNavigator, 30, 30, Short.MAX_VALUE)
					)
					.addComponent(separator1, 1, 1, Short.MAX_VALUE)
					.addComponent(tableSauvegardes, 100, 100, Short.MAX_VALUE)
					.addComponent(separator2, 1, 1, Short.MAX_VALUE)
					.addGroup(layoutPFormulaire.createSequentialGroup()
							.addComponent(bRealiserSauvegarde)
							.addGap(1, 1, Short.MAX_VALUE)
							.addComponent(bCopierSauvegarde)
					)
			)
			.addGap(5, 5, 5)
		);
		layoutPFormulaire.setVerticalGroup(layoutPFormulaire.createSequentialGroup()
			.addGap(2, 2, 2)
			.addGroup(layoutPFormulaire.createParallelGroup()
					.addGroup(layoutPFormulaire.createSequentialGroup()
							.addGap(10, 10, 10)
							.addComponent(separator1, 2, 2, 2)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(tableSauvegardes, 100, 100, Short.MAX_VALUE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(layoutPFormulaire.createParallelGroup()
									.addComponent(guiNavigator, 30, 30, 30)
							)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(separator2, 2, 2, 2)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(layoutPFormulaire.createParallelGroup()
									.addComponent(bRealiserSauvegarde, 20, 20, 20)
									.addComponent(bCopierSauvegarde, 20, 20, 20)
							)
							.addGap(10, 10, 10)
					)
			)
			.addGap(2, 2, 2)
		);
	}

	private void myAllEvents() {
		if (guiNavigator.isVisible()){
			guiNavigator.setTFNumPageDocumentListener(new DocumentListener() {
				public void removeUpdate(DocumentEvent evt) {
				}

				public void insertUpdate(DocumentEvent evt) {
					updateListSauvegardes();
				}

				public void changedUpdate(DocumentEvent evt) {
				}
			});

			guiNavigator.setCBNumberOfItemsActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt){
					guiNavigator.updateNumberItemsInPage();
					int totalStocks = guiNavigator.getTotalNumberOfItems();
					int nbItemsInPage = guiNavigator.getNumberItemsInPage();
					int numberOfPage = totalStocks / nbItemsInPage;
					if (totalStocks % nbItemsInPage > 0)
						numberOfPage++;
					guiNavigator.setNumberOfPages(numberOfPage);
					guiNavigator.setNumeroPage(guiNavigator.getNumPageAsInt());
					
					updateListSauvegardes();
				}
			});
		}
		
		bRealiserSauvegarde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				bRealiserSauvegardeActionPerformed(evt);
			}
		});
		
		bCopierSauvegarde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				bCopierSauvegardeActionPerformed(evt);
			}
		});
		
		tableSauvegardes.getTable().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt){
				tableSauvegardesMouseClicked(evt);
			}
		});
	}
	
	private void tableSauvegardesMouseClicked(MouseEvent evt){
		if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() >= 2){
			Vector<?> row = ((Vector<?>)tableSauvegardes.getSelectedRow());
			if (row == null){
				return;
			}
			SaveData saveData = (SaveData)row.get(row.size()-1);
			
			if (gui.utils.GUIMessageByOptionPane.showQuestionMessage(MainFrame.getInstanceWithoutCreation(), "Restauration", "Voulez-vous restaurer les donnez vers la date : "+saveData.getDate()+" heure : "+saveData.getHeure()+" ?")){
				MainFrame.restaureDataFromFile(saveData.getFile(), false);
			}
		}
	}
	
	private void bRealiserSauvegardeActionPerformed(ActionEvent evt){
		MainFrame.getInstanceWithoutCreation().savingAllDataApplication("Sauvegarde des Données ....");
		updateListSauvegardes();
	}
	
	private void bCopierSauvegardeActionPerformed(ActionEvent evt){
		Vector<Object> row = tableSauvegardes.getSelectedRow();
		if (row == null){
			notifierErreur("Veuillez sélectionner la sauvegarde à copier");
			return;
		}
		
		SaveData sd = (SaveData)row.get(row.size()-1);
		
		JFileChooser fc = DlgSauvegardeRestaurationSelectiveData.getFcOpenSaveDataFile();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.setDialogTitle("Copier de la sauvegarde automatique : Sélectionner le Répertoir");
		
		int response = fc.showSaveDialog(this);
		if (response == JFileChooser.CANCEL_OPTION){
			return;
		}
		
		File selectedDir = fc.getSelectedFile();
		
		String fileName = selectedDir.getAbsolutePath()+"/"+sd.file.getName();
		utils.FilesAndLaunchUtils.copyFile(sd.file.getAbsolutePath(), fileName, false);
		
		notifierConfirmation("La sauvegarde a été copiée avec succès");
	}
	
	protected void tfFilterDocumentUpdated(DocumentEvent evt) {
		
	}

	protected void bFermerActionPerformed(ActionEvent evt) {
		fermer();
	}

	protected void bExporterActionPerformed(ActionEvent evt) {
		fcExportImportXLS.setDialogType(JFileChooser.SAVE_DIALOG | JFileChooser.FILES_ONLY);
		fcExportImportXLS.setDialogTitle("Exporter la liste des stocks sous format Excel 2003 (*.xls)");
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

		exportToFile(file);
	}

	protected void bImporterActionPerformed(ActionEvent evt) {
		fcExportImportXLS.setDialogType(JFileChooser.OPEN_DIALOG | JFileChooser.FILES_ONLY);
		fcExportImportXLS.setDialogTitle("Importer des stocks à partir d'un fichier Excel format 2003 (*.xls)");
		int response = fcExportImportXLS.showOpenDialog(this);
		if (response != JFileChooser.APPROVE_OPTION){
			return;
		}
		File file = fcExportImportXLS.getSelectedFile();
		if (file.isDirectory() || !file.getAbsolutePath().toLowerCase().endsWith(".xls")){
			GUIMessageByOptionPane.showErrorMessage("Format Excel 2003", "Veuillez choisir un fichier format Excel 2003 (*.xls)");
			return;
		}

		importerFromFile(file);
	}

	protected void bPrintActionPerformed(ActionEvent evt) {
		generateAndShowPDF();
	}

	protected void bSupprimerActionPerformed(ActionEvent evt) {
	}

	protected void bValiderActionPerformed(ActionEvent evt) {
	}

	protected void listItemsMouseClicked(MouseEvent evt) {
	}

	protected void miDeleteAllActionPerformed(ActionEvent evt) {
	}

	private void exportToFile(File file){
		try{
		}
		catch (Exception e){
			GUIMessageByOptionPane.showErrorMessage("Erreur de génération d'Excel", "Errueur de génération de l'Excel");
			StringUtils.printDebug(e);
		}
	}

	private void importerFromFile(File file){
		try{
		}
		catch (Exception e){
			GUIMessageByOptionPane.showErrorMessage("Excel non valide", "Votre document Excel n'est pas valide");
			StringUtils.printDebug(e);
		}
	}

	protected void emptyFields() {
	}

	
	private void updateGUINavigator(int totalItems){
		guiNavigator.updateNumberItemsInPage();
		int nbItemsInPage = guiNavigator.getNumberItemsInPage();
		int numberOfPage = totalItems / nbItemsInPage;
		if (totalItems % nbItemsInPage > 0)
			numberOfPage++;
		guiNavigator.setTotalNumberOfItems(totalItems);
		guiNavigator.setNumberOfPages(numberOfPage);
		guiNavigator.setNumeroPage(1);
	}
	
	protected void initFields() {
		this.pNotification.setVisible(JInternalFrameManagementModel.isVisibilityNotificationBar());
		
		selectedItem = null;

		setPhoto(null, false);

		if (guiNavigator.isVisible()){
			(new File(utils.FilesAndLaunchUtils.getAppDataDir()+"/.ouzegganeRedouaneApplications/")).mkdir();
			File autoSavesDir = new File(appClient.Spool.getAutoSaveDir());
			autoSavesDir.mkdir();
			
			int totalSauvegardes = autoSavesDir.listFiles(SaveData.ZIP_AUTO_SAVE_FILE_FILTER).length;
			updateGUINavigator(totalSauvegardes);
		}
		
		updateListSauvegardes();
	}
	
	public void updateListSauvegardes(){
		tableSauvegardes.clearData();
		
		(new File(utils.FilesAndLaunchUtils.getAppDataDir()+"/.ouzegganeRedouaneApplications/")).mkdir();
		File autoSavesDir = new File(appClient.Spool.getAutoSaveDir());
		autoSavesDir.mkdir();
		
		int firstItem = guiNavigator.getFirst();
		int maxItems = guiNavigator.getNumberItemsInPage();
		
		tableSauvegardes.setData(SaveData.getDataAutoSave(autoSavesDir, true, firstItem, maxItems));
	}
	
	private void generateAndShowPDF(){
		try{
		}
		catch (Exception e){
			StringUtils.printDebug(e);
		}
	}

	public static void showFrame(JDesktopPane desktop){
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
			}catch (Exception e){
				StringUtils.printDebug(e);
			}
			
			return;
		}
		
		getInstance("Sauvegardes Automatiques Journalières");
		if (!instance.isAdded()){
			Dimension size = desktop.getSize();

			instance.setAdded(true);
			desktop.add(instance);

			int width = 1050;
			int height = 550;

			instance.setBounds((size.width - width) / 2, (size.height - height) / 2, width, height);
			
			try{
				instance.setMaximum(true);
			}
			catch (Exception e){
			}
		}

		if (instance.isIcon){
			try{
				instance.getDesktopPane().getDesktopManager().deiconifyFrame(instance);
				instance.setIcon(false);
			}catch (Exception e){
				StringUtils.printDebug(e);
			}
		}

		instance.setVisible(true);
		instance.toFront();
		instance.requestFocus();
	}
	
	/**
	 * 
	 * @author OUZEGGANE Redouane
	 *
	 */
	public static class SaveData implements Comparable<Object> {
		private File	file = null;
		
		public static final FileFilter ZIP_AUTO_SAVE_FILE_FILTER = new FileFilter() {
			public boolean accept(File file) {
				boolean accepted = file.getAbsolutePath().toLowerCase().endsWith(".zip") && file.isFile();
				if (accepted){
					java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("Sauvegarde_\\d\\d\\d\\d-\\d\\d-\\d\\d \\d\\d_\\d\\d_\\d\\d_.*.zip");
					java.util.regex.Matcher matcher = pattern.matcher(file.getName());
					
					accepted = matcher.find();
				}
				return accepted;
			}
		};
		
		public SaveData(File file) {
			this.file = file;
		}
		
		public String toString(){
			if (file == null){
				return "";
			}
			return file.getName();
		}
		
		public static List<SaveData> getListSaveData(String dirPath){
			return getListSaveData(new File(dirPath));
		}
		
		public static List<SaveData> getListSaveData(File dirPath){
			if (dirPath == null || !dirPath.exists() || !dirPath.isDirectory()){
				return null;
			}
			
			List<SaveData> list = new ArrayList<SaveData>();
			
			File[] sauvegardesFiles = dirPath.listFiles(ZIP_AUTO_SAVE_FILE_FILTER);
			
			for (File file : sauvegardesFiles){
				list.add(new SaveData(file));
			}
			
			Collections.sort((List<SaveData>)list);
			
			return list;
		}
		
		public String getName(){
			if (this.file == null){
				return "";
			}
			
			String name = this.file.getName();
			
			name = name.substring(0, name.indexOf(".zip"));
			String parts[] = name.split(" ");
			name = parts[0].replace("_", " ")+"  "+parts[1].replace("-", "/").replace("_", ":").substring(0, 11);
			
			return name;
		}
		
		public File getFile() {
			return file;
		}
		
		public String getDate(){
			if (this.file == null){
				return "??/??/????";
			}
			
			String name = getName();
			String date = utils.StringUtils.formatDateFromMySQL(name.split(" ")[1]);
			
			return date;
		}
		
		public String getHeure(){
			if (this.file == null){
				return "00:00:00:00";
			}
			
			String name = getName();
			return name.split(" ")[3];
		}
		
		public static java.util.Vector<Object> getTableHeaderAutoSave(boolean withNumero){
			java.util.Vector<Object> header = new java.util.Vector<Object>();
			
			if (withNumero)
				header.add("N°");
			header.add("Sauvegarde");
			header.add("Date");
			header.add("Heure");

			return header;
		}
		
		public java.util.Vector<Object> getTableRowSaveData(int numOrder){
			java.util.Vector<Object> row = new java.util.Vector<Object>();
			
			if (numOrder > 0)
				row.add(numOrder);
			
			row.add(this.getName());
			row.add(this.getDate());
			row.add(this.getHeure());
			row.add(this);
			
			return row;
		}
		
		public static java.util.Vector<java.util.Vector<Object>> getDataAutoSave(List<SaveData> list, boolean withOrder){
			return getDataAutoSave(list, withOrder, 0);
		}
		
		public static java.util.Vector<java.util.Vector<Object>> getDataAutoSave(List<SaveData> list, boolean withOrder, int baseRow){
			java.util.Vector<java.util.Vector<Object>> data = new java.util.Vector<java.util.Vector<Object>>();
			if (list == null)
				return data;
			
			int numRow = baseRow;
			for (SaveData stock : list){
				if (withOrder)
					numRow++;
				
				data.add(stock.getTableRowSaveData(numRow));
			}
			
			return data;
		}
		
		public static java.util.Vector<java.util.Vector<Object>> getDataAutoSave(File dirPath, boolean withOrder){
			return getDataAutoSave(getListSaveData(dirPath), withOrder);
		}
		
		public static java.util.Vector<java.util.Vector<Object>> getDataAutoSave(File dirPath, boolean withOrder, int firstItem, int maxItems){
			List<SaveData> listSD = getListSaveData(dirPath);
			if (firstItem >= listSD.size()){
				listSD.clear();
			}
			listSD = listSD.subList(firstItem, Math.min(listSD.size(), firstItem+maxItems));
			return getDataAutoSave(listSD, withOrder, firstItem);
		}

		public int compareTo(Object other) {
			if (! (other instanceof SaveData)){
				return -1;
			}
			
			SaveData otherSD = (SaveData)other;
			
			return -(this.getName().compareTo(otherSD.getName()));
		}
	}

	@Override
	protected void bAjouterActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		
	}
}