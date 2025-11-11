package com.eshop.catalog_api.view_models;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginatedItemsViewModel<T> {
    private int pageIndex;
    private int pageSize;
    private long count;
    private List<T> data;

    public PaginatedItemsViewModel(int pageIndex, int pageSize, long count, List<T> data) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.count = count;
        this.data = data;
    }
}
