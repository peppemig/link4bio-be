package it.peppemig.link4bio.controllers;

import it.peppemig.link4bio.dto.LinkDTO;
import it.peppemig.link4bio.entity.Link;
import it.peppemig.link4bio.service.LinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/links")
public class LinkController {
    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping("/{uri}")
    public ResponseEntity<List<LinkDTO>> saveLinksToPage(Authentication auth, @PathVariable String uri, @RequestBody List<Link> links) {
        List<LinkDTO> createdLinks = linkService.saveLinksToPage(auth.getName(), uri, links);
        return new ResponseEntity<List<LinkDTO>>(createdLinks, HttpStatus.CREATED);
    }

    @PutMapping("/{linkId}")
    public ResponseEntity<LinkDTO> updateLinkById(Authentication auth, @PathVariable Long linkId, @RequestBody Link link) {
        LinkDTO updatedLink = linkService.updateLink(auth.getName(), linkId, link);
        return new ResponseEntity<LinkDTO>(updatedLink, HttpStatus.OK);
    }

    @DeleteMapping("/{linkId}")
    public ResponseEntity<?> deleteLinkById(Authentication auth, @PathVariable Long linkId) {
        linkService.deleteLinkById(auth.getName(), linkId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
