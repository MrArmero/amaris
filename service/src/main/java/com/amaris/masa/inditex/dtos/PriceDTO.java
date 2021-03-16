package com.amaris.masa.inditex.dtos;

import java.util.Date;

/**
 * DTO para el response.
 *
 */
public class PriceDTO {

    /**
     * Identificador de producto
     */
    private int productId;
    /**
     * Identificador de cadena
     */
    private int brandId;

    /**
     * Tarifa a aplicar
     */
    private int priceId;

    /**
     * Fecha de inicio
     */
    private Date startDate;

    /**
     * Fecha de fin
     */
    private Date endDate;

    /**
     * Precio
     */
    private String price;

    /**
     * Moneda
     */
    private String currency;

    /**
     * Default constructor for mappings
     */
    public PriceDTO() {}

    /**
     * Constructor con todos los parámetros.
     * @param productId
     * @param brandId
     * @param priceId
     * @param startDate
     * @param endDate
     * @param price
     * @param currency
     */
    public PriceDTO(int productId, int brandId, int priceId, Date startDate, Date endDate, String price, String currency) {
        this.productId = productId;
        this.brandId = brandId;
        this.priceId = priceId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.currency = currency;
    }

    /**
     * @return identificador del producto
     */
    public int getProductId() {
        return productId;
    }

    /**
     * @param productId
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * @return identificador de marca
     */
    public int getBrandId() {
        return brandId;
    }

    /**
     * @param brandId
     */
    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    /**
     * @return identificador del precio
     */
    public int getPriceId() {
        return priceId;
    }

    /**
     * @param priceId
     */
    public void setPriceId(int priceId) {
        this.priceId = priceId;
    }

    /**
     * @return fecha de inicio
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return fecha de fin
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return precio
     */
    public String getPrice() {
        return price;
    }

    /**
     * @param price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * @return Moneda asociada
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
