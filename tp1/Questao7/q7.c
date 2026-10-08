#include <stdio.h>

void funcao(char *string, int tam){
	int cont=1, maior = 0,j,k,encontrou=0;
	for(int i = 0; i < tam; i++){// é o limite inferior. Controla a parte da esquerda da string.
		j=i+1;
		cont=1;
		encontrou=0;
		while(j<tam && encontrou==0){//o j é o limite superior, a direita da string.
			k=i;
			while(k<j){// o k vai andando até o j, e é comparado com ele, ao passo que o contador é incrementado toda vez que k é diferente de j. Caso seja igual,a substring acabou e uma nova substring é procurada.
				if(string[j]==string[k]) encontrou=1;
				k++;
			}
			if(encontrou==0){
			       	j++;
				cont++;
			}
		}
		if(cont > maior){//atualiza qual é a maior substring
			maior = cont;
		}
	}
	printf("%d\n",maior);
}

int tamanho(char*string){//calcula tamanho da string
	int tam=0;
	while(string[tam]!='\0') tam++;
	return tam;
}
int main(){
	char string[100];int tam=0;
	scanf("%s",string);
	tam=tamanho(string);
	while(string[0]!='F' && string[1]!='I' && string[2]!='M' && string[3]!='\0'){// mesma coisa dos exercicios anteriores
	funcao(string, tam);
	scanf("%s",string);
	tam=tamanho(string);
	}
}
