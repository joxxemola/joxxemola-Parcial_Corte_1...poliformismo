package controlador;

import modelo.ModeloEstudiantes;
import vista.VistaEstudiantesGUI;

public class ControladorEstudiantesGUI {
    
    private ModeloEstudiantes modelo;
    private VistaEstudiantesGUI vista;
    
    public ControladorEstudiantesGUI() {
        this.modelo = new ModeloEstudiantes();
        this.vista = new VistaEstudiantesGUI(modelo);
    }
    
    public void ejecutar() {
        // La GUI ya está visible, no necesita más acciones
    }
}