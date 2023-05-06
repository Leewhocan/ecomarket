package com.example.course.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin("http://localhost:3000/")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping()
    public List<Product> getAllProducts(@RequestParam(name = "title",required = false) String title) {
        List<Product> product = productService.getProducts(title);
        System.out.println(product);
        return product;
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id){
        Product product;
        product = productService.getProductById(id);
        return product;
    }
    @PostMapping()
    public Product addProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }


}
