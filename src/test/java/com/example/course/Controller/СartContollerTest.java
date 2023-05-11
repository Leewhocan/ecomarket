package com.example.course.Controller;

import com.example.course.Cart.Cart;
import com.example.course.Cart.CartController;
import com.example.course.Cart.CartRepository;
import com.example.course.Cart.CartSevice;
import com.example.course.CartItem.CartItem;
import com.example.course.CartItem.CartItemRepositroy;
import com.example.course.Product.ProductRepository;
import com.example.course.Product.ProductService;
import com.example.course.User.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.ExpectedCount.times;

public class СartContollerTest {
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
    @Mock
    private CartController cartController;
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
    public void testGetAllCartItemsWithCorrectUserId() {
        Long userId = 1L;
        List<CartItem> expectedList = new ArrayList<>();
        expectedList.add(new CartItem(1L, 2, 100.0));
        expectedList.add(new CartItem(2L, 1, 200.0));

        when(cartService.getCartItems(userId)).thenReturn(expectedList);

        List<CartItem> actualList = cartController.getAllCartItems(userId);

//        assertEquals(expectedList, actualList);
    }



    @Test
    public void testGetAllCartItemsWithIncorrectUserId() {
        Long userId = null;

        List<CartItem> actualList = cartController.getAllCartItems(userId);

//        assertNull(actualList);
    }



    @Test
    public void testAddToCartRequestWithSuccess() {
        Long productId = 1L;
        Long userId = 1L;
        Boolean increase = true;

//        when(cartService.addToCart(productId, userId, increase)).thenReturn("Товар успешно добавлен");

        ResponseEntity expectedResponse = ResponseEntity.ok().body("Товар успешно добавлен");
        ResponseEntity actualResponse = cartController.addToCartRequest(productId, userId, increase);

//        assertEquals(expectedResponse, actualResponse);
    }



    @Test
    public void testAddToCartRequestWithOutOfStock() {
        Long productId = 1L;
        Long userId = 1L;
        Boolean increase = true;

//        when(cartService.addToCart(productId, userId, increase)).thenThrow(new Exception("Закончились продукты"));

        ResponseEntity expectedResponse = ResponseEntity.badRequest().body("Закончились продукты");
        ResponseEntity actualResponse = cartController.addToCartRequest(productId, userId, increase);

//        assertEquals(expectedResponse, actualResponse);
    }



    @Test
    public void testDeleteCart() {
        Long userId = 1L;

        cartController.deleteCart(userId);

//        verify(cartService).clearCart(userId);
    }
}
