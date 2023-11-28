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

#define LEER 0
#define ESCRIBIR 1
#define TUBOS 2
#define HIJOS 2
#define ID_HIJO_A 0
#define ID_HIJO_B 1
#define NUMS_ALEAT 6
#define RANGO_MIN 1
#define RANGO_MAX 10

/*
Enviar numeros aleatorios a hijos, los hijos devuelven la suma al padre.
El padre imprime la suma
*/

bool esPar(int num){
    if(num % 2 == 0){
        return true;
    }
    return false;
}

int main(int argc, char const *argv[])
{
    int fd_A_pares[TUBOS];
    int fd_A_impares[TUBOS];
    int fd_B_pares[TUBOS];
    int fd_B_impares[TUBOS];

    pipe(fd_A_pares);
    pipe(fd_A_impares);
    pipe(fd_B_pares);
    pipe(fd_B_impares);

    pid_t hijo;
    int i = 0;

    for (i; i < HIJOS; i++)
    {
        hijo = fork();

        if(hijo == 0){
            break;
        }
    }

    if(hijo != 0){
        srand(time(NULL));
        close(fd_A_pares[LEER]);
        close(fd_A_impares[LEER]);
        close(fd_B_pares[ESCRIBIR]);
        close(fd_B_impares[ESCRIBIR]);

        int aleatorio;
        int suma_pares;
        int suma_impares;

        for (int j = 0; j < NUMS_ALEAT; j++)
        {
            aleatorio = rand() % (RANGO_MAX - RANGO_MIN) + RANGO_MIN;
            if(esPar(aleatorio)){
                write(fd_A_pares[ESCRIBIR], &aleatorio, sizeof(aleatorio));
            } else {
                write(fd_A_impares[ESCRIBIR], &aleatorio, sizeof(aleatorio));
            }
        }

        close(fd_A_pares[ESCRIBIR]);
        close(fd_A_impares[ESCRIBIR]);

        read(fd_B_pares[LEER], &suma_pares, sizeof(suma_pares));
        read(fd_B_impares[LEER], &suma_impares, sizeof(suma_impares));

        printf("PADRE: Los pares suman %d\n", suma_pares);
        close(fd_B_pares[LEER]);

        printf("PADRE: Los impares suman %d\n", suma_impares);
        close(fd_B_impares[LEER]);

        for (int j = 0; j < HIJOS; j++)
        {
            wait(NULL);
        }
    }

    if(i == ID_HIJO_A){
        close(fd_A_pares[ESCRIBIR]);
        close(fd_A_impares[ESCRIBIR]);
        close(fd_B_pares[LEER]);
        close(fd_B_impares[LEER]);
        close(fd_B_impares[ESCRIBIR]);
        close(fd_A_impares[LEER]);

        int aleatorio_recibido;
        int suma_pares_enviada = 0;

        printf("Soy el hijo A y he recibido los pares:\n");
        while (read(fd_A_pares[LEER], &aleatorio_recibido, sizeof(aleatorio_recibido)) > 0)
        {
            printf("%d\t", aleatorio_recibido);
            suma_pares_enviada += aleatorio_recibido;
        }

        printf("\n");

        write(fd_B_pares[ESCRIBIR], &suma_pares_enviada, sizeof(suma_pares_enviada));
        close(fd_A_pares[LEER]);
        close(fd_B_pares[ESCRIBIR]);
        
    }

    if(i == ID_HIJO_B){
        close(fd_A_pares[ESCRIBIR]);
        close(fd_A_impares[ESCRIBIR]);
        close(fd_B_pares[LEER]);
        close(fd_B_impares[LEER]);        
        close(fd_A_pares[LEER]);
        close(fd_B_pares[ESCRIBIR]);

        int aleatorio_recibido;
        int suma_impares_enviada = 0;

        printf("Soy el hijo B y he recibido los impares:\n");
        while (read(fd_A_impares[LEER], &aleatorio_recibido, sizeof(aleatorio_recibido)) > 0)
        {
            printf("%d\t", aleatorio_recibido);
            suma_impares_enviada += aleatorio_recibido;
        }

        printf("\n");

        write(fd_B_impares[ESCRIBIR], &suma_impares_enviada, sizeof(suma_impares_enviada));

        close(fd_A_impares[LEER]);
        close(fd_B_impares[ESCRIBIR]);
        
    }

    return 0;
}


