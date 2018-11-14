package Entidades.Enemigos;

import java.awt.Point;
import javax.swing.ImageIcon;
import Colisionador.Colisionador;
import Colisionador.ColisionadorEnemigoKamikaze;
import Inteligencia.Buscador;
import Inteligencia.Estados.Tontear;

/**
 * Clase TwoBehavior que extiende de Enemigo.
 * @author Aldana Casé (104870), Facundo Pierrestegui (99694), Stefania Heinrich (106205).
 *
 */
public class TwoBehavior  extends Enemigo{
	
	//Constructor
	public TwoBehavior(Point p) {
		super(p, 40, 40);
		
		this.imagen[0] = new ImageIcon(this.getClass().getResource("/Galaxian/Enemigos/twoBehavior.png"));
		this.imagen[1] = new ImageIcon(this.getClass().getResource("/Galaxian/Enemigos/twoBehavior_congelado.png"));
		this.imagen[2] = new ImageIcon(this.getClass().getResource("/Galaxian/Enemigos/twoBehavior.png"));
		this.imagen[3] = new ImageIcon(this.getClass().getResource("/Galaxian/Enemigos/twoBehavior.png"));
		this.imagen[4] = null;
		
		velocidad = 10;
		cantVidas = 1;
		porcentajeVida = 100;
		puntaje = 10;
		danioImpacto = 25;
		colisionador = new ColisionadorEnemigoKamikaze(this);
		inteligencia = new Buscador(this);
	}
	
	//Metodos
	
	public void meColisionan(Colisionador c) {
		c.colisionarEnemigoKamikaze(this);
	}
	
	public void restarVida(int i) {
		super.restarVida(i);
		if(porcentajeVida <= 50) {
			inteligencia.setEstado(new Tontear(inteligencia, this));
		}		
	}
	
}
