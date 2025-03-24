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
