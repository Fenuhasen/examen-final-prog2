package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.StockMovementService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.model.StockMovement;
import com.example.demo.model.MovementType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@Getter
@Setter

@RestController
public class StockMovementController {
    private StockMovementService stockMovementService;

    @GetMapping("/stock-mouvements")
    public List<StockMovement> getAllStockMouvementByType(@RequestParam String type) {
        return this.stockMovementService.getAllStockMouvementByType(MovementType.valueOf(type));
    }

    @GetMapping("/stock-mouvements")
    public List<StockMovement> findAll() {
        return this.stockMovementService.findAll();
    }

    @PostMapping("/stock-mouvements")
    public String postMethodName(@RequestBody String entity) {
        return entity;
    }

}
