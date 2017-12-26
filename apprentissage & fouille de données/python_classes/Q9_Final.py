# -*-coding:UTF-8 -*
import pickle
from Game   import *
from Neuron import *
from Player import *

with open('fichier_serialiser', 'rb') as inp: ns = pickle.load(inp)

game = Game(15)

string = str()
while string.lower() != "exit":
    print("Pour quitter Tapez : 'exit'")
    print("Tapez votre nom : ")
    string = input()
    if string.lower() != "exit":
        player1 = HumanPlayer(string)
        print("Bienvenue ", string,".")
        print("Choisissez un mode de jeu {easy, medium, hard} : ")
        string = input()
        while (string!="easy") and (string!="medium") and (string!="hard"):
            print("le mode de jeu saisi est incorrect !!!")
            print("veuillez saisir un parmi ces trois {easy, medium, hard} : ")
            string = input()
        if string.lower() in ("easy","medium","hard"):
            start = input("Voulez vous commencer le jeu en premier : (oui/non)? ")
            if start == 'oui' :
                player2   = CPUPlayer('Machine', string.lower(), 15)
                player2.setNeuronNetwork(ns)
                game.start(player1, player2, True)
            else:
                player2   = CPUPlayer('Machine', string.lower(), 15)
                player2.setNeuronNetwork(ns)
                game.start(player2, player1, True)
                
