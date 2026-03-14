package org.example.website.controller;



import org.example.website.model.ImagePortfolio;
import org.example.website.repository.ImagePortfolioRepository;
import org.example.website.repository.PortfolioRepository;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

@RestController
public class ImagePortfolioController {


    private final ImagePortfolioRepository imagePortfolioRepository;

    public ImagePortfolioController(PortfolioRepository portfolioRepository, ImagePortfolioRepository imagePortfolioRepository) {
        this.imagePortfolioRepository = imagePortfolioRepository;
    }


    @GetMapping("/imagesPortfolio/{id}")
    public ResponseEntity<?> getImagePortfolioById(@PathVariable Long id) throws UnsupportedEncodingException {
        ImagePortfolio image = imagePortfolioRepository.findById(id).orElse(null);
        if (image == null) {
            return ResponseEntity.notFound().build();
        }

        String encodedFileName = java.net.URLEncoder.encode(image.getOriginalFileName(), "UTF-8")
                .replace("+", "%20");

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename*=UTF-8''" + encodedFileName)
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }
}