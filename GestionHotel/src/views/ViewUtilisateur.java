package views;

import java.io.File;

import models.daos.client.DAOUtilisateur;
import models.beans.Utilisateur;

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

public abstract class ViewUtilisateur {
	
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

	public static javax.swing.JTextField getViewForNom(){
		return gui.utils.GUIFieldFactory.createSimpleTextField(50);
	}

	public static String getCaptionForNom(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("Nom", userParameters.getLang().getCodeLang());
		}

		return "Nom";
	}

	public static javax.swing.JTextField getViewForPrenom(){
		return gui.utils.GUIFieldFactory.createSimpleTextField(50);
	}

	public static String getCaptionForPrenom(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("Prénom", userParameters.getLang().getCodeLang());
		}

		return "Prénom";
	}

	public static javax.swing.JComponent getViewForCivilite(String typeView){
		String[] items = {"Mr.", "Mme."};
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
			return models.daos.client.DAOTranslation.translate("Civilité", userParameters.getLang().getCodeLang());
		}

		return "Civilité";
	}

	public static gui.utils.GUIDate getViewForDateNaissance(){
		return new gui.utils.GUIDate();
	}

	public static String getCaptionForDateNaissance(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("Date de naissance", userParameters.getLang().getCodeLang());
		}

		return "Date de naissance";
	}

	public static javax.swing.JTextField getViewForLieuNaissance(){
		return gui.utils.GUIFieldFactory.createSimpleTextField(50);
	}

	public static String getCaptionForLieuNaissance(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("Lieu de naissance", userParameters.getLang().getCodeLang());
		}

		return "Lieu de naissance";
	}

	public static javax.swing.JTextField getViewForLogin(){
		return gui.utils.GUIFieldFactory.createSimpleTextField(50);
	}

	public static String getCaptionForLogin(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("Login (Idnetifiant)", userParameters.getLang().getCodeLang());
		}

		return "Login (Idnetifiant)";
	}

	public static javax.swing.JPasswordField getViewForPassword(){
		return gui.utils.GUIFieldFactory.createSimplePasswordField(50);
	}

	public static String getCaptionForPassword(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("Mot de Passe", userParameters.getLang().getCodeLang());
		}

		return "Mot de Passe";
	}

	public static javax.swing.JComboBox getViewForPhoto(){
		java.util.List<models.beans.Photo> list = models.daos.client.DAOPhoto.getListInstances();
		javax.swing.JComboBox view = new gui.utils.GUIPanelFactory.JEditedComboBox(list.toArray());
		view.insertItemAt("Choisir Photo", 0);
		return view;
	}

	public static String getCaptionForPhoto(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("Photo", userParameters.getLang().getCodeLang());
		}

		return "Photo";
	}

	public static javax.swing.JComboBox getViewForRole(){
		java.util.List<models.beans.Role> list = models.daos.client.DAORole.getListInstances();
		javax.swing.JComboBox view = new gui.utils.GUIPanelFactory.JEditedComboBox(list.toArray());
		view.insertItemAt("Choisir Role", 0);
		return view;
	}

	public static String getCaptionForRole(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("Role", userParameters.getLang().getCodeLang());
		}

		return "Role";
	}

	public static javax.swing.JComboBox getViewForParametres(){
		java.util.List<models.beans.ParametresApplicationUtilisateur> list = models.daos.client.DAOParametresApplicationUtilisateur.getListInstances();
		javax.swing.JComboBox view = new gui.utils.GUIPanelFactory.JEditedComboBox(list.toArray());
		view.insertItemAt("Choisir ParametresApplicationUtilisateur", 0);
		return view;
	}

	public static String getCaptionForParametres(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("Paramètres Personnels", userParameters.getLang().getCodeLang());
		}

		return "Paramètres Personnels";
	}

	public static java.util.Vector<Object> getTableHeader(boolean withNumero){
		java.util.Vector<Object> header = new java.util.Vector<Object>();
		
		if (withNumero)
			header.add("N°");
		header.add("Nom");
		header.add("Prénom");
		header.add("Civilité");
		header.add("Date de naissance");
		header.add("Lieu de naissance");
		header.add("Login (Idnetifiant)");
		header.add("Mot de Passe");
		header.add("Photo");
		header.add("Role");
		header.add("Paramètres Personnels");
		
		return header;
	}

	public static java.util.Vector<Object> getTableRow(models.beans.Utilisateur utilisateur, int numOrder){
		java.util.Vector<Object> row = new java.util.Vector<Object>();
		
		if (numOrder > 0)
			row.add(numOrder);
		
		row.add(utilisateur.getNom());
		row.add(utilisateur.getPrenom());
		row.add(utilisateur.getCivilite());
		row.add(utilisateur.getDateNaissance());
		row.add(utilisateur.getLieuNaissance());
		row.add(utilisateur.getLogin());
		row.add(utilisateur.getPassword());
		row.add(utilisateur.getPhoto());
		row.add(utilisateur.getRole());
		row.add(utilisateur.getParametres());
		
		row.add(utilisateur);
		
		return row;
	}

	public static java.util.Vector<java.util.Vector<Object>> getData(java.util.List<models.beans.Utilisateur> list, boolean withOrder){
		return getData(list, withOrder, 0);
	}

	public static java.util.Vector<java.util.Vector<Object>> getData(java.util.List<models.beans.Utilisateur> list, boolean withOrder, int numRowBase){
		java.util.Vector<java.util.Vector<Object>> data = new java.util.Vector<java.util.Vector<Object>>();
		if (list == null)
			return data;
		
		int numRow = numRowBase;
		for (models.beans.Utilisateur utilisateur : list){
			if (withOrder)
				numRow++;
			
			data.add(getTableRow(utilisateur , numRow));
		}
		
		return data;
	}

	public static void exportToExcel(File file){
		exportToExcel(file, DAOUtilisateur.getListInstances());
	}

	public static void exportToExcel(File file, java.util.List<Utilisateur> list){
		try{
			WritableWorkbook workbook = Workbook.createWorkbook(file);
			WritableSheet sheet = workbook.createSheet( "Liste des ViewUtilisateurs", 0);

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
			label = new Label(1, 0, "Nom", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(2, 0, "Prénom", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(3, 0, "Civilité", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(4, 0, "Date de naissance", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(5, 0, "Lieu de naissance", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(6, 0, "Login (Idnetifiant)", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(7, 0, "Mot de Passe", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(8, 0, "Photo", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(9, 0, "Role", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(10, 0, "Paramètres Personnels", formatHeaderTableStr); sheet.addCell(label);
			
			int ligneBas = 1;
			for (Utilisateur utilisateur : list){
				int i = list.indexOf(utilisateur);
				label = new Label(0, ligneBas+i, String.valueOf(i), formatContentStr); sheet.addCell(label);
				label = new Label(1, ligneBas+i, utilisateur.getNom().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(2, ligneBas+i, utilisateur.getPrenom().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(3, ligneBas+i, utilisateur.getCivilite().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(4, ligneBas+i, utilisateur.getDateNaissance().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(5, ligneBas+i, utilisateur.getLieuNaissance().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(6, ligneBas+i, utilisateur.getLogin().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(7, ligneBas+i, utilisateur.getPassword().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(8, ligneBas+i, utilisateur.getIdPhoto().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(9, ligneBas+i, utilisateur.getIdRole().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(10, ligneBas+i, utilisateur.getIdParametres().toString(), formatHeaderTableStr); sheet.addCell(label);
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
				
				Utilisateur utilisateur = new Utilisateur();

				String value = "";
				try{
					value = sheet.getCell(1, ligne-1).getContents().trim();
					utilisateur.setNom(value);
					value = sheet.getCell(2, ligne-1).getContents().trim();
					utilisateur.setPrenom(value);
					value = sheet.getCell(3, ligne-1).getContents().trim();
					utilisateur.setCivilite(Utilisateur.Civilite.getByValue(value));
					value = sheet.getCell(4, ligne-1).getContents().trim();
					utilisateur.setDateNaissance(utils.StringUtils.getDateFromString(value));
					value = sheet.getCell(5, ligne-1).getContents().trim();
					utilisateur.setLieuNaissance(value);
					value = sheet.getCell(6, ligne-1).getContents().trim();
					utilisateur.setLogin(value);
					value = sheet.getCell(7, ligne-1).getContents().trim();
					utilisateur.setPassword(value);
					value = sheet.getCell(8, ligne-1).getContents().trim();
					utilisateur.setIdPhoto(Integer.parseInt(value));
					value = sheet.getCell(9, ligne-1).getContents().trim();
					utilisateur.setIdRole(Integer.parseInt(value));
					value = sheet.getCell(10, ligne-1).getContents().trim();
					utilisateur.setIdParametres(Integer.parseInt(value));
					queries.add(DAOUtilisateur.getSQLWriting(utilisateur));
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
		exportToPDF(file, DAOUtilisateur.getListInstances());
	}

	public static void exportToPDF(File file, java.util.List<Utilisateur> list){
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
				Paragraph header = new Paragraph("Utilisateurs", fontHVCA15_IB);
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
				
				cell = new PdfPCell(new Paragraph("Nom", fontTR10_B_WHITE));
				cell.setBackgroundColor(BaseColor.BLACK);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingBottom(5);
				pdfTable.addCell(cell);

				cell = new PdfPCell(new Paragraph("Prénom", fontTR10_B_WHITE));
				cell.setBackgroundColor(BaseColor.BLACK);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingBottom(5);
				pdfTable.addCell(cell);

				cell = new PdfPCell(new Paragraph("Civilité", fontTR10_B_WHITE));
				cell.setBackgroundColor(BaseColor.BLACK);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingBottom(5);
				pdfTable.addCell(cell);

				cell = new PdfPCell(new Paragraph("Date de naissance", fontTR10_B_WHITE));
				cell.setBackgroundColor(BaseColor.BLACK);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingBottom(5);
				pdfTable.addCell(cell);

				cell = new PdfPCell(new Paragraph("Lieu de naissance", fontTR10_B_WHITE));
				cell.setBackgroundColor(BaseColor.BLACK);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingBottom(5);
				pdfTable.addCell(cell);

				cell = new PdfPCell(new Paragraph("Login (Idnetifiant)", fontTR10_B_WHITE));
				cell.setBackgroundColor(BaseColor.BLACK);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingBottom(5);
				pdfTable.addCell(cell);

				cell = new PdfPCell(new Paragraph("Mot de Passe", fontTR10_B_WHITE));
				cell.setBackgroundColor(BaseColor.BLACK);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingBottom(5);
				pdfTable.addCell(cell);

				cell = new PdfPCell(new Paragraph("Photo", fontTR10_B_WHITE));
				cell.setBackgroundColor(BaseColor.BLACK);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingBottom(5);
				pdfTable.addCell(cell);

				cell = new PdfPCell(new Paragraph("Role", fontTR10_B_WHITE));
				cell.setBackgroundColor(BaseColor.BLACK);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingBottom(5);
				pdfTable.addCell(cell);

				cell = new PdfPCell(new Paragraph("Paramètres Personnels", fontTR10_B_WHITE));
				cell.setBackgroundColor(BaseColor.BLACK);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingBottom(5);
				pdfTable.addCell(cell);

				
				fontTR10_B.setColor(BaseColor.BLACK);
				for (int i=0; i<maxItemsAtPage; i++){
					int index = i + numPage * maxItemsAtPage;
					Utilisateur utilisateur = null;
					if (index < list.size())
						utilisateur = list.get(index);
					
					cell = new PdfPCell(new Paragraph((utilisateur != null) ? utilisateur.getNom().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((utilisateur != null) ? utilisateur.getPrenom().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((utilisateur != null) ? utilisateur.getCivilite().getValue() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((utilisateur != null) ? utils.StringUtils.formatDateFromMySQL(utilisateur.getDateNaissance().toString()) : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((utilisateur != null) ? utilisateur.getLieuNaissance().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((utilisateur != null) ? utilisateur.getLogin().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((utilisateur != null) ? utilisateur.getPassword().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((utilisateur != null) ? (utilisateur.getPhoto() == null ? "" : utilisateur.getPhoto().toString()) : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((utilisateur != null) ? (utilisateur.getRole() == null ? "" : utilisateur.getRole().toString()) : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((utilisateur != null) ? (utilisateur.getParametres() == null ? "" : utilisateur.getParametres().toString()) : " ", fontTR10));
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
		exportToText(file, DAOUtilisateur.getListInstances());
	}

	public static void exportToText(File file, java.util.List<Utilisateur> list){
		try{
			
			
		}
		catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
	}

	public static void exportToWord(File file){
		exportToWord(file, DAOUtilisateur.getListInstances());
	}

	public static void exportToWord(File file, java.util.List<Utilisateur> list){
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
		
		javax.swing.JLabel labelNom = new javax.swing.JLabel("Nom");
		javax.swing.JLabel labelPrenom = new javax.swing.JLabel("Prénom");
		javax.swing.JLabel labelCivilite = new javax.swing.JLabel("Civilité");
		javax.swing.JLabel labelDateNaissance = new javax.swing.JLabel("Date de naissance");
		javax.swing.JLabel labelLieuNaissance = new javax.swing.JLabel("Lieu de naissance");
		javax.swing.JLabel labelLogin = new javax.swing.JLabel("Login (Idnetifiant)");
		javax.swing.JLabel labelPassword = new javax.swing.JLabel("Mot de Passe");
		javax.swing.JLabel labelPhoto = new javax.swing.JLabel("Photo");
		javax.swing.JLabel labelRole = new javax.swing.JLabel("Role");
		javax.swing.JLabel labelParametres = new javax.swing.JLabel("Paramètres Personnels");
		
		javax.swing.JComponent componentNom = views.ViewUtilisateur.getViewForNom();
		javax.swing.JComponent componentPrenom = views.ViewUtilisateur.getViewForPrenom();
		javax.swing.JComponent componentCivilite = (javax.swing.JComboBox)views.ViewUtilisateur.getViewForCivilite("JCOMBOBOXE");
		javax.swing.JComponent componentDateNaissance = views.ViewUtilisateur.getViewForDateNaissance();
		javax.swing.JComponent componentLieuNaissance = views.ViewUtilisateur.getViewForLieuNaissance();
		javax.swing.JComponent componentLogin = views.ViewUtilisateur.getViewForLogin();
		javax.swing.JComponent componentPassword = views.ViewUtilisateur.getViewForPassword();
		javax.swing.JComponent componentPhoto = views.ViewUtilisateur.getViewForPhoto();
		javax.swing.JComponent componentRole = views.ViewUtilisateur.getViewForRole();
		javax.swing.JComponent componentParametres = views.ViewUtilisateur.getViewForParametres();
		
		javax.swing.GroupLayout layout = (javax.swing.GroupLayout)panel.getLayout();
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGap(1, 1, Short.MAX_VALUE)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelNom)
				.addComponent(labelPrenom)
				.addComponent(labelCivilite)
				.addComponent(labelDateNaissance)
				.addComponent(labelLieuNaissance)
				.addComponent(labelLogin)
				.addComponent(labelPassword)
				.addComponent(labelPhoto)
				.addComponent(labelRole)
				.addComponent(labelParametres)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(componentNom, 300, 300, 300)
				.addComponent(componentPrenom, 300, 300, 300)
				.addComponent(componentCivilite, 300, 300, 300)
				.addComponent(componentDateNaissance, 300, 300, 300)
				.addComponent(componentLieuNaissance, 300, 300, 300)
				.addComponent(componentLogin, 300, 300, 300)
				.addComponent(componentPassword, 300, 300, 300)
				.addComponent(componentPhoto, 300, 300, 300)
				.addComponent(componentRole, 300, 300, 300)
				.addComponent(componentParametres, 300, 300, 300)
			)
			.addGap(1, 1, Short.MAX_VALUE)
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addGap(1, 1, Short.MAX_VALUE)
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
				.addComponent(labelCivilite)
				.addComponent(componentCivilite, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelDateNaissance)
				.addComponent(componentDateNaissance, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelLieuNaissance)
				.addComponent(componentLieuNaissance, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelLogin)
				.addComponent(componentLogin, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelPassword)
				.addComponent(componentPassword, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelPhoto)
				.addComponent(componentPhoto, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelRole)
				.addComponent(componentRole, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelParametres)
				.addComponent(componentParametres, 20, 20, 20)
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
		
		javax.swing.JLabel labelNom = new javax.swing.JLabel("Nom");
		javax.swing.JLabel labelPrenom = new javax.swing.JLabel("Prénom");
		javax.swing.JLabel labelCivilite = new javax.swing.JLabel("Civilité");
		javax.swing.JLabel labelDateNaissance = new javax.swing.JLabel("Date de naissance");
		javax.swing.JLabel labelLieuNaissance = new javax.swing.JLabel("Lieu de naissance");
		javax.swing.JLabel labelLogin = new javax.swing.JLabel("Login (Idnetifiant)");
		javax.swing.JLabel labelPassword = new javax.swing.JLabel("Mot de Passe");
		javax.swing.JLabel labelPhoto = new javax.swing.JLabel("Photo");
		javax.swing.JLabel labelRole = new javax.swing.JLabel("Role");
		javax.swing.JLabel labelParametres = new javax.swing.JLabel("Paramètres Personnels");
		
		javax.swing.JComponent componentNom = views.ViewUtilisateur.getViewForNom();
		javax.swing.JComponent componentPrenom = views.ViewUtilisateur.getViewForPrenom();
		javax.swing.JComponent componentCivilite = (javax.swing.JComboBox)views.ViewUtilisateur.getViewForCivilite("JCOMBOBOXE");
		javax.swing.JComponent componentDateNaissance = views.ViewUtilisateur.getViewForDateNaissance();
		javax.swing.JComponent componentLieuNaissance = views.ViewUtilisateur.getViewForLieuNaissance();
		javax.swing.JComponent componentLogin = views.ViewUtilisateur.getViewForLogin();
		javax.swing.JComponent componentPassword = views.ViewUtilisateur.getViewForPassword();
		javax.swing.JComponent componentPhoto = views.ViewUtilisateur.getViewForPhoto();
		javax.swing.JComponent componentRole = views.ViewUtilisateur.getViewForRole();
		javax.swing.JComponent componentParametres = views.ViewUtilisateur.getViewForParametres();

		gui.utils.GroupLayouter.LineForm lineForm;
		lineForm = new gui.utils.GroupLayouter.LineForm(labelNom, componentNom);
		lineForm.setHeight(25);
		mapLabelsFields.put("nom", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelPrenom, componentPrenom);
		lineForm.setHeight(25);
		mapLabelsFields.put("prenom", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelCivilite, componentCivilite);
		lineForm.setHeight(25);
		mapLabelsFields.put("civilite", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelDateNaissance, componentDateNaissance);
		lineForm.setHeight(25);
		mapLabelsFields.put("datenaissance", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelLieuNaissance, componentLieuNaissance);
		lineForm.setHeight(25);
		mapLabelsFields.put("lieunaissance", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelLogin, componentLogin);
		lineForm.setHeight(25);
		mapLabelsFields.put("login", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelPassword, componentPassword);
		lineForm.setHeight(25);
		mapLabelsFields.put("password", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelPhoto, componentPhoto);
		lineForm.setHeight(25);
		mapLabelsFields.put("photo", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelRole, componentRole);
		lineForm.setHeight(25);
		mapLabelsFields.put("role", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelParametres, componentParametres);
		lineForm.setHeight(25);
		mapLabelsFields.put("parametres", lineForm);

		javax.swing.JComponent[][] matrice ={
			{labelNom, componentNom},
			{labelPrenom, componentPrenom},
			{labelCivilite, componentCivilite},
			{labelDateNaissance, componentDateNaissance},
			{labelLieuNaissance, componentLieuNaissance},
			{labelLogin, componentLogin},
			{labelPassword, componentPassword},
			{labelPhoto, componentPhoto},
			{labelRole, componentRole},
			{labelParametres, componentParametres},
		};

		gui.utils.GroupLayouter.Form form = new gui.utils.GroupLayouter.Form(matrice, 50, 50);
		gui.utils.GroupLayouter.createSimpleForm(panel, form);		
		return mapLabelsFields;
	}

	public static class DlgAddModifyUtilisateur extends javax.swing.JDialog{
		private static final long serialVersionUID = 1L;
		
		protected javax.swing.JPanel		pFormulaire;
		protected java.util.Map<String, gui.utils.GroupLayouter.LineForm>	mapAttributLineForm;
		protected javax.swing.JSeparator	sepBottom;
		protected javax.swing.JButton		bValidate, bCancel;
		
		protected models.beans.Utilisateur utilisateur = null;
		
		public DlgAddModifyUtilisateur(java.awt.Window parent, String title){
			this(parent, title, null);
		}
		
		public DlgAddModifyUtilisateur(java.awt.Window parent, String title, models.beans.Utilisateur utilisateur){
			super(parent);
			initComponents();
			this.setUtilisateur(utilisateur);
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
			Utilisateur utilisateur = this.getUtilisateur();
			if (utilisateur == null){
				utilisateur = new Utilisateur();
			}
			
			utilisateur.setNom(mapAttributLineForm.get("nom").getValueAsString());
			utilisateur.setPrenom(mapAttributLineForm.get("prenom").getValueAsString());
			utilisateur.setCivilite(models.beans.Utilisateur.Civilite.getByValue(mapAttributLineForm.get("civilite").getValueAsString()));
			utilisateur.setDateNaissance(mapAttributLineForm.get("datenaissance").getValueAsDate());
			utilisateur.setLieuNaissance(mapAttributLineForm.get("lieunaissance").getValueAsString());
			utilisateur.setLogin(mapAttributLineForm.get("login").getValueAsString());
			utilisateur.setPassword(mapAttributLineForm.get("password").getValueAsString());
			
			int idPhoto = utilisateur.getIdPhoto().intValue();
			if (mapAttributLineForm.get("photo").getValue() != null && mapAttributLineForm.get("photo").getValue() instanceof models.beans.Photo){
				idPhoto = ((models.beans.Photo)(mapAttributLineForm.get("photo").getValue())).getId().intValue();
			}
			utilisateur.setIdPhoto(idPhoto);
			
			int idRole = utilisateur.getIdRole().intValue();
			if (mapAttributLineForm.get("role").getValue() != null && mapAttributLineForm.get("role").getValue() instanceof models.beans.Role){
				idRole = ((models.beans.Role)(mapAttributLineForm.get("role").getValue())).getId().intValue();
			}
			utilisateur.setIdRole(idRole);
			
			int idParametres = utilisateur.getIdParametres().intValue();
			if (mapAttributLineForm.get("parametres").getValue() != null && mapAttributLineForm.get("parametres").getValue() instanceof models.beans.ParametresApplicationUtilisateur){
				idParametres = ((models.beans.ParametresApplicationUtilisateur)(mapAttributLineForm.get("parametres").getValue())).getId().intValue();
			}
			utilisateur.setIdParametres(idParametres);
			
			utilisateur.setId((Integer)communication.SocketCommunicator.getInstance().sendQuery(utilisateur));
			this.setUtilisateur(utilisateur);
			
			this.setVisible(false);
		}
		
		protected void bCancelActionPerformed(){
			this.setUtilisateur(null);
			this.setVisible(false);
		}
		
		public void setUtilisateur(models.beans.Utilisateur utilisateur) {
			this.utilisateur = utilisateur;
			updateForm();
		}
		
		public models.beans.Utilisateur getUtilisateur() {
			return this.utilisateur;
		}
		
		protected void emptyForm(){
			mapAttributLineForm.get("nom").setValue(null);
			mapAttributLineForm.get("prenom").setValue(null);
			mapAttributLineForm.get("civilite").setValue(null);
			mapAttributLineForm.get("datenaissance").setValue(null);
			mapAttributLineForm.get("lieunaissance").setValue(null);
			mapAttributLineForm.get("login").setValue(null);
			mapAttributLineForm.get("password").setValue(null);
			mapAttributLineForm.get("photo").setValue(null);
			mapAttributLineForm.get("role").setValue(null);
			mapAttributLineForm.get("parametres").setValue(null);
		}
		
		protected void updateForm(){
			if (utilisateur == null){
				emptyForm();
				return;
			}
			
			mapAttributLineForm.get("nom").setValue(utilisateur.getNom());
			mapAttributLineForm.get("prenom").setValue(utilisateur.getPrenom());
			mapAttributLineForm.get("civilite").setValue(utilisateur.getCivilite().getValue());
			mapAttributLineForm.get("datenaissance").setValue(utilisateur.getDateNaissance().toString());
			mapAttributLineForm.get("lieunaissance").setValue(utilisateur.getLieuNaissance());
			mapAttributLineForm.get("login").setValue(utilisateur.getLogin());
			mapAttributLineForm.get("password").setValue(utilisateur.getPassword());
			mapAttributLineForm.get("photo").setValue(utilisateur.getPhoto());
			mapAttributLineForm.get("role").setValue(utilisateur.getRole());
			mapAttributLineForm.get("parametres").setValue(utilisateur.getParametres());
		}
	}
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}