package ucll.gip.gip4_2dezit.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import ucll.gip.gip4_2dezit.model.User;
import ucll.gip.gip4_2dezit.service.UserService;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Stream;

public class DatabaseAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    public DatabaseAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, UserService userService) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Optional<User> user = userService.findUserByUsername(authentication.getName());
        if (user.isPresent()){
            if (passwordEncoder.matches(authentication.getCredentials().toString(), user.get().getPassword())){
                String roleName = "ROLE_" + user.get().getRole().name();
                return new UsernamePasswordAuthenticationToken(authentication.getName(),
                        authentication.getCredentials(),
                        Collections.singletonList(new SimpleGrantedAuthority(roleName)));
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
