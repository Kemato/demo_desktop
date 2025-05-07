package com.todo.demo.demo_desktop.controller;

import com.todo.demo.demo_desktop.model.dto.TaskDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDateTime;

public class TaskListController {

    @FXML
    private TableView<TaskDTO> taskTable;

    @FXML
    private TableColumn<TaskDTO, String> titleColumn;

    @FXML
    private TableColumn<TaskDTO, String> priorityColumn;

    @FXML
    private TableColumn<TaskDTO, String> assignedToColumn;

    @FXML
    private TableColumn<TaskDTO, String> shortDescriptionColumn;

    @FXML
    public void initialize() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
        assignedToColumn.setCellValueFactory(new PropertyValueFactory<>("assignedTo"));
        shortDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("shortDescription"));

        taskTable.setItems(loadDummyTasks()); // Заглушка — в будущем будет API
    }

    private ObservableList<TaskDTO> loadDummyTasks() {
        ObservableList<TaskDTO> list = FXCollections.observableArrayList();

        TaskDTO task = new TaskDTO(
                1L,
                "Задача 1",
                "Высокий",
                "В работе",
                "LOWER",
                "Назначено",
                "Автор",
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                null
        );

        for(int i = 0; i < 20;i++)list.add(task);

        return list;
    }
}
