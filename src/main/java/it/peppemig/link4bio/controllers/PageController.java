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
    public ResponseEntity<Page> savePage(@PathVariable String userId, @RequestBody Page page) {
        Page createdPage = pageService.savePage(userId, page);
        return new ResponseEntity<Page>(createdPage, HttpStatus.CREATED);
    }
}
