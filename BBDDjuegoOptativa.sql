drop database if exists extremeMemory;

create database if not exists extremeMemory;

use extremeMemory;

drop table if exists Perfiles; 

create table if not exists Perfiles (
	ID int auto_increment not null,
    Nombre varchar(50) not null,
    constraint PK_ID primary key (ID)
);

drop table if exists Partidas;

create table if not exists Partidas (
	id varchar(50) unique not null,
    perfil_id int,
    datos blob,
    constraint FK_Perfil foreign key (perfil_id) references Perfiles(ID) on delete cascade
);