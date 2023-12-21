package it.peppemig.link4bio.controllers;

import it.peppemig.link4bio.dto.PageDTO;
import it.peppemig.link4bio.entity.Page;
import it.peppemig.link4bio.service.PageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pages")
public class PageController {
    private final PageService pageService;

    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<PageDTO> savePageForUser(@PathVariable String userId, @RequestBody Page page) {
        PageDTO createdPage = pageService.savePageForUser(userId, page);
        return new ResponseEntity<PageDTO>(createdPage, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<PageDTO> getPageByUserId(@PathVariable String userId) {
        PageDTO foundPage = pageService.findPageByUserId(userId);
        return new ResponseEntity<PageDTO>(foundPage, HttpStatus.OK);
    }

    @GetMapping("/uri/{uri}")
    public ResponseEntity<PageDTO> getPageByUri(@PathVariable String uri) {
        PageDTO foundPage = pageService.findPageByUri(uri);
        return new ResponseEntity<PageDTO>(foundPage, HttpStatus.OK);
    }
}
