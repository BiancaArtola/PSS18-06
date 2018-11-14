package Entidades;

import java.awt.event.KeyEvent;
import java.awt.Point;
import javax.swing.ImageIcon;
import Armas.*;
import Juego.JuegoJugador;
import Colisionador.*;

/**
 * Clase Jugador que extiende de Entidad.
 * @author Aldana Casé (104870), Facundo Pierrestegui (99694), Stefania Heinrich (106205).
 *
 */
public class Jugador extends Entidad{
	
	private Arma arma;
	private Arma armaEspecial;
	private boolean escudo;
	private boolean pausarDisparo;
	
	//Constructor
	public Jugador(Point p) {
		super(p, 40, 40);
		
		this.imagen[0] = new ImageIcon(this.getClass().getResource("/Galaxian/Jugador/jugador.png"));
		this.imagen[1] = new ImageIcon(this.getClass().getResource("/Galaxian/Jugador/jugador_escudo.png"));
		this.imagen[2] = new ImageIcon(this.getClass().getResource("/Galaxian/Jugador/jugador.png"));
		this.imagen[3] = new ImageIcon(this.getClass().getResource("/Galaxian/Jugador/jugador.png"));
		this.imagen[4] = null;
		
		arma = new AJSimple(this);
		armaEspecial = null;
		escudo = false;
		pausarDisparo = false;
		velocidad = 10;
		cantVidas = 3;
		porcentajeVida = 100;
		puntaje = 0;
		danioImpacto = 100;
		colisionador = new ColisionadorJugador(this);
	}
	
	//Metodos
	
	public void accionar(int d) {
		JuegoJugador j = JuegoJugador.getInstance();
		int ancho = j.obtenerGrafica().anchoGrafica();
		
		switch (d){
		case KeyEvent.VK_LEFT : {//Izquierda
			if ((pos.x - velocidad) < 0)
				pos.setLocation(0, pos.y);
			else pos.setLocation(pos.x - velocidad, pos.y);
			if(escudo)
				setGrafico(1);
			else setGrafico(0);
			break;
		}
		case KeyEvent.VK_RIGHT : {//Derecha
			if ((pos.x + velocidad) > (ancho - 50))//550)
				pos.setLocation((ancho - 50), pos.y);
			else pos.setLocation(pos.x + velocidad, pos.y);
			if(escudo)
				setGrafico(1);
			else setGrafico(0);
			break;
		}
		case KeyEvent.VK_SPACE : {//Disparo
			if(!pausarDisparo)
				arma.accionar();
			break;
		}
		case KeyEvent.VK_CONTROL : {//Disparo especial
			if( !pausarDisparo && armaEspecial != null) {
				armaEspecial.accionar();
				armaEspecial = null;
			}
			break;
		}
		}
	}
	
	/**
	 * Establece el arma recibida por parámetro al jugador.
	 * @param a arma.
	 */
	public void establecerArma(ArmaJugador a) {
		if(a != null)
			arma = a;
	}
	
	/**
	 * Establece el arma especial recibida por parámetro al jugador.
	 * @param a arma especial.
	 */
	public void establecerArmaEspecial(AJProyectil p) {
		if(p != null && armaEspecial == null) {
			armaEspecial = p;
		}
	}

	public void meColisionan(Colisionador c) {
		c.colisionarJugador(this);
	}
	
	/**
	 * Suma al puntaje del jugar el recibido como parámetro, si es negativo, le resta.
	 * @param puntj puntaje a sumar o restar.
	 */
	public void sumarPuntaje(int puntj) {
		puntaje = puntaje + puntj;
		if (puntaje < 0)
			puntaje = 0;
	}

	/**
	 * Agrega al jugador el porcentaje de vida recibido por parámetro.
	 * @param p porcentaje de vida.
	 */
	public void sumarPorcentajeVida(int p) {
		porcentajeVida = porcentajeVida + p;
		if(porcentajeVida > 100) {
			porcentajeVida = porcentajeVida - 100;
			cantVidas++;
		}
		if(cantVidas > 3) {
			cantVidas--;
			porcentajeVida = 100;
		}
	}
	
	/**
	 * Devuelve el arma del Jugador.
	 * @return arma jugador.
	 */
	public Arma obtenerArma() {
		return arma;
	}
	
	/**
	 * Activa o desactiva el escudo del jugador.
	 */
	public void activarEscudo() {
		if(escudo) {
			width = 40;
			height = 40;
			escudo = false;
			setGrafico(0);
		}
		else {
			escudo = true;
			width = 50;
			height = 50;
			setGrafico(1);
		}
	}
	
	/**
	 * Establece que el jugador no pueda disparar.
	 */
	public void pausarDisparos() {
		pausarDisparo = !pausarDisparo;
	}
	
	/**
	 * Devuelve verdadero si el jugador posee escudo, caso contrario falso.
	 * @return estado del escudo.
	 */
	public boolean tengoProteccion() {
		return escudo;
	}
	
	public void mover() {
		//Solo me muevo si me lo indica un ser vivo, no una maquina.
	}
}
