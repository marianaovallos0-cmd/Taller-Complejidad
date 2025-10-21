package co.edu.unbosque.controller;

import co.edu.unbosque.model.Archivo;
import co.edu.unbosque.model.BusquedaKMP;
import co.edu.unbosque.view.VentanaPrincipal;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Controller {
	
	public VentanaPrincipal ventanaPrincipal;
	public Archivo archivo;
	public BusquedaKMP busquedaKMP;

	public Controller() {
		archivo = new Archivo();
		busquedaKMP = new BusquedaKMP();
		ventanaPrincipal = new VentanaPrincipal();
		ventanaPrincipal.getBtnBuscar().addActionListener(e -> buscar());
	}

	private String obtenerRutaArchivoSeleccionado() {
		File archivo = ventanaPrincipal.getArchivoSeleccionado();
		if (archivo == null || !archivo.exists()) {
			return null;
		}
		return archivo.getAbsolutePath();
	}
	private boolean obtenerKeySensitive() {
        return ventanaPrincipal.getKeySensitive().isSelected();
    }
	private String busquedaCadena() {
		return ventanaPrincipal.getTxtTexto().getText();
	}

	public void buscar() {
	    boolean aplicaKeySensitive = obtenerKeySensitive();
	    String cadenaBuscar = busquedaCadena();

	  
	    String contenidoArchivo = ventanaPrincipal.getTextoArea();

	    if (contenidoArchivo == null || contenidoArchivo.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Primero debes cargar un archivo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
	        return;
	    }
	    if (cadenaBuscar == null || cadenaBuscar.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Debes ingresar una palabra o frase para buscar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
	        return;
	    }

	    ArrayList<Integer> inicios = busquedaKMP.buscarPatron(contenidoArchivo, cadenaBuscar, aplicaKeySensitive);

	    ventanaPrincipal.resaltarCoincidencias(inicios, cadenaBuscar.length());
	}


}
