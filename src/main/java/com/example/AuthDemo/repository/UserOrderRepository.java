package com.example.AuthDemo.repository;

import com.example.AuthDemo.entity.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {

    @Query("SELECT u from UserOrder u Where u.user.name = :name")
    List<UserOrder> getOrderByUserName(@Param("name") String name);
}
