module com.example.schiffeversenken {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.schiffeversenken to javafx.fxml;
    exports com.example.schiffeversenken;
    exports com.example.schiffeversenken.classes;
    opens com.example.schiffeversenken.classes to javafx.fxml;
}