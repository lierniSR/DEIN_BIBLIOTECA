package es.liernisarraoa.biblioteca.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionDB {
    private final Connection conexion;
    /**
     * Inicia la conexion a la base de datos
     *
     * @throws SQLException Sí ocurre un error al hacer la conexion.
     */
    public ConexionDB() throws SQLException {
        // los parametros de la conexion
        Properties connConfig = new Properties();
        connConfig.setProperty("user", "root");
        connConfig.setProperty("password", "WinRar3009*.");
        //la conexion en sí
        conexion = DriverManager.getConnection("jdbc:mariadb://127.0.0.1/libros?serverTimezone=Europe/Madrid", connConfig);
        conexion.setAutoCommit(true);
    }
    /**
     * Obtiene la conexion
     *
     * @return la conexion de la bbdd
     */
    public Connection getConexion(){return this.conexion;}
    /**
     * Metodo para cerrar la conexion
     *
     * @return la conexion de la bbdd cerrada
     */
    public Connection cerrarConexion(){
        try {
            conexion.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conexion;
    }

    public static void main(String[] args) throws SQLException {
        ConexionDB conexionDB = new ConexionDB();
    }
}
