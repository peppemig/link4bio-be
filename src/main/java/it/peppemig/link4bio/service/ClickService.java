package it.peppemig.link4bio.service;

import it.peppemig.link4bio.dto.ClickDTO;
import it.peppemig.link4bio.repository.ClickRepository;
import org.springframework.stereotype.Service;

@Service
public class ClickService {
    private final ClickRepository clickRepository;

    public ClickService(ClickRepository clickRepository) {
        this.clickRepository = clickRepository;
    }

    // TODO
    // reference saveLinkToPage in LinkService
    //public ClickDTO saveClickToLink(String userId)
}
