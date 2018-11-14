package Colisionador;

import Entidades.Jugador;
import Entidades.Enemigos.Enemigo;
import Entidades.Enemigos.EnemigoArmado;
import Entidades.Obstaculos.Obstaculo;
import Entidades.PowerUps.PowerUp;
import Entidades.Disparos.*;

/**
 * Clase ColisionadorEnemigoArmado que extiende de Colisionador.
 * @author Aldana Casé (104870), Facundo Pierrestegui (99694), Stefania Heinrich (106205).
 *
 */
public class ColisionadorEnemigoArmado extends Colisionador{
	
	private EnemigoArmado enemigo;
	
	//Constructor
	public ColisionadorEnemigoArmado(EnemigoArmado e) {
		enemigo = e;
	}
	
	//Metodos

	public void colisionarJugador(Jugador j) {
		if(!enemigo.estoyMuerto()) {
			j.restarVida(enemigo.obtenerDanio());
			enemigo.restarVida(j.obtenerDanio());
			j.sumarPuntaje(-enemigo.getPuntaje());
		}
	}

	public void colisionarEnemigoArmado(Enemigo e) {
		//No hace nada
	}

	public void colisionarEnemigoKamikaze(Enemigo e){
		//No hace nada
	}
	
	public void colisionarObstaculo(Obstaculo o) {
		
	}
	
	public void colisionarObstaculoBarricada(Obstaculo o) {
		//No hace nada
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
