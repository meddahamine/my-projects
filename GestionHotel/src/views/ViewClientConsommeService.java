package views;

import java.io.File;

import models.daos.client.DAOClientConsommeService;
import models.beans.ClientConsommeService;

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

public abstract class ViewClientConsommeService {
	
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

	public static javax.swing.JComboBox getViewForService(){
		java.util.List<models.beans.Service> list = models.daos.client.DAOService.getListInstances();
		javax.swing.JComboBox view = new gui.utils.GUIPanelFactory.JEditedComboBox(list.toArray());
		view.insertItemAt("Choisir Service", 0);
		return view;
	}

	public static String getCaptionForService(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("", userParameters.getLang().getCodeLang());
		}

		return "";
	}

	public static javax.swing.JTextField getViewForPrixService(){
		return gui.utils.GUIFieldFactory.createNumericField(10, 10);
	}

	public static String getCaptionForPrixService(){
		if (userParameters != null && userParameters.getLang() != null){
			return models.daos.client.DAOTranslation.translate("", userParameters.getLang().getCodeLang());
		}

		return "";
	}

	public static gui.utils.GUIDate getViewForDateConsommationService(){
		return new gui.utils.GUIDate();
	}

	public static String getCaptionForDateConsommationService(){
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
		
		return header;
	}

	public static java.util.Vector<Object> getTableRow(models.beans.ClientConsommeService clientConsommeService, int numOrder){
		java.util.Vector<Object> row = new java.util.Vector<Object>();
		
		if (numOrder > 0)
			row.add(numOrder);
		
		row.add(clientConsommeService.getClient());
		row.add(clientConsommeService.getService());
		row.add(clientConsommeService.getPrixService());
		row.add(clientConsommeService.getDateConsommationService());
		
		row.add(clientConsommeService);
		
		return row;
	}

	public static java.util.Vector<java.util.Vector<Object>> getData(java.util.List<models.beans.ClientConsommeService> list, boolean withOrder){
		return getData(list, withOrder, 0);
	}

	public static java.util.Vector<java.util.Vector<Object>> getData(java.util.List<models.beans.ClientConsommeService> list, boolean withOrder, int numRowBase){
		java.util.Vector<java.util.Vector<Object>> data = new java.util.Vector<java.util.Vector<Object>>();
		if (list == null)
			return data;
		
		int numRow = numRowBase;
		for (models.beans.ClientConsommeService clientConsommeService : list){
			if (withOrder)
				numRow++;
			
			data.add(getTableRow(clientConsommeService , numRow));
		}
		
		return data;
	}

	public static void exportToExcel(File file){
		exportToExcel(file, DAOClientConsommeService.getListInstances());
	}

	public static void exportToExcel(File file, java.util.List<ClientConsommeService> list){
		try{
			WritableWorkbook workbook = Workbook.createWorkbook(file);
			WritableSheet sheet = workbook.createSheet( "Liste des ViewClientConsommeServices", 0);

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

			Label label = new Label(0, 0, "N°", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(1, 0, "", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(2, 0, "", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(3, 0, "", formatHeaderTableStr); sheet.addCell(label);
			label = new Label(4, 0, "", formatHeaderTableStr); sheet.addCell(label);
			
			int ligneBas = 1;
			for (ClientConsommeService clientConsommeService : list){
				int i = list.indexOf(clientConsommeService);
				label = new Label(0, ligneBas+i, String.valueOf(i), formatContentStr); sheet.addCell(label);
				label = new Label(1, ligneBas+i, clientConsommeService.getIdClient().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(2, ligneBas+i, clientConsommeService.getIdService().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(3, ligneBas+i, clientConsommeService.getPrixService().toString(), formatHeaderTableStr); sheet.addCell(label);
				label = new Label(4, ligneBas+i, clientConsommeService.getDateConsommationService().toString(), formatHeaderTableStr); sheet.addCell(label);
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
				
				ClientConsommeService clientConsommeService = new ClientConsommeService();

				String value = "";
				try{
					value = sheet.getCell(1, ligne-1).getContents().trim();
					clientConsommeService.setIdClient(Integer.parseInt(value));
					value = sheet.getCell(2, ligne-1).getContents().trim();
					clientConsommeService.setIdService(Integer.parseInt(value));
					value = sheet.getCell(3, ligne-1).getContents().trim();
					clientConsommeService.setPrixService(Double.parseDouble(value));
					value = sheet.getCell(4, ligne-1).getContents().trim();
					clientConsommeService.setDateConsommationService(utils.StringUtils.getDateFromString(value));
					queries.add(DAOClientConsommeService.getSQLWriting(clientConsommeService));
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
		exportToPDF(file, DAOClientConsommeService.getListInstances());
	}

	public static void exportToPDF(File file, java.util.List<ClientConsommeService> list){
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
					ClientConsommeService clientConsommeService = null;
					if (index < list.size())
						clientConsommeService = list.get(index);
					
					cell = new PdfPCell(new Paragraph((clientConsommeService != null) ? (clientConsommeService.getClient() == null ? "" : clientConsommeService.getClient().toString()) : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((clientConsommeService != null) ? (clientConsommeService.getService() == null ? "" : clientConsommeService.getService().toString()) : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((clientConsommeService != null) ? clientConsommeService.getPrixService().toString() : " ", fontTR10));
					cell.setBorderColor(BaseColor.WHITE);
					pdfTable.addCell(cell);

					cell = new PdfPCell(new Paragraph((clientConsommeService != null) ? utils.StringUtils.formatDateFromMySQL(clientConsommeService.getDateConsommationService().toString()) : " ", fontTR10));
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
		exportToText(file, DAOClientConsommeService.getListInstances());
	}

	public static void exportToText(File file, java.util.List<ClientConsommeService> list){
		try{
			
			
		}
		catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
	}

	public static void exportToWord(File file){
		exportToWord(file, DAOClientConsommeService.getListInstances());
	}

	public static void exportToWord(File file, java.util.List<ClientConsommeService> list){
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
		
		javax.swing.JLabel labelClient = new javax.swing.JLabel("");
		javax.swing.JLabel labelService = new javax.swing.JLabel("");
		javax.swing.JLabel labelPrixService = new javax.swing.JLabel("");
		javax.swing.JLabel labelDateConsommationService = new javax.swing.JLabel("");
		
		javax.swing.JComponent componentClient = views.ViewClientConsommeService.getViewForClient();
		javax.swing.JComponent componentService = views.ViewClientConsommeService.getViewForService();
		javax.swing.JComponent componentPrixService = views.ViewClientConsommeService.getViewForPrixService();
		javax.swing.JComponent componentDateConsommationService = views.ViewClientConsommeService.getViewForDateConsommationService();
		
		javax.swing.GroupLayout layout = (javax.swing.GroupLayout)panel.getLayout();
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGap(1, 1, Short.MAX_VALUE)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelClient)
				.addComponent(labelService)
				.addComponent(labelPrixService)
				.addComponent(labelDateConsommationService)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(componentClient, 300, 300, 300)
				.addComponent(componentService, 300, 300, 300)
				.addComponent(componentPrixService, 300, 300, 300)
				.addComponent(componentDateConsommationService, 300, 300, 300)
			)
			.addGap(1, 1, Short.MAX_VALUE)
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addGap(1, 1, Short.MAX_VALUE)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelClient)
				.addComponent(componentClient, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelService)
				.addComponent(componentService, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelPrixService)
				.addComponent(componentPrixService, 20, 20, 20)
			)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(labelDateConsommationService)
				.addComponent(componentDateConsommationService, 20, 20, 20)
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
		
		javax.swing.JLabel labelClient = new javax.swing.JLabel("");
		javax.swing.JLabel labelService = new javax.swing.JLabel("");
		javax.swing.JLabel labelPrixService = new javax.swing.JLabel("");
		javax.swing.JLabel labelDateConsommationService = new javax.swing.JLabel("");
		
		javax.swing.JComponent componentClient = views.ViewClientConsommeService.getViewForClient();
		javax.swing.JComponent componentService = views.ViewClientConsommeService.getViewForService();
		javax.swing.JComponent componentPrixService = views.ViewClientConsommeService.getViewForPrixService();
		javax.swing.JComponent componentDateConsommationService = views.ViewClientConsommeService.getViewForDateConsommationService();

		gui.utils.GroupLayouter.LineForm lineForm;
		lineForm = new gui.utils.GroupLayouter.LineForm(labelClient, componentClient);
		lineForm.setHeight(25);
		mapLabelsFields.put("client", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelService, componentService);
		lineForm.setHeight(25);
		mapLabelsFields.put("service", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelPrixService, componentPrixService);
		lineForm.setHeight(25);
		mapLabelsFields.put("prixservice", lineForm);
		lineForm = new gui.utils.GroupLayouter.LineForm(labelDateConsommationService, componentDateConsommationService);
		lineForm.setHeight(25);
		mapLabelsFields.put("dateconsommationservice", lineForm);

		javax.swing.JComponent[][] matrice ={
			{labelClient, componentClient},
			{labelService, componentService},
			{labelPrixService, componentPrixService},
			{labelDateConsommationService, componentDateConsommationService},
		};

		gui.utils.GroupLayouter.Form form = new gui.utils.GroupLayouter.Form(matrice, 50, 50);
		gui.utils.GroupLayouter.createSimpleForm(panel, form);		
		return mapLabelsFields;
	}

	public static class DlgAddModifyClientConsommeService extends javax.swing.JDialog{
		private static final long serialVersionUID = 1L;
		
		protected javax.swing.JPanel		pFormulaire;
		protected java.util.Map<String, gui.utils.GroupLayouter.LineForm>	mapAttributLineForm;
		protected javax.swing.JSeparator	sepBottom;
		protected javax.swing.JButton		bValidate, bCancel;
		
		protected models.beans.ClientConsommeService clientConsommeService = null;
		
		public DlgAddModifyClientConsommeService(java.awt.Window parent, String title){
			this(parent, title, null);
		}
		
		public DlgAddModifyClientConsommeService(java.awt.Window parent, String title, models.beans.ClientConsommeService clientConsommeService){
			super(parent);
			initComponents();
			this.setClientConsommeService(clientConsommeService);
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
			ClientConsommeService clientConsommeService = this.getClientConsommeService();
			if (clientConsommeService == null){
				clientConsommeService = new ClientConsommeService();
			}
			
			
			int idClient = clientConsommeService.getIdClient().intValue();
			if (mapAttributLineForm.get("client").getValue() != null && mapAttributLineForm.get("client").getValue() instanceof models.beans.Client){
				idClient = ((models.beans.Client)(mapAttributLineForm.get("client").getValue())).getId().intValue();
			}
			clientConsommeService.setIdClient(idClient);
			
			int idService = clientConsommeService.getIdService().intValue();
			if (mapAttributLineForm.get("service").getValue() != null && mapAttributLineForm.get("service").getValue() instanceof models.beans.Service){
				idService = ((models.beans.Service)(mapAttributLineForm.get("service").getValue())).getId().intValue();
			}
			clientConsommeService.setIdService(idService);
			clientConsommeService.setPrixService(mapAttributLineForm.get("prixservice").getValueAsDouble());
			clientConsommeService.setDateConsommationService(mapAttributLineForm.get("dateconsommationservice").getValueAsDate());
			
			clientConsommeService.setId((Integer)communication.SocketCommunicator.getInstance().sendQuery(clientConsommeService));
			this.setClientConsommeService(clientConsommeService);
			
			this.setVisible(false);
		}
		
		protected void bCancelActionPerformed(){
			this.setClientConsommeService(null);
			this.setVisible(false);
		}
		
		public void setClientConsommeService(models.beans.ClientConsommeService clientConsommeService) {
			this.clientConsommeService = clientConsommeService;
			updateForm();
		}
		
		public models.beans.ClientConsommeService getClientConsommeService() {
			return this.clientConsommeService;
		}
		
		protected void emptyForm(){
			mapAttributLineForm.get("client").setValue(null);
			mapAttributLineForm.get("service").setValue(null);
			mapAttributLineForm.get("prixservice").setValue(null);
			mapAttributLineForm.get("dateconsommationservice").setValue(null);
		}
		
		protected void updateForm(){
			if (clientConsommeService == null){
				emptyForm();
				return;
			}
			
			mapAttributLineForm.get("client").setValue(clientConsommeService.getClient());
			mapAttributLineForm.get("service").setValue(clientConsommeService.getService());
			mapAttributLineForm.get("prixservice").setValue(clientConsommeService.getPrixService());
			mapAttributLineForm.get("dateconsommationservice").setValue(clientConsommeService.getDateConsommationService().toString());
		}
	}
	

	/**
		This is the part of class which you can modifty, add or remove code ....
	*/

}