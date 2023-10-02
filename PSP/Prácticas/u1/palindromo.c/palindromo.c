#include <stdio.h>
#include <stdbool.h>
#include <ctype.h>

int main(void)
{
  /* Crea un programa en C que determine si una palabra o frase ingresada por el usuario es un palíndromo o no. 
  Un palíndromo es una palabra o frase que se lee igual de izquierda a derecha y de derecha
  a izquierda, ignorando espacios y signos de puntuación. El programa debe eliminar los espacios
   y considerar solo las letras en la verificación. */

  char cadena[50] = "";
  char nuevaCadena[50] = "";
  bool palindromo = true;
  int contador = 0;
  
  printf("Introduce una frase: \n");
  fgets(cadena, sizeof(cadena), stdin);
  printf("%s\n", cadena);
  
  for(int i = 0; i < strlen(cadena); i++){
      if (isalpha(cadena[i])){
          nuevaCadena[contador] = cadena[i];
          contador += 1;
      }
      //printf("Iteración %i\n", i);
  }
  
  printf("-----\n");
  printf("la frase es: %s\n", cadena);
  printf("la nueva frase es: %s\n", nuevaCadena);
  printf("-----\n");

  for (int i = 0; i < strlen(nuevaCadena); i++){
    if(nuevaCadena[i] != nuevaCadena[strlen(nuevaCadena) - i - 1]){
      palindromo = false;
    }
  }
    if(palindromo){
        printf("¡Genial! La cadena introducida es un palíndromo.");
    } else {
        printf("¡Vaya! La cadena introducida NO es un palíndromo.");      
    }
}