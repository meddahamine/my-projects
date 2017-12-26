package decrypt;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class Dechiffrage {

	private static int n = 256;
	private static BigInteger bmod = new BigInteger("256");

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		String sentence = "�����w�G���.����.��.E`F黔��E���w���*ĻG***EEE�G��`EEEEEEE�������������*��.�Ļ`F��E:��ݩvj��rYrY";

		// Permet de trouver la cl� utilis�e pour encoder la phrase "sentence"
		// par attaque par force brute.
		findKey(sentence);

		// Affiche sur la console la phrase d�chiffr�e.
		System.out.println(decode(sentence));
	}

	/**
	 * Cette m�thode permet de trouver la cl� utilis�e pour encoder le message
	 * clair. On sait que x = (a^-1)*(y-b). Ici, nous avons "y" (la lettre
	 * chiffr�e), donc nous essayons pour tous les a et b possibles. Une fois
	 * que l'on a trouv� tous les d�chiffrements possibles, on chercher dans le
	 * fichier une phrase claire �crite en fran�ais. Devant cette phrase se
	 * trouve la cl� utilis�e pour d�chiffrer la phrase. On a alors la cl�e
	 * utilis�e. Ici, nous avons trouv� que la cl� utilis�e est (179,91).
	 * 
	 */

	public static void findKey(String sentence) {
		int a = 2;
		int b = 0;

		StringBuilder sb = new StringBuilder();
		PrintWriter writer;
		try {
			writer = new PrintWriter("all_possibilities.txt", "UTF-8");

			// Pour tous les a possibles
			while (a < 256) {

				// On v�rifie que a et 256 sont premiers entre eux
				if (gcdThing(a, n) == 1) {

					// On calcule l'inverse de a (c'est-�-dire a^-1)
					BigInteger ba = new BigInteger(String.valueOf(a));
					int inverse = ba.modInverse(bmod).intValue();

					// Pour chaque a, nous essayons pour tous les b possibles
					while (b < 256) {

						// On pr�cise quelle est la cl� utilis�e pour d�chiffrer
						// les
						// lettres
						sb.append("(" + a + "," + b + ")  ");

						// Pour chaque lettre de la phrase
						for (byte letterByte : sentence.getBytes()) {

							// On r�cup�re la valeur ASCII de la lettre
							int y = (int) letterByte;
							if (y < 0)
								y += n;

							// On calcule y - b
							int tmp = y - b;

							// Puis on calcule x
							int x = (inverse * tmp) % n;
							if (x < 0)
								x += n;

							// On �crit la lettre d�chiffr�e avec la cl� (a,b)
							sb.append((char) x);
						}

						// On ajoute dans un fichier la phrase d�chiffr�e avec
						// la
						// cl� (a,b)
						writer.println(sb.toString());
						sb = new StringBuilder();
						b++;
					}
				}
				b = 0;
				a++;
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Une fois la cl� trouv�e � l'aide de la m�thode findKey(), on peut alors
	 * d�chiffrer la phrase. On utilise le m�me algorithme que pour la m�thode
	 * findKey(), mais cette fois-ci, avec une cl� pr�cise (ici, (179,91)).
	 * 
	 */

	public static String decode(String sentence) {
		int keyA = 179;
		int keyB = 91;
		StringBuilder sb = new StringBuilder();
		BigInteger ba = new BigInteger(String.valueOf(keyA));
		int inverse = ba.modInverse(bmod).intValue();

		for (byte letterByte : sentence.getBytes()) {
			int y = (int) letterByte;
			if (y < 0)
				y += n;

			int tmp = y - keyB;

			int x = (inverse * tmp) % n;
			if (x < 0)
				x += n;
			sb.append((char) x);
		}
		return sb.toString();
	}

	/**
	 * Permet de trouver le PGCD de a et b
	 * 
	 */

	private static int gcdThing(int a, int b) {
		BigInteger b1 = BigInteger.valueOf(a);
		BigInteger b2 = BigInteger.valueOf(b);
		BigInteger gcd = b1.gcd(b2);
		return gcd.intValue();
	}

}
