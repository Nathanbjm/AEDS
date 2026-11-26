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

typedef struct No{
	Veiculo elemento;
	No * esq, * dir;
	bool cor;
}No;

No * novoNo(Veiculo x){
	No * novo=malloc(sizeof(No));
	novo->elemento=x;
	novo->esq=novo->dir=NULL;
	novo->cor=true;
	return novo;
}

typedef struct{
	No * raiz;
}Arvore;

Arvore novaArvore(){
	Arvore arvore;
	arvore.raiz=NULL;
	return arvore;
}

	void inserir(Veiculo x,Arvore * a){
		if(a->raiz==NULL) a->raiz=novoNo(x);
		else inserirRec(x,NULL,NULL,NULL,a->raiz);
		a->raiz->cor=false;
	}
	void inserirRec(Veiculo x,No * bisavo, No * avo, No * pai, No * i,Arvore * a){
		if(i==NULL){
			if(strcmp(pai->elemento.modelo,x.modelo)>0){
				i=pai->esq=novoNo(x);
			}
			else i=pai->dir=novoNo(x);
			if(pai->cor) balancear(bisavo,avo,pai,i,a);
		}
		else{
			if(noEhDoTipoQuatro(i)){
			fragmentar(i);
			if(i==a->raiz) i->cor=false;
			else if(pai->cor) balancear(bisavo,avo,pai,i,a);
			}
			if(strcmp(i->elemento.modelo,x.modelo)>0) inserirRec(x,avo,pai,i,i->esq,a);
			else inserirRec(x,avo,pai,i,i->dir,a);

		}
	}

bool noEhDoTipoQuatro(No * i){
	return (i->esq!=NULL && i->dir!=NULL && i->esq->cor==true && i->dir->cor==true);
}

void fragmentar(No * i){
	i->cor=true;
	i->esq->cor=i->dir->cor=false;
}

void balancear(No * bisavo, No * avo, No * pai, No * i,Arvore * a){
	if(strcmp(avo->elemento.modelo,pai->elemento.modelo)<0){
		if(strcmp(pai->elemento.modelo,i->elemento.modelo)>0) pai=rotacaoDir(pai);
		avo=rotacaoEsq(avo);
	}
	else{
		if(strcmp(pai->elemento.modelo,i->elemento.modelo)<0) pai=rotacaoEsq(pai);
		avo=rotacaoDir(avo);
	}
	if(bisavo==NULL) a->raiz=avo;
	else if(strcmp(avo->elemento.modelo,bisavo->elemento.modelo)<0) bisavo->esq=avo;
	else bisavo->dir=avo;
	avo->cor=false;
	avo->esq->cor=avo->dir->cor=true;

}

No * rotacionarEsq(No * no){
	No * noDir=no->dir;
	No * noDirEsq=noDir->esq;
	noDir->esq=no;
	no->dir=noDirEsq;
	return noDir;
}

No * rotacionarDir(No * no){
	No * noEsq=no->esq;
	No *noEsqDir=noEsq->dir;
	noEsq->dir=no;
	no->esq=noEsqDir;
	return noEsq;
}

void pesquisar(char * modelo,Arvore * a){
	if(a->raiz!=NULL) printf("raiz ");
	bool c=pesquisarRec(modelo,a,a->raiz);
	if(c) printf("SIM\n"); else printf("NAO\n");
       }

bool pesquisarRec(char * modelo,Arvore * a,No * i){
	bool aux;
	if(i==NULL) aux=false;
	else if(strcmp(modelo,i->elemento.modelo)==0){
	       	aux=true;
	}
	else if(strcmp(modelo,i->elemento.modelo)<0){
		printf("esq ");
	       	aux=pesquisarRec(modelo,a,i->esq);
	
	}
	else{
		printf("dir ");
		aux=pesquisarRec(modelo,a,i->dir);
	}
	return aux;
}

int main() {
	Veiculo array[500];
	Arvore a=novaArvore();
	leitor("/tmp/veiculos.csv", array);

	int numeroId;
	while (scanf("%d", &numeroId) == 1 && numeroId != -1) {
		for (int i = 0; i < 500; i++) {
			if (array[i].id == numeroId) {
				inserir(array[i],&a);
			}
		}
	}
	char tmp;
	scanf("%c",&tmp);
	char modelo[1000];
	fgets(modelo,1000,stdin);
	retirarChar(modelo);
	while(strcmp(modelo,"FIM")!=0){
		pesquisar(modelo,&a);
		fgets(modelo,1000,stdin);
		retirarChar(modelo);
	}
}
