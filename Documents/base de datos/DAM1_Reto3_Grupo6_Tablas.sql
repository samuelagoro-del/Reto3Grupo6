-- create database Grupo6_Reto3_dam collate utf8mb4_spanish_ci;
-- grant all on empresa_nueva_dam.* to dam;
 use Grupo6_Reto3_dam;



-- ARTISTA

CREATE TABLE Artista (
    IdArtista VARCHAR(5) PRIMARY KEY,
    NombreArtistico VARCHAR(100) UNIQUE NOT NULL,
    Genero VARCHAR(50),
    Imagen VARCHAR(500),
    Descripcion VARCHAR(500) NOT NULL
);

-- MUSICO

CREATE TABLE Musico (
    IdMusico VARCHAR(5) PRIMARY KEY,
    Caracteristica ENUM('solista','grupo') NOT NULL
);


-- PODCASTER

CREATE TABLE Podcaster (
    IdPodcaster VARCHAR(5) PRIMARY KEY
);

-- IDIOMA

CREATE TABLE Idioma (
    IdIdioma ENUM('ES','EU','EN','FR','DE','CA','GA','AR') PRIMARY KEY,
    Descripcion VARCHAR(500) NOT NULL
);


-- CLIENTE

CREATE TABLE Cliente (
    IdCliente VARCHAR(5) PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Apellido VARCHAR(100) NOT NULL,
     IdIdioma ENUM('ES','EU','EN','FR','DE','CA','GA','AR') NOT NULL,
    Usuario VARCHAR(50) UNIQUE NOT NULL,
    Contraseña VARCHAR(100) NOT NULL,
    FechaNacimiento DATE NOT NULL,
    FechaRegistro DATE NOT NULL DEFAULT (CURRENT_DATE),
    Tipo ENUM('Free','Premium') NOT NULL,
    FOREIGN KEY (IdIdioma) REFERENCES Idioma(IdIdioma)
);

-- PREMIUM

CREATE TABLE Premium (
    IdCliente VARCHAR(5) PRIMARY KEY,
    FechaCaducidad DATE NOT NULL,
    FOREIGN KEY (IdCliente) REFERENCES Cliente(IdCliente)
);

-- AUDIO

CREATE TABLE Audio (
    IdAudio VARCHAR(10) PRIMARY KEY,
    Nombre VARCHAR(100) UNIQUE NOT NULL,
    Duracion INT NOT NULL,
    NReproducciones INT NOT NULL DEFAULT 0,
     Archivo  VARCHAR(500),
     Tipo ENUM('podcast','cancion') NOT NULL
);

-- PODCAST

CREATE TABLE Podcast (
    IdAudio VARCHAR(10) PRIMARY KEY,
    NColaboradores INT,
    IdPodcaster VARCHAR(5) NOT NULL,
    FOREIGN KEY (IdAudio) REFERENCES Audio(IdAudio),
    FOREIGN KEY (IdPodcaster) REFERENCES Podcaster(IdPodcaster)
);

-- ALBUM

CREATE TABLE Album (
    IdAlbum VARCHAR(10) PRIMARY KEY,
    Titulo VARCHAR(100) NOT NULL,
    Año YEAR NOT NULL,
    Genero VARCHAR(50) NOT NULL,
    Imagen VARCHAR(500),
    IdMusico VARCHAR(5) NOT NULL,
    FOREIGN KEY (IdMusico) REFERENCES Musico(IdMusico)
);


-- CANCIÓN

CREATE TABLE Cancion (
    IdCancion VARCHAR(10) PRIMARY KEY,
    IdAlbum VARCHAR(10) NOT NULL,
    ArtistasInvitados VARCHAR(100),
    FOREIGN KEY (IdAlbum) REFERENCES Album(IdAlbum)
);

-- PLAYLIST

CREATE TABLE Playlist (
    IdList INT PRIMARY KEY,
    Titulo VARCHAR(100) NOT NULL,
    FechaCreacion DATE NOT NULL,
    IdCliente VARCHAR(5) NOT NULL,
    FOREIGN KEY (IdCliente) REFERENCES Cliente(IdCliente)
);

-- PLAYLIST CANCIONES

CREATE TABLE Playlist_Canciones (
    IdList INT NOT NULL,
    IdCancion VARCHAR(10) NOT NULL,
    FechaPlaylist_Cancion DATE NOT NULL,
    PRIMARY KEY (IdList, IdCancion),
    FOREIGN KEY (IdList) REFERENCES Playlist(IdList),
    FOREIGN KEY (IdCancion) REFERENCES Cancion(IdCancion)
);

-- GUSTOS

CREATE TABLE Gustos (
    IdCliente VARCHAR(5),
    IdAudio VARCHAR(10),
    PRIMARY KEY (IdCliente, IdAudio),
    FOREIGN KEY (IdCliente) REFERENCES Cliente(IdCliente),
    FOREIGN KEY (IdAudio) REFERENCES Audio(IdAudio)
);



