#include <stdio.h>
#include <omp.h>

int main(){
    int vet[10]={2,4,65,35,6,32,1,6,22,5};
    int tam=0;
    #pragma omp parallel
    for(int fase=0;fase<10;fase++){
        #pragma omp for
        for(int i=fase%2;i<tam;i+=2){
            if(vet[i]>vet[i+1]) swap();
        }
    }
}