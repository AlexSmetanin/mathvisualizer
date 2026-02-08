module com.example.mathvisualizer {
    requires javafx.controls;
    requires javafx.fxml;
    requires itextpdf;
    requires java.desktop;


    opens com.example.mathvisualizer to javafx.fxml;
    exports com.example.mathvisualizer;
    exports com.example.mathvisualizer.model;
    opens com.example.mathvisualizer.model to javafx.fxml;
    exports com.example.mathvisualizer.controller;
    opens com.example.mathvisualizer.controller to javafx.fxml;
}