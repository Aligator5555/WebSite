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
import java.util.ArrayList;
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
                              MultipartFile file1, boolean deleteImage1,
                              MultipartFile file2, boolean deleteImage2,
                              MultipartFile file3, boolean deleteImage3) throws IOException {
        // Находим существующий продукт
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        // Обновляем основные поля
        existingProduct.setTitle(updatedProduct.getTitle());
        existingProduct.setSize(updatedProduct.getSize());
        existingProduct.setSkeleton(updatedProduct.getSkeleton());
        existingProduct.setAdvantages(updatedProduct.getAdvantages());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setProductCategory(productCategory);

        List<Image> currentImages = new ArrayList<>(existingProduct.getImages());
        boolean newPreviewProvided = false;

        // Обрабатываем каждое изображение последовательно
        currentImages = processImage(file1, deleteImage1, 0, currentImages, existingProduct, newPreviewProvided);
        currentImages = processImage(file2, deleteImage2, 1, currentImages, existingProduct, newPreviewProvided);
        currentImages = processImage(file3, deleteImage3, 2, currentImages, existingProduct, newPreviewProvided);

        // Устанавливаем превью, если его нет
        if (!currentImages.isEmpty() && currentImages.stream().noneMatch(Image::isPreviewImage)) {
            currentImages.get(0).setPreviewImage(true);
        }

        // Обновляем список изображений у продукта
        existingProduct.setImages(currentImages);

        // Устанавливаем ID превью-изображения
        Optional<Image> previewImage = currentImages.stream()
                .filter(Image::isPreviewImage)
                .findFirst();
        previewImage.ifPresent(image -> existingProduct.setPreviewImageId(image.getId()));

        // Сохраняем обновлённый продукт
        productRepository.save(existingProduct);
    }

    private List<Image> processImage(MultipartFile file, boolean deleteFlag, int position,
                                     List<Image> currentImages, Product product, boolean newPreviewProvided) throws IOException {
        List<Image> updatedImages = new ArrayList<>(currentImages);

        if (deleteFlag) {
            // Удаляем изображение по позиции, если есть и флаг установлен
            if (position < updatedImages.size()) {
                Image imageToDelete = updatedImages.get(position);
                product.removeImage(imageToDelete);
                imageRepository.delete(imageToDelete);
                updatedImages.remove(position);
            }
        } else if (file != null && file.getSize() > 0) {
            // Загружаем новое изображение
            Image newImage = toImageEntity(file);
            newImage.setProduct(product);

            // Если заменяем существующее изображение
            if (position < updatedImages.size()) {
                // Удаляем старое изображение
                Image oldImage = updatedImages.get(position);
                if (oldImage.isPreviewImage()) {
                    newImage.setPreviewImage(true);
                } else {
                    // Если новое изображение становится превью
                    if (!newPreviewProvided) {
                        newImage.setPreviewImage(true);
                        newPreviewProvided = true;
                    } else {
                        newImage.setPreviewImage(false);
                    }
                }
                product.removeImage(oldImage);
                imageRepository.delete(oldImage);
                updatedImages.set(position, newImage);
            } else {
                // Добавляем новое изображение в конец
                if (!newPreviewProvided) {
                    newImage.setPreviewImage(true);
                    newPreviewProvided = true;
                } else {
                    newImage.setPreviewImage(false);
                }
                updatedImages.add(newImage);
            }
        }

        return updatedImages;
    }

}
