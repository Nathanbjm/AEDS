#include <stdio.h>
#include <omp.h>

int main(){
    printf("Olá\n");
    int i;
    #pragma omp parallel
    for(i=1;i<=4;i++)
        printf("Bom dia! %d %d\n",i,omp_get_thread_num());
}