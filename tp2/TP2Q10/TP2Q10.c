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


void retirarChar(char string[]){
	int tam=0;
	while(string[tam]!='\0'){
		tam++;
	}
	string[tam-1]='\0';
}

typedef struct{
	int primeiro;
	int ultimo;
	Veiculo veiculos[6];
}Fila;

Fila construtorFila(){
	Fila f;
	f.primeiro=0;
	f.ultimo=f.primeiro;
	return f;
}

Veiculo remover(Fila *f){
	if(f->ultimo==f->primeiro){
		printf("erro: fila vazia");
		exit(1);
	}
	Veiculo resp=f->veiculos[f->primeiro];
	f->primeiro=(f->primeiro+1)%6;
	return resp;
}

void inserir(Veiculo x,Fila *f){
	if((f->ultimo+1)%6==f->primeiro){
		Veiculo a=remover(f);
		printf("(R)%s %s\n",a.marca,a.modelo);
	}
	f->veiculos[f->ultimo]=x;
	f->ultimo=(f->ultimo+1)%6;
}


void mostrar(Fila *f){
	int i=f->primeiro;
	while(i!=f->ultimo){
		formatVeiculo(f->veiculos[i]);
		i=(i+1)%6;
	}
}

Veiculo buscarVeiculo(Veiculo array[],int id){
	for(int i=0;i<500;i++){
		if(array[i].id==id) return array[i];
	}
}

int main() {
    Veiculo array[500];
    Fila fila=construtorFila();
    leitor("/tmp/veiculos.csv", array);

    int numeroId;
    while (scanf("%d", &numeroId) == 1 && numeroId != -1) {
        for (int i = 0; i < 500; i++) {
            if (array[i].id == numeroId) {
               inserir(array[i],&fila);
            }
        }
    }
	int operacoes;
	char string[100];
	scanf("%d",&operacoes);
	for(int i=0;i<operacoes;i++){
		scanf("%s",string);
		if(strcmp(string,"I")==0){
			int id;
			scanf("%d",&id);
			Veiculo v=buscarVeiculo(array,id);
			inserir(v,&fila);
		}
		if(strcmp(string,"R")==0){
			Veiculo v=remover(&fila);
			printf("(R)%s %s\n",v.marca,v.modelo);
		}
	}
	mostrar(&fila);
}
