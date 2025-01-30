package es.liernisarraoa.biblioteca.DAO;

import es.liernisarraoa.biblioteca.Biblioteca;
import es.liernisarraoa.biblioteca.Conexion.ConexionDB;
import es.liernisarraoa.biblioteca.Controlador.LibroOpciones.Dialogos.ErrorControlador;
import es.liernisarraoa.biblioteca.Modelo.HistoricoPrestamo;
import es.liernisarraoa.biblioteca.Modelo.Libro;
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

public class HistoricoDAO {
    private static ConexionDB conexionDB;

    public static void insertarHistorico(HistoricoPrestamo historico) {
        int lineas = 0;
        try {
            conexionDB = new ConexionDB();
            InputStream inputStream = null;
            String sql = "INSERT INTO historico_prestamo(dni_alumno, codigo_libro, fecha_prestamo, fecha_devolucion) VALUES (?,?,?,?)";
            try {
                PreparedStatement pstmt = conexionDB.getConexion().prepareStatement(sql);
                pstmt.setString(1, historico.getDni_alumno());
                pstmt.setInt(2, historico.getCodigo_libro());
                pstmt.setDate(3, historico.getFecha_prestamo());
                pstmt.setDate(4, historico.getFecha_devolucion());

                lineas = pstmt.executeUpdate();
                conexionDB.cerrarConexion();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static ArrayList<HistoricoPrestamo> listaDelHistorial() {
        ArrayList<HistoricoPrestamo> historicos = new ArrayList<HistoricoPrestamo>();
        try {
            conexionDB = new ConexionDB();
            String sql = "SELECT * FROM historico_prestamo";
            PreparedStatement pstmt = conexionDB.getConexion().prepareStatement(sql);
            ResultSet resultados = pstmt.executeQuery();
            while(resultados.next()){
                HistoricoPrestamo historicoPrestamo = new HistoricoPrestamo(resultados.getInt(1), resultados.getString(2), resultados.getInt(3), resultados.getDate(4), resultados.getDate(5));
                historicos.add(historicoPrestamo);
            }
            conexionDB.cerrarConexion();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return historicos;
    }

    public static ArrayList<HistoricoPrestamo> filtrarPorCodigoLibro(Integer codigo_libro) {
        ArrayList<HistoricoPrestamo> historicos = new ArrayList<HistoricoPrestamo>();
        try {
            conexionDB = new ConexionDB();
            String sql = "SELECT * FROM historico_prestamo WHERE codigo_libro = ?";
            PreparedStatement pstmt = conexionDB.getConexion().prepareStatement(sql);
            pstmt.setInt(1, codigo_libro);
            ResultSet resultados = pstmt.executeQuery();
            while(resultados.next()){
                HistoricoPrestamo historicoPrestamo = new HistoricoPrestamo(resultados.getInt(1), resultados.getString(2), resultados.getInt(3), resultados.getDate(4), resultados.getDate(5));
                historicos.add(historicoPrestamo);
            }
            conexionDB.cerrarConexion();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return historicos;
    }

    public static ArrayList<HistoricoPrestamo> filtrarPorDNI(String DNI) {
        ArrayList<HistoricoPrestamo> historicos = new ArrayList<HistoricoPrestamo>();
        try {
            conexionDB = new ConexionDB();
            String sql = "SELECT * FROM historico_prestamo WHERE dni_alumno = ?";
            PreparedStatement pstmt = conexionDB.getConexion().prepareStatement(sql);
            pstmt.setString(1, DNI);
            ResultSet resultados = pstmt.executeQuery();
            while(resultados.next()){
                HistoricoPrestamo historicoPrestamo = new HistoricoPrestamo(resultados.getInt(1), resultados.getString(2), resultados.getInt(3), resultados.getDate(4), resultados.getDate(5));
                historicos.add(historicoPrestamo);
            }
            conexionDB.cerrarConexion();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return historicos;
    }

    public static ArrayList<HistoricoPrestamo> filtrarPorFechaPrestamo(Date fechaPrestamo) {
        ArrayList<HistoricoPrestamo> historicos = new ArrayList<HistoricoPrestamo>();
        try {
            conexionDB = new ConexionDB();
            String sql = "SELECT * FROM historico_prestamo WHERE fecha_prestamo = ?";
            PreparedStatement pstmt = conexionDB.getConexion().prepareStatement(sql);
            pstmt.setDate(1, fechaPrestamo);
            ResultSet resultados = pstmt.executeQuery();
            while(resultados.next()){
                HistoricoPrestamo historicoPrestamo = new HistoricoPrestamo(resultados.getInt(1), resultados.getString(2), resultados.getInt(3), resultados.getDate(4), resultados.getDate(5));
                historicos.add(historicoPrestamo);
            }
            conexionDB.cerrarConexion();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return historicos;
    }

    public static ArrayList<HistoricoPrestamo> filtrarPorFechaDevolucion(Date fechaDevolucion) {
        ArrayList<HistoricoPrestamo> historicos = new ArrayList<HistoricoPrestamo>();
        try {
            conexionDB = new ConexionDB();
            String sql = "SELECT * FROM historico_prestamo WHERE fecha_devolucion = ?";
            PreparedStatement pstmt = conexionDB.getConexion().prepareStatement(sql);
            pstmt.setDate(1, fechaDevolucion);
            ResultSet resultados = pstmt.executeQuery();
            while(resultados.next()){
                HistoricoPrestamo historicoPrestamo = new HistoricoPrestamo(resultados.getInt(1), resultados.getString(2), resultados.getInt(3), resultados.getDate(4), resultados.getDate(5));
                historicos.add(historicoPrestamo);
            }
            conexionDB.cerrarConexion();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return historicos;
    }
}
