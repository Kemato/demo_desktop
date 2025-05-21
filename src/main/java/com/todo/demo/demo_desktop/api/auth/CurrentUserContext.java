package com.todo.demo.demo_desktop.api.auth;

import com.todo.demo.demo_desktop.model.dto.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CurrentUserContext {

    private static CurrentUserContext instance;

    //todo.. Убрать мок
    private UserDTO currentUser = new UserDTO( 1L,"Федор");

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