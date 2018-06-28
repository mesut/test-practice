package com.mstzn.testpractice.testdouble;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
/*
    We just need test behaviour of product addPrice method.
    That's why we need a dummy price object that we don't care about its content and behavior
 */

@RunWith(MockitoJUnitRunner.class)
public class DummyTestDouble {

    @Test
    public void it_should_check_product_has_price() {
        //given
        Price price = createDummyPrice(); //mock(Price.class)

        Product product = new Product();

        //when
        product.addPrice(price);

        //then
        assertThat(product.getPriceList()).containsOnly(price);
    }

    public Price createDummyPrice() {
        return new Price();
    }

    private static class Product {

        private List<Price> priceList = new ArrayList<>();

        public void addPrice(Price price) {
            priceList.add(price);
        }

        public List<Price> getPriceList() {
            return priceList;
        }
    }

    private static class Price {

    }


}
