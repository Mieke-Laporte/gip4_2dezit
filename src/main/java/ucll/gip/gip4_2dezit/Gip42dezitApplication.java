package ucll.gip.gip4_2dezit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

@SpringBootApplication
public class Gip42dezitApplication {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new SCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(Gip42dezitApplication.class, args);
    }

}
