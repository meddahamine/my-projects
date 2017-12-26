package gui.crud.framework;

import gui.utils.GUIButtonFactory;
import gui.utils.GUIXTable;
import gui.utils.GUIPanelFactory.BusyLayer.RunningClassMethod;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;


import appClient.Spool.DataExport;

/**
 * 
 * @author OUZEGGANE Redouane Classe de visualisation des données effectives à
 *         sauvegarder
 */
public class DlgSaveImportDataViewer extends JDialog {
	private static final long serialVersionUID = 1L;

	private static DlgSaveImportDataViewer singleton;

	private JPanel pContent;

	private DataExport	data = null;
	
	private JTabbedPane tabbedPaneData;
	
	private JPanel		pParamsOrganisme, pRoles, pUtilisateurs;
	
	private GUIXTable	tableParamsOrganisme, tableRoles, tableUtilisateurs;
	
	private JButton 	bFermer;
	private JButton 	bRestaure;

	private DlgSaveImportDataViewer(JDialog parent) {
		super(parent);
		initComponents();
	}

	private void initComponents() {
		pContent = new JPanel();
		TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), "");
		border.setTitleJustification(TitledBorder.CENTER);
		border.setTitleFont(border.getTitleFont().deriveFont(18.0f));
		pContent.setBorder(border);

		tabbedPaneData = new JTabbedPane();
		
//		tableClients.setColumnHorizontalAlignement(4, SwingConstants.RIGHT);
//		tableClients.setColumnHorizontalAlignement(5, SwingConstants.RIGHT);
		
//		tableCommandesClients.setColumnHorizontalAlignement(5, SwingConstants.RIGHT);
		
		
//		tableFournisseurs.setColumnHorizontalAlignement(3, SwingConstants.CENTER);
		
//		tableCommandesFournisseurs.setColumnHorizontalAlignement(5, SwingConstants.RIGHT);
		
		tableParamsOrganisme = new GUIXTable(views.ViewParametresOrganisme.getTableHeader(true), views.ViewParametresOrganisme.getData(null, true));
		pParamsOrganisme = new JPanel();
		
		tableParamsOrganisme.setColumnWidth(0, 35, 35, 35);
//		tableParamsOrganisme.setColumnWidth(2, 75, 75, 75);
		
		tableRoles = new GUIXTable(views.ViewRole.getTableHeader(true), views.ViewRole.getData(null, true));
		pRoles = new JPanel();
		
		tableRoles.setColumnWidth(0, 35, 35, 35);
		
		tableUtilisateurs = new GUIXTable(views.ViewUtilisateur.getTableHeader(true), views.ViewUtilisateur.getData(null, true));
		pUtilisateurs = new JPanel();
		
		tableUtilisateurs.setColumnWidth(0, 35, 35, 35);
		
		bFermer = GUIButtonFactory.createCloseButton("Fermer", 'F');
		bRestaure = GUIButtonFactory.createSimpleButton("Restaurer", app.utils.FrameworkRessources.RESTAURE_SELECTED_ICON_20_20,'R');
		
		this.setContentPane(pContent);
		
		configureRootPane(this.getRootPane());

		allLayout();
		allEvents();
	}

	private void configureRootPane(JRootPane rootPane) {
		InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),"escPressed");
		
		rootPane.getActionMap().put("escPressed",new AbstractAction("escPressed") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent actionEvent) {
				bFermerActionPerformed(actionEvent);
			}
		});
	}

	private void setData(DataExport data) {
		this.data = data;
		
		this.tabbedPaneData.removeAll();
		
		if (data == null) {
			return;
		}
		
//		if (data.hasParametresOrganisme()){
//			this.tableParamsOrganisme.setData(ParametresOrganisme.getDataForExportViewer(data.getListParamsOrganisme(), true));
//			this.tabbedPaneData.add("Paramètres de l'organisme", pParamsOrganisme);
//		}
//		
//		if (data.hasRoles()){
//			this.tableRoles.setData(Role.getDataForExportViewer(data.getListRoles(), true));
//			this.tabbedPaneData.add("Rôles", pRoles);
//		}
//		
//		if (data.hasUtilisateurs()){
//			this.tableUtilisateurs.setData(Utilisateur.getDataForExportViewer(data.getListUtilisateurs(), true));
//			this.tabbedPaneData.add("Utilisateurs", pUtilisateurs);
//		}
	}

	private void allLayout() {
		GroupLayout layoutPParamsOrganisme = new GroupLayout(pParamsOrganisme);
		pParamsOrganisme.setLayout(layoutPParamsOrganisme);
		layoutPParamsOrganisme.setHorizontalGroup(layoutPParamsOrganisme.createSequentialGroup()
				.addGap(4, 4, 4)
				.addComponent(tableParamsOrganisme, 600, 600, Short.MAX_VALUE)
				.addGap(4, 4, 4)
		);
		layoutPParamsOrganisme.setVerticalGroup(layoutPParamsOrganisme.createSequentialGroup()
				.addGap(4, 4, 4)
				.addComponent(tableParamsOrganisme, 400, 400, Short.MAX_VALUE)
				.addGap(4, 4, 4)
		);
		
		GroupLayout layoutPRoles = new GroupLayout(pRoles);
		pRoles.setLayout(layoutPRoles);
		layoutPRoles.setHorizontalGroup(layoutPRoles.createSequentialGroup()
				.addGap(4, 4, 4)
				.addComponent(tableRoles, 600, 600, Short.MAX_VALUE)
				.addGap(4, 4, 4)
		);
		layoutPRoles.setVerticalGroup(layoutPRoles.createSequentialGroup()
				.addGap(4, 4, 4)
				.addComponent(tableRoles, 400, 400, Short.MAX_VALUE)
				.addGap(4, 4, 4)
		);
		
		GroupLayout layoutPUtilisateurs = new GroupLayout(pUtilisateurs);
		pUtilisateurs.setLayout(layoutPUtilisateurs);
		layoutPUtilisateurs.setHorizontalGroup(layoutPUtilisateurs.createSequentialGroup()
				.addGap(4, 4, 4)
				.addComponent(tableUtilisateurs, 600, 600, Short.MAX_VALUE)
				.addGap(4, 4, 4)
		);
		layoutPUtilisateurs.setVerticalGroup(layoutPUtilisateurs.createSequentialGroup()
				.addGap(4, 4, 4)
				.addComponent(tableUtilisateurs, 400, 400, Short.MAX_VALUE)
				.addGap(4, 4, 4)
		);
		
		GroupLayout layout = new GroupLayout(pContent);
		pContent.setLayout(layout);

		layout.setHorizontalGroup(layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
						.addGap(5, 5, 5)
						.addComponent(tabbedPaneData, 700, 700, Short.MAX_VALUE)
						.addGap(5, 5,5)
				)
				.addGroup(layout.createSequentialGroup()
						.addGap(5, 5, 5)
						.addComponent(bRestaure)
						.addGap(5, 5, Short.MAX_VALUE)
						.addComponent(bFermer)
						.addGap(5, 5, 5)
				)
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGap(5, 5, 5)
				.addComponent(tabbedPaneData, 500, 500, Short.MAX_VALUE)
				.addGap(5, 5, 5)
				.addGroup(layout.createParallelGroup()
						.addComponent(bRestaure, 20, 20, 20)
						.addComponent(bFermer, 20, 20, 20)
				)
				.addGap(5, 5,5)
		);

		this.pack();
		this.setMinimumSize(this.getSize());
		this.setLocationRelativeTo(this.getParent());
	}

	private void allEvents() {
		bFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				bFermerActionPerformed(evt);
			}
		});
		
		bRestaure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				bIRestaureActionPerformed(evt);
			}
		});
	}
	
	public void restaure(){
		try{
			data.importData();
			gui.utils.GUIPanelFactory.BusyLayer.hideBusyMessage();
		}
		catch (Exception e){
			utils.StringUtils.printDebug(e);
		}
	}
	
	private void bIRestaureActionPerformed(ActionEvent evt){
		RunningClassMethod runningClassMethod = new RunningClassMethod();
		runningClassMethod.classMethodToRun = this.getClass();
		runningClassMethod.instanceOfClass = this;
		runningClassMethod.methodToRun = "restaure";
		
		Class<?>[] paramsTypes = {};
		runningClassMethod.paramsMethodeTypes = paramsTypes;

		Object[] paramsValues = {};
		runningClassMethod.paramsMethodeValues = paramsValues;
		
		String messageWaiting = "<html><body><center><font size='5'>Veuillez patienter, S.V.P !!!</font>";
		messageWaiting += "<br/><font size='2'>Importation de données en cours</font>";
		messageWaiting += "</center></body></html>";
		gui.utils.GUIPanelFactory.BusyLayer.showBusyMessage(runningClassMethod, messageWaiting);
	}

	private void bFermerActionPerformed(ActionEvent actionEvent) {
		this.setData(null);
		this.setVisible(false);
	}

	public void updateTitle(boolean forImport) {
		String title = "Visualisation des Données à ";
		if (forImport){
			title += "Restaurer";
		}
		else{
			title += "Sauvegarder";
		}
		
		bRestaure.setVisible(forImport);
		
		this.setTitle(title);
	}
	
	public void setTitle(String title) {
		TitledBorder border = (TitledBorder)pContent.getBorder();
		
		border.setTitle(title);
		super.setTitle(title);
	}
	
	public static DlgSaveImportDataViewer getSingleton(JDialog parent) {
		if (singleton == null) {
			singleton = new DlgSaveImportDataViewer(parent);
		}
		
		return singleton;
	}
	
	public static void showFrame(JDialog parent, DataExport data) {
		showFrame(parent, data, false);
	}
	
	public static void showFrame(JDialog parent, DataExport data, boolean forImport) {
		getSingleton(parent);
		
		singleton.updateTitle(forImport);
		singleton.setData(data);

		singleton.setVisible(true);
	}
}