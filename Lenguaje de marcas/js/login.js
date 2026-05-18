// comprobación de página - ejecuta la lógica solo si el usuario está en la pantalla de login
if (document.body.id === "PaginaLogin") {
    
    //captura de elementos del dom necesarios para el formulario de acceso
    const Overlay1 = document.getElementById("loginOverlay");
    const mensaje = document.getElementById("mensaje");
    const entrarBtn = document.getElementById("entrar");

    //credenciales de acceso predefinidas para administrador y usuario estándar
    const admin = "admin";
    const contrasenaAdmin = "123";
    const usuario1 = "pepe";
    const contrasenaUsuario = "mami";

    //variable de control de estado para la sesión del usuario
    let sesionIniciada = false;

    //función principal de acceso - valida las credenciales y gestiona la redirección o el error
    function entrar() {
        const usuario = document.getElementById("usuario").value;
        const password = document.getElementById("password").value;

        //condicional que verifica si los datos coinciden con alguno de los usuarios válidos
        if ((usuario === admin && password === contrasenaAdmin) ||
            (usuario === usuario1 && password === contrasenaUsuario)) {
            
            //guarda el estado de autenticación de forma persistente en la sesión del navegador
            sessionStorage.setItem("logeado", "true");

            //muestra un mensaje visual de éxito en color verde
            mensaje.textContent = "Bienvenido...";
            mensaje.style.color = "green";
            
            //temporizador que efectúa la redirección a la página principal tras un breve retardo
            setTimeout(() => {
                window.location.href = "PaginaPrincipal.html";
            }, 1200);
            
        } else {
            //muestra un mensaje visual de error en color rojo si los datos fallan
            mensaje.textContent = "Usuario o contraseña incorrectos.";
            mensaje.style.color = "red";
        }
    }

    //escucha de evento click - ejecuta la validación al pinchar en el botón de entrar
    entrarBtn.addEventListener("click", entrar);

    //escucha de evento de teclado - permite iniciar sesión de forma cómoda pulsando la tecla enter
    document.addEventListener("keydown", (e) => {
        if (e.key === "Enter") {
            entrar();
        }
    });
}