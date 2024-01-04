package it.peppemig.link4bio.service;

import it.peppemig.link4bio.dto.DetailsDTO;
import it.peppemig.link4bio.dto.InfosDTO;
import it.peppemig.link4bio.dto.PageDTO;
import it.peppemig.link4bio.dto.UserDTO;
import it.peppemig.link4bio.entity.Page;
import it.peppemig.link4bio.entity.User;
import it.peppemig.link4bio.exceptions.UnauthorizedException;
import it.peppemig.link4bio.repository.PageRepository;
import it.peppemig.link4bio.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PageRepository pageRepository;
    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, PageRepository pageRepository, CloudinaryService cloudinaryService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.pageRepository = pageRepository;
        this.cloudinaryService = cloudinaryService;
        this.modelMapper = modelMapper;
    }

    public UserDTO saveUser(String userId, User user) {
        if(!user.getId().equals(userId)) {
            throw new UnauthorizedException("Not authorized");
        }
        User newUser = userRepository.save(user);
        return modelMapper.map(newUser, UserDTO.class);
    }

    public PageDTO saveDetailsForUser(String userId, DetailsDTO details) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
        user.setBio(details.getBio());
        user.setLocation(details.getLocation());
        Page newPage = new Page();
        newPage.setUri(details.getUri());
        newPage.setUser(user);
        Page createdPage = pageRepository.save(newPage);
        return modelMapper.map(createdPage, PageDTO.class);
    }

    public String saveAvatarForUser(String userId, MultipartFile multipartFile) throws IOException {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        Map result = cloudinaryService.upload(multipartFile);
        String avatarUrl = (String) result.get("url");
        user.setAvatarUrl(avatarUrl);
        userRepository.save(user);
        return avatarUrl;
    }

    public UserDTO updateUserInfos(String userId, InfosDTO infos) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
        user.setDisplayName(infos.getDisplayName());
        user.setLocation(infos.getLocation());
        user.setBio(infos.getBio());
        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserDTO.class);
    }

    public UserDTO getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found for user id: " + userId));
        return modelMapper.map(user, UserDTO.class);
    }

    public boolean checkIfUserExists(String userId) {
        return userRepository.findById(userId).isPresent();
    }
}
