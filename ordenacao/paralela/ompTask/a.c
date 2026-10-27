#include <stdio.h>
#include <omp.h>

int main(){
    #pragma omp parallel
    {
        #pragma omp single
        {
            #pragma omp task
            printf("A-%d\n",omp_get_thread_num());

            #pragma omp task
            printf("B-%d\n",omp_get_thread_num());

            #pragma omp taskwait
            printf("C-%d\n",omp_get_thread_num());
        }
    }
}