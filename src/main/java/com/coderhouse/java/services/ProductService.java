package com.coderhouse.java.services;

import com.coderhouse.java.middlewares.ApiException;
import com.coderhouse.java.persistences.models.Product;
import com.coderhouse.java.persistences.repositories.InvoiceDetailRepository;
import com.coderhouse.java.persistences.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getOne(Long clientId) {
        return findProductOrFail(clientId);
    }

    public Product findProductOrFail(Long clientId) {
        return productRepository.findById(clientId).orElseThrow(() -> new ApiException("Product not found", HttpStatus.NOT_FOUND));
    }

    public Product createOne(Product product) {
        validateProperties(product);
        checkIfProductExists(product);
        Product instance = Product.createWith(product.getName(), product.getCode(), product.getPrice(), product.getStock());
        return productRepository.save(instance);
    }

    private void checkIfProductExists(Product product) {
        boolean exists = productRepository.findByCode(product.getCode()).isPresent();
        if (exists) throw new ApiException("Product already exists", HttpStatus.BAD_REQUEST);
    }


    public Product updateOne(Product product, Long clientId) {
        validateProperties(product);
        Product instance = findProductOrFail(clientId);
        instance.updateWith(product.getName(), product.getCode(), product.getPrice(), product.getStock());
        return productRepository.save(instance);
    }

    private static void validateProperties(Product product) {
        if (!product.hasName() || !product.hasCode() || !product.hasPrice() || !product.hasStock())
            throw new ApiException("Properties body missing", HttpStatus.BAD_REQUEST);
    }

    public void deleteOne(Long clientId) {
        Product product = findProductOrFail(clientId);
        checkIfProductIsLinked(product);
        productRepository.delete(product);
    }

    private void checkIfProductIsLinked(Product product) {
        boolean isLinked = invoiceDetailRepository.findByProductId(product.getId()).isPresent();
        if (isLinked) throw new ApiException("Product linked to InvoiceDetail", HttpStatus.BAD_REQUEST);
    }
}
