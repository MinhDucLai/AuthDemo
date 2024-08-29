package com.example.AuthDemo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderRequest {
    private long productId;
    private long userId;
    private double amount;
}
