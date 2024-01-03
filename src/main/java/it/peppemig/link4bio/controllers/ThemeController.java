package it.peppemig.link4bio.controllers;

import it.peppemig.link4bio.dto.PageDTO;
import it.peppemig.link4bio.dto.ThemeDTO;
import it.peppemig.link4bio.entity.Theme;
import it.peppemig.link4bio.service.ThemeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/themes")
public class ThemeController {
    private final ThemeService themeService;

    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @PostMapping("/{uri}")
    public ResponseEntity<ThemeDTO> saveThemeToPage(Authentication auth, @PathVariable String uri, @RequestBody Theme theme) {
        ThemeDTO createdTheme = themeService.saveThemeToPage(auth.getName(), uri, theme);
        return new ResponseEntity<ThemeDTO>(createdTheme, HttpStatus.CREATED);
    }
}
