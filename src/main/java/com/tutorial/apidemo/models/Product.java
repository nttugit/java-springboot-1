package com.tutorial.apidemo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


// POJO - Plain Object Java Object
@Entity
public class Product {
    // Định nghĩa Class là thực thể nên cần phải định nghĩa chỗ này
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // ~ PRIMARY KEY AUTO_INCREMENT
    private Long id;
    private String productName;
    private int productionYear;
    private Double price;
    private String url;

    public Product() {

    }

    public Product(String productName, int productionYear, Double price, String url) {
        this.productName = productName;
        this.productionYear = productionYear;
        this.price = price;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productionYear=" + productionYear +
                ", price=" + price +
                ", url='" + url + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
