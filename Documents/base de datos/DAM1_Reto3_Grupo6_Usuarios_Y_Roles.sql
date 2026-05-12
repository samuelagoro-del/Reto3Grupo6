-- creacion de usuarios
create user Administrador identified by 'Admin';

create user Salomon identified by '123';

create user Ibon identified by 'koldo67';

-- creacion de rol de administrador
create role 'ROL_ADMIN';
grant all on grupo6_reto3_dam.* to 'ROL_ADMIN'
with grant option;

-- creacion de rol de empleado
create role 'ROL_EMPLEADO';
grant select on grupo6_reto3_dam.cliente to 'ROL_EMPLEADO';
grant select on grupo6_reto3_dam.premium to 'ROL_EMPLEADO';
grant select on grupo6_reto3_dam.playlist to 'ROL_EMPLEADO';
grant select on grupo6_reto3_dam.playlist_canciones to 'ROL_EMPLEADO';
grant select on grupo6_reto3_dam.gustos to 'ROL_EMPLEADO';
grant select on grupo6_reto3_dam.idioma to 'ROL_EMPLEADO';
grant select, insert, update, delete on grupo6_reto3_dam.album to 'ROL_EMPLEADO';
grant select, insert, update, delete on grupo6_reto3_dam.artista to 'ROL_EMPLEADO';
grant select, insert, update, delete on grupo6_reto3_dam.audio to 'ROL_EMPLEADO';
grant select, insert, update, delete on grupo6_reto3_dam.cancion to 'ROL_EMPLEADO';
grant select, insert, update, delete on grupo6_reto3_dam.musico to 'ROL_EMPLEADO';
grant select, insert, update, delete on grupo6_reto3_dam.podcast to 'ROL_EMPLEADO';
grant select, insert, update, delete on grupo6_reto3_dam.podcaster to 'ROL_EMPLEADO';

-- creacion de rol de cliente
create role 'ROL_CLIENTE';

GRANT SELECT ON Grupo6_Reto3_dam.Artista TO 'ROL_CLIENTE';
GRANT SELECT ON Grupo6_Reto3_dam.Album TO 'ROL_CLIENTE';
GRANT SELECT ON Grupo6_Reto3_dam.Cancion TO 'ROL_CLIENTE';
GRANT SELECT ON Grupo6_Reto3_dam.Podcast TO 'ROL_CLIENTE';
GRANT SELECT ON Grupo6_Reto3_dam.Idioma TO 'ROL_CLIENTE';

GRANT SELECT, INSERT, UPDATE, DELETE ON Grupo6_Reto3_dam.Playlist TO 'ROL_CLIENTE';
GRANT SELECT, INSERT, DELETE ON Grupo6_Reto3_dam.Playlist_Canciones TO 'ROL_CLIENTE';
GRANT SELECT, INSERT, DELETE ON Grupo6_Reto3_dam.Gustos TO 'ROL_CLIENTE';

GRANT SELECT, UPDATE (NReproducciones) ON Grupo6_Reto3_dam.Audio TO 'ROL_CLIENTE';

GRANT SELECT, UPDATE (Nombre, Apellido, Usuario, Contraseña, IdIdioma) ON Grupo6_Reto3_dam.Cliente TO 'ROL_CLIENTE';
-- se aplican los roles correspondientes
grant 'ROL_ADMIN' to Administrador;
grant 'ROL_CLIENTE' to Salomon;
grant 'ROL_EMPLEADO' to Ibon;
-- actualizar roles
FLUSH PRIVILEGES;
