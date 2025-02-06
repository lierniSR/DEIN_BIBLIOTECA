package es.liernisarraoa.biblioteca.DAO;

import es.liernisarraoa.biblioteca.Biblioteca;
import es.liernisarraoa.biblioteca.Conexion.ConexionDB;
import es.liernisarraoa.biblioteca.Controlador.LibroOpciones.Dialogos.ErrorControlador;
import es.liernisarraoa.biblioteca.Modelo.Alumno;
import es.liernisarraoa.biblioteca.Modelo.Libro;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Consultas SQL para la tabla Libro
 *
 * @author Lierni Sarraoa Joaquin
 * @version 1.0
 */
public class LibroDAO {
    /**
     * Atributos para la clase
     */
    private static Stage modalStage;
    private static Scene modalScene;
    private static ConexionDB conexionDB;

    /**
     * Consulta para insertar un libro en la base de datos
     *
     * @param libro
     * @return si ha ido bien o no
     */
    public static boolean aniadirLibro(Libro libro) {
        int lineas = 0;
        try {
            conexionDB = new ConexionDB();
            InputStream inputStream = null;
            String sql = "INSERT INTO libro(codigo, titulo, autor, editorial, estado, baja) VALUES (?,?,?,?,?,?)";
            try {
                PreparedStatement pstmt = conexionDB.getConexion().prepareStatement(sql);
                pstmt.setInt(1, libro.getCodigo());
                pstmt.setString(2, libro.getTitulo());
                pstmt.setString(3, libro.getAutor());
                pstmt.setString(4, libro.getEditorial());
                pstmt.setString(5, libro.getEstado());
                pstmt.setInt(6, libro.getBaja());

                lineas = pstmt.executeUpdate();
                conexionDB.cerrarConexion();
            } catch (SQLException ex) {
                //Esto si el controlador necesita hacer algo en la ventana principal
                // Cargar el FXML de la ventana modal
                FXMLLoader loader =  new FXMLLoader(Biblioteca.class.getResource("Libro/dialogoError.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                    modalStage = new Stage();
                    modalScene = new Scene(root);

                    modalStage.setScene(modalScene);
                    modalStage.initModality(Modality.APPLICATION_MODAL);
                    modalStage.setTitle("NO SE HA GUARDADO");
                    modalStage.setResizable(false);
                    //Pasar al controlador el Stage
                    ErrorControlador controlador = loader.getController();
                    controlador.setStage(modalStage);
                    modalStage.showAndWait();

                } catch (IOException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("FXML");
                    alert.setContentText("El archivo que contiene la visualizacion de la pestaña no se ha podido cargar.");
                    alert.showAndWait();
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return lineas > 0;
    }

    /**
     * Para listar todos los libros de la base de datos.
     *
     * @return lista del objeto Prestamo
     */
    public static ArrayList<Libro> listaDeLibros() {
        ArrayList<Libro> libros = new ArrayList<Libro>();
        try {
            conexionDB = new ConexionDB();
            String sql = "SELECT * FROM libro";
            PreparedStatement pstmt = conexionDB.getConexion().prepareStatement(sql);
            ResultSet resultados = pstmt.executeQuery();
            while(resultados.next()){
                Libro libro = new Libro(resultados.getInt(1), resultados.getString(2), resultados.getString(3), resultados.getString(4), resultados.getString(5), resultados.getInt(6));
                libros.add(libro);
            }
            conexionDB.cerrarConexion();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return libros;
    }

    /**
     * Consulta para actualizar un libro en la base de datos
     *
     * @param libro
     */
    public static boolean actualizarLibro(Libro libro) {
        int lineas = 0;
        try {
            conexionDB = new ConexionDB();
            String sql = "UPDATE libro SET titulo = ?, autor = ?, editorial = ?, estado = ?, baja = ? WHERE codigo = ?";
            PreparedStatement pstmt = conexionDB.getConexion().prepareStatement(sql);
            pstmt.setString(1, libro.getTitulo());
            pstmt.setString(2, libro.getAutor());
            pstmt.setString(3, libro.getEditorial());
            pstmt.setString(4, libro.getEstado());
            pstmt.setInt(5, libro.getBaja());
            pstmt.setInt(6, libro.getCodigo());
            lineas = pstmt.executeUpdate();
            conexionDB.cerrarConexion();
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("SQL");
            alert.setContentText("No se ha podido ejecutar la sentencia.");
            alert.showAndWait();
            throw new RuntimeException(ex);
        }
        return lineas > 0;
    }

    /**
     * Consulta para eliminar un libro en la base de datos
     *
     * @param libro
     */
    public static boolean eliminarLibro(Libro libro) {
        int lineas = 0;
        try {
            conexionDB = new ConexionDB();
            String sql = "UPDATE libro SET baja = ? WHERE codigo = ?";
            PreparedStatement pstm = conexionDB.getConexion().prepareStatement(sql);
            if(libro.getBaja() == 0){
                pstm.setInt(1, 1);
            } else {
                pstm.setInt(1, 0);
            }
            pstm.setInt(2, libro.getCodigo());
            lineas = pstm.executeUpdate();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("SQL");
            alert.setContentText("No se ha podido ejecutar la sentencia SQL.");
            alert.showAndWait();
        }
        return lineas > 0;
    }

    /**
     * Para listar librosno prestados de la base de datos.
     *
     * @return lista del objeto Prestamo
     */
    public static ArrayList<Libro> listarLibrosNoPrestados(){
        ArrayList<Libro> libros = new ArrayList<Libro>();
        try {
            conexionDB = new ConexionDB();
            String sql = "SELECT * FROM libro WHERE codigo NOT IN (SELECT codigo_libro FROM prestamo) AND baja = 0";
            PreparedStatement pstmt = conexionDB.getConexion().prepareStatement(sql);
            ResultSet resultados = pstmt.executeQuery();
            while(resultados.next()){
                Libro libro = new Libro(resultados.getInt(1), resultados.getString(2), resultados.getString(3), resultados.getString(4), resultados.getString(5), resultados.getInt(6));
                libros.add(libro);
            }
            conexionDB.cerrarConexion();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return libros;
    }

    /**
     * Para listar los titulos de todos los libros de la base de datos.
     *
     * @return lista del objeto Prestamo
     */
    public static ArrayList<String> listaTitulos(){
        ArrayList<String> libros = new ArrayList<String>();
        try {
            conexionDB = new ConexionDB();
            String sql = "SELECT titulo FROM libro WHERE codigo NOT IN (SELECT codigo_libro FROM prestamo) AND baja = 0";
            PreparedStatement pstmt = conexionDB.getConexion().prepareStatement(sql);
            ResultSet resultados = pstmt.executeQuery();
            while(resultados.next()){
                libros.add(resultados.getString(1));
            }
            conexionDB.cerrarConexion();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return libros;
    }

    /**
     * Para listar los titulos de los libros en prestamo.
     *
     * @return lista del objeto Prestamo
     */
    public static ArrayList<String> listaTitulosEnPrestamo(){
        ArrayList<String> libros = new ArrayList<String>();
        try {
            conexionDB = new ConexionDB();
            String sql = "SELECT titulo FROM libro WHERE codigo IN (SELECT codigo_libro FROM prestamo)";
            PreparedStatement pstmt = conexionDB.getConexion().prepareStatement(sql);
            ResultSet resultados = pstmt.executeQuery();
            while(resultados.next()){
                libros.add(resultados.getString(1));
            }
            conexionDB.cerrarConexion();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return libros;
    }

    /**
     * Para conseguir el codigo en base a un codigo en la base de datos.
     *
     * @return lista del objeto Prestamo
     */
    public static Integer conseguirCodigoConTitulo(String titulo) {
        int codigo_libro = 0;
        try {
            conexionDB = new ConexionDB();
            String sql = "SELECT codigo FROM libro WHERE titulo = ?";
            PreparedStatement pstmt = conexionDB.getConexion().prepareStatement(sql);
            pstmt.setString(1, titulo);
            ResultSet resultados = pstmt.executeQuery();
            while(resultados.next()){
                codigo_libro = resultados.getInt(1);
            }
            conexionDB.cerrarConexion();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return codigo_libro;
    }
    /**
     * Para conseguir el titulo en base a un codigo de la base de datos
     *
     * @return lista del objeto Prestamo
     */
    public static String tituloPorCodigo(Integer codigoLibro) {
        String titulo = "";
        try {
            conexionDB = new ConexionDB();
            String sql = "SELECT titulo FROM libro WHERE codigo = ?";
            PreparedStatement pstmt = conexionDB.getConexion().prepareStatement(sql);
            pstmt.setInt(1, codigoLibro);
            ResultSet resultados = pstmt.executeQuery();
            while(resultados.next()){
                titulo = resultados.getString(1);
            }
            conexionDB.cerrarConexion();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return titulo;
    }
    /**
     * Consulta para actualizar el estado de un libro en la base de datos
     *
     * @param estado
     * @param codigo
     */
   public static void actualizarEstadoLibro(String estado, Integer codigo) {
        int lineas = 0;
        try {
            conexionDB = new ConexionDB();
            String sql = "UPDATE libro SET estado = ? WHERE codigo = ?";
            PreparedStatement pstmt = conexionDB.getConexion().prepareStatement(sql);
            pstmt.setString(1, estado);
            pstmt.setInt(2, codigo);
            lineas = pstmt.executeUpdate();
            conexionDB.cerrarConexion();
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("SQL");
            alert.setContentText("No se ha podido ejecutar la sentencia.");
            alert.showAndWait();
            throw new RuntimeException(ex);
        }
    }
}
