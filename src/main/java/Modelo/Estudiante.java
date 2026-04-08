/**
 * @author Jose David Molano Perdomo
 */

package modelo;  // Pertenece a la capa Modelo (datos)

public class Estudiante {
    
    // ============= ATRIBUTOS =============
    private int codigo;              // Código único del estudiante (mayor a 21000)
    private String nombre;           // Nombre completo del estudiante
    private double notaDesarrollo;   // Nota de la asignatura Desarrollo (0.0 a 5.0)
    private double notaMatematica;   // Nota de la asignatura Matemática (0.0 a 5.0)
    
    // ============= CONSTRUCTOR =============
    /**
     * Constructor - Crea un nuevo estudiante con todos sus datos
     * @param codigo            Código único (debe ser > 21000)
     * @param nombre            Nombre del estudiante
     * @param notaDesarrollo    Nota de Desarrollo (0.0 - 5.0)
     * @param notaMatematica    Nota de Matemática (0.0 - 5.0)
     */
    public Estudiante(int codigo, String nombre, double notaDesarrollo, double notaMatematica) {
        this.codigo = codigo;                 // Guarda el código
        this.nombre = nombre;                 // Guarda el nombre
        this.notaDesarrollo = notaDesarrollo; // Guarda nota de Desarrollo
        this.notaMatematica = notaMatematica; // Guarda nota de Matemática
    }
    
    // ============= MÉTODOS DE CÁLCULO =============
    
    /**
     * Calcula la nota definitiva del estudiante
     * Fórmula: (Desarrollo * 55%) + (Matemática * 45%)
     * @return La nota definitiva (entre 0.0 y 5.0)
     */
    public double calcularDefinitiva() {
        // Aplica los porcentajes: 55% para Desarrollo, 45% para Matemática
        return (notaDesarrollo * 0.55) + (notaMatematica * 0.45);
    }
    
    /**
     * Verifica si el estudiante aprueba o no según su nota definitiva
     * @return "SI APRUEBA" si definitiva >= 3.5, "NO APRUEBA" si es menor
     */
    public String verificarAprobacion() {
        double definitiva = calcularDefinitiva();  // Calcula la nota definitiva
        
        // Toma una decisión basada en la nota
        if (definitiva < 3.5) {
            return "NO APRUEBA";  // Menor a 3.5 = No aprueba
        } else {
            return "SI APRUEBA";  // Mayor o igual a 3.5 = Sí aprueba
        }
    }
    
    // ============= MÉTODOS PARA INCREMENTAR NOTAS =============
    
    /**
     * Incrementa la nota de Desarrollo con control de límite
     * @param incremento Cantidad a aumentar (debe ser entre 0.0 y 0.5)
     * Nota: Si la suma pasa de 5.0, la nota se deja en 5.0 (máximo permitido)
     */
    public void incrementarNotaDesarrollo(double incremento) {
        double nuevaNota = this.notaDesarrollo + incremento;  // Suma el incremento
        
        // Verifica si se pasa del límite máximo (5.0)
        if (nuevaNota > 5.0) {
            this.notaDesarrollo = 5.0;  // Si pasa, deja en 5.0
        } else {
            this.notaDesarrollo = nuevaNota;  // Si no pasa, asigna la nueva nota
        }
    }
    
    // ============= MÉTODOS PARA MODIFICAR NOTAS =============
    
    /**
     * Modifica la nota de Desarrollo con validación de rango
     * @param nuevaNota Nuevo valor para la nota (debe estar entre 0.0 y 5.0)
     * Si la nota no es válida, no se realiza el cambio
     */
    public void modificarNotaDesarrollo(double nuevaNota) {
        // Solo modifica si la nota está en el rango válido
        if (nuevaNota >= 0.0 && nuevaNota <= 5.0) {
            this.notaDesarrollo = nuevaNota;  // Actualiza la nota
        }
        // Si no es válida, simplemente ignora el cambio
    }
    
    /**
     * Modifica la nota de Matemática con validación de rango
     * @param nuevaNota Nuevo valor para la nota (debe estar entre 0.0 y 5.0)
     * Si la nota no es válida, no se realiza el cambio
     */
    public void modificarNotaMatematica(double nuevaNota) {
        // Solo modifica si la nota está en el rango válido
        if (nuevaNota >= 0.0 && nuevaNota <= 5.0) {
            this.notaMatematica = nuevaNota;  // Actualiza la nota
        }
        // Si no es válida, simplemente ignora el cambio
    }
    
    // ============= GETTERS (MÉTODOS DE ACCESO) =============
    
    /**
     * Obtiene el código del estudiante
     * @return El código único del estudiante
     */
    public int getCodigo() {
        return codigo;
    }
    
    /**
     * Obtiene el nombre del estudiante
     * @return El nombre completo
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Obtiene la nota de Desarrollo
     * @return La nota de Desarrollo actual
     */
    public double getNotaDesarrollo() {
        return notaDesarrollo;
    }
    
    /**
     * Obtiene la nota de Matemática
     * @return La nota de Matemática actual
     */
    public double getNotaMatematica() {
        return notaMatematica;
    }
}