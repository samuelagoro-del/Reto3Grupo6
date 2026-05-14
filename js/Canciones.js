document.addEventListener("DOMContentLoaded", () => {
    const urlParams = new URLSearchParams(window.location.search);
    const artistaId = urlParams.get('id');

    fetch('Artistas.json')
        .then(res => res.json())
        .then(data => {
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

                const listaContenedor = document.getElementById("lista-canciones");
                let contadorGlobal = 1;

                artista.albumes.forEach(album => {
                    album.canciones.forEach(cancion => {
                        const div = document.createElement("div");
                        div.classList.add("cancion-fila");
                        div.innerHTML = `
                            <span class="posicion-numero">${contadorGlobal++}</span>
                            <img src="${album.foto_album ? album.foto_album : `https://via.placeholder.com/50/222/ff8c00?text=${album.titulo.substring(0, 5)}`}" alt="${album.titulo}" class="foto-cancion">
                            <div class="info-cancion">
                                <h4>${cancion.titulo}</h4>
                                <p>${album.titulo}</p>
                            </div>
                            <span class="duracion-txt">${cancion.duracion}</span>
                            <button class="play-orange"><i class="fa-solid fa-play"></i></button>                        `;
                        listaContenedor.appendChild(div);
                    });
                });

                // 3. Cargar Izquierda (Álbumes)
                const albumesContenedor = document.getElementById("contenedor-albumes-izq");
                albumesContenedor.innerHTML = ""; // Limpiar antes de cargar

                artista.albumes.forEach(album => {
                    const albDiv = document.createElement("div");
                    albDiv.classList.add("album-card-estilo-nuevo");

                    const srcImagen = album.foto_album || `https://via.placeholder.com/150`;

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
                    albumesContenedor.appendChild(albDiv);
                });
            }
        })
        .catch(error => console.error("Error cargando artista:", error));
});