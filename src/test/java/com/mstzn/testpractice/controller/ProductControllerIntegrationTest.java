package com.mstzn.testpractice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mstzn.testpractice.domain.Product;
import com.mstzn.testpractice.dto.request.ProductCreateRequest;
import com.mstzn.testpractice.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ProductController.class)
public class ProductControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductRepository productRepository;

    @Test
    public void it_should_save_product() throws Exception {
        //given
        ProductCreateRequest productCreateRequest = new ProductCreateRequest("elbise");

        //when
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(productCreateRequest)))
                .andExpect(status().isCreated());

        //then
        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).save(productCaptor.capture());
        assertThat(productCaptor.getValue().getName()).isEqualTo("elbise");
    }

    @Test
    public void it_should_get_product_when_product_found() throws Exception {
        //given
        Product product = new Product();
        product.setName("elbise");
        when(productRepository.findByName("elbise")).thenReturn(Optional.of(product));

        //when
        mockMvc.perform(get("/product/elbise"))
                .andExpect(content().string("Product found  : elbise"))
                .andExpect(status().isOk());
    }

    @Test
    public void it_should_get_404_when_product_not_found() throws Exception {
        //given
        when(productRepository.findByName("elbise")).thenReturn(Optional.empty());

        //when
        mockMvc.perform(get("/product/elbise"))
                .andExpect(status().isNotFound());

        //then
    }
}