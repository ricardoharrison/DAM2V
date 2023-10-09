#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <stdlib.h>
#include <sys/wait.h>

int printTable(int i){
    printf("Tabla del %d   -> \t", i);
    for (int j = 1; j <= 10; j++){
        printf("%dx%d=%d\t", i, j, i*j);
    }
    printf("\n");
    return 0;
}

int main(void){
    for(int i = 1; i <= 10; i++){
        pid_t idhijo;
        idhijo = fork();
        if(idhijo == 0){
            printTable(i);
            exit(0);
        } else {
            wait(NULL); //esta línea debería ir en un bucle for fuera porque si no no es concurrente
        }
    }
    // for{} -> aquí
    printf("FIN");
}