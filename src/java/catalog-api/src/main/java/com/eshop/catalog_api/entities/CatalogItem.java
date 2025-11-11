package com.eshop.catalog_api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "\"Catalog\"")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CatalogItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"Id\"")
    private int id;

    @OneToOne
    @JoinColumn(name = "\"CatalogBrandId\"")
    private CatalogBrand catalogBrand;

    @OneToOne
    @JoinColumn(name = "\"CatalogTypeId\"")
    private CatalogType catalogType;

    @Column(name = "\"Description\"")
    private String description;

    @Column(name = "\"Name\"")
    private String name;

    @Column(name = "\"PictureFileName\"")
    private String pictureFileName;

    @Column(name = "\"Price\"", columnDefinition = "decimal")
    private double price;

    @Column(name = "\"AvailableStock\"")
    private int availableStock;

    @Column(name = "\"MaxStockThreshold\"")
    private int maxStockThreshold;

    @Column(name = "\"OnReorder\"")
    private boolean onReorder;

    @Column(name = "\"RestockThreshold\"")
    private int restockThreshold;

    public int removeStock(int quantityDesired) {
        return 0;
    }

    public int addStock(int quantity) {
        return 0;
    }
}
