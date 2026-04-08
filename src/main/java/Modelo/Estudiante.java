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
    public Estudiante(int codigo, String nombre, double notaDesarrollo, double notaMatematica) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.notaDesarrollo = notaDesarrollo;
        this.notaMatematica = notaMatematica;
    }
    
    // ============= MÉTODOS DE CÁLCULO =============
    
    public double calcularDefinitiva() {
        return (notaDesarrollo * 0.55) + (notaMatematica * 0.45);
    }
    
    public String verificarAprobacion() {
        double definitiva = calcularDefinitiva();
        if (definitiva < 3.5) {
            return "NO APRUEBA";
        } else {
            return "SI APRUEBA";
        }
    }
    
    // ============= MÉTODOS POLIMÓRFICOS =============
    
    /**
     * MÉTODO POLIMÓRFICO 1: Calcula diferentes operaciones según el tipo
     * @param tipoOperacion 1=Nota Máxima, 2=Nota Mínima, 3=Diferencia
     * @return resultado de la operación
     */
    public double calcularOperacionPolimorfica(int tipoOperacion) {
        switch(tipoOperacion) {
            case 1: // Nota máxima
                return Math.max(notaDesarrollo, notaMatematica);
            case 2: // Nota mínima
                return Math.min(notaDesarrollo, notaMatematica);
            case 3: // Diferencia
                return Math.abs(notaDesarrollo - notaMatematica);
            default:
                return 0;
        }
    }
    
    /**
     * MÉTODO POLIMÓRFICO 2: Sobrecarga - mismo nombre, diferentes parámetros
     * Calcula la nota con diferente ponderación
     */
    public double calcularDefinitiva(double ponderacionDesarrollo, double ponderacionMatematica) {
        return (notaDesarrollo * ponderacionDesarrollo) + (notaMatematica * ponderacionMatematica);
    }
    
    /**
     * MÉTODO POLIMÓRFICO 3: Sobrecarga - calcula definitiva con criterio diferente
     */
    public double calcularDefinitiva(boolean usarPromedioSimple) {
        if (usarPromedioSimple) {
            return (notaDesarrollo + notaMatematica) / 2;
        } else {
            return calcularDefinitiva(); // Usa la ponderación original
        }
    }
    
    // ============= MÉTODOS PARA INCREMENTAR NOTAS =============
    
    public void incrementarNotaDesarrollo(double incremento) {
        double nuevaNota = this.notaDesarrollo + incremento;
        if (nuevaNota > 5.0) {
            this.notaDesarrollo = 5.0;
        } else {
            this.notaDesarrollo = nuevaNota;
        }
    }
    
    // ============= MÉTODOS PARA MODIFICAR NOTAS =============
    
    public void modificarNotaDesarrollo(double nuevaNota) {
        if (nuevaNota >= 0.0 && nuevaNota <= 5.0) {
            this.notaDesarrollo = nuevaNota;
        }
    }
    
    public void modificarNotaMatematica(double nuevaNota) {
        if (nuevaNota >= 0.0 && nuevaNota <= 5.0) {
            this.notaMatematica = nuevaNota;
        }
    }
    
    // ============= GETTERS =============
    
    public int getCodigo() {
        return codigo;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public double getNotaDesarrollo() {
        return notaDesarrollo;
    }
    
    public double getNotaMatematica() {
        return notaMatematica;
    }
}