package it.peppemig.link4bio.service;

import it.peppemig.link4bio.dto.ButtonDTO;
import it.peppemig.link4bio.entity.Button;
import it.peppemig.link4bio.entity.Page;
import it.peppemig.link4bio.exceptions.UnauthorizedException;
import it.peppemig.link4bio.repository.ButtonRepository;
import it.peppemig.link4bio.repository.PageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ButtonService {
    private final ButtonRepository buttonRepository;
    private final PageRepository pageRepository;
    private final ModelMapper modelMapper;

    public ButtonService(ButtonRepository buttonRepository, PageRepository pageRepository, ModelMapper modelMapper) {
        this.buttonRepository = buttonRepository;
        this.pageRepository = pageRepository;
        this.modelMapper = modelMapper;
    }

    public ButtonDTO saveButtonToPage(String userId, Long pageId, Button button) {
        Page page = pageRepository.findById(pageId).orElseThrow(() -> new EntityNotFoundException("Page not found with ID: " + pageId));
        if (!page.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Not authorized");
        }
        button.setPage(page);
        Button newButton = buttonRepository.save(button);
        return modelMapper.map(newButton, ButtonDTO.class);
    }

    public ButtonDTO updateButton(String userId, Long buttonId, Button button) {
        Button existingButton = buttonRepository.findById(buttonId).orElseThrow(() -> new EntityNotFoundException("Button not found with ID: " + buttonId));
        if (!existingButton.getPage().getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Not authorized");
        }
        existingButton.setName(button.getName());
        existingButton.setUrl(button.getUrl());
        Button updatedButton = buttonRepository.save(existingButton);
        return modelMapper.map(updatedButton, ButtonDTO.class);
    }

    public void deleteButtonById(String userId, Long buttonId) {
        Button button = buttonRepository.findById(buttonId).orElseThrow(() -> new EntityNotFoundException("Button not found with ID: " + buttonId));
        if (!button.getPage().getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Not authorized");
        }
        buttonRepository.deleteById(buttonId);
    }
}
