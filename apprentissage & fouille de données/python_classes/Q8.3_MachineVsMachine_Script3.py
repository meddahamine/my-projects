# -*-coding:UTF-8 -*
from Game   import *
from Neuron import *
from Player import *

nb_fois=800

game = Game(15)

player1 = CPUPlayer('Machine1', 'easy', 15)
player2 = CPUPlayer('Machine2', 'easy', 15)

for i in range(1,nb_fois+1):
    game.start(player1, player2, False)

print("\n- easy contre easy")
print("Nombre de parties gagnees par la Machine1 : ", player1.getNbWin())
print("Nombre de parties gagnees par la Machine2 : ", player2.getNbWin(),"\n")

player1 = CPUPlayer('Machine1', 'easy', 15)
player2 = CPUPlayer('Machine2', 'medium', 15)

for i in range(1,nb_fois+1):
    game.start(player1, player2, False)

print("- easy contre medium")
print("Nombre de parties gagnees par ",player1.getName(),":", player1.getNbWin())
print("Nombre de parties gagnees par ",player2.getName(),":", player2.getNbWin(),"\n")

player1 = CPUPlayer('Machine1', 'medium', 15)
player2 = CPUPlayer('Machine2', 'easy', 15)

for i in range(1,nb_fois+1):
    game.start(player1, player2, False)

print("- medium contre easy")
print("Nombre de parties gagnees par ",player1.getName(),":", player1.getNbWin())
print("Nombre de parties gagnees par ",player2.getName(),":", player2.getNbWin(),"\n")

player1 = CPUPlayer('Machine1', 'medium', 15)
player2 = CPUPlayer('Machine2', 'hard', 15)

for i in range(1,nb_fois+1):
    game.start(player1, player2, False)

print("- medium contre hard")
print("Nombre de parties gagnees par ",player1.getName(),":", player1.getNbWin())
print("Nombre de parties gagnees par ",player2.getName(),":", player2.getNbWin(),"\n")

player1 = CPUPlayer('Machine1', 'hard', 15)
player2 = CPUPlayer('Machine2', 'medium', 15)

for i in range(1,nb_fois+1):
    game.start(player1, player2, False)

print("- hard contre medium")
print("Nombre de parties gagnees par ",player1.getName(),":", player1.getNbWin())
print("Nombre de parties gagnees par ",player2.getName(),":", player2.getNbWin(),"\n")

player1 = CPUPlayer('Machine1', 'easy', 15)
player2 = CPUPlayer('Machine2', 'hard', 15)

for i in range(1,nb_fois+1):
    game.start(player1, player2, False)

print("- easy contre hard")
print("Nombre de parties gagnees par ",player1.getName(),":", player1.getNbWin())
print("Nombre de parties gagnees par ",player2.getName(),":", player2.getNbWin(),"\n")

player1 = CPUPlayer('Machine1', 'hard', 15)
player2 = CPUPlayer('Machine2', 'easy', 15)

for i in range(1,nb_fois+1):
    game.start(player1, player2, False)

print("- hard contre easy")
print("Nombre de parties gagnees par ",player1.getName(),":", player1.getNbWin())
print("Nombre de parties gagnees par ",player2.getName(),":", player2.getNbWin(),"\n")

player1 = CPUPlayer('Machine1', 'medium', 15)
player2 = CPUPlayer('Machine2', 'medium', 15)

for i in range(1,nb_fois+1):
    game.start(player1, player2, False)

print("- medium contre medium")
print("Nombre de parties gagnees par ",player1.getName(),":", player1.getNbWin())
print("Nombre de parties gagnees par ",player2.getName(),":", player2.getNbWin(),"\n")

#player1 = CPUPlayer('Machine1', 'hard', 15)
#player2 = CPUPlayer('Machine2', 'hard', 15)

#for i in range(1,nb_fois+1):
 #   game.start(player1, player2, False)

#print("- hard contre hard")
#print("Nombre de parties gagnees par ",player1.getName(),":", player1.getNbWin())
#print("Nombre de parties gagnees par ",player2.getName(),":", player2.getNbWin(),"\n")
