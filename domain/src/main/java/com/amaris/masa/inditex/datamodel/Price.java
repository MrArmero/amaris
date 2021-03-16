package com.amaris.masa.inditex.datamodel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Price extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date")
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date")
    private Date endDate;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "brandy", foreignKeyDefinition = "foreign key /* FK */ (brand_id) references Brand"))
    private Brand brand;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( foreignKey = @ForeignKey(name = "producty", foreignKeyDefinition = "foreign key /* FK */ (product_id) references Product"))
    @JsonIgnore
    private Product product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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

    public void setAmount(BigDecimal price) {
        this.amount = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
