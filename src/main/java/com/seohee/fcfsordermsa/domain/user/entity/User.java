package com.seohee.fcfsordermsa.domain.user.entity;

import com.seohee.fcfsordermsa.domain.common.BaseEntity;
import com.seohee.fcfsordermsa.domain.user.converter.EncryptionConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Convert(converter = EncryptionConverter.class)
    private String name;

    @Convert(converter = EncryptionConverter.class)
    private String email;

    @Convert(converter = EncryptionConverter.class)
    private String phoneNumber;

    @Convert(converter = EncryptionConverter.class)
    private String address;

    private String password;

    @Builder
    public User(String name, String email, String password, String phoneNumber, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
