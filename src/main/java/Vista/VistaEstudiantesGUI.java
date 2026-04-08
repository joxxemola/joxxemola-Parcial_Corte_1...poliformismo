package vista;

import modelo.Estudiante;
import modelo.ModeloEstudiantes;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VistaEstudiantesGUI extends JFrame {
    
    private ModeloEstudiantes modelo;
    private JTable tablaEstudiantes;
    private DefaultTableModel modeloTabla;
    private JTextField txtCodigo, txtNombre, txtNotaDesarrollo, txtNotaMatematica, txtNotaLimite, txtIncremento;
    private JComboBox<String> cbOperacionPolimorfica;
    private JLabel lblEstado;
    
    public VistaEstudiantesGUI(ModeloEstudiantes modelo) {
        this.modelo = modelo;
        initComponents();
        setTitle("Sistema de Gestión de Estudiantes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        
        // Panel superior con título
        JPanel panelTitulo = new JPanel();
        JLabel titulo = new JLabel("SISTEMA DE GESTIÓN DE ESTUDIANTES", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(new Color(0, 102, 204));
        panelTitulo.add(titulo);
        add(panelTitulo, BorderLayout.NORTH);
        
        // Panel central con pestañas
        JTabbedPane pestañas = new JTabbedPane();
        pestañas.addTab("Registrar Estudiante", panelRegistrar());
        pestañas.addTab("Lista de Estudiantes", panelLista());
        pestañas.addTab("Filtrar por Nota", panelFiltrar());
        pestañas.addTab("Incrementar Notas", panelIncrementar());
        pestañas.addTab("Modificar Nota", panelModificar());
        pestañas.addTab("Promedios", panelPromedios());
        pestañas.addTab("Polimorfismo", panelPolimorfismo());
        add(pestañas, BorderLayout.CENTER);
        
        // Panel inferior con estado
        JPanel panelEstado = new JPanel();
        lblEstado = new JLabel("Listo");
        lblEstado.setForeground(Color.GRAY);
        panelEstado.add(lblEstado);
        add(panelEstado, BorderLayout.SOUTH);
    }
    
    private JPanel panelRegistrar() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Código
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Código (mayor a 21000):"), gbc);
        gbc.gridx = 1;
        txtCodigo = new JTextField(15);
        panel.add(txtCodigo, gbc);
        
        // Nombre
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(15);
        panel.add(txtNombre, gbc);
        
        // Nota Desarrollo
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Nota Desarrollo (0-5):"), gbc);
        gbc.gridx = 1;
        txtNotaDesarrollo = new JTextField(15);
        panel.add(txtNotaDesarrollo, gbc);
        
        // Nota Matemática
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Nota Matemática (0-5):"), gbc);
        gbc.gridx = 1;
        txtNotaMatematica = new JTextField(15);
        panel.add(txtNotaMatematica, gbc);
        
        // Botón Registrar
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        JButton btnRegistrar = new JButton("Registrar Estudiante");
        btnRegistrar.setBackground(new Color(0, 153, 76));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarEstudiante();
            }
        });
        panel.add(btnRegistrar, gbc);
        
        // Botón Registrar Múltiples
        gbc.gridy = 5;
        JButton btnRegistrarMultiples = new JButton("Registrar Múltiples Estudiantes");
        btnRegistrarMultiples.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarMultiplesEstudiantes();
            }
        });
        panel.add(btnRegistrarMultiples, gbc);
        
        return panel;
    }
    
    private JPanel panelLista() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        
        // Botones de control
        JPanel panelBotones = new JPanel();
        JButton btnRefrescar = new JButton("Refrescar Lista");
        btnRefrescar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarTabla();
            }
        });
        panelBotones.add(btnRefrescar);
        
        JButton btnOrdenar = new JButton("Ordenar por Definitiva");
        btnOrdenar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ordenarYMostrar();
            }
        });
        panelBotones.add(btnOrdenar);
        
        panel.add(panelBotones, BorderLayout.NORTH);
        
        // Tabla de estudiantes
        String[] columnas = {"Código", "Nombre", "Desarrollo", "Matemática", "Definitiva", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaEstudiantes = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaEstudiantes);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel panelFiltrar() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Nota Límite (0-5):"), gbc);
        gbc.gridx = 1;
        txtNotaLimite = new JTextField(10);
        panel.add(txtNotaLimite, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 2;
        JButton btnFiltrar = new JButton("Filtrar Estudiantes");
        btnFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrarPorNota();
            }
        });
        panel.add(btnFiltrar, gbc);
        
        // Área para mostrar resultados
        gbc.gridy = 2;
        JTextArea txtResultados = new JTextArea(15, 40);
        txtResultados.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtResultados);
        panel.add(scrollPane, gbc);
        
        // Guardar referencia para usar en el evento
        btnFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrarYMostrar(txtResultados);
            }
        });
        
        return panel;
    }
    
    private JPanel panelIncrementar() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Incremento (0-0.5):"), gbc);
        gbc.gridx = 1;
        txtIncremento = new JTextField(10);
        panel.add(txtIncremento, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 2;
        JButton btnIncrementar = new JButton("Incrementar Notas de Desarrollo");
        btnIncrementar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                incrementarNotas();
            }
        });
        panel.add(btnIncrementar, gbc);
        
        return panel;
    }
    
    private JPanel panelModificar() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Código del Estudiante:"), gbc);
        gbc.gridx = 1;
        JTextField txtCodigoModificar = new JTextField(10);
        panel.add(txtCodigoModificar, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Nueva Nota (0-5):"), gbc);
        gbc.gridx = 1;
        JTextField txtNuevaNota = new JTextField(10);
        panel.add(txtNuevaNota, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Seleccionar Asignatura:"), gbc);
        gbc.gridx = 1;
        String[] asignaturas = {"Desarrollo", "Matemática"};
        JComboBox<String> cbAsignatura = new JComboBox<>(asignaturas);
        panel.add(cbAsignatura, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        JButton btnModificar = new JButton("Modificar Nota");
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarNota(txtCodigoModificar.getText(), txtNuevaNota.getText(), 
                             (String) cbAsignatura.getSelectedItem());
            }
        });
        panel.add(btnModificar, gbc);
        
        return panel;
    }
    
    private JPanel panelPromedios() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        
        JButton btnCalcular = new JButton("Calcular Promedios por Asignatura");
        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularPromedios();
            }
        });
        panel.add(btnCalcular, BorderLayout.NORTH);
        
        JTextArea txtPromedios = new JTextArea(10, 40);
        txtPromedios.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtPromedios);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Guardar referencia
        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularYMostrarPromedios(txtPromedios);
            }
        });
        
        return panel;
    }
    
    private JPanel panelPolimorfismo() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Seleccionar Operación Polimórfica:"), gbc);
        gbc.gridx = 1;
        String[] operaciones = {"Nota Máxima", "Nota Mínima", "Diferencia de Notas"};
        cbOperacionPolimorfica = new JComboBox<>(operaciones);
        panel.add(cbOperacionPolimorfica, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 2;
        JButton btnProcesar = new JButton("Procesar con Polimorfismo");
        btnProcesar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarPolimorfismo();
            }
        });
        panel.add(btnProcesar, gbc);
        
        // Área de resultados
        gbc.gridy = 2;
        JTextArea txtResultados = new JTextArea(15, 50);
        txtResultados.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtResultados);
        panel.add(scrollPane, gbc);
        
        // Guardar referencia
        btnProcesar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarYMostrarPolimorfismo(txtResultados);
            }
        });
        
        return panel;
    }
    
    // ============= MÉTODOS FUNCIONALES =============
    
    private void registrarEstudiante() {
        try {
            int codigo = Integer.parseInt(txtCodigo.getText());
            if (codigo <= 21000) {
                mostrarMensaje("Error: El código debe ser mayor a 21000", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String nombre = txtNombre.getText();
            if (nombre.trim().isEmpty()) {
                mostrarMensaje("Error: El nombre no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            double notaDesarrollo = Double.parseDouble(txtNotaDesarrollo.getText());
            if (notaDesarrollo < 0 || notaDesarrollo > 5) {
                mostrarMensaje("Error: Nota de Desarrollo debe estar entre 0 y 5", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            double notaMatematica = Double.parseDouble(txtNotaMatematica.getText());
            if (notaMatematica < 0 || notaMatematica > 5) {
                mostrarMensaje("Error: Nota de Matemática debe estar entre 0 y 5", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Estudiante e = new Estudiante(codigo, nombre, notaDesarrollo, notaMatematica);
            modelo.agregarEstudiante(e);
            
            mostrarMensaje("Estudiante registrado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCamposRegistro();
            actualizarTabla();
            
        } catch (NumberFormatException ex) {
            mostrarMensaje("Error: Ingrese valores numéricos válidos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void registrarMultiplesEstudiantes() {
        String input = JOptionPane.showInputDialog(this, "Ingrese el número de estudiantes a registrar:");
        if (input != null) {
            try {
                int n = Integer.parseInt(input);
                for (int i = 0; i < n; i++) {
                    registrarEstudianteDialog(i + 1);
                }
                mostrarMensaje(n + " estudiantes registrados exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                actualizarTabla();
            } catch (NumberFormatException ex) {
                mostrarMensaje("Error: Ingrese un número válido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void registrarEstudianteDialog(int numero) {
        JTextField txtCodigo = new JTextField();
        JTextField txtNombre = new JTextField();
        JTextField txtNotaDesarrollo = new JTextField();
        JTextField txtNotaMatematica = new JTextField();
        
        Object[] mensaje = {
            "Código (mayor a 21000):", txtCodigo,
            "Nombre:", txtNombre,
            "Nota Desarrollo (0-5):", txtNotaDesarrollo,
            "Nota Matemática (0-5):", txtNotaMatematica
        };
        
        int option = JOptionPane.showConfirmDialog(this, mensaje, "Registrar Estudiante " + numero, 
                                                   JOptionPane.OK_CANCEL_OPTION);
        
        if (option == JOptionPane.OK_OPTION) {
            try {
                int codigo = Integer.parseInt(txtCodigo.getText());
                String nombre = txtNombre.getText();
                double notaDesarrollo = Double.parseDouble(txtNotaDesarrollo.getText());
                double notaMatematica = Double.parseDouble(txtNotaMatematica.getText());
                
                if (codigo > 21000 && notaDesarrollo >= 0 && notaDesarrollo <= 5 && 
                    notaMatematica >= 0 && notaMatematica <= 5 && !nombre.trim().isEmpty()) {
                    Estudiante e = new Estudiante(codigo, nombre, notaDesarrollo, notaMatematica);
                    modelo.agregarEstudiante(e);
                } else {
                    mostrarMensaje("Datos inválidos para el estudiante " + numero, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                mostrarMensaje("Error en formato de números", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        for (Estudiante e : modelo.getEstudiantes()) {
            Object[] fila = {
                e.getCodigo(),
                e.getNombre(),
                String.format("%.2f", e.getNotaDesarrollo()),
                String.format("%.2f", e.getNotaMatematica()),
                String.format("%.2f", e.calcularDefinitiva()),
                e.verificarAprobacion()
            };
            modeloTabla.addRow(fila);
        }
        lblEstado.setText("Lista actualizada - Total: " + modelo.getEstudiantes().size() + " estudiantes");
    }
    
    private void ordenarYMostrar() {
        if (modelo.getEstudiantes().isEmpty()) {
            mostrarMensaje("No hay estudiantes registrados", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        modelo.ordenarPorNotaDefinitiva();
        actualizarTabla();
        mostrarMensaje("Estudiantes ordenados por nota definitiva (ascendente)", "Información", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void filtrarYMostrar(JTextArea txtResultados) {
        if (modelo.getEstudiantes().isEmpty()) {
            txtResultados.setText("No hay estudiantes registrados.");
            return;
        }
        
        try {
            double notaLimite = Double.parseDouble(txtNotaLimite.getText());
            if (notaLimite < 0 || notaLimite > 5) {
                txtResultados.setText("Error: La nota límite debe estar entre 0 y 5");
                return;
            }
            
            StringBuilder resultados = new StringBuilder();
            resultados.append("Estudiantes con definitiva > ").append(notaLimite).append(":\n");
            resultados.append("=========================================\n");
            
            boolean encontrado = false;
            for (Estudiante e : modelo.getEstudiantes()) {
                if (e.calcularDefinitiva() > notaLimite) {
                    resultados.append(String.format("Código: %d | Nombre: %s | Definitiva: %.2f\n",
                        e.getCodigo(), e.getNombre(), e.calcularDefinitiva()));
                    encontrado = true;
                }
            }
            
            if (!encontrado) {
                resultados.append("No hay estudiantes con definitiva superior a ").append(notaLimite);
            }
            
            txtResultados.setText(resultados.toString());
            lblEstado.setText("Filtro aplicado - Nota límite: " + notaLimite);
            
        } catch (NumberFormatException ex) {
            txtResultados.setText("Error: Ingrese un valor numérico válido para la nota límite");
        }
    }
    
    private void incrementarNotas() {
        if (modelo.getEstudiantes().isEmpty()) {
            mostrarMensaje("No hay estudiantes registrados", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        try {
            double incremento = Double.parseDouble(txtIncremento.getText());
            if (incremento < 0 || incremento > 0.5) {
                mostrarMensaje("Error: El incremento debe estar entre 0 y 0.5", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            for (Estudiante e : modelo.getEstudiantes()) {
                e.incrementarNotaDesarrollo(incremento);
            }
            
            mostrarMensaje("Notas de Desarrollo incrementadas en " + incremento, "Éxito", JOptionPane.INFORMATION_MESSAGE);
            actualizarTabla();
            txtIncremento.setText("");
            
        } catch (NumberFormatException ex) {
            mostrarMensaje("Error: Ingrese un valor numérico válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void modificarNota(String codigoStr, String nuevaNotaStr, String asignatura) {
        try {
            int codigo = Integer.parseInt(codigoStr);
            double nuevaNota = Double.parseDouble(nuevaNotaStr);
            
            if (nuevaNota < 0 || nuevaNota > 5) {
                mostrarMensaje("Error: La nota debe estar entre 0 y 5", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Estudiante e = modelo.getEstudiantePorCodigo(codigo);
            if (e == null) {
                mostrarMensaje("No se encontró estudiante con código " + codigo, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (asignatura.equals("Desarrollo")) {
                e.modificarNotaDesarrollo(nuevaNota);
                mostrarMensaje("Nota de Desarrollo modificada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                e.modificarNotaMatematica(nuevaNota);
                mostrarMensaje("Nota de Matemática modificada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
            
            actualizarTabla();
            
        } catch (NumberFormatException ex) {
            mostrarMensaje("Error: Ingrese valores numéricos válidos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void calcularYMostrarPromedios(JTextArea txtPromedios) {
        if (modelo.getEstudiantes().isEmpty()) {
            txtPromedios.setText("No hay estudiantes registrados.");
            return;
        }
        
        double[] promedios = modelo.navegarYCalcularPromedioAsignaturas();
        
        StringBuilder sb = new StringBuilder();
        sb.append("=== PROMEDIOS POR ASIGNATURA ===\n\n");
        sb.append(String.format("Desarrollo: %.2f\n", promedios[0]));
        sb.append(String.format("Matemática: %.2f\n", promedios[1]));
        sb.append("\n");
        
        if (promedios[0] > promedios[1]) {
            sb.append("** Los estudiantes tienen mejor promedio en Desarrollo **");
        } else if (promedios[1] > promedios[0]) {
            sb.append("** Los estudiantes tienen mejor promedio en Matemática **");
        } else {
            sb.append("** Los promedios son iguales **");
        }
        
        txtPromedios.setText(sb.toString());
        lblEstado.setText("Promedios calculados");
    }
    
    private void procesarYMostrarPolimorfismo(JTextArea txtResultados) {
        if (modelo.getEstudiantes().isEmpty()) {
            txtResultados.setText("No hay estudiantes registrados.");
            return;
        }
        
        int tipoOperacion = cbOperacionPolimorfica.getSelectedIndex() + 1;
        String nombreOperacion = (String) cbOperacionPolimorfica.getSelectedItem();
        
        StringBuilder sb = new StringBuilder();
        sb.append("=== PROCESAMIENTO POLIMÓRFICO ===\n");
        sb.append("Operación: ").append(nombreOperacion).append("\n");
        sb.append("=================================\n\n");
        
        double suma = 0;
        double maximo = Double.MIN_VALUE;
        double minimo = Double.MAX_VALUE;
        String nombreMax = "", nombreMin = "";
        
        for (Estudiante e : modelo.getEstudiantes()) {
            double resultado = e.calcularOperacionPolimorfica(tipoOperacion);
            suma += resultado;
            
            sb.append(String.format("Estudiante: %-15s | Resultado: %.2f\n", e.getNombre(), resultado));
            
            if (resultado > maximo) {
                maximo = resultado;
                nombreMax = e.getNombre();
            }
            if (resultado < minimo) {
                minimo = resultado;
                nombreMin = e.getNombre();
            }
        }
        
        double promedio = suma / modelo.getEstudiantes().size();
        
        sb.append("\n=================================\n");
        sb.append(String.format("Suma total: %.2f\n", suma));
        sb.append(String.format("Promedio: %.2f\n", promedio));
        sb.append(String.format("Máximo: %.2f (%s)\n", maximo, nombreMax));
        sb.append(String.format("Mínimo: %.2f (%s)\n", minimo, nombreMin));
        sb.append("=================================");
        
        txtResultados.setText(sb.toString());
        lblEstado.setText("Procesamiento polimórfico completado - Operación: " + nombreOperacion);
    }
    
    private void filtrarPorNota() {
        // Método vacío para mantener compatibilidad
    }
    
    private void calcularPromedios() {
        // Método vacío para mantener compatibilidad
    }
    
    private void procesarPolimorfismo() {
        // Método vacío para mantener compatibilidad
    }
    
    private void limpiarCamposRegistro() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtNotaDesarrollo.setText("");
        txtNotaMatematica.setText("");
    }
    
    private void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }
}