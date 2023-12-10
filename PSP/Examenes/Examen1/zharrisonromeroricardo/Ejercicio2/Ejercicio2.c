#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <openssl/evp.h>
#include <time.h>
#include <sys/wait.h>
#include <stdbool.h>
#include <ctype.h>
#include <signal.h>
#include <math.h>

#define PROCESOS 10

void signal_handler(int signo){

}

int main(int argc, char const *argv[])
{
    pid_t hijo;
    pid_t lista_hijos[PROCESOS + 1];
    int i = 0;
    for (i = 0; i < PROCESOS; i++)
    {
        hijo = fork();

        if(hijo == 0){
            break;
        }
    }

    if(hijo != 0){
        srand(time(NULL));
        while(1){
            randomHijo = rand() % HIJOS;
            kill(lista_hijos[randomHijo], randomSignal);
        }
    }

    ////
    
    
    
    return 0;
}
