#include <stdio.h>

int covertToFarenheit(int celsiusValue){
  return (celsiusValue * 9 / 5) + 32;
}

int main(void){
  int userInput;

  printf("Insert a value in Celsius to convert to Farenheit: \n");
  scanf("%i", &userInput);

  printf("%iº Celsius are %iº Farenheit.", userInput, covertToFarenheit(userInput));

  return 0;
}