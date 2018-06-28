package com.mstzn.testpractice.controller;

import com.mstzn.testpractice.domain.Product;
import com.mstzn.testpractice.dto.request.ProductCreateRequest;
import com.mstzn.testpractice.exception.ProductNotFoundException;
import com.mstzn.testpractice.repository.ProductRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductRepository productRepository;

    @Rule
    public ExpectedException expectException = ExpectedException.none();

    @Test
    public void it_should_save_product() {
        //given
        ProductCreateRequest productCreateRequest = new ProductCreateRequest();
        Product product = new Product(productCreateRequest);

        //when
        productController.save(productCreateRequest);

        //then

        verify(productRepository).save(product);
    }

    @Test
    public void it_should_get_product_by_name() {
        //given
        Product product = new Product();
        product.setName("elbise");
        when(productRepository.findByName("elbise")).thenReturn(Optional.of(product));

        //when
        String response = productController.getByName("elbise");

        //then
        assertThat(response).isEqualTo("Product found  : elbise");
    }

    @Test
    public void it_should_throw_product_not_found_exception_when_product_not_found() {
        //then
        expectException.expect(ProductNotFoundException.class);

        //given
        when(productRepository.findByName("elbise")).thenReturn(Optional.empty());

        //when
        productController.getByName("elbise");
    }
}