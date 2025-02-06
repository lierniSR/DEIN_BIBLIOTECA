package es.liernisarraoa.biblioteca.Controlador.LibroOpciones;

import es.liernisarraoa.biblioteca.Biblioteca;
import es.liernisarraoa.biblioteca.Controlador.BibliotecaControlador;
import es.liernisarraoa.biblioteca.Controlador.LibroControlador;
import es.liernisarraoa.biblioteca.DAO.HistoricoDAO;
import es.liernisarraoa.biblioteca.Modelo.HistoricoPrestamo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

/**
 * Lista del historico de prestamos
 *
 * @version 1.0
 * @author Lierni Sarraoa Joaquin
 */
public class LibroHistoricoPrestamoControlador implements Initializable {
    /**
     * Atributos del archivo historicoPrestamos.fxml
     */
    @FXML
    public TextField tfDNI;
    @FXML
    public DatePicker dpFechaPrestamo;
    @FXML
    public TextField tfCodigoLibro;
    @FXML
    public ImageView iwFiltro;
    @FXML
    public ListView<HistoricoPrestamo> listaHistorico;
    @FXML
    public ImageView iwHome;
    @FXML
    public Button btnVolver;
    @FXML
    public DatePicker dpFechaDevolucion;

    /**
     * Atributos que se necesita para la clase
     */
    private Stage stage;

    /**
     * Este ActionEvent es para el boton de volver.
     * Para volver a la gestion de Libros.
     * Layout libros.fxml y el controlador LibroControlador.
     *
     * @param actionEvent
     */
    public void volverLibros(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(Biblioteca.class.getResource("Libro/libros.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Gestion de libros");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            //Pasar al controlador el Stage
            LibroControlador controlador = fxmlLoader.getController();
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
     * Este MouseEvent es para la imagen de la casa.
     * Para volver a la página principal.
     * Layout biblioteca.fxml y el controlador BibliotecaControlador.
     *
     * @param mouseEvent
     */
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

    /**
     * Este MouseEvent es para poder filtrar la lista.
     *
     * @param mouseEvent
     */
    public void filtrar(MouseEvent mouseEvent) {
        int filtrado = 0;
        if(!tfCodigoLibro.getText().isEmpty()){
            filtrado++;
        }
        if(!tfDNI.getText().isEmpty()){
            filtrado++;
        }
        if(dpFechaDevolucion.getValue() != null){
            filtrado++;
        }
        if(dpFechaPrestamo.getValue() != null){
            filtrado++;
        }
        System.out.println(filtrado);
        if(filtrado == 0){
            listaHistorico.getItems().setAll(HistoricoDAO.listaDelHistorial());
        }
        if(filtrado == 1){
            if(!tfCodigoLibro.getText().isEmpty()){
                try{
                    int codigo_libro = Integer.parseInt(tfCodigoLibro.getText());
                } catch (NumberFormatException e) {
                    throw new RuntimeException(e);
                }
                listaHistorico.getItems().setAll(HistoricoDAO.filtrarPorCodigoLibro(Integer.parseInt(tfCodigoLibro.getText())));
            }
            if(!tfDNI.getText().isEmpty()){
                listaHistorico.getItems().setAll(HistoricoDAO.filtrarPorDNI(tfDNI.getText()));
            }
            if(dpFechaPrestamo.getValue() != null){
                listaHistorico.getItems().setAll(HistoricoDAO.filtrarPorFechaPrestamo(Date.valueOf(dpFechaPrestamo.getValue())));
            }
            if(dpFechaDevolucion.getValue() != null){
                listaHistorico.getItems().setAll(HistoricoDAO.filtrarPorFechaDevolucion(Date.valueOf(tfCodigoLibro.getText())));
            }
        } else if(filtrado > 1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("FILTRADO");
            alert.setContentText("Este programa no admite utiliza dos o más filtros a la vez");
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

    /**
     * Es la implementacion de la interfaz Initializable.
     * Se ejecuta cada vez que se abre esta ventana.
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Crear un Tooltip
        Tooltip tooltipLista = new Tooltip("Lista del historico de prestamos");
        Tooltip tooltipHome = new Tooltip("Volver a la pagina inicial");
        Tooltip tooltipVolverLibro= new Tooltip("Volver a la pagina de libros");
        Tooltip tooltipFiltro = new Tooltip("Filtrar la lista");
        Tooltip tooltipFechaDevolucion = new Tooltip("Campo fecha devolucion");
        Tooltip tooltipFechaPrestamo = new Tooltip("Campo fecha prestamo");
        Tooltip tooltipCodigoLibro = new Tooltip("Campo codigo de libro");
        Tooltip tooltipDNI = new Tooltip("Campo DNI");

        // Asignar el Tooltip al botón
        Tooltip.install(btnVolver, tooltipVolverLibro);
        Tooltip.install(iwHome, tooltipHome);
        Tooltip.install(listaHistorico, tooltipLista);
        Tooltip.install(iwFiltro, tooltipFiltro);
        Tooltip.install(dpFechaDevolucion, tooltipFechaDevolucion);
        Tooltip.install(dpFechaPrestamo, tooltipFechaPrestamo);
        Tooltip.install(tfCodigoLibro, tooltipCodigoLibro);
        Tooltip.install(tfDNI, tooltipDNI);

        listaHistorico.getItems().addAll(HistoricoDAO.listaDelHistorial());
    }
}
