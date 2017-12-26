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

import models.beans.ParametresOrganisme;
import models.daos.client.DAOParametresOrganisme;

import gui.utils.GUIMessageByOptionPane;
import gui.utils.JInternalFrameManagementModel;

public class ParametresOrganismeManagement extends JInternalFrameManagementModel{
	private static final long serialVersionUID = 1L;

	private static ParametresOrganismeManagement instance = null;

	private JLabel labelDesignationOrganisme;
	private JLabel labelRaisonSocial;
	private JLabel labelCapitalSocial;
	private JLabel labelNumRC;
	private JLabel labelNumCB;
	private JLabel labelIdentificationFiscale;
	private JLabel labelNumArticle;
	private JLabel labelNIS;
	private JLabel labelAdresse;
	private JLabel labelBoitePostale;
	private JLabel labelNumTel;
	private JLabel labelNumFax;
	private JLabel labelEmail;
	private JLabel labelDescriptif;

	private javax.swing.JTextField componentDesignationOrganisme;
	private javax.swing.JTextField componentRaisonSocial;
	private javax.swing.JTextField componentCapitalSocial;
	private javax.swing.JTextField componentNumRC;
	private javax.swing.JTextField componentNumCB;
	private javax.swing.JTextField componentIdentificationFiscale;
	private javax.swing.JTextField componentNumArticle;
	private javax.swing.JTextField componentNIS;
	private javax.swing.JTextField componentAdresse;
	private javax.swing.JTextField componentBoitePostale;
	private javax.swing.JTextField componentNumTel;
	private javax.swing.JTextField componentNumFax;
	private javax.swing.JTextField componentEmail;
	private javax.swing.JScrollPane componentDescriptif;

	public static ParametresOrganismeManagement getInstance(String title){
		if (instance == null)
			instance = new ParametresOrganismeManagement(title);
		instance.initFields();
		return instance;
	}

	public static ParametresOrganismeManagement getInstanceWithoutCreation(){
		return instance;
	}

	public ParametresOrganismeManagement(String title) {
		super(title);
		myInitComponents();
	}

	private void myInitComponents() {
		this.spTreeItems.setVisible(false);
		this.spListItems.setVisible(false);
		this.pListButtons.setVisible(false);
		this.stpListContent.setDividerSize(0);		pPhoto.setVisible(true);
		pNotification.setVisible(false);
		this.setFrameIcon(null);
		
		labelDesignationOrganisme = new JLabel(views.ViewParametresOrganisme.getCaptionForDesignationOrganisme()+" : ");
		labelDesignationOrganisme.setVerticalAlignment(JLabel.CENTER);
		
		labelRaisonSocial = new JLabel(views.ViewParametresOrganisme.getCaptionForRaisonSocial()+" : ");
		labelRaisonSocial.setVerticalAlignment(JLabel.CENTER);
		
		labelCapitalSocial = new JLabel(views.ViewParametresOrganisme.getCaptionForCapitalSocial()+" : ");
		labelCapitalSocial.setVerticalAlignment(JLabel.CENTER);
		
		labelNumRC = new JLabel(views.ViewParametresOrganisme.getCaptionForNumRC()+" : ");
		labelNumRC.setVerticalAlignment(JLabel.CENTER);
		
		labelNumCB = new JLabel(views.ViewParametresOrganisme.getCaptionForNumCB()+" : ");
		labelNumCB.setVerticalAlignment(JLabel.CENTER);
		
		labelIdentificationFiscale = new JLabel(views.ViewParametresOrganisme.getCaptionForIdentificationFiscale()+" : ");
		labelIdentificationFiscale.setVerticalAlignment(JLabel.CENTER);
		
		labelNumArticle = new JLabel(views.ViewParametresOrganisme.getCaptionForNumArticle()+" : ");
		labelNumArticle.setVerticalAlignment(JLabel.CENTER);
		
		labelNIS = new JLabel(views.ViewParametresOrganisme.getCaptionForNIS()+" : ");
		labelNIS.setVerticalAlignment(JLabel.CENTER);
		
		labelAdresse = new JLabel(views.ViewParametresOrganisme.getCaptionForAdresse()+" : ");
		labelAdresse.setVerticalAlignment(JLabel.CENTER);
		
		labelBoitePostale = new JLabel(views.ViewParametresOrganisme.getCaptionForBoitePostale()+" : ");
		labelBoitePostale.setVerticalAlignment(JLabel.CENTER);
		
		labelNumTel = new JLabel(views.ViewParametresOrganisme.getCaptionForNumTel()+" : ");
		labelNumTel.setVerticalAlignment(JLabel.CENTER);
		
		labelNumFax = new JLabel(views.ViewParametresOrganisme.getCaptionForNumFax()+" : ");
		labelNumFax.setVerticalAlignment(JLabel.CENTER);
		
		labelEmail = new JLabel(views.ViewParametresOrganisme.getCaptionForEmail()+" : ");
		labelEmail.setVerticalAlignment(JLabel.CENTER);
		
		labelDescriptif = new JLabel(views.ViewParametresOrganisme.getCaptionForDescriptif()+" : ");
		labelDescriptif.setVerticalAlignment(JLabel.CENTER);
		

		componentDesignationOrganisme = views.ViewParametresOrganisme.getViewForDesignationOrganisme();
		componentRaisonSocial = views.ViewParametresOrganisme.getViewForRaisonSocial();
		componentCapitalSocial = views.ViewParametresOrganisme.getViewForCapitalSocial();
		componentNumRC = views.ViewParametresOrganisme.getViewForNumRC();
		componentNumCB = views.ViewParametresOrganisme.getViewForNumCB();
		componentIdentificationFiscale = views.ViewParametresOrganisme.getViewForIdentificationFiscale();
		componentNumArticle = views.ViewParametresOrganisme.getViewForNumArticle();
		componentNIS = views.ViewParametresOrganisme.getViewForNIS();
		componentAdresse = views.ViewParametresOrganisme.getViewForAdresse();
		componentBoitePostale = views.ViewParametresOrganisme.getViewForBoitePostale();
		componentNumTel = views.ViewParametresOrganisme.getViewForNumTel();
		componentNumFax = views.ViewParametresOrganisme.getViewForNumFax();
		componentEmail = views.ViewParametresOrganisme.getViewForEmail();
		componentDescriptif = views.ViewParametresOrganisme.getViewForDescriptif();
		pPhoto.setBorder(javax.swing.BorderFactory.createTitledBorder("Logo de l'entreprerise : "));

//		stpListContent.setDividerLocation(300);
		
		bAjouter.setToolTipText("Ajouter un Paramètres de l'Organisme");
		bSupprimer.setToolTipText("Supprimer le Paramètres de l'Organisme sélectionné");
		bExporter.setToolTipText("Exporter la liste des parametresOrganismes vers un fichier Excel");
		bImporter.setToolTipText("Importer des parametresOrganismes à partir d'un fichier Excel");
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
				.addComponent(labelDesignationOrganisme)
				.addComponent(labelRaisonSocial)
				.addComponent(labelCapitalSocial)
				.addComponent(labelNumRC)
				.addComponent(labelNumCB)
				.addComponent(labelIdentificationFiscale)
				.addComponent(labelNumArticle)
				.addComponent(labelNIS)
				.addComponent(labelAdresse)
				.addComponent(labelBoitePostale)
				.addComponent(labelNumTel)
				.addComponent(labelNumFax)
				.addComponent(labelEmail)
				.addComponent(labelDescriptif)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layoutPFormulaire.createParallelGroup()
				.addComponent(componentDesignationOrganisme, 300, 300, 300)
				.addComponent(componentRaisonSocial, 300, 300, 300)
				.addComponent(componentCapitalSocial, 300, 300, 300)
				.addComponent(componentNumRC, 300, 300, 300)
				.addComponent(componentNumCB, 300, 300, 300)
				.addComponent(componentIdentificationFiscale, 300, 300, 300)
				.addComponent(componentNumArticle, 300, 300, 300)
				.addComponent(componentNIS, 300, 300, 300)
				.addComponent(componentAdresse, 300, 300, 300)
				.addComponent(componentBoitePostale, 300, 300, 300)
				.addComponent(componentNumTel, 300, 300, 300)
				.addComponent(componentNumFax, 300, 300, 300)
				.addComponent(componentEmail, 300, 300, 300)
				.addComponent(componentDescriptif, 300, 300, 300)
			)
			.addGap(1, 1, Short.MAX_VALUE)
		);
		layoutPFormulaire.setVerticalGroup(layoutPFormulaire.createSequentialGroup()
				.addGap(10, 10, 10)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelDesignationOrganisme, 25, 25, 25)
					.addComponent(componentDesignationOrganisme, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelRaisonSocial, 25, 25, 25)
					.addComponent(componentRaisonSocial, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelCapitalSocial, 25, 25, 25)
					.addComponent(componentCapitalSocial, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelNumRC, 25, 25, 25)
					.addComponent(componentNumRC, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelNumCB, 25, 25, 25)
					.addComponent(componentNumCB, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelIdentificationFiscale, 25, 25, 25)
					.addComponent(componentIdentificationFiscale, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelNumArticle, 25, 25, 25)
					.addComponent(componentNumArticle, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelNIS, 25, 25, 25)
					.addComponent(componentNIS, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelAdresse, 25, 25, 25)
					.addComponent(componentAdresse, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelBoitePostale, 25, 25, 25)
					.addComponent(componentBoitePostale, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelNumTel, 25, 25, 25)
					.addComponent(componentNumTel, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelNumFax, 25, 25, 25)
					.addComponent(componentNumFax, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelEmail, 25, 25, 25)
					.addComponent(componentEmail, 25, 25, 25)
				)
				.addGap(5, 5, 5)
				.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelDescriptif, 25, 25, 25)
					.addComponent(componentDescriptif, 25, 25, 25)
				)
				.addGap(5, 5, 5)
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
					updateListParametresOrganismes();
				}
			});

			guiNavigator.setCBNumberOfItemsActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt){
					guiNavigator.updateNumberItemsInPage();
					int totalParametresOrganismes = guiNavigator.getTotalNumberOfItems();
					int nbItemsInPage = guiNavigator.getNumberItemsInPage();
					int numberOfPage = totalParametresOrganismes / nbItemsInPage;
					if (totalParametresOrganismes % nbItemsInPage > 0)
						numberOfPage++;
					guiNavigator.setNumberOfPages(numberOfPage);
					guiNavigator.setNumeroPage(guiNavigator.getNumPageAsInt());
					
					updateListParametresOrganismes(true);
				}
			});
		}
		
		pPhoto.setSupprimerActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				supprimerPhoto();
			}
		});
	}

	private void supprimerPhoto() {
		if (selectedItem == null || !(selectedItem instanceof ParametresOrganisme)){
			return;
		}
		
		ParametresOrganisme parametresOrganisme = (ParametresOrganisme)selectedItem;
		if (parametresOrganisme.getPhoto() != null){
			if (!GUIMessageByOptionPane.showQuestionMessage(this, "Supprimer la photo", "Voulez-vous supprimer la photo de le(la/l') Paramètres de l'Organisme ?")){;
				return;
			}
			
			controllers.ParametresOrganisme.deletePhoto(parametresOrganisme);
			
			pPhoto.setPhoto(null, false);
		}
	}

	protected void tfFilterDocumentUpdated(DocumentEvent evt) {
		updateListParametresOrganismes(true);
	}

	protected void bAjouterActionPerformed(ActionEvent evt) {
		if (selectedItem != null){
			if ((((ParametresOrganisme)selectedItem)).getId() == null || (((ParametresOrganisme)selectedItem)).getId() == 0){
				notifierErreur("Veuillez valider le parametresOrganisme que vous venez d'ajouter ...");
				return;
			}
		}

		ParametresOrganisme parametresOrganisme = new ParametresOrganisme();

		selectedItem = parametresOrganisme;
		dlmListItems.addElement(parametresOrganisme);
		listItems.setSelectedValue(selectedItem, true);
		fillFormulaireByParametresOrganisme(parametresOrganisme);
	}

	protected void bFermerActionPerformed(ActionEvent evt) {
		if (dlgLayerFilter.isVisible()){
			dlgLayerFilter.setVisible(false);
			return;
		}

		if (selectedItem != null){
			if ((((ParametresOrganisme)selectedItem)).getId() == null || (((ParametresOrganisme)selectedItem)).getId() == 0){
				if (GUIMessageByOptionPane.showQuestionMessage("Un parametresOrganisme non ajouté", "Voulez-vous quitter sans valider cet parametresOrganisme ?"))
					fermer();
				else
					return;
			}
		}
		fermer();
	}

	protected void bExporterActionPerformed(ActionEvent evt) {
		fcExportImportXLS.setDialogType(JFileChooser.SAVE_DIALOG | JFileChooser.FILES_ONLY);
		fcExportImportXLS.setDialogTitle("Exporter la liste des parametresOrganismes sous format Excel 2003 (*.xls)");
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
		fcExportImportXLS.setDialogTitle("Importer des parametresOrganismes à partir d'un fichier Excel format 2003 (*.xls)");
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
		if (selectedItem == null || !(selectedItem instanceof ParametresOrganisme)){
			notifierErreur("Veuillez sélectionner un parametresOrganisme ...");
			return;
		}

		if (! GUIMessageByOptionPane.showQuestionMessage("Supprimer un parametresOrganisme", "Êtes-vous sûr de la suppression de cet parametresOrganisme ?")){
			return;
		}

		int index = listItems.getSelectedIndex();
		
		ParametresOrganisme parametresOrganisme = (ParametresOrganisme)selectedItem;
		if (parametresOrganisme.getId() != null || parametresOrganisme.getId() > 0){
			DAOParametresOrganisme.delete(parametresOrganisme);
		}

		dlmListItems.remove(index);
		
		selectedItem = null;
		fillFormulaireByParametresOrganisme(null);
	}

	protected void bValiderActionPerformed(ActionEvent evt) {
		if (selectedItem == null || !(selectedItem instanceof ParametresOrganisme)){
			notifierErreur("Veuillez sélectionner un ParametresOrganisme ...");
			return;
		}

		if (componentDesignationOrganisme.getText().trim().equals("") || componentRaisonSocial.getText().trim().equals("") || componentCapitalSocial.getText().trim().equals("") || componentNumRC.getText().trim().equals("") || componentNumCB.getText().trim().equals("") || componentIdentificationFiscale.getText().trim().equals("") || componentNumArticle.getText().trim().equals("") || componentNIS.getText().trim().equals("") || componentAdresse.getText().trim().equals("") || componentBoitePostale.getText().trim().equals("") || componentNumTel.getText().trim().equals("") || componentNumFax.getText().trim().equals("") || componentEmail.getText().trim().equals("") || ((javax.swing.JTextArea)componentDescriptif.getViewport().getComponent(0)).getText().trim().equals("")){
			notifierErreur("Veuillez remplir tous les champs, SVP");
			return;
		}

		ParametresOrganisme selectedParametresOrganisme = (ParametresOrganisme) selectedItem;

		if (selectedParametresOrganisme.getId() == null || selectedParametresOrganisme.getId() == 0){
			selectedParametresOrganisme.setId(0);

			selectedParametresOrganisme.setDesignationOrganisme(componentDesignationOrganisme.getText().trim());
			selectedParametresOrganisme.setRaisonSocial(componentRaisonSocial.getText().trim());
			selectedParametresOrganisme.setCapitalSocial(componentCapitalSocial.getText().trim());
			selectedParametresOrganisme.setNumRC(componentNumRC.getText().trim());
			selectedParametresOrganisme.setNumCB(componentNumCB.getText().trim());
			selectedParametresOrganisme.setIdentificationFiscale(componentIdentificationFiscale.getText().trim());
			selectedParametresOrganisme.setNumArticle(componentNumArticle.getText().trim());
			selectedParametresOrganisme.setNIS(componentNIS.getText().trim());
			selectedParametresOrganisme.setAdresse(componentAdresse.getText().trim());
			selectedParametresOrganisme.setBoitePostale(componentBoitePostale.getText().trim());
			selectedParametresOrganisme.setNumTel(componentNumTel.getText().trim());
			selectedParametresOrganisme.setNumFax(componentNumFax.getText().trim());
			selectedParametresOrganisme.setEmail(componentEmail.getText().trim());
			selectedParametresOrganisme.setDescriptif(((javax.swing.JTextArea)componentDescriptif.getViewport().getComponent(0)).getText());
			
			byte[] dataPhoto = getDataPhoto();
			if (dataPhoto != null){
				models.beans.Photo photo = selectedParametresOrganisme.getPhoto();
				if (photo == null)
					photo = new models.beans.Photo();

				photo.setData(dataPhoto);
				models.daos.client.DAOPhoto.write(photo);
				selectedParametresOrganisme.setPhoto(photo);

				setDataPhoto(null);
			}
			selectedParametresOrganisme.setId((Integer) communication.SocketCommunicator.getInstance().sendQuery(selectedParametresOrganisme));
			notifierConfirmation("Paramètres de l'Organisme sauvegardé avec succès");
		}else{
			utils.SGBDConfig.InsertUpdateSQLQueries listQueries = new utils.SGBDConfig.InsertUpdateSQLQueries((utils.SGBDConfig.InsertUpdateSQLQueries.PreparedStatement[])null);
			if (! componentDesignationOrganisme.getText().trim().equals(selectedParametresOrganisme.getDesignationOrganisme())){
				selectedParametresOrganisme.setDesignationOrganisme(componentDesignationOrganisme.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOParametresOrganisme.getSQLUpdateForDesignationOrganismeByPreparedStatement(selectedParametresOrganisme));
			}
			if (! componentRaisonSocial.getText().trim().equals(selectedParametresOrganisme.getRaisonSocial())){
				selectedParametresOrganisme.setRaisonSocial(componentRaisonSocial.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOParametresOrganisme.getSQLUpdateForRaisonSocialByPreparedStatement(selectedParametresOrganisme));
			}
			if (! componentCapitalSocial.getText().trim().equals(selectedParametresOrganisme.getCapitalSocial())){
				selectedParametresOrganisme.setCapitalSocial(componentCapitalSocial.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOParametresOrganisme.getSQLUpdateForCapitalSocialByPreparedStatement(selectedParametresOrganisme));
			}
			if (! componentNumRC.getText().trim().equals(selectedParametresOrganisme.getNumRC())){
				selectedParametresOrganisme.setNumRC(componentNumRC.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOParametresOrganisme.getSQLUpdateForNumRCByPreparedStatement(selectedParametresOrganisme));
			}
			if (! componentNumCB.getText().trim().equals(selectedParametresOrganisme.getNumCB())){
				selectedParametresOrganisme.setNumCB(componentNumCB.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOParametresOrganisme.getSQLUpdateForNumCBByPreparedStatement(selectedParametresOrganisme));
			}
			if (! componentIdentificationFiscale.getText().trim().equals(selectedParametresOrganisme.getIdentificationFiscale())){
				selectedParametresOrganisme.setIdentificationFiscale(componentIdentificationFiscale.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOParametresOrganisme.getSQLUpdateForIdentificationFiscaleByPreparedStatement(selectedParametresOrganisme));
			}
			if (! componentNumArticle.getText().trim().equals(selectedParametresOrganisme.getNumArticle())){
				selectedParametresOrganisme.setNumArticle(componentNumArticle.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOParametresOrganisme.getSQLUpdateForNumArticleByPreparedStatement(selectedParametresOrganisme));
			}
			if (! componentNIS.getText().trim().equals(selectedParametresOrganisme.getNIS())){
				selectedParametresOrganisme.setNIS(componentNIS.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOParametresOrganisme.getSQLUpdateForNISByPreparedStatement(selectedParametresOrganisme));
			}
			if (! componentAdresse.getText().trim().equals(selectedParametresOrganisme.getAdresse())){
				selectedParametresOrganisme.setAdresse(componentAdresse.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOParametresOrganisme.getSQLUpdateForAdresseByPreparedStatement(selectedParametresOrganisme));
			}
			if (! componentBoitePostale.getText().trim().equals(selectedParametresOrganisme.getBoitePostale())){
				selectedParametresOrganisme.setBoitePostale(componentBoitePostale.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOParametresOrganisme.getSQLUpdateForBoitePostaleByPreparedStatement(selectedParametresOrganisme));
			}
			if (! componentNumTel.getText().trim().equals(selectedParametresOrganisme.getNumTel())){
				selectedParametresOrganisme.setNumTel(componentNumTel.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOParametresOrganisme.getSQLUpdateForNumTelByPreparedStatement(selectedParametresOrganisme));
			}
			if (! componentNumFax.getText().trim().equals(selectedParametresOrganisme.getNumFax())){
				selectedParametresOrganisme.setNumFax(componentNumFax.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOParametresOrganisme.getSQLUpdateForNumFaxByPreparedStatement(selectedParametresOrganisme));
			}
			if (! componentEmail.getText().trim().equals(selectedParametresOrganisme.getEmail())){
				selectedParametresOrganisme.setEmail(componentEmail.getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOParametresOrganisme.getSQLUpdateForEmailByPreparedStatement(selectedParametresOrganisme));
			}
			if (! ((javax.swing.JTextArea)componentDescriptif.getViewport().getComponent(0)).getText().trim().equals(selectedParametresOrganisme.getDescriptif())){
				selectedParametresOrganisme.setDescriptif(((javax.swing.JTextArea)componentDescriptif.getViewport().getComponent(0)).getText().trim());
				listQueries.addInsertUpdateSQLQueries(DAOParametresOrganisme.getSQLUpdateForDescriptifByPreparedStatement(selectedParametresOrganisme));
			}
			byte[] dataPhoto = getDataPhoto();
			if (dataPhoto != null){
				models.beans.Photo photo = selectedParametresOrganisme.getPhoto();
				if (photo == null)
					photo = new models.beans.Photo();

				photo.setData(dataPhoto);
				photo.setId((Integer)communication.SocketCommunicator.getInstance().sendQuery(photo));
				if (selectedParametresOrganisme.getIdPhoto().intValue() != photo.getId().intValue()){
					selectedParametresOrganisme.setIdPhoto(photo.getId());
					listQueries.addInsertUpdateSQLQueries(DAOParametresOrganisme.getSQLUpdateForIdPhotoByPreparedStatement(selectedParametresOrganisme));
				}
				
				selectedParametresOrganisme.setPhoto(photo);
				setDataPhoto(null);
			}
			
			communication.SocketCommunicator.getInstance().sendQuery(listQueries);
			notifierConfirmation("ParametresOrganisme modifié avec succès");
		}
		
		dlmListItems.set(dlmListItems.indexOf(selectedItem), selectedParametresOrganisme);
	}

	protected void listItemsMouseClicked(MouseEvent evt) {
		selectedItem = listItems.getSelectedValue();
		if (selectedItem == null){
			fillFormulaireByParametresOrganisme(null);
			return;
		}

		fillFormulaireByParametresOrganisme((ParametresOrganisme)selectedItem);
	}

	protected void miDeleteAllActionPerformed(ActionEvent evt) {
		if (GUIMessageByOptionPane.showQuestionMessage("Supprimer Tous ?", "Voulez-vous vraiment supprimer tous les Paramètres de l'Organisme ?")){
			DAOParametresOrganisme.deleteAll();
			fillFormulaireByParametresOrganisme(null);
		}
	}

	private void exportToExcelFile(File file){
		try{
			views.ViewParametresOrganisme.exportToExcel(file);
		}
		catch (Exception e){
			GUIMessageByOptionPane.showErrorMessage("Erreur de génération d'Excel", "Errueur de génération de l'Excel");
			StringUtils.printDebug(e);
		}
	}

	private void importFromExcelFile(File file){
		views.ViewParametresOrganisme.importFromExcel(file);
	}

	protected void emptyFields() {
		this.componentDesignationOrganisme.setText("");
		this.componentDesignationOrganisme.setEnabled(false);
		this.labelDesignationOrganisme.setEnabled(false);

		this.componentRaisonSocial.setText("");
		this.componentRaisonSocial.setEnabled(false);
		this.labelRaisonSocial.setEnabled(false);

		this.componentCapitalSocial.setText("");
		this.componentCapitalSocial.setEnabled(false);
		this.labelCapitalSocial.setEnabled(false);

		this.componentNumRC.setText("");
		this.componentNumRC.setEnabled(false);
		this.labelNumRC.setEnabled(false);

		this.componentNumCB.setText("");
		this.componentNumCB.setEnabled(false);
		this.labelNumCB.setEnabled(false);

		this.componentIdentificationFiscale.setText("");
		this.componentIdentificationFiscale.setEnabled(false);
		this.labelIdentificationFiscale.setEnabled(false);

		this.componentNumArticle.setText("");
		this.componentNumArticle.setEnabled(false);
		this.labelNumArticle.setEnabled(false);

		this.componentNIS.setText("");
		this.componentNIS.setEnabled(false);
		this.labelNIS.setEnabled(false);

		this.componentAdresse.setText("");
		this.componentAdresse.setEnabled(false);
		this.labelAdresse.setEnabled(false);

		this.componentBoitePostale.setText("");
		this.componentBoitePostale.setEnabled(false);
		this.labelBoitePostale.setEnabled(false);

		this.componentNumTel.setText("");
		this.componentNumTel.setEnabled(false);
		this.labelNumTel.setEnabled(false);

		this.componentNumFax.setText("");
		this.componentNumFax.setEnabled(false);
		this.labelNumFax.setEnabled(false);

		this.componentEmail.setText("");
		this.componentEmail.setEnabled(false);
		this.labelEmail.setEnabled(false);

		((javax.swing.JTextArea)this.componentDescriptif.getViewport().getComponent(0)).setText("");
		((javax.swing.JTextArea)this.componentDescriptif.getViewport().getComponent(0)).setEnabled(false);
		this.labelDescriptif.setEnabled(false);

		setPhoto(null, false);
		pPhoto.setEnabled(false);
	}

	protected void initFields() {
		this.pNotification.setVisible(JInternalFrameManagementModel.isVisibilityNotificationBar());

		selectedItem = null;

		setPhoto(null, false);

		if (guiNavigator.isVisible()){
			guiNavigator.updateNumberItemsInPage();
			int totalParametresOrganismes = DAOParametresOrganisme.getCountInTable();
			int nbItemsInPage = guiNavigator.getNumberItemsInPage();
			int numberOfPage = totalParametresOrganismes / nbItemsInPage;
			if (totalParametresOrganismes % nbItemsInPage > 0)
				numberOfPage++;
			guiNavigator.setTotalNumberOfItems(totalParametresOrganismes);
			guiNavigator.setNumberOfPages(numberOfPage);
			guiNavigator.setNumeroPage(1);
		}

		
		selectedItem = models.daos.client.DAOParametresOrganisme.getParametresOrganisme(1);
		fillFormulaireByParametresOrganisme((ParametresOrganisme)selectedItem);
	}

	private synchronized void updateListParametresOrganismes(){
		updateListParametresOrganismes(false);
	}

	private synchronized void updateListParametresOrganismes(boolean filtering){
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
				int totalParametresOrganismes = DAOParametresOrganisme.getCountInTable(condition);
				int nbItemsInPage = guiNavigator.getNumberItemsInPage();
				int numberOfPage = totalParametresOrganismes / nbItemsInPage;
				if (totalParametresOrganismes % nbItemsInPage > 0)
					numberOfPage++;
				guiNavigator.setTotalNumberOfItems(totalParametresOrganismes);
				guiNavigator.setNumberOfPages(numberOfPage);
				guiNavigator.setNumeroPage(1);
			}
			if (guiNavigator.getFirst() < 0)
				return;
		}
		
		dlmListItems.clear();
		List<ParametresOrganisme> listParametresOrganismes = DAOParametresOrganisme.getListInstances(condition);
		for (ParametresOrganisme parametresOrganisme : listParametresOrganismes){
			dlmListItems.addElement(parametresOrganisme);
		}
	}

	protected void fillFormulaireByParametresOrganisme(ParametresOrganisme parametresOrganisme) {
//		pFormulaire.setVisible(parametresOrganisme != null);
		if (parametresOrganisme == null){
			emptyFields();
			return;
		}

		this.componentDesignationOrganisme.setText(String.valueOf(parametresOrganisme.getDesignationOrganisme()));
		this.componentDesignationOrganisme.setEnabled(true);
		this.labelDesignationOrganisme.setEnabled(true);

		this.componentRaisonSocial.setText(String.valueOf(parametresOrganisme.getRaisonSocial()));
		this.componentRaisonSocial.setEnabled(true);
		this.labelRaisonSocial.setEnabled(true);

		this.componentCapitalSocial.setText(String.valueOf(parametresOrganisme.getCapitalSocial()));
		this.componentCapitalSocial.setEnabled(true);
		this.labelCapitalSocial.setEnabled(true);

		this.componentNumRC.setText(String.valueOf(parametresOrganisme.getNumRC()));
		this.componentNumRC.setEnabled(true);
		this.labelNumRC.setEnabled(true);

		this.componentNumCB.setText(String.valueOf(parametresOrganisme.getNumCB()));
		this.componentNumCB.setEnabled(true);
		this.labelNumCB.setEnabled(true);

		this.componentIdentificationFiscale.setText(String.valueOf(parametresOrganisme.getIdentificationFiscale()));
		this.componentIdentificationFiscale.setEnabled(true);
		this.labelIdentificationFiscale.setEnabled(true);

		this.componentNumArticle.setText(String.valueOf(parametresOrganisme.getNumArticle()));
		this.componentNumArticle.setEnabled(true);
		this.labelNumArticle.setEnabled(true);

		this.componentNIS.setText(String.valueOf(parametresOrganisme.getNIS()));
		this.componentNIS.setEnabled(true);
		this.labelNIS.setEnabled(true);

		this.componentAdresse.setText(String.valueOf(parametresOrganisme.getAdresse()));
		this.componentAdresse.setEnabled(true);
		this.labelAdresse.setEnabled(true);

		this.componentBoitePostale.setText(String.valueOf(parametresOrganisme.getBoitePostale()));
		this.componentBoitePostale.setEnabled(true);
		this.labelBoitePostale.setEnabled(true);

		this.componentNumTel.setText(String.valueOf(parametresOrganisme.getNumTel()));
		this.componentNumTel.setEnabled(true);
		this.labelNumTel.setEnabled(true);

		this.componentNumFax.setText(String.valueOf(parametresOrganisme.getNumFax()));
		this.componentNumFax.setEnabled(true);
		this.labelNumFax.setEnabled(true);

		this.componentEmail.setText(String.valueOf(parametresOrganisme.getEmail()));
		this.componentEmail.setEnabled(true);
		this.labelEmail.setEnabled(true);

		((javax.swing.JTextArea)this.componentDescriptif.getViewport().getComponent(0)).setText(String.valueOf(parametresOrganisme.getDescriptif()));
		((javax.swing.JTextArea)this.componentDescriptif.getViewport().getComponent(0)).setEnabled(true);
		this.labelDescriptif.setEnabled(true);

		if (parametresOrganisme.getPhoto() != null){
			setPhoto(parametresOrganisme.getPhoto().dataToFile(), false);
		}
		else{
			setPhoto(null, false);
		}
		pPhoto.setEnabled(true);
		
		listItems.setSelectedIndex(dlmListItems.indexOf(parametresOrganisme));
	}

	private void generateAndShowPDF(){
		try{
			File file = File.createTempFile( "ListeParametresOrganismes", ".pdf");
			file.deleteOnExit();
			
			views.ViewParametresOrganisme.exportToPDF(file);
			
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

		getInstance("Gestion des Paramètres de l'Organisme ...");
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
		javax.swing.JFrame frame = new javax.swing.JFrame("Test of access to Table : parametresOrganisme");
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