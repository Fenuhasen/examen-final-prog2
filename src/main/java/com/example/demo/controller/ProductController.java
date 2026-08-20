package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.ProductService;
import com.example.demo.model.StockMouvement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
    public List<StockMouvement> getStockMouvementByProductId(@PathVariable String id) {
        return this.productService.getStockMouvementByProductId(id);
    }

}
