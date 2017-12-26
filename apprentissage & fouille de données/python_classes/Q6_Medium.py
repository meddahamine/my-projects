# -*-coding:UTF-8 -*
from Game   import *
from Neuron import *
from Player import *

jouer = Game(15)

humanPlayer = HumanPlayer('MoiMedium')
cpuPlayer   = CPUPlayer('MachineMedium', 'medium', 15)

jouer.start(humanPlayer, cpuPlayer, True)
