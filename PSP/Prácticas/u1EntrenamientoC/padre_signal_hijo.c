#include <stdio.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>
#include <stdlib.h>

/*
Crea un proceso que sea capaz de gestionar un señal definida por el usuario. 
Luego hará fork y el padre le enviará la señas al hijo. Al gestionar la señal 
el hijo escribirá "Recibido y terminará el proceso."
*/

void signal_handler(int signal){
    if(signal == 3){
        printf("Ouch!\n");
        exit(0);
    }
    if(signal == 4){
        printf("Mamarracho!\n");
        exit(0);
    }
    if(signal == 15){
        printf("Recibido, me has matado\n");
        exit(0);
    }
}

int main(int argc, char const *argv[])
{
    pid_t child_id;
    child_id = fork();

    if(child_id == 0){
        signal(3, signal_handler);
        signal(4, signal_handler);
        signal(15, signal_handler);

        printf("Soy el hijo y estoy escribiendo puntos:\n");
        while(1){
            printf(".");
            fflush(stdout);
            sleep(1);
        }
    } else {
        int sig;
        printf("Envía una señal a mi hijo:\n");
        scanf("%i", &sig);
        kill(child_id, sig);
    }

    sleep(1);

    return 0;
}
