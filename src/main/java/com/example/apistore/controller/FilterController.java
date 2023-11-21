package com.example.apistore.controller;

import com.example.apistore.ApiCalls.SearchService;
import com.example.apistore.models.SearchModule.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("filter")
public class FilterController {
    @Autowired
    SearchService searchService;

    @GetMapping("/obtener/{query}")
    public Search obtenerBusqueda(@PathVariable String query){
        return searchService.getSearch(query);
    }
}
