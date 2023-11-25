#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>

/*
Crea un programa que reciba un número n por parámetro. El programa creará n hijos y les enviará una señal a cada uno de ellos para matarlos.
*/

int main(int argc, char const *argv[])
{
    const int EXPECTED_NUM_OF_PARAMS = 2;
    const int EXPECTED_PARAM_POSITION = 1;
    char param_err[] = "An incorrect number of parameters was received";
    char child_msg[] = "I'm the child number %d and I'm alive";

    if(argc != EXPECTED_NUM_OF_PARAMS)
    {
        printf("%s", param_err);
        return 1;
    }

    pid_t child_id;
    int fd[2];
    pipe(fd);
    
    int i = 0;

    for (i; i < argv[EXPECTED_PARAM_POSITION]; i++)
    {
        child_id = fork();

        if (child_id == 0)
        {
            close(fd[1]);
            break;
        }
        
    }

    printf(child_msg, i);

    if(child_id != 0){
        close(fd[0]);
        write(fd[1], %) //TO-DO
    }


    
    
    return 0;
}
