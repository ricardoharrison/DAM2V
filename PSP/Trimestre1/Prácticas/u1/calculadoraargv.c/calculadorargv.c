#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
    
    const int ARGUMENTOS_NECESARIOS = 4;
    float resultado;
    int opcionSwitch = 0;
   
    if (argc < ARGUMENTOS_NECESARIOS) {
        printf("No se han indicado suficientes argumentos.\n");
    } else if (argc > ARGUMENTOS_NECESARIOS) {
        printf("Se han indicado demasiados argumentos.\n");
    } else {
        if(strcmp(argv[1], "suma") == 0){
        opcionSwitch = 1;
        }
        if(strcmp(argv[1], "resta") == 0){
            opcionSwitch = 2;
        }
        if(strcmp(argv[1], "multiplicacion") == 0){
            opcionSwitch = 3;
        }
        if(strcmp(argv[1], "division") == 0){
            opcionSwitch = 4;
        }
        switch (opcionSwitch){
            case 1:
                resultado = atof(argv[2]) + atof(argv[3]);
                printf("El resultado de la suma es %.2f\n", resultado);
                break;
            case 2:
                resultado = atof(argv[2]) - atof(argv[3]);;
                printf("El resultado de la resta es %.2f\n", resultado);
                break;
            case 3:
                resultado = atof(argv[2]) * atof(argv[3]);
                printf("El resultado de la multiplicación es %.2f\n", resultado);
                break;
            case 4:
                if(atof(argv[3]) != 0){
                    resultado = atof(argv[2]) / atof(argv[3]);
                    printf("El resultado de la división es %.2f\n", resultado);
                } else {
                    printf("No es posible dividir entre 0\n");
                }
                break;
            default:
                printf("No se ha indicado ninguna orden válida\n");
        }
    }

    return 0;
}