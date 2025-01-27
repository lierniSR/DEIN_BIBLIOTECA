package es.liernisarraoa.biblioteca.Modelo;

/**
 * Esta clase representa el objeto de un/a Alumno/a
 *
 * @version 1.0
 * @author Lierni Sarraoa Joaquin
 */
public class Alumno {
    /**
     * Atributos de la clase
     */
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;

    /**
     * Constructor de la clase con todos los atributos
     *
     * @param dni
     * @param nombre
     * @param apellido1
     * @param apellido2
     */
    public Alumno(String dni, String nombre, String apellido1, String apellido2){
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
    }

    /**
     * Para obtener el valor del atributo DNI
     *
     * @return dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * Para cambiar el valor del atributo DNI
     *
     * @param dni
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Para obtener el valor del atributo Nombre
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Para cambiar el valor del atributo nombre.
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Para obtener el valor del atributo apellido1
     * @return Primer apellido
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * Para cambiar el valor del atributo apellido1
     *
     * @param apellido1
     */
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    /**
     * Para obtener el valor del atributo apellido2
     *
     * @return Segundo apellido
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * Para cambiar el valor del atributo apellido2
     *
     * @param apellido2
     */
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }
}
