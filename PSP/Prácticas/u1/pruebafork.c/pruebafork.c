#include <stdio.h>
#include <unistd.h> 

int main(void) {
  pid_t id;
  pid_t id2;

  id = fork();
  id2 = fork();   

  printf("Hello yo!");
}