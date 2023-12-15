#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <signal.h>

#define MAX_HIJOS 100
#define MIN_HIJOS 1
#define NUM_PARAM_ESPERADO 2
#define TIEMPO_ESPERA 1

/*
Crea un programa que reciba un número n por parámetro. El programa creará n hijos y les enviará una señal a cada uno de ellos para matarlos.
*/



int main(int argc, char *argv[]) {
    if (argc != NUM_PARAM_ESPERADO || atoi(argv[1]) > MAX_HIJOS || atoi(argv[1]) < MIN_HIJOS) {
        printf("Parámetros inválidos");
        return 1;
    }

    int num_hijos = atoi(argv[1]);
    pid_t child_pid[num_hijos];

    printf("Creando %d hijos...\n", num_hijos);

    for (int i = 0; i < num_hijos; i++) {
        child_pid[i] = fork();

        if (child_pid[i] == 0) { // Proceso hijo
            printf("Hijo %d creado con PID: %d\n", i + 1, getpid());
            while (1) {
                // El proceso hijo espera la señal SIGTERM
                //no hace falta 'break' en este caso (gracias al while(1))
                sleep(TIEMPO_ESPERA);
            }
        } else if (child_pid[i] < 0) {
            printf("Error al crear el proceso hijo.\n");
        }
    }

    sleep(TIEMPO_ESPERA); //damos tiempo a los hijos a crearse

    // Enviar señal SIGTERM a cada hijo (solo el padre ejecuta este bloque)
    if (child_pid[0] > 0) {
        for (int i = 0; i < num_hijos; i++) {
            kill(child_pid[i], SIGTERM); //(SIGTERM = 15)
            printf("Señal SIGTERM enviada al hijo con PID: %d\n", child_pid[i]);
        }
    }        

    return 0;
}