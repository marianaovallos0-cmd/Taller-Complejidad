package co.edu.unbosque.view;

import javax.swing.*;

public class VentanaPrincipal extends JFrame {
	
	private JTextArea areaDeTexto;
	private JScrollPane scroll;
	private JButton btnCargarArchivo;
	private JButton btnBuscar;
	private JTextField txtTexto;
	private JCheckBox keySensitive;
	
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
		
		java.awt.Font fuenteGeneral = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14);
		areaDeTexto.setFont(fuenteGeneral);
		txtTexto.setFont(fuenteGeneral);
		btnBuscar.setFont(fuenteGeneral);
		btnCargarArchivo.setFont(fuenteGeneral);
		keySensitive.setFont(fuenteGeneral);

		
		
	}

	public JTextArea getAreaDeTexto() {
		return areaDeTexto;
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

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	public JTextField getTxtTexto() {
		return txtTexto;
	}

	public void setTxtTexto(JTextField txtTexto) {
		this.txtTexto = txtTexto;
	}

	public JCheckBox getKeySensitive() {
		return keySensitive;
	}

	public void setKeySensitive(JCheckBox keySensitive) {
		this.keySensitive = keySensitive;
	}
	
	
	

}
