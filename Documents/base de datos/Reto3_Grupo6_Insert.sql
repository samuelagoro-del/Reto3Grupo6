USE Grupo6_Reto3_dam;

-- 1. IDIOMA
INSERT INTO Idioma (IdIdioma, Descripcion)
VALUES ('ES', 'Español');

-- 2. MUSICO
INSERT INTO Musico (IdMusico, Caracteristica)
VALUES 
    ('A001', 'solista'), ('A002', 'solista'), ('A003', 'grupo'), ('A004', 'solista'), 
    ('A005', 'solista'), ('A006', 'grupo'), ('A007', 'solista'), ('A008', 'solista');

-- 3. PODCASTER
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

-- 5. CLIENTE
INSERT INTO Cliente (IdCliente, Nombre, Apellido, IdIdioma, Usuario, Contraseña, FechaNacimiento, Tipo)
VALUES ('C001', 'Juan', 'Pérez', 'ES', 'juanp', '1234', '2000-05-10', 'Premium');

-- 6. PREMIUM
INSERT INTO Premium (IdCliente, FechaCaducidad)
VALUES ('C001', '2026-12-31');

-- 7. AUDIO (Añadido AU001 para el Podcast)
INSERT INTO Audio (IdAudio, Nombre, Duracion, NReproducciones, Archivo, Tipo)
VALUES 
('AU001', 'Episodio Podcast 1', 1200, 0, NULL, 'podcast'),
('C001', 'Neon Sunrise', 210, 0, NULL, 'cancion'), ('C002', 'Vocal Drift', 185, 0, NULL, 'cancion'),
('C003', 'Midnight Synth', 200, 0, NULL, 'cancion'), ('C004', 'Cyber Pulse Main', 240, 0, NULL, 'cancion'),
('C005', 'Binary Rain', 190, 0, NULL, 'cancion'), ('C006', 'Pop Dreamer', 180, 0, NULL, 'cancion'),
('C007', 'Sweet Echo', 210, 0, NULL, 'cancion'), ('C008', 'Shine Bright', 195, 0, NULL, 'cancion'),
('C009', 'Heavy Impact', 250, 0, NULL, 'cancion'), ('C010', 'Metal Roots', 230, 0, NULL, 'cancion'),
('C011', 'Iron Will', 220, 0, NULL, 'cancion'), ('C012', 'Street Anthem', 170, 0, NULL, 'cancion'),
('C013', 'City Flow', 190, 0, NULL, 'cancion'), ('C014', 'Urban Night', 205, 0, NULL, 'cancion'),
('C015', 'Blue Jazz Melodies', 310, 0, NULL, 'cancion'), ('C016', 'Sax Solo', 280, 0, NULL, 'cancion'),
('C017', 'Thunder Riff', 245, 0, NULL, 'cancion'), ('C018', 'Electric Storm', 235, 0, NULL, 'cancion'),
('C019', 'Future Step', 195, 0, NULL, 'cancion'), ('C020', 'Digital Beat', 185, 0, NULL, 'cancion'),
('C021', 'Summer Pop', 175, 0, NULL, 'cancion'), ('C022', 'Sun Glow', 190, 0, NULL, 'cancion'),
('C023', 'Fusion Bass', 215, 0, NULL, 'cancion'), ('C024', 'Urban Legend Track', 180, 0, NULL, 'cancion'),
('C025', 'Infinite Jazz', 290, 0, NULL, 'cancion'), ('C026', 'Chaos Theory', 240, 0, NULL, 'cancion'),
('C027', 'Pop Galaxy', 195, 0, NULL, 'cancion'), ('C028', 'Horizon Line', 220, 0, NULL, 'cancion');

-- 8. PODCAST
INSERT INTO Podcast (IdAudio, NColaboradores, IdPodcaster)
VALUES ('AU001', 2, 'P001');

-- 9. ALBUM
INSERT INTO Album (IdAlbum, Titulo, Año, Genero, Imagen, IdMusico)
VALUES 
('AL001', 'Electro Hits', 2024, 'Electrónica', 'https://tinyurl.com/alb-1', 'A001'),
('AL0031','Neon Waves',2024,'Electrónica','https://tinyurl.com/alb-2','A001'),
('AL002','Cyber Pulse',2023,'Electrónica','https://tinyurl.com/alb-3','A001'),
('AL005','Pop Dreams',2024,'Pop','https://tinyurl.com/alb-6','A002'),
('AL008','Rock Legends',2024,'Rock','https://tinyurl.com/alb-9','A003'),
('AL010','Urban Flow',2024,'HipHop','https://tinyurl.com/alb-11','A004'),
('AL014','Blue Notes',2024,'Jazz','https://tinyurl.com/alb-15','A005'),
('AL016','Dark Thunder',2024,'Metal','https://tinyurl.com/alb-17','A006'),
('AL020','Electric Pulse',2024,'Electrónica','https://tinyurl.com/alb-21','A007'),
('AL023','Shine Pop',2024,'Pop','https://tinyurl.com/alb-24','A008'),
('AL025','Fusion Nights',2024,'Electrónica','https://tinyurl.com/alb-26','A001'),
('AL026','Urban Legends',2024,'HipHop','https://tinyurl.com/alb-27','A004'),
('AL027','Jazz Infinity',2023,'Jazz','https://tinyurl.com/alb-28','A005'),
('AL028','Metal Chaos',2023,'Metal','https://tinyurl.com/alb-29','A006'),
('AL029','Pop Universe',2022,'Pop','https://tinyurl.com/alb-30','A002'),
('AL030','Rock Horizon',2021,'Rock','https://tinyurl.com/alb-31','A003');

-- 10. CANCION
INSERT INTO Cancion (IdCancion, IdAlbum, ArtistasInvitados)
VALUES
('C001', 'AL0031', NULL), ('C002', 'AL0031', 'Luna Vox'), ('C003', 'AL0031', NULL),
('C004', 'AL002', 'Electro Wave'), ('C005', 'AL002', NULL), ('C006', 'AL005', 'DJ Nova'),
('C007', 'AL005', NULL), ('C008', 'AL005', 'Pop Shine'), ('C009', 'AL008', 'Metal Storm'),
('C010', 'AL008', NULL), ('C011', 'AL008', NULL), ('C012', 'AL010', NULL),
('C013', 'AL010', 'DJ Nova'), ('C014', 'AL010', 'Luna Vox'), ('C015', 'AL014', NULL),
('C016', 'AL014', NULL), ('C017', 'AL016', 'Rock Atlas'), ('C018', 'AL016', NULL),
('C019', 'AL020', 'MC Urban'), ('C020', 'AL020', NULL), ('C021', 'AL023', 'Luna Vox'),
('C022', 'AL023', NULL), ('C023', 'AL025', NULL), ('C024', 'AL026', 'Pop Shine'),
('C025', 'AL027', NULL), ('C026', 'AL028', 'Rock Atlas'), ('C027', 'AL029', 'DJ Nova'),
('C028', 'AL030', NULL);

-- 11. PLAYLIST (Cabecera obligatoria)
INSERT INTO Playlist (IdList, Titulo, FechaCreacion, IdCliente)
VALUES (1, 'Favoritas', '2026-04-28', 'C001');

-- 12. PLAYLIST_CANCIONES (Relación)
INSERT INTO Playlist_Canciones (IdList, IdCancion, FechaPlaylist_Cancion)
VALUES 
(1, 'C001', '2026-04-28'), (1, 'C002', '2026-04-28'), (1, 'C004', '2026-04-29'),
(1, 'C006', '2026-04-29'), (1, 'C009', '2026-04-30'), (1, 'C012', '2026-05-01'),
(1, 'C015', '2026-05-02'), (1, 'C017', '2026-05-03'), (1, 'C021', '2026-05-04'),
(1, 'C025', '2026-05-05');

-- 13. GUSTOS
INSERT INTO Gustos (IdCliente, IdAudio)
VALUES ('C001', 'C001');
-- Podcaster
INSERT INTO Podcaster (IdPodcaster) VALUES 
('P002'), ('P003'), ('P004'), ('P005'), ('P006'), ('P007'), ('P008'), ('P009'), ('P010'),
('P011'), ('P012'), ('P013'), ('P014'), ('P015'), ('P016'), ('P017'), ('P018'), ('P019'),
('P020'), ('P021'), ('P022'), ('P023'), ('P024'), ('P025'), ('P026');

-- Audio
INSERT INTO Audio (IdAudio, Nombre, Duracion, NReproducciones, Archivo, Tipo) VALUES 
('AU002', 'Tecnología en 5 minutos', 300, 0, NULL, 'podcast'), ('AU003', 'El futuro de la IA', 1500, 0, NULL, 'podcast'),
('AU004', 'Crímenes Reales: Caso 1', 2400, 0, NULL, 'podcast'), ('AU005', 'Crímenes Reales: Caso 2', 3100, 0, NULL, 'podcast'),
('AU006', 'Cocina Express: Tortilla', 600, 0, NULL, 'podcast'), ('AU007', 'Secretos del Chef', 1800, 0, NULL, 'podcast'),
('AU008', 'Meditación Guiada Mañana', 900, 0, NULL, 'podcast'), ('AU009', 'Mindfulness para Dormir', 1200, 0, NULL, 'podcast'),
('AU010', 'Historia de Roma: Julio César', 3600, 0, NULL, 'podcast'), ('AU011', 'La Caída del Imperio', 4200, 0, NULL, 'podcast'),
('AU012', 'Economía para Dummies', 1100, 0, NULL, 'podcast'), ('AU013', 'Invertir en Cripto', 1300, 0, NULL, 'podcast'),
('AU014', 'Fútbol al Día: La Liga', 2000, 0, NULL, 'podcast'), ('AU015', 'Fichajes de Verano', 1800, 0, NULL, 'podcast'),
('AU016', 'Cine de Culto: 1980', 2500, 0, NULL, 'podcast'), ('AU017', 'Directores de Terror', 2700, 0, NULL, 'podcast'),
('AU018', 'Aprende Inglés: Viajes', 950, 0, NULL, 'podcast'), ('AU019', 'Inglés de Negocios', 1100, 0, NULL, 'podcast'),
('AU020', 'Marketing Digital 2026', 1500, 0, NULL, 'podcast'), ('AU021', 'Estrategias SEO', 1600, 0, NULL, 'podcast'),
('AU022', 'Misterios del Espacio', 3200, 0, NULL, 'podcast'), ('AU023', 'Vida en Marte', 3400, 0, NULL, 'podcast'),
('AU024', 'Salud Mental: Ansiedad', 2100, 0, NULL, 'podcast'), ('AU025', 'El Poder del Hábito', 2200, 0, NULL, 'podcast'),
('AU026', 'Moda Sostenible', 1400, 0, NULL, 'podcast'), ('AU027', 'Tendencias Primavera', 1350, 0, NULL, 'podcast'),
('AU028', 'Gaming News: Nueva Consola', 1900, 0, NULL, 'podcast'), ('AU029', 'Análisis RPG del Año', 2200, 0, NULL, 'podcast'),
('AU030', 'Política Internacional', 2800, 0, NULL, 'podcast'), ('AU031', 'Conflictos Globales', 3000, 0, NULL, 'podcast'),
('AU032', 'Ciencia Divertida', 1200, 0, NULL, 'podcast'), ('AU033', 'El ADN Explicado', 1500, 0, NULL, 'podcast'),
('AU034', 'Viajes: Destino Japón', 2500, 0, NULL, 'podcast'), ('AU035', 'Guía de Mochileros', 2600, 0, NULL, 'podcast'),
('AU036', 'Autoayuda: Motivación', 1800, 0, NULL, 'podcast'), ('AU037', 'Superación Personal', 1900, 0, NULL, 'podcast'),
('AU038', 'Física Cuántica', 4000, 0, NULL, 'podcast'), ('AU039', 'Relatividad General', 4200, 0, NULL, 'podcast'),
('AU040', 'Coches Eléctricos', 1600, 0, NULL, 'podcast'), ('AU041', 'Motores del Futuro', 1700, 0, NULL, 'podcast'),
('AU042', 'Cuidado de Mascotas', 1100, 0, NULL, 'podcast'), ('AU043', 'Adiestramiento Canino', 1200, 0, NULL, 'podcast'),
('AU044', 'Leyendas Urbanas', 2300, 0, NULL, 'podcast'), ('AU045', 'Fantasmas en la Ciudad', 2400, 0, NULL, 'podcast'),
('AU046', 'Fotografía para Móvil', 1400, 0, NULL, 'podcast'), ('AU047', 'Edición en Lightroom', 1550, 0, NULL, 'podcast'),
('AU048', 'Jardinería en Casa', 1000, 0, NULL, 'podcast'), ('AU049', 'Cultivo de Orquídeas', 1200, 0, NULL, 'podcast'),
('AU050', 'Humor: Especial Chistes', 900, 0, NULL, 'podcast'), ('AU051', 'Monólogos de Viernes', 2200, 0, NULL, 'podcast');


INSERT INTO Podcast (IdAudio, NColaboradores, IdPodcaster) VALUES 
('AU002', 1, 'P002'), ('AU003', 2, 'P002'),
('AU004', 1, 'P003'), ('AU005', 1, 'P003'),
('AU006', 0, 'P004'), ('AU007', 2, 'P004'),
('AU008', 1, 'P005'), ('AU009', 1, 'P005'),
('AU010', 3, 'P006'), ('AU011', 2, 'P006'),
('AU012', 1, 'P007'), ('AU013', 2, 'P007'),
('AU014', 2, 'P008'), ('AU015', 3, 'P008'),
('AU016', 1, 'P009'), ('AU017', 2, 'P009'),
('AU018', 0, 'P010'), ('AU019', 1, 'P010'),
('AU020', 1, 'P011'), ('AU021', 1, 'P011'),
('AU022', 2, 'P012'), ('AU023', 3, 'P012'),
('AU024', 1, 'P013'), ('AU025', 1, 'P013'),
('AU026', 1, 'P014'), ('AU027', 2, 'P014'),
('AU028', 2, 'P015'), ('AU029', 1, 'P015'),
('AU030', 3, 'P016'), ('AU031', 3, 'P016'),
('AU032', 1, 'P017'), ('AU033', 1, 'P017'),
('AU034', 2, 'P018'), ('AU035', 1, 'P018'),
('AU036', 1, 'P019'), ('AU037', 0, 'P019'),
('AU038', 2, 'P020'), ('AU039', 1, 'P020'),
('AU040', 1, 'P021'), ('AU041', 2, 'P021'),
('AU042', 0, 'P022'), ('AU043', 1, 'P022'),
('AU044', 3, 'P023'), ('AU045', 2, 'P023'),
('AU046', 1, 'P024'), ('AU047', 1, 'P024'),
('AU048', 0, 'P025'), ('AU049', 1, 'P025'),
('AU050', 2, 'P026'), ('AU051', 1, 'P026');
