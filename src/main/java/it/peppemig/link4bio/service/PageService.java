package it.peppemig.link4bio.service;

import it.peppemig.link4bio.entity.Page;
import it.peppemig.link4bio.entity.User;
import it.peppemig.link4bio.repository.PageRepository;
import it.peppemig.link4bio.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PageService {
    private final PageRepository pageRepository;
    private final UserRepository userRepository;

    public PageService(PageRepository pageRepository, UserRepository userRepository) {
        this.pageRepository = pageRepository;
        this.userRepository = userRepository;
    }

    public Page savePageForUser(String userId, Page page) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
        page.setUser(user);
        return pageRepository.save(page);
    }

    public Page findPageByUserId(String userId) {
        return pageRepository.findByUserId(userId).orElseThrow(() -> new EntityNotFoundException("Page not found for user with id: " + userId));
    }

    public Page findPageByUri(String uri) {
        return pageRepository.findByUri(uri).orElseThrow(() -> new EntityNotFoundException("Page not found for uri: " + uri));
    }
}
