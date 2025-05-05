package com.todo.demo.demo_desktop.model.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private long expiresAccessTokenIn;
    private long expiresRefreshTokenIn;
    private long id;
    private String username;
}
