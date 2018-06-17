package com.mstzn.testpractice.handler;

import com.mstzn.testpractice.exception.ProductNotFoundException;
import com.mstzn.testpractice.exception.WeatherNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestErrorHandler {

    @ExceptionHandler({ProductNotFoundException.class, WeatherNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleError() {
    }
}
