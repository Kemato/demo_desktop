package com.todo.demo.demo_desktop.api.auth;

import com.todo.demo.demo_desktop.model.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentUserContext {

    private static CurrentUserContext instance;

    private UserDTO currentUser;

    private CurrentUserContext() {
    }

    public static synchronized CurrentUserContext getInstance() {
        if (instance == null) {
            instance = new CurrentUserContext();
        }
        return instance;
    }

    public void clearCurrentUser() {
        this.currentUser = null;
    }
}