#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <locale.h>
#define N 50
int main(){
    setlocale(LC_ALL,"portuguese");

    char s[N];
    int i;
    char s2[N];

    printf("Digite qualquer coisa:\n");
    fgets(s,N,stdin);
    fflush(stdin);

    for(i=s; i>=0; i--){
        printf("%s", s[i]);
    }
}