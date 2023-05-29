package com.coderhouse.java.controllers;

import com.coderhouse.java.dto.ResponseHandler;
import com.coderhouse.java.middlewares.ApiException;
import com.coderhouse.java.persistences.models.Product;
import com.coderhouse.java.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Object> getAll() {
        try {
            List<Product> products = productService.getAll();
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseHandler.generate("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable Long id) {
        try {
            Product product = productService.getOne(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (ApiException apiException) {
            return ResponseHandler.generate(apiException.getMessage(), apiException.getHttpStatus());
        } catch (Exception e) {
            return ResponseHandler.generate("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Product body) {
        try {
            Product product = productService.createOne(body);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (ApiException apiException) {
            return ResponseHandler.generate(apiException.getMessage(), apiException.getHttpStatus());
        } catch (Exception e) {
            return ResponseHandler.generate("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Product body) {
        try {
            Product product = productService.updateOne(body, id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (ApiException apiException) {
            return ResponseHandler.generate(apiException.getMessage(), apiException.getHttpStatus());
        } catch (Exception e) {
            return ResponseHandler.generate("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            productService.deleteOne(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ApiException apiException) {
            return ResponseHandler.generate(apiException.getMessage(), apiException.getHttpStatus());
        } catch (Exception e) {
            return ResponseHandler.generate("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
