package com.example.demo.controller;

import com.example.demo.dto.request.CreateStockMovementRequest;
import com.example.demo.dto.response.StockMovementResponse;
import com.example.demo.service.StockMovementService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class StockMovementController {

    private final StockMovementService stockMovementService;

    public StockMovementController(StockMovementService stockMovementService) {
        this.stockMovementService = stockMovementService;
    }

    @GetMapping("/stock-movements")
    public List<StockMovementResponse> findStockMovements(@RequestParam(required = false) String type) {
        return stockMovementService.findStockMovements(type);
    }

    @GetMapping("/products/{id}/stock-movements")
    public List<StockMovementResponse> findStockMovementsByProduct(@PathVariable String id) {
        return stockMovementService.findStockMovementsByProductId(id);
    }

    @PostMapping("/stock-movements")
    @ResponseStatus(HttpStatus.CREATED)
    public StockMovementResponse createStockMovement(@RequestBody CreateStockMovementRequest request) {
        return stockMovementService.createStockMovement(request);
    }

    @GetMapping("/products/{id}/stock")
    public Map<String, Object> getProductStock(@PathVariable String id) {
        return Map.of(
                "productId", id,
                "stock", stockMovementService.getStock(id)
        );
    }
}
