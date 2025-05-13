package com.todo.demo.demo_desktop.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import lombok.Setter;

public class SidebarController {

    @FXML private ToggleButton taskMenuButton;
    @FXML private ToggleButton userMenuButton;
    @FXML private ToggleButton settingsButton;

    private final ToggleGroup menuGroup = new ToggleGroup();

    @Setter
    private MainController mainController;

    @FXML
    public void initialize() {
        taskMenuButton.setToggleGroup(menuGroup);
        userMenuButton.setToggleGroup(menuGroup);
        settingsButton.setToggleGroup(menuGroup);

        // Можно добавить listener:
        menuGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle == taskMenuButton) {
                mainController.showTaskView();
            } else if (newToggle == userMenuButton) {
                mainController.showUserProfile();
            } else if (newToggle == settingsButton) {
                mainController.showSettings();
            }
        });
    }
}
