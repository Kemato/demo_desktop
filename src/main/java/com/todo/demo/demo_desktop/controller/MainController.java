package com.todo.demo.demo_desktop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainController {

    @FXML
    private StackPane contentPane;

    @FXML
    private TextField searchField;

    @FXML
    private ChoiceBox<String> statusFilter;

    @FXML
    public void initialize() {
        statusFilter.setValue("Все");
        loadTaskListView(); // Загружаем список задач по умолчанию
    }

    public void loadTaskListView() {
        loadContent("/com/todo/demo/demo_desktop/view/component/task_list.fxml");
    }

    public void loadTaskDetailView(Long taskId) {
        // пример, как можно передать taskId в контроллер через FXML loader (если нужно)
        loadContent("/com/todo/demo/demo_desktop/view/component/task_detail.fxml");
    }

    public void loadTaskCreateView() {
        loadContent("/com/todo/demo/demo_desktop/view/component/task_create.fxml");
    }

    @FXML
    public void onCreateTaskClick(ActionEvent event) {
        loadTaskCreateView();
    }

    private void loadContent(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Node node = loader.load();
            contentPane.getChildren().setAll(node); // Заменяем содержимое центральной панели
        } catch (IOException e) {

        }
    }
}
