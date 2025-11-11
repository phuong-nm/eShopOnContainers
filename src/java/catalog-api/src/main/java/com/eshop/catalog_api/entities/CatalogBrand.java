package com.eshop.catalog_api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "\"CatalogBrand\"")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CatalogBrand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"Id\"")
    private int id;

    @Column(name = "\"Brand\"", nullable = false, length = 100)
    private String brand;
}
