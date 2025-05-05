package com.todo.demo.demo_desktop.auth;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AuthTokenHolder {

    private static AuthTokenHolder instance;

    private AuthTokenHolder(){};

    public static synchronized AuthTokenHolder getInstance() {
        if (instance == null) {
            instance = new AuthTokenHolder();
        }
        return instance;
    }

    public void clearTokens(){
        this.accessToken = null;
        this.refreshToken = null;
        this.expireAccessToken = null;
        this.expireRefreshToken = null;
    }

    private String accessToken;
    private String refreshToken;
    private LocalDateTime expireAccessToken;
    private LocalDateTime expireRefreshToken;
}
