package org.example.website.repository;


import org.example.website.model.TextEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextEntryRepository extends JpaRepository<TextEntry, Long> {
    // Здесь можно добавить кастомные запросы при необходимости
}

