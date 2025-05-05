package com.todo.demo.demo_desktop.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.demo.demo_desktop.model.dto.UserCreateDTO;
import com.todo.demo.demo_desktop.model.dto.UserDTO;
import com.todo.demo.demo_desktop.model.dto.auth.AuthResponse;
import com.todo.demo.demo_desktop.model.dto.auth.LoginRequest;
import com.todo.demo.demo_desktop.model.dto.auth.RefreshTokenRequest;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;

public class AuthApiClient {

    private final AuthTokenHolder authTokenHolder = AuthTokenHolder.getInstance();
    private final CurrentUserContext currentUser = CurrentUserContext.getInstance();

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String BASE_URL = "http://localhost:3000/api";

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public void login(String username, String password) {
        HttpResponse<String> response = sendRequest("/login", new LoginRequest(username, password));
        handleResponse(response);
    }

    public void register(UserCreateDTO newUser) {
        HttpResponse<String> response = sendRequest("/register", newUser);
        handleResponse(response);
    }

    public void refreshToken() {
        HttpResponse<String> response = sendRequest("/refresh", new RefreshTokenRequest(authTokenHolder.getRefreshToken()));
        handleResponse(response);
    }

    public void logout(String token) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/logout"))
                    .header("Authorization", "Bearer " + authTokenHolder.getAccessToken())
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                // Успешный logout, очищаем токены
                authTokenHolder.clearTokens();
                currentUser.clearCurrentUser();
            } else {
                throw new RuntimeException("Ошибка при выходе: Код ответа " + response.statusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при выполнении logout: " + e.getMessage(), e);
        }
    }

    private HttpResponse<String> sendRequest(String endpoint, Object body) {
        try{
            String requestBody = objectMapper.writeValueAsString(body);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + endpoint))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        }
        catch (Exception e) {
            throw new RuntimeException("Ошибка при выполнении запроса: " + e.getMessage(), e);
        }
    }

    private void handleResponse(HttpResponse<String> response) {
        try {
            if (response.statusCode() == 200) {
                parseResponse(response);
            } else {
                throw new RuntimeException("Ошибка: Код ответа " + response.statusCode());
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Ошибка обработки JSON: " + e.getMessage(), e);
        }
    }

    private void parseResponse(HttpResponse<String> response) throws JsonProcessingException {
        AuthResponse tokenResponse = objectMapper.readValue(response.body(), AuthResponse.class);

        currentUser.setCurrentUser(new UserDTO(tokenResponse.getId(), tokenResponse.getUsername()));

        authTokenHolder.setAccessToken(tokenResponse.getAccessToken());
        authTokenHolder.setRefreshToken(tokenResponse.getRefreshToken());
        authTokenHolder.setExpireAccessToken(LocalDateTime.now().plusSeconds(tokenResponse.getExpiresAccessTokenIn()));
        authTokenHolder.setExpireRefreshToken(LocalDateTime.now().plusSeconds(tokenResponse.getExpiresRefreshTokenIn()));
    }
}
