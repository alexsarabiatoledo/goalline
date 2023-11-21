package com.example.apistore.models.SearchModule;

import lombok.Data;

@Data
public class FilterValue {
    private String filterValueId;
    private String filterValueName;
    private int filterValueQuantityOfResults;

    public FilterValue(String filterValueId, String filterValueName, int filterValueQuantityOfResults) {
        this.filterValueId = filterValueId;
        this.filterValueName = filterValueName;
        this.filterValueQuantityOfResults = filterValueQuantityOfResults;
    }
}
