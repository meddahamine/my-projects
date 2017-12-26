package views;

import java.io.File;

import models.daos.client.DAOParametresApplication;
import models.beans.ParametresApplication;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
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
/**
 * @version 0.1
 * @author OUZEGGANE Redouane
 * 	<br/><br/><i>Description : </i>
 *	<br/>This class represents the ... 
 *	<br/>It contains methods for handling and processes in the business layer
 */

public abstract class ViewParametresApplication {
	
	//private static models.beans.ParametresApplicationUtilisateur userParameters = appClient.Spool.getUser().getParametresApplicationUtilisateur();
	private static models.beans.ParametresApplicationUtilisateur userParameters = appClient.Spool.getUser().getParametres();

	public static javax.swing.JTextField getViewForId(){
		return gui.utils.GUIFieldFactory.createNaturalField(11);
	}

	public static String getCaptionForId(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("", userParameters.getLang().getCodeLang());
		}

		return "";
	}

	public static javax.swing.JTextField getViewForDesignation(){
		return gui.utils.GUIFieldFactory.createSimpleTextField(200);
	}

	public static String getCaptionForDesignation(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("Désignation de l'Application", userParameters.getLang().getCodeLang());
		}

		return "Désignation de l'Application";
	}

	public static javax.swing.JScrollPane getViewForDescription(){
		return gui.utils.GUIFieldFactory.createTextAreaWithScroll();
	}

	public static String getCaptionForDescription(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("Description de l'Application", userParameters.getLang().getCodeLang());
		}

		return "Description de l'Application";
	}

	public static javax.swing.JComboBox getViewForPhoto(){
		java.util.List<models.beans.Photo> list = models.daos.client.DAOPhoto.getListInstances();
		javax.swing.JComboBox view = new gui.utils.GUIPanelFactory.JEditedComboBox(list.toArray());
		view.insertItemAt("Choisir Photo", 0);
		return view;
	}

	public static String getCaptionForPhoto(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("Icone de l'Application", userParameters.getLang().getCodeLang());
		}

		return "Icone de l'Application";
	}

	public static java.util.Vector<Object> getTableHeader(boolean withNumero){
		java.util.Vector<Object> header = new java.util.Vector<Object>();
		
		if (withNumero)
			header.add("N°");
		header.add("Désignation de l'Application");
		header.add("Description de l'Application");
		header.add("Icone de l'Application");
		
		return header;
	}

	public static java.util.Vector<Object> getTableRow(models.beans.ParametresApplication parametresApplication, int numOrder){
		java.util.Vector<Object> row = new java.util.Vector<Object>();
		
		if (numOrder > 0)
			row.add(numOrder);
		
		row.add(parametresApplication.getDesignation());
		row.add(parametresApplication.getDescription());
		row.add(parametresApplication.getPhoto());
		
		row.add(parametresApplication);
		
		return row;
	}

	public static java.util.Vector<java.util.Vector<Object>> getData(java.util.List<models.beans.ParametresApplication> list, boolean withOrder){
		return getData(list, withOrder, 0);
	}

	public static java.util.Vector<java.util.Vector<Object>> getData(java.util.List<models.beans.ParametresApplication> list, boolean withOrder, int numRowBase){
		java.util.Vector<java.util.Vector<Object>> data = new java.util.Vector<java.util.Vector<Object>>();
		if (list == null)
			return data;
		
		int numRow = numRowBase;
		for (models.beans.ParametresApplication parametresApplication : list){
			if (withOrder)
				numRow++;
			
			data.add(getTableRow(parametresApplication , numRow));
		}
		
		return data;
	}

	public static void exportToExcel(File file){
		exportToExcel(file, DAOParametresApplication.getListInstances());
	}

	public static void exportToExcel(File file, java.util.List<ParametresApplication> list){
		try{
			WritableWorkbook workbook = Workbook.createWorkbook(file);
			WritableSheet sheet = workbook.createSheet( "Liste des ViewParametresApplications", 0);

			sheet.getSettings().setDefaultRowHeight(300);
			sheet.getSettings().setDefaultColumnWidth(16);

			sheet.setColumnView(0, 10);
			sheet.setColumnView(1, 20);
			sheet.setColumnView(2, 20);
			
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
			label = new Label(2, 0, "Description de l'Application", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(3, 0, "Icone de l'Application", formatHeaderTableStr); sheet.addCell(label);
			
			int ligneBas = 1;
			for (ParametresApplication parametresApplication : list){
				int i = list.indexOf(parametresApplication);
				label = new Label(0, ligneBas+i, String.valueOf(i), formatContentStr); sheet.addCell(label);
				label = new Label(1, ligneBas+i, parametresApplication.getDesignation().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(2, ligneBas+i, parametresApplication.getDescription().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(3, ligneBas+i, parametresApplication.getIdPhoto().toString(), formatHeaderTableStr); sheet.addCell(label);
			}

			label = new Label(gui.utils.JInternalFrameManagementModel.POINT_CHECK_INFORMAITON.x, gui.utils.JInternalFrameManagementModel.POINT_CHECK_INFORMAITON.y, gui.utils.JInternalFrameManagementModel.CHECK_INFORMATION, formatContentStr);
			sheet.addCell(label);

			workbook.write();
			workbook.close();

			utils.FilesAndLaunchUtils.openFile(file);
		}
		catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
	}
	
	public static void importFromExcel(File file){
		try{
			WorkbookSettings wbSettings = new WorkbookSettings();
			wbSettings.setEncoding("8859_1");
			Workbook workbook = Workbook.getWorkbook(file, wbSettings);
			Sheet sheet = workbook.getSheet(0);
			Cell cell =  sheet.getCell(gui.utils.JInternalFrameManagementModel.POINT_CHECK_INFORMAITON.x, gui.utils.JInternalFrameManagementModel.POINT_CHECK_INFORMAITON.y);
			if (!cell.getContents().equals(gui.utils.JInternalFrameManagementModel.CHECK_INFORMATION)){
				gui.utils.GUIMessageByOptionPane.showErrorMessage("Excel non valide", "Votre document Excel n'est pas valide (Probablement non généré par l'Application)");
				return;
			}

			int ligne = 1;
			java.util.List<String> queries = new java.util.ArrayList<String>();
			while (true){
				ligne++;
				//cell = sheet.getCell("A"+ligne);
				cell = sheet.getCell(0, ligne-1);
				if (cell.getContents().trim().equals(""))
					break;
				
				ParametresApplication parametresApplication = new ParametresApplication();

				String value = "";
				try{
					value = sheet.getCell(1, ligne-1).getContents().trim();
					parametresApplication.setDesignation(value);
					value = sheet.getCell(2, ligne-1).getContents().trim();
					parametresApplication.setDescription(value);
					value = sheet.getCell(3, ligne-1).getContents().trim();
					parametresApplication.setIdPhoto(Integer.parseInt(value));
					queries.add(DAOParametresApplication.getSQLWriting(parametresApplication));
				}catch (Exception e){
					utils.StringUtils.printDebug(e);
				}
			}

			workbook.close();
			utils.SGBDConfig.UpdateDeleteSQLQueries sqlQueries = new utils.SGBDConfig.UpdateDeleteSQLQueries(queries);
			communication.SocketCommunicator.getInstance().sendQuery(sqlQueries);
		}
		catch (Exception ex){
			gui.utils.GUIMessageByOptionPane.showErrorMessage("Excel non valide", "Votre document Excel n'est pas valide");
			utils.StringUtils.printDebug(ex);
		}
	}


	public static void exportToPDF(File file){
		exportToPDF(file, DAOParametresApplication.getListInstances());
	}

	public static void exportToPDF(File file, java.util.List<ParametresApplication> list){
		try{
			Document docPDF = new Document(PageSize.A4, 25, 25, 20, 10);
			PdfWriter writer = PdfWriter.getInstance(docPDF,new java.io.FileOutputStream(file));
			
			Font fontHVCA15_IB = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD | Font.ITALIC);
			Font fontTR10_B = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
			Font fontTR10_B_WHITE = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
			fontTR10_B_WHITE.setColor(BaseColor.WHITE);
			Font fontTR10 = new Font(Font.FontFamily.TIMES_ROMAN, 10);
			
			int maxItemsAtPage = 50;
			int nbPages = (int) (list.size() / maxItemsAtPage);
			if ((nbPages == 0) || (list.size() % maxItemsAtPage > 0)){
				nbPages++;
			}
			
			docPDF.open();
//			PdfContentByte canvas = writer.getDirectContent();
			writer.getDirectContent();
			
			for (int numPage=0; numPage<nbPages; numPage++){
				docPDF.newPage();
				Paragraph header = new Paragraph("Paramètres de l'Application", fontHVCA15_IB);
				header.setAlignment(Paragraph.ALIGN_RIGHT);
				header.setSpacingAfter(5);
				docPDF.add(header);
				
				LineSeparator line = new LineSeparator();
				docPDF.add(line);
				
				PdfPCell cell;
				PdfPTable pdfTable;
				float[] a;
				float width = PageSize.A4.getWidth() - 100;
				
				a = new float[3];
				a[0] = (float)(width * 0.33333334);
				a[1] = (float)(width * 0.33333334);
				a[2] = (float)(width * 0.33333334);
				pdfTable = new PdfPTable(a.length);
				
				pdfTable.setWidthPercentage (a, PageSize.A4);
				pdfTable.setHorizontalAlignment(Element.ALIGN_CENTER);
				
				cell = new PdfPCell(new Paragraph("Désignation de l'Application", fontTR10_B_WHITE));
				cell.setBackgroundColor(BaseColor.BLACK);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingBottom(5);
				pdfTable.addCell(cell);

				cell = new PdfPCell(new Paragraph("Description de l'Application", fontTR10_B_WHITE));
				cell.setBackgroundColor(BaseColor.BLACK);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingBottom(5);
				pdfTable.addCell(cell);

				cell = new PdfPCell(new Paragraph("Icone de l'Application", fontTR10_B_WHITE));
				cell.setBackgroundColor(BaseColor.BLACK);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingBottom(5);
				pdfTable.addCell(cell);

				
				fontTR10_B.setColor(BaseColor.BLACK);
				for (int i=0; i<maxItemsAtPage; i++){
					int index = i + numPage * maxItemsAtPage;
					ParametresApplication parametresApplication = null;
					if (index < list.size())
						parametresApplication = list.get(index);
					
					cell = new PdfPCell(new Paragraph((parametresApplication != null) ? parametresApplication.getDesignation().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((parametresApplication != null) ? parametresApplication.getDescription().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((parametresApplication != null) ? (parametresApplication.getPhoto() == null ? "" : parametresApplication.getPhoto().toString()) : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

				}
				pdfTable.setSpacingBefore(5);
				docPDF.add(pdfTable);
				pdfTable.setSpacingAfter(5);
				
				docPDF.add(new Paragraph("\n"));
				docPDF.add(line);
				
				String dateTime = (String)communication.SocketCommunicator.getInstance().sendQuery("getDateTimeFromServer");
				String date = utils.StringUtils.formatDateFromMySQL(dateTime.split(" ")[0]);
				String time = dateTime.split(" ")[1]; time = time.substring(0, time.lastIndexOf(":"));
				
				Chunk petitEspaceChunk = new Chunk("      ");
				Chunk grandEspaceChunk = new Chunk("                                                                                                                                     ");
				
				header = new Paragraph("Date : "+date, fontTR10);
				header.add(petitEspaceChunk);
				header.add("Horaire : "+time);
				header.add(grandEspaceChunk);
				header.add("Page "+(numPage + 1)+"/"+nbPages);
				docPDF.add(header);
			}
			
			docPDF.close();
		}
		catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
	}

	public static void exportToText(File file){
		exportToText(file, DAOParametresApplication.getListInstances());
	}

	public static void exportToText(File file, java.util.List<ParametresApplication> list){
		try{
			
			
		}
		catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
	}

	public static void exportToWord(File file){
		exportToWord(file, DAOParametresApplication.getListInstances());
	}

	public static void exportToWord(File file, java.util.List<ParametresApplication> list){
		try{
			
			
		}
		catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
	}
	
	public static javax.swing.JPanel getForm(){
		return getForm(new javax.swing.JPanel());
	}
	
	public static javax.swing.JPanel getForm(javax.swing.JPanel panel){
		if (panel.getLayout() instanceof javax.swing.GroupLayout){
			javax.swing.GroupLayout gl = new javax.swing.GroupLayout(panel);
			panel.setLayout(gl);
		}
		
		javax.swing.JLabel labelDesignation = new javax.swing.JLabel("Désignation de l'Application");
		javax.swing.JLabel labelDescription = new javax.swing.JLabel("Description de l'Application");
		javax.swing.JLabel labelPhoto = new javax.swing.JLabel("Icone de l'Application");
		
		javax.swing.JComponent componentDesignation = views.ViewParametresApplication.getViewForDesignation();
		javax.swing.JComponent componentDescription = views.ViewParametresApplication.getViewForDescription();
		javax.swing.JComponent componentPhoto = views.ViewParametresApplication.getViewForPhoto();
		
		javax.swing.GroupLayout layout = (javax.swing.GroupLayout)panel.getLayout();
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGap(1, 1, Short.MAX_VALUE)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelDesignation)
				.addComponent(labelDescription)
				.addComponent(labelPhoto)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(componentDesignation, 300, 300, 300)
				.addComponent(componentDescription, 300, 300, 300)
				.addComponent(componentPhoto, 300, 300, 300)
			)
			.addGap(1, 1, Short.MAX_VALUE)
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addGap(1, 1, Short.MAX_VALUE)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelDesignation)
				.addComponent(componentDesignation, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelDescription)
				.addComponent(componentDescription, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelPhoto)
				.addComponent(componentPhoto, 20, 20, 20)
			)
			.addGap(1, 1, Short.MAX_VALUE)
		);
		
		return panel;
	}
	
	public static java.util.Map<String, gui.utils.GroupLayouter.LineForm> getFormSimple(javax.swing.JPanel panel){
		if (! (panel.getLayout() instanceof javax.swing.GroupLayout)){
			javax.swing.GroupLayout gl = new javax.swing.GroupLayout(panel);
			panel.setLayout(gl);
		}
		
		java.util.Map<String, gui.utils.GroupLayouter.LineForm> mapLabelsFields = new java.util.LinkedHashMap<String, gui.utils.GroupLayouter.LineForm>();
		
		javax.swing.JLabel labelDesignation = new javax.swing.JLabel("Désignation de l'Application");
		javax.swing.JLabel labelDescription = new javax.swing.JLabel("Description de l'Application");
		javax.swing.JLabel labelPhoto = new javax.swing.JLabel("Icone de l'Application");
		
		javax.swing.JComponent componentDesignation = views.ViewParametresApplication.getViewForDesignation();
		javax.swing.JComponent componentDescription = views.ViewParametresApplication.getViewForDescription();
		javax.swing.JComponent componentPhoto = views.ViewParametresApplication.getViewForPhoto();

		gui.utils.GroupLayouter.LineForm lineForm;
		lineForm = new gui.utils.GroupLayouter.LineForm(labelDesignation, componentDesignation);
		lineForm.setHeight(25);
		mapLabelsFields.put("designation", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelDescription, componentDescription);
		lineForm.setHeight(25);
		mapLabelsFields.put("description", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelPhoto, componentPhoto);
		lineForm.setHeight(25);
		mapLabelsFields.put("photo", lineForm);

		javax.swing.JComponent[][] matrice ={
			{labelDesignation, componentDesignation},
			{labelDescription, componentDescription},
			{labelPhoto, componentPhoto},
		};

		gui.utils.GroupLayouter.Form form = new gui.utils.GroupLayouter.Form(matrice, 50, 50);
		gui.utils.GroupLayouter.createSimpleForm(panel, form);		
		return mapLabelsFields;
	}

	public static class DlgAddModifyParametresApplication extends javax.swing.JDialog{
		private static final long serialVersionUID = 1L;
		
		protected javax.swing.JPanel		pFormulaire;
		protected java.util.Map<String, gui.utils.GroupLayouter.LineForm>	mapAttributLineForm;
		protected javax.swing.JSeparator	sepBottom;
		protected javax.swing.JButton		bValidate, bCancel;
		
		protected models.beans.ParametresApplication parametresApplication = null;
		
		public DlgAddModifyParametresApplication(java.awt.Window parent, String title){
			this(parent, title, null);
		}
		
		public DlgAddModifyParametresApplication(java.awt.Window parent, String title, models.beans.ParametresApplication parametresApplication){
			super(parent);
			initComponents();
			this.setParametresApplication(parametresApplication);
		}
		
		protected void initComponents(){
			this.setModal(true);
			pFormulaire = new javax.swing.JPanel();
			pFormulaire.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
			
			mapAttributLineForm = getFormSimple(pFormulaire);
			
			bValidate = gui.utils.GUIButtonFactory.createValidateButton("Valider", 'V');
			bCancel = gui.utils.GUIButtonFactory.createCancelButton("Annuler", 'A');
			sepBottom = new javax.swing.JSeparator();
			
			allLayout();
			allEvents();
		}
		
		protected void allLayout(){
			javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this.getContentPane());
			this.getContentPane().setLayout(layout);
			
			layout.setHorizontalGroup(layout.createParallelGroup()
					.addComponent(pFormulaire)
					.addComponent(sepBottom, 100, 100, Short.MAX_VALUE)
					.addGroup(layout.createSequentialGroup()
							.addGap(10)
							.addComponent(bValidate)
							.addGap(100, 100, Short.MAX_VALUE)
							.addComponent(bCancel)
							.addGap(10)
					)
			);
			
			layout.setVerticalGroup(layout.createSequentialGroup()
					.addGap(3)
					.addComponent(pFormulaire)
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(sepBottom, 2, 2, 2)
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup()
							.addComponent(bValidate, 25, 25, 25)
							.addComponent(bCancel, 25, 25, 25)
					)
					.addGap(3)
			);
			
			this.pack();
			this.setLocationRelativeTo(this.getParent());
		}
		
		protected void allEvents(){
			bValidate.addActionListener(new java.awt.event.ActionListener(){
				public void actionPerformed(java.awt.event.ActionEvent evt){
					bValiderActionPerformed();
				}
			});
			
			bCancel.addActionListener(new java.awt.event.ActionListener(){
				public void actionPerformed(java.awt.event.ActionEvent evt){
					bCancelActionPerformed();
				}
			});
			
			this.addWindowListener(new java.awt.event.WindowAdapter(){
				public void windowClosing(java.awt.event.WindowEvent evt){
					bCancelActionPerformed();
				}
			});
		}
		
		protected void bValiderActionPerformed(){
			ParametresApplication parametresApplication = this.getParametresApplication();
			if (parametresApplication == null){
				parametresApplication = new ParametresApplication();
			}
			
			parametresApplication.setDesignation(mapAttributLineForm.get("designation").getValueAsString());
			parametresApplication.setDescription(mapAttributLineForm.get("description").getValueAsString());
			
			int idPhoto = parametresApplication.getIdPhoto().intValue();
			if (mapAttributLineForm.get("photo").getValue() != null && mapAttributLineForm.get("photo").getValue() instanceof models.beans.Photo){
				idPhoto = ((models.beans.Photo)(mapAttributLineForm.get("photo").getValue())).getId().intValue();
			}
			parametresApplication.setIdPhoto(idPhoto);
			
			parametresApplication.setId((Integer)communication.SocketCommunicator.getInstance().sendQuery(parametresApplication));
			this.setParametresApplication(parametresApplication);
			
			this.setVisible(false);
		}
		
		protected void bCancelActionPerformed(){
			this.setParametresApplication(null);
			this.setVisible(false);
		}
		
		public void setParametresApplication(models.beans.ParametresApplication parametresApplication) {
			this.parametresApplication = parametresApplication;
			updateForm();
		}
		
		public models.beans.ParametresApplication getParametresApplication() {
			return this.parametresApplication;
		}
		
		protected void emptyForm(){
			mapAttributLineForm.get("designation").setValue(null);
			mapAttributLineForm.get("description").setValue(null);
			mapAttributLineForm.get("photo").setValue(null);
		}
		
		protected void updateForm(){
			if (parametresApplication == null){
				emptyForm();
				return;
			}
			
			mapAttributLineForm.get("designation").setValue(parametresApplication.getDesignation());
			mapAttributLineForm.get("description").setValue(parametresApplication.getDescription());
			mapAttributLineForm.get("photo").setValue(parametresApplication.getPhoto());
		}
	}
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}