package com.project_estramipyme_backend.Security.models;

public class AuthResponse {
    private String token;


    public AuthResponse(String token) {
        this.token = token;
    }


    public String getToken() {
        return token;
    }
}
