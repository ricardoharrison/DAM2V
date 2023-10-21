#include <stdio.h>
#include <unistd.h>
#include <time.h>
#include <stdlib.h>

int main(void) {
    int fd[2];
    pipe(fd);

    pid_t child_id = fork();

    if (child_id != 0) {
        close(fd[0]);
        int number1, number2, number3;

        srand(time(NULL));
        int min = 1;
        int max = 100;
        number1 = (rand() % (max - min + 1)) + min;
        number2 = (rand() % (max - min + 1)) + min;
        number3 = (rand() % (max - min + 1)) + min;

        int numbers[3] = {number1, number2, number3};

        // Write the numbers to the pipe
        write(fd[1], numbers, 3 * sizeof(int));
        close(fd[1]);

    } else {
        close(fd[1]);

        int numbers[3];
        read(fd[0], numbers, 3 * sizeof(int));

        close(fd[0]);

        // Sort the received numbers
        int mayor, medio, menor;
        if (numbers[0] < numbers[1]) {
            if (numbers[0] < numbers[2]) {
                menor = numbers[0];
                if (numbers[1] < numbers[2]) {
                    medio = numbers[1];
                    mayor = numbers[2];
                } else {
                    medio = numbers[2];
                    mayor = numbers[1];
                }
            } else {
                menor = numbers[2];
                medio = numbers[0];
                mayor = numbers[1];
            }
        } else {
            if (numbers[1] < numbers[2]) {
                menor = numbers[1];
                if (numbers[0] < numbers[2]) {
                    medio = numbers[0];
                    mayor = numbers[2];
                } else {
                    medio = numbers[2];
                    mayor = numbers[0];
                }
            } else {
                menor = numbers[2];
                medio = numbers[1];
                mayor = numbers[0];
            }
        }

        FILE *file = fopen("salida.txt", "w");
        fprintf(file, "Los números recibidos son, de menor a mayor: %d, %d, %d\n", menor, medio, mayor);
        fclose(file);
    }

    return 0;
}