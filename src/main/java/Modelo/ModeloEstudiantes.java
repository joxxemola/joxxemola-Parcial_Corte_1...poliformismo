/**
 * @author Jose David Molano Perdomo
 *
 * POLIMORFISMO — El modelo ahora recorre Asignatura[] polimórficamente
 * para calcular promedios por asignatura sin hardcodear los nombres.
 */
package modelo;

import java.util.ArrayList;

public class ModeloEstudiantes {

    private ArrayList<Estudiante> estudiantes;

    public ModeloEstudiantes() {
        this.estudiantes = new ArrayList<>();
    }

    // ============= MÉTODOS BÁSICOS =============

    public void agregarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public Estudiante getEstudiantePorCodigo(int codigo) {
        for (Estudiante e : estudiantes) {
            if (e.getCodigo() == codigo) return e;
        }
        return null;
    }

    // ============= ORDENAMIENTO (burbuja, sin cambios) =============

    public void ordenarPorNotaDefinitiva() {
        int n = estudiantes.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (estudiantes.get(j).calcularDefinitiva() >
                    estudiantes.get(j + 1).calcularDefinitiva()) {
                    Estudiante temp = estudiantes.get(j);
                    estudiantes.set(j, estudiantes.get(j + 1));
                    estudiantes.set(j + 1, temp);
                }
            }
        }
    }

    // ============= APROBACIÓN =============

    public String calcularAprobacion(double nota) {
        return (nota < 3.5) ? "NO APRUEBA" : "SI APRUEBA";
    }

    // ============= MÉTODO CON NAVEGACIÓN Y POLIMORFISMO =============

    /**
     * POLIMORFISMO EN EL MODELO:
     *
     * En lugar de sumar notaDesarrollo y notaMatematica por separado,
     * ahora recorre el array Asignatura[] de CADA estudiante.
     * El resultado es un arreglo dinámico: si el estudiante tuviera
     * 3 asignaturas, este método funcionaría sin cambiar una línea.
     *
     * @return double[] con el promedio de cada asignatura (mismo orden que el array)
     */
    public double[] navegarYCalcularPromedioAsignaturas() {
        if (estudiantes.isEmpty()) return new double[]{0, 0};

        // Asumimos que todos los estudiantes tienen las mismas asignaturas
        // (garantizado por el constructor de Estudiante)
        int cantAsignaturas = estudiantes.get(0).getAsignaturas().length;
        double[] sumas    = new double[cantAsignaturas];
        int      contador = estudiantes.size();

        // NAVEGACIÓN DOBLE — polimórfica en el nivel de asignatura
        for (Estudiante e : estudiantes) {                    // navega estudiantes
            Asignatura[] asigs = e.getAsignaturas();
            for (int i = 0; i < asigs.length; i++) {         // navega asignaturas
                sumas[i] += asigs[i].getNota();              // ← tipo padre, objeto concreto
            }
        }

        double[] promedios = new double[cantAsignaturas];
        for (int i = 0; i < cantAsignaturas; i++) {
            promedios[i] = sumas[i] / contador;
        }

        // DECISIÓN usando getNombre() polimórfico para el mensaje
        Asignatura[] refs = estudiantes.get(0).getAsignaturas();
        if (promedios[0] > promedios[1]) {
            System.out.println("** Mejor promedio en: " + refs[0].getNombre() + " **");
        } else if (promedios[1] > promedios[0]) {
            System.out.println("** Mejor promedio en: " + refs[1].getNombre() + " **");
        } else {
            System.out.println("** Los promedios son iguales **");
        }

        return promedios;
    }
}