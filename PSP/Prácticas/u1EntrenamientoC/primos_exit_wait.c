/*
Crea un programa que reciba por parámetro dos números grandes. El programa creará dos procesos hijos. 
Cada hijo gestionará un número primo y verificará si es primo o no. Cada hijo al finalizar indica
 en su estado si el número era primo o no y el proceso padre al recoger el estado del hijo cuenta si 
 era primo o no, el padre escribe el total de números primos.
*/

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
    if(argc != 3){
        printf("Número inválido de parametros");
        return 1;
    }

    const int NUM_HIJOS = 2;

    pid_t id_hijo;
    int i = 0;
    int contadorPrimos = 0;
    int resultadoHijos;
    for(i; i < NUM_HIJOS; i++){
        id_hijo = fork();

        if(id_hijo == 0){
            if(esPrimo(atoi(argv[i + 1]))){
                exit(0);
            } else {
                exit(1);
            }
        }
    }

    
    for (int j = 0; j < NUM_HIJOS; j++)
    {
        wait(&resultadoHijos);
        if(resultadoHijos == 0){
            contadorPrimos++;
        }
    }

    printf("Entre los %d números introducidos, hay %d numero/s primo/s y %d numero/s no primo/s\n", 
                    NUM_HIJOS, contadorPrimos, NUM_HIJOS - contadorPrimos); 

    return 0;
}