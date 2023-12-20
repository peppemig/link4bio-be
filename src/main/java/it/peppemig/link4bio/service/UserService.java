package it.peppemig.link4bio.service;

import it.peppemig.link4bio.entity.User;
import it.peppemig.link4bio.exceptions.UnauthorizedException;
import it.peppemig.link4bio.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(String userId, User user) {
        if(!user.getId().equals(userId)) {
            throw new UnauthorizedException("Not authorized");
        }
        return userRepository.save(user);
    }
}
