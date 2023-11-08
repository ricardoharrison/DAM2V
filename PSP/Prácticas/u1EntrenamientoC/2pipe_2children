#include <stdio.h>
#include <unistd.h>
#include <time.h>
#include <stdlib.h>
#include <sys/wait.h>

int main(void) {
    const int NUMBER_OF_CHILDREN = 2;
    const int NUMBER_OF_RANDOMS = 20;

    int randomEven[NUMBER_OF_RANDOMS], randomOdd[NUMBER_OF_RANDOMS];
    int counterEven = 0, counterOdd = 0;

    int fd1[2], fd2[2];
    pipe(fd1);
    pipe(fd2);

    pid_t child_id = fork();

    if (child_id != 0) {
        pid_t second_child_id = fork();

        if (second_child_id != 0) {
            close(fd1[0]);
            close(fd2[0]);
            srand(time(NULL));

            int min = 1, max = 100;

            for (int i = 0; i < NUMBER_OF_RANDOMS; i++) {
                int randomNumber = (rand() % (max - min + 1)) + min;
                if (randomNumber % 2 == 0) {
                    randomEven[counterEven] = randomNumber;
                    counterEven++;
                } else {
                    randomOdd[counterOdd] = randomNumber;
                    counterOdd++;
                }
            }

            write(fd1[1], &counterEven, sizeof(int));
            write(fd1[1], randomEven, counterEven * sizeof(int));

            write(fd2[1], &counterOdd, sizeof(int));
            write(fd2[1], randomOdd, counterOdd * sizeof(int));

            close(fd1[1]);
            close(fd2[1]);
            wait(NULL);
        } else {
            close(fd1[0]);
            close(fd1[1]);
            close(fd2[1]);
            int receivedCounter;
            read(fd2[0], &receivedCounter, sizeof(int));
            read(fd2[0], randomOdd, receivedCounter * sizeof(int));
            printf("I'm the SECOND child and I received the odd numbers:\t");
            for (int i = 0; i < NUMBER_OF_RANDOMS; i++) {
                if (i < receivedCounter) {
                    printf("%d\t", randomOdd[i]);
                } else {
                    printf("0\t");  // Print 0 for uninitialized elements
                }
            }
            printf("\n");
            close(fd2[0]);
        }      
        
    } else {
        close(fd1[1]);
        close(fd2[0]);
        close(fd2[1]);
        int receivedCounter;
        read(fd1[0], &receivedCounter, sizeof(int));
        read(fd1[0], randomEven, receivedCounter * sizeof(int));
        printf("I'm the FIRST child and I received the even numbers:\t");
        for (int i = 0; i < NUMBER_OF_RANDOMS; i++) {
            if (i < receivedCounter) {
                printf("%d\t", randomEven[i]);
            } else {
                printf("0\t");  // Print 0 for uninitialized elements
            }
        }
        printf("\n");
        close(fd1[0]);
    }
    return 0;
}