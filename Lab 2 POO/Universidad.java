import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Universidad {
    private List<Sede> sedes;
    private List<Curso> cursos;
    private boolean salonesCargados;
    private boolean cursosCargados;

    public Universidad() {
        sedes = new ArrayList<>();
        cursos = new ArrayList<>();
        salonesCargados = false;
        cursosCargados = false;
    }

    public boolean salonesCargados() {
        return salonesCargados;
    }

    public boolean cargarSalonesDesdeArchivo(String archivo) {
        List<String[]> datosSalones = CSVLoader.cargarDatosDesdeCSV(archivo);

        // Procesar los datos y crear instancias de sedes, edificios, salones
        for (String[] datos : datosSalones) {
            int idSede = Integer.parseInt(datos[0]);
            int idEdificio = Integer.parseInt(datos[1]);
            int nivel = Integer.parseInt(datos[2]);
            int idSalon = Integer.parseInt(datos[3]);
            int capacidad = Integer.parseInt(datos[4]);

            // Crear las instancias correspondientes y agregarlas a las listas
            Sede sede = buscarSedePorId(idSede);
            if (sede == null) {
                sede = new Sede(idSede, "Sede por defecto"); // Debes ajustar esto según tus datos
                sedes.add(sede);
            }

            Edificio edificio = sede.buscarEdificioPorId(idEdificio);
            if (edificio == null) {
                edificio = new Edificio(idEdificio, "Edificio por defecto", nivel);
                sede.agregarEdificio(edificio);
            }

            Salon salon = new Salon(idSalon, capacidad);
            edificio.agregarSalon(salon);
        }

        salonesCargados = true; // Actualizar el estado de salones cargados
        return true; // Indicar éxito en la carga de salones
    }

    private Sede buscarSedePorId(int idSede) {
        return null;
    }

    public boolean cursosCargados() {
        return cursosCargados;
    }

    public boolean cargarCursosDesdeArchivo(String archivo) {
        List<String[]> datosCursos = CSVLoader.cargarDatosDesdeCSV(archivo);

        // Procesar los datos y crear instancias de cursos
        for (String[] datos : datosCursos) {
            int idCurso = Integer.parseInt(datos[0]);
            String nombreCurso = datos[1];
            int horario = Integer.parseInt(datos[2]);
            int duracion = Integer.parseInt(datos[3]);
            List<String> dias = Arrays.asList(datos[4].split(",")); // Suponiendo que los días estén separados por comas
            int cantidadEstudiantes = Integer.parseInt(datos[5]);

            Curso curso = new Curso(idCurso, nombreCurso, horario, duracion, dias, cantidadEstudiantes);
            cursos.add(curso);
        }

        cursosCargados = true; // Actualizar el estado de cursos cargados
        return true; // Indicar éxito en la carga de cursos
    }

    public void asignarSalones() {
        // Implementa la lógica para asignar salones a cursos aquí
        // Debes recorrer los cursos y buscar salones disponibles para asignar
        // Actualiza el estado de los salones (ocupado/disponible) y lleva un registro de los cursos asignados y no asignados
    }

    public String consultarCurso(int idCurso) {
        // Implementa la lógica para consultar un curso por su ID y devuelve la información del curso en formato String
        // Puedes buscar el curso en la lista de cursos
        // Debes retornar la información del curso en formato legible
        return "Información del curso con ID " + idCurso; // Debes ajustar esto
    }

    public String consultarSalon(int idSede, int idEdificio, int nivel, int idSalon) {
        // Implementa la lógica para consultar un salón por su ID y devuelve la información del salón en formato String
        // Puedes buscar el salón en las listas de sedes, edificios y salones
        // Debes retornar la información del salón en formato legible
        return "Información del salón con ID " + idSalon; // Debes ajustar esto
    }

    public void generarInforme() {
    }
}
