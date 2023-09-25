import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SistemaAsignacion {
    private List<Sede> sedes;

    public SistemaAsignacion() {
        sedes = new ArrayList<>();
    }

    public void mostrarInforme() {
        System.out.println("===== Cursos Asignados =====");
        for (Sede sede : sedes) {
            for (Edificio edificio : sede.getEdificios()) {
                for (Salon salon : edificio.getSalones()) {
                    for (Curso curso : salon.getCursosAsignados()) {
                        System.out.println("Sede: " + sede.getNombreSede() +
                                " - Edificio: " + edificio.getNombreEdificio() +
                                " - Nivel: " + edificio.getNivel() +
                                " - Salon: " + salon.getIdSalon() +
                                " - Curso: " + curso.getNombreCurso());
                    }
                }
            }
        }

        System.out.println("===== Cursos No Asignados =====");
        for (Sede sede : sedes) {
            for (Edificio edificio : sede.getEdificios()) {
                for (Salon salon : edificio.getSalones()) {
                    for (Curso curso : salon.getCursosNoAsignados()) {
                        System.out.println("Sede: " + sede.getNombreSede() +
                                " - Edificio: " + edificio.getNombreEdificio() +
                                " - Nivel: " + edificio.getNivel() +
                                " - Salon: " + salon.getIdSalon() +
                                " - Curso: " + curso.getNombreCurso());
                    }
                }
            }
        }
    }

    public void cargarDatos() throws IOException {
        List<String[]> datosSalones = CSVLoader.cargarDatosDesdeCSV("salones.csv");
        List<String[]> datosCursos = CSVLoader.cargarDatosDesdeCSV("cursos.csv");

        for (String[] datos : datosSalones) {
            int idSede = Integer.parseInt(datos[0]);
            String nombreEdificio = datos[1];
            int nivel = Integer.parseInt(datos[2]);
            int idSalon = Integer.parseInt(datos[3]);
            int capacidad = Integer.parseInt(datos[4]);

            Sede sede = buscarSedeOrCreate(idSede);
            Edificio edificio = new Edificio(capacidad, nombreEdificio, nivel);
            Salon salon = new Salon(idSalon, capacidad);

            edificio.agregarSalon(salon);
            sede.agregarEdificio(edificio);
        }

        for (String[] datos : datosCursos) {
            int idCurso = Integer.parseInt(datos[0]);
            int idSede = Integer.parseInt(datos[1]);
            String nombreCurso = datos[2];
            int horario = Integer.parseInt(datos[3]);
            int duracion = Integer.parseInt(datos[4]);
            String[] dias = datos[5].split(",");
            int cantidadEstudiantes = Integer.parseInt(datos[6]);

            Curso curso = new Curso(idCurso, nombreCurso, horario, duracion, dias, cantidadEstudiantes);
            asignarCursoASalon(idSede, curso);
        }
    }

    private Sede buscarSedeOrCreate(int idSede) {
        for (Sede sede : sedes) {
            if (sede.getIdSede() == idSede) {
                return sede;
            }
        }
        Sede nuevaSede = new Sede(idSede, "Sede " + idSede);
        sedes.add(nuevaSede);
        return nuevaSede;
    }

    private void asignarCursoASalon(int idSede, Curso curso) {
        for (Sede sede : sedes) {
            if (sede.getIdSede() == idSede) {
                for (Edificio edificio : sede.getEdificios()) {
                    for (Salon salon : edificio.getSalones()) {
                        if (salon.asignarCurso(curso)) {
                            salon.asignarCurso(curso);
                            return;
                        }
                    }
                }
            }
        }
    }

    public void asignarSalones() {
        for (Sede sede : sedes) {
            for (Edificio edificio : sede.getEdificios()) {
                for (Salon salon : edificio.getSalones()) {
                    for (Curso curso : salon.getCursosNoAsignados()) {
                        if (salon.asignarCurso(curso)) {
                            salon.asignarCurso(curso);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        SistemaAsignacion sistema = new SistemaAsignacion();

        sistema.cargarDatos();
        sistema.asignarSalones();
        sistema.mostrarInforme();
    }
}
