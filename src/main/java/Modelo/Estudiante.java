/**
 * @author Jose David Molano Perdomo
 *
 * POLIMORFISMO — Clase que USA el array Asignatura[]
 *
 * El punto clave: los atributos notaDesarrollo y notaMatematica
 * desaparecen. En su lugar hay un array de tipo PADRE: Asignatura[].
 *
 * Cuando el código recorre ese array y llama a un método (calcularPonderacion,
 * getDescripcion, esIncrementable...), Java decide EN TIEMPO DE EJECUCIÓN
 * cuál implementación usar según el objeto real guardado en cada posición.
 * Eso es el "dispatch dinámico" o polimorfismo de subtipo.
 */
package modelo;

public class Estudiante {

    // ============= ATRIBUTOS =============
    private int codigo;
    private String nombre;

    /**
     * ARRAY POLIMÓRFICO: el tipo declarado es Asignatura (padre),
     * pero los objetos reales son AsignaturaDesarrollo y AsignaturaMatematica.
     * índice 0 → Desarrollo, índice 1 → Matemática
     */
    private Asignatura[] asignaturas;

    // ============= CONSTRUCTOR =============
    public Estudiante(int codigo, String nombre, double notaDesarrollo, double notaMatematica) {
        this.codigo = codigo;
        this.nombre = nombre;

        // Crea los objetos concretos y los guarda como tipo padre
        this.asignaturas = new Asignatura[] {
            new AsignaturaDesarrollo(notaDesarrollo),   // posición 0
            new AsignaturaMatematica(notaMatematica)    // posición 1
        };
    }

    // ============= MÉTODOS DE CÁLCULO (ahora POLIMÓRFICOS) =============

    /**
     * POLIMORFISMO EN ACCIÓN:
     * El bucle no sabe si cada elemento es Desarrollo o Matemática.
     * Simplemente llama a calcularPonderacion() y Java hace lo correcto.
     * Si mañana agrego una tercera asignatura, este método NO CAMBIA.
     */
    public double calcularDefinitiva() {
        double total = 0;
        for (Asignatura a : asignaturas) {
            total += a.calcularPonderacion();  // ← llamada polimórfica
        }
        return total;
    }

    /**
     * POLIMORFISMO EN ACCIÓN:
     * Recorre el array e intenta incrementar cada asignatura.
     * Solo AsignaturaDesarrollo responderá (esIncrementable() = true).
     * AsignaturaMatematica ignorará el incremento (esIncrementable() = false).
     * El controlador NO necesita saber cuál es cuál.
     */
    public void incrementarNotasIncrementables(double incremento) {
        for (Asignatura a : asignaturas) {
            a.incrementarNota(incremento);  // ← polimórfico: cada subclase decide
        }
    }

    /**
     * Devuelve aprobación según la nota definitiva.
     */
    public String verificarAprobacion() {
        return (calcularDefinitiva() >= 3.5) ? "SI APRUEBA" : "NO APRUEBA";
    }

    // ============= GETTERS DE NOTAS (delegan en el array) =============

    public double getNotaDesarrollo() { return asignaturas[0].getNota(); }
    public double getNotaMatematica() { return asignaturas[1].getNota(); }

    /**
     * Expone el array completo para que el controlador/vista puedan
     * recorrerlo sin conocer los tipos concretos.
     * Útil para mostrar dinámica mente las asignaturas disponibles.
     */
    public Asignatura[] getAsignaturas() { return asignaturas; }

    // ============= MÉTODOS PARA MODIFICAR NOTAS =============

    public void modificarNotaDesarrollo(double nuevaNota) { asignaturas[0].setNota(nuevaNota); }
    public void modificarNotaMatematica(double nuevaNota) { asignaturas[1].setNota(nuevaNota); }

    // ============= GETTERS BÁSICOS =============

    public int    getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
}