package com.example.demo.dto.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

import com.example.demo.model.MovementType;
import com.example.demo.model.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockMovement {

    private String id;
    private Instant createdAt;
    private MovementType movementType;
    private int quantity;
    private Product product;
}