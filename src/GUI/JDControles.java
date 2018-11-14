package GUI;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JDControles extends JDialog {
	
	private static final long serialVersionUID = 1L;
	//Atributes de clase
	private JPanel panelPrincipal;
	private JButton jbSalir;
	private JLabel jlBanner;
	
	public JDControles() {
		setTitle("Controles Juego");
		setSize(500,380);
		Image iconVentana = new ImageIcon(getClass().getResource("/Galaxian/Interfaz/iconVentana.png")).getImage();
		setIconImage(iconVentana);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		armar();
	}
	private void armar(){
		//Propiedades panel principal y definimos sus atributos
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(null);
		panelPrincipal.setBackground(new Color(000));
		
		//Creo titulos y campo a mostrar las entradas
		jlBanner = new JLabel();
		Icon imgBanner = new ImageIcon(this.getClass().getResource("/Galaxian/Interfaz/controles.png"));
		jlBanner.setIcon(imgBanner);
		jlBanner.setBounds(0,20, 500, 300);
		
		jbSalir = new JButton("");
		jbSalir.setBorder(null);
		jbSalir.setBorderPainted(false);
		jbSalir.setContentAreaFilled(false);
		jbSalir.setSelectedIcon(null);
		jbSalir.setIcon(new ImageIcon(this.getClass().getResource("/Galaxian/Interfaz/b_aceptar.png")));
		jbSalir.setPressedIcon(new ImageIcon(this.getClass().getResource("/Galaxian/Interfaz/b_aceptar_c.png")));
		jbSalir.setRolloverIcon(new ImageIcon(this.getClass().getResource("/Galaxian/Interfaz/b_aceptar_h.png")));
		jbSalir.setSelectedIcon(new ImageIcon(this.getClass().getResource("/Galaxian/Interfaz/b_aceptar_h.png")));
		jbSalir.setBounds(170, 280, 170, 44);
		jbSalir.setFocusable(false);
		jbSalir.setVisible(true);
		
		OyenteSalir oyenteSalir = new OyenteSalir();
		jbSalir.addActionListener(oyenteSalir);
		
		//Agregamos elementos al panelPrincipal
		panelPrincipal.add(jlBanner);
		panelPrincipal.add(jbSalir);
		
		//Agregamos el panel principal al contentPane		
		setContentPane(panelPrincipal);
	}
	//Oyente boton salir
	private class OyenteSalir implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//Cierro ventana
			dispose();
			}		
	}
}
