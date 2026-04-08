/**
 * @author Jose David Molano Perdomo
 */

package modelo;

import java.util.ArrayList;

public class ModeloEstudiantes {
    
    private ArrayList<Estudiante> estudiantes;
    
    public ModeloEstudiantes() {
        this.estudiantes = new ArrayList<>();
    }
    
    public void agregarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }
    
    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }
    
    public Estudiante getEstudiantePorCodigo(int codigo) {
        for (Estudiante e : estudiantes) {
            if (e.getCodigo() == codigo) {
                return e;
            }
        }
        return null;
    }
    
    public void ordenarPorNotaDefinitiva() {
        int n = estudiantes.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                double nota1 = estudiantes.get(j).calcularDefinitiva();
                double nota2 = estudiantes.get(j + 1).calcularDefinitiva();
                if (nota1 > nota2) {
                    Estudiante temp = estudiantes.get(j);
                    estudiantes.set(j, estudiantes.get(j + 1));
                    estudiantes.set(j + 1, temp);
                }
            }
        }
    }
    
    public String calcularAprobacion(double nota) {
        if (nota < 3.5) {
            return "NO APRUEBA";
        } else {
            return "SI APRUEBA";
        }
    }
    
    public double[] navegarYCalcularPromedioAsignaturas() {
        double sumaDesarrollo = 0;
        double sumaMatematica = 0;
        int contador = 0;
        
        for (Estudiante e : estudiantes) {
            sumaDesarrollo += e.getNotaDesarrollo();
            sumaMatematica += e.getNotaMatematica();
            contador++;
        }
        
        double[] promedios = new double[2];
        if (contador > 0) {
            promedios[0] = sumaDesarrollo / contador;
            promedios[1] = sumaMatematica / contador;
        } else {
            promedios[0] = 0;
            promedios[1] = 0;
        }
        
        if (promedios[0] < promedios[1]) {
            System.out.println("** Los estudiantes tienen mejor promedio en Matemática **");
        } else if (promedios[0] > promedios[1]) {
            System.out.println("** Los estudiantes tienen mejor promedio en Desarrollo **");
        } else {
            System.out.println("** Los promedios son iguales **");
        }
        
        return promedios;
    }
    
    // ============= MÉTODOS POLIMÓRFICOS NUEVOS =============
    
    /**
     * MÉTODO POLIMÓRFICO: Procesa estudiantes con diferentes operaciones
     * @param tipoOperacion 1=Nota Máxima, 2=Nota Mínima, 3=Diferencia
     */
    public void procesarConPolimorfismo(int tipoOperacion) {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        
        String nombreOperacion = "";
        switch(tipoOperacion) {
            case 1: nombreOperacion = "Nota Máxima"; break;
            case 2: nombreOperacion = "Nota Mínima"; break;
            case 3: nombreOperacion = "Diferencia de Notas"; break;
        }
        
        System.out.println("\n=== PROCESAMIENTO POLIMÓRFICO ===");
        System.out.println("Operación: " + nombreOperacion);
        System.out.println("=================================");
        
        double suma = 0;
        double maximo = Double.MIN_VALUE;
        double minimo = Double.MAX_VALUE;
        Estudiante estudianteMax = null;
        Estudiante estudianteMin = null;
        
        for (Estudiante e : estudiantes) {
            // POLIMORFISMO: el mismo método se comporta diferente según el parámetro
            double resultado = e.calcularOperacionPolimorfica(tipoOperacion);
            suma += resultado;
            
            System.out.printf("Código: %d | %-15s | Resultado: %.2f%n", 
                            e.getCodigo(), e.getNombre(), resultado);
            
            if (resultado > maximo) {
                maximo = resultado;
                estudianteMax = e;
            }
            if (resultado < minimo) {
                minimo = resultado;
                estudianteMin = e;
            }
        }
        
        double promedio = suma / estudiantes.size();
        
        System.out.println("=================================");
        System.out.printf("Suma total: %.2f%n", suma);
        System.out.printf("Promedio: %.2f%n", promedio);
        System.out.printf("Máximo: %.2f (%s)%n", maximo, estudianteMax.getNombre());
        System.out.printf("Mínimo: %.2f (%s)%n", minimo, estudianteMin.getNombre());
        System.out.println("=================================");
    }
    
    /**
     * MÉTODO POLIMÓRFICO: Muestra diferentes formas de calcular la definitiva
     */
    public void mostrarDiferentesFormasCalculo() {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        
        System.out.println("\n=== DIFERENTES FORMAS DE CÁLCULO (POLIMORFISMO POR SOBRECARGA) ===");
        System.out.println("=================================================================");
        
        for (Estudiante e : estudiantes) {
            System.out.println("\nEstudiante: " + e.getNombre());
            System.out.println("  Nota Desarrollo: " + e.getNotaDesarrollo());
            System.out.println("  Nota Matemática: " + e.getNotaMatematica());
            
            // POLIMORFISMO: el mismo método nombre con diferentes parámetros
            System.out.println("  Definitiva estándar (55% - 45%): " + String.format("%.2f", e.calcularDefinitiva()));
            System.out.println("  Definitiva con ponderación 70%-30%: " + String.format("%.2f", e.calcularDefinitiva(0.70, 0.30)));
            System.out.println("  Definitiva con ponderación 30%-70%: " + String.format("%.2f", e.calcularDefinitiva(0.30, 0.70)));
            System.out.println("  Definitiva como promedio simple: " + String.format("%.2f", e.calcularDefinitiva(true)));
        }
        System.out.println("=================================================================");
    }
}