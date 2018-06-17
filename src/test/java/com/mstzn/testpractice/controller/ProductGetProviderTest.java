package com.mstzn.testpractice.controller;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.RestPactRunner;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.target.MockMvcTarget;
import com.mstzn.testpractice.domain.Product;
import com.mstzn.testpractice.repository.ProductRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RestPactRunner.class)
@Provider("product_get_provider")
@PactFolder("pact")
public class ProductGetProviderTest {

    private ProductController productController;

    @Mock
    private ProductRepository productRepository;

    @TestTarget
    public final MockMvcTarget target = new MockMvcTarget();

    @Before
    public void setUp() {
        initMocks(this);
        productController = new ProductController(productRepository);
        target.setControllers(productController);
    }

    @State("product get data")
    public void it_should_get_product_data() {
        Product product = new Product();
        product.setName("elbise");
        when(productRepository.findByName("elbise")).thenReturn(Optional.of(product));
    }
}