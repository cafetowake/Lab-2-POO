import java.util.ArrayList;
import java.util.List;

public class Curso {
    private int idCurso;
    private String nombreCurso;
    private int horario;
    private int duracion;
    private List<String> dias;
    private int cantidadEstudiantes;
    private Sede sede;

    // Constructor
    public Curso(int idCurso, String nombreCurso, int horario, int duracion, List<String> dias, int cantidadEstudiantes) {
        this.idCurso = idCurso;
        this.nombreCurso = nombreCurso;
        this.horario = horario;
        this.duracion = duracion;
        this.dias = new ArrayList<>(dias); // Copiar la lista para evitar cambios externos
        this.cantidadEstudiantes = cantidadEstudiantes;
    }

    public Curso(int idCurso2, String nombreCurso2, int idSede, int cantidadEstudiantes2, String horario2,
            int idEdificio) {
    }

    // Getters y Setters
    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public int getHorario() {
        return horario;
    }

    public void setHorario(int horario) {
        this.horario = horario;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public List<String> getDias() {
        return dias;
    }

    public void setDias(List<String> dias) {
        this.dias = new ArrayList<>(dias);
    }

    public int getCantidadEstudiantes() {
        return cantidadEstudiantes;
    }

    public void setCantidadEstudiantes(int cantidadEstudiantes) {
        this.cantidadEstudiantes = cantidadEstudiantes;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    @Override
    public String toString() {
        return "Curso [idCurso=" + idCurso + ", nombreCurso=" + nombreCurso + ", horario=" + horario
                + ", duracion=" + duracion + ", dias=" + dias + ", cantidadEstudiantes=" + cantidadEstudiantes + "]";
    }

    public int getNivel() {
        if (sede != null) {
            return sede.getNivel();
        } else {
            return -1;
        }
    }
    
    public int getIdSede() {
        if (sede != null) {
            return sede.getIdSede();
        } else {
            return -1;
        }
    }

    public int getIdEdificio() {
        if (sede != null) {
            Edificio edificio = sede.buscarEdificioPorNivel(getNivel());
            if (edificio != null) {
                return edificio.getIdEdificio();
            }
        }
        return -1;
    }
}
