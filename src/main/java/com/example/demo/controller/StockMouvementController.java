package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.StockMouvementService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.model.StockMouvement;
import com.example.demo.model.MouvementType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@Getter
@Setter

@RestController
public class StockMouvementController {
    private StockMouvementService stockMouvementService;

    @GetMapping("/stock-mouvements")
    public List<StockMouvement> getAllStockMouvementByType(@RequestParam String type) {
        return this.stockMouvementService.getAllStockMouvementByType(MouvementType.valueof(type));
    }

    @GetMapping("/stock-mouvements")
    public List<StockMouvement> findAll() {
        return this.stockMouvementService.findAll();
    }

    @PostMapping("/stock-mouvements")
    public String postMethodName(@RequestBody String entity) {
        return entity;
    }

}
