package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
//import com.example.demo.repository;

import com.example.demo.model.StockMovement;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.StockMovementRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final StockMovementRepository stockMovementRepository;

    public int getStock(String id){
        return 0;
    }

    public List<StockMovement> getStockMovementByProductId(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStockMovementByProductId'");
    }

    
}
