#include <stdio.h>
char *ciframento(char*palavra,int i){
	if(palavra[i]=='\0'){//recursao acaba quando a string chegar ao fim.
		return palavra;
	}
	palavra[i]+=3;//soma 3 ao numero da tab ascii associado ao caractere. Ja devolve em tipo char.
	return ciframento(palavra,i+1);//volta a partir do segundo caractere. Depois o terceiro, quarto... até o ultimo.
}

void ciframentoIniciar(char *palavra){//funcao para iniciar a recursao com apenas um argumento
	ciframento(palavra,0);
}

int main(){
	char palavra[50];
	fgets(palavra,50,stdin);
	while(!(palavra[0]=='F' && palavra[1]=='I' && palavra[2]=='M' && (palavra[3]=='\n' || palavra[3]=='\0'))){//leitura padrao
		ciframentoIniciar(palavra);
		printf("%s\n",palavra);
		fgets(palavra,50,stdin);
	}
}
