package com.example.demo.dto.request;

import com.example.demo.model.MovementType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateStockMovementRequest {

    private String productId;
    private MovementType movementType;
    private int quantity;
}