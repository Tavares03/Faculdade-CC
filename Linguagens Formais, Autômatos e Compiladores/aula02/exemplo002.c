#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void Concatenar(char * R, char * A, char * B);

int main(){

    char A[] = "123x89 43";
    char B[] = "67y0";
    char R[100];

    Concatenar(R, A, B);

    printf("%s\n", R);

    char S[100];
    strcpy(S, A);
    strcat(S, B);

    printf("%s\n", S);

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