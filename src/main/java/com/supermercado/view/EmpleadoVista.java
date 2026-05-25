package com.supermercado.view;

import com.supermercado.model.Empleado;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 * CLASE: EmpleadoVista
 * CAPA: Vista - Patrón MVC
 *
 * ¿QUÉ ES ESTA CLASE?
 * Es la interfaz gráfica de usuario (GUI) de la aplicación.
 *
 * ¿QUÉ NO HACE ESTA CLASE?
 * NO sabe cómo buscar empleados. NO accede a los datos directamente. Su único trabajo es: mostrar información y capturar acciones del usuario.
 */
public class EmpleadoVista extends JFrame {

    // COMPONENTES GRÁFICOS (los "widgets" de la ventana)

    /**
     * Lista desplegable para seleccionar el cargo.
     * El usuario hace clic aquí para elegir qué cargo quiere consultar.
     */
    private JComboBox<String> comboCargo;

    /**
     * Botón que dispara la búsqueda cuando el usuario hace clic.
     */
    private JButton btnBuscar;

    /**
     * Tabla donde se muestran los empleados encontrados.
     */
    private JTable tablaEmpleados;

    /**
     * Modelo de datos de la tabla.
     */
    private DefaultTableModel modeloTabla;

    /**
     * Etiqueta que muestra cuántos resultados se encontraron.
     * Se actualiza dinámicamente cada vez que se hace una búsqueda.
     */
    private JLabel lblResultados;

    // CONSTRUCTOR

    /**
     * @param cargosDisponibles Lista de cargos que se cargarán  en el JComboBox 
     */
    public EmpleadoVista(List<String> cargosDisponibles) {
        configurarVentana();
        inicializarComponentes(cargosDisponibles);
        construirPanelSuperior();
        construirPanelCentral();
        construirPanelInferior();
    }

    /**
     * Configura las propiedades básicas de la ventana (JFrame).
     */
    private void configurarVentana() {
        setTitle("Supermercado — Consulta de Empleados por Cargo");
        setSize(850, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(0, 0));
        getContentPane().setBackground(new Color(245, 245, 245));
    }

    /**
     * Crea e inicializa todos los componentes gráficos.     
     * @param cargosDisponibles Lista de cargos para el ComboBox
     */
    private void inicializarComponentes(List<String> cargosDisponibles) {

        // --- ComboBox de cargos ---
        // Convertimos la lista a un arreglo porque JComboBox
        comboCargo = new JComboBox<>(
            cargosDisponibles.toArray(new String[0])
        );
        comboCargo.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        // --- Botón de búsqueda ---
        btnBuscar = new JButton("Buscar");
        btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnBuscar.setBackground(new Color(30, 100, 200));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFocusPainted(false); 

        // --- Etiqueta de resultados ---
        lblResultados = new JLabel("Seleccione un cargo y presione Buscar");
        lblResultados.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblResultados.setForeground(new Color(100, 100, 100));

        // --- Modelo de la tabla ---
        // Definimos los nombres de las columnas
        String[] columnas = {"ID", "Nombre", "Cargo", "Departamento", "Salario"};
        
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Ninguna celda será editable por el usuario
                return false;
            }
        };

        // --- Tabla ---
        tablaEmpleados = new JTable(modeloTabla);
        tablaEmpleados.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tablaEmpleados.setRowHeight(28); // altura de cada fila en píxeles
        tablaEmpleados.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tablaEmpleados.getTableHeader().setBackground(new Color(30, 100, 200));
        tablaEmpleados.getTableHeader().setForeground(Color.WHITE);
        tablaEmpleados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaEmpleados.setSelectionBackground(new Color(173, 214, 255));
    }

    /**
     * Construye el panel SUPERIOR de la ventana.
     * Contiene: título, ComboBox de cargos y botón de búsqueda.
     */
    private void construirPanelSuperior() {

        // Panel principal superior 
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(new Color(25, 80, 160));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        // Título de la aplicación
        JLabel lblTitulo = new JLabel("?Sistema de Empleados - Supermercado MVC");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);

        // Panel del filtro 
        JPanel panelFiltro = new JPanel();
        panelFiltro.setBackground(new Color(25, 80, 160));

        JLabel lblFiltro = new JLabel("Cargo: ");
        lblFiltro.setForeground(Color.WHITE);
        lblFiltro.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        panelFiltro.add(lblFiltro);
        panelFiltro.add(comboCargo);
        panelFiltro.add(btnBuscar);

        panelSuperior.add(lblTitulo,   BorderLayout.WEST);
        panelSuperior.add(panelFiltro, BorderLayout.EAST);

        add(panelSuperior, BorderLayout.NORTH);
    }

    /**
     * Construye el panel CENTRAL de la ventana.
     * Contiene la tabla de empleados dentro de un JScrollPane.
     */
    private void construirPanelCentral() {
        JScrollPane scrollPane = new JScrollPane(tablaEmpleados);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 5, 20));
        scrollPane.getViewport().setBackground(Color.WHITE);

        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Construye el panel INFERIOR de la ventana.
     * Contiene la etiqueta de resultados (contador).
     */
    private void construirPanelInferior() {
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBackground(new Color(235, 235, 235));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        panelInferior.add(lblResultados, BorderLayout.WEST);

        add(panelInferior, BorderLayout.SOUTH);
    }

    
    // Estos métodos son el puente entre la Vista y el Controlador.
    // El Controlador SOLO interactúa con la Vista a través de ellos.

    /**
     * Retorna el cargo que el usuario seleccionó en el ComboBox.
     * El Controlador llama a este método para saber qué filtro aplicar.     
     *
     * @return String con el cargo seleccionado (ej: "Cajero")
     */
    public String getCargoSeleccionado() {
        return (String) comboCargo.getSelectedItem();
    }

    /**
     * Muestra la lista de empleados en la tabla.
     * El Controlador llama a este método con los resultados del Modelo.
     */
    public void mostrarEmpleados(List<Empleado> empleados) {

        // Paso 1: limpiar filas anteriores
        modeloTabla.setRowCount(0);

        // Paso 2 y 3: agregar una fila por cada empleado
        // NumberFormat formatea el salario con separadores de miles
        // Locale("es","CO") indica formato colombiano: 1.500.000
        NumberFormat fmt = NumberFormat.getNumberInstance(new Locale("es", "CO"));

        for (Empleado emp : empleados) {
            Object[] fila = {
                emp.getId(),
                emp.getNombre(),
                emp.getCargo(),
                emp.getDepartamento(),
                "$ " + fmt.format(emp.getSalario())
            };
            modeloTabla.addRow(fila);
        }

        // Paso 4: actualizar etiqueta de resultados
        if (empleados.isEmpty()) {
            lblResultados.setText("No se encontraron empleados con ese cargo.");
        } else {
            lblResultados.setText("✔ " + empleados.size() +
                " empleado(s) encontrado(s) con cargo: " +
                getCargoSeleccionado());
        }
    }

    /**
     * Registra el ActionListener del botón "Buscar".
     *
     * Es una interfaz de Java que define qué debe ocurrir cuando el usuario hace clic en un botón. La Vista no sabe QUÉ hacer,
     * solo avisa que ocurrió un clic. El Controlador define la acción.
     *
     * @param listener El ActionListener creado por el Controlador
     */
    public void agregarListenerBuscar(ActionListener listener) {
        btnBuscar.addActionListener(listener);
    }
}