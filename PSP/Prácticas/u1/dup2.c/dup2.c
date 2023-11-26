#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>

int main(void)
{
  char *command = "ls";
  char *arguments[] = {"ls", "/", NULL};
  int myFile = open("output.txt", O_WRONLY | O_CREAT | O_TRUNC, 0644);
  if(myFile < 0){
    perror("No fue posible abrir el fichero");
    return 1;
  }

  pid_t id = fork();

  if(id == 0){
    execvp(command, arguments);  //este proceso lo muestra por consola
  } else {
    dup2(myFile, STDOUT_FILENO);
    execvp(command, arguments);  //ya ejecutado dup2, esta proceso lo saca al archivo
  }  

  close(myFile);
  return 0;
}