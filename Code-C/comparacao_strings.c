#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <locale.h>
#define N 50
int main(){
    setlocale(LC_ALL,"portuguese");

    char s[N];
    char s2[N];
    int ok;

    printf("Digite qualquer coisa:\n");
    fgets(s, N, stdin);
    fflush(stdin);

    printf("Digite outra coisa\n");
    fgets(s2, N, stdin);
    fflush(stdin);

    s[strcspn(s, "\n")] = '\0';
    s2[strcspn(s2, "\n")] = '\0';

    ok = strcmp(s, s2);

    if(ok == 0){
        printf("São iguais\n");
    }else{
        printf("Não são iguais\n");
    }


}