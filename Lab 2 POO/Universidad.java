import java.io.IOException;
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

    public boolean cargarSalonesDesdeArchivo(String archivo) throws IOException {
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
        for (Sede sede : sedes) {
            if (sede.getIdSede() == idSede) {
                return sede;
            }
        }
        return null;
    }

    public boolean cursosCargados() {
        return cursosCargados;
    }

    /**
     * @param archivo
     * @return
     */
    public boolean cargarCursosDesdeArchivo(String archivo) {
        List<String[]> datosCursos;
        try {
            datosCursos = CSVLoader.cargarDatosDesdeCSV(archivo);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        AsignadorSalones asignador = new AsignadorSalones();
        asignador.cargarSedes(sedes);
        asignador.cargarCursos(cursos);
        asignador.asignarSalones();
    }

    public String consultarCurso(int idCurso) {
        // Implementa la lógica para buscar y mostrar la información del curso por su ID
        for (Curso curso : cursos) {
            if (curso.getIdCurso() == idCurso) {
                return curso.toString();
            }
        }
        return "Curso no encontrado";
    }

    public String consultarSalon(int idSede, int idEdificio, int nivel, int idSalon) {
        // Implementa la lógica para buscar y mostrar la información del salón
        // por los parámetros especificados (idSede, idEdificio, nivel, idSalon).
        Sede sede = buscarSedePorId(idSede);
        if (sede != null) {
            Edificio edificio = sede.buscarEdificioPorId(idEdificio);
            if (edificio != null) {
                Salon salon = edificio.buscarSalonPorId(idSalon);
                if (salon != null) {
                    return salon.toString();
                }
            }
        }
        return "Salón no encontrado";
    }

    /**
     * 
     */
    public void generarInforme() {
        // Implementa la lógica para generar el informe y mostrarlo.
        Informe informe = new Informe(new ArrayList<>(), new ArrayList<>(), 0, 0, 0);
        
        for (Sede sede : sedes) {
            for (Edificio edificio : sede.getEdificios()) {
                for (Salon salon : edificio.getSalones()) {
                    List<Curso> cursosAsignados = salon.getCursosAsignados();
                    if (cursosAsignados.isEmpty()) {
                        informe.getCursosSinSalon().addAll(cursosAsignados);
                    } else {
                        informe.getCursosConSalon().addAll(cursosAsignados);
                    }
                    informe.setCapacidadTotalSalones(informe.getCapacidadTotalSalones() + salon.getCapacidad());
                }
            }
        }
        
        informe.setCapacidadTotalCursos(informe.calcularCapacidadTotalCursos());
        informe.imprimirInforme();
    }
}
