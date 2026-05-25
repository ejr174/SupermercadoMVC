package com.supermercado.model;

/**
 * CLASE: Empleado
 * CAPA: Modelo - Patrón MVC
 *
 * Entidad principal del sistema. Representa a un empleado real del supermercado con todos sus datos relevantes.
 *
 * Esta clase es la estructura de datos de un empleado. 
 * No sabe nada de botones, pantallas ni lógica de negocio.
 * Solo guarda y entrega información.
 *
 */
public class Empleado {

    // ATRIBUTOS

    // Identificador unico del empleado.
    private int id;

    //Nombre completo del empleado.
    private String nombre;

    // Cargo que ocupa el empleado en el supermercado.  Ejemplos: "Cajero", "Supervisor", "Gerente"
    private String cargo;

    //Departamento al que pertenece el empleado. Ejemplos: "Cajas", "Almacén", "Administración"
    private String departamento;

    //Salario mensual del empleado expresado en pesos colombianos.
    private double salario;

    // CONSTRUCTOR

    /**
     * CONSTRUCTOR 
     * @param id           Identificador único
     * @param nombre       Nombre completo
     * @param cargo        Cargo en el supermercado
     * @param departamento Departamento al que pertenece
     * @param salario      Salario mensual
     */
    public Empleado(int id, String nombre, String cargo,
                    String departamento, double salario) {
        this.id           = id;
        this.nombre       = nombre;
        this.cargo        = cargo;
        this.departamento = departamento;
        this.salario      = salario;
    }

    // GETTERS

    /**
     * Retorna el identificador único del empleado.
     * @return int con el ID
     */
    public int getId() {
        return id;
    }

    /**
     * Retorna el nombre completo del empleado.
     * @return String con el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Retorna el cargo del empleado.
     * Este getter es importante porque el Controlador
     * lo usa para comparar y filtrar empleados por el cargo.
     * @return String con el cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * Retorna el departamento del empleado.
     * @return String con el departamento
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Retorna el salario del empleado.
     * @return double con el salario mensual
     */
    public double getSalario() {
        return salario;
    }
}