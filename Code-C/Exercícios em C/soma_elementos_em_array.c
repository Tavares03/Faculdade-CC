#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
#include <string.h>

int main(){
    setlocale(LC_ALL, "portuguese");

    int v[5] = {1, 2, 3, 4, 5};
    float s = 0;
    int i;

    for(i=0;i<5;i++){
        s += v[i];
    }

    printf("Soma: %.2f\n", s);
}