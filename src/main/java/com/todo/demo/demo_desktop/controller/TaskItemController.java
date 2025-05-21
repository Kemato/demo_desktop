package com.todo.demo.demo_desktop.controller;

import com.todo.demo.demo_desktop.model.dto.TaskDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lombok.Getter;
import lombok.Setter;

public class TaskItemController {

    @FXML private Label titleLabel;
    @FXML private Label shortDescriptionLabel;
    @FXML private Label priorityLabel;

    @Getter
    private TaskDTO task;

    public void setTask(TaskDTO task) {
        this.task = task;
        titleLabel.setText(task.getTitle());
        shortDescriptionLabel.setText(task.getDescription());
        //..todo Обрезать слишком длинные описания
        priorityLabel.setText(task.getPriority());
    }

}
