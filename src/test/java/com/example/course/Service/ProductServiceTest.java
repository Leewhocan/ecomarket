package com.example.course.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.example.course.Product.Product;
import com.example.course.Product.ProductRepository;
import com.example.course.Product.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

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
    void testGetProducts() {
        // Проверка метода getProducts без параметров
        List<Product> expectedProducts = new ArrayList<>();
        // Добавление фейковых продуктов в тестовый список
        expectedProducts.add(new Product(1L, 100, 10, "Product 1", "Description 1"));
        expectedProducts.add(new Product(2L, 200, 20, "Product 2", "Description 2"));


        // Указываем, что при вызове метода findByTitle(null) должен возвращаться список expectedProducts
        when(productRepository.findByTitle(null)).thenReturn(expectedProducts);

        List<Product> actualProducts = productService.getProducts(null);

        // Проверка, что количество полученных продуктов равно ожидаемому
//        assertEquals(expectedProducts.size(), actualProducts.size());

        // Проверка, что полученный список продуктов содержит все ожидаемые продукты
//        assertTrue(actualProducts.containsAll(expectedProducts));
    }

    @Test
    void testGetProductById() {
        // Проверка метода getProductById с существующим ID
        Product expectedProduct = new Product("Test Product", "Test Description");
        when(productRepository.findById(1L)).thenReturn(java.util.Optional.of(expectedProduct));

        Product actualProduct = productService.getProductById(1L);

        // Проверка, что полученный продукт равен ожидаемому
        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    void testSaveProduct() {
        // Проверка метода saveProduct с несохраненным продуктом
        Product expectedProduct = new Product("Test Product", "Test Description");
        when(productRepository.save(expectedProduct)).thenReturn(expectedProduct);

        Product actualProduct = productService.saveProduct(expectedProduct);

        // Проверка, что сохраненный продукт не null
      //  assertNotNull(actualProduct.getId());
        // Проверка, что сохраненный продукт равен ожидаемому
        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    void testDeleteProduct() {
        // Проверка метода deleteProduct с существующим продуктом
        doNothing().when(productRepository).deleteById(1L);

        productService.deleteProduct(1L);

        // Проверяем, что deleteById был вызван один раз с аргументом 1L
        verify(productRepository, times(1)).deleteById(1L);
    }

}
