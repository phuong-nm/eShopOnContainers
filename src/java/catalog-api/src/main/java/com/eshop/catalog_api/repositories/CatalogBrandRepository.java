package com.eshop.catalog_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eshop.catalog_api.entities.CatalogBrand;


@Repository
public interface CatalogBrandRepository extends JpaRepository<CatalogBrand, Integer> {

}
