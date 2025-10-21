package co.edu.unbosque.view;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;

public class VentanaPrincipal extends JFrame   {
	
	private JTextArea areaDeTexto;
	private JScrollPane scroll;
	private JButton btnCargarArchivo;
	private JButton btnBuscar;
	private JTextField txtTexto;
	private JCheckBox keySensitive;
	private File archivoSeleccionado;

	private static final Highlighter.HighlightPainter MATCH_PAINTER =
			new DefaultHighlighter.DefaultHighlightPainter(new Color(255, 235, 59));

	private final JLabel lblResultados = new JLabel("0 coincidencias");


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
		
		lblResultados.setBounds(20, 520, 300, 30);
		lblResultados.setFont(new Font("Segoe UI", Font.BOLD, 14));
		add(lblResultados);


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
			
			/*if (!seleccionado.getName().toLowerCase().endsWith(".txt")) {
			    JOptionPane.showMessageDialog(this, "Solo se permiten archivos con extensión .txt", 
			        "Advertencia", JOptionPane.WARNING_MESSAGE);
			    return;
			}*/

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

	public void limpiarResaltado() {
		areaDeTexto.getHighlighter().removeAllHighlights();
		lblResultados.setText("0 coincidencias");
	}


	public void resaltarCoincidencias(ArrayList<Integer> inicios, int largoPatron) {
		Highlighter h = areaDeTexto.getHighlighter();
		h.removeAllHighlights();

		if (inicios == null || inicios.isEmpty() || largoPatron <= 0) {
			lblResultados.setText("0 coincidencias");
			return;
		}

		int docLen = areaDeTexto.getDocument().getLength();

		for (Integer start : inicios) {
			if (start == null) continue;
			int desde = Math.max(0, start);
			int hasta = Math.min(desde + largoPatron, docLen);
			if (hasta > desde) {
				try {
					h.addHighlight(desde, hasta, MATCH_PAINTER);
				} catch (BadLocationException ignored) {}
			}
		}

		try {
			int first = Math.min(inicios.get(0), docLen);
			areaDeTexto.setCaretPosition(first);
			Rectangle r;
			try {
				r = areaDeTexto.modelToView2D(first).getBounds();
			} catch (Throwable t) {
				r = areaDeTexto.modelToView(first);
			}
			if (r != null) areaDeTexto.scrollRectToVisible(r);
		} catch (Exception ignored) {}

		lblResultados.setText(inicios.size() + " coincidencia(s)");
		areaDeTexto.requestFocusInWindow();
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
	public String getTextoArea() {
	    return areaDeTexto.getText();
	}



}
