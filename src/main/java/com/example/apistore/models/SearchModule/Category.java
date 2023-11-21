package com.example.apistore.models.SearchModule;

import lombok.Data;

@Data
public class Category {
    private String categoryId;
    private String categoryName;

    public Category(String categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}
