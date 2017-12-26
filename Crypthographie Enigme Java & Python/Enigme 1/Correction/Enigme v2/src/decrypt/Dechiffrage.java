package decrypt;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class Dechiffrage {

	private static int n = 256;
	private static BigInteger bmod = new BigInteger("256");

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		String sentence = "ßú»’øw»Gú»«.ÄÄú».»”.E`Fé»”ú»E úÄw»«ß*Ä»G***EEEÄG­Ä`EEEEEEEööööööööööööÝ*»’.ÆÄ»`FàûE:ÔÐÝ©vj»…rYrY";

		// Permet de trouver la clé utilisée pour encoder la phrase "sentence"
		// par attaque par force brute.
		findKey(sentence);

		// Affiche sur la console la phrase déchiffrée.
		System.out.println(decode(sentence));
	}

	/**
	 * Cette méthode permet de trouver la clé utilisée pour encoder le message
	 * clair. On sait que x = (a^-1)*(y-b). Ici, nous avons "y" (la lettre
	 * chiffrée), donc nous essayons pour tous les a et b possibles. Une fois
	 * que l'on a trouvé tous les déchiffrements possibles, on chercher dans le
	 * fichier une phrase claire écrite en français. Devant cette phrase se
	 * trouve la clé utilisée pour déchiffrer la phrase. On a alors la clée
	 * utilisée. Ici, nous avons trouvé que la clé utilisée est (179,91).
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

				// On vérifie que a et 256 sont premiers entre eux
				if (gcdThing(a, n) == 1) {

					// On calcule l'inverse de a (c'est-à-dire a^-1)
					BigInteger ba = new BigInteger(String.valueOf(a));
					int inverse = ba.modInverse(bmod).intValue();

					// Pour chaque a, nous essayons pour tous les b possibles
					while (b < 256) {

						// On précise quelle est la clé utilisée pour déchiffrer
						// les
						// lettres
						sb.append("(" + a + "," + b + ")  ");

						// Pour chaque lettre de la phrase
						for (byte letterByte : sentence.getBytes()) {

							// On récupère la valeur ASCII de la lettre
							int y = (int) letterByte;
							if (y < 0)
								y += n;

							// On calcule y - b
							int tmp = y - b;

							// Puis on calcule x
							int x = (inverse * tmp) % n;
							if (x < 0)
								x += n;

							// On écrit la lettre déchiffrée avec la clé (a,b)
							sb.append((char) x);
						}

						// On ajoute dans un fichier la phrase déchiffrée avec
						// la
						// clé (a,b)
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
	 * Une fois la clé trouvée à l'aide de la méthode findKey(), on peut alors
	 * déchiffrer la phrase. On utilise le même algorithme que pour la méthode
	 * findKey(), mais cette fois-ci, avec une clé précise (ici, (179,91)).
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
