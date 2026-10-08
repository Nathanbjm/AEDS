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

typedef struct Celula{
	Veiculo elemento;
	struct Celula* prox;
}Celula;

typedef struct{
	struct Celula *primeiro;
	struct Celula *ultimo;
}Lista;

Celula *novaCelula(Veiculo x){
	Celula *nova=(Celula*)malloc(sizeof(Celula));
	nova->elemento=x;
	nova->prox=NULL;
	return nova;	
}
Celula *newCelula(){
	Celula *nova=(Celula*)malloc(sizeof(Celula));
	nova->prox=NULL;
	return nova;
}
Lista novaLista(){
	Lista nova;
	nova.primeiro=newCelula();
	nova.ultimo=nova.primeiro;
	return nova;
}

void inserirInicio(Lista *l,Veiculo x){
	Celula *tmp=novaCelula(x);
	tmp->prox=l->primeiro->prox;
	l->primeiro->prox=tmp;
	if(l->primeiro==l->ultimo)l->ultimo=tmp;
	tmp=NULL;
}
void inserirFim(Lista *l,Veiculo x){
	l->ultimo->prox=novaCelula(x);
	l->ultimo=l->ultimo->prox;
}
void inserir(Lista *l,Veiculo x,int pos){
	Celula *i=l->primeiro;
	for(int j=0;j<pos;j++){
		i=i->prox;
	}
	Celula* nova=novaCelula(x);
	nova->prox=i->prox;
	i->prox=nova;
	nova=NULL;
}
Veiculo removerInicio(Lista *l){
	Celula *tmp=l->primeiro->prox;
	Veiculo resp=tmp->elemento;
	l->primeiro->prox=tmp->prox;
	tmp->prox=NULL;
	free(tmp);
	tmp=NULL;
	return resp;
}
Veiculo removerFim(Lista *l){
	Celula *i;
	for(i=l->primeiro;i->prox!=l->ultimo;i=i->prox);
	Veiculo resp=i->prox->elemento;
	i->prox=NULL;
	l->ultimo=i;
	return resp;
}
Veiculo remover(Lista *l,int pos){
	Celula* i=l->primeiro;
	for(int j=0;j<pos;j++){
		i=i->prox;
	}
	Celula *tmp=i->prox;
	Veiculo resp=tmp->elemento;
	i->prox=tmp->prox;
	tmp->prox=NULL;
	free(tmp);
	tmp=NULL;
	return resp;
}
void mostrar(Lista *l){
	for(Celula *i=l->primeiro->prox;i!=NULL;i=i->prox){
		formatVeiculo(i->elemento);
	}
}

Veiculo buscarVeiculo(Veiculo array[],int id){
	for(int i=0;i<500;i++){
		if(array[i].id==id)return array[i];
	}
}



int main() {
    Veiculo array[500];
    Lista lista=novaLista();
    leitor("/tmp/veiculos.csv", array);

    int numeroId;
    while (scanf("%d", &numeroId) == 1 && numeroId != -1) {
        for (int i = 0; i < 500; i++) {
            if (array[i].id == numeroId) {
               inserirFim(&lista,array[i]);
            }
        }
    }
	int operacoes;
	char string[100];
	scanf("%d",&operacoes);
	for(int i=0;i<operacoes;i++){
		scanf("%s",string);
		if(strcmp(string,"II")==0){
			int id;
			scanf("%d",&id);
			Veiculo v=buscarVeiculo(array,id);
			inserirInicio(&lista,v);
		}
		if(strcmp(string,"IF")==0){
			int id;
			scanf("%d",&id);
			Veiculo v=buscarVeiculo(array,id);
			inserirFim(&lista,v);
		}
		if(strcmp(string,"I*")==0){
			int pos,id;
			scanf("%d",&pos);
			scanf("%d",&id);
			Veiculo v=buscarVeiculo(array,id);
			inserir(&lista,v,pos);
		}
		if(strcmp(string,"RI")==0){
			Veiculo v=removerInicio(&lista);
			printf("(R)%s %s\n",v.marca,v.modelo);
		}
		if(strcmp(string,"RF")==0){
			Veiculo v=removerFim(&lista);
			printf("(R)%s %s\n",v.marca,v.modelo);
		}
		if(strcmp(string,"R*")==0){
			int pos;
			scanf("%d",&pos);
			Veiculo v=remover(&lista,pos);
			printf("(R)%s %s\n",v.marca,v.modelo);
		}
	}
	mostrar(&lista);
}
