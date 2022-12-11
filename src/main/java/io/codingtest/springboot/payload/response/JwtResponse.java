package io.codingtest.springboot.payload.response;

import java.util.List;

public class JwtResponse {

    private Long id;
    private String username;
    private List<String> roles;
    private String accessToken;

    public JwtResponse(Long id, String username, List<String> roles, String accessToken) {
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.accessToken = accessToken;
    }

    public JwtResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
