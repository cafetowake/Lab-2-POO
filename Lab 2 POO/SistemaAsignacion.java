import java.util.ArrayList;
import java.util.List;

public class SistemaAsignacion {
    private List<Sede> sedes;

    public SistemaAsignacion() {
        sedes = new ArrayList<>();
    }

    public void mostrarInforme() {
        // Implementa la lógica para mostrar el informe con cursos asignados y no asignados
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

    public void cargarDatos() {
        // Implementa aquí la lógica para cargar datos desde archivos CSV
        // Debes crear instancias de sedes, edificios, salones y cursos a partir de los datos leídos desde los archivos CSV
        // Asegúrate de que los cursos se asignen a los salones correctamente
        List<String[]> datosSalones = CSVLoader.cargarDatosDesdeCSV("salones.csv");
        List<String[]> datosCursos = CSVLoader.cargarDatosDesdeCSV("cursos.csv");

        // Procesa los datos y crea instancias correspondientes
        for (String[] datos : datosSalones) {
            int idEdificio = Integer.parseInt(datos[0]);
            String nombreEdificio = datos[1];
            int nivel = Integer.parseInt(datos[2]);
            int idSalon = Integer.parseInt(datos[3]);
            int capacidad = Integer.parseInt(datos[4]);

            Sede sede = buscarSedeOrCreate(idEdificio); // Busca la sede existente o crea una nueva
            Edificio edificio = new Edificio(idEdificio, nombreEdificio, nivel);
            Salon salon = new Salon(idSalon, capacidad);

            edificio.agregarSalon(salon); // Agrega el salón al edificio
            sede.agregarEdificio(edificio); // Agrega el edificio a la sede
        }

        for (String[] datos : datosCursos) {
            int idCurso = Integer.parseInt(datos[0]);
            String nombreCurso = datos[1];
            int idSede = Integer.parseInt(datos[2]);
            int cantidadEstudiantes = Integer.parseInt(datos[3]);
            String horario = datos[4];
            int idEdificio = Integer.parseInt(datos[5]);

            Curso curso = new Curso(idCurso, nombreCurso, idSede, cantidadEstudiantes, horario, idEdificio);
            asignarCursoASalon(curso); // Asigna el curso al salón correspondiente
        }
    }

    private Sede buscarSedeOrCreate(int idSede) {
        for (Sede sede : sedes) {
            if (sede.getIdSede() == idSede) {
                return sede;
            }
        }
        // Si no se encuentra la sede, se crea una nueva
        Sede nuevaSede = new Sede(idSede, "Sede " + idSede);
        sedes.add(nuevaSede);
        return nuevaSede;
    }

    private void asignarCursoASalon(Curso curso) {
        for (Sede sede : sedes) {
            for (Edificio edificio : sede.getEdificios()) {
                for (Salon salon : edificio.getSalones()) {
                    if (salon.getIdSalon() == curso.getIdEdificio()) {
                        salon.asignarCurso(curso);
                        return;
                    }
                }
            }
        }
        // Si no se encuentra un salón correspondiente, el curso queda sin asignar
    }

    public void asignarSalones() {
        // Implementa la lógica para asignar salones a cursos aquí
        // Debes recorrer las sedes, edificios, salones y cursos para realizar la asignación
        // Actualiza el estado de los salones (ocupado/disponible) y lleva un registro de los cursos asignados y no asignados

        // Ejemplo de asignación de cursos a salones (debes ajustarlo a tus necesidades):
        for (Sede sede : sedes) {
            for (Edificio edificio : sede.getEdificios()) {
                for (Salon salon : edificio.getSalones()) {
                    for (Curso curso : salon.getCursosNoAsignados()) {
                        // Lógica para asignar el curso al salón si cumple con los requisitos (capacidad, horario, etc.)
                        if (cumpleRequisitos(curso, salon)) {
                            salon.asignarCurso(curso);
                        }
                    }
                }
            }
        }
    }

    private boolean cumpleRequisitos(Curso curso, Salon salon) {
        // Implementa la lógica para verificar si un curso puede asignarse a un salón
        // Verifica la capacidad, horario, disponibilidad, etc.
        // Retorna true si el curso puede asignarse, false en caso contrario
        return salon.getCapacidad() >= curso.getCantidadEstudiantes()
                && salon.esHorarioDisponible(curso.getHorario());
    }

    public static void main(String[] args) {
        SistemaAsignacion sistema = new SistemaAsignacion();

        sistema.cargarDatos();
        sistema.asignarSalones();
        sistema.mostrarInforme(); // Llama a mostrarInforme después de asignar los salones
    }
}
