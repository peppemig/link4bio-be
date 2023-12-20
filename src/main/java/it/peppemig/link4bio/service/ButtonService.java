package it.peppemig.link4bio.service;

import it.peppemig.link4bio.entity.Button;
import it.peppemig.link4bio.entity.Page;
import it.peppemig.link4bio.exceptions.UnauthorizedException;
import it.peppemig.link4bio.repository.ButtonRepository;
import it.peppemig.link4bio.repository.PageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ButtonService {
    private final ButtonRepository buttonRepository;
    private final PageRepository pageRepository;

    public ButtonService(ButtonRepository buttonRepository, PageRepository pageRepository) {
        this.buttonRepository = buttonRepository;
        this.pageRepository = pageRepository;
    }

    public Button saveButtonToPage(String userId, Long pageId, Button button) {
        Page page = pageRepository.findById(pageId).orElseThrow(() -> new EntityNotFoundException("Page not found with ID: " + pageId));
        if (!page.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Not authorized");
        }
        button.setPage(page);
        return buttonRepository.save(button);
    }

    public void deleteButtonById(String userId, Long buttonId) {
        Button button = buttonRepository.findById(buttonId).orElseThrow(() -> new EntityNotFoundException("Button not found with ID: " + buttonId));
        if (!button.getPage().getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Not authorized");
        }
        buttonRepository.deleteById(buttonId);
    }
}
