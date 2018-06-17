package com.mstzn.testpractice.exception;

import java.util.function.Supplier;

public class ProductNotFoundException extends RuntimeException implements Supplier<ProductNotFoundException> {

    private static final long serialVersionUID = 4085107021669430892L;

    @Override
    public ProductNotFoundException get() {
        return new ProductNotFoundException();
    }
}
