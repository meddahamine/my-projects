# -*-coding:UTF-8 -*
from Game   import *
from Neuron import *
from Player import *

sticks = 15

game = Game(sticks)

player1 = CPUPlayer('MachineHard1', 'hard', sticks)
player2 = CPUPlayer('MachineHard2', 'hard', sticks)

nb_fois=10000
for i in range(1,nb_fois):
    game.start(player1, player2, False)

print("Reseau neuronal de ",player1.getName(),":")
player1.getNeuronNetwork().printAllConnections()

print("Reseau neuronal de",player2.getName(),":")
player2.getNeuronNetwork().printAllConnections()

print("nombre de partie gagnée par ",player1.getName(),":",player1.getNbWin())
print("nombre de partie gagnée par ",player2.getName(),":",player2.getNbWin())
