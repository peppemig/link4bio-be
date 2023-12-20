package it.peppemig.link4bio.controllers;

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
    public ResponseEntity<Button> saveButtonToPage(Authentication auth, @PathVariable Long pageId, @RequestBody Button button) {
        Button createdButton = buttonService.saveButtonToPage(auth.getName(), pageId, button);
        return new ResponseEntity<Button>(createdButton, HttpStatus.CREATED);
    }

    @DeleteMapping("/{buttonId}")
    public ResponseEntity<?> deleteButtonById(Authentication auth, @PathVariable Long buttonId) {
        buttonService.deleteButtonById(auth.getName(), buttonId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
