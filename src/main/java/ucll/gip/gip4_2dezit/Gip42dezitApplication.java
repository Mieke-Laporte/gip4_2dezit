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
    public PasswordEncoder passwordEncoder() {
        return new SCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(Gip42dezitApplication.class, args);

        //Use this to encript a password to put in the database for an admin
        //IMPORTANT: admins are not created with the users endpoint, they need to be manually added to the database, otherwise anyone can make an admin account
        /*Gip42dezitApplication application = new Gip42dezitApplication();
        String password = "adminpass";
        application.encodePasswordAndPrint(password);*/
    }

    public void encodePasswordAndPrint(String password) {
        PasswordEncoder passwordEncoder = passwordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        System.out.println("Encoded Password: " + encodedPassword);
    }
}
