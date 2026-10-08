#include <stdio.h>
char * ciframento(char * palavra, int i){
    if(palavra[i]=='\0') return palavra;//recursao acaba quando a string acaba
    palavra[i]+=3;
    return(ciframento(palavra,i+1));// passa a mesma string, mas um endereço a frente. Assim, a string vai sendo percorrida
}

void ciframentoInicial(char *palavra){
	ciframento(palavra,0);
}


int main(){
    char palavra[50];
    fgets(palavra,50,stdin);
    while(!(palavra[0]=='F' && palavra[1]=='I' && palavra[2]=='M' && (palavra[3]=='\n' || palavra[3]=='\0'))){//leituras
    	ciframentoInicial(palavra);
	printf("%s\n",palavra);
        fgets(palavra,50,stdin);
    }
}
