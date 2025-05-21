package com.todo.demo.demo_desktop.controller;

import com.todo.demo.demo_desktop.model.dto.TaskDTO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskListController {

    @FXML private  VBox taskContainer;

    @Setter
    private MainController mainController;

    @FXML
    public void initialize() {
        loadTasks();
    }

    private void loadTasks() {
        // Здесь можно получить список задач, пока тестовый список:
        List<TaskDTO> tasks = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            TaskDTO task = new TaskDTO();
            task.setId((long)i);
            task.setTitle("Task " + (i+1));
            task.setDescription("Task " + (i+1)+ " Task");
            task.setPriority("Medium Rare");
            tasks.add(task);
        }

        for (TaskDTO task : tasks) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/todo/demo/demo_desktop/view/component/task_item.fxml"));
                Node node = loader.load();
                TaskItemController controller = loader.getController();
                controller.setTask(task);

                // Можно добавить слушатель на клик:
                node.setOnMouseClicked(event -> mainController.showTaskDetail(task));

                taskContainer.getChildren().add(node);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}