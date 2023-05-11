package com.example.course.Service;

import com.example.course.Cart.Cart;
import com.example.course.Cart.CartRepository;
import com.example.course.Cart.CartSevice;
import com.example.course.CartItem.CartItem;
import com.example.course.CartItem.CartItemRepositroy;
import com.example.course.Product.Product;
import com.example.course.Product.ProductRepository;
import com.example.course.Product.ProductService;
import com.example.course.User.User;
import com.example.course.User.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdCartTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private CartRepository cartRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CartSevice cartService;
    @Mock
    private CartItemRepositroy cartItemRepository;
    @Mock
    private Cart cart;
    @InjectMocks
    private ProductService productService;


    @BeforeEach
    void setUp() {
        // Инициализация мок-объектов
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
        // Очистка тестовых данных после запуска тестов
    }
    @Test
    public void testAddToCart() {
        //create user
        User user = new User();

        user.setEmail("johndoe@example.com");
        userRepository.save(user);

        //create product
        Product product = new Product(1L, 10, 5, "Product 1", "Opis 1");
        product.setTitle("Test Product");
        product.setPrice(100);
        product.setCount(5);
        productService.saveProduct(product);

        //add product to cart
       
        Cart cart = cartService.addToCart(product.getId(), user.getId(), true);

        //assertions
//        assertNotNull(cart);
//        assertEquals(user, cart.getUser());
//        assertEquals(1, cart.getCartItems().size());
//
//        CartItem cartItem = cart.getCartItems().get(0);
//        assertEquals(product, cartItem.getProduct());
//        assertEquals(1, Optional.ofNullable(cartItem.getQuantity()));
//
//        //increase quantity
//        cart = cartService.addToCart(product.getId(), user.getId(), true);
//        assertEquals(2, Optional.ofNullable(cart.getCartItems().get(0).getQuantity()));
//
//        //decrease quantity
//        cart = cartService.addToCart(product.getId(), user.getId(), false);
//        assertEquals(1, Optional.ofNullable(cart.getCartItems().get(0).getQuantity()));
//
//        //remove item from cart
//        cart = cartService.addToCart(product.getId(), user.getId(), false);
//        assertTrue(cart.getCartItems().isEmpty());
    }
    @Test
    public void testGetCartItems() {
        //create user
        User user = new User();

        user.setEmail("johndoe@example.com");
        userRepository.save(user);

        //create product
        Product product = new Product(1L, 10, 5, "Product 1", "Opis 1");
        product.setTitle("Test Product");
        product.setPrice(100);
        product.setCount(5);
        productService.saveProduct(product);

        //add product to cart
        Cart cart = cartService.addToCart(product.getId(), user.getId(), true);

        //get cart items
        List<CartItem> cartItems = cartService.getCartItems(user.getId());

        //assertions
//        assertNotNull(cartItems);
////        assertEquals(1, cartItems.size());
//
//        CartItem cartItem = cartItems.get(0);
//        assertEquals(product, cartItem.getProduct());
//        assertEquals(1, Optional.ofNullable(cartItem.getQuantity()));
    }

    @Test
    public void testRemoveFromCart() {
        //create user
        User user = new User();
        user.setEmail("johndoe@example.com");
        userRepository.save(user);

        //create product
        Product product = new Product(1L, 10, 5, "Product 1", "Opis 1");
        product.setTitle("Test Product");
        product.setPrice(100);
        product.setCount(5);
        productService.saveProduct(product);
        //Cart cart = user.getCart();
        //add product to cart
        //cart = cartService.addToCart(product.getId(), user.getId(), true);

        //get cart item id
        //Long cartItemId = cart.getCartItems().get(0).getId();

        //remove item from cart
        //cartService.removeFromCart(cartItemId);

        //get cart items
        //List<CartItem> cartItems = cartService.getCartItems(user.getId());

        //assertions
        //assertNotNull(cartItems);
        //assertTrue(cartItems.isEmpty());
    }
    @Test
    public void testClearCart() {
        // create test data
        User user = new User();
        user.setId(1L);
        Cart cart = new Cart();
        cart.setUser(user);
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(new CartItem());
        cart.setCartItems(cartItems);

        // mock repository methods
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(cartRepository.findByUser(user)).thenReturn(Optional.of(cart));

        // call the method
        cartService.clearCart(1L);

        // assert that the cart is empty
//        assertEquals(0, cart.getCartItems().size());
    }
}
