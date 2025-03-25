#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <locale.h>
#define N 50
int main(){
    setlocale(LC_ALL,"portuguese");

    char s[N];
    char s2[N];

    printf("Digite qualquer coisda:\n");
    fgets(s,N,stdin);
    fflush(stdin);

    s[strcspn(s, "\n")] = '\0';

    strcpy(s2, s);

    printf("String original: %s\n", s);
    printf("String copia: %s\n", s2);

}