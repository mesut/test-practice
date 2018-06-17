package com.mstzn.testpractice.repository;

import com.mstzn.testpractice.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void it_should_find_by_name() {
        //given
        Product product = new Product();
        product.setName("elbise");
        productRepository.save(product);

        //when
        Optional<Product> foundProduct = productRepository.findByName("elbise");

        //then
        assertThat(foundProduct.get().getName()).isEqualTo("elbise");
    }

    @Test
    public void it_should_save_product() {
        //given
        Product product = new Product();
        product.setName("elbise");

        //when
        Product savedProduct = productRepository.save(product);

        //then
        assertThat(savedProduct.getId()).isNotNull();
    }

    @Test
    public void it_should_delete_product_by_id() {
        //given
        Product product = new Product();
        product.setName("elbise");
        Product savedProduct = productRepository.save(product);

        //when
        productRepository.delete(savedProduct);

        //then
        assertThat(productRepository.findAll()).isEmpty();
    }
}