package com.amaris.masa.inditex.datamodel;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Price extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @NotNull
    @Column
    private int priority;

    @NotNull
    @Column
    private BigDecimal amount;

    @Column
    @NotEmpty
    @Size(max=4)
    private String currency;

    @NotNull
    @Column
    private int brandId;

    @NotNull
    @Column
    private int productId;

    public Price() {}

    public Price(Price originalPrice) {
        this.id = originalPrice.getId();
        this.startDate = originalPrice.getStartDate();
        this.endDate = originalPrice.getEndDate();
        this.priority = originalPrice.getPriority();
        this.amount = originalPrice.getAmount();
        this.currency = originalPrice.getCurrency();
        this.brandId = originalPrice.getBrandId();
        this.productId = originalPrice.getProductId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
