package com.example.demo.service;

import com.example.demo.dto.mapper.DtoMapper;
import com.example.demo.dto.request.CreateStockMovementRequest;
import com.example.demo.dto.response.StockMovementResponse;
import com.example.demo.model.MovementType;
import com.example.demo.model.Product;
import com.example.demo.model.StockMovement;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.StockMovementRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
public class StockMovementService {

    private final StockMovementRepository stockMovementRepository;
    private final ProductRepository productRepository;

    public StockMovementService(StockMovementRepository stockMovementRepository, ProductRepository productRepository) {
        this.stockMovementRepository = stockMovementRepository;
        this.productRepository = productRepository;
    }

    public List<StockMovementResponse> findStockMovements(String type) {
        try {
            List<StockMovement> movements = type == null
                    ? stockMovementRepository.findAll()
                    : stockMovementRepository.findByType(parseMovementType(type));

            return movements.stream()
                    .map(DtoMapper::toStockMovementResponse)
                    .toList();
        } catch (ResponseStatusException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), exception);
        }
    }

    public List<StockMovementResponse> findStockMovementsByProductId(String productId) {
        try {
            ensureProductExists(productId);

            return stockMovementRepository.findByProductId(productId).stream()
                    .map(DtoMapper::toStockMovementResponse)
                    .toList();
        } catch (ResponseStatusException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), exception);
        }
    }

    public StockMovementResponse createStockMovement(CreateStockMovementRequest request) {
        try {
            if (request == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "request body is required");
            }

            if (request.getProductId() == null || request.getProductId().isBlank()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "productId is required");
            }

            if (request.getMovementType() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "movementType is required");
            }

            if (request.getQuantity() <= 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "quantity must be greater than 0");
            }

            Product product = ensureProductExists(request.getProductId());
            StockMovement stockMovement = DtoMapper.toStockMovement(request);
            stockMovement.setId(UUID.randomUUID().toString());
            stockMovement.setCreatedAt(Instant.now());
            stockMovement.setProduct(product);

            stockMovementRepository.save(stockMovement);
            return DtoMapper.toStockMovementResponse(stockMovement);
        } catch (ResponseStatusException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), exception);
        }
    }

    public int getStock(String productId) {
        try {
            ensureProductExists(productId);
            return stockMovementRepository.getStock(productId);
        } catch (ResponseStatusException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), exception);
        }
    }

    private Product ensureProductExists(String productId) throws Exception {
        Product product = productRepository.findById(productId);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }

        return product;
    }

    private MovementType parseMovementType(String type) {
        try {
            return MovementType.valueOf(type.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "type must be in or out");
        }
    }
}
