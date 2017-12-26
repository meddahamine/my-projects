package gui;

import gui.utils.GUIButtonFactory;
import gui.utils.GUIMessageByOptionPane;
import gui.utils.GUIPanelFactory.TransparentBackground;

import java.awt.Color;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle;

import utils.StringUtils;

/**
 * 
 * @author OUZEGGANE Redouane
 *
 */
public class DlgLocker extends JDialog{
	private static final long serialVersionUID = 1L;
	
	private static DlgLocker instance = null;

	private JFrame				myParent = null;
	
	private JLabel				lMessage;
	private JLabel				lPassword;
	private JPasswordField		pwfPassword;
	private JButton				bDeverouiller, bAnnuler;
	
	private JPanel				pContent;
	
	private TransparentBackground		transparentBackground;
	
	public static DlgLocker getInstance(JFrame myParent) {
		if (instance == null){
			instance = new DlgLocker(myParent);
		}
		
		instance.initFields();
		return instance;
	}
	
	private DlgLocker(JFrame myParent){
		super(myParent, true);
		this.myParent = myParent;
		
		initComponents();
	}
	

	private void initFields(){
		transparentBackground.setAlpha(0.7f);
		transparentBackground.setBackground(Color.BLUE.brighter().brighter());
		transparentBackground.setRefresh(false);
		
		Container content = myParent.getContentPane();
		transparentBackground.setBoundsImage(content.getLocationOnScreen(), content.getSize());
		
		transparentBackground.forceUpdateBackground();
		
		Rectangle bounds = myParent.getBounds();
		
		models.beans.Utilisateur user = appClient.Spool.getUser();
		
		String message = user.getCivilite().getValue()+" "+user.getPrenom()+" "+user.getNom()+", Votre session est verrouillée.<br/> Saisissez le mot de passe pour la dévérouiller";
		lMessage.setText("<html><body><font size='5'><center>"+message+"</center></font></body></html>");
		lMessage.setHorizontalAlignment(JLabel.CENTER);
		lMessage.setVerticalAlignment(JLabel.CENTER);
		pwfPassword.setText("");
		
		updateLayoutPContent();
		
		this.pContent.setVisible(false);
		
		this.setBounds(bounds);
	}
	
	private void initComponents(){
		transparentBackground = new TransparentBackground(this);
		transparentBackground.setAlpha(0.6f);
		this.setContentPane(transparentBackground);
		
		lMessage = new JLabel();
		lMessage.setBorder(BorderFactory.createEtchedBorder());
		
		lPassword = new JLabel("Mot de passe : ");
		pwfPassword = new JPasswordField();
		
		bDeverouiller = GUIButtonFactory.createSimpleButton("Dévérouiller", app.utils.FrameworkRessources.UNLOCK_ICON_5_20_20);
		bAnnuler = GUIButtonFactory.createCancelButton("Annuler");
		
		pContent = new JPanel();
		pContent.setBorder(BorderFactory.createEtchedBorder());
		
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Session Vérouillée");
		
		this.getRootPane().setDefaultButton(bDeverouiller);
		configureRootPane(this.getRootPane());
		
		allLayouts();
		allEvents();
	}
	
	private void allLayouts(){
		GroupLayout layoutPContent = new GroupLayout(pContent);
		pContent.setLayout(layoutPContent);
		
		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGap(10, 10, Short.MAX_VALUE)
				.addComponent(pContent, 600, 600, 600)
				.addGap(10, 10, Short.MAX_VALUE)
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGap(10, 10, Short.MAX_VALUE)
				.addComponent(pContent, 200, 200, 200)
				.addGap(10, 10, Short.MAX_VALUE)
		);
		
		this.setBounds(myParent.getBounds());
	}
	
	private void updateLayoutPContent(){
		pContent.removeAll();
		GroupLayout layoutPContent = (GroupLayout)pContent.getLayout();
		layoutPContent.setHorizontalGroup(layoutPContent.createParallelGroup()
				.addGroup(layoutPContent.createSequentialGroup()
						.addGap(10, 10, 10)
						.addComponent(lMessage, 10, 10, Short.MAX_VALUE)
						.addGap(10, 10, 10)
				)
				.addGroup(layoutPContent.createSequentialGroup()
						.addGap(10, 10, Short.MAX_VALUE)
						.addComponent(lPassword)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(pwfPassword, 200, 200, 200)
						.addGap(10, 10, Short.MAX_VALUE)
				)
				.addGroup(layoutPContent.createSequentialGroup()
						.addGap(10, 10, Short.MAX_VALUE)
						.addComponent(bDeverouiller)
						.addGap(10, 10, Short.MAX_VALUE)
						.addComponent(bAnnuler)
						.addGap(10, 10, Short.MAX_VALUE)
				)
		);
		layoutPContent.setVerticalGroup(layoutPContent.createSequentialGroup()
				.addGap(10, 10, Short.MAX_VALUE)
				.addComponent(lMessage)
				.addGap(30, 30, 30)
				.addGroup(layoutPContent.createParallelGroup()
						.addComponent(lPassword)
						.addComponent(pwfPassword, 20, 20, 20)
				)
				.addGap(30, 30, 30)
				.addGroup(layoutPContent.createParallelGroup()
						.addComponent(bAnnuler, 20, 20, 20)
						.addComponent(bDeverouiller, 20, 20, 20)
				)
				.addGap(10, 10, Short.MAX_VALUE)
		);
	}
	
	private void allEvents(){
		bDeverouiller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				bDeverouillerActionPerformed(evt);
			}
		});
		
		bAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				bAnnulerActionPerformed(evt);
			}
		});
		
		this.getContentPane().addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent evt){
				thisMouseDragged();
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt){
				thisMouseDragged();
			}
		});
	}
	
	private void configureRootPane(JRootPane rootPane) {
		InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escPressed");
		rootPane.getActionMap().put(
		"escPressed",
		new AbstractAction("escPressed") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent actionEvent) {
				onKeyEscape();
			}
		});
	}

	private void onKeyEscape(){
		bAnnulerActionPerformed(null);
	}
	
	private void thisMouseDragged(){
		if (!this.pContent.isVisible()){
			this.pContent.setVisible(true);
			this.repaint();
			this.getRootPane().setDefaultButton(bDeverouiller);
			pwfPassword.requestFocus();
		}
	}
	
	@SuppressWarnings("deprecation")
	private void bDeverouillerActionPerformed(ActionEvent evt){
		String password = pwfPassword.getText().trim();
		if (password.equals("")){
			GUIMessageByOptionPane.showErrorMessage(this, "Mot de passe vide", "Veuillez introduire votre mot de passe");
			return;
		}
		
		if (appClient.Spool.getUser().getPassword().equals(StringUtils.encodeMD5(password))){
			this.setVisible(false);
			myParent.setVisible(true);
		}
		else{
			GUIMessageByOptionPane.showErrorMessage(this, "Mot de passe erroné", "le mot de passe est erronée, veuillez intoduire du nouveau le mot de passe");
		}
	}
	
	private void bAnnulerActionPerformed(ActionEvent evt){
		this.pContent.setVisible(false);
		this.repaint();
	}
	
	public static void showFrame(JFrame parent){
		getInstance(parent);
		
		instance.myParent.setVisible(false);
		instance.setVisible(true);
	}
}
