#include <stdio.h>
#include <omp.h>

int main(){
    omp_set_num_threads(13);
    #pragma omp parallel
    {
        printf("Paralela %d\n",omp_get_num_threads());
    }
}