module es.liernisarraoa.biblioteca {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires org.checkerframework.checker.qual;
    requires jasperreports;


    opens es.liernisarraoa.biblioteca to javafx.fxml;
    exports es.liernisarraoa.biblioteca;
    exports es.liernisarraoa.biblioteca.Controlador;
    opens es.liernisarraoa.biblioteca.Controlador to javafx.fxml;
    exports es.liernisarraoa.biblioteca.Controlador.AlumnoOpciones;
    opens es.liernisarraoa.biblioteca.Controlador.AlumnoOpciones to javafx.fxml;
    exports es.liernisarraoa.biblioteca.Controlador.AlumnoOpciones.Dialogos;
    opens es.liernisarraoa.biblioteca.Controlador.AlumnoOpciones.Dialogos to javafx.fxml;
    exports es.liernisarraoa.biblioteca.Modelo;
    opens es.liernisarraoa.biblioteca.Modelo to javafx.fxml;
    exports es.liernisarraoa.biblioteca.Controlador.LibroOpciones;
    opens es.liernisarraoa.biblioteca.Controlador.LibroOpciones to javafx.fxml;
    exports es.liernisarraoa.biblioteca.Controlador.LibroOpciones.Dialogos;
    opens es.liernisarraoa.biblioteca.Controlador.LibroOpciones.Dialogos to javafx.fxml;
    exports es.liernisarraoa.biblioteca.Controlador.Informe;
    opens es.liernisarraoa.biblioteca.Controlador.Informe to javafx.fxml;
}