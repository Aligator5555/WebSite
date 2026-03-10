package org.example.website.service;

import org.example.website.model.Product;
import org.example.website.model.TextEntry;
import org.example.website.repository.TextEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TextService {

    private final TextEntryRepository textEntryRepository;

    @Autowired
    public TextService(TextEntryRepository textEntryRepository) {
        this.textEntryRepository = textEntryRepository;
    }

    /**
     * Метод для сохранения текста в базу данных
     * @param text текст для сохранения
     * @return сохранённая сущность TextEntry
     */
    @Transactional
    public TextEntry saveText(String text) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Текст не может быть пустым");
        }

        TextEntry textEntry = new TextEntry(text.trim());
        return textEntryRepository.save(textEntry);
    }
    public List<TextEntry> lisText() {
        return textEntryRepository.findAll();
    }

    @Transactional
    public void deleteText(Long id) {
        textEntryRepository.deleteById(id);
    }
}
