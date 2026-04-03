package org.example.website.controller;



import org.example.website.model.Product;
import org.example.website.model.ProductCategory;
import org.example.website.repository.ImageRepository;
import org.example.website.repository.ProductRepository;
import org.example.website.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    private final ProductService productService;


    public ProductController(ProductService productService, ImageRepository imageRepository, ProductRepository productRepository) {
        this.productService = productService;
    }

    @GetMapping("/createProduct")
    public String createProduct(Model model) {
        return "createProduct";
    }


    @GetMapping("/listProduct")
    public String products(@RequestParam(name = "title", required = false) String title, Model model) {
        model.addAttribute("products", productService.listProducts(title));
        return "listProduct";
    }

    @GetMapping("/product/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return "redirect:/error";
        }
        model.addAttribute("product", product);
        model.addAttribute("images", product.getImages());
        return "EditProduct"; // шаблон для формы редактирования
    }

    @PostMapping("/product/update/{id}")
    public String updateProduct(@PathVariable Long id,
                                @RequestParam(value = "file1", required = false) MultipartFile file1,
                                @RequestParam(value = "file2", required = false) MultipartFile file2,
                                @RequestParam(value = "file3", required = false) MultipartFile file3,
                                @RequestParam(value = "deleteImage1", required = false, defaultValue = "false") boolean deleteImage1,
                                @RequestParam(value = "deleteImage2", required = false, defaultValue = "false") boolean deleteImage2,
                                @RequestParam(value = "deleteImage3", required = false, defaultValue = "false") boolean deleteImage3,
                                Product updatedProduct,
                                ProductCategory productCategory) throws IOException {
        try {
            productService.updateProduct(id, updatedProduct, productCategory,
                    file1, deleteImage1, file2, deleteImage2, file3, deleteImage3);
            return "redirect:/";
        } catch (Exception e) {

            return "redirect:/error?message=" + URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8);
        }
    }



    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("images", product.getImages());
        return "product-info";
    }

    @PostMapping("/product/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String createProduct(@RequestParam("file1") MultipartFile file1,
                                @RequestParam("file2") MultipartFile file2,
                                @RequestParam("file3") MultipartFile file3,
                                Product product,
                                ProductCategory productCategory) throws IOException {
        productService.saveProduct(product, productCategory,  file1, file2, file3);
        return "redirect:/";
    }

    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }

    @GetMapping("/description/{id}")
    public  String description(@PathVariable(name = "id") Long id, Model model) {
        Optional<Product> product = productService.addIdProduct(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            model.addAttribute("images", product.get().getImages());
            return "ProductCard";
        } else {
            return "redirect:/error";
        }

    }
    @GetMapping("/descriptionFoto/{id}")
    public  String descriptionFoto(@PathVariable(name = "id") Long id, Model model) {
        Optional<Product> product = productService.addIdProduct(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            model.addAttribute("images", product.get().getImages());
            return "ProductFoto";
        } else {
            return "redirect:/error";
        }

    }
    // Отображение по категории
    @GetMapping("/products/category/{category}")
    public String productsByCategory(@PathVariable ProductCategory category,
                                     Model model) {
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        return "/oneCategory";
    }

    @GetMapping("/searchByTitle")
    public String searchByTitle(@RequestParam(name = "title", required = false) String title, Model model) {
        if (title == null || title.trim().isEmpty()) {
            // Возвращаем пустую страницу или список всех товаров
            model.addAttribute("searchByTitle", new ArrayList<>());
            return "SearchByTitle";
        }

        List<Product> products = productService.titleProducts(title);
        model.addAttribute("searchByTitle", products);
        return "SearchByTitle";
    }


}


