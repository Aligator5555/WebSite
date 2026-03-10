package org.example.website.repository;


import org.example.website.model.Product;
import org.example.website.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByTitle(String title);
    List<Product> findAllByProductCategory(ProductCategory productCategory);
    List<Product> findByTitleContainingIgnoreCase(String title);
}
