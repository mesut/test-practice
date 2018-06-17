package com.mstzn.testpractice.e2e;

import com.mstzn.testpractice.domain.Product;
import com.mstzn.testpractice.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductGetE2ERestTest {

    @Autowired
    private ProductRepository productRepository;

    @LocalServerPort
    private int port;

    @Test
    public void it_should_get_product_by_name() {
        //given
        Product product = new Product();
        product.setName("elbise");
        productRepository.save(product);

        //when
        when().get(String.format("http://localhost:%s/product/elbise", port))
                .then()
                .statusCode(is(200))
                .body(containsString("Product found  : elbise"));
    }
}
