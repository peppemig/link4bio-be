package it.peppemig.link4bio.controllers;

import it.peppemig.link4bio.dto.PageDTO;
import it.peppemig.link4bio.entity.Page;
import it.peppemig.link4bio.service.PageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pages")
public class PageController {
    private final PageService pageService;

    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    @PostMapping("")
    public ResponseEntity<PageDTO> savePageForUser(Authentication auth, @RequestBody Page page) {
        PageDTO createdPage = pageService.savePageForUser(auth.getName(), page);
        return new ResponseEntity<PageDTO>(createdPage, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<PageDTO> getPageByUserId(Authentication auth) {
        PageDTO foundPage = pageService.findPageByUserId(auth.getName());
        return new ResponseEntity<PageDTO>(foundPage, HttpStatus.OK);
    }

    @GetMapping("/user/exists")
    public ResponseEntity<Boolean> checkIfUserHasPage(Authentication auth) {
        Boolean userHasPage = pageService.checkIfUserHasPage(auth.getName());
        return new ResponseEntity<Boolean>(userHasPage, HttpStatus.OK);
    }

    @GetMapping("/uri/{uri}")
    public ResponseEntity<PageDTO> getPageByUri(@PathVariable String uri) {
        PageDTO foundPage = pageService.findPageByUri(uri);
        return new ResponseEntity<PageDTO>(foundPage, HttpStatus.OK);
    }

    @GetMapping("/uri/{uri}/exists")
    public ResponseEntity<Boolean> checkIfUriIsTaken(@PathVariable String uri) {
        boolean isUriTaken = pageService.checkIfUriIsTaken(uri);
        return new ResponseEntity<Boolean>(isUriTaken, HttpStatus.OK);
    }

}
