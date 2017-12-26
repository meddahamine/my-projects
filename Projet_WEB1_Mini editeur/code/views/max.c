
#include<stdio.h>

int max(int i,int j)
{
if (i > j) return(i); else return(j);
}

int main(int argc, char *argv[]) {
int i;
int j;
scanf("%d",&i);
//scanf("%d",&j);
int maxc=max(i,5);
printf("le max entre 5 et %d est : %d",i,maxc);
return 0;
}
