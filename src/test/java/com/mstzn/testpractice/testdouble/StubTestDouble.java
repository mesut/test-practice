package com.mstzn.testpractice.testdouble;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
/*
   The role of the test stub is to return controlled values to the object being tested
   These are described as indirect inputs to the test

   Responder’s : happy path
   Saboteur's : exceptional behavior
 */

@RunWith(MockitoJUnitRunner.class)
public class StubTestDouble {

    @Test
    public void it_should_get_product_price() {
        //given
        PriceService stubPriceService = mock(PriceService.class);
        ProductService productService = new ProductService(stubPriceService);

        when(stubPriceService.getPrice()).thenReturn(20);

        //when
        String productPrice = productService.whatIsMyPrice();

        //then
        assertThat(productPrice).isEqualTo("ProductPrice Is : 20");
    }

    private static class ProductService {

        private final PriceService priceService;

        private ProductService(PriceService priceService) {
            this.priceService = priceService;
        }

        public String whatIsMyPrice() {
            return "ProductPrice Is : " + priceService.getPrice();
        }
    }

    private static class PriceService {

        public int getPrice() {
            return 10;
        }
    }

}
