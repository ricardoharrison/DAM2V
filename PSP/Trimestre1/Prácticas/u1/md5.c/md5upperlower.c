#include <stdio.h>
#include <string.h>
#include <openssl/evp.h>
#include <time.h>
#include "md5magic.c"
#include <unistd.h>
#include <sys/wait.h>

// ./md5 f4a1c8901a3d406f17af67144a3ec71a d66e29062829e8ae0313adc5a673f863



int main(int argc, char const *argv[]){  

  void searchBetween(char a, char b){
      const int STR_LEN = 5;
      unsigned char result[EVP_MAX_MD_SIZE];
      char stringToCompare[STR_LEN + 1];
      stringToCompare[STR_LEN] = '\0';
              
      for (char c1 = a; c1 <= b; c1++) {
          stringToCompare[0] = c1;
          printf("Searching %c", c1);
          for (char c2 = 'A'; c2 <= 'z'; c2++) {
              stringToCompare[1] = c2;
              for (char c3 = 'A'; c3 <= 'z'; c3++) {
                  stringToCompare[2] = c3;
                  for (char c4 = 'A'; c4 <= 'z'; c4++) {
                      stringToCompare[3] = c4;
                      for (char c5 = 'A'; c4 <= 'z'; c5++){  
                          stringToCompare[4] = c5;      
                          generateMD5(stringToCompare, result);
                          if (strcmp(result, argv[1]) == 0) {
                              printf("La palabra clave %d es -> %s\n", i + 1, stringToCompare);
                              break;
                          }
                      }
                  }
              }
          }
      }        
    }   

    clock_t start, end;
    double cpu_time_used;

    const int NUMBER_OF_CHILDREN = 26;
    const char lettersString[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    start = clock();
    int i = 0;
    pid_t id;

    for(i = 0; i < NUMBER_OF_CHILDREN; i++){
      id = fork();
      if(id == 0){
        break;
      }
    }

    searchBetween(lettersString[2 * i], lettersString[2 * i + 1]); 
    
    //esperar a los hijos
    if (id != 0) {
      while ((wait(NULL)) > 0) ;
    }

    //solo el padre imprime
    if(id != 0){
      end = clock();
      cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC;    
      printf("Tiempo de ejecución: %f segundos\n", cpu_time_used);
    }
}