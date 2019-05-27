create database funcionarios;
use funcionarios;
create table Cargo(
    id int NOT NULL AUTO_INCREMENT,
    nome varchar(50) NOT NULL ,
    PRIMARY KEY(id),
    UNIQUE(nome)
);

create table Usuario(
    id int NOT NULL AUTO_INCREMENT,
    nome varchar(50) NOT NULL,
    id_cargo int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_cargo) REFERENCES Cargo(id)
);
create table Telefone(
    id int NOT NULL AUTO_INCREMENT,
    ddd varchar(5) NOT NULL,
    numero varchar(14) NOT NULL,
    id_usuario int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id)
);

insert into Cargo(nome) values ('Programador'),('Programador Front'), ('DBA'), ('Caminhoneiro');

insert into Usuario(nome,id_cargo) 
values ('Alberto',1),('Carlos',1),('Joao',2),
	   ('Gabriel',3),('Felipe',3);

insert into telefone(ddd,numero,id_usuario) values
('021','123456789',1),
('021','123456789',2),
('021','123456789',3),
('021','123456789',4),
('021','123456789',5);

insert into Usuario(nome,id_cargo) 
values ('Lemos',1),('Pedro',1),('Carlos',2),
	   ('Hibere',3),('Marcos',3);