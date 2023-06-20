package ucll.gip.gip4_2dezit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ucll.gip.gip4_2dezit.model.Role;
import ucll.gip.gip4_2dezit.model.User;
import ucll.gip.gip4_2dezit.repository.UserRepository;
import ucll.gip.gip4_2dezit.dtos.UserDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(UserDTO userDTO) {
        if (userDTO.getRawPassword().isEmpty() || userDTO.getRawPassword().strip().equals("")){
            throw new UserPasswordEmptyException();
        }
        if (findUserByUsername(userDTO.getName()).isPresent()){
            throw new UserAllreadyExistsException();
        }
        String password = userDTO.getRawPassword();
        String encodedPassword = passwordEncoder.encode(password);
        User user = userDTO.toUser(Role.UITGEVER); //role admin needs to be added via the database itself
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public User getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }

    public Optional<User> findUserByUsername(String username){
        return userRepository.findByName(username);
    }

    public List<User> getAllUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).toList();
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}