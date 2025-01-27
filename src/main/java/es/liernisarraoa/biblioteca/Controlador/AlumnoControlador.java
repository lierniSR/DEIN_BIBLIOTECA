package es.liernisarraoa.biblioteca.Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AlumnoControlador implements Initializable {
    @FXML
    private ImageView btnAniadir;
    @FXML
    private ImageView btnModificar;
    @FXML
    private ImageView btnEliminar;
    @FXML
    private Button volverPrincipal;

    public void abrirFormularioAniadir(MouseEvent mouseEvent) {
    }

    public void abrirFormularioModificar(MouseEvent mouseEvent) {
    }

    public void abrirListaEliminar(MouseEvent mouseEvent) {
    }

    public void volverBiblioteca(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
