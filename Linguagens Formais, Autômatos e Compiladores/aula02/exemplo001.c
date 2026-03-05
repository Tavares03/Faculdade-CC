#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void reverter(char * reverso, char * Palavra);
int calcularTamanho(char * Palavra);
void Concatenar(char * R, char * A, char * B);
int palindromo(char * Palavra);

int main(){

    char A[] = "123x89 43";
    char B[] = "67y0";
    char R[100];

    printf("%s%s\n", A, B);

    Concatenar(R, A, B);

    printf("%s\n", R);

    char S[100];
    strcpy(S, A);
    strcat(S, B);

    printf("%s\n", S);

    printf("Tamanho de A: %d\n", calcularTamanho(A));
    printf("Tamanho de B: %d\n", calcularTamanho(B));

    reverter(R, A);
    printf("%s\n", R);

    printf("%d\n", palindromo("OVO"));
    printf("%d\n", palindromo("ACAIACA"));

}

void Concatenar(char * R, char * A, char * B){
    int i = 0;

    while(A[i] != '\0') {
        R[i] = A[i];
        i++;
    }

    int j = 0;

    while(B[j] != '\0') {
        R[i + j] = B[j];
        j++;
    }

    R[i + j] = '\0';

}

int calcularTamanho(char * Palavra){
    int i = 0;
    while(Palavra[i] != '\0'){
        i++;
    }

    return i;
}

void reverter(char * reverso, char * Palavra){
    int PosicaoFinal = strlen(Palavra) - 1;

    for (int i = 0; i <= PosicaoFinal; i++){
        reverso[i] = Palavra[PosicaoFinal - i];
    }

    reverso[PosicaoFinal + 1] = '\0';
}

int palindromo(char * Palavra){
    char reverso[100];

    int PosicaoFinal = strlen(Palavra) - 1;

    for (int i = 0; i <= PosicaoFinal; i++){
        reverso[i] = Palavra[PosicaoFinal - i];
    }

    reverso[PosicaoFinal + 1] = '\0';

    if (strcmp(reverso, Palavra) == 0){
        return 1;
    } else {
        return 0;
    }
}