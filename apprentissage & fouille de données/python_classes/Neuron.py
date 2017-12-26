# les importation
#importation de la biblothèque random
import random

BASE_WEIGHT = 10
RECOMPENSE = 8

class NeuronNetwork:
    # constructeur de la classe NeuronNetwork 
    # maxDist correspond au nombre maximum de batons que l'on peut retirer
    # nbSticks correspond au nombre de batons en jeu au début de la partie
    def __init__(self,maxDist,nbSticks):
        # initialisation d'une liste vide qui contiendra les Neurons
        self.neurons = []
        # creation d'autant de neuron qu'il y a de sticks
        for i in range(1,nbSticks+1):
            # initialiser le neuron et l'inserer dans la liste self.neurons crée précédement 
            self.neurons.append(Neuron(self,i))
        # creation des connexions pour chaque neuron toute en parcourant la liste des neurons self.neurons
        for neuron in self.neurons:
            neuron.makeConnections(maxDist,nbSticks,BASE_WEIGHT)
        self.initPath()

    # la fonction initPath de la classe NeuronNetwork
    def initPath(self):
        # Path correspond au 'chemin que le réseau de neurone va emprunter'
        self.path = {}

    # la fonction getNeuron permet de récupérer un neuron qui se trouve dans la liste 'neurons' à l'index passé en paramètre.
    def getNeuron(self,index):
        if index-1>=0 and index<=len(self.neurons): return self.neurons[index-1]
        else: return None

    # la fonction locale activateNeuronPath permet de lier un neuronne avec un autre grâce à la fonction 'initPath'
    def activateNeuronPath(self,neuron1,neuron2):
        self.path[neuron1]=neuron2

    # la fonction recompenseConnections permet de récompenser les connections de neuronnes qui permettent la victoire.
    def recompenseConnections(self):
        for neuron1,neuron2 in self.path.items():
            neuron1.recompenseConnection(neuron2)
        # réinitialiser les chemins.
        self.initPath()

    # La fonction printAllConnections permet d'afficher toutes les connections.
    def printAllConnections(self):
        for neuron in self.neurons: neuron.printConnections()

    # La fonction printScores permet d'afficher le score des neuronnes.
    def printScores(self):
        scores = {}
        for neuron in self.neurons:
            for n,s in neuron.connections.items():
                if n not in scores: scores[n]=s
                else: scores[n] = scores[n] + s
        for neuron,score in scores.items():
            print(neuron.asString(),score)

class Neuron:
    # Constructeur de la classe Neuron qui permet d'initialiser un neuron
    def __init__(self,network,index):
        # Référence vers le réseau de neurones
        self.network = network
        # Valeur du neurone actuel (correspond aux nombre de batons à jouer)
        self.index = index
        # Dictionnaire des connexions
        self.connections = {}

    # la fonction makeConnections permet de définir les connexions entre les Neurons
    # Chaque Neuron aura maxDist*2+1 connexions (ici, 7-1=6 :: cf.range); sauf le dernier
    # qui en aura maxDist+1 (4-1=3 :: cf.range).
    # Cette valeur est utilisée afin de construire les connexions entre les neurons
    # et leur associer
    def makeConnections(self,maxDist,nbSticks,baseWeight):
        # Si l'identifiant du neurone actuel est différent du nombre de sticks
        # nb reçoit maxDist*2 + 1.
        if self.index!=nbSticks: nb=maxDist*2 +1
        # Sinon, si l'identifiant du neurone actuel est égal au nombre de sticks
        # nb reçoit maxDist + 1.
        else: nb=maxDist +1
        # boucle de 1 a nb tel que nb non inclu c-à-d range(1, nb) =>  [1, 2, 3, 4, 5, ..., nb-1]
        for i in range(1,nb):
            # récupèrer le neurone du réseau qui se trouve à l'index i-1
            neuron = self.network.getNeuron(self.index-i)
            # Si neuron n'est pas null, alors initialiser le poids du neurone à baseWeight
            if neuron!=None: self.connections[neuron]=baseWeight

    # la fonction chooseConnectedNeuron permet de retourner le neurone connecté au neurone actuel
    # en fonction du 'shift'
    def chooseConnectedNeuron(self,shift):
        # TODO méthode qui retourne un neurone connecté au neurone actuel en fonction du 'shift' (cf. CPUPlayer).
        # On devra utiliser la méthode self.weighted_choice pour choisir au hasard dans une liste de connexions disponibles en fonction de leurs poids
        neuron = None
        boolean = False
        connex = dict(self.connections)
        while not boolean:
            if len(connex) == 0: boolean = True
            else:
               neuron = self.weighted_choice(connex)
               if neuron.testNeuron(self.index - shift): boolean = True
               else:connex.pop(neuron)
        return neuron

    # la fonction testNeuron qui permet de retourner True si la différence entre
    # la 'inValue' et la valeur du neurone actuel est comprise entre 1 et 3 inclus
    def testNeuron(self,inValue): 
        diff =  inValue - self.index
        return (diff >= 1 and diff <= 3)

    # la fonction recompenseConnection permet de récompenser la connexion
    # entre le neuron actuel et 'neuron'
    def recompenseConnection(self,neuron):
        self.connections[neuron] = self.connections[neuron] + RECOMPENSE

    # la fonction printConnections permet d'afficher les connexions
    # entre les neurons (du noeud courant).
    def printConnections(self):
        print("Connections of",self.asString()+":")
        for neuron in self.connections:
            print(neuron.asString(),self.connections[neuron])

    # la fonction asString permet de retourne un objet de type chaine de caractères
    # en concatenant la lettre N et le par to Strin de l"index du neuron
    def asString(self):
        return "N"+str(self.index)

    # la fonction weighted_choice permet de renvoyer un Neuron parmi les Neurons
    # connectés au neuron actuel en fonction des connections de poids fort.
    def weighted_choice(self,connections):
       # La ligne ci-dessous est constituée d'un générateur de la somme "sum"; 
       # la variable locale total recoit la valeur retourne par la fonction la somme des w (pour weight) de chaque items de la collection connections
       total = sum(w for c, w in connections.items())
       # r recoit une valeur aléatoire dans l'interval qui est entre 0 et la valeur de la variable total
       r = random.uniform(0, total)
       # initialiser la variable locale upto à 0
       upto = 0
       # On parcours la collection connections et on retourne le neurone si il répond à la condition
       for c, w in connections.items():
          # si upto + poids du neurone >= r => on retourne ce neurone
          if upto + w >= r: return c
          #sinon incrémenter la valeur de la variable locale upto en lui ajoutant la valeur de w
          upto += w
