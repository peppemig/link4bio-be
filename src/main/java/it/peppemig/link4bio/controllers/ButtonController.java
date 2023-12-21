package it.peppemig.link4bio.controllers;

import it.peppemig.link4bio.dto.ButtonDTO;
import it.peppemig.link4bio.entity.Button;
import it.peppemig.link4bio.service.ButtonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/buttons")
public class ButtonController {
    private final ButtonService buttonService;

    public ButtonController(ButtonService buttonService) {
        this.buttonService = buttonService;
    }

    @PostMapping("/{pageId}")
    public ResponseEntity<ButtonDTO> saveButtonToPage(Authentication auth, @PathVariable Long pageId, @RequestBody Button button) {
        ButtonDTO createdButton = buttonService.saveButtonToPage(auth.getName(), pageId, button);
        return new ResponseEntity<ButtonDTO>(createdButton, HttpStatus.CREATED);
    }

    @PutMapping("/{buttonId}")
    public ResponseEntity<ButtonDTO> updateButtonById(Authentication auth, @PathVariable Long buttonId, @RequestBody Button button) {
        ButtonDTO updatedButton = buttonService.updateButton(auth.getName(), buttonId, button);
        return new ResponseEntity<ButtonDTO>(updatedButton, HttpStatus.OK);
    }

    @DeleteMapping("/{buttonId}")
    public ResponseEntity<?> deleteButtonById(Authentication auth, @PathVariable Long buttonId) {
        buttonService.deleteButtonById(auth.getName(), buttonId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
