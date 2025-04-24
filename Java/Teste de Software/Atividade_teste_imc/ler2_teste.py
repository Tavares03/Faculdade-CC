import unittest

from ler2 import dados

class IMCTest(unittest.TestCase):

    def test_meninas(self):
        menina6 = dados("Menina", '2019-02-01', 'Femi', 12, 1.0)
        self.assertEqual(0, menina6.getTarget())

        menina18 = dados("Adulta", "2006-11-12", 'Femi', 50, 1.65)
        with self.assertRaises(ArithmeticError):
            menina18.getTarget()


if __name__ == '__main__':
    unittest.main()