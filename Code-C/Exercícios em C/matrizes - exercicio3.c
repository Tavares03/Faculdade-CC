#include <stdio.h>
int main(){
    int m[4][4] = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}};
    int i, j, s=0;

    puts("soma das linhas\n");
    for(i=0;i<4;i++){
        for(j=0;j<4;j++){
           s += m[i][j];
        }
        printf("Soma da linha %d: %d\n", i, s);
        s=0;
    }
    puts("\nSoma das colunas\n");
    for(i=0;i<4;i++){
        for(j=0;j<4;j++){
           s += m[j][i];
        }
        printf("Soma da coluna %d: %d\n", i, s);
        s=0;
    }

}
