package com.example.AuthDemo.entity;

import com.example.AuthDemo.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double price;

    private Double quantity;

    private String image;

    @OneToMany(mappedBy = "product")
    List<UserOrder> userOrders;


    public Product(long productId) {
        this.id = productId;
    }

    public ProductDTO getVO(){
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(this, productDTO);

        return productDTO;
    }
}
