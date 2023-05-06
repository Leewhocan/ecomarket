package com.example.course.Cart;

import com.example.course.CartItem.CartItem;
import com.example.course.User.User;
import com.example.course.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("carts")
public class CartController {

    @Autowired
    public CartSevice cartSevice;

    @GetMapping()
    public List<CartItem> getAllCartItems(
            @RequestParam("user_id") Long userId
    ){
        System.out.println("xnj ,kznm yt nfr " + userId);
        List<CartItem> list = cartSevice.getCartItems(userId);
        System.out.println(list);
        return list;
    }

    @PostMapping()
    public ResponseEntity addToCartRequest(
            @RequestParam("product_id") Long productId,
            @RequestParam("user_id") Long userId,
            @RequestParam("increase") Boolean increase
    ) {
        // TODO: 1. increase=true, cartRepository.findByProductId() = null => create new
        // TODO: 2. increase=true,
        //       2.1 productRepository.findById().count == getQuantity, не увеличиваем
        //       2.2 find = cartItem => setQuantity (getQuantity + 1)

        //
        try {
            return ResponseEntity.ok().body(cartSevice.addToCart(productId, userId, increase));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Закончились продукты");
        }

    }

}
