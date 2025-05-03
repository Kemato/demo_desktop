package com.todo.demo.demo_desktop.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.todo.demo.demo_desktop.model.UserDTO;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class UserService {

    private static final String BASE_URL = "http://localhost:8080/api/user";
    private static final String TOKEN = "your_auth_token_here"; // 🔐 вставь свой токен

    private final HttpClient client;
    private final ObjectMapper objectMapper;

    public UserService() {
        this.client = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public List<UserDTO> getAllUsers() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Authorization", "Bearer " + TOKEN)
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return objectMapper.readValue(response.body(), new TypeReference<>() {});
        } else {
            throw new RuntimeException("Ошибка получения пользователей: " + response.statusCode());
        }
    }

    public UserDTO getUserById(Long id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .header("Authorization", "Bearer " + TOKEN)
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return objectMapper.readValue(response.body(), UserDTO.class);
        } else {
            throw new RuntimeException("Пользователь не найден: " + response.statusCode());
        }
    }
}
