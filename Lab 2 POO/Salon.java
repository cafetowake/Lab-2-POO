import java.util.ArrayList;
import java.util.List;

public class Salon {
    private int idSalon;
    private int capacidad;
    private boolean ocupado;
    private List<Curso> cursosAsignados;

    // Constructor
    public Salon(int idSalon, int capacidad) {
        this.idSalon = idSalon;
        this.capacidad = capacidad;
        this.ocupado = false; // Inicialmente, el salón está libre
        this.cursosAsignados = new ArrayList<>();
    }

    // Getters y Setters

    public int getIdSalon() {
        return idSalon;
    }

    public void setIdSalon(int idSalon) {
        this.idSalon = idSalon;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public boolean estaOcupado() {
        return ocupado;
    }

    public void marcarComoOcupado() {
        ocupado = true;
    }

    public void marcarComoDisponible() {
        ocupado = false;
    }

    public List<Curso> getCursosAsignados() {
        return cursosAsignados;
    }

    public void setCursosAsignados(List<Curso> cursosAsignados) {
        this.cursosAsignados = cursosAsignados;
    }

    public void agregarCurso(Curso curso) {
        cursosAsignados.add(curso);
        marcarComoOcupado();
    }

    public void quitarCurso(Curso curso) {
        cursosAsignados.remove(curso);
        if (cursosAsignados.isEmpty()) {
            marcarComoDisponible();
        }
    }

    public void mostrarCursosAsignados() {
        if (cursosAsignados.isEmpty()) {
            System.out.println("El salón " + idSalon + " no tiene cursos asignados.");
        } else {
            System.out.println("Cursos asignados en el salón " + idSalon + ":");
            for (Curso curso : cursosAsignados) {
                System.out.println(curso.toString());
            }
        }
    }

    @Override
    public String toString() {
        return "Salon [idSalon=" + idSalon + ", capacidad=" + capacidad + ", ocupado=" + ocupado + "]";
    }

    public void asignarCurso(Curso curso) {
    }

    public boolean esHorarioDisponible(String horario) {
        return false;
    }

    public Object obtenerInformacionDetallada() {
        return null;
    }

    public Curso[] getCursosNoAsignados() {
        return null;
    }

    public boolean esHorarioDisponible(int horario) {
        return false;
    }
}
