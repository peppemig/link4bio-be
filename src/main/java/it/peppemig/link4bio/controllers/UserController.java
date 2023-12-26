package it.peppemig.link4bio.controllers;

import it.peppemig.link4bio.dto.UserDTO;
import it.peppemig.link4bio.entity.User;
import it.peppemig.link4bio.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> saveUser(Authentication auth, @RequestBody User user) {
        UserDTO createdUser = userService.saveUser(auth.getName(), user);
        return new ResponseEntity<UserDTO>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<UserDTO> getUser(Authentication auth) {
        UserDTO user = userService.getUser(auth.getName());
        return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
    }
}
