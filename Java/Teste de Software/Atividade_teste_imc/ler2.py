import struct
from datetime import datetime

class dados():
    def __init__(self, nome:str, nasc:str, genero:str, peso:float, altura:float):
        self.nome = nome
        self.nasc = nasc
        self.genero = genero
        self.peso = peso
        self.altura = altura
    
    def calcIdade(self):
        n = datetime.strptime(self.nasc, "%Y-%m-%d")
        return (datetime.now() - n).days // 365

    def getTarget(self):
        idade = self.calcIdade()
        imc = self.peso / (self.altura**2)
        if self.genero == 'Masc':
            if idade == 6:
                if imc < 14.5:
                    return 0
                elif imc < 16.7:
                    return 1
                elif imc < 18:
                    return 2
                else:
                    return 3
            elif idade == 7:
                if imc < 15:
                    return 0
                elif imc < 17.4:
                    return 1
                elif imc < 19.1:
                    return 2
                else:
                    return 3
            elif idade == 8:
                if imc < 15.6:
                    return 0
                elif imc < 16.8:
                    return 1
                elif imc < 20.3:
                    return 2
                else:
                    return 3
        elif self.genero == 'Femi':
            if idade == 6:
                if imc < 14.3:
                    return 0
                elif imc < 16.2:
                    return 1
                elif imc < 17.4:
                    return 2
                else:
                    return 3
            elif idade == 7:
                if imc < 14.9:
                    return 0
                elif imc < 17.2:
                    return 1
                elif imc < 18.9:
                    return 2
                else:
                    return 3
            elif idade == 8:
                if imc < 15.6:
                    return 0
                elif imc < 18.2:
                    return 1
                elif imc < 20.3:
                    return 2
                else:
                    return 3
        raise ArithmeticError("Valores inválidos")

    def __str__(self) -> str:
        print(f"Nome: {self.nome}")
        print(f"Data de Nascimento: {self.nasc}")
        print(f"Gênero: {self.genero}")
        print(f"Idade: {self.calcIdade()}")
        print(f"Peso: {self.peso} Kg")
        print(f"Altura: {self.altura} m")
        print(f"Interpretação IMC: {self.getTarget}")


def ler_dados(arq):

    form = '30s 11s 4s f f i'

    tam = struct.calcsize(form)

    with open(arq, 'rb') as f:
        while True:
            record_data = f.read(tam)
            if not record_data:
                break
            record = struct.unpack(form, record_data)
            string1 = record[0].decode().strip('\x00')
            string2 = record[1].decode().strip('\x00')
            string3 = record[2].decode().strip('\x00')
            float1 = record[3]
            float2 = record[4]
            integer = record[5]
            dado = dados(string1, string2, string3, float1, float2)
            print(dado)

if __name__ == '__main__':
    ler_dados('dados.bin')