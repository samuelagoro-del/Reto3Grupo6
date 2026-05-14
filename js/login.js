if (document.body.id === "PaginaLogin") {
    const Overlay1 = document.getElementById("loginOverlay");
    const mensaje = document.getElementById("mensaje");
    const entrarBtn = document.getElementById("entrar");

    const admin = "admin";
    const contrasenaAdmin = "123";
    const usuario1 = "pepe";
    const contrasenaUsuario = "mami";

    let sesionIniciada = false;

    function entrar() {
        const usuario = document.getElementById("usuario").value;
        const password = document.getElementById("password").value;

        if ((usuario === admin && password === contrasenaAdmin) ||
            (usuario === usuario1 && password === contrasenaUsuario)) {
            
            sessionStorage.setItem("logeado", "true");

            mensaje.textContent = "Bienvenido...";
            mensaje.style.color = "green";
            
            // Redirige después de 1.2 segundos
            setTimeout(() => {
                window.location.href = "PaginaPrincipal.html";
            }, 1200);
            
        } else {
            mensaje.textContent = "Usuario o contraseña incorrectos.";
            mensaje.style.color = "red";
        }
    }
    // Ejecutar al hacer clic en el botón
    entrarBtn.addEventListener("click", entrar);

    // Ejecutar SOLO cuando se presione la tecla Enter
    document.addEventListener("keydown", (e) => {
        if (e.key === "Enter") {
            entrar();
        }
    });
}