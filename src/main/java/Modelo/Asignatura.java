/**
 * @author Jose David Molano Perdomo
 *
 * POLIMORFISMO — Clase ABSTRACTA base
 *
 * Al declarar esta clase como "abstract", Java prohíbe crear objetos
 * directamente con "new Asignatura(...)". Solo se pueden crear objetos
 * de las subclases: AsignaturaDesarrollo y AsignaturaMatematica.
 *
 * Los métodos abstractos (sin cuerpo) OBLIGAN a cada subclase a dar
 * su propia implementación → eso ES el polimorfismo.
 */
package modelo;

public abstract class Asignatura {

    // ============= ATRIBUTOS =============
    protected String nombre;      // Nombre de la asignatura (lo da cada subclase)
    protected double porcentaje;  // Porcentaje en la nota final
    protected double nota;        // Nota actual del estudiante en esta asignatura

    // ============= CONSTRUCTOR =============
    /**
     * Constructor protegido: solo lo pueden llamar las subclases con super(...)
     */
    public Asignatura(String nombre, double porcentaje, double nota) {
        this.nombre     = nombre;
        this.porcentaje = porcentaje;
        this.nota       = nota;
    }

    // ============= MÉTODOS CONCRETOS (iguales para todas las subclases) =============

    /**
     * Calcula la ponderación de la nota según el porcentaje de la asignatura.
     * Este método es IGUAL para todas las subclases → permanece concreto aquí.
     * Ejemplo: nota=4.0, porcentaje=55 → 4.0 * 0.55 = 2.2
     */
    public double calcularPonderacion() {
        return nota * (porcentaje / 100.0);
    }

    /**
     * Intenta incrementar la nota. Solo lo aplica si esIncrementable() es true.
     * La decisión la toma cada subclase sobreescribiendo esIncrementable().
     *
     * POLIMORFISMO EN ACCIÓN: este método llama a esIncrementable(), que se
     * resuelve en tiempo de ejecución según el objeto real (Desarrollo o Matemática).
     */
    public void incrementarNota(double incremento) {
        if (esIncrementable()) {                 // ← llamada polimórfica
            double nueva = this.nota + incremento;
            this.nota = (nueva > 5.0) ? 5.0 : nueva;
        }
        // Si esIncrementable() devuelve false, no hace nada
    }

    // ============= MÉTODOS ABSTRACTOS (cada subclase DEBE implementarlos) =============

    /**
     * Devuelve una descripción propia de la asignatura.
     * ABSTRACTO → cada subclase decide qué texto retornar.
     */
    public abstract String getDescripcion();

    /**
     * Indica si esta asignatura permite incremento automático de notas.
     * ABSTRACTO → Desarrollo devuelve true, Matemática devuelve false.
     */
    public abstract boolean esIncrementable();

    // ============= GETTERS Y SETTER =============

    public String getNombre()     { return nombre; }
    public double getPorcentaje() { return porcentaje; }
    public double getNota()       { return nota; }

    public void setNota(double nuevaNota) {
        if (nuevaNota >= 0.0 && nuevaNota <= 5.0) {
            this.nota = nuevaNota;
        }
    }
}