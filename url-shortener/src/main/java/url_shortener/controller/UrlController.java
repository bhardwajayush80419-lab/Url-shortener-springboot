package url_shortener.controller;

import url_shortener.dto.UrlRequest;
import url_shortener.dto.UrlResponse;
import url_shortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/api/shorten")
    public ResponseEntity<UrlResponse> shortenUrl(@RequestBody UrlRequest request) {
        UrlResponse response = urlService.generateShortUrl(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{shortCode:[a-zA-Z0-9]{6}}")
    public ResponseEntity<Void> redirectToOriginal(@PathVariable String shortCode) {
        String originalUrl = urlService.getOriginalUrl(shortCode);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
    }

    @GetMapping("/api/analytics/{shortCode}")
    public ResponseEntity<UrlResponse> getAnalytics(@PathVariable String shortCode) {
        UrlResponse response = urlService.getAnalytics(shortCode);
        return ResponseEntity.ok(response);
    }
}