package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.demo.repository.StockMovementRepository;
import com.example.demo.model.MovementType;
import com.example.demo.model.StockMovement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

@Service
public class StockMovementService {
    private final StockMovementRepository stockMouvementRepository;

    public List<StockMovement> findAll(){
        return this.stockMouvementRepository.findAll();
    }

    public List<StockMovement> getAllStockMouvementByType(MovementType valueOf) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllStockMouvementByType'");
    }

}
