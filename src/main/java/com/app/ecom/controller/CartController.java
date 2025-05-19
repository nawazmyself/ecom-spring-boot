package com.app.ecom.controller;

import com.app.ecom.dto.CartItemRequest;
import com.app.ecom.model.CartItem;
import com.app.ecom.service.CartService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;


    @PostMapping
    public ResponseEntity<String> addToCart(
            @RequestHeader("Nawaz") String userId,
            @RequestBody CartItemRequest request) {
        if (cartService.addToCart(userId, request)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404 Not Found");
    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity<Void> removeFromCart(
            @RequestHeader("Nawaz") String userId,
            @PathVariable Long productId){
        boolean deleted = cartService.deleteItemFromCart(userId,productId);
        return deleted?ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
    @GetMapping
    public ResponseEntity<List<CartItem>> getCart(
            @RequestHeader("Nawaz") String userId){
        return ResponseEntity.ok(cartService.getCart(userId));
    }

}