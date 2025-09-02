package com.eleven.office.service;

import com.eleven.office.entity.Product;
import com.eleven.office.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    // Get all products
    public List<Product> getAll() {
        return repo.findAll();
    }

    // Get product by ID
    public Product getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    // Save product
    public Product save(Product product) {
        return repo.save(product);
    }

    // Update product
    public Product update(Long id, Product updatedProduct) {
        Optional<Product> optionalProduct = repo.findById(id);
        if (optionalProduct.isPresent()) {
            Product existing = optionalProduct.get();

            existing.setName(updatedProduct.getName());
            existing.setModel(updatedProduct.getModel());
            existing.setCategory(updatedProduct.getCategory());
            existing.setPrice(updatedProduct.getPrice());
            existing.setType(updatedProduct.getType());
            existing.setRatio(updatedProduct.getRatio());
            existing.setPower(updatedProduct.getPower());
            existing.setMaterial(updatedProduct.getMaterial());
            existing.setFeatures(updatedProduct.getFeatures());
            existing.setDescription(updatedProduct.getDescription());

            return repo.save(existing);
        } else {
            return null; // or throw exception
        }
    }

    // Delete product
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
