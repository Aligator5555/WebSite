package org.example.website.service;

import org.example.website.model.Image;
import org.example.website.model.Product;
import org.example.website.model.ProductCategory;
import org.example.website.repository.ImageRepository;
import org.example.website.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;

    public ProductService(ProductRepository productRepository, ImageRepository imageRepository) {
        this.productRepository = productRepository;
        this.imageRepository = imageRepository;
    }

    public List<Product> listProducts(String title) {
        if (title != null) return productRepository.findByTitle(title);
        return productRepository.findAll();
    }

    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    public List<Image> listImages() {
        return imageRepository.findAll();
    }



    public void saveProduct(Product product,
                            ProductCategory productCategory,
                            MultipartFile file1,
                            MultipartFile file2, MultipartFile file3) throws IOException {
        Image image1;
        Image image2;
        Image image3;
        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            product.addImageToProduct(image1);
        }
        if (file2.getSize() != 0) {
            image2 = toImageEntity(file2);
            product.addImageToProduct(image2);
        }
        if (file3.getSize() != 0) {
            image3 = toImageEntity(file3);
            product.addImageToProduct(image3);
        }
        product.setProductCategory(productCategory);
        Product productFromDb = productRepository.save(product);
        productFromDb.setPreviewImageId(productFromDb.getImages().get(0).getId());
        productRepository.save(product);
    }
    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }
    @Transactional
    public void deleteProduct(Long id) {
        Product product = new Product();
        product.setId(id);
        productRepository.deleteById(product.getId());
    }
    public Product getProductById(Long id) {

        return productRepository.findById(id).orElse(null);
    }
    public Optional<Product> addIdProduct(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getProductsByCategory(ProductCategory category) {
        return productRepository.findAllByProductCategory(category);
    }
    public List<Product> titleProducts(String title) {
        return productRepository.findByTitleContainingIgnoreCase(title);
    }

    @Transactional
    public void updateProduct(Long productId, Product updatedProduct,
                              ProductCategory productCategory,
                              MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        // Находим существующий продукт
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        // Обновляем основные поля
        existingProduct.setTitle(updatedProduct.getTitle());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setProductCategory(productCategory);

        // Очищаем существующие изображения (если нужно полностью заменить)
        imageRepository.deleteAll(existingProduct.getImages());
        existingProduct.getImages().clear();

        // Добавляем новые изображения
        Image image1, image2, image3;
        if (file1 != null && file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            existingProduct.addImageToProduct(image1);
        }
        if (file2 != null && file2.getSize() != 0) {
            image2 = toImageEntity(file2);
            existingProduct.addImageToProduct(image2);
        }
        if (file3 != null && file3.getSize() != 0) {
            image3 = toImageEntity(file3);
            existingProduct.addImageToProduct(image3);
        }

        // Устанавливаем ID превью-изображения (первое из добавленных)
        if (!existingProduct.getImages().isEmpty()) {
            existingProduct.setPreviewImageId(existingProduct.getImages().get(0).getId());
        }

        // Сохраняем обновлённый продукт
        productRepository.save(existingProduct);
    }


}
