package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.StockMovement;
import com.example.demo.service.ProductService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@AllArgsConstructor
@Getter
@Setter

@RestController
public class ProductController {
    private ProductService productService;

    @GetMapping("/products/{id}/stock")
    public int getStock(@PathVariable String id) {
        return this.productService.getStock(id);
    }

    @GetMapping("/products/{id}/stock-mouvements")
    public List<StockMovement> getStockMouvementByProductId(@PathVariable String id) {
        return this.productService.getStockMovementByProductId(id);
    }

}
