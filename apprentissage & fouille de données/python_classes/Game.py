# Inutile de modifier cette classe normalement

class Game:
    # constructeur de la classe Game 
    def __init__(self,nbSticks):
        # affecter l'entier envoyer en parameter a la variable locale self.nbSticks 
        self.nbSticks = nbSticks
        
    # la fonction start qui permet de demarrer le jeux
    def start(self,player1,player2,verbose):
        # si verbose = True alors afficher New game
        if verbose: print("New game")
        #sticks recoit la valeur de la variable locale self.nbSticks
        sticks = self.nbSticks
        #currp recoit la valeur de player1 qui de type humanPlayer 
        currp = player1
        # tant que sticks(nombre de bâtons) superieur à 0 faire 
        while sticks>0:
            # si verbose = True alors afficher Remaining sticks concatener avec la valeur de la variable sticks
            if verbose: print("Remaining sticks:",sticks)
            # n recoite la valeur retrouner par la fonction play(sticks) de la classe HumanPlayer
            n = currp.play(sticks)
            # si n (nombre de bâtons retiré) inferieur strictement à 1 ou superieur a 3 afficher Error
            if n<1 or n>3: print("Error")
            # si verbose = True alors afficher le nom du joueur(currp.getName()) concatener avecle mot "takes" et la valeur qu'il a saisi
            if verbose: print(currp.getName(),"takes",n)
            # soustraire n de la variable sticks qui correpond au nombre de bâtons restant 
            sticks-=n
            # si la valeur de currp celle de player1 alors on affecte a currp la valeur de player2
            # pour laisser le tour au 2eme joueur pour jouer
            if currp==player1: currp = player2
            # sinon l'inverse de la la condition de Si
            else: currp = player1
        # a la sortie de la boucle wihle c-à-d la fin de la partie
        # si verbose = True afficher le nom de joueur avec "currp.getName()" concateneravec le mot "Win!"
        if verbose: print(currp.getName(),"wins!")
        # si le gangnant est le player1 
        if player1==currp:
            # incremente le nombre de victoires pour le joueur player1
            player1.addWin()
            player2.addLoss()
        # sinon l'inverse de la la condition de Si
        else:
            player1.addLoss()
            # incremente le nombre de victoires pour le joueur player2
            player2.addWin()
