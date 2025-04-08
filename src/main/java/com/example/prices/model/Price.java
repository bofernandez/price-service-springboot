package com.example.prices.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRICES")
public class Price {

    // Se define un campo id autogenerado para la entidad.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Identificador de la marca (ejemplo: 1 para ZARA).
    @Column(name = "BRAND_ID")
    private Integer brandId;

    // Fecha y hora de inicio de la vigencia del precio.
    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    // Fecha y hora de fin de la vigencia del precio.
    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    // Identificador de la tarifa (price list).
    @Column(name = "PRICE_LIST")
    private Integer priceList;

    // Identificador del producto.
    @Column(name = "PRODUCT_ID")
    private Integer productId;

    // Prioridad para desambiguar, en caso de que haya solapamientos en el rango de fechas.
    @Column(name = "PRIORITY")
    private Integer priority;

    // Precio final de venta.
    @Column(name = "PRICE")
    private BigDecimal price;

    // Código de moneda (ISO) del precio.
    @Column(name = "CURR")
    private String currency;

    // Getters y setters (puedes generarlos manualmente o utilizar Lombok)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getBrandId() {
        return brandId;
    }
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
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
    public Integer getPriceList() {
        return priceList;
    }
    public void setPriceList(Integer priceList) {
        this.priceList = priceList;
    }
    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public Integer getPriority() {
        return priority;
    }
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
