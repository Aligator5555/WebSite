package org.example.website.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "portfolios") // стандартизированное имя таблицы
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // заменили AUTO на IDENTITY
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ImagePortfolio> images = new ArrayList<>(); // убрали @Column

    @Column(name = "preview_image_id")
    private Long previewImageId;

    @Column(name = "date_of_created")
    private LocalDateTime dateOfCreated;

    public Portfolio() {}

    public void addImage(ImagePortfolio imagePortfolio) {
        imagePortfolio.setPortfolio(this);
        images.add(imagePortfolio);
    }

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

    public Portfolio(Long id, String name, List<ImagePortfolio> images, Long previewImageId, LocalDateTime dateOfCreated) {
        this.id = id;
        this.name = name;
        this.images = images;
        this.previewImageId = previewImageId;
        this.dateOfCreated = dateOfCreated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ImagePortfolio> getImages() {
        return images;
    }

    public void setImages(List<ImagePortfolio> images) {
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
}
