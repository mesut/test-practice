package com.mstzn.testpractice.controller;

import com.mstzn.testpractice.domain.Product;
import com.mstzn.testpractice.dto.request.ProductCreateRequest;
import com.mstzn.testpractice.exception.ProductNotFoundException;
import com.mstzn.testpractice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody ProductCreateRequest productCreateRequest) {
        productRepository.save(new Product(productCreateRequest));
    }

    @GetMapping(value = "/{name}")
    public String getByName(@PathVariable String name) {
        Optional<Product> foundProduct = productRepository.findByName(name);
        return foundProduct
                .map(product -> String.format("Product found  : %s", product.getName()))
                .orElseThrow(new ProductNotFoundException());
    }
}
