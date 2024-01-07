package it.peppemig.link4bio.controllers;

import it.peppemig.link4bio.dto.LinkAnalyticsDTO;
import it.peppemig.link4bio.service.AnalyticsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {
    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/clicks")
    public ResponseEntity<List<LinkAnalyticsDTO>> getTotalClicksPerLinkByUserId(Authentication auth) {
        List<LinkAnalyticsDTO> clicks = analyticsService.getTotalClicksPerLinkByUserId(auth.getName());
        return new ResponseEntity<List<LinkAnalyticsDTO>>(clicks, HttpStatus.OK);
    }
}
