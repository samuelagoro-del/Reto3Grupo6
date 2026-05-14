document.addEventListener("DOMContentLoaded", () => {
    const contenedor = document.getElementById("contenedor-artistas");
    const buscador = document.getElementById("buscador");
    let listaArtistas = [];

    fetch('Artistas.json') 
        .then(response => {
            if (!response.ok) throw new Error("Error al cargar el JSON");
            return response.json();
        })
        .then(data => {
            listaArtistas = data.artistas;
            renderizarArtistas(listaArtistas);
        })
        .catch(error => {
            console.error("Error:", error);
            contenedor.innerHTML = `<p style="color:white;">Error al cargar artistas. Revisa la consola.</p>`;
        });

    function renderizarArtistas(artistas) {
        contenedor.innerHTML = ""; 

        artistas.forEach(artista => {
            const card = document.createElement("div");
            card.classList.add("card-artista");

            card.addEventListener("click", () => {
                window.location.href = `paginaArtista.html?id=${artista.id}`;
            });
            
            // Estructura interna estilo "Fila" (como en la imagen)
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
            contenedor.appendChild(card);
        });
    }

    buscador.addEventListener("input", (e) => {
        const filtro = e.target.value.toLowerCase();
        const filtrados = listaArtistas.filter(art => 
            art.nombre.toLowerCase().includes(filtro) || 
            art.genero.toLowerCase().includes(filtro)
        );
        renderizarArtistas(filtrados);
    });
});