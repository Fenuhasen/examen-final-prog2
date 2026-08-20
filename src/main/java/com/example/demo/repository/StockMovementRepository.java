package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.StockMovement;

public interface StockMovementRepository {

    List<StockMovement> findAll();
}
