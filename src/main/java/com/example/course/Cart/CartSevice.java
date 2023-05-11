package com.example.course.Cart;

import com.example.course.CartItem.CartItem;
import com.example.course.CartItem.CartItemRepositroy;
import com.example.course.Product.Product;
import com.example.course.Product.ProductRepository;
import com.example.course.Product.ProductService;
import com.example.course.User.User;
import com.example.course.User.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartSevice {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private CartItemRepositroy cartItemRepository;
    public Cart addToCart(Long productId, Long userId,Boolean increase) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartRepository.findByUser(user).orElse(new Cart());
        if(cart.getId() == null) {
            cart.setUser(user);
            cart = cartRepository.save(cart);
        }
        Product product = productService.getProductById(productId);
        CartItem existingCartItem = cartItemRepository.findByCartAndProduct(cart, product);
        if(existingCartItem != null) {
            int currentQuantity = existingCartItem.getQuantity();
            if (increase){
                if(currentQuantity < product.getCount()) {
                    existingCartItem.setQuantity(currentQuantity + 1);
                    cartItemRepository.save(existingCartItem);
                } else {throw new RuntimeException("No products left");
                }
            }
            else {
                if (currentQuantity > 1){
                    existingCartItem.setQuantity(currentQuantity - 1);
                    cartItemRepository.save(existingCartItem);
                }
                else{
                    cartItemRepository.deleteById(existingCartItem.getId());
                }
            }
        } else {
            CartItem cartItem = new CartItem(cart,product,1);
            cartItemRepository.save(cartItem);
        }
        return cart;
    }
    public List<CartItem> getCartItems(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("NOT FOUND by id in cart serv"));;
        return user.getCart().getCartItems();
    }
    public void removeFromCart(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElse(null);
        if(cartItem != null) {
            cartItemRepository.deleteById(cartItemId);
        }

    }
    public void clearCart(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartRepository.findByUser(user).orElse(new Cart());
        List<CartItem> cartItems = cart.getCartItems();
        cartItems.clear();
        cart.setCartItems(cartItems);
        user.setCart(cart);
        userRepository.save(user);
    }
}
