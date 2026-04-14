# 🔍 Analisador Léxico - MicroPascal
## 📌 Descrição

Este projeto consiste na implementação de um analisador léxico para a linguagem MicroPascal, desenvolvido em C.

O analisador é responsável por ler um arquivo fonte (entrada.pas) e transformar o código em uma sequência de tokens, além de identificar erros léxicos e manter uma tabela de símbolos.

## 🎯 Objetivo
Reconhecer tokens da linguagem MicroPascal
Identificar erros léxicos
Gerar arquivos de saída com os resultados da análise
Simular o funcionamento da primeira etapa de um compilador

## ⚙️ Tecnologias utilizadas
Linguagem C
Compilador GCC (MinGW no Windows)

## 📁 Estrutura do projeto
```
analisador/
│── main.c        # Função principal
│── lexer.c       # Analisador léxico
│── lexer.h
│── ts.c          # Tabela de símbolos
│── ts.h
│── utils.h
│── entrada.pas   # Arquivo de entrada
```

# ▶️ Como compilar e executar

## 🔧 Compilação
```gcc main.c lexer.c ts.c -o analisador -mconsole```

## ▶️ Execução
```.\analisador.exe```

## 📥 Entrada

O programa lê um arquivo chamado:

```entrada.pas```

### Exemplo:
```
program teste;
var
   x : integer;
begin
   x := 10;
end.
```
## 📤 Saídas geradas

Após a execução, o programa gera:

### 📄 saida.lex

Lista de tokens reconhecidos:
```
<KW_PROGRAM, program> 1 1
<ID, teste> 1 9
<SMB_SEM, ;> 1 14
```

### 📄 saida.ts

Tabela de símbolos:
```
program - KW
teste - ID
x - ID
```

### 📄 saida.err

Lista de erros léxicos (se houver):

Erro: Caractere invalido (5:10)
🧠 Funcionamento do código

O analisador percorre o arquivo caractere por caractere e identifica padrões da linguagem.

🔹 Identificadores e palavras-chave
Iniciam com letra
Podem conter letras e números
São comparados com palavras reservadas

Exemplo:

if(isalpha(c)) {
🔹 Números
Inteiros → 10
Reais → 2.5

Validação de erro:

10.
🔹 Operadores

Reconhece:

Aritméticos: + - * /
Relacionais: < > =
Compostos: <= >= <> :=

🔹 Comentários
Delimitados por { e }
Ignorados pelo analisador

🔹 Tratamento de erros

Detecta:

Caracteres inválidos
Comentários não fechados
Números mal formados

## 🗂️ Tabela de símbolos

Armazena:

Palavras-chave
Identificadores

Regras:

Não permite duplicatas
Case insensitive

### 🔷 Autômato Finito Determinístico (AFD)

O analisador foi baseado em um AFD com estados como:

Inicial
Identificador
Número inteiro
Número real
Operador
Comentário
Erro

## 🧪 Exemplos de testes
✅ Entrada válida
x := 10;
❌ Entrada com erro
x := 10 @ 2;

## ✏️ Exemplo de teste (coloca isso no entrada.pas):
```
program teste;
var
   x, y : integer;
   z : real;

begin
   x := 10;
   y := 20;
   z := x + y * 2.5;

   if x > y then
      x := x - 1
   else
      y := y + 1;

   while z <= 100 do
   begin
      z := z * 1.5;
      x := x + 2
   end
end.
```

## 📌 Conclusão

O projeto implementa com sucesso um analisador léxico funcional, capaz de reconhecer tokens, identificar erros e organizar informações em uma tabela de símbolos.

A estrutura modular e o uso de AFD garantem eficiência e clareza no processamento.