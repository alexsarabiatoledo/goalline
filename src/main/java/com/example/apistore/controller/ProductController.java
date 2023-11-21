package com.example.apistore.controller;

import com.example.apistore.ApiCalls.ApiMercadolibreService;
import com.example.apistore.ApiCalls.OutstandingProductService;
import com.example.apistore.models.OutstandingProduct;
import com.example.apistore.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/search")
public class ProductController {

    private final ApiMercadolibreService apiMercadolibreService;
    private final OutstandingProductService outstandingProductService;

    @GetMapping("/query/{product}")
    public List<Product> getProductsByQuery(@PathVariable String product){
        return apiMercadolibreService.getProductsByQuery(product);
    }

    @GetMapping("{idProduct}")
    public Product getProductById(@PathVariable String idProduct){
        return apiMercadolibreService.getProductById(idProduct);
    }

    @GetMapping("/category/{category}")
    public List<OutstandingProduct> getProductsByCategory(@PathVariable String category){
        return outstandingProductService.getProducts("https://api.mercadolibre.com/sites/MLC/search?category="+category);
    }

    @GetMapping("/category/a/{category}")
    public List<Product> getProductsByCategoryAlex(@PathVariable String category){
        return apiMercadolibreService.getProductsByCategory(category);
    }

    @GetMapping("/free_shipping")
    public List<OutstandingProduct> getFreeShippingProducts(){
        return outstandingProductService.getProducts(
                "https://api.mercadolibre.com/sites/MLC/search?category=MLC1051&shipping_cost=free&limit=4"
        );
    }

    @GetMapping("/console_discount")
    public List<OutstandingProduct> getDiscountProducts(){
        return outstandingProductService.getProducts(
                "https://api.mercadolibre.com/sites/MLC/search?category=MLC1144&discount=50-100&limit=4"
        );
    }
}