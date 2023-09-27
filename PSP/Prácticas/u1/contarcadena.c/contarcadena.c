#include <stdio.h>
#include <string.h>

int main(void){
    
  char cadena[20];
  int vocales = 0;
  int consonantes = 0;
  
  printf("Introduce una cadena: \n");
  scanf("%s", &cadena);
  
  for(int i; i < strlen(cadena); i++){
      if ((toupper(cadena[i]) >= 'A' && toupper(cadena[i]) <= 'Z')) {
        if (toupper(cadena[i]) == 'A' || toupper(cadena[i]) == 'E' || toupper(cadena[i]) == 'I' || toupper(cadena[i]) == 'O' || toupper(cadena[i]) == 'U') {
            vocales += 1;
        } else {
            consonantes += 1;
        }
    }
  }
  
  printf("La palabra introducida tiene %i vocales y %i consonantes.", vocales, consonantes);
  
  return 0;
}