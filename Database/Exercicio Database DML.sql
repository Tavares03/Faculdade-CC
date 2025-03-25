CREATE DATABASE BibliotecaDB;
USE BibliotecaDB;

CREATE TABLE Autores (
    id_autor INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    sobrenome VARCHAR(50) NOT NULL,
    nacionalidade VARCHAR(30)
);

CREATE TABLE Categorias (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL
);

CREATE TABLE Livros (
    id_livro INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    ano_publicacao YEAR,
    id_autor INT,
    id_categoria INT,
    CONSTRAINT fk_livro_autor FOREIGN KEY (id_autor) REFERENCES Autores(id_autor),
    CONSTRAINT fk_livro_categoria FOREIGN KEY (id_categoria) REFERENCES Categorias(id_categoria)
);

INSERT INTO Autores (nome, sobrenome, nacionalidade) values
('João', 'Silva', 'Brasileiro'),
('Maria', 'Oliveira', 'Portuguesa'),
('Carlos', 'Santos', 'Brasileiro'),
('Ana', 'Pereira', 'Espanhola'),
('Pedro', 'Gomes', 'Argentino');

SELECT * FROM Autores
WHERE nacionalidade = 'Brasileiro';

UPDATE Autores SET nacionalidade = 'Portuguesa' WHERE id_autor = 4;

DELETE FROM Autores WHERE id_autor = 3;

SELECT nome, sobrenome FROM Autores
WHERE nome LIKE '%a%';

SELECT * FROM Autores;

INSERT INTO Categorias (descricao) values
('Ficção'),
('Não-FIcção'),
('História'),
('Ciência');

SELECT descricao FROM Categorias
WHERE descricao LIKE 'c%';

UPDATE Categorias SET descricao = 'Biografia' WHERE id_categoria = 2;

DELETE FROM Categorias WHERE id_categoria = 1;

SELECT * FROM Categorias;

INSERT INTO Livros (titulo, ano_publicacao, id_autor, id_categoria) values
('O Mistério do Lago', 2015, 1, 1),
('História do Brasil', 2018, 3, 3),
('A Ciência da Vida', 2020, 5, 4),
('Contos de Fadas', 2012, 2, 1),
('Memórias de um Viajantes', 2019, 4, 2),
('A Revolução Industrial', 2017, 3, 3);

SELECT titulo, ano_publicacao FROM Livros
WHERE ano_publicacao >= 2015;

SELECT id_categoria, count(*) FROM Livros
GROUP BY id_categoria;

UPDATE Livros SET ano_publicacao = '2016' WHERE id_livro = 1;

DELETE FROM Livros WHERE id_livro = 5;

SELECT titulo, id_categoria FROM Livros
WHERE id_categoria = 1;

SELECT * FROM Livros;
