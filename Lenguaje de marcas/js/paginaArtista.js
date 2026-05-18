//evento de inicio - ejecuta el script una vez estructurado el html del documento
document.addEventListener("DOMContentLoaded", () => {
    //lectura de parámetros - extrae el id del artista desde la dirección url de la página
    const urlParams = new URLSearchParams(window.location.search);
    const artistaId = urlParams.get('id');

    // Asignar el enlace dinámico al botón CANCIONES
    const linkCanciones = document.getElementById("link-canciones");
    const linkAlbumes = document.getElementById("link-albumes");
    if (linkCanciones && artistaId) {
        linkCanciones.href = `canciones.html?id=${artistaId}`;
        linkAlbumes.href = `canciones.html?id=${artistaId}`;
    }

    //control de errores preliminar - frena la ejecución si no se detecta ningún id en la url
    if (!artistaId) {
        document.getElementById("perfil-nombre").textContent = "ARTISTA NO ENCONTRADO";
        return;
    }

    //petición de datos - lee el json de artistas para localizar al músico seleccionado
    fetch('json/Artistas.json') 
        .then(response => {
            if (!response.ok) throw new Error("Error al cargar el JSON");
            return response.json();
        })
        .then(data => {
            //método de búsqueda - filtra el array para emparejar el id de la url con el id del json
            const artistaEncontrado = data.artistas.find(art => art.id == artistaId);
            if (artistaEncontrado) {
                renderizarPerfil(artistaEncontrado);
            } else {
                document.getElementById("perfil-nombre").textContent = "ARTISTA NO ENCONTRADO";
            }
        })
        .catch(error => console.error("Error:", error));

    //función de pintado de perfil - inyecta toda la información del artista en el dom
    function renderizarPerfil(artista) {
        // Textos Básicos y Bio
        document.getElementById("bread-nombre").textContent = artista.nombre;
        document.getElementById("perfil-nombre").textContent = artista.nombre.toUpperCase();
        document.getElementById("perfil-genero").textContent = artista.genero;
        document.getElementById("perfil-genero-mini").textContent = artista.genero;
        
        // Si el JSON tiene biografía, la mostramos; si no, texto por defecto
        document.getElementById("perfil-bio").textContent = artista.biografia || "Biografía no disponible en este momento. Más adelante se conectará con la base de datos XML.";

        // Imagen principal
        const imgElement = document.getElementById("perfil-img");
        imgElement.src = artista.foto;
        imgElement.alt = artista.nombre;
        // Imagen de respaldo en caso de que no cargue
        imgElement.onerror = function() { this.src = 'https://via.placeholder.com/300?text=FOTO'; };

        // Información detallada (Caja inferior y Datos rápidos)
        // Usamos "|| 'No disponible'" para que, si aún no has puesto los datos en otro artista, no salga vacío
        document.getElementById("info-debut").textContent = artista.debut || "-";
        document.getElementById("info-nombre-real").textContent = artista.nombre_real || "No disponible";
        document.getElementById("info-nacionalidad").textContent = artista.nacionalidad || "No disponible";
        document.getElementById("info-estilo").textContent = artista.estilo || "No disponible";
        document.getElementById("info-nacimiento").textContent = artista.nacimiento || "No disponible";
        document.getElementById("info-discografica").textContent = artista.discografica || "No disponible";
        document.getElementById("info-activo").textContent = artista.activo_desde || "No disponible";

        //preparación de la rejilla de álbumes - captura y vacía el contenedor previo
        const contenedorAlbumes = document.getElementById("contenedor-albumes");
        contenedorAlbumes.innerHTML = ""; 

        //condicional de álbumes - comprueba si el artista dispone de una lista musical
        if (artista.albumes && artista.albumes.length > 0) {
            //bucle iterador - mapea el listado de álbumes para maquetar sus tarjetas individuales
            artista.albumes.forEach(album => {
                const albumCard = document.createElement("div");
                albumCard.classList.add("album-card");

                // Verificamos si existe la foto del álbum, si no, ponemos un placeholder de color naranja
                const srcImagen = album.foto_album ? album.foto_album : `https://via.placeholder.com/150/222/ff8c00?text=${album.titulo.substring(0, 5)}`;

                albumCard.innerHTML = `
                    <img src="${srcImagen}" alt="${album.titulo}" onerror="this.src='https://via.placeholder.com/150/222/ff8c00?text=ERROR'">
                    <h4>${album.titulo}</h4>
                    <p>${album.año}</p>
                `;
                //inserción final - añade la ficha de disco al interior del contenedor de álbumes
                contenedorAlbumes.appendChild(albumCard);
            });
        } else {
            //mensaje de aviso - informa textualmente en pantalla si el array de discos está vacío
            contenedorAlbumes.innerHTML = "<p style='color: white;'>No hay álbumes registrados.</p>";
        }
    }
});