#include <stdio.h>
#include <unistd.h>

int main()
{
    // Comando a ejecutar
    char *args[] = {"bash", "-c", "ifconfig | grep -oE '\\b([0-9]{1,3}\\.){3}[0-9]{1,3}\\b'", NULL};

    // Ejecutar el comando
    execvp(args[0], args);

    return 0;
}

/* #include <stdio.h>
#include <stdlib.h>
#include <regex.h>
#include <string.h>

#define MAX_BUF 1024

int main() {
    FILE *fp;
    char path[MAX_BUF];
    char buffer[MAX_BUF];

    // Abrir una tubería al comando 'ip a' para leer su salida
    fp = popen("ip a", "r");
    if (fp == NULL) {
        printf("Error ejecutando el comando.\n");
        return EXIT_FAILURE;
    }

    // Crear una expresión regular para buscar direcciones IP
    regex_t regex;
    int reti;
    char regex_pattern[] = "([0-9]+\\.){3}[0-9]+"; // Expresión regular para direcciones IPv4

    if ((reti = regcomp(&regex, regex_pattern, REG_EXTENDED)) != 0) {
        printf("Error compilando la expresión regular.\n");
        return EXIT_FAILURE;
    }

    // Leer la salida del comando 'ip a' y filtrar direcciones IP
    while (fgets(buffer, sizeof(buffer), fp) != NULL) {
        char *token = strtok(buffer, " \t\n"); // Dividir la línea en tokens usando espacios, tabulaciones y saltos de línea como delimitadores
        while (token != NULL) {
            // Aplicar la expresión regular para buscar direcciones IP
            if (regexec(&regex, token, 0, NULL, 0) == 0) {
                printf("%s\n", token); // Imprimir la dirección IP encontrada
            }
            token = strtok(NULL, " \t\n");
        }
    }

    // Cerrar la tubería y liberar la expresión regular
    pclose(fp);
    regfree(&regex);

    return EXIT_SUCCESS;
} */