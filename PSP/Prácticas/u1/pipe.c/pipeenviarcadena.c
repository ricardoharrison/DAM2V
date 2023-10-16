#include <stdio.h>
#include <unistd.h>

int main(void)
{
  int fd[2];
  pipe(fd);
  pid_t id;

  id = fork();

  if(id != 0){
    //padre
    close(fd[0]);
    char frase[100];
    printf("Soy el padre y te pido una frase para dársela a mi hijo:\n");
    fgets(frase, sizeof(frase), stdin);
    write(fd[1], &frase, sizeof(frase));
    close(fd[1]);
  } else {
    //hijo
    close(fd[1]);
    char fraseRecibida[100];
    read(fd[0], &fraseRecibida, sizeof(fraseRecibida));
    printf("Soy el hijo y he recibido de mi padre esta frase: '%s'", fraseRecibida);
    close(fd[0]);
  }

  return 0;
}