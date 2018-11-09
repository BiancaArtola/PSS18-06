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
import java.awt.Font;

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
					Comentar frame = new Comentar("");
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
	public Comentar(String usuario) {
		this.usuario = usuario;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Comentario");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(15, 0, 424, 53);
		contentPane.add(lblNewLabel);
		
		textComentario = new JTextField();
		textComentario.setHorizontalAlignment(SwingConstants.CENTER);
		textComentario.setBounds(15, 52, 359, 159);
		contentPane.add(textComentario);
		textComentario.setColumns(10);
		
		JButton btnEnviarComentario = new JButton("Enviar comentario");
		btnEnviarComentario.setBounds(105, 222, 189, 23);
		contentPane.add(btnEnviarComentario);
		OyenteComentario oyente = new OyenteComentario();
		btnEnviarComentario.addActionListener(oyente);
	}
	
	class OyenteComentario implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (!textComentario.getText().equals("") ) {
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
						bw.newLine();
					}
					bw.close();
				}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Comentario enviado", "Aviso", JOptionPane.CLOSED_OPTION);
				setVisible(false);
			}
			else
				JOptionPane.showMessageDialog(null, "Ingrese un comentario", "Error", JOptionPane.ERROR_MESSAGE);
			}
	}

}
