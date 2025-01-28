package es.liernisarraoa.biblioteca.Modelo;

/**
 * Esta clase representa el objeto de Libro
 *
 * @version 1.0
 * @author Lierni Sarraoa Joaquin
 */
public class Libro {
    /**
     * Los atributos de la clase
     */
    private Integer codigo;
    private String titulo;
    private String autor;
    private String editorial;
    private String estado;
    private Integer baja;

    /**
     * Contructor con todos los atributos
     *
     * @param codigo
     * @param titulo
     * @param autor
     * @param editorial
     * @param estado
     * @param baja
     */
    public Libro(Integer codigo, String titulo, String autor, String editorial, String estado, Integer baja){
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.estado = estado;
        this.baja = baja;
    }

    /**
     * Para obtener el valor del atributo codigo
     *
     * @return codigo
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * Para cambiar el valor del atributo codigo
     *
     * @param codigo
     */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    /**
     * Para obtener el valor del atributo título
     *
     * @return titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Para cambiar el valor del atributo título
     *
     * @param titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Para obtener el valor del atributo autor
     *
     * @return Nombre del autor
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Para cambiar el valor del atributo autor
     *
     * @param autor
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Para obtener el valor del atributo editorial
     *
     * @return editorial
     */
    public String getEditorial() {
        return editorial;
    }

    /**
     * Para cambiar el valor del atributo editorial
     *
     * @param editorial
     */
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    /**
     * Para obtener el valor del atributo estado
     *
     * @return estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Para cambiar el valor del atributo estado
     *
     * @param estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Para obtener el valor del atributo baja
     *
     * @return Si esta de baja 1 si no 0
     */
    public Integer getBaja() {
        return baja;
    }

    /**
     * Para cambiar el valor del atributo baja
     *
     * @param baja
     */
    public void setBaja(Integer baja) {
        this.baja = baja;
    }

    @Override
    public String toString() {
        if(baja == 1){
            return codigo.toString() + " --> " +
                    "Titulo: " + titulo + "/" +
                    "Autor: " + autor + "/" +
                    "Editorial: " + editorial + "/" +
                    "Estado: " + estado + "/" +
                    "Esta de baja. ";
        }
        return codigo.toString() + " --> " +
                "Titulo: " + titulo + "/" +
                "Autor: " + autor + "/" +
                "Editorial: " + editorial + "/" +
                "Estado: " + estado + "/" +
                "No esta de baja. ";
    }
}
