package co.edu.unbosque.model;

import java.util.ArrayList;

public class BusquedaKMP {
	

	public ArrayList<Integer> buscarPatron(String texto, String patron, boolean sensibleMayus) {
		
		ArrayList<Integer> posiciones = new ArrayList<>();
		
		// Validar las entradas
		if (texto == null || patron == null || patron.isEmpty()) {
			return posiciones;
		}
		
		// Si no es sensible a mayusculas, convierte ambos a minusculas
		if (!sensibleMayus) {
			texto = texto.toLowerCase();
			patron = patron.toLowerCase();
		}
		
		/* Preprocesar patron: crear tabla LPS
		 * LPS Es una tabla auxiliar (un arreglo de enteros) que usa el algoritmo KMP 
		 * para evitar comparaciones innecesarias cuando hay un fallo parcial en la busqueda del patron.
		 */
		
		int[] lps = construirLPS(patron);
		
		int i = 0; // indice en texto
		int j = 0; // indice en patron
		
		while (i < texto.length()) {
			
			if (texto.charAt(i) == patron.charAt(j)) {
				i++;
				j++;
			}
			
			if (j == patron.length()) {
				posiciones.add(i - j);  // Coincidencia completa
				j = lps[j - 1];         // Continuar buscando
			}
			
			else if (i < texto.length() && texto.charAt(i) != patron.charAt(j)) {
				if (j != 0) {
					j = lps[j - 1];     // Saltar segun tabla LPS
				} else {
					i++;                // Avanzar texto
				}
			}
		}
		
		return posiciones;
	}
	
	/**
	 * Construye la tabla LPS (Longest Prefix Suffix) del patron
	 * Esta tabla indica para cada posición del patron cual es la
	 * longitud del prefijo mas largo que tambien es sufijo.
	 * 
	 */
	private int[] construirLPS(String patron) {
		
		int[] lps = new int[patron.length()];
		int len = 0; // longitud del prefijo-sufijo anterior
		int i = 1;
		
		while (i < patron.length()) {
			if (patron.charAt(i) == patron.charAt(len)) {
				len++;
				lps[i] = len;
				i++;
			} else {
				if (len != 0) {
					len = lps[len - 1];
				} else {
					lps[i] = 0;
					i++;
				}
			}
		}
		
		return lps;
	}
}
