package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

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