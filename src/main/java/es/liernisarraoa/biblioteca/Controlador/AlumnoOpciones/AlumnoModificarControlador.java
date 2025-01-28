package es.liernisarraoa.biblioteca.Controlador.AlumnoOpciones;

import es.liernisarraoa.biblioteca.Modelo.Alumno;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AlumnoModificarControlador {
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

    private Stage stage;

    public void volverAlumno(ActionEvent actionEvent) {
    }

    public void modificarAlumno(ActionEvent actionEvent) {
    }

    public void volverHome(MouseEvent mouseEvent) {
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
