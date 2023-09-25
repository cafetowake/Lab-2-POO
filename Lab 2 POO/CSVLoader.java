import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVLoader {
    // Método para cargar datos desde un archivo CSV y devolver una lista de líneas
    public static List<String[]> cargarDatosDesdeCSV(String nombreArchivo) throws IOException {
        List<String[]> lineas = new ArrayList<>();
        
        // Verificar si el archivo existe antes de intentar abrirlo
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
            throw new IOException("El archivo CSV no existe: " + nombreArchivo);
        }

        // Utilizar try-with-resources para garantizar que el recurso se cierre adecuadamente
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(",");
                lineas.add(campos);
            }
        }

        return lineas;
    }

    public static void imprimirDatosCSV(List<String[]> datos) {
        for (String[] linea : datos) {
            for (String campo : linea) {
                System.out.print(campo + " ");
            }
            System.out.println(); // Imprime un salto de línea después de cada línea del archivo
        }
    }

    public static void main(String[] args) {
        String nombreArchivo = "cursos.csv";

        try {
            List<String[]> datos = CSVLoader.cargarDatosDesdeCSV(nombreArchivo);
            imprimirDatosCSV(datos);
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo CSV: " + e.getMessage());
        }
    }
}
