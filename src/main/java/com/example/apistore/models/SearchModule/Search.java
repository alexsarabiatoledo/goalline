package com.example.apistore.models.SearchModule;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Search {
    private Category category; //Este se usara para elegir los filters según la categóría q corresponda
    private ArrayList<Category>pathFromRoot; //este para la navegación
    private ArrayList<Filter> availableFilters;

    public Search(Category category, ArrayList<Category> pathFromRoot, ArrayList<Filter> availableFilters) {
        this.category = category;
        this.pathFromRoot = pathFromRoot;
        this.availableFilters = availableFilters;
    }

    public Search(ArrayList<Filter> availableFilters) {
        this.availableFilters = availableFilters;
    }
}
