package com.example.demo.repository;

import com.example.demo.model.MovementType;
import com.example.demo.model.Product;
import com.example.demo.model.StockMovement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StockMovementRepository {

    private final Connection connection;

    public StockMovementRepository(Connection connection) {
        this.connection = connection;
    }

    public List<StockMovement> findByProductId(String productId) throws Exception {
        String sql = """
                SELECT sm.id, sm.created_at, sm.movement_type, sm.quantity,
                       p.id AS product_id, p.name, p.description, p.unit_price
                FROM stock_movement sm
                JOIN product p ON p.id = sm.product_id
                WHERE sm.product_id = ?
                ORDER BY sm.created_at
                """;

        List<StockMovement> movements = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, productId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = new Product(
                            resultSet.getString("product_id"),
                            resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getBigDecimal("unit_price")
                    );

                    StockMovement movement = new StockMovement(
                            resultSet.getString("id"),
                            resultSet.getTimestamp("created_at").toInstant(),
                            MovementType.valueOf(resultSet.getString("movement_type")),
                            resultSet.getInt("quantity"),
                            product
                    );

                    movements.add(movement);
                }
            }
        }

        return movements;
    }

    public List<StockMovement> findByType(MovementType type) throws Exception {
        String sql = """
                SELECT sm.id, sm.created_at, sm.movement_type, sm.quantity,
                       p.id AS product_id, p.name, p.description, p.unit_price
                FROM stock_movement sm
                JOIN product p ON p.id = sm.product_id
                WHERE sm.movement_type = ?
                ORDER BY sm.created_at
                """;

        List<StockMovement> movements = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, type.name());

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = new Product(
                            resultSet.getString("product_id"),
                            resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getBigDecimal("unit_price")
                    );

                    StockMovement movement = new StockMovement(
                            resultSet.getString("id"),
                            resultSet.getTimestamp("created_at").toInstant(),
                            MovementType.valueOf(resultSet.getString("movement_type")),
                            resultSet.getInt("quantity"),
                            product
                    );

                    movements.add(movement);
                }
            }
        }

        return movements;
    }

    public void save(StockMovement stockMovement) throws Exception {
        String sql = """
                INSERT INTO stock_movement
                    (id, movement_type, quantity, product_id)
                VALUES (?, ?::movement_type, ?, ?)
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, stockMovement.getId());
            statement.setString(2, stockMovement.getMovementType().name());
            statement.setInt(3, stockMovement.getQuantity());
            statement.setString(4, stockMovement.getProduct().getId());

            statement.executeUpdate();
        }
    }

    public int getStock(String productId) throws Exception {
        String sql = """
                SELECT COALESCE(
                    SUM(
                        CASE
                            WHEN movement_type = 'IN' THEN quantity
                            WHEN movement_type = 'OUT' THEN -quantity
                        END
                    ),
                    0
                )
                FROM stock_movement
                WHERE product_id = ?
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, productId);

            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1);
            }
        }
    }
}