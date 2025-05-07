package com.todo.demo.demo_desktop.service;

import com.todo.demo.demo_desktop.api.TaskApiClient;
import com.todo.demo.demo_desktop.model.dto.TaskCreateDTO;
import com.todo.demo.demo_desktop.model.dto.TaskDTO;
import com.todo.demo.demo_desktop.model.dto.TaskUpdateDTO;
import com.todo.demo.demo_desktop.util.ErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private static TaskService instance;
    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);
    private static final TaskApiClient taskApiClient = TaskApiClient.getInstance();
    private TaskService() {}
    public static synchronized TaskService getInstance(){
        if (instance == null) {
            instance = new TaskService();
        }
        return instance;
    }
    public TaskDTO createTask(TaskCreateDTO taskCreateDTO) {
        try {
            return taskApiClient.createTask(taskCreateDTO);
        } catch (Exception e) {
            ErrorHandler.showError("Ошибка создания задачи: " + e.getMessage());
            return null;
        }
    }
    public TaskDTO updateTask(TaskUpdateDTO taskUpdateDTO) {
        try {
            return taskApiClient.updateTask(taskUpdateDTO);
        } catch (Exception e) {
            logger.error("Ошибка обновления задачи: {}", e.getMessage());
            ErrorHandler.showError("Ошибка обновления задачи: " + e.getMessage());
            return null;
        }
    }
    public void deleteTask(Long taskId) {
        try {
            taskApiClient.deleteTask(taskId);
        } catch (Exception e) {
            logger.error("Ошибка при удалении задачи: {}", e.getMessage());
            ErrorHandler.showError("Ошибка при удалении задачи: "+e.getMessage());
        }
    }
    public TaskDTO getTaskById(Long taskId) {
        try {
            return taskApiClient.getTaskById(taskId);
        } catch (Exception e) {
            logger.error("Ошибка получения задачи: {}", e.getMessage());
            ErrorHandler.showError("Ошибка получения задачи: " + e.getMessage());
            return null;
        }
    }
    public List<TaskDTO> getAllTasks() {
        try {
            return taskApiClient.getAllTasks();
        } catch (Exception e) {
            logger.error(e.getMessage());
            ErrorHandler.showError(e.getMessage());
            return new ArrayList<>();
        }
    }

}
