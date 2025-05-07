package com.todo.demo.demo_desktop.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.demo.demo_desktop.api.auth.CurrentUserContext;
import com.todo.demo.demo_desktop.model.dto.UserDTO;
import com.todo.demo.demo_desktop.model.dto.UserUpdateDTO;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

import static com.todo.demo.demo_desktop.api.HttpRequestBuilder.buildRequest;

public class UserApiClient {
    private static UserApiClient instance;
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String BASE_URL = "http://localhost:3000/api/users";

    private UserApiClient() {}

    public static synchronized UserApiClient getInstance() {
        if (instance == null) {
            instance = new UserApiClient();
        }
        return instance;
    }

    public UserDTO getUserById(Long id) throws IOException, InterruptedException {
        HttpResponse<String> response = buildRequest(
                BASE_URL + "/" + id,
                "",
                ""
        );
        if (response.statusCode() == 200) {
            return mapper.readValue(response.body(), UserDTO.class);
        }
        else{
            throw new RuntimeException("Пользователь не найден: " + response.statusCode());
        }
    }

    public List<UserDTO> getAllUsers() throws IOException, InterruptedException {
        HttpResponse<String> response = buildRequest(BASE_URL, "", "");
        if (response.statusCode() == 200) {
            return mapper.readValue(
                    response.body(),
                    mapper.getTypeFactory().constructCollectionType(List.class, UserDTO.class)
            );
        } else {
            throw new RuntimeException("Ошибка получения пользователей: " + response.statusCode());
        }
    }

    public void updateUser(UserUpdateDTO userUpdateDTO) throws IOException, InterruptedException {
        HttpResponse<String> response = buildRequest(
                BASE_URL + "/" + CurrentUserContext.getInstance().getCurrentUser().getId(),
                "PUT",
                mapper.writeValueAsString(userUpdateDTO));

        if (response.statusCode() == 200) {
            CurrentUserContext.getInstance().setCurrentUser(mapper.readValue(response.body(), UserDTO.class));
        } else {
            throw new RuntimeException("Ошибка обновления пользователя: " + response.statusCode());
        }
    }

    public void deleteUser() throws IOException, InterruptedException {
        String uri = BASE_URL + "/" + CurrentUserContext.getInstance().getCurrentUser().getId();
        HttpResponse<String> response = buildRequest(uri, "DELETE", null);
        if (response.statusCode() == 200) {
            System.out.println("Пользователь успешно удален");
        } else {
            throw new RuntimeException("Ошибка удаления пользователя: " + response.statusCode());
        }
    }

}
