#include <stdio.h>
void inversao(char * string,int tam){//vai trocando a posicao as posicoes com swap, para que a string seja invertida. Só precisa ir até o meio.
	char tmp;int meio=tam/2;
	for(int i=0;i<meio;i++){
		tmp=string[i];
		string[i]=string[tam-i-1];
		string[tam-i-1]=tmp;
	}
}

int main(){
	char vetor[100];int tam=0;
	fgets(vetor,100,stdin);
	while(!(vetor[0]=='F' && vetor[1]=='I' && vetor[2]=='M' && (vetor[3]=='\n' || vetor[3]=='\0'))){//leituras de entrada normais.
		while(vetor[tam]!='\n'){
			tam++;
		}
			vetor[tam]='\0';
			inversao(vetor,tam);
			printf("%s\n",vetor);
			tam=0;
			fgets(vetor,100,stdin);

	}
}
