# -*-coding:UTF-8 -*
from Game   import *
from Neuron import *
from Player import *

game = Game(15)

Player1   = HumanPlayer('MoiHard')
Player2   = CPUPlayer('MachineHard', 'hard', 15)

game.start(Player1, Player2, True)
