#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int calcularTamanho(char * Palavra);

int main(){

    char A[] = "123x89 43";
    char B[] = "67y0";
    char R[100];

    printf("Tamanho de A: %d\n", calcularTamanho(A));
    printf("Tamanho de B: %d\n", calcularTamanho(B));
}

int calcularTamanho(char * Palavra){
    int i = 0;
    while(Palavra[i] != '\0'){
        i++;
    }

    return i;
}