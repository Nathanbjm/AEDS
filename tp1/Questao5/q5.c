#include  <stdio.h>

int soma(int n){//pega o resto da divisao do numero por 10 e guarda em uma variavel. Depois, divide o numero por 10 e repete a mesma coisa. Assim, cada digito é somado, até que n seja menor qu 10(ou seja, até a divisao de n por 10 inteira dar 0;
        int valor=n;
        int resp=0;
        while(valor!=0){
                resp+=(valor%10);
                valor=(valor/10);
        }
        return resp;
}

int main(){
        int n;
        while((scanf("%d",&n))!=EOF){// le a entrada até o fim do arquivo
                printf("%d",soma(n));
                printf("\n");
        }
}
