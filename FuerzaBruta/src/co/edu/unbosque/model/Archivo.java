package co.edu.unbosque.model;

public class Archivo {
	
	public Archivo() {
		
	}
	
	public String cargarArchivo() {
	    javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
	    int opcion = fileChooser.showOpenDialog(null);

	    if (opcion == javax.swing.JFileChooser.APPROVE_OPTION) {
	        java.io.File archivoSeleccionado = fileChooser.getSelectedFile();
	        
	        //Evitar que se suban archivos con extension diferente a .txt
	        if (!archivoSeleccionado.getName().toLowerCase().endsWith(".txt")) {
	            javax.swing.JOptionPane.showMessageDialog(null, "Solo se pueden cargar archivos con extensión .txt");
	            return null;
	        }

	        try {
	            java.io.BufferedReader lector = new java.io.BufferedReader(new java.io.FileReader(archivoSeleccionado));
	            StringBuilder contenido = new StringBuilder();
	            String linea;

	            while ((linea = lector.readLine()) != null) {
	                contenido.append(linea).append("\n");
	            }

	            lector.close();
	            return contenido.toString();

	        } catch (java.io.IOException e) {
	            e.printStackTrace();
	            return null;
	        }
	    } else {
	        // Si el usuario cancela o cierra el cuadro de diálogo
	        return null;
	    }
	}


}
