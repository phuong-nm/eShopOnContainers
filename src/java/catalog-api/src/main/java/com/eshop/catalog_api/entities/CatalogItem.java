package com.eshop.catalog_api.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
CREATE TABLE [Microsoft.eShopOnContainers.Services.CatalogDb].dbo.[Catalog] (
	Id int NOT NULL,
	CatalogBrandId int NOT NULL,
	CatalogTypeId int NOT NULL,
	Description nvarchar(MAX) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	Name nvarchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	PictureFileName nvarchar(MAX) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	Price decimal(18,2) NOT NULL,
	AvailableStock int DEFAULT 0 NOT NULL,
	MaxStockThreshold int DEFAULT 0 NOT NULL,
	OnReorder bit DEFAULT CONVERT([bit],(0)) NOT NULL,
	RestockThreshold int DEFAULT 0 NOT NULL,
	CONSTRAINT PK_Catalog PRIMARY KEY (Id),
	CONSTRAINT FK_Catalog_CatalogBrand_CatalogBrandId FOREIGN KEY (CatalogBrandId) REFERENCES [Microsoft.eShopOnContainers.Services.CatalogDb].dbo.CatalogBrand(Id) ON DELETE CASCADE,
	CONSTRAINT FK_Catalog_CatalogType_CatalogTypeId FOREIGN KEY (CatalogTypeId) REFERENCES [Microsoft.eShopOnContainers.Services.CatalogDb].dbo.CatalogType(Id) ON DELETE CASCADE
);
*/

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

    @Column(name = "\"Name\"", length = 50)
    private String name;

    @Column(name = "\"Description\"")
    private String description;

    @Column(name = "\"Price\"", columnDefinition = "decimal", precision = 18, scale = 2)
    private BigDecimal price;

    @Column(name = "\"PictureFileName\"")
    private String pictureFileName;

    @Transient
    private String pictureUri;

    @Column(name = "\"CatalogTypeId\"", insertable = false, updatable = false)
    private int CatalogTypeId;

    @OneToOne
    @JoinColumn(name = "\"CatalogTypeId\"")
    private CatalogType catalogType;

    @Column(name = "\"CatalogBrandId\"", insertable = false, updatable = false)
    private int catalogBrandId;

    @OneToOne
    @JoinColumn(name = "\"CatalogBrandId\"")
    private CatalogBrand catalogBrand;

    @Column(name = "\"AvailableStock\"")
    private int availableStock;

    @Column(name = "\"RestockThreshold\"")
    private int restockThreshold;

    @Column(name = "\"MaxStockThreshold\"")
    private int maxStockThreshold;

    @Column(name = "\"OnReorder\"")
    private boolean onReorder;

    public int removeStock(int quantityDesired) {
        return 0;
    }

    public int addStock(int quantity) {
        return 0;
    }
}
