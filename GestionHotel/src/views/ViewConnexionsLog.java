package views;

import java.io.File;

import models.daos.client.DAOConnexionsLog;
import models.beans.ConnexionsLog;

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

public abstract class ViewConnexionsLog {
	
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

	public static javax.swing.JTextField getViewForLogin(){
		return gui.utils.GUIFieldFactory.createSimpleTextField(100);
	}

	public static String getCaptionForLogin(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("", userParameters.getLang().getCodeLang());
		}

		return "";
	}

	public static javax.swing.JTextField getViewForUuid(){
		return gui.utils.GUIFieldFactory.createSimpleTextField(36);
	}

	public static String getCaptionForUuid(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("", userParameters.getLang().getCodeLang());
		}

		return "";
	}

	public static gui.utils.GUIDate getViewForDateDeConnexion(){
		return new gui.utils.GUIDate();
	}

	public static String getCaptionForDateDeConnexion(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("", userParameters.getLang().getCodeLang());
		}

		return "";
	}

	public static gui.utils.GUIDate getViewForDateDeDeconnexion(){
		return new gui.utils.GUIDate();
	}

	public static String getCaptionForDateDeDeconnexion(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("", userParameters.getLang().getCodeLang());
		}

		return "";
	}

	public static javax.swing.JTextField getViewForIp(){
		return gui.utils.GUIFieldFactory.createSimpleTextField(15);
	}

	public static String getCaptionForIp(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("", userParameters.getLang().getCodeLang());
		}

		return "";
	}

	public static javax.swing.JTextField getViewForMac(){
		return gui.utils.GUIFieldFactory.createSimpleTextField(17);
	}

	public static String getCaptionForMac(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("", userParameters.getLang().getCodeLang());
		}

		return "";
	}

	public static javax.swing.JTextField getViewForMachine(){
		return gui.utils.GUIFieldFactory.createSimpleTextField(100);
	}

	public static String getCaptionForMachine(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("", userParameters.getLang().getCodeLang());
		}

		return "";
	}

	public static javax.swing.JComponent getViewForConnexionAccepted(String typeView){
		String[] items = {"oui", "non"};
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

	public static String getCaptionForConnexionAccepted(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("", userParameters.getLang().getCodeLang());
		}

		return "";
	}

	public static javax.swing.JTextField getViewForMotifError(){
		return gui.utils.GUIFieldFactory.createSimpleTextField(500);
	}

	public static String getCaptionForMotifError(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("", userParameters.getLang().getCodeLang());
		}

		return "";
	}

	public static javax.swing.JComboBox getViewForUtilisateur(){
		java.util.List<models.beans.Utilisateur> list = models.daos.client.DAOUtilisateur.getListInstances();
		javax.swing.JComboBox view = new gui.utils.GUIPanelFactory.JEditedComboBox(list.toArray());
		view.insertItemAt("Choisir Utilisateur", 0);
		return view;
	}

	public static String getCaptionForUtilisateur(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("", userParameters.getLang().getCodeLang());
		}

		return "";
	}

	public static java.util.Vector<Object> getTableHeader(boolean withNumero){
		java.util.Vector<Object> header = new java.util.Vector<Object>();
		
		if (withNumero)
			header.add("N°");
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

	public static java.util.Vector<Object> getTableRow(models.beans.ConnexionsLog connexionsLog, int numOrder){
		java.util.Vector<Object> row = new java.util.Vector<Object>();
		
		if (numOrder > 0)
			row.add(numOrder);
		
		row.add(connexionsLog.getLogin());
		row.add(connexionsLog.getUuid());
		row.add(connexionsLog.getDateDeConnexion());
		row.add(connexionsLog.getDateDeDeconnexion());
		row.add(connexionsLog.getIp());
		row.add(connexionsLog.getMac());
		row.add(connexionsLog.getMachine());
		row.add(connexionsLog.getConnexionAccepted());
		row.add(connexionsLog.getMotifError());
		row.add(connexionsLog.getUtilisateur());
		
		row.add(connexionsLog);
		
		return row;
	}

	public static java.util.Vector<java.util.Vector<Object>> getData(java.util.List<models.beans.ConnexionsLog> list, boolean withOrder){
		return getData(list, withOrder, 0);
	}

	public static java.util.Vector<java.util.Vector<Object>> getData(java.util.List<models.beans.ConnexionsLog> list, boolean withOrder, int numRowBase){
		java.util.Vector<java.util.Vector<Object>> data = new java.util.Vector<java.util.Vector<Object>>();
		if (list == null)
			return data;
		
		int numRow = numRowBase;
		for (models.beans.ConnexionsLog connexionsLog : list){
			if (withOrder)
				numRow++;
			
			data.add(getTableRow(connexionsLog , numRow));
		}
		
		return data;
	}

	public static void exportToExcel(File file){
		exportToExcel(file, DAOConnexionsLog.getListInstances());
	}

	public static void exportToExcel(File file, java.util.List<ConnexionsLog> list){
		try{
			WritableWorkbook workbook = Workbook.createWorkbook(file);
			WritableSheet sheet = workbook.createSheet( "Liste des ViewConnexionsLogs", 0);

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
			
			int ligneBas = 1;
			for (ConnexionsLog connexionsLog : list){
				int i = list.indexOf(connexionsLog);
				label = new Label(0, ligneBas+i, String.valueOf(i), formatContentStr); sheet.addCell(label);
				label = new Label(1, ligneBas+i, connexionsLog.getLogin().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(2, ligneBas+i, connexionsLog.getUuid().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(3, ligneBas+i, connexionsLog.getDateDeConnexion().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(4, ligneBas+i, connexionsLog.getDateDeDeconnexion().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(5, ligneBas+i, connexionsLog.getIp().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(6, ligneBas+i, connexionsLog.getMac().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(7, ligneBas+i, connexionsLog.getMachine().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(8, ligneBas+i, connexionsLog.getConnexionAccepted().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(9, ligneBas+i, connexionsLog.getMotifError().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(10, ligneBas+i, connexionsLog.getIdUtilisateur().toString(), formatHeaderTableStr); sheet.addCell(label);
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
				
				ConnexionsLog connexionsLog = new ConnexionsLog();

				String value = "";
				try{
					value = sheet.getCell(1, ligne-1).getContents().trim();
					connexionsLog.setLogin(value);
					value = sheet.getCell(2, ligne-1).getContents().trim();
					connexionsLog.setUuid(value);
					value = sheet.getCell(3, ligne-1).getContents().trim();
					connexionsLog.setDateDeConnexion(utils.StringUtils.getTimestampFromString(value));
					value = sheet.getCell(4, ligne-1).getContents().trim();
					connexionsLog.setDateDeDeconnexion(utils.StringUtils.getTimestampFromString(value));
					value = sheet.getCell(5, ligne-1).getContents().trim();
					connexionsLog.setIp(value);
					value = sheet.getCell(6, ligne-1).getContents().trim();
					connexionsLog.setMac(value);
					value = sheet.getCell(7, ligne-1).getContents().trim();
					connexionsLog.setMachine(value);
					value = sheet.getCell(8, ligne-1).getContents().trim();
					connexionsLog.setConnexionAccepted(ConnexionsLog.ConnexionAccepted.getByValue(value));
					value = sheet.getCell(9, ligne-1).getContents().trim();
					connexionsLog.setMotifError(value);
					value = sheet.getCell(10, ligne-1).getContents().trim();
					connexionsLog.setIdUtilisateur(Integer.parseInt(value));
					queries.add(DAOConnexionsLog.getSQLWriting(connexionsLog));
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
		exportToPDF(file, DAOConnexionsLog.getListInstances());
	}

	public static void exportToPDF(File file, java.util.List<ConnexionsLog> list){
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
				Paragraph header = new Paragraph("Journal des Connexions", fontHVCA15_IB);
				header.setAlignment(Paragraph.ALIGN_RIGHT);
				header.setSpacingAfter(5);
				docPDF.add(header);
				
				LineSeparator line = new LineSeparator();
				docPDF.add(line);
				
				PdfPCell cell;
				PdfPTable pdfTable;
				float[] a;
				float width = PageSize.A4.getWidth() - 100;
				
				a = new float[10];
				a[0] = (float)(width * 0.1);
				a[1] = (float)(width * 0.1);
				a[2] = (float)(width * 0.1);
				a[3] = (float)(width * 0.1);
				a[4] = (float)(width * 0.1);
				a[5] = (float)(width * 0.1);
				a[6] = (float)(width * 0.1);
				a[7] = (float)(width * 0.1);
				a[8] = (float)(width * 0.1);
				a[9] = (float)(width * 0.1);
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

				
				fontTR10_B.setColor(BaseColor.BLACK);
				for (int i=0; i<maxItemsAtPage; i++){
					int index = i + numPage * maxItemsAtPage;
					ConnexionsLog connexionsLog = null;
					if (index < list.size())
						connexionsLog = list.get(index);
					
					cell = new PdfPCell(new Paragraph((connexionsLog != null) ? connexionsLog.getLogin().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((connexionsLog != null) ? connexionsLog.getUuid().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((connexionsLog != null) ? connexionsLog.getDateDeConnexion().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((connexionsLog != null) ? connexionsLog.getDateDeDeconnexion().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((connexionsLog != null) ? connexionsLog.getIp().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((connexionsLog != null) ? connexionsLog.getMac().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((connexionsLog != null) ? connexionsLog.getMachine().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((connexionsLog != null) ? connexionsLog.getConnexionAccepted().getValue() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((connexionsLog != null) ? connexionsLog.getMotifError().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((connexionsLog != null) ? (connexionsLog.getUtilisateur() == null ? "" : connexionsLog.getUtilisateur().toString()) : " ", fontTR10));
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
		exportToText(file, DAOConnexionsLog.getListInstances());
	}

	public static void exportToText(File file, java.util.List<ConnexionsLog> list){
		try{
			
			
		}
		catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
	}

	public static void exportToWord(File file){
		exportToWord(file, DAOConnexionsLog.getListInstances());
	}

	public static void exportToWord(File file, java.util.List<ConnexionsLog> list){
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
		
		javax.swing.JLabel labelLogin = new javax.swing.JLabel("");
		javax.swing.JLabel labelUuid = new javax.swing.JLabel("");
		javax.swing.JLabel labelDateDeConnexion = new javax.swing.JLabel("");
		javax.swing.JLabel labelDateDeDeconnexion = new javax.swing.JLabel("");
		javax.swing.JLabel labelIp = new javax.swing.JLabel("");
		javax.swing.JLabel labelMac = new javax.swing.JLabel("");
		javax.swing.JLabel labelMachine = new javax.swing.JLabel("");
		javax.swing.JLabel labelConnexionAccepted = new javax.swing.JLabel("");
		javax.swing.JLabel labelMotifError = new javax.swing.JLabel("");
		javax.swing.JLabel labelUtilisateur = new javax.swing.JLabel("");
		
		javax.swing.JComponent componentLogin = views.ViewConnexionsLog.getViewForLogin();
		javax.swing.JComponent componentUuid = views.ViewConnexionsLog.getViewForUuid();
		javax.swing.JComponent componentDateDeConnexion = views.ViewConnexionsLog.getViewForDateDeConnexion();
		javax.swing.JComponent componentDateDeDeconnexion = views.ViewConnexionsLog.getViewForDateDeDeconnexion();
		javax.swing.JComponent componentIp = views.ViewConnexionsLog.getViewForIp();
		javax.swing.JComponent componentMac = views.ViewConnexionsLog.getViewForMac();
		javax.swing.JComponent componentMachine = views.ViewConnexionsLog.getViewForMachine();
		javax.swing.JComponent componentConnexionAccepted = (javax.swing.JComboBox)views.ViewConnexionsLog.getViewForConnexionAccepted("JCOMBOBOXE");
		javax.swing.JComponent componentMotifError = views.ViewConnexionsLog.getViewForMotifError();
		javax.swing.JComponent componentUtilisateur = views.ViewConnexionsLog.getViewForUtilisateur();
		
		javax.swing.GroupLayout layout = (javax.swing.GroupLayout)panel.getLayout();
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGap(1, 1, Short.MAX_VALUE)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelLogin)
				.addComponent(labelUuid)
				.addComponent(labelDateDeConnexion)
				.addComponent(labelDateDeDeconnexion)
				.addComponent(labelIp)
				.addComponent(labelMac)
				.addComponent(labelMachine)
				.addComponent(labelConnexionAccepted)
				.addComponent(labelMotifError)
				.addComponent(labelUtilisateur)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(componentLogin, 300, 300, 300)
				.addComponent(componentUuid, 300, 300, 300)
				.addComponent(componentDateDeConnexion, 300, 300, 300)
				.addComponent(componentDateDeDeconnexion, 300, 300, 300)
				.addComponent(componentIp, 300, 300, 300)
				.addComponent(componentMac, 300, 300, 300)
				.addComponent(componentMachine, 300, 300, 300)
				.addComponent(componentConnexionAccepted, 300, 300, 300)
				.addComponent(componentMotifError, 300, 300, 300)
				.addComponent(componentUtilisateur, 300, 300, 300)
			)
			.addGap(1, 1, Short.MAX_VALUE)
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addGap(1, 1, Short.MAX_VALUE)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelLogin)
				.addComponent(componentLogin, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelUuid)
				.addComponent(componentUuid, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelDateDeConnexion)
				.addComponent(componentDateDeConnexion, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelDateDeDeconnexion)
				.addComponent(componentDateDeDeconnexion, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelIp)
				.addComponent(componentIp, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelMac)
				.addComponent(componentMac, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelMachine)
				.addComponent(componentMachine, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelConnexionAccepted)
				.addComponent(componentConnexionAccepted, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelMotifError)
				.addComponent(componentMotifError, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelUtilisateur)
				.addComponent(componentUtilisateur, 20, 20, 20)
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
		
		javax.swing.JLabel labelLogin = new javax.swing.JLabel("");
		javax.swing.JLabel labelUuid = new javax.swing.JLabel("");
		javax.swing.JLabel labelDateDeConnexion = new javax.swing.JLabel("");
		javax.swing.JLabel labelDateDeDeconnexion = new javax.swing.JLabel("");
		javax.swing.JLabel labelIp = new javax.swing.JLabel("");
		javax.swing.JLabel labelMac = new javax.swing.JLabel("");
		javax.swing.JLabel labelMachine = new javax.swing.JLabel("");
		javax.swing.JLabel labelConnexionAccepted = new javax.swing.JLabel("");
		javax.swing.JLabel labelMotifError = new javax.swing.JLabel("");
		javax.swing.JLabel labelUtilisateur = new javax.swing.JLabel("");
		
		javax.swing.JComponent componentLogin = views.ViewConnexionsLog.getViewForLogin();
		javax.swing.JComponent componentUuid = views.ViewConnexionsLog.getViewForUuid();
		javax.swing.JComponent componentDateDeConnexion = views.ViewConnexionsLog.getViewForDateDeConnexion();
		javax.swing.JComponent componentDateDeDeconnexion = views.ViewConnexionsLog.getViewForDateDeDeconnexion();
		javax.swing.JComponent componentIp = views.ViewConnexionsLog.getViewForIp();
		javax.swing.JComponent componentMac = views.ViewConnexionsLog.getViewForMac();
		javax.swing.JComponent componentMachine = views.ViewConnexionsLog.getViewForMachine();
		javax.swing.JComponent componentConnexionAccepted = (javax.swing.JComboBox)views.ViewConnexionsLog.getViewForConnexionAccepted("JCOMBOBOXE");
		javax.swing.JComponent componentMotifError = views.ViewConnexionsLog.getViewForMotifError();
		javax.swing.JComponent componentUtilisateur = views.ViewConnexionsLog.getViewForUtilisateur();

		gui.utils.GroupLayouter.LineForm lineForm;
		lineForm = new gui.utils.GroupLayouter.LineForm(labelLogin, componentLogin);
		lineForm.setHeight(25);
		mapLabelsFields.put("login", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelUuid, componentUuid);
		lineForm.setHeight(25);
		mapLabelsFields.put("uuid", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelDateDeConnexion, componentDateDeConnexion);
		lineForm.setHeight(25);
		mapLabelsFields.put("datedeconnexion", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelDateDeDeconnexion, componentDateDeDeconnexion);
		lineForm.setHeight(25);
		mapLabelsFields.put("datededeconnexion", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelIp, componentIp);
		lineForm.setHeight(25);
		mapLabelsFields.put("ip", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelMac, componentMac);
		lineForm.setHeight(25);
		mapLabelsFields.put("mac", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelMachine, componentMachine);
		lineForm.setHeight(25);
		mapLabelsFields.put("machine", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelConnexionAccepted, componentConnexionAccepted);
		lineForm.setHeight(25);
		mapLabelsFields.put("connexionaccepted", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelMotifError, componentMotifError);
		lineForm.setHeight(25);
		mapLabelsFields.put("motiferror", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelUtilisateur, componentUtilisateur);
		lineForm.setHeight(25);
		mapLabelsFields.put("utilisateur", lineForm);

		javax.swing.JComponent[][] matrice ={
			{labelLogin, componentLogin},
			{labelUuid, componentUuid},
			{labelDateDeConnexion, componentDateDeConnexion},
			{labelDateDeDeconnexion, componentDateDeDeconnexion},
			{labelIp, componentIp},
			{labelMac, componentMac},
			{labelMachine, componentMachine},
			{labelConnexionAccepted, componentConnexionAccepted},
			{labelMotifError, componentMotifError},
			{labelUtilisateur, componentUtilisateur},
		};

		gui.utils.GroupLayouter.Form form = new gui.utils.GroupLayouter.Form(matrice, 50, 50);
		gui.utils.GroupLayouter.createSimpleForm(panel, form);		
		return mapLabelsFields;
	}

	public static class DlgAddModifyConnexionsLog extends javax.swing.JDialog{
		private static final long serialVersionUID = 1L;
		
		protected javax.swing.JPanel		pFormulaire;
		protected java.util.Map<String, gui.utils.GroupLayouter.LineForm>	mapAttributLineForm;
		protected javax.swing.JSeparator	sepBottom;
		protected javax.swing.JButton		bValidate, bCancel;
		
		protected models.beans.ConnexionsLog connexionsLog = null;
		
		public DlgAddModifyConnexionsLog(java.awt.Window parent, String title){
			this(parent, title, null);
		}
		
		public DlgAddModifyConnexionsLog(java.awt.Window parent, String title, models.beans.ConnexionsLog connexionsLog){
			super(parent);
			initComponents();
			this.setConnexionsLog(connexionsLog);
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
			ConnexionsLog connexionsLog = this.getConnexionsLog();
			if (connexionsLog == null){
				connexionsLog = new ConnexionsLog();
			}
			
			connexionsLog.setLogin(mapAttributLineForm.get("login").getValueAsString());
			connexionsLog.setUuid(mapAttributLineForm.get("uuid").getValueAsString());
			connexionsLog.setDateDeConnexion(mapAttributLineForm.get("datedeconnexion").getValueAsTimestamp());
			connexionsLog.setDateDeDeconnexion(mapAttributLineForm.get("datededeconnexion").getValueAsTimestamp());
			connexionsLog.setIp(mapAttributLineForm.get("ip").getValueAsString());
			connexionsLog.setMac(mapAttributLineForm.get("mac").getValueAsString());
			connexionsLog.setMachine(mapAttributLineForm.get("machine").getValueAsString());
			connexionsLog.setConnexionAccepted(models.beans.ConnexionsLog.ConnexionAccepted.getByValue(mapAttributLineForm.get("connexionaccepted").getValueAsString()));
			connexionsLog.setMotifError(mapAttributLineForm.get("motiferror").getValueAsString());
			
			int idUtilisateur = connexionsLog.getIdUtilisateur().intValue();
			if (mapAttributLineForm.get("utilisateur").getValue() != null && mapAttributLineForm.get("utilisateur").getValue() instanceof models.beans.Utilisateur){
				idUtilisateur = ((models.beans.Utilisateur)(mapAttributLineForm.get("utilisateur").getValue())).getId().intValue();
			}
			connexionsLog.setIdUtilisateur(idUtilisateur);
			
			connexionsLog.setId((Integer)communication.SocketCommunicator.getInstance().sendQuery(connexionsLog));
			this.setConnexionsLog(connexionsLog);
			
			this.setVisible(false);
		}
		
		protected void bCancelActionPerformed(){
			this.setConnexionsLog(null);
			this.setVisible(false);
		}
		
		public void setConnexionsLog(models.beans.ConnexionsLog connexionsLog) {
			this.connexionsLog = connexionsLog;
			updateForm();
		}
		
		public models.beans.ConnexionsLog getConnexionsLog() {
			return this.connexionsLog;
		}
		
		protected void emptyForm(){
			mapAttributLineForm.get("login").setValue(null);
			mapAttributLineForm.get("uuid").setValue(null);
			mapAttributLineForm.get("datedeconnexion").setValue(null);
			mapAttributLineForm.get("datededeconnexion").setValue(null);
			mapAttributLineForm.get("ip").setValue(null);
			mapAttributLineForm.get("mac").setValue(null);
			mapAttributLineForm.get("machine").setValue(null);
			mapAttributLineForm.get("connexionaccepted").setValue(null);
			mapAttributLineForm.get("motiferror").setValue(null);
			mapAttributLineForm.get("utilisateur").setValue(null);
		}
		
		protected void updateForm(){
			if (connexionsLog == null){
				emptyForm();
				return;
			}
			
			mapAttributLineForm.get("login").setValue(connexionsLog.getLogin());
			mapAttributLineForm.get("uuid").setValue(connexionsLog.getUuid());
			mapAttributLineForm.get("datedeconnexion").setValue(connexionsLog.getDateDeConnexion());
			mapAttributLineForm.get("datededeconnexion").setValue(connexionsLog.getDateDeDeconnexion());
			mapAttributLineForm.get("ip").setValue(connexionsLog.getIp());
			mapAttributLineForm.get("mac").setValue(connexionsLog.getMac());
			mapAttributLineForm.get("machine").setValue(connexionsLog.getMachine());
			mapAttributLineForm.get("connexionaccepted").setValue(connexionsLog.getConnexionAccepted().getValue());
			mapAttributLineForm.get("motiferror").setValue(connexionsLog.getMotifError());
			mapAttributLineForm.get("utilisateur").setValue(connexionsLog.getUtilisateur());
		}
	}
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}