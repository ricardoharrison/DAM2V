#include <stdio.h>
#include <string.h>
#include <openssl/evp.h>
#include <time.h>
#include "md5magic.c"
#include <unistd.h>
#include <sys/wait.h>

//./md5forks 582fc884d6299814fbd4f12c1f9ae70f 74437fabd7c8e8fd178ae89acbe446f2 28ea19352381b8659df830dd6d5c90a3 90f077d7759d0d4d21e6867727d4b2bd


//TO DO -> void searchBetween(char a, char 0){}

int main(int argc, char const *argv[])
{
    const int STR_LEN = 4;
    unsigned char result[EVP_MAX_MD_SIZE];
    char stringToCompare[STR_LEN + 1];
    
    clock_t start, end;
    double cpu_time_used;
    
    pid_t idA = fork();
    pid_t idB = fork();

    const int NUMBER_OF_CHILDREN = 3;

    start = clock();

    //primer fork
    if(idA != 0 && idB != 0){
      for (int i = 0; i < argc - 1; i++) {
        for (char c1 = 'a'; c1 <= 'f'; c1++) {
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


    //segundo fork
    if(idA != 0 && idB == 0){
      for (int i = 0; i < argc - 1; i++) {
        for (char c1 = 'g'; c1 <= 'm'; c1++) {
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

    //tercer fork
    if(idA == 0 && idB != 0){
      for (int i = 0; i < argc - 1; i++) {
        for (char c1 = 'n'; c1 <= 's'; c1++) {
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

    //cuarto fork
    if(idA == 0 && idB == 0){
      for (int i = 0; i < argc - 1; i++) {
        for (char c1 = 't'; c1 <= 'z'; c1++) {
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
    
    end = clock();
    
    //esperar a los hijos
    for(int i = 0; i < NUMBER_OF_CHILDREN; i++){
        wait(NULL);
    }

    //solo el padre imprime
    if(idA != 0 && idB != 0){
      cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC;    
      printf("Tiempo de ejecución: %f segundos\n", cpu_time_used);
    }
    
    

}