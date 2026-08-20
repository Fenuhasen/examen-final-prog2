package com.example.demo.dto.response;

import com.example.demo.model.MovementType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockMovementResponse {

    private String id;
    private Instant createdAt;
    private MovementType movementType;
    private int quantity;
    private String productId;
}