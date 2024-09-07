package com.seohee.fcfsordermsa.domain.user.dto;

import com.seohee.fcfsordermsa.domain.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserSignupRequestDto {

    private String name;

    @Email(message = "이메일 형식에 맞춰 입력해주세요.")
    private String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$",
            message = "비밀번호는 최소 8자 이상, 최대 15자 이하이며, 알파벳 대소문자, 숫자, 특수문자를 포함해야 합니다.")
    @NotBlank
    private String password;

    private String phoneNumber;

    private String address;

    public User toEntity(String encryptedPassword){

        return User.builder()
                .name(name)
                .password(encryptedPassword)
                .email(email)
                .address(address)
                .phoneNumber(phoneNumber)
                .build();
    }

}
