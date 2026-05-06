USE Grupo6_Reto3_dam;

-- IDIOMA
INSERT INTO Idioma (IdIdioma, Descripcion)
VALUES ('ES', 'Español');

-- MUSICO
INSERT INTO Musico (IdMusico, Caracteristica)
VALUES 
    ('A001', 'solista'), -- DJ Nova
    ('A002', 'solista'), -- Luna Vox
    ('A003', 'grupo'),   -- Rock Atlas
    ('A004', 'solista'), -- MC Urban
    ('A005', 'solista'), -- Jazz Blue
    ('A006', 'grupo'),   -- Metal Storm
    ('A007', 'solista'), -- Electro Wave
    ('A008', 'solista'); -- Pop Shine
-- PODCASTER
INSERT INTO Podcaster (IdPodcaster)
VALUES ('P001');

-- =========================
-- ARTISTA (8)
-- =========================
INSERT INTO Artista (IdArtista, NombreArtistico, Genero, Imagen, Descripcion)
VALUES 
  ('A001', 'DJ Nova', 'Electrónica', NULL, 'Artista de música electrónica'),
('A002','Luna Vox','Pop',NULL,'Cantante pop'),
('A003','Rock Atlas','Rock',NULL,'Banda rock'),
('A004','MC Urban','HipHop',NULL,'Rap urbano'),
('A005','Jazz Blue','Jazz',NULL,'Jazz clásico'),
('A006','Metal Storm','Metal',NULL,'Metal band'),
('A007','Electro Wave','Electrónica',NULL,'DJ festival'),
('A008','Pop Shine','Pop',NULL,'Artista pop');


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
VALUES ('AL001', 'Electro Hits', 2024, 'Electrónica', NULL, 'M001'),
  -- DJ Nova (M001) → más activo
('AL0031','Neon Waves',2024,'Electrónica',0x89504E4701,'M001'),
('AL002','Cyber Pulse',2023,'Electrónica',0x89504E4702,'M001'),
('AL003','Digital Void',2022,'Electrónica',0x89504E4703,'M001'),
('AL004','Afterlight',2021,'Electrónica',0x89504E4704,'M001'),

-- Luna Vox (M002) → pop media discografía
('AL005','Pop Dreams',2024,'Pop',0x89504E4705,'M002'),
('AL006','Heart Echo',2023,'Pop',0x89504E4706,'M002'),
('AL007','Luminous Love',2022,'Pop',0x89504E4707,'M002'),

-- Rock Atlas (M003) → pocos pero fuertes
('AL008','Rock Legends',2024,'Rock',0x89504E4708,'M003'),
('AL009','Broken Strings',2022,'Rock',0x89504E4709,'M003'),

-- MC Urban (M004) → muy activo
('AL010','Urban Flow',2024,'HipHop',0x89504E4710,'M004'),
('AL011','Street Code',2023,'HipHop',0x89504E4711,'M004'),
('AL012','Concrete Dreams',2022,'HipHop',0x89504E4712,'M004'),
('AL013','Back to Blocks',2021,'HipHop',0x89504E4713,'M004'),

-- Jazz Blue (M005) → estable
('AL014','Blue Notes',2024,'Jazz',0x89504E4714,'M005'),
('AL015','Midnight Jazz',2023,'Jazz',0x89504E4715,'M005'),

-- Metal Storm (M006) → irregular pero potente
('AL016','Dark Thunder',2024,'Metal',0x89504E4716,'M006'),
('AL017','Iron Storm',2023,'Metal',0x89504E4717,'M006'),
('AL018','Black Silence',2021,'Metal',0x89504E4718,'M006'),
('AL019','Fury Core',2020,'Metal',0x89504E4719,'M006'),

-- Electro Wave (M007) → muy activo
('AL020','Electric Pulse',2024,'Electrónica',0x89504E4720,'M007'),
('AL021','Future Bass',2023,'Electrónica',0x89504E4721,'M007'),
('AL022','Neon Future',2022,'Electrónica',0x89504E4722,'M007'),

-- Pop Shine (M008) → nuevo artista
('AL023','Shine Pop',2024,'Pop',0x89504E4723,'M008'),
('AL024','Summer Glow',2023,'Pop',0x89504E4724,'M008'),

-- extras variados (mezcla realista de industria)
('AL025','Fusion Nights',2024,'Electrónica',0x89504E4725,'M001'),
('AL026','Urban Legends',2024,'HipHop',0x89504E4726,'M004'),
('AL027','Jazz Infinity',2023,'Jazz',0x89504E4727,'M005'),
('AL028','Metal Chaos',2023,'Metal',0x89504E4728,'M006'),
('AL029','Pop Universe',2022,'Pop',0x89504E4729,'M002'),
('AL030','Rock Horizon',2021,'Rock',0x89504E4730,'M003');

-- CANCION

INSERT INTO Cancion (IdCancion, IdAlbum, ArtistasInvitados)
VALUES
-- Canciones para el álbum AL0031 (Neon Waves - DJ Nova)
('C001', 'AL0031', NULL),
('C002', 'AL0031', 'Luna Vox'),
('C003', 'AL0031', NULL),

-- Canciones para el álbum AL002 (Cyber Pulse - DJ Nova)
('C004', 'AL002', 'Electro Wave'),
('C005', 'AL002', NULL),

-- Canciones para el álbum AL005 (Pop Dreams - Luna Vox)
('C006', 'AL005', 'DJ Nova'),
('C007', 'AL005', NULL),
('C008', 'AL005', 'Pop Shine'),

-- Canciones para el álbum AL008 (Rock Legends - Rock Atlas)
('C009', 'AL008', 'Metal Storm'),
('C010', 'AL008', NULL),
('C011', 'AL008', NULL),

-- Canciones para el álbum AL010 (Urban Flow - MC Urban)
('C012', 'AL010', NULL),
('C013', 'AL010', 'DJ Nova'),
('C014', 'AL010', 'Luna Vox'),

-- Canciones para el álbum AL014 (Blue Notes - Jazz Blue)
('C015', 'AL014', NULL),
('C016', 'AL014', NULL),

-- Canciones para el álbum AL016 (Dark Thunder - Metal Storm)
('C017', 'AL016', 'Rock Atlas'),
('C018', 'AL016', NULL),

-- Canciones para el álbum AL020 (Electric Pulse - Electro Wave)
('C019', 'AL020', 'MC Urban'),
('C020', 'AL020', NULL),

-- Canciones para el álbum AL023 (Shine Pop - Pop Shine)
('C021', 'AL023', 'Luna Vox'),
('C022', 'AL023', NULL),

-- Extras para los demás álbumes variados (AL025 a AL030)
('C023', 'AL025', NULL),
('C024', 'AL026', 'Pop Shine'),
('C025', 'AL027', NULL),
('C026', 'AL028', 'Rock Atlas'),
('C027', 'AL029', 'DJ Nova'),
('C028', 'AL030', NULL);

-- PLAYLIST
INSERT INTO Playlist (IdList, Titulo, FechaCreacion, IdCliente)
VALUES (1, 'Favoritas', '2026-04-28', 'C001');

-- PLAYLIST_CANCIONES
INSERT INTO Playlist_Canciones (IdList, IdCancion, FechaPlaylist_Cancion)
VALUES (1, 'CA001', '2026-04-28');

-- GUSTOS
INSERT INTO Gustos (IdCliente, IdAudio)
VALUES ('C001', 'AU001');




