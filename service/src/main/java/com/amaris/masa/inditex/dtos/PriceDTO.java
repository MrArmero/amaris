package com.amaris.masa.inditex.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd-HH.mm.ss")
    private LocalDateTime startDate;

    /**
     * Fecha de fin
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd-HH.mm.ss")
    private LocalDateTime endDate;

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
    public PriceDTO(int productId, int brandId, int priceId, LocalDateTime startDate, LocalDateTime endDate, String price, String currency) {
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
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * @param startDate
     */
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    /**
     * @return fecha de fin
     */
    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     */
    public void setEndDate(LocalDateTime endDate) {
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

    @Override
    public String toString() {
        return "PriceDTO{" +
                "productId=" + productId +
                ", brandId=" + brandId +
                ", priceId=" + priceId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", price='" + price + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
