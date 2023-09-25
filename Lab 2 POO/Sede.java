import java.util.ArrayList;
import java.util.List;

public class Sede {
    private int idSede;
    private String nombreSede;
    private List<Edificio> edificios;

    // Constructor
    public Sede(int idSede, String nombreSede) {
        this.idSede = idSede;
        this.nombreSede = nombreSede;
        this.edificios = new ArrayList<>();
    }

    // Getters y Setters
    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    public List<Edificio> getEdificios() {
        return edificios;
    }

    public void agregarEdificio(Edificio edificio) {
        edificios.add(edificio);
    }

    public void eliminarEdificio(Edificio edificio) {
        edificios.remove(edificio);
    }

    public Edificio buscarEdificioPorId(int idEdificio) {
        return edificios.stream()
                        .filter(edif -> edif.getIdEdificio() == idEdificio)
                        .findFirst()
                        .orElse(null);
    }

    public String obtenerInformacionDetallada() {
        StringBuilder info = new StringBuilder();
        info.append("Sede: ").append(nombreSede).append("\n");
        info.append("Edificios:\n");
        for (Edificio edificio : edificios) {
            info.append(edificio.obtenerInformacionDetallada()).append("\n");
        }
        return info.toString();
    }

    @Override
    public String toString() {
        return "Sede [idSede=" + idSede + ", nombreSede=" + nombreSede + "]";
    }

    public int getNivel() {
        return 0;
    }
}
