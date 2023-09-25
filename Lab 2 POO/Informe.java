import java.util.List;

public class Informe {
    private List<Curso> cursosSinSalon;
    private List<Curso> cursosConSalon;
    private int capacidadTotalSalones; // Total de capacidad de salones disponibles
    private int cursosAsignadosExitosamente; // Número de cursos asignados con éxito
    private int cursosNoAsignados; // Número de cursos no asignados

    // Constructor
    public Informe(List<Curso> cursosSinSalon, List<Curso> cursosConSalon,
                   int capacidadTotalSalones, int cursosAsignadosExitosamente, int cursosNoAsignados) {
        this.cursosSinSalon = cursosSinSalon;
        this.cursosConSalon = cursosConSalon;
        this.capacidadTotalSalones = capacidadTotalSalones;
        this.cursosAsignadosExitosamente = cursosAsignadosExitosamente;
        this.cursosNoAsignados = cursosNoAsignados;
    }

    // Getters y Setters (no es necesario modificarlos)

    public List<Curso> getCursosSinSalon() {
        return cursosSinSalon;
    }

    public void setCursosSinSalon(List<Curso> cursosSinSalon) {
        this.cursosSinSalon = cursosSinSalon;
    }

    public List<Curso> getCursosConSalon() {
        return cursosConSalon;
    }

    public void setCursosConSalon(List<Curso> cursosConSalon) {
        this.cursosConSalon = cursosConSalon;
    }

    public int getCapacidadTotalSalones() {
        return capacidadTotalSalones;
    }

    public void setCapacidadTotalSalones(int capacidadTotalSalones) {
        this.capacidadTotalSalones = capacidadTotalSalones;
    }

    // Métodos para obtener información calculada (no es necesario modificarlos)

    public int getCapacidadTotalCursos() {
        int capacidadTotal = 0;
        for (Curso curso : cursosConSalon) {
            capacidadTotal += curso.getCantidadEstudiantes();
        }
        return capacidadTotal;
    }

    public double getPorcentajeCapacidadUtilizada() {
        if (capacidadTotalSalones == 0) {
            return 0.0; // Evitar división por cero
        }
        double porcentaje = ((double) getCapacidadTotalCursos() / capacidadTotalSalones) * 100;
        return Math.round(porcentaje * 100.0) / 100.0; // Redondear a dos decimales
    }

    // Método para imprimir el informe en formato legible
    public void imprimirInforme() {
        System.out.println("===== Informe de Asignación de Salones =====");
        System.out.printf("Cantidad de Cursos Asignados con Éxito: %d%n", cursosAsignadosExitosamente);
        System.out.printf("Cantidad de Cursos No Asignados: %d%n", cursosNoAsignados);
        System.out.printf("Capacidad Total de Salones Disponibles: %d%n", capacidadTotalSalones);
        System.out.printf("Capacidad Total de Cursos Asignados: %d%n", getCapacidadTotalCursos());
        System.out.printf("Porcentaje de Capacidad Utilizada: %.2f%%%n", getPorcentajeCapacidadUtilizada());
    }

    public Object calcularCapacidadTotalCursos() {
        return null;
    }

    public void setCapacidadTotalCursos(Object calcularCapacidadTotalCursos) {
    }
}
