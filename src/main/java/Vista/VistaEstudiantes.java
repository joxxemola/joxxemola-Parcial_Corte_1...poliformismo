/**
 * @author Jose David Molano Perdomo
 */

package vista;

import java.util.ArrayList;
import java.util.Scanner;
import modelo.Estudiante;

public class VistaEstudiantes {
    
    private Scanner scanner;
    
    public VistaEstudiantes() {
        this.scanner = new Scanner(System.in);
    }
    
    public void mostrarMenu() {
        System.out.println("\n===== SISTEMA DE GESTIÓN DE ESTUDIANTES =====");
        System.out.println("1. Registrar estudiantes");
        System.out.println("2. Mostrar estudiantes ordenados por definitiva");
        System.out.println("3. Filtrar por nota límite");
        System.out.println("4. Incrementar notas de Desarrollo");
        System.out.println("5. Modificar nota de un estudiante");
        System.out.println("6. Calcular promedios por asignatura");
        System.out.println("7. *** PROCESAR CON POLIMORFISMO (Máx/Mín/Dif) ***");
        System.out.println("8. *** MOSTRAR DIFERENTES FORMAS DE CÁLCULO ***");
        System.out.println("9. Salir");
        System.out.print("Seleccione una opción: ");
    }
    
    public int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextInt();
    }
    
    public double leerDouble(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextDouble();
    }
    
    public String leerString(String mensaje) {
        System.out.print(mensaje);
        scanner.nextLine();
        return scanner.nextLine();
    }
    
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    
    public void mostrarEstudiante(Estudiante e) {
        System.out.println("Código: " + e.getCodigo());
        System.out.println("Nombre: " + e.getNombre());
        System.out.println("Nota Desarrollo: " + e.getNotaDesarrollo());
        System.out.println("Nota Matemática: " + e.getNotaMatematica());
        System.out.println("Nota Definitiva: " + String.format("%.2f", e.calcularDefinitiva()));
        System.out.println("Estado: " + e.verificarAprobacion());
    }
    
    public void mostrarListaEstudiantes(ArrayList<Estudiante> lista) {
        System.out.println("\n=== LISTA DE ESTUDIANTES ===");
        System.out.println("Código | Nombre | Nota Des | Nota Mat | Definitiva | Estado");
        System.out.println("----------------------------------------------------------------");
        for (Estudiante e : lista) {
            System.out.printf("%d | %s | %.2f | %.2f | %.2f | %s%n",
                e.getCodigo(), e.getNombre(), e.getNotaDesarrollo(), 
                e.getNotaMatematica(), e.calcularDefinitiva(), e.verificarAprobacion());
        }
    }
}