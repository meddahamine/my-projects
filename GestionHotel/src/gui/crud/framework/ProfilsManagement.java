package gui.crud.framework;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import javax.swing.InputMap;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import models.beans.ParametresApplicationUtilisateur;
import models.beans.Photo;
import models.beans.Utilisateur;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import utils.Item;
import utils.StringUtils;
import gui.utils.GUIFieldFactory;
import gui.utils.GUIMessageByOptionPane;
import gui.utils.GUIPanelFactory;
import gui.utils.GUIThemes;
import gui.utils.JInternalFrameManagementModel;
import gui.utils.MySwingComponents;
import appClient.Spool;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

public class ProfilsManagement extends JInternalFrameManagementModel{
	private static final long serialVersionUID = 1L;

	private static ProfilsManagement instance = null;

	private JLabel 		labelPeriodeNotification;
	private JLabel 		labelVisibilityOfNotificationBar;
	private JLabel 		labelVisibilityOfMainToolBar;
	private JLabel 		labelThemesGUI;
	
	private JTextField	tfPeriodeNotification;
	private JComboBox	cbVisibilityOfNotificationBar;
	private JComboBox	cbVisibilityOfMainToolBar;
	private JComboBox	cbThemesGUI;

	public static ProfilsManagement getInstance(String title){
		if (instance == null)
			instance = new ProfilsManagement(title);
		instance.initFields();
		return instance;
	}

	public ProfilsManagement(String title) {
		super(title);
		myInitComponents();
	}

	private void myInitComponents() {
		spTreeItems.setVisible(false);
		splitPaneTreeList.setDividerSize(0);
		spListItems.setVisible(true);
		this.setFrameIcon(app.utils.FrameworkRessources.UTILISATEUR_20_ICON);
		
		this.stpListContent.setDividerSize(0);
		
		labelPeriodeNotification = new JLabel("Durée des notifications des fenêtres internes (en seconds) : ");
		labelVisibilityOfNotificationBar = new JLabel("Afficher la barre de notification : ");
		labelVisibilityOfMainToolBar = new JLabel("Afficher la barre d'outil principale : ");
		labelThemesGUI = new JLabel("Thèmes et Motifs pour l'Interface Graphique");
		
		tfPeriodeNotification = GUIFieldFactory.createNaturalField(2);
		
		String[] ouiNon = {"Oui", "Non"};
		cbVisibilityOfNotificationBar = new MySwingComponents.MyJCombobox(ouiNon);
		cbVisibilityOfMainToolBar = new MySwingComponents.MyJCombobox(ouiNon);
		
		cbThemesGUI = new GUIPanelFactory.JEditedComboBox();
		
		for (Item theme : GUIThemes.getListOfThmes()){
			cbThemesGUI.addItem(theme);
		}
		for (Item theme : GUIThemes.getListOfSubstancesThmes()){
			cbThemesGUI.addItem(theme);
		}
		
		pPhoto.setBorder(javax.swing.BorderFactory.createTitledBorder("Ma Photo : "));

//		stpListContent.setDividerLocation(300);
		
		bAjouter.setToolTipText("Ajouter un profil");
		bSupprimer.setToolTipText("Supprimer le profil sélectionné");
		bExporter.setToolTipText("Exporter la liste des profils vers un fichier Excel");
		bImporter.setToolTipText("Importer des profils à partir d'un fichier Excel");
		
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
					.addComponent(labelVisibilityOfNotificationBar)
					.addComponent(labelVisibilityOfMainToolBar)
					.addComponent(labelThemesGUI)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(tfPeriodeNotification, 50, 50, 50)
					.addComponent(cbVisibilityOfNotificationBar, 130, 130, 130)
					.addComponent(cbVisibilityOfMainToolBar, 130, 130, 130)
					.addComponent(cbThemesGUI, 400, 400, 400)
			)
			.addGap(1, 1, Short.MAX_VALUE)
		);
		layoutPFormulaire.setVerticalGroup(layoutPFormulaire.createSequentialGroup()
			.addGap(5, 5, 5)
			.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelPeriodeNotification)
					.addComponent(tfPeriodeNotification, 25, 25, 25)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelVisibilityOfNotificationBar)
					.addComponent(cbVisibilityOfNotificationBar, 25, 25, 25)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelVisibilityOfMainToolBar)
					.addComponent(cbVisibilityOfMainToolBar, 25, 25, 25)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layoutPFormulaire.createParallelGroup()
					.addComponent(labelThemesGUI)
					.addComponent(cbThemesGUI, 25, 25, 25)
			)
			.addGap(5, 5, 5)
		);
	}

	private void myAllEvents() {
		if (guiNavigator.isVisible()){
			guiNavigator.setTFNumPageDocumentListener(new DocumentListener() {
				public void removeUpdate(DocumentEvent evt) {
				}

				public void insertUpdate(DocumentEvent evt) {
				}

				public void changedUpdate(DocumentEvent evt) {
				}
			});

			guiNavigator.setCBNumberOfItemsActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt){
					guiNavigator.updateNumberItemsInPage();
					int totalProfils = guiNavigator.getTotalNumberOfItems();
					int nbItemsInPage = guiNavigator.getNumberItemsInPage();
					int numberOfPage = totalProfils / nbItemsInPage;
					if (totalProfils % nbItemsInPage > 0)
						numberOfPage++;
					guiNavigator.setNumberOfPages(numberOfPage);
					guiNavigator.setNumeroPage(guiNavigator.getNumPageAsInt());
				}
			});
		}
	}

	protected void tfFilterDocumentUpdated(DocumentEvent evt) {
		
	}

	protected void bAjouterActionPerformed(ActionEvent evt) {
	}

	protected void bFermerActionPerformed(ActionEvent evt) {
		if (dlgLayerFilter.isVisible()){
			dlgLayerFilter.setVisible(false);
			return;
		}
		
		fermer();
	}

	protected void bExporterActionPerformed(ActionEvent evt) {
		fcExportImportXLS.setDialogType(JFileChooser.SAVE_DIALOG | JFileChooser.FILES_ONLY);
		fcExportImportXLS.setDialogTitle("Exporter la liste des profils sous format Excel 2003 (*.xls)");
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
		fcExportImportXLS.setDialogTitle("Importer des profils à partir d'un fichier Excel format 2003 (*.xls)");
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
//		if (selectedItem == null || !(selectedItem instanceof ParametresApplicationUtilisateur)){
//			notifierErreur("Veuillez sélectionner un profil ...");
//			return;
//		}

		Utilisateur user = Spool.getUser();
		ParametresApplicationUtilisateur selectedProfil = user.getParametres();
		
		if (selectedProfil.getId() == null || selectedProfil.getId() == 0){
			selectedProfil.setId(0);

			
			byte[] dataPhoto = getDataPhoto();
			if (dataPhoto != null){
				Photo photo = user.getPhoto();
				if (photo == null)
					photo = new Photo();

				photo.setData(dataPhoto);
				photo.setId((Integer)communication.SocketCommunicator.getInstance().sendQuery(photo));
				user.setIdPhoto(photo.getId());
				user.setPhoto(photo);

				setDataPhoto(null);
			}
			selectedProfil.setId((Integer) communication.SocketCommunicator.getInstance().sendQuery(selectedProfil));
			notifierConfirmation("Profil sauvegardé avec succès");
		}else{
			java.util.List<String> listQueries = new java.util.ArrayList<String>();
			
			int periodeNotification = 5;
			try{
				periodeNotification = Integer.parseInt(tfPeriodeNotification.getText().trim());
			}
			catch (Exception e){
				periodeNotification = 5;
				tfPeriodeNotification.setText("5");
			}
			boolean visibilityOfNotificationBar = cbVisibilityOfNotificationBar.getSelectedIndex() == 0;
			boolean visibilityOfMainToolBar = cbVisibilityOfMainToolBar.getSelectedIndex() == 0;
			
			JInternalFrameManagementModel.setVisibilityNotificationBar(visibilityOfNotificationBar);
			JInternalFrameManagementModel.updateVisibilityNotificationBarOfDesktopPane(gui.MainFrame.getInstanceWithoutCreation().getDesktopPane());
			this.hideNotificationBar();
			
			if (periodeNotification  != (selectedProfil.getPeriodeNotification())){
				selectedProfil.setPeriodeNotification(periodeNotification);
				listQueries.add(models.daos.client.DAOParametresApplicationUtilisateur.getSQLUpdateForPeriodeNotification(selectedProfil));
			}
			if (visibilityOfNotificationBar  != (selectedProfil.isVisibilityOfNotification().booleanValue())){
				selectedProfil.setVisibilityOfNotification(visibilityOfNotificationBar);
				listQueries.add(models.daos.client.DAOParametresApplicationUtilisateur.getSQLUpdateForVisibilityOfNotification(selectedProfil)   );
			}
			if (visibilityOfMainToolBar  != (selectedProfil.isVisibilityOfMainToolBar().booleanValue())){
				selectedProfil.setVisibilityOfMainToolBar(visibilityOfMainToolBar);
				listQueries.add(models.daos.client.DAOParametresApplicationUtilisateur.getSQLUpdateForVisibilityOfMainToolBar(selectedProfil));
			}
			Item item = (Item)cbThemesGUI.getSelectedItem();
			if (! item.getValue().equals(selectedProfil.getLookAndFeel())){
				selectedProfil.setLookAndFeel(item.getValue());
				listQueries.add(models.daos.client.DAOParametresApplicationUtilisateur.getSQLUpdateForLookAndFeel(selectedProfil));
			}
			
			byte[] dataPhoto = getDataPhoto();
			if (dataPhoto != null){
				Photo photo = user.getPhoto();
				if (photo == null)
					photo = new Photo();

				photo.setData(dataPhoto);
				photo.setId((Integer)communication.SocketCommunicator.getInstance().sendQuery(photo));
				if (user.getIdPhoto().intValue() != photo.getId().intValue()){
					user.setIdPhoto(photo.getId());
					listQueries.add(models.daos.client.DAOUtilisateur.getSQLUpdateForIdPhoto(user));
				}
				
				user.setPhoto(photo);
				setDataPhoto(null);
			}
			
			utils.SGBDConfig.UpdateDeleteSQLQueries updateQueries = new utils.SGBDConfig.UpdateDeleteSQLQueries(listQueries);
			communication.SocketCommunicator.getInstance().sendQuery(updateQueries);
			notifierConfirmation("Profil modifié avec succès");
		}
		
//		dlmListItems.set(dlmListItems.indexOf(selectedItem), selectedProfil);
		gui.MainFrame.getInstanceWithoutCreation().updateGUIWithUserParameters();
	}

	protected void listItemsMouseClicked(MouseEvent evt) {
	}

	protected void miDeleteAllActionPerformed(ActionEvent evt) {
	}

	private void exportToFile(File file){
		try{
			WritableWorkbook workbook = Workbook.createWorkbook(file);
			WritableSheet sheet = workbook.createSheet( "Liste des Porfils", 0);

			sheet.getSettings().setDefaultRowHeight(300);
			sheet.getSettings().setDefaultColumnWidth(16);

			sheet.setColumnView(0, 10);
			sheet.setColumnView(1, 20);
			sheet.setColumnView(2, 20);
			sheet.setColumnView(3, 20);
			sheet.setColumnView(4, 20);
			sheet.setColumnView(5, 20);
			sheet.setColumnView(6, 20);
			sheet.setColumnView(7, 20);
			sheet.setColumnView(8, 20);
			sheet.setColumnView(9, 20);
			sheet.setColumnView(10, 20);
			sheet.setColumnView(11, 20);
			sheet.setColumnView(12, 20);
			sheet.setColumnView(13, 20);
			
			WritableFont font1 = new WritableFont(WritableFont.TIMES, 14);
			WritableCellFormat formatEntete = new WritableCellFormat(font1);
			formatEntete.setAlignment(Alignment.CENTRE);
			formatEntete.setVerticalAlignment(VerticalAlignment.CENTRE);

			WritableCellFormat formatHeaderTableStr = new WritableCellFormat(new WritableFont(WritableFont.TIMES, 13));
			formatHeaderTableStr.setAlignment(Alignment.CENTRE);
			formatHeaderTableStr.setVerticalAlignment(VerticalAlignment.CENTRE);
			formatHeaderTableStr.setBorder(Border.ALL, BorderLineStyle.THICK);
			formatHeaderTableStr.setBackground(Colour.GRAY_25);

			WritableFont font2 = new WritableFont(WritableFont.TIMES, 12);
			WritableCellFormat formatContentStr = new WritableCellFormat(font2);
			formatContentStr.setAlignment(Alignment.LEFT);
			formatContentStr.setVerticalAlignment(VerticalAlignment.CENTRE);
			formatContentStr.setBorder(Border.ALL, BorderLineStyle.THICK);

			Label label = new Label(0, 0, "N°", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(1, 0, "Désignation de l'Application", formatHeaderTableStr); sheet.addCell(label);
			
//			int ligneBas = 1;
//			java.util.List<ParametresOrganisme> listParametresOrganismes = ParametresOrganisme.getListInstancesFromServer();
//			for (ParametresOrganisme parametresOrganisme : listParametresOrganismes){
//				int i = listParametresOrganismes.indexOf(parametresOrganisme);
//				label = new Label(0, ligneBas+i, String.valueOf(i), formatContentStr); sheet.addCell(label);
//			}

			label = new Label(POINT_CHECK_INFORMAITON.x, POINT_CHECK_INFORMAITON.y, CHECK_INFORMATION, formatContentStr);
			sheet.addCell(label);

			workbook.write();
			workbook.close();

			utils.FilesAndLaunchUtils.openFile(file);
		}
		catch (Exception e){
			GUIMessageByOptionPane.showErrorMessage("Erreur de génération d'Excel", "Errueur de génération de l'Excel");
			StringUtils.printDebug(e);
		}
	}

	private void importerFromFile(File file){
		try{
			Workbook workbook = Workbook.getWorkbook(file);
			Sheet sheet = workbook.getSheet(0);
			Cell cell =  sheet.getCell(POINT_CHECK_INFORMAITON.x, POINT_CHECK_INFORMAITON.y);
			if (!cell.getContents().equals(CHECK_INFORMATION)){
				GUIMessageByOptionPane.showErrorMessage("Excel non valide", "Votre document Excel n'est pas valide (Probablement non généré par l'Application)");
				return;
			}

//			int ligne = 1;
			java.util.List<String> queries = new java.util.ArrayList<String>();
//			while (true){
//				ligne++;
//				cell = sheet.getCell("A"+ligne);
//				if (cell.getContents().trim().equals(""))
//					break;
//				
//				ParametresOrganisme parametresOrganisme = new ParametresOrganisme(0);
//
//				String value = "";
//				value = sheet.getCell("C"+ligne).getContents().trim();
//				parametresOrganisme.setDesignationOrganisme(value);
//			}
			
			workbook.close();
			utils.SGBDConfig.UpdateDeleteSQLQueries sqlQueries = new utils.SGBDConfig.UpdateDeleteSQLQueries(queries);
			communication.SocketCommunicator.getInstance().sendQuery(sqlQueries);
			initFields();
		}
		catch (Exception e){
			GUIMessageByOptionPane.showErrorMessage("Excel non valide", "Votre document Excel n'est pas valide");
			StringUtils.printDebug(e);
		}
	}

	protected void emptyFields() {
		setPhoto(null, false);
		pPhoto.setEnabled(false);
	}

	protected void initFields() {
		this.pNotification.setVisible(JInternalFrameManagementModel.isVisibilityNotificationBar());
		
		pListButtons.setVisible(false);
		
		selectedItem = Spool.getUser();
		
		fillFormulaireByUserProfil(Spool.getUser());
	}

	protected void fillFormulaireByUserProfil(Utilisateur user) {
		if (user == null){
			emptyFields();
			return;
		}

		ParametresApplicationUtilisateur parameter = user.getParametres();
		
		this.tfPeriodeNotification.setText(String.valueOf(parameter.getPeriodeNotification()));
		this.tfPeriodeNotification.setEnabled(true);
		this.labelPeriodeNotification.setEnabled(true);
		
		this.cbVisibilityOfNotificationBar.setSelectedIndex( parameter.isVisibilityOfNotification() ? 0 : 1 );
		this.cbVisibilityOfNotificationBar.setEnabled(true);
		this.labelVisibilityOfNotificationBar.setEnabled(true);

		this.cbVisibilityOfMainToolBar.setSelectedIndex( parameter.isVisibilityOfMainToolBar() ? 0 : 1 );
		this.cbVisibilityOfMainToolBar.setEnabled(true);
		this.labelVisibilityOfMainToolBar.setEnabled(true);
		
		this.cbThemesGUI.setSelectedIndex(0);
		for (int i=0; i<cbThemesGUI.getItemCount(); i++){
			Item item = (Item)cbThemesGUI.getItemAt(i);
			if (item.getValue().equals(parameter.getLookAndFeel())){
				this.cbThemesGUI.setSelectedIndex(i);
				break;
			}
		}
		this.cbThemesGUI.setEnabled(true);
		this.labelThemesGUI.setEnabled(true);
		
		
		if (user.getPhoto() != null){
			setPhoto(user.getPhoto().dataToFile(), false);
		}
		else{
			setPhoto(null, false);
		}
		pPhoto.setEnabled(true);
	}

	private void generateAndShowPDF(){
		try{
			File file = File.createTempFile( "Liste des Profils", ".pdf");
			file.deleteOnExit();
			
			Document docPDF = new Document(PageSize.A4, 25, 25, 20, 10);
			PdfWriter.getInstance(docPDF,new java.io.FileOutputStream(file));
//			PdfWriter writer = PdfWriter.getInstance(docPDF,new java.io.FileOutputStream(file));
			
//			Font fontHVCA15_IB = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD | Font.ITALIC);
//			Font fontTR10_B = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
//			Font fontTR10_B_WHITE = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
//			fontTR10_B_WHITE.setColor(BaseColor.WHITE);
//			Font fontTR10 = new Font(Font.FontFamily.TIMES_ROMAN, 10);
			
//			int maxItemsAtPage = 50;
//			List<ParametresOrganisme> listParametresOrganismes = ParametresOrganisme.getListInstancesFromServer();
//			int nbPages = (int) (listParametresOrganismes.size() / maxItemsAtPage);
//			if ((nbPages == 0) || (listParametresOrganismes.size() % maxItemsAtPage > 0)){
//				nbPages++;
//			}
//			
//			docPDF.open();
////			PdfContentByte canvas = writer.getDirectContent();
//			
//			for (int numPage=0; numPage<nbPages; numPage++){
//				Paragraph header = new Paragraph("Paramètres de l'Organisme", fontHVCA15_IB);
//				header.setAlignment(Paragraph.ALIGN_RIGHT);
//				header.setSpacingAfter(5);
//				docPDF.add(header);
//				
//				LineSeparator line = new LineSeparator();
//				docPDF.add(line);
//				
//				PdfPCell cell;
//				PdfPTable table;
//				float[] a;
//				float width = PageSize.A4.getWidth() - 100;
//				
//				a = new float[14];
//				a[0] = (float)(width * 0.071428575);
//				a[1] = (float)(width * 0.071428575);
//				a[2] = (float)(width * 0.071428575);
//				a[3] = (float)(width * 0.071428575);
//				a[4] = (float)(width * 0.071428575);
//				a[5] = (float)(width * 0.071428575);
//				a[6] = (float)(width * 0.071428575);
//				a[7] = (float)(width * 0.071428575);
//				a[8] = (float)(width * 0.071428575);
//				a[9] = (float)(width * 0.071428575);
//				a[10] = (float)(width * 0.071428575);
//				a[11] = (float)(width * 0.071428575);
//				a[12] = (float)(width * 0.071428575);
//				a[13] = (float)(width * 0.071428575);
//				table = new PdfPTable(a.length);
//				
//				table.setWidthPercentage (a, PageSize.A4);
//				table.setHorizontalAlignment(Element.ALIGN_CENTER);
//				
//				cell = new PdfPCell(new Paragraph("Désignation de l'Application", fontTR10_B_WHITE));
//				cell.setBackgroundColor(BaseColor.BLACK);
//				cell.setBorderColor(BaseColor.WHITE);
//				cell.setPaddingBottom(5);
//				table.addCell(cell);
//
//				cell = new PdfPCell(new Paragraph("Désignation de l'Organisme", fontTR10_B_WHITE));
//				cell.setBackgroundColor(BaseColor.BLACK);
//				cell.setBorderColor(BaseColor.WHITE);
//				cell.setPaddingBottom(5);
//				table.addCell(cell);
//
//				cell = new PdfPCell(new Paragraph("Raison Social", fontTR10_B_WHITE));
//				cell.setBackgroundColor(BaseColor.BLACK);
//				cell.setBorderColor(BaseColor.WHITE);
//				cell.setPaddingBottom(5);
//				table.addCell(cell);
//
//				cell = new PdfPCell(new Paragraph("Capital Social", fontTR10_B_WHITE));
//				cell.setBackgroundColor(BaseColor.BLACK);
//				cell.setBorderColor(BaseColor.WHITE);
//				cell.setPaddingBottom(5);
//				table.addCell(cell);
//
//				cell = new PdfPCell(new Paragraph("N° de Registre Commercial", fontTR10_B_WHITE));
//				cell.setBackgroundColor(BaseColor.BLACK);
//				cell.setBorderColor(BaseColor.WHITE);
//				cell.setPaddingBottom(5);
//				table.addCell(cell);
//
//				cell = new PdfPCell(new Paragraph("N° du Compte Bancaire", fontTR10_B_WHITE));
//				cell.setBackgroundColor(BaseColor.BLACK);
//				cell.setBorderColor(BaseColor.WHITE);
//				cell.setPaddingBottom(5);
//				table.addCell(cell);
//
//				cell = new PdfPCell(new Paragraph("Identification Fiscale", fontTR10_B_WHITE));
//				cell.setBackgroundColor(BaseColor.BLACK);
//				cell.setBorderColor(BaseColor.WHITE);
//				cell.setPaddingBottom(5);
//				table.addCell(cell);
//
//				cell = new PdfPCell(new Paragraph("N° Article", fontTR10_B_WHITE));
//				cell.setBackgroundColor(BaseColor.BLACK);
//				cell.setBorderColor(BaseColor.WHITE);
//				cell.setPaddingBottom(5);
//				table.addCell(cell);
//
//				cell = new PdfPCell(new Paragraph("Adresse", fontTR10_B_WHITE));
//				cell.setBackgroundColor(BaseColor.BLACK);
//				cell.setBorderColor(BaseColor.WHITE);
//				cell.setPaddingBottom(5);
//				table.addCell(cell);
//
//				cell = new PdfPCell(new Paragraph("Boite Postale", fontTR10_B_WHITE));
//				cell.setBackgroundColor(BaseColor.BLACK);
//				cell.setBorderColor(BaseColor.WHITE);
//				cell.setPaddingBottom(5);
//				table.addCell(cell);
//
//				cell = new PdfPCell(new Paragraph("TEL", fontTR10_B_WHITE));
//				cell.setBackgroundColor(BaseColor.BLACK);
//				cell.setBorderColor(BaseColor.WHITE);
//				cell.setPaddingBottom(5);
//				table.addCell(cell);
//
//				cell = new PdfPCell(new Paragraph("FAX", fontTR10_B_WHITE));
//				cell.setBackgroundColor(BaseColor.BLACK);
//				cell.setBorderColor(BaseColor.WHITE);
//				cell.setPaddingBottom(5);
//				table.addCell(cell);
//
//				cell = new PdfPCell(new Paragraph("Descriptif de l'Organisme", fontTR10_B_WHITE));
//				cell.setBackgroundColor(BaseColor.BLACK);
//				cell.setBorderColor(BaseColor.WHITE);
//				cell.setPaddingBottom(5);
//				table.addCell(cell);
//
//				cell = new PdfPCell(new Paragraph("Logo de l'entreprerise", fontTR10_B_WHITE));
//				cell.setBackgroundColor(BaseColor.BLACK);
//				cell.setBorderColor(BaseColor.WHITE);
//				cell.setPaddingBottom(5);
//				table.addCell(cell);
//
//				
//				fontTR10_B.setColor(BaseColor.BLACK);
//				for (int i=0; i<maxItemsAtPage; i++){
//					int index = i + numPage * maxItemsAtPage;
//					ParametresOrganisme parametresOrganisme = null;
//					if (index < listParametresOrganismes.size())
//						parametresOrganisme = listParametresOrganismes.get(index);					
//
//					cell = new PdfPCell(new Paragraph((parametresOrganisme != null) ? parametresOrganisme.getDesignationOrganisme() : " ", fontTR10));
//					cell.setBorderColor(BaseColor.WHITE);
//					table.addCell(cell);
//
//					cell = new PdfPCell(new Paragraph((parametresOrganisme != null) ? parametresOrganisme.getRaisonSocial() : " ", fontTR10));
//					cell.setBorderColor(BaseColor.WHITE);
//					table.addCell(cell);
//
//					cell = new PdfPCell(new Paragraph((parametresOrganisme != null) ? parametresOrganisme.getCapitalSocial() : " ", fontTR10));
//					cell.setBorderColor(BaseColor.WHITE);
//					table.addCell(cell);
//
//					cell = new PdfPCell(new Paragraph((parametresOrganisme != null) ? parametresOrganisme.getNumRC() : " ", fontTR10));
//					cell.setBorderColor(BaseColor.WHITE);
//					table.addCell(cell);
//
//					cell = new PdfPCell(new Paragraph((parametresOrganisme != null) ? parametresOrganisme.getNumCB() : " ", fontTR10));
//					cell.setBorderColor(BaseColor.WHITE);
//					table.addCell(cell);
//
//					cell = new PdfPCell(new Paragraph((parametresOrganisme != null) ? parametresOrganisme.getIdentificationFiscale() : " ", fontTR10));
//					cell.setBorderColor(BaseColor.WHITE);
//					table.addCell(cell);
//
//					cell = new PdfPCell(new Paragraph((parametresOrganisme != null) ? parametresOrganisme.getNumArticle() : " ", fontTR10));
//					cell.setBorderColor(BaseColor.WHITE);
//					table.addCell(cell);
//
//					cell = new PdfPCell(new Paragraph((parametresOrganisme != null) ? parametresOrganisme.getAdresse() : " ", fontTR10));
//					cell.setBorderColor(BaseColor.WHITE);
//					table.addCell(cell);
//
//					cell = new PdfPCell(new Paragraph((parametresOrganisme != null) ? parametresOrganisme.getBoitePostale() : " ", fontTR10));
//					cell.setBorderColor(BaseColor.WHITE);
//					table.addCell(cell);
//
//					cell = new PdfPCell(new Paragraph((parametresOrganisme != null) ? parametresOrganisme.getNumTel() : " ", fontTR10));
//					cell.setBorderColor(BaseColor.WHITE);
//					table.addCell(cell);
//
//					cell = new PdfPCell(new Paragraph((parametresOrganisme != null) ? parametresOrganisme.getNumFax() : " ", fontTR10));
//					cell.setBorderColor(BaseColor.WHITE);
//					table.addCell(cell);
//
//					cell = new PdfPCell(new Paragraph((parametresOrganisme != null) ? parametresOrganisme.getDescriptif() : " ", fontTR10));
//					cell.setBorderColor(BaseColor.WHITE);
//					table.addCell(cell);
//
//					cell = new PdfPCell(new Paragraph((parametresOrganisme != null) ? parametresOrganisme.getPhoto().toString() : " ", fontTR10));
//					cell.setBorderColor(BaseColor.WHITE);
//					table.addCell(cell);
//
//				}
//				table.setSpacingBefore(5);
//				docPDF.add(table);
//				table.setSpacingAfter(5);
//				
//				docPDF.add(new Paragraph("\n"));
//				docPDF.add(line);
//				
//				String dateTime = (String)communication.SocketCommunicator.getInstance().sendQuery("getDateTimeFromServer");
//				String date = StringUtils.formatDateFromMySQL(dateTime.split(" ")[0]);
//				String time = dateTime.split(" ")[1]; time = time.substring(0, time.lastIndexOf(":"));
//				
//				Chunk petitEspaceChunk = new Chunk("      ");
//				Chunk grandEspaceChunk = new Chunk("                                                                                                                                     ");
//				
//				header = new Paragraph("Date : "+date, fontTR10);
//				header.add(petitEspaceChunk);
//				header.add("Horaire : "+time);
//				header.add(grandEspaceChunk);
//				header.add("Page "+(numPage + 1)+"/"+nbPages);
//				docPDF.add(header);
//			}
			
			docPDF.close();
			
			utils.FilesAndLaunchUtils.openPDFFile(file);
		}
		catch (Exception e){
			StringUtils.printDebug(e);
		}
	}

	public static void createAndShow(JDesktopPane desktop){
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
		
		getInstance("Mon Profil");
		if (!instance.isAdded()){
			Dimension size = desktop.getSize();

			instance.setAdded(true);
			desktop.add(instance);

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

		instance.setVisible(true);
		instance.toFront();
		instance.requestFocus();
	}
}