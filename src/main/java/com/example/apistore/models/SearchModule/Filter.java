package com.example.apistore.models.SearchModule;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Filter {
    private String filterId;
    private String filterName;
    private ArrayList<FilterValue> filterValues;

    public Filter(String filterId, String filterName, ArrayList<FilterValue> filterValues) {
        this.filterId = filterId;
        this.filterName = filterName;
        this.filterValues = filterValues;
    }
}
