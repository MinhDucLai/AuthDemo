package com.example.AuthDemo.service;

import com.example.AuthDemo.entity.Product;
import com.example.AuthDemo.repository.ProductRepository;
import com.example.AuthDemo.request.FilterRequest;
import com.example.AuthDemo.request.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import static com.example.AuthDemo.utils.CopyProperties.copyNonNullProperties;
import static com.example.AuthDemo.utils.UploadImage.UploadImages;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;


    public Product create(ProductRequest request) {

        if (request.getImage() == null ) {
            throw new IllegalArgumentException("Image is required");
        }
        String path =  UploadImages(request.getImage());

        Product product = Product
                .builder()
                .name(request.getName())
                .description(request.getDescription())
                .image(path)
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .build();
        productRepository.save(product);
        return product;
    }

    public Page<Product> getAll(FilterRequest request , Pageable pageable) {
        return productRepository.search(request , pageable);
    }

    public Product update(ProductRequest request , Long id) {
        Product product;
        try {
            product = productRepository.findById(id).get();
        }
        catch (Exception e) {
            throw new RuntimeException("Not found product " + request.getProductId());
        }
        try {
            if (request.getImage() != null){
                String path =  UploadImages(request.getImage());
                product.setImage(path);
            }


            copyNonNullProperties(request,product);
            productRepository.save(product);
        }catch (Exception e) {
            throw new RuntimeException("Not save product " + request.getProductId());
        }
        return product;
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public Product getOne(Long id) {
        return productRepository.findById(id).get();
    }
}
