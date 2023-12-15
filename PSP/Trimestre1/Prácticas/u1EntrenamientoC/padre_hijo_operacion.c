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

/*
Crea un programa que cree un hijo y le mande a través de un pipe un carácter
 y dos números. El carácter representa una operación matemática: suma o resta.
  El proceso hijo devolverá en su estado el resultado de la operación.
*/

int main(int argc, char const *argv[])
{
    int fd[2];
    pipe(fd);
    int child_id = fork();

    if (child_id != 0)
    {
        close(fd[0]);
        int operando1;
        char operador;
        int operando2;
        char buffer_cleaner[100];

        printf("Escribe el primer operando:\n");
        scanf("%d", &operando1);
        fgets(buffer_cleaner, sizeof(buffer_cleaner), stdin); //limpiar buffer
        printf("Escribe el operador:\n");
        scanf("%c", &operador);
        printf("Escribe el segundo operando:\n");
        scanf("%d", &operando2);

        write(fd[1], &operando1, sizeof(int));
        write(fd[1], &operador, sizeof(char));
        write(fd[1], &operando2, sizeof(int));

        close(fd[1]);
        wait(NULL);
    }
    else
    {
        close(fd[1]);
        int operando1Recibido;
        char operadorRecibido;
        int operando2Recibido;

        read(fd[0], &operando1Recibido, sizeof(int));
        read(fd[0], &operadorRecibido, sizeof(char));
        read(fd[0], &operando2Recibido, sizeof(int));

        switch(operadorRecibido){
            case '+':
                printf("El resultado de %d%c%d es %d\n", operando1Recibido, operadorRecibido, operando2Recibido, operando1Recibido + operando2Recibido);
                break;
            case '-':
                printf("El resultado de %d%c%d es %d\n", operando1Recibido, operadorRecibido, operando2Recibido, operando1Recibido - operando2Recibido);
                break;
            case '*':
                printf("El resultado de %d%c%d es %d\n", operando1Recibido, operadorRecibido, operando2Recibido, operando1Recibido * operando2Recibido);
                break;
            case '/':
                if(operando2Recibido != 0){
                    printf("El resultado de %d%c%d es %d\n", operando1Recibido, operadorRecibido, operando2Recibido, operando1Recibido / operando2Recibido);
                    break;
                } else {
                    printf("No se puede dividir entre 0\n");
                    break;
                }
            default:
                printf("Operación no reconocida\n");
                break;                       
        }
        close(fd[0]);
    }

    return 0;
}