#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include "ts.h"

int linha = 1, coluna = 0;

// ================= PALAVRAS RESERVADAS =================
typedef struct {
    char *lexema;
    char *token;
} Keyword;

Keyword keywords[] = {
    {"program", "KW_PROGRAM"},
    {"var", "KW_VAR"},
    {"integer", "KW_INTEGER"},
    {"real", "KW_REAL"},
    {"begin", "KW_BEGIN"},
    {"end", "KW_END"},
    {"if", "KW_IF"},
    {"then", "KW_THEN"},
    {"else", "KW_ELSE"},
    {"while", "KW_WHILE"},
    {"do", "KW_DO"}
};

int isKeyword(char *lexema, char *token) {
    for(int i=0;i<11;i++) {
        if(strcmp(keywords[i].lexema, lexema)==0) {
            strcpy(token, keywords[i].token);
            return 1;
        }
    }
    return 0;
}

// ================= ERRO =================
void erro(FILE *err, char *msg) {
    fprintf(err,"Erro: %s (%d:%d)\n", msg, linha, coluna);
}

// ================= ANALISADOR =================
void analisar(char *arquivo) {

    FILE *f = fopen(arquivo,"r");
    FILE *lex = fopen("saida.lex","w");
    FILE *err = fopen("saida.err","w");

    char c;

    while((c=fgetc(f))!=EOF) {
        coluna++;

        // ESPAÇOS
        if(c==' ' || c=='\t') continue;

        if(c=='\n') {
            linha++;
            coluna=0;
            continue;
        }

        // ================= COMENTÁRIO =================
        if(c=='{') {
            while((c=fgetc(f))!=EOF && c!='}') {
                if(c=='\n') linha++;
            }
            if(c==EOF) erro(err,"Comentario nao fechado");
            continue;
        }

        // ================= IDENTIFICADOR =================
        if(isalpha(c)) {
            char lexema[100];
            int i=0;

            lexema[i++] = tolower(c);

            while(isalnum(c=fgetc(f))) {
                lexema[i++] = tolower(c);
                coluna++;
            }

            lexema[i]='\0';
            ungetc(c,f);

            char token[50];

            if(isKeyword(lexema, token)) {
                fprintf(lex,"<%s, %s> %d %d\n", token, lexema, linha, coluna);
            } else {
                fprintf(lex,"<ID, %s> %d %d\n", lexema, linha, coluna);
                addTS(lexema,"ID");
            }

            continue;
        }

        // ================= NÚMEROS =================
        if(isdigit(c)) {
            char lexema[100];
            int i=0;

            lexema[i++] = c;

            while(isdigit(c=fgetc(f))) {
                lexema[i++] = c;
                coluna++;
            }

            if(c=='.') {
                lexema[i++] = c;

                if(!isdigit(c=fgetc(f))) {
                    erro(err,"Numero real mal formado");
                }

                while(isdigit(c)) {
                    lexema[i++] = c;
                    c=fgetc(f);
                    coluna++;
                }

                lexema[i]='\0';
                fprintf(lex,"<NUM_REAL, %s> %d %d\n", lexema, linha, coluna);

            } else {
                lexema[i]='\0';
                fprintf(lex,"<NUM_INT, %s> %d %d\n", lexema, linha, coluna);
            }

            ungetc(c,f);
            continue;
        }

        // ================= OPERADORES =================
        switch(c) {

            case '=':
                fprintf(lex,"<OP_EQ, => %d %d\n",linha,coluna);
                break;

            case '<':
                c=fgetc(f);
                if(c=='=') fprintf(lex,"<OP_LE, <=> %d %d\n",linha,coluna);
                else if(c=='>') fprintf(lex,"<OP_NE, <>> %d %d\n",linha,coluna);
                else {
                    ungetc(c,f);
                    fprintf(lex,"<OP_LT, <> %d %d\n",linha,coluna);
                }
                break;

            case '>':
                c=fgetc(f);
                if(c=='=') fprintf(lex,"<OP_GE, >=> %d %d\n",linha,coluna);
                else {
                    ungetc(c,f);
                    fprintf(lex,"<OP_GT, >> %d %d\n",linha,coluna);
                }
                break;

            case '+': fprintf(lex,"<OP_AD, +> %d %d\n",linha,coluna); break;
            case '-': fprintf(lex,"<OP_MIN, -> %d %d\n",linha,coluna); break;
            case '*': fprintf(lex,"<OP_MUL, *> %d %d\n",linha,coluna); break;
            case '/': fprintf(lex,"<OP_DIV, /> %d %d\n",linha,coluna); break;

            case ':':
                c=fgetc(f);
                if(c=='=') fprintf(lex,"<OP_ASS, :=> %d %d\n",linha,coluna);
                else {
                    ungetc(c,f);
                    fprintf(lex,"<SMB_COL, :> %d %d\n",linha,coluna);
                }
                break;

            case ';': fprintf(lex,"<SMB_SEM, ;> %d %d\n",linha,coluna); break;
            case ',': fprintf(lex,"<SMB_COM, ,> %d %d\n",linha,coluna); break;
            case '(': fprintf(lex,"<SMB_OPA, (> %d %d\n",linha,coluna); break;
            case ')': fprintf(lex,"<SMB_CPA, )> %d %d\n",linha,coluna); break;
            case '.': fprintf(lex,"<SMB_DOT, .> %d %d\n",linha,coluna); break;

            default:
                erro(err,"Caractere invalido");
        }
    }

    fclose(f);
    fclose(lex);
    fclose(err);
}