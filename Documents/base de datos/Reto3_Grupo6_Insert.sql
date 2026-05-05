USE Grupo6_Reto3_dam;

-- IDIOMA
INSERT INTO Idioma (IdIdioma, Descripcion)
VALUES ('ES', 'Español');

-- MUSICO
INSERT INTO Musico (IdMusico, Caracteristica)
VALUES ('M001', 'solista');

-- PODCASTER
INSERT INTO Podcaster (IdPodcaster)
VALUES ('P001');

-- =========================
-- ARTISTA (8)
-- =========================
INSERT INTO Artista (IdArtista, NombreArtistico, Genero, Imagen, Descripcion)
VALUES 
  ('A001', 'DJ Nova', 'Electrónica', NULL, 'Artista de música electrónica');
('A002','Luna Vox','Pop',NULL,'Cantante pop'),
('A003','Rock Atlas','Rock',NULL,'Banda rock'),
('A004','MC Urban','HipHop',NULL,'Rap urbano'),
('A005','Jazz Blue','Jazz',NULL,'Jazz clásico'),
('A006','Metal Storm','Metal',NULL,'Metal band'),
('A007','Electro Wave','Electrónica',NULL,'DJ festival'),
('A008','Pop Shine','Pop',NULL,'Artista pop'),


-- CLIENTE
INSERT INTO Cliente (IdCliente, Nombre, Apellido, IdIdioma, Usuario, Contraseña, FechaNacimiento, Tipo)
VALUES ('C001', 'Juan', 'Pérez', 'ES', 'juanp', '1234', '2000-05-10', 'Premium');

-- PREMIUM
INSERT INTO Premium (IdCliente, FechaCaducidad)
VALUES ('C001', '2026-12-31');

-- AUDIO
INSERT INTO Audio (IdAudio, Nombre, Duracion, NReproducciones, Archivo, Tipo)
VALUES ('AU001', 'Cancion Demo', 210, 0, NULL, 'cancion');

-- PODCAST
INSERT INTO Podcast (IdAudio, NColaboradores, IdPodcaster)
VALUES ('AU001', 2, 'P001');

-- ALBUM
INSERT INTO Album (IdAlbum, Titulo, Año, Genero, Imagen, IdMusico)
VALUES ('AL001', 'Electro Hits', 2024, 'Electrónica', NULL, 'M001');

-- CANCION
INSERT INTO Cancion (IdCancion, IdAlbum, ArtistasInvitados)
VALUES ('CA001', 'AL001', 'DJ Nova');

-- PLAYLIST
INSERT INTO Playlist (IdList, Titulo, FechaCreacion, IdCliente)
VALUES (1, 'Favoritas', '2026-04-28', 'C001');

-- PLAYLIST_CANCIONES
INSERT INTO Playlist_Canciones (IdList, IdCancion, FechaPlaylist_Cancion)
VALUES (1, 'CA001', '2026-04-28');

-- GUSTOS
INSERT INTO Gustos (IdCliente, IdAudio)
VALUES ('C001', 'AU001');
