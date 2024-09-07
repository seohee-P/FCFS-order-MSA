package com.seohee.fcfsordermsa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FcfsOrderMsaApplication {

    public static void main(String[] args) {
        SpringApplication.run(FcfsOrderMsaApplication.class, args);
    }

}
