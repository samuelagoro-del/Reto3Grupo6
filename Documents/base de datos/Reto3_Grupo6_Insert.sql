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

-- ARTISTA
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

-- ALBUM (CORREGIDO: Solo se han cambiado los IdMusico de 'M' a 'A')
INSERT INTO Album (IdAlbum, Titulo, Año, Genero, Imagen, IdMusico)
VALUES 
('AL001', 'Electro Hits', 2024, 'Electrónica', NULL, 'A001'),
('AL0031','Neon Waves',2024,'Electrónica',0x89504E4701,'A001'),
('AL002','Cyber Pulse',2023,'Electrónica',0x89504E4702,'A001'),
('AL003','Digital Void',2022,'Electrónica',0x89504E4703,'A001'),
('AL004','Afterlight',2021,'Electrónica',0x89504E4704,'A001'),
('AL005','Pop Dreams',2024,'Pop',0x89504E4705,'A002'),
('AL006','Heart Echo',2023,'Pop',0x89504E4706,'A002'),
('AL007','Luminous Love',2022,'Pop',0x89504E4707,'A002'),
('AL008','Rock Legends',2024,'Rock',0x89504E4708,'A003'),
('AL009','Broken Strings',2022,'Rock',0x89504E4709,'A003'),
('AL010','Urban Flow',2024,'HipHop',0x89504E4710,'A004'),
('AL011','Street Code',2023,'HipHop',0x89504E4711,'A004'),
('AL012','Concrete Dreams',2022,'HipHop',0x89504E4712,'A004'),
('AL013','Back to Blocks',2021,'HipHop',0x89504E4713,'A004'),
('AL014','Blue Notes',2024,'Jazz',0x89504E4714,'A005'),
('AL015','Midnight Jazz',2023,'Jazz',0x89504E4715,'A005'),
('AL016','Dark Thunder',2024,'Metal',0x89504E4716,'A006'),
('AL017','Iron Storm',2023,'Metal',0x89504E4717,'A006'),
('AL018','Black Silence',2021,'Metal',0x89504E4718,'A006'),
('AL019','Fury Core',2020,'Metal',0x89504E4719,'A006'),
('AL020','Electric Pulse',2024,'Electrónica',0x89504E4720,'A007'),
('AL021','Future Bass',2023,'Electrónica',0x89504E4721,'A007'),
('AL022','Neon Future',2022,'Electrónica',0x89504E4722,'A007'),
('AL023','Shine Pop',2024,'Pop',0x89504E4723,'A008'),
('AL024','Summer Glow',2023,'Pop',0x89504E4724,'A008'),
('AL025','Fusion Nights',2024,'Electrónica',0x89504E4725,'A001'),
('AL026','Urban Legends',2024,'HipHop',0x89504E4726,'A004'),
('AL027','Jazz Infinity',2023,'Jazz',0x89504E4727,'A005'),
('AL028','Metal Chaos',2023,'Metal',0x89504E4728,'A006'),
('AL029','Pop Universe',2022,'Pop',0x89504E4729,'A002'),
('AL030','Rock Horizon',2021,'Rock',0x89504E4730,'A003');

-- CANCION (Tus datos intactos)
INSERT INTO Cancion (IdCancion, IdAlbum, ArtistasInvitados)
VALUES
('C001', 'AL0031', NULL),
('C002', 'AL0031', 'Luna Vox'),
('C003', 'AL0031', NULL),
('C004', 'AL002', 'Electro Wave'),
('C005', 'AL002', NULL),
('C006', 'AL005', 'DJ Nova'),
('C007', 'AL005', NULL),
('C008', 'AL005', 'Pop Shine'),
('C009', 'AL008', 'Metal Storm'),
('C010', 'AL008', NULL),
('C011', 'AL008', NULL),
('C012', 'AL010', NULL),
('C013', 'AL010', 'DJ Nova'),
('C014', 'AL010', 'Luna Vox'),
('C015', 'AL014', NULL),
('C016', 'AL014', NULL),
('C017', 'AL016', 'Rock Atlas'),
('C018', 'AL016', NULL),
('C019', 'AL020', 'MC Urban'),
('C020', 'AL020', NULL),
('C021', 'AL023', 'Luna Vox'),
('C022', 'AL023', NULL),
('C023', 'AL025', NULL),
('C024', 'AL026', 'Pop Shine'),
('C025', 'AL027', NULL),
('C026', 'AL028', 'Rock Atlas'),
('C027', 'AL029', 'DJ Nova'),
('C028', 'AL030', NULL);

-- PLAYLIST
INSERT INTO Playlist (IdList, Titulo, FechaCreacion, IdCliente)
VALUES (1, 'Favoritas', '2026-04-28', 'C001');

--- PLAYLIST_CANCIONES
INSERT INTO Playlist_Canciones (IdList, IdCancion, FechaPlaylist_Cancion)
VALUES (1, 'C001', '2026-04-28'); -- He cambiado 'CA001' por 'C001'

-- GUSTOS
INSERT INTO Gustos (IdCliente, IdAudio)
VALUES ('C001', 'AU001');
