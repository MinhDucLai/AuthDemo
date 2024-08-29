package com.example.AuthDemo.entity;

import com.example.AuthDemo.dto.ProductDTO;
import com.example.AuthDemo.dto.UserDTO;
import com.example.AuthDemo.dto.UserOrderDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "user_order")
public class UserOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private long amount;

    public UserOrderDTO getVO(){
        UserOrderDTO userOrderDTO = new UserOrderDTO();
        BeanUtils.copyProperties(this, userOrderDTO);

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);

        userOrderDTO.setUser(userDTO);
        userOrderDTO.setProduct(productDTO);
        return userOrderDTO;
    }

}
