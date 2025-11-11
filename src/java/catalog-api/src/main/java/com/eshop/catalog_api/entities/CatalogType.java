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
@Table(name = "\"CatalogType\"")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CatalogType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"Id\"")
    private int id;

    @Column(name = "\"Type\"", nullable = false, length = 100)
    private String type;
}
