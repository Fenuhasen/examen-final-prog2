package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.demo.repository.StockMouvementRepository;
import com.example.demo.model.StockMouvement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

@Service
public class StockMouvementService {
    private final StockMouvementRepository stockMouvementRepository;

    public List<StockMouvement> findAll(){
        return this.stockMouvementRepository.findAll();
    }

}
