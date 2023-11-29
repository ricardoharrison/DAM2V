#include <stdio.h>
#include <unistd.h>

int main(void){
  char* command = "date";
  char* arguments[] = {"date", NULL};
  execvp(command, arguments);
}