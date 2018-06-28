package com.mstzn.testpractice.testdouble;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/*
    Mock objects are used to verify object behaviour during a test.
 */
public class MockTestDouble {

    @Test
    public void it_should_get_product_price() {
        //given
        PriceService stubPriceService = mock(PriceService.class);
        HistoryService historyService = mock(HistoryService.class);
        ProductService productService = new ProductService(stubPriceService, historyService);

        when(stubPriceService.getPrice()).thenReturn(20);

        //when
        String productPrice = productService.whatIsMyPrice();

        //then
        assertThat(productPrice).isEqualTo("ProductPrice Is : 20");
        verify(historyService).newHistory("ProductPrice Is : 20");
    }

    private static class ProductService {

        private final PriceService priceService;
        private final HistoryService historyService;

        private ProductService(PriceService priceService,
                               HistoryService historyService) {
            this.priceService = priceService;
            this.historyService = historyService;
        }

        public String whatIsMyPrice() {
            String myPrice = "ProductPrice Is : " + priceService.getPrice();
            historyService.newHistory(myPrice);
            return myPrice;
        }
    }

    private static class PriceService {

        public int getPrice() {
            return 10;
        }
    }

    private static class HistoryService {

        public void newHistory(String history) {

        }
    }

}
