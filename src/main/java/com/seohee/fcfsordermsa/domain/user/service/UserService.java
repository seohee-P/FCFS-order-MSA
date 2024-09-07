package com.seohee.fcfsordermsa.domain.user.service;

import com.seohee.fcfsordermsa.domain.user.dto.UserResponseDto;
import com.seohee.fcfsordermsa.domain.user.dto.UserSignupRequestDto;
import com.seohee.fcfsordermsa.domain.user.entity.User;
import com.seohee.fcfsordermsa.domain.user.repository.UserRepository;
import com.seohee.fcfsordermsa.global.exception.DuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import static com.seohee.fcfsordermsa.global.exception.ExceptionCode.DUPLICATED_USER_EMAIL;
import static com.seohee.fcfsordermsa.global.exception.ExceptionCode.DUPLICATED_USER_PHONE_NUMBER;

@RequiredArgsConstructor
@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserResponseDto signup(@RequestBody UserSignupRequestDto requestDto) {

        checkDuplicatedEmail(requestDto.getEmail());
        checkDuplicatedPhoneNumber(requestDto.getPhoneNumber());

        String password = passwordEncoder.encode(requestDto.getPassword());

        User user = requestDto.toEntity(password);

        userRepository.save(user);

        return new UserResponseDto(user);
    }

    private void checkDuplicatedEmail(String email) {

        if (userRepository.existsByEmail(email)) {
            throw new DuplicateException(DUPLICATED_USER_EMAIL);
        }
    }

    private void checkDuplicatedPhoneNumber(String phoneNumber) {

        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new DuplicateException(DUPLICATED_USER_PHONE_NUMBER);
        }
    }
}
