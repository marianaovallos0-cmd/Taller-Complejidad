package co.edu.unbosque.controller;

import co.edu.unbosque.model.Archivo;
import co.edu.unbosque.model.BusquedaKMP;
import co.edu.unbosque.view.VentanaPrincipal;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

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
		String ruta = obtenerRutaArchivoSeleccionado();
		boolean aplicaKeySensitive = obtenerKeySensitive();
		String rutaCadena = busquedaCadena();


		if (ruta == null ) {
			return;
		}

		System.out.println(aplicaKeySensitive+" esto existe");
		System.out.println(ruta+" esto existe");


		String contenidoArchivo = archivo.leerArchivo(ruta);
		ArrayList<Integer> caracteres = busquedaKMP.buscarPatron(contenidoArchivo,rutaCadena,aplicaKeySensitive);

        for (Integer caractere : caracteres) {
            System.out.println(caractere);
        }






	}

}
