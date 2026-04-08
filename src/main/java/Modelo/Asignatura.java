/**
 * @author Jose David Molano Perdomo
 */
package modelo; 

public class Asignatura {
    // ============= ATRIBUTOS =============
    private String nombre;      // Nombre de la asignatura (ej: "Desarrollo", "Matemática")
    private double porcentaje;  // Porcentaje que vale en la nota final (ej: 55.0, 45.0)
    
    // ============= CONSTRUCTOR =============
    /**
     * Constructor - Se ejecuta al crear un nuevo objeto Asignatura
     * @param nombre El nombre de la asignatura
     * @param porcentaje El porcentaje que vale (ej: 55 para 55%)
     */
    public Asignatura(String nombre, double porcentaje) {
        // Asigna los valores recibidos a los atributos de la clase
        this.nombre = nombre;          // Guarda el nombre
        this.porcentaje = porcentaje;  // Guarda el porcentaje
    }
    
    // ============= MÉTODOS =============
    /**
     * Calcula la ponderación de una nota según el porcentaje de la asignatura
     * Ejemplo: Si porcentaje=55 y nota=4.0 → 4.0 * (55/100) = 2.2
     * @param nota      La nota a ponderar (debe estar entre 0.0 y 5.0)
     * @return          El valor ponderado de la nota
     */
    public double calcularPonderacion(double nota) {
        // Fórmula: nota * (porcentaje / 100)
        // Divide porcentaje entre 100 para convertir a decimal (55 → 0.55)
        return nota * (porcentaje / 100.0);
    }
    
    // ============= GETTERS =============
    /**
     * Obtiene el nombre de la asignatura
     * @return El nombre almacenado
     */
    public String getNombre() {
        return nombre;  // Devuelve el nombre
    }
    
    /**
     * Obtiene el porcentaje de la asignatura
     * @return El porcentaje almacenado
     */
    public double getPorcentaje() {
        return porcentaje;  // Devuelve el porcentaje
    }
}