//evento principal - espera a que el dom esté completamente cargado antes de ejecutar el script
document.addEventListener("DOMContentLoaded", () => {
    //captura del contenedor de la rejilla de artistas y el input del buscador
    const contenedor = document.getElementById("contenedor-artistas");
    const buscador = document.getElementById("buscador");
    //variable global local para almacenar la lista de artistas obtenida del backend
    let listaArtistas = [];

    //petición fetch - carga los datos desde el archivo json local
    fetch('json/Artistas.json') 
        .then(response => {
            if (!response.ok) throw new Error("Error al cargar el JSON");
            return response.json();
        })
        .then(data => {
            //guarda el array en la variable de control e inicia el renderizado en pantalla
            listaArtistas = data.artistas;
            renderizarArtistas(listaArtistas);
        })
        .catch(error => {
            //captura y muestra de errores visuales si falla la lectura del json
            console.error("Error:", error);
            contenedor.innerHTML = `<p style="color:white;">Error al cargar artistas. Revisa la consola.</p>`;
        });

    //función de renderizado - genera dinámicamente las tarjetas html de cada artista
    function renderizarArtistas(artistas) {
        //limpia el contenedor para evitar duplicados al filtrar o recargar
        contenedor.innerHTML = ""; 

        //ciclo que recorre el array de artistas para construir cada elemento visual
        artistas.forEach(artista => {
            const card = document.createElement("div");
            card.classList.add("card-artista");

            //evento de redirección - lleva al usuario al perfil detallado inyectando el id en la url
            card.addEventListener("click", () => {
                window.location.href = `paginaArtista.html?id=${artista.id}`;
            });
            
            //Estructura interna estilo "Fila" (como en la imagen)
            card.innerHTML = `
                <div class="card-content">
                    <img src="${artista.foto}" alt="${artista.nombre}" onerror="this.src='https://via.placeholder.com/100'">
                    <div class="info-artista">
                        <h3>${artista.nombre.toUpperCase()}</h3>
                        <p>${artista.genero.toUpperCase()}</p>
                    </div>
                    <div class="play-icon">
                        <i class="fa-solid fa-circle-play"></i>
                    </div>
                </div>
            `;
            //inyecta la tarjeta ya construida dentro de la rejilla principal
            contenedor.appendChild(card);
        });
    }

    //evento de escucha del buscador - filtra los artistas por nombre o género mientras el usuario escribe
    buscador.addEventListener("input", (e) => {
        const filtro = e.target.value.toLowerCase();
        //realiza un filtrado flexible evaluando coincidencias en minúsculas
        const filtrados = listaArtistas.filter(art => 
            art.nombre.toLowerCase().includes(filtro) || 
            art.genero.toLowerCase().includes(filtro)
        );
        //vuelve a renderizar únicamente los artistas que cumplen con el criterio de búsqueda
        renderizarArtistas(filtrados);
    });
});