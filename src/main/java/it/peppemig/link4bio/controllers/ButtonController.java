package it.peppemig.link4bio.controllers;

import it.peppemig.link4bio.dto.ButtonDTO;
import it.peppemig.link4bio.entity.Button;
import it.peppemig.link4bio.service.ButtonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buttons")
public class ButtonController {
    private final ButtonService buttonService;

    public ButtonController(ButtonService buttonService) {
        this.buttonService = buttonService;
    }

    @PostMapping("/{uri}")
    public ResponseEntity<List<ButtonDTO>> saveButtonsToPage(Authentication auth, @PathVariable String uri, @RequestBody List<Button> buttons) {
        List<ButtonDTO> createdButtons = buttonService.saveButtonsToPage(auth.getName(), uri, buttons);
        return new ResponseEntity<List<ButtonDTO>>(createdButtons, HttpStatus.CREATED);
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
