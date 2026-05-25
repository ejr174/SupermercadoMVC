package com.supermercado;

import com.supermercado.controller.EmpleadoControlador;
import com.supermercado.model.EmpleadoRepositorio;
import com.supermercado.view.EmpleadoVista;
import javax.swing.SwingUtilities;

/**
 * CLASE: SupermercadoMVC (Main)
 * CAPA: Punto de entrada de la aplicación
 *
 *
 * ¿QUÉ ES SwingUtilities.invokeLater()?
 * Las interfaces gráficas en Java Swing tienen su propio hilo de ejecución llamado EDT (Event Dispatch Thread). 
 * Todas las operaciones sobre componentes gráficos DEBEN ejecutarse en ese hilo para evitar errores visuales o bloqueo
 */
public class SupermercadoMVC {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            // PASO 1 - Crear el Modelo
            // El repositorio carga los empleados en memoria
            EmpleadoRepositorio repositorio = new EmpleadoRepositorio();

            // PASO 2 - Crear la Vista
            // Le pasamos los cargos disponibles para poblar el ComboBox
            EmpleadoVista vista = new EmpleadoVista(
                repositorio.obtenerCargos()
            );

            // PASO 3 - Crear el Controlador
            // El Controlador recibe ambos y los conecta internamente
            EmpleadoControlador controlador =
                new EmpleadoControlador(repositorio, vista);

            // PASO 4 - Hacer visible la ventana
            vista.setVisible(true);
        });
    }
}