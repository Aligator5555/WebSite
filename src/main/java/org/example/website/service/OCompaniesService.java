package org.example.website.service;

import org.example.website.model.OCompanies;
import org.example.website.repository.OCompaniesRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OCompaniesService {

    private final OCompaniesRepository oCompaniesRepository;

    public OCompaniesService(OCompaniesRepository oCompaniesRepository) {
        this.oCompaniesRepository = oCompaniesRepository;
    }

    public void saveOCompanies(OCompanies oCompanies) {
        oCompaniesRepository.save(oCompanies);
    }

    public OCompanies findById(Long id) {
        return oCompaniesRepository.findById(id).orElse(null);
    }

    public List<OCompanies> findAll() {
        return oCompaniesRepository.findAll();
    }





}
