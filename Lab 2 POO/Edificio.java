import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Edificio {
    private int idEdificio;
    private String nombreEdificio;
    private int nivel;
    private List<Salon> salones;

    public Edificio(int idEdificio, String nombreEdificio, int nivel) {
        this.idEdificio = idEdificio;
        this.nombreEdificio = nombreEdificio;
        this.nivel = nivel;
        this.salones = new ArrayList<>();
    }

    public int getIdEdificio() {
        return idEdificio;
    }

    public String getNombreEdificio() {
        return nombreEdificio;
    }

    public int getNivel() {
        return nivel;
    }

    public List<Salon> getSalones() {
        return salones;
    }

    public void agregarSalon(Salon salon) {
        salones.add(salon);
    }

    public void eliminarSalon(Salon salon) {
        salones.remove(salon);
    }

    public Salon buscarSalonPorId(int idSalon) {
        return salones.stream()
                      .filter(salon -> salon.getIdSalon() == idSalon)
                      .findFirst()
                      .orElse(null);
    }

    /**
     * @param horario
     * @return
     */
    public List<Salon> obtenerSalonesDisponiblesParaHorario(String horario) {
        return salones.stream()
                      .filter(salon -> extracted4(horario, salon))
                      .toList();
    }

    private Object extracted4(String horario, Salon salon) {
        return extracted3(horario, salon);
    }

    private Object extracted3(String horario, Salon salon) {
        return extracted2(horario, salon);
    }

    private Object extracted2(String horario, Salon salon) {
        return extracted(horario, salon);
    }

    private Object extracted(String horario, Salon salon) {
        return salon.esHorarioDisponible(horario);
    }

    public int calcularCapacidadTotal() {
        return salones.stream()
                      .mapToInt(Salon::getCapacidad)
                      .sum();
    }

    public List<Salon> obtenerSalonesOrdenadosPorCapacidad() {
        List<Salon> salonesOrdenados = new ArrayList<>(salones);
        salonesOrdenados.sort(Comparator.comparingInt(Salon::getCapacidad));
        return salonesOrdenados;
    }

    public String generarInformeSalones() {
        StringBuilder informe = new StringBuilder("Informe de Salones en " + nombreEdificio + " (Nivel " + nivel + "):\n");
        for (Salon salon : salones) {
            informe.append("- Salon ").append(salon.getIdSalon()).append(": Capacidad ").append(salon.getCapacidad());
            informe.append(salon.estaOcupado() ? " (Ocupado)\n" : " (Disponible)\n");
        }
        return informe.toString();
    }

    public void marcarSalonComoOcupado(int idSalon) {
        Salon salon = buscarSalonPorId(idSalon);
        if (salon != null) {
            salon.marcarComoOcupado();
        }
    }

    public void marcarSalonComoDisponible(int idSalon) {
        Salon salon = buscarSalonPorId(idSalon);
        if (salon != null) {
            salon.marcarComoDisponible();
        }
    }

    public String obtenerInformacionDetallada() {
        StringBuilder info = new StringBuilder();
        info.append("Edificio: ").append(nombreEdificio).append(" (Nivel ").append(nivel).append(")\n");
        info.append("Salones:\n");
        for (Salon salon : salones) {
            info.append(salon.obtenerInformacionDetallada()).append("\n");
        }
        return info.toString();
    }

    @Override
    public String toString() {
        return "Edificio [idEdificio=" + idEdificio + ", nombreEdificio=" + nombreEdificio + ", nivel=" + nivel + "]";
    }
}
