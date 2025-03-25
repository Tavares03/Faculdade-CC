#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <locale.h>
#define N 50
int main(){
    setlocale(LC_ALL,"portuguese");

    char s1[N];
    char s2[N];
    char r[N] = "";

    printf("Digite o que você quiser:\n");
    fgets(s1, N, stdin);
    fflush(stdin);

    printf("Digite outra coisa:\n");
    fgets(s2, N, stdin);
    fflush(stdin);

    s1[strcspn(s1, "\n")]=' ';

    strcat(s1, s2);
    strcat(r, s1);

    printf("Resultado da junção das strings: %s\n", r);

}