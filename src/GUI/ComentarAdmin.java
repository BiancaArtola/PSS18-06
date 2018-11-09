package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JTextField;

public class ComentarAdmin extends JFrame {

	private JPanel contentPane;
	private JTextArea textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComentarAdmin frame = new ComentarAdmin();
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
	public ComentarAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblComentariosDeUsuarios = new JLabel("Comentarios de usuarios:");
		lblComentariosDeUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 22));
		contentPane.add(lblComentariosDeUsuarios, BorderLayout.NORTH);
		
		textField = new JTextArea();
		textField.setEditable(false);
		contentPane.add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
		
		cargarComentarios();
	}

	private void cargarComentarios() {
		String ruta = "comentarios.txt";
		File archivo = new File(ruta);
		BufferedReader bw=null;
		try {
			bw = new BufferedReader(new FileReader(archivo));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String cadena;
		 try {
			while((cadena = bw.readLine())!=null) {
			   textField.setText(textField.getText()+"Usuario: "+cadena+"\r\n"); 
			   cadena = bw.readLine();
			   textField.setText(textField.getText()+"Comentario: "+cadena+"\n"); 
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	}

}
