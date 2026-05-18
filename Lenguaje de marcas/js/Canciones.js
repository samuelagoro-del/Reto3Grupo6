//evento de inicialización - arranca la lógica al estructurarse por completo el árbol dom
document.addEventListener("DOMContentLoaded", () => {
    //extracción del identificador del artista a través de la url del navegador
    const urlParams = new URLSearchParams(window.location.search);
    const artistaId = urlParams.get('id');

    //consulta fetch - solicita el archivo de base de datos json para mapear la información
    fetch('json/Artistas.json')
        .then(res => res.json())
        .then(data => {
            //método find - empareja el artista del json que coincida con el id extraído
            const artista = data.artistas.find(a => a.id == artistaId);
            if (artista) {

                // 1. Cargar Izquierda (Bio y Foto)
                document.getElementById("artista-foto").src = artista.foto;
                document.getElementById("artista-nombre-bio").textContent = artista.nombre.toUpperCase();
                document.getElementById("artista-genero").textContent = artista.genero;
                document.getElementById("artista-biografia").textContent = artista.biografia;
                document.getElementById("info-debut").textContent = artista.debut || "N/A";
                document.getElementById("info-genero").textContent = artista.genero || "N/A";

                // 2. Cargar Centro (Canciones)
                document.getElementById("bread-nombre").textContent = artista.nombre;

                //captura del listado de canciones y declaración del contador numérico secuencial
                const listaContenedor = document.getElementById("lista-canciones");
                let contadorGlobal = 1;

                //primer bucle foreach - recorre la colección de álbumes que posee el artista
                artista.albumes.forEach(album => {
                    //segundo bucle anidado - itera sobre cada canción dentro del álbum actual
                    album.canciones.forEach(cancion => {
                        const div = document.createElement("div");
                        div.classList.add("cancion-fila");
                        //inyección de la estructura de la fila con incremento automático del contador
                        div.innerHTML = `
                            <span class="posicion-numero">${contadorGlobal++}</span>
                            <img src="${album.foto_album ? album.foto_album : `https://via.placeholder.com/50/222/ff8c00?text=${album.titulo.substring(0, 5)}`}" alt="${album.titulo}" class="foto-cancion">
                            <div class="info-cancion">
                                <h4>${cancion.titulo}</h4>
                                <p>${album.titulo}</p>
                            </div>
                            <span class="duracion-txt">${cancion.duracion}</span>
                            <button class="play-orange"><i class="fa-solid fa-play"></i></button>                        `;
                        //añade la fila de la pista musical al listado central del reproductor
                        listaContenedor.appendChild(div);
                    });
                });

                // 3. Cargar Izquierda (Álbumes)
                const albumesContenedor = document.getElementById("contenedor-albumes-izq");
                albumesContenedor.innerHTML = ""; // Limpiar antes de cargar

                //bucle de renderizado de discos - mapea los álbumes para la sección lateral
                artista.albumes.forEach(album => {
                    const albDiv = document.createElement("div");
                    albDiv.classList.add("album-card-estilo-nuevo");

                    const srcImagen = album.foto_album || `https://via.placeholder.com/150`;

                    //maquetación de la tarjeta con estilo de portada flotante flotante y total de canciones
                    albDiv.innerHTML = `
                        <div class="portada-flotante">
                            <img src="${srcImagen}" alt="${album.titulo}">
                        </div>
                        <div class="album-card-info">
                            <h4>${album.titulo}</h4>
                            <p class="anio">${album.anio || '2022'}</p>
                            <p class="num-canciones">${album.canciones.length} canciones</p>
                        </div>
                    `;
                    //inyecta el disco diseñado dentro de la columna lateral izquierda
                    albumesContenedor.appendChild(albDiv);
                });
            }
        })
        //captura defensiva - reporta fallos en consola si la carga del artista se ve interrumpida
        .catch(error => console.error("Error cargando artista:", error));
});