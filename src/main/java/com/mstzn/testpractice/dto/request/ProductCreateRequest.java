package com.mstzn.testpractice.dto.request;

import javax.validation.constraints.NotBlank;

public class ProductCreateRequest {

    @NotBlank
    private String productName;

    public ProductCreateRequest() {
    }

    public ProductCreateRequest(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
