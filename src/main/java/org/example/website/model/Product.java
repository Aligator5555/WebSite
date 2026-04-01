package org.example.website.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title; // Название продукта
    @Column(name = "description", columnDefinition = "text")
    private String description; // Описание продукта
    @Column(name = "price")
    private String price; // Цена продукта
    @Column(name = "size")
    private String size; // Размер продукта
    @Column(name = "skeleton")
    private String skeleton; // Каркас продукта
    @Column(name = "advantages:")
    private String advantages;  // Преимущества
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "product")
    @Column(length = 100)
    private List<Image> images = new ArrayList<>();
    private Long previewImageId;
    private LocalDateTime dateOfCreated;
    @Enumerated(EnumType.STRING)
    @Column(name = "product_category", length = 100)
    private ProductCategory productCategory;


    public void removeImage(Image image) {
        images.remove(image);
        image.setProduct(null);
    }
    public Product() {

    }

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }


    public void addImageToProduct(Image image) {
        image.setProduct(this);
        images.add(image);
    }

    public Product(Long id, String title, String description,
                   String price, String size, String skeleton,
                   String verticalBeams, String gateStitching,
                   String advantages, List<Image> images,
                   Long previewImageId, LocalDateTime dateOfCreated,
                   ProductCategory productCategory) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.size = size;
        this.skeleton = skeleton;
        this.advantages = advantages;
        this.images = images;
        this.previewImageId = previewImageId;
        this.dateOfCreated = dateOfCreated;
        this.productCategory = productCategory;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSkeleton() {
        return skeleton;
    }

    public void setSkeleton(String skeleton) {
        this.skeleton = skeleton;
    }

    public String getAdvantages() {
        return advantages;
    }

    public void setAdvantages(String advantages) {
        this.advantages = advantages;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Long getPreviewImageId() {
        return previewImageId;
    }

    public void setPreviewImageId(Long previewImageId) {
        this.previewImageId = previewImageId;
    }

    public LocalDateTime getDateOfCreated() {
        return dateOfCreated;
    }

    public void setDateOfCreated(LocalDateTime dateOfCreated) {
        this.dateOfCreated = dateOfCreated;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }
}

