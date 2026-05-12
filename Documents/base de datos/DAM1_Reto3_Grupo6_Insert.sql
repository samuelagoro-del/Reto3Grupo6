USE Grupo6_Reto3_dam;


-- ==========================================
-- 2. INSERCIÓN DE DATOS ACTUALIZADOS
-- ==========================================

-- 2.1 IDIOMA
INSERT INTO Idioma (IdIdioma, Descripcion) VALUES ('ES', 'Español'), ('EN', 'Inglés');

-- 2.2 MUSICO (8 Artistas Reales)
INSERT INTO Musico (IdMusico, Caracteristica) VALUES 
('A001', 'solista'), ('A002', 'solista'), ('A003', 'solista'), ('A004', 'solista'), 
('A005', 'solista'), ('A006', 'solista'), ('A007', 'solista'), ('A008', 'solista');

-- 2.3 ARTISTAS ACTUALES
INSERT INTO Artista (IdArtista, NombreArtistico, Genero, Imagen, Descripcion) VALUES 
('A001', 'Bad Bunny', 'Reggaeton', 'https://tinyurl.com/badbunny-img', 
 'Benito Martínez empezó empaquetando bolsas en un supermercado mientras subía música a SoundCloud. Hoy es el artista más escuchado del mundo, rompiendo récords históricos de streaming con su álbum Un Verano Sin Ti.'),

('A002', 'Taylor Swift', 'Pop', 'https://tinyurl.com/taylor-img', 
 'Empezó en el country a los 14 años. Es la primera artista en ganar cuatro veces el Grammy a Álbum del Año. Sus méritos incluyen regrabar sus propios discos para recuperar la propiedad de su música y dominar las listas globales.'),

('A003', 'Feid', 'Reggaeton', 'https://tinyurl.com/feid-img', 
 'Comenzó como compositor para otros artistas (J Balvin, Maluma). Su ascenso al estrellato global se dio tras adoptar su estética verde y el lenguaje paisa, convirtiéndose en el referente actual del género urbano en Colombia.'),

('A004', 'Dua Lipa', 'Pop', 'https://tinyurl.com/dua-img', 
 'De origen albano-kosovar, empezó subiendo covers a YouTube. Con el álbum Future Nostalgia, revivió el sonido disco-pop, ganando múltiples Grammys y estableciéndose como la cara principal del pop británico actual.'),

('A005', 'Karol G', 'Urbano', 'https://tinyurl.com/karolg-img', 
 'La Bichota comenzó en el Factor X de Colombia. Tras años de lucha en un género dominado por hombres, logró que Mañana Será Bonito fuera el primer álbum femenino en español en alcanzar el #1 en el Billboard 200.'),

('A006', 'The Weeknd', 'R&B', 'https://tinyurl.com/weeknd-img', 
 'Abel Tesfaye empezó subiendo canciones anónimas a YouTube. Ha redefinido el R&B moderno con sonidos ochenteros. Su canción Blinding Lights es oficialmente la más reproducida en la historia de las plataformas de streaming.'),

('A007', 'Bizarrap', 'Electrónica/Trap', 'https://tinyurl.com/biza-img', 
 'Productor argentino que empezó haciendo "Combos Locos" en YouTube. Sus Music Sessions han revolucionado la industria musical, logrando varios récords Guinness junto a artistas como Shakira.'),

('A008', 'Quevedo', 'Urbano', 'https://tinyurl.com/quevedo-img', 
 'Artista canario que saltó a la fama mundial con su sesión con Bizarrap (Quédate). En tiempo récord, logró posicionar a Canarias en el mapa musical global, siendo el artista español más escuchado en 2023.');

-- 2.4 AUDIO (Canciones de estos artistas)
INSERT INTO Audio (IdAudio, Nombre, Duracion, NReproducciones, Archivo, Tipo) VALUES 
('C001', 'Monaco', 267, 150000, NULL, 'cancion'), ('C002', 'Perro Negro', 162, 120000, NULL, 'cancion'),
('C003', 'Anti-Hero', 200, 180000, NULL, 'cancion'), ('C004', 'Cruel Summer', 178, 250000, NULL, 'cancion'),
('C005', 'Luna', 196, 95000, NULL, 'cancion'), ('C006', 'Bubalu', 225, 88000, NULL, 'cancion'),
('C007', 'Houdini', 185, 130000, NULL, 'cancion'), ('C008', 'Training Season', 209, 110000, NULL, 'cancion'),
('C009', 'Provenza', 210, 160000, NULL, 'cancion'), ('C010', 'TQG', 199, 210000, NULL, 'cancion'),
('C011', 'Starboy', 230, 300000, NULL, 'cancion'), ('C012', 'Save Your Tears', 215, 280000, NULL, 'cancion'),
('C013', 'Shakira Session 53', 217, 450000, NULL, 'cancion'), ('C014', 'Fradique Session', 190, 80000, NULL, 'cancion'),
('C015', 'Columbia', 192, 140000, NULL, 'cancion'), ('C016', 'Punto G', 150, 90000, NULL, 'cancion');

-- 2.5 ALBUMES ACTUALES
INSERT INTO Album (IdAlbum, Titulo, Año, Genero, Imagen, IdMusico) VALUES 
('AL001', 'Nadie Sabe Lo Que Va A Pasar', 2023, 'Urbano', 'https://tinyurl.com/alb-ns', 'A001'),
('AL002', 'Midnights', 2022, 'Pop', 'https://tinyurl.com/alb-mn', 'A002'),
('AL003', 'MOR No Le Temas a la Oscuridad', 2023, 'Reggaeton', 'https://tinyurl.com/alb-mor', 'A003'),
('AL004', 'Radical Optimism', 2024, 'Pop', 'https://tinyurl.com/alb-ro', 'A004'),
('AL005', 'Mañana Será Bonito', 2023, 'Urbano', 'https://tinyurl.com/alb-msb', 'A005'),
('AL006', 'After Hours', 2020, 'R&B', 'https://tinyurl.com/alb-ah', 'A006'),
('AL007', 'Bzrp Sessions Vol. 1', 2023, 'Electrónica', 'https://tinyurl.com/alb-bz', 'A007'),
('AL008', 'Donde Quiero Estar', 2023, 'Urbano', 'https://tinyurl.com/alb-dq', 'A008');

-- 2.6 CANCION (Relación Audio-Album)
INSERT INTO Cancion (IdCancion, IdAlbum, ArtistasInvitados) VALUES 
('C001', 'AL001', NULL), ('C002', 'AL001', 'Feid'),
('C003', 'AL002', NULL), ('C004', 'AL002', NULL),
('C005', 'AL003', NULL), ('C006', 'AL003', 'Rels B'),
('C007', 'AL004', NULL), ('C008', 'AL004', NULL),
('C009', 'AL005', NULL), ('C010', 'AL005', 'Shakira'),
('C011', 'AL006', 'Daft Punk'), ('C012', 'AL006', NULL),
('C013', 'AL007', 'Shakira'), ('C014', 'AL007', 'Fradique'),
('C015', 'AL008', NULL), ('C016', 'AL008', NULL);

-- ==========================================
-- 3. MANTENER PODCASTS (Tal cual pediste)
-- ==========================================
INSERT INTO Podcaster (IdPodcaster) VALUES 
('P001'),('P002'),('P003'),('P004'),('P005'),('P006'),('P007'),('P008'),('P009'),('P010'),
('P011'),('P012'),('P013'),('P014'),('P015'),('P016'),('P017'),('P018'),('P019'),('P020'),
('P021'),('P022'),('P023'),('P024'),('P025'),('P026');

INSERT INTO Audio (IdAudio, Nombre, Duracion, NReproducciones, Archivo, Tipo) VALUES 
('AU001', 'Episodio Podcast 1', 1200, 0, NULL, 'podcast'),
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
('AU001', 2, 'P001'), ('AU002', 1, 'P002'), ('AU003', 2, 'P002'),
('AU004', 1, 'P003'), ('AU005', 1, 'P003'), ('AU006', 0, 'P004'), ('AU007', 2, 'P004'),
('AU008', 1, 'P005'), ('AU009', 1, 'P005'), ('AU010', 3, 'P006'), ('AU011', 2, 'P006'),
('AU012', 1, 'P007'), ('AU013', 2, 'P007'), ('AU014', 2, 'P008'), ('AU015', 3, 'P008'),
('AU016', 1, 'P009'), ('AU017', 2, 'P009'), ('AU018', 0, 'P010'), ('AU019', 1, 'P010'),
('AU020', 1, 'P011'), ('AU021', 1, 'P011'), ('AU022', 2, 'P012'), ('AU023', 3, 'P012'),
('AU024', 1, 'P013'), ('AU025', 1, 'P013'), ('AU026', 1, 'P014'), ('AU027', 2, 'P014'),
('AU028', 2, 'P015'), ('AU029', 1, 'P015'), ('AU030', 3, 'P016'), ('AU031', 3, 'P016'),
('AU032', 1, 'P017'), ('AU033', 1, 'P017'), ('AU034', 2, 'P018'), ('AU035', 1, 'P018'),
('AU036', 1, 'P019'), ('AU037', 0, 'P019'), ('AU038', 2, 'P020'), ('AU039', 1, 'P020'),
('AU040', 1, 'P021'), ('AU041', 2, 'P021'), ('AU042', 0, 'P022'), ('AU043', 1, 'P022'),
('AU044', 3, 'P023'), ('AU045', 2, 'P023'), ('AU046', 1, 'P024'), ('AU047', 1, 'P024'),
('AU048', 0, 'P025'), ('AU049', 1, 'P025'), ('AU050', 2, 'P026'), ('AU051', 1, 'P026');

-- ==========================================
-- 4. CLIENTE Y PLAYLIST DE PRUEBA
-- ==========================================
INSERT INTO Cliente (IdCliente, Nombre, Apellido, IdIdioma, Usuario, Contraseña, FechaNacimiento, Tipo)
VALUES ('C001', 'Juan', 'Pérez', 'ES', 'juanp', '1234', '2000-05-10', 'Premium');

INSERT INTO Premium (IdCliente, FechaCaducidad) VALUES ('C001', '2026-12-31');

INSERT INTO Playlist (IdList, Titulo, FechaCreacion, IdCliente) VALUES (1, 'Favoritas 2026', '2026-05-07', 'C001');

INSERT INTO Playlist_Canciones (IdList, IdCancion, FechaPlaylist_Cancion) VALUES 
(1, 'C001', '2026-05-07'), (1, 'C003', '2026-05-07'), (1, 'C013', '2026-05-07');



USE Grupo6_Reto3_dam;

-- ==========================================
-- 1. AÑADIR MÁS ÁLBUMES (Random 1, 2 o 3 por artista)
-- ==========================================
INSERT INTO Album (IdAlbum, Titulo, Año, Genero, Imagen, IdMusico) VALUES 
-- Bad Bunny (A001) ya tiene 1, añadimos 2 más
('AL009', 'Un Verano Sin Ti', 2022, 'Urbano', 'https://tinyurl.com/uvst-img', 'A001'),
('AL010', 'YHLQMDLG', 2020, 'Urbano', 'https://tinyurl.com/yhlq-img', 'A001'),

-- Taylor Swift (A002) ya tiene 1, añadimos 1 más
('AL011', '1989 (Taylor Version)', 2023, 'Pop', 'https://tinyurl.com/1989-img', 'A002'),

-- Feid (A003) ya tiene 1, añadimos 2 más
('AL012', 'Feliz Cumpleaños Ferxxo', 2022, 'Reggaeton', 'https://tinyurl.com/fcf-img', 'A003'),
('AL013', 'Inter Shibuya', 2021, 'Reggaeton', 'https://tinyurl.com/ish-img', 'A003'),

-- Dua Lipa (A004) ya tiene 1, añadimos 1 más
('AL014', 'Future Nostalgia', 2020, 'Pop', 'https://tinyurl.com/fn-img', 'A004'),

-- Karol G (A005) ya tiene 1, añadimos 1 más
('AL015', 'KG0516', 2021, 'Urbano', 'https://tinyurl.com/kg-img', 'A005'),

-- The Weeknd (A006) ya tiene 1, añadimos 2 más
('AL016', 'Starboy Album', 2016, 'R&B', 'https://tinyurl.com/sb-img', 'A006'),
('AL017', 'Dawn FM', 2022, 'R&B', 'https://tinyurl.com/dfm-img', 'A006');

-- ==========================================
-- 2. AÑADIR MÁS AUDIOS (Para las nuevas canciones)
-- ==========================================
INSERT INTO Audio (IdAudio, Nombre, Duracion, NReproducciones, Archivo, Tipo) VALUES 
-- Canciones para AL009 (Bad Bunny)
('C017', 'Me Porto Bonito', 178, 500000, NULL, 'cancion'), 
('C018', 'Tití Me Preguntó', 243, 600000, NULL, 'cancion'),
('C019', 'Ojitos Lindos', 258, 450000, NULL, 'cancion'),
-- Canciones para AL011 (Taylor Swift)
('C020', 'Shake It Off', 219, 700000, NULL, 'cancion'),
('C021', 'Blank Space', 231, 650000, NULL, 'cancion'),
-- Canciones para AL012 (Feid)
('C022', 'Normal', 170, 300000, NULL, 'cancion'),
('C023', 'Ferxxo 100', 183, 280000, NULL, 'cancion'),
('C024', 'Feliz Cumpleaños Ferxxo Song', 155, 320000, NULL, 'cancion'),
('C025', 'Prohibidox', 161, 200000, NULL, 'cancion'),
-- Canciones para AL014 (Dua Lipa)
('C026', 'Levitating', 203, 800000, NULL, 'cancion'),
('C027', 'Don´t Start Now', 183, 750000, NULL, 'cancion'),
('C028', 'Physical', 193, 400000, NULL, 'cancion'),
-- Canciones para AL016 (The Weeknd)
('C029', 'I Feel It Coming', 269, 550000, NULL, 'cancion'),
('C030', 'Reminder', 218, 200000, NULL, 'cancion');

-- ==========================================
-- 3. VINCULAR CANCIONES A LOS ÁLBUMES
-- ==========================================
INSERT INTO Cancion (IdCancion, IdAlbum, ArtistasInvitados) VALUES 
('C017', 'AL009', 'Chencho Corleone'), 
('C018', 'AL009', NULL),
('C019', 'AL009', 'Bomba Estéreo'),
('C020', 'AL011', NULL),
('C021', 'AL011', NULL),
('C022', 'AL012', NULL),
('C023', 'AL012', NULL),
('C024', 'AL012', NULL),
('C025', 'AL012', NULL),
('C026', 'AL014', 'DaBaby'),
('C027', 'AL014', NULL),
('C028', 'AL014', NULL),
('C029', 'AL016', 'Daft Punk'),
('C030', 'AL016', NULL);



-- Actualizamos las rutas de los artistas con los nombres de archivo que tienes
UPDATE Artista SET Imagen = 'src/imagenes/artistas/1. Bad Bunnyimages.jfif' WHERE IdArtista = 'A001';
UPDATE Artista SET Imagen = 'src/imagenes/artistas/2. Taylor Swiftimages.jfif' WHERE IdArtista = 'A002';
UPDATE Artista SET Imagen = 'src/imagenes/artistas/3.Feidimages.jfif' WHERE IdArtista = 'A003';
UPDATE Artista SET Imagen = 'src/imagenes/artistas/4. Dua Lipaimages.jfif' WHERE IdArtista = 'A004';
UPDATE Artista SET Imagen = 'src/imagenes/artistas/5. Karol Gimages.jfif' WHERE IdArtista = 'A005';
UPDATE Artista SET Imagen = 'src/imagenes/artistas/6. The Weekndimages.jfif' WHERE IdArtista = 'A006';
UPDATE Artista SET Imagen = 'src/imagenes/artistas/7. Bizarrapimages.jfif' WHERE IdArtista = 'A007';
UPDATE Artista SET Imagen = 'src/imagenes/artistas/8. Quevedoimages.jfif' WHERE IdArtista = 'A008';

USE Grupo6_Reto3_dam;

-- Actualizamos las rutas con las mayúsculas correctas: src/Imagenes/Album/
UPDATE Album SET Imagen = 'src/Imagenes/Album/id(AL001)bad-bunny-nadie-sabe.jpg' WHERE IdAlbum = 'AL001';
UPDATE Album SET Imagen = 'src/Imagenes/Album/IdAlbum(AL002)descarga.jfif' WHERE IdAlbum = 'AL002';
UPDATE Album SET Imagen = 'src/Imagenes/Album/IdAlmub(AL003)descarga.jfif' WHERE IdAlbum = 'AL003';
UPDATE Album SET Imagen = 'src/Imagenes/Album/IdAlmub(AL004)descarga.jfif' WHERE IdAlbum = 'AL004';
UPDATE Album SET Imagen = 'src/Imagenes/Album/IdAlmub(AL005)descarga.jfif' WHERE IdAlbum = 'AL005';
UPDATE Album SET Imagen = 'src/Imagenes/Album/IdAlmub(AL006)descarga.jfif' WHERE IdAlbum = 'AL006';
UPDATE Album SET Imagen = 'src/Imagenes/Album/IdAlbum(AL007)descarga.jfif' WHERE IdAlbum = 'AL007';
UPDATE Album SET Imagen = 'src/Imagenes/Album/idalbum(AL008)descarga.jfif' WHERE IdAlbum = 'AL008';

  --   -----------------LOS AUDIOS ACTUALIZADO---------------------------
use grupo6_reto3_dam;

-- Bad Bunny
UPDATE Audio SET Archivo = 'src/canciones/monaco.mp3' WHERE IdAudio = 'C001';
UPDATE Audio SET Archivo = 'src/canciones/perronegro.mp3' WHERE IdAudio = 'C002';
UPDATE Audio SET Archivo = 'src/canciones/meportobonito.mp3' WHERE IdAudio = 'C017';
UPDATE Audio SET Archivo = 'src/canciones/TitíMePreguntó.mp3' WHERE IdAudio = 'C018';

UPDATE Audio SET Archivo = 'src/canciones/ojitoslindos.mp3' WHERE IdAudio = 'C019';

-- Taylor Swift
UPDATE Audio SET Archivo = 'src/canciones/antihero.mp3' WHERE IdAudio = 'C003';
UPDATE Audio SET Archivo = 'src/canciones/cruelsummer.mp3' WHERE IdAudio = 'C004';
UPDATE Audio SET Archivo = 'src/canciones/shakeitoff.mp3' WHERE IdAudio = 'C020';
UPDATE Audio SET Archivo = 'src/canciones/blankspace.mp3' WHERE IdAudio = 'C021';

-- Feid
UPDATE Audio SET Archivo = 'src/canciones/luna.mp3' WHERE IdAudio = 'C005';
UPDATE Audio SET Archivo = 'src/canciones/bubalu.mp3' WHERE IdAudio = 'C006';
UPDATE Audio SET Archivo = 'src/canciones/normal.mp3' WHERE IdAudio = 'C022';
UPDATE Audio SET Archivo = 'src/canciones/ferxxo100.mp3' WHERE IdAudio = 'C023';
UPDATE Audio SET Archivo = 'src/canciones/felizcumple.mp3' WHERE IdAudio = 'C024';
UPDATE Audio SET Archivo = 'src/canciones/prohibidox.mp3' WHERE IdAudio = 'C025';

-- Dua Lipa
UPDATE Audio SET Archivo = 'src/canciones/houdini.mp3' WHERE IdAudio = 'C007';
UPDATE Audio SET Archivo = 'src/canciones/trainingseason.mp3' WHERE IdAudio = 'C008';
UPDATE Audio SET Archivo = 'src/canciones/levitating.mp3' WHERE IdAudio = 'C026';
UPDATE Audio SET Archivo = 'src/canciones/dontstartnow.mp3' WHERE IdAudio = 'C027';
UPDATE Audio SET Archivo = 'src/canciones/physical.mp3' WHERE IdAudio = 'C028';

-- The Weeknd
UPDATE Audio SET Archivo = 'src/canciones/starboy.mp3' WHERE IdAudio = 'C011';
UPDATE Audio SET Archivo = 'src/canciones/saveyourtears.mp3' WHERE IdAudio = 'C012';
UPDATE Audio SET Archivo = 'src/canciones/ifeelitcoming.mp3' WHERE IdAudio = 'C029';
UPDATE Audio SET Archivo = 'src/canciones/reminder.mp3' WHERE IdAudio = 'C030';

-- Otros (Karol G, Shakira, Quevedo)
UPDATE Audio SET Archivo = 'src/canciones/provenza.mp3' WHERE IdAudio = 'C009';
UPDATE Audio SET Archivo = 'src/canciones/tqg.mp3' WHERE IdAudio = 'C010';
UPDATE Audio SET Archivo = 'src/canciones/shakira53.mp3' WHERE IdAudio = 'C013';
UPDATE Audio SET Archivo = 'src/canciones/gardensession.mp3' WHERE IdAudio = 'C014';
UPDATE Audio SET Archivo = 'src/canciones/columbia.mp3' WHERE IdAudio = 'C015';
UPDATE Audio SET Archivo = 'src/canciones/puntog.mp3' WHERE IdAudio = 'C016';




