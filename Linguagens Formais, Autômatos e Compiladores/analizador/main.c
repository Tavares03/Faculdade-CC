#include <stdio.h>
#include "lexer.h"
#include "ts.h"

int main() {

    initTS();

    analisar("entrada.pas");

    printTS();

    printf("Analise concluida!\n");

    return 0;
}