package com.amaris.masa.inditex.services;

import com.amaris.masa.inditex.datamodel.Brand;
import com.amaris.masa.inditex.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> getAll() {
        return brandRepository.findAll();
    }
}
