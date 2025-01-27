module es.liernisarraoa.biblioteca {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens es.liernisarraoa.biblioteca to javafx.fxml;
    exports es.liernisarraoa.biblioteca;
    exports es.liernisarraoa.biblioteca.Controlador;
    opens es.liernisarraoa.biblioteca.Controlador to javafx.fxml;
}