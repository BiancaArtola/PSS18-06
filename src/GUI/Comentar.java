package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
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

public class Comentar extends JFrame {

	private JPanel contentPane;
	private String usuario;
	private JTextField textComentario;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Comentar frame = new Comentar();
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
	public Comentar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Comentario:");
		lblNewLabel.setBounds(5, 5, 424, 14);
		contentPane.add(lblNewLabel);
		
		textComentario = new JTextField();
		textComentario.setBounds(5, 19, 419, 176);
		contentPane.add(textComentario);
		textComentario.setColumns(10);
		
		JButton btnEnviarComentario = new JButton("Enviar comentario");
		btnEnviarComentario.setBounds(155, 206, 129, 23);
		contentPane.add(btnEnviarComentario);
		OyenteComentario oyente = new OyenteComentario();
	}
	
	class OyenteComentario implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String ruta = "comentarios.txt";
			File archivo = new File(ruta);
			BufferedWriter bw = null;
			try {
				FileWriter fw= new FileWriter(archivo, true);
				if(archivo.exists()) {
					bw = new BufferedWriter(fw);
					bw.write(usuario);
					bw.newLine();
					bw.write(textComentario.getText());   
				}
				bw.close();
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}	
	}

}
