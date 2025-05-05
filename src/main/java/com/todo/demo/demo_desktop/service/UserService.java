package com.todo.demo.demo_desktop.service;

import com.todo.demo.demo_desktop.api.UserApiClient;
import com.todo.demo.demo_desktop.model.dto.UserDTO;
import com.todo.demo.demo_desktop.model.dto.UserUpdateDTO;

import java.io.IOException;
import java.util.List;

public class UserService {
    private static UserService instance;
    private static UserApiClient userApiClient = UserApiClient.getInstance();
    private UserService() {
    }
    public static synchronized UserService getInstance(){
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public UserDTO getUserById(Long id) {
        try {
            return userApiClient.getUserById(id);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Ошибка получения пользователя: " + e.getMessage());
        }
    }

    public List<UserDTO> getAllUsers() {
        try {
            return userApiClient.getAllUsers();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Ошибка получения пользователей: " + e.getMessage());
        }
    }

    public void updateUser(UserUpdateDTO userUpdateDTO) {
        try {
            userApiClient.updateUser(userUpdateDTO);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Ошибка обновления пользователя: " + e.getMessage());
        }
    }

    public void deleteUser() {
        try {
            userApiClient.deleteUser();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Ошибка удаления пользователя: " + e.getMessage());
        }
    }
}
