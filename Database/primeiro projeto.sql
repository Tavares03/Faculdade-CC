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