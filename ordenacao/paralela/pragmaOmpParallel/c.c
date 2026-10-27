// i agora é declarado como variável compartilhada entre as threads
#include <stdio.h>
#include <omp.h>

int main(){
    int i;
    #pragma omp parallel for num_threads(3)
        for(i=1; i<=9;i++)
            printf("Paralela %d-%d\n",omp_get_thread_num(),i);
}