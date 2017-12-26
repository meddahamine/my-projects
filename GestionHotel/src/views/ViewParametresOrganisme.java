package views;

import java.io.File;

import models.daos.client.DAOParametresOrganisme;
import models.beans.ParametresOrganisme;

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

public abstract class ViewParametresOrganisme {
	
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

	public static javax.swing.JTextField getViewForDesignationOrganisme(){
		return gui.utils.GUIFieldFactory.createSimpleTextField(200);
	}

	public static String getCaptionForDesignationOrganisme(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("Désignation de l'Organisme", userParameters.getLang().getCodeLang());
		}

		return "Désignation de l'Organisme";
	}

	public static javax.swing.JTextField getViewForRaisonSocial(){
		return gui.utils.GUIFieldFactory.createSimpleTextField(100);
	}

	public static String getCaptionForRaisonSocial(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("Raison Social", userParameters.getLang().getCodeLang());
		}

		return "Raison Social";
	}

	public static javax.swing.JTextField getViewForCapitalSocial(){
		return gui.utils.GUIFieldFactory.createSimpleTextField(30);
	}

	public static String getCaptionForCapitalSocial(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("Capital Social", userParameters.getLang().getCodeLang());
		}

		return "Capital Social";
	}

	public static javax.swing.JTextField getViewForNumRC(){
		return gui.utils.GUIFieldFactory.createSimpleTextField(50);
	}

	public static String getCaptionForNumRC(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("N? de Registre Commercial", userParameters.getLang().getCodeLang());
		}

		return "N? de Registre Commercial";
	}

	public static javax.swing.JTextField getViewForNumCB(){
		return gui.utils.GUIFieldFactory.createSimpleTextField(50);
	}

	public static String getCaptionForNumCB(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("N? du Compte Bancaire", userParameters.getLang().getCodeLang());
		}

		return "N? du Compte Bancaire";
	}

	public static javax.swing.JTextField getViewForIdentificationFiscale(){
		return gui.utils.GUIFieldFactory.createSimpleTextField(50);
	}

	public static String getCaptionForIdentificationFiscale(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("Identification Fiscale", userParameters.getLang().getCodeLang());
		}

		return "Identification Fiscale";
	}

	public static javax.swing.JTextField getViewForNumArticle(){
		return gui.utils.GUIFieldFactory.createSimpleTextField(50);
	}

	public static String getCaptionForNumArticle(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("N? Article", userParameters.getLang().getCodeLang());
		}

		return "N? Article";
	}

	public static javax.swing.JTextField getViewForNIS(){
		return gui.utils.GUIFieldFactory.createSimpleTextField(20);
	}

	public static String getCaptionForNIS(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("NIS", userParameters.getLang().getCodeLang());
		}

		return "NIS";
	}

	public static javax.swing.JTextField getViewForAdresse(){
		return gui.utils.GUIFieldFactory.createSimpleTextField(100);
	}

	public static String getCaptionForAdresse(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("Adresse", userParameters.getLang().getCodeLang());
		}

		return "Adresse";
	}

	public static javax.swing.JTextField getViewForBoitePostale(){
		return gui.utils.GUIFieldFactory.createSimpleTextField(50);
	}

	public static String getCaptionForBoitePostale(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("Boite Postale", userParameters.getLang().getCodeLang());
		}

		return "Boite Postale";
	}

	public static javax.swing.JTextField getViewForNumTel(){
		return gui.utils.GUIFieldFactory.createSimpleTextField(17);
	}

	public static String getCaptionForNumTel(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("TEL", userParameters.getLang().getCodeLang());
		}

		return "TEL";
	}

	public static javax.swing.JTextField getViewForNumFax(){
		return gui.utils.GUIFieldFactory.createSimpleTextField(17);
	}

	public static String getCaptionForNumFax(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("FAX", userParameters.getLang().getCodeLang());
		}

		return "FAX";
	}

	public static javax.swing.JTextField getViewForEmail(){
		return gui.utils.GUIFieldFactory.createSimpleTextField(40);
	}

	public static String getCaptionForEmail(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("Email", userParameters.getLang().getCodeLang());
		}

		return "Email";
	}

	public static javax.swing.JScrollPane getViewForDescriptif(){
		return gui.utils.GUIFieldFactory.createTextAreaWithScroll();
	}

	public static String getCaptionForDescriptif(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("Descriptif de l'Organisme", userParameters.getLang().getCodeLang());
		}

		return "Descriptif de l'Organisme";
	}

	public static javax.swing.JComboBox getViewForPhoto(){
		java.util.List<models.beans.Photo> list = models.daos.client.DAOPhoto.getListInstances();
		javax.swing.JComboBox view = new gui.utils.GUIPanelFactory.JEditedComboBox(list.toArray());
		view.insertItemAt("Choisir Photo", 0);
		return view;
	}

	public static String getCaptionForPhoto(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("Logo de l'entreprerise", userParameters.getLang().getCodeLang());
		}

		return "Logo de l'entreprerise";
	}

	public static java.util.Vector<Object> getTableHeader(boolean withNumero){
		java.util.Vector<Object> header = new java.util.Vector<Object>();
		
		if (withNumero)
			header.add("N°");
		header.add("Désignation de l'Organisme");
		header.add("Raison Social");
		header.add("Capital Social");
		header.add("N? de Registre Commercial");
		header.add("N? du Compte Bancaire");
		header.add("Identification Fiscale");
		header.add("N? Article");
		header.add("NIS");
		header.add("Adresse");
		header.add("Boite Postale");
		header.add("TEL");
		header.add("FAX");
		header.add("Email");
		header.add("Descriptif de l'Organisme");
		header.add("Logo de l'entreprerise");
		
		return header;
	}

	public static java.util.Vector<Object> getTableRow(models.beans.ParametresOrganisme parametresOrganisme, int numOrder){
		java.util.Vector<Object> row = new java.util.Vector<Object>();
		
		if (numOrder > 0)
			row.add(numOrder);
		
		row.add(parametresOrganisme.getDesignationOrganisme());
		row.add(parametresOrganisme.getRaisonSocial());
		row.add(parametresOrganisme.getCapitalSocial());
		row.add(parametresOrganisme.getNumRC());
		row.add(parametresOrganisme.getNumCB());
		row.add(parametresOrganisme.getIdentificationFiscale());
		row.add(parametresOrganisme.getNumArticle());
		row.add(parametresOrganisme.getNIS());
		row.add(parametresOrganisme.getAdresse());
		row.add(parametresOrganisme.getBoitePostale());
		row.add(parametresOrganisme.getNumTel());
		row.add(parametresOrganisme.getNumFax());
		row.add(parametresOrganisme.getEmail());
		row.add(parametresOrganisme.getDescriptif());
		row.add(parametresOrganisme.getPhoto());
		
		row.add(parametresOrganisme);
		
		return row;
	}

	public static java.util.Vector<java.util.Vector<Object>> getData(java.util.List<models.beans.ParametresOrganisme> list, boolean withOrder){
		return getData(list, withOrder, 0);
	}

	public static java.util.Vector<java.util.Vector<Object>> getData(java.util.List<models.beans.ParametresOrganisme> list, boolean withOrder, int numRowBase){
		java.util.Vector<java.util.Vector<Object>> data = new java.util.Vector<java.util.Vector<Object>>();
		if (list == null)
			return data;
		
		int numRow = numRowBase;
		for (models.beans.ParametresOrganisme parametresOrganisme : list){
			if (withOrder)
				numRow++;
			
			data.add(getTableRow(parametresOrganisme , numRow));
		}
		
		return data;
	}

	public static void exportToExcel(File file){
		exportToExcel(file, DAOParametresOrganisme.getListInstances());
	}

	public static void exportToExcel(File file, java.util.List<ParametresOrganisme> list){
		try{
			WritableWorkbook workbook = Workbook.createWorkbook(file);
			WritableSheet sheet = workbook.createSheet( "Liste des ViewParametresOrganismes", 0);

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

			Label label = new Label(0, 0, "N°", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(1, 0, "Désignation de l'Organisme", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(2, 0, "Raison Social", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(3, 0, "Capital Social", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(4, 0, "N? de Registre Commercial", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(5, 0, "N? du Compte Bancaire", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(6, 0, "Identification Fiscale", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(7, 0, "N? Article", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(8, 0, "NIS", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(9, 0, "Adresse", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(10, 0, "Boite Postale", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(11, 0, "TEL", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(12, 0, "FAX", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(13, 0, "Email", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(14, 0, "Descriptif de l'Organisme", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(15, 0, "Logo de l'entreprerise", formatHeaderTableStr); sheet.addCell(label);
			
			int ligneBas = 1;
			for (ParametresOrganisme parametresOrganisme : list){
				int i = list.indexOf(parametresOrganisme);
				label = new Label(0, ligneBas+i, String.valueOf(i), formatContentStr); sheet.addCell(label);
				label = new Label(1, ligneBas+i, parametresOrganisme.getDesignationOrganisme().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(2, ligneBas+i, parametresOrganisme.getRaisonSocial().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(3, ligneBas+i, parametresOrganisme.getCapitalSocial().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(4, ligneBas+i, parametresOrganisme.getNumRC().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(5, ligneBas+i, parametresOrganisme.getNumCB().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(6, ligneBas+i, parametresOrganisme.getIdentificationFiscale().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(7, ligneBas+i, parametresOrganisme.getNumArticle().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(8, ligneBas+i, parametresOrganisme.getNIS().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(9, ligneBas+i, parametresOrganisme.getAdresse().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(10, ligneBas+i, parametresOrganisme.getBoitePostale().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(11, ligneBas+i, parametresOrganisme.getNumTel().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(12, ligneBas+i, parametresOrganisme.getNumFax().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(13, ligneBas+i, parametresOrganisme.getEmail().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(14, ligneBas+i, parametresOrganisme.getDescriptif().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(15, ligneBas+i, parametresOrganisme.getIdPhoto().toString(), formatHeaderTableStr); sheet.addCell(label);
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
				
				ParametresOrganisme parametresOrganisme = new ParametresOrganisme();

				String value = "";
				try{
					value = sheet.getCell(1, ligne-1).getContents().trim();
					parametresOrganisme.setDesignationOrganisme(value);
					value = sheet.getCell(2, ligne-1).getContents().trim();
					parametresOrganisme.setRaisonSocial(value);
					value = sheet.getCell(3, ligne-1).getContents().trim();
					parametresOrganisme.setCapitalSocial(value);
					value = sheet.getCell(4, ligne-1).getContents().trim();
					parametresOrganisme.setNumRC(value);
					value = sheet.getCell(5, ligne-1).getContents().trim();
					parametresOrganisme.setNumCB(value);
					value = sheet.getCell(6, ligne-1).getContents().trim();
					parametresOrganisme.setIdentificationFiscale(value);
					value = sheet.getCell(7, ligne-1).getContents().trim();
					parametresOrganisme.setNumArticle(value);
					value = sheet.getCell(8, ligne-1).getContents().trim();
					parametresOrganisme.setNIS(value);
					value = sheet.getCell(9, ligne-1).getContents().trim();
					parametresOrganisme.setAdresse(value);
					value = sheet.getCell(10, ligne-1).getContents().trim();
					parametresOrganisme.setBoitePostale(value);
					value = sheet.getCell(11, ligne-1).getContents().trim();
					parametresOrganisme.setNumTel(value);
					value = sheet.getCell(12, ligne-1).getContents().trim();
					parametresOrganisme.setNumFax(value);
					value = sheet.getCell(13, ligne-1).getContents().trim();
					parametresOrganisme.setEmail(value);
					value = sheet.getCell(14, ligne-1).getContents().trim();
					parametresOrganisme.setDescriptif(value);
					value = sheet.getCell(15, ligne-1).getContents().trim();
					parametresOrganisme.setIdPhoto(Integer.parseInt(value));
					queries.add(DAOParametresOrganisme.getSQLWriting(parametresOrganisme));
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
		exportToPDF(file, DAOParametresOrganisme.getListInstances());
	}

	public static void exportToPDF(File file, java.util.List<ParametresOrganisme> list){
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
				Paragraph header = new Paragraph("Paramètres de l'Organisme", fontHVCA15_IB);
				header.setAlignment(Paragraph.ALIGN_RIGHT);
				header.setSpacingAfter(5);
				docPDF.add(header);
				
				LineSeparator line = new LineSeparator();
				docPDF.add(line);
				
				PdfPCell cell;
				PdfPTable pdfTable;
				float[] a;
				float width = PageSize.A4.getWidth() - 100;
				
				a = new float[15];
				a[0] = (float)(width * 0.06666667);
				a[1] = (float)(width * 0.06666667);
				a[2] = (float)(width * 0.06666667);
				a[3] = (float)(width * 0.06666667);
				a[4] = (float)(width * 0.06666667);
				a[5] = (float)(width * 0.06666667);
				a[6] = (float)(width * 0.06666667);
				a[7] = (float)(width * 0.06666667);
				a[8] = (float)(width * 0.06666667);
				a[9] = (float)(width * 0.06666667);
				a[10] = (float)(width * 0.06666667);
				a[11] = (float)(width * 0.06666667);
				a[12] = (float)(width * 0.06666667);
				a[13] = (float)(width * 0.06666667);
				a[14] = (float)(width * 0.06666667);
				pdfTable = new PdfPTable(a.length);
				
				pdfTable.setWidthPercentage (a, PageSize.A4);
				pdfTable.setHorizontalAlignment(Element.ALIGN_CENTER);
				
				cell = new PdfPCell(new Paragraph("Désignation de l'Organisme", fontTR10_B_WHITE));
				cell.setBackgroundColor(BaseColor.BLACK);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingBottom(5);
				pdfTable.addCell(cell);

				cell = new PdfPCell(new Paragraph("Raison Social", fontTR10_B_WHITE));
				cell.setBackgroundColor(BaseColor.BLACK);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingBottom(5);
				pdfTable.addCell(cell);

				cell = new PdfPCell(new Paragraph("Capital Social", fontTR10_B_WHITE));
				cell.setBackgroundColor(BaseColor.BLACK);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingBottom(5);
				pdfTable.addCell(cell);

				cell = new PdfPCell(new Paragraph("N? de Registre Commercial", fontTR10_B_WHITE));
				cell.setBackgroundColor(BaseColor.BLACK);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingBottom(5);
				pdfTable.addCell(cell);

				cell = new PdfPCell(new Paragraph("N? du Compte Bancaire", fontTR10_B_WHITE));
				cell.setBackgroundColor(BaseColor.BLACK);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingBottom(5);
				pdfTable.addCell(cell);

				cell = new PdfPCell(new Paragraph("Identification Fiscale", fontTR10_B_WHITE));
				cell.setBackgroundColor(BaseColor.BLACK);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingBottom(5);
				pdfTable.addCell(cell);

				cell = new PdfPCell(new Paragraph("N? Article", fontTR10_B_WHITE));
				cell.setBackgroundColor(BaseColor.BLACK);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingBottom(5);
				pdfTable.addCell(cell);

				cell = new PdfPCell(new Paragraph("NIS", fontTR10_B_WHITE));
				cell.setBackgroundColor(BaseColor.BLACK);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingBottom(5);
				pdfTable.addCell(cell);

				cell = new PdfPCell(new Paragraph("Adresse", fontTR10_B_WHITE));
				cell.setBackgroundColor(BaseColor.BLACK);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingBottom(5);
				pdfTable.addCell(cell);

				cell = new PdfPCell(new Paragraph("Boite Postale", fontTR10_B_WHITE));
				cell.setBackgroundColor(BaseColor.BLACK);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingBottom(5);
				pdfTable.addCell(cell);

				cell = new PdfPCell(new Paragraph("TEL", fontTR10_B_WHITE));
				cell.setBackgroundColor(BaseColor.BLACK);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingBottom(5);
				pdfTable.addCell(cell);

				cell = new PdfPCell(new Paragraph("FAX", fontTR10_B_WHITE));
				cell.setBackgroundColor(BaseColor.BLACK);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingBottom(5);
				pdfTable.addCell(cell);

				cell = new PdfPCell(new Paragraph("Email", fontTR10_B_WHITE));
				cell.setBackgroundColor(BaseColor.BLACK);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingBottom(5);
				pdfTable.addCell(cell);

				cell = new PdfPCell(new Paragraph("Descriptif de l'Organisme", fontTR10_B_WHITE));
				cell.setBackgroundColor(BaseColor.BLACK);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingBottom(5);
				pdfTable.addCell(cell);

				cell = new PdfPCell(new Paragraph("Logo de l'entreprerise", fontTR10_B_WHITE));
				cell.setBackgroundColor(BaseColor.BLACK);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingBottom(5);
				pdfTable.addCell(cell);

				
				fontTR10_B.setColor(BaseColor.BLACK);
				for (int i=0; i<maxItemsAtPage; i++){
					int index = i + numPage * maxItemsAtPage;
					ParametresOrganisme parametresOrganisme = null;
					if (index < list.size())
						parametresOrganisme = list.get(index);
					
					cell = new PdfPCell(new Paragraph((parametresOrganisme != null) ? parametresOrganisme.getDesignationOrganisme().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((parametresOrganisme != null) ? parametresOrganisme.getRaisonSocial().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((parametresOrganisme != null) ? parametresOrganisme.getCapitalSocial().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((parametresOrganisme != null) ? parametresOrganisme.getNumRC().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((parametresOrganisme != null) ? parametresOrganisme.getNumCB().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((parametresOrganisme != null) ? parametresOrganisme.getIdentificationFiscale().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((parametresOrganisme != null) ? parametresOrganisme.getNumArticle().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((parametresOrganisme != null) ? parametresOrganisme.getNIS().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((parametresOrganisme != null) ? parametresOrganisme.getAdresse().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((parametresOrganisme != null) ? parametresOrganisme.getBoitePostale().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((parametresOrganisme != null) ? parametresOrganisme.getNumTel().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((parametresOrganisme != null) ? parametresOrganisme.getNumFax().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((parametresOrganisme != null) ? parametresOrganisme.getEmail().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((parametresOrganisme != null) ? parametresOrganisme.getDescriptif().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((parametresOrganisme != null) ? (parametresOrganisme.getPhoto() == null ? "" : parametresOrganisme.getPhoto().toString()) : " ", fontTR10));
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
		exportToText(file, DAOParametresOrganisme.getListInstances());
	}

	public static void exportToText(File file, java.util.List<ParametresOrganisme> list){
		try{
			
			
		}
		catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
	}

	public static void exportToWord(File file){
		exportToWord(file, DAOParametresOrganisme.getListInstances());
	}

	public static void exportToWord(File file, java.util.List<ParametresOrganisme> list){
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
		
		javax.swing.JLabel labelDesignationOrganisme = new javax.swing.JLabel("Désignation de l'Organisme");
		javax.swing.JLabel labelRaisonSocial = new javax.swing.JLabel("Raison Social");
		javax.swing.JLabel labelCapitalSocial = new javax.swing.JLabel("Capital Social");
		javax.swing.JLabel labelNumRC = new javax.swing.JLabel("N? de Registre Commercial");
		javax.swing.JLabel labelNumCB = new javax.swing.JLabel("N? du Compte Bancaire");
		javax.swing.JLabel labelentificationFiscale = new javax.swing.JLabel("Identification Fiscale");
		javax.swing.JLabel labelNumArticle = new javax.swing.JLabel("N? Article");
		javax.swing.JLabel labelNIS = new javax.swing.JLabel("NIS");
		javax.swing.JLabel labelAdresse = new javax.swing.JLabel("Adresse");
		javax.swing.JLabel labelBoitePostale = new javax.swing.JLabel("Boite Postale");
		javax.swing.JLabel labelNumTel = new javax.swing.JLabel("TEL");
		javax.swing.JLabel labelNumFax = new javax.swing.JLabel("FAX");
		javax.swing.JLabel labelEmail = new javax.swing.JLabel("Email");
		javax.swing.JLabel labelDescriptif = new javax.swing.JLabel("Descriptif de l'Organisme");
		javax.swing.JLabel labelPhoto = new javax.swing.JLabel("Logo de l'entreprerise");
		
		javax.swing.JComponent componentDesignationOrganisme = views.ViewParametresOrganisme.getViewForDesignationOrganisme();
		javax.swing.JComponent componentRaisonSocial = views.ViewParametresOrganisme.getViewForRaisonSocial();
		javax.swing.JComponent componentCapitalSocial = views.ViewParametresOrganisme.getViewForCapitalSocial();
		javax.swing.JComponent componentNumRC = views.ViewParametresOrganisme.getViewForNumRC();
		javax.swing.JComponent componentNumCB = views.ViewParametresOrganisme.getViewForNumCB();
		javax.swing.JComponent componentIdentificationFiscale = views.ViewParametresOrganisme.getViewForIdentificationFiscale();
		javax.swing.JComponent componentNumArticle = views.ViewParametresOrganisme.getViewForNumArticle();
		javax.swing.JComponent componentNIS = views.ViewParametresOrganisme.getViewForNIS();
		javax.swing.JComponent componentAdresse = views.ViewParametresOrganisme.getViewForAdresse();
		javax.swing.JComponent componentBoitePostale = views.ViewParametresOrganisme.getViewForBoitePostale();
		javax.swing.JComponent componentNumTel = views.ViewParametresOrganisme.getViewForNumTel();
		javax.swing.JComponent componentNumFax = views.ViewParametresOrganisme.getViewForNumFax();
		javax.swing.JComponent componentEmail = views.ViewParametresOrganisme.getViewForEmail();
		javax.swing.JComponent componentDescriptif = views.ViewParametresOrganisme.getViewForDescriptif();
		javax.swing.JComponent componentPhoto = views.ViewParametresOrganisme.getViewForPhoto();
		
		javax.swing.GroupLayout layout = (javax.swing.GroupLayout)panel.getLayout();
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGap(1, 1, Short.MAX_VALUE)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelDesignationOrganisme)
				.addComponent(labelRaisonSocial)
				.addComponent(labelCapitalSocial)
				.addComponent(labelNumRC)
				.addComponent(labelNumCB)
				.addComponent(labelentificationFiscale)
				.addComponent(labelNumArticle)
				.addComponent(labelNIS)
				.addComponent(labelAdresse)
				.addComponent(labelBoitePostale)
				.addComponent(labelNumTel)
				.addComponent(labelNumFax)
				.addComponent(labelEmail)
				.addComponent(labelDescriptif)
				.addComponent(labelPhoto)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
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
				.addComponent(componentPhoto, 300, 300, 300)
			)
			.addGap(1, 1, Short.MAX_VALUE)
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addGap(1, 1, Short.MAX_VALUE)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelDesignationOrganisme)
				.addComponent(componentDesignationOrganisme, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelRaisonSocial)
				.addComponent(componentRaisonSocial, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelCapitalSocial)
				.addComponent(componentCapitalSocial, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelNumRC)
				.addComponent(componentNumRC, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelNumCB)
				.addComponent(componentNumCB, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelentificationFiscale)
				.addComponent(componentIdentificationFiscale, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelNumArticle)
				.addComponent(componentNumArticle, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelNIS)
				.addComponent(componentNIS, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelAdresse)
				.addComponent(componentAdresse, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelBoitePostale)
				.addComponent(componentBoitePostale, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelNumTel)
				.addComponent(componentNumTel, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelNumFax)
				.addComponent(componentNumFax, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelEmail)
				.addComponent(componentEmail, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelDescriptif)
				.addComponent(componentDescriptif, 20, 20, 20)
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
		
		javax.swing.JLabel labelDesignationOrganisme = new javax.swing.JLabel("Désignation de l'Organisme");
		javax.swing.JLabel labelRaisonSocial = new javax.swing.JLabel("Raison Social");
		javax.swing.JLabel labelCapitalSocial = new javax.swing.JLabel("Capital Social");
		javax.swing.JLabel labelNumRC = new javax.swing.JLabel("N? de Registre Commercial");
		javax.swing.JLabel labelNumCB = new javax.swing.JLabel("N? du Compte Bancaire");
		javax.swing.JLabel labelentificationFiscale = new javax.swing.JLabel("Identification Fiscale");
		javax.swing.JLabel labelNumArticle = new javax.swing.JLabel("N? Article");
		javax.swing.JLabel labelNIS = new javax.swing.JLabel("NIS");
		javax.swing.JLabel labelAdresse = new javax.swing.JLabel("Adresse");
		javax.swing.JLabel labelBoitePostale = new javax.swing.JLabel("Boite Postale");
		javax.swing.JLabel labelNumTel = new javax.swing.JLabel("TEL");
		javax.swing.JLabel labelNumFax = new javax.swing.JLabel("FAX");
		javax.swing.JLabel labelEmail = new javax.swing.JLabel("Email");
		javax.swing.JLabel labelDescriptif = new javax.swing.JLabel("Descriptif de l'Organisme");
		javax.swing.JLabel labelPhoto = new javax.swing.JLabel("Logo de l'entreprerise");
		
		javax.swing.JComponent componentDesignationOrganisme = views.ViewParametresOrganisme.getViewForDesignationOrganisme();
		javax.swing.JComponent componentRaisonSocial = views.ViewParametresOrganisme.getViewForRaisonSocial();
		javax.swing.JComponent componentCapitalSocial = views.ViewParametresOrganisme.getViewForCapitalSocial();
		javax.swing.JComponent componentNumRC = views.ViewParametresOrganisme.getViewForNumRC();
		javax.swing.JComponent componentNumCB = views.ViewParametresOrganisme.getViewForNumCB();
		javax.swing.JComponent componentIdentificationFiscale = views.ViewParametresOrganisme.getViewForIdentificationFiscale();
		javax.swing.JComponent componentNumArticle = views.ViewParametresOrganisme.getViewForNumArticle();
		javax.swing.JComponent componentNIS = views.ViewParametresOrganisme.getViewForNIS();
		javax.swing.JComponent componentAdresse = views.ViewParametresOrganisme.getViewForAdresse();
		javax.swing.JComponent componentBoitePostale = views.ViewParametresOrganisme.getViewForBoitePostale();
		javax.swing.JComponent componentNumTel = views.ViewParametresOrganisme.getViewForNumTel();
		javax.swing.JComponent componentNumFax = views.ViewParametresOrganisme.getViewForNumFax();
		javax.swing.JComponent componentEmail = views.ViewParametresOrganisme.getViewForEmail();
		javax.swing.JComponent componentDescriptif = views.ViewParametresOrganisme.getViewForDescriptif();
		javax.swing.JComponent componentPhoto = views.ViewParametresOrganisme.getViewForPhoto();

		gui.utils.GroupLayouter.LineForm lineForm;
		lineForm = new gui.utils.GroupLayouter.LineForm(labelDesignationOrganisme, componentDesignationOrganisme);
		lineForm.setHeight(25);
		mapLabelsFields.put("designationorganisme", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelRaisonSocial, componentRaisonSocial);
		lineForm.setHeight(25);
		mapLabelsFields.put("raisonsocial", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelCapitalSocial, componentCapitalSocial);
		lineForm.setHeight(25);
		mapLabelsFields.put("capitalsocial", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelNumRC, componentNumRC);
		lineForm.setHeight(25);
		mapLabelsFields.put("numrc", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelNumCB, componentNumCB);
		lineForm.setHeight(25);
		mapLabelsFields.put("numcb", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelentificationFiscale, componentIdentificationFiscale);
		lineForm.setHeight(25);
		mapLabelsFields.put("entificationfiscale", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelNumArticle, componentNumArticle);
		lineForm.setHeight(25);
		mapLabelsFields.put("numarticle", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelNIS, componentNIS);
		lineForm.setHeight(25);
		mapLabelsFields.put("nis", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelAdresse, componentAdresse);
		lineForm.setHeight(25);
		mapLabelsFields.put("adresse", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelBoitePostale, componentBoitePostale);
		lineForm.setHeight(25);
		mapLabelsFields.put("boitepostale", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelNumTel, componentNumTel);
		lineForm.setHeight(25);
		mapLabelsFields.put("numtel", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelNumFax, componentNumFax);
		lineForm.setHeight(25);
		mapLabelsFields.put("numfax", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelEmail, componentEmail);
		lineForm.setHeight(25);
		mapLabelsFields.put("email", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelDescriptif, componentDescriptif);
		lineForm.setHeight(25);
		mapLabelsFields.put("descriptif", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelPhoto, componentPhoto);
		lineForm.setHeight(25);
		mapLabelsFields.put("photo", lineForm);

		javax.swing.JComponent[][] matrice ={
			{labelDesignationOrganisme, componentDesignationOrganisme},
			{labelRaisonSocial, componentRaisonSocial},
			{labelCapitalSocial, componentCapitalSocial},
			{labelNumRC, componentNumRC},
			{labelNumCB, componentNumCB},
			{labelentificationFiscale, componentIdentificationFiscale},
			{labelNumArticle, componentNumArticle},
			{labelNIS, componentNIS},
			{labelAdresse, componentAdresse},
			{labelBoitePostale, componentBoitePostale},
			{labelNumTel, componentNumTel},
			{labelNumFax, componentNumFax},
			{labelEmail, componentEmail},
			{labelDescriptif, componentDescriptif},
			{labelPhoto, componentPhoto},
		};

		gui.utils.GroupLayouter.Form form = new gui.utils.GroupLayouter.Form(matrice, 50, 50);
		gui.utils.GroupLayouter.createSimpleForm(panel, form);		
		return mapLabelsFields;
	}

	public static class DlgAddModifyParametresOrganisme extends javax.swing.JDialog{
		private static final long serialVersionUID = 1L;
		
		protected javax.swing.JPanel		pFormulaire;
		protected java.util.Map<String, gui.utils.GroupLayouter.LineForm>	mapAttributLineForm;
		protected javax.swing.JSeparator	sepBottom;
		protected javax.swing.JButton		bValidate, bCancel;
		
		protected models.beans.ParametresOrganisme parametresOrganisme = null;
		
		public DlgAddModifyParametresOrganisme(java.awt.Window parent, String title){
			this(parent, title, null);
		}
		
		public DlgAddModifyParametresOrganisme(java.awt.Window parent, String title, models.beans.ParametresOrganisme parametresOrganisme){
			super(parent);
			initComponents();
			this.setParametresOrganisme(parametresOrganisme);
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
			ParametresOrganisme parametresOrganisme = this.getParametresOrganisme();
			if (parametresOrganisme == null){
				parametresOrganisme = new ParametresOrganisme();
			}
			
			parametresOrganisme.setDesignationOrganisme(mapAttributLineForm.get("designationorganisme").getValueAsString());
			parametresOrganisme.setRaisonSocial(mapAttributLineForm.get("raisonsocial").getValueAsString());
			parametresOrganisme.setCapitalSocial(mapAttributLineForm.get("capitalsocial").getValueAsString());
			parametresOrganisme.setNumRC(mapAttributLineForm.get("numrc").getValueAsString());
			parametresOrganisme.setNumCB(mapAttributLineForm.get("numcb").getValueAsString());
			parametresOrganisme.setIdentificationFiscale(mapAttributLineForm.get("identificationfiscale").getValueAsString());
			parametresOrganisme.setNumArticle(mapAttributLineForm.get("numarticle").getValueAsString());
			parametresOrganisme.setNIS(mapAttributLineForm.get("nis").getValueAsString());
			parametresOrganisme.setAdresse(mapAttributLineForm.get("adresse").getValueAsString());
			parametresOrganisme.setBoitePostale(mapAttributLineForm.get("boitepostale").getValueAsString());
			parametresOrganisme.setNumTel(mapAttributLineForm.get("numtel").getValueAsString());
			parametresOrganisme.setNumFax(mapAttributLineForm.get("numfax").getValueAsString());
			parametresOrganisme.setEmail(mapAttributLineForm.get("email").getValueAsString());
			parametresOrganisme.setDescriptif(mapAttributLineForm.get("descriptif").getValueAsString());
			
			int idPhoto = parametresOrganisme.getIdPhoto().intValue();
			if (mapAttributLineForm.get("photo").getValue() != null && mapAttributLineForm.get("photo").getValue() instanceof models.beans.Photo){
				idPhoto = ((models.beans.Photo)(mapAttributLineForm.get("photo").getValue())).getId().intValue();
			}
			parametresOrganisme.setIdPhoto(idPhoto);
			
			parametresOrganisme.setId((Integer)communication.SocketCommunicator.getInstance().sendQuery(parametresOrganisme));
			this.setParametresOrganisme(parametresOrganisme);
			
			this.setVisible(false);
		}
		
		protected void bCancelActionPerformed(){
			this.setParametresOrganisme(null);
			this.setVisible(false);
		}
		
		public void setParametresOrganisme(models.beans.ParametresOrganisme parametresOrganisme) {
			this.parametresOrganisme = parametresOrganisme;
			updateForm();
		}
		
		public models.beans.ParametresOrganisme getParametresOrganisme() {
			return this.parametresOrganisme;
		}
		
		protected void emptyForm(){
			mapAttributLineForm.get("designationorganisme").setValue(null);
			mapAttributLineForm.get("raisonsocial").setValue(null);
			mapAttributLineForm.get("capitalsocial").setValue(null);
			mapAttributLineForm.get("numrc").setValue(null);
			mapAttributLineForm.get("numcb").setValue(null);
			mapAttributLineForm.get("identificationfiscale").setValue(null);
			mapAttributLineForm.get("numarticle").setValue(null);
			mapAttributLineForm.get("nis").setValue(null);
			mapAttributLineForm.get("adresse").setValue(null);
			mapAttributLineForm.get("boitepostale").setValue(null);
			mapAttributLineForm.get("numtel").setValue(null);
			mapAttributLineForm.get("numfax").setValue(null);
			mapAttributLineForm.get("email").setValue(null);
			mapAttributLineForm.get("descriptif").setValue(null);
			mapAttributLineForm.get("photo").setValue(null);
		}
		
		protected void updateForm(){
			if (parametresOrganisme == null){
				emptyForm();
				return;
			}
			
			mapAttributLineForm.get("designationorganisme").setValue(parametresOrganisme.getDesignationOrganisme());
			mapAttributLineForm.get("raisonsocial").setValue(parametresOrganisme.getRaisonSocial());
			mapAttributLineForm.get("capitalsocial").setValue(parametresOrganisme.getCapitalSocial());
			mapAttributLineForm.get("numrc").setValue(parametresOrganisme.getNumRC());
			mapAttributLineForm.get("numcb").setValue(parametresOrganisme.getNumCB());
			mapAttributLineForm.get("identificationfiscale").setValue(parametresOrganisme.getIdentificationFiscale());
			mapAttributLineForm.get("numarticle").setValue(parametresOrganisme.getNumArticle());
			mapAttributLineForm.get("nis").setValue(parametresOrganisme.getNIS());
			mapAttributLineForm.get("adresse").setValue(parametresOrganisme.getAdresse());
			mapAttributLineForm.get("boitepostale").setValue(parametresOrganisme.getBoitePostale());
			mapAttributLineForm.get("numtel").setValue(parametresOrganisme.getNumTel());
			mapAttributLineForm.get("numfax").setValue(parametresOrganisme.getNumFax());
			mapAttributLineForm.get("email").setValue(parametresOrganisme.getEmail());
			mapAttributLineForm.get("descriptif").setValue(parametresOrganisme.getDescriptif());
			mapAttributLineForm.get("photo").setValue(parametresOrganisme.getPhoto());
		}
	}
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}