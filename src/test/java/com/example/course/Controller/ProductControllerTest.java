package com.example.course.Controller;

import com.example.course.Product.Product;
import com.example.course.Product.ProductController;
import com.example.course.Product.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private List<Product> productList;

    @BeforeEach
    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        productList = new ArrayList<>();
//        productList.add(new Product(1L, 10, 5, "Product 1", "Opis 1"));
//        productList.add(new Product(2L, 20, 3, "Product 2", "Opis 2"));
//        productList.add(new Product(3L, 30, 7, "Product 3", "Opis 3"));
    }

    @Test
    public void testGetAllProducts() {
//        when(productService.getProducts(null)).thenReturn(productList);
//
//        List<Product> products = productController.getAllProducts(null);
//
//        Assertions.assertEquals(3, products.size());
//        Assertions.assertEquals("Product 1", products.get(0).getTitle());
//        Assertions.assertEquals("Product 2", products.get(1).getTitle());
//        Assertions.assertEquals("Product 3", products.get(2).getTitle());
//    }
//
//    @Test
//    public void testGetAllProductsByTitle() {
//        when(productService.getProducts("Product 1")).thenReturn(List.of(productList.get(0)));
//
//        List<Product> products = productController.getAllProducts("Product 1");
//
//        Assertions.assertEquals(1, products.size());
//        Assertions.assertEquals("Product 1", products.get(0).getTitle());
    }




    @Test
    public void testAddProduct() {
//        Product product = new Product(null, 50, 2, "New Product", "Opis");
//        when(productService.saveProduct(product)).thenReturn(new Product(4L, 50, 2, "New Product", "Opis"));
//
//        Product savedProduct = productController.addProduct(product);
//
//        Assertions.assertEquals(4L, savedProduct.getId());
//        Assertions.assertEquals("New Product", savedProduct.getTitle());
//        Assertions.assertEquals(50, savedProduct.getPrice());
//        Assertions.assertEquals(2, savedProduct.getCount());
//        Assertions.assertEquals("Opis", savedProduct.getOpis());
    }
}