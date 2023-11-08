#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <sys/wait.h>

void generateCombinations(FILE *file, char *combination, int position, int length) {
    if (position == length) {
        fprintf(file, "%s\t", combination);
    } else {
        for (char c = 'a'; c <= 'z'; c++) {
            combination[position] = c;
            generateCombinations(file, combination, position + 1, length);
        }
    }
}

int main(int argc, char *argv[]) {
    if (argc != 2) {
        printf("Número de parámetros no válido\n");
        return 1;
    }

    int numberOfChildren = atoi(argv[1]);

    for (int i = 1; i <= numberOfChildren; i++) {
        pid_t child = fork();
        if (child == 0) {
            char filename[20];
            sprintf(filename, "datos%d.txt", i);
            FILE *file = fopen(filename, "w");
            
            if (file == NULL) {
                perror("Error al abrir el archivo");
                exit(1);
            }

            char combination[i + 1];
            combination[i] = '\0';

            generateCombinations(file, combination, 0, i);

            fclose(file);
            exit(0);
        }
    }

    for (int i = 0; i < numberOfChildren; i++) {
        wait(NULL);
    }

    return 0;
}