/**
 * @author Jose David Molano Perdomo
 *
 * POLIMORFISMO — Subclase CONCRETA de Asignatura
 *
 * Misma herencia que AsignaturaDesarrollo, pero con comportamiento diferente
 * en los métodos abstractos → ese comportamiento diferente es el polimorfismo.
 */
package modelo;

public class AsignaturaMatematica extends Asignatura {

    // ============= CONSTRUCTOR =============
    /**
     * Fija nombre="Matemática" y porcentaje=45.
     */
    public AsignaturaMatematica(double nota) {
        super("Matemática", 45.0, nota);
    }

    // ============= IMPLEMENTACIÓN DE MÉTODOS ABSTRACTOS =============

    /**
     * POLIMORFISMO: descripción diferente a AsignaturaDesarrollo.
     */
    @Override
    public String getDescripcion() {
        return "Cálculo y álgebra lineal (45%)";
    }

    /**
     * POLIMORFISMO: Matemática NO permite incremento automático.
     * Retorna false → el método incrementarNota() heredado NO hará nada.
     */
    @Override
    public boolean esIncrementable() {
        return false;
    }
}