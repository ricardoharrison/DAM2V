#include <stdio.h>
#include <string.h>
#include <openssl/evp.h>
#include "md5magic.c"

int main(int argc, char const *argv[])
{
    const int STR_LEN = 4;
    unsigned char result[EVP_MAX_MD_SIZE];
    char *stringToCompare[STR_LEN + 1];

    for(char c1 = 'a'; c1 <= 'z'; c1++){
        for(char c2 = 'a'; c2 <= 'z'; c2++){
            for(char c3 = 'a'; c3 <= 'z'; c3++){
                for(char c4 = 'a'; c4 <= 'z'; c4++){
                    stringToCompare[0] = c1;
                    stringToCompare[1] = c2;
                    stringToCompare[2] = c3;
                    stringToCompare[3] = c4;
                    stringToCompare[4] = '\0';
                    generateMD5(stringToCompare, result);
                    if(strcmp(result, argv[1]) == 0){
                        printf("La palabra clave es -> %s", stringToCompare);
                        break;
                    }                
                }
            }
        }
    }
}