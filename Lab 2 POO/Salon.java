import java.util.ArrayList;
import java.util.List;

public class Salon {
    private int idSalon;
    private int capacidad;
    private boolean ocupado;
    private List<Curso> cursosAsignados;

    public Salon(int idSalon, int capacidad) {
        this.idSalon = idSalon;
        this.capacidad = capacidad;
        this.ocupado = false; // Inicialmente, el salón está libre
        this.cursosAsignados = new ArrayList<>();
    }

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

    public boolean asignarCurso(Curso curso) {
        return ocupado;
    }

    public Object esHorarioDisponible(String horario) {
        return null;
    }

    public Object obtenerInformacionDetallada() {
        return null;
    }

    public Curso[] getCursosNoAsignados() {
        return null;
    }
}
