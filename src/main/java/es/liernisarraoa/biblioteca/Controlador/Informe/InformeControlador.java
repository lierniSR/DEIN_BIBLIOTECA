package es.liernisarraoa.biblioteca.Controlador.Informe;

import es.liernisarraoa.biblioteca.Biblioteca;
import es.liernisarraoa.biblioteca.Controlador.BibliotecaControlador;
import es.liernisarraoa.biblioteca.Propiedades.Propiedades;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.HashMap;

/**
 * Controlador para abrir los informes
 *
 * @version 1.0
 * @author Lierni Sarraoa Joaquin
 */
public class InformeControlador {
    /**
     * Atributos del layout biblioteca.fxml
     */
    @FXML
    public Button btnLibro;
    @FXML
    public Button btnGraficos;
    @FXML
    public Button btnAlumnos;
    @FXML
    public ImageView iwHome;
    @FXML
    public Button btnVolver;

    /**
     * Atributos que necesitamos de la clase, para abrir ventanas normales y modales
     */
    private Stage stage;
    private final Propiedades propiedades = new Propiedades();

    /**
     * Este ActionEvent perteneces al boton de listado de libros
     * Es para abrir el informe creado en JasperReport
     *
     * @param actionEvent
     */
    public void abrirInformeLibros(ActionEvent actionEvent) {
        LocalDate fechaActual = LocalDate.now();
        try {
            // Ruta del archivo Jasper (compilado)
            String reportPath = "C:\\DM2\\DEIN\\ProyectoFXJasper\\Biblioteca\\src\\main\\resources\\es\\liernisarraoa\\biblioteca\\JasperInformeLibros\\ListaAlumnos.jasper";

            // Cargar el archivo Jasper
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportPath);

            // Configurar conexión a la base de datos
            String dbUrl = "jdbc:mariadb://localhost:3306/libros";
            String dbUser  = propiedades.getProperty("db.usuario");
            String dbPassword = propiedades.getProperty("db.contrasenia");

            Connection connection = DriverManager.getConnection(dbUrl, dbUser , dbPassword);

            // Llenar el informe con datos
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);

            // Mostrar el informe
            JasperViewer.viewReport(jasperPrint, false);

            // Exportar a PDF (opcional)
            JasperExportManager.exportReportToPdfFile(jasperPrint, "PDF/ReportesLibro/reporteLibros_" + fechaActual + ".pdf");

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirInformeDeGraficos(ActionEvent actionEvent) {
    }

    /**
     * Este ActionEvent perteneces al boton de listado de alumnos
     * Es para abrir el informe creado en JasperReport
     *
     * @param actionEvent
     */
    public void abrirInformeAlumnos(ActionEvent actionEvent) {
        LocalDate fechaActual = LocalDate.now();
        try {
            // Ruta del archivo Jasper (compilado)
            String reportPath = "C:\\DM2\\DEIN\\ProyectoFXJasper\\Biblioteca\\src\\main\\resources\\es\\liernisarraoa\\biblioteca\\JasperPrestamoAlta\\PrestamoAlta.jasper";

            // Cargar el archivo Jasper
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportPath);

            // Configurar conexión a la base de datos
            String dbUrl = "jdbc:mariadb://localhost:3306/libros";
            String dbUser  = propiedades.getProperty("db.usuario");
            String dbPassword = propiedades.getProperty("db.contrasenia");

            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            if (connection != null) {
                System.out.println("✅ Conexión exitosa a la base de datos.");
            } else {
                System.out.println("❌ Error al conectar a la base de datos.");
            }

            // Llenar el informe con datos
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), connection);

            // Depurar si el informe tiene datos
            System.out.println("Número de páginas del informe: " + jasperPrint.getPages().size());

            // Mostrar el informe
            JasperViewer.viewReport(jasperPrint, false);

            // Exportar a PDF
            JasperExportManager.exportReportToPdfFile(jasperPrint, "PDF/AlumnoCalculo/reporteAlumno_" + fechaActual + ".pdf");

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Este MouseEvent es para la imagen de la casa.
     * Para volver a la página principal.
     * Layout biblioteca.fxml y el controlador BibliotecaControlador.
     *
     * @param mouseEvent
     */
    public void volverHome(ActionEvent mouseEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(Biblioteca.class.getResource("biblioteca.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Biblioteca");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            //Pasar al controlador el Stage
            BibliotecaControlador controlador = fxmlLoader.getController();
            controlador.setStage(stage);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("FXML");
            alert.setContentText("El archivo que contiene la visualizacion de la pestaña no se ha podido cargar.");
            alert.showAndWait();
        }
    }

    /**
     * Este atributo es para poder cambiar de ventanas
     *
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
