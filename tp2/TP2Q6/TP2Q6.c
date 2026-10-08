#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct {
    int ano;
    int mes;
    int dia;
} Data;

Data parseData(char *s) {
    Data d;
    sscanf(s, "%d-%d-%d", &d.ano, &d.mes, &d.dia);
    return d;
}

void formatData(Data d) {
    printf("%02d/%02d/%04d", d.dia, d.mes, d.ano);
}

typedef struct {
    int id;
    char marca[50];
    char modelo[50];
    int ano;
    char categoria[50];
    char combustivel[50];
    int cilindro;
    double cilindrada;
    char transmissao[50];
    char tracao[50];
    double consumoCidade;
    double consumoEstrada;
    double co2;
    int turbo;
    Data dataRegistro;
} Veiculo;

Veiculo parseVeiculo(char *linha) {
    Veiculo v;
    char combustivel_str[50], turbo_str[10], data_str[20];

    sscanf(linha, "%d,%[^,],%[^,],%d,%[^,],%[^,],%d,%lf,%[^,],%[^,],%lf,%lf,%lf,%[^,],%s",
         &v.id,
         v.marca,
         v.modelo,
         &v.ano,
         v.categoria,
         combustivel_str,
         &v.cilindro,
         &v.cilindrada,
         v.transmissao,
         v.tracao,
         &v.consumoCidade,
         &v.consumoEstrada,
         &v.co2,
         turbo_str,
         data_str);

    v.turbo = (strcmp(turbo_str, "true") == 0) ? 1 : 0;
    for (int i = 0; combustivel_str[i] != '\0'; i++) {
    	if (combustivel_str[i] == ';') {
        	combustivel_str[i] = ',';
    	}
    }
    strcpy(v.combustivel, combustivel_str);
    v.dataRegistro = parseData(data_str);
    
    return v;
}

void formatVeiculo(Veiculo v) {
    printf("[%d ## %s ## %s ## %d ## %s ## [%s] ## %d ## %.1f ## %s ## %s ## %.2f ## %.2f ## %.1f ## %s ## ",
         v.id,
         v.marca,
         v.modelo,
         v.ano,
         v.categoria,
         v.combustivel,
         v.cilindro,
         v.cilindrada,
         v.transmissao,
         v.tracao,
         v.consumoCidade,
         v.consumoEstrada,
         v.co2,
         v.turbo ? "true" : "false");
         
    formatData(v.dataRegistro);
    printf("]\n");
}

void leitor(char *caminho, Veiculo veiculos[]) {
    FILE *file = fopen(caminho, "r");
    char linha[1000];
    fgets(linha, sizeof(linha), file);

    int i = 0;
    while (fgets(linha, sizeof(linha), file) != NULL && i < 500) {
        linha[strcspn(linha, "\r\n")] = 0;
        veiculos[i] = parseVeiculo(linha);
        i++;
    }

    fclose(file);
}

void radixCounting(Veiculo array[],int tam,int exp){
	Veiculo novo[tam];
	int contador[10];
	for(int i=0;i<10;i++){
		contador[i]=0;
	}
	for(int i=0;i<tam;i++){
		contador[(array[i].ano/exp)%10]++;
	}
	for(int j=1;j<10;j++){
		contador[j]+=contador[j-1];
	}
	for(int i=tam-1;i>=0;i--){
		novo[contador[(array[i].ano/exp)%10]-1]=array[i];
		contador[(array[i].ano/exp)%10]--;	
	}
	for(int i=0;i<tam;i++){
		array[i]=novo[i];
	}
}

int getMaior(Veiculo array[],int tam){
	int maior=array[0].ano;
	for(int i=1;i<tam;i++){
		if(array[i].ano>maior)maior=array[i].ano;
	}
	return maior;
}


void radixSort(Veiculo array[], int tam){
	int maior=getMaior(array,tam);
	for(int exp=1;maior/exp>0;exp*=10){
		radixCounting(array,tam,exp);
	}
}

int main() {
    Veiculo array[500];
    Veiculo veiculos[500];
    leitor("/tmp/veiculos.csv", array);

    int numeroId,j=0;
    while (scanf("%d", &numeroId) == 1 && numeroId != -1) {
        for (int i = 0; i < 500; i++) {
            if (array[i].id == numeroId) {
                veiculos[j]=array[i];
		j++;
            }
        }
    }
    radixSort(veiculos,j);
    for(int i=0;i<j;i++){
	    formatVeiculo(veiculos[i]);
    }
}
