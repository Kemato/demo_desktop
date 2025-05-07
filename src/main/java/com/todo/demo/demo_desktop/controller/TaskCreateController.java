package com.todo.demo.demo_desktop.controller;

import com.todo.demo.demo_desktop.api.auth.CurrentUserContext;
import com.todo.demo.demo_desktop.model.dto.TaskCreateDTO;
import com.todo.demo.demo_desktop.service.TaskService;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskCreateController {

    @FXML private TextField titleField;
    @FXML private TextArea descriptionArea;
    @FXML private TextField priorityField;
    @FXML private TextField assigneeIdField;
    @FXML private TextField deadlineField;

    private final TaskService taskService = TaskService.getInstance();

    private Pane contentPane;

    public void setContentPane(Pane contentPane) {
        this.contentPane = contentPane;
    }

    @FXML
    public void onCreate() {
        try {
            TaskCreateDTO dto = new TaskCreateDTO();
            dto.setTitle(titleField.getText());
            dto.setDescription(descriptionArea.getText());
            dto.setPriority(priorityField.getText());
            dto.setAssignee(Long.parseLong(assigneeIdField.getText()));
            dto.setAuthor(CurrentUserContext.getInstance().getCurrentUser().getId());
            dto.setDeadline(LocalDateTime.parse(deadlineField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));

            taskService.createTask(dto);

            // TODO: обновить список задач в главном окне
            onBack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onBack() {
        // TODO: Заменить contentPane на список задач
    }
}
