USE Grupo6_Reto3_dam;

-- ARTISTA
INSERT INTO Artista VALUES
('A001','Bad Bunny','Reggaeton',NULL,'Artista puertorriqueño de música urbana');

-- MUSICO
INSERT INTO Musico VALUES
('A001','solista');

-- PODCASTER
INSERT INTO Podcaster VALUES
('P001');

-- IDIOMA
INSERT INTO Idioma VALUES
('ES','Español');

-- CLIENTE
INSERT INTO Cliente VALUES
('C001','Alvaro','Garcia','ES','alvaro23','1234pass','2000-05-12','2026-04-28','Premium');

-- PREMIUM
INSERT INTO Premium VALUES
('C001','2027-04-28');

-- AUDIO
INSERT INTO Audio VALUES
('AU001','Tití Me Preguntó',240,NULL,'cancion');

-- PODCAST
INSERT INTO Audio VALUES
('AU002','Charlando Tech',1800,NULL,'podcast');

INSERT INTO Podcast VALUES
('AU002',2,'P001');

-- ALBUM
INSERT INTO Album VALUES
('AL001','Un Verano Sin Ti',2022,'Reggaeton',NULL,'A001');

-- CANCION
INSERT INTO Cancion VALUES
('CA001','AU001','AL001','');

-- PLAYLIST
INSERT INTO Playlist VALUES
(1,'Favoritas','2026-04-28','C001');

-- PLAYLIST_CANCIONES
INSERT INTO Playlist_Canciones VALUES
(1,'CA001','2026-04-28');

-- GUSTOS
INSERT INTO Gustos VALUES
('C001','AU001');

-- REPRODUCCIONES
INSERT INTO Reproducciones VALUES
('C001','AU001','2026-04-28');