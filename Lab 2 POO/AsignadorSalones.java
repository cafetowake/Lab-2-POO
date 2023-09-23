import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class AsignadorSalones {
    private List<Sede> sedes;
    private List<Curso> cursosSinSalon;
    private List<Curso> cursosAsignados;

    public AsignadorSalones() {
        this.sedes = new ArrayList<>();
        this.cursosSinSalon = new ArrayList<>();
        this.cursosAsignados = new ArrayList<>();
    }

    public void cargarSedes(List<Sede> sedes) {
        this.sedes = sedes;
    }

    public void cargarCursos(List<Curso> cursos) {
        this.cursosSinSalon = cursos;
    }

    public void asignarSalones() {
        cursosAsignados.clear(); // Limpiar la lista de cursos asignados

        Iterator<Curso> cursoIterator = cursosSinSalon.iterator();
        while (cursoIterator.hasNext()) {
            Curso curso = cursoIterator.next();
            if (asignarCurso(curso)) {
                cursosAsignados.add(curso);
                cursoIterator.remove(); // Eliminar el curso de cursosSinSalon
            }
        }
    }

    private boolean asignarCurso(Curso curso) {
        for (Sede sede : sedes) {
            Edificio edificio = sede.buscarEdificioPorId(curso.getNivel());
            if (edificio != null) {
                Salon salonDisponible = edificio.buscarSalonPorId(curso.getHorario());
                if (salonDisponible != null && salonDisponible.getCapacidad() >= curso.getCantidadEstudiantes()) {
                    salonDisponible.asignarCurso(curso);
                    return true; // Curso asignado con éxito
                }
            }
        }
        return false; // Curso no asignado
    }

    public List<Curso> getCursosSinSalon() {
        return cursosSinSalon;
    }

    public List<Curso> getCursosAsignados() {
        return cursosAsignados;
    }
}
