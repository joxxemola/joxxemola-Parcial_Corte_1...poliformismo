/**
 * @author Jose David Molano Perdomo
 */

package modelo;  // Pertenece a la capa Modelo (datos)

import java.util.ArrayList;  // Importa la clase ArrayList para manejar listas dinámicas

public class ModeloEstudiantes {
    
    // ============= ATRIBUTO =============
    private ArrayList<Estudiante> estudiantes;  // Lista dinámica que almacena TODOS los estudiantes
    
    // ============= CONSTRUCTOR =============
    /**
     * Constructor - Inicializa la lista de estudiantes vacía
     * Se ejecuta al crear un nuevo objeto ModeloEstudiantes
     */
    public ModeloEstudiantes() {
        // Crea una nueva ArrayList vacía para guardar estudiantes
        this.estudiantes = new ArrayList<>();
    }
    
    // ============= MÉTODOS BÁSICOS DE GESTIÓN =============
    
    /**
     * Agrega un nuevo estudiante a la colección
     * @param estudiante El objeto Estudiante a agregar
     */
    public void agregarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);  // Añade el estudiante al final de la lista
    }
    
    /**
     * Obtiene la lista completa de estudiantes
     * @return ArrayList con todos los estudiantes registrados
     */
    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;  // Devuelve la lista completa
    }
    
    /**
     * Busca un estudiante por su código único
     * @param codigo El código del estudiante a buscar
     * @return El objeto Estudiante si lo encuentra, null si no existe
     */
    public Estudiante getEstudiantePorCodigo(int codigo) {
        // Recorre TODA la lista de estudiantes (NAVEGACIÓN)
        for (Estudiante e : estudiantes) {
            // Compara el código de cada estudiante con el buscado
            if (e.getCodigo() == codigo) {
                return e;  // Si lo encuentra, lo devuelve inmediatamente
            }
        }
        return null;  // Si termina el ciclo y no lo encuentra, retorna null
    }
    
    // ============= MÉTODO DE ORDENAMIENTO =============
    
    /**
     * Ordena los estudiantes por nota definitiva de forma ASCENDENTE (menor a mayor)
     * Implementa el algoritmo de BURBUJA (sin usar librerías de ordenamiento)
     */
    public void ordenarPorNotaDefinitiva() {
        int n = estudiantes.size();  // Obtiene la cantidad de estudiantes
        
        // Algoritmo de burbuja: compara pares adyacentes y los intercambia si están desordenados
        for (int i = 0; i < n - 1; i++) {           // Controla el número de pasadas
            for (int j = 0; j < n - i - 1; j++) {   // Compara elementos adyacentes
                
                // Obtiene las notas definitivas de los dos estudiantes a comparar
                double nota1 = estudiantes.get(j).calcularDefinitiva();
                double nota2 = estudiantes.get(j + 1).calcularDefinitiva();
                
                // Si están en orden descendente (nota1 mayor que nota2) los intercambia
                if (nota1 > nota2) {
                    // INTERCAMBIO: Guarda temporalmente, reemplaza, y coloca el temporal
                    Estudiante temp = estudiantes.get(j);           // Guarda el primero
                    estudiantes.set(j, estudiantes.get(j + 1));     // Pone el segundo en la posición del primero
                    estudiantes.set(j + 1, temp);                   // Pone el primero (guardado) en la posición del segundo
                }
            }
        }
        // Al finalizar, la lista queda ordenada de menor a mayor nota definitiva
    }
    
    // ============= MÉTODO DE APROBACIÓN =============
    
    /**
     * Determina si una nota implica aprobación o no
     * @param nota La nota a evaluar
     * @return "SI APRUEBA" si nota >= 3.5, "NO APRUEBA" si nota < 3.5
     */
    public String calcularAprobacion(double nota) {
        // Toma una decisión basada en la nota
        if (nota < 3.5) {
            return "NO APRUEBA";  // Menor a 3.5 = No aprueba
        } else {
            return "SI APRUEBA";  // Mayor o igual a 3.5 = Sí aprueba
        }
    }
    
    // ============= MÉTODO ESPECIAL CON NAVEGACIÓN Y DECISIONES =============
    
    /**
     * MÉTODO QUE CUMPLE CON EL REQUISITO DE NAVEGACIÓN Y DECISIONES
     * 
     * 1. NAVEGA: Recorre TODOS los estudiantes de la lista
     * 2. DECIDE: Compara los promedios y muestra un mensaje según cuál sea mayor
     * 
     * @return Arreglo con [promedioDesarrollo, promedioMatematica]
     */
    public double[] navegarYCalcularPromedioAsignaturas() {
        double sumaDesarrollo = 0;      // Acumulador para notas de Desarrollo
        double sumaMatematica = 0;      // Acumulador para notas de Matemática
        int contador = 0;               // Contador de estudiantes
        
        // ===== PARTE 1: NAVEGACIÓN =====
        // Recorre UNO POR UNO todos los estudiantes en la lista
        for (Estudiante e : estudiantes) {
            // Por cada estudiante, suma sus notas a los acumuladores
            sumaDesarrollo += e.getNotaDesarrollo();    // Acumula nota de Desarrollo
            sumaMatematica += e.getNotaMatematica();    // Acumula nota de Matemática
            contador++;                                 // Incrementa el contador
        }
        
        // Prepara el arreglo para guardar los promedios
        double[] promedios = new double[2];
        
        // Calcula los promedios (solo si hay estudiantes)
        if (contador > 0) {
            promedios[0] = sumaDesarrollo / contador; // Promedio de Desarrollo
            promedios[1] = sumaMatematica / contador; // Promedio de Matemática
        } else {
            promedios[0] = 0;  // Si no hay estudiantes, promedio cero
            promedios[1] = 0;
        }
        
        // ===== PARTE 2: DECISIONES =====
        // Compara los promedios y muestra un mensaje según el resultado
        if (promedios[0] < promedios[1]) {
            // Caso 1: Matemática tiene mejor promedio
            System.out.println("** Los estudiantes tienen mejor promedio en Matemática **");
        } else if (promedios[0] > promedios[1]) {
            // Caso 2: Desarrollo tiene mejor promedio
            System.out.println("** Los estudiantes tienen mejor promedio en Desarrollo **");
        } else {
            // Caso 3: Los promedios son iguales
            System.out.println("** Los promedios son iguales **");
        }
        
        return promedios;  // Devuelve los promedios calculados
    }
}