use DAM1_reto2_Grupo6;

create table Cliente (
DNI char(9) primary key,
nombre varchar(50) not null,
apellido varchar(100) not null,
correo varchar(200) unique not null,
contrasena varchar(255) not null
);

create table Compra (
idCompra int auto_increment primary key,
fecha datetime not null,
hora time not null,
precio decimal(8,2) not null,
descuento decimal(5,2),
DNI char(9) not null,
constraint fk_DNI_Cliente foreign key(DNI) references Cliente(DNI) on update cascade
);

create table Sala(
idSala int auto_increment primary key,
nombre varchar(50) not null
);

create table Pelicula(
idPeli int auto_increment primary key,
titulo varchar(100)	not null,
duracion int not null,
genero varchar(50),
precio decimal(8,2) not null
);

create table Sesion (
idSesion int auto_increment primary key,
fecha date not null,
horaIni time not null,
horaFin time not null,
precio decimal(8,2) not null,
idSala int not null,
idPeli int not null,
constraint fk_idSala_Sala foreign key(idSala) references Sala(idSala) on update cascade,
constraint fk_idPeli_Pelicula foreign key(idPeli) references Pelicula(idPeli) on update cascade
);

create table Entrada (
idEntrada int auto_increment primary key,
descuento decimal(5,2),
precio decimal(8,2) not null,
num_pers int unsigned not null,
idCompra int not null,
idSesion int not null,
constraint fk_idCompra_Compra foreign key(idCompra) references Compra(idCompra) on update cascade,
constraint fk_idSesion_Sesion foreign key(idSesion) references Sesion(idSesion) on update cascade
);