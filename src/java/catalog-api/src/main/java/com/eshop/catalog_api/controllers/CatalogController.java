package com.eshop.catalog_api.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.eshop.catalog_api.entities.CatalogBrand;
import com.eshop.catalog_api.entities.CatalogItem;
import com.eshop.catalog_api.entities.CatalogType;
import com.eshop.catalog_api.repositories.CatalogBrandRepository;
import com.eshop.catalog_api.repositories.CatalogRepository;
import com.eshop.catalog_api.repositories.CatalogTypeRepository;
import com.eshop.catalog_api.view_models.PaginatedItemsViewModel;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Slf4j
@RestController
@RequestMapping(path = "/api/v1/catalog")
public class CatalogController {

    private final CatalogRepository catalogRepository;
    private final CatalogTypeRepository catalogTypeRepository;
    private final CatalogBrandRepository catalogBrandRepository;

    public CatalogController(
        CatalogRepository catalogRepository,
        CatalogTypeRepository catalogTypeRepository,
        CatalogBrandRepository catalogBrandRepository
    ) {
        this.catalogRepository = catalogRepository;
        this.catalogTypeRepository = catalogTypeRepository;
        this.catalogBrandRepository = catalogBrandRepository;
    }

    @GetMapping(path = "/items")
    public PaginatedItemsViewModel<CatalogItem> getCatalogItems(
        @RequestParam(required = false, defaultValue = "10") int pageSize,
        @RequestParam(required = false, defaultValue = "0") int pageIndex,
        @RequestParam(required = false) String ids
    ) {
        List<CatalogItem> items = catalogRepository.findAll();
        return createPaginatedItems(items, pageSize, pageIndex);
    }

    @PutMapping(path = "/items", consumes = "application/json")
    public ResponseEntity<String> putCatalogItems(
        @RequestParam CatalogItem item
    ) {
        //TODO: process PUT request
        return ResponseEntity.badRequest().build();
    }

    @PostMapping(path = "/items", consumes = "application/json")
    public ResponseEntity<String> postCatalogItems(
        @RequestParam CatalogItem item
    ) {
        //TODO: process POST request
        return ResponseEntity.badRequest().build();
    }

    @GetMapping(path = "/items/{id}")
    public ResponseEntity<CatalogItem> getCatalogItemById(
        @PathVariable(required = true) int id
    ) {
        Optional<CatalogItem> item = catalogRepository.findById(id);
        if (!item.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(item.get());
    }

    @GetMapping(path = "/items/withname/{name}")
    public PaginatedItemsViewModel<CatalogItem> getCatalogItemByName(
        @PathVariable(required = true) String name,
        @RequestParam(defaultValue = "10") int pageSize,
        @RequestParam(defaultValue = "0") int pageIndex
    ) {
        // TODO support findByNameHint
        List<CatalogItem> items = new ArrayList<>();
        for (CatalogItem item : catalogRepository.findAll()) {
            if (item.getName().contains(name)) {
                items.add(item);
            }
        }
        return createPaginatedItems(items, pageSize, pageIndex);
    }

    @GetMapping(path = "/items/type/{catalogTypeId}/brand/{catalogBrandId}")
    public PaginatedItemsViewModel<CatalogItem> getCatalogItemByTypeAndBrand(
        @PathVariable(required = true) int catalogTypeId,
        @PathVariable(required = true) int catalogBrandId,
        @RequestParam(defaultValue = "10") int pageSize,
        @RequestParam(defaultValue = "0") int pageIndex
    ) {
        List<CatalogItem> items = new ArrayList<>();
        Optional<CatalogType> catalogType = catalogTypeRepository.findById(catalogTypeId);
        Optional<CatalogBrand> catalogBrand = catalogBrandRepository.findById(catalogBrandId);
        if (catalogType.isPresent() && catalogBrand.isPresent()) {
            items = catalogRepository.findByCatalogTypeAndCatalogBrand(catalogType.get(), catalogBrand.get());
        }
        return createPaginatedItems(items, pageSize, pageIndex);
    }

    @GetMapping(path = "/items/type/all/brand/{catalogBrandId}")
    public PaginatedItemsViewModel<CatalogItem> getCatalogItemByBrand(
        @PathVariable(required = true) int catalogBrandId,
        @RequestParam(defaultValue = "10") int pageSize,
        @RequestParam(defaultValue = "0") int pageIndex
    ) {
        List<CatalogItem> items = new ArrayList<>();
        Optional<CatalogBrand> catalogBrand = catalogBrandRepository.findById(catalogBrandId);
        if (catalogBrand.isPresent()) {
            items = catalogRepository.findByCatalogBrand(catalogBrand.get());
        }
        return createPaginatedItems(items, pageSize, pageIndex);
    }

    @GetMapping(path = "/catalogtypes")
    public List<CatalogType> getCatalogTypes() {
        return catalogTypeRepository.findAll();
    }

    @GetMapping(path = "/catalogbrands")
    public List<CatalogBrand> getCatalogBrands() {
        return catalogBrandRepository.findAll();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteCatalogItem(
        @RequestParam(required = true) int id
    ) {
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/items/{id}/pic")
    public ResponseEntity<byte[]> getCatalogItemPic(
        @PathVariable(required = true) int id
    ) {
        Optional<CatalogItem> item = catalogRepository.findById(id);
        try {
            if (item.isPresent()) {
                ClassPathResource imageResource = new ClassPathResource("static/pics/" + item.get().getPictureFileName());
                return ResponseEntity.ok()
                        .header("Content-Type", "image/" + FilenameUtils.getExtension(imageResource.getFilename()))
                        .body(imageResource.getInputStream().readAllBytes());
            }
        } catch (Exception e) {
            log.warn("Failed to build item picture response {}", e.getMessage());
        }
        return ResponseEntity.notFound().build();
    }

    private PaginatedItemsViewModel<CatalogItem> createPaginatedItems(
        List<CatalogItem> items,
        int pageSize,
        int pageIndex
    ) {
        PaginatedItemsViewModel<CatalogItem> paginatedItems = new PaginatedItemsViewModel<>(pageIndex, pageSize, items.size(), new ArrayList<>());
        if ((pageIndex * pageSize) < items.size()) {
            final int indexFrom = pageSize * pageIndex;
            int indexTo = pageSize * (pageIndex + 1);
            if (indexTo > items.size()) {
                indexTo = items.size();
            }
            paginatedItems.setData(items.subList(indexFrom, indexTo));
        }
        return paginatedItems;
    }
}
