package principal;

import controlador.ControladorEstudiantesGUI;

public class MainGUI {
    public static void main(String[] args) {
        // Usar la interfaz gráfica
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ControladorEstudiantesGUI controlador = new ControladorEstudiantesGUI();
                controlador.ejecutar();
            }
        });
    }
}