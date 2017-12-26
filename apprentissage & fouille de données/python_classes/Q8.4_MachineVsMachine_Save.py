# -*-coding:UTF-8 -*
import pickle
from Game   import *
from Neuron import *
from Player import *

sticks = 15

game = Game(sticks)

player1 = CPUPlayer('MachineHard1', 'hard', sticks)
player2 = CPUPlayer('MachineHard2', 'hard', sticks)

i = 0
while i < 10000:
    game.start(player1, player2, False)
    i += 1

print("\nReseau neuronal ",player1.getName(),":")
player1.getNeuronNetwork().printAllConnections()

print("\nReseau neuronal ",player2.getName(),":")
player2.getNeuronNetwork().printAllConnections()

with open('fichier_serialiser','wb') as output: pickle.dump(player1.getNeuronNetwork(),output,pickle.HIGHEST_PROTOCOL)


