package Colisionador;

import Entidades.Jugador;
import Entidades.Disparos.*;
import Entidades.Enemigos.Enemigo;
import Entidades.Obstaculos.Obstaculo;
import Entidades.PowerUps.PowerUp;

/**
 * Clase ColisionadorDisparoEnemigo que extiende de Colisionador.
 * @author Aldana Casé (104870), Facundo Pierrestegui (99694), Stefania Heinrich (106205).
 *
 */
public class ColisionadorDisparoEnemigo extends Colisionador{
	
	private DisparoEnemigo disparo;
	
	//Constructor
	public ColisionadorDisparoEnemigo(DisparoEnemigo d) {
		disparo = d;
	}
	
	//Metodos
	
	public void colisionarJugador(Jugador j) {
		j.restarVida(disparo.obtenerDanio());
		j.sumarPuntaje(disparo.getPuntaje());
		disparo.restarVida(100);
	}

	public void colisionarEnemigoArmado(Enemigo e) {
		//No hace nada
	}
	
	public void colisionarEnemigoKamikaze(Enemigo e){
		//No hace nada
	}

	public void colisionarObstaculo(Obstaculo o) {
		if(!disparo.estoyMuerto() && !o.estoyMuerto()) {
			o.restarVida(disparo.obtenerDanio());
			disparo.restarVida(100);
		}
	}
	
	public void colisionarObstaculoBarricada(Obstaculo o) {
		//Los disparos de los enemigos no le hacen nada
	}

	public void colisionarPowerUp(PowerUp pU) {
		//No hace nada
	}

	public void colisionarDisparoJugador(DisparoJugador dJ) {
		//No hace nada
	}

	public void colisionarDisparoEnemigo(DisparoEnemigo dE) {
		//No hace nada
	}
}
