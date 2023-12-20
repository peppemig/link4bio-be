package it.peppemig.link4bio.controllers;

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
    public ResponseEntity<Page> savePageForUser(@PathVariable String userId, @RequestBody Page page) {
        Page createdPage = pageService.savePageForUser(userId, page);
        return new ResponseEntity<Page>(createdPage, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Page> getPageByUserId(@PathVariable String userId) {
        Page foundPage = pageService.findPageByUserId(userId);
        return new ResponseEntity<Page>(foundPage, HttpStatus.OK);
    }

    @GetMapping("/uri/{uri}")
    public ResponseEntity<Page> getPageByUri(@PathVariable String uri) {
        Page foundPage = pageService.findPageByUri(uri);
        return new ResponseEntity<Page>(foundPage, HttpStatus.OK);
    }
}
