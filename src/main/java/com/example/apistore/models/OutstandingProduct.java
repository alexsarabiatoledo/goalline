package com.example.apistore.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OutstandingProduct {
    private String id;
    private String title;
    private String price;
    private String originalPrice;
    private String thumbnail;
    private String permalink;
    private String picture;
}
