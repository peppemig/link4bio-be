package it.peppemig.link4bio.service;

import it.peppemig.link4bio.dto.PageDTO;
import it.peppemig.link4bio.dto.ThemeDTO;
import it.peppemig.link4bio.entity.Page;
import it.peppemig.link4bio.entity.Theme;
import it.peppemig.link4bio.exceptions.UnauthorizedException;
import it.peppemig.link4bio.repository.PageRepository;
import it.peppemig.link4bio.repository.ThemeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ThemeService {
    private final ThemeRepository themeRepository;
    private final PageRepository pageRepository;
    private final ModelMapper modelMapper;

    public ThemeService(ThemeRepository themeRepository, PageRepository pageRepository, ModelMapper modelMapper) {
        this.themeRepository = themeRepository;
        this.pageRepository = pageRepository;
        this.modelMapper = modelMapper;
    }

    public ThemeDTO saveThemeToPage(String userId, String uri, Theme theme) {
        Page page = pageRepository.findByUri(uri).orElseThrow(() -> new EntityNotFoundException("Page not found with URI: " + uri));
        if (!page.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Not authorized");
        }
        page.setTheme(theme);
        Theme newTheme = themeRepository.save(theme);
        Page updatedPage = pageRepository.save(page);
        return modelMapper.map(newTheme, ThemeDTO.class);
    }
}
