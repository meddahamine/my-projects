package views;

import java.io.File;

import models.daos.client.DAOClient;
import models.beans.Client;

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

public abstract class ViewClient {
	
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

	public static javax.swing.JComponent getViewForTypeClient(String typeView){
		String[] items = {"Hébergé", "Non Hébergé"};
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

	public static String getCaptionForTypeClient(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("", userParameters.getLang().getCodeLang());
		}

		return "";
	}

	public static javax.swing.JTextField getViewForNom(){
		return gui.utils.GUIFieldFactory.createSimpleTextField(50);
	}

	public static String getCaptionForNom(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("", userParameters.getLang().getCodeLang());
		}

		return "";
	}

	public static javax.swing.JTextField getViewForPrenom(){
		return gui.utils.GUIFieldFactory.createSimpleTextField(50);
	}

	public static String getCaptionForPrenom(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("", userParameters.getLang().getCodeLang());
		}

		return "";
	}

	public static gui.utils.GUIDate getViewForDateNaiss(){
		return new gui.utils.GUIDate();
	}

	public static String getCaptionForDateNaiss(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("", userParameters.getLang().getCodeLang());
		}

		return "";
	}

	public static javax.swing.JTextField getViewForAdresse(){
		return gui.utils.GUIFieldFactory.createSimpleTextField(100);
	}

	public static String getCaptionForAdresse(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("", userParameters.getLang().getCodeLang());
		}

		return "";
	}

	public static javax.swing.JTextField getViewForNationalite(){
		return gui.utils.GUIFieldFactory.createSimpleTextField(50);
	}

	public static String getCaptionForNationalite(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("", userParameters.getLang().getCodeLang());
		}

		return "";
	}

	public static javax.swing.JTextField getViewForTelephone(){
		return gui.utils.GUIFieldFactory.createSimpleTextField(20);
	}

	public static String getCaptionForTelephone(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("", userParameters.getLang().getCodeLang());
		}

		return "";
	}

	public static javax.swing.JComponent getViewForTypeCarte(String typeView){
		String[] items = {"CN", "PC", "Passeport"};
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

	public static String getCaptionForTypeCarte(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("", userParameters.getLang().getCodeLang());
		}

		return "";
	}

	public static javax.swing.JTextField getViewForNumCarte(){
		return gui.utils.GUIFieldFactory.createSimpleTextField(10);
	}

	public static String getCaptionForNumCarte(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("", userParameters.getLang().getCodeLang());
		}

		return "";
	}

	public static javax.swing.JTextField getViewForProfession(){
		return gui.utils.GUIFieldFactory.createSimpleTextField(50);
	}

	public static String getCaptionForProfession(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("", userParameters.getLang().getCodeLang());
		}

		return "";
	}

	public static javax.swing.JTextField getViewForAutreTypeClient(){
		return gui.utils.GUIFieldFactory.createSimpleTextField(50);
	}

	public static String getCaptionForAutreTypeClient(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("", userParameters.getLang().getCodeLang());
		}

		return "";
	}

	public static javax.swing.JTextField getViewForAutreTypeCarte(){
		return gui.utils.GUIFieldFactory.createSimpleTextField(50);
	}

	public static String getCaptionForAutreTypeCarte(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("", userParameters.getLang().getCodeLang());
		}

		return "";
	}

	public static javax.swing.JTextField getViewForLieuDeNaiss(){
		return gui.utils.GUIFieldFactory.createSimpleTextField(50);
	}

	public static String getCaptionForLieuDeNaiss(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("", userParameters.getLang().getCodeLang());
		}

		return "";
	}

	public static javax.swing.JComponent getViewForCivilite(String typeView){
		String[] items = {"Mr.", "Mme.", "Mlle."};
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

	public static String getCaptionForCivilite(){
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
		header.add("");
		header.add("");
		header.add("");
		header.add("");
		header.add("");
		header.add("");
		header.add("");
		header.add("");
		header.add("");
		header.add("");
		
		return header;
	}

	public static java.util.Vector<Object> getTableRow(models.beans.Client client, int numOrder){
		java.util.Vector<Object> row = new java.util.Vector<Object>();
		
		if (numOrder > 0)
			row.add(numOrder);
		
		row.add(client.getTypeClient());
		row.add(client.getNom());
		row.add(client.getPrenom());
		row.add(client.getDateNaiss());
		row.add(client.getAdresse());
		row.add(client.getNationalite());
		row.add(client.getTelephone());
		row.add(client.getTypeCarte());
		row.add(client.getNumCarte());
		row.add(client.getProfession());
		row.add(client.getAutreTypeClient());
		row.add(client.getAutreTypeCarte());
		row.add(client.getLieuDeNaiss());
		row.add(client.getCivilite());
		
		row.add(client);
		
		return row;
	}

	public static java.util.Vector<java.util.Vector<Object>> getData(java.util.List<models.beans.Client> list, boolean withOrder){
		return getData(list, withOrder, 0);
	}

	public static java.util.Vector<java.util.Vector<Object>> getData(java.util.List<models.beans.Client> list, boolean withOrder, int numRowBase){
		java.util.Vector<java.util.Vector<Object>> data = new java.util.Vector<java.util.Vector<Object>>();
		if (list == null)
			return data;
		
		int numRow = numRowBase;
		for (models.beans.Client client : list){
			if (withOrder)
				numRow++;
			
			data.add(getTableRow(client , numRow));
		}
		
		return data;
	}

	public static void exportToExcel(File file){
		exportToExcel(file, DAOClient.getListInstances());
	}

	public static void exportToExcel(File file, java.util.List<Client> list){
		try{
			WritableWorkbook workbook = Workbook.createWorkbook(file);
			WritableSheet sheet = workbook.createSheet( "Liste des ViewClients", 0);

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
			sheet.setColumnView(14, 20);
			
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
			label = new Label(5, 0, "", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(6, 0, "", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(7, 0, "", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(8, 0, "", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(9, 0, "", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(10, 0, "", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(11, 0, "", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(12, 0, "", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(13, 0, "", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(14, 0, "", formatHeaderTableStr); sheet.addCell(label);
			
			int ligneBas = 1;
			for (Client client : list){
				int i = list.indexOf(client);
				label = new Label(0, ligneBas+i, String.valueOf(i), formatContentStr); sheet.addCell(label);
				label = new Label(1, ligneBas+i, client.getTypeClient().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(2, ligneBas+i, client.getNom().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(3, ligneBas+i, client.getPrenom().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(4, ligneBas+i, client.getDateNaiss().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(5, ligneBas+i, client.getAdresse().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(6, ligneBas+i, client.getNationalite().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(7, ligneBas+i, client.getTelephone().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(8, ligneBas+i, client.getTypeCarte().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(9, ligneBas+i, client.getNumCarte().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(10, ligneBas+i, client.getProfession().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(11, ligneBas+i, client.getAutreTypeClient().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(12, ligneBas+i, client.getAutreTypeCarte().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(13, ligneBas+i, client.getLieuDeNaiss().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(14, ligneBas+i, client.getCivilite().toString(), formatHeaderTableStr); sheet.addCell(label);
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
				
				Client client = new Client();

				String value = "";
				try{
					value = sheet.getCell(1, ligne-1).getContents().trim();
					client.setTypeClient(Client.TypeClient.getByValue(value));
					value = sheet.getCell(2, ligne-1).getContents().trim();
					client.setNom(value);
					value = sheet.getCell(3, ligne-1).getContents().trim();
					client.setPrenom(value);
					value = sheet.getCell(4, ligne-1).getContents().trim();
					client.setDateNaiss(utils.StringUtils.getDateFromString(value));
					value = sheet.getCell(5, ligne-1).getContents().trim();
					client.setAdresse(value);
					value = sheet.getCell(6, ligne-1).getContents().trim();
					client.setNationalite(value);
					value = sheet.getCell(7, ligne-1).getContents().trim();
					client.setTelephone(value);
					value = sheet.getCell(8, ligne-1).getContents().trim();
					client.setTypeCarte(Client.TypeCarte.getByValue(value));
					value = sheet.getCell(9, ligne-1).getContents().trim();
					client.setNumCarte(value);
					value = sheet.getCell(10, ligne-1).getContents().trim();
					client.setProfession(value);
					value = sheet.getCell(11, ligne-1).getContents().trim();
					client.setAutreTypeClient(value);
					value = sheet.getCell(12, ligne-1).getContents().trim();
					client.setAutreTypeCarte(value);
					value = sheet.getCell(13, ligne-1).getContents().trim();
					client.setLieuDeNaiss(value);
					value = sheet.getCell(14, ligne-1).getContents().trim();
					client.setCivilite(Client.Civilite.getByValue(value));
					queries.add(DAOClient.getSQLWriting(client));
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
		exportToPDF(file, DAOClient.getListInstances());
	}

	public static void exportToPDF(File file, java.util.List<Client> list){
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
				
				a = new float[14];
				a[0] = (float)(width * 0.071428575);
				a[1] = (float)(width * 0.071428575);
				a[2] = (float)(width * 0.071428575);
				a[3] = (float)(width * 0.071428575);
				a[4] = (float)(width * 0.071428575);
				a[5] = (float)(width * 0.071428575);
				a[6] = (float)(width * 0.071428575);
				a[7] = (float)(width * 0.071428575);
				a[8] = (float)(width * 0.071428575);
				a[9] = (float)(width * 0.071428575);
				a[10] = (float)(width * 0.071428575);
				a[11] = (float)(width * 0.071428575);
				a[12] = (float)(width * 0.071428575);
				a[13] = (float)(width * 0.071428575);
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
					Client client = null;
					if (index < list.size())
						client = list.get(index);
					
					cell = new PdfPCell(new Paragraph((client != null) ? client.getTypeClient().getValue() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((client != null) ? client.getNom().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((client != null) ? client.getPrenom().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((client != null) ? utils.StringUtils.formatDateFromMySQL(client.getDateNaiss().toString()) : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((client != null) ? client.getAdresse().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((client != null) ? client.getNationalite().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((client != null) ? client.getTelephone().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((client != null) ? client.getTypeCarte().getValue() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((client != null) ? client.getNumCarte().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((client != null) ? client.getProfession().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((client != null) ? client.getAutreTypeClient().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((client != null) ? client.getAutreTypeCarte().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((client != null) ? client.getLieuDeNaiss().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((client != null) ? client.getCivilite().getValue() : " ", fontTR10));
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
		exportToText(file, DAOClient.getListInstances());
	}

	public static void exportToText(File file, java.util.List<Client> list){
		try{
			
			
		}
		catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
	}

	public static void exportToWord(File file){
		exportToWord(file, DAOClient.getListInstances());
	}

	public static void exportToWord(File file, java.util.List<Client> list){
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
		
		javax.swing.JLabel labelTypeClient = new javax.swing.JLabel("");
		javax.swing.JLabel labelNom = new javax.swing.JLabel("");
		javax.swing.JLabel labelPrenom = new javax.swing.JLabel("");
		javax.swing.JLabel labelDateNaiss = new javax.swing.JLabel("");
		javax.swing.JLabel labelAdresse = new javax.swing.JLabel("");
		javax.swing.JLabel labelNationalite = new javax.swing.JLabel("");
		javax.swing.JLabel labelTelephone = new javax.swing.JLabel("");
		javax.swing.JLabel labelTypeCarte = new javax.swing.JLabel("");
		javax.swing.JLabel labelNumCarte = new javax.swing.JLabel("");
		javax.swing.JLabel labelProfession = new javax.swing.JLabel("");
		javax.swing.JLabel labelAutreTypeClient = new javax.swing.JLabel("");
		javax.swing.JLabel labelAutreTypeCarte = new javax.swing.JLabel("");
		javax.swing.JLabel labelLieuDeNaiss = new javax.swing.JLabel("");
		javax.swing.JLabel labelCivilite = new javax.swing.JLabel("");
		
		javax.swing.JComponent componentTypeClient = (javax.swing.JComboBox)views.ViewClient.getViewForTypeClient("JCOMBOBOXE");
		javax.swing.JComponent componentNom = views.ViewClient.getViewForNom();
		javax.swing.JComponent componentPrenom = views.ViewClient.getViewForPrenom();
		javax.swing.JComponent componentDateNaiss = views.ViewClient.getViewForDateNaiss();
		javax.swing.JComponent componentAdresse = views.ViewClient.getViewForAdresse();
		javax.swing.JComponent componentNationalite = views.ViewClient.getViewForNationalite();
		javax.swing.JComponent componentTelephone = views.ViewClient.getViewForTelephone();
		javax.swing.JComponent componentTypeCarte = (javax.swing.JComboBox)views.ViewClient.getViewForTypeCarte("JCOMBOBOXE");
		javax.swing.JComponent componentNumCarte = views.ViewClient.getViewForNumCarte();
		javax.swing.JComponent componentProfession = views.ViewClient.getViewForProfession();
		javax.swing.JComponent componentAutreTypeClient = views.ViewClient.getViewForAutreTypeClient();
		javax.swing.JComponent componentAutreTypeCarte = views.ViewClient.getViewForAutreTypeCarte();
		javax.swing.JComponent componentLieuDeNaiss = views.ViewClient.getViewForLieuDeNaiss();
		javax.swing.JComponent componentCivilite = (javax.swing.JComboBox)views.ViewClient.getViewForCivilite("JCOMBOBOXE");
		
		javax.swing.GroupLayout layout = (javax.swing.GroupLayout)panel.getLayout();
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGap(1, 1, Short.MAX_VALUE)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelTypeClient)
				.addComponent(labelNom)
				.addComponent(labelPrenom)
				.addComponent(labelDateNaiss)
				.addComponent(labelAdresse)
				.addComponent(labelNationalite)
				.addComponent(labelTelephone)
				.addComponent(labelTypeCarte)
				.addComponent(labelNumCarte)
				.addComponent(labelProfession)
				.addComponent(labelAutreTypeClient)
				.addComponent(labelAutreTypeCarte)
				.addComponent(labelLieuDeNaiss)
				.addComponent(labelCivilite)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(componentTypeClient, 300, 300, 300)
				.addComponent(componentNom, 300, 300, 300)
				.addComponent(componentPrenom, 300, 300, 300)
				.addComponent(componentDateNaiss, 300, 300, 300)
				.addComponent(componentAdresse, 300, 300, 300)
				.addComponent(componentNationalite, 300, 300, 300)
				.addComponent(componentTelephone, 300, 300, 300)
				.addComponent(componentTypeCarte, 300, 300, 300)
				.addComponent(componentNumCarte, 300, 300, 300)
				.addComponent(componentProfession, 300, 300, 300)
				.addComponent(componentAutreTypeClient, 300, 300, 300)
				.addComponent(componentAutreTypeCarte, 300, 300, 300)
				.addComponent(componentLieuDeNaiss, 300, 300, 300)
				.addComponent(componentCivilite, 300, 300, 300)
			)
			.addGap(1, 1, Short.MAX_VALUE)
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addGap(1, 1, Short.MAX_VALUE)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelTypeClient)
				.addComponent(componentTypeClient, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelNom)
				.addComponent(componentNom, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelPrenom)
				.addComponent(componentPrenom, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelDateNaiss)
				.addComponent(componentDateNaiss, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelAdresse)
				.addComponent(componentAdresse, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelNationalite)
				.addComponent(componentNationalite, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelTelephone)
				.addComponent(componentTelephone, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelTypeCarte)
				.addComponent(componentTypeCarte, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelNumCarte)
				.addComponent(componentNumCarte, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelProfession)
				.addComponent(componentProfession, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelAutreTypeClient)
				.addComponent(componentAutreTypeClient, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelAutreTypeCarte)
				.addComponent(componentAutreTypeCarte, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelLieuDeNaiss)
				.addComponent(componentLieuDeNaiss, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelCivilite)
				.addComponent(componentCivilite, 20, 20, 20)
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
		
		javax.swing.JLabel labelTypeClient = new javax.swing.JLabel("");
		javax.swing.JLabel labelNom = new javax.swing.JLabel("");
		javax.swing.JLabel labelPrenom = new javax.swing.JLabel("");
		javax.swing.JLabel labelDateNaiss = new javax.swing.JLabel("");
		javax.swing.JLabel labelAdresse = new javax.swing.JLabel("");
		javax.swing.JLabel labelNationalite = new javax.swing.JLabel("");
		javax.swing.JLabel labelTelephone = new javax.swing.JLabel("");
		javax.swing.JLabel labelTypeCarte = new javax.swing.JLabel("");
		javax.swing.JLabel labelNumCarte = new javax.swing.JLabel("");
		javax.swing.JLabel labelProfession = new javax.swing.JLabel("");
		javax.swing.JLabel labelAutreTypeClient = new javax.swing.JLabel("");
		javax.swing.JLabel labelAutreTypeCarte = new javax.swing.JLabel("");
		javax.swing.JLabel labelLieuDeNaiss = new javax.swing.JLabel("");
		javax.swing.JLabel labelCivilite = new javax.swing.JLabel("");
		
		javax.swing.JComponent componentTypeClient = (javax.swing.JComboBox)views.ViewClient.getViewForTypeClient("JCOMBOBOXE");
		javax.swing.JComponent componentNom = views.ViewClient.getViewForNom();
		javax.swing.JComponent componentPrenom = views.ViewClient.getViewForPrenom();
		javax.swing.JComponent componentDateNaiss = views.ViewClient.getViewForDateNaiss();
		javax.swing.JComponent componentAdresse = views.ViewClient.getViewForAdresse();
		javax.swing.JComponent componentNationalite = views.ViewClient.getViewForNationalite();
		javax.swing.JComponent componentTelephone = views.ViewClient.getViewForTelephone();
		javax.swing.JComponent componentTypeCarte = (javax.swing.JComboBox)views.ViewClient.getViewForTypeCarte("JCOMBOBOXE");
		javax.swing.JComponent componentNumCarte = views.ViewClient.getViewForNumCarte();
		javax.swing.JComponent componentProfession = views.ViewClient.getViewForProfession();
		javax.swing.JComponent componentAutreTypeClient = views.ViewClient.getViewForAutreTypeClient();
		javax.swing.JComponent componentAutreTypeCarte = views.ViewClient.getViewForAutreTypeCarte();
		javax.swing.JComponent componentLieuDeNaiss = views.ViewClient.getViewForLieuDeNaiss();
		javax.swing.JComponent componentCivilite = (javax.swing.JComboBox)views.ViewClient.getViewForCivilite("JCOMBOBOXE");

		gui.utils.GroupLayouter.LineForm lineForm;
		lineForm = new gui.utils.GroupLayouter.LineForm(labelTypeClient, componentTypeClient);
		lineForm.setHeight(25);
		mapLabelsFields.put("typeclient", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelNom, componentNom);
		lineForm.setHeight(25);
		mapLabelsFields.put("nom", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelPrenom, componentPrenom);
		lineForm.setHeight(25);
		mapLabelsFields.put("prenom", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelDateNaiss, componentDateNaiss);
		lineForm.setHeight(25);
		mapLabelsFields.put("datenaiss", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelAdresse, componentAdresse);
		lineForm.setHeight(25);
		mapLabelsFields.put("adresse", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelNationalite, componentNationalite);
		lineForm.setHeight(25);
		mapLabelsFields.put("nationalite", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelTelephone, componentTelephone);
		lineForm.setHeight(25);
		mapLabelsFields.put("telephone", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelTypeCarte, componentTypeCarte);
		lineForm.setHeight(25);
		mapLabelsFields.put("typecarte", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelNumCarte, componentNumCarte);
		lineForm.setHeight(25);
		mapLabelsFields.put("numcarte", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelProfession, componentProfession);
		lineForm.setHeight(25);
		mapLabelsFields.put("profession", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelAutreTypeClient, componentAutreTypeClient);
		lineForm.setHeight(25);
		mapLabelsFields.put("autretypeclient", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelAutreTypeCarte, componentAutreTypeCarte);
		lineForm.setHeight(25);
		mapLabelsFields.put("autretypecarte", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelLieuDeNaiss, componentLieuDeNaiss);
		lineForm.setHeight(25);
		mapLabelsFields.put("lieudenaiss", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelCivilite, componentCivilite);
		lineForm.setHeight(25);
		mapLabelsFields.put("civilite", lineForm);

		javax.swing.JComponent[][] matrice ={
			{labelTypeClient, componentTypeClient},
			{labelNom, componentNom},
			{labelPrenom, componentPrenom},
			{labelDateNaiss, componentDateNaiss},
			{labelAdresse, componentAdresse},
			{labelNationalite, componentNationalite},
			{labelTelephone, componentTelephone},
			{labelTypeCarte, componentTypeCarte},
			{labelNumCarte, componentNumCarte},
			{labelProfession, componentProfession},
			{labelAutreTypeClient, componentAutreTypeClient},
			{labelAutreTypeCarte, componentAutreTypeCarte},
			{labelLieuDeNaiss, componentLieuDeNaiss},
			{labelCivilite, componentCivilite},
		};

		gui.utils.GroupLayouter.Form form = new gui.utils.GroupLayouter.Form(matrice, 50, 50);
		gui.utils.GroupLayouter.createSimpleForm(panel, form);		
		return mapLabelsFields;
	}

	public static class DlgAddModifyClient extends javax.swing.JDialog{
		private static final long serialVersionUID = 1L;
		
		protected javax.swing.JPanel		pFormulaire;
		protected java.util.Map<String, gui.utils.GroupLayouter.LineForm>	mapAttributLineForm;
		protected javax.swing.JSeparator	sepBottom;
		protected javax.swing.JButton		bValidate, bCancel;
		
		protected models.beans.Client client = null;
		
		public DlgAddModifyClient(java.awt.Window parent, String title){
			this(parent, title, null);
		}
		
		public DlgAddModifyClient(java.awt.Window parent, String title, models.beans.Client client){
			super(parent);
			initComponents();
			this.setClient(client);
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
			Client client = this.getClient();
			if (client == null){
				client = new Client();
			}
			
			client.setTypeClient(models.beans.Client.TypeClient.getByValue(mapAttributLineForm.get("typeclient").getValueAsString()));
			client.setNom(mapAttributLineForm.get("nom").getValueAsString());
			client.setPrenom(mapAttributLineForm.get("prenom").getValueAsString());
			client.setDateNaiss(mapAttributLineForm.get("datenaiss").getValueAsDate());
			client.setAdresse(mapAttributLineForm.get("adresse").getValueAsString());
			client.setNationalite(mapAttributLineForm.get("nationalite").getValueAsString());
			client.setTelephone(mapAttributLineForm.get("telephone").getValueAsString());
			client.setTypeCarte(models.beans.Client.TypeCarte.getByValue(mapAttributLineForm.get("typecarte").getValueAsString()));
			client.setNumCarte(mapAttributLineForm.get("numcarte").getValueAsString());
			client.setProfession(mapAttributLineForm.get("profession").getValueAsString());
			client.setAutreTypeClient(mapAttributLineForm.get("autretypeclient").getValueAsString());
			client.setAutreTypeCarte(mapAttributLineForm.get("autretypecarte").getValueAsString());
			client.setLieuDeNaiss(mapAttributLineForm.get("lieudenaiss").getValueAsString());
			client.setCivilite(models.beans.Client.Civilite.getByValue(mapAttributLineForm.get("civilite").getValueAsString()));
			
			client.setId((Integer)communication.SocketCommunicator.getInstance().sendQuery(client));
			this.setClient(client);
			
			this.setVisible(false);
		}
		
		protected void bCancelActionPerformed(){
			this.setClient(null);
			this.setVisible(false);
		}
		
		public void setClient(models.beans.Client client) {
			this.client = client;
			updateForm();
		}
		
		public models.beans.Client getClient() {
			return this.client;
		}
		
		protected void emptyForm(){
			mapAttributLineForm.get("typeclient").setValue(null);
			mapAttributLineForm.get("nom").setValue(null);
			mapAttributLineForm.get("prenom").setValue(null);
			mapAttributLineForm.get("datenaiss").setValue(null);
			mapAttributLineForm.get("adresse").setValue(null);
			mapAttributLineForm.get("nationalite").setValue(null);
			mapAttributLineForm.get("telephone").setValue(null);
			mapAttributLineForm.get("typecarte").setValue(null);
			mapAttributLineForm.get("numcarte").setValue(null);
			mapAttributLineForm.get("profession").setValue(null);
			mapAttributLineForm.get("autretypeclient").setValue(null);
			mapAttributLineForm.get("autretypecarte").setValue(null);
			mapAttributLineForm.get("lieudenaiss").setValue(null);
			mapAttributLineForm.get("civilite").setValue(null);
		}
		
		protected void updateForm(){
			if (client == null){
				emptyForm();
				return;
			}
			
			mapAttributLineForm.get("typeclient").setValue(client.getTypeClient().getValue());
			mapAttributLineForm.get("nom").setValue(client.getNom());
			mapAttributLineForm.get("prenom").setValue(client.getPrenom());
			mapAttributLineForm.get("datenaiss").setValue(client.getDateNaiss().toString());
			mapAttributLineForm.get("adresse").setValue(client.getAdresse());
			mapAttributLineForm.get("nationalite").setValue(client.getNationalite());
			mapAttributLineForm.get("telephone").setValue(client.getTelephone());
			mapAttributLineForm.get("typecarte").setValue(client.getTypeCarte().getValue());
			mapAttributLineForm.get("numcarte").setValue(client.getNumCarte());
			mapAttributLineForm.get("profession").setValue(client.getProfession());
			mapAttributLineForm.get("autretypeclient").setValue(client.getAutreTypeClient());
			mapAttributLineForm.get("autretypecarte").setValue(client.getAutreTypeCarte());
			mapAttributLineForm.get("lieudenaiss").setValue(client.getLieuDeNaiss());
			mapAttributLineForm.get("civilite").setValue(client.getCivilite().getValue());
		}
	}
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}