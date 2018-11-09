package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class InicioSesion {

	private JFrame frame;
	private JTextField textContrasena;
	private JTextField textUsuario;
	private JButton btnIngresar;
	private JButton btnRegistrar;
	private File archivo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioSesion window = new InicioSesion();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InicioSesion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textContrasena = new JTextField();
		textContrasena.setBounds(98, 84, 133, 20);
		frame.getContentPane().add(textContrasena);
		textContrasena.setColumns(10);
		
		textUsuario = new JTextField();
		textUsuario.setColumns(10);
		textUsuario.setBounds(98, 29, 133, 20);
		frame.getContentPane().add(textUsuario);
		
		JLabel LabelUsuario = new JLabel("Usuario");
		LabelUsuario.setBounds(140, 11, 46, 14);
		frame.getContentPane().add(LabelUsuario);
		
		JLabel LabelContrasena = new JLabel("Contrase\u00F1a");
		LabelContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		LabelContrasena.setBounds(108, 60, 105, 14);
		frame.getContentPane().add(LabelContrasena);
		
		 btnIngresar = new JButton("Ingresar");
		btnIngresar.setBounds(124, 113, 89, 20);
		frame.getContentPane().add(btnIngresar);
		
		 btnRegistrar = new JButton("Registrarse");
		btnRegistrar.setBounds(124, 138, 89, 23);
		frame.getContentPane().add(btnRegistrar);
	
		manejoBotones();
		
	}
	
	private void escribirArchivo(String usuario, String contrasena) throws IOException {
		 String ruta = "archivo.txt";
	        File archivo = new File(ruta);
	        BufferedWriter bw = null;
	        FileWriter fw= new FileWriter(archivo, true);
	        if(archivo.exists()) {
	            bw = new BufferedWriter(fw);
	            bw.write(usuario);
	            bw.newLine();
	            bw.write(contrasena);
	            bw.newLine();
	        }
		
		bw.close();
	}
	
	private boolean leerArchivo(String usuario, String contrasena) {
		String ruta = "archivo.txt";
		File archivo = new File(ruta);
		BufferedReader bw=null;
		try {
			bw = new BufferedReader(new FileReader(archivo));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String cadena;
		boolean encontre = false;
		 try {
			while((cadena = bw.readLine())!=null && encontre==false) {
			      encontre = buscarUsuario(bw, cadena, usuario, contrasena);
			  }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      return encontre;
	}

	private boolean buscarUsuario(BufferedReader bw, String cadena, String usuario, String contrasena) throws IOException {
		if (cadena.equals(usuario)) {
			try {
				cadena = bw.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (cadena.equals(contrasena))
				return true;		
		}else
			bw.readLine();
		return false;
	}

	private void manejoBotones() {		
		btnIngresar.addActionListener(new oyenteIngresar());
		btnRegistrar.addActionListener(new oyenteRegistrar());		
	}
	
	private void abrir (){         
		try{                 
			FileReader lector = new FileReader(archivo);
			BufferedReader bufer = new BufferedReader(lector);
			String linea = "";
			linea = bufer.readLine();

			while((linea = bufer.readLine()) != null){
				System.out.println(linea);
			}
			
			bufer.close();
			lector.close();


		}catch(Exception e){                 
			e.printStackTrace();         
		}
	}
	
	private void guardar (){        
		try{
			FileWriter escritura = new FileWriter(archivo, true);
			BufferedWriter Bsalida = new BufferedWriter(escritura);
			PrintWriter cp = new PrintWriter(escritura);

	/*		if(area.getText()!= null){
				StringTokenizer tk = new StringTokenizer(area.getText(),"\n");
				cp.println(area.getText());
			}*/

			cp.close();
			Bsalida.close();
			escritura.close();

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
	class oyenteIngresar implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String usuario = textUsuario.getText();
			String contrasena = textContrasena.getText();
			boolean registrado = leerArchivo(usuario, contrasena);
			
			if (registrado) {
				// TODO Auto-generated method stub
				frame.setVisible(false);
				gui gui2;
				if (usuario.equals("admin"))
					gui2= new gui(true, "admin");
				else
					gui2=new gui(false, usuario);
				gui2.show();
			}
			else {
				JOptionPane.showMessageDialog(frame,
					    "Usuario o contrasena incorrectos.",
					    "Error",
					    JOptionPane.ERROR_MESSAGE);
			}
				
		}
	}
	
	class oyenteRegistrar implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String usuario = textUsuario.getText();
			String contrasena = textContrasena.getText();
			try {
				escribirArchivo(usuario, contrasena);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			frame.setVisible(false);
			gui gui2= new gui(false, usuario);
			gui2.show();
		}
	}
}
