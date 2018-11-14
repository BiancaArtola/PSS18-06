package GUI;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;   
import Hilos.HiloPrincipal;
import Juego.JuegoGrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Clase GUI que extiende de JFrame.
 * @author Aldana Casé (104870), Facundo Pierrestegui (99694), Stefania Heinrich (106205).
 *
 */
public class gui extends JFrame {

	private static final long serialVersionUID = 1L;
	private  int width = 600;
	private int height = 700;
	
	private HiloPrincipal hiloPrincipal;
	
	//Atributos panel inicial
	private JPanel pInicio;
	private JLabel jlBanner;
	private JButton jbJugar;
	private JButton jbControles;
	private JButton jbSalir;
	
	//Atributos panel juego
	private JPanel pJuego;
	private JLabel nivel;
	private JLabel puntaje;
	private JLabel vida [];
	private JLabel fuerza;
	private JLabel congelar;
	private JButton reiniciar;
	private JButton salirJuego;
	private JLabel armEspecial;
	private int x_vida[];
	private Icon imagenVida[];
	private Icon imagenFuerza[];
	private Icon imagenCongelar[];
	private Icon imagenAEspecial[];
	
	//Atributos panel final
	private JPanel pFinal;
	private JLabel jlGanar;
	private JLabel jlPerder;
	private JLabel jlPuntaje;
	//private Icon imagenFinal[]; //se usa en el caso de que se agreguen imagenes en la pantalla final.
	private JButton jbVolverJugar;
	
	//Imagenes Botones ( normal: 0, press: 1, holding: 2 )
	private Icon imagenJugar[];
	private Icon imagenControles[];
	private Icon imagenVolverJugar[];
	private Icon imagenSalir[];
	
	private boolean esAdmin;
	private String usuario;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui frame = new gui(false, "");
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 * @param b 
	 */
	public gui(boolean bAdmin, String usuario) {
		this.usuario = usuario;
		esAdmin = bAdmin;
		setTitle("Galaxian");
		Image iconVentana = new ImageIcon(getClass().getResource("/Galaxian/Interfaz/iconVentana.png")).getImage();
		setIconImage(iconVentana);
		pantallaInicio();
		
	}
	
	
	//Metodos extras

	public int altoGrafica() {
		return height;
	}
	
	public int anchoGrafica() {
		return width;
	}
	
	protected void accionesJugador(KeyEvent key){
		
		JuegoGrafica juego = JuegoGrafica.getInstance();
		juego.obtenerJugador().accionar(key.getKeyCode());
		this.repaint();
		
	}
	
	/**
	 * Actualiza los indicadores de estado en la interfaz del juego.
	 */
	public void actualizarIndicadores() {
		/**
		 * TERMINAR
		 * Se actualizaran:
		 * Vida LISTO
		 * Puntaje Falta tama�o texto
		 * Nivel.Falta tama�o texto
		 */
		JuegoGrafica juego = JuegoGrafica.getInstance();
		
		puntaje.setText(""+juego.obtenerPuntaje());
		puntaje.setForeground(Color.white); 
		
		nivel.setText("Nivel: "+juego.obtenerNivel());
		nivel.setForeground(Color.white);
		
		actualizarVidas(juego.obtenerJugador().cantVidas(), juego.obtenerJugador().porcentajeVida());
	}
	
	/**
	 * Cambia el panel de la ventana al panel de fin de juego mostrando el resultado de la
	 * partida. Si el resultado recibido como parámetro es verdadero, se indica que se gano,
	 * caso contrario, se perdio.
	 * @param gano verdadero si se gano, caso conrario, se perdio.
	 */
	public void partidaFinalizada(boolean gano) {
		/**
		 * TERMINAR
		 * -Agregar:
		 * 
		 * 2) Poner el estado final de la partida con el puntaje obtenido.
		 * 4) Opcional, cambiar fondo.
		 */
		
		pantallaFinal();
		
		if(gano) {
			jlGanar.setText("�Has ganado! ");
			jlGanar.setForeground(Color.blue);
			//jlGanar.setIcon(imagenFinal[1]);
		    jlPuntaje.setForeground(Color.yellow); 	
		}
		else {
			jlPerder.setText("�Has perdido! ");
			jlPerder.setForeground(Color.red); 
			//jlPerder.setIcon(imagenFinal[0]);
		    jlPuntaje.setForeground(Color.white); 	
		}
		
		hiloPrincipal.stop();
	}
	
	public void setFuerza(boolean est) {
		if(est)
			fuerza.setIcon(imagenFuerza[1]);
		else
			fuerza.setIcon(imagenFuerza[0]);
			
	}
	
	public void setCongelarTiempo(boolean est) {
     	if(est)
				congelar.setIcon(imagenCongelar[1]);
			else
				congelar.setIcon(imagenCongelar[0]);
	}
	
	public void setArmaEspecial(boolean est) {
    	if(est)
			armEspecial.setIcon(imagenAEspecial[1]);
		else
			armEspecial.setIcon(imagenAEspecial[0]);
	}
	
	//METODOS PRIVADOS
	
	private void pantallaInicio() {
		setFocusable(true);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(width, height));
		setLocationRelativeTo(null);
		setResizable(false);
		
		pInicio = new JPanel();
		pInicio.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pInicio);
		pInicio.setLayout(null);
		pInicio.setBackground(new Color(000));
		
		// Imagen superior
		
		jlBanner = new JLabel();
		Icon imgBanner = new ImageIcon(this.getClass().getResource("/Galaxian/Interfaz/banner.png"));
		jlBanner.setIcon(imgBanner);
		jlBanner.setBounds(50,30, 500, 300);
		pInicio.add(jlBanner);
	
		
		//botones Juegar, controles, salir
		cargarImagenesBotones();
		cargarBotonesPanelInicial();
	}
	


	private void pantallaJuego() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				accionesJugador(arg0);
			}
		});
		setFocusable(true);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(width, height));
		setLocationRelativeTo(null);
		setResizable(false);
		pJuego = new JPanel();
		pJuego.setBorder(new EmptyBorder(5, 5, 5, 5));
		pJuego.setLayout(null);
		pJuego.setBackground(new Color(000));
		setContentPane(pJuego);
		
		crearIndicadores();
		
		JuegoGrafica juego = JuegoGrafica.getInstance();
		juego.establecerGrafica(this);
		juego.iniciarJuego();
		hiloPrincipal = new HiloPrincipal();
		hiloPrincipal.start();
		
		actualizarIndicadores();
	}
	
	private void pantallaFinal() {
		pFinal = new JPanel();
		pFinal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pFinal);
		pFinal.setLayout(null);
		pFinal.setBackground(new Color(000));
		
		/*imagenFinal = new Icon [2];
		imagenFinal[0] =  new ImageIcon(this.getClass().getResource("/Galaxian/Interfaz/fuerza_on.png"));
		imagenFinal[1] =  new ImageIcon(this.getClass().getResource("/Galaxian/Interfaz/fuerza_off.png"));
		*/
		jlGanar = new JLabel();
		//etiquetaGanar.setBounds(10, 10, 30, 30);
		//this.add(etiquetaGanar);
		
		jlGanar.setFont(new Font("Italica", Font.ITALIC, 15));
		jlGanar.setHorizontalAlignment(SwingConstants.CENTER);
		jlGanar.setBounds(200, 100, 150, 42);
		pFinal.add(jlGanar);
		
		jlPerder= new JLabel();
		//etiquetaPerder.setBounds(10, 10, 30, 30);
		//this.add(etiquetaPerder);
		
		jlPerder.setFont(new Font("Italica", Font.ITALIC, 15));
		jlPerder.setHorizontalAlignment(SwingConstants.CENTER);
		jlPerder.setBounds(200, 100, 150, 42);
		pFinal.add(jlPerder);
		
		JuegoGrafica juego = JuegoGrafica.getInstance();
		jlPuntaje = new JLabel();
		jlPuntaje.setText("Puntaje Obtenido: "+juego.obtenerPuntaje());
		jlPuntaje.setFont(new Font("Italica", Font.ITALIC, 15));
		jlPuntaje.setHorizontalAlignment(SwingConstants.CENTER);
		jlPuntaje.setBounds(200, 150, 150, 42);
		pFinal.add(jlPuntaje);
		
		cargarImagenesBotones();
		cargarBotonesPanelFinal();
	}
	
 	private void crearIndicadores(){
		cargarImagenes();
		
		//Nivel 
		nivel = new JLabel();
		nivel.setBounds(5, 5, 60, 20);
		getContentPane().add(nivel);
		
		//Puntaje
		puntaje = new JLabel();
		puntaje.setBounds(width - 40, 5, 60, 20);
		getContentPane().add(puntaje);
		
		//Vidas
		x_vida = new int[3];
		x_vida[0] = 5;
		x_vida[1] = 40;
		x_vida[2] = 75;
		
		vida = new JLabel[3];
		for(int i=0; i<vida.length; i++){
			vida[i] = new JLabel(imagenVida[0]);
			vida[i].setBounds(x_vida[i], height - 65, 30, 30);
			getContentPane().add(vida[i]);
		}
		
		//Fuerza
		fuerza = new JLabel();
		fuerza.setIcon(imagenFuerza[0]);
		fuerza.setBounds(width - 120,height - 65, 30, 30);
		getContentPane().add(fuerza);
		
		//Congelar tiempo
		congelar = new JLabel();
		congelar.setIcon(imagenCongelar[0]);
		congelar.setBounds(width - 80,height - 65, 30, 30);
		getContentPane().add(congelar);
		
		//Boton reiniciar
		reiniciar= new JButton("Reiniciar");
		reiniciar.setBounds(200, 5, 100, 20);
		pJuego.add(reiniciar);
		Oyente oyenteReiniciar= new Oyente();
		reiniciar.addActionListener(oyenteReiniciar);
		
		//Boton Salir
		salirJuego= new JButton("Salir");
		salirJuego.setBounds(300, 5, 100, 20);
		pJuego.add(salirJuego);
		OyenteSalir oyenteSalir= new OyenteSalir();
		salirJuego.addActionListener(oyenteSalir);
		
		//Arma especial
		armEspecial = new JLabel();
		armEspecial.setIcon(imagenAEspecial[0]);
		armEspecial.setBounds(width - 40,height - 65, 30, 30);
		getContentPane().add(armEspecial);
	}
 	
 	class Oyente implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			pJuego.setVisible(false);
			hiloPrincipal.stop();
			pantallaInicio();
		}
	}
 	
	class OyenteSalir implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}
	}
	
	private void actualizarVidas(int cantVidas, int porcVid) {
		
		
		switch (cantVidas){
		case 0 : {//No deberia entrar aca, peeeeeero por las dudas
			for(int i=0; i<vida.length; i++){
				vida[i].setIcon(imagenVida[4]);
				vida[i].setBounds(x_vida[i], height - 65, 30, 30);
			}
			break;
		}
		case 1 : {//Vida 1
			int cantV = cantVidas - 1;
			
			vida[cantV].setIcon(imagenVidaPorcentaje(porcVid));
			vida[cantV].setBounds(x_vida[cantV], height - 65, 30, 30);
			
			for(int i = (cantV + 1); i < 3; i++) {			
				vida[i].setIcon(imagenVida[4]);
				vida[i].setBounds(x_vida[i], height - 65, 30, 30);
			}
			break;
		}
		case 2 : {//Vida 2
			int cantV = cantVidas - 1;
			
			vida[0].setIcon(imagenVida[0]);
			vida[0].setBounds(x_vida[0], height - 65, 30, 30);
			
			vida[cantV].setIcon(imagenVidaPorcentaje(porcVid));
			vida[cantV].setBounds(x_vida[cantV], height - 65, 30, 30);
			
			vida[2].setIcon(imagenVida[4]);
			vida[2].setBounds(x_vida[2], height - 65, 30, 30);
			break;
		}
		case 3 : {//Vida 3
			int cantV = cantVidas - 1;
			
			for(int i = 0; i < (cantV + 1); i++) {			
				vida[i].setIcon(imagenVida[0]);
				vida[i].setBounds(x_vida[i], height - 65, 30, 30);
			}
			
			vida[cantV].setIcon(imagenVidaPorcentaje(porcVid));
			vida[cantV].setBounds(x_vida[cantV], height - 65, 30, 30);
			break;
		}
		}	
		
	}
	
	private Icon imagenVidaPorcentaje(int porcV) {
		Icon image = null;
		
		if(porcV == 0)
			image = imagenVida[4];
		if(porcV > 0 && porcV <= 25)
			image = imagenVida[3];
		if(porcV > 25 && porcV <= 50)
			image = imagenVida[2];
		if(porcV > 50 && porcV <= 75)
			image = imagenVida[1];
		if(porcV > 75 && porcV <= 100)
			image = imagenVida[0];
		
		return image;
	}
	
	private void cargarImagenes() {
		imagenVida = new Icon[5];
		imagenVida[0] = new ImageIcon(this.getClass().getResource("/Galaxian/Interfaz/vida100.png"));
		imagenVida[1] = new ImageIcon(this.getClass().getResource("/Galaxian/Interfaz/vida75.png"));
		imagenVida[2] = new ImageIcon(this.getClass().getResource("/Galaxian/Interfaz/vida50.png"));
		imagenVida[3] = new ImageIcon(this.getClass().getResource("/Galaxian/Interfaz/vida25.png"));
		imagenVida[4] = new ImageIcon(this.getClass().getResource("/Galaxian/Interfaz/vida0.png"));
		
		imagenFuerza = new Icon[2];
		imagenFuerza[0] = new ImageIcon(this.getClass().getResource("/Galaxian/Interfaz/fuerza_off.png"));
		imagenFuerza[1] = new ImageIcon(this.getClass().getResource("/Galaxian/Interfaz/fuerza_on.png"));
		
		imagenCongelar = new Icon[2];
		imagenCongelar[0] = new ImageIcon(this.getClass().getResource("/Galaxian/Interfaz/congelartiempo_off.png"));
		imagenCongelar[1] = new ImageIcon(this.getClass().getResource("/Galaxian/Interfaz/congelartiempo_on.png"));
		
		imagenAEspecial = new Icon[2];
		imagenAEspecial[0] = new ImageIcon(this.getClass().getResource("/Galaxian/Interfaz/proyectil_off.png"));
		imagenAEspecial[1] = new ImageIcon(this.getClass().getResource("/Galaxian/Interfaz/proyectil_on.png"));
		
	}
	
	private void cargarImagenesBotones() {
		imagenJugar = new Icon[3];
		imagenJugar[0] = new ImageIcon(this.getClass().getResource("/Galaxian/Interfaz/b_jugar.png"));
		imagenJugar[1] = new ImageIcon(this.getClass().getResource("/Galaxian/Interfaz/b_jugar_c.png"));
		imagenJugar[2] = new ImageIcon(this.getClass().getResource("/Galaxian/Interfaz/b_jugar_h.png"));
		
		imagenControles = new Icon[3];
		imagenControles[0] = new ImageIcon(this.getClass().getResource("/Galaxian/Interfaz/b_controles.png"));
		imagenControles[1] = new ImageIcon(this.getClass().getResource("/Galaxian/Interfaz/b_controles_c.png"));
		imagenControles[2] = new ImageIcon(this.getClass().getResource("/Galaxian/Interfaz/b_controles_h.png"));
		
		imagenVolverJugar = new Icon[3];
		imagenVolverJugar[0] = new ImageIcon(this.getClass().getResource("/Galaxian/Interfaz/b_volverJugar.png"));
		imagenVolverJugar[1] = new ImageIcon(this.getClass().getResource("/Galaxian/Interfaz/b_volverJugar_c.png"));
		imagenVolverJugar[2] = new ImageIcon(this.getClass().getResource("/Galaxian/Interfaz/b_volverJugar_h.png"));
		
		imagenSalir = new Icon[3];
		imagenSalir[0] = new ImageIcon(this.getClass().getResource("/Galaxian/Interfaz/b_salir.png"));
		imagenSalir[1] = new ImageIcon(this.getClass().getResource("/Galaxian/Interfaz/b_salir_c.png"));
		imagenSalir[2] = new ImageIcon(this.getClass().getResource("/Galaxian/Interfaz/b_salir_h.png"));
		
	}
	
	private void cargarBotonesPanelInicial() {
		//Atributos Principales
		jbJugar = new JButton("");
		jbJugar.setBorder(null);
		jbJugar.setBorderPainted(false);
		jbJugar.setContentAreaFilled(false);
		jbJugar.setSelectedIcon(null);
		jbJugar.setIcon(imagenJugar[0]);
		jbJugar.setPressedIcon(imagenJugar[1]);
		jbJugar.setRolloverIcon(imagenJugar[2]);
		jbJugar.setSelectedIcon(imagenJugar[2]);
		jbJugar.setBounds(145, 367, 310, 81);
		jbJugar.setFocusable(false);
		
		jbControles = new JButton("");
		jbControles.setBorder(null);
		jbControles.setBorderPainted(false);
		jbControles.setContentAreaFilled(false);
		jbControles.setSelectedIcon(null);
		jbControles.setIcon(imagenControles[0]);
		jbControles.setPressedIcon(imagenControles[1]);
		jbControles.setRolloverIcon(imagenControles[2]);
		jbControles.setSelectedIcon(imagenControles[2]);
		jbControles.setBounds(145, 468, 310, 81);
		jbControles.setFocusable(false);
		
		jbSalir = new JButton("");
		jbSalir.setBorder(null);
		jbSalir.setBorderPainted(false);
		jbSalir.setContentAreaFilled(false);
		jbSalir.setSelectedIcon(null);
		jbSalir.setIcon(imagenSalir[0]);
		jbSalir.setPressedIcon(imagenSalir[1]);
		jbSalir.setRolloverIcon(imagenSalir[2]);
		jbSalir.setSelectedIcon(imagenSalir[2]);
		jbSalir.setBounds(145, 569, 310, 81);
		jbSalir.setFocusable(false);
		
		//Oyentes
		OJugar oJugar = new OJugar();
		OControles oControles = new OControles();
		OSalir oSalir = new OSalir();
		jbJugar.addActionListener(oJugar);
		jbControles.addActionListener(oControles);
		jbSalir.addActionListener(oSalir);
		
		//Agregados al panel
		pInicio.add(jbJugar);
		pInicio.add(jbControles);
		pInicio.add(jbSalir);		
		
		logicaComentarios();
		
	}
	
	private void logicaComentarios() {
		JButton btnComentario = new JButton("");
		btnComentario.setForeground(Color.WHITE);
		btnComentario.setBackground(Color.BLACK);
		btnComentario.setBounds(10, 11, 151, 23);
		pInicio.add(btnComentario);// TODO Auto-generated method stub		
		
		JButton btnCerrarSesion = new JButton("Cerrar sesion");
		btnCerrarSesion.setForeground(Color.WHITE);
		btnCerrarSesion.setBackground(Color.BLACK);
		btnCerrarSesion.setBounds(433, 11, 151, 23);
		pInicio.add(btnCerrarSesion);
		OyenteBotonCerrar oyenteCerrar = new OyenteBotonCerrar();
		btnCerrarSesion.addActionListener(oyenteCerrar);
		
		if (esAdmin) {
			btnComentario.setText("Ver comentarios");
		}else {
			btnComentario.setText("Dejar comentario");
		}
		OyenteBotonComentarios oyenteBC = new OyenteBotonComentarios();
		btnComentario.addActionListener(oyenteBC);
	}
	
	private void cargarBotonesPanelFinal() {
		//Atributos Principales
		jbVolverJugar = new JButton("");
		jbVolverJugar.setBorder(null);
		jbVolverJugar.setBorderPainted(false);
		jbVolverJugar.setContentAreaFilled(false);
		jbVolverJugar.setSelectedIcon(null);
		jbVolverJugar.setIcon(imagenVolverJugar[0]);
		jbVolverJugar.setPressedIcon(imagenVolverJugar[1]);
		jbVolverJugar.setRolloverIcon(imagenVolverJugar[2]);
		jbVolverJugar.setSelectedIcon(imagenVolverJugar[2]);
		jbVolverJugar.setBounds(145, 468, 310, 81);
		jbVolverJugar.setFocusable(false);
		
		jbSalir = new JButton("");
		jbSalir.setBorder(null);
		jbSalir.setBorderPainted(false);
		jbSalir.setContentAreaFilled(false);
		jbSalir.setSelectedIcon(null);
		jbSalir.setIcon(imagenSalir[0]);
		jbSalir.setPressedIcon(imagenSalir[1]);
		jbSalir.setRolloverIcon(imagenSalir[2]);
		jbSalir.setSelectedIcon(imagenSalir[2]);
		jbSalir.setBounds(145, 569, 310, 81);
		jbSalir.setFocusable(false);
		
		//Oyentes
		OVolverJugar oVolverJugar = new OVolverJugar();
		OSalir oSalir = new OSalir();
		jbVolverJugar.addActionListener(oVolverJugar);
		jbSalir.addActionListener(oSalir);
		
		//Agregados al panel
		pFinal.add(jbVolverJugar);
		pFinal.add(jbSalir);
		
	}
	
	/**
	 * CLASES OYENTES
	 */
	
	//Oyente boton Jugar
	private class OJugar implements ActionListener{
		public void actionPerformed(ActionEvent e){
			pInicio.setVisible(false);
			pantallaJuego();
		}
	}
	
	//Oyente boton Jugar
	private class OControles implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
		}
	}
	
	//Oyente boton Jugar
	private class OVolverJugar implements ActionListener{
		public void actionPerformed(ActionEvent e){
			pFinal.setVisible(false);
			pantallaJuego();
		}
	}

	//Oyente boton salir
	private class OSalir implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//Cierro ventana
			dispose();
		}
	}
	
	private class OyenteBotonComentarios implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if (!esAdmin) {
				Comentar panelComentarios = new Comentar(usuario);
				panelComentarios.setVisible(true);
			}else {
				ComentarAdmin comentario = new ComentarAdmin();
				comentario.setVisible(true);
			}
		}
	}
	
	private class OyenteBotonCerrar implements ActionListener{
		public void actionPerformed(ActionEvent e){			
			dispose();
			InicioSesion inicio = new InicioSesion();
			inicio.setVisible(true);
		}
	}
}
