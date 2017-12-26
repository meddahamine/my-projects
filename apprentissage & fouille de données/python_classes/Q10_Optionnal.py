# -*-coding:UTF-8 -*
import pickle
from Game   import *
from Neuron import *
from Player import *

sticks = 15
fois  = 1000000

with open('fichier_serialiser', 'rb') as inp: ns = pickle.load(inp)

game = Game(sticks)

player1 = CPUPlayer('MachineHard1', 'hard', sticks)
player2 = CPUPlayer('MachineHard2', 'hard', sticks)

# Ajout du réseau de neurones
player1.setNeuronNetwork(ns)
player2.setNeuronNetwork(ns)

for i in range(1, fois+1):
    game.start(player1, player2, False)
    if i%1000 == 0: print(i)

# Calcul du taux de victoire du joueur qui commence
wins1 = player1.getNbWin()
taux1 = (wins1 / fois) * 100
wins2 = player2.getNbWin()
taux2 = (wins2 / fois) * 100
# Affichage du résultat
print('Sur ', fois, ' parties jouées :')
print(player1.getName(),'qui a commencé à gagner :', wins1, 'fois, Taux :', taux1, '% de victoires.')
print(player2.getName(),'a gagné', wins2, 'fois, Taux :', taux2, '% de victoires.')
