package com.example.course.Cart;

import com.example.course.Product.Product;
import com.example.course.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);

}
