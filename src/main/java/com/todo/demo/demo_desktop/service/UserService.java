package com.todo.demo.demo_desktop.service;

import com.todo.demo.demo_desktop.api.UserApiClient;
import com.todo.demo.demo_desktop.api.auth.CurrentUserContext;
import com.todo.demo.demo_desktop.model.dto.UserDTO;
import com.todo.demo.demo_desktop.model.dto.UserUpdateDTO;
import com.todo.demo.demo_desktop.util.ErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class UserService {
    private static UserService instance;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private static final UserApiClient userApiClient = UserApiClient.getInstance();
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
            logger.error("Ошибка получения пользователя: {}", e.getMessage());
            ErrorHandler.showError("Ошибка получения пользователя"+e.getMessage());
            return null;
        }
    }

    public List<UserDTO> getAllUsers() {
        // fixme.. Убрать мок
        List<UserDTO> users = new ArrayList<>();
        UserDTO user1 = new UserDTO();
        user1.setId(1L);
        user1.setName("Иван Иванов");
        users.add(user1);

        UserDTO user2 = new UserDTO();
        user2.setId(2L);
        user2.setName("Мария Петрова");
        users.add(user2);

        return users;

//        try {
//            return userApiClient.getAllUsers();
//        } catch (IOException | InterruptedException e) {
//            logger.error("Ошибка получения пользователей: {}", e.getMessage());
//            ErrorHandler.showError("Ошибка получения пользователей: " + e.getMessage());
//            return new ArrayList<>();
//        }
    }

    public UserDTO updateUser(UserUpdateDTO userUpdateDTO) {
        try {
            userApiClient.updateUser(userUpdateDTO);
        } catch (IOException | InterruptedException e) {
            logger.error("Ошибка обновления пользователя: {}", e.getMessage());
            ErrorHandler.showError("Ошибка обновления пользователя: " + e.getMessage());
        }
        return CurrentUserContext.getInstance().getCurrentUser();
    }

    public void deleteUser() {
        try {
            userApiClient.deleteUser();
        } catch (IOException | InterruptedException e) {
            logger.error("Ошибка удаления пользователя: {}", e.getMessage());
            ErrorHandler.showError("Ошибка удаления пользователя: " + e.getMessage());
        }
    }
}
