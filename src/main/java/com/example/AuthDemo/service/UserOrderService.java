package com.example.AuthDemo.service;

import com.example.AuthDemo.dto.UserOrderDTO;
import com.example.AuthDemo.entity.Product;
import com.example.AuthDemo.entity.User;
import com.example.AuthDemo.entity.UserOrder;
import com.example.AuthDemo.exception.UserNotFoundException;
import com.example.AuthDemo.repository.ProductRepository;
import com.example.AuthDemo.repository.UserOrderRepository;
import com.example.AuthDemo.repository.UserRepository;
import com.example.AuthDemo.request.FilterRequest;
import com.example.AuthDemo.request.UserOrderRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserOrderService {
    @Autowired
    private UserOrderRepository userOrderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public UserOrder addOrder(UserOrderRequest request) {
        //reduce product
        try {
            Product product = productRepository.findById(request.getProductId()).get();
            if (product.getQuantity() < request.getAmount()) {
                throw new Exception("Not enough product");
            }
            product.setQuantity(product.getQuantity() - request.getAmount());
            productRepository.save(product);
        }
        catch (Exception e) {
            throw new RuntimeException("Product not found");
        }
        // add user order
        UserOrder userOrder = UserOrder
                .builder()
                .user(new User(request.getUserId()))
                .product(new Product(request.getProductId()))
                .amount((long) request.getAmount())
                .build();
        userOrderRepository.save(userOrder);
        return userOrder;
    }

    public List<UserOrderDTO> getAll(Pageable pageable) {
        Page<UserOrder> userOrders = userOrderRepository.findAll(pageable);
        List<UserOrderDTO> userOrderList = new ArrayList<>();


        for (UserOrder userOrder : userOrders) {
            userOrderList.add(userOrder.getVO());
        }
        return userOrderList;
    }

    public List<UserOrderDTO> getListOrderbyUser(FilterRequest request)  {

        List<UserOrder> userOrders = userOrderRepository.getOrderByUserName(request.getName());
        List<UserOrderDTO> userOrderList = new ArrayList<>();
        for (UserOrder userOrder : userOrders) {
            userOrderList.add(userOrder.getVO());
        }
        return  userOrderList;
    }
}
