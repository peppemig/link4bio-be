package it.peppemig.link4bio.service;

import it.peppemig.link4bio.entity.Link;
import it.peppemig.link4bio.entity.Page;
import it.peppemig.link4bio.exceptions.UnauthorizedException;
import it.peppemig.link4bio.repository.LinkRepository;
import it.peppemig.link4bio.repository.PageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LinkService {
    private final LinkRepository linkRepository;
    private final PageRepository pageRepository;

    public LinkService(LinkRepository linkRepository, PageRepository pageRepository) {
        this.linkRepository = linkRepository;
        this.pageRepository = pageRepository;
    }

    public Link saveLinkToPage(String userId, Long pageId, Link link) {
        Page page = pageRepository.findById(pageId).orElseThrow(() -> new EntityNotFoundException("Page not found with ID: " + pageId));
        if (!page.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Not authorized");
        }
        link.setPage(page);
        return linkRepository.save(link);
    }

    public void deleteLinkById(String userId, Long linkId) {
        Link link = linkRepository.findById(linkId).orElseThrow(() -> new EntityNotFoundException("Link not found with ID: " + linkId));
        if (!link.getPage().getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Not authorized");
        }
        linkRepository.deleteById(linkId);
    }
}
