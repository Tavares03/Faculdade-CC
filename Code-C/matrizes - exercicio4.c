#include <stdio.h>
int main(){
    int m[3][3] = {{1,2,3}, {4,5,6}, {7,8,9}};
    int i, j, valor;
   
    printf("Digite um numero para multiplicar todos os termos da matrix:\n");
    scanf("%d", &valor);

    for(i=0;i<3;i++){
        for(j=0;j<3;j++){
            m[i][j] *= valor;
        }
    }
     for(i=0;i<3;i++){
        for(j=0;j<3;j++){
            printf(" |%d| ", m[i][j]);
        }
        printf("\n");
     }
}
