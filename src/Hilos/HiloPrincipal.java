package Hilos;

import java.util.LinkedList;
import javax.swing.JLabel;
import Entidades.*;
import Entidades.Enemigos.Enemigo;
import Juego.JuegoHilo;

/**
 * Clase HiloPrincipal que extiende de Thread.
 * @author Aldana Casé (104870), Facundo Pierrestegui (99694), Stefania Heinrich (106205).
 *
 */
public class HiloPrincipal extends Thread{
	
	private LinkedList<Enemigo> enemigos;
	private LinkedList<Entidad> entidades;
	private LinkedList<Enemigo> elimEnemigos;
	private LinkedList<Entidad> elimEntidades;
	
	//Constructor
	public HiloPrincipal(){
		JuegoHilo juego = JuegoHilo.getInstance();
		enemigos = juego.obtenerEnemigos();
		entidades = juego.obtenerEntidades();
	}
	
	//Metodos
	
	@Override
	public void run() {
		JuegoHilo juego = JuegoHilo.getInstance();
		while(true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	
			elimEnemigos = new LinkedList<Enemigo>();
			elimEntidades = new LinkedList<Entidad>();
			
			colisionarEnemigos();
			elimEnemigos();
			
			colisionarEntidades();
			elimEntidades();
			
			juego.actualizarGrafica();
			
			if(!juego.hayEnemigos()) {
				juego.siguienteNivel();
			}
		}
		
	}

	//Metodos Privados
	
	/**
	 * Colisiona a los enemigos con las entidades y el jugador.
	 */
	private void colisionarEnemigos() {
		for(Enemigo enem : enemigos) {
			enem.mover();
			
			synchronized(entidades) {
			//Colisionar entidades
			for(Entidad ent: entidades) {
				if(colisionan(enem.getGrafico(), ent.getGrafico()) || colisionan(ent.getGrafico(), enem.getGrafico())) {
					enem.colisionar(ent);
				}	
			}
			}
			//colisionar Jugador
			JuegoHilo juego = JuegoHilo.getInstance();
			if(colisionan(enem.getGrafico(), juego.obtenerJugador().getGrafico()) || colisionan( juego.obtenerJugador().getGrafico(), enem.getGrafico())) {
				enem.colisionar(juego.obtenerJugador());
				verificarVidaJugador();
			}
			//agregar enemigo sin vida a eliminar
			if(enem.estoyMuerto()) {
				elimEnemigos.add(enem);
			}
		}
	}

	/**
	 * Colisiona las entidades con los enemigos y jugador.
	 */
	private void colisionarEntidades() {
		synchronized(entidades) {
		for(Entidad ent : entidades) {
			ent.mover();
			
			//Colisionar entidades con enemigos
			for(Enemigo enem : enemigos) {
				if(colisionan(ent.getGrafico(), enem.getGrafico()) || colisionan(enem.getGrafico(), ent.getGrafico())) {
					ent.colisionar(enem);
				}
			}
			
			//Colisionar entidades con entidades
			for(Entidad ent2: entidades) {
				if(ent != ent2) {
					if(colisionan(ent.getGrafico(), ent2.getGrafico()) || colisionan(ent2.getGrafico(), ent.getGrafico())) {
						ent.colisionar(ent2);
					}
				}
			}
			
			//colisionar Jugador
			JuegoHilo juego = JuegoHilo.getInstance();
			if(colisionan(ent.getGrafico(), juego.obtenerJugador().getGrafico()) || colisionan(juego.obtenerJugador().getGrafico(), ent.getGrafico()) ) {
				ent.colisionar(juego.obtenerJugador());
				verificarVidaJugador();
			}
			
			//agregar entidad sin vida a eliminar
			if(ent.estoyMuerto()) {
				elimEntidades.add(ent);
			}
		}
		}
	}
	
	/**
	 * Elimina del juego a los enemigos muertos.
	 */
	private void elimEnemigos() {
		JuegoHilo juego = JuegoHilo.getInstance();
		for(Enemigo enem : elimEnemigos) {
			juego.eliminarEnemigo(enem);
		}
	}
	
	//Elimina del juego a las entidades muertas.
	private void elimEntidades() {
		JuegoHilo juego = JuegoHilo.getInstance();
		for(Entidad ent : elimEntidades) {
			juego.eliminarEntidad(ent);
		}
	}
	
	/**
	 * Devuelve verdadero si las entidades colisionan, caso contrario falso.
	 * @param l1 label de entidad 1.
	 * @param l2 label de entidad 2.
	 * @return verdadero si colisionan, caso contrario falso.
	 */
	private boolean colisionan(JLabel l1, JLabel l2) {
		boolean colisionan = false;
		
		int x_centro = l1.getX() + l1.getWidth();
		int y_centro = l1.getY() + l1.getHeight();
		int x_centro2 = l2.getX() + l2.getWidth();
		int y_centro2 = l2.getY() + l2.getHeight();
		
		if((x_centro > l2.getX()) && (x_centro < x_centro2))
			if((y_centro > l2.getY()) && (y_centro < y_centro2)) 
				colisionan = true;
		
		return colisionan;
	}
	
	/**
	 * Verifica que el jugador sigue con vida, si esta muerto le indica al juego que murio.
	 */
	private void verificarVidaJugador() {
		JuegoHilo juego = JuegoHilo.getInstance();
		juego.actualizarGrafica();
		if (juego.obtenerJugador().estoyMuerto()) {
			juego.seMurioJugador();
		}
	}
}