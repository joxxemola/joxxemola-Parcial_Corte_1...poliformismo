/**
 * @author Jose David Molano Perdomo
 */

package vista;                  // Pertenece a la capa Vista (interfaz de usuario)

import java.util.List;          // Importa la clase Estudiante para mostrarla
import java.util.Scanner;       // Importa List para recibir listas de estudiantes

import modelo.Estudiante;       // Importa Scanner para leer datos del teclado

public class VistaEstudiantes {
    
    // ============= ATRIBUTO =============
    private Scanner scanner;  // Objeto para leer lo que el usuario escribe por teclado
    
    // ============= CONSTRUCTOR =============
    /**
     * Constructor - Inicializa el scanner para leer datos del teclado
     * Se ejecuta al crear una nueva VistaEstudiantes
     */
    public VistaEstudiantes() {
        this.scanner = new Scanner(System.in);  // Crea el scanner conectado al teclado (System.in)
    }
    
    // ============= MÉTODOS DE SALIDA (Mostrar información) =============
    
    /**
     * Muestra un mensaje simple en pantalla
     * @param mensaje El texto a mostrar
     */
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);  // Imprime el mensaje y hace un salto de línea
    }
    
    /**
     * Muestra la información completa de UN estudiante
     * Incluye: código, nombre, ambas notas, definitiva y si aprueba
     * @param e El objeto Estudiante a mostrar
     */
    public void mostrarEstudiante(Estudiante e) {
        // Calcula la nota definitiva llamando al método del estudiante
        double definitiva = e.calcularDefinitiva();
        
        // Verifica si aprueba llamando al método del estudiante
        String aprobacion = e.verificarAprobacion();
        
        // Muestra todos los datos en una línea formateada
        System.out.println("Código: " + e.getCodigo() + " | Nombre: " + e.getNombre() + " | Nota Desarrollo: " + e.getNotaDesarrollo() + " | Nota Matemática: " + e.getNotaMatematica() +  " | Definitiva: " + String.format("%.2f", definitiva) + " | " + aprobacion);}
    
    /**
     * Muestra una lista COMPLETA de estudiantes
     * @param lista Lista de objetos Estudiante a mostrar
     */
    public void mostrarListaEstudiantes(List<Estudiante> lista) {
        // Verifica si la lista está vacía
        if (lista.isEmpty()) {
            System.out.println("No hay estudiantes para mostrar.");
            return;  // Sale del método sin hacer nada más
        }
        
        // Encabezado de la lista
        System.out.println("\n=== LISTA DE ESTUDIANTES ===");
        
        // Recorre la lista y muestra cada estudiante (REUTILIZA mostrarEstudiante)
        for (Estudiante e : lista) {
            mostrarEstudiante(e);  // Llama al método que muestra un estudiante
        }
        
        // Pie de la lista
        System.out.println("=============================\n");
    }
    
    /**
     * Muestra el menú principal del programa con todas las opciones
     */
    public void mostrarMenu() {
        System.out.println("\n======== SISTEMA DE NOTAS DE ESTUDIANTES =======");
        System.out.println("=================================================");
        System.out.println("1. Registrar estudiantes");
        System.out.println("2. Mostrar todos los estudiantes (orden ascendente)");
        System.out.println("3. Filtrar por nota límite");
        System.out.println("4. Incrementar notas de Desarrollo");
        System.out.println("5. Modificar nota de un estudiante");
        System.out.println("6. Calcular promedios por asignatura (navegación)");
        System.out.println("7. Salir");
        System.out.println("=================================================");
        System.out.print("Seleccione una opción: ");  // Print sin "ln" para que el usuario escriba al lado
    }
    
    // ============= MÉTODOS DE ENTRADA (Leer datos del usuario) =============
    
    /**
     * Lee un número ENTERO ingresado por el usuario con validación
     * @param mensaje El texto que se muestra para pedir el dato
     * @return El número entero ingresado
     */
    public int leerEntero(String mensaje) {
        System.out.print(mensaje);  // Muestra el mensaje (ej: "Ingrese código: ")
        
        // VALIDACIÓN: Mientras no se ingrese un entero, sigue pidiendo
        while (!scanner.hasNextInt()) {
            System.out.print("Error. Ingrese un número entero: ");  // Mensaje de error
            scanner.next();  // Descarta lo que no es número y sigue esperando
        }
        
        int valor = scanner.nextInt();     // Lee el número entero
        scanner.nextLine();                // Limpia el buffer (consume el enter)
        return valor;                      // Devuelve el número leído
    }
    
    /**
     * Lee un número DECIMAL (double) ingresado por el usuario con validación
     * @param mensaje El texto que se muestra para pedir el dato
     * @return El número decimal ingresado
     */
    public double leerDouble(String mensaje) {
        System.out.print(mensaje);  // Muestra el mensaje (ej: "Ingrese nota: ")
        
        // VALIDACIÓN: Mientras no se ingrese un decimal, sigue pidiendo
        while (!scanner.hasNextDouble()) {
            System.out.print("Error. Ingrese un número válido: ");  // Mensaje de error
            scanner.next();  // Descarta lo que no es número y sigue esperando
        }
        
        double valor = scanner.nextDouble();  // Lee el número decimal
        scanner.nextLine();                   // Limpia el buffer (consume el enter)
        return valor;                          // Devuelve el número leído
    }
    
    /**
     * Lee una línea de TEXTO (String) ingresada por el usuario
     * @param mensaje El texto que se muestra para pedir el dato
     * @return El texto ingresado (puede incluir espacios)
     */
    public String leerString(String mensaje) {
        String input;
        // Pide hasta que el usuario ingrese solo letras (incluye letras Unicode y espacios)
        while (true) {
            System.out.print(mensaje);
            input = scanner.nextLine().trim();
            if (input.matches("^[\\p{L} ]+$")) { // Solo letras y espacios
                return input;
            }
            System.out.println("Error: solo se permiten letras y espacios. Intente de nuevo.");
        }
    }
}