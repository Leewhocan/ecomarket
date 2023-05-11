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
@CrossOrigin("http://localhost:3000")
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

        try {
            return ResponseEntity.ok().body(cartSevice.addToCart(productId, userId, increase));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Закончились продукты");
        }

    }
    @GetMapping("/delete")
    public void deleteCart(@RequestParam("user_id") Long userId){
        cartSevice.clearCart(userId);
    }

}
