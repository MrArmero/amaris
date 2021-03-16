package com.amaris.masa.inditex.dtos;

import java.util.Date;

/**
 * Default DTO used for priceRequest
 */
public class PriceRequest {

    private Date date;
    private int productId;
    private int brandId;

    public PriceRequest() {}

    public PriceRequest(Date date, int productId, int brandId) {
        this.date = date;
        this.productId = productId;
        this.brandId = brandId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
