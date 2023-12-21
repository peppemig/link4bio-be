package it.peppemig.link4bio.service;

import it.peppemig.link4bio.dto.LinkDTO;
import it.peppemig.link4bio.entity.Link;
import it.peppemig.link4bio.entity.Page;
import it.peppemig.link4bio.exceptions.UnauthorizedException;
import it.peppemig.link4bio.repository.LinkRepository;
import it.peppemig.link4bio.repository.PageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class LinkService {
    private final LinkRepository linkRepository;
    private final PageRepository pageRepository;
    private final ModelMapper modelMapper;

    public LinkService(LinkRepository linkRepository, PageRepository pageRepository, ModelMapper modelMapper) {
        this.linkRepository = linkRepository;
        this.pageRepository = pageRepository;
        this.modelMapper = modelMapper;
    }

    public LinkDTO saveLinkToPage(String userId, Long pageId, Link link) {
        Page page = pageRepository.findById(pageId).orElseThrow(() -> new EntityNotFoundException("Page not found with ID: " + pageId));
        if (!page.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Not authorized");
        }
        link.setPage(page);
        Link newLink = linkRepository.save(link);
        return modelMapper.map(newLink, LinkDTO.class);
    }

    public LinkDTO updateLink(String userId, Long linkId, Link link) {
        Link existingLink = linkRepository.findById(linkId).orElseThrow(() -> new EntityNotFoundException("Link not found with ID: " + linkId));
        if (!existingLink.getPage().getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Not authorized");
        }
        existingLink.setTitle(link.getTitle());
        existingLink.setSubtitle(link.getSubtitle());
        existingLink.setImageUrl(link.getImageUrl());
        existingLink.setUrl(link.getUrl());
        Link updatedLink = linkRepository.save(existingLink);
        return modelMapper.map(updatedLink, LinkDTO.class);
    }

    public void deleteLinkById(String userId, Long linkId) {
        Link link = linkRepository.findById(linkId).orElseThrow(() -> new EntityNotFoundException("Link not found with ID: " + linkId));
        if (!link.getPage().getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Not authorized");
        }
        linkRepository.deleteById(linkId);
    }
}
