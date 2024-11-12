#include <stdio.h>

void bubble_sort(int vetor[], int tam){ //Função chamada bubble_sort com dois parametros, um vetor do tipo inteiro e um tamanho do tipo inteiro
	//variável auxiliar
	int proximo = 0; // foi criada uma variavel do tipo inteiro inicializada em 0.

	//varre todo o vetor externo
	for(int i = 0; i < tam; i++){ //foi criado um laço de repetição para rodar o loop mais uma vez para organizar os numeros
		//trabalha com os próximos elementos
		for(int j = 0; j < (tam - 1); j++){ //laço de repetição para colocar os numeros nos lugares certos em ordem crescente
			//Ocorre a troca
	        if(vetor[j] > vetor[j+1]){ //foi criado para ordenar a cada dois numeros se eles estao na posição certa, se o da esquerda for maior que o da direita o if é executado
	        	proximo = vetor[j]; //guardando o maior numero em uma variavel
	        	vetor[j] = vetor[j+1]; //fazendo o maior numero receber o menor
	        	vetor[j+1]=proximo; //o menor numero recebendo o maior
	        }
	    }
	 }
}

int main() {
   int vetor[6] = {8, 3, 1, 42, 12, 5}; //vetor com 6 numeros inicializados fora de ordem

   //Aplicando a ordenação;
   bubble_sort(vetor, 6); //executando a função com o vetor e o tamanho 6

   //Apresentando o vetor ordenado
   for(int i = 0; i < 6; i++){ //laço de repetição para imprimir o vetor inteiro em ordem
	   printf("%d\n", vetor[i]); //imprimindo o vetor inteiro
   }

}
