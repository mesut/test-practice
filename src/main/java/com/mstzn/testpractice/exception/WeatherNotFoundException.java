package com.mstzn.testpractice.exception;

import java.util.function.Supplier;

public class WeatherNotFoundException extends RuntimeException implements Supplier<WeatherNotFoundException> {

    private static final long serialVersionUID = -6401446654124805781L;

    @Override
    public WeatherNotFoundException get() {
        return new WeatherNotFoundException();
    }
}
