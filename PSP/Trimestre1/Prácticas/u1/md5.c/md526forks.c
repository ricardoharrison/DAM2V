#include <stdio.h>
#include <string.h>
#include <openssl/evp.h>
#include <time.h>
#include "md5magic.c"
#include <unistd.h>
#include <sys/wait.h>

//./md5 582fc884d6299814fbd4f12c1f9ae70f 74437fabd7c8e8fd178ae89acbe446f2 28ea19352381b8659df830dd6d5c90a3 90f077d7759d0d4d21e6867727d4b2bd

int main(int argc, char const *argv[]){  

    void searchBetween(char a, char b){
        const int STR_LEN = 4;
        unsigned char result[EVP_MAX_MD_SIZE];
        char stringToCompare[STR_LEN + 1];
        for (int i = 0; i < argc - 1; i++) {
            for (char c1 = a; c1 <= b; c1++) {
                for (char c2 = 'a'; c2 <= 'z'; c2++) {
                    for (char c3 = 'a'; c3 <= 'z'; c3++) {
                        for (char c4 = 'a'; c4 <= 'z'; c4++) {
                            stringToCompare[4] = '\0';
                            stringToCompare[0] = c1;
                            stringToCompare[1] = c2;
                            stringToCompare[2] = c3;
                            stringToCompare[3] = c4;

                            generateMD5(stringToCompare, result);
                            if (strcmp(result, argv[i + 1]) == 0) {
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
    const char lettersString[] = "abcdefghijklmnopqrstuvwxyz";

    start = clock();
    int i = 0;
    pid_t id;

    for(i = 0; i < NUMBER_OF_CHILDREN; i++){
      id = fork();
      if(id == 0){
        break;
      }
    }

    searchBetween(lettersString[i], lettersString[i]); 

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