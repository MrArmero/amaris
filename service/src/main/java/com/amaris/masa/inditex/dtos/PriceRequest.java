package com.amaris.masa.inditex.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * Default DTO used for priceRequest
 */
public class PriceRequest {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd-HH.mm.ss")
    private LocalDateTime date;
    private int productId;
    private int brandId;

    public PriceRequest() {}

    public PriceRequest(LocalDateTime date, int productId, int brandId) {
        this.date = date;
        this.productId = productId;
        this.brandId = brandId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }
}
