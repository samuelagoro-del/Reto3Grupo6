/*
===============================================================================
ARCHIVO: consultas_modelo.sql
DESCRIPCIÓN: Listado de consultas SQL utilizadas en la lógica del Modelo (Java)
PROYECTO: Reto 3 - Grupo 6 (DAM)
===============================================================================
*/

-- ==========================================================
-- 1. GESTIÓN DE USUARIOS (LOGIN Y REGISTRO)
-- ==========================================================

-- Autenticar usuario y detectar si es Premium o Free mediante el LEFT JOIN
SELECT C.*, P.FechaCaducidad 
FROM Cliente C 
LEFT JOIN Premium P ON C.IdCliente = P.IdCliente 
WHERE C.Usuario = ? AND C.Contraseña = ?;

-- Obtener el ID más alto para generar el siguiente ID manual (C001, C002...)
SELECT MAX(IdCliente) FROM Cliente;

-- Registrar datos básicos del cliente
INSERT INTO Cliente (IdCliente, Nombre, Apellido, IdIdioma, Usuario, Contraseña, FechaNacimiento, FechaRegistro, Tipo) 
VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);

-- Registrar extensión premium si corresponde (Relación de herencia)
INSERT INTO Premium (IdCliente, FechaCaducidad) 
VALUES (?, ?);


-- ==========================================================
-- 2. CONSULTAS DE NAVEGACIÓN (MÚSICA Y PODCASTS)
-- ==========================================================

-- Mostrar discografía completa de un artista con conteo de canciones por álbum
SELECT art.NombreArtistico, art.Genero AS GeneroArtista, art.Descripcion, art.Imagen AS ImagenArtista, 
       a.Titulo AS NombreAlbum, a.Año, COUNT(c.IdCancion) AS NumeroCanciones 
FROM Artista art 
INNER JOIN Album a ON art.IdArtista = a.IdMusico 
LEFT JOIN Cancion c ON a.IdAlbum = c.IdAlbum 
WHERE art.IdArtista = ? 
GROUP BY a.IdAlbum, art.IdArtista 
ORDER BY a.Año DESC;

-- Listar canciones de un álbum (Puente entre Audio y Album)
SELECT au.Nombre 
FROM Audio au 
JOIN Cancion c ON au.IdAudio = c.IdCancion 
JOIN Album al ON c.IdAlbum = al.IdAlbum 
WHERE al.Titulo = ?;


-- ==========================================================
-- 3. GESTIÓN DE PLAYLISTS Y FAVORITOS
-- ==========================================================

-- Validar si el usuario Free ya tiene el límite de 3 playlists
SELECT COUNT(*) FROM Playlist WHERE IdCliente = ?;

-- Marcar como favorito (IGNORE evita errores si el usuario ya le dio a Like antes)
INSERT IGNORE INTO Gustos (IdCliente, IdAudio) 
VALUES (?, ?);

-- Borrar contenido de una lista (Necesario antes de borrar la Playlist por Integridad Referencial)
DELETE FROM Playlist_Canciones WHERE IdList = ?;


-- ==========================================================
-- 4. ESTADÍSTICAS (TOP 10)
-- ==========================================================

-- TOP 10 Canciones con más "Likes"
SELECT a.Nombre, COUNT(g.IdAudio) AS Total 
FROM Audio a 
JOIN Gustos g ON a.IdAudio = g.IdAudio 
WHERE a.Tipo = 'cancion' 
GROUP BY a.IdAudio 
ORDER BY Total DESC 
LIMIT 10;

-- TOP 10 Audios más reproducidos (Consulta directa a la columna de contador)
SELECT Nombre, NReproducciones 
FROM Audio 
ORDER BY NReproducciones DESC 
LIMIT 10;


-- ==========================================================
-- 5. ADMINISTRACIÓN (CRUD AVANZADO)
-- ==========================================================

-- Editar datos de artista sin afectar la integridad de sus álbumes
UPDATE Artista 
SET NombreArtistico = ?, Genero = ? 
WHERE IdArtista = ?;

-- Borrar canción filtrando específicamente por tipo (evita borrar podcasts por error)
DELETE FROM Audio 
WHERE Nombre = ? 
AND IdAudio IN (SELECT IdCancion FROM Cancion);

-- Inserción de Audio (Parte 1 de la transacción de subida)
INSERT INTO Audio (IdAudio, Nombre, Duracion, Archivo, Tipo) 
VALUES (?, ?, ?, ?, 'cancion');

-- Inserción de Canción (Parte 2 de la transacción de subida - Vincula al álbum)
INSERT INTO Cancion (IdCancion, IdAlbum) 
VALUES (?, ?);