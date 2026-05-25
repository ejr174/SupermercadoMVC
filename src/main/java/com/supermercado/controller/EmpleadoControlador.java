package com.supermercado.controller;

import com.supermercado.model.Empleado;
import com.supermercado.model.EmpleadoRepositorio;
import com.supermercado.view.EmpleadoVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * CLASE: EmpleadoControlador
 * CAPA: Controlador - Patrón MVC
 *
 * ¿QUÉ ES ESTA CLASE?
 * Es el intermediario entre la Vista y el Modelo. Es el único componente del sistema que conoce y habla con los otros dos.
 *
 * FLUJO COMPLETO cuando el usuario hace clic en "Buscar":
 *   Usuario → clic en botón
 *   Vista   → notifica al Controlador (via ActionListener)
 *   Controlador → pregunta a la Vista qué cargo eligió
 *   Controlador → le pide al Repositorio (Modelo) los empleados
 *   Controlador → entrega la lista a la Vista para que la muestre
 *   Vista   → actualiza la tabla
 */
public class EmpleadoControlador {

    /**
     * Referencia al Modelo (repositorio de datos).
     */
    private final EmpleadoRepositorio repositorio;

    /**
     * Referencia a la Vista (interfaz gráfica).
     */
    private final EmpleadoVista vista;

    // CONSTRUCTOR

    /**
     * @param repositorio La instancia del Modelo (datos)
     * @param vista       La instancia de la Vista (GUI)
     */
    public EmpleadoControlador(EmpleadoRepositorio repositorio,
                                EmpleadoVista vista) {
        this.repositorio = repositorio;
        this.vista       = vista;

        // Al crear el Controlador, inmediatamente conecta la Vista con sus acciones (listeners)
        inicializarEventos();

        // Muestra todos los empleados al arrancar la aplicación
        mostrarTodos();
    }

    /**
     * Conecta los eventos de la Vista con la lógica del Controlador.
     */
    private void inicializarEventos() {
        vista.agregarListenerBuscar((ActionEvent e) -> buscarPorCargo());
    }

    /**
     * Ejecuta la búsqueda de empleados según el cargo seleccionado.
     *
     * Este es el método central del Controlador. Coordina:
     * 1. Leer la selección del usuario desde la Vista
     * 2. Decidir qué método del Modelo llamar
     * 3. Entregar el resultado a la Vista
     */
    private void buscarPorCargo() {

        // Paso 1: obtener el cargo que el usuario eligió en el ComboBox
        String cargoSeleccionado = vista.getCargoSeleccionado();

        // Paso 2: consultar el Modelo según la selección
        List<Empleado> resultado;

        if ("Todos".equals(cargoSeleccionado)) {
            // Si eligió "Todos", traemos todos los empleados
            resultado = repositorio.obtenerTodos();
        } else {
            // Si eligió un cargo específico, filtramos
            resultado = repositorio.filtrarPorCargo(cargoSeleccionado);
        }

        // Paso 3: entregar los resultados a la Vista para que los muestre
        vista.mostrarEmpleados(resultado);
    }

    /**
     * Muestra todos los empleados al iniciar la aplicación.
     * Se llama desde el constructor para que la tabla no aparezca vacía cuando el usuario abre el programa.
     */
    private void mostrarTodos() {
        vista.mostrarEmpleados(repositorio.obtenerTodos());
    }
}