/**
 * @author Jose David Molano Perdomo
 *
 * POLIMORFISMO EN EL CONTROLADOR:
 * - registrarEstudiantes: llama a Estudiante() que internamente crea el array polimórfico
 * - incrementarNotasDesarrollo: llama al método polimórfico del estudiante
 * - modificarNotaEstudiante: recorre el array Asignatura[] sin conocer los tipos concretos
 * - navegarYCalcularPromedios: delega al modelo que también es polimórfico
 */
package controlador;

import java.util.ArrayList;
import modelo.Asignatura;
import modelo.Estudiante;
import modelo.ModeloEstudiantes;
import vista.VistaEstudiantes;

public class ControladorEstudiantes {

    private ModeloEstudiantes modelo;
    private VistaEstudiantes  vista;

    public ControladorEstudiantes() {
        this.modelo = new ModeloEstudiantes();
        this.vista  = new VistaEstudiantes();
    }

    // ============= BUCLE PRINCIPAL =============

    public void ejecutar() {
        int opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.leerEntero("");
            switch (opcion) {
                case 1: registrarEstudiantes();         break;
                case 2: mostrarEstudiantes();           break;
                case 3: filtrarPorNotaLimite();         break;
                case 4: incrementarNotasDesarrollo();   break;
                case 5: modificarNotaEstudiante();      break;
                case 6: navegarYCalcularPromedios();    break;
                case 7: vista.mostrarMensaje("¡Hasta luego!"); break;
                default: vista.mostrarMensaje("Opción no válida.");
            }
        } while (opcion != 7);
    }

    // ============= MÉTODO 1: REGISTRAR =============

    public void registrarEstudiantes() {
        int n = vista.leerEntero("Ingrese el número de estudiantes: ");
        for (int i = 0; i < n; i++) {
            vista.mostrarMensaje("\n--- Estudiante " + (i + 1) + " ---");

            int codigo;
            do {
                codigo = vista.leerEntero("Código (mayor a 21000): ");
                if (codigo <= 21000)
                    vista.mostrarMensaje("Error: El código debe ser mayor a 21000");
            } while (codigo <= 21000);

            String nombre = vista.leerString("Nombre: ");

            double notaDesarrollo;
            do {
                notaDesarrollo = vista.leerDouble("Nota de Desarrollo (0.0 - 5.0): ");
                if (notaDesarrollo < 0 || notaDesarrollo > 5)
                    vista.mostrarMensaje("Error: La nota debe estar entre 0.0 y 5.0");
            } while (notaDesarrollo < 0 || notaDesarrollo > 5);

            double notaMatematica;
            do {
                notaMatematica = vista.leerDouble("Nota de Matemática (0.0 - 5.0): ");
                if (notaMatematica < 0 || notaMatematica > 5)
                    vista.mostrarMensaje("Error: La nota debe estar entre 0.0 y 5.0");
            } while (notaMatematica < 0 || notaMatematica > 5);

            // El constructor de Estudiante crea el array Asignatura[] internamente
            modelo.agregarEstudiante(new Estudiante(codigo, nombre, notaDesarrollo, notaMatematica));
        }
        vista.mostrarMensaje("\nEstudiantes registrados exitosamente.");
    }

    // ============= MÉTODO 2: MOSTRAR =============

    public void mostrarEstudiantes() {
        if (modelo.getEstudiantes().isEmpty()) {
            vista.mostrarMensaje("No hay estudiantes registrados.");
            return;
        }
        modelo.ordenarPorNotaDefinitiva();
        vista.mostrarListaEstudiantes(modelo.getEstudiantes());
    }

    // ============= MÉTODO 3: FILTRAR =============

    public void filtrarPorNotaLimite() {
        if (modelo.getEstudiantes().isEmpty()) {
            vista.mostrarMensaje("No hay estudiantes registrados.");
            return;
        }
        double notaLimite;
        do {
            notaLimite = vista.leerDouble("Ingrese nota límite (0.0 - 5.0): ");
            if (notaLimite < 0 || notaLimite > 5)
                vista.mostrarMensaje("Error: La nota debe estar entre 0.0 y 5.0");
        } while (notaLimite < 0 || notaLimite > 5);

        ArrayList<Estudiante> filtrados = new ArrayList<>();
        for (Estudiante e : modelo.getEstudiantes()) {
            if (e.calcularDefinitiva() > notaLimite) filtrados.add(e);
        }

        if (filtrados.isEmpty()) {
            vista.mostrarMensaje("No hay estudiantes con definitiva superior a " + notaLimite);
        } else {
            vista.mostrarMensaje("\nEstudiantes con definitiva > " + notaLimite + ":");
            for (Estudiante e : filtrados) {
                vista.mostrarMensaje("Código: " + e.getCodigo() +
                    " | Nombre: " + e.getNombre() +
                    " | Definitiva: " + String.format("%.2f", e.calcularDefinitiva()));
            }
        }
    }

    // ============= MÉTODO 4: INCREMENTAR =============
    /**
     * POLIMORFISMO:
     * El controlador simplemente llama a incrementarNotasIncrementables().
     * Estudiante recorre su array Asignatura[] y cada asignatura decide si
     * acepta o no el incremento (según esIncrementable()).
     * El controlador no necesita saber qué asignatura es Desarrollo.
     */
    public void incrementarNotasDesarrollo() {
        if (modelo.getEstudiantes().isEmpty()) {
            vista.mostrarMensaje("No hay estudiantes registrados.");
            return;
        }
        double incremento;
        do {
            incremento = vista.leerDouble("Ingrese incremento (0.0 - 0.5): ");
            if (incremento < 0 || incremento > 0.5)
                vista.mostrarMensaje("Error: El incremento debe estar entre 0.0 y 0.5");
        } while (incremento < 0 || incremento > 0.5);

        for (Estudiante e : modelo.getEstudiantes()) {
            e.incrementarNotasIncrementables(incremento);  // ← polimórfico
        }
        vista.mostrarMensaje("Notas incrementables actualizadas exitosamente.");
    }

    // ============= MÉTODO 5: MODIFICAR NOTA =============
    /**
     * POLIMORFISMO EN EL MENÚ:
     * En lugar de mostrar "1. Desarrollo / 2. Matemática" hardcodeado,
     * recorremos el array Asignatura[] del estudiante y mostramos
     * getNombre() + getDescripcion() de cada uno.
     * Si el arreglo tuviera 3 asignaturas, el menú aparecería con 3 opciones
     * sin cambiar ninguna línea aquí.
     */
    public void modificarNotaEstudiante() {
        if (modelo.getEstudiantes().isEmpty()) {
            vista.mostrarMensaje("No hay estudiantes registrados.");
            return;
        }

        int codigo = vista.leerEntero("Ingrese el código del estudiante: ");
        Estudiante e = modelo.getEstudiantePorCodigo(codigo);
        if (e == null) {
            vista.mostrarMensaje("No se encontró estudiante con ese código.");
            return;
        }

        vista.mostrarMensaje("\nEstudiante encontrado:");
        vista.mostrarEstudiante(e);

        // MENÚ POLIMÓRFICO: construido desde el array Asignatura[]
        Asignatura[] asigs = e.getAsignaturas();
        vista.mostrarMensaje("\n¿Qué nota desea modificar?");
        for (int i = 0; i < asigs.length; i++) {
            // getNombre() y getDescripcion() son polimórficos
            vista.mostrarMensaje((i + 1) + ". " + asigs[i].getNombre()
                + " — " + asigs[i].getDescripcion());
        }

        int opcion = vista.leerEntero("Seleccione: ");
        if (opcion < 1 || opcion > asigs.length) {
            vista.mostrarMensaje("Opción no válida.");
            return;
        }

        double nuevaNota;
        do {
            nuevaNota = vista.leerDouble("Nueva nota de "
                + asigs[opcion - 1].getNombre() + " (0.0 - 5.0): ");
            if (nuevaNota < 0 || nuevaNota > 5)
                vista.mostrarMensaje("Error: La nota debe estar entre 0.0 y 5.0");
        } while (nuevaNota < 0 || nuevaNota > 5);

        asigs[opcion - 1].setNota(nuevaNota);  // ← setNota es del padre, funciona para todas
        vista.mostrarMensaje("Nota de " + asigs[opcion - 1].getNombre() + " modificada exitosamente.");
    }

    // ============= MÉTODO 6: PROMEDIOS =============

    public void navegarYCalcularPromedios() {
        if (modelo.getEstudiantes().isEmpty()) {
            vista.mostrarMensaje("No hay estudiantes registrados.");
            return;
        }
        double[] promedios = modelo.navegarYCalcularPromedioAsignaturas();

        vista.mostrarMensaje("\nPromedio de notas por asignatura:");
        Asignatura[] refs = modelo.getEstudiantes().get(0).getAsignaturas();
        for (int i = 0; i < promedios.length; i++) {
            // getNombre() es polimórfico: muestra el nombre real de cada asignatura
            vista.mostrarMensaje(refs[i].getNombre() + ": "
                + String.format("%.2f", promedios[i]));
        }
    }
}