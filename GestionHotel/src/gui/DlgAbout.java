package gui;

import gui.utils.GUIButtonFactory;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

/**
 * 
 * @author OUZEGGANE Redouane
 *
 */
public class DlgAbout extends JDialog{
	private static final long serialVersionUID = 1L;
	
	private static DlgAbout instance = null;
	
	private JLabel			lPhoto;
	private JLabel			lAppName;
	private JEditorPane		epPersonnalInformation;
	private JScrollPane		spPersonnalInformation;
	
	private JButton			bFermer;
	
	private Thread			threadTextGeneric;
	
	public static DlgAbout getInstance(JFrame myParent) {
		if (instance == null){
			instance = new DlgAbout(myParent);
		}
		instance.scrollUp();
		return instance;
	}
	
	public DlgAbout(JFrame myParent) {
		super(myParent, true);
		this.setTitle("À Propos ...");
		initComponents();
	}
	
	private void initComponents(){
		threadTextGeneric = new Thread(new Runnable() {
			@SuppressWarnings("deprecation")
			public void run() {
				Thread thread = Thread.currentThread();
				thread.suspend();
				while (true){
					try{
						Thread.sleep(500);
						scrollDown();
					}
					catch (Exception e){
					}
				}
			}
		});
		
		threadTextGeneric.start();
		
		lPhoto = new JLabel();
		lPhoto.setBorder(BorderFactory.createEtchedBorder());
		lPhoto.setIcon(app.utils.FrameworkRessources.MY_PHOTO_100);
		
		lAppName = new JLabel("<html><center>Logiciel de Gestion du Stock</center></html>");
		lAppName.setHorizontalAlignment(JLabel.CENTER);
		lAppName.setVerticalAlignment(JLabel.CENTER);
		lAppName.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		
		epPersonnalInformation = new JEditorPane("text/html", "");
		epPersonnalInformation.setEditable(false);
		
		spPersonnalInformation = new JScrollPane(epPersonnalInformation);
		spPersonnalInformation.getVerticalScrollBar().setPreferredSize (new Dimension(0,0));
		spPersonnalInformation.setWheelScrollingEnabled(false);
		
		epPersonnalInformation.setBackground(Color.BLACK);
		epPersonnalInformation.setForeground(Color.WHITE);
		
		String personnalInformation = "<hmtl><body> <font color='white'>";
		personnalInformation += "<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>";
		personnalInformation += "M<sup>r.</sup> <i>OUZEGGANE Redouane</i>";
		personnalInformation += "<br/><br/>Assurant l'enseignement des modules B.T.W. et Alogrithmique au sein du Département Téchnologie, université A.Mira-Bejaia.";
		personnalInformation += "<br/><br/>Responsable de la section Système d'Information au CSRICTED à l'université A.Mira-Bejaia de 12 Janvier 2011 au 03 Février 2013.";
		personnalInformation += "<br/><br/>Concepteur et Développeur de l'Application des inscriptions des Bacheliers / Masters à l'université de Béjaia";
		personnalInformation += "<br/><br/> <center>Email : "+app.utils.FrameworkConstantes.MY_EMAILS+"</center>";
		personnalInformation += "<br/><br/> N° Tél : "+app.utils.FrameworkConstantes.MY_PHONE_NUMBERS;
		personnalInformation += "<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>";
		personnalInformation += "</font></body></html>";
		epPersonnalInformation.setText(personnalInformation);
		
		bFermer = GUIButtonFactory.createSimpleButton("Fermer", app.utils.FrameworkRessources.CLOSE_ICON_10_10, 'F');
		
		this.setResizable(false);
		
		configureRootPane(this.getRootPane());
		
		allLayout();
		allEvents();
	}
	
	private void configureRootPane(JRootPane rootPane) {
		InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escPressed");
		rootPane.getActionMap().put("escPressed",
			new AbstractAction("escPressed") {
				private static final long serialVersionUID = 1L;
				
				public void actionPerformed(ActionEvent actionEvent) {
					fermer();
				}
			}
		);
	}
	
	private void scrollUp(){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JScrollBar scrollBar = instance.spPersonnalInformation.getVerticalScrollBar();
				scrollBar.setMinimum(0);
				scrollBar.setMaximum(100);
				
				scrollBar.setValue(scrollBar.getMinimum());
			}
		});
	}
	
	private void scrollDown(){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JScrollBar scrollBar = instance.spPersonnalInformation.getVerticalScrollBar();
				int newValue = scrollBar.getValue();
				if (newValue == 730){
					newValue = scrollBar.getMinimum();
				}
				else{
					newValue = Math.min(newValue += 10, scrollBar.getMaximum());
				}
				
				scrollBar.setValue(newValue);
			}
		});
	}
	
	private void allLayout(){
		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);
		
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
						.addGap(10, 10, 10)
						.addComponent(lAppName, 400, 400, Short.MAX_VALUE)
						.addGap(10, 10, 10)
				)
				.addGroup(layout.createSequentialGroup()
						.addGap(10, 10, 10)
						.addComponent(lPhoto, app.utils.FrameworkRessources.MY_PHOTO_100.getIconWidth(), app.utils.FrameworkRessources.MY_PHOTO_100.getIconWidth(), app.utils.FrameworkRessources.MY_PHOTO_100.getIconWidth())
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(spPersonnalInformation, 200, 200, Short.MAX_VALUE)
						.addGap(10, 10, 10)
				)
				.addGroup(layout.createSequentialGroup()
						.addGap(10, 10, Short.MAX_VALUE)
						.addComponent(bFermer)
						.addGap(10, 10, 10)
				)
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGap(10)
				.addComponent(lAppName, 30, 30, 30)
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(layout.createParallelGroup()
						.addComponent(lPhoto, app.utils.FrameworkRessources.MY_PHOTO_100.getIconHeight(), app.utils.FrameworkRessources.MY_PHOTO_100.getIconHeight(), app.utils.FrameworkRessources.MY_PHOTO_100.getIconHeight())
						.addComponent(spPersonnalInformation, 300, 300, Short.MAX_VALUE)
				)
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(bFermer, 20, 20, 20)
				.addGap(10)
		);
		
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	private void allEvents(){
		bFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				fermer();
			}
		});
	}
	
	@SuppressWarnings("deprecation")
	private void fermer(){
		instance.threadTextGeneric.suspend();
		this.setVisible(false);
	}
	
	@SuppressWarnings("deprecation")
	public static void showFrame(JFrame myParent){
		getInstance(myParent);
		instance.threadTextGeneric.resume();
		instance.setVisible(true);
	}
}
