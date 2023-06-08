package com.tutorial.apidemo.models;

import jakarta.persistence.*;

import java.util.Calendar;
import java.util.Objects;


// POJO - Plain Object Java Object
@Entity
@Table(name="tblProduct") // map
public class Product {
    // Định nghĩa Class là thực thể nên cần phải định nghĩa chỗ này
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO) // ~ PRIMARY KEY AUTO_INCREMENT

    // Quy tac tao ID , "sequence"
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1 // increment by 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Long id;

    // validate =constraint
    @Column(nullable = false, unique = true, length = 300)
    private String productName;
    private int productionYear;
    private Double price;
    private String url;
    // calculated field
    @Transient
    private int age;
    public int getAge(){
        return Calendar.getInstance().get(Calendar.YEAR) - productionYear;
    }
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

    // Project nay ko dung den
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return productionYear == product.productionYear && age == product.age && Objects.equals(id, product.id) && Objects.equals(productName, product.productName) && Objects.equals(price, product.price) && Objects.equals(url, product.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, productionYear, price, url, age);
    }
}
