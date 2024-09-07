package com.seohee.fcfsordermsa.domain.user.dto;

import com.seohee.fcfsordermsa.domain.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {

    private Long id;

    private String name;

    private String email;

    private LocalDateTime createdAt;

    public UserResponseDto (User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
    }
}