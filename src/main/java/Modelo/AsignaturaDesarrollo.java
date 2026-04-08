/**
 * @author Jose David Molano Perdomo
 *
 * POLIMORFISMO — Subclase CONCRETA de Asignatura
 *
 * "extends Asignatura" significa herencia: esta clase hereda todos los
 * atributos y métodos de Asignatura, y DEBE implementar los abstractos.
 */
package modelo;

public class AsignaturaDesarrollo extends Asignatura {

    // ============= CONSTRUCTOR =============
    /**
     * Llama al constructor del padre con super(...).
     * Fija nombre="Desarrollo" y porcentaje=55 para todas las instancias.
     * @param nota La nota inicial del estudiante en esta asignatura
     */
    public AsignaturaDesarrollo(double nota) {
        super("Desarrollo", 55.0, nota);
    }

    // ============= IMPLEMENTACIÓN DE MÉTODOS ABSTRACTOS =============

    /**
     * POLIMORFISMO: implementación propia de getDescripcion().
     * Cuando el código llame asignatura.getDescripcion() sobre un objeto
     * de este tipo, Java ejecutará ESTE método (no el de Matemática).
     */
    @Override
    public String getDescripcion() {
        return "Programación y desarrollo de software (55%)";
    }

    /**
     * POLIMORFISMO: Desarrollo SÍ permite incremento automático.
     * Retorna true → el método incrementarNota() heredado SÍ aplicará el cambio.
     */
    @Override
    public boolean esIncrementable() {
        return true;
    }
}