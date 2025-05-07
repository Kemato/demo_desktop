package com.todo.demo.demo_desktop.controller;

import com.todo.demo.demo_desktop.model.dto.TaskDTO;
import com.todo.demo.demo_desktop.model.dto.TaskUpdateDTO;
import com.todo.demo.demo_desktop.model.dto.UserDTO;
import com.todo.demo.demo_desktop.service.TaskService;
import com.todo.demo.demo_desktop.service.UserService;
import com.todo.demo.demo_desktop.util.FieldChangeListener;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import jfxtras.scene.control.LocalTimePicker;
import lombok.Setter;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class TaskDetailController implements Initializable {
    @FXML private TextField titleField;
    FieldChangeListener titleListener = new FieldChangeListener(titleField);
    @FXML private TextArea descriptionArea;
    FieldChangeListener descriptionListener = new FieldChangeListener(descriptionArea);
    @FXML private ComboBox<String> statusBox;
    FieldChangeListener statusListener = new FieldChangeListener(statusBox);
    @FXML private ComboBox<String> priorityBox;
    FieldChangeListener priorityListener = new FieldChangeListener(priorityBox);
    @FXML private ComboBox<UserDTO> assigneeBox;
    FieldChangeListener assigneeListener = new FieldChangeListener(assigneeBox);
    @FXML public DatePicker deadlineDatePicker;
    FieldChangeListener deadlineDateListener = new FieldChangeListener(deadlineDatePicker);
    @FXML public LocalTimePicker deadlineTimePicker;
    FieldChangeListener deadlineTimeListener = new FieldChangeListener(deadlineTimePicker);
    @FXML public CheckBox finishedCheck;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Настройка слушателя для проверки введённого текста в ComboBox
        assigneeBox.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if (!allUsersNames.contains(newValue)) {
                assigneeBox.getEditor().setText(oldValue);
            }
        });

        // Заполнение ComboBox списком пользователей
        assigneeBox.setItems(FXCollections.observableArrayList(allUsers));
    }

    private final List<UserDTO> allUsers = UserService.getInstance().getAllUsers();
    private final List<String> allUsersNames = allUsers.stream().map(UserDTO::getName).toList();

    private final TaskService taskService = TaskService.getInstance();

    private TaskDTO task;

    @Setter
    private Pane contentPane;

    public void setTask(TaskDTO task) {
        this.task = task;
        if (task != null) {
            titleField.setText(task.getTitle());
            descriptionArea.setText(task.getDescription());
            priorityBox.setValue(task.getPriority());
            statusBox.setValue(task.getStatus());
            assigneeBox.setValue(allUsers.stream().filter(user -> user.getName().equals(task.getAssignee())).findFirst().get());
            deadlineDatePicker.setValue(task.getDeadline().toLocalDate());
            deadlineTimePicker.setLocalTime(task.getDeadline().toLocalTime());
        }
    }

    @FXML
    public void onSave() {
        if (task == null) return;

        TaskUpdateDTO dto = new TaskUpdateDTO();
        dto.setId(task.getId());
        if(titleListener.isChanged())dto.setTitle(Optional.of(titleField.getText()));
        if(descriptionListener.isChanged())dto.setDescription(Optional.of(descriptionArea.getText()));
        if(statusListener.isChanged())dto.setStatus(Optional.of(statusBox.getValue()));
        if(priorityListener.isChanged())dto.setPriority(Optional.of(priorityBox.getValue()));
        if(deadlineDateListener.isChanged() | deadlineTimeListener.isChanged())
            dto.setDeadline(Optional.of(LocalDateTime.of(deadlineDatePicker.getValue(),deadlineTimePicker.getLocalTime())));

        if (finishedCheck.isSelected()) {
            dto.setStatus(Optional.of("FINISHED"));
            dto.setDateFinished(Optional.of(LocalDateTime.now()));
        }

        taskService.updateTask(dto);

        // TODO: обновить UI после сохранения
        onBack();
    }

    @FXML
    public void onDelete() {
        if (task == null) return;

        taskService.deleteTask(task.getId());

        // TODO: обновить список задач
        onBack();
    }

    @FXML
    public void onBack() {
        // TODO: заменить contentPane на таблицу задач
    }
}
