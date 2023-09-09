package com.sneha.usersignupsignin.dto;

public class UserResponseDTO {
    private Integer userId;
    private String key;

    public UserResponseDTO(Integer userId, String key) {
        this.userId = userId;
        this.key = key;
    }

    // Getters and setters for userId and key
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
