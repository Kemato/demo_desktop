package com.todo.demo.demo_desktop.controller;

import com.todo.demo.demo_desktop.model.dto.TaskDTO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Objects;

public class MainController {

    @FXML private StackPane contentPane;
    @FXML private VBox taskListPane;
    @FXML private VBox taskCreatePane;
    @FXML private VBox taskDetailPane;
    @FXML private VBox settingsPane;
    @FXML private VBox userProfilePane;
    @FXML private VBox userSettingsPane;

    @FXML
    public void initialize() {
        try {
            taskListPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../component/task_list.fxml")));
            showTaskList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showUserSettingsPane(){
        hide();
        userSettingsPane.setVisible(true);
        userSettingsPane.setManaged(true);
    }

    public void showUserProfile() {
        hide();
        userProfilePane.setVisible(true);
        userProfilePane.setManaged(true);
    }

    public void showSettings() {
        hide();
        settingsPane.setVisible(true);
        settingsPane.setManaged(true);
    }

    public void showTaskList() {
        hide();
        taskListPane.setVisible(true);
        taskListPane.setManaged(true);
    }

    public void showTaskDetail(TaskDTO task) {
        hide();
        taskDetailPane.setVisible(true);
        taskDetailPane.setManaged(true);
    }

    public void showTaskCreate(TaskDTO task) {
        hide();
        taskCreatePane.setVisible(true);
        taskCreatePane.setManaged(true);
    }

    private void hide(){
        settingsPane.setVisible(false);
        settingsPane.setManaged(false);

        userSettingsPane.setVisible(false);
        userSettingsPane.setManaged(false);

        userProfilePane.setVisible(false);
        userProfilePane.setManaged(false);

        taskCreatePane.setVisible(false);
        taskCreatePane.setManaged(false);

        taskDetailPane.setVisible(false);
        taskDetailPane.setManaged(false);

        taskListPane.setVisible(false);
        taskListPane.setManaged(false);
    }
}
