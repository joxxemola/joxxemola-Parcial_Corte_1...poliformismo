/**
 * @author Jose David Molano Perdomo
 */

package controlador;  // Pertenece a la capa Controlador (lógica de negocio)

import java.util.ArrayList;             // Importa la clase Estudiante para crearlos y manipularlos

import modelo.Estudiante;               // Importa el Modelo para acceder a los datos
import modelo.ModeloEstudiantes;        // Importa la Vista para mostrar información y recibir datos
import vista.VistaEstudiantes;          // Importa ArrayList para crear listas temporales

public class ControladorEstudiantes {
    
    // ============= ATRIBUTOS =============
    private ModeloEstudiantes modelo;  // Referencia al MODELO (datos)
    private VistaEstudiantes vista;    // Referencia a la VISTA (interfaz)
    
    // ============= CONSTRUCTOR =============
    /**
     * Constructor - Crea el modelo y la vista que va a utilizar el controlador
     * Se ejecuta al crear un nuevo ControladorEstudiantes
     */
    public ControladorEstudiantes() {
        this.modelo = new ModeloEstudiantes();  // Crea el modelo (lista de estudiantes vacía)
        this.vista = new VistaEstudiantes();    // Crea la vista (para interactuar con el usuario)
    }
    
    // ============= MÉTODO PRINCIPAL =============
    /**
     * Ejecuta el programa principal
     * Muestra el menú y procesa las opciones del usuario en un ciclo
     */
    public void ejecutar() {
        int opcion;  // Variable para guardar la opción del usuario
        
        // Ciclo do-while: se ejecuta al menos una vez y hasta que el usuario elija salir (opción 7)
        do {
            vista.mostrarMenu();                 // Muestra el menú de opciones
            opcion = vista.leerEntero("");       // Lee la opción del usuario
            
            // Procesa la opción seleccionada
            switch (opcion) {
                case 1:
                    registrarEstudiantes();       // Opción 1: Registrar estudiantes
                    break;
                case 2:
                    mostrarEstudiantes();         // Opción 2: Mostrar todos ordenados
                    break;
                case 3:
                    filtrarPorNotaLimite();       // Opción 3: Filtrar por nota límite
                    break;
                case 4:
                    incrementarNotasDesarrollo(); // Opción 4: Incrementar notas de Desarrollo
                    break;
                case 5:
                    modificarNotaEstudiante();    // Opción 5: Modificar nota de un estudiante
                    break;
                case 6:
                    navegarYCalcularPromedios();  // Opción 6: Calcular promedios (navegación)
                    break;
                case 7:
                    vista.mostrarMensaje("¡Hasta luego!");  // Opción 7: Salir
                    break;
                default:
                    vista.mostrarMensaje("Opción no válida. Intente de nuevo.");  // Opción inválida
            }
        } while (opcion != 7);  // Repite hasta que el usuario elija salir
    }
    
    // ============= MÉTODO 1: REGISTRAR ESTUDIANTES =============
    /**
     * Registra uno o más estudiantes en el sistema
     * Pide los datos por teclado, los valida y los guarda en el modelo
     */
    public void registrarEstudiantes() {
        // Pide cuántos estudiantes va a registrar
        int n = vista.leerEntero("Ingrese el número de estudiantes: ");
        
        // Ciclo para registrar cada estudiante
        for (int i = 0; i < n; i++) {
            vista.mostrarMensaje("\n--- Estudiante " + (i + 1) + " ---");
            
            // ===== VALIDACIÓN DEL CÓDIGO =====
            int codigo;
            do {
                codigo = vista.leerEntero("Código (mayor a 21000): ");
                if (codigo <= 21000) {
                    vista.mostrarMensaje("Error: El código debe ser mayor a 21000");
                }
            } while (codigo <= 21000);  // Repite hasta que el código sea válido
            
            // ===== LECTURA DEL NOMBRE =====
            String nombre = vista.leerString("Nombre: ");
            
            // ===== VALIDACIÓN DE NOTA DESARROLLO =====
            double notaDesarrollo;
            do {
                notaDesarrollo = vista.leerDouble("Nota de Desarrollo (0.0 - 5.0): ");
                if (notaDesarrollo < 0 || notaDesarrollo > 5) {
                    vista.mostrarMensaje("Error: La nota debe estar entre 0.0 y 5.0");
                }
            } while (notaDesarrollo < 0 || notaDesarrollo > 5);  // Repite hasta que sea válida
            
            // ===== VALIDACIÓN DE NOTA MATEMÁTICA =====
            double notaMatematica;
            do {
                notaMatematica = vista.leerDouble("Nota de Matemática (0.0 - 5.0): ");
                if (notaMatematica < 0 || notaMatematica > 5) {
                    vista.mostrarMensaje("Error: La nota debe estar entre 0.0 y 5.0");
                }
            } while (notaMatematica < 0 || notaMatematica > 5);  // Repite hasta que sea válida
            
            // ===== CREACIÓN Y GUARDADO =====
            // Crea un nuevo objeto Estudiante con los datos ingresados
            Estudiante e = new Estudiante(codigo, nombre, notaDesarrollo, notaMatematica);
            
            // Guarda el estudiante en el modelo (en la lista)
            modelo.agregarEstudiante(e);
        }
        
        // Mensaje de confirmación
        vista.mostrarMensaje("\nEstudiantes registrados exitosamente.");
    }
    
    // ============= MÉTODO 2: MOSTRAR ESTUDIANTES =============
    /**
     * Muestra todos los estudiantes ordenados por nota definitiva (ascendente)
     */
    public void mostrarEstudiantes() {
        // Verifica si hay estudiantes registrados
        if (modelo.getEstudiantes().isEmpty()) {
            vista.mostrarMensaje("No hay estudiantes registrados.");
            return;  // Sale del método si no hay estudiantes
        }
        
        // Ordena los estudiantes por nota definitiva (llama al método del modelo)
        modelo.ordenarPorNotaDefinitiva();
        
        // Muestra la lista completa usando la vista
        vista.mostrarListaEstudiantes(modelo.getEstudiantes());
    }
    
    // ============= MÉTODO 3: FILTRAR POR NOTA LÍMITE =============
    /**
     * Filtra y muestra los estudiantes cuya nota definitiva es mayor a un límite dado
     */
    public void filtrarPorNotaLimite() {
        // Verifica si hay estudiantes registrados
        if (modelo.getEstudiantes().isEmpty()) {
            vista.mostrarMensaje("No hay estudiantes registrados.");
            return;
        }
        
        // ===== VALIDACIÓN DE NOTA LÍMITE =====
        double notaLimite;
        do {
            notaLimite = vista.leerDouble("Ingrese nota límite (0.0 - 5.0): ");
            if (notaLimite < 0 || notaLimite > 5) {
                vista.mostrarMensaje("Error: La nota debe estar entre 0.0 y 5.0");
            }
        } while (notaLimite < 0 || notaLimite > 5);
        
        // ===== FILTRADO =====
        ArrayList<Estudiante> filtrados = new ArrayList<>();  // Lista temporal para los que cumplen
        
        // Recorre todos los estudiantes
        for (Estudiante e : modelo.getEstudiantes()) {
            // Si la definitiva es mayor que el límite, lo agrega a la lista filtrados
            if (e.calcularDefinitiva() > notaLimite) {
                filtrados.add(e);
            }
        }
        
        // ===== MOSTRAR RESULTADOS =====
        if (filtrados.isEmpty()) {
            vista.mostrarMensaje("No hay estudiantes con definitiva superior a " + notaLimite);
        } else {
            vista.mostrarMensaje("\nEstudiantes con definitiva > " + notaLimite + ":");
            // Muestra cada estudiante filtrado (solo código, nombre y definitiva)
            for (Estudiante e : filtrados) {
                vista.mostrarMensaje("Código: " + e.getCodigo() + 
                                   " | Nombre: " + e.getNombre() + 
                                   " | Definitiva: " + String.format("%.2f", e.calcularDefinitiva()));
            }
        }
    }
    
    // ============= MÉTODO 4: INCREMENTAR NOTAS DE DESARROLLO =============
    /**
     * Incrementa la nota de Desarrollo de TODOS los estudiantes
     * Controla que no se pase de 5.0
     */
    public void incrementarNotasDesarrollo() {
        // Verifica si hay estudiantes registrados
        if (modelo.getEstudiantes().isEmpty()) {
            vista.mostrarMensaje("No hay estudiantes registrados.");
            return;
        }
        
        // ===== VALIDACIÓN DEL INCREMENTO =====
        double incremento;
        do {
            incremento = vista.leerDouble("Ingrese incremento (0.0 - 0.5): ");
            if (incremento < 0 || incremento > 0.5) {
                vista.mostrarMensaje("Error: El incremento debe estar entre 0.0 y 0.5");
            }
        } while (incremento < 0 || incremento > 0.5);
        
        // ===== APLICAR INCREMENTO A TODOS =====
        // Recorre todos los estudiantes
        for (Estudiante e : modelo.getEstudiantes()) {
            // Llama al método del estudiante que incrementa la nota (con control de límite)
            e.incrementarNotaDesarrollo(incremento);
        }
        
        vista.mostrarMensaje("Notas de Desarrollo incrementadas exitosamente.");
    }
    
    // ============= MÉTODO 5: MODIFICAR NOTA DE UN ESTUDIANTE =============
    /**
     * Busca un estudiante por código y permite modificar una de sus notas
     */
    public void modificarNotaEstudiante() {
        // Verifica si hay estudiantes registrados
        if (modelo.getEstudiantes().isEmpty()) {
            vista.mostrarMensaje("No hay estudiantes registrados.");
            return;
        }
        
        // ===== BUSCAR ESTUDIANTE POR CÓDIGO =====
        int codigo = vista.leerEntero("Ingrese el código del estudiante a modificar: ");
        Estudiante e = modelo.getEstudiantePorCodigo(codigo);  // Busca en el modelo
        
        // Verifica si encontró al estudiante
        if (e == null) {
            vista.mostrarMensaje("No se encontró estudiante con ese código.");
            return;
        }
        
        // ===== MOSTRAR DATOS ACTUALES =====
        vista.mostrarMensaje("\nEstudiante encontrado:");
        vista.mostrarEstudiante(e);  // Muestra la información actual
        
        // ===== PREGUNTAR QUÉ NOTA MODIFICAR =====
        vista.mostrarMensaje("\n1. Modificar nota de Desarrollo");
        vista.mostrarMensaje("2. Modificar nota de Matemática");
        int opcion = vista.leerEntero("Seleccione qué nota modificar: ");
        
        // ===== PROCESAR SEGÚN LA OPCIÓN =====
        if (opcion == 1) {
            // MODIFICAR NOTA DE DESARROLLO
            double nuevaNota;
            do {
                nuevaNota = vista.leerDouble("Nueva nota de Desarrollo (0.0 - 5.0): ");
                if (nuevaNota < 0 || nuevaNota > 5) {
                    vista.mostrarMensaje("Error: La nota debe estar entre 0.0 y 5.0");
                }
            } while (nuevaNota < 0 || nuevaNota > 5);
            
            e.modificarNotaDesarrollo(nuevaNota);  // Llama al método del estudiante
            vista.mostrarMensaje("Nota de Desarrollo modificada exitosamente.");
            
        } else if (opcion == 2) {
            // MODIFICAR NOTA DE MATEMÁTICA
            double nuevaNota;
            do {
                nuevaNota = vista.leerDouble("Nueva nota de Matemática (0.0 - 5.0): ");
                if (nuevaNota < 0 || nuevaNota > 5) {
                    vista.mostrarMensaje("Error: La nota debe estar entre 0.0 y 5.0");
                }
            } while (nuevaNota < 0 || nuevaNota > 5);
            
            e.modificarNotaMatematica(nuevaNota);  // Llama al método del estudiante
            vista.mostrarMensaje("Nota de Matemática modificada exitosamente.");
            
        } else {
            vista.mostrarMensaje("Opción no válida.");
        }
    }
    
    // ============= MÉTODO 6: NAVEGAR Y CALCULAR PROMEDIOS =============
    /**
     * Calcula los promedios de las asignaturas (método con navegación del modelo)
     * Este método demuestra la navegación a través de las clases contenidas
     */
    public void navegarYCalcularPromedios() {
        // Verifica si hay estudiantes registrados
        if (modelo.getEstudiantes().isEmpty()) {
            vista.mostrarMensaje("No hay estudiantes registrados.");
            return;
        }
        
        // Llama al método del modelo que NAVEGA por todos los estudiantes y DECIDE
        double[] promedios = modelo.navegarYCalcularPromedioAsignaturas();
        
        // Muestra los resultados
        vista.mostrarMensaje("\nPromedio de notas por asignatura:");
        vista.mostrarMensaje("Desarrollo: " + String.format("%.2f", promedios[0]));
        vista.mostrarMensaje("Matemática: " + String.format("%.2f", promedios[1]));
    }
}