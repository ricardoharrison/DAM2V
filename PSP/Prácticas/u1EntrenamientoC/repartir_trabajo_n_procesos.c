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

bool esPrimo(int num) {
    if (num <= 1) {
        return false;  // Los números menores o iguales a 1 no son primos
    }

    for (int i = 2; i <= sqrt(num); i++) {
        if (num % i == 0) {
            return false;  // Si num es divisible por i, no es primo
            break;
        }
    }
    
    return true;  // Si no se encontraron divisores, el número es primo
}

int main(int argc, char const *argv[])
{
    int longitud = atoi(argv[1]);
    int procesos = atoi(argv[2]);

    double rangoMin = pow(10, (longitud - 1));
    double rangoMax = pow(10, (longitud)) - 1;

    pid_t child_id;
    int i = 0;

    for (i; i < procesos; i++)
    {
        child_id = fork();

        if(child_id == 0){
            break;
        }
    }

    for (int j = rangoMin; j <= rangoMax; j++)
    {
       if(j % procesos == i){
        if(esPrimo(j)){
            printf("El proceso nº%d ha encontrado el numero primo %d\n", i, j);
        }
       }
    }

    if(child_id != 0){
        for(int k = 0; k < procesos; k++){
            wait(NULL);
    }
    }
    

    return 0;
}