package es.liernisarraoa.biblioteca.Controlador.AlumnoOpciones;

import es.liernisarraoa.biblioteca.Biblioteca;
import es.liernisarraoa.biblioteca.Controlador.AlumnoControlador;
import es.liernisarraoa.biblioteca.Controlador.AlumnoOpciones.Dialogos.HechoControlador;
import es.liernisarraoa.biblioteca.Controlador.AlumnoOpciones.Dialogos.HechoModificarControlador;
import es.liernisarraoa.biblioteca.Controlador.BibliotecaControlador;
import es.liernisarraoa.biblioteca.DAO.AlumnoDAO;
import es.liernisarraoa.biblioteca.Modelo.Alumno;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para el layout modificarAlumno.fxml
 *
 * @version 1.0
 * @author Lierni Sarraoa Joaquin
 */
public class AlumnoModificarControlador implements Initializable {
    /**
     * Atributos del layout biblioteca.fxml
     */
    @FXML
    public ChoiceBox<Alumno> seleccionAlumnos;
    @FXML
    public TextField tfDNI;
    @FXML
    public TextField tfNombre;
    @FXML
    public TextField tfApellido1;
    @FXML
    public TextField tfApellido2;
    @FXML
    public ImageView iwHome;
    @FXML
    public Button btnModificar;
    @FXML
    public Button volverAlumno;

    /**
     * Atributos que necesitamos de la clase, para abrir ventanas normales y modales
     */
    private Stage stage;
    private Stage modalStage;
    private Scene modalScene;

    /**
     * Este ActionEvent es para el boton de volver.
     * Para volver a la gestion de Alumnos.
     * Layout alumnos.fxml y el controlador AlumnoControlador.
     *
     * @param actionEvent
     */
    public void volverAlumno(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(Biblioteca.class.getResource("Alumno/alumnos.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Gestion de alumnos");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            //Pasar al controlador el Stage
            AlumnoControlador controlador = fxmlLoader.getController();
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
     * Este ActionEvent es para el boton de modificar.
     * Cuando se clica comprueba que los campos no esten vacíos,
     * después hace una consulta a la base de datos desde la clase AlumnoDAO,
     * si la consulta sale true abrira una ventana modal con el layout dialogoBienModificar.fxml
     * y el controlador HechoModificarControlador.
     * Si da algún error saldrá una alerta de error.
     *
     * @param actionEvent
     */
    public void modificarAlumno(ActionEvent actionEvent) {
        if(!tfDNI.getText().isEmpty() && !tfNombre.getText().isEmpty() && !tfApellido1.getText().isEmpty() && !tfApellido2.getText().isEmpty() && tfDNI.getText().length() == 9){
            Alumno alumno = new Alumno(tfDNI.getText(), tfNombre.getText(), tfApellido1.getText(), tfApellido2.getText());
            if(AlumnoDAO.modificarAlumno(alumno)){
                //Esto si el controlador necesita hacer algo en la ventana principal
                // Cargar el FXML de la ventana modal
                FXMLLoader loader =  new FXMLLoader(Biblioteca.class.getResource("Alumno/dialogoBienModificar.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                    modalStage = new Stage();
                    modalScene = new Scene(root);

                    modalStage.setScene(modalScene);
                    modalStage.initModality(Modality.APPLICATION_MODAL);
                    modalStage.setTitle("SE HA MODIFICADO");
                    modalStage.setResizable(false);
                    //Pasar al controlador el Stage
                    HechoModificarControlador controlador = loader.getController();
                    controlador.setStage(modalStage);
                    modalStage.showAndWait();

                    // Actualizar los elementos del ListView
                    seleccionAlumnos.getItems().setAll(AlumnoDAO.listaDeAlumnos());
                    seleccionAlumnos.getSelectionModel().select(new Alumno(tfDNI.getText(), tfNombre.getText(), tfApellido1.getText(), tfApellido2.getText()));

                } catch (IOException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("FXML");
                    alert.setContentText("El archivo que contiene la visualizacion de la pestaña no se ha podido cargar.");
                    alert.showAndWait();
                    throw new RuntimeException(e);
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("NO ACTUALIZADO");
            alert.setContentText("El alumno que acabas de insertar no se ha podido actualizar.\n" +
                    "Todos los campos que aparecen en el formulario tienen que estar llenos.\n" +
                    "El campo DNI no puede tener ni más ni menos de 9 carácteres.");
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
        Tooltip tootltipSeleccionAlumnos = new Tooltip("Selecciona un alumno");
        Tooltip tooltiptfDNI = new Tooltip("Campo DNI");
        Tooltip tooltiptfNombre = new Tooltip("Campo nombre");
        Tooltip tooltiptfApellido1 = new Tooltip("Campo 1º apellido");
        Tooltip tooltiptfApellido2 = new Tooltip("Campo 2º apellido");
        Tooltip tooltipIwHome = new Tooltip("Volver a la pagina inicial");
        Tooltip tooltipBtnModificar = new Tooltip("Guardar los cambios");
        Tooltip tooltipVolverAlumno = new Tooltip("Volver a la pagina de alumnos");

        // Asignar el Tooltip al botón
        Tooltip.install(seleccionAlumnos, tootltipSeleccionAlumnos);
        Tooltip.install(tfDNI, tooltiptfDNI);
        Tooltip.install(tfNombre, tooltiptfNombre);
        Tooltip.install(tfApellido1, tooltiptfApellido1);
        Tooltip.install(tfApellido2, tooltiptfApellido2);
        Tooltip.install(iwHome, tooltipIwHome);
        Tooltip.install(btnModificar, tooltipBtnModificar);
        Tooltip.install(volverAlumno, tooltipVolverAlumno);

        seleccionAlumnos.getItems().addAll(AlumnoDAO.listaDeAlumnos());
        seleccionAlumnos.setOnAction(actionEvent ->{
                    Alumno alumno = seleccionAlumnos.getSelectionModel().getSelectedItem();
                    tfNombre.setDisable(false);
                    tfApellido1.setDisable(false);
                    tfApellido2.setDisable(false);

                    tfDNI.setText(alumno.getDni());
                    tfNombre.setText(alumno.getNombre());
                    tfApellido1.setText(alumno.getApellido1());
                    tfApellido2.setText(alumno.getApellido2());
                });
    }
}
