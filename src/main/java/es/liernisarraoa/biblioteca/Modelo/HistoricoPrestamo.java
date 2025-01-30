package es.liernisarraoa.biblioteca.Modelo;

import java.util.Date;

/**
 * Clase para guardar el historico de los préstamos
 *
 * @author Lierni Sarraoa Joaquin
 * @version 1.0
 */
public class HistoricoPrestamo {
    /**
     * Atributos de la clase
     */
    private Integer id_prestamo;
    private String dni_alumno;
    private Integer codigo_libro;
    private java.sql.Date fecha_prestamo;
    private java.sql.Date fecha_devolucion;

    /**
     * Constructor básico con todos los atributos
     *
     * @param id_prestamo
     * @param dni_alumno
     * @param codigo_libro
     * @param fecha_prestamo
     * @param fecha_devolucion
     */
    public HistoricoPrestamo(Integer id_prestamo, String dni_alumno, Integer codigo_libro, java.sql.Date fecha_prestamo, java.sql.Date fecha_devolucion){
        this.id_prestamo = id_prestamo;
        this.dni_alumno = dni_alumno;
        this.codigo_libro = codigo_libro;
        this.fecha_prestamo = fecha_prestamo;
        this.fecha_devolucion = fecha_devolucion;
    }

    /**
     * Para obtener el valor del atributo id_prestamo
     *
     * @return codigo del prestamo
     */
    public Integer getId_prestamo() {
        return id_prestamo;
    }

    public void setId_prestamo(Integer id_prestamo) {
        this.id_prestamo = id_prestamo;
    }

    /**
     * Para obtener el valor del atributo dni_alumno
     *
     * @return el dni del alumno
     */
    public String getDni_alumno() {
        return dni_alumno;
    }

    /**
     * Para cambiar el valor del atributo dni_alumno
     *
     * @param dni_alumno
     */
    public void setDni_alumno(String dni_alumno) {
        this.dni_alumno = dni_alumno;
    }

    /**
     * Para obtener el valor del atributo codigo_libro
     *
     * @return El codigo del libro
     */
    public Integer getCodigo_libro() {
        return codigo_libro;
    }

    /**
     * Para cambiar el valor del atributo codigo_libro
     *
     * @param codigo_libro
     */
    public void setCodigo_libro(Integer codigo_libro) {
        this.codigo_libro = codigo_libro;
    }

    /**
     * Para obtener el valor del atributo fecha_prestamo
     *
     * @return fecha donde se presta el libro
     */
    public java.sql.Date getFecha_prestamo() {
        return fecha_prestamo;
    }

    /**
     * Para cambiar el valor del atributo fecha_prestamo
     *
     * @param fecha_prestamo
     */
    public void setFecha_prestamo(java.sql.Date fecha_prestamo) {
        this.fecha_prestamo = fecha_prestamo;
    }

    /**
     * Para obtener el valor del atributo fecha_devolucion
     *
     * @return fecha donde se devuelve el libro
     */
    public java.sql.Date getFecha_devolucion() {
        return fecha_devolucion;
    }

    /**
     * Para cambiar el valor del atributo fecha_devolucion
     *
     * @param fecha_devolucion
     */
    public void setFecha_devolucion(java.sql.Date fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    @Override
    public String toString() {
        return id_prestamo + " -->" +
                " DNI ALUMNO: " + dni_alumno + " / " +
                "CODIGO DE LIBRO: " + codigo_libro + " / " +
                "FECHA PRESTAMO: " + fecha_prestamo + " / " +
                "FECHA DEVOLUCION: " + fecha_devolucion;
    }
}
