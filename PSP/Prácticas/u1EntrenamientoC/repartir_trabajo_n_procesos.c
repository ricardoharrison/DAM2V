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

/*
Crea un programa en c que reciba un número n y un número m. El programa escribirá todos
 los números primos de la longitud n, utilizando m procesos.
*/

int main(int argc, char const *argv[])
{
    int longitud = atoi(argv[1]);
    int procesos = atoi(argv[2]);

    double rangoMin = pow(10, (longitud - 1));
    double rangoMin = pow(10, (longitud)) - 1;

    pid_t child_id;
    int i = 0;

    for (i; i < procesos; i++)
    {
        child_id = fork();

        if(child_id == 0){
            break;
        }
    }
    

    return 0;
}
