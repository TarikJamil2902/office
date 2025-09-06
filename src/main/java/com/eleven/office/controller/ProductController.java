package com.eleven.office.controller;

import com.eleven.office.entity.Product;
import com.eleven.office.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService service;

    @GetMapping("/test")
    public String testLog() {
        logger.info("Test log working!");
        return "Check console!";
    }

    @GetMapping
    public List<Product> getAllProducts() {
        List<Product> products = service.getAll();
        System.out.println(products);
        return products;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        Product product = service.getById(id);
        logger.info("Fetched product by ID {}: {}", id, product);
        return product;
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product) {
        Product savedProduct = service.save(product);
        logger.info("Saved product: {}", savedProduct);
        return savedProduct;
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updated = service.update(id, product);
        logger.info("Updated product ID {}: {}", id, updated);
        return updated;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        service.delete(id);
        logger.info("Deleted product ID {}", id);
    }

    @PutMapping("/{id}/image")
    public Product updateProductImage(@PathVariable Long id, @RequestBody String base64Image) {
        Product product = service.getById(id);
    
        // Remove the data:image/jpeg;base64, prefix if present
        if (base64Image.startsWith("data:image")) {
            base64Image = base64Image.substring(base64Image.indexOf(",") + 1);
        }
    
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
        product.setImage(imageBytes); // Your Product class must use byte[]
        
        Product updated = service.update(id, product);
        logger.info("Updated product image ID {}: {}", id, updated);
        return updated;
    }
    
    
}
