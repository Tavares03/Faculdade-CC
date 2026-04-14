#ifndef TS_H
#define TS_H

#define MAX_TS 1000

typedef struct {
    char lexema[100];
    char tipo[50];
} Simbolo;

void initTS();
void addTS(char *lexema, char *tipo);
void printTS();

#endif