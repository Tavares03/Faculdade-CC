#include <stdio.h>

void insert_sort(int vetor[], int tam){ //função chamada insert_sort com os parametros int vetor e int tamanho
  int troca; // variavel chamada troca

  //Percorre todo o vetor
  for(int i = 1; i < tam; i++){ //laço de repetição para percorrer todo o vetor
    int proximo = i; //variavel chamada proximo inicializada com 'i'

    //Responsável pelas trocas
    //laço de repetição com uma condição que tem que comprir as duas para ser executada, a variavel proximo não pode ser igual a 0 e o vetor na posição i tem que ser menor que o i-1
    while((proximo != 0) && (vetor[proximo] < vetor[proximo - 1])){
      troca = vetor[proximo]; //guardando o valor do vetor na posição i na variavel troca
      vetor[proximo] = vetor[proximo - 1]; //vetor na posição i recebendo valor do vetor na posição i - 1
      vetor[proximo - 1] = troca; // vetor na posição i - 1 recebendo o valor da variavel troca
      proximo--; //variavel proximo decrementando em 1
    }
  }
}

int main() {
   int vetor[6] = {8, 3, 1, 42, 12, 5}; //inicializando um vetor com 6 numeros fora de ordem

   //Aplicando a ordenação;
   insert_sort(vetor, 6); //aplicando a função com a variavel vetor e seu tamanho

   //Apresentando o vetor ordenado
   for(int i = 0; i < 6; i++){ //usando o laço de repetição for para percorrrer o vetor
	   printf("%d\n", vetor[i]); //imprimindo o vetor completo em ordem crescente
   }

}
