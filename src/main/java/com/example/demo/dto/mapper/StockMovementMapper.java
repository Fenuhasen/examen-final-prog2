package com.example.demo.dto.mapper;

import com.example.demo.dto.response.StockMovementResponse;
import com.example.demo.model.StockMovement;

public final class StockMovementMapper {

    private StockMovementMapper() {
    }

    public static StockMovementResponse toResponse(StockMovement stockMovement) {
        return DtoMapper.toStockMovementResponse(stockMovement);
    }
}
