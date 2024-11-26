#include <stdio.h>
#include <conio.h>
#include <stdlib.h>

typedef struct {
    int dificuldade;
    int valor1;
    int valor2;
    int operacao;
    int resultado;
}Calcular;

//Função de soma
int soma(int valor1, int valor2){
    return valor1+valor2;
}

//Função de Subtração
int subtracao(int valor1, int valor2){
    return valor1-valor2;
}

//Função de Multiplicação
int multiplicacao(int valor1, int valor2){
    return valor1*valor2;
} 

//Função de Divisão
int divisao(int valor1, int valor2){
    if (valor1 == 0){
        return 0;
        printf("Operacao Invalida");
    } else {
        return valor1/valor2;
    }
}

int calcular(int valor1, int valor2, int num, int p){
	
	int resultado = 0;
	printf("Insira o resultado:\n");
	scanf("%d", &resultado);
	switch(num)
	{
		case 0:
		 if(resultado == soma(valor1,valor2)){
		    printf("Parabens, Voce acertou!!!");
            p += 10;
            printf("%d", p);
		 } else {
		    printf("voce errou, contineu tentando!");
            p -= 10;
		}
		break;
		
		case 1:
		 if(resultado==subtracao(valor1,valor2)){
		 	printf("Parabens, Voce acertou!!!\n");
            p += 10;
		 } else {
		 	printf("Voce errou, continue tentando!\n");
            p -= 10;
		 }
		break;
		
		case 2:
		if(resultado==multiplicacao(valor1,valor2)){
		 	printf("Parabens, Voce acertou!!!\n");
		    p += 10;
		 } else {
		 	printf("Voce errou, continue tentando!\n");
            p -= 10;
		 }
		break;
		
		case 3:
		if(resultado==divisao(valor1,valor2)){
		 	printf("Parabens, Voce acertou!!!\n");
            p += 10;
		 } else {
		 	printf("Voce errou, continue tentando!\n");
            p -= 10;
		 }
		break;
	}

    return p;
}

int aleatoriedade(int valor1, int valor2, int p){
    int num, pontuacao;
    num = rand() %3;
    switch (num)
    {
        case 0:
            printf("Questao de Soma: %d + %d\n", valor1, valor2);
            return calcular(valor1, valor2, num, p);
        break;

        case 1:
            printf("Questao de Subtracao: %d - %d\n", valor1, valor2);
            return calcular(valor1, valor2, num, p);
        break;
    
        case 2:
            printf("Questao de Multiplicacao: %d * %d\n", valor1, valor2);
            return calcular(valor1, valor2, num, p);
        break;

        case 3:
            printf("Questao de Divisao: %d / %d\n", valor1, valor2);
            return calcular(valor1, valor2, num, p);
        break;


    default:
        break;
    }
}

void jogar(){  
    int dificuldade = 0;
    int valor1, valor2;
    int p = 0;
    int f = 0;

    while (f == 0){

    for (int i = 0; i < 3; i++){

        printf("Escolha a dificuldade desejada:\n");
        printf("(1)facil\n(2)medio\n(3)dificil\n(4)insano\n");
        scanf("%d", &dificuldade);

    switch (dificuldade){
    case 1:
        printf("Dificuldade facil escolhida\n");
        valor1 = rand() %10;
        valor2 = rand() %10;
        p = aleatoriedade(valor1, valor2, p);
        break;
    
    case 2:
        printf("Dificuldade medio escolhidada\n");
        valor1 = rand() %100;
        valor2 = rand() %100;
        p = aleatoriedade(valor1, valor2, p);
        break;

    case 3:
        printf("DIficuldade dificil escolhida\n");
        valor1 = rand() %1000;
        valor2 = rand() %1000;
        p = aleatoriedade(valor1, valor2, p);
        break;

    case 4:
        printf("Dificuldade insano escolhida\n");
        valor1 = rand() %10000;
        valor2 = rand() %10000;
        p = aleatoriedade(valor1, valor2, p);
        break;

    default:
        printf("Opcao invalida, tente novamente\n");
        break;
    }

    }
    printf("Deseja sair?(0) nao (1) sim\n");
    scanf("%d", &f);
    if (f == 1)
    {
        printf("\nSaindo do jogo\n");
        printf("Pontuacao: %d", p);
    }
}
}

int main(){

    jogar();

    return 0;
}
