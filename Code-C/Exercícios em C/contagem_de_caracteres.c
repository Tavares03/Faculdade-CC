#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
#include <string.h>
#define N 50
int main(){
    setlocale(LC_ALL, "portuguese");

    char s[N];
    int i;

    printf("Digite qualquer coisa que quiser:\n");
    fgets(s, N, stdin);
    fflush(stdin);

    s[strcspn(s, "\n")] = '\0';

    i = strlen(s);

    printf("A sua string tem %d caracteres.\n", i);
    printf("Impreção de posição em posição:\n");

    for(i=0; i < strlen(s); i++){
        printf("%d - %c \n", i, s[i]);
    }
}