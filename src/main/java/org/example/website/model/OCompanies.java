package org.example.website.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Companies")
public class OCompanies {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "text")
    private List<String> texts;

    public OCompanies(Long id, List<String> texts) {
        this.id = id;
        this.texts = texts;
    }

    public OCompanies() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getTexts() {
        return texts;
    }

    public void setTexts(List<String> texts) {
        this.texts = texts;
    }
}
