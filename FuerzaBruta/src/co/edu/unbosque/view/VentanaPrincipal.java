package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class VentanaPrincipal extends JFrame   {
	
	private JTextArea areaDeTexto;
	private JScrollPane scroll;
	private JButton btnCargarArchivo;
	private JButton btnBuscar;
	private JTextField txtTexto;
	private JCheckBox keySensitive;
	private File archivoSeleccionado;

	public VentanaPrincipal() {
		setTitle("Fuerza Bruta - Algoritmo KMP");
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null); 
		getContentPane().setBackground(new java.awt.Color(240, 248, 255)); 
		setLocationRelativeTo(null); 
	
		inicializarComponentes();
		
		setVisible(true);
	}
	
	private void inicializarComponentes() {

		areaDeTexto = new JTextArea();
		areaDeTexto.setEditable(false);
		areaDeTexto.setLineWrap(true);
		areaDeTexto.setWrapStyleWord(true);

		scroll = new JScrollPane(areaDeTexto);
		scroll.setBounds(20, 20, 740, 350);
		add(scroll);
		
		txtTexto = new JTextField();
		txtTexto.setBounds(20, 400, 250, 30);
		add(txtTexto);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(280, 400, 100, 30);
		add(btnBuscar);
		
		keySensitive = new JCheckBox("Key Sensitive");
		keySensitive.setBounds(400, 400, 150, 30);
		add(keySensitive);
		
		btnCargarArchivo = new JButton("Cargar Archivo");
		btnCargarArchivo.setBounds(20, 460, 200, 40);
		add(btnCargarArchivo);

		btnCargarArchivo.addActionListener(this::accionCargarArchivo);
		
		java.awt.Font fuenteGeneral = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14);
		areaDeTexto.setFont(fuenteGeneral);
		txtTexto.setFont(fuenteGeneral);
		btnBuscar.setFont(fuenteGeneral);
		btnCargarArchivo.setFont(fuenteGeneral);
		keySensitive.setFont(fuenteGeneral);

	}

	private void accionCargarArchivo(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int resp = chooser.showOpenDialog(this);
		if (resp == JFileChooser.APPROVE_OPTION) {
			File seleccionado = chooser.getSelectedFile();

			if (seleccionado != null) {
				try {
					String contenido = Files.readString(seleccionado.toPath(), StandardCharsets.UTF_8);
					archivoSeleccionado = seleccionado;
					areaDeTexto.setText(contenido);
					setTitle("Fuerza Bruta - Algoritmo KMP — " + seleccionado.getName());
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(this, "No se pudo leer el archivo:\n" + ex.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	public void popError(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public JTextArea getAreaDeTexto() {
		return areaDeTexto;
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public JTextField getTxtTexto() {
		return txtTexto;
	}

	public JCheckBox getKeySensitive() {
		return keySensitive;
	}

	public File getArchivoSeleccionado() {
		return archivoSeleccionado;
	}

	public void setAreaDeTexto(JTextArea areaDeTexto) {
		this.areaDeTexto = areaDeTexto;
	}

	public JScrollPane getScroll() {
		return scroll;
	}

	public void setScroll(JScrollPane scroll) {
		this.scroll = scroll;
	}

	public JButton getBtnCargarArchivo() {
		return btnCargarArchivo;
	}

	public void setBtnCargarArchivo(JButton btnCargarArchivo) {
		this.btnCargarArchivo = btnCargarArchivo;
	}

	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	public void setTxtTexto(JTextField txtTexto) {
		this.txtTexto = txtTexto;
	}

	public void setKeySensitive(JCheckBox keySensitive) {
		this.keySensitive = keySensitive;
	}

	public void setArchivoSeleccionado(File archivoSeleccionado) {
		this.archivoSeleccionado = archivoSeleccionado;
	}
}
