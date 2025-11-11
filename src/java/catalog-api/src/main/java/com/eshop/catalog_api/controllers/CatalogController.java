package com.eshop.catalog_api.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.eshop.catalog_api.entities.CatalogBrand;
import com.eshop.catalog_api.entities.CatalogType;
import com.eshop.catalog_api.repositories.CatalogBrandRepository;
import com.eshop.catalog_api.repositories.CatalogTypeRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping(path = "/api/v1/catalog")
public class CatalogController {

    private final CatalogTypeRepository catalogTypeRepository;
    private final CatalogBrandRepository catalogBrandRepository;

    public CatalogController(CatalogTypeRepository catalogTypeRepository, CatalogBrandRepository catalogBrandRepository) {
        this.catalogTypeRepository = catalogTypeRepository;
        this.catalogBrandRepository = catalogBrandRepository;
    }


    @GetMapping("/catalogtypes")
    public List<CatalogType> getCatalogTypes() {
        return catalogTypeRepository.findAll();
    }

    @GetMapping("/catalogbrands")
    public List<CatalogBrand> getCatalogBrands() {
        return catalogBrandRepository.findAll();
    }

}
