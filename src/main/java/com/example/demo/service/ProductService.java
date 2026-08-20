package com.example.demo.service;

import org.springframework.stereotype.Service;
//import com.example.demo.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public int getStock(String id){
        return 0;
    }

    
}
