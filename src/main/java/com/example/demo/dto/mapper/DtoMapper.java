package com.example.demo.dto.mapper;

import com.example.demo.dto.request.CreateStockMovementRequest;
import com.example.demo.dto.response.ProductResponse;
import com.example.demo.dto.response.StockMovementResponse;
import com.example.demo.model.Product;
import com.example.demo.model.StockMovement;

public final class DtoMapper {

    private DtoMapper() {
    }

    public static StockMovement toStockMovement(CreateStockMovementRequest request) {
        if (request == null) {
            return null;
        }

        StockMovement stockMovement = new StockMovement();
        stockMovement.setMovementType(request.getMovementType());
        stockMovement.setQuantity(request.getQuantity());

        Product product = new Product();
        product.setId(request.getProductId());
        stockMovement.setProduct(product);

        return stockMovement;
    }

    public static StockMovementResponse toStockMovementResponse(StockMovement stockMovement) {
        if (stockMovement == null) {
            return null;
        }

        String productId = stockMovement.getProduct() != null
                ? stockMovement.getProduct().getId()
                : null;

        return new StockMovementResponse(
                stockMovement.getId(),
                stockMovement.getCreatedAt(),
                stockMovement.getMovementType(),
                stockMovement.getQuantity(),
                productId
        );
    }

    public static ProductResponse toProductResponse(Product product) {
        if (product == null) {
            return null;
        }

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getUnitPrice()
        );
    }
}