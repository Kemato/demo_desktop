package com.todo.demo.demo_desktop.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.demo.demo_desktop.auth.AuthTokenHolder;
import com.todo.demo.demo_desktop.model.dto.TaskCreateDTO;
import com.todo.demo.demo_desktop.model.dto.TaskDTO;
import com.todo.demo.demo_desktop.model.dto.TaskUpdateDTO;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static com.todo.demo.demo_desktop.api.HttpRequestBuilder.buildRequest;

public class TaskApiClient {

    private static TaskApiClient instance;
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String BASE_URL = "http://localhost:3000/api/tasks";
    private static final String TOKEN = AuthTokenHolder.getInstance().getAccessToken();
    private static final HttpClient client = HttpClient.newHttpClient();

    private TaskApiClient() {
    }

    public static synchronized TaskApiClient getInstance() {
        if (instance == null) {
            instance = new TaskApiClient();
        }
        return instance;
    }

    public void createTask(TaskCreateDTO taskCreateDTO) throws IOException, InterruptedException {
        HttpResponse<String> response = buildRequest(
                BASE_URL,
                "POST",
                mapper.writeValueAsString(taskCreateDTO)
        );
        if (response.statusCode() != 201) {
            throw new RuntimeException("Ошибка создания задачи: " + response.statusCode());
        }
    }

    public void updateTask(TaskUpdateDTO taskUpdateDTO) throws IOException, InterruptedException {
        HttpResponse<String> response = buildRequest(
                BASE_URL + "/" + taskUpdateDTO.getId(),
                "PUT",
                mapper.writeValueAsString(taskUpdateDTO)
        );
        if (response.statusCode() != 200) {
            throw new RuntimeException("Ошибка обновления задачи: " + response.statusCode());
        }
    }

    public void deleteTask(Long taskId) throws IOException, InterruptedException {
        HttpResponse<String> response = buildRequest(
                BASE_URL + "/" + taskId,
                "DELETE",
                ""
        );
        if (response.statusCode() != 204) {
            throw new RuntimeException("Ошибка удаления задачи: " + response.statusCode());
        }
    }

    public TaskDTO getTaskById(Long taskId) throws IOException, InterruptedException {
        HttpResponse<String> response = buildRequest(
                BASE_URL + "/" + taskId,
                "GET",
                ""
        );
        if (response.statusCode() == 200) {
            return mapper.readValue(response.body(), TaskDTO.class);
        } else {
            throw new RuntimeException("Ошибка получения задачи: " + response.statusCode());
        }
    }

    public List<TaskDTO> getAllTasks() throws IOException, InterruptedException {
        HttpResponse<String> response = buildRequest(
                BASE_URL,
                "GET",
                ""
        );
        if (response.statusCode() == 200) {
            return mapper.readValue(
                    response.body(),
                    mapper.getTypeFactory().constructCollectionType(List.class, TaskDTO.class)
            );
        } else {
            throw new RuntimeException("Ошибка получения задач: " + response.statusCode());
        }
    }
}
