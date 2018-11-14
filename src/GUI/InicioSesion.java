package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class InicioSesion extends JFrame {

	private JPanel contentPane;
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
					InicioSesion frame = new InicioSesion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InicioSesion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		textContrasena = new JTextField();
		textContrasena.setBounds(129, 124, 167, 20);
		getContentPane().add(textContrasena);
		textContrasena.setColumns(10);
		
		textUsuario = new JTextField();
		textUsuario.setColumns(10);
		textUsuario.setBounds(129, 69, 167, 20);
		getContentPane().add(textUsuario);
		
		JLabel LabelUsuario = new JLabel("Usuario");
		LabelUsuario.setBounds(194, 55, 46, 14);
		getContentPane().add(LabelUsuario);
		
		JLabel LabelContrasena = new JLabel("Contrase\u00F1a");
		LabelContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		LabelContrasena.setBounds(157, 109, 105, 14);
		getContentPane().add(LabelContrasena);
		
		 btnIngresar = new JButton("Ingresar");
		btnIngresar.setBounds(129, 155, 167, 23);
		getContentPane().add(btnIngresar);
		
		 btnRegistrar = new JButton("Registrarse");
		btnRegistrar.setBounds(129, 185, 167, 23);
		getContentPane().add(btnRegistrar);
	
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
		
	class oyenteIngresar implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String usuario = textUsuario.getText();
			String contrasena = textContrasena.getText();
			boolean registrado = leerArchivo(usuario, contrasena);
			
			if (registrado) {
				// TODO Auto-generated method stub
				setVisible(false);
				gui gui2;
				if (usuario.equals("admin"))
					gui2= new gui(true, "admin");
				else
					gui2=new gui(false, usuario);
				gui2.show();
			}
			else {
				JOptionPane.showMessageDialog(null,
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
			setVisible(false);
			gui gui2= new gui(false, usuario);
			gui2.show();
		}
	}



}
