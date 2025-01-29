package es.liernisarraoa.biblioteca.DAO;

import es.liernisarraoa.biblioteca.Biblioteca;
import es.liernisarraoa.biblioteca.Conexion.ConexionDB;
import es.liernisarraoa.biblioteca.Controlador.LibroOpciones.Dialogos.ErrorControlador;
import es.liernisarraoa.biblioteca.Controlador.LibroOpciones.Dialogos.ErrorPrestamoControlador;
import es.liernisarraoa.biblioteca.Modelo.Libro;
import es.liernisarraoa.biblioteca.Modelo.Prestamo;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PrestamoDAO {
    private static ConexionDB conexionDB;
    private static Stage modalStage;
    private static Scene modalScene;

    public static boolean insertarPrestamo(Prestamo prestamo) {
        int lineas = 0;
        try {
            conexionDB = new ConexionDB();
            InputStream inputStream = null;
            String sql = "INSERT INTO prestamo(id_prestamo, dni_alumno, codigo_libro, fecha_prestamo) VALUES (?,?,?,?)";
            try {
                PreparedStatement pstmt = conexionDB.getConexion().prepareStatement(sql);
                pstmt.setInt(1, prestamo.getId_prestamo());
                pstmt.setString(2, prestamo.getDni_alumno());
                pstmt.setInt(3, prestamo.getCodigo_libro());
                pstmt.setDate(4, Date.valueOf(prestamo.getFecha_prestamo()));
                lineas = pstmt.executeUpdate();
                conexionDB.cerrarConexion();
            } catch (SQLException ex) {
                //Esto si el controlador necesita hacer algo en la ventana principal
                // Cargar el FXML de la ventana modal
                FXMLLoader loader =  new FXMLLoader(Biblioteca.class.getResource("Libro/dialogoErrorPrestamo.fxml"));
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
                    ErrorPrestamoControlador controlador = loader.getController();
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

    public static ArrayList<Prestamo> listaDePrestamos() {
        ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
        try {
            conexionDB = new ConexionDB();
            String sql = "SELECT * FROM prestamo";
            PreparedStatement pstmt = conexionDB.getConexion().prepareStatement(sql);
            ResultSet resultados = pstmt.executeQuery();
            while(resultados.next()){
                Prestamo prestamo = new Prestamo(resultados.getInt(1), resultados.getString(2), resultados.getInt(3), resultados.getDate(4).toLocalDate());
                prestamos.add(prestamo);
            }
            conexionDB.cerrarConexion();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return prestamos;
    }
}
