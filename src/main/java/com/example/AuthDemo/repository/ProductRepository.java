package com.example.AuthDemo.repository;

import com.example.AuthDemo.entity.Product;
import com.example.AuthDemo.request.FilterRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(
            "SELECT p FROM Product p " +
                    "WHERE 1 = 1" +
                    "AND (:#{#f.name} is null OR lower(p.name) like concat('%',lower(:#{#f.name}) ,'%') )"
    )
    Page<Product> search(@Param("f") FilterRequest request , Pageable pageable);
}
