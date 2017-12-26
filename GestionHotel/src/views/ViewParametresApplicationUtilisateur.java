package views;

import java.io.File;

import models.daos.client.DAOParametresApplicationUtilisateur;
import models.beans.ParametresApplicationUtilisateur;

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

public abstract class ViewParametresApplicationUtilisateur {
	
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

	public static javax.swing.JTextField getViewForPeriodeNotification(){
		return gui.utils.GUIFieldFactory.createNaturalField(11);
	}

	public static String getCaptionForPeriodeNotification(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("Période de Notification (Seconds)", userParameters.getLang().getCodeLang());
		}

		return "Période de Notification (Seconds)";
	}

	public static javax.swing.JCheckBox getViewForVisibilityOfNotification(){
		return new javax.swing.JCheckBox();
	}

	public static String getCaptionForVisibilityOfNotification(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("Barre de Notification", userParameters.getLang().getCodeLang());
		}

		return "Barre de Notification";
	}

	public static javax.swing.JCheckBox getViewForVisibilityOfMainToolBar(){
		return new javax.swing.JCheckBox();
	}

	public static String getCaptionForVisibilityOfMainToolBar(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("Barre d'Outil Principale", userParameters.getLang().getCodeLang());
		}

		return "Barre d'Outil Principale";
	}

	public static javax.swing.JComboBox getViewForLang(){
		java.util.List<models.beans.Lang> list = models.daos.client.DAOLang.getListInstances();
		javax.swing.JComboBox view = new gui.utils.GUIPanelFactory.JEditedComboBox(list.toArray());
		view.insertItemAt("Choisir Lang", 0);
		return view;
	}

	public static String getCaptionForLang(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("Language de l'Interface", userParameters.getLang().getCodeLang());
		}

		return "Language de l'Interface";
	}

	public static javax.swing.JTextField getViewForLookAndFeel(){
		return gui.utils.GUIFieldFactory.createSimpleTextField(250);
	}

	public static String getCaptionForLookAndFeel(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("", userParameters.getLang().getCodeLang());
		}

		return "";
	}

	public static java.util.Vector<Object> getTableHeader(boolean withNumero){
		java.util.Vector<Object> header = new java.util.Vector<Object>();
		
		if (withNumero)
			header.add("N°");
		header.add("Période de Notification (Seconds)");
		header.add("Barre de Notification");
		header.add("Barre d'Outil Principale");
		header.add("Language de l'Interface");
		header.add("");
		
		return header;
	}

	public static java.util.Vector<Object> getTableRow(models.beans.ParametresApplicationUtilisateur parametresApplicationUtilisateur, int numOrder){
		java.util.Vector<Object> row = new java.util.Vector<Object>();
		
		if (numOrder > 0)
			row.add(numOrder);
		
		row.add(parametresApplicationUtilisateur.getPeriodeNotification());
		row.add(parametresApplicationUtilisateur.isVisibilityOfNotification());
		row.add(parametresApplicationUtilisateur.isVisibilityOfMainToolBar());
		row.add(parametresApplicationUtilisateur.getLang());
		row.add(parametresApplicationUtilisateur.getLookAndFeel());
		
		row.add(parametresApplicationUtilisateur);
		
		return row;
	}

	public static java.util.Vector<java.util.Vector<Object>> getData(java.util.List<models.beans.ParametresApplicationUtilisateur> list, boolean withOrder){
		return getData(list, withOrder, 0);
	}

	public static java.util.Vector<java.util.Vector<Object>> getData(java.util.List<models.beans.ParametresApplicationUtilisateur> list, boolean withOrder, int numRowBase){
		java.util.Vector<java.util.Vector<Object>> data = new java.util.Vector<java.util.Vector<Object>>();
		if (list == null)
			return data;
		
		int numRow = numRowBase;
		for (models.beans.ParametresApplicationUtilisateur parametresApplicationUtilisateur : list){
			if (withOrder)
				numRow++;
			
			data.add(getTableRow(parametresApplicationUtilisateur , numRow));
		}
		
		return data;
	}

	public static void exportToExcel(File file){
		exportToExcel(file, DAOParametresApplicationUtilisateur.getListInstances());
	}

	public static void exportToExcel(File file, java.util.List<ParametresApplicationUtilisateur> list){
		try{
			WritableWorkbook workbook = Workbook.createWorkbook(file);
			WritableSheet sheet = workbook.createSheet( "Liste des ViewParametresApplicationUtilisateurs", 0);

			sheet.getSettings().setDefaultRowHeight(300);
			sheet.getSettings().setDefaultColumnWidth(16);

			sheet.setColumnView(0, 10);
			sheet.setColumnView(1, 20);
			sheet.setColumnView(2, 20);
			sheet.setColumnView(3, 20);
			sheet.setColumnView(4, 20);
			sheet.setColumnView(5, 20);
			
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
			label = new Label(1, 0, "Période de Notification (Seconds)", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(2, 0, "Barre de Notification", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(3, 0, "Barre d'Outil Principale", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(4, 0, "Language de l'Interface", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(5, 0, "", formatHeaderTableStr); sheet.addCell(label);
			
			int ligneBas = 1;
			for (ParametresApplicationUtilisateur parametresApplicationUtilisateur : list){
				int i = list.indexOf(parametresApplicationUtilisateur);
				label = new Label(0, ligneBas+i, String.valueOf(i), formatContentStr); sheet.addCell(label);
				label = new Label(1, ligneBas+i, parametresApplicationUtilisateur.getPeriodeNotification().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(2, ligneBas+i, parametresApplicationUtilisateur.isVisibilityOfNotification().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(3, ligneBas+i, parametresApplicationUtilisateur.isVisibilityOfMainToolBar().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(4, ligneBas+i, parametresApplicationUtilisateur.getIdLang().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(5, ligneBas+i, parametresApplicationUtilisateur.getLookAndFeel().toString(), formatHeaderTableStr); sheet.addCell(label);
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
				
				ParametresApplicationUtilisateur parametresApplicationUtilisateur = new ParametresApplicationUtilisateur();

				String value = "";
				try{
					value = sheet.getCell(1, ligne-1).getContents().trim();
					parametresApplicationUtilisateur.setPeriodeNotification(Integer.parseInt(value));
					value = sheet.getCell(2, ligne-1).getContents().trim();
					parametresApplicationUtilisateur.setVisibilityOfNotification(Boolean.parseBoolean(value));
					value = sheet.getCell(3, ligne-1).getContents().trim();
					parametresApplicationUtilisateur.setVisibilityOfMainToolBar(Boolean.parseBoolean(value));
					value = sheet.getCell(4, ligne-1).getContents().trim();
					parametresApplicationUtilisateur.setIdLang(Integer.parseInt(value));
					value = sheet.getCell(5, ligne-1).getContents().trim();
					parametresApplicationUtilisateur.setLookAndFeel(value);
					queries.add(DAOParametresApplicationUtilisateur.getSQLWriting(parametresApplicationUtilisateur));
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
		exportToPDF(file, DAOParametresApplicationUtilisateur.getListInstances());
	}

	public static void exportToPDF(File file, java.util.List<ParametresApplicationUtilisateur> list){
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
				Paragraph header = new Paragraph("Paramètres de l'Utilisateur", fontHVCA15_IB);
				header.setAlignment(Paragraph.ALIGN_RIGHT);
				header.setSpacingAfter(5);
				docPDF.add(header);
				
				LineSeparator line = new LineSeparator();
				docPDF.add(line);
				
				PdfPCell cell;
				PdfPTable pdfTable;
				float[] a;
				float width = PageSize.A4.getWidth() - 100;
				
				a = new float[5];
				a[0] = (float)(width * 0.2);
				a[1] = (float)(width * 0.2);
				a[2] = (float)(width * 0.2);
				a[3] = (float)(width * 0.2);
				a[4] = (float)(width * 0.2);
				pdfTable = new PdfPTable(a.length);
				
				pdfTable.setWidthPercentage (a, PageSize.A4);
				pdfTable.setHorizontalAlignment(Element.ALIGN_CENTER);
				
				cell = new PdfPCell(new Paragraph("Période de Notification (Seconds)", fontTR10_B_WHITE));
				cell.setBackgroundColor(BaseColor.BLACK);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingBottom(5);
				pdfTable.addCell(cell);

				cell = new PdfPCell(new Paragraph("Barre de Notification", fontTR10_B_WHITE));
				cell.setBackgroundColor(BaseColor.BLACK);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingBottom(5);
				pdfTable.addCell(cell);

				cell = new PdfPCell(new Paragraph("Barre d'Outil Principale", fontTR10_B_WHITE));
				cell.setBackgroundColor(BaseColor.BLACK);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingBottom(5);
				pdfTable.addCell(cell);

				cell = new PdfPCell(new Paragraph("Language de l'Interface", fontTR10_B_WHITE));
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
					ParametresApplicationUtilisateur parametresApplicationUtilisateur = null;
					if (index < list.size())
						parametresApplicationUtilisateur = list.get(index);
					
					cell = new PdfPCell(new Paragraph((parametresApplicationUtilisateur != null) ? parametresApplicationUtilisateur.getPeriodeNotification().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((parametresApplicationUtilisateur != null) ? parametresApplicationUtilisateur.isVisibilityOfNotification().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((parametresApplicationUtilisateur != null) ? parametresApplicationUtilisateur.isVisibilityOfMainToolBar().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((parametresApplicationUtilisateur != null) ? (parametresApplicationUtilisateur.getLang() == null ? "" : parametresApplicationUtilisateur.getLang().toString()) : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((parametresApplicationUtilisateur != null) ? parametresApplicationUtilisateur.getLookAndFeel().toString() : " ", fontTR10));
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
		exportToText(file, DAOParametresApplicationUtilisateur.getListInstances());
	}

	public static void exportToText(File file, java.util.List<ParametresApplicationUtilisateur> list){
		try{
			
			
		}
		catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
	}

	public static void exportToWord(File file){
		exportToWord(file, DAOParametresApplicationUtilisateur.getListInstances());
	}

	public static void exportToWord(File file, java.util.List<ParametresApplicationUtilisateur> list){
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
		
		javax.swing.JLabel labelPeriodeNotification = new javax.swing.JLabel("Période de Notification (Seconds)");
		javax.swing.JLabel labelVisibilityOfNotification = new javax.swing.JLabel("Barre de Notification");
		javax.swing.JLabel labelVisibilityOfMainToolBar = new javax.swing.JLabel("Barre d'Outil Principale");
		javax.swing.JLabel labelLang = new javax.swing.JLabel("Language de l'Interface");
		javax.swing.JLabel labelLookAndFeel = new javax.swing.JLabel("");
		
		javax.swing.JComponent componentPeriodeNotification = views.ViewParametresApplicationUtilisateur.getViewForPeriodeNotification();
		javax.swing.JComponent componentVisibilityOfNotification = views.ViewParametresApplicationUtilisateur.getViewForVisibilityOfNotification();
		javax.swing.JComponent componentVisibilityOfMainToolBar = views.ViewParametresApplicationUtilisateur.getViewForVisibilityOfMainToolBar();
		javax.swing.JComponent componentLang = views.ViewParametresApplicationUtilisateur.getViewForLang();
		javax.swing.JComponent componentLookAndFeel = views.ViewParametresApplicationUtilisateur.getViewForLookAndFeel();
		
		javax.swing.GroupLayout layout = (javax.swing.GroupLayout)panel.getLayout();
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGap(1, 1, Short.MAX_VALUE)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelPeriodeNotification)
				.addComponent(labelVisibilityOfNotification)
				.addComponent(labelVisibilityOfMainToolBar)
				.addComponent(labelLang)
				.addComponent(labelLookAndFeel)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(componentPeriodeNotification, 300, 300, 300)
				.addComponent(componentVisibilityOfNotification, 300, 300, 300)
				.addComponent(componentVisibilityOfMainToolBar, 300, 300, 300)
				.addComponent(componentLang, 300, 300, 300)
				.addComponent(componentLookAndFeel, 300, 300, 300)
			)
			.addGap(1, 1, Short.MAX_VALUE)
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addGap(1, 1, Short.MAX_VALUE)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelPeriodeNotification)
				.addComponent(componentPeriodeNotification, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelVisibilityOfNotification)
				.addComponent(componentVisibilityOfNotification, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelVisibilityOfMainToolBar)
				.addComponent(componentVisibilityOfMainToolBar, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelLang)
				.addComponent(componentLang, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelLookAndFeel)
				.addComponent(componentLookAndFeel, 20, 20, 20)
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
		
		javax.swing.JLabel labelPeriodeNotification = new javax.swing.JLabel("Période de Notification (Seconds)");
		javax.swing.JLabel labelVisibilityOfNotification = new javax.swing.JLabel("Barre de Notification");
		javax.swing.JLabel labelVisibilityOfMainToolBar = new javax.swing.JLabel("Barre d'Outil Principale");
		javax.swing.JLabel labelLang = new javax.swing.JLabel("Language de l'Interface");
		javax.swing.JLabel labelLookAndFeel = new javax.swing.JLabel("");
		
		javax.swing.JComponent componentPeriodeNotification = views.ViewParametresApplicationUtilisateur.getViewForPeriodeNotification();
		javax.swing.JComponent componentVisibilityOfNotification = views.ViewParametresApplicationUtilisateur.getViewForVisibilityOfNotification();
		javax.swing.JComponent componentVisibilityOfMainToolBar = views.ViewParametresApplicationUtilisateur.getViewForVisibilityOfMainToolBar();
		javax.swing.JComponent componentLang = views.ViewParametresApplicationUtilisateur.getViewForLang();
		javax.swing.JComponent componentLookAndFeel = views.ViewParametresApplicationUtilisateur.getViewForLookAndFeel();

		gui.utils.GroupLayouter.LineForm lineForm;
		lineForm = new gui.utils.GroupLayouter.LineForm(labelPeriodeNotification, componentPeriodeNotification);
		lineForm.setHeight(25);
		mapLabelsFields.put("periodenotification", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelVisibilityOfNotification, componentVisibilityOfNotification);
		lineForm.setHeight(25);
		mapLabelsFields.put("visibilityofnotification", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelVisibilityOfMainToolBar, componentVisibilityOfMainToolBar);
		lineForm.setHeight(25);
		mapLabelsFields.put("visibilityofmaintoolbar", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelLang, componentLang);
		lineForm.setHeight(25);
		mapLabelsFields.put("lang", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelLookAndFeel, componentLookAndFeel);
		lineForm.setHeight(25);
		mapLabelsFields.put("lookandfeel", lineForm);

		javax.swing.JComponent[][] matrice ={
			{labelPeriodeNotification, componentPeriodeNotification},
			{labelVisibilityOfNotification, componentVisibilityOfNotification},
			{labelVisibilityOfMainToolBar, componentVisibilityOfMainToolBar},
			{labelLang, componentLang},
			{labelLookAndFeel, componentLookAndFeel},
		};

		gui.utils.GroupLayouter.Form form = new gui.utils.GroupLayouter.Form(matrice, 50, 50);
		gui.utils.GroupLayouter.createSimpleForm(panel, form);		
		return mapLabelsFields;
	}

	public static class DlgAddModifyParametresApplicationUtilisateur extends javax.swing.JDialog{
		private static final long serialVersionUID = 1L;
		
		protected javax.swing.JPanel		pFormulaire;
		protected java.util.Map<String, gui.utils.GroupLayouter.LineForm>	mapAttributLineForm;
		protected javax.swing.JSeparator	sepBottom;
		protected javax.swing.JButton		bValidate, bCancel;
		
		protected models.beans.ParametresApplicationUtilisateur parametresApplicationUtilisateur = null;
		
		public DlgAddModifyParametresApplicationUtilisateur(java.awt.Window parent, String title){
			this(parent, title, null);
		}
		
		public DlgAddModifyParametresApplicationUtilisateur(java.awt.Window parent, String title, models.beans.ParametresApplicationUtilisateur parametresApplicationUtilisateur){
			super(parent);
			initComponents();
			this.setParametresApplicationUtilisateur(parametresApplicationUtilisateur);
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
			ParametresApplicationUtilisateur parametresApplicationUtilisateur = this.getParametresApplicationUtilisateur();
			if (parametresApplicationUtilisateur == null){
				parametresApplicationUtilisateur = new ParametresApplicationUtilisateur();
			}
			
			parametresApplicationUtilisateur.setPeriodeNotification(mapAttributLineForm.get("periodenotification").getValueAsInteger());
			parametresApplicationUtilisateur.setVisibilityOfNotification(mapAttributLineForm.get("visibilityofnotification").getValueAsBoolean());
			parametresApplicationUtilisateur.setVisibilityOfMainToolBar(mapAttributLineForm.get("visibilityofmaintoolbar").getValueAsBoolean());
			
			int idLang = parametresApplicationUtilisateur.getIdLang().intValue();
			if (mapAttributLineForm.get("lang").getValue() != null && mapAttributLineForm.get("lang").getValue() instanceof models.beans.Lang){
				idLang = ((models.beans.Lang)(mapAttributLineForm.get("lang").getValue())).getId().intValue();
			}
			parametresApplicationUtilisateur.setIdLang(idLang);
			parametresApplicationUtilisateur.setLookAndFeel(mapAttributLineForm.get("lookandfeel").getValueAsString());
			
			parametresApplicationUtilisateur.setId((Integer)communication.SocketCommunicator.getInstance().sendQuery(parametresApplicationUtilisateur));
			this.setParametresApplicationUtilisateur(parametresApplicationUtilisateur);
			
			this.setVisible(false);
		}
		
		protected void bCancelActionPerformed(){
			this.setParametresApplicationUtilisateur(null);
			this.setVisible(false);
		}
		
		public void setParametresApplicationUtilisateur(models.beans.ParametresApplicationUtilisateur parametresApplicationUtilisateur) {
			this.parametresApplicationUtilisateur = parametresApplicationUtilisateur;
			updateForm();
		}
		
		public models.beans.ParametresApplicationUtilisateur getParametresApplicationUtilisateur() {
			return this.parametresApplicationUtilisateur;
		}
		
		protected void emptyForm(){
			mapAttributLineForm.get("periodenotification").setValue(null);
			mapAttributLineForm.get("visibilityofnotification").setValue(null);
			mapAttributLineForm.get("visibilityofmaintoolbar").setValue(null);
			mapAttributLineForm.get("lang").setValue(null);
			mapAttributLineForm.get("lookandfeel").setValue(null);
		}
		
		protected void updateForm(){
			if (parametresApplicationUtilisateur == null){
				emptyForm();
				return;
			}
			
			mapAttributLineForm.get("periodenotification").setValue(parametresApplicationUtilisateur.getPeriodeNotification());
			mapAttributLineForm.get("visibilityofnotification").setValue(parametresApplicationUtilisateur.isVisibilityOfNotification());
			mapAttributLineForm.get("visibilityofmaintoolbar").setValue(parametresApplicationUtilisateur.isVisibilityOfMainToolBar());
			mapAttributLineForm.get("lang").setValue(parametresApplicationUtilisateur.getLang());
			mapAttributLineForm.get("lookandfeel").setValue(parametresApplicationUtilisateur.getLookAndFeel());
		}
	}
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}