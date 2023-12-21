package it.peppemig.link4bio.service;

import it.peppemig.link4bio.dto.PageDTO;
import it.peppemig.link4bio.entity.Page;
import it.peppemig.link4bio.entity.User;
import it.peppemig.link4bio.repository.PageRepository;
import it.peppemig.link4bio.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PageService {
    private final PageRepository pageRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public PageService(PageRepository pageRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.pageRepository = pageRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public PageDTO savePageForUser(String userId, Page page) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
        page.setUser(user);
        Page createdPage = pageRepository.save(page);
        return modelMapper.map(createdPage, PageDTO.class);
    }

    public PageDTO findPageByUserId(String userId) {
        Page page = pageRepository.findByUserId(userId).orElseThrow(() -> new EntityNotFoundException("Page not found for user with id: " + userId));
        return modelMapper.map(page, PageDTO.class);
    }

    public PageDTO findPageByUri(String uri) {
        Page page = pageRepository.findByUri(uri).orElseThrow(() -> new EntityNotFoundException("Page not found for uri: " + uri));
        return modelMapper.map(page, PageDTO.class);
    }
}
