package url_shortener.service;

import url_shortener.dto.UrlRequest;
import url_shortener.dto.UrlResponse;
import url_shortener.entity.Url;
import url_shortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_CODE_LENGTH = 6;
    private static final String BASE_URL = "http://localhost:8080/";

    public UrlResponse generateShortUrl(UrlRequest request) {
        String shortCode = generateUniqueCode();

        Url url = Url.builder()
                .originalUrl(request.getOriginalUrl())
                .shortCode(shortCode)
                .build();

        Url savedUrl = urlRepository.save(url);

        return UrlResponse.builder()
                .originalUrl(savedUrl.getOriginalUrl())
                .shortUrl(BASE_URL + savedUrl.getShortCode())
                .shortCode(savedUrl.getShortCode())
                .clickCount(savedUrl.getClickCount())
                .createdAt(savedUrl.getCreatedAt())
                .build();
    }

    @Transactional
    public String getOriginalUrl(String shortCode) {
        Optional<Url> urlOptional = urlRepository.findByShortCode(shortCode);

        if (urlOptional.isPresent()) {
            urlRepository.incrementClickCount(shortCode);
            return urlOptional.get().getOriginalUrl();
        }
        throw new RuntimeException("URL not found!");
    }

    public UrlResponse getAnalytics(String shortCode) {
        Url url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("URL not found!"));

        return UrlResponse.builder()
                .originalUrl(url.getOriginalUrl())
                .shortUrl(BASE_URL + url.getShortCode())
                .shortCode(url.getShortCode())
                .clickCount(url.getClickCount())
                .createdAt(url.getCreatedAt())
                .build();
    }

    private String generateUniqueCode() {
        Random random = new Random();
        StringBuilder code;
        do {
            code = new StringBuilder();
            for (int i = 0; i < SHORT_CODE_LENGTH; i++) {
                code.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
            }
        } while (urlRepository.existsByShortCode(code.toString()));

        return code.toString();
    }
}