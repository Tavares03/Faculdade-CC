#include <ctype.h>
#include <stdio.h>
#include <string.h>

typedef enum {
    E0,
    E1,
    E2,
    E3,
    E4,
    E5,
    E6,
    E7,
    X
} State;

static int is_final(State s) {
    return (s == E3 || s == E4 || s == E7);
}

static State delta(State s, char c) {
    int d = isdigit((unsigned char)c);

    switch (s) {
        case E0:
            if (c == '+' || c == '-') return E1;
            if (c == '.') return E2;
            if (d) return E3;
            return X;

        case E1:
            if (c == '.') return E2;
            if (d) return E3;
            return X;

        case E2:
            if (d) return E4;
            return X;

        case E3:
            if (d) return E3;
            if (c == '.') return E4;
            if (c == 'E') return E5;
            return X;

        case E4:
            if (d) return E4;
            if (c == 'E') return E5;
            return X;

        case E5:
            if (c == '+' || c == '-') return E6;
            if (d) return E7;
            return X;

        case E6:
            if (d) return E7;
            return X;

        case E7:
            if (d) return E7;
            return X;

        case X:
        default:
            return X;
    }
}

static int aceita_lexema(const char *lexema) {
    State s = E0;

    for (size_t i = 0; lexema[i] != '\0'; i++) {
        s = delta(s, lexema[i]);
        if (s == X) return 0;
    }

    return is_final(s);
}

int main(void) {
    char line[4096];

    if (!fgets(line, (int)sizeof(line), stdin)) {
        return 0;
    }

    line[strcspn(line, "\r\n")] = '\0';

    const char *delim = " \t";
    char *context = NULL;
    char *token = strtok_s(line, delim, &context);
    while (token) {
        printf("%s -> %s\n", token, aceita_lexema(token) ? "ACEITO" : "REJEITADO");
        token = strtok_s(NULL, delim, &context);
    }

    return 0;
}
