package vista;



import java.awt.Color;

import java.awt.Font;

import java.awt.Image;

import java.io.FileInputStream;

import javax.swing.BorderFactory;

import javax.swing.ImageIcon;

import javax.swing.JButton;

import javax.swing.JLabel;

import javax.swing.JOptionPane;

import javax.swing.JPanel;

import javax.swing.Timer;

import javax.swing.SwingConstants;

import javazoom.jl.player.Player;

import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;



public class VentanaReproducion extends JPanel {



	private static final long serialVersionUID = 1L;

	private VentanaPrincipal principal;


	private JLabel lblPortada, lblInformacion, lblCronometro;

	private JButton btnPlay, btnAnterior, btnSiguiente, btnMenu, btnGustokoa,btnUsuario;


	private Timer timer;

	private int segundosTranscurridos = 0;

	private int duracionTotalSegundos = 0;

	private boolean estaReproduciendo = false;



	private Player player;

	private Thread hiloMusica;

	private String rutaArchivoActual;

	private String nombreCancionActual;



	private String[] listaCancionesActuales; // Lista de canciones cargada (de un álbum o playlist)

	private int indiceActual = 0;

	private String nombreAlbumActual = "Varios";

	private String rutaImagenActual = "src/img/default.png";



	public VentanaReproducion(VentanaPrincipal principal) {

		this.principal = principal;


		setBounds(100, 100, 800, 500);

		setLayout(null);

		setBackground(new Color(192, 192, 192));



		// Botón Atrás

		JButton btnAtras = new JButton("Atrás");

		btnAtras.setBounds(20, 20, 80, 25);

		btnAtras.addActionListener(e -> {

			detenerReproduccion();

			principal.mostrarPantalla("PANTALLA_MENU");

		});

		add(btnAtras);



		// Botón Usuario

		btnUsuario = new JButton("Usuario");

		btnUsuario.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {


				principal.mostrarPantalla("PANTALLA_REGISTRO");



			}

		});

		btnUsuario.setFont(new Font("Tahoma", Font.PLAIN, 10));

		btnUsuario.setBounds(650, 20, 110, 25);

		add(btnUsuario);


		lblPortada = new JLabel("");

		lblPortada.setHorizontalAlignment(SwingConstants.CENTER);

		lblPortada.setBounds(242, 46, 272, 169);

		lblPortada.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		add(lblPortada);



		// BOTÓN MENÚ (Añadir a Playlist o Exportar)

		btnMenu = new JButton("Menú");

		btnMenu.setBounds(114, 279, 90, 30);

		btnMenu.addActionListener(e -> {

			String[] opciones = {"Añadir a playlist", "Exportar canción a .txt"};

			int seleccion = JOptionPane.showOptionDialog(this, "Selecciona una opción", "Menú de Audio",

					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);


			if (seleccion == 0) {

				abrirSelectorPlaylist();

			} else if (seleccion == 1) {

				principal.getControlador().exportarPlaylistAFichero(nombreCancionActual);

				JOptionPane.showMessageDialog(this, "Datos de la canción exportados.");

			}

		});

		add(btnMenu);



		// BOTÓN ANTERIOR

		btnAnterior = new JButton("<");

		btnAnterior.setBounds(214, 279, 60, 30);

		btnAnterior.addActionListener(e -> {

			if (listaCancionesActuales != null && indiceActual > 0) {

				cambiarCancion(indiceActual - 1);

			}

		});

		add(btnAnterior);



		// BOTÓN PLAY / PAUSE

		btnPlay = new JButton("Play");

		btnPlay.setBounds(306, 279, 120, 30);

		btnPlay.addActionListener(e -> {

			if (!estaReproduciendo) {

				reproducirReal();

			} else {

				detenerReproduccion();

			}

		});

		add(btnPlay);



		// BOTÓN SIGUIENTE

		btnSiguiente = new JButton(">");

		btnSiguiente.setBounds(437, 279, 60, 30);

		btnSiguiente.addActionListener(e -> {

			if (listaCancionesActuales != null && indiceActual < listaCancionesActuales.length - 1) {

				cambiarCancion(indiceActual + 1);

			}

		});

		add(btnSiguiente);



		// BOTÓN FAVORITO (GUSTOKOA)

		btnGustokoa = new JButton("Favorito");

		btnGustokoa.setBounds(525, 279, 100, 30);

		btnGustokoa.addActionListener(e -> {

			if (nombreCancionActual != null) {

				principal.getControlador().guardarFavorito(nombreCancionActual);

				JOptionPane.showMessageDialog(this, "¡Añadido a tus favoritos!");

			}

		});

		add(btnGustokoa);



		// CRONÓMETRO

		lblCronometro = new JLabel("0:00 / 0:00");

		lblCronometro.setHorizontalAlignment(SwingConstants.CENTER);

		lblCronometro.setFont(new Font("Tahoma", Font.BOLD, 14));

		lblCronometro.setBounds(296, 226, 218, 42);

		add(lblCronometro);



		lblInformacion = new JLabel("<html>Información:<br>Canción: -<br>Álbum: -</html>");

		lblInformacion.setVerticalAlignment(SwingConstants.TOP);

		lblInformacion.setBounds(26, 320, 400, 72);

		add(lblInformacion);



		timer = new Timer(1000, e -> {

			if (segundosTranscurridos < duracionTotalSegundos) {

				segundosTranscurridos++;

				actualizarCronometro();

			} else {

				detenerReproduccion();

				// Opcional: saltar a la siguiente automáticamente

				if (indiceActual < listaCancionesActuales.length - 1) {

					cambiarCancion(indiceActual + 1);

				}

			}

		});

	}



	private void abrirSelectorPlaylist() {

		String[] playlists = principal.getControlador().pedirPlaylistsUsuario();

		if (playlists != null && playlists.length > 0) {

			String seleccion = (String) JOptionPane.showInputDialog(this, "Selecciona una playlist:",

					"Añadir a Playlist", JOptionPane.QUESTION_MESSAGE, null, playlists, playlists[0]);


			if (seleccion != null) {

				principal.getControlador().añadirCancionAPlaylist(nombreCancionActual, seleccion);

				JOptionPane.showMessageDialog(this, "Añadida correctamente.");

			}

		} else {

			JOptionPane.showMessageDialog(this, "No tienes playlists creadas. Ve a la sección Playlists.");

		}

	}



	// MÉTODO CLAVE: Carga una lista de canciones (desde álbum o desde playlist)

	public void actualizarListaReproduccion(String[] canciones) {

		if (canciones != null && canciones.length > 0) {

			this.listaCancionesActuales = canciones;

			this.indiceActual = 0;

			cambiarCancion(0); // Empieza por la primera de la lista

		}

	}



	private void reproducirReal() {

		if (rutaArchivoActual == null || rutaArchivoActual.isEmpty()) return;


		hiloMusica = new Thread(() -> {

			try {

				java.io.File archivo = new java.io.File("src/musica/" + rutaArchivoActual); // Asegúrate de que la ruta sea correcta

				if (archivo.exists()) {

					player = new Player(new FileInputStream(archivo));

					player.play();

				}

			} catch (Exception e) { e.printStackTrace(); }

		});

		hiloMusica.start();

		timer.start();

		btnPlay.setText("Pause");

		estaReproduciendo = true;

	}


	public void detenerReproduccion() {

		if (player != null) player.close();

		if (timer != null) timer.stop();

		estaReproduciendo = false;

		btnPlay.setText("Play");

	}



	public void cargarDatosAudio(String titulo, String album, String img, int seg, String mp3, String[] listaTotal) {

		this.nombreCancionActual = titulo;

		this.duracionTotalSegundos = seg;

		this.segundosTranscurridos = 0;

		this.rutaArchivoActual = mp3;

		this.listaCancionesActuales = listaTotal;

		this.nombreAlbumActual = album;

		this.rutaImagenActual = (img != null) ? img : "src/img/default.png";



		lblInformacion.setText("<html><b>Información:</b><br>Canción: " + titulo +

				"<br>Álbum: " + album + "</html>");

		actualizarCronometro();


		try {

			ImageIcon icon = new ImageIcon(rutaImagenActual);

			Image portada = icon.getImage().getScaledInstance(272, 169, Image.SCALE_SMOOTH);

			lblPortada.setIcon(new ImageIcon(portada));

		} catch (Exception e) { lblPortada.setIcon(null); }

	}



	private void cambiarCancion(int nuevaPosicion) {

		this.indiceActual = nuevaPosicion;

		String nuevoNombre = listaCancionesActuales[indiceActual];

		String[] datos = principal.getControlador().pedirDatosReproduccion(nuevoNombre);


		if (datos != null) {

			detenerReproduccion();

			// Los índices de 'datos' dependen de tu SQL: 0:Nombre, 1:Duracion, 2:Archivo

			cargarDatosAudio(nuevoNombre, nombreAlbumActual, rutaImagenActual,

					Integer.parseInt(datos[1]), datos[2], listaCancionesActuales);

			reproducirReal();

		}

	}



	private void actualizarCronometro() {

		lblCronometro.setText(formatearTiempoSolo(segundosTranscurridos) + " / " + formatearTiempoSolo(duracionTotalSegundos));

	}



	private String formatearTiempoSolo(int totalSegundos) {

		return String.format("%d:%02d", totalSegundos / 60, totalSegundos % 60);

	}


	public void setNombreUsuario(String nombre) {

		btnUsuario.setText(nombre);

	}

}