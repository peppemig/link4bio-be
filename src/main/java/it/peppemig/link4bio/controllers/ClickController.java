package it.peppemig.link4bio.controllers;

import it.peppemig.link4bio.service.ClickService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clicks")
public class ClickController {
    private final ClickService clickService;

    public ClickController(ClickService clickService) {
        this.clickService = clickService;
    }
}
