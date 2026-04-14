#ifndef UTILS_H
#define UTILS_H

#include <ctype.h>

int isLetter(char c) {
    return isalpha(c);
}

int isDigit(char c) {
    return isdigit(c);
}

#endif