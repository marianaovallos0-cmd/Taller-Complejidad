package co.edu.unbosque.controller;

import java.util.ArrayList;
import javax.swing.JOptionPane;

import co.edu.unbosque.model.Archivo;
import co.edu.unbosque.model.BusquedaKMP;
import co.edu.unbosque.view.VentanaPrincipal;

public class Controller {
	
	private Archivo archivo = new Archivo();
	private BusquedaKMP busquedaKMP = new BusquedaKMP();
	private VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
	private String contenidoActual = ""; // para guardar el texto del archivo
	
	public Controller() {
		
		// Cargar archivo
		ventanaPrincipal.getBtnCargarArchivo().addActionListener(e -> {
			String contenido = archivo.cargarArchivo();
			if (contenido != null) {
				contenidoActual = contenido;
				ventanaPrincipal.getAreaDeTexto().setText(contenido);
			}
		});
		
		// Buscar el texto
		ventanaPrincipal.getBtnBuscar().addActionListener(e -> {
			String textoBuscado = ventanaPrincipal.getTxtTexto().getText();
			boolean keySensitive = ventanaPrincipal.getKeySensitive().isSelected();
			
			if (contenidoActual.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Primero carga un archivo .txt");
				return;
			}
			
			if (textoBuscado.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Escribe el texto que deseas buscar");
				return;
			}
			
			ArrayList<Integer> posiciones = busquedaKMP.buscarPatron(contenidoActual, textoBuscado, keySensitive);
			
			if (posiciones.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No se encontraron coincidencias");
			} else {
				JOptionPane.showMessageDialog(null, "Se encontraron " + posiciones.size() + " coincidencias");
				resaltarCoincidencias(posiciones, textoBuscado.length(), ventanaPrincipal.getAreaDeTexto());
			}
		});
	}
	
	// Metodo para resaltar las coincidencias
	private void resaltarCoincidencias(ArrayList<Integer> posiciones, int longitud, javax.swing.JTextArea area) {
		javax.swing.text.Highlighter marcador = area.getHighlighter();
		marcador.removeAllHighlights();
		
		javax.swing.text.Highlighter.HighlightPainter colorResaltado =
				new javax.swing.text.DefaultHighlighter.DefaultHighlightPainter(java.awt.Color.YELLOW);
		
		for (int pos : posiciones) {
			try {
				marcador.addHighlight(pos, pos + longitud, colorResaltado);
			} catch (javax.swing.text.BadLocationException e) {
				e.printStackTrace();
			}
		}
	}
}
