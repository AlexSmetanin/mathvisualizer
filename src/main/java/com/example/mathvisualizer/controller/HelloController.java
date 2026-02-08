package com.example.mathvisualizer.controller;

import com.example.mathvisualizer.model.MathFunctions;
import com.itextpdf.text.DocumentException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javax.swing.*;
import java.io.IOException;
import java.util.Map;

public class HelloController {
    @FXML private ComboBox<String> functionComboBox;
    @FXML private TextField xMinField, xMaxField, stepField;
    @FXML private TableView<Map.Entry<Double, Double>> resultTable;
    @FXML private TableColumn<Map.Entry<Double, Double>, Double> xColumn, yColumn;
    @FXML private LineChart<Double, Double> lineChart;
    @FXML private Button calculateButton, clearButton, reportButton, exitButton;

    private Map<Double, Double> resultMap;

    @FXML
    void initialize() {
        functionComboBox.setItems(FXCollections.observableArrayList("sin(x)", "cos(x)", "exp(x)", "log(x)"));
        reportButton.setDisable(true);

        calculateButton.setOnAction(e -> calculate());
        clearButton.setOnAction(e -> clearFields());
        reportButton.setOnAction(e -> createReport());
        exitButton.setOnAction(e -> {
            if (JOptionPane.showConfirmDialog(null, "Вийти з програми?", "Підтвердження",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }

    private void calculate() {
        try {
            double xMin = Double.parseDouble(xMinField.getText());
            double xMax = Double.parseDouble(xMaxField.getText());
            double step = Double.parseDouble(stepField.getText());
            String func = functionComboBox.getValue();

            if (func == null || step <= 0 || xMax <= xMin) {
                throw new IllegalArgumentException();
            }

            resultMap = MathFunctions.calculate(func, xMin, xMax, step);
            showResults();
            reportButton.setDisable(false);
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, "Помилка введення даних!").showAndWait();
        }
    }

    private void showResults() {
        lineChart.getData().clear();
        XYChart.Series<Double, Double> series = new XYChart.Series<>();
        series.setName(functionComboBox.getValue());

        for (Map.Entry<Double, Double> entry : resultMap.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }
        lineChart.getData().add(series);
    }

    private void clearFields() {
        xMinField.clear();
        xMaxField.clear();
        stepField.clear();
        resultTable.getItems().clear();
        lineChart.getData().clear();
        reportButton.setDisable(true);
    }

    private void createReport() {
        try {
            ReportController.createReport(functionComboBox.getValue(), resultMap);
        } catch (IOException | DocumentException e) {
            throw new RuntimeException(e);
        }
    }

}
