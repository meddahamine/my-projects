------------------------------------------------RAPPORT-----------------------------------------------------------------

Dans le cadre du projet de python 2015-2016 nous avons essayer de faire la totalit� des point (nous n'avons pas vraiment r�ussi):

Difficult�:
------------
-Les temps d'executions se r�v�le important quand les image sont de grande r�solution. 
-D�finition technique d'un tableau difficile a d�termin�.


Demarche suivi:
---------------
-On charge une image avec le package OpenCV (imread()).
-On la redimensionne. 
-On la transforme en niveaux de gris.
-On d�tecte les bords avec l�algorithme de � Canny � impl�mentait par OpenCV.
-On applique ensuite l�algorithme de d�tection des droites de OpenCV(HoughLines()) en lui passant le r�sultat de � Canny �. 
-Nous trions les lignes trouv�s :
	+Entre horizontales et verticales.
	+Dans chaque cat�gorie nous rep�rant celles qui ont un minimum de distance entre elle.
	+Nous formant les quadrilat�res (2 verticales, 2 horizontales) pouvant �tre un tableau :
		�Deux angles minimum sont �gaux.
		�Les angles du quadrilat�re sont dans l�intervalle [85 ; 95] 
-Apr�s d�tection nous tra�ons les lignes du quadrilat�re pour l�affichage en mode d�bogage.
-Nous calculons les points d�intersection et nous les repr�sentant sous forme de cercles (toujours pour le mode d�bogage)
-On redresse la partie de l�image extraite (cv2.getPerspectiveTransform()puis cv2.warpPerspective()).
-La partie am�lioration de l�image n�est pas tr�s aboutie(On ne la pas incluse dans le r�sultat final).
-Nous retournons le r�sultat sous forme d�image JPEG (resultat.jpg) apr�s l�avoir affich� a l��cran.


Utilisation:
------------
Il suffit de lancer le fichier projet.py avec le terminal en lui passant le PATH de l'image comme premier argument
Les options :
	-Un "D" comme deuxieme argumentpour le mode d�bogage
	-Un "A" pour une am�lioration du rendu final.