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

    public void setIdEdificio(int idEdificio) {
        this.idEdificio = idEdificio;
    }

    public String getNombreEdificio() {
        return nombreEdificio;
    }

    public void setNombreEdificio(String nombreEdificio) {
        this.nombreEdificio = nombreEdificio;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public List<Salon> getSalones() {
        return salones;
    }

    public void setSalones(List<Salon> salones) {
        this.salones = salones;
    }

    public void agregarSalon(Salon salon) {
        salones.add(salon);
    }

    public void eliminarSalon(Salon salon) {
        salones.remove(salon);
    }

    public Salon buscarSalonPorId(int idSalon) {
        for (Salon salon : salones) {
            if (salon.getIdSalon() == idSalon) {
                return salon;
            }
        }
        return null; // Si no se encuentra el salón
    }

    public List<Salon> obtenerSalonesDisponiblesParaHorario(String horario) {
        List<Salon> salonesDisponibles = new ArrayList<>();
        for (Salon salon : salones) {
            if (salon.esHorarioDisponible(horario)) {
                salonesDisponibles.add(salon);
            }
        }
        return salonesDisponibles;
    }

    public int calcularCapacidadTotal() {
        int capacidadTotal = 0;
        for (Salon salon : salones) {
            capacidadTotal += salon.getCapacidad();
        }
        return capacidadTotal;
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
            if (salon.estaOcupado()) {
                informe.append(" (Ocupado)");
            } else {
                informe.append(" (Disponible)");
            }
            informe.append("\n");
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
