package ucll.gip.gip4_2dezit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ucll.gip.gip4_2dezit.model.User;
import ucll.gip.gip4_2dezit.requests.CreateUserRequest;
import ucll.gip.gip4_2dezit.service.UserAllreadyExistsException;
import ucll.gip.gip4_2dezit.service.UserPasswordEmptyException;
import ucll.gip.gip4_2dezit.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/addUser")
    public ResponseEntity<Object> createUser(@RequestBody CreateUserRequest userRequest) {
        try {
            userService.createUser(userRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.findUserByUsername(userRequest.getName()));
        } catch (UserPasswordEmptyException e){
            return ResponseEntity.badRequest().body("Password cannot be empty");
        } catch (UserAllreadyExistsException e){
            return ResponseEntity.badRequest().body("User with username " + userRequest.getName() + " already exists.");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User user = userService.getUserById(id);
        if (user != null) {
            // Update the user with the provided data
            user.setName(updatedUser.getName());
            user.setAddress(updatedUser.getAddress());
            user.setContactInformation(updatedUser.getContactInformation());
            userService.updateUser(user);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/

    /*@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/
}