#include <stdio.h>
int main(){
    int mat[3][3];
    int i, j, n, n1;

    printf("Digite valores para preencher uma matriz de 3x3:\n");
    for(i=0, n=1;i<3 && n<=3;i++, n++){
        for(j=0, n1=1;j<3 && n1<=3;j++, n1++){
            printf("Digite o numero da %d linha e %d coluna:\n", n, n1);
            scanf("%d", &mat[i][j]);
        }
    }
    
    printf("\nMatriz completa:\n");

    for(i=0;i<3;i++){
        for(j=0;j<3;j++){
            printf("%d ", mat[i][j]);
        }
        printf("\n");
    }

}
