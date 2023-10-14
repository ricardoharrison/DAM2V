#include <stdio.h>
#include <unistd.h>

int main(void){
  char *command = "ip";
  char *arguments[] = {"ip", "a", NULL};
  execvp(command, arguments);
}