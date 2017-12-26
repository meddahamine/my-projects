# les importation
#importation de la biblothèque random
import random
#importation du fichier Neuron
from Neuron import *

class Player:
    #constructeur de la classe Player permet l'initialisation du nom du
    # joueur et son nombre de victoires.
    def __init__(self,name):
        self.name = name
        # initialiser le nombre de victoire a 0.
        self.nbWin = 0

    # getName permet de retourner le nom du joueur
    def getName(self):
        return self.name
    # getNbWin permet de retourner le nombre de victoire du joueur
    def getNbWin(self):
        return self.nbWin
    # addWin permet de d'incrémenter le nombre de victoire du joueur
    def addWin(self):
        self.nbWin+=1
    # addLoss permet d'avoir la méthode au cas ou on souhaite effectuer un 
    # traitement plus tard.
    def addLoss(self):
        pass

# la Classe HumainPlayer permet de créer un objet qui contient
# les informations d'un joueur humain.
class HumanPlayer(Player):
    # la fonction play permet de jouer une partie
    # elle récupère et vérifie l'entrée saisie par l'utilisateur.
    def play(self,sticks):
        # si le nombre de sticks = 1 alors retoune 1 c-à-d le joueur n'a pas besoin de saisir la valeur d'entrée
        if sticks==1: return 1
        # sinon l'inverse de la condition de Si
        else:
            # initailiser la variable boolean correct à Flase
            correct = False
            # tant que non correct c-à-d correct = true
            while not correct:
                # affiche le mot "Sticks?" avec la possibilite de saisir une valeur d'entrée
                # la variable nb qui recoit la valeur saisie qui est de type String
                nb = input('Sticks?\n')
                # condition try
                try:
                    # parser le type nb to Int
                    nb=int(nb)
                    # si nb inferieur strictement à 1 et superieur strictement à 3 et sticks-nb superieur ou égale à 0
                    if nb>=1 and nb<=3 and sticks-nb>=0:
                        # la variable correct recoit True
                        correct=True
                except: pass
            # retourner la valeur de nb
            return nb

# la Classe CPUPlayer permet de créer un objet qui contient
# les informations d'un joueur machine.
class CPUPlayer(Player):
    # le constructeur de la classe CPUPlayer permet d'initialiser l'objet de type CPUPlayer 
    # en affectant les valeurs des paramètres (name,mode,nbSticks) aux variables locales
    def __init__(self,name,mode,nbSticks):
        # affectation de la valeur du paramètre name à la variable name de la super-classe Player
        super().__init__(name)
        # affectation de la valeur du paramètre mode a la variable locale self.mode 
        self.mode = mode
        # la variable locale self.netw recoit la vlaeur retouner par la fonction locale NeuronNetwork(3,nbSticks) qui prend comme paramètre 3 et nbSticks
        self.netw = NeuronNetwork(3,nbSticks)
        # affecter NULL à la variable locale self.previousNeuron 
        self.previousNeuron = None

    # la fonction play permet de determine de quel mode la partie sera jouée
    # le resultat retouner par cette fonction est un entier compris entre >=1 et <=3.
    def play(self,sticks):
        # si la valeur de la variable locale self.mode = "easy" appelle à la fonction locale self.playEasy(sticks) qui prend le nombre de sticks comme paramètre
        if self.mode=='easy': return self.playEasy(sticks)
        # sinon si la valeur de la variable locale self.mode = "hard" appelle à la fonction locale self.playHard(sticks) qui prend le nombre de sticks comme paramètre
        elif self.mode=='hard': return self.playHard(sticks)
        # sinon appelle à la fonction locale self.playMedium(sticks) qui prend le nombre de sticks comme paramètre
        else: return self.playMedium(sticks)

    # la fonction playMedium permet de jouer une partie en mode medium
    def playMedium(self,sticks):
        # TODO modifier ici avec les quelques conditions pour éviter de faire une grosse erreur aux derniers tours
        # si le nombre de sticks = 1 alors il le prend qui implique qu'il a perdu la partie
        if   sticks==1: return 1
        #sinon si le nombre de sticks inferieur ou egale à 4 il prend nombre de sticks-1 pour laiser le dernier sticks alors gagnant
        elif sticks<=4: return sticks-1
        # sinon prend un nombre aleatoire entre >=1 et <=3 
        else: return self.playRandom(sticks)

    # la fonction playEasy permet de jouer une partie en mode easy
    # elle retourne un nombre entier entre >=1 et <=3
    # totalement aleatoire grace a la fonction playRandom 
    def playEasy(self,sticks):
        # retouner le resultat de la fonction playRandom(sticks) qui prend le nombre de sticks comme paramètre
        return self.playRandom(sticks)

    # la fonction playRandom permet de retouner aleatoirement un entier >=1 et <=1
    def playRandom(self,sticks):
        # retoune un entier aléatoire dans l'interval qui est entre 1 et sticks MOD 3 + 1 
        return random.randint(1, (sticks%3)+1)

    # la fonction playHard permet de jouer une partie en mode hard
    # elle retourne un nombre entier entre >=1 et <=3 comme les autre mode 
    # cette fois le choixs  n'est pas aleatoire mais elle utilise le
    # réseau neuronal pour choisir le nombre de bâtons à jouer
    def playHard(self,sticks):
        # TODO utiliser le réseau neuronal pour choisir le nombre de bâtons à jouer
        # utiliser l'attribut self.previousNeuron pour avoir le neuron précédemment sollicité dans la partie
        # calculer un 'shift' qui correspond à la différence entre la valeur du précédent neurone et le nombre de bâtons encore en jeu
        # utiliser la méthode 'chooseConnectedNeuron' du self.previousNeuron puis retourner le nombre de bâtons à jouer
        # bien activer le réseau de neurones avec la méthode 'activateNeuronPath' après avoir choisi un neurone cible
        # attention à gérer les cas particuliers (premier tour ou sticks==1)

        # si le nombre de sticks = 1 alors il le prend qui implique qu'il a perdu la partie
        if   sticks==1: return 1
        if self.previousNeuron == None:
            self.previousNeuron = self.netw.getNeuron(sticks)
            shift = 0
        else:
            shift = self.previousNeuron.index - sticks

        neuronActuel = self.previousNeuron.chooseConnectedNeuron(shift)

        # si le nombre de sticks = 2 alors il prend 1 se qui implique gagnant.
        if neuronActuel == None: number = 1
        # sinon si le nombre de sticks > 2
        else:
            #sinon le nombre de sricks à jouer égale sticks - neuronActuel.index.  
            number = sticks - neuronActuel.index
            # activation du reseau de neurons avec la methode activateNeuronPath.
            self.netw.activateNeuronPath(self.previousNeuron, neuronActuel)
            self.previousNeuron = neuronActuel
        # retourner le nombre de batons à jouer.
        return number

    # la fonction getNeuronNetwork permet de retourner le réseau de neurons.
    def getNeuronNetwork(self): return self.netw

    # la fonction setNeuronNetwork permet de creer une instance du réseau de neurons.
    def setNeuronNetwork(self,network): self.netw = network

    # La méthode addWin permet d'incrémenter le nombre de victoire du joueur
    # et de réinitialiser l'etat du reseau de neurons.
    def addWin(self):
        super().addWin()
        self.netw.recompenseConnections()
        self.previousNeuron=None

    # La méthode addLoss permet réinitialiser l'etat du reseau de neurons.
    def addLoss(self):
        super().addLoss()
        self.previousNeuron=None
