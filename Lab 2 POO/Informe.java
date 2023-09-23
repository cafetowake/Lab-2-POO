import java.util.List;

public class Informe {
    private List<Curso> cursosSinSalon;
    private List<Curso> cursosConSalon;
    private int capacidadTotalSalones; // Total de capacidad de salones disponibles
    private int capacidadTotalCursos; // Total de capacidad de cursos
    private int cursosAsignadosExitosamente; // Número de cursos asignados con éxito
    private int cursosNoAsignados; // Número de cursos no asignados

    // Constructor
    public Informe(List<Curso> cursosSinSalon, List<Curso> cursosConSalon, int capacidadTotalSalones,
                   int cursosAsignadosExitosamente, int cursosNoAsignados) {
        this.cursosSinSalon = cursosSinSalon;
        this.cursosConSalon = cursosConSalon;
        this.capacidadTotalSalones = capacidadTotalSalones;
        this.cursosAsignadosExitosamente = cursosAsignadosExitosamente;
        this.cursosNoAsignados = cursosNoAsignados;
        // Calcula la capacidad total de cursos
        this.capacidadTotalCursos = calcularCapacidadTotalCursos();
    }
    

    // Getters y Setters
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

    public int getCapacidadTotalCursos() {
        return capacidadTotalCursos;
    }

    public void setCapacidadTotalCursos(int capacidadTotalCursos) {
        this.capacidadTotalCursos = capacidadTotalCursos;
    }

    // Método para calcular la capacidad total de cursos con salón
    private int calcularCapacidadTotalCursos() {
        int capacidadTotal = 0;
        for (Curso curso : cursosConSalon) {
            capacidadTotal += curso.getCantidadEstudiantes();
        }
        return capacidadTotal;
    }

    // Método para calcular el porcentaje de capacidad utilizada en los salones
    public double calcularPorcentajeCapacidadUtilizada() {
        if (capacidadTotalSalones == 0) {
            return 0.0; // Evitar división por cero
        }
        double porcentaje = ((double) capacidadTotalCursos / capacidadTotalSalones) * 100;
        return Math.round(porcentaje * 100.0) / 100.0; // Redondear a dos decimales
    }
    

    // Método para obtener la cantidad de cursos asignados con éxito
    public int obtenerCursosAsignadosExitosamente() {
        return cursosAsignadosExitosamente;
    }

    // Método para obtener la cantidad de cursos no asignados
    public int obtenerCursosNoAsignados() {
        return cursosNoAsignados;
    }

    // Método para imprimir el informe en formato legible
    public void imprimirInforme() {
        System.out.println("===== Informe de Asignación de Salones =====");
        System.out.printf("Cantidad de Cursos Asignados con Éxito: %d%n", obtenerCursosAsignadosExitosamente());
        System.out.printf("Cantidad de Cursos No Asignados: %d%n", obtenerCursosNoAsignados());
        System.out.printf("Capacidad Total de Salones Disponibles: %d%n", capacidadTotalSalones);
        System.out.printf("Capacidad Total de Cursos Asignados: %d%n", capacidadTotalCursos);
        System.out.printf("Porcentaje de Capacidad Utilizada: %.2f%%%n", calcularPorcentajeCapacidadUtilizada());
    }
}
        // Puedes agregar más información si es necesario
    
    
    

