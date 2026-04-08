/**
 * @author Jose David Molano Perdomo
 */

package controlador;

import java.util.ArrayList;
import modelo.Estudiante;
import modelo.ModeloEstudiantes;
import vista.VistaEstudiantes;

public class ControladorEstudiantes {
    
    private ModeloEstudiantes modelo;
    private VistaEstudiantes vista;
    
    public ControladorEstudiantes() {
        this.modelo = new ModeloEstudiantes();
        this.vista = new VistaEstudiantes();
    }
    
    public void ejecutar() {
        int opcion;
        
        do {
            vista.mostrarMenu();
            opcion = vista.leerEntero("");
            
            switch (opcion) {
                case 1:
                    registrarEstudiantes();
                    break;
                case 2:
                    mostrarEstudiantes();
                    break;
                case 3:
                    filtrarPorNotaLimite();
                    break;
                case 4:
                    incrementarNotasDesarrollo();
                    break;
                case 5:
                    modificarNotaEstudiante();
                    break;
                case 6:
                    navegarYCalcularPromedios();
                    break;
                case 7:
                    procesarConPolimorfismo();  // NUEVO: Polimorfismo
                    break;
                case 8:
                    mostrarDiferentesFormasCalculo();  // NUEVO: Sobrecarga polimórfica
                    break;
                case 9:
                    vista.mostrarMensaje("¡Hasta luego!");
                    break;
                default:
                    vista.mostrarMensaje("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 9);
    }
    
    public void registrarEstudiantes() {
        int n = vista.leerEntero("Ingrese el número de estudiantes: ");
        
        for (int i = 0; i < n; i++) {
            vista.mostrarMensaje("\n--- Estudiante " + (i + 1) + " ---");
            
            int codigo;
            do {
                codigo = vista.leerEntero("Código (mayor a 21000): ");
                if (codigo <= 21000) {
                    vista.mostrarMensaje("Error: El código debe ser mayor a 21000");
                }
            } while (codigo <= 21000);
            
            String nombre = vista.leerString("Nombre: ");
            
            double notaDesarrollo;
            do {
                notaDesarrollo = vista.leerDouble("Nota de Desarrollo (0.0 - 5.0): ");
                if (notaDesarrollo < 0 || notaDesarrollo > 5) {
                    vista.mostrarMensaje("Error: La nota debe estar entre 0.0 y 5.0");
                }
            } while (notaDesarrollo < 0 || notaDesarrollo > 5);
            
            double notaMatematica;
            do {
                notaMatematica = vista.leerDouble("Nota de Matemática (0.0 - 5.0): ");
                if (notaMatematica < 0 || notaMatematica > 5) {
                    vista.mostrarMensaje("Error: La nota debe estar entre 0.0 y 5.0");
                }
            } while (notaMatematica < 0 || notaMatematica > 5);
            
            Estudiante e = new Estudiante(codigo, nombre, notaDesarrollo, notaMatematica);
            modelo.agregarEstudiante(e);
        }
        
        vista.mostrarMensaje("\nEstudiantes registrados exitosamente.");
    }
    
    public void mostrarEstudiantes() {
        if (modelo.getEstudiantes().isEmpty()) {
            vista.mostrarMensaje("No hay estudiantes registrados.");
            return;
        }
        
        modelo.ordenarPorNotaDefinitiva();
        vista.mostrarListaEstudiantes(modelo.getEstudiantes());
    }
    
    public void filtrarPorNotaLimite() {
        if (modelo.getEstudiantes().isEmpty()) {
            vista.mostrarMensaje("No hay estudiantes registrados.");
            return;
        }
        
        double notaLimite;
        do {
            notaLimite = vista.leerDouble("Ingrese nota límite (0.0 - 5.0): ");
            if (notaLimite < 0 || notaLimite > 5) {
                vista.mostrarMensaje("Error: La nota debe estar entre 0.0 y 5.0");
            }
        } while (notaLimite < 0 || notaLimite > 5);
        
        ArrayList<Estudiante> filtrados = new ArrayList<>();
        
        for (Estudiante e : modelo.getEstudiantes()) {
            if (e.calcularDefinitiva() > notaLimite) {
                filtrados.add(e);
            }
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
    
    public void incrementarNotasDesarrollo() {
        if (modelo.getEstudiantes().isEmpty()) {
            vista.mostrarMensaje("No hay estudiantes registrados.");
            return;
        }
        
        double incremento;
        do {
            incremento = vista.leerDouble("Ingrese incremento (0.0 - 0.5): ");
            if (incremento < 0 || incremento > 0.5) {
                vista.mostrarMensaje("Error: El incremento debe estar entre 0.0 y 0.5");
            }
        } while (incremento < 0 || incremento > 0.5);
        
        for (Estudiante e : modelo.getEstudiantes()) {
            e.incrementarNotaDesarrollo(incremento);
        }
        
        vista.mostrarMensaje("Notas de Desarrollo incrementadas exitosamente.");
    }
    
    public void modificarNotaEstudiante() {
        if (modelo.getEstudiantes().isEmpty()) {
            vista.mostrarMensaje("No hay estudiantes registrados.");
            return;
        }
        
        int codigo = vista.leerEntero("Ingrese el código del estudiante a modificar: ");
        Estudiante e = modelo.getEstudiantePorCodigo(codigo);
        
        if (e == null) {
            vista.mostrarMensaje("No se encontró estudiante con ese código.");
            return;
        }
        
        vista.mostrarMensaje("\nEstudiante encontrado:");
        vista.mostrarEstudiante(e);
        
        vista.mostrarMensaje("\n1. Modificar nota de Desarrollo");
        vista.mostrarMensaje("2. Modificar nota de Matemática");
        int opcion = vista.leerEntero("Seleccione qué nota modificar: ");
        
        if (opcion == 1) {
            double nuevaNota;
            do {
                nuevaNota = vista.leerDouble("Nueva nota de Desarrollo (0.0 - 5.0): ");
                if (nuevaNota < 0 || nuevaNota > 5) {
                    vista.mostrarMensaje("Error: La nota debe estar entre 0.0 y 5.0");
                }
            } while (nuevaNota < 0 || nuevaNota > 5);
            
            e.modificarNotaDesarrollo(nuevaNota);
            vista.mostrarMensaje("Nota de Desarrollo modificada exitosamente.");
            
        } else if (opcion == 2) {
            double nuevaNota;
            do {
                nuevaNota = vista.leerDouble("Nueva nota de Matemática (0.0 - 5.0): ");
                if (nuevaNota < 0 || nuevaNota > 5) {
                    vista.mostrarMensaje("Error: La nota debe estar entre 0.0 y 5.0");
                }
            } while (nuevaNota < 0 || nuevaNota > 5);
            
            e.modificarNotaMatematica(nuevaNota);
            vista.mostrarMensaje("Nota de Matemática modificada exitosamente.");
            
        } else {
            vista.mostrarMensaje("Opción no válida.");
        }
    }
    
    public void navegarYCalcularPromedios() {
        if (modelo.getEstudiantes().isEmpty()) {
            vista.mostrarMensaje("No hay estudiantes registrados.");
            return;
        }
        
        double[] promedios = modelo.navegarYCalcularPromedioAsignaturas();
        
        vista.mostrarMensaje("\nPromedio de notas por asignatura:");
        vista.mostrarMensaje("Desarrollo: " + String.format("%.2f", promedios[0]));
        vista.mostrarMensaje("Matemática: " + String.format("%.2f", promedios[1]));
    }
    
    // ============= NUEVO MÉTODO CON POLIMORFISMO =============
    
    public void procesarConPolimorfismo() {
        if (modelo.getEstudiantes().isEmpty()) {
            vista.mostrarMensaje("No hay estudiantes registrados.");
            return;
        }
        
        vista.mostrarMensaje("\n--- OPERACIONES POLIMÓRFICAS ---");
        vista.mostrarMensaje("1. Calcular nota máxima (entre Desarrollo y Matemática)");
        vista.mostrarMensaje("2. Calcular nota mínima (entre Desarrollo y Matemática)");
        vista.mostrarMensaje("3. Calcular diferencia entre notas");
        
        int opcion = vista.leerEntero("Seleccione una operación: ");
        
        if (opcion >= 1 && opcion <= 3) {
            modelo.procesarConPolimorfismo(opcion);
        } else {
            vista.mostrarMensaje("Opción no válida.");
        }
    }
    
    public void mostrarDiferentesFormasCalculo() {
        if (modelo.getEstudiantes().isEmpty()) {
            vista.mostrarMensaje("No hay estudiantes registrados.");
            return;
        }
        
        modelo.mostrarDiferentesFormasCalculo();
    }
}