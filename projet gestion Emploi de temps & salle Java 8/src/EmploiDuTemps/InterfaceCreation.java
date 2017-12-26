package EmploiDuTemps;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface InterfaceCreation.
 */
public interface InterfaceCreation <T> {
	/**
	 * methode creerListe()  générique qui permet de creer une nouvelle liste de type souhaité avec un nombre variable d'arguments.   
	 * @param un élément de type T.
	 * @return une Liste d'elements de type T.
	 */
	default List<T> creerListe(T... args){
		List<T> liste = new ArrayList<>();
			for (T s: args)
				liste.add(s);
		return liste;		
		}
	
}

