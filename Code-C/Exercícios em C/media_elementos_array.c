#include <stdio.h>
#include <locale.h>

int main(){
    setlocale(LC_ALL, "portuguese");
 
    float v[5] = {1.5,2.5,3.5,4.5,5.5};
    int i;
    float m = 0;

    for(i=0;i<5;i++){
        m+=v[i];
    }
    m /= 5;
    printf("Media: %.2f\n", m);
}