package org.example.kotiki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class KotikiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KotikiApplication.class, args);
    }

}
