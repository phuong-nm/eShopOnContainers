package com.eshop.catalog_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eshop.catalog_api.entities.CatalogType;


@Repository
public interface CatalogTypeRepository extends JpaRepository<CatalogType, Integer> {

}
