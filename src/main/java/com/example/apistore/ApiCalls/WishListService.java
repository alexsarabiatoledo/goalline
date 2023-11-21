package com.example.apistore.ApiCalls;


import com.example.apistore.models.OutstandingProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WishListService {

    private List<OutstandingProduct> wishListItems = new ArrayList<>();


    public String addToWishList(OutstandingProduct wishlistItem) {
        if (isProductInWishList(wishlistItem)) {
            return "El producto ya está en la lista de deseos.";
        } else {
            wishListItems.add(wishlistItem);
            return "El producto se ha agregado a la lista de deseos con éxito.";
        }
    }


    public boolean isProductInWishList(OutstandingProduct product) {
        return wishListItems.stream().anyMatch(item -> item.getId().equals(product.getId()));
    }

    public List<OutstandingProduct> getWishList() {
        return wishListItems;
    }


    public void removeFromWishList(OutstandingProduct product) {
        wishListItems.removeIf(item -> item.getId().equals(product.getId()));
    }

}
