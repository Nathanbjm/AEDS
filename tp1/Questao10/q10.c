#include <stdio.h>

int ehVogal(char c) {//retorna true se for vogal
    if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
        
        return 1;
    }
    return 0;
}
    int vogal(char *str, int i) {
        if (str[i] == '\n' || str[i] == '\0') {//recursao acaba quando a string acaba
            return 1;
        }
        if (ehVogal(str[i])) {//verifica se a posicao é uma vogal, e logo depois manda como parametro a mesma string, uma posicao a frente.
            return vogal(str, i + 1);
        }

        return 0;
    }

    int vogalIniciar(char *str) {
        return vogal(str, 0);
    }

    int consoante(char *str, int i) {//mesma coisa, pra consoante. Se nao é vogal e é uma letra( entre 'a' e 'z' ou 'A' e 'Z' é uma consoante.
        if (str[i] == '\n' || str[i] == '\0') {
            return 1;
        }

        if (!ehVogal(str[i]) &&((str[i] >= 'a' && str[i] <= 'z') || (str[i] >= 'A' && str[i] <= 'Z'))) {
             
            
            return consoante(str, i + 1);
        }

        return 0;
    }

    int consoanteIniciar(char *str) {
        return consoante(str, 0);
    }

    int inteiro(char *str, int i) {//mesma coisa, se todas as posicoes  estiverem entre 0 e 9 é um inteiro.
        if (str[i] == '\n' || str[i] == '\0') {
            return 1;
        }

        if (str[i] >= '0' && str[i] <= '9') {
            return inteiro(str, i + 1);
        }

        return 0;
    }

    int inteiroIniciar(char *str) {
        return inteiro(str, 0);
    }

    int real(char *str, int i, int separador) {
        if (str[i] == '\n' || str[i] == '\0') {
            return 1;
        }

        if (str[i] >= '0' && str[i] <= '9') {//para ser real, tem que ter 0 ou 1 ponto/virgula
            return real(str, i + 1, separador);
        }

        if ((str[i] == ',' || str[i] == '.') && separador == 0) {
            
            return real(str, i + 1, 1);
        }

        return 0;
    }

    int realIniciar(char str[]) {
        return real(str, 0, 0);
    }

    int main() {
        char str[1000];

        fgets(str,1000, stdin);

        while (!(str[0] == 'F' && str[1] == 'I' && str[2] == 'M' && (str[3] == '\n' || str[3] == '\0'))) {//leitura da entrada
             
            if (vogalIniciar(str)) {
                printf("SIM ");
            } else {
                printf("NAO ");
            }

            if (consoanteIniciar(str)) {
                printf("SIM ");
            } else {
                printf("NAO ");
            }

            if (inteiroIniciar(str)) {
                printf("SIM ");
            } else {
                printf("NAO ");
            }

            if (realIniciar(str)) {
                printf("SIM\n");
            } else {
                printf("NAO\n");
            }

            fgets(str,1000,stdin);
        }
    }
