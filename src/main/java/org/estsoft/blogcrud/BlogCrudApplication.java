package org.estsoft.blogcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BlogCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogCrudApplication.class, args);
    }

}
