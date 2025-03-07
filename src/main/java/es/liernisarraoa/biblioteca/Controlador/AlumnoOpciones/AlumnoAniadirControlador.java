package es.liernisarraoa.biblioteca.Controlador.AlumnoOpciones;

import es.liernisarraoa.biblioteca.Biblioteca;
import es.liernisarraoa.biblioteca.Controlador.AlumnoControlador;
import es.liernisarraoa.biblioteca.Controlador.AlumnoOpciones.Dialogos.HechoControlador;
import es.liernisarraoa.biblioteca.Controlador.BibliotecaControlador;
import es.liernisarraoa.biblioteca.DAO.AlumnoDAO;
import es.liernisarraoa.biblioteca.Modelo.Alumno;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para el layout aniadirAlumno.fxml
 *
 * @version 1.0
 * @author Lierni Sarraoa Joaquin
 */
public class AlumnoAniadirControlador implements Initializable {
    /**
     * Atributos del layout aniadirAlumno.fxml
     */
    @FXML
    public TextField tfDNI;
    @FXML
    public TextField tfNombre;
    @FXML
    public TextField tfApellido1;
    @FXML
    public TextField tfApellido2;
    @FXML
    public ImageView volverHome;
    @FXML
    public Button aniadirAlumno;
    @FXML
    public Button volverAlumno;

    /**
     * Atributos que necesitamos de la clase, para abrir ventanas normales y modales
     */
    private Stage stage;
    private Stage modalStage;
    private Scene modalScene;

    /**
     * Este MouseEvent es para la imagen de la casa.
     * Para volver a la página principal.
     * Layout biblioteca.fxml y el controlador BibliotecaControlador.
     *
     * @param mouseEvent
     */
    public void abrirHome(MouseEvent mouseEvent) {
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
     * Este ActionEvent es para el boton de añadir.
     * Cuando se clica comprueba que todos los campos no esten vacios,
     * después hace una consulta a la base de datos desde la clase AlumnoDAO,
     * si la consulta sale true abrira una ventana modal con el layout dialogoBien.fxml
     * y el controlador HechoControlador.
     * Si da algún error saldrá una alerta de error.
     *
     * @param actionEvent
     */
    public void aniadirAlumno(ActionEvent actionEvent) {
        if(!tfDNI.getText().isEmpty() && !tfNombre.getText().isEmpty() && !tfApellido1.getText().isEmpty() && !tfApellido2.getText().isEmpty() && tfDNI.getText().length() == 9){
            Alumno alumno = new Alumno(tfDNI.getText(), tfNombre.getText(), tfApellido1.getText(), tfApellido2.getText());
            if(AlumnoDAO.insertarAlumno(alumno)){
                //Esto si el controlador necesita hacer algo en la ventana principal
                // Cargar el FXML de la ventana modal
                FXMLLoader loader =  new FXMLLoader(Biblioteca.class.getResource("Alumno/dialogoBien.fxml"));
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
                    HechoControlador controlador = loader.getController();
                    controlador.setStage(modalStage);
                    modalStage.showAndWait();
                    tfDNI.setText("");
                    tfNombre.setText("");
                    tfApellido1.setText("");
                    tfApellido2.setText("");

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
            alert.setTitle("NO INSERTADO");
            alert.setContentText("El alumno que acabas de insertar no se ha podido guardar.\n" +
                    "Todos los campos que aparecen en el formulario tienen que estar llenos.\n" +
                    "El campo DNI no puede tener ni más ni menos de 9 carácteres.");
            alert.showAndWait();
        }
    }

    /**
     * Este ActionEvent es para el boton de volver.
     * Para volver a la gestion de Alumnos.
     * Layout alumnos.fxml y el controlador AlumnoControlador.
     *
     * @param actionEvent
     */
    public void volverAlumnos(ActionEvent actionEvent) {
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
        Tooltip tooltipAniadir = new Tooltip("Insertar el nuevo alumno");
        Tooltip tooltipHome = new Tooltip("Volver a la pagina inicial");
        Tooltip tooltipDNI = new Tooltip("Campo para insertar el DNI del alumno");
        Tooltip tooltipNombre = new Tooltip("Campo para insertar el nombre del alumno");
        Tooltip tooltipApellido1 = new Tooltip("Campo para insertar el primer apellido del alumno");
        Tooltip tooltipApellido2 = new Tooltip("Campo para insertar el segundo apellido del alumno");
        Tooltip tooltipVolver = new Tooltip("Volver a la página de alumnos");

        // Asignar el Tooltip al botón
        Tooltip.install(aniadirAlumno, tooltipAniadir);
        Tooltip.install(volverHome, tooltipHome);
        Tooltip.install(volverAlumno, tooltipVolver);
        Tooltip.install(tfDNI, tooltipDNI);
        Tooltip.install(tfNombre, tooltipNombre);
        Tooltip.install(tfApellido1, tooltipApellido1);
        Tooltip.install(tfApellido2, tooltipApellido2);
    }
}
