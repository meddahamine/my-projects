filein = open('/home/lounnas/enigmein.txt', 'r', encoding='latin1')
fileout = open('/home/lounnas/enigmeout.txt','w',encoding='utf-8')
dico = {'©':'c','-':'e','l':'-','a':'h','y':'é','í':'o','/':'u','¥':'f','£':'r','%':'d','§':'v','â':',','+':'q',\
'«':'s','e':'l','#':'p','¬':"'",'å':'n',"'":'t',")":'a','À':'.','(':'à','"':'0'}
 
while 1:
    char = filein .read(1)         
    if not char: break
   
    if char in dico:
       fileout.write(dico[char])

    else :
       fileout.write(char)
 
# Fermer le fichier
filein.close()
fileout.close()

