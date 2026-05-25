package com.supermercado.model;

import java.util.ArrayList;
import java.util.List;

/**
 * CLASE: EmpleadoRepositorio
 * CAPA: Modelo - Patrón MVC
 *
 * ¿QUÉ ES ESTA CLASE?
 * "base de datos en memoria" de la aplicación.
 * Contiene la lista completa de empleados del supermercado (datos quemados) y los métodos para consultarlos.
 *
 * ¿POR QUÉ EXISTE SEPARADA DE Empleado.java?
 * Porque tiene una responsabilidad diferente:
 * - Empleado.java  → define QUE ES un empleado (estructura de datos)
 * - EmpleadoRepositorio.java → gestiona LA COLECCIÓN de empleados
 * Esta separación sigue el principio de Responsabilidad Única
 */
public class EmpleadoRepositorio {

     // Coleccion ordenada de objetos de tipo Empleado.
    private final List<Empleado> empleados;

    // CONSTRUCTOR
    public EmpleadoRepositorio() {
        empleados = new ArrayList<>();
        cargarDatos();
    }

    // Carga los empleados del supermercado en la lista de memoria.
    private void cargarDatos() {
        // CAJEROS 
        empleados.add(new Empleado(1,  "Ana María Torres",    "Cajero",     "Cajas",         1_500_000));
        empleados.add(new Empleado(2,  "Luis Felipe Gómez",   "Cajero",     "Cajas",         1_500_000));
        empleados.add(new Empleado(3,  "Diana Sofía Ríos",    "Cajero",     "Cajas",         1_500_000));

        // REPONEDORES 
        empleados.add(new Empleado(4,  "Carlos Andrés Vega",  "Reponedor",  "Almacén",       1_300_000));
        empleados.add(new Empleado(5,  "Paola Jimena Ruiz",   "Reponedor",  "Almacén",       1_300_000));
        empleados.add(new Empleado(6,  "Sergio Iván Mora",    "Reponedor",  "Almacén",       1_300_000));

        // SUPERVISORES
        empleados.add(new Empleado(7,  "Marcela Duarte",      "Supervisor", "Operaciones",   2_200_000));
        empleados.add(new Empleado(8,  "Jorge Enrique Peña",  "Supervisor", "Operaciones",   2_200_000));

        // GERENTES ──────────────────────────────────────────
        empleados.add(new Empleado(9,  "Ricardo Herrera",     "Gerente",    "Administración",4_500_000));
        empleados.add(new Empleado(10, "Claudia Patricia Soto","Gerente",   "Administración",4_500_000));

        // SEGURIDAD
        empleados.add(new Empleado(11, "Andrés Felipe Mejía", "Seguridad",  "Seguridad",     1_800_000));
        empleados.add(new Empleado(12, "Natalia Vargas",      "Seguridad",  "Seguridad",     1_800_000));
    }

    // Retorna TODOS los empleados del supermercado.
    public List<Empleado> obtenerTodos() {
        return new ArrayList<>(empleados);
    }

    /**
     * Retorna solo los empleados que coinciden con el cargo indicado.
     */
    public List<Empleado> filtrarPorCargo(String cargo) {
        List<Empleado> resultado = new ArrayList<>();

        for (Empleado empleado : empleados) {
            if (empleado.getCargo().equalsIgnoreCase(cargo)) {
                resultado.add(empleado);
            }
        }

        return resultado;
    }


    // Retorna la lista de cargos disponibles.
    public List<String> obtenerCargos() {
        List<String> cargos = new ArrayList<>();
        cargos.add("Todos");
        cargos.add("Cajero");
        cargos.add("Reponedor");
        cargos.add("Supervisor");
        cargos.add("Gerente");
        cargos.add("Seguridad");
        return cargos;
    }
}