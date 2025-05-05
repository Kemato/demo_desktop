package com.todo.demo.demo_desktop.controller;

import com.todo.demo.demo_desktop.model.dto.UserCreateDTO;
import com.todo.demo.demo_desktop.service.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AuthorizationController {

    @FXML
    private VBox loginPanel;

    @FXML
    private VBox registerPanel;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField regUsernameField;

    @FXML
    private TextField regPasswordField;

    @FXML
    private TextField regPasswordConfirmField;

    @FXML
    private HBox errorMessageInclude; // Контейнер для включенного FXML

    private Text errorText; // Поле для отображения ошибок

    @FXML
    private void initialize() {
        // Получаем доступ к errorText из включенного FXML
        errorText = (Text) errorMessageInclude.lookup("#errorText");
    }

    @FXML
    private void handleLogin(){

    }

    @FXML
    private void handleRegister() {
        if (errorText != null) {
            errorText.setText(""); // Очистка текста ошибок перед началом проверки
        }

        try {
            UserCreateDTO user = new UserCreateDTO();
            user.setName(regUsernameField.getText());

            String password = regPasswordField.getText();
            String confirmPassword = regPasswordConfirmField.getText();

            if (!password.equals(confirmPassword)) {
                throw new IllegalArgumentException("Пароли не совпадают");
            }

            if (!isPasswordValid(password)) {
                throw new IllegalArgumentException("Пароль не соответствует требованиям:\n" +
                        "Минимум 8 символов, одна цифра, одна буква и один спецсимвол");
            }

            user.setPassword(password);
//            UserService userService = new UserService();
            // userService.create(user);
            if (errorText != null) {
                errorText.setText("Регистрация успешна!");
            }
        } catch (IllegalArgumentException e) {
            if (errorText != null) {
                errorText.setText(e.getMessage()); // Отображение сообщения об ошибке в интерфейсе
            }
        } catch (Exception e) {
            if (errorText != null) {
                errorText.setText("Произошла ошибка. Попробуйте снова."); // Общая обработка ошибок
            }
        }
    }

    private boolean isPasswordValid(String password) {
        if (password.length() < 6) {
            return false; // Минимальная длина пароля — 6 символов
        }
        if (!password.matches(".*\\d.*")) {
            return false; // Должен содержать хотя бы одну цифру
        }
        if (!password.matches(".*[a-zA-Z].*")) {
            return false; // Должен содержать хотя бы одну букву
        }
        return true;
    }

    @FXML
    private void showLogin(){
        loginPanel.setVisible(true);
        registerPanel.setVisible(false);
    }

    @FXML
    private void showRegister(){
        loginPanel.setVisible(false);
        registerPanel.setVisible(true);
    }

}
