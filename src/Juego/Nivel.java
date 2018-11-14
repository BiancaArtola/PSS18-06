package Juego;

import java.io.*;
import java.util.*;
import java.awt.Point;
import Entidades.Enemigos.*;
import Entidades.PowerUps.PowerUp;

/**
 * Clase Nivel que se encarga de la carga de las entidades en el juego de acuerdo al nivel.
 * @author Aldana Casé (104870), Facundo Pierrestegui (99694), Stefania Heinrich (106205).
 */
public class Nivel {
	//Atributos de clase
	private static int N_Inicial = 1;
	private static int N_Final = 3;
	
	//Atributos de instancia
	private int N_Actual;
	private FabricaEntidades fabrica;
	
	//Constructor
	public Nivel() {
		N_Actual = N_Inicial;
		fabrica = new FabricaEntidades();
	}
	
	//Metodos
	
	/**
	 * Devuelve el nivel actual del juego
	 * @return nivel actual del juego.
	 */
	public int obtenerNivelActual(){
		return N_Actual;
	}
	
	/**
	 * Devuelve el nivel maximo del juego
	 * @return nivel maximo.
	 */
	public int obtenerNivelMax() {
		return N_Final;
	}
	
	/**
	 * Devuelve verdadero si se pudo establecer el siguiente nivel, caso contrario falso, indicando que no hay mas niveles.
	 * @return verdadero si se avanzo de nivel, caso contrario falso.
	 */
	public boolean siguienteNivel(){
		boolean haySig = false;
		if (N_Actual < N_Final) {
			N_Actual++;
			haySig = true;
		}
		return haySig;
	}
	
	/**
	 * Carga las entidades correspondientes al juego de acuerdo al nivel actual, requiere que se establezca el siguiente nivel.
	 */
	public void cargarEntidadesAlJuego() {
		cargarEnemigos();
		cargarObstaculos();
	}
	
	//Metodos privados
	
	/**
	 * Carga los enemigos al juego.
	 */
	private void cargarEnemigos(){
		LinkedList<Enemigo> list = new LinkedList<Enemigo>();
		JuegoNivel juego = JuegoNivel.getInstance();
		
		BufferedReader br = null;
		InputStream s = this.getClass().getResourceAsStream("/Galaxian/Niveles/n"+N_Actual+"_enemigos.txt");
				
		try {
			String sCurrentLine;
			br = new BufferedReader(new InputStreamReader(s));
			// Para cada linea del archivo
            while ((sCurrentLine = br.readLine()) != null) {
            	// Para cada letra de la linea
            	int i = 0;
            	while(i < sCurrentLine.length()){
            		char tipo = sCurrentLine.charAt(i); //Obtengo tipo de enemmigo
            		int x = 0;
            		int y = 0;
            		i++;
            		while(i < sCurrentLine.length() && sCurrentLine.charAt(i) != ' ') {
            			x = leerVariable(i, sCurrentLine);
            			i = i + 3;
            			y = leerVariable(i, sCurrentLine);
            			i = i + 3;
            		}
            		if(i < sCurrentLine.length())
            			if(sCurrentLine.charAt(i) == ' ')
            				i++;
            		Point p = new Point(x, y);
            		Enemigo enem = fabrica.crearEnemigo(tipo, p);
             		list.addLast(enem);
             		juego.agregarEnemigo(enem);             		
            	}
            }
			
		} catch (IOException u) { // Esto es por si ocurre un error
            u.printStackTrace();
        } finally { // Esto es para que, haya ocurrido error o no
            try {
                if (br != null)br.close(); // Cierre el archivo
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
		asignarPowerUps(list);	
	}
	
	/**
	 * Carga los obstaculos al juego.
	 */
	private void cargarObstaculos(){
		JuegoNivel juego = JuegoNivel.getInstance();
		
		BufferedReader br = null;
		InputStream s = this.getClass().getResourceAsStream("/Galaxian/Niveles/n"+N_Actual+"_obstaculos.txt");
		
		try {
			String sCurrentLine;
			br = new BufferedReader(new InputStreamReader(s));
			// Para cada linea del archivo
            while ((sCurrentLine = br.readLine()) != null) {
            	// Para cada letra de la linea
            	int i = 0;
            	while(i < sCurrentLine.length()){
            		char tipo = sCurrentLine.charAt(i); //Obtengo tipo de enemmigo
            		int x = 0;
            		int y = 0;
            		i++;
            		while(i < sCurrentLine.length() && sCurrentLine.charAt(i) != ' ') {
            			x = leerVariable(i, sCurrentLine);
            			i = i + 3;
            			y = leerVariable(i, sCurrentLine);
            			i = i + 3;
            		}
            		if(i < sCurrentLine.length())
            			if(sCurrentLine.charAt(i) == ' ')
            				i++;
            		Point p = new Point(x, y);
             		juego.agregarEntidad(fabrica.crearObstaculo(tipo, p));
            	}
            }
			
		} catch (IOException u) { // Esto es por si ocurre un error
            u.printStackTrace();
        } finally { // Esto es para que, haya ocurrido error o no
            try {
                if (br != null)br.close(); // Cierre el archivo
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
	}
	
	/**
	 * Lee desde el archivo la coordenada X o Y de tres numeros.
	 * @param i posición en la linea.
	 * @param sCurrentLine linea de archivo.
	 * @return variable.
	 */
	private int leerVariable(int i, String sCurrentLine) {
		String variable = "";
		int j = 0;
		while(j < 3 && i < sCurrentLine.length()){
			variable = variable + sCurrentLine.charAt(i);
			i++;
			j++;
		}
		return Integer.parseInt(variable);
	}
	
	/**
	 * Asigna los power ups a los enemigos.
	 * @param list lista de enemigos.
	 */
	private void asignarPowerUps(LinkedList<Enemigo> list) {
		//7
		Random rnd = new Random();
		Enemigo e;
		int cantAEntregar = rnd.nextInt(N_Actual) + 1;
		for(int i = 0; i <= cantAEntregar; i++) {
			PowerUp pU = fabrica.crearPowerUp(rnd.nextInt(7), new Point(0,0));
			e = list.get(rnd.nextInt(list.size()));
			while(e.doyPowerUp()) {
				e = list.get(rnd.nextInt(list.size()));
			}
			e.establecerPowerUp(pU);
		}
	}
}
