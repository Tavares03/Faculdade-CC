#include <stdio.h>
int main(){
    int mat[2][2] = {{1,2}, {3,4}};
    int mat1[2][2] = {{5,6}, {7,8}};
    int resultado[2][2];
    int i, j;

    for(i=0;i<2;i++){
        for(j=0;j<2;j++){
            resultado[i][j] = mat[i][j] + mat1[i][j];
        }
    }

    for(i=0;i<2;i++){
        for(j=0;j<2;j++){
            printf(" |%d| ", resultado[i][j]);
        }
        printf("\n");
    }
    
}



