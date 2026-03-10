package org.example.website.controller;

import org.example.website.model.Portfolio;
import org.example.website.model.Product;
import org.example.website.model.TextEntry;
import org.example.website.service.PortfolioService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class PortfolioController {

    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }
    @GetMapping("/portfolio/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        portfolioService.deletePortfolio(id);
        return "redirect:/";
    }

    @GetMapping("/addPortfolio")
    public String addPortfolio(Model model) {
        return "addPortfolio";
    }

    @GetMapping("/cardPortfolio")
    public String cardPortfolio(Model model) {
        return "CardPortfolio";
    }

    @GetMapping("/portfolio")
    public String Portfolio(Model model) {
        List<Portfolio> listPortfolio = portfolioService.listPortfolio();
        System.out.println("Количество портфолио: " + listPortfolio.size()); // Логируем размер списка
        model.addAttribute("portfolios", listPortfolio);
        return "Portfolio";
    }
    @GetMapping("/portfolio/{id}")
    public String productInfo(@PathVariable Long id, Model model) {
        Portfolio portfolio = portfolioService.getPortfolioById(id);
        model.addAttribute("portfolio", portfolio);
        model.addAttribute("images", portfolio.getImages());
        return "Portfolio";
    }

    @GetMapping("/descriptionPortfolio/{id}")
    public  String description(@PathVariable(name = "id") Long id, Model model) {
        Optional<Portfolio> portfolio = portfolioService.addIdPortfolio(id);
        if (portfolio.isPresent()) {
                model.addAttribute("portfolios", portfolio.get());
            model.addAttribute("images", portfolio.get().getImages());
            return "CardPortfolio";
        } else {
            return "redirect:/error";
        }

    }


    @PostMapping("/savePortfolio")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String savePortfolio(@RequestParam("file1") MultipartFile file1,
                                @RequestParam("file2") MultipartFile file2,
                                @RequestParam("file3") MultipartFile file3,
                                Portfolio portfolio) throws IOException {
        portfolioService.savePortfolio(portfolio, file1, file2, file3);

        return "redirect:/";

    }
}
