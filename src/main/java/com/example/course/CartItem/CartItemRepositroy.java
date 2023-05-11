package com.example.course.CartItem;

import com.example.course.Cart.Cart;
import com.example.course.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepositroy extends JpaRepository<CartItem, Long> {
    CartItem findByCartAndProduct(Cart cart, Product product);
    CartItem findByCart(Cart cart);
}
