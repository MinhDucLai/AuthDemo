package com.example.AuthDemo.controller;

import com.example.AuthDemo.dto.ProductDTO;
import com.example.AuthDemo.entity.Product;
import com.example.AuthDemo.request.FilterRequest;
import com.example.AuthDemo.request.ProductRequest;
import com.example.AuthDemo.response.GlobalResponse;
import com.example.AuthDemo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public GlobalResponse createProduct(@ModelAttribute ProductRequest request) {
        Product product = productService.create(request);
        return new GlobalResponse(200,"success",product);

    }
    @PostMapping("/list")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public GlobalResponse getListProduct(@RequestBody FilterRequest request ) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize() , Sort.Direction.valueOf(request.getSortDirection()),request.getSortProperty());

        Page<Product> products = productService.getAll(request,pageable);
        List<ProductDTO> productList = new ArrayList<>();
        for (Product product : products.getContent()) {
            productList.add(product.getVO());
        }
        return new GlobalResponse(200,"success",productList);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public GlobalResponse getProductById(@PathVariable Long id) {

        Product product = productService.getOne(id);

        return new GlobalResponse(200,"success",product.getVO());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity updateProduct(@ModelAttribute ProductRequest request ,@PathVariable("id") Long id) {
        Product product = productService.update(request , id);
        return new ResponseEntity<>(
                product.getVO(), HttpStatus.OK
        );
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
       productService.delete(id);
        return new ResponseEntity<>(
                "DELETE",
                 HttpStatus.OK

        );
    }
}
