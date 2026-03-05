#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void reverter(char * reverso, char * Palavra);

int main(){

    char A[] = "123x89 43";
    char B[] = "67y0";
    char R[100];

    reverter(R, A);
    printf("%s\n", R);
}

void reverter(char * reverso, char * Palavra){
    int PosicaoFinal = strlen(Palavra) - 1;

    for (int i = 0; i <= PosicaoFinal; i++){
        reverso[i] = Palavra[PosicaoFinal - i];
    }

    reverso[PosicaoFinal + 1] = '\0';
}