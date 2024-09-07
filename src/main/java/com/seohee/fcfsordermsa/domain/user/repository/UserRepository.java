package com.seohee.fcfsordermsa.domain.user.repository;

import com.seohee.fcfsordermsa.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    User findByEmail(String email);
}
