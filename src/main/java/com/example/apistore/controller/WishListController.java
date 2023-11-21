package com.example.apistore.controller;

import com.example.apistore.ApiCalls.WishListService;
import com.example.apistore.models.OutstandingProduct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/wishlist")
public class WishListController {

    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }


    @PostMapping("/add")
    public ResponseEntity<String> addToWishList(@RequestBody OutstandingProduct wishListItem) {
        String message = wishListService.addToWishList(wishListItem);

        if (message.contains("con éxito")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/items")
    public List<OutstandingProduct> getWishListItems() {
        return wishListService.getWishList();
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeFromWishList(@RequestBody OutstandingProduct wishListItem) {
        try {
            wishListService.removeFromWishList(wishListItem);
            return new ResponseEntity<>("El producto se ha eliminado de la lista de deseos con éxito.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el producto de la lista de deseos.", HttpStatus.BAD_REQUEST);
        }
    }
}
