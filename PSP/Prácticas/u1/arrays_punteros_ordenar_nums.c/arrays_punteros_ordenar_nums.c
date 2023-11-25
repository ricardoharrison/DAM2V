#include <stdio.h>

// Prototipo de la función order(), ya que es necesario declarar antes de usar
int* order(int* list, int size);

int main(int argc, char const *argv[]) {
    int a = 3, b = 5, c = 1, d = -2, e = 0;
    int list[] = {a, b, c, d, e}; // Crear un array de enteros

    int size = 5; // Tamaño del array

    int* orderedList = order(list, size);

    for (int i = 0; i < size; i++) {
        printf("%d\t", orderedList[i]);
    }

    return 0;
}

// Definición de la función order()
int* order(int* list, int size) {
    for(int j = 0; j < size - 1; j++){
        for (int i = 0; i < size - 1; i++) {
            if (list[i] > list[i + 1]) {
                int temp = list[i + 1];
                list[i + 1] = list[i];
                list[i] = temp;
            }
        }
    }   

    return list;
}