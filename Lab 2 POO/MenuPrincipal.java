import java.util.Scanner;

public class MenuPrincipal {
    private Universidad universidad;

    public MenuPrincipal() {
        universidad = new Universidad();
    }

    public void mostrarMenu() {
        try (Scanner scanner = new Scanner(System.in)) {
            int opcion;

            do {
                System.out.println("===== Menú Principal =====");
                System.out.println("1. Cargar Archivos");
                System.out.println("2. Consultar Salón");
                System.out.println("3. Consultar Curso");
                System.out.println("4. Asignar Salones");
                System.out.println("5. Ver Informe");
                System.out.println("6. Salir");
                System.out.print("Seleccione una opción: ");

                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        cargarArchivos();
                        break;
                    case 2:
                        consultarSalon();
                        break;
                    case 3:
                        consultarCurso();
                        break;
                    case 4:
                        asignarSalones();
                        break;
                    case 5:
                        verInforme();
                        break;
                    case 6:
                        System.out.println("¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } while (opcion != 6);
        }
    }

    private void cargarArchivos() {
        if (universidad.cargarSalonesDesdeArchivo("salones.csv") && universidad.cargarSalonesDesdeArchivo("cursos.csv")) {
            System.out.println("Archivos CSV cargados exitosamente.");
        } else {
            System.out.println("Error al cargar los archivos CSV.");
        }
    }

    private void consultarSalon() {
        if (universidad.salonesCargados()) {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.print("Ingrese el ID de la sede: ");
                int idSede = scanner.nextInt();
                System.out.print("Ingrese el ID del edificio: ");
                int idEdificio = scanner.nextInt();
                System.out.print("Ingrese el ID del nivel: ");
                int nivel = scanner.nextInt();
                System.out.print("Ingrese el ID del salón: ");
                int idSalon = scanner.nextInt();

                // Llama a universidad.consultarSalon(idSede, idEdificio, nivel, idSalon)
                // para mostrar la información del salón
                String infoSalon = universidad.consultarSalon(idSede, idEdificio, nivel, idSalon);
                System.out.println(infoSalon);
            }
        } else {
            System.out.println("Debes cargar los archivos de salones antes de consultar un salón.");
        }
    }

    private void consultarCurso() {
        if (universidad.cursosCargados()) {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.print("Ingrese el ID del curso: ");
                int idCurso = scanner.nextInt();

                // Llama a universidad.consultarCurso(idCurso) para mostrar la información del curso
                String infoCurso = universidad.consultarCurso(idCurso);
                System.out.println(infoCurso);
            }
        } else {
            System.out.println("Debes cargar los archivos de cursos antes de consultar un curso.");
        }
    }

    private void asignarSalones() {
        if (universidad.salonesCargados() && universidad.cursosCargados()) {
            universidad.asignarSalones();
            System.out.println("Salones asignados correctamente.");
        } else {
            System.out.println("Debes cargar los archivos de salones y cursos antes de asignar salones.");
        }
    }

    private void verInforme() {
        if (universidad.salonesCargados()) {
            universidad.generarInforme(); // Llama al método de Universidad para generar el informe
        } else {
            System.out.println("Debes asignar salones antes de ver el informe.");
        }
    }

    public static void main(String[] args) {
        MenuPrincipal menu = new MenuPrincipal();
        menu.mostrarMenu();
    }
}
