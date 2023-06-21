package ucll.gip.gip4_2dezit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ucll.gip.gip4_2dezit.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;


    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        DatabaseAuthenticationProvider databaseAuthenticationProvider = new DatabaseAuthenticationProvider(userDetailsService, passwordEncoder, userService);
        auth.authenticationProvider(databaseAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/authenticate").permitAll()
                .antMatchers(HttpMethod.GET, "/users/all").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/users/addUser").permitAll()
                .antMatchers(HttpMethod.GET, "/books/**").permitAll()
                .antMatchers(HttpMethod.POST, "/books/**").hasRole("UITGEVER")
                .antMatchers(HttpMethod.PUT, "/books/**").hasRole("UITGEVER")
                .antMatchers(HttpMethod.DELETE, "/books/**").hasRole("UITGEVER")
                .antMatchers(HttpMethod.GET, "/authors/**").hasRole("UITGEVER")
                .antMatchers(HttpMethod.POST, "/authors/**").hasRole("UITGEVER")
                .antMatchers(HttpMethod.POST, "/authors/**").hasRole("UITGEVER")
                .antMatchers(HttpMethod.PUT, "/authors/**").hasRole("UITGEVER")
                .antMatchers(HttpMethod.DELETE, "/authors/**").hasRole("UITGEVER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().denyAll()
                .and()
                .httpBasic();
    }

}