package it.peppemig.link4bio.service;

import it.peppemig.link4bio.dto.UserDTO;
import it.peppemig.link4bio.entity.User;
import it.peppemig.link4bio.exceptions.UnauthorizedException;
import it.peppemig.link4bio.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserDTO saveUser(String userId, User user) {
        if(!user.getId().equals(userId)) {
            throw new UnauthorizedException("Not authorized");
        }
        User newUser = userRepository.save(user);
        return modelMapper.map(newUser, UserDTO.class);
    }
}
