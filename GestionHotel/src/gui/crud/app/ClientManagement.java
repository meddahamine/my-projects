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

import models.beans.Client;
import models.daos.client.DAOClient;

import gui.utils.GUIMessageByOptionPane;
import gui.utils.JInternalFrameManagementModel;

public class ClientManagement extends JInternalFrameManagementModel{
	private static final long serialVersionUID = 1L;

	private static ClientManagement instance = null;

	private JLabel labelTypeClient;
	private JLabel labelNom;
	private JLabel labelPrenom;
	private JLabel labelDateNaiss;
	private JLabel labelAdresse;
	private JLabel labelNationalite;
	private JLabel labelTelephone;
	private JLabel labelTypeCarte;
	private JLabel labelNumCarte;
	private JLabel labelProfession;
	private JLabel labelAutreTypeClient;
	private JLabel labelAutreTypeCarte;
	private JLabel labelLieuDeNaiss;
	private JLabel labelCivilite;

	private javax.swing.JComboBox componentTypeClient;
	private javax.swing.JTextField componentNom;
	private javax.swing.JTextField componentPrenom;
	private gui.utils.GUIDate componentDateNaiss;
	private javax.swing.JTextField componentAdresse;
	private javax.swing.JTextField componentNationalite;
	private javax.swing.JTextField componentTelephone;
	private javax.swing.JComboBox componentTypeCarte;
	private javax.swing.JTextField componentNumCarte;
	private javax.swing.JTextField componentProfession;
	private javax.swing.JTextField componentAutreTypeClient;
	private javax.swing.JTextField componentAutreTypeCarte;
	private javax.swing.JTextField componentLieuDeNaiss;
	private javax.swing.JComboBox componentCivilite;

	public static ClientManagement getInstance(String title){
		if (instance == null)
			instance = new ClientManagement(title);
		instance.initFields();
		return instance;
	}

	public static ClientManagement getInstanceWithoutCreation(){
		return instance;
	}

	public ClientManagement(String title) {
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
		
		labelTypeClient = new JLabel(views.ViewClient.getCaptionForTypeClient()+" Type client : ");
		labelTypeClient.setVerticalAlignment(JLabel.CENTER);
		
		labelNom = new JLabel(views.ViewClient.getCaptionForNom()+" Nom : ");
		labelNom.setVerticalAlignment(JLabel.CENTER);
		
		labelPrenom = new JLabel(views.ViewClient.getCaptionForPrenom()+" Prenom : ");
		labelPrenom.setVerticalAlignment(JLabel.CENTER);
		
		labelDateNaiss = new JLabel(views.ViewClient.getCaptionForDateNaiss()+" Date de naissance : ");
		labelDateNaiss.setVerticalAlignment(JLabel.CENTER);
		
		labelAdresse = new JLabel(views.ViewClient.getCaptionForAdresse()+" Addresse : ");
		labelAdresse.setVerticalAlignment(JLabel.CENTER);
		
		labelNationalite = new JLabel(views.ViewClient.getCaptionForNationalite()+" Nationnalité : ");
		labelNationalite.setVerticalAlignment(JLabel.CENTER);
		
		labelTelephone = new JLabel(views.ViewClient.getCaptionForTelephone()+" Téléphone : ");
		labelTelephone.setVerticalAlignment(JLabel.CENTER);
		
		labelTypeCarte = new JLabel(views.ViewClient.getCaptionForTypeCarte()+" Type carte : ");
		labelTypeCarte.setVerticalAlignment(JLabel.CENTER);
		
		labelNumCarte = new JLabel(views.ViewClient.getCaptionForNumCarte()+" Numéro de la carte : ");
		labelNumCarte.setVerticalAlignment(JLabel.CENTER);
		
		labelProfession = new JLabel(views.ViewClient.getCaptionForProfession()+" Profession : ");
		labelProfession.setVerticalAlignment(JLabel.CENTER);
		
		labelAutreTypeClient = new JLabel(views.ViewClient.getCaptionForAutreTypeClient()+" Autre type client : ");
		labelAutreTypeClient.setVerticalAlignment(JLabel.CENTER);
		
		labelAutreTypeCarte = new JLabel(views.ViewClient.getCaptionForAutreTypeCarte()+" Autre type carte : ");
		labelAutreTypeCarte.setVerticalAlignment(JLabel.CENTER);
		
		labelLieuDeNaiss = new JLabel(views.ViewClient.getCaptionForLieuDeNaiss()+" Lieu de naissance : ");
		labelLieuDeNaiss.setVerticalAlignment(JLabel.CENTER);
		
		labelCivilite = new JLabel(views.ViewClient.getCaptionForCivilite()+" Civilité : ");
		labelCivilite.setVerticalAlignment(JLabel.CENTER);
		

		componentTypeClient = (javax.swing.JComboBox)views.ViewClient.getViewForTypeClient("JCOMBOBOXE");
		componentNom = views.ViewClient.getViewForNom();
		componentPrenom = views.ViewClient.getViewForPrenom();
		componentDateNaiss = views.ViewClient.getViewForDateNaiss();
		componentAdresse = views.ViewClient.getViewForAdresse();
		componentNationalite = views.ViewClient.getViewForNationalite();
		componentTelephone = views.ViewClient.getViewForTelephone();
		componentTypeCarte = (javax.swing.JComboBox)views.ViewClient.getViewForTypeCarte("JCOMBOBOXE");
		componentNumCarte = views.ViewClient.getViewForNumCarte();
		componentProfession = views.ViewClient.getViewForProfession();
		componentAutreTypeClient = views.ViewClient.getViewForAutreTypeClient();
		componentAutreTypeCarte = views.ViewClient.getViewForAutreTypeCarte();
		componentLieuDeNaiss = views.ViewClient.getViewForLieuDeNaiss();
		componentCivilite = (javax.swing.JComboBox)views.ViewClient.getViewForCivilite("JCOMBOBOXE");

//		stpListContent.setDividerLocation(300);
		
		bAjouter.setToolTipText("Ajouter un ");
		bSupprimer.setToolTipText("Supprimer le  sélectionné");
		bExporter.setToolTipText("Exporter la liste des clients vers un fichier Excel");
		bImporter.setToolTipText("Importer des clients à partir d'un fichier Excel");
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
				.addComponent(labelCivilite)	
				.addComponent(labelNom)
				.addComponent(labelPrenom)
				.addComponent(labelDateNaiss)
				.addComponent(labelLieuDeNaiss)
				.addComponent(labelAdresse)
				.addComponent(labelNationalite)
				.addComponent(labelProfession)
				.addComponent(labelTelephone)				
				.addComponent(labelTypeClient)
				.addComponent(labelAutreTypeClient)
				.addComponent(labelTypeCarte)
				.addComponent(labelAutreTypeCarte)
				.addComponent(labelNumCarte)
				
				
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layoutPFormulaire.createParallelGroup()
				.addComponent(componentCivilite, 300, 300, 300)
				.addComponent(componentNom, 300, 300, 300)
				.addComponent(componentPrenom, 300, 300, 300)
				.addComponent(componentDateNaiss, 300, 300, 300)
				.addComponent(componentLieuDeNaiss, 300, 300, 300)
				.addComponent(componentAdresse, 300, 300, 300)
				.addComponent(componentNationalite, 300, 300, 300)
				.addComponent(componentProfession, 300, 300, 300)
				.addComponent(componentTelephone, 300, 300, 300)
				.addComponent(componentTypeClient, 300, 300, 300)
				.addComponent(componentAutreTypeClient, 300, 300, 300)
				.addComponent(componentTypeCarte, 300, 300, 300)
				.addComponent(componentNumCarte, 300, 300, 300)
				.addComponent(componentAutreTypeCarte, 300, 300, 300)
				
				
			)
			.addGap(1, 1, Short.MAX_VALUE)
		);
		layoutPFormulaire.setVerticalGroup(layoutPFormulaire.createSequentialGroup()
				.addGap(10, 10, 10)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelCivilite, 25, 25, 25)
					.addComponent(componentCivilite, 25, 25, 25)
				)
				.addGap(10, 10, 10)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelNom, 25, 25, 25)
					.addComponent(componentNom, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelPrenom, 25, 25, 25)
					.addComponent(componentPrenom, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelDateNaiss, 25, 25, 25)
					.addComponent(componentDateNaiss, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelLieuDeNaiss, 25, 25, 25)
					.addComponent(componentLieuDeNaiss, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelAdresse, 25, 25, 25)
					.addComponent(componentAdresse, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelNationalite, 25, 25, 25)
					.addComponent(componentNationalite, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelProfession, 25, 25, 25)
					.addComponent(componentProfession, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelTelephone, 25, 25, 25)
					.addComponent(componentTelephone, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelTypeClient, 25, 25, 25)
					.addComponent(componentTypeClient, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelAutreTypeClient, 25, 25, 25)
					.addComponent(componentAutreTypeClient, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelTypeCarte, 25, 25, 25)
					.addComponent(componentTypeCarte, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelNumCarte, 25, 25, 25)
					.addComponent(componentNumCarte, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelAutreTypeCarte, 25, 25, 25)
					.addComponent(componentAutreTypeCarte, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				
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
					updateListClients();
				}
			});

			guiNavigator.setCBNumberOfItemsActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt){
					guiNavigator.updateNumberItemsInPage();
					int totalClients = guiNavigator.getTotalNumberOfItems();
					int nbItemsInPage = guiNavigator.getNumberItemsInPage();
					int numberOfPage = totalClients / nbItemsInPage;
					if (totalClients % nbItemsInPage > 0)
						numberOfPage++;
					guiNavigator.setNumberOfPages(numberOfPage);
					guiNavigator.setNumeroPage(guiNavigator.getNumPageAsInt());
					
					updateListClients(true);
				}
			});
		}
	}

	protected void tfFilterDocumentUpdated(DocumentEvent evt) {
		updateListClients(true);
	}

	protected void bAjouterActionPerformed(ActionEvent evt) {
		if (selectedItem != null){
			if ((((Client)selectedItem)).getId() == null || (((Client)selectedItem)).getId() == 0){
				notifierErreur("Veuillez valider le client que vous venez d'ajouter ...");
				return;
			}
		}

		Client client = new Client();

		selectedItem = client;
		dlmListItems.addElement(client);
		listItems.setSelectedValue(selectedItem, true);
		fillFormulaireByClient(client);
	}

	protected void bFermerActionPerformed(ActionEvent evt) {
		if (dlgLayerFilter.isVisible()){
			dlgLayerFilter.setVisible(false);
			return;
		}

		if (selectedItem != null){
			if ((((Client)selectedItem)).getId() == null || (((Client)selectedItem)).getId() == 0){
				if (GUIMessageByOptionPane.showQuestionMessage("Un client non ajouté", "Voulez-vous quitter sans valider cet client ?"))
					fermer();
				else
					return;
			}
		}
		fermer();
	}

	protected void bExporterActionPerformed(ActionEvent evt) {
		fcExportImportXLS.setDialogType(JFileChooser.SAVE_DIALOG | JFileChooser.FILES_ONLY);
		fcExportImportXLS.setDialogTitle("Exporter la liste des clients sous format Excel 2003 (*.xls)");
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
		fcExportImportXLS.setDialogTitle("Importer des clients à partir d'un fichier Excel format 2003 (*.xls)");
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
		if (selectedItem == null || !(selectedItem instanceof Client)){
			notifierErreur("Veuillez sélectionner un client ...");
			return;
		}

		if (! GUIMessageByOptionPane.showQuestionMessage("Supprimer un client", "Êtes-vous sûr de la suppression de cet client ?")){
			return;
		}

		int index = listItems.getSelectedIndex();
		
		Client client = (Client)selectedItem;
		if (client.getId() != null || client.getId() > 0){
			DAOClient.delete(client);
		}

		dlmListItems.remove(index);
		
		selectedItem = null;
		fillFormulaireByClient(null);
	}

	protected void bValiderActionPerformed(ActionEvent evt) {
		if (selectedItem == null || !(selectedItem instanceof Client)){
			notifierErreur("Veuillez sélectionner un Client ...");
			return;
		}

		if (componentNom.getText().trim().equals("") || componentPrenom.getText().trim().equals("") || componentDateNaiss.getMySQLDate().equals("") || componentAdresse.getText().trim().equals("") || componentNationalite.getText().trim().equals("") || componentTelephone.getText().trim().equals("") || componentNumCarte.getText().trim().equals("") || componentProfession.getText().trim().equals("") || componentAutreTypeClient.getText().trim().equals("") || componentAutreTypeCarte.getText().trim().equals("") || componentLieuDeNaiss.getText().trim().equals("")){
			notifierErreur("Veuillez remplir tous les champs, SVP");
			return;
		}

		Client selectedClient = (Client) selectedItem;

		if (selectedClient.getId() == null || selectedClient.getId() == 0){
			selectedClient.setId(0);

			selectedClient.setTypeClient(Client.TypeClient.getByValue(componentTypeClient.getSelectedItem().toString()));
			selectedClient.setNom(componentNom.getText().trim());
			selectedClient.setPrenom(componentPrenom.getText().trim());
			selectedClient.setDateNaiss(StringUtils.getDateFromString(componentDateNaiss.getMySQLDate()));
			selectedClient.setAdresse(componentAdresse.getText().trim());
			selectedClient.setNationalite(componentNationalite.getText().trim());
			selectedClient.setTelephone(componentTelephone.getText().trim());
			selectedClient.setTypeCarte(Client.TypeCarte.getByValue(componentTypeCarte.getSelectedItem().toString()));
			selectedClient.setNumCarte(componentNumCarte.getText().trim());
			selectedClient.setProfession(componentProfession.getText().trim());
			selectedClient.setAutreTypeClient(componentAutreTypeClient.getText().trim());
			selectedClient.setAutreTypeCarte(componentAutreTypeCarte.getText().trim());
			selectedClient.setLieuDeNaiss(componentLieuDeNaiss.getText().trim());
			selectedClient.setCivilite(Client.Civilite.getByValue(componentCivilite.getSelectedItem().toString()));
			
			selectedClient.setId((Integer) communication.SocketCommunicator.getInstance().sendQuery(selectedClient));
			notifierConfirmation(" sauvegardé avec succès");
		}else{
			utils.SGBDConfig.InsertUpdateSQLQueries listQueries = new utils.SGBDConfig.InsertUpdateSQLQueries((utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement[])null);
			if (selectedClient.getTypeClient() == null || ! componentTypeClient.getSelectedItem().toString().equals(selectedClient.getTypeClient().getValue())){
				selectedClient.setTypeClient(Client.TypeClient.getByValue(componentTypeClient.getSelectedItem().toString()));
				listQueries.addInsertUpdateSQLQueries(DAOClient.getSQLUpdateForTypeClientByPreparedStatement(selectedClient));
			}
			if (! componentNom.getText().trim().equals(selectedClient.getNom())){
				selectedClient.setNom(componentNom.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOClient.getSQLUpdateForNomByPreparedStatement(selectedClient));
			}
			if (! componentPrenom.getText().trim().equals(selectedClient.getPrenom())){
				selectedClient.setPrenom(componentPrenom.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOClient.getSQLUpdateForPrenomByPreparedStatement(selectedClient));
			}
			if (selectedClient.getDateNaiss() == null || ! selectedClient.getDateNaiss().toString().equals(StringUtils.getDateFromString(componentDateNaiss.getMySQLDate()))){
				selectedClient.setDateNaiss(StringUtils.getDateFromString(componentDateNaiss.getMySQLDate()));
				listQueries.addInsertUpdateSQLQueries(DAOClient.getSQLUpdateForDateNaissByPreparedStatement(selectedClient));
			}
			if (! componentAdresse.getText().trim().equals(selectedClient.getAdresse())){
				selectedClient.setAdresse(componentAdresse.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOClient.getSQLUpdateForAdresseByPreparedStatement(selectedClient));
			}
			if (! componentNationalite.getText().trim().equals(selectedClient.getNationalite())){
				selectedClient.setNationalite(componentNationalite.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOClient.getSQLUpdateForNationaliteByPreparedStatement(selectedClient));
			}
			if (! componentTelephone.getText().trim().equals(selectedClient.getTelephone())){
				selectedClient.setTelephone(componentTelephone.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOClient.getSQLUpdateForTelephoneByPreparedStatement(selectedClient));
			}
			if (selectedClient.getTypeCarte() == null || ! componentTypeCarte.getSelectedItem().toString().equals(selectedClient.getTypeCarte().getValue())){
				selectedClient.setTypeCarte(Client.TypeCarte.getByValue(componentTypeCarte.getSelectedItem().toString()));
				listQueries.addInsertUpdateSQLQueries(DAOClient.getSQLUpdateForTypeCarteByPreparedStatement(selectedClient));
			}
			if (! componentNumCarte.getText().trim().equals(selectedClient.getNumCarte())){
				selectedClient.setNumCarte(componentNumCarte.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOClient.getSQLUpdateForNumCarteByPreparedStatement(selectedClient));
			}
			if (! componentProfession.getText().trim().equals(selectedClient.getProfession())){
				selectedClient.setProfession(componentProfession.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOClient.getSQLUpdateForProfessionByPreparedStatement(selectedClient));
			}
			if (! componentAutreTypeClient.getText().trim().equals(selectedClient.getAutreTypeClient())){
				selectedClient.setAutreTypeClient(componentAutreTypeClient.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOClient.getSQLUpdateForAutreTypeClientByPreparedStatement(selectedClient));
			}
			if (! componentAutreTypeCarte.getText().trim().equals(selectedClient.getAutreTypeCarte())){
				selectedClient.setAutreTypeCarte(componentAutreTypeCarte.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOClient.getSQLUpdateForAutreTypeCarteByPreparedStatement(selectedClient));
			}
			if (! componentLieuDeNaiss.getText().trim().equals(selectedClient.getLieuDeNaiss())){
				selectedClient.setLieuDeNaiss(componentLieuDeNaiss.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOClient.getSQLUpdateForLieuDeNaissByPreparedStatement(selectedClient));
			}
			if (selectedClient.getCivilite() == null || ! componentCivilite.getSelectedItem().toString().equals(selectedClient.getCivilite().getValue())){
				selectedClient.setCivilite(Client.Civilite.getByValue(componentCivilite.getSelectedItem().toString()));
				listQueries.addInsertUpdateSQLQueries(DAOClient.getSQLUpdateForCiviliteByPreparedStatement(selectedClient));
			}
			
			communication.SocketCommunicator.getInstance().sendQuery(listQueries);
			notifierConfirmation("Client modifié avec succès");
		}
		
		dlmListItems.set(dlmListItems.indexOf(selectedItem), selectedClient);
	}

	protected void listItemsMouseClicked(MouseEvent evt) {
		selectedItem = listItems.getSelectedValue();
		if (selectedItem == null){
			fillFormulaireByClient(null);
			return;
		}

		fillFormulaireByClient((Client)selectedItem);
	}

	protected void miDeleteAllActionPerformed(ActionEvent evt) {
		if (GUIMessageByOptionPane.showQuestionMessage("Supprimer Tous ?", "Voulez-vous vraiment supprimer tous les  ?")){
			DAOClient.deleteAll();
			fillFormulaireByClient(null);
		}
	}

	private void exportToExcelFile(File file){
		try{
			views.ViewClient.exportToExcel(file);
		}
		catch (Exception e){
			GUIMessageByOptionPane.showErrorMessage("Erreur de génération d'Excel", "Errueur de génération de l'Excel");
			StringUtils.printDebug(e);
		}
	}

	private void importFromExcelFile(File file){
		views.ViewClient.importFromExcel(file);
	}

	protected void emptyFields() {
		this.componentTypeClient.setSelectedIndex(0);
		this.componentTypeClient.setEnabled(false);
		this.labelTypeClient.setEnabled(false);

		this.componentNom.setText("");
		this.componentNom.setEnabled(false);
		this.labelNom.setEnabled(false);

		this.componentPrenom.setText("");
		this.componentPrenom.setEnabled(false);
		this.labelPrenom.setEnabled(false);

		this.componentDateNaiss.clear();
		this.componentDateNaiss.setEnabled(false);
		this.labelDateNaiss.setEnabled(false);

		this.componentAdresse.setText("");
		this.componentAdresse.setEnabled(false);
		this.labelAdresse.setEnabled(false);

		this.componentNationalite.setText("");
		this.componentNationalite.setEnabled(false);
		this.labelNationalite.setEnabled(false);

		this.componentTelephone.setText("");
		this.componentTelephone.setEnabled(false);
		this.labelTelephone.setEnabled(false);

		this.componentTypeCarte.setSelectedIndex(0);
		this.componentTypeCarte.setEnabled(false);
		this.labelTypeCarte.setEnabled(false);

		this.componentNumCarte.setText("");
		this.componentNumCarte.setEnabled(false);
		this.labelNumCarte.setEnabled(false);

		this.componentProfession.setText("");
		this.componentProfession.setEnabled(false);
		this.labelProfession.setEnabled(false);

		this.componentAutreTypeClient.setText("");
		this.componentAutreTypeClient.setEnabled(false);
		this.labelAutreTypeClient.setEnabled(false);

		this.componentAutreTypeCarte.setText("");
		this.componentAutreTypeCarte.setEnabled(false);
		this.labelAutreTypeCarte.setEnabled(false);

		this.componentLieuDeNaiss.setText("");
		this.componentLieuDeNaiss.setEnabled(false);
		this.labelLieuDeNaiss.setEnabled(false);

		this.componentCivilite.setSelectedIndex(0);
		this.componentCivilite.setEnabled(false);
		this.labelCivilite.setEnabled(false);

	}

	protected void initFields() {
		this.pNotification.setVisible(JInternalFrameManagementModel.isVisibilityNotificationBar());

		selectedItem = null;

		setPhoto(null, false);

		if (guiNavigator.isVisible()){
			guiNavigator.updateNumberItemsInPage();
			int totalClients = DAOClient.getCountInTable();
			int nbItemsInPage = guiNavigator.getNumberItemsInPage();
			int numberOfPage = totalClients / nbItemsInPage;
			if (totalClients % nbItemsInPage > 0)
				numberOfPage++;
			guiNavigator.setTotalNumberOfItems(totalClients);
			guiNavigator.setNumberOfPages(numberOfPage);
			guiNavigator.setNumeroPage(1);
		}

		updateListClients(true);

		
		fillFormulaireByClient(null);
	}

	private synchronized void updateListClients(){
		updateListClients(false);
	}

	private synchronized void updateListClients(boolean filtering){
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
			condition = "WHERE nom like '"+filterText+"%' ";
		}

		int totalClients = DAOClient.getCountInTable(condition);
		condition += orderByLimit;
		
		if (guiNavigator.isVisible()){
			if (filtering){
				guiNavigator.updateNumberItemsInPage();
				int nbItemsInPage = guiNavigator.getNumberItemsInPage();
				int numberOfPage = totalClients / nbItemsInPage;
				if (totalClients % nbItemsInPage > 0)
					numberOfPage++;
				guiNavigator.setTotalNumberOfItems(totalClients);
				guiNavigator.setNumberOfPages(numberOfPage);
				guiNavigator.setNumeroPage(1);
			}
			if (guiNavigator.getFirst() < 0)
				return;
		}
		
		dlmListItems.clear();
		List<Client> listClients = DAOClient.getListInstances(condition);
		for (Client client : listClients){
			dlmListItems.addElement(client);
		}
		
		updateNbreItems(listClients.size(), "", totalClients);
	}

	protected void fillFormulaireByClient(Client client) {
//		pFormulaire.setVisible(client != null);
		if (client == null){
			emptyFields();
			return;
		}

		if (client.getTypeClient() != null)
			this.componentTypeClient.setSelectedItem(client.getTypeClient().getValue());
		else
			this.componentTypeClient.setSelectedIndex(0);
		this.componentTypeClient.setEnabled(true);
		this.labelTypeClient.setEnabled(true);

		this.componentNom.setText(String.valueOf(client.getNom()));
		this.componentNom.setEnabled(true);
		this.labelNom.setEnabled(true);

		this.componentPrenom.setText(String.valueOf(client.getPrenom()));
		this.componentPrenom.setEnabled(true);
		this.labelPrenom.setEnabled(true);

		if (client.getDateNaiss() == null)
			this.componentDateNaiss.clear();
		else
			this.componentDateNaiss.setMySQLDate(client.getDateNaiss().toString());
		this.componentDateNaiss.setEnabled(true);
		this.labelDateNaiss.setEnabled(true);

		this.componentAdresse.setText(String.valueOf(client.getAdresse()));
		this.componentAdresse.setEnabled(true);
		this.labelAdresse.setEnabled(true);

		this.componentNationalite.setText(String.valueOf(client.getNationalite()));
		this.componentNationalite.setEnabled(true);
		this.labelNationalite.setEnabled(true);

		this.componentTelephone.setText(String.valueOf(client.getTelephone()));
		this.componentTelephone.setEnabled(true);
		this.labelTelephone.setEnabled(true);

		if (client.getTypeCarte() != null)
			this.componentTypeCarte.setSelectedItem(client.getTypeCarte().getValue());
		else
			this.componentTypeCarte.setSelectedIndex(0);
		this.componentTypeCarte.setEnabled(true);
		this.labelTypeCarte.setEnabled(true);

		this.componentNumCarte.setText(String.valueOf(client.getNumCarte()));
		this.componentNumCarte.setEnabled(true);
		this.labelNumCarte.setEnabled(true);

		this.componentProfession.setText(String.valueOf(client.getProfession()));
		this.componentProfession.setEnabled(true);
		this.labelProfession.setEnabled(true);

		this.componentAutreTypeClient.setText(String.valueOf(client.getAutreTypeClient()));
		this.componentAutreTypeClient.setEnabled(true);
		this.labelAutreTypeClient.setEnabled(true);

		this.componentAutreTypeCarte.setText(String.valueOf(client.getAutreTypeCarte()));
		this.componentAutreTypeCarte.setEnabled(true);
		this.labelAutreTypeCarte.setEnabled(true);

		this.componentLieuDeNaiss.setText(String.valueOf(client.getLieuDeNaiss()));
		this.componentLieuDeNaiss.setEnabled(true);
		this.labelLieuDeNaiss.setEnabled(true);

		if (client.getCivilite() != null)
			this.componentCivilite.setSelectedItem(client.getCivilite().getValue());
		else
			this.componentCivilite.setSelectedIndex(0);
		this.componentCivilite.setEnabled(true);
		this.labelCivilite.setEnabled(true);

		
		listItems.setSelectedIndex(dlmListItems.indexOf(client));
	}

	private void generateAndShowPDF(){
//		try{
//			File file = File.createTempFile( "ListeClients", ".pdf");
//			file.deleteOnExit();
//			
//			views.ViewClient.exportToPDF(file);
//			
//			utils.FilesAndLaunchUtils.openPDFFile(file);
//		}
//		catch (Exception e){
//			StringUtils.printDebug(e);
//		}
		
		if (selectedItem == null || !(selectedItem instanceof Client)){
			notifierErreur("Veuillez sélectionner un Client ...");
			return;
		}
		
		Client client = (Client)selectedItem;
		
		controllers.Client.imprimerFicheClient(client);
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

		getInstance("Gestion des Clients");
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
		javax.swing.JFrame frame = new javax.swing.JFrame("Test of access to Table : client");
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