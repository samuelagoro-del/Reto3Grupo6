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
-- ARTISTA (50)
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
('A009','Urban Flow','HipHop',NULL,'Rap flow'),
('A010','Golden Jazz','Jazz',NULL,'Jazz moderno'),
('A011','Rock Fire','Rock',NULL,'Rock alternativo'),
('A012','Bass Drop','Electrónica',NULL,'DJ bass'),
('A013','MC Prime','HipHop',NULL,'Rap competitivo'),
('A014','Pop Dream','Pop',NULL,'Pop romántico'),
('A015','Metal Core','Metal',NULL,'Metal core'),
('A016','Soul Vibe','R&B',NULL,'Soul cantante'),
('A017','Latin Beat','Reggaeton',NULL,'Beat latino'),
('A018','DJ Echo','Electrónica',NULL,'DJ experimental'),
('A019','Rock Edge','Rock',NULL,'Rock moderno'),
('A020','MC Blaze','HipHop',NULL,'Rap rápido'),
('A021','Pop Star','Pop',NULL,'Estrella pop'),
('A022','Jazz Moon','Jazz',NULL,'Jazz nocturno'),
('A023','Metal Rage','Metal',NULL,'Metal agresivo'),
('A024','Soul Deep','R&B',NULL,'R&B suave'),
('A025','Latin Flow','Reggaeton',NULL,'Flow urbano'),
('A026','DJ Future','Electrónica',NULL,'Sonido futuro'),
('A027','Rock Nova','Rock',NULL,'Rock moderno'),
('A028','MC King','HipHop',NULL,'Rap líder'),
('A029','Pop Light','Pop',NULL,'Pop suave'),
('A030','Jazz Fire','Jazz',NULL,'Jazz intenso'),
('A031','Metal Dark','Metal',NULL,'Metal oscuro'),
('A032','Soul Angel','R&B',NULL,'Voz suave'),
('A033','Latin Fire','Reggaeton',NULL,'Artista latino'),
('A034','DJ Storm','Electrónica',NULL,'DJ festival'),
('A035','Rock Wave','Rock',NULL,'Rock alternativo'),
('A036','MC Zero','HipHop',NULL,'Rap underground'),
('A037','Pop Nova','Pop',NULL,'Pop actual'),
('A038','Jazz Soul','Jazz',NULL,'Jazz emocional'),
('A039','Metal Beast','Metal',NULL,'Metal extremo'),
('A040','Soul Groove','R&B',NULL,'Groove soul'),
('A041','Latin Star','Reggaeton',NULL,'Estrella latina'),
('A042','DJ Pulse','Electrónica',NULL,'DJ club'),
('A043','Rock Storm','Rock',NULL,'Rock potente'),
('A044','MC Flow','HipHop',NULL,'Flow urbano'),
('A045','Pop Wave','Pop',NULL,'Pop fresco'),
('A046','Jazz Night','Jazz',NULL,'Jazz nocturno'),
('A047','Metal Force','Metal',NULL,'Fuerza metal'),
('A048','Soul Rhythm','R&B',NULL,'Ritmo soul'),
('A049','Latin Groove','Reggaeton',NULL,'Groove latino');

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
