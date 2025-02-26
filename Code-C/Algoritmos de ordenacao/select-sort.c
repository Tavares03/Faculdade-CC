#include <stdio.h>

void select_sort(int vetor[], int tam){ // função chamada select_sort com dois parametros, vetor do tippo inteiro e variavel tamanho do tipo inteiro
  int menor, troca; // duas variaveis do tipo inteiro, menor e troca
  //Loop externo para repassar todo vetor
  for(int i = 0; i < (tam-1); i++){ //laço de repetição para rodar o vetor
	//variável para acompanhar o loop for pegando sempre o menor elemento
	menor = i;
	//Loop interno para trabalhar com o próximo elemento
    for(int j = (i+1); j < tam; j++){
      if(vetor[j] < vetor[menor]){ //se o vetor na posição j for menor que o vetor na posição i, o if sera executado
        menor = j; // variavel menor recebe j
      }
    }
    //Onde ocorre a troca
    if(i != menor){ // se o i for diferente de menor o if sera executado
      troca = vetor[i]; // valor do vetor na posição i é guardado na variavel troca
      vetor[i] = vetor[menor]; // vetor na posição i recebendo o valor do vetor na posição menor
      vetor[menor] = troca; //vetor na posição menor recebendo o valor da variavel troca
    }
  }
}

int main() {
   int vetor[6] = {8, 3, 1, 42, 12, 5}; // vetor inicializado com 6 numeros fora de ordem

   //Aplicando a ordenação;
   select_sort(vetor, 6); // usando a função com a variavel vetor e seu tamanho 6

   //Apresentando o vetor ordenado
   for(int i = 0; i < 6; i++){ //laço de repetição para rodar o vetor
	   printf("%d\n", vetor[i]); // imprimindo o vetor inteiro em ordem
   }

}
