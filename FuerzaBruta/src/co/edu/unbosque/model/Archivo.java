package co.edu.unbosque.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Archivo {

	public String leerArchivo(String rutaArchivo) {
		File archivo = new File(rutaArchivo);

		if (!archivo.exists()) {
			System.out.println("El archivo no existe: " + rutaArchivo);
			return null;
		}
		if (!archivo.getName().toLowerCase().endsWith(".txt")) {
			System.out.println("Solo se permiten archivos con extensión .txt");
			return null;
		}

		try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
			StringBuilder contenido = new StringBuilder();
			String linea;
			while ((linea = lector.readLine()) != null) {
				contenido.append(linea).append('\n');
			}
			return contenido.toString();
		} catch (IOException e) {
			return e.getMessage();
		}
	}
}
