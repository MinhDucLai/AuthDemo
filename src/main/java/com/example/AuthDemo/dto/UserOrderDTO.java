package com.example.AuthDemo.dto;

import com.example.AuthDemo.entity.Product;
import com.example.AuthDemo.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderDTO {
    private Long id;
    private UserDTO user;
    private ProductDTO product;
    private long amount;
}
