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

import java.util.ArrayList;
import java.util.List;

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

    public List<ButtonDTO> saveButtonsToPage(String userId, String uri, List<Button> buttons) {
        Page page = pageRepository.findByUri(uri).orElseThrow(() -> new EntityNotFoundException("Page not found with URI: " + uri));
        if (!page.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Not authorized");
        }
        List<ButtonDTO> savedButtons = new ArrayList<>();
        for (Button button : buttons) {
            button.setPage(page);
            Button newButton = buttonRepository.save(button);
            savedButtons.add(modelMapper.map(newButton, ButtonDTO.class));
        }
        return savedButtons;
    }

    public ButtonDTO updateButton(String userId, Long buttonId, Button button) {
        Button existingButton = buttonRepository.findById(buttonId).orElseThrow(() -> new EntityNotFoundException("Button not found with ID: " + buttonId));
        if (!existingButton.getPage().getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Not authorized");
        }
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
