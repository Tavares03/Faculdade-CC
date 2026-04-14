#include <stdio.h>
#include <string.h>
#include "ts.h"

Simbolo tabela[MAX_TS];
int ts_size = 0;

void initTS() {

    char *keywords[] = {
        "program","var","integer","real",
        "begin","end","if","then",
        "else","while","do"
    };

    for(int i=0;i<11;i++) {
        strcpy(tabela[ts_size].lexema, keywords[i]);
        strcpy(tabela[ts_size].tipo, "KW");
        ts_size++;
    }
}

int exists(char *lex) {
    for(int i=0;i<ts_size;i++) {
        if(strcmp(tabela[i].lexema, lex)==0)
            return 1;
    }
    return 0;
}

void addTS(char *lexema, char *tipo) {
    if(!exists(lexema)) {
        strcpy(tabela[ts_size].lexema, lexema);
        strcpy(tabela[ts_size].tipo, tipo);
        ts_size++;
    }
}

void printTS() {
    FILE *f = fopen("saida.ts","w");
    for(int i=0;i<ts_size;i++) {
        fprintf(f,"%s - %s\n", tabela[i].lexema, tabela[i].tipo);
    }
    fclose(f);
}