#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>

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


void retirarChar(char string[]){
	int tam=0;
	while(string[tam]!='\0'){
		tam++;
	}
	string[tam-1]='\0';
}

int somar(char * modelo){
	int soma=0;
	for(int i=0;i<strlen(modelo);i++){
		soma+=modelo[i];
	}
	return soma;
}


int rehash(int pos2,Veiculo tabela,int tmp){
	int aux;
	if(tabela[pos2]!=NULL && pos2!=tmp) aux=rehash(pos2+1,tabela);
	else if(tabela[pos2]==NULL) aux=pos2;
	else aux=-1;
	return aux;
}

void inserir(Veiculo x,Veiculo tabela[]){
	int pos=hash(somar(x.modelo));
	if(tabela[pos]==NULL) tabela[pos]=x;
	else{
		int pos2=rehash(somar(x.modelo),tabela,pos);
		if(pos2==-1) formatVeiculo(x);
		else tabela[pos2]=x;
	}
}

void pesquisar(char * modelo,Veiculo tabela[]){
	int pos1=hash(somar(x.modelo));
	if(
}
int main() {
	Veiculo array[500];
	Veiculo tabela[50];
	for(int i=0;i<50;i++){
		tabela[i]=NULL;
	}
	leitor("/tmp/veiculos.csv", array);

	int numeroId;
	while (scanf("%d", &numeroId) == 1 && numeroId != -1) {
		for (int i = 0; i < 500; i++) {
			if (array[i].id == numeroId) {
				inserir(array[i],tabela);
			}
		}
	}
	
	char modelo[1000];
	getchar();
	fgets(modelo,1000,stdin);
	retirarChar(modelo);
	while(strcmp(modelo,"FIM")!=0){
		pesquisar(modelo,tabela);
		fgets(modelo,1000,stdin);
		retirarChar(modelo);
	}
	
}
