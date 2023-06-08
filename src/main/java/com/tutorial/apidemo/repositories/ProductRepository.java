package com.tutorial.apidemo.repositories;

import com.tutorial.apidemo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
// JpaRepository có các hàm thêm sửa xoá, ta chỉ cần kết thừa lại
// Product: kiểu của thực thể, Long:  kiểu của khoá chính
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Write exactly the Entity's property name, JpaRepository can detect it
    List<Product> findByProductName(String productName);
}
