package hello;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"hello", "jpa.config.java", "security.config.java"})
@EnableAutoConfiguration(exclude=HibernateJpaAutoConfiguration.class)
public class Application {

    public static void main(String[] args) {
        /* How to encrypt the password
        String password = "plaintextPassword";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        */
        SpringApplication.run(Application.class, args);
    }
}
