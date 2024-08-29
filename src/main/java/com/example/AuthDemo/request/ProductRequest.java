package com.example.AuthDemo.request;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class ProductRequest {
    private Integer productId;

    private String name;

    private String description;

    private Double price;

    private Double quantity;

    private MultipartFile image;
}
