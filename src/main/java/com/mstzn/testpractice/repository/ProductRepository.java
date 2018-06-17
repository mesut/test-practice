package com.mstzn.testpractice.repository;

import com.mstzn.testpractice.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Optional<Product> findByName(String name);
}
