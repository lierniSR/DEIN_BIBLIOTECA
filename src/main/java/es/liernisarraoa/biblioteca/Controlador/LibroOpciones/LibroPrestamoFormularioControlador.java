package es.liernisarraoa.biblioteca.Controlador.LibroOpciones;

import es.liernisarraoa.biblioteca.Biblioteca;
import es.liernisarraoa.biblioteca.Controlador.BibliotecaControlador;
import es.liernisarraoa.biblioteca.Controlador.LibroControlador;
import es.liernisarraoa.biblioteca.Controlador.LibroOpciones.Dialogos.ErrorControlador;
import es.liernisarraoa.biblioteca.Controlador.LibroOpciones.Dialogos.HechoPrestamoControlador;
import es.liernisarraoa.biblioteca.DAO.AlumnoDAO;
import es.liernisarraoa.biblioteca.DAO.LibroDAO;
import es.liernisarraoa.biblioteca.DAO.PrestamoDAO;
import es.liernisarraoa.biblioteca.Modelo.Libro;
import es.liernisarraoa.biblioteca.Modelo.Prestamo;
import es.liernisarraoa.biblioteca.Propiedades.Propiedades;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.*;

public class LibroPrestamoFormularioControlador implements Initializable {
    @FXML
    public TextField tfIdPrestamo;
    @FXML
    public DatePicker dpFechaPrestamo;
    @FXML
    public ImageView iwHome;
    @FXML
    public Button btnPrestar;
    @FXML
    public Button btnVolver;
    @FXML
    public ChoiceBox<String> cbAlumno;
    @FXML
    public ChoiceBox<String> cbCodigoLibro;

    private Stage stage;
    private Stage modalStage;
    private Scene modalScene;
    private final Propiedades propiedades = new Propiedades();

    public void volverPrestamo(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(Biblioteca.class.getResource("Libro/prestamoLibro.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Prestamo de libros");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            //Pasar al controlador el Stage
            LibroPrestamoControlador controlador = fxmlLoader.getController();
            controlador.setStage(stage);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("FXML");
            alert.setContentText("El archivo que contiene la visualizacion de la pestaña no se ha podido cargar.");
            alert.showAndWait();
        }
    }

    public void prestarLibro(ActionEvent actionEvent) {
        if(!tfIdPrestamo.getText().isEmpty() && cbCodigoLibro.getSelectionModel().getSelectedItem() != null && cbAlumno.getSelectionModel().getSelectedItem() != null && dpFechaPrestamo.getValue() != null){
            try{
                Integer codigo = Integer.parseInt(tfIdPrestamo.getText());
                Integer codig_libro = LibroDAO.conseguirCodigoConTitulo(cbCodigoLibro.getSelectionModel().getSelectedItem());
                Prestamo prestamo = new Prestamo(codigo, cbAlumno.getSelectionModel().getSelectedItem(), codig_libro, dpFechaPrestamo.getValue());
                if(PrestamoDAO.insertarPrestamo(prestamo)){
                    //Esto si el controlador necesita hacer algo en la ventana principal
                    // Cargar el FXML de la ventana modal
                    FXMLLoader loader =  new FXMLLoader(Biblioteca.class.getResource("Libro/dialogoBienPrestamo.fxml"));
                    Parent root = null;
                    try {
                        root = loader.load();
                        modalStage = new Stage();
                        modalScene = new Scene(root);

                        modalStage.setScene(modalScene);
                        modalStage.initModality(Modality.APPLICATION_MODAL);
                        modalStage.setTitle("SE HA GUARDADO");
                        modalStage.setResizable(false);
                        //Pasar al controlador el Stage
                        HechoPrestamoControlador controlador = loader.getController();
                        controlador.setStage(modalStage);
                        modalStage.showAndWait();
                        generarImforme(Integer.parseInt(tfIdPrestamo.getText()));
                        //System.out.println(tfIdPrestamo.getText());
                        cbCodigoLibro.getSelectionModel().clearSelection();
                        cbCodigoLibro.getItems().setAll(LibroDAO.listaTitulos());
                        cbAlumno.getSelectionModel().clearSelection();
                        tfIdPrestamo.setText("");
                        dpFechaPrestamo.setValue(null);
                    } catch (IOException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setTitle("FXML");
                        alert.setContentText("El archivo que contiene la visualizacion de la pestaña no se ha podido cargar.");
                        alert.showAndWait();
                    }
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("NO PRESTADO");
            alert.setContentText("No se ha podido prestar el libro.\n" +
                    "Todos los campos tienen que estar llenos.");
            alert.showAndWait();
        }
    }

    private void generarImforme(int idPrestamo) {
        System.out.println(idPrestamo);
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

            Connection connection = DriverManager.getConnection(dbUrl, dbUser , dbPassword);

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("codigo_prestamo", idPrestamo);
            // Llenar el informe con datos
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, connection);

            // Mostrar el informe
            JasperViewer.viewReport(jasperPrint, false);

            // Exportar a PDF (opcional)
            JasperExportManager.exportReportToPdfFile(jasperPrint, "PDF/AltasPrestamo/reporteAltaPrestamo_" + fechaActual + ".pdf");

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void volverHome(MouseEvent mouseEvent) {
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

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Crear un Tooltip
        Tooltip tooltipIDPrestamo = new Tooltip("Campo para insertar el ID del prestamo");
        Tooltip tooltipDNIAlumno = new Tooltip("Campo para seleccionar un DNI");
        Tooltip tooltipCodigoLibro= new Tooltip("Campo para el codigo del libro");
        Tooltip tooltipFechaPrestamo = new Tooltip("Campo para seleccionar la fecha");
        Tooltip tooltipVolverPrestamo = new Tooltip("Volver a la pagina de prestamos");
        Tooltip tooltipVolverHome = new Tooltip("Volver a la pagina principal");
        Tooltip tooltipPrestar = new Tooltip("Coger prestado el libro");

        // Asignar el Tooltip al botón
        Tooltip.install(tfIdPrestamo, tooltipIDPrestamo);
        Tooltip.install(cbAlumno, tooltipDNIAlumno);
        Tooltip.install(cbCodigoLibro, tooltipCodigoLibro);
        Tooltip.install(dpFechaPrestamo, tooltipFechaPrestamo);
        Tooltip.install(btnVolver, tooltipVolverPrestamo);
        Tooltip.install(btnPrestar, tooltipPrestar);
        Tooltip.install(iwHome, tooltipVolverHome);

        cbAlumno.getItems().addAll(AlumnoDAO.listaDNI());
        cbCodigoLibro.getItems().addAll(LibroDAO.listaTitulos());
    }
}
