package com.eshop.catalog_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eshop.catalog_api.entities.CatalogBrand;
import com.eshop.catalog_api.entities.CatalogItem;
import com.eshop.catalog_api.entities.CatalogType;


@Repository
public interface CatalogRepository extends JpaRepository<CatalogItem, Integer> {
    List<CatalogItem> findByCatalogType(CatalogType catalogType);
    List<CatalogItem> findByCatalogBrand(CatalogBrand catalogBrand);
    List<CatalogItem> findByCatalogTypeAndCatalogBrand(CatalogType catalogType, CatalogBrand catalogBrand);
}
