package io.codingtest.springboot.payload.response;

public class MessageResponse {
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    public MessageResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}