package co.edu.unbosque.controller;

import co.edu.unbosque.model.Archivo;
import co.edu.unbosque.model.BusquedaKMP;
import co.edu.unbosque.view.VentanaPrincipal;

public class Controller {
	
	Archivo archivo = new Archivo();
	BusquedaKMP busquedaKMP = new BusquedaKMP();
	VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
	
	public Controller() {
		//Action Listener
		ventanaPrincipal.getBtnCargarArchivo().addActionListener(e -> {
			System.out.println("Botón cargar Archivo presionado");
		});
		ventanaPrincipal.getBtnBuscar().addActionListener(e -> {
			System.out.println("Botón Buscar presionado");
		});
	}
}
