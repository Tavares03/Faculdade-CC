CREATE DATABASE db_venda_veiculo;
USE db_venda_veiculo;

CREATE TABLE Funcionario (
    id_funcionario INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    salario DECIMAL(10, 2) NOT NULL DEFAULT 0,
    departamento VARCHAR(50) NOT NULL
);

CREATE TABLE Veiculo (
    id_veiculo INT PRIMARY KEY AUTO_INCREMENT,
    marca VARCHAR(50) NOT NULL,
    placa VARCHAR(10) NOT NULL UNIQUE,
    modelo VARCHAR(50) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    ano_fabricacao INT NOT NULL CHECK (ano_fabricacao >= 1900),
    status ENUM('Disponível', 'Vendido') NOT NULL DEFAULT 'Disponível'
);


CREATE TABLE Cliente (
    id_cliente INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    cnh VARCHAR(20) NOT NULL UNIQUE,
    placa_veiculo VARCHAR(10),  
    tipo_cliente ENUM('Física', 'Jurídica') NOT NULL,
    cartao_pagamento VARCHAR(50) NOT NULL
);


CREATE TABLE Venda (
    id_venda INT PRIMARY KEY AUTO_INCREMENT,
    valor_venda DECIMAL(10, 2) NOT NULL,
    data_venda DATE NOT NULL,
    id_veiculo INT NOT NULL UNIQUE,  
    id_funcionario INT NOT NULL,
    id_cliente INT NOT NULL,
    CONSTRAINT fk_veiculo_venda FOREIGN KEY (id_veiculo) REFERENCES Veiculo (id_veiculo) ON DELETE RESTRICT,
    CONSTRAINT fk_funcionario_venda FOREIGN KEY (id_funcionario) REFERENCES Funcionario (id_funcionario) ON DELETE RESTRICT,
    CONSTRAINT fk_cliente_venda FOREIGN KEY (id_cliente) REFERENCES Cliente (id_cliente) ON DELETE RESTRICT
);

INSERT INTO Cliente (nome, cnh, tipo_cliente, cartao_pagamento) VALUES
('João Silva', '12345678901', 'Física', '4111111111111111'),
('Empresa X', '12345678920', 'Jurídica', '5222222222222222'),
('Carlos', '98765432100', 'Física', '6333344444444444'),
('Pedro', '98765432101', 'Física', '6333333333333335'),
('Ricardo', '98765432102', 'Física', '6333333333333337');

INSERT INTO Funcionario (nome, salario, departamento) VALUES
('Carlos Souza', 2500.00, 'Vendas'),
('Ana Costa', 3000.00, 'Administração'),
('Pedro Lima', 2800.00, 'Vendas'),
('William Roberto', 30000.00, 'RH'),
('Paulo Castro', 5000.00, 'Estoque');

INSERT INTO Veiculo (marca, placa, valor, modelo, ano_fabricacao) VALUES
('Ford', 'DEF1234', 65000, 'Fiesta', 2018),
('Toyota', 'XCV3456', 137000, 'Corolla', 2020),
('Chevrolet', 'LMO4321', 55000, 'Onix', 2019);


INSERT INTO Venda (valor_venda, data_venda, id_veiculo, id_cliente, id_funcionario) VALUES
(45000.00, '2024-01-15', 1, 1, 1),
(75000.00, '2024-02-20', 2, 2, 2),
(50000.00, '2024-03-10', 3, 3, 3);


SELECT * FROM Cliente;
SELECT * FROM Funcionario;
SELECT * FROM Veiculo;
SELECT * FROM Venda;

SELECT 
    Veiculo.marca, Veiculo.modelo, Veiculo.ano_fabricacao, Veiculo.placa,
    Venda.valor_venda, Venda.data_venda,
    Cliente.nome AS nome_cliente,
    Funcionario.nome AS nome_funcionario
FROM Veiculo
INNER JOIN Venda ON Veiculo.id_veiculo = Venda.id_veiculo
INNER JOIN Cliente ON Venda.id_cliente = Cliente.id_cliente
INNER JOIN Funcionario ON Venda.id_funcionario = Funcionario.id_funcionario;

SELECT
	Venda.id_venda, Venda.valor_venda, data_venda,
	Cliente.nome AS nome_cliente, 
    Funcionario.nome AS nome_funcionario
FROM Venda
INNER JOIN Cliente ON Venda.id_cliente = Cliente.id_cliente
INNER JOIN Funcionario ON Venda.id_funcionario = Funcionario.id_funcionario;

SELECT
	Cliente.nome AS nome_cliente,
	Veiculo.modelo
FROM Veiculo
INNER JOIN Cliente ON Cliente.id_cliente = Cliente.id_cliente;

select avg(valor_venda) from venda;

CREATE VIEW media_vendas AS
SELECT
    ROUND(AVG(valor_venda), 2) AS media_valor_venda,
    COUNT(*) AS total_vendas
FROM Venda;

select * from media_vendas;

CREATE VIEW total_vendas_por_funcionario AS
SELECT
	id_funcionario,
    COUNT(*) total_vendas
FROM Venda
GROUP BY id_funcionario;

SELECT * FROM total_vendas_por_funcionario;


CREATE VIEW media_vendas_por_funcionario AS
SELECT
    id_funcionario,
    ROUND(AVG(valor_venda), 2) AS media_valor_venda,
    COUNT(*) AS total_vendas
FROM Venda
GROUP BY id_funcionario;

SELECT * FROM media_vendas_por_funcionario;


CREATE VIEW media_vendas_por_nome_funcionario AS
SELECT
    f.nome AS nome_funcionario,
    ROUND(AVG(v.valor_venda), 2) AS media_valor_venda,
    COUNT(*) AS total_vendas
FROM Venda v
JOIN Funcionario f ON v.id_funcionario = f.id_funcionario
GROUP BY f.nome;

SELECT * FROM media_vendas_por_nome_funcionario;

CREATE VIEW media_vendas_por_funcionarios_ordenada AS
SELECT
	f.nome,
    AVG(v.valor_venda) AS media_valor,
    COUNT(*) AS total_vendas
FROM Venda v
JOIN Funcionario f ON v.id_funcionario = f.id_funcionario
GROUP BY f.nome
ORDER BY media_valor DESC;

SELECT * FROM media_vendas_por_funcionarios_ordenada;

CREATE VIEW media_vendas_por_funcionarios_por_periodo AS
SELECT
	f.nome,
    AVG(v.valor_venda) AS media_valor,
    COUNT(*) AS total_vendas
FROM Venda v
JOIN Funcionario f ON v.id_funcionario = f.id_funcionario
WHERE v.data_venda >= '2024-01-01'
GROUP BY f.nome;

SELECT
	MAX(valor_venda) AS maior_valor,
    MIN(valor_venda) AS menor_valor
FROM Venda;

SELECT
	id_funcionario,
    COUNT(*) AS total_vendas
FROM Venda
GROUP BY id_funcionario;

SELECT
	v.ano_fabricacao,
    COUNT(*) AS total_vendidos
FROM Venda vd
JOIN Veiculo v ON vd.id_veiculo = v.id_veiculo
GROUP BY v.ano_fabricacao
ORDER BY v.ano_fabricacao;

UPDATE Veiculo SET valor = 60000 WHERE placa = 'DEF1234';

UPDATE Veiculo SET status = 'Vendido' WHERE id_veiculo = 3;

SELECT 
    Veiculo.marca, Veiculo.modelo, Veiculo.ano_fabricacao, Veiculo.placa,
    Venda.valor_venda, Venda.data_venda,
    Cliente.nome AS nome_cliente,
    Funcionario.nome AS nome_funcionario
FROM Veiculo
INNER JOIN Venda ON Veiculo.id_veiculo = Venda.id_veiculo
INNER JOIN Cliente ON Venda.id_cliente = Cliente.id_cliente
INNER JOIN Funcionario ON Venda.id_funcionario = Funcionario.id_funcionario
WHERE Venda.data_venda > '2024-09-01';

DELIMITER $$

CREATE PROCEDURE inserir_clientes()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= 50 DO
        INSERT INTO Cliente (nome, cnh, tipo_cliente, cartao_pagamento)
        VALUES (
            CONCAT('Cliente ', i), 
            CONCAT('CNH', LPAD(i, 10, '0')), 
            IF(i % 2 = 0, 'Física', 'Jurídica'),
            LPAD(FLOOR(RAND() * 10000000000000000), 16, '0') 
        );
        SET i = i + 1;
    END WHILE;
END $$

DELIMITER ;

CALL inserir_clientes();

SELECT * FROM Veiculo
WHERE ano_fabricacao >= '2020';

SELECT * FROM Cliente
WHERE nome LIKE 'c%';

SELECT * FROM Funcionario
WHERE nome LIKE '%silva%';

SELECT * FROM Venda
WHERE data_venda >= '2024-03-01';

DELIMITER $$

CREATE PROCEDURE inserir_vendas()
BEGIN
	DECLARE i INT DEFAULT 1;
    DECLARE v_id_veiculo INT;
    DECLARE v_id_cliente INT;
    DECLARE v_id_funcionario INT;
    DECLARE v_valor_venda DECIMAL(10,2);
    DECLARE v_data_venda DATE;
    
    WHILE i <= 10 DO
		SELECT id_veiculo INTO v_id_veiculo
        FROM Veiculo
        WHERE status = 'Disponível'
        ORDER BY RAND()
        LIMIT 1;
        
        SELECT id_cliente INTO v_id_cliente
        FROM Cliente
        ORDER BY RAND()
        LIMIT 1;
        
        SELECT id_funcionario INTO v_id_funcionario
        FROM Funcionario
        ORDER BY RAND()
        LIMIT 1;
        
        SET v_valor_venda = ROUND(RAND() * 50000 + 30000, 2);
        SET v_data_venda = DATE_ADD('2024-01-01', INTERVAL FLOOR(RAND() * 365) DAY);
        
        UPDATE Veiculo SET status = 'Vendido' WHERE id_veiculo = v_id_veiculo;
        
        INSERT INTO Venda (valor_venda, data_venda, id_veiculo, id_funcionario, id_cliente)
        VALUES (v_valor_venda, v_data_venda, v_id_veiculo, v_id_funcionario, v_id_cliente);
		
        SET i = i + 1;
	END WHILE;
END $$;

DELIMITER ;

CALL inserir_vendas();

SELECT 
    c.nome AS nome_cliente,
    f.nome AS nome_funcionario,
    v.modelo AS modelo_veiculo,
    vd.valor_venda
FROM Venda vd
JOIN Cliente c ON vd.id_cliente = c.id_cliente
JOIN Funcionario f ON vd.id_funcionario = f.id_funcionario
JOIN Veiculo v ON vd.id_veiculo = v.id_veiculo
WHERE MONTH(vd.data_venda) = MONTH(CURDATE())
  AND YEAR(vd.data_venda) = YEAR(CURDATE());
  
  CREATE OR REPLACE VIEW ranking_funcionarios_vendas AS
SELECT 
    f.id_funcionario,
    f.nome AS nome_funcionario,
    COUNT(vd.id_venda) AS total_vendas
FROM Funcionario f
LEFT JOIN Venda vd ON f.id_funcionario = vd.id_funcionario
GROUP BY f.id_funcionario
ORDER BY total_vendas DESC;

SELECT * FROM ranking_funcionarios_vendas;

SELECT 
    COUNT(DISTINCT vd.id_cliente) AS total_clientes_juridicos
FROM Venda vd
JOIN Cliente c ON vd.id_cliente = c.id_cliente
WHERE c.tipo_cliente = 'Jurídica'
  AND vd.data_venda > '2024-01-01';
