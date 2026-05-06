USE Grupo6_Reto3_dam;
USE Grupo6_Reto3_dam;

-- IDIOMA
INSERT INTO Idioma (IdIdioma, Descripcion)
VALUES ('ES', 'Español');

-- MUSICO
INSERT INTO Musico (IdMusico, Caracteristica)
VALUES 
    ('A001', 'solista'), 
    ('A002', 'solista'), 
    ('A003', 'grupo'),   
    ('A004', 'solista'), 
    ('A005', 'solista'), 
    ('A006', 'grupo'),   
    ('A007', 'solista'), 
    ('A008', 'solista');

-- PODCASTER
INSERT INTO Podcaster (IdPodcaster)
VALUES ('P001');

-- ARTISTA (Descripciones ampliadas)
INSERT INTO Artista (IdArtista, NombreArtistico, Genero, Imagen, Descripcion)
VALUES 
('A001', 'DJ Nova', 'Electrónica', 'https://tinyurl.com/musica-elec1', 
 'Nacido en Berlín, comenzó pinchando en clubes clandestinos a los 16 años. Tras una década en la escena underground, su estilo innovador que mezcla techno con sintetizadores retro lo ha llevado a los festivales más grandes del mundo.'),

('A002', 'Luna Vox', 'Pop', 'https://tinyurl.com/musica-pop1', 
 'Criada en una familia de músicos, Luna empezó subiendo covers a redes sociales desde su habitación. Su voz angelical y sus letras sobre el desamor adolescente la convirtieron en un fenómeno global en menos de dos años.'),

('A003', 'Rock Atlas', 'Rock', 'https://tinyurl.com/musica-rock1', 
 'Formada en un garaje de Seattle en 2010, esta banda revivió el sonido grunge clásico. Tras años de giras en furgoneta, alcanzaron el éxito mundial con su tercer álbum, convirtiéndose en el estandarte del rock moderno.'),

('A004', 'MC Urban', 'HipHop', 'https://tinyurl.com/musica-hip1', 
 'Originario de los barrios humildes de Nueva York, MC Urban utiliza sus rimas para narrar la cruda realidad de las calles. Empezó en batallas de gallos locales donde destacó por su rapidez mental y su mensaje social.'),

('A005', 'Jazz Blue', 'Jazz', 'https://tinyurl.com/musica-jazz1', 
 'Pianista prodigio que estudió en los mejores conservatorios de Nueva Orleans. Su carrera despegó al fusionar el jazz clásico con ritmos contemporáneos, manteniendo siempre la elegancia y la improvisación pura.'),

('A006', 'Metal Storm', 'Metal', 'https://tinyurl.com/musica-met1', 
 'Banda nórdica conocida por su potencia sonora y puestas en escena teatrales. Sus integrantes se conocieron en la universidad y decidieron crear un proyecto que explorara la mitología antigua a través de riffs pesados.'),

('A007', 'Electro Wave', 'Electrónica', 'https://tinyurl.com/musica-elec2', 
 'Productor y DJ que empezó experimentando con software gratuito en su computadora. Hoy es un referente del Synthwave, especializado en crear atmósferas futuristas que parecen sacadas de una película de ciencia ficción.'),

('A008', 'Pop Shine', 'Pop', 'https://tinyurl.com/musica-pop2', 
 'La nueva promesa del pop bailable. Con un pasado como bailarín profesional, Shine decidió dar el salto al canto, combinando coreografías complejas con estribillos pegadizos que dominan todas las listas de éxitos actuales.');
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

-- ALBUM (CORREGIDO: IDs de músico sincronizados e imágenes como link)
INSERT INTO Album (IdAlbum, Titulo, Año, Genero, Imagen, IdMusico)
VALUES 
('AL001', 'Electro Hits', 2024, 'Electrónica', 'https://tinyurl.com/alb-1', 'A001'),
('AL0031','Neon Waves',2024,'Electrónica','https://tinyurl.com/alb-2','A001'),
('AL002','Cyber Pulse',2023,'Electrónica','https://tinyurl.com/alb-3','A001'),
('AL003','Digital Void',2022,'Electrónica','https://tinyurl.com/alb-4','A001'),
('AL004','Afterlight',2021,'Electrónica','https://tinyurl.com/alb-5','A001'),
('AL005','Pop Dreams',2024,'Pop','https://tinyurl.com/alb-6','A002'),
('AL006','Heart Echo',2023,'Pop','https://tinyurl.com/alb-7','A002'),
('AL007','Luminous Love',2022,'Pop','https://tinyurl.com/alb-8','A002'),
('AL008','Rock Legends',2024,'Rock','https://tinyurl.com/alb-9','A003'),
('AL009','Broken Strings',2022,'Rock','https://tinyurl.com/alb-10','A003'),
('AL010','Urban Flow',2024,'HipHop','https://tinyurl.com/alb-11','A004'),
('AL011','Street Code',2023,'HipHop','https://tinyurl.com/alb-12','A004'),
('AL012','Concrete Dreams',2022,'HipHop','https://tinyurl.com/alb-13','A004'),
('AL013','Back to Blocks',2021,'HipHop','https://tinyurl.com/alb-14','A004'),
('AL014','Blue Notes',2024,'Jazz','https://tinyurl.com/alb-15','A005'),
('AL015','Midnight Jazz',2023,'Jazz','https://tinyurl.com/alb-16','A005'),
('AL016','Dark Thunder',2024,'Metal','https://tinyurl.com/alb-17','A006'),
('AL017','Iron Storm',2023,'Metal','https://tinyurl.com/alb-18','A006'),
('AL018','Black Silence',2021,'Metal','https://tinyurl.com/alb-19','A006'),
('AL019','Fury Core',2020,'Metal','https://tinyurl.com/alb-20','A006'),
('AL020','Electric Pulse',2024,'Electrónica','https://tinyurl.com/alb-21','A007'),
('AL021','Future Bass',2023,'Electrónica','https://tinyurl.com/alb-22','A007'),
('AL022','Neon Future',2022,'Electrónica','https://tinyurl.com/alb-23','A007'),
('AL023','Shine Pop',2024,'Pop','https://tinyurl.com/alb-24','A008'),
('AL024','Summer Glow',2023,'Pop','https://tinyurl.com/alb-25','A008'),
('AL025','Fusion Nights',2024,'Electrónica','https://tinyurl.com/alb-26','A001'),
('AL026','Urban Legends',2024,'HipHop','https://tinyurl.com/alb-27','A004'),
('AL027','Jazz Infinity',2023,'Jazz','https://tinyurl.com/alb-28','A005'),
('AL028','Metal Chaos',2023,'Metal','https://tinyurl.com/alb-29','A006'),
('AL029','Pop Universe',2022,'Pop','https://tinyurl.com/alb-30','A002'),
('AL030','Rock Horizon',2021,'Rock','https://tinyurl.com/alb-31','A003');

-- CANCION
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

-- PLAYLIST_CANCIONES
INSERT INTO Playlist_Canciones (IdList, IdCancion, FechaPlaylist_Cancion)
VALUES (1, 'C001', '2026-04-28');

-- GUSTOS
INSERT INTO Gustos (IdCliente, IdAudio)
VALUES ('C001', 'AU001');
