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

int comparar(char *s,char*t){
	char c[50];char d[50];
	int i=0;
	while(s[i]!='\0'){
		if(s[i]>=65 && s[i]<=90) c[i]=s[i]+32;
		else c[i]=s[i];
		i++;
	}
	c[i]='\0';
	i=0;
	while(t[i]!='\0'){
		if(t[i]>=65 && t[i]<=90) d[i]=t[i]+32;
		else d[i]=t[i];
		i++;
	}
	d[i]='\0';
	return strcmp(c,d);
}


void swap(Veiculo vetor[],int i,int j){
	Veiculo tmp=vetor[i];
	vetor[i]=vetor[j];
	vetor[j]=tmp;
}
int compararVeiculos(Veiculo a,Veiculo b){
	if(a.consumoEstrada>b.consumoEstrada) return 1;
	else if(a.consumoEstrada==b.consumoEstrada) return 0;
	else return -1;
}
Veiculo mediana(Veiculo a,Veiculo b,Veiculo c){
		if((compararVeiculos(a,b)>=0 && (compararVeiculos(a,c)<=0) || compararVeiculos(a,b)<=0 && compararVeiculos(a,c)>=0)) return a;
		else if((compararVeiculos(a,b)>=0 && compararVeiculos(b,c)<=0) || compararVeiculos(a,b)<=0 && compararVeiculos(b,c)>=0) return b;
		else return c;

}
void quick(Veiculo array[],int esq,int dir){
	int meio=(esq+dir)/2;
	int i=esq,j=dir;
	Veiculo pivo=mediana(array[esq],array[meio],array[dir]);
	while(i<=j){
		while(array[i].consumoEstrada<pivo.consumoEstrada || (array[i].consumoEstrada==pivo.consumoEstrada &&(comparar(array[i].marca,pivo.marca)<0))) i++;
		while(array[j].consumoEstrada>pivo.consumoEstrada || (array[j].consumoEstrada==pivo.consumoEstrada && (comparar(array[j].marca,pivo.marca)>0))) j--;
		if(i<=j){
			swap(array,i,j);
			i++;j--;
		}
	}
	if(i<dir) quick(array,i,dir);
	if(j>esq) quick(array,esq,j);
}



int main() {
    Veiculo array[500];
    Veiculo temporario[500];
    leitor("/tmp/veiculos.csv", array);

    int numeroId,j=0;
    while (scanf("%d", &numeroId) == 1 && numeroId != -1) {
        for (int i = 0; i < 500; i++) {
            if (array[i].id == numeroId) {
                temporario[j]=array[i];
		j++;
            }
        }
    }
    quick(temporario,0,j-1);
    for(int i=0;i<j;i++){
	    formatVeiculo(temporario[i]);
    }
}

