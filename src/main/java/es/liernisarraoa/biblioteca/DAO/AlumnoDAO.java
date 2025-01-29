package es.liernisarraoa.biblioteca.DAO;

import es.liernisarraoa.biblioteca.Biblioteca;
import es.liernisarraoa.biblioteca.Conexion.ConexionDB;
import es.liernisarraoa.biblioteca.Controlador.AlumnoControlador;
import es.liernisarraoa.biblioteca.Controlador.AlumnoOpciones.Dialogos.ErrorControlador;
import es.liernisarraoa.biblioteca.Modelo.Alumno;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class AlumnoDAO {
    private static ConexionDB conexionDB;
    private static Stage modalStage;
    private static Scene modalScene;

    public static boolean insertarAlumno(Alumno alumno){
        int lineas = 0;
        try {
            conexionDB = new ConexionDB();
            InputStream inputStream = null;
            String sql = "INSERT INTO alumno(dni, nombre, apellido1, apellido2) VALUES (?,?,?,?)";
            try {
                PreparedStatement pstmt = conexionDB.getConexion().prepareStatement(sql);
                pstmt.setString(1, alumno.getDni());
                pstmt.setString(2, alumno.getNombre());
                pstmt.setString(3, alumno.getApellido1());
                pstmt.setString(4, alumno.getApellido2());

                lineas = pstmt.executeUpdate();
                conexionDB.cerrarConexion();
            } catch (SQLException ex) {
                //Esto si el controlador necesita hacer algo en la ventana principal
                // Cargar el FXML de la ventana modal
                FXMLLoader loader =  new FXMLLoader(Biblioteca.class.getResource("Alumno/dialogoError.fxml"));
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

    public static ArrayList<Alumno> listaDeAlumnos() {
        ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
        try {
            conexionDB = new ConexionDB();
            String sql = "SELECT * FROM alumno";
            PreparedStatement pstmt = conexionDB.getConexion().prepareStatement(sql);
            ResultSet resultados = pstmt.executeQuery();
            while(resultados.next()){
                Alumno alumno = new Alumno(resultados.getString(1), resultados.getString(2), resultados.getString(3), resultados.getString(4));
                alumnos.add(alumno);
            }
            conexionDB.cerrarConexion();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return alumnos;
    }

    public static boolean modificarAlumno(Alumno alumno) {
        int lineas = 0;
        try {
            conexionDB = new ConexionDB();
            String sql = "UPDATE alumno SET nombre = ?, apellido1 = ?, apellido2 = ? WHERE dni = ?";
            PreparedStatement pstmt = conexionDB.getConexion().prepareStatement(sql);
            pstmt.setString(1, alumno.getNombre());
            pstmt.setString(2, alumno.getApellido1());
            pstmt.setString(3, alumno.getApellido2());
            pstmt.setString(4, alumno.getDni());
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

    public static boolean eliminarAlumno(Alumno alumno) {
        int lineas = 0;
        try {
            conexionDB = new ConexionDB();
            String sql = "DELETE FROM alumno WHERE dni = ?";
            PreparedStatement pstm = conexionDB.getConexion().prepareStatement(sql);
            pstm.setString(1, alumno.getDni());
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

    public static ArrayList<String> listaDNI(){
        ArrayList<String> alumnos = new ArrayList<String>();
        try {
            conexionDB = new ConexionDB();
            String sql = "SELECT dni FROM alumno";
            PreparedStatement pstmt = conexionDB.getConexion().prepareStatement(sql);
            ResultSet resultados = pstmt.executeQuery();
            while(resultados.next()){
                alumnos.add(resultados.getString(1));
            }
            conexionDB.cerrarConexion();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return alumnos;
    }
}
