package org.example.website.service;

import org.example.website.model.ImagePortfolio;
import org.example.website.model.Portfolio;
import org.example.website.model.Product;
import org.example.website.repository.ImagePortfolioRepository;
import org.example.website.repository.PortfolioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PortfolioService {
    private final PortfolioRepository portfolioRepository;
    private final ImagePortfolioRepository imagePortfolioRepository;

    public PortfolioService(PortfolioRepository portfolioRepository, ImagePortfolioRepository imagePortfolioRepository) {
        this.portfolioRepository = portfolioRepository;
        this.imagePortfolioRepository = imagePortfolioRepository;
    }

    public Optional<Portfolio> addIdPortfolio(Long id) {
        return portfolioRepository.findById(id);
    }

    public Portfolio getPortfolioById(Long id) {

        return portfolioRepository.findById(id).orElse(null);
    }
    public List<Portfolio> listPortfolio() {
        return portfolioRepository.findAll();
    }
    public void savePortfolio(Portfolio portfolio, MultipartFile file1,
                                   MultipartFile file2, MultipartFile file3) throws IOException {
        ImagePortfolio imagePortfolio1;
        ImagePortfolio imagePortfolio2;
        ImagePortfolio imagePortfolio3;

        if (file1.getSize() != 0) {
            imagePortfolio1 = toImageEntity(file1);
            imagePortfolio1.setPreviewImage(true);
            portfolio.addImage(imagePortfolio1);
        }
        if (file2.getSize() != 0) {
            imagePortfolio2 = toImageEntity(file2);
            portfolio.addImage(imagePortfolio2);
        }
        if (file3.getSize() != 0) {
            imagePortfolio3 = toImageEntity(file3);
            portfolio.addImage(imagePortfolio3);
        }
        Portfolio portfolioFromDb = portfolioRepository.save(portfolio);
        portfolioFromDb.setPreviewImageId(portfolioFromDb.getImages().get(0).getId());
        portfolioRepository.save(portfolio);
    }

    private ImagePortfolio toImageEntity(MultipartFile file) throws IOException {
        ImagePortfolio imagePortfolio = new ImagePortfolio();
        imagePortfolio.setName(file.getName());
        imagePortfolio.setOriginalFileName(file.getOriginalFilename());
        imagePortfolio.setContentType(file.getContentType());
        imagePortfolio.setSize(file.getSize());
        imagePortfolio.setBytes(file.getBytes());
        return imagePortfolio;
    }
    @Transactional
    public void deletePortfolio(Long id) {
        portfolioRepository.deleteById(id);
    }
}

