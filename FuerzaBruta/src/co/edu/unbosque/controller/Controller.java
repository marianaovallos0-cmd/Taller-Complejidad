package co.edu.unbosque.controller;

import co.edu.unbosque.model.BusquedaKMP;
import co.edu.unbosque.view.VentanaPrincipal;

import java.util.ArrayList;

public class Controller {
	
	public VentanaPrincipal ventanaPrincipal;
	public BusquedaKMP busquedaKMP;

	public Controller() {
		busquedaKMP = new BusquedaKMP();
		ventanaPrincipal = new VentanaPrincipal();
		ventanaPrincipal.getBtnBuscar().addActionListener(e -> buscar());
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
			ventanaPrincipal.ShowMessage("Carga un archivo");
	        return;
	    }

	    if (cadenaBuscar == null || cadenaBuscar.isEmpty()) {
			ventanaPrincipal.ShowMessage("Ingresa un texto a buscar");
	        return;
	    }

	    ArrayList<Integer> inicios = busquedaKMP.buscarPatron(contenidoArchivo, cadenaBuscar, aplicaKeySensitive);
	    ventanaPrincipal.resaltarCoincidencias(inicios, cadenaBuscar.length());

	}
}
