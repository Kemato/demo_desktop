package com.todo.demo.demo_desktop.controller;

import com.todo.demo.demo_desktop.model.dto.TaskDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class TaskViewController {

    @FXML private Label titleLabel;
    @FXML private Label authorLabel;
    @FXML private Label assigneeLabel;
    @FXML private Label statusLabel;
    @FXML private Label priorityLabel;
    @FXML private Label dateCreatedLabel;
    @FXML private Label dateUpdatedLabel;
    @FXML private Label deadlineLabel;
    @FXML private Label dateFinishedLabel;
    @FXML private TextArea descriptionArea;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public void setTask(TaskDTO task) {
        titleLabel.setText(Optional.ofNullable(task.getTitle()).orElse("Без названия"));
        authorLabel.setText(Optional.ofNullable(task.getAuthor()).orElse("Неизвестно"));
        assigneeLabel.setText(Optional.ofNullable(task.getAssignee()).orElse("Не назначен"));
        statusLabel.setText(Optional.ofNullable(task.getStatus()).orElse("Нет статуса"));
        priorityLabel.setText(Optional.ofNullable(task.getPriority()).orElse("Без приоритета"));

        dateCreatedLabel.setText(task.getDateCreated() != null ? formatter.format(task.getDateCreated()) : "—");
        dateUpdatedLabel.setText(task.getDateUpdated() != null ? formatter.format(task.getDateUpdated()) : "—");
        deadlineLabel.setText(task.getDeadline() != null ? formatter.format(task.getDeadline()) : "Без срока");
        dateFinishedLabel.setText(task.getDateFinished() != null ? formatter.format(task.getDateFinished()) : "Не завершена");

        descriptionArea.setText(Optional.ofNullable(task.getDescription()).orElse("Нет описания"));
    }
}
