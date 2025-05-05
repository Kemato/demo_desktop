package com.todo.demo.demo_desktop.api;

import com.todo.demo.demo_desktop.auth.AuthApiClient;
import com.todo.demo.demo_desktop.auth.AuthTokenHolder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;

public class HttpRequestBuilder {
    private static final AuthApiClient authApiClient = AuthApiClient.getInstance();
    private static final HttpClient client = HttpClient.newHttpClient();

    public static HttpResponse<String> buildRequest(String url, String method, String body) throws IOException, InterruptedException {
        // Проверка срока действия токена
        AuthTokenHolder tokenHolder = AuthTokenHolder.getInstance();
        if (tokenHolder.getExpireAccessToken().isBefore(LocalDateTime.now())) {
            authApiClient.refreshToken();
        }

        // Создание запроса
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + tokenHolder.getAccessToken())
                .header("Accept", "application/json");

        switch (method) {
            case "POST" -> builder.POST(HttpRequest.BodyPublishers.ofString(body));
            case "PUT" -> builder.PUT(HttpRequest.BodyPublishers.ofString(body));
            case "DELETE" -> builder.DELETE();
            default -> builder.GET();
        }

        HttpRequest request = builder.build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
