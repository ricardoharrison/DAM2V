#include <stdio.h>

#include <unistd.h>

#include <time.h>

#include <stdlib.h>

#include <sys/wait.h>

int main(void)
{
    const int NUMBER_OF_CHILDREN = 2;
    const int NUMBER_OF_RANDOMS = 20, MIN = 1, MAX = 100;
    const int SLEEP_TIME = 1;

    int fd_even[2], fd_odd[2];
    pipe(fd_even);
    pipe(fd_odd);

    pid_t child_id;
    int i = 0;

    // create children
    for (i; i < NUMBER_OF_CHILDREN; i++)
    { 
        child_id = fork();

        if (child_id == 0)
        {
            break;
        }
    }

    // parent
    if (i == 2)
    {
        close(fd_even[0]);
        close(fd_odd[0]);
        
        srand(time(NULL));
        for (int i = 0; i < NUMBER_OF_RANDOMS; i++)
        {
            int randomNumber = (rand() % (MAX - MIN)) + MIN;
            if (randomNumber % 2 == 0)
            {
                write(fd_even[1], &randomNumber, sizeof(int));
            }
            else
            {
                write(fd_odd[1], &randomNumber, sizeof(int));
            }
        }
        close(fd_even[1]);
        close(fd_odd[1]);
    }

    // first child
    if (i == 0)
    {
        close(fd_even[1]);
        close(fd_odd[0]);
        close(fd_odd[1]);

        int receivedNumber;
        printf("I'm the FIRST child and I received the even numbers:\n");

        while (read(fd_even[0], &receivedNumber, sizeof(int)) > 0)
        {
            printf("\t%d", receivedNumber);
        }

        printf("\n");
        close(fd_even[0]);
    }

    // second child
    if (i == 1)
    {
        close(fd_even[0]);
        close(fd_even[1]);
        close(fd_odd[1]);

        int receivedNumber;

        // sleep for 1 second to guarantee that the output is printed in the desired order
        sleep(SLEEP_TIME);

        printf("I'm the SECOND child and I received the odd numbers:\n");

        while (read(fd_odd[0], &receivedNumber, sizeof(int)) > 0)
        {
            printf("\t%d", receivedNumber);
        }

        printf("\n");
        close(fd_odd[0]);
    }

    // parent waits for children to end

    for (int i = 0; i < NUMBER_OF_CHILDREN; i++)
    {
        wait(NULL);
    }

    return 0;
}