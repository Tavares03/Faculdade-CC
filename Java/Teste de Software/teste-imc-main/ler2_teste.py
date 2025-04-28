import unittest
from ler2 import dados, IdadeIncorreta

class IMCTest(unittest.TestCase):

    def test_meninas(self):
        menina1 = dados("Menina", '2019-02-01', 'Femi', 10, 1.0)
        self.assertEqual(menina1.getTarget(), 0)

        menina2 = dados("Menina2", '2018-02-01', 'Femi', 15, 1.1)
        self.assertIn(menina2.getTarget(), [0, 1, 2, 3])

        menina3 = dados("Menina3", '2017-02-01', 'Femi', 22, 1.2)
        self.assertIn(menina3.getTarget(), [0, 1, 2, 3])

    def test_meninos(self):
        menino1 = dados("Menino", '2019-02-01', 'Masc', 16, 1.05)
        self.assertIn(menino1.getTarget(), [0, 1, 2, 3])

        menino2 = dados("Menino2", '2018-02-01', 'Masc', 11, 1.1)
        self.assertIn(menino2.getTarget(), [0, 1, 2, 3])

        menino3 = dados("Menino3", '2017-02-01', 'Masc', 20, 1.2)
        self.assertIn(menino3.getTarget(), [0, 1, 2, 3])

    def test_altura_zero_erro(self):
        menino = dados("erroAltura", '2018-02-01', 'Masc', 30, 0)
        with self.assertRaises(ValueError) as context:
            menino.getTarget()
        self.assertEqual(str(context.exception), "altura tem que ser maior que o numero 0")

    def test_idade_incorreta(self):
        adulto = dados("Adulto", "2010-01-01", "Masc", 70, 1.75)
        with self.assertRaises(IdadeIncorreta) as context:
            adulto.getTarget()
        self.assertIn("A idade", str(context.exception))

    def test_str_saida(self):
        pessoa = dados("Teste", "2019-02-01", "Femi", 12, 1.0)
        output = str(pessoa)
        self.assertIn("Nome: Teste", output)
        self.assertIn("Gênero: Femi", output)
        self.assertIn("Peso: 12", output)
        self.assertIn("Altura: 1.0", output)
        self.assertIn("Interpretação IMC", output)

    def test_calc_idade(self):
        pessoa = dados("idadeTeste", "2017-01-01", "Masc", 20, 1.2)
        idade = pessoa.calcIdade()
        self.assertTrue(idade >= 7)  

if __name__ == '__main__':
    unittest.main()
