package com.amaris.masa.inditex.controllers;


import com.amaris.masa.inditex.datamodel.Brand;
import com.amaris.masa.inditex.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<Brand>> getPrices() {
        return new ResponseEntity<List<Brand>>(brandService.getAll(), new HttpHeaders(), HttpStatus.OK);
    }
}
