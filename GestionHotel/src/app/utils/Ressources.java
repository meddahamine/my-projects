package app.utils;

import gui.utils.GUIImageUtils;

import javax.swing.ImageIcon;

public abstract class Ressources {
	public static final ImageIcon CHAMBRE_ICON = new ImageIcon(Ressources.class.getResource("/ressources/imgs/app/chambre1.png"));
	public static final ImageIcon CHAMBRE_ICON_20_20 = GUIImageUtils.resizeIconTo(CHAMBRE_ICON, 20, 20);
	public static final ImageIcon CHAMBRE_ICON_40_40 = GUIImageUtils.resizeIconTo(CHAMBRE_ICON, 40, 40);
	
	public static final ImageIcon CLIENT_ICON = new ImageIcon(Ressources.class.getResource("/ressources/imgs/app/client.png"));
	public static final ImageIcon CLIENT_ICON_20_20 = GUIImageUtils.resizeIconTo(CLIENT_ICON, 20, 20);
	public static final ImageIcon CLIENT_ICON_40_40 = GUIImageUtils.resizeIconTo(CLIENT_ICON, 40, 40);
	
	
	public static final ImageIcon FACTURE_ICON = new ImageIcon(Ressources.class.getResource("/ressources/imgs/app/facture.png"));
	public static final ImageIcon FACTURE_ICON_20_20 = GUIImageUtils.resizeIconTo(FACTURE_ICON, 20, 20);
	public static final ImageIcon FACTURE_ICON_40_40 = GUIImageUtils.resizeIconTo(FACTURE_ICON, 40, 40);
	
	public static final ImageIcon CATEGORIE_ICON = new ImageIcon(Ressources.class.getResource("/ressources/imgs/app/chambre2.jpg"));
	public static final ImageIcon CATEGORIE_ICON_20_20 = GUIImageUtils.resizeIconTo(CATEGORIE_ICON, 20, 20);
	public static final ImageIcon CATEGORIE_ICON_40_40 = GUIImageUtils.resizeIconTo(CATEGORIE_ICON, 40, 40);

	public static final ImageIcon SERVICE_ICON = new ImageIcon(Ressources.class.getResource("/ressources/imgs/app/service.png"));
	public static final ImageIcon SERVICE_ICON_20_20 = GUIImageUtils.resizeIconTo(SERVICE_ICON, 20, 20);
	public static final ImageIcon SERVICE_ICON_40_40 = GUIImageUtils.resizeIconTo(SERVICE_ICON, 40, 40);
	
	public static final ImageIcon RESERVATION_ICON = new ImageIcon(Ressources.class.getResource("/ressources/imgs/app/reservation1.png"));
	public static final ImageIcon RESERVATION_ICON_20_20 = GUIImageUtils.resizeIconTo(RESERVATION_ICON, 20, 20);
	public static final ImageIcon RESERVATION_ICON_40_40 = GUIImageUtils.resizeIconTo(RESERVATION_ICON, 40, 40);
	
	public static final ImageIcon CONSOMMATION_ICON = new ImageIcon(Ressources.class.getResource("/ressources/imgs/app/service.jpg"));
	public static final ImageIcon CONSOMMATION_ICON_20_20 = GUIImageUtils.resizeIconTo(CONSOMMATION_ICON, 20, 20);
	public static final ImageIcon CONSOMMATION_ICON_40_40 = GUIImageUtils.resizeIconTo(CONSOMMATION_ICON, 40, 40);
	
	public static final ImageIcon SUPPRIMER_ICON = new ImageIcon(Ressources.class.getResource("/ressources/imgs/app/supprimer1.png"));
	public static final ImageIcon SUPPRIMER_ICON_20_20 = GUIImageUtils.resizeIconTo(SUPPRIMER_ICON, 20, 20);
	public static final ImageIcon SUPPRIMER_ICON_40_40 = GUIImageUtils.resizeIconTo(SUPPRIMER_ICON, 40, 40);
}