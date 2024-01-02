package it.peppemig.link4bio.controllers;

import it.peppemig.link4bio.dto.DetailsDTO;
import it.peppemig.link4bio.dto.InfosDTO;
import it.peppemig.link4bio.dto.PageDTO;
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

    @PostMapping("/details")
    public ResponseEntity<PageDTO> saveDetailsForUser(Authentication auth, @RequestBody DetailsDTO details) {
        PageDTO newPageWithDetails = userService.saveDetailsForUser(auth.getName(), details);
        return new ResponseEntity<PageDTO>(newPageWithDetails, HttpStatus.CREATED);
    }

    @PutMapping("/infos")
    public ResponseEntity<UserDTO> updateUserInfos(Authentication auth, @RequestBody InfosDTO infos) {
        UserDTO updatedUser = userService.updateUserInfos(auth.getName(), infos);
        return new ResponseEntity<UserDTO>(updatedUser, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<UserDTO> getUser(Authentication auth) {
        UserDTO user = userService.getUser(auth.getName());
        return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkIfUserExists(Authentication auth) {
        Boolean userExists = userService.checkIfUserExists(auth.getName());
        return new ResponseEntity<Boolean>(userExists, HttpStatus.OK);
    }
}
