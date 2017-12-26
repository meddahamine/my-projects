package gui.crud.framework;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import gui.MainFrame;
import gui.utils.GUIButtonFactory;
import gui.utils.GUIDate;
import gui.utils.GUIMessageByOptionPane;
import gui.utils.GUIPanelFactory;
import gui.utils.GUIPanelFactory.BusyLayer.RunningClassMethod;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;

import org.jdesktop.swingx.JXTitledSeparator;

import appClient.Spool.DataExport;

import utils.FilesAndLaunchUtils;
import utils.StringUtils;

public class DlgSauvegardeRestaurationSelectiveData extends JDialog{
	private static final long serialVersionUID = 1L;
	
	private static DlgSauvegardeRestaurationSelectiveData instance = null;
	
	public static final String FILE_SAVE_SELECTED_DATA = "dataSavedByExportation.dat";
	
	private static JFileChooser	fcOpenSaveDataFile = null;
	
	private DataExport savedData;
	
	private static final FileFilter ZIP_FILE_FILTER = new FileFilter() {
		public String getDescription() {
			return "Fichiers ZIP uniquement (*.zip)";
		}
		
		public boolean accept(File file) {
			if (file.isDirectory() || (file.isFile() && file.getAbsolutePath().toLowerCase().endsWith(".zip")))
				return true;
			return false;
		}
	};
	
	public static final int SAVE_SELECTED_DLG = 1;
	public static final int RESTAURE_SELECTED_DLG = 2;
	
	private int typeDlg = SAVE_SELECTED_DLG;
	
	private JCheckBox chbParametresOrganismes, chbRoles, chbUtilisateurs;
	private JCheckBox chbArticle, chbUniteMesures, chbCategoriesArticle;
	private JCheckBox chbClients, chbFournisseurs;
	private JCheckBox chbCommandesFournisseur, chbLivraisonsFournisseurs, chbRetourFournisseurs;
	private JCheckBox chbCommandesClient, chbLivraisonsClients, chbRetourClients;
	
	private List<JCheckBox> listSelectCHBoxes = new ArrayList<JCheckBox>();
	
	private JButton 	bSelectAll, bUnselectAll;
	private JButton 	bVisualize, bSave, bCancel;
	
	private JPanel		pContent;
	
	private JXTitledSeparator	sepContentSave, sepParamsSave, sepBottom1, sepBottom2, sepBottom3;
	private JCheckBox			chbAutoSaveData;
	
	private JTabbedPane tabbedPane;
	
	private JScrollPane		spConfigParams;
	private JScrollPane		spArticlesCAUMStock;
	private JScrollPane		spAchatsRF;
	private JScrollPane		spVentesRC;
	
	private JPanel			pModeSauvegarde;
	private JRadioButton	rbEcrasement, rbInsertion;
	
	private JPanel			pDateSauvegarde;
	private JCheckBox		chbDateDu, chbDateAu;
	private JLabel			lDateDu, lDateAu;
	private GUIDate			gdDateDu, gdDateAu;
	
	public static DlgSauvegardeRestaurationSelectiveData getInstance() {
		if (instance == null){
			instance = new DlgSauvegardeRestaurationSelectiveData();
		}
		return instance;
	}

	private DlgSauvegardeRestaurationSelectiveData() {
		super(MainFrame.getInstanceWithoutCreation(), true);
		initComponents();
	}
	
	private void initFields(){
		bVisualize.setVisible(this.getTypeDlg() == SAVE_SELECTED_DLG);
		bSave.setVisible(this.getTypeDlg() == SAVE_SELECTED_DLG);
		
		String borderTitle = " Sauvegarde Selective des Données ";
		if (this.getTypeDlg() == RESTAURE_SELECTED_DLG){
			borderTitle = "Importation des Données";
		}
		((TitledBorder)this.pContent.getBorder()).setTitle(borderTitle);
		this.pContent.updateUI();
		this.pack();
		
		chbDatesActionPerformed();
		chbDataActionPerformed();
		
		this.setBackground(Color.WHITE);
	}
	
	private void initComponents(){
		this.setUndecorated(true);
		
		chbParametresOrganismes = new JCheckBox("Paramètres Organismes");
		chbParametresOrganismes.setName("ParametresOrganisme");
		listSelectCHBoxes.add(chbParametresOrganismes);
		
		chbRoles = new JCheckBox("Rôles (Profils d'utilisateurs)");
		chbRoles.setName("Role");
		listSelectCHBoxes.add(chbRoles);
		
		chbUtilisateurs = new JCheckBox("Utilisateurs");
		chbUtilisateurs.setName("Utilisateur");
		listSelectCHBoxes.add(chbUtilisateurs);
		
		chbArticle = new JCheckBox("Articles");
		chbArticle.setName("Article");
		listSelectCHBoxes.add(chbArticle);
		
		chbUniteMesures = new JCheckBox("Unités de mésures");
		chbUniteMesures.setName("UniteMesure");
		listSelectCHBoxes.add(chbUniteMesures);
		
		chbCategoriesArticle = new JCheckBox("Catégories Articles");
		chbCategoriesArticle.setName("CategorieArticle");
		listSelectCHBoxes.add(chbCategoriesArticle);
		
		chbClients = new JCheckBox("Clients");
		chbClients.setName("Client");
		listSelectCHBoxes.add(chbClients);
		
		chbFournisseurs = new JCheckBox("Fournisseurs");
		chbFournisseurs.setName("Fournisseur");
		listSelectCHBoxes.add(chbFournisseurs);
		
		chbCommandesFournisseur = new JCheckBox("Commandes Fournisseurs");
		chbCommandesFournisseur.setName("CommandeFournisseur");
		listSelectCHBoxes.add(chbCommandesFournisseur);
		
		chbLivraisonsFournisseurs = new JCheckBox("Achats (Livraisons Fournisseurs)");
		chbLivraisonsFournisseurs.setName("LivraisonFournisseur");
		listSelectCHBoxes.add(chbLivraisonsFournisseurs);
		
		chbRetourFournisseurs = new JCheckBox("Retours Fournisseurs");
		chbRetourFournisseurs.setName("RetourFournisseur");
		listSelectCHBoxes.add(chbRetourFournisseurs);
		chbRetourFournisseurs.setVisible(false);
		
		chbCommandesClient = new JCheckBox("Commnades Clients");
		chbCommandesClient.setName("CommandeClient");
		listSelectCHBoxes.add(chbCommandesClient);
		
		chbLivraisonsClients = new JCheckBox("Ventes (Livraisons Clients)");
		chbLivraisonsClients.setName("Vente");
		listSelectCHBoxes.add(chbLivraisonsClients);
		
		chbRetourClients = new JCheckBox("Retours Clients");
		chbRetourClients.setName("RetourClient");
		listSelectCHBoxes.add(chbRetourClients);
		chbRetourClients.setVisible(false);
		
		Component[] listComponentsParams = 	{chbParametresOrganismes, chbRoles, chbUtilisateurs};
		
		Component[] listComponentsArticles = {chbArticle, chbUniteMesures, chbCategoriesArticle};
		
		Component[] listComponentsClients = {chbClients, chbCommandesClient, chbLivraisonsClients, chbRetourClients};
		
		Component[] listComponentsFournisseur = {chbFournisseurs, chbCommandesFournisseur, chbLivraisonsFournisseurs, chbRetourFournisseurs};
		
		spConfigParams = GUIPanelFactory.createPanelLinearLayoutVerticalWithScroll(listComponentsParams);
		spArticlesCAUMStock = GUIPanelFactory.createPanelLinearLayoutVerticalWithScroll(listComponentsArticles);
		spAchatsRF = GUIPanelFactory.createPanelLinearLayoutVerticalWithScroll(listComponentsFournisseur);
		spVentesRC = GUIPanelFactory.createPanelLinearLayoutVerticalWithScroll(listComponentsClients);
		
		bSelectAll = GUIButtonFactory.createCheck4Button("Tous sélectionner");
		bUnselectAll = GUIButtonFactory.createCheck4Button("Tous dessélectionner");
		
		tabbedPane = GUIPanelFactory.createTabbedPane(GUIPanelFactory.PLASTIQUE_PANE_TYPE);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
		tabbedPane.add("Clients - Ventes - BCC - BRC", spVentesRC);
		tabbedPane.add("Fournisseurs - Achats - BCF - BRF", spAchatsRF);
		tabbedPane.add("Articles - Catégories - Unités Mésures - Stock", spArticlesCAUMStock);
		tabbedPane.add("Paramètres", spConfigParams);
		
		sepContentSave = new JXTitledSeparator("Données à sauvegarder : ");
		sepParamsSave = new JXTitledSeparator("Paramètres de la sauvegarde : ");
		
		sepBottom1 = new JXTitledSeparator("");
		sepBottom2 = new JXTitledSeparator("");
		
		sepBottom3 = new JXTitledSeparator("");
		chbAutoSaveData = new JCheckBox("Réaliser une sauvegarde avant l'importation des données");
		chbAutoSaveData.setSelected(true);
		
		rbEcrasement = new JRadioButton("Écrasement / Remplacement");
		rbInsertion = new JRadioButton("Insertion / Ajout");
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rbEcrasement);
		bg.add(rbInsertion);
		rbInsertion.setSelected(true);
		
		Component[] listComponentsModeSauvegarde = {rbInsertion, rbEcrasement};
		
		pModeSauvegarde = GUIPanelFactory.createPanelLinearLayoutVertical(listComponentsModeSauvegarde);
		pModeSauvegarde.setBorder(BorderFactory.createTitledBorder("Mode de sauvegarde : "));
		
		pDateSauvegarde = new JPanel();
		pDateSauvegarde.setBorder(BorderFactory.createTitledBorder("Dates de la sauvegarde : "));
		
		chbDateDu = new JCheckBox();
		chbDateAu = new JCheckBox();
		
		lDateDu = new JLabel("Du : ");
		lDateAu = new JLabel("Au : ");
		
		gdDateDu = new GUIDate(this);
		gdDateAu = new GUIDate(this);
		
		gdDateDu.setDateMySQL(appClient.Spool.getDateDuJour());
		gdDateAu.setDateMySQL(appClient.Spool.getDateDuJour());
		
		bVisualize = GUIButtonFactory.createSimpleButton("Visualiser", app.utils.FrameworkRessources.ZOOM_ICON_20_20, 'V');
		bSave = GUIButtonFactory.createSimpleButton("Sauvegarder", app.utils.FrameworkRessources.SAVE_SELECTED_20_20, 'S');
		bCancel = GUIButtonFactory.createCancelButton("Annuler", 'A');
		
		pContent = new JPanel();
		TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), "");
		pContent.setBorder(border);
		border.setTitleJustification(TitledBorder.CENTER);
		border.setTitleFont(border.getTitleFont().deriveFont(17f));
		
		this.setContentPane(pContent);
		
		configureRootPane(this.getRootPane());
		
		allLayout();
		allEvents();
	}
	
	private void configureRootPane(JRootPane rootPane) {
		InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escPressed");
		rootPane.getActionMap().put("escPressed",
			new AbstractAction("escPressed") {
				private static final long serialVersionUID = 1L;
	
				public void actionPerformed(ActionEvent actionEvent) {
					bCancelActionPerformed(actionEvent);
				}
			}
		);
	}
	
	private void allLayout(){
		GroupLayout layoutPDateSauvegarde = new GroupLayout(pDateSauvegarde);
		pDateSauvegarde.setLayout(layoutPDateSauvegarde);
		layoutPDateSauvegarde.setHorizontalGroup(layoutPDateSauvegarde.createSequentialGroup()
				.addGap(4, 4, 4)
				.addGroup(layoutPDateSauvegarde.createParallelGroup()
						.addComponent(chbDateDu)
						.addComponent(chbDateAu)
				)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layoutPDateSauvegarde.createParallelGroup()
						.addComponent(lDateDu)
						.addComponent(lDateAu)
				)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layoutPDateSauvegarde.createParallelGroup()
						.addComponent(gdDateDu)
						.addComponent(gdDateAu)
				)
				.addGap(4, 4, 4)
		);
		layoutPDateSauvegarde.setVerticalGroup(layoutPDateSauvegarde.createSequentialGroup()
				.addGap(1, 1, Short.MAX_VALUE)
				.addGroup(layoutPDateSauvegarde.createParallelGroup()
						.addComponent(chbDateDu)
						.addComponent(lDateDu)
						.addComponent(gdDateDu)
				)
				.addGap(1, 1, Short.MAX_VALUE)
				.addGroup(layoutPDateSauvegarde.createParallelGroup()
						.addComponent(chbDateAu)
						.addComponent(lDateAu)
						.addComponent(gdDateAu)
				)
				.addGap(1, 1, Short.MAX_VALUE)
		);
		
		GroupLayout layout = new GroupLayout(pContent);
		pContent.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
						.addComponent(sepContentSave, 400, 400, Short.MAX_VALUE)
				)
				.addGroup(layout.createSequentialGroup()
						.addGap(5)
						.addComponent(tabbedPane, 700, 700, Short.MAX_VALUE)
						.addGap(5)
				)
				.addComponent(sepParamsSave, 800, 800, Short.MAX_VALUE)
				.addGroup(layout.createSequentialGroup()
						.addGap(5)
						.addComponent(pModeSauvegarde, 300, 300, Short.MAX_VALUE)
						.addGap(5)
						.addComponent(pDateSauvegarde, 300, 300, Short.MAX_VALUE)
						.addGap(5)
				)
				.addComponent(sepBottom1, 800, 800, Short.MAX_VALUE)
				.addGroup(layout.createSequentialGroup()
						.addGap(10, 10, 10)
						.addComponent(bSelectAll)
						.addGap(10, 10, Short.MAX_VALUE)
						.addComponent(bUnselectAll)
						.addGap(10, 10, 10)
				)
				.addComponent(sepBottom2, 800, 800, Short.MAX_VALUE)
				.addGroup(layout.createSequentialGroup()
						.addGap(10, 10, 10)
						.addComponent(chbAutoSaveData)
						.addGap(10, 10, 10)
				)
				.addComponent(sepBottom3, 800, 800, Short.MAX_VALUE)
				.addGroup(layout.createSequentialGroup()
						.addGap(10, 10, 10)
						.addComponent(bVisualize)
						.addGap(10, 10, Short.MAX_VALUE)
						.addComponent(bSave)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(bCancel)
						.addGap(10, 10, 10)
				)
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGap(5, 5, 5)
				.addGroup(layout.createParallelGroup()
						.addComponent(sepContentSave)
				)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(tabbedPane, 130, 130, Short.MAX_VALUE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(sepBottom1)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup()
						.addComponent(bSelectAll, 20, 20, 20)
						.addComponent(bUnselectAll, 20, 20, 20)
				)
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(sepParamsSave)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup()
						.addComponent(pModeSauvegarde, 80, 80, 80)
						.addComponent(pDateSauvegarde, 80, 80, 80)
				)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(sepBottom2)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(chbAutoSaveData)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(sepBottom3)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup()
						.addComponent(bVisualize, 20, 20, 20)
						.addComponent(bSave, 20, 20, 20)
						.addComponent(bCancel, 20, 20, 20)						
				)
				.addGap(5, 5, 5)
		);
		
		this.pack();
	}
	
	private void allEvents(){
		bCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				bCancelActionPerformed(evt);
			}
		});
		
		bSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				bSaveActionPerformed(evt);
			}
		});
		
		bVisualize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				bVisualizeActionPerformed(evt);
			}
		});
		
		bSelectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				bSelectAllActionPerformed(evt);
			}
		});
		
		bUnselectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				bUnselectAllActionPerformed(evt);
			}
		});
		
		ActionListener alChbDateActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				chbDatesActionPerformed();
			}
		};
		
		chbDateDu.addActionListener(alChbDateActionListener);
		chbDateAu.addActionListener(alChbDateActionListener);
		
		ActionListener alChbDataActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				chbDataActionPerformed();
			}
		};
		for (JCheckBox chb : listSelectCHBoxes){
			chb.addActionListener(alChbDataActionListener);
		}
	}
	
	private void chbDataActionPerformed(){
		boolean enabled = false;
		for (JCheckBox chb : listSelectCHBoxes){
			if (chb.isSelected()){
				enabled = true;
				break;
			}
		}
		
		bSave.setEnabled(enabled);
		bVisualize.setEnabled(enabled);
	}
	
	private void chbDatesActionPerformed(){
		boolean dateDu = chbDateDu.isSelected();
		boolean dateAu = chbDateAu.isSelected();
		
		lDateDu.setEnabled(dateDu);
		gdDateDu.setEnabled(dateDu);
		
		lDateAu.setEnabled(dateAu);
		gdDateAu.setEnabled(dateAu);
	}
	
	private List<String> getSelectedTableToSave(){
		List<String> fileTableNames = new ArrayList<String>();
	
		for (JCheckBox chb : listSelectCHBoxes){
			if (chb.isSelected()){
				fileTableNames.add(chb.getName());
			}
		}
		
		return fileTableNames;
	}
	
	private void bCancelActionPerformed(ActionEvent evt){
		this.setVisible(false);
	}
	
	private DataExport createDataSauvegarde(List<String> tableNames){
		String dateDu = null;
		String dateAu = null;
		
		if (chbDateDu.isSelected()){
			dateDu = gdDateDu.getMySQLDate();
		}
		if (chbDateAu.isSelected()){
			dateAu = gdDateAu.getMySQLDate();
		}
		
		DataExport data = new DataExport();
		for (String tableName : tableNames){
			Class<?> classController;
			try{
				classController = Class.forName("controllers."+tableName);
			}
			catch (Exception e){
				continue;
			}
			
			String whereCondition = " where 1 ";
			
			try{
				classController.getSuperclass().getDeclaredField("date");
				if (dateDu != null){
					whereCondition += " and `date` >= '"+dateDu+"'";
				}
				
				if (dateAu != null){
					whereCondition += " and `date` <= '"+dateAu+"'";
				}
			}catch (Exception e){
			}
			
			try{
				List<?> list = (List<?>)classController.getMethod("getListInstancesFromServer", Class.forName("java.lang.String")).invoke(null, whereCondition);
				data.addListDataObject(list);
			}
			catch (Exception e){
				continue;
			}
		}
		
		return data;
	}
	
	public void visualiserData(){
		try{
			savedData = createDataSauvegarde(getSelectedTableToSave());
			DlgSaveImportDataViewer.getSingleton(DlgSauvegardeRestaurationSelectiveData.getInstance());
			gui.utils.GUIPanelFactory.BusyLayer.hideBusyMessage();
		}
		catch (Exception e){
			StringUtils.printDebug(e);
		}
	}
	
	private void bVisualizeActionPerformed(ActionEvent evt){
		RunningClassMethod runningClassMethod = new RunningClassMethod();
		runningClassMethod.classMethodToRun = this.getClass();
		runningClassMethod.instanceOfClass = this;
		runningClassMethod.methodToRun = "visualiserData";
		
		Class<?>[] paramsTypes = {};
		runningClassMethod.paramsMethodeTypes = paramsTypes;

		Object[] paramsValues = {};
		runningClassMethod.paramsMethodeValues = paramsValues;
		
		String messageWaiting = "<html><body><center><font size='5'>Veuillez patienter, S.V.P !!!</font>";
		messageWaiting += "<br/><font size='2' color='blue'>Visualisation des données à sauvegarder</font>";
		messageWaiting += "</center></body></html>";
		gui.utils.GUIPanelFactory.BusyLayer.showBusyMessage(runningClassMethod, messageWaiting);
		
		if (savedData != null){
			DlgSaveImportDataViewer.showFrame(DlgSauvegardeRestaurationSelectiveData.getInstance(), savedData);
		}
	}
	
	public void save(File fileSelected, List<String> tableNames){
		try{
			File file = new File(FILE_SAVE_SELECTED_DATA);
			
			savedData = createDataSauvegarde(tableNames);
			savedData.setAutoSaveData(chbAutoSaveData.isSelected());
			
			ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file));
			writer.writeObject(savedData);
			
			writer.flush();
			writer.close();
			
			utils.FilesAndLaunchUtils.createZipFile(file, fileSelected, utils.StringUtils.PASSWORD_DATA);
//			
			file.delete();
			
			gui.utils.GUIPanelFactory.BusyLayer.hideBusyMessage();
			GUIMessageByOptionPane.showConfirmMessage("Sauvegarde de données", "La sauvegarde sélective de données a été effectuée avec succès ...");
		}
		catch (Exception e){
			StringUtils.printDebug(e);
		}
	}
	
	private void bSaveActionPerformed(ActionEvent evt){
		final List<String> tableNames = getSelectedTableToSave();
		if (tableNames.size() == 0){
			GUIMessageByOptionPane.showErrorMessage(this, "Sélection vide", "Veuillez sélectionner un élément à sauvegarder !!!");
			return;
		}
		
		fcOpenSaveDataFile.setDialogTitle("Sauvegarde sélective de données : Création de fichier de sauvegarde (*.zip)");
		int response = fcOpenSaveDataFile.showSaveDialog(MainFrame.getInstanceWithoutCreation());
		if (response == JFileChooser.CANCEL_OPTION){
			return;
		}
		
		File selectedFile = fcOpenSaveDataFile.getSelectedFile();
		if (!selectedFile.getAbsolutePath().toLowerCase().endsWith("zip") && typeDlg == SAVE_SELECTED_DLG){
			selectedFile = new File(selectedFile.getAbsolutePath()+".zip");
		}
		
		if (selectedFile.getTotalSpace() > 0){
			if (GUIMessageByOptionPane.showQuestionMessage(this, "Écraser le fichier ?", "Le fichier existe déjà, voulez-vous l'ecraser ?")){
				selectedFile.delete();
				selectedFile = new File(selectedFile.getAbsolutePath());
			}
			else{
				GUIMessageByOptionPane.showWarningMessage(this, "Sauvegarde de données", "La sauvegarde de données a été annulée ...");
				return;
			}
		}
		
		RunningClassMethod runningClassMethod = new RunningClassMethod();
		runningClassMethod.classMethodToRun = this.getClass();
		runningClassMethod.instanceOfClass = this;
		runningClassMethod.methodToRun = "save";
		
		Class<?>[] paramsTypes = {File.class, List.class};
		runningClassMethod.paramsMethodeTypes = paramsTypes;

		Object[] paramsValues = {selectedFile, tableNames};
		runningClassMethod.paramsMethodeValues = paramsValues;
		
		String messageWaiting = "<html><body><center><font size='5'>Veuillez patienter, S.V.P !!!</font>";
		messageWaiting += "<br/><font size='2'>Sauvegarde Sélective de données en cours</font>";
		messageWaiting += "</center></body></html>";
		gui.utils.GUIPanelFactory.BusyLayer.showBusyMessage(runningClassMethod, messageWaiting);
		
		this.setVisible(false);
	}
	
	private void setSelectionAll(boolean selected){
		for (JCheckBox chb : listSelectCHBoxes){
			chb.setSelected(selected);
		}
		
		chbDataActionPerformed();
	}
	
	private void bSelectAllActionPerformed(ActionEvent evt){
		setSelectionAll(true);
	}

	private void bUnselectAllActionPerformed(ActionEvent evt){
		setSelectionAll(false);
	}
	
	public int getTypeDlg() {
		return typeDlg;
	}
	
	public void setTypeDlg(int typeDlg) {
		this.typeDlg = typeDlg;
	}
	
	private static void createFileChooser(){
		if (fcOpenSaveDataFile == null){
			fcOpenSaveDataFile = new JFileChooser();
			fcOpenSaveDataFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fcOpenSaveDataFile.setFileFilter(ZIP_FILE_FILTER);
		}
	}
	
	public static JFileChooser getFcOpenSaveDataFile() {
		createFileChooser();
		fcOpenSaveDataFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
		return fcOpenSaveDataFile;
	}
	
	public static String getSQLExportAllData(){
		String txtQueries = "";
		
		List<Class<?>> classes = FilesAndLaunchUtils.getClasses("controllers");
		for (Class<?> classControllers : classes){
			try{
				txtQueries += (String)classControllers.getMethod("getSQLStructure").invoke(null);
				List<?> listItems = (List<?>)classControllers.getMethod("getListInstancesFromServer").invoke(null);
				for (Object item : listItems){
					txtQueries += (String)classControllers.getMethod("getSQLTuple").invoke(item);
				}
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		txtQueries = ("SET FOREIGN_KEY_CHECKS=0;"+txtQueries+"SET FOREIGN_KEY_CHECKS=1;");
		
		return txtQueries;
	}
	
	public static String getSQLExportAllDataByMySQLDumbCommandElementary(){
		String txtQueries = "";
		
		List<String> listClasses = new ArrayList<String>();
		listClasses.add("Photo");
		listClasses.add("Document");
		listClasses.add("CategorieArticleNiveauConfiguration");
		
		List<Class<?>> classes = FilesAndLaunchUtils.getClasses("controllers");
		for (Class<?> classControllers : classes){
			try{
				String className = classControllers.getName().substring(classControllers.getName().lastIndexOf(".")+1);
				if (listClasses.contains(className)){
					txtQueries += (String)classControllers.getMethod("getSQLStructure").invoke(null);
					List<?> listItems = (List<?>)classControllers.getMethod("getListInstancesFromServer").invoke(null);
					for (Object item : listItems){
						txtQueries += (String)classControllers.getMethod("getSQLTuple").invoke(item);
					}
				}
				else{
					txtQueries += FilesAndLaunchUtils.launcheProgram("mysqldump -uroot -pjfqlksjHe6ggkjlmhUiNInujfsdklIklfrdsedjqlkfoudqjOaIlfnjdsqesoddvljfksdlq gestionStock "+(String)classControllers.getMethod("getTableName").invoke(null));
				}
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		txtQueries = ("SET FOREIGN_KEY_CHECKS=0;"+txtQueries+"SET FOREIGN_KEY_CHECKS=1;");
		
		return txtQueries;
	}
	
	public static String getSQLExportAllDataByMySQLDumbCommand(){
		String txtQueries = FilesAndLaunchUtils.launcheProgram("mysqldump -uroot -pjfqlksjHe6ggkjlmhUiNInujfsdklIklfrdsedjqlkfoudqjOaIlfnjdsqesoddvljfksdlq gestionStock");
		txtQueries = ("SET FOREIGN_KEY_CHECKS=0;"+txtQueries+"SET FOREIGN_KEY_CHECKS=1;");
		
		return txtQueries;
	}
	
	public static void showFrame(int typeDlg){
		getInstance();
		createFileChooser();
		
		JFileChooser fcOpenSaveDataFile = getFcOpenSaveDataFile();
		
		fcOpenSaveDataFile.setDialogType((typeDlg == RESTAURE_SELECTED_DLG)? JFileChooser.OPEN_DIALOG : JFileChooser.SAVE_DIALOG);
		
		if (typeDlg == RESTAURE_SELECTED_DLG){
			fcOpenSaveDataFile.setDialogTitle("Restauration de données : Sélectionner un fichier de sauvegarde sélective (*.zip)");
			
			int response = fcOpenSaveDataFile.showOpenDialog(MainFrame.getInstanceWithoutCreation());
			if (response == JFileChooser.CANCEL_OPTION){
				return;
			}
			
			final File selectedFile = fcOpenSaveDataFile.getSelectedFile();
			if (!selectedFile.getAbsolutePath().toLowerCase().endsWith("zip") && typeDlg == RESTAURE_SELECTED_DLG){
				GUIMessageByOptionPane.showErrorMessage("Erreur d'importation", "Veuillez sélectionner un fichier ZIP");
				return;
			}			
			
			try{
				utils.FilesAndLaunchUtils.extractAll(selectedFile, new File(utils.FilesAndLaunchUtils.getTempDir()), utils.StringUtils.PASSWORD_DATA);
				File file = new File (utils.FilesAndLaunchUtils.getTempDir()+"/"+FILE_SAVE_SELECTED_DATA);
				if (!file.exists()){
					gui.utils.GUIPanelFactory.BusyLayer.hideBusyMessage();
					GUIMessageByOptionPane.showErrorMessage("Erreur de fichier", "Le fichier ZIP est incohérent avec le système");
					return;
				}
				ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file));
				Object obj = reader.readObject();
				reader.close();
				if (obj instanceof DataExport){
					DataExport data = (DataExport)obj;
					DlgSaveImportDataViewer.showFrame(null, data, true);
				}
				else{
					gui.utils.GUIPanelFactory.BusyLayer.hideBusyMessage();
					GUIMessageByOptionPane.showErrorMessage("Erreur de fichier", "Erreur du contenu du fichier");
				}
			}
			catch (Exception e){
				utils.StringUtils.printDebug(e);
			}
			
			return;
		}
		
		fcOpenSaveDataFile.setDialogTitle("Sauvegarde Sélective de données : Indiquer un fichier à créer (*.zip)");
		
		instance.setTypeDlg(typeDlg);
		instance.initFields();
		instance.setLocationRelativeTo(MainFrame.getInstanceWithoutCreation());
		instance.setVisible(true);
		instance.requestFocus();
	}
}
