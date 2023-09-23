import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVLoader {
    // Método para cargar datos desde un archivo CSV y devolver una lista de líneas
    public static List<String[]> cargarDatosDesdeCSV(String nombreArchivo) {
        List<String[]> lineas = new ArrayList<>();

        try (BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(",");
                lineas.add(campos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lineas;
    }
    public static void main(String[] args) {
        String nombreArchivo = "tu_archivo.csv"; // Reemplaza "tu_archivo.csv" con el nombre de tu archivo CSV
        List<String[]> datos = CSVLoader.cargarDatosDesdeCSV(nombreArchivo);
    
        // Ahora puedes trabajar con la lista 'datos' que contiene las líneas del archivo CSV.
        for (String[] linea : datos) {
            for (String campo : linea) {
                System.out.print(campo + " ");
            }
            System.out.println(); // Imprime un salto de línea después de cada línea del archivo
        }
    }
}
