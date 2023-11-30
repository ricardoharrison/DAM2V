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

#define HIJOS 2
#define ID_HIJO_A 0
#define ID_HIJO_B 1
#define READ 0
#define WRITE 1
#define PIPE_ENDS 2

bool esPrimo(int num)
{
    if (num <= 1)
    {
        return false; // Los números menores o iguales a 1 no son primos
    }

    for (int i = 2; i <= sqrt(num); i++)
    {
        if (num % i == 0)
        {
            return false; // Si num es divisible por i, no es primo
            break;
        }
    }

    return true; // Si no se encontraron divisores, el número es primo
}

int main(int argc, char const *argv[])
{
    int i = 0;
    pid_t hijo;

    int longitud = atoi(argv[1]);
    int cantidad = atoi(argv[2]);

    const int RANGO_MAX = pow(10, longitud) - 1;
    const int RANGO_MIN = pow(10, longitud - 1);

    int fdA[PIPE_ENDS];
    int fdB[PIPE_ENDS];
    pipe(fdA);
    pipe(fdB);
    pid_t lista_hijos[HIJOS];
    for (i; i < HIJOS; i++)
    {
        hijo = fork();
        lista_hijos[i] = hijo;

        if(hijo == 0){
            break;
        }
    }

    if(hijo != 0){ //padre
        srand(time(NULL));
        close(fdA[READ]);
        close(fdB[READ]);

        int random;

        for (int i = 0; i < cantidad; i++)
        {
            random = rand() % (RANGO_MAX - RANGO_MIN) + RANGO_MIN;            
            write(fdA[WRITE], &random, sizeof(random));
        }

        for (int i = 0; i < cantidad; i++)
        {
            random = rand() % (RANGO_MAX - RANGO_MIN) + RANGO_MIN;
            write(fdB[WRITE], &random, sizeof(random));
        }

        close(fdB[WRITE]);
        close(fdA[WRITE]);

        for (int j = 0; j < HIJOS; j++)
        {
            int status;
            waitpid(lista_hijos[j], &status, 0);
            if (status == 0)
            {
                printf("El hijo %d ha terminado con EXITO (no encuentra primo)\n", lista_hijos[j]);
            }
            else
            {
                printf("El hijo %d ha terminado con ERROR (encuentra un número primo)\n", lista_hijos[j]);
            }
        }
    }

    if(i == ID_HIJO_A){
        close(fdB[READ]);
        close(fdA[WRITE]);
        close(fdB[WRITE]);

        int randomReceived;
        while(read(fdA[READ], &randomReceived, sizeof(randomReceived)) > 0){
            printf("Soy el hijo %d y he recibido el número %d\t", getpid(), randomReceived);
            if(esPrimo(randomReceived)){
                printf("Es primo\n");
                exit(EXIT_FAILURE);
            } else {
                printf("No es primo\n");
            }        
        }

        close(fdA[READ]);
        exit(EXIT_SUCCESS);
    }

    if (i == ID_HIJO_B)
    {
        close(fdA[READ]);
        close(fdA[WRITE]);
        close(fdB[WRITE]);

        int randomReceived;
        while (read(fdB[READ], &randomReceived, sizeof(randomReceived)) > 0)
        {
            printf("Soy el hijo %d y he recibido el número %d\t", getpid(), randomReceived);
            if (esPrimo(randomReceived))
            {
                printf("Es primo\n");
                exit(EXIT_FAILURE);
            }
            else
            {
                printf("No es primo\n");
            }
        }

        close(fdB[READ]);
        exit(EXIT_SUCCESS);
    }

    return 0;
}