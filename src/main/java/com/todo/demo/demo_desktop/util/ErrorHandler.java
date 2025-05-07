package com.todo.demo.demo_desktop.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class ErrorHandler {
    public static void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Произошла ошибка");
        alert.setContentText(message);
        alert.getButtonTypes().setAll(ButtonType.OK);
        alert.showAndWait();
    }
}