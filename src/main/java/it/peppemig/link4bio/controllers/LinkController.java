package it.peppemig.link4bio.controllers;

import it.peppemig.link4bio.entity.Link;
import it.peppemig.link4bio.service.LinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/links")
public class LinkController {
    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping("/{pageId}")
    public ResponseEntity<Link> saveLinkToPage(Authentication auth, @PathVariable Long pageId, @RequestBody Link link) {
        Link createdLink = linkService.saveLinkToPage(auth.getName(), pageId, link);
        return new ResponseEntity<Link>(createdLink, HttpStatus.CREATED);
    }

    @DeleteMapping("/{linkId}")
    public ResponseEntity<?> deleteLinkById(Authentication auth, @PathVariable Long linkId) {
        linkService.deleteLinkById(auth.getName(), linkId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
