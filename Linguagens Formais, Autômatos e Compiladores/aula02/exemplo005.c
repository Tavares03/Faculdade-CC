#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int palindromo(char * Palavra);

int main(){

    printf("%d\n", palindromo("OVO"));
    printf("%d\n", palindromo("ACAIACA"));

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