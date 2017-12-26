# -*-coding:UTF-8 -*
from Game   import *
from Neuron import *
from Player import *

jouer = Game(15)

humanPlayer = HumanPlayer('MoiEasy')
cpuPlayer   = CPUPlayer('MachineEasy', 'easy', 15)

jouer.start(humanPlayer, cpuPlayer, True)
