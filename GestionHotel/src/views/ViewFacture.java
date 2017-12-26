package views;

import java.io.File;

import models.daos.server.DAOFacture;
import models.beans.Facture;

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

public abstract class ViewFacture {
	
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

	public static gui.utils.GUIDate getViewForDateFacture(){
		return new gui.utils.GUIDate();
	}

	public static String getCaptionForDateFacture(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("", userParameters.getLang().getCodeLang());
		}

		return "";
	}

	public static javax.swing.JComboBox getViewForClient(){
		java.util.List<models.beans.Client> list = models.daos.client.DAOClient.getListInstances();
		javax.swing.JComboBox view = new gui.utils.GUIPanelFactory.JEditedComboBox(list.toArray());
		view.insertItemAt("Choisir Client", 0);
		return view;
	}

	public static String getCaptionForClient(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("", userParameters.getLang().getCodeLang());
		}

		return "";
	}

	public static javax.swing.JTextField getViewForMontant(){
		return gui.utils.GUIFieldFactory.createNumericField(10, 10);
	}

	public static String getCaptionForMontant(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("", userParameters.getLang().getCodeLang());
		}

		return "";
	}

	public static javax.swing.JComponent getViewForTypePayementC(String typeView){
		String[] items = {"CB", "Chèque", "Espasse"};
		if (typeView.equalsIgnoreCase("JCOMBOBOXE"))
			return new gui.utils.GUIPanelFactory.JEditedComboBox(items);
		else if (typeView.equalsIgnoreCase("JCHECKBOXES")) {
			java.util.List<java.awt.Component> cbs = new java.util.ArrayList<java.awt.Component>();
			for (String val : items){
				cbs.add(new javax.swing.JCheckBox(val));
			}
			return gui.utils.GUIPanelFactory.createPanelLinearLayout(gui.utils.GUIPanelFactory.LINEAR_LAYOUT_HORIZONTAL, cbs);
		}
		return null;
	}

	public static String getCaptionForTypePayementC(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("", userParameters.getLang().getCodeLang());
		}

		return "";
	}

	public static java.util.Vector<Object> getTableHeader(boolean withNumero){
		java.util.Vector<Object> header = new java.util.Vector<Object>();
		
		if (withNumero)
			header.add("NÂ°");
		header.add("");
		header.add("");
		header.add("");
		header.add("");
		
		return header;
	}

	public static java.util.Vector<Object> getTableRow(models.beans.Facture facture, int numOrder){
		java.util.Vector<Object> row = new java.util.Vector<Object>();
		
		if (numOrder > 0)
			row.add(numOrder);
		
		row.add(facture.getDateFacture());
		row.add(facture.getClient());
		row.add(facture.getMontant());
		row.add(facture.getTypePayementC());
		
		row.add(facture);
		
		return row;
	}

	public static java.util.Vector<java.util.Vector<Object>> getData(java.util.List<models.beans.Facture> list, boolean withOrder){
		return getData(list, withOrder, 0);
	}

	public static java.util.Vector<java.util.Vector<Object>> getData(java.util.List<models.beans.Facture> list, boolean withOrder, int numRowBase){
		java.util.Vector<java.util.Vector<Object>> data = new java.util.Vector<java.util.Vector<Object>>();
		if (list == null)
			return data;
		
		int numRow = numRowBase;
		for (models.beans.Facture facture : list){
			if (withOrder)
				numRow++;
			
			data.add(getTableRow(facture , numRow));
		}
		
		return data;
	}

	public static void exportToExcel(File file){
		exportToExcel(file, DAOFacture.getListInstances());
	}

	public static void exportToExcel(File file, java.util.List<Facture> list){
		try{
			WritableWorkbook workbook = Workbook.createWorkbook(file);
			WritableSheet sheet = workbook.createSheet( "Liste des ViewFactures", 0);

			sheet.getSettings().setDefaultRowHeight(300);
			sheet.getSettings().setDefaultColumnWidth(16);

			sheet.setColumnView(0, 10);
			sheet.setColumnView(1, 20);
			sheet.setColumnView(2, 20);
			sheet.setColumnView(3, 20);
			sheet.setColumnView(4, 20);
			
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

			Label label = new Label(0, 0, "NÂ°", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(1, 0, "", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(2, 0, "", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(3, 0, "", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(4, 0, "", formatHeaderTableStr); sheet.addCell(label);
			
			int ligneBas = 1;
			for (Facture facture : list){
				int i = list.indexOf(facture);
				label = new Label(0, ligneBas+i, String.valueOf(i), formatContentStr); sheet.addCell(label);
				label = new Label(1, ligneBas+i, facture.getDateFacture().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(2, ligneBas+i, facture.getIdClient().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(3, ligneBas+i, facture.getMontant().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(4, ligneBas+i, facture.getTypePayementC().toString(), formatHeaderTableStr); sheet.addCell(label);
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
				
				Facture facture = new Facture();

				String value = "";
				try{
					value = sheet.getCell(1, ligne-1).getContents().trim();
					facture.setDateFacture(utils.StringUtils.getDateFromString(value));
					value = sheet.getCell(2, ligne-1).getContents().trim();
					facture.setIdClient(Integer.parseInt(value));
					value = sheet.getCell(3, ligne-1).getContents().trim();
					facture.setMontant(Double.parseDouble(value));
					value = sheet.getCell(4, ligne-1).getContents().trim();
					facture.setTypePayementC(Facture.TypePayementC.getByValue(value));
					queries.add(DAOFacture.getSQLWriting(facture));
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
		exportToPDF(file, DAOFacture.getListInstances());
	}

	public static void exportToPDF(File file, java.util.List<Facture> list){
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
				Paragraph header = new Paragraph("", fontHVCA15_IB);
				header.setAlignment(Paragraph.ALIGN_RIGHT);
				header.setSpacingAfter(5);
				docPDF.add(header);
				
				LineSeparator line = new LineSeparator();
				docPDF.add(line);
				
				PdfPCell cell;
				PdfPTable pdfTable;
				float[] a;
				float width = PageSize.A4.getWidth() - 100;
				
				a = new float[4];
				a[0] = (float)(width * 0.25);
				a[1] = (float)(width * 0.25);
				a[2] = (float)(width * 0.25);
				a[3] = (float)(width * 0.25);
				pdfTable = new PdfPTable(a.length);
				
				pdfTable.setWidthPercentage (a, PageSize.A4);
				pdfTable.setHorizontalAlignment(Element.ALIGN_CENTER);
				
				cell = new PdfPCell(new Paragraph("", fontTR10_B_WHITE));
				cell.setBackgroundColor(BaseColor.BLACK);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingBottom(5);
				pdfTable.addCell(cell);

				cell = new PdfPCell(new Paragraph("", fontTR10_B_WHITE));
				cell.setBackgroundColor(BaseColor.BLACK);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingBottom(5);
				pdfTable.addCell(cell);

				cell = new PdfPCell(new Paragraph("", fontTR10_B_WHITE));
				cell.setBackgroundColor(BaseColor.BLACK);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingBottom(5);
				pdfTable.addCell(cell);

				cell = new PdfPCell(new Paragraph("", fontTR10_B_WHITE));
				cell.setBackgroundColor(BaseColor.BLACK);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingBottom(5);
				pdfTable.addCell(cell);

				
				fontTR10_B.setColor(BaseColor.BLACK);
				for (int i=0; i<maxItemsAtPage; i++){
					int index = i + numPage * maxItemsAtPage;
					Facture facture = null;
					if (index < list.size())
						facture = list.get(index);
					
					cell = new PdfPCell(new Paragraph((facture != null) ? utils.StringUtils.formatDateFromMySQL(facture.getDateFacture().toString()) : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((facture != null) ? (facture.getClient() == null ? "" : facture.getClient().toString()) : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((facture != null) ? facture.getMontant().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((facture != null) ? facture.getTypePayementC().getValue() : " ", fontTR10));
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
		exportToText(file, DAOFacture.getListInstances());
	}

	public static void exportToText(File file, java.util.List<Facture> list){
		try{
			
			
		}
		catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
	}

	public static void exportToWord(File file){
		exportToWord(file, DAOFacture.getListInstances());
	}

	public static void exportToWord(File file, java.util.List<Facture> list){
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
		
		javax.swing.JLabel labelDateFacture = new javax.swing.JLabel("");
		javax.swing.JLabel labelClient = new javax.swing.JLabel("");
		javax.swing.JLabel labelMontant = new javax.swing.JLabel("");
		javax.swing.JLabel labelTypePayementC = new javax.swing.JLabel("");
		
		javax.swing.JComponent componentDateFacture = views.ViewFacture.getViewForDateFacture();
		javax.swing.JComponent componentClient = views.ViewFacture.getViewForClient();
		javax.swing.JComponent componentMontant = views.ViewFacture.getViewForMontant();
		javax.swing.JComponent componentTypePayementC = (javax.swing.JComboBox)views.ViewFacture.getViewForTypePayementC("JCOMBOBOXE");
		
		javax.swing.GroupLayout layout = (javax.swing.GroupLayout)panel.getLayout();
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGap(1, 1, Short.MAX_VALUE)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelDateFacture)
				.addComponent(labelClient)
				.addComponent(labelMontant)
				.addComponent(labelTypePayementC)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(componentDateFacture, 300, 300, 300)
				.addComponent(componentClient, 300, 300, 300)
				.addComponent(componentMontant, 300, 300, 300)
				.addComponent(componentTypePayementC, 300, 300, 300)
			)
			.addGap(1, 1, Short.MAX_VALUE)
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addGap(1, 1, Short.MAX_VALUE)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelDateFacture)
				.addComponent(componentDateFacture, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelClient)
				.addComponent(componentClient, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelMontant)
				.addComponent(componentMontant, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelTypePayementC)
				.addComponent(componentTypePayementC, 20, 20, 20)
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
		
		javax.swing.JLabel labelDateFacture = new javax.swing.JLabel("");
		javax.swing.JLabel labelClient = new javax.swing.JLabel("");
		javax.swing.JLabel labelMontant = new javax.swing.JLabel("");
		javax.swing.JLabel labelTypePayementC = new javax.swing.JLabel("");
		
		javax.swing.JComponent componentDateFacture = views.ViewFacture.getViewForDateFacture();
		javax.swing.JComponent componentClient = views.ViewFacture.getViewForClient();
		javax.swing.JComponent componentMontant = views.ViewFacture.getViewForMontant();
		javax.swing.JComponent componentTypePayementC = (javax.swing.JComboBox)views.ViewFacture.getViewForTypePayementC("JCOMBOBOXE");

		gui.utils.GroupLayouter.LineForm lineForm;
		lineForm = new gui.utils.GroupLayouter.LineForm(labelDateFacture, componentDateFacture);
		lineForm.setHeight(25);
		mapLabelsFields.put("datefacture", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelClient, componentClient);
		lineForm.setHeight(25);
		mapLabelsFields.put("client", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelMontant, componentMontant);
		lineForm.setHeight(25);
		mapLabelsFields.put("montant", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelTypePayementC, componentTypePayementC);
		lineForm.setHeight(25);
		mapLabelsFields.put("typepayementc", lineForm);

		javax.swing.JComponent[][] matrice ={
			{labelDateFacture, componentDateFacture},
			{labelClient, componentClient},
			{labelMontant, componentMontant},
			{labelTypePayementC, componentTypePayementC},
		};

		gui.utils.GroupLayouter.Form form = new gui.utils.GroupLayouter.Form(matrice, 50, 50);
		gui.utils.GroupLayouter.createSimpleForm(panel, form);		
		return mapLabelsFields;
	}

	public static class DlgAddModifyFacture extends javax.swing.JDialog{
		private static final long serialVersionUID = 1L;
		
		protected javax.swing.JPanel		pFormulaire;
		protected java.util.Map<String, gui.utils.GroupLayouter.LineForm>	mapAttributLineForm;
		protected javax.swing.JSeparator	sepBottom;
		protected javax.swing.JButton		bValidate, bCancel;
		
		protected models.beans.Facture facture = null;
		
		public DlgAddModifyFacture(java.awt.Window parent, String title){
			this(parent, title, null);
		}
		
		public DlgAddModifyFacture(java.awt.Window parent, String title, models.beans.Facture facture){
			super(parent);
			initComponents();
			this.setFacture(facture);
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
			Facture facture = this.getFacture();
			if (facture == null){
				facture = new Facture();
			}
			
			facture.setDateFacture(mapAttributLineForm.get("datefacture").getValueAsDate());
			
			int idClient = facture.getIdClient().intValue();
			if (mapAttributLineForm.get("client").getValue() != null && mapAttributLineForm.get("client").getValue() instanceof models.beans.Client){
				idClient = ((models.beans.Client)(mapAttributLineForm.get("client").getValue())).getId().intValue();
			}
			facture.setIdClient(idClient);
			facture.setMontant(mapAttributLineForm.get("montant").getValueAsDouble());
			facture.setTypePayementC(models.beans.Facture.TypePayementC.getByValue(mapAttributLineForm.get("typepayementc").getValueAsString()));
			
			facture.setId((Integer)communication.SocketCommunicator.getInstance().sendQuery(facture));
			this.setFacture(facture);
			
			this.setVisible(false);
		}
		
		protected void bCancelActionPerformed(){
			this.setFacture(null);
			this.setVisible(false);
		}
		
		public void setFacture(models.beans.Facture facture) {
			this.facture = facture;
			updateForm();
		}
		
		public models.beans.Facture getFacture() {
			return this.facture;
		}
		
		protected void emptyForm(){
			mapAttributLineForm.get("datefacture").setValue(null);
			mapAttributLineForm.get("client").setValue(null);
			mapAttributLineForm.get("montant").setValue(null);
			mapAttributLineForm.get("typepayementc").setValue(null);
		}
		
		protected void updateForm(){
			if (facture == null){
				emptyForm();
				return;
			}
			
			mapAttributLineForm.get("datefacture").setValue(facture.getDateFacture().toString());
			mapAttributLineForm.get("client").setValue(facture.getClient());
			mapAttributLineForm.get("montant").setValue(facture.getMontant());
			mapAttributLineForm.get("typepayementc").setValue(facture.getTypePayementC().getValue());
		}
	}
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}