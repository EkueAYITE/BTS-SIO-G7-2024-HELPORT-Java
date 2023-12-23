module sio.projetjavahelport {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens sio.projetjavahelport to javafx.fxml;
    opens sio.projetjavahelport.tools to javafx.fxml;
    exports sio.projetjavahelport;
    exports sio.projetjavahelport.tools;
}