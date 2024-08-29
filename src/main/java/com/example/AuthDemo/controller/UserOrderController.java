package com.example.AuthDemo.controller;

import com.example.AuthDemo.dto.UserOrderDTO;
import com.example.AuthDemo.entity.UserOrder;
import com.example.AuthDemo.request.FilterRequest;
import com.example.AuthDemo.request.UserOrderRequest;
import com.example.AuthDemo.response.GlobalResponse;
import com.example.AuthDemo.service.UserOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class UserOrderController {
    private final UserOrderService userOrderService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public GlobalResponse addOrder(@RequestBody UserOrderRequest request) {
       UserOrder userOrder = userOrderService.addOrder(request);
       return new GlobalResponse(
                200,"success",userOrder.getVO()
        );
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public GlobalResponse getAll(@RequestBody FilterRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        List<UserOrderDTO> userOrders = userOrderService.getAll(pageable);


        return new GlobalResponse(
                200,"success",userOrders
        );
    }
}
