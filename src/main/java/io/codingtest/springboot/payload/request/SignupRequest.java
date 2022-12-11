package io.codingtest.springboot.payload.request;

import java.util.List;

public class SignupRequest {

    private String email;
    private String password;

    private List<String> roles;

    public SignupRequest(String email, String password,List<String> roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public SignupRequest() {
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
