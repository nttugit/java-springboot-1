package com.tutorial.apidemo.database;

import com.tutorial.apidemo.models.Product;
import com.tutorial.apidemo.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// Now connect with mysql using JPA
/*
docker run -d --rm --name mysql-spring-boot-tutorial -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_USER=hoangnd -e MYSQL_PASSWORD=123456 -e MYSQL_DATABASE=test_db -p 3309:3306 --volume mysql-spring-boot-tutorial-volume:/var/lib/mysql mysql:latest
docker run -d --rm --name mysql-spring-boot-tutorial -e MYSQL_ROOT_PASSWORD= -e MYSQL_USER=localhost -e MYSQL_PASSWORD= -e MYSQL_DATABASE=test_db -p 3309:3306 --volume mysql-spring-boot-tutorial-volume:/var/lib/mysql mysql:latest

mysql -h localhost -P 3309 --protocol=tcp -u localhost -p

* */
@Configuration
public class Database {
    // seeding data cho chúng ta (Initialize for H2 Database)
    // logger hỗ trợ đọc log, nhớ truyền tham số để biết class nào
    private static final Logger logger = LoggerFactory.getLogger(Database.class);

    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository) {
        logger.info("\nkdKFDHSJFHKDJSHKJK\n");

        // Tạo đối tượng để thực thi interface CommandLineRunner
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
//                Product productA = new Product("Macbook Pro 16", 2023, 2400.0, "https://example.com/product1");
//                Product productB = new Product("Ipad Air Green Pro 16", 2021, 599.0, "https://example.com/product2");
//                logger.info("Insert data: " + productRepository.save(productA));
//                logger.info("Insert data: " + productRepository.save(productB));
            }
        };
    }

}
