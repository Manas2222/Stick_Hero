module com.example.stick_hero {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
                    requires org.kordamp.ikonli.javafx;
                    requires com.almasb.fxgl.all;
    requires javafx.media;

    opens com.example.stick_hero to javafx.fxml;
    exports com.example.stick_hero;
}