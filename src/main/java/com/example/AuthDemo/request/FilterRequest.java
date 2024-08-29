package com.example.AuthDemo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterRequest {
    private String name;
    private String sortProperty = "id";
    private String sortDirection = "DESC";
    private int page = 0;
    private int size = 10;
}
