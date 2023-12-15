#include <stdio.h>          //libería para standard input/output
#include <unistd.h>         //llamadas del sistema unix
#include <sys/types.h>      //typos del sistema (necesario para poder usar pid)

int main(void){
    pid_t id; //declaración de variable para almacenar id del proceso

    int n = 42;
    double pi = 3.14;

    //clonación
    id = fork();

    //ahora hay 2 procesos
    if(id != 0) {                               
        printf("Soy el padre, mi id es %d, y mi hijo es %d\n", getpid(), id);        
    } else {
        printf("Soy el hijo, mi id es %d, y mi hijo es %d\n", getpid(), id);  
    }

    //al compilar y ejecutar, comprobamos que un proceso ejecuta el "if" y el otro el "else"

    return 0;
}