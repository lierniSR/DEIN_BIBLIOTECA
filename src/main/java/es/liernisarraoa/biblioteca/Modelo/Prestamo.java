package es.liernisarraoa.biblioteca.Modelo;

import java.util.Date;

/**
 * Esta clase representa la tabla de la base de datos
 *
 * @author Lierni Sarraoa Joaquin
 * @version 1.0
 */
public class Prestamo {
    /**
     * Atributos de la clase
     */
    private Integer id_prestamo;
    private String dni_alumno;
    private Integer codigo_libro;
    private Date fecha_prestamo;

    /**
     * Constructor para la clase con todos los atributos
     *
     * @param id_prestamo
     * @param dni_alumno
     * @param codigo_libro
     * @param fecha_prestamo
     */
    public Prestamo(Integer id_prestamo, String dni_alumno, Integer codigo_libro, Date fecha_prestamo){
        this.id_prestamo = id_prestamo;
        this.dni_alumno = dni_alumno;
        this.codigo_libro = codigo_libro;
        this.fecha_prestamo = fecha_prestamo;
    }

    /**
     * Para obtener el valor del atributo id_prestamo
     *
     * @return El codigo del prestamo
     */
    public Integer getId_prestamo() {
        return id_prestamo;
    }

    /**
     * Para cambiar el valor de id_prestamo
     *
     * @param id_prestamo
     */
    public void setId_prestamo(Integer id_prestamo) {
        this.id_prestamo = id_prestamo;
    }

    /**
     * Para obtener el valor del atributo dni_alumno
     *
     * @return DNI del alumno
     */
    public String getDni_alumno() {
        return dni_alumno;
    }

    /**
     * Para cambiar el valor de dni_alumno
     *
     * @param dni_alumno
     */
    public void setDni_alumno(String dni_alumno) {
        this.dni_alumno = dni_alumno;
    }

    /**
     * Para obtener el valor del atributo codigo_libro
     *
     * @return El codigo
     */
    public Integer getCodigo_libro() {
        return codigo_libro;
    }

    public void setCodigo_libro(Integer codigo_libro) {
        this.codigo_libro = codigo_libro;
    }

    /**
     * Para obtener el valor del atributo fecha_prestamo
     *
     * @return La fecha donde se prestó el libro
     */
    public Date getFecha_prestamo() {
        return fecha_prestamo;
    }

    /**
     * Para cambiar el valor de fecha_prestamo
     *
     * @param fecha_prestamo
     */
    public void setFecha_prestamo(Date fecha_prestamo) {
        this.fecha_prestamo = fecha_prestamo;
    }
}
