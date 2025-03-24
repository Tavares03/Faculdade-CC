create database db_venda_veiculo;
use db_venda_veiculo;

create table cliente(
	id_cliente int unsigned auto_increment not null,
    nome varchar(50) not null,
    cnh varchar(20) unique,
    cpf varchar(11) not null,
    tipo_cliente varchar(15) not null,
    check (tipo_cliente in ('Física','Juridica')),
    cartao varchar(16),
    primary key (id_cliente)
);

create table funcionario(
	id_funcionario int unsigned auto_increment not null,
	nome varchar(50) not null,
    salario decimal(10, 2) not null default '0',
    departamento varchar(45) not null,
    primary key (id_funcionario)
);

create table veiculo(
	id_veiculo int unsigned auto_increment not null,
	marca varchar(50) not null,
    placa_veiculo varchar(7) unique not null,
    modelo_veiculo varchar(50) not null,
    valor decimal(10, 2) not null,
    ano_fabricacao int not null check (ano_facbricacao >= 2010),
    status enum ('Disponivel' , 'Vendido') not null default 'Disponivel',
    primary key (id_veiculo)
);

create table venda(
	id_venda int unsigned auto_increment not null,
    valor_venda decimal(10, 2) not null,
    data_venda date not null,
    id_cliente int unsigned not null,
    id_fincionario int unsigned not null,
    id_veiculo int unsigned not null,
    constraint fk_funcionario_venda foreign key (id_funcionario) references funcionario (id_funcionario),
    constraint fk_veiculo_venda foreign key (id_veiculo) references veiculo (id_veiculo),
    constraint fk_cliente_venda foreign key (id_cliente) references cliente (id_cliente),
    primary key (id_venda)
);

insert into cliente (nome, cnh, cpf, tipo_cliente, cartao) values
('Gabriel', '12345678901', '12376892467', 'Física', '4111111111111111'),
('Exmpresa X', '0987654321', '11259733579', 'Juridica', '5222222222222'),
('Adson', '1234512345', '09579240069', 'Física', '63333333333333'),
('Sorriso', '0987609876', '86209529512', 'Juridica', '7444444444444');

insert into funcionario (nome, salario, departamento) values
('Carlos Souza', 2500.00, 'Vendas'),
('Gabiel Tavares', 1500.00, 'Administração'),
('Adson', 2100.00, 'Estoque'),
('Sorriso', 1000.00, 'Vendas');

insert into veiculo (marca, placa_veiculo, modelo_veiculo, valor, ano_fabricacao) values
('Ford', 'DEF1234', 'Fiesta', 65000.00, 2018),
('Toyota', 'XCV3456', 'Corolla', 123000.00, 2020),
('Chevrolet', 'GTP5624', 'Camaro', 450000.00, 2019),
('Ferrari', 'PTB1570', 'Ferrari Spider', 1500000.00, 2023);

insert into venda (valor_venda, data_venda, fk_funcionario_venda, fk_veiculo_venda, fk_cliente_venda) values
(25000.00, 2020, 1, 1, 1),
(150000, 2019, 2, 2, 2),
(75000, 2018, 3, 3, 3),
(80500, 2023, 4, 4, 4);

select * from cliente;
select * from funcionario;
select * from veiculo;
select * from venda;

SELECT
	veiculo.marca, veiculo.modelo, veiculo.ano_fabricacao, veiculo.placa_veiculo,
    veiculo.valor_venda, veiculo.data_venda,
    cliente.nome AS nome_cliente,
    funcionario.nome AS nome_funcionario
FROM veiculo
inner join venda on veiculo.id_veiculo = venda.id_veiculo
inner join cliente on venda.id_cliente = cliente.id_cliente
inner join funcionario on venda.id_funcionario = funcionario.id_funcionario;

select avg(valor_venda) from venda;

create view media_venda as
select
	round(avg(valor_venda), 2) as media_valor_venda,
    count(*) as total_vendas
from venda;

select * from media_venda;

create view media_vendas_por_funcionario as
select
	id_funcionario,
    round(avg(valor_venda), 2) as media_valor_venda,
    count(*) as total_vendas
from venda
group by id_funcionario;

select * from media_vendas_por_funcionario;