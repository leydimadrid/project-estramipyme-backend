package com.project_estramipyme_backend.Security.models;

public class AuthResponse {
    private String token;

    // Constructor que inicializa el token
    public AuthResponse(String token) {
        this.token = token;
    }

    // Getter para el token
    public String getToken() {
        return token;
    }
}
