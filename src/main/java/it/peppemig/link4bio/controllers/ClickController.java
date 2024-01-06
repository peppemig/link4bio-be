package it.peppemig.link4bio.controllers;

import it.peppemig.link4bio.dto.ClickDTO;
import it.peppemig.link4bio.service.ClickService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clicks")
public class ClickController {
    private final ClickService clickService;

    public ClickController(ClickService clickService) {
        this.clickService = clickService;
    }

    @PostMapping("/{linkId}")
    public ResponseEntity<ClickDTO> saveClickToLink(@PathVariable Long linkId) {
        ClickDTO click = clickService.saveClickToLink(linkId);
        return new ResponseEntity<ClickDTO>(click, HttpStatus.OK);
    }
}
