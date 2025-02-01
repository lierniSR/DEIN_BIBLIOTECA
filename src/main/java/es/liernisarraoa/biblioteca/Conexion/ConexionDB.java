package es.liernisarraoa.biblioteca.Conexion;

import es.liernisarraoa.biblioteca.Propiedades.Propiedades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionDB {
    private final Connection conexion;
    private final Propiedades propiedades = new Propiedades();
    /**
     * Inicia la conexion a la base de datos
     *
     * @throws SQLException Sí ocurre un error al hacer la conexion.
     */
    public ConexionDB() throws SQLException {
        // los parametros de la conexion
        Properties connConfig = new Properties();
        connConfig.setProperty("user", propiedades.getProperty("db.usuario"));
        connConfig.setProperty("password", propiedades.getProperty("db.contrasenia"));
        //la conexion en sí
        conexion = DriverManager.getConnection(propiedades.getProperty("db.url"), connConfig);
        conexion.setAutoCommit(true);
        System.out.println("Conexion completada");
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
}
