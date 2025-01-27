module es.liernisarraoa.biblioteca {
    requires javafx.controls;
    requires javafx.fxml;


    opens es.liernisarraoa.biblioteca to javafx.fxml;
    exports es.liernisarraoa.biblioteca;
}