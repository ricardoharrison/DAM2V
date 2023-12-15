#include <unistd.h>
#include <stdio.h>
#include <signal.h>
#include <stdlib.h>


void signal_handler(int sig){
	if(sig == 1){
		printf("Estoy saltando");
    } else if (sig == 2){
		printf("Qué es eso?");
	} else if (sig == 4){
		printf("No tengo ganas de terminar");
	} else if (sig == 5){
		printf("Déjame en paz!");
	}
    //exit(0); //si se incluye esta línea, el programa termina tras recibir la primera señal
}

int main(void){
	signal(1, signal_handler);
    signal(2, signal_handler); //esta línea la sobresescribiría después la instrucción 'signal(SIGINT, SIG_IGN);'
	signal(4, signal_handler);
	signal(5, signal_handler);
    //signal(SIGINT, SIG_IGN); //SIGINT = 2
    signal(SIGQUIT, SIG_IGN); //SIGQUIT = 3
	while (1) {
        printf(".");
        fflush(stdout);  //para imprimir los puntos cada segundo en vez de todos de golpe al final
        sleep(1); 
   }
	return 0;

}
